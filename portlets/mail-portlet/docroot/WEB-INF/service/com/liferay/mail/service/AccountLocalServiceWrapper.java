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

	/**
	* Adds the account to the database. Also notifies the appropriate model listeners.
	*
	* @param account the account to add
	* @return the account that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.mail.model.Account addAccount(
		com.liferay.mail.model.Account account)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.addAccount(account);
	}

	/**
	* Creates a new account with the primary key. Does not add the account to the database.
	*
	* @param accountId the primary key for the new account
	* @return the new account
	*/
	public com.liferay.mail.model.Account createAccount(long accountId) {
		return _accountLocalService.createAccount(accountId);
	}

	/**
	* Deletes the account with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountId the primary key of the account to delete
	* @throws PortalException if a account with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteAccount(long accountId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_accountLocalService.deleteAccount(accountId);
	}

	/**
	* Deletes the account from the database. Also notifies the appropriate model listeners.
	*
	* @param account the account to delete
	* @throws SystemException if a system exception occurred
	*/
	public void deleteAccount(com.liferay.mail.model.Account account)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_accountLocalService.deleteAccount(account);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query to search with
	* @param start the lower bound of the range of model instances to return
	* @param end the upper bound of the range of model instances to return (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query to search with
	* @param start the lower bound of the range of model instances to return
	* @param end the upper bound of the range of model instances to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Counts the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Gets the account with the primary key.
	*
	* @param accountId the primary key of the account to get
	* @return the account
	* @throws PortalException if a account with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.mail.model.Account getAccount(long accountId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.getAccount(accountId);
	}

	/**
	* Gets a range of all the accounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of accounts to return
	* @param end the upper bound of the range of accounts to return (not inclusive)
	* @return the range of accounts
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.mail.model.Account> getAccounts(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.getAccounts(start, end);
	}

	/**
	* Gets the number of accounts.
	*
	* @return the number of accounts
	* @throws SystemException if a system exception occurred
	*/
	public int getAccountsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.getAccountsCount();
	}

	/**
	* Updates the account in the database. Also notifies the appropriate model listeners.
	*
	* @param account the account to update
	* @return the account that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.mail.model.Account updateAccount(
		com.liferay.mail.model.Account account)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.updateAccount(account);
	}

	/**
	* Updates the account in the database. Also notifies the appropriate model listeners.
	*
	* @param account the account to update
	* @param merge whether to merge the account with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the account that was updated
	* @throws SystemException if a system exception occurred
	*/
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