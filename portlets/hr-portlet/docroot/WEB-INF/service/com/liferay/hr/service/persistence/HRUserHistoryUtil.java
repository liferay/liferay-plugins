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

import com.liferay.hr.model.HRUserHistory;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the h r user history service. This utility wraps {@link HRUserHistoryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HRUserHistoryPersistence
 * @see HRUserHistoryPersistenceImpl
 * @generated
 */
public class HRUserHistoryUtil {
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
	public static void clearCache(HRUserHistory hrUserHistory) {
		getPersistence().clearCache(hrUserHistory);
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
	public static List<HRUserHistory> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HRUserHistory> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HRUserHistory> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static HRUserHistory remove(HRUserHistory hrUserHistory)
		throws SystemException {
		return getPersistence().remove(hrUserHistory);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static HRUserHistory update(HRUserHistory hrUserHistory,
		boolean merge) throws SystemException {
		return getPersistence().update(hrUserHistory, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static HRUserHistory update(HRUserHistory hrUserHistory,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(hrUserHistory, merge, serviceContext);
	}

	/**
	* Caches the h r user history in the entity cache if it is enabled.
	*
	* @param hrUserHistory the h r user history to cache
	*/
	public static void cacheResult(
		com.liferay.hr.model.HRUserHistory hrUserHistory) {
		getPersistence().cacheResult(hrUserHistory);
	}

	/**
	* Caches the h r user histories in the entity cache if it is enabled.
	*
	* @param hrUserHistories the h r user histories to cache
	*/
	public static void cacheResult(
		java.util.List<com.liferay.hr.model.HRUserHistory> hrUserHistories) {
		getPersistence().cacheResult(hrUserHistories);
	}

	/**
	* Creates a new h r user history with the primary key. Does not add the h r user history to the database.
	*
	* @param hrUserHistoryId the primary key for the new h r user history
	* @return the new h r user history
	*/
	public static com.liferay.hr.model.HRUserHistory create(
		long hrUserHistoryId) {
		return getPersistence().create(hrUserHistoryId);
	}

	/**
	* Removes the h r user history with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrUserHistoryId the primary key of the h r user history to remove
	* @return the h r user history that was removed
	* @throws com.liferay.hr.NoSuchUserHistoryException if a h r user history with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRUserHistory remove(
		long hrUserHistoryId)
		throws com.liferay.hr.NoSuchUserHistoryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(hrUserHistoryId);
	}

	public static com.liferay.hr.model.HRUserHistory updateImpl(
		com.liferay.hr.model.HRUserHistory hrUserHistory, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(hrUserHistory, merge);
	}

	/**
	* Finds the h r user history with the primary key or throws a {@link com.liferay.hr.NoSuchUserHistoryException} if it could not be found.
	*
	* @param hrUserHistoryId the primary key of the h r user history to find
	* @return the h r user history
	* @throws com.liferay.hr.NoSuchUserHistoryException if a h r user history with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRUserHistory findByPrimaryKey(
		long hrUserHistoryId)
		throws com.liferay.hr.NoSuchUserHistoryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(hrUserHistoryId);
	}

	/**
	* Finds the h r user history with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrUserHistoryId the primary key of the h r user history to find
	* @return the h r user history, or <code>null</code> if a h r user history with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRUserHistory fetchByPrimaryKey(
		long hrUserHistoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(hrUserHistoryId);
	}

	/**
	* Finds all the h r user histories.
	*
	* @return the h r user histories
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRUserHistory> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Finds a range of all the h r user histories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r user histories to return
	* @param end the upper bound of the range of h r user histories to return (not inclusive)
	* @return the range of h r user histories
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRUserHistory> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Finds an ordered range of all the h r user histories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r user histories to return
	* @param end the upper bound of the range of h r user histories to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r user histories
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRUserHistory> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the h r user histories from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Counts all the h r user histories.
	*
	* @return the number of h r user histories
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static HRUserHistoryPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (HRUserHistoryPersistence)PortletBeanLocatorUtil.locate(com.liferay.hr.service.ClpSerializer.getServletContextName(),
					HRUserHistoryPersistence.class.getName());

			ReferenceRegistry.registerReference(HRUserHistoryUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(HRUserHistoryPersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(HRUserHistoryUtil.class,
			"_persistence");
	}

	private static HRUserHistoryPersistence _persistence;
}