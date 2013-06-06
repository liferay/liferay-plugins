/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AccountLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AccountLocalService
 * @generated
 */
public class AccountLocalServiceWrapper implements AccountLocalService,
	ServiceWrapper<AccountLocalService> {
	public AccountLocalServiceWrapper(AccountLocalService accountLocalService) {
		_accountLocalService = accountLocalService;
	}

	/**
	* Adds the account to the database. Also notifies the appropriate model listeners.
	*
	* @param account the account
	* @return the account that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
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
	@Override
	public com.liferay.mail.model.Account createAccount(long accountId) {
		return _accountLocalService.createAccount(accountId);
	}

	/**
	* Deletes the account with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountId the primary key of the account
	* @return the account that was removed
	* @throws PortalException if a account with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.mail.model.Account deleteAccount(long accountId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.deleteAccount(accountId);
	}

	/**
	* Deletes the account from the database. Also notifies the appropriate model listeners.
	*
	* @param account the account
	* @return the account that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.mail.model.Account deleteAccount(
		com.liferay.mail.model.Account account)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.deleteAccount(account);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _accountLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.mail.model.impl.AccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.mail.model.impl.AccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.dynamicQueryCount(dynamicQuery);
	}

	@Override
	public com.liferay.mail.model.Account fetchAccount(long accountId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.fetchAccount(accountId);
	}

	/**
	* Returns the account with the primary key.
	*
	* @param accountId the primary key of the account
	* @return the account
	* @throws PortalException if a account with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.mail.model.Account getAccount(long accountId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.getAccount(accountId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the accounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.mail.model.impl.AccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of accounts
	* @param end the upper bound of the range of accounts (not inclusive)
	* @return the range of accounts
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<com.liferay.mail.model.Account> getAccounts(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.getAccounts(start, end);
	}

	/**
	* Returns the number of accounts.
	*
	* @return the number of accounts
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getAccountsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.getAccountsCount();
	}

	/**
	* Updates the account in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param account the account
	* @return the account that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.mail.model.Account updateAccount(
		com.liferay.mail.model.Account account)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.updateAccount(account);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _accountLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_accountLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _accountLocalService.invokeMethod(name, parameterTypes, arguments);
	}

	@Override
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

	@Override
	public void deleteAccounts(long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_accountLocalService.deleteAccounts(userId);
	}

	@Override
	public com.liferay.mail.model.Account getAccount(long userId,
		java.lang.String address)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.getAccount(userId, address);
	}

	@Override
	public java.util.List<com.liferay.mail.model.Account> getAccounts(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.getAccounts(userId);
	}

	@Override
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

	@Override
	public com.liferay.mail.model.Account updateFolders(long accountId,
		long inboxFolderId, long draftFolderId, long sentFolderId,
		long trashFolderId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.updateFolders(accountId, inboxFolderId,
			draftFolderId, sentFolderId, trashFolderId);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public AccountLocalService getWrappedAccountLocalService() {
		return _accountLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedAccountLocalService(
		AccountLocalService accountLocalService) {
		_accountLocalService = accountLocalService;
	}

	@Override
	public AccountLocalService getWrappedService() {
		return _accountLocalService;
	}

	@Override
	public void setWrappedService(AccountLocalService accountLocalService) {
		_accountLocalService = accountLocalService;
	}

	private AccountLocalService _accountLocalService;
}