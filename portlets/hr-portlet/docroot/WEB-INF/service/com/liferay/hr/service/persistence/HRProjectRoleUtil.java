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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the h r project role service. This utility wraps {@link HRProjectRolePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRProjectRolePersistence
 * @see HRProjectRolePersistenceImpl
 * @generated
 */
public class HRProjectRoleUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(HRProjectRole hrProjectRole) {
		getPersistence().clearCache(hrProjectRole);
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
	public static List<HRProjectRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HRProjectRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HRProjectRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static HRProjectRole remove(HRProjectRole hrProjectRole)
		throws SystemException {
		return getPersistence().remove(hrProjectRole);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static HRProjectRole update(HRProjectRole hrProjectRole,
		boolean merge) throws SystemException {
		return getPersistence().update(hrProjectRole, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static HRProjectRole update(HRProjectRole hrProjectRole,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(hrProjectRole, merge, serviceContext);
	}

	/**
	* Caches the h r project role in the entity cache if it is enabled.
	*
	* @param hrProjectRole the h r project role
	*/
	public static void cacheResult(
		com.liferay.hr.model.HRProjectRole hrProjectRole) {
		getPersistence().cacheResult(hrProjectRole);
	}

	/**
	* Caches the h r project roles in the entity cache if it is enabled.
	*
	* @param hrProjectRoles the h r project roles
	*/
	public static void cacheResult(
		java.util.List<com.liferay.hr.model.HRProjectRole> hrProjectRoles) {
		getPersistence().cacheResult(hrProjectRoles);
	}

	/**
	* Creates a new h r project role with the primary key. Does not add the h r project role to the database.
	*
	* @param hrProjectRoleId the primary key for the new h r project role
	* @return the new h r project role
	*/
	public static com.liferay.hr.model.HRProjectRole create(
		long hrProjectRoleId) {
		return getPersistence().create(hrProjectRoleId);
	}

	/**
	* Removes the h r project role with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrProjectRoleId the primary key of the h r project role
	* @return the h r project role that was removed
	* @throws com.liferay.hr.NoSuchProjectRoleException if a h r project role with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRProjectRole remove(
		long hrProjectRoleId)
		throws com.liferay.hr.NoSuchProjectRoleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(hrProjectRoleId);
	}

	public static com.liferay.hr.model.HRProjectRole updateImpl(
		com.liferay.hr.model.HRProjectRole hrProjectRole, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(hrProjectRole, merge);
	}

	/**
	* Returns the h r project role with the primary key or throws a {@link com.liferay.hr.NoSuchProjectRoleException} if it could not be found.
	*
	* @param hrProjectRoleId the primary key of the h r project role
	* @return the h r project role
	* @throws com.liferay.hr.NoSuchProjectRoleException if a h r project role with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRProjectRole findByPrimaryKey(
		long hrProjectRoleId)
		throws com.liferay.hr.NoSuchProjectRoleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(hrProjectRoleId);
	}

	/**
	* Returns the h r project role with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrProjectRoleId the primary key of the h r project role
	* @return the h r project role, or <code>null</code> if a h r project role with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRProjectRole fetchByPrimaryKey(
		long hrProjectRoleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(hrProjectRoleId);
	}

	/**
	* Returns all the h r project roles.
	*
	* @return the h r project roles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRProjectRole> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the h r project roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r project roles
	* @param end the upper bound of the range of h r project roles (not inclusive)
	* @return the range of h r project roles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRProjectRole> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the h r project roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r project roles
	* @param end the upper bound of the range of h r project roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r project roles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRProjectRole> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the h r project roles from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of h r project roles.
	*
	* @return the number of h r project roles
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static HRProjectRolePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (HRProjectRolePersistence)PortletBeanLocatorUtil.locate(com.liferay.hr.service.ClpSerializer.getServletContextName(),
					HRProjectRolePersistence.class.getName());

			ReferenceRegistry.registerReference(HRProjectRoleUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(HRProjectRolePersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(HRProjectRoleUtil.class,
			"_persistence");
	}

	private static HRProjectRolePersistence _persistence;
}