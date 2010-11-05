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

package com.liferay.mail.service.persistence;

import com.liferay.mail.model.Account;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the account service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountPersistenceImpl
 * @see AccountUtil
 * @generated
 */
public interface AccountPersistence extends BasePersistence<Account> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AccountUtil} to access the account persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the account in the entity cache if it is enabled.
	*
	* @param account the account to cache
	*/
	public void cacheResult(com.liferay.mail.model.Account account);

	/**
	* Caches the accounts in the entity cache if it is enabled.
	*
	* @param accounts the accounts to cache
	*/
	public void cacheResult(
		java.util.List<com.liferay.mail.model.Account> accounts);

	/**
	* Creates a new account with the primary key. Does not add the account to the database.
	*
	* @param accountId the primary key for the new account
	* @return the new account
	*/
	public com.liferay.mail.model.Account create(long accountId);

	/**
	* Removes the account with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountId the primary key of the account to remove
	* @return the account that was removed
	* @throws com.liferay.mail.NoSuchAccountException if a account with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.mail.model.Account remove(long accountId)
		throws com.liferay.mail.NoSuchAccountException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Account updateImpl(
		com.liferay.mail.model.Account account, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the account with the primary key or throws a {@link com.liferay.mail.NoSuchAccountException} if it could not be found.
	*
	* @param accountId the primary key of the account to find
	* @return the account
	* @throws com.liferay.mail.NoSuchAccountException if a account with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.mail.model.Account findByPrimaryKey(long accountId)
		throws com.liferay.mail.NoSuchAccountException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the account with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accountId the primary key of the account to find
	* @return the account, or <code>null</code> if a account with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.mail.model.Account fetchByPrimaryKey(long accountId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the accounts where userId = &#63;.
	*
	* @param userId the user id to search with
	* @return the matching accounts
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.mail.model.Account> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the accounts where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user id to search with
	* @param start the lower bound of the range of accounts to return
	* @param end the upper bound of the range of accounts to return (not inclusive)
	* @return the range of matching accounts
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.mail.model.Account> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the accounts where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user id to search with
	* @param start the lower bound of the range of accounts to return
	* @param end the upper bound of the range of accounts to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching accounts
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.mail.model.Account> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the first account in the ordered set where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching account
	* @throws com.liferay.mail.NoSuchAccountException if a matching account could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.mail.model.Account findByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchAccountException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the last account in the ordered set where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching account
	* @throws com.liferay.mail.NoSuchAccountException if a matching account could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.mail.model.Account findByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchAccountException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the accounts before and after the current account in the ordered set where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountId the primary key of the current account
	* @param userId the user id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next account
	* @throws com.liferay.mail.NoSuchAccountException if a account with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.mail.model.Account[] findByUserId_PrevAndNext(
		long accountId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchAccountException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the account where userId = &#63; and address = &#63; or throws a {@link com.liferay.mail.NoSuchAccountException} if it could not be found.
	*
	* @param userId the user id to search with
	* @param address the address to search with
	* @return the matching account
	* @throws com.liferay.mail.NoSuchAccountException if a matching account could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.mail.model.Account findByU_A(long userId,
		java.lang.String address)
		throws com.liferay.mail.NoSuchAccountException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the account where userId = &#63; and address = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user id to search with
	* @param address the address to search with
	* @return the matching account, or <code>null</code> if a matching account could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.mail.model.Account fetchByU_A(long userId,
		java.lang.String address)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the account where userId = &#63; and address = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user id to search with
	* @param address the address to search with
	* @return the matching account, or <code>null</code> if a matching account could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.mail.model.Account fetchByU_A(long userId,
		java.lang.String address, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the accounts.
	*
	* @return the accounts
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.mail.model.Account> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the accounts.
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
	public java.util.List<com.liferay.mail.model.Account> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the accounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of accounts to return
	* @param end the upper bound of the range of accounts to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of accounts
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.mail.model.Account> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the accounts where userId = &#63; from the database.
	*
	* @param userId the user id to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the account where userId = &#63; and address = &#63; from the database.
	*
	* @param userId the user id to search with
	* @param address the address to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByU_A(long userId, java.lang.String address)
		throws com.liferay.mail.NoSuchAccountException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the accounts from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the accounts where userId = &#63;.
	*
	* @param userId the user id to search with
	* @return the number of matching accounts
	* @throws SystemException if a system exception occurred
	*/
	public int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the accounts where userId = &#63; and address = &#63;.
	*
	* @param userId the user id to search with
	* @param address the address to search with
	* @return the number of matching accounts
	* @throws SystemException if a system exception occurred
	*/
	public int countByU_A(long userId, java.lang.String address)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the accounts.
	*
	* @return the number of accounts
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}