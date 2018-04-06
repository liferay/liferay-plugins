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

import com.liferay.meeting.webex.exception.NoSuchAccountException;
import com.liferay.meeting.webex.model.WebExAccount;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the web ex account service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Anant Singh
 * @see com.liferay.meeting.webex.service.persistence.impl.WebExAccountPersistenceImpl
 * @see WebExAccountUtil
 * @generated
 */
@ProviderType
public interface WebExAccountPersistence extends BasePersistence<WebExAccount> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WebExAccountUtil} to access the web ex account persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the web ex accounts where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching web ex accounts
	*/
	public java.util.List<WebExAccount> findByUuid(java.lang.String uuid);

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
	public java.util.List<WebExAccount> findByUuid(java.lang.String uuid,
		int start, int end);

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
	public java.util.List<WebExAccount> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WebExAccount> orderByComparator);

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
	public java.util.List<WebExAccount> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WebExAccount> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first web ex account in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching web ex account
	* @throws NoSuchAccountException if a matching web ex account could not be found
	*/
	public WebExAccount findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WebExAccount> orderByComparator)
		throws NoSuchAccountException;

	/**
	* Returns the first web ex account in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching web ex account, or <code>null</code> if a matching web ex account could not be found
	*/
	public WebExAccount fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WebExAccount> orderByComparator);

	/**
	* Returns the last web ex account in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching web ex account
	* @throws NoSuchAccountException if a matching web ex account could not be found
	*/
	public WebExAccount findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WebExAccount> orderByComparator)
		throws NoSuchAccountException;

	/**
	* Returns the last web ex account in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching web ex account, or <code>null</code> if a matching web ex account could not be found
	*/
	public WebExAccount fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WebExAccount> orderByComparator);

	/**
	* Returns the web ex accounts before and after the current web ex account in the ordered set where uuid = &#63;.
	*
	* @param webExAccountId the primary key of the current web ex account
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next web ex account
	* @throws NoSuchAccountException if a web ex account with the primary key could not be found
	*/
	public WebExAccount[] findByUuid_PrevAndNext(long webExAccountId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WebExAccount> orderByComparator)
		throws NoSuchAccountException;

	/**
	* Removes all the web ex accounts where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of web ex accounts where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching web ex accounts
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the web ex account where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchAccountException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching web ex account
	* @throws NoSuchAccountException if a matching web ex account could not be found
	*/
	public WebExAccount findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchAccountException;

	/**
	* Returns the web ex account where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching web ex account, or <code>null</code> if a matching web ex account could not be found
	*/
	public WebExAccount fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the web ex account where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching web ex account, or <code>null</code> if a matching web ex account could not be found
	*/
	public WebExAccount fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the web ex account where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the web ex account that was removed
	*/
	public WebExAccount removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchAccountException;

	/**
	* Returns the number of web ex accounts where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching web ex accounts
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the web ex accounts where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching web ex accounts
	*/
	public java.util.List<WebExAccount> findByUuid_C(java.lang.String uuid,
		long companyId);

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
	public java.util.List<WebExAccount> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

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
	public java.util.List<WebExAccount> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WebExAccount> orderByComparator);

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
	public java.util.List<WebExAccount> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WebExAccount> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first web ex account in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching web ex account
	* @throws NoSuchAccountException if a matching web ex account could not be found
	*/
	public WebExAccount findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WebExAccount> orderByComparator)
		throws NoSuchAccountException;

	/**
	* Returns the first web ex account in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching web ex account, or <code>null</code> if a matching web ex account could not be found
	*/
	public WebExAccount fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WebExAccount> orderByComparator);

	/**
	* Returns the last web ex account in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching web ex account
	* @throws NoSuchAccountException if a matching web ex account could not be found
	*/
	public WebExAccount findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WebExAccount> orderByComparator)
		throws NoSuchAccountException;

	/**
	* Returns the last web ex account in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching web ex account, or <code>null</code> if a matching web ex account could not be found
	*/
	public WebExAccount fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WebExAccount> orderByComparator);

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
	public WebExAccount[] findByUuid_C_PrevAndNext(long webExAccountId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WebExAccount> orderByComparator)
		throws NoSuchAccountException;

	/**
	* Removes all the web ex accounts where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of web ex accounts where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching web ex accounts
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the web ex accounts where groupId = &#63; and webExSiteId = &#63;.
	*
	* @param groupId the group ID
	* @param webExSiteId the web ex site ID
	* @return the matching web ex accounts
	*/
	public java.util.List<WebExAccount> findByG_W(long groupId, long webExSiteId);

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
	public java.util.List<WebExAccount> findByG_W(long groupId,
		long webExSiteId, int start, int end);

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
	public java.util.List<WebExAccount> findByG_W(long groupId,
		long webExSiteId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WebExAccount> orderByComparator);

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
	public java.util.List<WebExAccount> findByG_W(long groupId,
		long webExSiteId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WebExAccount> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first web ex account in the ordered set where groupId = &#63; and webExSiteId = &#63;.
	*
	* @param groupId the group ID
	* @param webExSiteId the web ex site ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching web ex account
	* @throws NoSuchAccountException if a matching web ex account could not be found
	*/
	public WebExAccount findByG_W_First(long groupId, long webExSiteId,
		com.liferay.portal.kernel.util.OrderByComparator<WebExAccount> orderByComparator)
		throws NoSuchAccountException;

	/**
	* Returns the first web ex account in the ordered set where groupId = &#63; and webExSiteId = &#63;.
	*
	* @param groupId the group ID
	* @param webExSiteId the web ex site ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching web ex account, or <code>null</code> if a matching web ex account could not be found
	*/
	public WebExAccount fetchByG_W_First(long groupId, long webExSiteId,
		com.liferay.portal.kernel.util.OrderByComparator<WebExAccount> orderByComparator);

	/**
	* Returns the last web ex account in the ordered set where groupId = &#63; and webExSiteId = &#63;.
	*
	* @param groupId the group ID
	* @param webExSiteId the web ex site ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching web ex account
	* @throws NoSuchAccountException if a matching web ex account could not be found
	*/
	public WebExAccount findByG_W_Last(long groupId, long webExSiteId,
		com.liferay.portal.kernel.util.OrderByComparator<WebExAccount> orderByComparator)
		throws NoSuchAccountException;

	/**
	* Returns the last web ex account in the ordered set where groupId = &#63; and webExSiteId = &#63;.
	*
	* @param groupId the group ID
	* @param webExSiteId the web ex site ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching web ex account, or <code>null</code> if a matching web ex account could not be found
	*/
	public WebExAccount fetchByG_W_Last(long groupId, long webExSiteId,
		com.liferay.portal.kernel.util.OrderByComparator<WebExAccount> orderByComparator);

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
	public WebExAccount[] findByG_W_PrevAndNext(long webExAccountId,
		long groupId, long webExSiteId,
		com.liferay.portal.kernel.util.OrderByComparator<WebExAccount> orderByComparator)
		throws NoSuchAccountException;

	/**
	* Returns all the web ex accounts that the user has permission to view where groupId = &#63; and webExSiteId = &#63;.
	*
	* @param groupId the group ID
	* @param webExSiteId the web ex site ID
	* @return the matching web ex accounts that the user has permission to view
	*/
	public java.util.List<WebExAccount> filterFindByG_W(long groupId,
		long webExSiteId);

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
	public java.util.List<WebExAccount> filterFindByG_W(long groupId,
		long webExSiteId, int start, int end);

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
	public java.util.List<WebExAccount> filterFindByG_W(long groupId,
		long webExSiteId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WebExAccount> orderByComparator);

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
	public WebExAccount[] filterFindByG_W_PrevAndNext(long webExAccountId,
		long groupId, long webExSiteId,
		com.liferay.portal.kernel.util.OrderByComparator<WebExAccount> orderByComparator)
		throws NoSuchAccountException;

	/**
	* Removes all the web ex accounts where groupId = &#63; and webExSiteId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param webExSiteId the web ex site ID
	*/
	public void removeByG_W(long groupId, long webExSiteId);

	/**
	* Returns the number of web ex accounts where groupId = &#63; and webExSiteId = &#63;.
	*
	* @param groupId the group ID
	* @param webExSiteId the web ex site ID
	* @return the number of matching web ex accounts
	*/
	public int countByG_W(long groupId, long webExSiteId);

	/**
	* Returns the number of web ex accounts that the user has permission to view where groupId = &#63; and webExSiteId = &#63;.
	*
	* @param groupId the group ID
	* @param webExSiteId the web ex site ID
	* @return the number of matching web ex accounts that the user has permission to view
	*/
	public int filterCountByG_W(long groupId, long webExSiteId);

	/**
	* Caches the web ex account in the entity cache if it is enabled.
	*
	* @param webExAccount the web ex account
	*/
	public void cacheResult(WebExAccount webExAccount);

	/**
	* Caches the web ex accounts in the entity cache if it is enabled.
	*
	* @param webExAccounts the web ex accounts
	*/
	public void cacheResult(java.util.List<WebExAccount> webExAccounts);

	/**
	* Creates a new web ex account with the primary key. Does not add the web ex account to the database.
	*
	* @param webExAccountId the primary key for the new web ex account
	* @return the new web ex account
	*/
	public WebExAccount create(long webExAccountId);

	/**
	* Removes the web ex account with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param webExAccountId the primary key of the web ex account
	* @return the web ex account that was removed
	* @throws NoSuchAccountException if a web ex account with the primary key could not be found
	*/
	public WebExAccount remove(long webExAccountId)
		throws NoSuchAccountException;

	public WebExAccount updateImpl(WebExAccount webExAccount);

	/**
	* Returns the web ex account with the primary key or throws a {@link NoSuchAccountException} if it could not be found.
	*
	* @param webExAccountId the primary key of the web ex account
	* @return the web ex account
	* @throws NoSuchAccountException if a web ex account with the primary key could not be found
	*/
	public WebExAccount findByPrimaryKey(long webExAccountId)
		throws NoSuchAccountException;

	/**
	* Returns the web ex account with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param webExAccountId the primary key of the web ex account
	* @return the web ex account, or <code>null</code> if a web ex account with the primary key could not be found
	*/
	public WebExAccount fetchByPrimaryKey(long webExAccountId);

	@Override
	public java.util.Map<java.io.Serializable, WebExAccount> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the web ex accounts.
	*
	* @return the web ex accounts
	*/
	public java.util.List<WebExAccount> findAll();

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
	public java.util.List<WebExAccount> findAll(int start, int end);

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
	public java.util.List<WebExAccount> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WebExAccount> orderByComparator);

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
	public java.util.List<WebExAccount> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WebExAccount> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the web ex accounts from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of web ex accounts.
	*
	* @return the number of web ex accounts
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}