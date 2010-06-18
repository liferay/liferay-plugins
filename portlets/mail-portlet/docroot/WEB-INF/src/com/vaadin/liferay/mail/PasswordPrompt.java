package com.vaadin.liferay.mail;

import com.liferay.mail.model.Account;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;

import com.vaadin.liferay.mail.util.Lang;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

@SuppressWarnings("serial")
public class PasswordPrompt extends Window implements Button.ClickListener {

	private final Account account;
	
	private final TextField password = new TextField("Password");
	
	private final Button loginBtn = new Button("Login", this);	
	
	private final Button cancelBtn = new Button("Cancel", this);
	
	private final CheckBox savePassword = new CheckBox(Lang.get("save-password"));
	
	public enum Status{
		VALIDATED, CANCELED
	}
	
	private Status status = Status.CANCELED;
	
	public PasswordPrompt(Account account) {	
		this.account = account;
				
		setCaption(Lang.get("please-enter-your-password-for-x",new String[]{account.getAddress()}));
		setWidth("400px");
		setResizable(false);
		setModal(true);
		setClosable(false);
		
		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);
		layout.setMargin(true);
		setContent(layout);		
		
		password.setSecret(true);
		password.setWidth("100%");
		addComponent(password);
		
		savePassword.setValue(account.isSavePassword());
		addComponent(savePassword);
		
		HorizontalLayout buttons = new HorizontalLayout();
		buttons.setSpacing(true);		
		buttons.addComponent(loginBtn);			
		buttons.setComponentAlignment(loginBtn, Alignment.MIDDLE_CENTER);		
		buttons.addComponent(cancelBtn);
		buttons.setComponentAlignment(cancelBtn, Alignment.MIDDLE_CENTER);
		addComponent(buttons);		
		layout.setComponentAlignment(buttons, Alignment.BOTTOM_RIGHT);
		
	}

	@Override
	public void attach() {		
		super.attach();
		
		password.focus();		
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		if(event.getButton() == loginBtn){
			String password = this.password.getValue().toString();		
			
			try {
				JSONObject obj = Controller.get().getMailManager().storePassword(account.getAccountId(), password);
				String result = obj.getString("status");
				String message = obj.getString("message");		
				
				if(result.equals("success")){
					getWindow().showNotification(Lang.get(message));
					status = Status.VALIDATED;
					account.setPasswordDecrypted(password);
					
					if(savePassword.booleanValue() && !account.isSavePassword()){
						account.setSavePassword(true);					
						Controller.get().getAccountManager().updateAccount(account, Controller.get());
					}
					
					close();				
				} else {
					getWindow().showNotification(Lang.get(message));
				}
				
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(event.getButton() == cancelBtn){
			status = Status.CANCELED;
			close();
		}		
	}
	
	public String getPassword(){
		return password.getValue().toString();
	}
	
	public Account getAccount(){
		return account;
	}
	
	public Status getStatus(){
		return status;
	}
	
}
