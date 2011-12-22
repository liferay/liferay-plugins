/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.mail.mailbox;

import com.liferay.mail.model.Account;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;

/**
 * @author Scott Lee
 */
public class IMAPMailboxFactory implements MailboxFactory {

	public Mailbox getMailbox(User user, Account account, String password) {
		return new IMAPMailbox(user, account, password);
	}

	public Mailbox getMailbox(User user, String protocol) {
		return new IMAPMailbox(user, null, StringPool.BLANK);
	}

	public void initialize() {
	}

}