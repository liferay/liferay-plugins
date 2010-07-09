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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AccountPersistence
 * @see       AccountPersistenceImpl
 * @generated
 */
public class AccountUtil {
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
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static Account remove(Account account) throws SystemException {
		return getPersistence().remove(account);
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

	public static void cacheResult(com.liferay.mail.model.Account account) {
		getPersistence().cacheResult(account);
	}

	public static void cacheResult(
		java.util.List<com.liferay.mail.model.Account> accounts) {
		getPersistence().cacheResult(accounts);
	}

	public static com.liferay.mail.model.Account create(long accountId) {
		return getPersistence().create(accountId);
	}

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

	public static com.liferay.mail.model.Account findByPrimaryKey(
		long accountId)
		throws com.liferay.mail.NoSuchAccountException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(accountId);
	}

	public static com.liferay.mail.model.Account fetchByPrimaryKey(
		long accountId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(accountId);
	}

	public static java.util.List<com.liferay.mail.model.Account> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId);
	}

	public static java.util.List<com.liferay.mail.model.Account> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId, start, end);
	}

	public static java.util.List<com.liferay.mail.model.Account> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
	}

	public static com.liferay.mail.model.Account findByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchAccountException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	public static com.liferay.mail.model.Account findByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchAccountException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	public static com.liferay.mail.model.Account[] findByUserId_PrevAndNext(
		long accountId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchAccountException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_PrevAndNext(accountId, userId,
			orderByComparator);
	}

	public static com.liferay.mail.model.Account findByU_A(long userId,
		java.lang.String address)
		throws com.liferay.mail.NoSuchAccountException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_A(userId, address);
	}

	public static com.liferay.mail.model.Account fetchByU_A(long userId,
		java.lang.String address)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByU_A(userId, address);
	}

	public static com.liferay.mail.model.Account fetchByU_A(long userId,
		java.lang.String address, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByU_A(userId, address, retrieveFromCache);
	}

	public static java.util.List<com.liferay.mail.model.Account> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.mail.model.Account> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.mail.model.Account> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	public static void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUserId(userId);
	}

	public static void removeByU_A(long userId, java.lang.String address)
		throws com.liferay.mail.NoSuchAccountException,
			com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByU_A(userId, address);
	}

	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	public static int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUserId(userId);
	}

	public static int countByU_A(long userId, java.lang.String address)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByU_A(userId, address);
	}

	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static AccountPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AccountPersistence)PortletBeanLocatorUtil.locate(com.liferay.mail.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					AccountPersistence.class.getName());
		}

		return _persistence;
	}

	public void setPersistence(AccountPersistence persistence) {
		_persistence = persistence;
	}

	private static AccountPersistence _persistence;
}