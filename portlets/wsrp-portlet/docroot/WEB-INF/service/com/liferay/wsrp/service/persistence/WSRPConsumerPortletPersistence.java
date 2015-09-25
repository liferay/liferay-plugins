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

package com.liferay.wsrp.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.wsrp.model.WSRPConsumerPortlet;

/**
 * The persistence interface for the w s r p consumer portlet service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.wsrp.service.persistence.impl.WSRPConsumerPortletPersistenceImpl
 * @see WSRPConsumerPortletUtil
 * @generated
 */
@ProviderType
public interface WSRPConsumerPortletPersistence extends BasePersistence<WSRPConsumerPortlet> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WSRPConsumerPortletUtil} to access the w s r p consumer portlet persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the w s r p consumer portlets where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching w s r p consumer portlets
	*/
	public java.util.List<WSRPConsumerPortlet> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the w s r p consumer portlets where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPConsumerPortletModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of w s r p consumer portlets
	* @param end the upper bound of the range of w s r p consumer portlets (not inclusive)
	* @return the range of matching w s r p consumer portlets
	*/
	public java.util.List<WSRPConsumerPortlet> findByUuid(
		java.lang.String uuid, int start, int end);

	/**
	* Returns an ordered range of all the w s r p consumer portlets where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPConsumerPortletModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of w s r p consumer portlets
	* @param end the upper bound of the range of w s r p consumer portlets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching w s r p consumer portlets
	*/
	public java.util.List<WSRPConsumerPortlet> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPConsumerPortlet> orderByComparator);

	/**
	* Returns the first w s r p consumer portlet in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching w s r p consumer portlet
	* @throws NoSuchConsumerPortletException if a matching w s r p consumer portlet could not be found
	*/
	public WSRPConsumerPortlet findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPConsumerPortlet> orderByComparator)
		throws com.liferay.wsrp.NoSuchConsumerPortletException;

	/**
	* Returns the first w s r p consumer portlet in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching w s r p consumer portlet, or <code>null</code> if a matching w s r p consumer portlet could not be found
	*/
	public WSRPConsumerPortlet fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPConsumerPortlet> orderByComparator);

	/**
	* Returns the last w s r p consumer portlet in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching w s r p consumer portlet
	* @throws NoSuchConsumerPortletException if a matching w s r p consumer portlet could not be found
	*/
	public WSRPConsumerPortlet findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPConsumerPortlet> orderByComparator)
		throws com.liferay.wsrp.NoSuchConsumerPortletException;

	/**
	* Returns the last w s r p consumer portlet in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching w s r p consumer portlet, or <code>null</code> if a matching w s r p consumer portlet could not be found
	*/
	public WSRPConsumerPortlet fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPConsumerPortlet> orderByComparator);

	/**
	* Returns the w s r p consumer portlets before and after the current w s r p consumer portlet in the ordered set where uuid = &#63;.
	*
	* @param wsrpConsumerPortletId the primary key of the current w s r p consumer portlet
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next w s r p consumer portlet
	* @throws NoSuchConsumerPortletException if a w s r p consumer portlet with the primary key could not be found
	*/
	public WSRPConsumerPortlet[] findByUuid_PrevAndNext(
		long wsrpConsumerPortletId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPConsumerPortlet> orderByComparator)
		throws com.liferay.wsrp.NoSuchConsumerPortletException;

	/**
	* Removes all the w s r p consumer portlets where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of w s r p consumer portlets where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching w s r p consumer portlets
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns all the w s r p consumer portlets where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching w s r p consumer portlets
	*/
	public java.util.List<WSRPConsumerPortlet> findByUuid_C(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of all the w s r p consumer portlets where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPConsumerPortletModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of w s r p consumer portlets
	* @param end the upper bound of the range of w s r p consumer portlets (not inclusive)
	* @return the range of matching w s r p consumer portlets
	*/
	public java.util.List<WSRPConsumerPortlet> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end);

	/**
	* Returns an ordered range of all the w s r p consumer portlets where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPConsumerPortletModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of w s r p consumer portlets
	* @param end the upper bound of the range of w s r p consumer portlets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching w s r p consumer portlets
	*/
	public java.util.List<WSRPConsumerPortlet> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPConsumerPortlet> orderByComparator);

	/**
	* Returns the first w s r p consumer portlet in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching w s r p consumer portlet
	* @throws NoSuchConsumerPortletException if a matching w s r p consumer portlet could not be found
	*/
	public WSRPConsumerPortlet findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPConsumerPortlet> orderByComparator)
		throws com.liferay.wsrp.NoSuchConsumerPortletException;

	/**
	* Returns the first w s r p consumer portlet in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching w s r p consumer portlet, or <code>null</code> if a matching w s r p consumer portlet could not be found
	*/
	public WSRPConsumerPortlet fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPConsumerPortlet> orderByComparator);

	/**
	* Returns the last w s r p consumer portlet in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching w s r p consumer portlet
	* @throws NoSuchConsumerPortletException if a matching w s r p consumer portlet could not be found
	*/
	public WSRPConsumerPortlet findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPConsumerPortlet> orderByComparator)
		throws com.liferay.wsrp.NoSuchConsumerPortletException;

	/**
	* Returns the last w s r p consumer portlet in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching w s r p consumer portlet, or <code>null</code> if a matching w s r p consumer portlet could not be found
	*/
	public WSRPConsumerPortlet fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPConsumerPortlet> orderByComparator);

	/**
	* Returns the w s r p consumer portlets before and after the current w s r p consumer portlet in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param wsrpConsumerPortletId the primary key of the current w s r p consumer portlet
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next w s r p consumer portlet
	* @throws NoSuchConsumerPortletException if a w s r p consumer portlet with the primary key could not be found
	*/
	public WSRPConsumerPortlet[] findByUuid_C_PrevAndNext(
		long wsrpConsumerPortletId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPConsumerPortlet> orderByComparator)
		throws com.liferay.wsrp.NoSuchConsumerPortletException;

	/**
	* Removes all the w s r p consumer portlets where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of w s r p consumer portlets where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching w s r p consumer portlets
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the w s r p consumer portlets where wsrpConsumerId = &#63;.
	*
	* @param wsrpConsumerId the wsrp consumer ID
	* @return the matching w s r p consumer portlets
	*/
	public java.util.List<WSRPConsumerPortlet> findByWsrpConsumerId(
		long wsrpConsumerId);

	/**
	* Returns a range of all the w s r p consumer portlets where wsrpConsumerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPConsumerPortletModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param wsrpConsumerId the wsrp consumer ID
	* @param start the lower bound of the range of w s r p consumer portlets
	* @param end the upper bound of the range of w s r p consumer portlets (not inclusive)
	* @return the range of matching w s r p consumer portlets
	*/
	public java.util.List<WSRPConsumerPortlet> findByWsrpConsumerId(
		long wsrpConsumerId, int start, int end);

	/**
	* Returns an ordered range of all the w s r p consumer portlets where wsrpConsumerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPConsumerPortletModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param wsrpConsumerId the wsrp consumer ID
	* @param start the lower bound of the range of w s r p consumer portlets
	* @param end the upper bound of the range of w s r p consumer portlets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching w s r p consumer portlets
	*/
	public java.util.List<WSRPConsumerPortlet> findByWsrpConsumerId(
		long wsrpConsumerId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPConsumerPortlet> orderByComparator);

	/**
	* Returns the first w s r p consumer portlet in the ordered set where wsrpConsumerId = &#63;.
	*
	* @param wsrpConsumerId the wsrp consumer ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching w s r p consumer portlet
	* @throws NoSuchConsumerPortletException if a matching w s r p consumer portlet could not be found
	*/
	public WSRPConsumerPortlet findByWsrpConsumerId_First(long wsrpConsumerId,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPConsumerPortlet> orderByComparator)
		throws com.liferay.wsrp.NoSuchConsumerPortletException;

	/**
	* Returns the first w s r p consumer portlet in the ordered set where wsrpConsumerId = &#63;.
	*
	* @param wsrpConsumerId the wsrp consumer ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching w s r p consumer portlet, or <code>null</code> if a matching w s r p consumer portlet could not be found
	*/
	public WSRPConsumerPortlet fetchByWsrpConsumerId_First(
		long wsrpConsumerId,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPConsumerPortlet> orderByComparator);

	/**
	* Returns the last w s r p consumer portlet in the ordered set where wsrpConsumerId = &#63;.
	*
	* @param wsrpConsumerId the wsrp consumer ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching w s r p consumer portlet
	* @throws NoSuchConsumerPortletException if a matching w s r p consumer portlet could not be found
	*/
	public WSRPConsumerPortlet findByWsrpConsumerId_Last(long wsrpConsumerId,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPConsumerPortlet> orderByComparator)
		throws com.liferay.wsrp.NoSuchConsumerPortletException;

	/**
	* Returns the last w s r p consumer portlet in the ordered set where wsrpConsumerId = &#63;.
	*
	* @param wsrpConsumerId the wsrp consumer ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching w s r p consumer portlet, or <code>null</code> if a matching w s r p consumer portlet could not be found
	*/
	public WSRPConsumerPortlet fetchByWsrpConsumerId_Last(long wsrpConsumerId,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPConsumerPortlet> orderByComparator);

	/**
	* Returns the w s r p consumer portlets before and after the current w s r p consumer portlet in the ordered set where wsrpConsumerId = &#63;.
	*
	* @param wsrpConsumerPortletId the primary key of the current w s r p consumer portlet
	* @param wsrpConsumerId the wsrp consumer ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next w s r p consumer portlet
	* @throws NoSuchConsumerPortletException if a w s r p consumer portlet with the primary key could not be found
	*/
	public WSRPConsumerPortlet[] findByWsrpConsumerId_PrevAndNext(
		long wsrpConsumerPortletId, long wsrpConsumerId,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPConsumerPortlet> orderByComparator)
		throws com.liferay.wsrp.NoSuchConsumerPortletException;

	/**
	* Removes all the w s r p consumer portlets where wsrpConsumerId = &#63; from the database.
	*
	* @param wsrpConsumerId the wsrp consumer ID
	*/
	public void removeByWsrpConsumerId(long wsrpConsumerId);

	/**
	* Returns the number of w s r p consumer portlets where wsrpConsumerId = &#63;.
	*
	* @param wsrpConsumerId the wsrp consumer ID
	* @return the number of matching w s r p consumer portlets
	*/
	public int countByWsrpConsumerId(long wsrpConsumerId);

	/**
	* Returns the w s r p consumer portlet where wsrpConsumerId = &#63; and portletHandle = &#63; or throws a {@link NoSuchConsumerPortletException} if it could not be found.
	*
	* @param wsrpConsumerId the wsrp consumer ID
	* @param portletHandle the portlet handle
	* @return the matching w s r p consumer portlet
	* @throws NoSuchConsumerPortletException if a matching w s r p consumer portlet could not be found
	*/
	public WSRPConsumerPortlet findByW_P(long wsrpConsumerId,
		java.lang.String portletHandle)
		throws com.liferay.wsrp.NoSuchConsumerPortletException;

	/**
	* Returns the w s r p consumer portlet where wsrpConsumerId = &#63; and portletHandle = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param wsrpConsumerId the wsrp consumer ID
	* @param portletHandle the portlet handle
	* @return the matching w s r p consumer portlet, or <code>null</code> if a matching w s r p consumer portlet could not be found
	*/
	public WSRPConsumerPortlet fetchByW_P(long wsrpConsumerId,
		java.lang.String portletHandle);

	/**
	* Returns the w s r p consumer portlet where wsrpConsumerId = &#63; and portletHandle = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param wsrpConsumerId the wsrp consumer ID
	* @param portletHandle the portlet handle
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching w s r p consumer portlet, or <code>null</code> if a matching w s r p consumer portlet could not be found
	*/
	public WSRPConsumerPortlet fetchByW_P(long wsrpConsumerId,
		java.lang.String portletHandle, boolean retrieveFromCache);

	/**
	* Removes the w s r p consumer portlet where wsrpConsumerId = &#63; and portletHandle = &#63; from the database.
	*
	* @param wsrpConsumerId the wsrp consumer ID
	* @param portletHandle the portlet handle
	* @return the w s r p consumer portlet that was removed
	*/
	public WSRPConsumerPortlet removeByW_P(long wsrpConsumerId,
		java.lang.String portletHandle)
		throws com.liferay.wsrp.NoSuchConsumerPortletException;

	/**
	* Returns the number of w s r p consumer portlets where wsrpConsumerId = &#63; and portletHandle = &#63;.
	*
	* @param wsrpConsumerId the wsrp consumer ID
	* @param portletHandle the portlet handle
	* @return the number of matching w s r p consumer portlets
	*/
	public int countByW_P(long wsrpConsumerId, java.lang.String portletHandle);

	/**
	* Caches the w s r p consumer portlet in the entity cache if it is enabled.
	*
	* @param wsrpConsumerPortlet the w s r p consumer portlet
	*/
	public void cacheResult(WSRPConsumerPortlet wsrpConsumerPortlet);

	/**
	* Caches the w s r p consumer portlets in the entity cache if it is enabled.
	*
	* @param wsrpConsumerPortlets the w s r p consumer portlets
	*/
	public void cacheResult(
		java.util.List<WSRPConsumerPortlet> wsrpConsumerPortlets);

	/**
	* Creates a new w s r p consumer portlet with the primary key. Does not add the w s r p consumer portlet to the database.
	*
	* @param wsrpConsumerPortletId the primary key for the new w s r p consumer portlet
	* @return the new w s r p consumer portlet
	*/
	public WSRPConsumerPortlet create(long wsrpConsumerPortletId);

	/**
	* Removes the w s r p consumer portlet with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param wsrpConsumerPortletId the primary key of the w s r p consumer portlet
	* @return the w s r p consumer portlet that was removed
	* @throws NoSuchConsumerPortletException if a w s r p consumer portlet with the primary key could not be found
	*/
	public WSRPConsumerPortlet remove(long wsrpConsumerPortletId)
		throws com.liferay.wsrp.NoSuchConsumerPortletException;

	public WSRPConsumerPortlet updateImpl(
		WSRPConsumerPortlet wsrpConsumerPortlet);

	/**
	* Returns the w s r p consumer portlet with the primary key or throws a {@link NoSuchConsumerPortletException} if it could not be found.
	*
	* @param wsrpConsumerPortletId the primary key of the w s r p consumer portlet
	* @return the w s r p consumer portlet
	* @throws NoSuchConsumerPortletException if a w s r p consumer portlet with the primary key could not be found
	*/
	public WSRPConsumerPortlet findByPrimaryKey(long wsrpConsumerPortletId)
		throws com.liferay.wsrp.NoSuchConsumerPortletException;

	/**
	* Returns the w s r p consumer portlet with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param wsrpConsumerPortletId the primary key of the w s r p consumer portlet
	* @return the w s r p consumer portlet, or <code>null</code> if a w s r p consumer portlet with the primary key could not be found
	*/
	public WSRPConsumerPortlet fetchByPrimaryKey(long wsrpConsumerPortletId);

	@Override
	public java.util.Map<java.io.Serializable, WSRPConsumerPortlet> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the w s r p consumer portlets.
	*
	* @return the w s r p consumer portlets
	*/
	public java.util.List<WSRPConsumerPortlet> findAll();

	/**
	* Returns a range of all the w s r p consumer portlets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPConsumerPortletModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of w s r p consumer portlets
	* @param end the upper bound of the range of w s r p consumer portlets (not inclusive)
	* @return the range of w s r p consumer portlets
	*/
	public java.util.List<WSRPConsumerPortlet> findAll(int start, int end);

	/**
	* Returns an ordered range of all the w s r p consumer portlets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPConsumerPortletModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of w s r p consumer portlets
	* @param end the upper bound of the range of w s r p consumer portlets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of w s r p consumer portlets
	*/
	public java.util.List<WSRPConsumerPortlet> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPConsumerPortlet> orderByComparator);

	/**
	* Removes all the w s r p consumer portlets from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of w s r p consumer portlets.
	*
	* @return the number of w s r p consumer portlets
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}