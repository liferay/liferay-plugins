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

import com.liferay.meeting.webex.model.WebExAccount;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the web ex account service. This utility wraps {@link com.liferay.meeting.webex.service.persistence.impl.WebExAccountPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Anant Singh
 * @see WebExAccountPersistence
 * @see com.liferay.meeting.webex.service.persistence.impl.WebExAccountPersistenceImpl
 * @generated
 */
@ProviderType
public class WebExAccountUtil {
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
	public static void clearCache(WebExAccount webExAccount) {
		getPersistence().clearCache(webExAccount);
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
	public static List<WebExAccount> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<WebExAccount> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<WebExAccount> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<WebExAccount> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static WebExAccount update(WebExAccount webExAccount) {
		return getPersistence().update(webExAccount);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static WebExAccount update(WebExAccount webExAccount,
		ServiceContext serviceContext) {
		return getPersistence().update(webExAccount, serviceContext);
	}

	/**
	* Returns all the web ex accounts where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching web ex accounts
	*/
	public static List<WebExAccount> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the web ex accounts where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of web ex accounts
	* @param end the upper bound of the range of web ex accounts (not inclusive)
	* @return the range of matching web ex accounts
	*/
	public static List<WebExAccount> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the web ex accounts where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of web ex accounts
	* @param end the upper bound of the range of web ex accounts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching web ex accounts
	*/
	public static List<WebExAccount> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<WebExAccount> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the web ex accounts where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of web ex accounts
	* @param end the upper bound of the range of web ex accounts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching web ex accounts
	*/
	public static List<WebExAccount> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<WebExAccount> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first web ex account in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching web ex account
	* @throws NoSuchAccountException if a matching web ex account could not be found
	*/
	public static WebExAccount findByUuid_First(java.lang.String uuid,
		OrderByComparator<WebExAccount> orderByComparator)
		throws com.liferay.meeting.webex.exception.NoSuchAccountException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first web ex account in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching web ex account, or <code>null</code> if a matching web ex account could not be found
	*/
	public static WebExAccount fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<WebExAccount> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last web ex account in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching web ex account
	* @throws NoSuchAccountException if a matching web ex account could not be found
	*/
	public static WebExAccount findByUuid_Last(java.lang.String uuid,
		OrderByComparator<WebExAccount> orderByComparator)
		throws com.liferay.meeting.webex.exception.NoSuchAccountException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last web ex account in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching web ex account, or <code>null</code> if a matching web ex account could not be found
	*/
	public static WebExAccount fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<WebExAccount> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the web ex accounts before and after the current web ex account in the ordered set where uuid = &#63;.
	*
	* @param webExAccountId the primary key of the current web ex account
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next web ex account
	* @throws NoSuchAccountException if a web ex account with the primary key could not be found
	*/
	public static WebExAccount[] findByUuid_PrevAndNext(long webExAccountId,
		java.lang.String uuid, OrderByComparator<WebExAccount> orderByComparator)
		throws com.liferay.meeting.webex.exception.NoSuchAccountException {
		return getPersistence()
				   .findByUuid_PrevAndNext(webExAccountId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the web ex accounts where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of web ex accounts where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching web ex accounts
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the web ex account where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchAccountException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching web ex account
	* @throws NoSuchAccountException if a matching web ex account could not be found
	*/
	public static WebExAccount findByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.meeting.webex.exception.NoSuchAccountException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the web ex account where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching web ex account, or <code>null</code> if a matching web ex account could not be found
	*/
	public static WebExAccount fetchByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the web ex account where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching web ex account, or <code>null</code> if a matching web ex account could not be found
	*/
	public static WebExAccount fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the web ex account where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the web ex account that was removed
	*/
	public static WebExAccount removeByUUID_G(java.lang.String uuid,
		long groupId)
		throws com.liferay.meeting.webex.exception.NoSuchAccountException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of web ex accounts where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching web ex accounts
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the web ex accounts where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching web ex accounts
	*/
	public static List<WebExAccount> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the web ex accounts where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of web ex accounts
	* @param end the upper bound of the range of web ex accounts (not inclusive)
	* @return the range of matching web ex accounts
	*/
	public static List<WebExAccount> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the web ex accounts where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of web ex accounts
	* @param end the upper bound of the range of web ex accounts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching web ex accounts
	*/
	public static List<WebExAccount> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<WebExAccount> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the web ex accounts where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of web ex accounts
	* @param end the upper bound of the range of web ex accounts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching web ex accounts
	*/
	public static List<WebExAccount> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<WebExAccount> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first web ex account in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching web ex account
	* @throws NoSuchAccountException if a matching web ex account could not be found
	*/
	public static WebExAccount findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<WebExAccount> orderByComparator)
		throws com.liferay.meeting.webex.exception.NoSuchAccountException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first web ex account in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching web ex account, or <code>null</code> if a matching web ex account could not be found
	*/
	public static WebExAccount fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<WebExAccount> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last web ex account in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching web ex account
	* @throws NoSuchAccountException if a matching web ex account could not be found
	*/
	public static WebExAccount findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<WebExAccount> orderByComparator)
		throws com.liferay.meeting.webex.exception.NoSuchAccountException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last web ex account in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching web ex account, or <code>null</code> if a matching web ex account could not be found
	*/
	public static WebExAccount fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<WebExAccount> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the web ex accounts before and after the current web ex account in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param webExAccountId the primary key of the current web ex account
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next web ex account
	* @throws NoSuchAccountException if a web ex account with the primary key could not be found
	*/
	public static WebExAccount[] findByUuid_C_PrevAndNext(long webExAccountId,
		java.lang.String uuid, long companyId,
		OrderByComparator<WebExAccount> orderByComparator)
		throws com.liferay.meeting.webex.exception.NoSuchAccountException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(webExAccountId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the web ex accounts where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of web ex accounts where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching web ex accounts
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the web ex accounts where groupId = &#63; and webExSiteId = &#63;.
	*
	* @param groupId the group ID
	* @param webExSiteId the web ex site ID
	* @return the matching web ex accounts
	*/
	public static List<WebExAccount> findByG_W(long groupId, long webExSiteId) {
		return getPersistence().findByG_W(groupId, webExSiteId);
	}

	/**
	* Returns a range of all the web ex accounts where groupId = &#63; and webExSiteId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param webExSiteId the web ex site ID
	* @param start the lower bound of the range of web ex accounts
	* @param end the upper bound of the range of web ex accounts (not inclusive)
	* @return the range of matching web ex accounts
	*/
	public static List<WebExAccount> findByG_W(long groupId, long webExSiteId,
		int start, int end) {
		return getPersistence().findByG_W(groupId, webExSiteId, start, end);
	}

	/**
	* Returns an ordered range of all the web ex accounts where groupId = &#63; and webExSiteId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param webExSiteId the web ex site ID
	* @param start the lower bound of the range of web ex accounts
	* @param end the upper bound of the range of web ex accounts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching web ex accounts
	*/
	public static List<WebExAccount> findByG_W(long groupId, long webExSiteId,
		int start, int end, OrderByComparator<WebExAccount> orderByComparator) {
		return getPersistence()
				   .findByG_W(groupId, webExSiteId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the web ex accounts where groupId = &#63; and webExSiteId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param webExSiteId the web ex site ID
	* @param start the lower bound of the range of web ex accounts
	* @param end the upper bound of the range of web ex accounts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching web ex accounts
	*/
	public static List<WebExAccount> findByG_W(long groupId, long webExSiteId,
		int start, int end, OrderByComparator<WebExAccount> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_W(groupId, webExSiteId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first web ex account in the ordered set where groupId = &#63; and webExSiteId = &#63;.
	*
	* @param groupId the group ID
	* @param webExSiteId the web ex site ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching web ex account
	* @throws NoSuchAccountException if a matching web ex account could not be found
	*/
	public static WebExAccount findByG_W_First(long groupId, long webExSiteId,
		OrderByComparator<WebExAccount> orderByComparator)
		throws com.liferay.meeting.webex.exception.NoSuchAccountException {
		return getPersistence()
				   .findByG_W_First(groupId, webExSiteId, orderByComparator);
	}

	/**
	* Returns the first web ex account in the ordered set where groupId = &#63; and webExSiteId = &#63;.
	*
	* @param groupId the group ID
	* @param webExSiteId the web ex site ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching web ex account, or <code>null</code> if a matching web ex account could not be found
	*/
	public static WebExAccount fetchByG_W_First(long groupId, long webExSiteId,
		OrderByComparator<WebExAccount> orderByComparator) {
		return getPersistence()
				   .fetchByG_W_First(groupId, webExSiteId, orderByComparator);
	}

	/**
	* Returns the last web ex account in the ordered set where groupId = &#63; and webExSiteId = &#63;.
	*
	* @param groupId the group ID
	* @param webExSiteId the web ex site ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching web ex account
	* @throws NoSuchAccountException if a matching web ex account could not be found
	*/
	public static WebExAccount findByG_W_Last(long groupId, long webExSiteId,
		OrderByComparator<WebExAccount> orderByComparator)
		throws com.liferay.meeting.webex.exception.NoSuchAccountException {
		return getPersistence()
				   .findByG_W_Last(groupId, webExSiteId, orderByComparator);
	}

	/**
	* Returns the last web ex account in the ordered set where groupId = &#63; and webExSiteId = &#63;.
	*
	* @param groupId the group ID
	* @param webExSiteId the web ex site ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching web ex account, or <code>null</code> if a matching web ex account could not be found
	*/
	public static WebExAccount fetchByG_W_Last(long groupId, long webExSiteId,
		OrderByComparator<WebExAccount> orderByComparator) {
		return getPersistence()
				   .fetchByG_W_Last(groupId, webExSiteId, orderByComparator);
	}

	/**
	* Returns the web ex accounts before and after the current web ex account in the ordered set where groupId = &#63; and webExSiteId = &#63;.
	*
	* @param webExAccountId the primary key of the current web ex account
	* @param groupId the group ID
	* @param webExSiteId the web ex site ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next web ex account
	* @throws NoSuchAccountException if a web ex account with the primary key could not be found
	*/
	public static WebExAccount[] findByG_W_PrevAndNext(long webExAccountId,
		long groupId, long webExSiteId,
		OrderByComparator<WebExAccount> orderByComparator)
		throws com.liferay.meeting.webex.exception.NoSuchAccountException {
		return getPersistence()
				   .findByG_W_PrevAndNext(webExAccountId, groupId, webExSiteId,
			orderByComparator);
	}

	/**
	* Returns all the web ex accounts that the user has permission to view where groupId = &#63; and webExSiteId = &#63;.
	*
	* @param groupId the group ID
	* @param webExSiteId the web ex site ID
	* @return the matching web ex accounts that the user has permission to view
	*/
	public static List<WebExAccount> filterFindByG_W(long groupId,
		long webExSiteId) {
		return getPersistence().filterFindByG_W(groupId, webExSiteId);
	}

	/**
	* Returns a range of all the web ex accounts that the user has permission to view where groupId = &#63; and webExSiteId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param webExSiteId the web ex site ID
	* @param start the lower bound of the range of web ex accounts
	* @param end the upper bound of the range of web ex accounts (not inclusive)
	* @return the range of matching web ex accounts that the user has permission to view
	*/
	public static List<WebExAccount> filterFindByG_W(long groupId,
		long webExSiteId, int start, int end) {
		return getPersistence().filterFindByG_W(groupId, webExSiteId, start, end);
	}

	/**
	* Returns an ordered range of all the web ex accounts that the user has permissions to view where groupId = &#63; and webExSiteId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param webExSiteId the web ex site ID
	* @param start the lower bound of the range of web ex accounts
	* @param end the upper bound of the range of web ex accounts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching web ex accounts that the user has permission to view
	*/
	public static List<WebExAccount> filterFindByG_W(long groupId,
		long webExSiteId, int start, int end,
		OrderByComparator<WebExAccount> orderByComparator) {
		return getPersistence()
				   .filterFindByG_W(groupId, webExSiteId, start, end,
			orderByComparator);
	}

	/**
	* Returns the web ex accounts before and after the current web ex account in the ordered set of web ex accounts that the user has permission to view where groupId = &#63; and webExSiteId = &#63;.
	*
	* @param webExAccountId the primary key of the current web ex account
	* @param groupId the group ID
	* @param webExSiteId the web ex site ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next web ex account
	* @throws NoSuchAccountException if a web ex account with the primary key could not be found
	*/
	public static WebExAccount[] filterFindByG_W_PrevAndNext(
		long webExAccountId, long groupId, long webExSiteId,
		OrderByComparator<WebExAccount> orderByComparator)
		throws com.liferay.meeting.webex.exception.NoSuchAccountException {
		return getPersistence()
				   .filterFindByG_W_PrevAndNext(webExAccountId, groupId,
			webExSiteId, orderByComparator);
	}

	/**
	* Removes all the web ex accounts where groupId = &#63; and webExSiteId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param webExSiteId the web ex site ID
	*/
	public static void removeByG_W(long groupId, long webExSiteId) {
		getPersistence().removeByG_W(groupId, webExSiteId);
	}

	/**
	* Returns the number of web ex accounts where groupId = &#63; and webExSiteId = &#63;.
	*
	* @param groupId the group ID
	* @param webExSiteId the web ex site ID
	* @return the number of matching web ex accounts
	*/
	public static int countByG_W(long groupId, long webExSiteId) {
		return getPersistence().countByG_W(groupId, webExSiteId);
	}

	/**
	* Returns the number of web ex accounts that the user has permission to view where groupId = &#63; and webExSiteId = &#63;.
	*
	* @param groupId the group ID
	* @param webExSiteId the web ex site ID
	* @return the number of matching web ex accounts that the user has permission to view
	*/
	public static int filterCountByG_W(long groupId, long webExSiteId) {
		return getPersistence().filterCountByG_W(groupId, webExSiteId);
	}

	/**
	* Caches the web ex account in the entity cache if it is enabled.
	*
	* @param webExAccount the web ex account
	*/
	public static void cacheResult(WebExAccount webExAccount) {
		getPersistence().cacheResult(webExAccount);
	}

	/**
	* Caches the web ex accounts in the entity cache if it is enabled.
	*
	* @param webExAccounts the web ex accounts
	*/
	public static void cacheResult(List<WebExAccount> webExAccounts) {
		getPersistence().cacheResult(webExAccounts);
	}

	/**
	* Creates a new web ex account with the primary key. Does not add the web ex account to the database.
	*
	* @param webExAccountId the primary key for the new web ex account
	* @return the new web ex account
	*/
	public static WebExAccount create(long webExAccountId) {
		return getPersistence().create(webExAccountId);
	}

	/**
	* Removes the web ex account with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param webExAccountId the primary key of the web ex account
	* @return the web ex account that was removed
	* @throws NoSuchAccountException if a web ex account with the primary key could not be found
	*/
	public static WebExAccount remove(long webExAccountId)
		throws com.liferay.meeting.webex.exception.NoSuchAccountException {
		return getPersistence().remove(webExAccountId);
	}

	public static WebExAccount updateImpl(WebExAccount webExAccount) {
		return getPersistence().updateImpl(webExAccount);
	}

	/**
	* Returns the web ex account with the primary key or throws a {@link NoSuchAccountException} if it could not be found.
	*
	* @param webExAccountId the primary key of the web ex account
	* @return the web ex account
	* @throws NoSuchAccountException if a web ex account with the primary key could not be found
	*/
	public static WebExAccount findByPrimaryKey(long webExAccountId)
		throws com.liferay.meeting.webex.exception.NoSuchAccountException {
		return getPersistence().findByPrimaryKey(webExAccountId);
	}

	/**
	* Returns the web ex account with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param webExAccountId the primary key of the web ex account
	* @return the web ex account, or <code>null</code> if a web ex account with the primary key could not be found
	*/
	public static WebExAccount fetchByPrimaryKey(long webExAccountId) {
		return getPersistence().fetchByPrimaryKey(webExAccountId);
	}

	public static java.util.Map<java.io.Serializable, WebExAccount> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the web ex accounts.
	*
	* @return the web ex accounts
	*/
	public static List<WebExAccount> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the web ex accounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of web ex accounts
	* @param end the upper bound of the range of web ex accounts (not inclusive)
	* @return the range of web ex accounts
	*/
	public static List<WebExAccount> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the web ex accounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of web ex accounts
	* @param end the upper bound of the range of web ex accounts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of web ex accounts
	*/
	public static List<WebExAccount> findAll(int start, int end,
		OrderByComparator<WebExAccount> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the web ex accounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of web ex accounts
	* @param end the upper bound of the range of web ex accounts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of web ex accounts
	*/
	public static List<WebExAccount> findAll(int start, int end,
		OrderByComparator<WebExAccount> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the web ex accounts from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of web ex accounts.
	*
	* @return the number of web ex accounts
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static WebExAccountPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (WebExAccountPersistence)PortletBeanLocatorUtil.locate(com.liferay.meeting.webex.service.ClpSerializer.getServletContextName(),
					WebExAccountPersistence.class.getName());

			ReferenceRegistry.registerReference(WebExAccountUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	private static WebExAccountPersistence _persistence;
}