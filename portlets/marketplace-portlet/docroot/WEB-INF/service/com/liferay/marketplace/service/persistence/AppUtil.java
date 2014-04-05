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

import com.liferay.marketplace.model.App;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the app service. This utility wraps {@link AppPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ryan Park
 * @see AppPersistence
 * @see AppPersistenceImpl
 * @generated
 */
public class AppUtil {
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
	public static void clearCache(App app) {
		getPersistence().clearCache(app);
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
	public static List<App> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<App> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<App> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static App update(App app) throws SystemException {
		return getPersistence().update(app);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static App update(App app, ServiceContext serviceContext)
		throws SystemException {
		return getPersistence().update(app, serviceContext);
	}

	/**
	* Returns all the apps where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching apps
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.marketplace.model.App> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the apps where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.marketplace.model.impl.AppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of apps
	* @param end the upper bound of the range of apps (not inclusive)
	* @return the range of matching apps
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.marketplace.model.App> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the apps where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.marketplace.model.impl.AppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of apps
	* @param end the upper bound of the range of apps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching apps
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.marketplace.model.App> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the first app in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app
	* @throws com.liferay.marketplace.NoSuchAppException if a matching app could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.App findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.marketplace.NoSuchAppException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first app in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app, or <code>null</code> if a matching app could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.App fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last app in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app
	* @throws com.liferay.marketplace.NoSuchAppException if a matching app could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.App findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.marketplace.NoSuchAppException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last app in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app, or <code>null</code> if a matching app could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.App fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the apps before and after the current app in the ordered set where uuid = &#63;.
	*
	* @param appId the primary key of the current app
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next app
	* @throws com.liferay.marketplace.NoSuchAppException if a app with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.App[] findByUuid_PrevAndNext(
		long appId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.marketplace.NoSuchAppException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_PrevAndNext(appId, uuid, orderByComparator);
	}

	/**
	* Removes all the apps where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of apps where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching apps
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns all the apps where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching apps
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.marketplace.model.App> findByUuid_C(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the apps where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.marketplace.model.impl.AppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of apps
	* @param end the upper bound of the range of apps (not inclusive)
	* @return the range of matching apps
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.marketplace.model.App> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the apps where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.marketplace.model.impl.AppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of apps
	* @param end the upper bound of the range of apps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching apps
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.marketplace.model.App> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first app in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app
	* @throws com.liferay.marketplace.NoSuchAppException if a matching app could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.App findByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.marketplace.NoSuchAppException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first app in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app, or <code>null</code> if a matching app could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.App fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last app in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app
	* @throws com.liferay.marketplace.NoSuchAppException if a matching app could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.App findByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.marketplace.NoSuchAppException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last app in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app, or <code>null</code> if a matching app could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.App fetchByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the apps before and after the current app in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param appId the primary key of the current app
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next app
	* @throws com.liferay.marketplace.NoSuchAppException if a app with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.App[] findByUuid_C_PrevAndNext(
		long appId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.marketplace.NoSuchAppException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(appId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the apps where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of apps where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching apps
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the apps where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching apps
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.marketplace.model.App> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	* Returns a range of all the apps where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.marketplace.model.impl.AppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of apps
	* @param end the upper bound of the range of apps (not inclusive)
	* @return the range of matching apps
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.marketplace.model.App> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the apps where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.marketplace.model.impl.AppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of apps
	* @param end the upper bound of the range of apps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching apps
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.marketplace.model.App> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first app in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app
	* @throws com.liferay.marketplace.NoSuchAppException if a matching app could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.App findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.marketplace.NoSuchAppException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the first app in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app, or <code>null</code> if a matching app could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.App fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last app in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app
	* @throws com.liferay.marketplace.NoSuchAppException if a matching app could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.App findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.marketplace.NoSuchAppException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the last app in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app, or <code>null</code> if a matching app could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.App fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the apps before and after the current app in the ordered set where companyId = &#63;.
	*
	* @param appId the primary key of the current app
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next app
	* @throws com.liferay.marketplace.NoSuchAppException if a app with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.App[] findByCompanyId_PrevAndNext(
		long appId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.marketplace.NoSuchAppException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(appId, companyId,
			orderByComparator);
	}

	/**
	* Removes all the apps where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Returns the number of apps where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching apps
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Returns the app where remoteAppId = &#63; or throws a {@link com.liferay.marketplace.NoSuchAppException} if it could not be found.
	*
	* @param remoteAppId the remote app ID
	* @return the matching app
	* @throws com.liferay.marketplace.NoSuchAppException if a matching app could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.App findByRemoteAppId(
		long remoteAppId)
		throws com.liferay.marketplace.NoSuchAppException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByRemoteAppId(remoteAppId);
	}

	/**
	* Returns the app where remoteAppId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param remoteAppId the remote app ID
	* @return the matching app, or <code>null</code> if a matching app could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.App fetchByRemoteAppId(
		long remoteAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByRemoteAppId(remoteAppId);
	}

	/**
	* Returns the app where remoteAppId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param remoteAppId the remote app ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching app, or <code>null</code> if a matching app could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.App fetchByRemoteAppId(
		long remoteAppId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByRemoteAppId(remoteAppId, retrieveFromCache);
	}

	/**
	* Removes the app where remoteAppId = &#63; from the database.
	*
	* @param remoteAppId the remote app ID
	* @return the app that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.App removeByRemoteAppId(
		long remoteAppId)
		throws com.liferay.marketplace.NoSuchAppException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByRemoteAppId(remoteAppId);
	}

	/**
	* Returns the number of apps where remoteAppId = &#63;.
	*
	* @param remoteAppId the remote app ID
	* @return the number of matching apps
	* @throws SystemException if a system exception occurred
	*/
	public static int countByRemoteAppId(long remoteAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByRemoteAppId(remoteAppId);
	}

	/**
	* Returns all the apps where category = &#63;.
	*
	* @param category the category
	* @return the matching apps
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.marketplace.model.App> findByCategory(
		java.lang.String category)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCategory(category);
	}

	/**
	* Returns a range of all the apps where category = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.marketplace.model.impl.AppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param category the category
	* @param start the lower bound of the range of apps
	* @param end the upper bound of the range of apps (not inclusive)
	* @return the range of matching apps
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.marketplace.model.App> findByCategory(
		java.lang.String category, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCategory(category, start, end);
	}

	/**
	* Returns an ordered range of all the apps where category = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.marketplace.model.impl.AppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param category the category
	* @param start the lower bound of the range of apps
	* @param end the upper bound of the range of apps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching apps
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.marketplace.model.App> findByCategory(
		java.lang.String category, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCategory(category, start, end, orderByComparator);
	}

	/**
	* Returns the first app in the ordered set where category = &#63;.
	*
	* @param category the category
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app
	* @throws com.liferay.marketplace.NoSuchAppException if a matching app could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.App findByCategory_First(
		java.lang.String category,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.marketplace.NoSuchAppException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCategory_First(category, orderByComparator);
	}

	/**
	* Returns the first app in the ordered set where category = &#63;.
	*
	* @param category the category
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app, or <code>null</code> if a matching app could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.App fetchByCategory_First(
		java.lang.String category,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCategory_First(category, orderByComparator);
	}

	/**
	* Returns the last app in the ordered set where category = &#63;.
	*
	* @param category the category
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app
	* @throws com.liferay.marketplace.NoSuchAppException if a matching app could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.App findByCategory_Last(
		java.lang.String category,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.marketplace.NoSuchAppException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCategory_Last(category, orderByComparator);
	}

	/**
	* Returns the last app in the ordered set where category = &#63;.
	*
	* @param category the category
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app, or <code>null</code> if a matching app could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.App fetchByCategory_Last(
		java.lang.String category,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByCategory_Last(category, orderByComparator);
	}

	/**
	* Returns the apps before and after the current app in the ordered set where category = &#63;.
	*
	* @param appId the primary key of the current app
	* @param category the category
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next app
	* @throws com.liferay.marketplace.NoSuchAppException if a app with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.App[] findByCategory_PrevAndNext(
		long appId, java.lang.String category,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.marketplace.NoSuchAppException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCategory_PrevAndNext(appId, category,
			orderByComparator);
	}

	/**
	* Removes all the apps where category = &#63; from the database.
	*
	* @param category the category
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByCategory(java.lang.String category)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCategory(category);
	}

	/**
	* Returns the number of apps where category = &#63;.
	*
	* @param category the category
	* @return the number of matching apps
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCategory(java.lang.String category)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCategory(category);
	}

	/**
	* Caches the app in the entity cache if it is enabled.
	*
	* @param app the app
	*/
	public static void cacheResult(com.liferay.marketplace.model.App app) {
		getPersistence().cacheResult(app);
	}

	/**
	* Caches the apps in the entity cache if it is enabled.
	*
	* @param apps the apps
	*/
	public static void cacheResult(
		java.util.List<com.liferay.marketplace.model.App> apps) {
		getPersistence().cacheResult(apps);
	}

	/**
	* Creates a new app with the primary key. Does not add the app to the database.
	*
	* @param appId the primary key for the new app
	* @return the new app
	*/
	public static com.liferay.marketplace.model.App create(long appId) {
		return getPersistence().create(appId);
	}

	/**
	* Removes the app with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param appId the primary key of the app
	* @return the app that was removed
	* @throws com.liferay.marketplace.NoSuchAppException if a app with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.App remove(long appId)
		throws com.liferay.marketplace.NoSuchAppException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(appId);
	}

	public static com.liferay.marketplace.model.App updateImpl(
		com.liferay.marketplace.model.App app)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(app);
	}

	/**
	* Returns the app with the primary key or throws a {@link com.liferay.marketplace.NoSuchAppException} if it could not be found.
	*
	* @param appId the primary key of the app
	* @return the app
	* @throws com.liferay.marketplace.NoSuchAppException if a app with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.App findByPrimaryKey(long appId)
		throws com.liferay.marketplace.NoSuchAppException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(appId);
	}

	/**
	* Returns the app with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param appId the primary key of the app
	* @return the app, or <code>null</code> if a app with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.App fetchByPrimaryKey(
		long appId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(appId);
	}

	/**
	* Returns all the apps.
	*
	* @return the apps
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.marketplace.model.App> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the apps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.marketplace.model.impl.AppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of apps
	* @param end the upper bound of the range of apps (not inclusive)
	* @return the range of apps
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.marketplace.model.App> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the apps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.marketplace.model.impl.AppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of apps
	* @param end the upper bound of the range of apps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of apps
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.marketplace.model.App> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the apps from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of apps.
	*
	* @return the number of apps
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static AppPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AppPersistence)PortletBeanLocatorUtil.locate(com.liferay.marketplace.service.ClpSerializer.getServletContextName(),
					AppPersistence.class.getName());

			ReferenceRegistry.registerReference(AppUtil.class, "_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(AppPersistence persistence) {
	}

	private static AppPersistence _persistence;
}