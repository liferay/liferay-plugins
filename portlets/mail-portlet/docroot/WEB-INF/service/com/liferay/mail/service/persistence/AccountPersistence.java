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

package com.liferay.mail.service.persistence;

import aQute.bnd.annotation.ProviderType;

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
 * @see com.liferay.mail.service.persistence.impl.AccountPersistenceImpl
 * @see AccountUtil
 * @generated
 */
@ProviderType
public interface AccountPersistence extends BasePersistence<Account> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AccountUtil} to access the account persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the accounts where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching accounts
	*/
	public java.util.List<Account> findByUserId(long userId);

	/**
	* Returns a range of all the accounts where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of accounts
	* @param end the upper bound of the range of accounts (not inclusive)
	* @return the range of matching accounts
	*/
	public java.util.List<Account> findByUserId(long userId, int start, int end);

	/**
	* Returns an ordered range of all the accounts where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of accounts
	* @param end the upper bound of the range of accounts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching accounts
	*/
	public java.util.List<Account> findByUserId(long userId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Account> orderByComparator);

	/**
	* Returns the first account in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account
	* @throws NoSuchAccountException if a matching account could not be found
	*/
	public Account findByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<Account> orderByComparator)
		throws com.liferay.mail.NoSuchAccountException;

	/**
	* Returns the first account in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account, or <code>null</code> if a matching account could not be found
	*/
	public Account fetchByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<Account> orderByComparator);

	/**
	* Returns the last account in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account
	* @throws NoSuchAccountException if a matching account could not be found
	*/
	public Account findByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<Account> orderByComparator)
		throws com.liferay.mail.NoSuchAccountException;

	/**
	* Returns the last account in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account, or <code>null</code> if a matching account could not be found
	*/
	public Account fetchByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<Account> orderByComparator);

	/**
	* Returns the accounts before and after the current account in the ordered set where userId = &#63;.
	*
	* @param accountId the primary key of the current account
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account
	* @throws NoSuchAccountException if a account with the primary key could not be found
	*/
	public Account[] findByUserId_PrevAndNext(long accountId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<Account> orderByComparator)
		throws com.liferay.mail.NoSuchAccountException;

	/**
	* Removes all the accounts where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public void removeByUserId(long userId);

	/**
	* Returns the number of accounts where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching accounts
	*/
	public int countByUserId(long userId);

	/**
	* Returns the account where userId = &#63; and address = &#63; or throws a {@link NoSuchAccountException} if it could not be found.
	*
	* @param userId the user ID
	* @param address the address
	* @return the matching account
	* @throws NoSuchAccountException if a matching account could not be found
	*/
	public Account findByU_A(long userId, java.lang.String address)
		throws com.liferay.mail.NoSuchAccountException;

	/**
	* Returns the account where userId = &#63; and address = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param address the address
	* @return the matching account, or <code>null</code> if a matching account could not be found
	*/
	public Account fetchByU_A(long userId, java.lang.String address);

	/**
	* Returns the account where userId = &#63; and address = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param address the address
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching account, or <code>null</code> if a matching account could not be found
	*/
	public Account fetchByU_A(long userId, java.lang.String address,
		boolean retrieveFromCache);

	/**
	* Removes the account where userId = &#63; and address = &#63; from the database.
	*
	* @param userId the user ID
	* @param address the address
	* @return the account that was removed
	*/
	public Account removeByU_A(long userId, java.lang.String address)
		throws com.liferay.mail.NoSuchAccountException;

	/**
	* Returns the number of accounts where userId = &#63; and address = &#63;.
	*
	* @param userId the user ID
	* @param address the address
	* @return the number of matching accounts
	*/
	public int countByU_A(long userId, java.lang.String address);

	/**
	* Caches the account in the entity cache if it is enabled.
	*
	* @param account the account
	*/
	public void cacheResult(Account account);

	/**
	* Caches the accounts in the entity cache if it is enabled.
	*
	* @param accounts the accounts
	*/
	public void cacheResult(java.util.List<Account> accounts);

	/**
	* Creates a new account with the primary key. Does not add the account to the database.
	*
	* @param accountId the primary key for the new account
	* @return the new account
	*/
	public Account create(long accountId);

	/**
	* Removes the account with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountId the primary key of the account
	* @return the account that was removed
	* @throws NoSuchAccountException if a account with the primary key could not be found
	*/
	public Account remove(long accountId)
		throws com.liferay.mail.NoSuchAccountException;

	public Account updateImpl(Account account);

	/**
	* Returns the account with the primary key or throws a {@link NoSuchAccountException} if it could not be found.
	*
	* @param accountId the primary key of the account
	* @return the account
	* @throws NoSuchAccountException if a account with the primary key could not be found
	*/
	public Account findByPrimaryKey(long accountId)
		throws com.liferay.mail.NoSuchAccountException;

	/**
	* Returns the account with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accountId the primary key of the account
	* @return the account, or <code>null</code> if a account with the primary key could not be found
	*/
	public Account fetchByPrimaryKey(long accountId);

	@Override
	public java.util.Map<java.io.Serializable, Account> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the accounts.
	*
	* @return the accounts
	*/
	public java.util.List<Account> findAll();

	/**
	* Returns a range of all the accounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of accounts
	* @param end the upper bound of the range of accounts (not inclusive)
	* @return the range of accounts
	*/
	public java.util.List<Account> findAll(int start, int end);

	/**
	* Returns an ordered range of all the accounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of accounts
	* @param end the upper bound of the range of accounts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of accounts
	*/
	public java.util.List<Account> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Account> orderByComparator);

	/**
	* Removes all the accounts from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of accounts.
	*
	* @return the number of accounts
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}