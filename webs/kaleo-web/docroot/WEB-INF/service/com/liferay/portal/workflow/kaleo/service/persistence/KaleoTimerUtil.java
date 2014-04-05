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
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
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
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static KaleoTimer update(KaleoTimer kaleoTimer)
		throws SystemException {
		return getPersistence().update(kaleoTimer);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static KaleoTimer update(KaleoTimer kaleoTimer,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(kaleoTimer, serviceContext);
	}

	/**
	* Returns all the kaleo timers where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @return the matching kaleo timers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTimer> findByKCN_KCPK(
		java.lang.String kaleoClassName, long kaleoClassPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByKCN_KCPK(kaleoClassName, kaleoClassPK);
	}

	/**
	* Returns a range of all the kaleo timers where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTimerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param start the lower bound of the range of kaleo timers
	* @param end the upper bound of the range of kaleo timers (not inclusive)
	* @return the range of matching kaleo timers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTimer> findByKCN_KCPK(
		java.lang.String kaleoClassName, long kaleoClassPK, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKCN_KCPK(kaleoClassName, kaleoClassPK, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo timers where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTimerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param start the lower bound of the range of kaleo timers
	* @param end the upper bound of the range of kaleo timers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo timers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTimer> findByKCN_KCPK(
		java.lang.String kaleoClassName, long kaleoClassPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
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
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTimerException if a matching kaleo timer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTimer findByKCN_KCPK_First(
		java.lang.String kaleoClassName, long kaleoClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTimerException {
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
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTimer fetchByKCN_KCPK_First(
		java.lang.String kaleoClassName, long kaleoClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
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
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTimerException if a matching kaleo timer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTimer findByKCN_KCPK_Last(
		java.lang.String kaleoClassName, long kaleoClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTimerException {
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
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTimer fetchByKCN_KCPK_Last(
		java.lang.String kaleoClassName, long kaleoClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
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
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTimerException if a kaleo timer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTimer[] findByKCN_KCPK_PrevAndNext(
		long kaleoTimerId, java.lang.String kaleoClassName, long kaleoClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTimerException {
		return getPersistence()
				   .findByKCN_KCPK_PrevAndNext(kaleoTimerId, kaleoClassName,
			kaleoClassPK, orderByComparator);
	}

	/**
	* Removes all the kaleo timers where kaleoClassName = &#63; and kaleoClassPK = &#63; from the database.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByKCN_KCPK(java.lang.String kaleoClassName,
		long kaleoClassPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByKCN_KCPK(kaleoClassName, kaleoClassPK);
	}

	/**
	* Returns the number of kaleo timers where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @return the number of matching kaleo timers
	* @throws SystemException if a system exception occurred
	*/
	public static int countByKCN_KCPK(java.lang.String kaleoClassName,
		long kaleoClassPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByKCN_KCPK(kaleoClassName, kaleoClassPK);
	}

	/**
	* Returns all the kaleo timers where kaleoClassName = &#63; and kaleoClassPK = &#63; and blocking = &#63;.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param blocking the blocking
	* @return the matching kaleo timers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTimer> findByKCN_KCPK_Blocking(
		java.lang.String kaleoClassName, long kaleoClassPK, boolean blocking)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKCN_KCPK_Blocking(kaleoClassName, kaleoClassPK,
			blocking);
	}

	/**
	* Returns a range of all the kaleo timers where kaleoClassName = &#63; and kaleoClassPK = &#63; and blocking = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTimerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param blocking the blocking
	* @param start the lower bound of the range of kaleo timers
	* @param end the upper bound of the range of kaleo timers (not inclusive)
	* @return the range of matching kaleo timers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTimer> findByKCN_KCPK_Blocking(
		java.lang.String kaleoClassName, long kaleoClassPK, boolean blocking,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKCN_KCPK_Blocking(kaleoClassName, kaleoClassPK,
			blocking, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo timers where kaleoClassName = &#63; and kaleoClassPK = &#63; and blocking = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTimerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param blocking the blocking
	* @param start the lower bound of the range of kaleo timers
	* @param end the upper bound of the range of kaleo timers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo timers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTimer> findByKCN_KCPK_Blocking(
		java.lang.String kaleoClassName, long kaleoClassPK, boolean blocking,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
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
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTimerException if a matching kaleo timer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTimer findByKCN_KCPK_Blocking_First(
		java.lang.String kaleoClassName, long kaleoClassPK, boolean blocking,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTimerException {
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
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTimer fetchByKCN_KCPK_Blocking_First(
		java.lang.String kaleoClassName, long kaleoClassPK, boolean blocking,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
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
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTimerException if a matching kaleo timer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTimer findByKCN_KCPK_Blocking_Last(
		java.lang.String kaleoClassName, long kaleoClassPK, boolean blocking,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTimerException {
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
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTimer fetchByKCN_KCPK_Blocking_Last(
		java.lang.String kaleoClassName, long kaleoClassPK, boolean blocking,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
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
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTimerException if a kaleo timer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTimer[] findByKCN_KCPK_Blocking_PrevAndNext(
		long kaleoTimerId, java.lang.String kaleoClassName, long kaleoClassPK,
		boolean blocking,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTimerException {
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
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByKCN_KCPK_Blocking(
		java.lang.String kaleoClassName, long kaleoClassPK, boolean blocking)
		throws com.liferay.portal.kernel.exception.SystemException {
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
	* @throws SystemException if a system exception occurred
	*/
	public static int countByKCN_KCPK_Blocking(
		java.lang.String kaleoClassName, long kaleoClassPK, boolean blocking)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByKCN_KCPK_Blocking(kaleoClassName, kaleoClassPK,
			blocking);
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
		com.liferay.portal.workflow.kaleo.model.KaleoTimer kaleoTimer)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(kaleoTimer);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTimerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTimerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Removes all the kaleo timers from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
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

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(KaleoTimerPersistence persistence) {
	}

	private static KaleoTimerPersistence _persistence;
}