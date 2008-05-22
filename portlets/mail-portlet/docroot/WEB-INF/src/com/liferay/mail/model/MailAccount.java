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

import com.liferay.portal.model.User;

/**
 * <a href="MailAccount.java.html"><b><i>View Source</i></b></a>
 *
 * @author Scott Lee
 *
 */
public class MailAccount {

    public MailAccount(User user, int accountId) {
    	if (accountId == 0) {
        	_mailInHostName = "imap.gmail.com";
        	_mailInPort = "993";
        	_mailOutHostName = "smtp.gmail.com";
        	_mailOutPort = "465";
        	_mailSecure = true;
        	_emailAddress = "liferay.mail.1@gmail.com";
            _username = "liferay.mail.1";
            _password = "loveispatient";
    	}
    	else if (accountId == 1) {
        	_mailInHostName = "imap.gmail.com";
        	_mailInPort = "993";
        	_mailOutHostName = "smtp.gmail.com";
        	_mailOutPort = "465";
        	_mailSecure = true;
        	_emailAddress = "liferay.mail.2@gmail.com";
        	_username = "liferay.mail.2";
            _password = "loveispatient";
    	}
    	else if (accountId == 2) {
        	_mailInHostName = "imap.gmail.com";
        	_mailInPort = "993";
        	_mailOutHostName = "smtp.gmail.com";
        	_mailOutPort = "465";
        	_mailSecure = true;
        	_emailAddress = "liferay.mail.2@gmail.com";
            _username = "liferay.mail.3";
            _password = "loveispatient";
    	}
    }

	public String getMailInHostName() {
		return _mailInHostName;
	}

	public String getMailInPort() {
		return _mailInPort;
	}

	public String getMailOutHostName() {
		return _mailOutHostName;
	}

	public String getMailOutPort() {
		return _mailOutPort;
	}

	public boolean isMailSecure() {
		return _mailSecure;
	}

	public String getUsername() {
		return _username;
	}

	public String getPassword() {
		return _password;
	}

	public String getEmailAddress() {
		return _emailAddress;
	}

	private String _emailAddress;
	private String _mailInHostName;
	private String _mailInPort;
	private String _mailOutHostName;
	private String _mailOutPort;
	private boolean _mailSecure;
    private String _password;
    private String _username;

}