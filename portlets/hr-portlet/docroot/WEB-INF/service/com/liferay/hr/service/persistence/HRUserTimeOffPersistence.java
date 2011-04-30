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

import com.liferay.hr.model.HRUserTimeOff;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the h r user time off service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRUserTimeOffPersistenceImpl
 * @see HRUserTimeOffUtil
 * @generated
 */
public interface HRUserTimeOffPersistence extends BasePersistence<HRUserTimeOff> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HRUserTimeOffUtil} to access the h r user time off persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the h r user time off in the entity cache if it is enabled.
	*
	* @param hrUserTimeOff the h r user time off to cache
	*/
	public void cacheResult(com.liferay.hr.model.HRUserTimeOff hrUserTimeOff);

	/**
	* Caches the h r user time offs in the entity cache if it is enabled.
	*
	* @param hrUserTimeOffs the h r user time offs to cache
	*/
	public void cacheResult(
		java.util.List<com.liferay.hr.model.HRUserTimeOff> hrUserTimeOffs);

	/**
	* Creates a new h r user time off with the primary key. Does not add the h r user time off to the database.
	*
	* @param hrUserTimeOffId the primary key for the new h r user time off
	* @return the new h r user time off
	*/
	public com.liferay.hr.model.HRUserTimeOff create(long hrUserTimeOffId);

	/**
	* Removes the h r user time off with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrUserTimeOffId the primary key of the h r user time off to remove
	* @return the h r user time off that was removed
	* @throws com.liferay.hr.NoSuchUserTimeOffException if a h r user time off with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRUserTimeOff remove(long hrUserTimeOffId)
		throws com.liferay.hr.NoSuchUserTimeOffException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.hr.model.HRUserTimeOff updateImpl(
		com.liferay.hr.model.HRUserTimeOff hrUserTimeOff, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the h r user time off with the primary key or throws a {@link com.liferay.hr.NoSuchUserTimeOffException} if it could not be found.
	*
	* @param hrUserTimeOffId the primary key of the h r user time off to find
	* @return the h r user time off
	* @throws com.liferay.hr.NoSuchUserTimeOffException if a h r user time off with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRUserTimeOff findByPrimaryKey(
		long hrUserTimeOffId)
		throws com.liferay.hr.NoSuchUserTimeOffException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the h r user time off with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrUserTimeOffId the primary key of the h r user time off to find
	* @return the h r user time off, or <code>null</code> if a h r user time off with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRUserTimeOff fetchByPrimaryKey(
		long hrUserTimeOffId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the h r user time offs.
	*
	* @return the h r user time offs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRUserTimeOff> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the h r user time offs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r user time offs to return
	* @param end the upper bound of the range of h r user time offs to return (not inclusive)
	* @return the range of h r user time offs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRUserTimeOff> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the h r user time offs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r user time offs to return
	* @param end the upper bound of the range of h r user time offs to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r user time offs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRUserTimeOff> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the h r user time offs from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the h r user time offs.
	*
	* @return the number of h r user time offs
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public HRUserTimeOff remove(HRUserTimeOff hrUserTimeOff)
		throws SystemException;
}