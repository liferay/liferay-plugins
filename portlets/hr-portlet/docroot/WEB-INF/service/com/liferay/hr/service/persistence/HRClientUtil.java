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

import com.liferay.hr.model.HRClient;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the h r client service. This utility wraps {@link HRClientPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRClientPersistence
 * @see HRClientPersistenceImpl
 * @generated
 */
public class HRClientUtil {
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
	public static void clearCache(HRClient hrClient) {
		getPersistence().clearCache(hrClient);
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
	public static List<HRClient> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HRClient> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HRClient> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static HRClient remove(HRClient hrClient) throws SystemException {
		return getPersistence().remove(hrClient);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static HRClient update(HRClient hrClient, boolean merge)
		throws SystemException {
		return getPersistence().update(hrClient, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static HRClient update(HRClient hrClient, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(hrClient, merge, serviceContext);
	}

	/**
	* Caches the h r client in the entity cache if it is enabled.
	*
	* @param hrClient the h r client
	*/
	public static void cacheResult(com.liferay.hr.model.HRClient hrClient) {
		getPersistence().cacheResult(hrClient);
	}

	/**
	* Caches the h r clients in the entity cache if it is enabled.
	*
	* @param hrClients the h r clients
	*/
	public static void cacheResult(
		java.util.List<com.liferay.hr.model.HRClient> hrClients) {
		getPersistence().cacheResult(hrClients);
	}

	/**
	* Creates a new h r client with the primary key. Does not add the h r client to the database.
	*
	* @param hrClientId the primary key for the new h r client
	* @return the new h r client
	*/
	public static com.liferay.hr.model.HRClient create(long hrClientId) {
		return getPersistence().create(hrClientId);
	}

	/**
	* Removes the h r client with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrClientId the primary key of the h r client
	* @return the h r client that was removed
	* @throws com.liferay.hr.NoSuchClientException if a h r client with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRClient remove(long hrClientId)
		throws com.liferay.hr.NoSuchClientException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(hrClientId);
	}

	public static com.liferay.hr.model.HRClient updateImpl(
		com.liferay.hr.model.HRClient hrClient, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(hrClient, merge);
	}

	/**
	* Returns the h r client with the primary key or throws a {@link com.liferay.hr.NoSuchClientException} if it could not be found.
	*
	* @param hrClientId the primary key of the h r client
	* @return the h r client
	* @throws com.liferay.hr.NoSuchClientException if a h r client with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRClient findByPrimaryKey(
		long hrClientId)
		throws com.liferay.hr.NoSuchClientException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(hrClientId);
	}

	/**
	* Returns the h r client with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrClientId the primary key of the h r client
	* @return the h r client, or <code>null</code> if a h r client with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRClient fetchByPrimaryKey(
		long hrClientId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(hrClientId);
	}

	/**
	* Returns all the h r clients.
	*
	* @return the h r clients
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRClient> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the h r clients.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r clients
	* @param end the upper bound of the range of h r clients (not inclusive)
	* @return the range of h r clients
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRClient> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the h r clients.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r clients
	* @param end the upper bound of the range of h r clients (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r clients
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRClient> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the h r clients from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of h r clients.
	*
	* @return the number of h r clients
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static HRClientPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (HRClientPersistence)PortletBeanLocatorUtil.locate(com.liferay.hr.service.ClpSerializer.getServletContextName(),
					HRClientPersistence.class.getName());

			ReferenceRegistry.registerReference(HRClientUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(HRClientPersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(HRClientUtil.class, "_persistence");
	}

	private static HRClientPersistence _persistence;
}