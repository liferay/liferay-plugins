/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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
import com.liferay.mail.model.Account;
import com.liferay.mail.model.Folder;
import com.liferay.mail.model.Message;
import com.liferay.mail.service.AccountLocalServiceUtil;
import com.liferay.mail.vaadin.Composer.ComposerListener;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import com.vaadin.terminal.Resource;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupView.PopupVisibilityEvent;
import com.vaadin.ui.PopupView.PopupVisibilityListener;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.VerticalLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Henri Sara
 */
public class MessageToolbar extends HorizontalLayout {

	private static Log _log = LogFactoryUtil.getLog(MessageToolbar.class);

	private static final Resource ICON_EDIT_DRAFT =
		new PortletResource("images/vaadin/icons/edit_draft.png");
	private static final Resource ICON_REPLY_ALL =
		new PortletResource("images/vaadin/icons/reply_all.png");
	private static final Resource ICON_REPLY =
		new PortletResource("images/vaadin/icons/reply.png");
	private static final Resource ICON_FORWARD =
		new PortletResource("images/vaadin/icons/forward.png");
	private static final Resource ICON_DELETE =
		new PortletResource("images/vaadin/icons/delete.png");
	private static final Resource ICON_MOVE_TO =
		new PortletResource("images/vaadin/icons/move.png");
	private static final Resource ICON_REFRESH =
		new PortletResource("images/vaadin/icons/reload.png");

	private int moveToIndex = 4;

	private Button openDraft;
	private Button reply;
	private Button replyAll;
	private Button forward;
	private Button delete;
	private Button moveTo;
	private Button refresh;

	// ID of the account currently shown in the view
	private Long currentAccountId;

	private MainMailView mainMailView;

	private String TOOLBAR_BUTTON_STYLE = "toolbar";

	public MessageToolbar(MainMailView mainMailView) {

		this.mainMailView = mainMailView;

		openDraft = new NativeButton(Lang.get("edit-draft"), new ClickListener() {

			public void buttonClick(ClickEvent event) {

				openDraft();
			}
		});
		openDraft.setIcon(ICON_EDIT_DRAFT);
		openDraft.setStyleName(TOOLBAR_BUTTON_STYLE);
		reply = new NativeButton(Lang.get("reply"), new ClickListener() {

			public void buttonClick(ClickEvent event) {

				reply(false);
			}
		});
		reply.setIcon(ICON_REPLY);
		reply.setStyleName(TOOLBAR_BUTTON_STYLE);
		replyAll = new NativeButton(Lang.get("reply-all"), new ClickListener() {

			public void buttonClick(ClickEvent event) {

				reply(true);
			}
		});
		replyAll.setIcon(ICON_REPLY_ALL);
		replyAll.setStyleName(TOOLBAR_BUTTON_STYLE);
		forward = new NativeButton(Lang.get("forward"), new ClickListener() {

			public void buttonClick(ClickEvent event) {

				forward();
			}
		});
		forward.setIcon(ICON_FORWARD);
		forward.setStyleName(TOOLBAR_BUTTON_STYLE);
		delete = new NativeButton(Lang.get("delete"), new ClickListener() {

			public void buttonClick(ClickEvent event) {

				delete();
			}
		});
		delete.setIcon(ICON_DELETE);
		delete.setStyleName(TOOLBAR_BUTTON_STYLE);
		moveTo = new NativeButton(Lang.get("move-to"), new ClickListener() {

			public void buttonClick(ClickEvent event) {

				selectMoveTarget();
			}
		});
		moveTo.setIcon(ICON_MOVE_TO);
		moveTo.setStyleName(TOOLBAR_BUTTON_STYLE);

		refresh = new NativeButton(Lang.get("refresh"), new ClickListener() {

			public void buttonClick(ClickEvent event) {

				refresh();
			}
		});
		refresh.setIcon(ICON_REFRESH);
		refresh.setStyleName(TOOLBAR_BUTTON_STYLE);

		updateToolbar(null, null);

		setStyleName("toolbar");
	}

	public void updateToolbar(Long accountId, Folder folder) {

		currentAccountId = accountId;

		boolean drafts = isDraftFolder(accountId, folder);

		removeAllComponents();

		if (drafts) {
			addComponent(openDraft);
		}
		else {
			addComponent(reply);
			addComponent(replyAll);
			addComponent(forward);
			moveToIndex = 4;
			addComponent(moveTo);
		}
		addComponent(delete);

		Label spacer = new Label("");
		addComponent(spacer);

		setExpandRatio(spacer, 1);

		addComponent(refresh);
	}

	private boolean isDraftFolder(Long accountId, Folder folder) {

		boolean drafts = false;

		if (accountId != null && folder != null) {
			try {
				Account account = AccountLocalServiceUtil.getAccount(accountId);
				drafts = (account.getDraftFolderId() == folder.getFolderId());
			}
			catch (SystemException e) {
				// show normal mode and not draft mode if there was a problem
				_log.warn("Could not get draft folder", e);
			}
			catch (PortalException e) {
				// show normal mode and not draft mode if there was a problem
				_log.warn("Could not get draft folder", e);
			}
		}
		return drafts;
	}

	protected void delete() {

		List<Message> messages = mainMailView.getSelectedMessages();
		if (messages.isEmpty()) {
			Controller.get().showError(Lang.get("no-messages-selected"));
			return;
		}

		try {
			MessageUtil.deleteMessages(messages);

			Controller.get().showInfo(Lang.get("messages-have-been-deleted"));
		}
		catch (Exception e) {
			Controller.get().showError(Lang.get("unable-to-delete-messages"), e);
		}

	}

	protected void selectMoveTarget() {

		// Ensure a mail is selected
		List<Message> message = mainMailView.getSelectedMessages();
		if (message.isEmpty()) {
			Controller.get().showInfo(Lang.get("no-messages-selected"));
			return;
		}

		Account account;
		try {
			account = MessageUtil.getAccountForMessage(message.get(0));
		}
		catch (Exception e) {
			Controller.get().showError(Lang.get("unable-to-move-messages"), e);
			return;
		}

		VerticalLayout panelLayout = new VerticalLayout();
		panelLayout.setMargin(false, true, true, false);
		panelLayout.setSizeUndefined();

		Panel p = new Panel(panelLayout);
		p.setSizeUndefined();

		final PopupView popupView = new PopupView("", p);

		FolderTree destinationTree = new FolderTree(new FolderChangeListener() {

			public void selectedFolderChanged(Folder folder) {

				if (folder != null) {
					popupView.setPopupVisible(false);
					Controller controller = Controller.get();
					try {
						MessageUtil.moveMessagesTo(
							mainMailView.getSelectedMessages(), folder);

						controller.showInfo(
							Lang.get("messages-have-been-moved"));
					}
					catch (MailException me) {
						if (me.getType() == MailException.FOLDER_INVALID_DESTINATION) {
							controller.showError(Lang
									.get("cannot-move-messages-to-this-folder"));
						}
					}
					catch (PortalException e) {
						controller.showError(
							Lang.get("unable-to-move-messages"), e);
					}
					catch (SystemException e) {
						controller.showError(
							Lang.get("unable-to-move-messages"), e);
					}

				}
			}
		}, null);

		p.addComponent(destinationTree);

		popupView.setPopupVisible(true);
		popupView.addListener(new PopupVisibilityListener() {

			public void popupVisibilityChange(PopupVisibilityEvent event) {

				// Remove popupview from layout when it has been closed
				if (!event.isPopupVisible()) {
					removeComponent(event.getPopupView());

				}
			}
		});

		// Set tree properties
		List<Account> accounts = new ArrayList<Account>();
		accounts.add(account);
		final FolderContainer folderContainer = new FolderContainer(accounts);

		destinationTree.setContainerDataSource(folderContainer);
		destinationTree.expandItemsRecursively(destinationTree.rootItemIds().iterator().next());

		addComponent(popupView, moveToIndex);

	}

	protected void forward() {

		// Ensure a mail is selected
		Message message = mainMailView.getSelectedMessage();
		if (message == null) {
			Controller.get().showInfo(Lang.get("no-messages-selected"));
			return;
		}

		try {
			message = MessageUtil.getFullMessage(message, true);
		} catch (SystemException e) {
			Controller.get().showError(
					Lang.get("unable-to-synchronize-account"), e);
			return;
		} catch (PortalException e) {
			Controller.get().showError(
					Lang.get("unable-to-synchronize-account"), e);
			return;
		}

		Controller.get().forwardInComposer(message);

	}

	protected void reply(boolean toAll) {

		// Ensure a mail is selected
		Message selectedMessage = mainMailView.getSelectedMessage();
		if (selectedMessage == null) {
			Controller.get().showInfo(Lang.get("no-messages-selected"));
			return;
		}
		try {
			selectedMessage = MessageUtil.getFullMessage(selectedMessage, true);
		} catch (SystemException e) {
			Controller.get().showError(
					Lang.get("unable-to-synchronize-account"), e);
			return;
		} catch (PortalException e) {
			Controller.get().showError(
					Lang.get("unable-to-synchronize-account"), e);
			return;
		}
		final Message originalMessage = selectedMessage;

		Composer composer = Controller.get().replyInComposer(originalMessage, toAll);
		composer.addListener(new ComposerListener() {

			public void messageDiscarded(Composer composer) {

			}

			public void messageSaved(Composer composer, Message message) {

			}

			public void messageSent(Composer composer, Message message) {

				// Set replied flag on mail
				try {
					MessageUtil.setAnswered(originalMessage, true);
				}
				catch (PortalException e) {
					Controller.get().showError(
						Lang.get("unable-to-flag-messages"), e);
				}
				catch (SystemException e) {
					Controller.get().showError(
						Lang.get("unable-to-flag-messages"), e);
				}

			}

		});

	}

	protected void openDraft() {

		// Ensure a mail is selected
		Message message = mainMailView.getSelectedMessage();
		if (message == null) {
			Controller.get().showInfo(Lang.get("no-messages-selected"));
			return;
		}

		try {
			message = MessageUtil.getFullMessage(message, true);
		} catch (SystemException e) {
			Controller.get().showError(
					Lang.get("unable-to-synchronize-account"), e);
			return;
		} catch (PortalException e) {
			Controller.get().showError(
					Lang.get("unable-to-synchronize-account"), e);
			return;
		}

		Controller.get().openDraftInComposer(message);

	}

	protected void refresh() {
		if (currentAccountId == null) {
			return;
		}

		try {
			Controller.get().getMailManager().synchronizeAccount(currentAccountId);
			mainMailView.update();
		}
		catch (SystemException e) {
			Controller.get().showError(
				Lang.get("unable-to-synchronize-account"), e);
		}
		catch (PortalException e) {
			Controller.get().showError(
				Lang.get("unable-to-synchronize-account"), e);
		}
	}

}