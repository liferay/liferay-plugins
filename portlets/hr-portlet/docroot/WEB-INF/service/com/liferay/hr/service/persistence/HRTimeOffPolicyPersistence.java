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

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the h r time off policy service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HRTimeOffPolicyPersistenceImpl
 * @see HRTimeOffPolicyUtil
 * @generated
 */
public interface HRTimeOffPolicyPersistence extends BasePersistence<HRTimeOffPolicy> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HRTimeOffPolicyUtil} to access the h r time off policy persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the h r time off policy in the entity cache if it is enabled.
	*
	* @param hrTimeOffPolicy the h r time off policy to cache
	*/
	public void cacheResult(
		com.liferay.hr.model.HRTimeOffPolicy hrTimeOffPolicy);

	/**
	* Caches the h r time off policies in the entity cache if it is enabled.
	*
	* @param hrTimeOffPolicies the h r time off policies to cache
	*/
	public void cacheResult(
		java.util.List<com.liferay.hr.model.HRTimeOffPolicy> hrTimeOffPolicies);

	/**
	* Creates a new h r time off policy with the primary key. Does not add the h r time off policy to the database.
	*
	* @param hrTimeOffPolicyId the primary key for the new h r time off policy
	* @return the new h r time off policy
	*/
	public com.liferay.hr.model.HRTimeOffPolicy create(long hrTimeOffPolicyId);

	/**
	* Removes the h r time off policy with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrTimeOffPolicyId the primary key of the h r time off policy to remove
	* @return the h r time off policy that was removed
	* @throws com.liferay.hr.NoSuchTimeOffPolicyException if a h r time off policy with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRTimeOffPolicy remove(long hrTimeOffPolicyId)
		throws com.liferay.hr.NoSuchTimeOffPolicyException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.hr.model.HRTimeOffPolicy updateImpl(
		com.liferay.hr.model.HRTimeOffPolicy hrTimeOffPolicy, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the h r time off policy with the primary key or throws a {@link com.liferay.hr.NoSuchTimeOffPolicyException} if it could not be found.
	*
	* @param hrTimeOffPolicyId the primary key of the h r time off policy to find
	* @return the h r time off policy
	* @throws com.liferay.hr.NoSuchTimeOffPolicyException if a h r time off policy with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRTimeOffPolicy findByPrimaryKey(
		long hrTimeOffPolicyId)
		throws com.liferay.hr.NoSuchTimeOffPolicyException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the h r time off policy with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrTimeOffPolicyId the primary key of the h r time off policy to find
	* @return the h r time off policy, or <code>null</code> if a h r time off policy with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRTimeOffPolicy fetchByPrimaryKey(
		long hrTimeOffPolicyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the h r time off policies.
	*
	* @return the h r time off policies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRTimeOffPolicy> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.hr.model.HRTimeOffPolicy> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.hr.model.HRTimeOffPolicy> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the h r time off policies from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the h r time off policies.
	*
	* @return the number of h r time off policies
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public HRTimeOffPolicy remove(HRTimeOffPolicy hrTimeOffPolicy)
		throws SystemException;
}