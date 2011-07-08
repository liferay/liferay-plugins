/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.mail.vaadin;

import com.liferay.mail.mailbox.PasswordRetriever;
import com.liferay.mail.model.Message;
import com.liferay.mail.util.MailManager;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;

import com.vaadin.Application;
import com.vaadin.service.ApplicationContext.TransactionListener;
import com.vaadin.ui.Window.Notification;

import java.util.Locale;
import java.util.Map;
import java.util.WeakHashMap;

import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Henri Sara
 */
public class Controller {

	private static Log _log = LogFactoryUtil.getLog(Controller.class);

	private MailApplication mailApplication;
	private User user;
	private PortletPreferences preferences;
	private MailManager mailMgr;
	private PasswordRetriever passwordRetriever;
	private AccountManager accountMgr;
	private ConfigurationManager configMgr;
	private Map<MessageModifiedListener, Object> listeners =
		new WeakHashMap<MessageModifiedListener, Object>();
	// a value for the listener map entries
	private Object present = new Object();
	private PortletConfig config;

	private static TransactionListener tl;
	private static ThreadLocal<Controller> controller =
		new ThreadLocal<Controller>();

	public Controller(MailApplication mailApplication) {

		this.mailApplication = mailApplication;

		tl = new TransactionListener() {

			public void transactionStart(
				Application application, Object transactionData) {

				if (application instanceof MailApplication) {
					Controller ctr =
						((MailApplication) application).getController();
					controller.set(ctr);
				}
				else {
					// This should not happen
					controller.set(null);
				}

			}

			public void transactionEnd(
				Application application, Object transactionData) {

			}
		};

		this.mailApplication.getContext().addTransactionListener(tl);

	}

	public void setUser(User newUser) {

		user = newUser;
	}

	/**
	 * Sets the current user based on the request and initializes any other
	 * request-specific classes such as MailManager.
	 */
	public void setRequest(PortletRequest request) {
		user = null;
		try {
			user = PortalUtil.getUser(request);
		} catch (PortalException e) {
			_log.debug(e);
		} catch (SystemException e) {
			_log.debug(e);
		}

		// get HTTP request from the portlet request
		HttpServletRequest httpRequest = PortalUtil
				.getHttpServletRequest(request);

		// get PortletConfig
		config = (PortletConfig)request.getAttribute(
			JavaConstants.JAVAX_PORTLET_CONFIG);

		// create MailManager
		passwordRetriever = new PasswordRetriever(httpRequest);
		mailMgr = new MailManager(user, passwordRetriever, config);
	}

	public static Controller get() {

		return controller.get();
	}

	public User getUser() {

		return user;
	}

	public PortletConfig getPortletConfig() {
		return config;
	}

	public MailApplication getApplication() {

		return mailApplication;
	}

	public void setPreferences(PortletPreferences preferences) {

		this.preferences = preferences;
	}

	public PortletPreferences getPreferences() {

		return preferences;
	}

	public AccountManager getAccountManager() {

		if (accountMgr == null) {
			accountMgr = new AccountManager();
		}
		return accountMgr;
	}

	public ConfigurationManager getConfigurationManager() {

		if (configMgr == null) {
			configMgr = new ConfigurationManager();
		}
		return configMgr;
	}

	/**
	 * Returns the mail manager for the request or <code>null</code> if not
	 * initialized.
	 *
	 * @return MailManager for current request, <code>null</code> if none
	 */
	public MailManager getMailManager() {
		return mailMgr;
	}

	/**
	 * Returns the password retriever for the current request, <code>null</code>
	 * if not set.
	 */
	public PasswordRetriever getPasswordRetriever() {
		return passwordRetriever;
	}

	public Locale getUserLocale() {

		return getUser().getLocale();
	}

	public void showInfo(String string) {

		getApplication().getMainWindow().showNotification(
			string, Notification.TYPE_HUMANIZED_MESSAGE);
	}

	public void showError(String string, Throwable t) {

		// if exception given, log as error
		showError(string);
		_log.error(string, t);
	}

	public void showError(String string) {

		getApplication().getMainWindow().showNotification(
			string, Notification.TYPE_ERROR_MESSAGE);
	}

	public interface MessageModifiedListener {

		void messageModified(long messageId);

		void messagesAddedOrRemoved();

	}

	public void addListener(MessageModifiedListener listener) {

		listeners.put(listener, present);
	}

	public void removeListener(MessageModifiedListener listener) {

		listeners.remove(listener);
	}

	void notifyMessagesAddedOrRemovedListeners() {

		/*
		 * Apparently there is a delay somewhere in the indexer which makes it
		 * return the wrong messages so we wait here for a while...
		 */
		try {
			Thread.sleep(500);
		}
		catch (InterruptedException e) {
			_log.debug(e);
		}

		for (MessageModifiedListener listener : listeners.keySet()) {
			listener.messagesAddedOrRemoved();
		}
	}

	void notifyMessageModifyListeners(long messageId) {

		for (MessageModifiedListener listener : listeners.keySet()) {
			listener.messageModified(messageId);
		}
	}

	public Composer replyInComposer(Message originalMessage, boolean replyAll) {

		String newMessageTo = originalMessage.getSender();
		String newMessageCc = "";
		if (replyAll) {
			String to = originalMessage.getTo();
			String cc = originalMessage.getCc();
			if (to != null && !to.equals("")) {
				newMessageCc = to;
			}
			if (cc != null && !cc.equals("")) {
				if (!newMessageCc.equals("")) {
					newMessageCc += ", ";
				}
				newMessageCc += cc;
			}
		}

		String newMessageSubject = "Re: " + originalMessage.getSubject();
		String newMessageBody = MessageUtil.createReplyMessage(originalMessage);

		Composer c = compose(
				newMessageTo, newMessageCc, newMessageSubject, newMessageBody);
		c.focusBody();
		return c;
	}

	public Composer forwardInComposer(Message originalMessage) {
		String newMessageSubject = "Fwd: " + originalMessage.getSubject();
		String newMessageBody =
			MessageUtil.createForwardMessage(originalMessage);

		Composer c = compose(null, null, newMessageSubject, newMessageBody);
		c.focusToField();
		return c;
	}

	public Composer openDraftInComposer(Message originalMessage) {

		String newMessageTo = originalMessage.getTo();
		String newMessageCc = originalMessage.getCc();
		String newMessageBcc = originalMessage.getBcc();

		String newMessageSubject = originalMessage.getSubject();
		String newMessageBody = originalMessage.getBody();

		Composer composer =
			compose(
			newMessageTo, newMessageCc, newMessageBcc, newMessageSubject,
			newMessageBody);

		composer.setDraftMessageId(originalMessage.getMessageId());

		return composer;

	}

	private Composer compose(String to, String cc, String subject, String body) {
		return compose(to, cc, "", subject, body);
	}

	private Composer compose(
		String to, String cc, String bcc, String subject, String body) {

		Composer composer = new Composer();

		composer.setSubject(subject==null?"":subject);
		composer.setMessage(body==null?"":body);
		composer.setTo(to==null?"":to);
		composer.setCc(cc==null?"":cc);
		composer.setBcc(bcc==null?"":bcc);

		getApplication().switchToCompose(composer, null);

		return composer;
	}

	public void showUnexpectedError(Exception e) {

		showError(Lang.get("an-unexpected-error-occurred"), e);
		_log.error("Unexpected error", e);
	}
}