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
import com.liferay.portal.model.User;

import org.json.JSONObject;

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

		_emailAddress = jsonObj.optString("emailAddress");
		_mailInHostName = jsonObj.optString("mailInHostName");
		_mailInPort = jsonObj.optInt("mailInPort");
		_mailOutHostName = jsonObj.optString("mailOutHostName");
		_mailOutPort = jsonObj.optInt("mailOutPort");
		_mailSecure = jsonObj.optBoolean("mailSecure");
		_password = jsonObj.optString("password");
		_username = jsonObj.optString("username");
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

	public boolean isMailSecure() {
		return _mailSecure;
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

	private int _accountId;
	private String _emailAddress;
	private String _mailInHostName;
	private int _mailInPort;
	private String _mailOutHostName;
	private int _mailOutPort;
	private boolean _mailSecure;
	private String _password;
	private User _user;
	private String _username;

}