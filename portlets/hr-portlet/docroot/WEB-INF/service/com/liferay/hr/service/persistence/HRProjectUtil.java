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

import com.liferay.hr.model.HRProject;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the h r project service. This utility wraps {@link HRProjectPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRProjectPersistence
 * @see HRProjectPersistenceImpl
 * @generated
 */
public class HRProjectUtil {
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
	public static void clearCache(HRProject hrProject) {
		getPersistence().clearCache(hrProject);
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
	public static List<HRProject> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HRProject> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HRProject> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static HRProject remove(HRProject hrProject)
		throws SystemException {
		return getPersistence().remove(hrProject);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static HRProject update(HRProject hrProject, boolean merge)
		throws SystemException {
		return getPersistence().update(hrProject, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static HRProject update(HRProject hrProject, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(hrProject, merge, serviceContext);
	}

	/**
	* Caches the h r project in the entity cache if it is enabled.
	*
	* @param hrProject the h r project
	*/
	public static void cacheResult(com.liferay.hr.model.HRProject hrProject) {
		getPersistence().cacheResult(hrProject);
	}

	/**
	* Caches the h r projects in the entity cache if it is enabled.
	*
	* @param hrProjects the h r projects
	*/
	public static void cacheResult(
		java.util.List<com.liferay.hr.model.HRProject> hrProjects) {
		getPersistence().cacheResult(hrProjects);
	}

	/**
	* Creates a new h r project with the primary key. Does not add the h r project to the database.
	*
	* @param hrProjectId the primary key for the new h r project
	* @return the new h r project
	*/
	public static com.liferay.hr.model.HRProject create(long hrProjectId) {
		return getPersistence().create(hrProjectId);
	}

	/**
	* Removes the h r project with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrProjectId the primary key of the h r project
	* @return the h r project that was removed
	* @throws com.liferay.hr.NoSuchProjectException if a h r project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRProject remove(long hrProjectId)
		throws com.liferay.hr.NoSuchProjectException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(hrProjectId);
	}

	public static com.liferay.hr.model.HRProject updateImpl(
		com.liferay.hr.model.HRProject hrProject, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(hrProject, merge);
	}

	/**
	* Returns the h r project with the primary key or throws a {@link com.liferay.hr.NoSuchProjectException} if it could not be found.
	*
	* @param hrProjectId the primary key of the h r project
	* @return the h r project
	* @throws com.liferay.hr.NoSuchProjectException if a h r project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRProject findByPrimaryKey(
		long hrProjectId)
		throws com.liferay.hr.NoSuchProjectException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(hrProjectId);
	}

	/**
	* Returns the h r project with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrProjectId the primary key of the h r project
	* @return the h r project, or <code>null</code> if a h r project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRProject fetchByPrimaryKey(
		long hrProjectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(hrProjectId);
	}

	/**
	* Returns all the h r projects.
	*
	* @return the h r projects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRProject> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the h r projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r projects
	* @param end the upper bound of the range of h r projects (not inclusive)
	* @return the range of h r projects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRProject> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the h r projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r projects
	* @param end the upper bound of the range of h r projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r projects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRProject> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the h r projects from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of h r projects.
	*
	* @return the number of h r projects
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static HRProjectPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (HRProjectPersistence)PortletBeanLocatorUtil.locate(com.liferay.hr.service.ClpSerializer.getServletContextName(),
					HRProjectPersistence.class.getName());

			ReferenceRegistry.registerReference(HRProjectUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(HRProjectPersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(HRProjectUtil.class, "_persistence");
	}

	private static HRProjectPersistence _persistence;
}