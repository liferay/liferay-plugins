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

import com.vaadin.event.Action.Handler;
import com.vaadin.event.Action;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * @author Henri Sara
 */
public class ConfirmDialog extends Window implements Handler {

	private String confirmWindowCaption = "Confirm";
	private Button confirmButton = new Button("OK");
	private Button cancelButton = new Button("Cancel");
	private Label message = new Label("");
	private VerticalLayout extraLayout = new VerticalLayout();

	public ConfirmDialog() {

		setModal(true);
		VerticalLayout vl = new VerticalLayout();
		vl.setMargin(true);
		vl.setSpacing(true);
		setContent(vl);

		setCaption(confirmWindowCaption);

		confirmButton.setCaption(Lang.get("ok"));
		confirmButton.setStyleName("primary");
		cancelButton.setCaption(Lang.get("cancel"));

		cancelButton.addListener(new Button.ClickListener() {

			public void buttonClick(Button.ClickEvent event) {

				closeDialog();
			}
		});

		vl.addComponent(message);

		vl.addComponent(extraLayout);

		HorizontalLayout buttons = new HorizontalLayout();
		buttons.setSpacing(true);

		buttons.addComponent(confirmButton);
		buttons.addComponent(cancelButton);

		vl.addComponent(buttons);
		vl.setComponentAlignment(buttons, "right");

		setWidth("300px");
		setResizable(false);
		confirmButton.focus();
	}

	public ConfirmDialog(String confirmationText) {

		this();
		message.setValue(confirmationText);
	}

	public ConfirmDialog(String caption, String confirmationText) {

		this(confirmationText);
		setCaption(caption);
	}

	public ConfirmDialog(String caption, String confirmButtonCaption,
			String confirmationText) {

		this(caption, confirmationText);
		confirmButton.setCaption(confirmButtonCaption);
	}

	public void addExtraComponent(Component component) {
		extraLayout.addComponent(component);
	}

	/**
	 * Closes the dialog.
	 */
	public void closeDialog() {

		getApplication().getMainWindow().removeWindow(this);
	}

	/**
	 * Adds a listener that will be called when the confirm button is clicked.
	 * There may be several listeners that all will be called.
	 *
	 * @param listener
	 *			a Button.ClickListener that will be called when confirm button
	 *			is clicked.
	 */
	public void addConfirmButtonListener(Button.ClickListener listener) {

		if (listener != null) {
			confirmButton.addListener(listener);
		}
	}

	public String getMessage() {

		return (String) message.getValue();
	}

	public void setMessage(String message) {

		this.message.setValue(message);
	}

	public void setConfirmButtonCaption(String confirmButtonCaption) {

		confirmButton.setCaption(confirmButtonCaption);
	}

	public void setCancelButtonCaption(String cancelButtonCaption) {

		cancelButton.setCaption(cancelButtonCaption);
	}

	Action action_cancel = new ShortcutAction("Escape",
			ShortcutAction.KeyCode.ESCAPE, null);

	public Action[] getActions(Object target, Object sender) {

		return new Action[] { action_cancel };
	}

	public void handleAction(Action action, Object sender, Object target) {

		if (action == action_cancel) {
			closeDialog();
		}
	}

}