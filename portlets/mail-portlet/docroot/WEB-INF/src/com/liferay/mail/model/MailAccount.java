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

package com.liferay.mail.model;

import com.liferay.mail.util.MailDiskManager;
import com.liferay.mail.util.MailPasswordUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;

/**
 * <a href="MailAccount.java.html"><b><i>View Source</i></b></a>
 *
 * @author Scott Lee
 *
 */
public class MailAccount {

	public MailAccount(User user, String emailAddress) {
		_user = user;

		JSONObject jsonObj = MailDiskManager.getJSONAccount(user, emailAddress);

		if (Validator.isNotNull(jsonObj)) {
			_emailAddress = jsonObj.getString("emailAddress");
	   		_initialized = jsonObj.getBoolean("initialized");
			_mailInHostName = jsonObj.getString("mailInHostName");
			_mailInPort = jsonObj.getInt("mailInPort");
			_mailInSecure = jsonObj.getBoolean("mailInSecure");
			_mailOutHostName = jsonObj.getString("mailOutHostName");
			_mailOutPort = jsonObj.getInt("mailOutPort");
			_mailOutSecure = jsonObj.getBoolean("mailOutSecure");
			_password = MailPasswordUtil.decrypt(
				jsonObj.getString("password"));
			_username = jsonObj.getString("username");
		}
	}

	public MailAccount(
		User user, String emailAddress, boolean initialized,
		String mailInHostName, String mailInPort, boolean mailInSecure,
		String mailOutHostName, String mailOutPort, boolean mailOutSecure,
		String password, String username) {

		_user = user;
		_emailAddress = emailAddress;
   		_initialized = initialized;
		_mailInHostName = mailInHostName;
		_mailInPort = GetterUtil.getInteger(mailInPort);
		_mailInSecure = mailInSecure;
		_mailOutHostName = mailOutHostName;
		_mailOutPort = GetterUtil.getInteger(mailOutPort);
		_mailOutSecure = mailOutSecure;
		_password = password;
		_username = username;
	}

	public int getAccountId() {
		return _accountId;
	}

	public String getEmailAddress() {
		return _emailAddress;
	}

	public String getMailInHostName() {
		return _mailInHostName;
	}

	public int getMailInPort() {
		return _mailInPort;
	}

	public String getMailOutHostName() {
		return _mailOutHostName;
	}

	public int getMailOutPort() {
		return _mailOutPort;
	}

	public String getPassword() {
		return _password;
	}

	public User getUser() {
		return _user;
	}

	public String getUsername() {
		return _username;
	}

	public boolean isInitialized() {
		return _initialized;
	}

	public boolean isMailInSecure() {
		return _mailInSecure;
	}

	public boolean isMailOutSecure() {
		return _mailOutSecure;
	}

	public void setInitialized(boolean initialized) {
		this._initialized = initialized;
	}

	private int _accountId;
	private String _emailAddress;
	private boolean _initialized;
	private String _mailInHostName;
	private int _mailInPort;
	private boolean _mailInSecure;
	private String _mailOutHostName;
	private int _mailOutPort;
	private boolean _mailOutSecure;
	private String _password;
	private User _user;
	private String _username;

}