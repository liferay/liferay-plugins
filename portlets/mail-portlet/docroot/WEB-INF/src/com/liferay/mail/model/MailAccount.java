/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.mail.model;

import com.liferay.mail.util.MailDiskManager;
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
			_password = jsonObj.getString("password");
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