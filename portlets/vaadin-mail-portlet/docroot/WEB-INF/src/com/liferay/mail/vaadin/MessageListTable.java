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

import com.liferay.mail.model.Folder;
import com.liferay.mail.model.Message;
import com.liferay.mail.service.AccountLocalServiceUtil;
import com.liferay.mail.vaadin.Controller.MessageModifiedListener;

import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.event.Action.Handler;
import com.vaadin.event.Action;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Table;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Henri Sara
 */
public class MessageListTable extends Table
	implements Handler, MessageModifiedListener {

	private Object[] visibleColumns = new Object[] {
		"subject", "sender", "sentDate", "size"
	};
	private Object[] sentFolderVisibleColumns = new Object[] {
		"subject", "recipientsTo", "sentDate", "size"
	};

	private MessageList messageList;

	private Message mostSelected = null;

	private MessageAction markAsRead = new MessageAction("mark-as-read");
	private MessageAction markAsUnread = new MessageAction("mark-as-unread");
	private MessageAction markImportant =
		new MessageAction("flag-as-important");
	private MessageAction clearImportant = new MessageAction("remove-flag");

	public MessageListTable(MessageList msgList) {

		messageList = msgList;

		Controller.get().addListener(this);

		setPageLength(10);
		setStyleName("borderless");
		setHeight("100.0%");
		setWidth("100.0%");

		// Add D&D support
		setDragMode(TableDragMode.MULTIROW);

		setImmediate(true);
		setMultiSelect(true);
		setSelectable(false);

		addListener(new ItemClickListener() {

			public void itemClick(ItemClickEvent event) {

				if (event.getButton() != ItemClickEvent.BUTTON_LEFT) {
					return;
				}
				Message id = (Message) event.getItemId();
				if (event.isCtrlKey()) {
					if (toggleSelected(id)) {
						mostSelected = id;
					}
				}
				else if (event.isShiftKey()) {
					if (mostSelected == null) {
						mostSelected = id;
					}
					selectRange(mostSelected, id);
					mostSelected = id;
				}
				else {
					mostSelected = id;
					HashSet<Object> selected = new HashSet<Object>();
					selected.add(id);
					if (!selected.equals(getValue())) {
						setValue(selected);
					}
				}
				messageList.showMessage(getSelectedMessage());
			}

		});

		setNullSelectionAllowed(true);
		addListener(new ValueChangeListener() {

			public void valueChange(
				Property.ValueChangeEvent event) {

				messageList.showMessage(getSelectedMessage());

			}

		});

		addActionHandler(this);
	}

	private void selectRange(Message startMessage, Message endMessage) {

		Container.Indexed container =
			(Container.Indexed) getContainerDataSource();
		HashSet<Object> range = new HashSet<Object>();

		int idx = container.indexOfId(startMessage);
		int end = container.indexOfId(endMessage);
		if (idx > end) {
			int t = idx;
			idx = end;
			end = t;
			range.add(endMessage);
		}
		else {
			range.add(startMessage);
		}

		while (idx < end) {
			Object id = container.getIdByIndex(++idx);
			range.add(id);
		}

		Set<Message> selected = (Set<Message>) getValue();
		if (selected != null && selected.containsAll(range)) {
			// unselect
			HashSet<Message> newSelection = new HashSet<Message>();
			newSelection.addAll(selected);
			newSelection.removeAll(range);
			setValue(newSelection);
		}
		else {
			setValue(range);
		}
	}

	private boolean toggleSelected(Message message) {

		HashSet<Message> select = new HashSet<Message>();
		Set<Message> selected = (Set<Message>) getValue();
		if (selected != null) {
			for (Message i : selected) {
				if (i != message) {
					select.add(i);
				}
			}
		}
		boolean retval = false;
		if (!isSelected(message)) {
			select.add(message);
			retval = true;
		}
		setValue(select);
		return retval;
	}

	public Action[] getActions(Object target, Object sender) {

		Message msg = (Message) target;
		boolean seen = MessageUtil.isSeen(msg);
		boolean important = MessageUtil.isImportant(msg);

		Action[] actions = new Action[2];
		if (seen) {
			actions[0] = markAsUnread;
		}
		else {
			actions[0] = markAsRead;
		}

		if (important) {
			actions[1] = clearImportant;
		}
		else {
			actions[1] = markImportant;
		}

		return actions;
	}

	public void handleAction(Action action, Object sender, Object target) {

		if (!(action instanceof MessageAction)) {
			return;
		}

		if (target == null) {
			return;
		}

		MessageAction messageAction = (MessageAction) action;
		String actionId = messageAction.getId();
		try {
			if (actionId.equals("mark-as-read")) {
				MessageUtil.markMessageRead((Message) target, true);
			}
			else if (actionId.equals("mark-as-unread")) {
				MessageUtil.markMessageRead((Message) target, false);
			}
			else if (actionId.equals("flag-as-important")) {
				MessageUtil.flagMessageAsImportant((Message) target, true);
			}
			else if (actionId.equals("remove-flag")) {
				MessageUtil.flagMessageAsImportant((Message) target, false);
			}
		}
		catch (Exception e) {
			Controller.get().showError(Lang.get("unable-to-flag-messages"), e);
		}
	}

	public void showMessages(Long accountId, Folder folder) {

		boolean first = !(getContainerDataSource() instanceof MessageContainer);

		try {
			if (accountId == null || folder == null) {
				setVisible(false);
				return;
			}
			else {
				setVisible(true);
			}

			setEnabled(true);

			MessageContainer messageContainer = new MessageContainer(accountId, folder);

			setContainerDataSource(messageContainer);
			boolean sentFolder = AccountLocalServiceUtil.getAccount(accountId)
					.getSentFolderId() == folder.getFolderId();

			// Disabled for now because of an unknown problem
			sentFolder = false;
			if (sentFolder) {
				setVisibleColumns(sentFolderVisibleColumns);
			}
			else {
				setVisibleColumns(visibleColumns);
			}
			setColumnHeaders(getColumnNames(sentFolder));
			setColumnExpandRatio("subject", 1);
			setRowHeaderMode(ROW_HEADER_MODE_ICON_ONLY);
			setItemIconPropertyId("icon");

			if (first) {
				setSortContainerPropertyId("sentDate");
				setSortAscending(false);
			}
		}
		catch (Exception e) {
			setEnabled(false);
			Controller.get().showError(Lang.get("unable-to-access-account"), e);
		}
	}

	private String[] getColumnNames(boolean sentFolder) {

		String subject = Lang.get("subject");
		String fromTo;
		if (sentFolder) {
			fromTo = Lang.get("recipient");
		}
		else {
			fromTo = Lang.get("sender");
		}
		String sentDate = Lang.get("date");
		String size = Lang.get("size");
		return new String[] {
			subject, fromTo, sentDate, size
		};
	}

	public List<Message> getSelectedMessages() {

		Set<Message> selected = (Set<Message>) getValue();
		ArrayList<Message> res = new ArrayList<Message>();
		res.addAll(selected);
		return res;
	}

	/**
	 * @return selected message, <code>null</code> if many are selected
	 */
	public Message getSelectedMessage() {

		Set<Message> selected = (Set<Message>) getValue();
		if (mostSelected == null || selected.isEmpty()) {
			return null;
		}
		if (!isSelected(mostSelected)) {
			mostSelected = selected.iterator().next();
		}
		return mostSelected;
	}

	public class MessageAction extends Action {

		private String id;

		public String getId() {

			return id;
		}

		public void setId(String id) {

			this.id = id;
		}

		public MessageAction(String id) {

			super(Lang.get(id));
			this.id = id;
		}

	}

	public void messageModified(long messageId) {

		try {
			MessageContainer container = getMessageContainer();
			if (container != null) {
				container.refreshMessageFlags(messageId);
			}
		}
		catch (Exception e) {
			Controller.get().showError(Lang.get("unable-to-access-account"), e);
		}
	}

	private MessageContainer getMessageContainer() {
		Container c = getContainerDataSource();
		if (c instanceof MessageContainer) {
			return (MessageContainer) getContainerDataSource();
		}
		return null;
	}

	public void messagesAddedOrRemoved() {

		MessageContainer container = getMessageContainer();
		if (container != null) {
			container.refreshMessages();
		}
	}

}