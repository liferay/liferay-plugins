package com.vaadin.liferay.mail;

import com.liferay.mail.model.Account;
import com.vaadin.ui.Button.ClickEvent;

@SuppressWarnings("serial")
public class GMailAccountEditor extends AccountEditor {

	public GMailAccountEditor(Account account, Controller controller,
			AccountEditorListener listener) {
		super(account, controller, listener);
	
		loginField.setVisible(false);
		personalNameField.setVisible(false);
		
		mailInHostNameField.setVisible(false);
		mailInPortCombo.setVisible(false);
		mailInSecureCheckBox.setVisible(false);
		
		mailOutHostNameField.setVisible(false);
		mailOutPortCombo.setVisible(false);
		mailOutSecureCheckBox.setVisible(false);
		
		applyTemplate();
	}
	
	private void applyTemplate(){
		mailInHostNameField.setValue("imap.gmail.com");
		mailInPortCombo.setValue(993);
		mailInSecureCheckBox.setValue(true);
		
		mailOutHostNameField.setValue("smtp.gmail.com");
		mailOutPortCombo.setValue(465);
		mailOutSecureCheckBox.setValue(true);	
	}
	
	@Override
	public void buttonClick(ClickEvent event) {		
		loginField.setValue(addressField.getValue());		
		super.buttonClick(event);
	}
}
