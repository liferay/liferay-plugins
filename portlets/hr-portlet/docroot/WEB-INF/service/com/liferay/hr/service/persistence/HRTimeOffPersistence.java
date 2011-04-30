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

import com.liferay.hr.model.HRTimeOff;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the h r time off service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRTimeOffPersistenceImpl
 * @see HRTimeOffUtil
 * @generated
 */
public interface HRTimeOffPersistence extends BasePersistence<HRTimeOff> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HRTimeOffUtil} to access the h r time off persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the h r time off in the entity cache if it is enabled.
	*
	* @param hrTimeOff the h r time off to cache
	*/
	public void cacheResult(com.liferay.hr.model.HRTimeOff hrTimeOff);

	/**
	* Caches the h r time offs in the entity cache if it is enabled.
	*
	* @param hrTimeOffs the h r time offs to cache
	*/
	public void cacheResult(
		java.util.List<com.liferay.hr.model.HRTimeOff> hrTimeOffs);

	/**
	* Creates a new h r time off with the primary key. Does not add the h r time off to the database.
	*
	* @param hrTimeOffId the primary key for the new h r time off
	* @return the new h r time off
	*/
	public com.liferay.hr.model.HRTimeOff create(long hrTimeOffId);

	/**
	* Removes the h r time off with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrTimeOffId the primary key of the h r time off to remove
	* @return the h r time off that was removed
	* @throws com.liferay.hr.NoSuchTimeOffException if a h r time off with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRTimeOff remove(long hrTimeOffId)
		throws com.liferay.hr.NoSuchTimeOffException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.hr.model.HRTimeOff updateImpl(
		com.liferay.hr.model.HRTimeOff hrTimeOff, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the h r time off with the primary key or throws a {@link com.liferay.hr.NoSuchTimeOffException} if it could not be found.
	*
	* @param hrTimeOffId the primary key of the h r time off to find
	* @return the h r time off
	* @throws com.liferay.hr.NoSuchTimeOffException if a h r time off with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRTimeOff findByPrimaryKey(long hrTimeOffId)
		throws com.liferay.hr.NoSuchTimeOffException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the h r time off with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrTimeOffId the primary key of the h r time off to find
	* @return the h r time off, or <code>null</code> if a h r time off with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRTimeOff fetchByPrimaryKey(long hrTimeOffId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the h r time offs.
	*
	* @return the h r time offs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRTimeOff> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the h r time offs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r time offs to return
	* @param end the upper bound of the range of h r time offs to return (not inclusive)
	* @return the range of h r time offs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRTimeOff> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the h r time offs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r time offs to return
	* @param end the upper bound of the range of h r time offs to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r time offs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRTimeOff> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the h r time offs from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the h r time offs.
	*
	* @return the number of h r time offs
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public HRTimeOff remove(HRTimeOff hrTimeOff) throws SystemException;
}