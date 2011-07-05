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

import com.liferay.hr.model.HRBillability;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the h r billability service. This utility wraps {@link HRBillabilityPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRBillabilityPersistence
 * @see HRBillabilityPersistenceImpl
 * @generated
 */
public class HRBillabilityUtil {
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
	public static void clearCache(HRBillability hrBillability) {
		getPersistence().clearCache(hrBillability);
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
	public static List<HRBillability> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HRBillability> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HRBillability> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static HRBillability remove(HRBillability hrBillability)
		throws SystemException {
		return getPersistence().remove(hrBillability);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static HRBillability update(HRBillability hrBillability,
		boolean merge) throws SystemException {
		return getPersistence().update(hrBillability, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static HRBillability update(HRBillability hrBillability,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(hrBillability, merge, serviceContext);
	}

	/**
	* Caches the h r billability in the entity cache if it is enabled.
	*
	* @param hrBillability the h r billability
	*/
	public static void cacheResult(
		com.liferay.hr.model.HRBillability hrBillability) {
		getPersistence().cacheResult(hrBillability);
	}

	/**
	* Caches the h r billabilities in the entity cache if it is enabled.
	*
	* @param hrBillabilities the h r billabilities
	*/
	public static void cacheResult(
		java.util.List<com.liferay.hr.model.HRBillability> hrBillabilities) {
		getPersistence().cacheResult(hrBillabilities);
	}

	/**
	* Creates a new h r billability with the primary key. Does not add the h r billability to the database.
	*
	* @param hrBillabilityId the primary key for the new h r billability
	* @return the new h r billability
	*/
	public static com.liferay.hr.model.HRBillability create(
		long hrBillabilityId) {
		return getPersistence().create(hrBillabilityId);
	}

	/**
	* Removes the h r billability with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrBillabilityId the primary key of the h r billability
	* @return the h r billability that was removed
	* @throws com.liferay.hr.NoSuchBillabilityException if a h r billability with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRBillability remove(
		long hrBillabilityId)
		throws com.liferay.hr.NoSuchBillabilityException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(hrBillabilityId);
	}

	public static com.liferay.hr.model.HRBillability updateImpl(
		com.liferay.hr.model.HRBillability hrBillability, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(hrBillability, merge);
	}

	/**
	* Returns the h r billability with the primary key or throws a {@link com.liferay.hr.NoSuchBillabilityException} if it could not be found.
	*
	* @param hrBillabilityId the primary key of the h r billability
	* @return the h r billability
	* @throws com.liferay.hr.NoSuchBillabilityException if a h r billability with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRBillability findByPrimaryKey(
		long hrBillabilityId)
		throws com.liferay.hr.NoSuchBillabilityException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(hrBillabilityId);
	}

	/**
	* Returns the h r billability with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrBillabilityId the primary key of the h r billability
	* @return the h r billability, or <code>null</code> if a h r billability with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRBillability fetchByPrimaryKey(
		long hrBillabilityId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(hrBillabilityId);
	}

	/**
	* Returns the h r billability where groupId = &#63; and code = &#63; or throws a {@link com.liferay.hr.NoSuchBillabilityException} if it could not be found.
	*
	* @param groupId the group ID
	* @param code the code
	* @return the matching h r billability
	* @throws com.liferay.hr.NoSuchBillabilityException if a matching h r billability could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRBillability findByG_C(long groupId,
		java.lang.String code)
		throws com.liferay.hr.NoSuchBillabilityException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_C(groupId, code);
	}

	/**
	* Returns the h r billability where groupId = &#63; and code = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param code the code
	* @return the matching h r billability, or <code>null</code> if a matching h r billability could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRBillability fetchByG_C(long groupId,
		java.lang.String code)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByG_C(groupId, code);
	}

	/**
	* Returns the h r billability where groupId = &#63; and code = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param code the code
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching h r billability, or <code>null</code> if a matching h r billability could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRBillability fetchByG_C(long groupId,
		java.lang.String code, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByG_C(groupId, code, retrieveFromCache);
	}

	/**
	* Returns all the h r billabilities.
	*
	* @return the h r billabilities
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRBillability> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the h r billabilities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r billabilities
	* @param end the upper bound of the range of h r billabilities (not inclusive)
	* @return the range of h r billabilities
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRBillability> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the h r billabilities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r billabilities
	* @param end the upper bound of the range of h r billabilities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r billabilities
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRBillability> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes the h r billability where groupId = &#63; and code = &#63; from the database.
	*
	* @param groupId the group ID
	* @param code the code
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_C(long groupId, java.lang.String code)
		throws com.liferay.hr.NoSuchBillabilityException,
			com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_C(groupId, code);
	}

	/**
	* Removes all the h r billabilities from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of h r billabilities where groupId = &#63; and code = &#63;.
	*
	* @param groupId the group ID
	* @param code the code
	* @return the number of matching h r billabilities
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_C(long groupId, java.lang.String code)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_C(groupId, code);
	}

	/**
	* Returns the number of h r billabilities.
	*
	* @return the number of h r billabilities
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static HRBillabilityPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (HRBillabilityPersistence)PortletBeanLocatorUtil.locate(com.liferay.hr.service.ClpSerializer.getServletContextName(),
					HRBillabilityPersistence.class.getName());

			ReferenceRegistry.registerReference(HRBillabilityUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(HRBillabilityPersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(HRBillabilityUtil.class,
			"_persistence");
	}

	private static HRBillabilityPersistence _persistence;
}