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

import aQute.bnd.annotation.ProviderType;

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
 * @see com.liferay.marketplace.service.persistence.impl.AppPersistenceImpl
 * @see AppUtil
 * @generated
 */
@ProviderType
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
	*/
	public java.util.List<App> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the apps where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of apps
	* @param end the upper bound of the range of apps (not inclusive)
	* @return the range of matching apps
	*/
	public java.util.List<App> findByUuid(java.lang.String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the apps where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of apps
	* @param end the upper bound of the range of apps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching apps
	*/
	public java.util.List<App> findByUuid(java.lang.String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<App> orderByComparator);

	/**
	* Returns the first app in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app
	* @throws NoSuchAppException if a matching app could not be found
	*/
	public App findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<App> orderByComparator)
		throws com.liferay.marketplace.NoSuchAppException;

	/**
	* Returns the first app in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app, or <code>null</code> if a matching app could not be found
	*/
	public App fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<App> orderByComparator);

	/**
	* Returns the last app in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app
	* @throws NoSuchAppException if a matching app could not be found
	*/
	public App findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<App> orderByComparator)
		throws com.liferay.marketplace.NoSuchAppException;

	/**
	* Returns the last app in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app, or <code>null</code> if a matching app could not be found
	*/
	public App fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<App> orderByComparator);

	/**
	* Returns the apps before and after the current app in the ordered set where uuid = &#63;.
	*
	* @param appId the primary key of the current app
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next app
	* @throws NoSuchAppException if a app with the primary key could not be found
	*/
	public App[] findByUuid_PrevAndNext(long appId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<App> orderByComparator)
		throws com.liferay.marketplace.NoSuchAppException;

	/**
	* Removes all the apps where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of apps where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching apps
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns all the apps where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching apps
	*/
	public java.util.List<App> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the apps where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of apps
	* @param end the upper bound of the range of apps (not inclusive)
	* @return the range of matching apps
	*/
	public java.util.List<App> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the apps where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of apps
	* @param end the upper bound of the range of apps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching apps
	*/
	public java.util.List<App> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<App> orderByComparator);

	/**
	* Returns the first app in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app
	* @throws NoSuchAppException if a matching app could not be found
	*/
	public App findByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<App> orderByComparator)
		throws com.liferay.marketplace.NoSuchAppException;

	/**
	* Returns the first app in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app, or <code>null</code> if a matching app could not be found
	*/
	public App fetchByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<App> orderByComparator);

	/**
	* Returns the last app in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app
	* @throws NoSuchAppException if a matching app could not be found
	*/
	public App findByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<App> orderByComparator)
		throws com.liferay.marketplace.NoSuchAppException;

	/**
	* Returns the last app in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app, or <code>null</code> if a matching app could not be found
	*/
	public App fetchByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<App> orderByComparator);

	/**
	* Returns the apps before and after the current app in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param appId the primary key of the current app
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next app
	* @throws NoSuchAppException if a app with the primary key could not be found
	*/
	public App[] findByUuid_C_PrevAndNext(long appId, java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<App> orderByComparator)
		throws com.liferay.marketplace.NoSuchAppException;

	/**
	* Removes all the apps where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of apps where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching apps
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the apps where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching apps
	*/
	public java.util.List<App> findByCompanyId(long companyId);

	/**
	* Returns a range of all the apps where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of apps
	* @param end the upper bound of the range of apps (not inclusive)
	* @return the range of matching apps
	*/
	public java.util.List<App> findByCompanyId(long companyId, int start,
		int end);

	/**
	* Returns an ordered range of all the apps where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of apps
	* @param end the upper bound of the range of apps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching apps
	*/
	public java.util.List<App> findByCompanyId(long companyId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<App> orderByComparator);

	/**
	* Returns the first app in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app
	* @throws NoSuchAppException if a matching app could not be found
	*/
	public App findByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<App> orderByComparator)
		throws com.liferay.marketplace.NoSuchAppException;

	/**
	* Returns the first app in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app, or <code>null</code> if a matching app could not be found
	*/
	public App fetchByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<App> orderByComparator);

	/**
	* Returns the last app in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app
	* @throws NoSuchAppException if a matching app could not be found
	*/
	public App findByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<App> orderByComparator)
		throws com.liferay.marketplace.NoSuchAppException;

	/**
	* Returns the last app in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app, or <code>null</code> if a matching app could not be found
	*/
	public App fetchByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<App> orderByComparator);

	/**
	* Returns the apps before and after the current app in the ordered set where companyId = &#63;.
	*
	* @param appId the primary key of the current app
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next app
	* @throws NoSuchAppException if a app with the primary key could not be found
	*/
	public App[] findByCompanyId_PrevAndNext(long appId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<App> orderByComparator)
		throws com.liferay.marketplace.NoSuchAppException;

	/**
	* Removes all the apps where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public void removeByCompanyId(long companyId);

	/**
	* Returns the number of apps where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching apps
	*/
	public int countByCompanyId(long companyId);

	/**
	* Returns the app where remoteAppId = &#63; or throws a {@link NoSuchAppException} if it could not be found.
	*
	* @param remoteAppId the remote app ID
	* @return the matching app
	* @throws NoSuchAppException if a matching app could not be found
	*/
	public App findByRemoteAppId(long remoteAppId)
		throws com.liferay.marketplace.NoSuchAppException;

	/**
	* Returns the app where remoteAppId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param remoteAppId the remote app ID
	* @return the matching app, or <code>null</code> if a matching app could not be found
	*/
	public App fetchByRemoteAppId(long remoteAppId);

	/**
	* Returns the app where remoteAppId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param remoteAppId the remote app ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching app, or <code>null</code> if a matching app could not be found
	*/
	public App fetchByRemoteAppId(long remoteAppId, boolean retrieveFromCache);

	/**
	* Removes the app where remoteAppId = &#63; from the database.
	*
	* @param remoteAppId the remote app ID
	* @return the app that was removed
	*/
	public App removeByRemoteAppId(long remoteAppId)
		throws com.liferay.marketplace.NoSuchAppException;

	/**
	* Returns the number of apps where remoteAppId = &#63;.
	*
	* @param remoteAppId the remote app ID
	* @return the number of matching apps
	*/
	public int countByRemoteAppId(long remoteAppId);

	/**
	* Returns all the apps where category = &#63;.
	*
	* @param category the category
	* @return the matching apps
	*/
	public java.util.List<App> findByCategory(java.lang.String category);

	/**
	* Returns a range of all the apps where category = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param category the category
	* @param start the lower bound of the range of apps
	* @param end the upper bound of the range of apps (not inclusive)
	* @return the range of matching apps
	*/
	public java.util.List<App> findByCategory(java.lang.String category,
		int start, int end);

	/**
	* Returns an ordered range of all the apps where category = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param category the category
	* @param start the lower bound of the range of apps
	* @param end the upper bound of the range of apps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching apps
	*/
	public java.util.List<App> findByCategory(java.lang.String category,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<App> orderByComparator);

	/**
	* Returns the first app in the ordered set where category = &#63;.
	*
	* @param category the category
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app
	* @throws NoSuchAppException if a matching app could not be found
	*/
	public App findByCategory_First(java.lang.String category,
		com.liferay.portal.kernel.util.OrderByComparator<App> orderByComparator)
		throws com.liferay.marketplace.NoSuchAppException;

	/**
	* Returns the first app in the ordered set where category = &#63;.
	*
	* @param category the category
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app, or <code>null</code> if a matching app could not be found
	*/
	public App fetchByCategory_First(java.lang.String category,
		com.liferay.portal.kernel.util.OrderByComparator<App> orderByComparator);

	/**
	* Returns the last app in the ordered set where category = &#63;.
	*
	* @param category the category
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app
	* @throws NoSuchAppException if a matching app could not be found
	*/
	public App findByCategory_Last(java.lang.String category,
		com.liferay.portal.kernel.util.OrderByComparator<App> orderByComparator)
		throws com.liferay.marketplace.NoSuchAppException;

	/**
	* Returns the last app in the ordered set where category = &#63;.
	*
	* @param category the category
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app, or <code>null</code> if a matching app could not be found
	*/
	public App fetchByCategory_Last(java.lang.String category,
		com.liferay.portal.kernel.util.OrderByComparator<App> orderByComparator);

	/**
	* Returns the apps before and after the current app in the ordered set where category = &#63;.
	*
	* @param appId the primary key of the current app
	* @param category the category
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next app
	* @throws NoSuchAppException if a app with the primary key could not be found
	*/
	public App[] findByCategory_PrevAndNext(long appId,
		java.lang.String category,
		com.liferay.portal.kernel.util.OrderByComparator<App> orderByComparator)
		throws com.liferay.marketplace.NoSuchAppException;

	/**
	* Removes all the apps where category = &#63; from the database.
	*
	* @param category the category
	*/
	public void removeByCategory(java.lang.String category);

	/**
	* Returns the number of apps where category = &#63;.
	*
	* @param category the category
	* @return the number of matching apps
	*/
	public int countByCategory(java.lang.String category);

	/**
	* Caches the app in the entity cache if it is enabled.
	*
	* @param app the app
	*/
	public void cacheResult(App app);

	/**
	* Caches the apps in the entity cache if it is enabled.
	*
	* @param apps the apps
	*/
	public void cacheResult(java.util.List<App> apps);

	/**
	* Creates a new app with the primary key. Does not add the app to the database.
	*
	* @param appId the primary key for the new app
	* @return the new app
	*/
	public App create(long appId);

	/**
	* Removes the app with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param appId the primary key of the app
	* @return the app that was removed
	* @throws NoSuchAppException if a app with the primary key could not be found
	*/
	public App remove(long appId)
		throws com.liferay.marketplace.NoSuchAppException;

	public App updateImpl(App app);

	/**
	* Returns the app with the primary key or throws a {@link NoSuchAppException} if it could not be found.
	*
	* @param appId the primary key of the app
	* @return the app
	* @throws NoSuchAppException if a app with the primary key could not be found
	*/
	public App findByPrimaryKey(long appId)
		throws com.liferay.marketplace.NoSuchAppException;

	/**
	* Returns the app with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param appId the primary key of the app
	* @return the app, or <code>null</code> if a app with the primary key could not be found
	*/
	public App fetchByPrimaryKey(long appId);

	@Override
	public java.util.Map<java.io.Serializable, App> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the apps.
	*
	* @return the apps
	*/
	public java.util.List<App> findAll();

	/**
	* Returns a range of all the apps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of apps
	* @param end the upper bound of the range of apps (not inclusive)
	* @return the range of apps
	*/
	public java.util.List<App> findAll(int start, int end);

	/**
	* Returns an ordered range of all the apps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of apps
	* @param end the upper bound of the range of apps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of apps
	*/
	public java.util.List<App> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<App> orderByComparator);

	/**
	* Removes all the apps from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of apps.
	*
	* @return the number of apps
	*/
	public int countAll();
}