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

import com.liferay.meeting.webex.exception.NoSuchSiteException;
import com.liferay.meeting.webex.model.WebExSite;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the web ex site service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Anant Singh
 * @see com.liferay.meeting.webex.service.persistence.impl.WebExSitePersistenceImpl
 * @see WebExSiteUtil
 * @generated
 */
@ProviderType
public interface WebExSitePersistence extends BasePersistence<WebExSite> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WebExSiteUtil} to access the web ex site persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the web ex sites where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching web ex sites
	*/
	public java.util.List<WebExSite> findByUuid(java.lang.String uuid);

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
	public java.util.List<WebExSite> findByUuid(java.lang.String uuid,
		int start, int end);

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
	public java.util.List<WebExSite> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WebExSite> orderByComparator);

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
	public java.util.List<WebExSite> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WebExSite> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first web ex site in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching web ex site
	* @throws NoSuchSiteException if a matching web ex site could not be found
	*/
	public WebExSite findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WebExSite> orderByComparator)
		throws NoSuchSiteException;

	/**
	* Returns the first web ex site in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching web ex site, or <code>null</code> if a matching web ex site could not be found
	*/
	public WebExSite fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WebExSite> orderByComparator);

	/**
	* Returns the last web ex site in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching web ex site
	* @throws NoSuchSiteException if a matching web ex site could not be found
	*/
	public WebExSite findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WebExSite> orderByComparator)
		throws NoSuchSiteException;

	/**
	* Returns the last web ex site in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching web ex site, or <code>null</code> if a matching web ex site could not be found
	*/
	public WebExSite fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WebExSite> orderByComparator);

	/**
	* Returns the web ex sites before and after the current web ex site in the ordered set where uuid = &#63;.
	*
	* @param webExSiteId the primary key of the current web ex site
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next web ex site
	* @throws NoSuchSiteException if a web ex site with the primary key could not be found
	*/
	public WebExSite[] findByUuid_PrevAndNext(long webExSiteId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WebExSite> orderByComparator)
		throws NoSuchSiteException;

	/**
	* Removes all the web ex sites where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of web ex sites where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching web ex sites
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the web ex site where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchSiteException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching web ex site
	* @throws NoSuchSiteException if a matching web ex site could not be found
	*/
	public WebExSite findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchSiteException;

	/**
	* Returns the web ex site where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching web ex site, or <code>null</code> if a matching web ex site could not be found
	*/
	public WebExSite fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the web ex site where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching web ex site, or <code>null</code> if a matching web ex site could not be found
	*/
	public WebExSite fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the web ex site where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the web ex site that was removed
	*/
	public WebExSite removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchSiteException;

	/**
	* Returns the number of web ex sites where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching web ex sites
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the web ex sites where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching web ex sites
	*/
	public java.util.List<WebExSite> findByUuid_C(java.lang.String uuid,
		long companyId);

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
	public java.util.List<WebExSite> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

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
	public java.util.List<WebExSite> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WebExSite> orderByComparator);

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
	public java.util.List<WebExSite> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WebExSite> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first web ex site in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching web ex site
	* @throws NoSuchSiteException if a matching web ex site could not be found
	*/
	public WebExSite findByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WebExSite> orderByComparator)
		throws NoSuchSiteException;

	/**
	* Returns the first web ex site in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching web ex site, or <code>null</code> if a matching web ex site could not be found
	*/
	public WebExSite fetchByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WebExSite> orderByComparator);

	/**
	* Returns the last web ex site in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching web ex site
	* @throws NoSuchSiteException if a matching web ex site could not be found
	*/
	public WebExSite findByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WebExSite> orderByComparator)
		throws NoSuchSiteException;

	/**
	* Returns the last web ex site in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching web ex site, or <code>null</code> if a matching web ex site could not be found
	*/
	public WebExSite fetchByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WebExSite> orderByComparator);

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
	public WebExSite[] findByUuid_C_PrevAndNext(long webExSiteId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WebExSite> orderByComparator)
		throws NoSuchSiteException;

	/**
	* Removes all the web ex sites where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of web ex sites where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching web ex sites
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the web ex sites where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching web ex sites
	*/
	public java.util.List<WebExSite> findByGroupId(long groupId);

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
	public java.util.List<WebExSite> findByGroupId(long groupId, int start,
		int end);

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
	public java.util.List<WebExSite> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<WebExSite> orderByComparator);

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
	public java.util.List<WebExSite> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<WebExSite> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first web ex site in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching web ex site
	* @throws NoSuchSiteException if a matching web ex site could not be found
	*/
	public WebExSite findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<WebExSite> orderByComparator)
		throws NoSuchSiteException;

	/**
	* Returns the first web ex site in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching web ex site, or <code>null</code> if a matching web ex site could not be found
	*/
	public WebExSite fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<WebExSite> orderByComparator);

	/**
	* Returns the last web ex site in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching web ex site
	* @throws NoSuchSiteException if a matching web ex site could not be found
	*/
	public WebExSite findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<WebExSite> orderByComparator)
		throws NoSuchSiteException;

	/**
	* Returns the last web ex site in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching web ex site, or <code>null</code> if a matching web ex site could not be found
	*/
	public WebExSite fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<WebExSite> orderByComparator);

	/**
	* Returns the web ex sites before and after the current web ex site in the ordered set where groupId = &#63;.
	*
	* @param webExSiteId the primary key of the current web ex site
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next web ex site
	* @throws NoSuchSiteException if a web ex site with the primary key could not be found
	*/
	public WebExSite[] findByGroupId_PrevAndNext(long webExSiteId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<WebExSite> orderByComparator)
		throws NoSuchSiteException;

	/**
	* Returns all the web ex sites that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching web ex sites that the user has permission to view
	*/
	public java.util.List<WebExSite> filterFindByGroupId(long groupId);

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
	public java.util.List<WebExSite> filterFindByGroupId(long groupId,
		int start, int end);

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
	public java.util.List<WebExSite> filterFindByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WebExSite> orderByComparator);

	/**
	* Returns the web ex sites before and after the current web ex site in the ordered set of web ex sites that the user has permission to view where groupId = &#63;.
	*
	* @param webExSiteId the primary key of the current web ex site
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next web ex site
	* @throws NoSuchSiteException if a web ex site with the primary key could not be found
	*/
	public WebExSite[] filterFindByGroupId_PrevAndNext(long webExSiteId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<WebExSite> orderByComparator)
		throws NoSuchSiteException;

	/**
	* Removes all the web ex sites where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of web ex sites where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching web ex sites
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns the number of web ex sites that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching web ex sites that the user has permission to view
	*/
	public int filterCountByGroupId(long groupId);

	/**
	* Returns the web ex site where siteKey = &#63; or throws a {@link NoSuchSiteException} if it could not be found.
	*
	* @param siteKey the site key
	* @return the matching web ex site
	* @throws NoSuchSiteException if a matching web ex site could not be found
	*/
	public WebExSite findBySiteKey(long siteKey) throws NoSuchSiteException;

	/**
	* Returns the web ex site where siteKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param siteKey the site key
	* @return the matching web ex site, or <code>null</code> if a matching web ex site could not be found
	*/
	public WebExSite fetchBySiteKey(long siteKey);

	/**
	* Returns the web ex site where siteKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param siteKey the site key
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching web ex site, or <code>null</code> if a matching web ex site could not be found
	*/
	public WebExSite fetchBySiteKey(long siteKey, boolean retrieveFromCache);

	/**
	* Removes the web ex site where siteKey = &#63; from the database.
	*
	* @param siteKey the site key
	* @return the web ex site that was removed
	*/
	public WebExSite removeBySiteKey(long siteKey) throws NoSuchSiteException;

	/**
	* Returns the number of web ex sites where siteKey = &#63;.
	*
	* @param siteKey the site key
	* @return the number of matching web ex sites
	*/
	public int countBySiteKey(long siteKey);

	/**
	* Caches the web ex site in the entity cache if it is enabled.
	*
	* @param webExSite the web ex site
	*/
	public void cacheResult(WebExSite webExSite);

	/**
	* Caches the web ex sites in the entity cache if it is enabled.
	*
	* @param webExSites the web ex sites
	*/
	public void cacheResult(java.util.List<WebExSite> webExSites);

	/**
	* Creates a new web ex site with the primary key. Does not add the web ex site to the database.
	*
	* @param webExSiteId the primary key for the new web ex site
	* @return the new web ex site
	*/
	public WebExSite create(long webExSiteId);

	/**
	* Removes the web ex site with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param webExSiteId the primary key of the web ex site
	* @return the web ex site that was removed
	* @throws NoSuchSiteException if a web ex site with the primary key could not be found
	*/
	public WebExSite remove(long webExSiteId) throws NoSuchSiteException;

	public WebExSite updateImpl(WebExSite webExSite);

	/**
	* Returns the web ex site with the primary key or throws a {@link NoSuchSiteException} if it could not be found.
	*
	* @param webExSiteId the primary key of the web ex site
	* @return the web ex site
	* @throws NoSuchSiteException if a web ex site with the primary key could not be found
	*/
	public WebExSite findByPrimaryKey(long webExSiteId)
		throws NoSuchSiteException;

	/**
	* Returns the web ex site with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param webExSiteId the primary key of the web ex site
	* @return the web ex site, or <code>null</code> if a web ex site with the primary key could not be found
	*/
	public WebExSite fetchByPrimaryKey(long webExSiteId);

	@Override
	public java.util.Map<java.io.Serializable, WebExSite> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the web ex sites.
	*
	* @return the web ex sites
	*/
	public java.util.List<WebExSite> findAll();

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
	public java.util.List<WebExSite> findAll(int start, int end);

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
	public java.util.List<WebExSite> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WebExSite> orderByComparator);

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
	public java.util.List<WebExSite> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WebExSite> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the web ex sites from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of web ex sites.
	*
	* @return the number of web ex sites
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}