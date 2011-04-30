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

import com.liferay.hr.model.HRBillability;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the h r billability service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HRBillabilityPersistenceImpl
 * @see HRBillabilityUtil
 * @generated
 */
public interface HRBillabilityPersistence extends BasePersistence<HRBillability> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HRBillabilityUtil} to access the h r billability persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the h r billability in the entity cache if it is enabled.
	*
	* @param hrBillability the h r billability to cache
	*/
	public void cacheResult(com.liferay.hr.model.HRBillability hrBillability);

	/**
	* Caches the h r billabilities in the entity cache if it is enabled.
	*
	* @param hrBillabilities the h r billabilities to cache
	*/
	public void cacheResult(
		java.util.List<com.liferay.hr.model.HRBillability> hrBillabilities);

	/**
	* Creates a new h r billability with the primary key. Does not add the h r billability to the database.
	*
	* @param hrBillabilityId the primary key for the new h r billability
	* @return the new h r billability
	*/
	public com.liferay.hr.model.HRBillability create(long hrBillabilityId);

	/**
	* Removes the h r billability with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrBillabilityId the primary key of the h r billability to remove
	* @return the h r billability that was removed
	* @throws com.liferay.hr.NoSuchBillabilityException if a h r billability with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRBillability remove(long hrBillabilityId)
		throws com.liferay.hr.NoSuchBillabilityException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.hr.model.HRBillability updateImpl(
		com.liferay.hr.model.HRBillability hrBillability, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the h r billability with the primary key or throws a {@link com.liferay.hr.NoSuchBillabilityException} if it could not be found.
	*
	* @param hrBillabilityId the primary key of the h r billability to find
	* @return the h r billability
	* @throws com.liferay.hr.NoSuchBillabilityException if a h r billability with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRBillability findByPrimaryKey(
		long hrBillabilityId)
		throws com.liferay.hr.NoSuchBillabilityException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the h r billability with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrBillabilityId the primary key of the h r billability to find
	* @return the h r billability, or <code>null</code> if a h r billability with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRBillability fetchByPrimaryKey(
		long hrBillabilityId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the h r billability where groupId = &#63; and code = &#63; or throws a {@link com.liferay.hr.NoSuchBillabilityException} if it could not be found.
	*
	* @param groupId the group ID to search with
	* @param code the code to search with
	* @return the matching h r billability
	* @throws com.liferay.hr.NoSuchBillabilityException if a matching h r billability could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRBillability findByG_C(long groupId,
		java.lang.String code)
		throws com.liferay.hr.NoSuchBillabilityException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the h r billability where groupId = &#63; and code = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID to search with
	* @param code the code to search with
	* @return the matching h r billability, or <code>null</code> if a matching h r billability could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRBillability fetchByG_C(long groupId,
		java.lang.String code)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the h r billability where groupId = &#63; and code = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID to search with
	* @param code the code to search with
	* @return the matching h r billability, or <code>null</code> if a matching h r billability could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRBillability fetchByG_C(long groupId,
		java.lang.String code, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the h r billabilities.
	*
	* @return the h r billabilities
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRBillability> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the h r billabilities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r billabilities to return
	* @param end the upper bound of the range of h r billabilities to return (not inclusive)
	* @return the range of h r billabilities
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRBillability> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the h r billabilities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r billabilities to return
	* @param end the upper bound of the range of h r billabilities to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r billabilities
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRBillability> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the h r billability where groupId = &#63; and code = &#63; from the database.
	*
	* @param groupId the group ID to search with
	* @param code the code to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByG_C(long groupId, java.lang.String code)
		throws com.liferay.hr.NoSuchBillabilityException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the h r billabilities from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the h r billabilities where groupId = &#63; and code = &#63;.
	*
	* @param groupId the group ID to search with
	* @param code the code to search with
	* @return the number of matching h r billabilities
	* @throws SystemException if a system exception occurred
	*/
	public int countByG_C(long groupId, java.lang.String code)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the h r billabilities.
	*
	* @return the number of h r billabilities
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public HRBillability remove(HRBillability hrBillability)
		throws SystemException;
}