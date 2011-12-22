/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the account service. This utility wraps {@link AccountPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountPersistence
 * @see AccountPersistenceImpl
 * @generated
 */
public class AccountUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(Account account) {
		getPersistence().clearCache(account);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Account> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Account> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Account> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static Account update(Account account, boolean merge)
		throws SystemException {
		return getPersistence().update(account, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static Account update(Account account, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(account, merge, serviceContext);
	}

	/**
	* Caches the account in the entity cache if it is enabled.
	*
	* @param account the account
	*/
	public static void cacheResult(com.liferay.mail.model.Account account) {
		getPersistence().cacheResult(account);
	}

	/**
	* Caches the accounts in the entity cache if it is enabled.
	*
	* @param accounts the accounts
	*/
	public static void cacheResult(
		java.util.List<com.liferay.mail.model.Account> accounts) {
		getPersistence().cacheResult(accounts);
	}

	/**
	* Creates a new account with the primary key. Does not add the account to the database.
	*
	* @param accountId the primary key for the new account
	* @return the new account
	*/
	public static com.liferay.mail.model.Account create(long accountId) {
		return getPersistence().create(accountId);
	}

	/**
	* Removes the account with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountId the primary key of the account
	* @return the account that was removed
	* @throws com.liferay.mail.NoSuchAccountException if a account with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.mail.model.Account remove(long accountId)
		throws com.liferay.mail.NoSuchAccountException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(accountId);
	}

	public static com.liferay.mail.model.Account updateImpl(
		com.liferay.mail.model.Account account, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(account, merge);
	}

	/**
	* Returns the account with the primary key or throws a {@link com.liferay.mail.NoSuchAccountException} if it could not be found.
	*
	* @param accountId the primary key of the account
	* @return the account
	* @throws com.liferay.mail.NoSuchAccountException if a account with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.mail.model.Account findByPrimaryKey(
		long accountId)
		throws com.liferay.mail.NoSuchAccountException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(accountId);
	}

	/**
	* Returns the account with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accountId the primary key of the account
	* @return the account, or <code>null</code> if a account with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.mail.model.Account fetchByPrimaryKey(
		long accountId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(accountId);
	}

	/**
	* Returns all the accounts where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching accounts
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.mail.model.Account> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId);
	}

	/**
	* Returns a range of all the accounts where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of accounts
	* @param end the upper bound of the range of accounts (not inclusive)
	* @return the range of matching accounts
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.mail.model.Account> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	* Returns an ordered range of all the accounts where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of accounts
	* @param end the upper bound of the range of accounts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching accounts
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.mail.model.Account> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
	}

	/**
	* Returns the first account in the ordered set where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account
	* @throws com.liferay.mail.NoSuchAccountException if a matching account could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.mail.model.Account findByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchAccountException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the last account in the ordered set where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account
	* @throws com.liferay.mail.NoSuchAccountException if a matching account could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.mail.model.Account findByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchAccountException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the accounts before and after the current account in the ordered set where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountId the primary key of the current account
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account
	* @throws com.liferay.mail.NoSuchAccountException if a account with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.mail.model.Account[] findByUserId_PrevAndNext(
		long accountId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchAccountException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_PrevAndNext(accountId, userId,
			orderByComparator);
	}

	/**
	* Returns the account where userId = &#63; and address = &#63; or throws a {@link com.liferay.mail.NoSuchAccountException} if it could not be found.
	*
	* @param userId the user ID
	* @param address the address
	* @return the matching account
	* @throws com.liferay.mail.NoSuchAccountException if a matching account could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.mail.model.Account findByU_A(long userId,
		java.lang.String address)
		throws com.liferay.mail.NoSuchAccountException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_A(userId, address);
	}

	/**
	* Returns the account where userId = &#63; and address = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param address the address
	* @return the matching account, or <code>null</code> if a matching account could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.mail.model.Account fetchByU_A(long userId,
		java.lang.String address)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByU_A(userId, address);
	}

	/**
	* Returns the account where userId = &#63; and address = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param address the address
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching account, or <code>null</code> if a matching account could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.mail.model.Account fetchByU_A(long userId,
		java.lang.String address, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByU_A(userId, address, retrieveFromCache);
	}

	/**
	* Returns all the accounts.
	*
	* @return the accounts
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.mail.model.Account> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the accounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of accounts
	* @param end the upper bound of the range of accounts (not inclusive)
	* @return the range of accounts
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.mail.model.Account> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the accounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of accounts
	* @param end the upper bound of the range of accounts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of accounts
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.mail.model.Account> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the accounts where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUserId(userId);
	}

	/**
	* Removes the account where userId = &#63; and address = &#63; from the database.
	*
	* @param userId the user ID
	* @param address the address
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByU_A(long userId, java.lang.String address)
		throws com.liferay.mail.NoSuchAccountException,
			com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByU_A(userId, address);
	}

	/**
	* Removes all the accounts from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of accounts where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching accounts
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Returns the number of accounts where userId = &#63; and address = &#63;.
	*
	* @param userId the user ID
	* @param address the address
	* @return the number of matching accounts
	* @throws SystemException if a system exception occurred
	*/
	public static int countByU_A(long userId, java.lang.String address)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByU_A(userId, address);
	}

	/**
	* Returns the number of accounts.
	*
	* @return the number of accounts
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static AccountPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AccountPersistence)PortletBeanLocatorUtil.locate(com.liferay.mail.service.ClpSerializer.getServletContextName(),
					AccountPersistence.class.getName());

			ReferenceRegistry.registerReference(AccountUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(AccountPersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(AccountUtil.class, "_persistence");
	}

	private static AccountPersistence _persistence;
}