/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

import com.liferay.mail.exception.MailException;
import com.liferay.mail.exception.NoSuchAccountException;
import com.liferay.mail.model.Account;
import com.liferay.mail.service.AccountLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;

/**
 * @author Scott Lee
 */
public abstract class BaseMailbox implements Mailbox {

	public Account addAccount(
			String address, String personalName, String protocol,
			String incomingHostName, int incomingPort, boolean incomingSecure,
			String outgoingHostName, int outgoingPort, boolean outgoingSecure,
			String login, String password, boolean savePassword,
			String signature, boolean useSignature, String folderPrefix,
			boolean defaultSender)
		throws PortalException {

		validateAccount(
			incomingHostName, incomingPort, incomingSecure, outgoingHostName,
			outgoingPort, outgoingSecure, login, password);

		try {
			AccountLocalServiceUtil.getAccount(user.getUserId(), address);

			throw new MailException(MailException.ACCOUNT_ALREADY_EXISTS);
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
				defaultSender);
		}
	}

	public void deleteAccount() throws PortalException {
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

	public Account updateAccount(
			long accountId, String personalName, String password,
			boolean savePassword, String signature, boolean useSignature,
			String folderPrefix, boolean defaultSender)
		throws PortalException {

		Account account = AccountLocalServiceUtil.getAccount(accountId);

		validateAccount(
			account.getIncomingHostName(), account.getIncomingPort(),
			account.getIncomingSecure(), account.getOutgoingHostName(),
			account.getOutgoingPort(), account.getOutgoingSecure(),
			account.getLogin(), password);

		return AccountLocalServiceUtil.updateAccount(
			accountId, personalName, password, savePassword, signature,
			useSignature, folderPrefix, defaultSender);
	}

	public void updateFolders(
			long inboxFolderId, long draftFolderId, long sentFolderId,
			long trashFolderId)
		throws PortalException {

		AccountLocalServiceUtil.updateFolders(
			account.getAccountId(), inboxFolderId, draftFolderId, sentFolderId,
			trashFolderId);
	}

	protected Account account;
	protected User user;

}