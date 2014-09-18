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

package com.liferay.marketplace.service.persistence;

import com.liferay.marketplace.model.Module;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the module service. This utility wraps {@link ModulePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ryan Park
 * @see ModulePersistence
 * @see ModulePersistenceImpl
 * @generated
 */
public class ModuleUtil {
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
	public static void clearCache(Module module) {
		getPersistence().clearCache(module);
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
	public static List<Module> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Module> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Module> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static Module update(Module module) throws SystemException {
		return getPersistence().update(module);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static Module update(Module module, ServiceContext serviceContext)
		throws SystemException {
		return getPersistence().update(module, serviceContext);
	}

	/**
	* Returns all the modules where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching modules
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.marketplace.model.Module> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the modules where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.marketplace.model.impl.ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of modules
	* @param end the upper bound of the range of modules (not inclusive)
	* @return the range of matching modules
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.marketplace.model.Module> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the modules where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.marketplace.model.impl.ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of modules
	* @param end the upper bound of the range of modules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching modules
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.marketplace.model.Module> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the first module in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching module
	* @throws com.liferay.marketplace.NoSuchModuleException if a matching module could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.Module findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.marketplace.NoSuchModuleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first module in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching module, or <code>null</code> if a matching module could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.Module fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last module in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching module
	* @throws com.liferay.marketplace.NoSuchModuleException if a matching module could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.Module findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.marketplace.NoSuchModuleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last module in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching module, or <code>null</code> if a matching module could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.Module fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the modules before and after the current module in the ordered set where uuid = &#63;.
	*
	* @param moduleId the primary key of the current module
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next module
	* @throws com.liferay.marketplace.NoSuchModuleException if a module with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.Module[] findByUuid_PrevAndNext(
		long moduleId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.marketplace.NoSuchModuleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_PrevAndNext(moduleId, uuid, orderByComparator);
	}

	/**
	* Removes all the modules where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of modules where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching modules
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns all the modules where appId = &#63;.
	*
	* @param appId the app ID
	* @return the matching modules
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.marketplace.model.Module> findByAppId(
		long appId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAppId(appId);
	}

	/**
	* Returns a range of all the modules where appId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.marketplace.model.impl.ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param appId the app ID
	* @param start the lower bound of the range of modules
	* @param end the upper bound of the range of modules (not inclusive)
	* @return the range of matching modules
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.marketplace.model.Module> findByAppId(
		long appId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAppId(appId, start, end);
	}

	/**
	* Returns an ordered range of all the modules where appId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.marketplace.model.impl.ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param appId the app ID
	* @param start the lower bound of the range of modules
	* @param end the upper bound of the range of modules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching modules
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.marketplace.model.Module> findByAppId(
		long appId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAppId(appId, start, end, orderByComparator);
	}

	/**
	* Returns the first module in the ordered set where appId = &#63;.
	*
	* @param appId the app ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching module
	* @throws com.liferay.marketplace.NoSuchModuleException if a matching module could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.Module findByAppId_First(
		long appId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.marketplace.NoSuchModuleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAppId_First(appId, orderByComparator);
	}

	/**
	* Returns the first module in the ordered set where appId = &#63;.
	*
	* @param appId the app ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching module, or <code>null</code> if a matching module could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.Module fetchByAppId_First(
		long appId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByAppId_First(appId, orderByComparator);
	}

	/**
	* Returns the last module in the ordered set where appId = &#63;.
	*
	* @param appId the app ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching module
	* @throws com.liferay.marketplace.NoSuchModuleException if a matching module could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.Module findByAppId_Last(
		long appId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.marketplace.NoSuchModuleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAppId_Last(appId, orderByComparator);
	}

	/**
	* Returns the last module in the ordered set where appId = &#63;.
	*
	* @param appId the app ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching module, or <code>null</code> if a matching module could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.Module fetchByAppId_Last(
		long appId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByAppId_Last(appId, orderByComparator);
	}

	/**
	* Returns the modules before and after the current module in the ordered set where appId = &#63;.
	*
	* @param moduleId the primary key of the current module
	* @param appId the app ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next module
	* @throws com.liferay.marketplace.NoSuchModuleException if a module with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.Module[] findByAppId_PrevAndNext(
		long moduleId, long appId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.marketplace.NoSuchModuleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAppId_PrevAndNext(moduleId, appId, orderByComparator);
	}

	/**
	* Removes all the modules where appId = &#63; from the database.
	*
	* @param appId the app ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByAppId(long appId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByAppId(appId);
	}

	/**
	* Returns the number of modules where appId = &#63;.
	*
	* @param appId the app ID
	* @return the number of matching modules
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAppId(long appId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAppId(appId);
	}

	/**
	* Returns all the modules where bundleSymbolicName = &#63;.
	*
	* @param bundleSymbolicName the bundle symbolic name
	* @return the matching modules
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.marketplace.model.Module> findByBundleSymbolicName(
		java.lang.String bundleSymbolicName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByBundleSymbolicName(bundleSymbolicName);
	}

	/**
	* Returns a range of all the modules where bundleSymbolicName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.marketplace.model.impl.ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param bundleSymbolicName the bundle symbolic name
	* @param start the lower bound of the range of modules
	* @param end the upper bound of the range of modules (not inclusive)
	* @return the range of matching modules
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.marketplace.model.Module> findByBundleSymbolicName(
		java.lang.String bundleSymbolicName, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByBundleSymbolicName(bundleSymbolicName, start, end);
	}

	/**
	* Returns an ordered range of all the modules where bundleSymbolicName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.marketplace.model.impl.ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param bundleSymbolicName the bundle symbolic name
	* @param start the lower bound of the range of modules
	* @param end the upper bound of the range of modules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching modules
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.marketplace.model.Module> findByBundleSymbolicName(
		java.lang.String bundleSymbolicName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByBundleSymbolicName(bundleSymbolicName, start, end,
			orderByComparator);
	}

	/**
	* Returns the first module in the ordered set where bundleSymbolicName = &#63;.
	*
	* @param bundleSymbolicName the bundle symbolic name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching module
	* @throws com.liferay.marketplace.NoSuchModuleException if a matching module could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.Module findByBundleSymbolicName_First(
		java.lang.String bundleSymbolicName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.marketplace.NoSuchModuleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByBundleSymbolicName_First(bundleSymbolicName,
			orderByComparator);
	}

	/**
	* Returns the first module in the ordered set where bundleSymbolicName = &#63;.
	*
	* @param bundleSymbolicName the bundle symbolic name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching module, or <code>null</code> if a matching module could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.Module fetchByBundleSymbolicName_First(
		java.lang.String bundleSymbolicName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByBundleSymbolicName_First(bundleSymbolicName,
			orderByComparator);
	}

	/**
	* Returns the last module in the ordered set where bundleSymbolicName = &#63;.
	*
	* @param bundleSymbolicName the bundle symbolic name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching module
	* @throws com.liferay.marketplace.NoSuchModuleException if a matching module could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.Module findByBundleSymbolicName_Last(
		java.lang.String bundleSymbolicName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.marketplace.NoSuchModuleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByBundleSymbolicName_Last(bundleSymbolicName,
			orderByComparator);
	}

	/**
	* Returns the last module in the ordered set where bundleSymbolicName = &#63;.
	*
	* @param bundleSymbolicName the bundle symbolic name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching module, or <code>null</code> if a matching module could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.Module fetchByBundleSymbolicName_Last(
		java.lang.String bundleSymbolicName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByBundleSymbolicName_Last(bundleSymbolicName,
			orderByComparator);
	}

	/**
	* Returns the modules before and after the current module in the ordered set where bundleSymbolicName = &#63;.
	*
	* @param moduleId the primary key of the current module
	* @param bundleSymbolicName the bundle symbolic name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next module
	* @throws com.liferay.marketplace.NoSuchModuleException if a module with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.Module[] findByBundleSymbolicName_PrevAndNext(
		long moduleId, java.lang.String bundleSymbolicName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.marketplace.NoSuchModuleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByBundleSymbolicName_PrevAndNext(moduleId,
			bundleSymbolicName, orderByComparator);
	}

	/**
	* Removes all the modules where bundleSymbolicName = &#63; from the database.
	*
	* @param bundleSymbolicName the bundle symbolic name
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByBundleSymbolicName(
		java.lang.String bundleSymbolicName)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByBundleSymbolicName(bundleSymbolicName);
	}

	/**
	* Returns the number of modules where bundleSymbolicName = &#63;.
	*
	* @param bundleSymbolicName the bundle symbolic name
	* @return the number of matching modules
	* @throws SystemException if a system exception occurred
	*/
	public static int countByBundleSymbolicName(
		java.lang.String bundleSymbolicName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByBundleSymbolicName(bundleSymbolicName);
	}

	/**
	* Returns all the modules where contextName = &#63;.
	*
	* @param contextName the context name
	* @return the matching modules
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.marketplace.model.Module> findByContextName(
		java.lang.String contextName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByContextName(contextName);
	}

	/**
	* Returns a range of all the modules where contextName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.marketplace.model.impl.ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param contextName the context name
	* @param start the lower bound of the range of modules
	* @param end the upper bound of the range of modules (not inclusive)
	* @return the range of matching modules
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.marketplace.model.Module> findByContextName(
		java.lang.String contextName, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByContextName(contextName, start, end);
	}

	/**
	* Returns an ordered range of all the modules where contextName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.marketplace.model.impl.ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param contextName the context name
	* @param start the lower bound of the range of modules
	* @param end the upper bound of the range of modules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching modules
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.marketplace.model.Module> findByContextName(
		java.lang.String contextName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByContextName(contextName, start, end, orderByComparator);
	}

	/**
	* Returns the first module in the ordered set where contextName = &#63;.
	*
	* @param contextName the context name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching module
	* @throws com.liferay.marketplace.NoSuchModuleException if a matching module could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.Module findByContextName_First(
		java.lang.String contextName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.marketplace.NoSuchModuleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByContextName_First(contextName, orderByComparator);
	}

	/**
	* Returns the first module in the ordered set where contextName = &#63;.
	*
	* @param contextName the context name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching module, or <code>null</code> if a matching module could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.Module fetchByContextName_First(
		java.lang.String contextName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByContextName_First(contextName, orderByComparator);
	}

	/**
	* Returns the last module in the ordered set where contextName = &#63;.
	*
	* @param contextName the context name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching module
	* @throws com.liferay.marketplace.NoSuchModuleException if a matching module could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.Module findByContextName_Last(
		java.lang.String contextName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.marketplace.NoSuchModuleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByContextName_Last(contextName, orderByComparator);
	}

	/**
	* Returns the last module in the ordered set where contextName = &#63;.
	*
	* @param contextName the context name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching module, or <code>null</code> if a matching module could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.Module fetchByContextName_Last(
		java.lang.String contextName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByContextName_Last(contextName, orderByComparator);
	}

	/**
	* Returns the modules before and after the current module in the ordered set where contextName = &#63;.
	*
	* @param moduleId the primary key of the current module
	* @param contextName the context name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next module
	* @throws com.liferay.marketplace.NoSuchModuleException if a module with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.Module[] findByContextName_PrevAndNext(
		long moduleId, java.lang.String contextName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.marketplace.NoSuchModuleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByContextName_PrevAndNext(moduleId, contextName,
			orderByComparator);
	}

	/**
	* Removes all the modules where contextName = &#63; from the database.
	*
	* @param contextName the context name
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByContextName(java.lang.String contextName)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByContextName(contextName);
	}

	/**
	* Returns the number of modules where contextName = &#63;.
	*
	* @param contextName the context name
	* @return the number of matching modules
	* @throws SystemException if a system exception occurred
	*/
	public static int countByContextName(java.lang.String contextName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByContextName(contextName);
	}

	/**
	* Returns the module where appId = &#63; and contextName = &#63; or throws a {@link com.liferay.marketplace.NoSuchModuleException} if it could not be found.
	*
	* @param appId the app ID
	* @param contextName the context name
	* @return the matching module
	* @throws com.liferay.marketplace.NoSuchModuleException if a matching module could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.Module findByA_CN(long appId,
		java.lang.String contextName)
		throws com.liferay.marketplace.NoSuchModuleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByA_CN(appId, contextName);
	}

	/**
	* Returns the module where appId = &#63; and contextName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param appId the app ID
	* @param contextName the context name
	* @return the matching module, or <code>null</code> if a matching module could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.Module fetchByA_CN(long appId,
		java.lang.String contextName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByA_CN(appId, contextName);
	}

	/**
	* Returns the module where appId = &#63; and contextName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param appId the app ID
	* @param contextName the context name
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching module, or <code>null</code> if a matching module could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.Module fetchByA_CN(long appId,
		java.lang.String contextName, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByA_CN(appId, contextName, retrieveFromCache);
	}

	/**
	* Removes the module where appId = &#63; and contextName = &#63; from the database.
	*
	* @param appId the app ID
	* @param contextName the context name
	* @return the module that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.Module removeByA_CN(
		long appId, java.lang.String contextName)
		throws com.liferay.marketplace.NoSuchModuleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByA_CN(appId, contextName);
	}

	/**
	* Returns the number of modules where appId = &#63; and contextName = &#63;.
	*
	* @param appId the app ID
	* @param contextName the context name
	* @return the number of matching modules
	* @throws SystemException if a system exception occurred
	*/
	public static int countByA_CN(long appId, java.lang.String contextName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByA_CN(appId, contextName);
	}

	/**
	* Returns the module where appId = &#63; and bundleSymbolicName = &#63; and bundleVersion = &#63; or throws a {@link com.liferay.marketplace.NoSuchModuleException} if it could not be found.
	*
	* @param appId the app ID
	* @param bundleSymbolicName the bundle symbolic name
	* @param bundleVersion the bundle version
	* @return the matching module
	* @throws com.liferay.marketplace.NoSuchModuleException if a matching module could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.Module findByA_BSN_BV(
		long appId, java.lang.String bundleSymbolicName,
		java.lang.String bundleVersion)
		throws com.liferay.marketplace.NoSuchModuleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByA_BSN_BV(appId, bundleSymbolicName, bundleVersion);
	}

	/**
	* Returns the module where appId = &#63; and bundleSymbolicName = &#63; and bundleVersion = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param appId the app ID
	* @param bundleSymbolicName the bundle symbolic name
	* @param bundleVersion the bundle version
	* @return the matching module, or <code>null</code> if a matching module could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.Module fetchByA_BSN_BV(
		long appId, java.lang.String bundleSymbolicName,
		java.lang.String bundleVersion)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByA_BSN_BV(appId, bundleSymbolicName, bundleVersion);
	}

	/**
	* Returns the module where appId = &#63; and bundleSymbolicName = &#63; and bundleVersion = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param appId the app ID
	* @param bundleSymbolicName the bundle symbolic name
	* @param bundleVersion the bundle version
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching module, or <code>null</code> if a matching module could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.Module fetchByA_BSN_BV(
		long appId, java.lang.String bundleSymbolicName,
		java.lang.String bundleVersion, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByA_BSN_BV(appId, bundleSymbolicName, bundleVersion,
			retrieveFromCache);
	}

	/**
	* Removes the module where appId = &#63; and bundleSymbolicName = &#63; and bundleVersion = &#63; from the database.
	*
	* @param appId the app ID
	* @param bundleSymbolicName the bundle symbolic name
	* @param bundleVersion the bundle version
	* @return the module that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.Module removeByA_BSN_BV(
		long appId, java.lang.String bundleSymbolicName,
		java.lang.String bundleVersion)
		throws com.liferay.marketplace.NoSuchModuleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .removeByA_BSN_BV(appId, bundleSymbolicName, bundleVersion);
	}

	/**
	* Returns the number of modules where appId = &#63; and bundleSymbolicName = &#63; and bundleVersion = &#63;.
	*
	* @param appId the app ID
	* @param bundleSymbolicName the bundle symbolic name
	* @param bundleVersion the bundle version
	* @return the number of matching modules
	* @throws SystemException if a system exception occurred
	*/
	public static int countByA_BSN_BV(long appId,
		java.lang.String bundleSymbolicName, java.lang.String bundleVersion)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByA_BSN_BV(appId, bundleSymbolicName, bundleVersion);
	}

	/**
	* Caches the module in the entity cache if it is enabled.
	*
	* @param module the module
	*/
	public static void cacheResult(com.liferay.marketplace.model.Module module) {
		getPersistence().cacheResult(module);
	}

	/**
	* Caches the modules in the entity cache if it is enabled.
	*
	* @param modules the modules
	*/
	public static void cacheResult(
		java.util.List<com.liferay.marketplace.model.Module> modules) {
		getPersistence().cacheResult(modules);
	}

	/**
	* Creates a new module with the primary key. Does not add the module to the database.
	*
	* @param moduleId the primary key for the new module
	* @return the new module
	*/
	public static com.liferay.marketplace.model.Module create(long moduleId) {
		return getPersistence().create(moduleId);
	}

	/**
	* Removes the module with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param moduleId the primary key of the module
	* @return the module that was removed
	* @throws com.liferay.marketplace.NoSuchModuleException if a module with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.Module remove(long moduleId)
		throws com.liferay.marketplace.NoSuchModuleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(moduleId);
	}

	public static com.liferay.marketplace.model.Module updateImpl(
		com.liferay.marketplace.model.Module module)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(module);
	}

	/**
	* Returns the module with the primary key or throws a {@link com.liferay.marketplace.NoSuchModuleException} if it could not be found.
	*
	* @param moduleId the primary key of the module
	* @return the module
	* @throws com.liferay.marketplace.NoSuchModuleException if a module with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.Module findByPrimaryKey(
		long moduleId)
		throws com.liferay.marketplace.NoSuchModuleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(moduleId);
	}

	/**
	* Returns the module with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param moduleId the primary key of the module
	* @return the module, or <code>null</code> if a module with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.Module fetchByPrimaryKey(
		long moduleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(moduleId);
	}

	/**
	* Returns all the modules.
	*
	* @return the modules
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.marketplace.model.Module> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the modules.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.marketplace.model.impl.ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of modules
	* @param end the upper bound of the range of modules (not inclusive)
	* @return the range of modules
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.marketplace.model.Module> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the modules.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.marketplace.model.impl.ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of modules
	* @param end the upper bound of the range of modules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of modules
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.marketplace.model.Module> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the modules from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of modules.
	*
	* @return the number of modules
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static ModulePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (ModulePersistence)PortletBeanLocatorUtil.locate(com.liferay.marketplace.service.ClpSerializer.getServletContextName(),
					ModulePersistence.class.getName());

			ReferenceRegistry.registerReference(ModuleUtil.class, "_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(ModulePersistence persistence) {
	}

	private static ModulePersistence _persistence;
}