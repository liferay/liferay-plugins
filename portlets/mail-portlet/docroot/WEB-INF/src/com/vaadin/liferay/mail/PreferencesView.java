
package com.vaadin.liferay.mail;

import com.liferay.mail.model.Account;
import com.liferay.mail.service.AccountLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import com.vaadin.liferay.mail.AccountEditor.AccountEditorListener;
import com.vaadin.liferay.mail.util.Lang;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import java.util.List;

/**
 * User e-mail preferences: account management (creation, modification,
 * deletion).
 *
 * @author Henri Sara
 */
@SuppressWarnings("serial")
public class PreferencesView extends VerticalLayout {

	private final class SaveAccountListener implements AccountEditorListener {

		private final Window editorWindow;

		private SaveAccountListener(Window editorWindow) {

			this.editorWindow = editorWindow;
		}

		public void saveAccount(Account account) {

			try {
				if (account.isNew()) {
					String resultKey = controller.getAccountManager().addAccount(account, controller);
					if (resultKey == null) {
						controller.showInfo(Lang.get("account-has-been-created"));
					} else {
						controller.showError(Lang.get(resultKey));
					}
				} else {
					String resultKey = controller.getAccountManager().updateAccount(account, controller);
					if (resultKey == null) {
						controller.showInfo(Lang.get("account-has-been-updated"));
					} else {
						controller.showError(Lang.get(resultKey));
					}
				}
				synchronizeAccount(account);
			}
			catch (SystemException e) {
				controller.showError(Lang.get("unable-to-save-account"), e);
			}
			catch (PortalException e) {
				controller.showError(Lang.get("unable-to-save-account"), e);
			}
			controller.getApplication().getMainWindow().removeWindow(
				editorWindow);

			updateAccountList();
		}

		public void cancel() {

			controller.getApplication().getMainWindow().removeWindow(
				editorWindow);
		}
	}

	private Panel accountPanel;
	private final Controller controller;

	public PreferencesView(final Controller controller) {

		this.controller = controller;

		setSpacing(true);

		accountPanel = new Panel(Lang.get("your-email-accounts"));
		updateAccountList();
		addComponent(accountPanel);
		
		HorizontalLayout buttons = new HorizontalLayout();
		buttons.setSpacing(true);
		addComponent(buttons);

		Button createAccountButton = new Button(Lang.get("create-account"));
		createAccountButton.addListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				editAccount(null);
			}
		});
		buttons.addComponent(createAccountButton);
		
		Button createGmailButton = new Button("Create GMail account");
		createGmailButton.addListener(new ClickListener() {					
			public void buttonClick(ClickEvent event) {
				editGmailAccount(null);
				
			}
		});
		buttons.addComponent(createGmailButton);

		Link poweredByVaadin =
			new Link("", new ExternalResource("http://vaadin.com"));
		poweredByVaadin.setIcon(new PortletResource("images/vaadin/powered-by-vaadin.png"));
		poweredByVaadin.setDescription("Mail portlet interface created using Vaadin - RIA's with pure Java.");
		addComponent(poweredByVaadin);
	}

	private void updateAccountList() {

		try {
			accountPanel.removeAllComponents();

			List<Account> accounts = controller.getAccountManager()
					.getAccounts(controller.getUser());
			if (accounts.size() > 0) {
				GridLayout grid = new GridLayout(3, accounts.size());
				grid.setSpacing(true);
				for (final Account account : accounts) {
					grid.addComponent(new Label(account.getAddress()));

					Button editButton = new Button(Lang.get("edit"));
					editButton.setStyleName("small");
					editButton.addListener(new ClickListener() {

						public void buttonClick(ClickEvent event) {

							editAccount(account);
						}
					});
					grid.addComponent(editButton);

					Button deleteButton = new Button(Lang.get("delete"));
					deleteButton.setStyleName("small");
					deleteButton.addListener(new ClickListener() {

						public void buttonClick(ClickEvent event) {

							deleteAccount(account);
							controller.showInfo(Lang.get("account-has-been-deleted"));
						}
					});
					grid.addComponent(deleteButton);
				}
				accountPanel.addComponent(grid);
			}
		}
		catch (SystemException e) {
			// unable to read account information
			controller.showUnexpectedError(e);
		}
	}

	private void deleteAccount(final Account account) {

		// ask user to confirm first
		final ConfirmDialog confirm =
			new ConfirmDialog(
				Lang.get("confirm"), Lang.get("delete"),
				Lang.get("are-you-sure-you-want-to-delete-this-account"));

		confirm.addConfirmButtonListener(new ClickListener() {

			public void buttonClick(ClickEvent event) {

				try {
					confirm.closeDialog();

					AccountLocalServiceUtil.deleteAccount(account);
				}
				catch (SystemException e) {
					controller.showError(
						Lang.get("unable-to-delete-account"), e);
				}
				catch (PortalException e) {
					controller.showError(
						Lang.get("unable-to-delete-account"), e);
				}
				updateAccountList();
			}
		});

		controller.getApplication().getMainWindow().addWindow(confirm);
	}

	private void synchronizeAccount(final Account account) {
		try {
			if(account.isSavePassword()){
				controller.getMailManager().synchronizeAccount(account.getAccountId());
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

	private void editAccount(Account account) {

		String windowTitle = Lang.get(account == null ? "add-mail-account"
				: "edit-account");
		final Window editorWindow = new Window(windowTitle);
		editorWindow.setSizeUndefined();
		editorWindow.center();
		editorWindow.setModal(true);
		editorWindow.setResizable(false);

		AccountEditor.AccountEditorListener listener = new SaveAccountListener(
				editorWindow);

		// show a pre-filled edit dialog
		AccountEditor editor = new AccountEditor(account, controller, listener);
		editorWindow.setContent(editor);
		controller.getApplication().getMainWindow().addWindow(editorWindow);
	}
	
	private void editGmailAccount(Account account) {
		String windowTitle = Lang.get(account == null ? "Add GMail Account"
				: "Edit GMail Account");
		final Window editorWindow = new Window(windowTitle);
		editorWindow.setSizeUndefined();
		editorWindow.center();
		editorWindow.setModal(true);
		editorWindow.setResizable(false);

		AccountEditor.AccountEditorListener listener = new SaveAccountListener(
				editorWindow);

		// show a pre-filled edit dialog
		AccountEditor editor = new GMailAccountEditor(account, controller, listener);
		editorWindow.setContent(editor);
		controller.getApplication().getMainWindow().addWindow(editorWindow);
	}

}