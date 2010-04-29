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

import com.liferay.mail.MailException;
import com.liferay.mail.NoSuchAccountException;
import com.liferay.mail.model.Account;
import com.liferay.mail.service.AccountLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

/**
 * <a href="BaseMailbox.java.html"><b><i>View Source</i></b></a>
 *
 * @author Scott Lee
 */
public abstract class BaseMailbox implements Mailbox {

	public Account addAccount(
			String address, String protocol, String incomingHostName,
			int incomingPort, boolean incomingSecure, String outgoingHostName,
			int outgoingPort, boolean outgoingSecure, String folderPrefix,
			String password, boolean savePassword, String login,
			String personalName, String signature, boolean useSignature)
		throws PortalException, SystemException {

		validateAccount(
			incomingHostName, incomingPort, incomingSecure, outgoingHostName,
			outgoingPort, outgoingSecure, login, password);

		try {
			AccountLocalServiceUtil.getAccount(user.getUserId(), address);
		}
		catch (NoSuchAccountException nsae) {
			long inboxFolderId = 0;
			long draftFolderId = 0;
			long sentFolderId = 0;
			long trashFolderId = 0;

			return AccountLocalServiceUtil.addAccount(
				user.getUserId(), address, personalName, protocol,
				incomingHostName, incomingPort, incomingSecure,
				outgoingHostName, outgoingPort, outgoingSecure, login, password,
				savePassword, signature, useSignature, folderPrefix,
				inboxFolderId, draftFolderId, sentFolderId, trashFolderId,
				true);
		}

		throw new MailException("Address already exists");
	}

	public void deleteAccount() throws PortalException, SystemException {
		AccountLocalServiceUtil.deleteAccount(account.getAccountId());
	}

	public Account getAccount() {
		return account;
	}

	public User getUser() {
		return user;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setUser(User user) {
		this.user = user;
	}

	protected Account account;
	protected User user;

}