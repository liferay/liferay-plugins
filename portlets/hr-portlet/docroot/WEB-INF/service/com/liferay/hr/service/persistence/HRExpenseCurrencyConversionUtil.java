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

import com.liferay.hr.model.HRExpenseCurrencyConversion;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the h r expense currency conversion service. This utility wraps {@link HRExpenseCurrencyConversionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRExpenseCurrencyConversionPersistence
 * @see HRExpenseCurrencyConversionPersistenceImpl
 * @generated
 */
public class HRExpenseCurrencyConversionUtil {
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
	public static void clearCache(
		HRExpenseCurrencyConversion hrExpenseCurrencyConversion) {
		getPersistence().clearCache(hrExpenseCurrencyConversion);
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
	public static List<HRExpenseCurrencyConversion> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HRExpenseCurrencyConversion> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HRExpenseCurrencyConversion> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static HRExpenseCurrencyConversion remove(
		HRExpenseCurrencyConversion hrExpenseCurrencyConversion)
		throws SystemException {
		return getPersistence().remove(hrExpenseCurrencyConversion);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static HRExpenseCurrencyConversion update(
		HRExpenseCurrencyConversion hrExpenseCurrencyConversion, boolean merge)
		throws SystemException {
		return getPersistence().update(hrExpenseCurrencyConversion, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static HRExpenseCurrencyConversion update(
		HRExpenseCurrencyConversion hrExpenseCurrencyConversion, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence()
				   .update(hrExpenseCurrencyConversion, merge, serviceContext);
	}

	/**
	* Caches the h r expense currency conversion in the entity cache if it is enabled.
	*
	* @param hrExpenseCurrencyConversion the h r expense currency conversion
	*/
	public static void cacheResult(
		com.liferay.hr.model.HRExpenseCurrencyConversion hrExpenseCurrencyConversion) {
		getPersistence().cacheResult(hrExpenseCurrencyConversion);
	}

	/**
	* Caches the h r expense currency conversions in the entity cache if it is enabled.
	*
	* @param hrExpenseCurrencyConversions the h r expense currency conversions
	*/
	public static void cacheResult(
		java.util.List<com.liferay.hr.model.HRExpenseCurrencyConversion> hrExpenseCurrencyConversions) {
		getPersistence().cacheResult(hrExpenseCurrencyConversions);
	}

	/**
	* Creates a new h r expense currency conversion with the primary key. Does not add the h r expense currency conversion to the database.
	*
	* @param hrExpenseCurrencyConversionId the primary key for the new h r expense currency conversion
	* @return the new h r expense currency conversion
	*/
	public static com.liferay.hr.model.HRExpenseCurrencyConversion create(
		long hrExpenseCurrencyConversionId) {
		return getPersistence().create(hrExpenseCurrencyConversionId);
	}

	/**
	* Removes the h r expense currency conversion with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrExpenseCurrencyConversionId the primary key of the h r expense currency conversion
	* @return the h r expense currency conversion that was removed
	* @throws com.liferay.hr.NoSuchExpenseCurrencyConversionException if a h r expense currency conversion with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRExpenseCurrencyConversion remove(
		long hrExpenseCurrencyConversionId)
		throws com.liferay.hr.NoSuchExpenseCurrencyConversionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(hrExpenseCurrencyConversionId);
	}

	public static com.liferay.hr.model.HRExpenseCurrencyConversion updateImpl(
		com.liferay.hr.model.HRExpenseCurrencyConversion hrExpenseCurrencyConversion,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(hrExpenseCurrencyConversion, merge);
	}

	/**
	* Returns the h r expense currency conversion with the primary key or throws a {@link com.liferay.hr.NoSuchExpenseCurrencyConversionException} if it could not be found.
	*
	* @param hrExpenseCurrencyConversionId the primary key of the h r expense currency conversion
	* @return the h r expense currency conversion
	* @throws com.liferay.hr.NoSuchExpenseCurrencyConversionException if a h r expense currency conversion with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRExpenseCurrencyConversion findByPrimaryKey(
		long hrExpenseCurrencyConversionId)
		throws com.liferay.hr.NoSuchExpenseCurrencyConversionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(hrExpenseCurrencyConversionId);
	}

	/**
	* Returns the h r expense currency conversion with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrExpenseCurrencyConversionId the primary key of the h r expense currency conversion
	* @return the h r expense currency conversion, or <code>null</code> if a h r expense currency conversion with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRExpenseCurrencyConversion fetchByPrimaryKey(
		long hrExpenseCurrencyConversionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(hrExpenseCurrencyConversionId);
	}

	/**
	* Returns the h r expense currency conversion where groupId = &#63; and fromHRExpenseCurrencyId = &#63; and toHRExpenseCurrencyId = &#63; and conversionDate &lt; &#63; or throws a {@link com.liferay.hr.NoSuchExpenseCurrencyConversionException} if it could not be found.
	*
	* @param groupId the group ID
	* @param fromHRExpenseCurrencyId the from h r expense currency ID
	* @param toHRExpenseCurrencyId the to h r expense currency ID
	* @param conversionDate the conversion date
	* @return the matching h r expense currency conversion
	* @throws com.liferay.hr.NoSuchExpenseCurrencyConversionException if a matching h r expense currency conversion could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRExpenseCurrencyConversion findByG_F_T_C(
		long groupId, long fromHRExpenseCurrencyId, long toHRExpenseCurrencyId,
		java.util.Date conversionDate)
		throws com.liferay.hr.NoSuchExpenseCurrencyConversionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_F_T_C(groupId, fromHRExpenseCurrencyId,
			toHRExpenseCurrencyId, conversionDate);
	}

	/**
	* Returns the h r expense currency conversion where groupId = &#63; and fromHRExpenseCurrencyId = &#63; and toHRExpenseCurrencyId = &#63; and conversionDate &lt; &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param fromHRExpenseCurrencyId the from h r expense currency ID
	* @param toHRExpenseCurrencyId the to h r expense currency ID
	* @param conversionDate the conversion date
	* @return the matching h r expense currency conversion, or <code>null</code> if a matching h r expense currency conversion could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRExpenseCurrencyConversion fetchByG_F_T_C(
		long groupId, long fromHRExpenseCurrencyId, long toHRExpenseCurrencyId,
		java.util.Date conversionDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_F_T_C(groupId, fromHRExpenseCurrencyId,
			toHRExpenseCurrencyId, conversionDate);
	}

	/**
	* Returns the h r expense currency conversion where groupId = &#63; and fromHRExpenseCurrencyId = &#63; and toHRExpenseCurrencyId = &#63; and conversionDate &lt; &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param fromHRExpenseCurrencyId the from h r expense currency ID
	* @param toHRExpenseCurrencyId the to h r expense currency ID
	* @param conversionDate the conversion date
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching h r expense currency conversion, or <code>null</code> if a matching h r expense currency conversion could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRExpenseCurrencyConversion fetchByG_F_T_C(
		long groupId, long fromHRExpenseCurrencyId, long toHRExpenseCurrencyId,
		java.util.Date conversionDate, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_F_T_C(groupId, fromHRExpenseCurrencyId,
			toHRExpenseCurrencyId, conversionDate, retrieveFromCache);
	}

	/**
	* Returns all the h r expense currency conversions.
	*
	* @return the h r expense currency conversions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRExpenseCurrencyConversion> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the h r expense currency conversions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r expense currency conversions
	* @param end the upper bound of the range of h r expense currency conversions (not inclusive)
	* @return the range of h r expense currency conversions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRExpenseCurrencyConversion> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the h r expense currency conversions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r expense currency conversions
	* @param end the upper bound of the range of h r expense currency conversions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r expense currency conversions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRExpenseCurrencyConversion> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes the h r expense currency conversion where groupId = &#63; and fromHRExpenseCurrencyId = &#63; and toHRExpenseCurrencyId = &#63; and conversionDate &lt; &#63; from the database.
	*
	* @param groupId the group ID
	* @param fromHRExpenseCurrencyId the from h r expense currency ID
	* @param toHRExpenseCurrencyId the to h r expense currency ID
	* @param conversionDate the conversion date
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_F_T_C(long groupId,
		long fromHRExpenseCurrencyId, long toHRExpenseCurrencyId,
		java.util.Date conversionDate)
		throws com.liferay.hr.NoSuchExpenseCurrencyConversionException,
			com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByG_F_T_C(groupId, fromHRExpenseCurrencyId,
			toHRExpenseCurrencyId, conversionDate);
	}

	/**
	* Removes all the h r expense currency conversions from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of h r expense currency conversions where groupId = &#63; and fromHRExpenseCurrencyId = &#63; and toHRExpenseCurrencyId = &#63; and conversionDate &lt; &#63;.
	*
	* @param groupId the group ID
	* @param fromHRExpenseCurrencyId the from h r expense currency ID
	* @param toHRExpenseCurrencyId the to h r expense currency ID
	* @param conversionDate the conversion date
	* @return the number of matching h r expense currency conversions
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_F_T_C(long groupId,
		long fromHRExpenseCurrencyId, long toHRExpenseCurrencyId,
		java.util.Date conversionDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByG_F_T_C(groupId, fromHRExpenseCurrencyId,
			toHRExpenseCurrencyId, conversionDate);
	}

	/**
	* Returns the number of h r expense currency conversions.
	*
	* @return the number of h r expense currency conversions
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static HRExpenseCurrencyConversionPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (HRExpenseCurrencyConversionPersistence)PortletBeanLocatorUtil.locate(com.liferay.hr.service.ClpSerializer.getServletContextName(),
					HRExpenseCurrencyConversionPersistence.class.getName());

			ReferenceRegistry.registerReference(HRExpenseCurrencyConversionUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(
		HRExpenseCurrencyConversionPersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(HRExpenseCurrencyConversionUtil.class,
			"_persistence");
	}

	private static HRExpenseCurrencyConversionPersistence _persistence;
}