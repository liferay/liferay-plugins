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

package com.liferay.mail.util;

import com.liferay.mail.mailbox.Mailbox;
import com.liferay.mail.mailbox.MailboxFactoryUtil;
import com.liferay.mail.model.Account;
import com.liferay.mail.service.AccountLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.util.PortalUtil;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * <a href="MailManager.java.html"><b><i>View Source</i></b></a>
 *
 * @author Scott Lee
 */
public class MailManager {

	public static MailManager getInstance(HttpServletRequest request) {
		long userId = PortalUtil.getUserId(request);

		return new MailManager(userId);
	}

	public MailManager(long userId) {
		_userId = userId;
	}

	public List<Account> getAccounts() throws SystemException {
		return AccountLocalServiceUtil.getAccounts(_userId);
	}

	public void synchronizeAccount(long accountId)
		throws PortalException, SystemException {

		Mailbox mailbox = MailboxFactoryUtil.getMailbox(_userId, accountId);

		mailbox.synchronize();
	}

	private long _userId;

}