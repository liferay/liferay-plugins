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

import com.liferay.hr.model.HRWageType;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the h r wage type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRWageTypePersistenceImpl
 * @see HRWageTypeUtil
 * @generated
 */
public interface HRWageTypePersistence extends BasePersistence<HRWageType> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HRWageTypeUtil} to access the h r wage type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the h r wage type in the entity cache if it is enabled.
	*
	* @param hrWageType the h r wage type
	*/
	public void cacheResult(com.liferay.hr.model.HRWageType hrWageType);

	/**
	* Caches the h r wage types in the entity cache if it is enabled.
	*
	* @param hrWageTypes the h r wage types
	*/
	public void cacheResult(
		java.util.List<com.liferay.hr.model.HRWageType> hrWageTypes);

	/**
	* Creates a new h r wage type with the primary key. Does not add the h r wage type to the database.
	*
	* @param hrWageTypeId the primary key for the new h r wage type
	* @return the new h r wage type
	*/
	public com.liferay.hr.model.HRWageType create(long hrWageTypeId);

	/**
	* Removes the h r wage type with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrWageTypeId the primary key of the h r wage type
	* @return the h r wage type that was removed
	* @throws com.liferay.hr.NoSuchWageTypeException if a h r wage type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRWageType remove(long hrWageTypeId)
		throws com.liferay.hr.NoSuchWageTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.hr.model.HRWageType updateImpl(
		com.liferay.hr.model.HRWageType hrWageType, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the h r wage type with the primary key or throws a {@link com.liferay.hr.NoSuchWageTypeException} if it could not be found.
	*
	* @param hrWageTypeId the primary key of the h r wage type
	* @return the h r wage type
	* @throws com.liferay.hr.NoSuchWageTypeException if a h r wage type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRWageType findByPrimaryKey(long hrWageTypeId)
		throws com.liferay.hr.NoSuchWageTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the h r wage type with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrWageTypeId the primary key of the h r wage type
	* @return the h r wage type, or <code>null</code> if a h r wage type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRWageType fetchByPrimaryKey(long hrWageTypeId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the h r wage type where groupId = &#63; and code = &#63; or throws a {@link com.liferay.hr.NoSuchWageTypeException} if it could not be found.
	*
	* @param groupId the group ID
	* @param code the code
	* @return the matching h r wage type
	* @throws com.liferay.hr.NoSuchWageTypeException if a matching h r wage type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRWageType findByG_C(long groupId,
		java.lang.String code)
		throws com.liferay.hr.NoSuchWageTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the h r wage type where groupId = &#63; and code = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param code the code
	* @return the matching h r wage type, or <code>null</code> if a matching h r wage type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRWageType fetchByG_C(long groupId,
		java.lang.String code)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the h r wage type where groupId = &#63; and code = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param code the code
	* @return the matching h r wage type, or <code>null</code> if a matching h r wage type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRWageType fetchByG_C(long groupId,
		java.lang.String code, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the h r wage types.
	*
	* @return the h r wage types
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRWageType> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the h r wage types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r wage types
	* @param end the upper bound of the range of h r wage types (not inclusive)
	* @return the range of h r wage types
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRWageType> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the h r wage types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r wage types
	* @param end the upper bound of the range of h r wage types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r wage types
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRWageType> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the h r wage type where groupId = &#63; and code = &#63; from the database.
	*
	* @param groupId the group ID
	* @param code the code
	* @throws SystemException if a system exception occurred
	*/
	public void removeByG_C(long groupId, java.lang.String code)
		throws com.liferay.hr.NoSuchWageTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the h r wage types from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of h r wage types where groupId = &#63; and code = &#63;.
	*
	* @param groupId the group ID
	* @param code the code
	* @return the number of matching h r wage types
	* @throws SystemException if a system exception occurred
	*/
	public int countByG_C(long groupId, java.lang.String code)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of h r wage types.
	*
	* @return the number of h r wage types
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public HRWageType remove(HRWageType hrWageType) throws SystemException;
}