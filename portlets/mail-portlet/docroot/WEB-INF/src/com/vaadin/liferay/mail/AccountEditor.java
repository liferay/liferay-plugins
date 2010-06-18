
package com.vaadin.liferay.mail;

import com.liferay.mail.model.Account;
import com.liferay.mail.model.impl.AccountImpl;

import com.vaadin.liferay.mail.util.Lang;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

/**
 * Editor for an account and its settings. Note that the original AccountEntry
 * and AccountSettings object instances are modified at save time.
 *
 * @author Henri Sara
 */
@SuppressWarnings("serial")
public class AccountEditor extends FormLayout implements ClickListener {

	public static interface AccountEditorListener {

		void saveAccount(Account account);

		void cancel();
	}

	// these can also be null (new account)
	private Account account;

	private final boolean useLocalPartOfEmailAddressAsLogin;
	private final boolean hideSettings;
	private final Controller controller;
	private final AccountEditorListener listener;

	protected TextField addressField;
	protected TextField loginField;
	protected TextField personalNameField;
	protected TextField passwordField;
	protected CheckBox passwordSavedCheckBox;
	protected TextField mailInHostNameField;
	protected ComboBox mailInPortCombo;
	protected CheckBox mailInSecureCheckBox;
	protected TextField mailOutHostNameField;
	protected ComboBox mailOutPortCombo;
	protected CheckBox mailOutSecureCheckBox;

	private Button cancelButton;
	private Button okButton;

	public AccountEditor(Account account, Controller controller,
			AccountEditorListener listener) {

		this.account = account;

		useLocalPartOfEmailAddressAsLogin = false;
		hideSettings = false;

		this.controller = controller;
		this.listener = listener;

		setSizeUndefined();
		setMargin(true);

		// using FormLayout instead of two separate forms on the page
		// somewhat complex logic on what to show or not, ...

		createComponents();

		initializeCommonFields(controller);
		if (account != null) {
			setAccount(account);
		}

		personalNameField.setReadOnly(true);
	}

	private void createComponents() {

		addressField = new TextField(Lang.get("email-address"));
		addressField.focus();
		loginField = new TextField(Lang.get("login"));
		personalNameField = new TextField(Lang.get("personal-name"));
		passwordField = new TextField(Lang.get("password"));
		passwordField.setSecret(true);
		passwordSavedCheckBox = new CheckBox(Lang.get("save-password"));
		mailInHostNameField = new TextField(Lang.get("incoming-imap-server"));
		mailInPortCombo =
			new ComboBox(
				Lang.get("incoming-port"),
				controller.getConfigurationManager().getIncomingPorts());
		mailInSecureCheckBox =
			new CheckBox(Lang.get("use-secure-incoming-connection"));
		mailOutHostNameField = new TextField(Lang.get("outgoing-smtp-server"));
		mailOutPortCombo =
			new ComboBox(
				Lang.get("outgoing-port"),
				controller.getConfigurationManager().getOutgoingPorts());
		mailOutSecureCheckBox =
			new CheckBox(Lang.get("use-secure-outgoing-connection"));

		// initial validation - the account is then tested
		addressField.setRequired(true);
		mailInHostNameField.setRequired(true);
		mailOutHostNameField.setRequired(true);

		addComponent(addressField);
		if (!useLocalPartOfEmailAddressAsLogin) {
			addComponent(loginField);
		}
		addComponent(personalNameField);
		addComponent(passwordField);
		addComponent(passwordSavedCheckBox);
		if (!hideSettings) {
			addComponent(mailInHostNameField);
			addComponent(mailInPortCombo);
			addComponent(mailInSecureCheckBox);
			addComponent(mailOutHostNameField);
			addComponent(mailOutPortCombo);
			addComponent(mailOutSecureCheckBox);
		}

		HorizontalLayout footer = new HorizontalLayout();
		footer.setSpacing(true);
		okButton = new Button(Lang.get("save"));
		okButton.setStyleName("primary");
		okButton.addListener(this);
		footer.addComponent(okButton);
		cancelButton = new Button(Lang.get("cancel"));
		cancelButton.addListener(this);
		footer.addComponent(cancelButton);

		// TODO add footer to layout in a cleaner manner
		addComponent(footer);
	}

	private void setAccount(Account account) {

		addressField.setValue(account.getAddress());
		loginField.setValue(account.getLogin());
		personalNameField.setValue(account.getPersonalName());
		passwordSavedCheckBox.setValue(account.isSavePassword());
		mailInHostNameField.setValue(account.getIncomingHostName());
		mailInPortCombo.setValue(account.getIncomingPort());
		mailInSecureCheckBox.setValue(account.isIncomingSecure());
		mailOutHostNameField.setValue(account.getOutgoingHostName());
		mailOutPortCombo.setValue(account.getOutgoingPort());
		mailOutSecureCheckBox.setValue(account.isOutgoingSecure());
	}

	private void initializeCommonFields(Controller controller) {
		personalNameField.setValue(controller.getUser().getFullName());
	}

	/**
	 * Get the created/modified Account object based on the contents of the window.
	 *
	 * @return Account
	 */
	private Account getAccount() {

		if (account == null) {
			account = new AccountImpl();
			account.setNew(true);

			account.setProtocol("imap");

			account.setSignature("");
			account.setUseSignature(false);
			account.setFolderPrefix("");
			account.setDefaultSender(false);
		}

		account.setAddress((String) addressField.getValue());
		if (useLocalPartOfEmailAddressAsLogin) {
			account.setLogin(account.getAddress().split("@")[0]);
		}
		else {
			account.setLogin((String) loginField.getValue());
		}
		account.setPersonalName((String) personalNameField.getValue());
		account.setPasswordDecrypted((String) passwordField.getValue());
		account.setSavePassword((Boolean) passwordSavedCheckBox.getValue());

		account.setIncomingHostName((String) mailInHostNameField.getValue());
		account.setIncomingPort((Integer) mailInPortCombo.getValue());
		account.setIncomingSecure((Boolean) mailInSecureCheckBox.getValue());
		account.setOutgoingHostName((String) mailOutHostNameField.getValue());
		account.setOutgoingPort((Integer) mailOutPortCombo.getValue());
		account.setOutgoingSecure((Boolean) mailOutSecureCheckBox.getValue());

		return account;
	}

	public void buttonClick(ClickEvent event) {

		if (listener != null) {
			if (event.getButton() == cancelButton) {
				listener.cancel();
			}
			else if (event.getButton() == okButton) {
				if (!addressField.isValid()) {
					controller.showInfo(Lang.get("please-enter-a-valid-email-address"));
				}
				else if (!mailInHostNameField.isValid()) {
					controller.showInfo(Lang.get("please-enter-a-valid-incoming-server-name"));
				}
				else if (!mailOutHostNameField.isValid()) {
					controller.showInfo(Lang.get("please-enter-a-valid-outgoing-server-name"));
				}
				else {
					listener.saveAccount(getAccount());
				}
			}
		}
	}
}