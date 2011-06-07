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

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the h r expense currency conversion service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRExpenseCurrencyConversionPersistenceImpl
 * @see HRExpenseCurrencyConversionUtil
 * @generated
 */
public interface HRExpenseCurrencyConversionPersistence extends BasePersistence<HRExpenseCurrencyConversion> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HRExpenseCurrencyConversionUtil} to access the h r expense currency conversion persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the h r expense currency conversion in the entity cache if it is enabled.
	*
	* @param hrExpenseCurrencyConversion the h r expense currency conversion
	*/
	public void cacheResult(
		com.liferay.hr.model.HRExpenseCurrencyConversion hrExpenseCurrencyConversion);

	/**
	* Caches the h r expense currency conversions in the entity cache if it is enabled.
	*
	* @param hrExpenseCurrencyConversions the h r expense currency conversions
	*/
	public void cacheResult(
		java.util.List<com.liferay.hr.model.HRExpenseCurrencyConversion> hrExpenseCurrencyConversions);

	/**
	* Creates a new h r expense currency conversion with the primary key. Does not add the h r expense currency conversion to the database.
	*
	* @param hrExpenseCurrencyConversionId the primary key for the new h r expense currency conversion
	* @return the new h r expense currency conversion
	*/
	public com.liferay.hr.model.HRExpenseCurrencyConversion create(
		long hrExpenseCurrencyConversionId);

	/**
	* Removes the h r expense currency conversion with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrExpenseCurrencyConversionId the primary key of the h r expense currency conversion
	* @return the h r expense currency conversion that was removed
	* @throws com.liferay.hr.NoSuchExpenseCurrencyConversionException if a h r expense currency conversion with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRExpenseCurrencyConversion remove(
		long hrExpenseCurrencyConversionId)
		throws com.liferay.hr.NoSuchExpenseCurrencyConversionException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.hr.model.HRExpenseCurrencyConversion updateImpl(
		com.liferay.hr.model.HRExpenseCurrencyConversion hrExpenseCurrencyConversion,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the h r expense currency conversion with the primary key or throws a {@link com.liferay.hr.NoSuchExpenseCurrencyConversionException} if it could not be found.
	*
	* @param hrExpenseCurrencyConversionId the primary key of the h r expense currency conversion
	* @return the h r expense currency conversion
	* @throws com.liferay.hr.NoSuchExpenseCurrencyConversionException if a h r expense currency conversion with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRExpenseCurrencyConversion findByPrimaryKey(
		long hrExpenseCurrencyConversionId)
		throws com.liferay.hr.NoSuchExpenseCurrencyConversionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the h r expense currency conversion with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrExpenseCurrencyConversionId the primary key of the h r expense currency conversion
	* @return the h r expense currency conversion, or <code>null</code> if a h r expense currency conversion with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRExpenseCurrencyConversion fetchByPrimaryKey(
		long hrExpenseCurrencyConversionId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.hr.model.HRExpenseCurrencyConversion findByG_F_T_C(
		long groupId, long fromHRExpenseCurrencyId, long toHRExpenseCurrencyId,
		java.util.Date conversionDate)
		throws com.liferay.hr.NoSuchExpenseCurrencyConversionException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.hr.model.HRExpenseCurrencyConversion fetchByG_F_T_C(
		long groupId, long fromHRExpenseCurrencyId, long toHRExpenseCurrencyId,
		java.util.Date conversionDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the h r expense currency conversion where groupId = &#63; and fromHRExpenseCurrencyId = &#63; and toHRExpenseCurrencyId = &#63; and conversionDate &lt; &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param fromHRExpenseCurrencyId the from h r expense currency ID
	* @param toHRExpenseCurrencyId the to h r expense currency ID
	* @param conversionDate the conversion date
	* @return the matching h r expense currency conversion, or <code>null</code> if a matching h r expense currency conversion could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRExpenseCurrencyConversion fetchByG_F_T_C(
		long groupId, long fromHRExpenseCurrencyId, long toHRExpenseCurrencyId,
		java.util.Date conversionDate, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the h r expense currency conversions.
	*
	* @return the h r expense currency conversions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRExpenseCurrencyConversion> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.hr.model.HRExpenseCurrencyConversion> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.hr.model.HRExpenseCurrencyConversion> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the h r expense currency conversion where groupId = &#63; and fromHRExpenseCurrencyId = &#63; and toHRExpenseCurrencyId = &#63; and conversionDate &lt; &#63; from the database.
	*
	* @param groupId the group ID
	* @param fromHRExpenseCurrencyId the from h r expense currency ID
	* @param toHRExpenseCurrencyId the to h r expense currency ID
	* @param conversionDate the conversion date
	* @throws SystemException if a system exception occurred
	*/
	public void removeByG_F_T_C(long groupId, long fromHRExpenseCurrencyId,
		long toHRExpenseCurrencyId, java.util.Date conversionDate)
		throws com.liferay.hr.NoSuchExpenseCurrencyConversionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the h r expense currency conversions from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public int countByG_F_T_C(long groupId, long fromHRExpenseCurrencyId,
		long toHRExpenseCurrencyId, java.util.Date conversionDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of h r expense currency conversions.
	*
	* @return the number of h r expense currency conversions
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public HRExpenseCurrencyConversion remove(
		HRExpenseCurrencyConversion hrExpenseCurrencyConversion)
		throws SystemException;
}