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

package com.liferay.mail.service;


/**
 * <p>
 * This class is a wrapper for {@link AccountLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AccountLocalService
 * @generated
 */
public class AccountLocalServiceWrapper implements AccountLocalService {
	public AccountLocalServiceWrapper(AccountLocalService accountLocalService) {
		_accountLocalService = accountLocalService;
	}

	public com.liferay.mail.model.Account addAccount(
		com.liferay.mail.model.Account account)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.addAccount(account);
	}

	public com.liferay.mail.model.Account createAccount(long accountId) {
		return _accountLocalService.createAccount(accountId);
	}

	public void deleteAccount(long accountId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_accountLocalService.deleteAccount(accountId);
	}

	public void deleteAccount(com.liferay.mail.model.Account account)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_accountLocalService.deleteAccount(account);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.dynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.mail.model.Account getAccount(long accountId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.getAccount(accountId);
	}

	public java.util.List<com.liferay.mail.model.Account> getAccounts(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.getAccounts(start, end);
	}

	public int getAccountsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.getAccountsCount();
	}

	public com.liferay.mail.model.Account updateAccount(
		com.liferay.mail.model.Account account)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.updateAccount(account);
	}

	public com.liferay.mail.model.Account updateAccount(
		com.liferay.mail.model.Account account, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.updateAccount(account, merge);
	}

	public com.liferay.mail.model.Account addAccount(long userId,
		java.lang.String address, java.lang.String personalName,
		java.lang.String protocol, java.lang.String incomingHostName,
		int incomingPort, boolean incomingSecure,
		java.lang.String outgoingHostName, int outgoingPort,
		boolean outgoingSecure, java.lang.String login,
		java.lang.String password, boolean savePassword,
		java.lang.String signature, boolean useSignature,
		java.lang.String folderPrefix, long inboxFolderId, long draftFolderId,
		long sentFolderId, long trashFolderId, boolean defaultSender)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.addAccount(userId, address, personalName,
			protocol, incomingHostName, incomingPort, incomingSecure,
			outgoingHostName, outgoingPort, outgoingSecure, login, password,
			savePassword, signature, useSignature, folderPrefix, inboxFolderId,
			draftFolderId, sentFolderId, trashFolderId, defaultSender);
	}

	public void deleteAccounts(long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_accountLocalService.deleteAccounts(userId);
	}

	public com.liferay.mail.model.Account getAccount(long userId,
		java.lang.String address)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.getAccount(userId, address);
	}

	public java.util.List<com.liferay.mail.model.Account> getAccounts(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.getAccounts(userId);
	}

	public com.liferay.mail.model.Account updateAccount(long accountId,
		java.lang.String personalName, java.lang.String password,
		boolean savePassword, java.lang.String signature, boolean useSignature,
		java.lang.String folderPrefix, boolean defaultSender)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.updateAccount(accountId, personalName,
			password, savePassword, signature, useSignature, folderPrefix,
			defaultSender);
	}

	public com.liferay.mail.model.Account updateFolders(long accountId,
		long inboxFolderId, long draftFolderId, long sentFolderId,
		long trashFolderId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.updateFolders(accountId, inboxFolderId,
			draftFolderId, sentFolderId, trashFolderId);
	}

	public AccountLocalService getWrappedAccountLocalService() {
		return _accountLocalService;
	}

	private AccountLocalService _accountLocalService;
}