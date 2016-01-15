/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.mail.imap;

import com.liferay.mail.exception.MailException;
import com.liferay.mail.model.Account;
import com.liferay.mail.util.PortletPropsValues;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;

import javax.net.ssl.SSLSocketFactory;

import org.apache.commons.lang.time.StopWatch;

/**
 * @author Scott Lee
 */
public class IMAPConnection {

	public IMAPConnection(Account account, String password) {
		this(
			account.getIncomingHostName(), account.getIncomingPort(),
			account.getIncomingSecure(), account.getOutgoingHostName(),
			account.getOutgoingPort(), account.getOutgoingSecure(),
			account.getLogin(), password);
	}

	public IMAPConnection(
		String incomingHostName, int incomingPort, boolean incomingSecure,
		String outgoingHostName, int outgoingPort, boolean outgoingSecure,
		String login, String password) {

		_incomingHostName = incomingHostName;
		_incomingPort = incomingPort;
		_incomingSecure = incomingSecure;
		_outgoingHostName = outgoingHostName;
		_outgoingPort = outgoingPort;
		_outgoingSecure = outgoingSecure;
		_login = login;
		_password = password;
	}

	public Session getSession() {
		if (_session != null) {
			return _session;
		}

		Properties properties = new Properties();

		properties.put(
			"mail.debug", String.valueOf(PortletPropsValues.JAVAMAIL_DEBUG));
		properties.put("mail.imap.host", _incomingHostName);
		properties.put("mail.imap.port", _incomingPort);
		properties.put("mail.imaps.auth", "true");
		properties.put("mail.imaps.host", _incomingHostName);
		properties.put("mail.imaps.port", _incomingPort);
		properties.put(
			"mail.imaps.socketFactory.class", SSLSocketFactory.class.getName());
		properties.put("mail.imaps.socketFactory.fallback", "false");
		properties.put("mail.imaps.socketFactory.port", _incomingPort);
		properties.put("mail.smtp.host", _outgoingHostName);
		properties.put("mail.smtp.port", _outgoingPort);
		properties.put("mail.smtps.auth", "true");
		properties.put("mail.smtps.host", _outgoingHostName);
		properties.put("mail.smtps.port", _outgoingPort);
		properties.put(
			"mail.smtps.socketFactory.class", SSLSocketFactory.class.getName());
		properties.put("mail.smtps.socketFactory.fallback", "false");
		properties.put("mail.smtps.socketFactory.port", _outgoingPort);

		_session = Session.getInstance(properties);

		_session.setDebug(PortletPropsValues.JAVAMAIL_DEBUG);

		return _session;
	}

	public Store getStore(boolean useOldStores) throws MailException {
		Store store = null;

		try {
			String storeKey = _incomingHostName.concat(
				_outgoingHostName).concat(_login);

			if (useOldStores) {
				store = _allStores.get(storeKey);

				if ((store != null) && !store.isConnected()) {
					store.close();

					store = null;
				}
			}

			if (store == null) {
				Session session = getSession();

				if (_incomingSecure) {
					store = session.getStore("imaps");
				}
				else {
					store = session.getStore("imap");
				}

				store.connect(
					_incomingHostName, _incomingPort, _login, _password);

				if (useOldStores) {
					_allStores.put(storeKey, store);
				}
			}

			return store;
		}
		catch (MessagingException me) {
			throw new MailException(
				MailException.ACCOUNT_INCOMING_CONNECTION_FAILED, me);
		}
	}

	public Transport getTransport() throws MailException {
		Transport transport = null;

		try {
			Session session = getSession();

			if (_outgoingSecure) {
				transport = session.getTransport("smtps");
			}
			else {
				transport = session.getTransport("smtp");
			}

			transport.connect(
				_outgoingHostName, _outgoingPort, _login, _password);

			return transport;
		}
		catch (MessagingException me) {
			throw new MailException(
				MailException.ACCOUNT_OUTGOING_CONNECTION_FAILED, me);
		}
	}

	public void testConnection() throws MailException {
		MailException mailException = null;

		boolean failedIncomingConnection = false;

		try {
			testIncomingConnection();
		}
		catch (MailException me) {
			mailException = me;

			failedIncomingConnection = true;
		}

		boolean failedOutgoingConnection = false;

		try {
			testOutgoingConnection();
		}
		catch (MailException me) {
			mailException = me;

			failedOutgoingConnection = true;
		}

		if (failedIncomingConnection && failedOutgoingConnection) {
			throw new MailException(
				MailException.ACCOUNT_CONNECTIONS_FAILED, mailException);
		}
		else if (failedIncomingConnection) {
			throw new MailException(
				MailException.ACCOUNT_INCOMING_CONNECTION_FAILED,
				mailException);
		}
		else if (failedOutgoingConnection) {
			throw new MailException(
				MailException.ACCOUNT_OUTGOING_CONNECTION_FAILED,
				mailException);
		}
	}

	protected void testIncomingConnection() throws MailException {
		StopWatch stopWatch = new StopWatch();

		stopWatch.start();

		try {
			Store store = getStore(false);

			store.close();
		}
		catch (Exception e) {
			throw new MailException(
				MailException.ACCOUNT_INCOMING_CONNECTION_FAILED, e);
		}
		finally {
			if (_log.isDebugEnabled()) {
				stopWatch.stop();

				_log.debug(
					"Testing incoming connection completed in " +
						stopWatch.getTime() + " ms");
			}
		}
	}

	protected void testOutgoingConnection() throws MailException {
		StopWatch stopWatch = new StopWatch();

		stopWatch.start();

		try {
			Transport transport = getTransport();

			transport.isConnected();

			transport.close();
		}
		catch (Exception e) {
			throw new MailException(
				MailException.ACCOUNT_OUTGOING_CONNECTION_FAILED, e);
		}
		finally {
			if (_log.isDebugEnabled()) {
				stopWatch.stop();

				_log.debug(
					"Testing outgoing connection completed in " +
						stopWatch.getTime() + " ms");
			}
		}
	}

	private static final String _TRANSPORT = "_TRANSPORT_";

	private static Log _log = LogFactoryUtil.getLog(IMAPConnection.class);

	private static ConcurrentHashMap<String, Store> _allStores =
		new ConcurrentHashMap<>();

	private String _incomingHostName;
	private int _incomingPort;
	private boolean _incomingSecure;
	private String _login;
	private String _outgoingHostName;
	private int _outgoingPort;
	private boolean _outgoingSecure;
	private String _password;
	private Session _session;

}