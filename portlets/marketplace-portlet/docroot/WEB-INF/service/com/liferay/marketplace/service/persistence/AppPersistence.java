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

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the app service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ryan Park
 * @see AppPersistenceImpl
 * @see AppUtil
 * @generated
 */
public interface AppPersistence extends BasePersistence<App> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AppUtil} to access the app persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the apps where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching apps
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.marketplace.model.App> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.marketplace.model.App> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.marketplace.model.App> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app
	* @throws com.liferay.marketplace.NoSuchAppException if a matching app could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.marketplace.model.App findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.marketplace.NoSuchAppException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app, or <code>null</code> if a matching app could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.marketplace.model.App fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app
	* @throws com.liferay.marketplace.NoSuchAppException if a matching app could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.marketplace.model.App findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.marketplace.NoSuchAppException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app, or <code>null</code> if a matching app could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.marketplace.model.App fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.marketplace.model.App[] findByUuid_PrevAndNext(
		long appId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.marketplace.NoSuchAppException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the apps where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of apps where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching apps
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the apps where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching apps
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.marketplace.model.App> findByUuid_C(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.marketplace.model.App> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.marketplace.model.App> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.marketplace.model.App findByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.marketplace.NoSuchAppException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app, or <code>null</code> if a matching app could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.marketplace.model.App fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.marketplace.model.App findByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.marketplace.NoSuchAppException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app, or <code>null</code> if a matching app could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.marketplace.model.App fetchByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.marketplace.model.App[] findByUuid_C_PrevAndNext(
		long appId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.marketplace.NoSuchAppException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the apps where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of apps where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching apps
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the apps where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching apps
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.marketplace.model.App> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.marketplace.model.App> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.marketplace.model.App> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app
	* @throws com.liferay.marketplace.NoSuchAppException if a matching app could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.marketplace.model.App findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.marketplace.NoSuchAppException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app, or <code>null</code> if a matching app could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.marketplace.model.App fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app
	* @throws com.liferay.marketplace.NoSuchAppException if a matching app could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.marketplace.model.App findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.marketplace.NoSuchAppException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app, or <code>null</code> if a matching app could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.marketplace.model.App fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.marketplace.model.App[] findByCompanyId_PrevAndNext(
		long appId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.marketplace.NoSuchAppException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the apps where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of apps where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching apps
	* @throws SystemException if a system exception occurred
	*/
	public int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app where remoteAppId = &#63; or throws a {@link com.liferay.marketplace.NoSuchAppException} if it could not be found.
	*
	* @param remoteAppId the remote app ID
	* @return the matching app
	* @throws com.liferay.marketplace.NoSuchAppException if a matching app could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.marketplace.model.App findByRemoteAppId(long remoteAppId)
		throws com.liferay.marketplace.NoSuchAppException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app where remoteAppId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param remoteAppId the remote app ID
	* @return the matching app, or <code>null</code> if a matching app could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.marketplace.model.App fetchByRemoteAppId(
		long remoteAppId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app where remoteAppId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param remoteAppId the remote app ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching app, or <code>null</code> if a matching app could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.marketplace.model.App fetchByRemoteAppId(
		long remoteAppId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the app where remoteAppId = &#63; from the database.
	*
	* @param remoteAppId the remote app ID
	* @return the app that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.marketplace.model.App removeByRemoteAppId(
		long remoteAppId)
		throws com.liferay.marketplace.NoSuchAppException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of apps where remoteAppId = &#63;.
	*
	* @param remoteAppId the remote app ID
	* @return the number of matching apps
	* @throws SystemException if a system exception occurred
	*/
	public int countByRemoteAppId(long remoteAppId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the apps where category = &#63;.
	*
	* @param category the category
	* @return the matching apps
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.marketplace.model.App> findByCategory(
		java.lang.String category)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.marketplace.model.App> findByCategory(
		java.lang.String category, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.marketplace.model.App> findByCategory(
		java.lang.String category, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app in the ordered set where category = &#63;.
	*
	* @param category the category
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app
	* @throws com.liferay.marketplace.NoSuchAppException if a matching app could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.marketplace.model.App findByCategory_First(
		java.lang.String category,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.marketplace.NoSuchAppException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app in the ordered set where category = &#63;.
	*
	* @param category the category
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app, or <code>null</code> if a matching app could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.marketplace.model.App fetchByCategory_First(
		java.lang.String category,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app in the ordered set where category = &#63;.
	*
	* @param category the category
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app
	* @throws com.liferay.marketplace.NoSuchAppException if a matching app could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.marketplace.model.App findByCategory_Last(
		java.lang.String category,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.marketplace.NoSuchAppException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app in the ordered set where category = &#63;.
	*
	* @param category the category
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app, or <code>null</code> if a matching app could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.marketplace.model.App fetchByCategory_Last(
		java.lang.String category,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.marketplace.model.App[] findByCategory_PrevAndNext(
		long appId, java.lang.String category,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.marketplace.NoSuchAppException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the apps where category = &#63; from the database.
	*
	* @param category the category
	* @throws SystemException if a system exception occurred
	*/
	public void removeByCategory(java.lang.String category)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of apps where category = &#63;.
	*
	* @param category the category
	* @return the number of matching apps
	* @throws SystemException if a system exception occurred
	*/
	public int countByCategory(java.lang.String category)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the app in the entity cache if it is enabled.
	*
	* @param app the app
	*/
	public void cacheResult(com.liferay.marketplace.model.App app);

	/**
	* Caches the apps in the entity cache if it is enabled.
	*
	* @param apps the apps
	*/
	public void cacheResult(
		java.util.List<com.liferay.marketplace.model.App> apps);

	/**
	* Creates a new app with the primary key. Does not add the app to the database.
	*
	* @param appId the primary key for the new app
	* @return the new app
	*/
	public com.liferay.marketplace.model.App create(long appId);

	/**
	* Removes the app with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param appId the primary key of the app
	* @return the app that was removed
	* @throws com.liferay.marketplace.NoSuchAppException if a app with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.marketplace.model.App remove(long appId)
		throws com.liferay.marketplace.NoSuchAppException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.marketplace.model.App updateImpl(
		com.liferay.marketplace.model.App app)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app with the primary key or throws a {@link com.liferay.marketplace.NoSuchAppException} if it could not be found.
	*
	* @param appId the primary key of the app
	* @return the app
	* @throws com.liferay.marketplace.NoSuchAppException if a app with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.marketplace.model.App findByPrimaryKey(long appId)
		throws com.liferay.marketplace.NoSuchAppException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param appId the primary key of the app
	* @return the app, or <code>null</code> if a app with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.marketplace.model.App fetchByPrimaryKey(long appId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the apps.
	*
	* @return the apps
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.marketplace.model.App> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.marketplace.model.App> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.marketplace.model.App> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the apps from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of apps.
	*
	* @return the number of apps
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}