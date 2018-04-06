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

package com.liferay.meeting.webex.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.meeting.webex.model.WebExSite;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the web ex site service. This utility wraps {@link com.liferay.meeting.webex.service.persistence.impl.WebExSitePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Anant Singh
 * @see WebExSitePersistence
 * @see com.liferay.meeting.webex.service.persistence.impl.WebExSitePersistenceImpl
 * @generated
 */
@ProviderType
public class WebExSiteUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(WebExSite webExSite) {
		getPersistence().clearCache(webExSite);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<WebExSite> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<WebExSite> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<WebExSite> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<WebExSite> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static WebExSite update(WebExSite webExSite) {
		return getPersistence().update(webExSite);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static WebExSite update(WebExSite webExSite,
		ServiceContext serviceContext) {
		return getPersistence().update(webExSite, serviceContext);
	}

	/**
	* Returns all the web ex sites where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching web ex sites
	*/
	public static List<WebExSite> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the web ex sites where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of web ex sites
	* @param end the upper bound of the range of web ex sites (not inclusive)
	* @return the range of matching web ex sites
	*/
	public static List<WebExSite> findByUuid(java.lang.String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the web ex sites where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of web ex sites
	* @param end the upper bound of the range of web ex sites (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching web ex sites
	*/
	public static List<WebExSite> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<WebExSite> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the web ex sites where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of web ex sites
	* @param end the upper bound of the range of web ex sites (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching web ex sites
	*/
	public static List<WebExSite> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<WebExSite> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first web ex site in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching web ex site
	* @throws NoSuchSiteException if a matching web ex site could not be found
	*/
	public static WebExSite findByUuid_First(java.lang.String uuid,
		OrderByComparator<WebExSite> orderByComparator)
		throws com.liferay.meeting.webex.exception.NoSuchSiteException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first web ex site in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching web ex site, or <code>null</code> if a matching web ex site could not be found
	*/
	public static WebExSite fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<WebExSite> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last web ex site in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching web ex site
	* @throws NoSuchSiteException if a matching web ex site could not be found
	*/
	public static WebExSite findByUuid_Last(java.lang.String uuid,
		OrderByComparator<WebExSite> orderByComparator)
		throws com.liferay.meeting.webex.exception.NoSuchSiteException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last web ex site in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching web ex site, or <code>null</code> if a matching web ex site could not be found
	*/
	public static WebExSite fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<WebExSite> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the web ex sites before and after the current web ex site in the ordered set where uuid = &#63;.
	*
	* @param webExSiteId the primary key of the current web ex site
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next web ex site
	* @throws NoSuchSiteException if a web ex site with the primary key could not be found
	*/
	public static WebExSite[] findByUuid_PrevAndNext(long webExSiteId,
		java.lang.String uuid, OrderByComparator<WebExSite> orderByComparator)
		throws com.liferay.meeting.webex.exception.NoSuchSiteException {
		return getPersistence()
				   .findByUuid_PrevAndNext(webExSiteId, uuid, orderByComparator);
	}

	/**
	* Removes all the web ex sites where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of web ex sites where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching web ex sites
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the web ex site where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchSiteException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching web ex site
	* @throws NoSuchSiteException if a matching web ex site could not be found
	*/
	public static WebExSite findByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.meeting.webex.exception.NoSuchSiteException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the web ex site where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching web ex site, or <code>null</code> if a matching web ex site could not be found
	*/
	public static WebExSite fetchByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the web ex site where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching web ex site, or <code>null</code> if a matching web ex site could not be found
	*/
	public static WebExSite fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the web ex site where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the web ex site that was removed
	*/
	public static WebExSite removeByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.meeting.webex.exception.NoSuchSiteException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of web ex sites where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching web ex sites
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the web ex sites where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching web ex sites
	*/
	public static List<WebExSite> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the web ex sites where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of web ex sites
	* @param end the upper bound of the range of web ex sites (not inclusive)
	* @return the range of matching web ex sites
	*/
	public static List<WebExSite> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the web ex sites where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of web ex sites
	* @param end the upper bound of the range of web ex sites (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching web ex sites
	*/
	public static List<WebExSite> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<WebExSite> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the web ex sites where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of web ex sites
	* @param end the upper bound of the range of web ex sites (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching web ex sites
	*/
	public static List<WebExSite> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<WebExSite> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first web ex site in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching web ex site
	* @throws NoSuchSiteException if a matching web ex site could not be found
	*/
	public static WebExSite findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<WebExSite> orderByComparator)
		throws com.liferay.meeting.webex.exception.NoSuchSiteException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first web ex site in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching web ex site, or <code>null</code> if a matching web ex site could not be found
	*/
	public static WebExSite fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<WebExSite> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last web ex site in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching web ex site
	* @throws NoSuchSiteException if a matching web ex site could not be found
	*/
	public static WebExSite findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<WebExSite> orderByComparator)
		throws com.liferay.meeting.webex.exception.NoSuchSiteException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last web ex site in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching web ex site, or <code>null</code> if a matching web ex site could not be found
	*/
	public static WebExSite fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<WebExSite> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the web ex sites before and after the current web ex site in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param webExSiteId the primary key of the current web ex site
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next web ex site
	* @throws NoSuchSiteException if a web ex site with the primary key could not be found
	*/
	public static WebExSite[] findByUuid_C_PrevAndNext(long webExSiteId,
		java.lang.String uuid, long companyId,
		OrderByComparator<WebExSite> orderByComparator)
		throws com.liferay.meeting.webex.exception.NoSuchSiteException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(webExSiteId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the web ex sites where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of web ex sites where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching web ex sites
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the web ex sites where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching web ex sites
	*/
	public static List<WebExSite> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the web ex sites where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of web ex sites
	* @param end the upper bound of the range of web ex sites (not inclusive)
	* @return the range of matching web ex sites
	*/
	public static List<WebExSite> findByGroupId(long groupId, int start, int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the web ex sites where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of web ex sites
	* @param end the upper bound of the range of web ex sites (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching web ex sites
	*/
	public static List<WebExSite> findByGroupId(long groupId, int start,
		int end, OrderByComparator<WebExSite> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the web ex sites where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of web ex sites
	* @param end the upper bound of the range of web ex sites (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching web ex sites
	*/
	public static List<WebExSite> findByGroupId(long groupId, int start,
		int end, OrderByComparator<WebExSite> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first web ex site in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching web ex site
	* @throws NoSuchSiteException if a matching web ex site could not be found
	*/
	public static WebExSite findByGroupId_First(long groupId,
		OrderByComparator<WebExSite> orderByComparator)
		throws com.liferay.meeting.webex.exception.NoSuchSiteException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first web ex site in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching web ex site, or <code>null</code> if a matching web ex site could not be found
	*/
	public static WebExSite fetchByGroupId_First(long groupId,
		OrderByComparator<WebExSite> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last web ex site in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching web ex site
	* @throws NoSuchSiteException if a matching web ex site could not be found
	*/
	public static WebExSite findByGroupId_Last(long groupId,
		OrderByComparator<WebExSite> orderByComparator)
		throws com.liferay.meeting.webex.exception.NoSuchSiteException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last web ex site in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching web ex site, or <code>null</code> if a matching web ex site could not be found
	*/
	public static WebExSite fetchByGroupId_Last(long groupId,
		OrderByComparator<WebExSite> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the web ex sites before and after the current web ex site in the ordered set where groupId = &#63;.
	*
	* @param webExSiteId the primary key of the current web ex site
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next web ex site
	* @throws NoSuchSiteException if a web ex site with the primary key could not be found
	*/
	public static WebExSite[] findByGroupId_PrevAndNext(long webExSiteId,
		long groupId, OrderByComparator<WebExSite> orderByComparator)
		throws com.liferay.meeting.webex.exception.NoSuchSiteException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(webExSiteId, groupId,
			orderByComparator);
	}

	/**
	* Returns all the web ex sites that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching web ex sites that the user has permission to view
	*/
	public static List<WebExSite> filterFindByGroupId(long groupId) {
		return getPersistence().filterFindByGroupId(groupId);
	}

	/**
	* Returns a range of all the web ex sites that the user has permission to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of web ex sites
	* @param end the upper bound of the range of web ex sites (not inclusive)
	* @return the range of matching web ex sites that the user has permission to view
	*/
	public static List<WebExSite> filterFindByGroupId(long groupId, int start,
		int end) {
		return getPersistence().filterFindByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the web ex sites that the user has permissions to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of web ex sites
	* @param end the upper bound of the range of web ex sites (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching web ex sites that the user has permission to view
	*/
	public static List<WebExSite> filterFindByGroupId(long groupId, int start,
		int end, OrderByComparator<WebExSite> orderByComparator) {
		return getPersistence()
				   .filterFindByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns the web ex sites before and after the current web ex site in the ordered set of web ex sites that the user has permission to view where groupId = &#63;.
	*
	* @param webExSiteId the primary key of the current web ex site
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next web ex site
	* @throws NoSuchSiteException if a web ex site with the primary key could not be found
	*/
	public static WebExSite[] filterFindByGroupId_PrevAndNext(
		long webExSiteId, long groupId,
		OrderByComparator<WebExSite> orderByComparator)
		throws com.liferay.meeting.webex.exception.NoSuchSiteException {
		return getPersistence()
				   .filterFindByGroupId_PrevAndNext(webExSiteId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the web ex sites where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of web ex sites where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching web ex sites
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns the number of web ex sites that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching web ex sites that the user has permission to view
	*/
	public static int filterCountByGroupId(long groupId) {
		return getPersistence().filterCountByGroupId(groupId);
	}

	/**
	* Returns the web ex site where siteKey = &#63; or throws a {@link NoSuchSiteException} if it could not be found.
	*
	* @param siteKey the site key
	* @return the matching web ex site
	* @throws NoSuchSiteException if a matching web ex site could not be found
	*/
	public static WebExSite findBySiteKey(long siteKey)
		throws com.liferay.meeting.webex.exception.NoSuchSiteException {
		return getPersistence().findBySiteKey(siteKey);
	}

	/**
	* Returns the web ex site where siteKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param siteKey the site key
	* @return the matching web ex site, or <code>null</code> if a matching web ex site could not be found
	*/
	public static WebExSite fetchBySiteKey(long siteKey) {
		return getPersistence().fetchBySiteKey(siteKey);
	}

	/**
	* Returns the web ex site where siteKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param siteKey the site key
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching web ex site, or <code>null</code> if a matching web ex site could not be found
	*/
	public static WebExSite fetchBySiteKey(long siteKey,
		boolean retrieveFromCache) {
		return getPersistence().fetchBySiteKey(siteKey, retrieveFromCache);
	}

	/**
	* Removes the web ex site where siteKey = &#63; from the database.
	*
	* @param siteKey the site key
	* @return the web ex site that was removed
	*/
	public static WebExSite removeBySiteKey(long siteKey)
		throws com.liferay.meeting.webex.exception.NoSuchSiteException {
		return getPersistence().removeBySiteKey(siteKey);
	}

	/**
	* Returns the number of web ex sites where siteKey = &#63;.
	*
	* @param siteKey the site key
	* @return the number of matching web ex sites
	*/
	public static int countBySiteKey(long siteKey) {
		return getPersistence().countBySiteKey(siteKey);
	}

	/**
	* Caches the web ex site in the entity cache if it is enabled.
	*
	* @param webExSite the web ex site
	*/
	public static void cacheResult(WebExSite webExSite) {
		getPersistence().cacheResult(webExSite);
	}

	/**
	* Caches the web ex sites in the entity cache if it is enabled.
	*
	* @param webExSites the web ex sites
	*/
	public static void cacheResult(List<WebExSite> webExSites) {
		getPersistence().cacheResult(webExSites);
	}

	/**
	* Creates a new web ex site with the primary key. Does not add the web ex site to the database.
	*
	* @param webExSiteId the primary key for the new web ex site
	* @return the new web ex site
	*/
	public static WebExSite create(long webExSiteId) {
		return getPersistence().create(webExSiteId);
	}

	/**
	* Removes the web ex site with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param webExSiteId the primary key of the web ex site
	* @return the web ex site that was removed
	* @throws NoSuchSiteException if a web ex site with the primary key could not be found
	*/
	public static WebExSite remove(long webExSiteId)
		throws com.liferay.meeting.webex.exception.NoSuchSiteException {
		return getPersistence().remove(webExSiteId);
	}

	public static WebExSite updateImpl(WebExSite webExSite) {
		return getPersistence().updateImpl(webExSite);
	}

	/**
	* Returns the web ex site with the primary key or throws a {@link NoSuchSiteException} if it could not be found.
	*
	* @param webExSiteId the primary key of the web ex site
	* @return the web ex site
	* @throws NoSuchSiteException if a web ex site with the primary key could not be found
	*/
	public static WebExSite findByPrimaryKey(long webExSiteId)
		throws com.liferay.meeting.webex.exception.NoSuchSiteException {
		return getPersistence().findByPrimaryKey(webExSiteId);
	}

	/**
	* Returns the web ex site with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param webExSiteId the primary key of the web ex site
	* @return the web ex site, or <code>null</code> if a web ex site with the primary key could not be found
	*/
	public static WebExSite fetchByPrimaryKey(long webExSiteId) {
		return getPersistence().fetchByPrimaryKey(webExSiteId);
	}

	public static java.util.Map<java.io.Serializable, WebExSite> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the web ex sites.
	*
	* @return the web ex sites
	*/
	public static List<WebExSite> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the web ex sites.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of web ex sites
	* @param end the upper bound of the range of web ex sites (not inclusive)
	* @return the range of web ex sites
	*/
	public static List<WebExSite> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the web ex sites.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of web ex sites
	* @param end the upper bound of the range of web ex sites (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of web ex sites
	*/
	public static List<WebExSite> findAll(int start, int end,
		OrderByComparator<WebExSite> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the web ex sites.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of web ex sites
	* @param end the upper bound of the range of web ex sites (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of web ex sites
	*/
	public static List<WebExSite> findAll(int start, int end,
		OrderByComparator<WebExSite> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the web ex sites from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of web ex sites.
	*
	* @return the number of web ex sites
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static WebExSitePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (WebExSitePersistence)PortletBeanLocatorUtil.locate(com.liferay.meeting.webex.service.ClpSerializer.getServletContextName(),
					WebExSitePersistence.class.getName());

			ReferenceRegistry.registerReference(WebExSiteUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	private static WebExSitePersistence _persistence;
}