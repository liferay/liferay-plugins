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

import com.liferay.hr.model.HRUser;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the h r user service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRUserPersistenceImpl
 * @see HRUserUtil
 * @generated
 */
public interface HRUserPersistence extends BasePersistence<HRUser> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HRUserUtil} to access the h r user persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the h r user in the entity cache if it is enabled.
	*
	* @param hrUser the h r user
	*/
	public void cacheResult(com.liferay.hr.model.HRUser hrUser);

	/**
	* Caches the h r users in the entity cache if it is enabled.
	*
	* @param hrUsers the h r users
	*/
	public void cacheResult(java.util.List<com.liferay.hr.model.HRUser> hrUsers);

	/**
	* Creates a new h r user with the primary key. Does not add the h r user to the database.
	*
	* @param hrUserId the primary key for the new h r user
	* @return the new h r user
	*/
	public com.liferay.hr.model.HRUser create(long hrUserId);

	/**
	* Removes the h r user with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrUserId the primary key of the h r user
	* @return the h r user that was removed
	* @throws com.liferay.hr.NoSuchUserException if a h r user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRUser remove(long hrUserId)
		throws com.liferay.hr.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.hr.model.HRUser updateImpl(
		com.liferay.hr.model.HRUser hrUser, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the h r user with the primary key or throws a {@link com.liferay.hr.NoSuchUserException} if it could not be found.
	*
	* @param hrUserId the primary key of the h r user
	* @return the h r user
	* @throws com.liferay.hr.NoSuchUserException if a h r user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRUser findByPrimaryKey(long hrUserId)
		throws com.liferay.hr.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the h r user with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrUserId the primary key of the h r user
	* @return the h r user, or <code>null</code> if a h r user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRUser fetchByPrimaryKey(long hrUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the h r users.
	*
	* @return the h r users
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRUser> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the h r users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r users
	* @param end the upper bound of the range of h r users (not inclusive)
	* @return the range of h r users
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRUser> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the h r users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r users
	* @param end the upper bound of the range of h r users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r users
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRUser> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the h r users from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of h r users.
	*
	* @return the number of h r users
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public HRUser remove(HRUser hrUser) throws SystemException;
}