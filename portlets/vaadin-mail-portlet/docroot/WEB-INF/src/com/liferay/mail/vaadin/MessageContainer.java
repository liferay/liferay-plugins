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

import com.liferay.mail.mailbox.Mailbox;
import com.liferay.mail.model.Attachment;
import com.liferay.mail.model.Folder;
import com.liferay.mail.model.Message;
import com.liferay.mail.model.MessagesDisplay;
import com.liferay.mail.model.impl.MessageImpl;
import com.liferay.mail.service.AttachmentLocalServiceUtil;
import com.liferay.mail.service.MessageLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import com.vaadin.data.Container.PropertySetChangeNotifier;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.terminal.Resource;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Label;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Henri Sara
 */
public class MessageContainer extends BeanItemContainer<Message> implements PropertySetChangeNotifier {

	private static Log _log = LogFactoryUtil.getLog(MessageContainer.class);

	private List<PropertySetChangeListener> propertySetChangeListeners = new ArrayList<PropertySetChangeListener>();

	private static final int PAGE_SIZE = 20;

	private int totalMessages = 0;

	private long accountId;

	private Folder folder;

	// private Mailbox mailbox;

	private Map<Message, Boolean> hasAttachments =
		new HashMap<Message, Boolean>();

	private final Resource attachmentIcon = new PortletResource("images/vaadin/icons/clip.png");

	public MessageContainer(long accountEntryId, Folder folder) {

		super(MessageImpl.class);

		this.accountId = accountEntryId;
		this.folder = folder;

		try {
			updateSize();
			fetchMessages(0);
		}
		catch (PortalException e) {
			_log.warn("Could not fetch messages", e);
		}
		catch (SystemException e) {
			_log.warn("Could not fetch messages", e);
		}

	}

	private void fetchMessages(int messageIndex)
		throws PortalException, SystemException {

		int pageNumber = 1 + messageIndex / PAGE_SIZE;

		List<Message> messages = new ArrayList<Message>();

		String orderByField = "";
		String orderByType = "";
		String keywords = "";

		if (folder != null) {
			Mailbox mailbox = MessageUtil.getMailbox(accountId);

			try{
			MessagesDisplay messagesDisplay = mailbox.getMessagesDisplay(folder
					.getFolderId(), keywords, pageNumber, PAGE_SIZE,
					orderByField, orderByType);
			messages = messagesDisplay.getMessages();
			} catch(Exception e) {
				_log.error("Failed loading messages from Mailbox");
			}
		}

		int count = messages.size();
		int expected = PAGE_SIZE;
		if (count < (messageIndex + PAGE_SIZE)) {
			// subtract the beginning of the page with messageIndex
			expected = count - (pageNumber - 1) * PAGE_SIZE;
		}

		if (messages.size() != expected) {
			_log.error("Expected " + expected + ", got " + messages.size());
		}
		int index = (pageNumber - 1) * PAGE_SIZE;
		for (Message message : messages) {
			List<Attachment> attachments = AttachmentLocalServiceUtil
					.getAttachments(message.getMessageId());
			hasAttachments.put(message, attachments != null
					&& !attachments.isEmpty());
			addItemAt(index++, message);
		}
	}

	@Override
	public int size() {

		return totalMessages;
	}

	public void updateSize() {

		try {
			totalMessages =
				MessageLocalServiceUtil.getFolderMessagesCount(folder.getFolderId());
		}
		catch (Exception e) {
			Controller.get().showUnexpectedError(e);
		}
	}

	@Override
	public Message getIdByIndex(int index) {

		if (index >= super.size()) {
			try {
				fetchMessages(index);
			}
			catch (PortalException e) {
				_log.warn("Could not fetch messages", e);
			}
			catch (SystemException e) {
				_log.warn("Could not fetch messages", e);
			}
		}

		return super.getIdByIndex(index);
	}

	@Override
	public Class<?> getType(Object propertyId) {

		if (propertyId.equals("icon")) {
			return Resource.class;
		}
		else if (propertyId.equals("selectbox")) {
			return CheckBox.class;
		}
		else if (propertyId.equals("subject")) {
			return Label.class;
		}
		else {
			return super.getType(propertyId);
		}
	}

	@Override
	public Property getContainerProperty(Object itemId, Object propertyId) {

		Message m = (Message) itemId;
		if (propertyId.equals("icon")) {
			Boolean hasAttachments = this.hasAttachments.get(m);

			if (hasAttachments == null) {
				return null;
			}
			else if (hasAttachments) {
				return new ObjectProperty(attachmentIcon);
			}
			else {
				return null;
			}
		}

		Property p = super.getContainerProperty(itemId, propertyId);

		// Override the formatting of some columns
		if (propertyId.equals("sentDate")) {
			return formatDate((Date) p.getValue());
		}
		else if (propertyId.equals("size")) {
			return formatSize((Long) p.getValue());
		}
		else if (propertyId.equals("subject")) {
			return formatSubject(m, (String) p.getValue());
		}
		else {
			return p;
		}
	}

	private Property formatSubject(Message m, String value) {

		if (value == null) {
			return null;
		}

		Label l = new Label(value, Label.CONTENT_XHTML);

		if (!MessageUtil.isSeen(m)) {
			l.addStyleName(MessageList.STYLE_NOT_SEEN);
		}
		if (MessageUtil.isImportant(m)) {
			l.addStyleName(MessageList.STYLE_IMPORTANT);
		}

		l.setWidth(null);
		return new ObjectProperty(l);
	}

	private Property formatSize(Long value) {

		if (value == null) {
			return null;
		}

		String size =
			MessageUtil.formatSize(value, Controller.get().getUserLocale());
		return new ObjectProperty(size);
	}

	private Property formatDate(Date date) {

		if (date == null) {
			return null;
		}
		return new ObjectProperty(DateFormat.getDateInstance().format(date));
	}

	public void refreshMessageFlags(long messageId)
		throws PortalException, SystemException {

		Message newMsg = MessageLocalServiceUtil.getMessage(messageId);
		BeanItem<Message> item = getItem(newMsg);
		if (item != null) {
			item.getItemProperty("flags").setValue(newMsg.getFlags());
		}

		notifyPropertySetChangeListeners();
	}

	public void refreshMessages() {

		updateSize();
		super.removeAllItems();
	}

	public void addListener(PropertySetChangeListener listener) {
		this.propertySetChangeListeners.add(listener);

	}

	public void removeListener(PropertySetChangeListener listener) {
		this.propertySetChangeListeners.remove(listener);

	}

	protected void notifyPropertySetChangeListeners() {
		for (PropertySetChangeListener listener : propertySetChangeListeners) {
			listener.containerPropertySetChange(new PropertySetChangeEvent() {
				public Container getContainer() {
					return MessageContainer.this;
				}
			});
		}
	}

}