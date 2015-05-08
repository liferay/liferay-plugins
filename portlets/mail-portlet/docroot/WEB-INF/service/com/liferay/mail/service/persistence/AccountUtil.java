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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the account service. This utility wraps {@link com.liferay.mail.service.persistence.impl.AccountPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountPersistence
 * @see com.liferay.mail.service.persistence.impl.AccountPersistenceImpl
 * @generated
 */
@ProviderType
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
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Account> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Account> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Account> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Account> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static Account update(Account account) {
		return getPersistence().update(account);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static Account update(Account account, ServiceContext serviceContext) {
		return getPersistence().update(account, serviceContext);
	}

	/**
	* Returns all the accounts where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching accounts
	*/
	public static List<Account> findByUserId(long userId) {
		return getPersistence().findByUserId(userId);
	}

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
	public static List<Account> findByUserId(long userId, int start, int end) {
		return getPersistence().findByUserId(userId, start, end);
	}

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
	public static List<Account> findByUserId(long userId, int start, int end,
		OrderByComparator<Account> orderByComparator) {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
	}

	/**
	* Returns the first account in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account
	* @throws NoSuchAccountException if a matching account could not be found
	*/
	public static Account findByUserId_First(long userId,
		OrderByComparator<Account> orderByComparator)
		throws com.liferay.mail.NoSuchAccountException {
		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the first account in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account, or <code>null</code> if a matching account could not be found
	*/
	public static Account fetchByUserId_First(long userId,
		OrderByComparator<Account> orderByComparator) {
		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the last account in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account
	* @throws NoSuchAccountException if a matching account could not be found
	*/
	public static Account findByUserId_Last(long userId,
		OrderByComparator<Account> orderByComparator)
		throws com.liferay.mail.NoSuchAccountException {
		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the last account in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account, or <code>null</code> if a matching account could not be found
	*/
	public static Account fetchByUserId_Last(long userId,
		OrderByComparator<Account> orderByComparator) {
		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the accounts before and after the current account in the ordered set where userId = &#63;.
	*
	* @param accountId the primary key of the current account
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account
	* @throws NoSuchAccountException if a account with the primary key could not be found
	*/
	public static Account[] findByUserId_PrevAndNext(long accountId,
		long userId, OrderByComparator<Account> orderByComparator)
		throws com.liferay.mail.NoSuchAccountException {
		return getPersistence()
				   .findByUserId_PrevAndNext(accountId, userId,
			orderByComparator);
	}

	/**
	* Removes all the accounts where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public static void removeByUserId(long userId) {
		getPersistence().removeByUserId(userId);
	}

	/**
	* Returns the number of accounts where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching accounts
	*/
	public static int countByUserId(long userId) {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Returns the account where userId = &#63; and address = &#63; or throws a {@link NoSuchAccountException} if it could not be found.
	*
	* @param userId the user ID
	* @param address the address
	* @return the matching account
	* @throws NoSuchAccountException if a matching account could not be found
	*/
	public static Account findByU_A(long userId, java.lang.String address)
		throws com.liferay.mail.NoSuchAccountException {
		return getPersistence().findByU_A(userId, address);
	}

	/**
	* Returns the account where userId = &#63; and address = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param address the address
	* @return the matching account, or <code>null</code> if a matching account could not be found
	*/
	public static Account fetchByU_A(long userId, java.lang.String address) {
		return getPersistence().fetchByU_A(userId, address);
	}

	/**
	* Returns the account where userId = &#63; and address = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param address the address
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching account, or <code>null</code> if a matching account could not be found
	*/
	public static Account fetchByU_A(long userId, java.lang.String address,
		boolean retrieveFromCache) {
		return getPersistence().fetchByU_A(userId, address, retrieveFromCache);
	}

	/**
	* Removes the account where userId = &#63; and address = &#63; from the database.
	*
	* @param userId the user ID
	* @param address the address
	* @return the account that was removed
	*/
	public static Account removeByU_A(long userId, java.lang.String address)
		throws com.liferay.mail.NoSuchAccountException {
		return getPersistence().removeByU_A(userId, address);
	}

	/**
	* Returns the number of accounts where userId = &#63; and address = &#63;.
	*
	* @param userId the user ID
	* @param address the address
	* @return the number of matching accounts
	*/
	public static int countByU_A(long userId, java.lang.String address) {
		return getPersistence().countByU_A(userId, address);
	}

	/**
	* Caches the account in the entity cache if it is enabled.
	*
	* @param account the account
	*/
	public static void cacheResult(Account account) {
		getPersistence().cacheResult(account);
	}

	/**
	* Caches the accounts in the entity cache if it is enabled.
	*
	* @param accounts the accounts
	*/
	public static void cacheResult(List<Account> accounts) {
		getPersistence().cacheResult(accounts);
	}

	/**
	* Creates a new account with the primary key. Does not add the account to the database.
	*
	* @param accountId the primary key for the new account
	* @return the new account
	*/
	public static Account create(long accountId) {
		return getPersistence().create(accountId);
	}

	/**
	* Removes the account with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountId the primary key of the account
	* @return the account that was removed
	* @throws NoSuchAccountException if a account with the primary key could not be found
	*/
	public static Account remove(long accountId)
		throws com.liferay.mail.NoSuchAccountException {
		return getPersistence().remove(accountId);
	}

	public static Account updateImpl(Account account) {
		return getPersistence().updateImpl(account);
	}

	/**
	* Returns the account with the primary key or throws a {@link NoSuchAccountException} if it could not be found.
	*
	* @param accountId the primary key of the account
	* @return the account
	* @throws NoSuchAccountException if a account with the primary key could not be found
	*/
	public static Account findByPrimaryKey(long accountId)
		throws com.liferay.mail.NoSuchAccountException {
		return getPersistence().findByPrimaryKey(accountId);
	}

	/**
	* Returns the account with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accountId the primary key of the account
	* @return the account, or <code>null</code> if a account with the primary key could not be found
	*/
	public static Account fetchByPrimaryKey(long accountId) {
		return getPersistence().fetchByPrimaryKey(accountId);
	}

	public static java.util.Map<java.io.Serializable, Account> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the accounts.
	*
	* @return the accounts
	*/
	public static List<Account> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<Account> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<Account> findAll(int start, int end,
		OrderByComparator<Account> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the accounts from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of accounts.
	*
	* @return the number of accounts
	*/
	public static int countAll() {
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

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setPersistence(AccountPersistence persistence) {
	}

	private static AccountPersistence _persistence;
}