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

import com.liferay.hr.model.HRProjectRole;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the h r project role service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HRProjectRolePersistenceImpl
 * @see HRProjectRoleUtil
 * @generated
 */
public interface HRProjectRolePersistence extends BasePersistence<HRProjectRole> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HRProjectRoleUtil} to access the h r project role persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the h r project role in the entity cache if it is enabled.
	*
	* @param hrProjectRole the h r project role to cache
	*/
	public void cacheResult(com.liferay.hr.model.HRProjectRole hrProjectRole);

	/**
	* Caches the h r project roles in the entity cache if it is enabled.
	*
	* @param hrProjectRoles the h r project roles to cache
	*/
	public void cacheResult(
		java.util.List<com.liferay.hr.model.HRProjectRole> hrProjectRoles);

	/**
	* Creates a new h r project role with the primary key. Does not add the h r project role to the database.
	*
	* @param hrProjectRoleId the primary key for the new h r project role
	* @return the new h r project role
	*/
	public com.liferay.hr.model.HRProjectRole create(long hrProjectRoleId);

	/**
	* Removes the h r project role with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrProjectRoleId the primary key of the h r project role to remove
	* @return the h r project role that was removed
	* @throws com.liferay.hr.NoSuchProjectRoleException if a h r project role with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRProjectRole remove(long hrProjectRoleId)
		throws com.liferay.hr.NoSuchProjectRoleException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.hr.model.HRProjectRole updateImpl(
		com.liferay.hr.model.HRProjectRole hrProjectRole, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the h r project role with the primary key or throws a {@link com.liferay.hr.NoSuchProjectRoleException} if it could not be found.
	*
	* @param hrProjectRoleId the primary key of the h r project role to find
	* @return the h r project role
	* @throws com.liferay.hr.NoSuchProjectRoleException if a h r project role with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRProjectRole findByPrimaryKey(
		long hrProjectRoleId)
		throws com.liferay.hr.NoSuchProjectRoleException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the h r project role with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrProjectRoleId the primary key of the h r project role to find
	* @return the h r project role, or <code>null</code> if a h r project role with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRProjectRole fetchByPrimaryKey(
		long hrProjectRoleId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the h r project roles.
	*
	* @return the h r project roles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRProjectRole> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the h r project roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r project roles to return
	* @param end the upper bound of the range of h r project roles to return (not inclusive)
	* @return the range of h r project roles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRProjectRole> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the h r project roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r project roles to return
	* @param end the upper bound of the range of h r project roles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r project roles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRProjectRole> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the h r project roles from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the h r project roles.
	*
	* @return the number of h r project roles
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public HRProjectRole remove(HRProjectRole hrProjectRole)
		throws SystemException;
}