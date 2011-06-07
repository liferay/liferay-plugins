/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.hr.service.persistence;

import com.liferay.hr.model.HRExpenseAccount;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the h r expense account service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRExpenseAccountPersistenceImpl
 * @see HRExpenseAccountUtil
 * @generated
 */
public interface HRExpenseAccountPersistence extends BasePersistence<HRExpenseAccount> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HRExpenseAccountUtil} to access the h r expense account persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the h r expense account in the entity cache if it is enabled.
	*
	* @param hrExpenseAccount the h r expense account
	*/
	public void cacheResult(
		com.liferay.hr.model.HRExpenseAccount hrExpenseAccount);

	/**
	* Caches the h r expense accounts in the entity cache if it is enabled.
	*
	* @param hrExpenseAccounts the h r expense accounts
	*/
	public void cacheResult(
		java.util.List<com.liferay.hr.model.HRExpenseAccount> hrExpenseAccounts);

	/**
	* Creates a new h r expense account with the primary key. Does not add the h r expense account to the database.
	*
	* @param hrExpenseAccountId the primary key for the new h r expense account
	* @return the new h r expense account
	*/
	public com.liferay.hr.model.HRExpenseAccount create(long hrExpenseAccountId);

	/**
	* Removes the h r expense account with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrExpenseAccountId the primary key of the h r expense account
	* @return the h r expense account that was removed
	* @throws com.liferay.hr.NoSuchExpenseAccountException if a h r expense account with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRExpenseAccount remove(long hrExpenseAccountId)
		throws com.liferay.hr.NoSuchExpenseAccountException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.hr.model.HRExpenseAccount updateImpl(
		com.liferay.hr.model.HRExpenseAccount hrExpenseAccount, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the h r expense account with the primary key or throws a {@link com.liferay.hr.NoSuchExpenseAccountException} if it could not be found.
	*
	* @param hrExpenseAccountId the primary key of the h r expense account
	* @return the h r expense account
	* @throws com.liferay.hr.NoSuchExpenseAccountException if a h r expense account with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRExpenseAccount findByPrimaryKey(
		long hrExpenseAccountId)
		throws com.liferay.hr.NoSuchExpenseAccountException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the h r expense account with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrExpenseAccountId the primary key of the h r expense account
	* @return the h r expense account, or <code>null</code> if a h r expense account with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRExpenseAccount fetchByPrimaryKey(
		long hrExpenseAccountId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the h r expense account where groupId = &#63; and name = &#63; or throws a {@link com.liferay.hr.NoSuchExpenseAccountException} if it could not be found.
	*
	* @param groupId the group ID
	* @param name the name
	* @return the matching h r expense account
	* @throws com.liferay.hr.NoSuchExpenseAccountException if a matching h r expense account could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRExpenseAccount findByG_N(long groupId,
		java.lang.String name)
		throws com.liferay.hr.NoSuchExpenseAccountException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the h r expense account where groupId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param name the name
	* @return the matching h r expense account, or <code>null</code> if a matching h r expense account could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRExpenseAccount fetchByG_N(long groupId,
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the h r expense account where groupId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param name the name
	* @return the matching h r expense account, or <code>null</code> if a matching h r expense account could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRExpenseAccount fetchByG_N(long groupId,
		java.lang.String name, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the h r expense accounts.
	*
	* @return the h r expense accounts
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRExpenseAccount> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the h r expense accounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r expense accounts
	* @param end the upper bound of the range of h r expense accounts (not inclusive)
	* @return the range of h r expense accounts
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRExpenseAccount> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the h r expense accounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r expense accounts
	* @param end the upper bound of the range of h r expense accounts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r expense accounts
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRExpenseAccount> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the h r expense account where groupId = &#63; and name = &#63; from the database.
	*
	* @param groupId the group ID
	* @param name the name
	* @throws SystemException if a system exception occurred
	*/
	public void removeByG_N(long groupId, java.lang.String name)
		throws com.liferay.hr.NoSuchExpenseAccountException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the h r expense accounts from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of h r expense accounts where groupId = &#63; and name = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @return the number of matching h r expense accounts
	* @throws SystemException if a system exception occurred
	*/
	public int countByG_N(long groupId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of h r expense accounts.
	*
	* @return the number of h r expense accounts
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public HRExpenseAccount remove(HRExpenseAccount hrExpenseAccount)
		throws SystemException;
}