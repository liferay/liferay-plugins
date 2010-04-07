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

package com.liferay.mail.mailbox;

import com.liferay.mail.model.Account;
import com.liferay.mail.service.AccountLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.Map;

/**
 * <a href="MailboxFactory.java.html"><b><i>View Source</i></b></a>
 *
 * @author Scott Lee
 *
 */
public class MailboxFactory {

	public static Mailbox createMailbox(long userId, long accountId)
		throws PortalException, SystemException {

		User user = UserLocalServiceUtil.getUser(userId);
		Account account = AccountLocalServiceUtil.getAccount(accountId);

		Mailbox mailbox = _mailboxes.get(account.getProtocol());

		if (mailbox == null) {
			throw new IllegalArgumentException(
				"Unable to locate mailbox protocol: " + account.getProtocol());
		}

		return mailbox.clone(user, account);
	}

	public void setMailboxes(Map<String, Mailbox> mailboxes) {
		_mailboxes = mailboxes;
	}

	private static Map<String, Mailbox> _mailboxes;

}