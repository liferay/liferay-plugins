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

import com.liferay.hr.model.HRExpenseCurrency;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the h r expense currency service. This utility wraps {@link HRExpenseCurrencyPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HRExpenseCurrencyPersistence
 * @see HRExpenseCurrencyPersistenceImpl
 * @generated
 */
public class HRExpenseCurrencyUtil {
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
	public static void clearCache(HRExpenseCurrency hrExpenseCurrency) {
		getPersistence().clearCache(hrExpenseCurrency);
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
	public static List<HRExpenseCurrency> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HRExpenseCurrency> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HRExpenseCurrency> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static HRExpenseCurrency remove(HRExpenseCurrency hrExpenseCurrency)
		throws SystemException {
		return getPersistence().remove(hrExpenseCurrency);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static HRExpenseCurrency update(
		HRExpenseCurrency hrExpenseCurrency, boolean merge)
		throws SystemException {
		return getPersistence().update(hrExpenseCurrency, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static HRExpenseCurrency update(
		HRExpenseCurrency hrExpenseCurrency, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(hrExpenseCurrency, merge, serviceContext);
	}

	/**
	* Caches the h r expense currency in the entity cache if it is enabled.
	*
	* @param hrExpenseCurrency the h r expense currency to cache
	*/
	public static void cacheResult(
		com.liferay.hr.model.HRExpenseCurrency hrExpenseCurrency) {
		getPersistence().cacheResult(hrExpenseCurrency);
	}

	/**
	* Caches the h r expense currencies in the entity cache if it is enabled.
	*
	* @param hrExpenseCurrencies the h r expense currencies to cache
	*/
	public static void cacheResult(
		java.util.List<com.liferay.hr.model.HRExpenseCurrency> hrExpenseCurrencies) {
		getPersistence().cacheResult(hrExpenseCurrencies);
	}

	/**
	* Creates a new h r expense currency with the primary key. Does not add the h r expense currency to the database.
	*
	* @param hrExpenseCurrencyId the primary key for the new h r expense currency
	* @return the new h r expense currency
	*/
	public static com.liferay.hr.model.HRExpenseCurrency create(
		long hrExpenseCurrencyId) {
		return getPersistence().create(hrExpenseCurrencyId);
	}

	/**
	* Removes the h r expense currency with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrExpenseCurrencyId the primary key of the h r expense currency to remove
	* @return the h r expense currency that was removed
	* @throws com.liferay.hr.NoSuchExpenseCurrencyException if a h r expense currency with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRExpenseCurrency remove(
		long hrExpenseCurrencyId)
		throws com.liferay.hr.NoSuchExpenseCurrencyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(hrExpenseCurrencyId);
	}

	public static com.liferay.hr.model.HRExpenseCurrency updateImpl(
		com.liferay.hr.model.HRExpenseCurrency hrExpenseCurrency, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(hrExpenseCurrency, merge);
	}

	/**
	* Finds the h r expense currency with the primary key or throws a {@link com.liferay.hr.NoSuchExpenseCurrencyException} if it could not be found.
	*
	* @param hrExpenseCurrencyId the primary key of the h r expense currency to find
	* @return the h r expense currency
	* @throws com.liferay.hr.NoSuchExpenseCurrencyException if a h r expense currency with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRExpenseCurrency findByPrimaryKey(
		long hrExpenseCurrencyId)
		throws com.liferay.hr.NoSuchExpenseCurrencyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(hrExpenseCurrencyId);
	}

	/**
	* Finds the h r expense currency with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrExpenseCurrencyId the primary key of the h r expense currency to find
	* @return the h r expense currency, or <code>null</code> if a h r expense currency with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRExpenseCurrency fetchByPrimaryKey(
		long hrExpenseCurrencyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(hrExpenseCurrencyId);
	}

	/**
	* Finds the h r expense currency where groupId = &#63; and fromCurrencyCode = &#63; and toCurrencyCode = &#63; and conversionDate = &#63; or throws a {@link com.liferay.hr.NoSuchExpenseCurrencyException} if it could not be found.
	*
	* @param groupId the group ID to search with
	* @param fromCurrencyCode the from currency code to search with
	* @param toCurrencyCode the to currency code to search with
	* @param conversionDate the conversion date to search with
	* @return the matching h r expense currency
	* @throws com.liferay.hr.NoSuchExpenseCurrencyException if a matching h r expense currency could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRExpenseCurrency findByG_F_T_C(
		long groupId, java.lang.String fromCurrencyCode,
		java.lang.String toCurrencyCode, java.util.Date conversionDate)
		throws com.liferay.hr.NoSuchExpenseCurrencyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_F_T_C(groupId, fromCurrencyCode, toCurrencyCode,
			conversionDate);
	}

	/**
	* Finds the h r expense currency where groupId = &#63; and fromCurrencyCode = &#63; and toCurrencyCode = &#63; and conversionDate = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID to search with
	* @param fromCurrencyCode the from currency code to search with
	* @param toCurrencyCode the to currency code to search with
	* @param conversionDate the conversion date to search with
	* @return the matching h r expense currency, or <code>null</code> if a matching h r expense currency could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRExpenseCurrency fetchByG_F_T_C(
		long groupId, java.lang.String fromCurrencyCode,
		java.lang.String toCurrencyCode, java.util.Date conversionDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_F_T_C(groupId, fromCurrencyCode, toCurrencyCode,
			conversionDate);
	}

	/**
	* Finds the h r expense currency where groupId = &#63; and fromCurrencyCode = &#63; and toCurrencyCode = &#63; and conversionDate = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID to search with
	* @param fromCurrencyCode the from currency code to search with
	* @param toCurrencyCode the to currency code to search with
	* @param conversionDate the conversion date to search with
	* @return the matching h r expense currency, or <code>null</code> if a matching h r expense currency could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRExpenseCurrency fetchByG_F_T_C(
		long groupId, java.lang.String fromCurrencyCode,
		java.lang.String toCurrencyCode, java.util.Date conversionDate,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_F_T_C(groupId, fromCurrencyCode, toCurrencyCode,
			conversionDate, retrieveFromCache);
	}

	/**
	* Finds all the h r expense currencies.
	*
	* @return the h r expense currencies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRExpenseCurrency> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Finds a range of all the h r expense currencies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r expense currencies to return
	* @param end the upper bound of the range of h r expense currencies to return (not inclusive)
	* @return the range of h r expense currencies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRExpenseCurrency> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Finds an ordered range of all the h r expense currencies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r expense currencies to return
	* @param end the upper bound of the range of h r expense currencies to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r expense currencies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRExpenseCurrency> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes the h r expense currency where groupId = &#63; and fromCurrencyCode = &#63; and toCurrencyCode = &#63; and conversionDate = &#63; from the database.
	*
	* @param groupId the group ID to search with
	* @param fromCurrencyCode the from currency code to search with
	* @param toCurrencyCode the to currency code to search with
	* @param conversionDate the conversion date to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_F_T_C(long groupId,
		java.lang.String fromCurrencyCode, java.lang.String toCurrencyCode,
		java.util.Date conversionDate)
		throws com.liferay.hr.NoSuchExpenseCurrencyException,
			com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByG_F_T_C(groupId, fromCurrencyCode, toCurrencyCode,
			conversionDate);
	}

	/**
	* Removes all the h r expense currencies from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Counts all the h r expense currencies where groupId = &#63; and fromCurrencyCode = &#63; and toCurrencyCode = &#63; and conversionDate = &#63;.
	*
	* @param groupId the group ID to search with
	* @param fromCurrencyCode the from currency code to search with
	* @param toCurrencyCode the to currency code to search with
	* @param conversionDate the conversion date to search with
	* @return the number of matching h r expense currencies
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_F_T_C(long groupId,
		java.lang.String fromCurrencyCode, java.lang.String toCurrencyCode,
		java.util.Date conversionDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByG_F_T_C(groupId, fromCurrencyCode, toCurrencyCode,
			conversionDate);
	}

	/**
	* Counts all the h r expense currencies.
	*
	* @return the number of h r expense currencies
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static HRExpenseCurrencyPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (HRExpenseCurrencyPersistence)PortletBeanLocatorUtil.locate(com.liferay.hr.service.ClpSerializer.getServletContextName(),
					HRExpenseCurrencyPersistence.class.getName());

			ReferenceRegistry.registerReference(HRExpenseCurrencyUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(HRExpenseCurrencyPersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(HRExpenseCurrencyUtil.class,
			"_persistence");
	}

	private static HRExpenseCurrencyPersistence _persistence;
}