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

import com.liferay.hr.model.HREmploymentType;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the h r employment type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HREmploymentTypePersistenceImpl
 * @see HREmploymentTypeUtil
 * @generated
 */
public interface HREmploymentTypePersistence extends BasePersistence<HREmploymentType> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HREmploymentTypeUtil} to access the h r employment type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the h r employment type in the entity cache if it is enabled.
	*
	* @param hrEmploymentType the h r employment type
	*/
	public void cacheResult(
		com.liferay.hr.model.HREmploymentType hrEmploymentType);

	/**
	* Caches the h r employment types in the entity cache if it is enabled.
	*
	* @param hrEmploymentTypes the h r employment types
	*/
	public void cacheResult(
		java.util.List<com.liferay.hr.model.HREmploymentType> hrEmploymentTypes);

	/**
	* Creates a new h r employment type with the primary key. Does not add the h r employment type to the database.
	*
	* @param hrEmploymentTypeId the primary key for the new h r employment type
	* @return the new h r employment type
	*/
	public com.liferay.hr.model.HREmploymentType create(long hrEmploymentTypeId);

	/**
	* Removes the h r employment type with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrEmploymentTypeId the primary key of the h r employment type
	* @return the h r employment type that was removed
	* @throws com.liferay.hr.NoSuchEmploymentTypeException if a h r employment type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HREmploymentType remove(long hrEmploymentTypeId)
		throws com.liferay.hr.NoSuchEmploymentTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.hr.model.HREmploymentType updateImpl(
		com.liferay.hr.model.HREmploymentType hrEmploymentType, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the h r employment type with the primary key or throws a {@link com.liferay.hr.NoSuchEmploymentTypeException} if it could not be found.
	*
	* @param hrEmploymentTypeId the primary key of the h r employment type
	* @return the h r employment type
	* @throws com.liferay.hr.NoSuchEmploymentTypeException if a h r employment type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HREmploymentType findByPrimaryKey(
		long hrEmploymentTypeId)
		throws com.liferay.hr.NoSuchEmploymentTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the h r employment type with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrEmploymentTypeId the primary key of the h r employment type
	* @return the h r employment type, or <code>null</code> if a h r employment type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HREmploymentType fetchByPrimaryKey(
		long hrEmploymentTypeId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the h r employment type where groupId = &#63; and code = &#63; or throws a {@link com.liferay.hr.NoSuchEmploymentTypeException} if it could not be found.
	*
	* @param groupId the group ID
	* @param code the code
	* @return the matching h r employment type
	* @throws com.liferay.hr.NoSuchEmploymentTypeException if a matching h r employment type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HREmploymentType findByG_C(long groupId,
		java.lang.String code)
		throws com.liferay.hr.NoSuchEmploymentTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the h r employment type where groupId = &#63; and code = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param code the code
	* @return the matching h r employment type, or <code>null</code> if a matching h r employment type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HREmploymentType fetchByG_C(long groupId,
		java.lang.String code)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the h r employment type where groupId = &#63; and code = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param code the code
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching h r employment type, or <code>null</code> if a matching h r employment type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HREmploymentType fetchByG_C(long groupId,
		java.lang.String code, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the h r employment types.
	*
	* @return the h r employment types
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HREmploymentType> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the h r employment types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r employment types
	* @param end the upper bound of the range of h r employment types (not inclusive)
	* @return the range of h r employment types
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HREmploymentType> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the h r employment types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r employment types
	* @param end the upper bound of the range of h r employment types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r employment types
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HREmploymentType> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the h r employment type where groupId = &#63; and code = &#63; from the database.
	*
	* @param groupId the group ID
	* @param code the code
	* @throws SystemException if a system exception occurred
	*/
	public void removeByG_C(long groupId, java.lang.String code)
		throws com.liferay.hr.NoSuchEmploymentTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the h r employment types from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of h r employment types where groupId = &#63; and code = &#63;.
	*
	* @param groupId the group ID
	* @param code the code
	* @return the number of matching h r employment types
	* @throws SystemException if a system exception occurred
	*/
	public int countByG_C(long groupId, java.lang.String code)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of h r employment types.
	*
	* @return the number of h r employment types
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public HREmploymentType remove(HREmploymentType hrEmploymentType)
		throws SystemException;
}