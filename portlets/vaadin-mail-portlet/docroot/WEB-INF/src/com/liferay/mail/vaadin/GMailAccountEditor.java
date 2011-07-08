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

import com.liferay.mail.model.Account;

import com.vaadin.ui.Button.ClickEvent;

/**
 * @author Henri Sara
 */
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

	private void applyTemplate() {
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