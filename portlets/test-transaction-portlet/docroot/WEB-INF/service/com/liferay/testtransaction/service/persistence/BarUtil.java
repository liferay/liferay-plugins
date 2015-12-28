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

package com.liferay.testtransaction.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.liferay.testtransaction.model.Bar;

import java.util.List;

/**
 * The persistence utility for the bar service. This utility wraps {@link com.liferay.testtransaction.service.persistence.impl.BarPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BarPersistence
 * @see com.liferay.testtransaction.service.persistence.impl.BarPersistenceImpl
 * @generated
 */
@ProviderType
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
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Bar> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Bar> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Bar> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator<Bar> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static Bar update(Bar bar) {
		return getPersistence().update(bar);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static Bar update(Bar bar, ServiceContext serviceContext) {
		return getPersistence().update(bar, serviceContext);
	}

	/**
	* Returns all the bars where text = &#63;.
	*
	* @param text the text
	* @return the matching bars
	*/
	public static List<Bar> findByText(java.lang.String text) {
		return getPersistence().findByText(text);
	}

	/**
	* Returns a range of all the bars where text = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param text the text
	* @param start the lower bound of the range of bars
	* @param end the upper bound of the range of bars (not inclusive)
	* @return the range of matching bars
	*/
	public static List<Bar> findByText(java.lang.String text, int start, int end) {
		return getPersistence().findByText(text, start, end);
	}

	/**
	* Returns an ordered range of all the bars where text = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param text the text
	* @param start the lower bound of the range of bars
	* @param end the upper bound of the range of bars (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching bars
	*/
	public static List<Bar> findByText(java.lang.String text, int start,
		int end, OrderByComparator<Bar> orderByComparator) {
		return getPersistence().findByText(text, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the bars where text = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param text the text
	* @param start the lower bound of the range of bars
	* @param end the upper bound of the range of bars (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching bars
	*/
	public static List<Bar> findByText(java.lang.String text, int start,
		int end, OrderByComparator<Bar> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByText(text, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first bar in the ordered set where text = &#63;.
	*
	* @param text the text
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching bar
	* @throws NoSuchBarException if a matching bar could not be found
	*/
	public static Bar findByText_First(java.lang.String text,
		OrderByComparator<Bar> orderByComparator)
		throws com.liferay.testtransaction.NoSuchBarException {
		return getPersistence().findByText_First(text, orderByComparator);
	}

	/**
	* Returns the first bar in the ordered set where text = &#63;.
	*
	* @param text the text
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching bar, or <code>null</code> if a matching bar could not be found
	*/
	public static Bar fetchByText_First(java.lang.String text,
		OrderByComparator<Bar> orderByComparator) {
		return getPersistence().fetchByText_First(text, orderByComparator);
	}

	/**
	* Returns the last bar in the ordered set where text = &#63;.
	*
	* @param text the text
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching bar
	* @throws NoSuchBarException if a matching bar could not be found
	*/
	public static Bar findByText_Last(java.lang.String text,
		OrderByComparator<Bar> orderByComparator)
		throws com.liferay.testtransaction.NoSuchBarException {
		return getPersistence().findByText_Last(text, orderByComparator);
	}

	/**
	* Returns the last bar in the ordered set where text = &#63;.
	*
	* @param text the text
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching bar, or <code>null</code> if a matching bar could not be found
	*/
	public static Bar fetchByText_Last(java.lang.String text,
		OrderByComparator<Bar> orderByComparator) {
		return getPersistence().fetchByText_Last(text, orderByComparator);
	}

	/**
	* Returns the bars before and after the current bar in the ordered set where text = &#63;.
	*
	* @param barId the primary key of the current bar
	* @param text the text
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next bar
	* @throws NoSuchBarException if a bar with the primary key could not be found
	*/
	public static Bar[] findByText_PrevAndNext(long barId,
		java.lang.String text, OrderByComparator<Bar> orderByComparator)
		throws com.liferay.testtransaction.NoSuchBarException {
		return getPersistence()
				   .findByText_PrevAndNext(barId, text, orderByComparator);
	}

	/**
	* Removes all the bars where text = &#63; from the database.
	*
	* @param text the text
	*/
	public static void removeByText(java.lang.String text) {
		getPersistence().removeByText(text);
	}

	/**
	* Returns the number of bars where text = &#63;.
	*
	* @param text the text
	* @return the number of matching bars
	*/
	public static int countByText(java.lang.String text) {
		return getPersistence().countByText(text);
	}

	/**
	* Caches the bar in the entity cache if it is enabled.
	*
	* @param bar the bar
	*/
	public static void cacheResult(Bar bar) {
		getPersistence().cacheResult(bar);
	}

	/**
	* Caches the bars in the entity cache if it is enabled.
	*
	* @param bars the bars
	*/
	public static void cacheResult(List<Bar> bars) {
		getPersistence().cacheResult(bars);
	}

	/**
	* Creates a new bar with the primary key. Does not add the bar to the database.
	*
	* @param barId the primary key for the new bar
	* @return the new bar
	*/
	public static Bar create(long barId) {
		return getPersistence().create(barId);
	}

	/**
	* Removes the bar with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param barId the primary key of the bar
	* @return the bar that was removed
	* @throws NoSuchBarException if a bar with the primary key could not be found
	*/
	public static Bar remove(long barId)
		throws com.liferay.testtransaction.NoSuchBarException {
		return getPersistence().remove(barId);
	}

	public static Bar updateImpl(Bar bar) {
		return getPersistence().updateImpl(bar);
	}

	/**
	* Returns the bar with the primary key or throws a {@link NoSuchBarException} if it could not be found.
	*
	* @param barId the primary key of the bar
	* @return the bar
	* @throws NoSuchBarException if a bar with the primary key could not be found
	*/
	public static Bar findByPrimaryKey(long barId)
		throws com.liferay.testtransaction.NoSuchBarException {
		return getPersistence().findByPrimaryKey(barId);
	}

	/**
	* Returns the bar with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param barId the primary key of the bar
	* @return the bar, or <code>null</code> if a bar with the primary key could not be found
	*/
	public static Bar fetchByPrimaryKey(long barId) {
		return getPersistence().fetchByPrimaryKey(barId);
	}

	public static java.util.Map<java.io.Serializable, Bar> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the bars.
	*
	* @return the bars
	*/
	public static List<Bar> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the bars.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of bars
	* @param end the upper bound of the range of bars (not inclusive)
	* @return the range of bars
	*/
	public static List<Bar> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the bars.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of bars
	* @param end the upper bound of the range of bars (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of bars
	*/
	public static List<Bar> findAll(int start, int end,
		OrderByComparator<Bar> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the bars.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of bars
	* @param end the upper bound of the range of bars (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of bars
	*/
	public static List<Bar> findAll(int start, int end,
		OrderByComparator<Bar> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the bars from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of bars.
	*
	* @return the number of bars
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static BarPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (BarPersistence)PortletBeanLocatorUtil.locate(com.liferay.testtransaction.service.ClpSerializer.getServletContextName(),
					BarPersistence.class.getName());

			ReferenceRegistry.registerReference(BarUtil.class, "_persistence");
		}

		return _persistence;
	}

	private static BarPersistence _persistence;
}