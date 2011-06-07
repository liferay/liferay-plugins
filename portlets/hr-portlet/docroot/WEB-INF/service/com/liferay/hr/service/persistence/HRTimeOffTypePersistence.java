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

import com.liferay.hr.model.HRTimeOffType;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the h r time off type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRTimeOffTypePersistenceImpl
 * @see HRTimeOffTypeUtil
 * @generated
 */
public interface HRTimeOffTypePersistence extends BasePersistence<HRTimeOffType> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HRTimeOffTypeUtil} to access the h r time off type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the h r time off type in the entity cache if it is enabled.
	*
	* @param hrTimeOffType the h r time off type
	*/
	public void cacheResult(com.liferay.hr.model.HRTimeOffType hrTimeOffType);

	/**
	* Caches the h r time off types in the entity cache if it is enabled.
	*
	* @param hrTimeOffTypes the h r time off types
	*/
	public void cacheResult(
		java.util.List<com.liferay.hr.model.HRTimeOffType> hrTimeOffTypes);

	/**
	* Creates a new h r time off type with the primary key. Does not add the h r time off type to the database.
	*
	* @param hrTimeOffTypeId the primary key for the new h r time off type
	* @return the new h r time off type
	*/
	public com.liferay.hr.model.HRTimeOffType create(long hrTimeOffTypeId);

	/**
	* Removes the h r time off type with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrTimeOffTypeId the primary key of the h r time off type
	* @return the h r time off type that was removed
	* @throws com.liferay.hr.NoSuchTimeOffTypeException if a h r time off type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRTimeOffType remove(long hrTimeOffTypeId)
		throws com.liferay.hr.NoSuchTimeOffTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.hr.model.HRTimeOffType updateImpl(
		com.liferay.hr.model.HRTimeOffType hrTimeOffType, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the h r time off type with the primary key or throws a {@link com.liferay.hr.NoSuchTimeOffTypeException} if it could not be found.
	*
	* @param hrTimeOffTypeId the primary key of the h r time off type
	* @return the h r time off type
	* @throws com.liferay.hr.NoSuchTimeOffTypeException if a h r time off type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRTimeOffType findByPrimaryKey(
		long hrTimeOffTypeId)
		throws com.liferay.hr.NoSuchTimeOffTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the h r time off type with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrTimeOffTypeId the primary key of the h r time off type
	* @return the h r time off type, or <code>null</code> if a h r time off type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRTimeOffType fetchByPrimaryKey(
		long hrTimeOffTypeId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the h r time off types.
	*
	* @return the h r time off types
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRTimeOffType> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the h r time off types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r time off types
	* @param end the upper bound of the range of h r time off types (not inclusive)
	* @return the range of h r time off types
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRTimeOffType> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the h r time off types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r time off types
	* @param end the upper bound of the range of h r time off types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r time off types
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRTimeOffType> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the h r time off types from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of h r time off types.
	*
	* @return the number of h r time off types
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public HRTimeOffType remove(HRTimeOffType hrTimeOffType)
		throws SystemException;
}