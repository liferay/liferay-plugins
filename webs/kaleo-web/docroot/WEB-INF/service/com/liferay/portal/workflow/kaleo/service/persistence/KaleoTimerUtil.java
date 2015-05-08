/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.model.KaleoTimer;

import java.util.List;

/**
 * The persistence utility for the kaleo timer service. This utility wraps {@link com.liferay.portal.workflow.kaleo.service.persistence.impl.KaleoTimerPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTimerPersistence
 * @see com.liferay.portal.workflow.kaleo.service.persistence.impl.KaleoTimerPersistenceImpl
 * @generated
 */
@ProviderType
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
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<KaleoTimer> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<KaleoTimer> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<KaleoTimer> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<KaleoTimer> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static KaleoTimer update(KaleoTimer kaleoTimer) {
		return getPersistence().update(kaleoTimer);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static KaleoTimer update(KaleoTimer kaleoTimer,
		ServiceContext serviceContext) {
		return getPersistence().update(kaleoTimer, serviceContext);
	}

	/**
	* Returns all the kaleo timers where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @return the matching kaleo timers
	*/
	public static List<KaleoTimer> findByKCN_KCPK(
		java.lang.String kaleoClassName, long kaleoClassPK) {
		return getPersistence().findByKCN_KCPK(kaleoClassName, kaleoClassPK);
	}

	/**
	* Returns a range of all the kaleo timers where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTimerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param start the lower bound of the range of kaleo timers
	* @param end the upper bound of the range of kaleo timers (not inclusive)
	* @return the range of matching kaleo timers
	*/
	public static List<KaleoTimer> findByKCN_KCPK(
		java.lang.String kaleoClassName, long kaleoClassPK, int start, int end) {
		return getPersistence()
				   .findByKCN_KCPK(kaleoClassName, kaleoClassPK, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo timers where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTimerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param start the lower bound of the range of kaleo timers
	* @param end the upper bound of the range of kaleo timers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo timers
	*/
	public static List<KaleoTimer> findByKCN_KCPK(
		java.lang.String kaleoClassName, long kaleoClassPK, int start, int end,
		OrderByComparator<KaleoTimer> orderByComparator) {
		return getPersistence()
				   .findByKCN_KCPK(kaleoClassName, kaleoClassPK, start, end,
			orderByComparator);
	}

	/**
	* Returns the first kaleo timer in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo timer
	* @throws NoSuchTimerException if a matching kaleo timer could not be found
	*/
	public static KaleoTimer findByKCN_KCPK_First(
		java.lang.String kaleoClassName, long kaleoClassPK,
		OrderByComparator<KaleoTimer> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchTimerException {
		return getPersistence()
				   .findByKCN_KCPK_First(kaleoClassName, kaleoClassPK,
			orderByComparator);
	}

	/**
	* Returns the first kaleo timer in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo timer, or <code>null</code> if a matching kaleo timer could not be found
	*/
	public static KaleoTimer fetchByKCN_KCPK_First(
		java.lang.String kaleoClassName, long kaleoClassPK,
		OrderByComparator<KaleoTimer> orderByComparator) {
		return getPersistence()
				   .fetchByKCN_KCPK_First(kaleoClassName, kaleoClassPK,
			orderByComparator);
	}

	/**
	* Returns the last kaleo timer in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo timer
	* @throws NoSuchTimerException if a matching kaleo timer could not be found
	*/
	public static KaleoTimer findByKCN_KCPK_Last(
		java.lang.String kaleoClassName, long kaleoClassPK,
		OrderByComparator<KaleoTimer> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchTimerException {
		return getPersistence()
				   .findByKCN_KCPK_Last(kaleoClassName, kaleoClassPK,
			orderByComparator);
	}

	/**
	* Returns the last kaleo timer in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo timer, or <code>null</code> if a matching kaleo timer could not be found
	*/
	public static KaleoTimer fetchByKCN_KCPK_Last(
		java.lang.String kaleoClassName, long kaleoClassPK,
		OrderByComparator<KaleoTimer> orderByComparator) {
		return getPersistence()
				   .fetchByKCN_KCPK_Last(kaleoClassName, kaleoClassPK,
			orderByComparator);
	}

	/**
	* Returns the kaleo timers before and after the current kaleo timer in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	*
	* @param kaleoTimerId the primary key of the current kaleo timer
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo timer
	* @throws NoSuchTimerException if a kaleo timer with the primary key could not be found
	*/
	public static KaleoTimer[] findByKCN_KCPK_PrevAndNext(long kaleoTimerId,
		java.lang.String kaleoClassName, long kaleoClassPK,
		OrderByComparator<KaleoTimer> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchTimerException {
		return getPersistence()
				   .findByKCN_KCPK_PrevAndNext(kaleoTimerId, kaleoClassName,
			kaleoClassPK, orderByComparator);
	}

	/**
	* Removes all the kaleo timers where kaleoClassName = &#63; and kaleoClassPK = &#63; from the database.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	*/
	public static void removeByKCN_KCPK(java.lang.String kaleoClassName,
		long kaleoClassPK) {
		getPersistence().removeByKCN_KCPK(kaleoClassName, kaleoClassPK);
	}

	/**
	* Returns the number of kaleo timers where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @return the number of matching kaleo timers
	*/
	public static int countByKCN_KCPK(java.lang.String kaleoClassName,
		long kaleoClassPK) {
		return getPersistence().countByKCN_KCPK(kaleoClassName, kaleoClassPK);
	}

	/**
	* Returns all the kaleo timers where kaleoClassName = &#63; and kaleoClassPK = &#63; and blocking = &#63;.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param blocking the blocking
	* @return the matching kaleo timers
	*/
	public static List<KaleoTimer> findByKCN_KCPK_Blocking(
		java.lang.String kaleoClassName, long kaleoClassPK, boolean blocking) {
		return getPersistence()
				   .findByKCN_KCPK_Blocking(kaleoClassName, kaleoClassPK,
			blocking);
	}

	/**
	* Returns a range of all the kaleo timers where kaleoClassName = &#63; and kaleoClassPK = &#63; and blocking = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTimerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param blocking the blocking
	* @param start the lower bound of the range of kaleo timers
	* @param end the upper bound of the range of kaleo timers (not inclusive)
	* @return the range of matching kaleo timers
	*/
	public static List<KaleoTimer> findByKCN_KCPK_Blocking(
		java.lang.String kaleoClassName, long kaleoClassPK, boolean blocking,
		int start, int end) {
		return getPersistence()
				   .findByKCN_KCPK_Blocking(kaleoClassName, kaleoClassPK,
			blocking, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo timers where kaleoClassName = &#63; and kaleoClassPK = &#63; and blocking = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTimerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param blocking the blocking
	* @param start the lower bound of the range of kaleo timers
	* @param end the upper bound of the range of kaleo timers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo timers
	*/
	public static List<KaleoTimer> findByKCN_KCPK_Blocking(
		java.lang.String kaleoClassName, long kaleoClassPK, boolean blocking,
		int start, int end, OrderByComparator<KaleoTimer> orderByComparator) {
		return getPersistence()
				   .findByKCN_KCPK_Blocking(kaleoClassName, kaleoClassPK,
			blocking, start, end, orderByComparator);
	}

	/**
	* Returns the first kaleo timer in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and blocking = &#63;.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param blocking the blocking
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo timer
	* @throws NoSuchTimerException if a matching kaleo timer could not be found
	*/
	public static KaleoTimer findByKCN_KCPK_Blocking_First(
		java.lang.String kaleoClassName, long kaleoClassPK, boolean blocking,
		OrderByComparator<KaleoTimer> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchTimerException {
		return getPersistence()
				   .findByKCN_KCPK_Blocking_First(kaleoClassName, kaleoClassPK,
			blocking, orderByComparator);
	}

	/**
	* Returns the first kaleo timer in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and blocking = &#63;.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param blocking the blocking
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo timer, or <code>null</code> if a matching kaleo timer could not be found
	*/
	public static KaleoTimer fetchByKCN_KCPK_Blocking_First(
		java.lang.String kaleoClassName, long kaleoClassPK, boolean blocking,
		OrderByComparator<KaleoTimer> orderByComparator) {
		return getPersistence()
				   .fetchByKCN_KCPK_Blocking_First(kaleoClassName,
			kaleoClassPK, blocking, orderByComparator);
	}

	/**
	* Returns the last kaleo timer in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and blocking = &#63;.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param blocking the blocking
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo timer
	* @throws NoSuchTimerException if a matching kaleo timer could not be found
	*/
	public static KaleoTimer findByKCN_KCPK_Blocking_Last(
		java.lang.String kaleoClassName, long kaleoClassPK, boolean blocking,
		OrderByComparator<KaleoTimer> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchTimerException {
		return getPersistence()
				   .findByKCN_KCPK_Blocking_Last(kaleoClassName, kaleoClassPK,
			blocking, orderByComparator);
	}

	/**
	* Returns the last kaleo timer in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and blocking = &#63;.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param blocking the blocking
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo timer, or <code>null</code> if a matching kaleo timer could not be found
	*/
	public static KaleoTimer fetchByKCN_KCPK_Blocking_Last(
		java.lang.String kaleoClassName, long kaleoClassPK, boolean blocking,
		OrderByComparator<KaleoTimer> orderByComparator) {
		return getPersistence()
				   .fetchByKCN_KCPK_Blocking_Last(kaleoClassName, kaleoClassPK,
			blocking, orderByComparator);
	}

	/**
	* Returns the kaleo timers before and after the current kaleo timer in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and blocking = &#63;.
	*
	* @param kaleoTimerId the primary key of the current kaleo timer
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param blocking the blocking
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo timer
	* @throws NoSuchTimerException if a kaleo timer with the primary key could not be found
	*/
	public static KaleoTimer[] findByKCN_KCPK_Blocking_PrevAndNext(
		long kaleoTimerId, java.lang.String kaleoClassName, long kaleoClassPK,
		boolean blocking, OrderByComparator<KaleoTimer> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchTimerException {
		return getPersistence()
				   .findByKCN_KCPK_Blocking_PrevAndNext(kaleoTimerId,
			kaleoClassName, kaleoClassPK, blocking, orderByComparator);
	}

	/**
	* Removes all the kaleo timers where kaleoClassName = &#63; and kaleoClassPK = &#63; and blocking = &#63; from the database.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param blocking the blocking
	*/
	public static void removeByKCN_KCPK_Blocking(
		java.lang.String kaleoClassName, long kaleoClassPK, boolean blocking) {
		getPersistence()
			.removeByKCN_KCPK_Blocking(kaleoClassName, kaleoClassPK, blocking);
	}

	/**
	* Returns the number of kaleo timers where kaleoClassName = &#63; and kaleoClassPK = &#63; and blocking = &#63;.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param blocking the blocking
	* @return the number of matching kaleo timers
	*/
	public static int countByKCN_KCPK_Blocking(
		java.lang.String kaleoClassName, long kaleoClassPK, boolean blocking) {
		return getPersistence()
				   .countByKCN_KCPK_Blocking(kaleoClassName, kaleoClassPK,
			blocking);
	}

	/**
	* Caches the kaleo timer in the entity cache if it is enabled.
	*
	* @param kaleoTimer the kaleo timer
	*/
	public static void cacheResult(KaleoTimer kaleoTimer) {
		getPersistence().cacheResult(kaleoTimer);
	}

	/**
	* Caches the kaleo timers in the entity cache if it is enabled.
	*
	* @param kaleoTimers the kaleo timers
	*/
	public static void cacheResult(List<KaleoTimer> kaleoTimers) {
		getPersistence().cacheResult(kaleoTimers);
	}

	/**
	* Creates a new kaleo timer with the primary key. Does not add the kaleo timer to the database.
	*
	* @param kaleoTimerId the primary key for the new kaleo timer
	* @return the new kaleo timer
	*/
	public static KaleoTimer create(long kaleoTimerId) {
		return getPersistence().create(kaleoTimerId);
	}

	/**
	* Removes the kaleo timer with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTimerId the primary key of the kaleo timer
	* @return the kaleo timer that was removed
	* @throws NoSuchTimerException if a kaleo timer with the primary key could not be found
	*/
	public static KaleoTimer remove(long kaleoTimerId)
		throws com.liferay.portal.workflow.kaleo.NoSuchTimerException {
		return getPersistence().remove(kaleoTimerId);
	}

	public static KaleoTimer updateImpl(KaleoTimer kaleoTimer) {
		return getPersistence().updateImpl(kaleoTimer);
	}

	/**
	* Returns the kaleo timer with the primary key or throws a {@link NoSuchTimerException} if it could not be found.
	*
	* @param kaleoTimerId the primary key of the kaleo timer
	* @return the kaleo timer
	* @throws NoSuchTimerException if a kaleo timer with the primary key could not be found
	*/
	public static KaleoTimer findByPrimaryKey(long kaleoTimerId)
		throws com.liferay.portal.workflow.kaleo.NoSuchTimerException {
		return getPersistence().findByPrimaryKey(kaleoTimerId);
	}

	/**
	* Returns the kaleo timer with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param kaleoTimerId the primary key of the kaleo timer
	* @return the kaleo timer, or <code>null</code> if a kaleo timer with the primary key could not be found
	*/
	public static KaleoTimer fetchByPrimaryKey(long kaleoTimerId) {
		return getPersistence().fetchByPrimaryKey(kaleoTimerId);
	}

	public static java.util.Map<java.io.Serializable, KaleoTimer> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the kaleo timers.
	*
	* @return the kaleo timers
	*/
	public static List<KaleoTimer> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the kaleo timers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTimerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo timers
	* @param end the upper bound of the range of kaleo timers (not inclusive)
	* @return the range of kaleo timers
	*/
	public static List<KaleoTimer> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the kaleo timers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTimerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo timers
	* @param end the upper bound of the range of kaleo timers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of kaleo timers
	*/
	public static List<KaleoTimer> findAll(int start, int end,
		OrderByComparator<KaleoTimer> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the kaleo timers from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of kaleo timers.
	*
	* @return the number of kaleo timers
	*/
	public static int countAll() {
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

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setPersistence(KaleoTimerPersistence persistence) {
	}

	private static KaleoTimerPersistence _persistence;
}