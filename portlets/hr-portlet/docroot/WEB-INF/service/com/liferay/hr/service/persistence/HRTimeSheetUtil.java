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

import com.liferay.hr.model.HRTimeSheet;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the h r time sheet service. This utility wraps {@link HRTimeSheetPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRTimeSheetPersistence
 * @see HRTimeSheetPersistenceImpl
 * @generated
 */
public class HRTimeSheetUtil {
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
	public static void clearCache(HRTimeSheet hrTimeSheet) {
		getPersistence().clearCache(hrTimeSheet);
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
	public static List<HRTimeSheet> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HRTimeSheet> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HRTimeSheet> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static HRTimeSheet remove(HRTimeSheet hrTimeSheet)
		throws SystemException {
		return getPersistence().remove(hrTimeSheet);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static HRTimeSheet update(HRTimeSheet hrTimeSheet, boolean merge)
		throws SystemException {
		return getPersistence().update(hrTimeSheet, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static HRTimeSheet update(HRTimeSheet hrTimeSheet, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(hrTimeSheet, merge, serviceContext);
	}

	/**
	* Caches the h r time sheet in the entity cache if it is enabled.
	*
	* @param hrTimeSheet the h r time sheet to cache
	*/
	public static void cacheResult(com.liferay.hr.model.HRTimeSheet hrTimeSheet) {
		getPersistence().cacheResult(hrTimeSheet);
	}

	/**
	* Caches the h r time sheets in the entity cache if it is enabled.
	*
	* @param hrTimeSheets the h r time sheets to cache
	*/
	public static void cacheResult(
		java.util.List<com.liferay.hr.model.HRTimeSheet> hrTimeSheets) {
		getPersistence().cacheResult(hrTimeSheets);
	}

	/**
	* Creates a new h r time sheet with the primary key. Does not add the h r time sheet to the database.
	*
	* @param hrTimeSheetId the primary key for the new h r time sheet
	* @return the new h r time sheet
	*/
	public static com.liferay.hr.model.HRTimeSheet create(long hrTimeSheetId) {
		return getPersistence().create(hrTimeSheetId);
	}

	/**
	* Removes the h r time sheet with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrTimeSheetId the primary key of the h r time sheet to remove
	* @return the h r time sheet that was removed
	* @throws com.liferay.hr.NoSuchTimeSheetException if a h r time sheet with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRTimeSheet remove(long hrTimeSheetId)
		throws com.liferay.hr.NoSuchTimeSheetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(hrTimeSheetId);
	}

	public static com.liferay.hr.model.HRTimeSheet updateImpl(
		com.liferay.hr.model.HRTimeSheet hrTimeSheet, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(hrTimeSheet, merge);
	}

	/**
	* Finds the h r time sheet with the primary key or throws a {@link com.liferay.hr.NoSuchTimeSheetException} if it could not be found.
	*
	* @param hrTimeSheetId the primary key of the h r time sheet to find
	* @return the h r time sheet
	* @throws com.liferay.hr.NoSuchTimeSheetException if a h r time sheet with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRTimeSheet findByPrimaryKey(
		long hrTimeSheetId)
		throws com.liferay.hr.NoSuchTimeSheetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(hrTimeSheetId);
	}

	/**
	* Finds the h r time sheet with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrTimeSheetId the primary key of the h r time sheet to find
	* @return the h r time sheet, or <code>null</code> if a h r time sheet with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRTimeSheet fetchByPrimaryKey(
		long hrTimeSheetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(hrTimeSheetId);
	}

	/**
	* Finds all the h r time sheets.
	*
	* @return the h r time sheets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRTimeSheet> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Finds a range of all the h r time sheets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r time sheets to return
	* @param end the upper bound of the range of h r time sheets to return (not inclusive)
	* @return the range of h r time sheets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRTimeSheet> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Finds an ordered range of all the h r time sheets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r time sheets to return
	* @param end the upper bound of the range of h r time sheets to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r time sheets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRTimeSheet> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the h r time sheets from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Counts all the h r time sheets.
	*
	* @return the number of h r time sheets
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static HRTimeSheetPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (HRTimeSheetPersistence)PortletBeanLocatorUtil.locate(com.liferay.hr.service.ClpSerializer.getServletContextName(),
					HRTimeSheetPersistence.class.getName());

			ReferenceRegistry.registerReference(HRTimeSheetUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(HRTimeSheetPersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(HRTimeSheetUtil.class,
			"_persistence");
	}

	private static HRTimeSheetPersistence _persistence;
}