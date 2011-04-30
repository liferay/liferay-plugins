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

import com.liferay.hr.model.HRTimeOffPolicy;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the h r time off policy service. This utility wraps {@link HRTimeOffPolicyPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRTimeOffPolicyPersistence
 * @see HRTimeOffPolicyPersistenceImpl
 * @generated
 */
public class HRTimeOffPolicyUtil {
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
	public static void clearCache(HRTimeOffPolicy hrTimeOffPolicy) {
		getPersistence().clearCache(hrTimeOffPolicy);
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
	public static List<HRTimeOffPolicy> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HRTimeOffPolicy> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HRTimeOffPolicy> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static HRTimeOffPolicy remove(HRTimeOffPolicy hrTimeOffPolicy)
		throws SystemException {
		return getPersistence().remove(hrTimeOffPolicy);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static HRTimeOffPolicy update(HRTimeOffPolicy hrTimeOffPolicy,
		boolean merge) throws SystemException {
		return getPersistence().update(hrTimeOffPolicy, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static HRTimeOffPolicy update(HRTimeOffPolicy hrTimeOffPolicy,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(hrTimeOffPolicy, merge, serviceContext);
	}

	/**
	* Caches the h r time off policy in the entity cache if it is enabled.
	*
	* @param hrTimeOffPolicy the h r time off policy to cache
	*/
	public static void cacheResult(
		com.liferay.hr.model.HRTimeOffPolicy hrTimeOffPolicy) {
		getPersistence().cacheResult(hrTimeOffPolicy);
	}

	/**
	* Caches the h r time off policies in the entity cache if it is enabled.
	*
	* @param hrTimeOffPolicies the h r time off policies to cache
	*/
	public static void cacheResult(
		java.util.List<com.liferay.hr.model.HRTimeOffPolicy> hrTimeOffPolicies) {
		getPersistence().cacheResult(hrTimeOffPolicies);
	}

	/**
	* Creates a new h r time off policy with the primary key. Does not add the h r time off policy to the database.
	*
	* @param hrTimeOffPolicyId the primary key for the new h r time off policy
	* @return the new h r time off policy
	*/
	public static com.liferay.hr.model.HRTimeOffPolicy create(
		long hrTimeOffPolicyId) {
		return getPersistence().create(hrTimeOffPolicyId);
	}

	/**
	* Removes the h r time off policy with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrTimeOffPolicyId the primary key of the h r time off policy to remove
	* @return the h r time off policy that was removed
	* @throws com.liferay.hr.NoSuchTimeOffPolicyException if a h r time off policy with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRTimeOffPolicy remove(
		long hrTimeOffPolicyId)
		throws com.liferay.hr.NoSuchTimeOffPolicyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(hrTimeOffPolicyId);
	}

	public static com.liferay.hr.model.HRTimeOffPolicy updateImpl(
		com.liferay.hr.model.HRTimeOffPolicy hrTimeOffPolicy, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(hrTimeOffPolicy, merge);
	}

	/**
	* Finds the h r time off policy with the primary key or throws a {@link com.liferay.hr.NoSuchTimeOffPolicyException} if it could not be found.
	*
	* @param hrTimeOffPolicyId the primary key of the h r time off policy to find
	* @return the h r time off policy
	* @throws com.liferay.hr.NoSuchTimeOffPolicyException if a h r time off policy with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRTimeOffPolicy findByPrimaryKey(
		long hrTimeOffPolicyId)
		throws com.liferay.hr.NoSuchTimeOffPolicyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(hrTimeOffPolicyId);
	}

	/**
	* Finds the h r time off policy with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrTimeOffPolicyId the primary key of the h r time off policy to find
	* @return the h r time off policy, or <code>null</code> if a h r time off policy with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRTimeOffPolicy fetchByPrimaryKey(
		long hrTimeOffPolicyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(hrTimeOffPolicyId);
	}

	/**
	* Finds all the h r time off policies.
	*
	* @return the h r time off policies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRTimeOffPolicy> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Finds a range of all the h r time off policies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r time off policies to return
	* @param end the upper bound of the range of h r time off policies to return (not inclusive)
	* @return the range of h r time off policies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRTimeOffPolicy> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Finds an ordered range of all the h r time off policies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r time off policies to return
	* @param end the upper bound of the range of h r time off policies to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r time off policies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRTimeOffPolicy> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the h r time off policies from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Counts all the h r time off policies.
	*
	* @return the number of h r time off policies
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static HRTimeOffPolicyPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (HRTimeOffPolicyPersistence)PortletBeanLocatorUtil.locate(com.liferay.hr.service.ClpSerializer.getServletContextName(),
					HRTimeOffPolicyPersistence.class.getName());

			ReferenceRegistry.registerReference(HRTimeOffPolicyUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(HRTimeOffPolicyPersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(HRTimeOffPolicyUtil.class,
			"_persistence");
	}

	private static HRTimeOffPolicyPersistence _persistence;
}