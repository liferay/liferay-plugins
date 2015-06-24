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

package com.liferay.mail.service.impl;

import com.liferay.mail.model.Account;
import com.liferay.mail.service.base.AccountLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;

import java.util.Date;
import java.util.List;

/**
 * @author Scott Lee
 */
public class AccountLocalServiceImpl extends AccountLocalServiceBaseImpl {

	public Account addAccount(
			long userId, String address, String personalName, String protocol,
			String incomingHostName, int incomingPort, boolean incomingSecure,
			String outgoingHostName, int outgoingPort, boolean outgoingSecure,
			String login, String password, boolean savePassword,
			String signature, boolean useSignature, String folderPrefix,
			long inboxFolderId, long draftFolderId, long sentFolderId,
			long trashFolderId, boolean defaultSender)
		throws PortalException {

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		long accountId = counterLocalService.increment();

		Account account = accountPersistence.create(accountId);

		account.setCompanyId(user.getCompanyId());
		account.setUserId(user.getUserId());
		account.setUserName(user.getFullName());
		account.setCreateDate(now);
		account.setModifiedDate(now);
		account.setAddress(address);
		account.setPersonalName(personalName);
		account.setProtocol(protocol);
		account.setIncomingHostName(incomingHostName);
		account.setIncomingPort(incomingPort);
		account.setIncomingSecure(incomingSecure);
		account.setOutgoingHostName(outgoingHostName);
		account.setOutgoingPort(outgoingPort);
		account.setOutgoingSecure(outgoingSecure);
		account.setLogin(login);

		if (savePassword && Validator.isNotNull(password)) {
			account.setPasswordDecrypted(password);
		}
		else {
			account.setPasswordDecrypted(StringPool.BLANK);
		}

		account.setSavePassword(savePassword);
		account.setSignature(signature);
		account.setUseSignature(useSignature);
		account.setFolderPrefix(folderPrefix);
		account.setInboxFolderId(inboxFolderId);
		account.setDraftFolderId(draftFolderId);
		account.setSentFolderId(sentFolderId);
		account.setTrashFolderId(trashFolderId);
		account.setDefaultSender(defaultSender);

		accountPersistence.update(account);

		return account;
	}

	@Override
	public Account deleteAccount(Account account) throws PortalException {

		// Account

		accountPersistence.remove(account);

		// Folders

		folderLocalService.deleteFolders(account.getAccountId());

		// Indexer

		Indexer<Account> indexer = IndexerRegistryUtil.getIndexer(
			Account.class);

		indexer.delete(account);

		return account;
	}

	@Override
	public Account deleteAccount(long accountId) throws PortalException {
		Account account = accountPersistence.findByPrimaryKey(accountId);

		return deleteAccount(account);
	}

	public void deleteAccounts(long userId) throws PortalException {
		List<Account> accounts = accountPersistence.findByUserId(userId);

		for (Account account : accounts) {
			deleteAccount(account);
		}
	}

	public Account getAccount(long userId, String address)
		throws PortalException {

		return accountPersistence.findByU_A(userId, address);
	}

	public List<Account> getAccounts(long userId) {
		return accountPersistence.findByUserId(userId);
	}

	public Account updateAccount(
			long accountId, String personalName, String password,
			boolean savePassword, String signature, boolean useSignature,
			String folderPrefix, boolean defaultSender)
		throws PortalException {

		Account account = accountPersistence.findByPrimaryKey(accountId);

		account.setModifiedDate(new Date());
		account.setPersonalName(personalName);

		if (savePassword && Validator.isNotNull(password)) {
			account.setPasswordDecrypted(password);
		}
		else {
			account.setPassword(StringPool.BLANK);
		}

		account.setSavePassword(savePassword);
		account.setSignature(signature);
		account.setUseSignature(useSignature);
		account.setFolderPrefix(folderPrefix);
		account.setDefaultSender(defaultSender);

		accountPersistence.update(account);

		return account;
	}

	public Account updateFolders(
			long accountId, long inboxFolderId, long draftFolderId,
			long sentFolderId, long trashFolderId)
		throws PortalException {

		Account account = accountPersistence.findByPrimaryKey(accountId);

		account.setModifiedDate(new Date());

		account.setInboxFolderId(inboxFolderId);
		account.setDraftFolderId(draftFolderId);
		account.setSentFolderId(sentFolderId);
		account.setTrashFolderId(trashFolderId);

		accountPersistence.update(account);

		return account;
	}

}