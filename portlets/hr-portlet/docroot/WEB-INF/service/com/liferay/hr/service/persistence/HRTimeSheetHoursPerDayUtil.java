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

import com.liferay.hr.model.HRTimeSheetHoursPerDay;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the h r time sheet hours per day service. This utility wraps {@link HRTimeSheetHoursPerDayPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HRTimeSheetHoursPerDayPersistence
 * @see HRTimeSheetHoursPerDayPersistenceImpl
 * @generated
 */
public class HRTimeSheetHoursPerDayUtil {
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
	public static void clearCache(HRTimeSheetHoursPerDay hrTimeSheetHoursPerDay) {
		getPersistence().clearCache(hrTimeSheetHoursPerDay);
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
	public static List<HRTimeSheetHoursPerDay> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HRTimeSheetHoursPerDay> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HRTimeSheetHoursPerDay> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static HRTimeSheetHoursPerDay remove(
		HRTimeSheetHoursPerDay hrTimeSheetHoursPerDay)
		throws SystemException {
		return getPersistence().remove(hrTimeSheetHoursPerDay);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static HRTimeSheetHoursPerDay update(
		HRTimeSheetHoursPerDay hrTimeSheetHoursPerDay, boolean merge)
		throws SystemException {
		return getPersistence().update(hrTimeSheetHoursPerDay, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static HRTimeSheetHoursPerDay update(
		HRTimeSheetHoursPerDay hrTimeSheetHoursPerDay, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence()
				   .update(hrTimeSheetHoursPerDay, merge, serviceContext);
	}

	/**
	* Caches the h r time sheet hours per day in the entity cache if it is enabled.
	*
	* @param hrTimeSheetHoursPerDay the h r time sheet hours per day to cache
	*/
	public static void cacheResult(
		com.liferay.hr.model.HRTimeSheetHoursPerDay hrTimeSheetHoursPerDay) {
		getPersistence().cacheResult(hrTimeSheetHoursPerDay);
	}

	/**
	* Caches the h r time sheet hours per daies in the entity cache if it is enabled.
	*
	* @param hrTimeSheetHoursPerDaies the h r time sheet hours per daies to cache
	*/
	public static void cacheResult(
		java.util.List<com.liferay.hr.model.HRTimeSheetHoursPerDay> hrTimeSheetHoursPerDaies) {
		getPersistence().cacheResult(hrTimeSheetHoursPerDaies);
	}

	/**
	* Creates a new h r time sheet hours per day with the primary key. Does not add the h r time sheet hours per day to the database.
	*
	* @param hrTimeSheetHoursPerDay the primary key for the new h r time sheet hours per day
	* @return the new h r time sheet hours per day
	*/
	public static com.liferay.hr.model.HRTimeSheetHoursPerDay create(
		long hrTimeSheetHoursPerDay) {
		return getPersistence().create(hrTimeSheetHoursPerDay);
	}

	/**
	* Removes the h r time sheet hours per day with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrTimeSheetHoursPerDay the primary key of the h r time sheet hours per day to remove
	* @return the h r time sheet hours per day that was removed
	* @throws com.liferay.hr.NoSuchTimeSheetHoursPerDayException if a h r time sheet hours per day with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRTimeSheetHoursPerDay remove(
		long hrTimeSheetHoursPerDay)
		throws com.liferay.hr.NoSuchTimeSheetHoursPerDayException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(hrTimeSheetHoursPerDay);
	}

	public static com.liferay.hr.model.HRTimeSheetHoursPerDay updateImpl(
		com.liferay.hr.model.HRTimeSheetHoursPerDay hrTimeSheetHoursPerDay,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(hrTimeSheetHoursPerDay, merge);
	}

	/**
	* Finds the h r time sheet hours per day with the primary key or throws a {@link com.liferay.hr.NoSuchTimeSheetHoursPerDayException} if it could not be found.
	*
	* @param hrTimeSheetHoursPerDay the primary key of the h r time sheet hours per day to find
	* @return the h r time sheet hours per day
	* @throws com.liferay.hr.NoSuchTimeSheetHoursPerDayException if a h r time sheet hours per day with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRTimeSheetHoursPerDay findByPrimaryKey(
		long hrTimeSheetHoursPerDay)
		throws com.liferay.hr.NoSuchTimeSheetHoursPerDayException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(hrTimeSheetHoursPerDay);
	}

	/**
	* Finds the h r time sheet hours per day with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrTimeSheetHoursPerDay the primary key of the h r time sheet hours per day to find
	* @return the h r time sheet hours per day, or <code>null</code> if a h r time sheet hours per day with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRTimeSheetHoursPerDay fetchByPrimaryKey(
		long hrTimeSheetHoursPerDay)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(hrTimeSheetHoursPerDay);
	}

	/**
	* Finds all the h r time sheet hours per daies.
	*
	* @return the h r time sheet hours per daies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRTimeSheetHoursPerDay> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Finds a range of all the h r time sheet hours per daies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r time sheet hours per daies to return
	* @param end the upper bound of the range of h r time sheet hours per daies to return (not inclusive)
	* @return the range of h r time sheet hours per daies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRTimeSheetHoursPerDay> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Finds an ordered range of all the h r time sheet hours per daies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r time sheet hours per daies to return
	* @param end the upper bound of the range of h r time sheet hours per daies to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r time sheet hours per daies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRTimeSheetHoursPerDay> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the h r time sheet hours per daies from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Counts all the h r time sheet hours per daies.
	*
	* @return the number of h r time sheet hours per daies
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static HRTimeSheetHoursPerDayPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (HRTimeSheetHoursPerDayPersistence)PortletBeanLocatorUtil.locate(com.liferay.hr.service.ClpSerializer.getServletContextName(),
					HRTimeSheetHoursPerDayPersistence.class.getName());

			ReferenceRegistry.registerReference(HRTimeSheetHoursPerDayUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(HRTimeSheetHoursPerDayPersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(HRTimeSheetHoursPerDayUtil.class,
			"_persistence");
	}

	private static HRTimeSheetHoursPerDayPersistence _persistence;
}