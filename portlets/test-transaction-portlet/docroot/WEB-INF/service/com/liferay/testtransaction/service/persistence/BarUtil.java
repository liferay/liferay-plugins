/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.testtransaction.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.liferay.testtransaction.model.Bar;

import java.util.List;

/**
 * The persistence utility for the bar service. This utility wraps {@link BarPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BarPersistence
 * @see BarPersistenceImpl
 * @generated
 */
public class BarUtil {
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
	public static void clearCache(Bar bar) {
		getPersistence().clearCache(bar);
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
	public static List<Bar> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Bar> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Bar> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static Bar update(Bar bar, boolean merge) throws SystemException {
		return getPersistence().update(bar, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static Bar update(Bar bar, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(bar, merge, serviceContext);
	}

	/**
	* Caches the bar in the entity cache if it is enabled.
	*
	* @param bar the bar
	*/
	public static void cacheResult(com.liferay.testtransaction.model.Bar bar) {
		getPersistence().cacheResult(bar);
	}

	/**
	* Caches the bars in the entity cache if it is enabled.
	*
	* @param bars the bars
	*/
	public static void cacheResult(
		java.util.List<com.liferay.testtransaction.model.Bar> bars) {
		getPersistence().cacheResult(bars);
	}

	/**
	* Creates a new bar with the primary key. Does not add the bar to the database.
	*
	* @param barId the primary key for the new bar
	* @return the new bar
	*/
	public static com.liferay.testtransaction.model.Bar create(long barId) {
		return getPersistence().create(barId);
	}

	/**
	* Removes the bar with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param barId the primary key of the bar
	* @return the bar that was removed
	* @throws com.liferay.testtransaction.NoSuchBarException if a bar with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.testtransaction.model.Bar remove(long barId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.testtransaction.NoSuchBarException {
		return getPersistence().remove(barId);
	}

	public static com.liferay.testtransaction.model.Bar updateImpl(
		com.liferay.testtransaction.model.Bar bar, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(bar, merge);
	}

	/**
	* Returns the bar with the primary key or throws a {@link com.liferay.testtransaction.NoSuchBarException} if it could not be found.
	*
	* @param barId the primary key of the bar
	* @return the bar
	* @throws com.liferay.testtransaction.NoSuchBarException if a bar with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.testtransaction.model.Bar findByPrimaryKey(
		long barId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.testtransaction.NoSuchBarException {
		return getPersistence().findByPrimaryKey(barId);
	}

	/**
	* Returns the bar with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param barId the primary key of the bar
	* @return the bar, or <code>null</code> if a bar with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.testtransaction.model.Bar fetchByPrimaryKey(
		long barId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(barId);
	}

	/**
	* Returns all the bars where text = &#63;.
	*
	* @param text the text
	* @return the matching bars
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.testtransaction.model.Bar> findByText(
		java.lang.String text)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByText(text);
	}

	/**
	* Returns a range of all the bars where text = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param text the text
	* @param start the lower bound of the range of bars
	* @param end the upper bound of the range of bars (not inclusive)
	* @return the range of matching bars
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.testtransaction.model.Bar> findByText(
		java.lang.String text, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByText(text, start, end);
	}

	/**
	* Returns an ordered range of all the bars where text = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param text the text
	* @param start the lower bound of the range of bars
	* @param end the upper bound of the range of bars (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching bars
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.testtransaction.model.Bar> findByText(
		java.lang.String text, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByText(text, start, end, orderByComparator);
	}

	/**
	* Returns the first bar in the ordered set where text = &#63;.
	*
	* @param text the text
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching bar
	* @throws com.liferay.testtransaction.NoSuchBarException if a matching bar could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.testtransaction.model.Bar findByText_First(
		java.lang.String text,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.testtransaction.NoSuchBarException {
		return getPersistence().findByText_First(text, orderByComparator);
	}

	/**
	* Returns the first bar in the ordered set where text = &#63;.
	*
	* @param text the text
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching bar, or <code>null</code> if a matching bar could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.testtransaction.model.Bar fetchByText_First(
		java.lang.String text,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByText_First(text, orderByComparator);
	}

	/**
	* Returns the last bar in the ordered set where text = &#63;.
	*
	* @param text the text
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching bar
	* @throws com.liferay.testtransaction.NoSuchBarException if a matching bar could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.testtransaction.model.Bar findByText_Last(
		java.lang.String text,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.testtransaction.NoSuchBarException {
		return getPersistence().findByText_Last(text, orderByComparator);
	}

	/**
	* Returns the last bar in the ordered set where text = &#63;.
	*
	* @param text the text
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching bar, or <code>null</code> if a matching bar could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.testtransaction.model.Bar fetchByText_Last(
		java.lang.String text,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByText_Last(text, orderByComparator);
	}

	/**
	* Returns the bars before and after the current bar in the ordered set where text = &#63;.
	*
	* @param barId the primary key of the current bar
	* @param text the text
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next bar
	* @throws com.liferay.testtransaction.NoSuchBarException if a bar with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.testtransaction.model.Bar[] findByText_PrevAndNext(
		long barId, java.lang.String text,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.testtransaction.NoSuchBarException {
		return getPersistence()
				   .findByText_PrevAndNext(barId, text, orderByComparator);
	}

	/**
	* Returns all the bars.
	*
	* @return the bars
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.testtransaction.model.Bar> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the bars.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of bars
	* @param end the upper bound of the range of bars (not inclusive)
	* @return the range of bars
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.testtransaction.model.Bar> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the bars.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of bars
	* @param end the upper bound of the range of bars (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of bars
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.testtransaction.model.Bar> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the bars where text = &#63; from the database.
	*
	* @param text the text
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByText(java.lang.String text)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByText(text);
	}

	/**
	* Removes all the bars from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of bars where text = &#63;.
	*
	* @param text the text
	* @return the number of matching bars
	* @throws SystemException if a system exception occurred
	*/
	public static int countByText(java.lang.String text)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByText(text);
	}

	/**
	* Returns the number of bars.
	*
	* @return the number of bars
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static BarPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (BarPersistence)PortletBeanLocatorUtil.locate(com.liferay.testtransaction.service.ClpSerializer.getServletContextName(),
					BarPersistence.class.getName());

			ReferenceRegistry.registerReference(BarUtil.class, "_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(BarPersistence persistence) {
	}

	private static BarPersistence _persistence;
}