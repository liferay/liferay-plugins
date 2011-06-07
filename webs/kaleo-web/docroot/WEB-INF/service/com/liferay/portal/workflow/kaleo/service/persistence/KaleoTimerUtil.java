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

package com.liferay.portal.workflow.kaleo.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.model.KaleoTimer;

import java.util.List;

/**
 * The persistence utility for the kaleo timer service. This utility wraps {@link KaleoTimerPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTimerPersistence
 * @see KaleoTimerPersistenceImpl
 * @generated
 */
public class KaleoTimerUtil {
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
	public static void clearCache(KaleoTimer kaleoTimer) {
		getPersistence().clearCache(kaleoTimer);
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
	public static List<KaleoTimer> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<KaleoTimer> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<KaleoTimer> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static KaleoTimer remove(KaleoTimer kaleoTimer)
		throws SystemException {
		return getPersistence().remove(kaleoTimer);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static KaleoTimer update(KaleoTimer kaleoTimer, boolean merge)
		throws SystemException {
		return getPersistence().update(kaleoTimer, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static KaleoTimer update(KaleoTimer kaleoTimer, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(kaleoTimer, merge, serviceContext);
	}

	/**
	* Caches the kaleo timer in the entity cache if it is enabled.
	*
	* @param kaleoTimer the kaleo timer
	*/
	public static void cacheResult(
		com.liferay.portal.workflow.kaleo.model.KaleoTimer kaleoTimer) {
		getPersistence().cacheResult(kaleoTimer);
	}

	/**
	* Caches the kaleo timers in the entity cache if it is enabled.
	*
	* @param kaleoTimers the kaleo timers
	*/
	public static void cacheResult(
		java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTimer> kaleoTimers) {
		getPersistence().cacheResult(kaleoTimers);
	}

	/**
	* Creates a new kaleo timer with the primary key. Does not add the kaleo timer to the database.
	*
	* @param kaleoTimerId the primary key for the new kaleo timer
	* @return the new kaleo timer
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTimer create(
		long kaleoTimerId) {
		return getPersistence().create(kaleoTimerId);
	}

	/**
	* Removes the kaleo timer with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTimerId the primary key of the kaleo timer
	* @return the kaleo timer that was removed
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTimerException if a kaleo timer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTimer remove(
		long kaleoTimerId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTimerException {
		return getPersistence().remove(kaleoTimerId);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTimer updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoTimer kaleoTimer,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(kaleoTimer, merge);
	}

	/**
	* Returns the kaleo timer with the primary key or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchTimerException} if it could not be found.
	*
	* @param kaleoTimerId the primary key of the kaleo timer
	* @return the kaleo timer
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTimerException if a kaleo timer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTimer findByPrimaryKey(
		long kaleoTimerId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTimerException {
		return getPersistence().findByPrimaryKey(kaleoTimerId);
	}

	/**
	* Returns the kaleo timer with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param kaleoTimerId the primary key of the kaleo timer
	* @return the kaleo timer, or <code>null</code> if a kaleo timer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTimer fetchByPrimaryKey(
		long kaleoTimerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(kaleoTimerId);
	}

	/**
	* Returns all the kaleo timers where parentKaleoNodeId = &#63;.
	*
	* @param parentKaleoNodeId the parent kaleo node ID
	* @return the matching kaleo timers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTimer> findByparentKaleoNodeId(
		long parentKaleoNodeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByparentKaleoNodeId(parentKaleoNodeId);
	}

	/**
	* Returns a range of all the kaleo timers where parentKaleoNodeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentKaleoNodeId the parent kaleo node ID
	* @param start the lower bound of the range of kaleo timers
	* @param end the upper bound of the range of kaleo timers (not inclusive)
	* @return the range of matching kaleo timers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTimer> findByparentKaleoNodeId(
		long parentKaleoNodeId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByparentKaleoNodeId(parentKaleoNodeId, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo timers where parentKaleoNodeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentKaleoNodeId the parent kaleo node ID
	* @param start the lower bound of the range of kaleo timers
	* @param end the upper bound of the range of kaleo timers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo timers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTimer> findByparentKaleoNodeId(
		long parentKaleoNodeId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByparentKaleoNodeId(parentKaleoNodeId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first kaleo timer in the ordered set where parentKaleoNodeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentKaleoNodeId the parent kaleo node ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo timer
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTimerException if a matching kaleo timer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTimer findByparentKaleoNodeId_First(
		long parentKaleoNodeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTimerException {
		return getPersistence()
				   .findByparentKaleoNodeId_First(parentKaleoNodeId,
			orderByComparator);
	}

	/**
	* Returns the last kaleo timer in the ordered set where parentKaleoNodeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentKaleoNodeId the parent kaleo node ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo timer
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTimerException if a matching kaleo timer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTimer findByparentKaleoNodeId_Last(
		long parentKaleoNodeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTimerException {
		return getPersistence()
				   .findByparentKaleoNodeId_Last(parentKaleoNodeId,
			orderByComparator);
	}

	/**
	* Returns the kaleo timers before and after the current kaleo timer in the ordered set where parentKaleoNodeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoTimerId the primary key of the current kaleo timer
	* @param parentKaleoNodeId the parent kaleo node ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo timer
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTimerException if a kaleo timer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTimer[] findByparentKaleoNodeId_PrevAndNext(
		long kaleoTimerId, long parentKaleoNodeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTimerException {
		return getPersistence()
				   .findByparentKaleoNodeId_PrevAndNext(kaleoTimerId,
			parentKaleoNodeId, orderByComparator);
	}

	/**
	* Returns all the kaleo timers where parentKaleoNodeId = &#63; and defaultTimer = &#63;.
	*
	* @param parentKaleoNodeId the parent kaleo node ID
	* @param defaultTimer the default timer
	* @return the matching kaleo timers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTimer> findByPKNI_DT(
		long parentKaleoNodeId, boolean defaultTimer)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPKNI_DT(parentKaleoNodeId, defaultTimer);
	}

	/**
	* Returns a range of all the kaleo timers where parentKaleoNodeId = &#63; and defaultTimer = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentKaleoNodeId the parent kaleo node ID
	* @param defaultTimer the default timer
	* @param start the lower bound of the range of kaleo timers
	* @param end the upper bound of the range of kaleo timers (not inclusive)
	* @return the range of matching kaleo timers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTimer> findByPKNI_DT(
		long parentKaleoNodeId, boolean defaultTimer, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPKNI_DT(parentKaleoNodeId, defaultTimer, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo timers where parentKaleoNodeId = &#63; and defaultTimer = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentKaleoNodeId the parent kaleo node ID
	* @param defaultTimer the default timer
	* @param start the lower bound of the range of kaleo timers
	* @param end the upper bound of the range of kaleo timers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo timers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTimer> findByPKNI_DT(
		long parentKaleoNodeId, boolean defaultTimer, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPKNI_DT(parentKaleoNodeId, defaultTimer, start, end,
			orderByComparator);
	}

	/**
	* Returns the first kaleo timer in the ordered set where parentKaleoNodeId = &#63; and defaultTimer = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentKaleoNodeId the parent kaleo node ID
	* @param defaultTimer the default timer
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo timer
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTimerException if a matching kaleo timer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTimer findByPKNI_DT_First(
		long parentKaleoNodeId, boolean defaultTimer,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTimerException {
		return getPersistence()
				   .findByPKNI_DT_First(parentKaleoNodeId, defaultTimer,
			orderByComparator);
	}

	/**
	* Returns the last kaleo timer in the ordered set where parentKaleoNodeId = &#63; and defaultTimer = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentKaleoNodeId the parent kaleo node ID
	* @param defaultTimer the default timer
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo timer
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTimerException if a matching kaleo timer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTimer findByPKNI_DT_Last(
		long parentKaleoNodeId, boolean defaultTimer,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTimerException {
		return getPersistence()
				   .findByPKNI_DT_Last(parentKaleoNodeId, defaultTimer,
			orderByComparator);
	}

	/**
	* Returns the kaleo timers before and after the current kaleo timer in the ordered set where parentKaleoNodeId = &#63; and defaultTimer = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoTimerId the primary key of the current kaleo timer
	* @param parentKaleoNodeId the parent kaleo node ID
	* @param defaultTimer the default timer
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo timer
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTimerException if a kaleo timer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTimer[] findByPKNI_DT_PrevAndNext(
		long kaleoTimerId, long parentKaleoNodeId, boolean defaultTimer,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTimerException {
		return getPersistence()
				   .findByPKNI_DT_PrevAndNext(kaleoTimerId, parentKaleoNodeId,
			defaultTimer, orderByComparator);
	}

	/**
	* Returns all the kaleo timers.
	*
	* @return the kaleo timers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTimer> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the kaleo timers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of kaleo timers
	* @param end the upper bound of the range of kaleo timers (not inclusive)
	* @return the range of kaleo timers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTimer> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the kaleo timers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of kaleo timers
	* @param end the upper bound of the range of kaleo timers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of kaleo timers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTimer> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the kaleo timers where parentKaleoNodeId = &#63; from the database.
	*
	* @param parentKaleoNodeId the parent kaleo node ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByparentKaleoNodeId(long parentKaleoNodeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByparentKaleoNodeId(parentKaleoNodeId);
	}

	/**
	* Removes all the kaleo timers where parentKaleoNodeId = &#63; and defaultTimer = &#63; from the database.
	*
	* @param parentKaleoNodeId the parent kaleo node ID
	* @param defaultTimer the default timer
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByPKNI_DT(long parentKaleoNodeId,
		boolean defaultTimer)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByPKNI_DT(parentKaleoNodeId, defaultTimer);
	}

	/**
	* Removes all the kaleo timers from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of kaleo timers where parentKaleoNodeId = &#63;.
	*
	* @param parentKaleoNodeId the parent kaleo node ID
	* @return the number of matching kaleo timers
	* @throws SystemException if a system exception occurred
	*/
	public static int countByparentKaleoNodeId(long parentKaleoNodeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByparentKaleoNodeId(parentKaleoNodeId);
	}

	/**
	* Returns the number of kaleo timers where parentKaleoNodeId = &#63; and defaultTimer = &#63;.
	*
	* @param parentKaleoNodeId the parent kaleo node ID
	* @param defaultTimer the default timer
	* @return the number of matching kaleo timers
	* @throws SystemException if a system exception occurred
	*/
	public static int countByPKNI_DT(long parentKaleoNodeId,
		boolean defaultTimer)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByPKNI_DT(parentKaleoNodeId, defaultTimer);
	}

	/**
	* Returns the number of kaleo timers.
	*
	* @return the number of kaleo timers
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static KaleoTimerPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (KaleoTimerPersistence)PortletBeanLocatorUtil.locate(com.liferay.portal.workflow.kaleo.service.ClpSerializer.getServletContextName(),
					KaleoTimerPersistence.class.getName());

			ReferenceRegistry.registerReference(KaleoTimerUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(KaleoTimerPersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(KaleoTimerUtil.class, "_persistence");
	}

	private static KaleoTimerPersistence _persistence;
}