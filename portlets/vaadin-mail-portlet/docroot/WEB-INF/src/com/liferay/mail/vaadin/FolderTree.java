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

import com.liferay.mail.MailException;
import com.liferay.mail.mailbox.Mailbox;
import com.liferay.mail.mailbox.MailboxFactoryUtil;
import com.liferay.mail.model.Account;
import com.liferay.mail.model.Folder;
import com.liferay.mail.model.Message;
import com.liferay.mail.service.AccountLocalServiceUtil;
import com.liferay.mail.service.FolderLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.event.Action;
import com.vaadin.event.DataBoundTransferable;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.event.dd.acceptcriteria.And;
import com.vaadin.event.dd.acceptcriteria.Not;
import com.vaadin.event.dd.acceptcriteria.Or;
import com.vaadin.event.dd.acceptcriteria.SourceIs;
import com.vaadin.event.dd.acceptcriteria.TargetDetailIs;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Henri Sara
 */
public class FolderTree extends Tree implements DropHandler, Action.Handler {

	private static class AccountAction extends Action {

		private final Long accountId;

		public AccountAction(String captionKey, Long accountId) {
			super(Lang.get(captionKey));
			this.accountId = accountId;
		}

		public Long getAccountId() {
			return accountId;
		}

	}

	private static class FolderAction extends Action {

		private final Folder folder;

		public FolderAction(String captionKey, Folder folder) {
			super(Lang.get(captionKey));
			this.folder = folder;
		}

		public Folder getFolder() {
			return folder;
		}

	}

	private FolderChangeListener folderChangedListener;

	private MessageList messageList;

	public FolderTree(FolderChangeListener listener, MessageList msgList) {

		super("");
		this.folderChangedListener = listener;
		this.messageList = msgList;

		setSizeFull();
		setSelectable(true);
		setNullSelectionAllowed(false);
		setImmediate(true);
		setMultiSelect(false);

		// Only add drag&drop support if the msgList is enabled
		if (messageList != null) {
			setDropHandler(this);
		}

		// Initiate folder selected event when a folder is selected
		addListener(new Property.ValueChangeListener() {

			public void valueChange(Property.ValueChangeEvent event) {

				if (folderChangedListener != null) {
					folderChangedListener.selectedFolderChanged(((FolderContainer) getContainerDataSource()).getFolder(event.getProperty().getValue()));
				}
			}

		});

		addActionHandler(this);
	}

	@Override
	public void setContainerDataSource(Container newDataSource) {

		super.setContainerDataSource(newDataSource);

		if (newDataSource instanceof FolderContainer) {
			setItemCaptionPropertyId(FolderContainer.getCaptionPropertyId());
			setItemIconPropertyId(FolderContainer.getIconPropertyId());
		}
	}

	private FolderContainer getFolderContainer() {

		return (FolderContainer) getContainerDataSource();
	}

	public void expand(Long accountId, Long folderId) {

		if (accountId == null) {
			return;
		}
		expandItemsRecursively(getFolderContainer().getAccountItemId(
			accountId));

	}

	public void selectFolder(Long accountId, Long folderId) {

		if (accountId == null || folderId == null) {
			return;
		}
		select(getFolderContainer().getFolderItemId(accountId, folderId));
	}

	public void drop(DragAndDropEvent dropEvent) {

		DataBoundTransferable tr = (DataBoundTransferable) dropEvent
				.getTransferable();

		Object itemId = tr.getItemId();

		// Get the details of the drop target
		TreeTargetDetails details = (TreeTargetDetails) dropEvent
				.getTargetDetails();

		if (rootItemIds().contains(details.getItemIdOver())) {
			// TODO should do with criteria instead
			return;
		}

		// Get the target folder
		Folder targetFolder = getFolderContainer().getFolder(
				details.getItemIdOver());

		// support dropping multiple messages at once
		if (itemId instanceof Message) {
			Message msg = (Message) itemId;
			List<Message> messages = new ArrayList<Message>();
			if (tr.getSourceComponent() instanceof MessageListTable) {
				// possible multi-row drop, need to get selection from table
				MessageListTable table = (MessageListTable) tr.getSourceComponent();
				Collection<Message> value = (Collection) table.getValue();
				messages.addAll(value);
			}
			// single message drop
			if (messages.isEmpty()) {
				messages.add(msg);
			}

			// Move message
			try {
				MessageUtil.moveMessagesTo(messages, targetFolder);
				Controller.get().showInfo(Lang.get("messages-have-been-moved"));
			} catch (PortalException e) {
				Controller.get().showError(Lang.get("unable-to-move-messages"),
						e);
			} catch (SystemException e) {
				Controller.get().showError(Lang.get("unable-to-move-messages"),
						e);
			}

			if (folderChangedListener != null) {
				Folder currentFolder = getFolderContainer().getFolder(
						getValue());
				folderChangedListener.selectedFolderChanged(currentFolder);
			}
		}
	}

	public AcceptCriterion getAcceptCriterion() {
		TargetDetailIs isMiddle = new TargetDetailIs("detail", "MIDDLE");
		SourceIs isFromMessageList = new SourceIs(messageList.getTable());

		List<TargetItemIs> rootItemCriterias = new ArrayList<TargetItemIs>();
		for (Object root : rootItemIds()) {
			rootItemCriterias.add(new TargetItemIs(this, root));
		}
		Or or = new Or(rootItemCriterias.toArray(new TargetItemIs[rootItemCriterias.size()]));
		Not notRootItem = new Not(or);

		return new And(isMiddle, isFromMessageList, notRootItem);
	}

	public Action[] getActions(Object target, Object sender) {
		Folder folder = getFolderContainer().getFolder(target);
		Account account = getFolderContainer().getAccount(target);
		ArrayList<Action> actions = new ArrayList<Action>();
		boolean modifiable = getFolderContainer().isModifiable(target);

		if (account != null && folder == null) {
			actions.add(new AccountAction("create-folder", account.getAccountId()));
		}
		if (folder != null && modifiable) {
			actions.add(new FolderAction("rename-folder", folder));
			actions.add(new FolderAction("delete-folder", folder));
		}

		return actions.toArray(new Action[0]);
	}

	public void handleAction(Action action, Object sender, Object target) {
		if (action instanceof AccountAction) {
			handleAccountAction((AccountAction) action);
		} else if (action instanceof FolderAction) {
			handleFolderAction((FolderAction) action);
		}
		// else do nothing
	}

	public void handleAccountAction(AccountAction action) {
		final Long accountId = action.getAccountId();
		if (accountId == null) {
			return;
		}

		if (Lang.get("create-folder").equals(action.getCaption())) {
			String title = Lang.get("create-folder");
			String message = Lang.get("please-enter-a-folder-name");
			final ConfirmDialog confirm = new ConfirmDialog(
					Lang.get("confirm"), title, message);
			final TextField newNameField = new TextField();
			newNameField.setNullRepresentation("");
			newNameField.setValue("new folder");
			confirm.addExtraComponent(newNameField);

			confirm.addConfirmButtonListener(new ClickListener() {

				public void buttonClick(ClickEvent event) {
					String newName = (String) newNameField.getValue();
					if (newName != null && !"".equals(newName)) {
						confirm.closeDialog();

						addFolder(accountId, newName);

						synchronizeAccount(accountId, Controller.get());
						refresh();
					} else {
						Controller.get().showError(Lang.get("please-enter-a-folder-name"));
					}
				}
			});

			Controller.get().getApplication().getMainWindow().addWindow(confirm);
		}
	}

	public void handleFolderAction(FolderAction action) {
		final Folder folder = action.getFolder();
		if (folder == null) {
			return;
		}

		if (Lang.get("rename-folder").equals(action.getCaption())) {
			String title = Lang.get("rename-folder");
			String message = Lang.get("please-enter-a-new-folder-name");
			final ConfirmDialog confirm = new ConfirmDialog(
					Lang.get("confirm"), title, message);
			final TextField newNameField = new TextField();
			newNameField.setNullRepresentation("");
			newNameField.setValue(folder.getDisplayName());
			confirm.addExtraComponent(newNameField);

			confirm.addConfirmButtonListener(new ClickListener() {

				public void buttonClick(ClickEvent event) {
					String newName = (String) newNameField.getValue();
					if (newName != null && !"".equals(newName)) {
						confirm.closeDialog();

						renameFolder(folder.getFolderId(), newName);

						synchronizeAccount(folder.getAccountId(), Controller.get());
						refresh();
					} else {
						Controller.get().showError(Lang.get("please-enter-a-new-folder-name"));
					}
				}
			});

			Controller.get().getApplication().getMainWindow().addWindow(confirm);
		} else if (Lang.get("delete-folder").equals(action.getCaption())) {
			final ConfirmDialog confirm =
				new ConfirmDialog(
					Lang.get("confirm"), Lang.get("delete-folder"),
					Lang.get("are-you-sure-you-want-to-delete-this-folder"));

			confirm.addConfirmButtonListener(new ClickListener() {

				public void buttonClick(ClickEvent event) {
					confirm.closeDialog();

					deleteFolder(folder.getFolderId());

					synchronizeAccount(folder.getAccountId(), Controller.get());
					refresh();
				}
			});

			Controller.get().getApplication().getMainWindow().addWindow(confirm);
		}
	}

	/**
	 * Refresh the tree contents after a synchronization with the server. No
	 * synchronization is performed in this method.
	 *
	 * The current tree root expansion state and selected folder (if any) are
	 * preserved.
	 */
	public void refresh() {
		ArrayList<Long> expanded = new ArrayList<Long>();
		for (Object root : rootItemIds()) {
			if (isExpanded(root)) {
				Account account = getFolderContainer().getAccount(root);
				if (account != null) {
					expanded.add(account.getAccountId());
				}
			}
		}
		Folder selectedFolder = getFolderContainer().getFolder(getValue());

		getFolderContainer().refresh();

		for (Long accountId : expanded) {
			expandItem(getFolderContainer().getAccountItemId(
					accountId));
		}
		if (selectedFolder != null) {
			Object id = getFolderContainer()
					.getFolderItemId(selectedFolder.getAccountId(),
							selectedFolder.getFolderId());
			select(id);
		}
	}

	/**
	 * Similar to {@link MailManager#addFolder(long, String)}: create a new
	 * folder. Success or error message display is handled inside this method.
	 */
	private void addFolder(long accountId, String displayName) {
		Controller controller = Controller.get();
		try {
			Mailbox mailbox = MailboxFactoryUtil.getMailbox(
				controller.getUser().getUserId(), accountId,
				controller.getPasswordRetriever().getPassword(accountId));

			mailbox.addFolder(displayName);

			controller.showInfo(Lang.get("folder-has-been-created"));
		}
		catch (MailException me) {
			if (me.getType() == MailException.FOLDER_ALREADY_EXISTS) {
				controller.showError(Lang
						.get("a-folder-with-the-same-name-already-exists"));
				return;
			}

			_log.error(me, me);

			controller.showError(Lang.get("unable-to-create-folder"));
		} catch (PortalException e) {
			_log.error(e, e);

			controller.showError(Lang.get("unable-to-create-folder"));
		} catch (SystemException e) {
			_log.error(e, e);

			controller.showError(Lang.get("unable-to-create-folder"));
		}

	}

	/**
	 * Similar to {@link MailManager#renameFolder(long, String)}: rename a
	 * folder. Success or error message display is handled inside this method.
	 */
	private void renameFolder(long folderId, String displayName) {
		Controller controller = Controller.get();

		try {
			Folder folder = FolderLocalServiceUtil.getFolder(folderId);

			Mailbox mailbox = MailboxFactoryUtil.getMailbox(controller
					.getUser().getUserId(), folder.getAccountId(), controller
					.getPasswordRetriever().getPassword(folder.getAccountId()));

			mailbox.renameFolder(folderId, displayName);

			controller.showInfo(Lang.get("folder-renamed-successfully"));
		}
		catch (MailException me) {
			if (me.getType() == MailException.FOLDER_ALREADY_EXISTS) {
				controller.showError(Lang
						.get("a-folder-with-the-same-name-already-exists"));
				return;
			}

			_log.error(me, me);

			controller.showError(Lang.get("unable-to-rename-folder"));
		} catch (PortalException e) {
			_log.error(e, e);

			controller.showError(Lang.get("unable-to-rename-folder"));
		} catch (SystemException e) {
			_log.error(e, e);

			controller.showError(Lang.get("unable-to-rename-folder"));
		}

	}

	/**
	 * Similar to {@link MailManager#deleteFolder(long, String)}: delete a
	 * folder. Success or error message display is handled inside this method.
	 */
	private void deleteFolder(long folderId) {
		Controller controller = Controller.get();

		try {
			Folder folder = FolderLocalServiceUtil.getFolder(folderId);

			Mailbox mailbox = MailboxFactoryUtil.getMailbox(controller
					.getUser().getUserId(), folder.getAccountId(), controller
					.getPasswordRetriever().getPassword(folder.getAccountId()));

			mailbox.deleteFolder(folderId);

			controller.showInfo(Lang.get("folder-has-been-deleted"));
		}
		catch (MailException me) {
			if (me.getType() == MailException.FOLDER_REQUIRED) {
				controller.showError(Lang
						.get("this-is-a-required-folder-and-can-not-be-deleted"));
				return;
			}
			else if (me.getType() == MailException.FOLDER_DELETE_FAILED) {
				controller.showError(Lang
						.get("the-mail-server-will-not-allow-this-folder-to-be-deleted"));
				return;
			}

			_log.error(me, me);

			controller.showError(Lang.get("unable-to-delete-folder"));
		} catch (PortalException e) {
			_log.error(e, e);

			controller.showError(Lang.get("unable-to-delete-folder"));
		} catch (SystemException e) {
			_log.error(e, e);

			controller.showError(Lang.get("unable-to-delete-folder"));
		}

	}

	private void synchronizeAccount(final long accountId, Controller controller) {
		try {
			Account acc = AccountLocalServiceUtil.getAccount(accountId);
			if (acc.isSavePassword()) {
				controller.getMailManager().synchronizeAccount(accountId);
			} else if (controller.getPasswordRetriever().getPassword(accountId) != null) {
				controller.getMailManager().synchronizeAccount(accountId);
			}
		}
		catch (SystemException e) {
			controller.showError(
				Lang.get("unable-to-synchronize-account"), e);
		}
		catch (PortalException e) {
			controller.showError(
				Lang.get("unable-to-synchronize-account"), e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(FolderTree.class);

}