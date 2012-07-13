/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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
 * @see WSRPConsumerPortletPersistenceImpl
 * @see WSRPConsumerPortletUtil
 * @generated
 */
public interface WSRPConsumerPortletPersistence extends BasePersistence<WSRPConsumerPortlet> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WSRPConsumerPortletUtil} to access the w s r p consumer portlet persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the w s r p consumer portlet in the entity cache if it is enabled.
	*
	* @param wsrpConsumerPortlet the w s r p consumer portlet
	*/
	public void cacheResult(
		com.liferay.wsrp.model.WSRPConsumerPortlet wsrpConsumerPortlet);

	/**
	* Caches the w s r p consumer portlets in the entity cache if it is enabled.
	*
	* @param wsrpConsumerPortlets the w s r p consumer portlets
	*/
	public void cacheResult(
		java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> wsrpConsumerPortlets);

	/**
	* Creates a new w s r p consumer portlet with the primary key. Does not add the w s r p consumer portlet to the database.
	*
	* @param wsrpConsumerPortletId the primary key for the new w s r p consumer portlet
	* @return the new w s r p consumer portlet
	*/
	public com.liferay.wsrp.model.WSRPConsumerPortlet create(
		long wsrpConsumerPortletId);

	/**
	* Removes the w s r p consumer portlet with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param wsrpConsumerPortletId the primary key of the w s r p consumer portlet
	* @return the w s r p consumer portlet that was removed
	* @throws com.liferay.wsrp.NoSuchConsumerPortletException if a w s r p consumer portlet with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPConsumerPortlet remove(
		long wsrpConsumerPortletId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerPortletException;

	public com.liferay.wsrp.model.WSRPConsumerPortlet updateImpl(
		com.liferay.wsrp.model.WSRPConsumerPortlet wsrpConsumerPortlet,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the w s r p consumer portlet with the primary key or throws a {@link com.liferay.wsrp.NoSuchConsumerPortletException} if it could not be found.
	*
	* @param wsrpConsumerPortletId the primary key of the w s r p consumer portlet
	* @return the w s r p consumer portlet
	* @throws com.liferay.wsrp.NoSuchConsumerPortletException if a w s r p consumer portlet with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPConsumerPortlet findByPrimaryKey(
		long wsrpConsumerPortletId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerPortletException;

	/**
	* Returns the w s r p consumer portlet with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param wsrpConsumerPortletId the primary key of the w s r p consumer portlet
	* @return the w s r p consumer portlet, or <code>null</code> if a w s r p consumer portlet with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPConsumerPortlet fetchByPrimaryKey(
		long wsrpConsumerPortletId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the w s r p consumer portlets where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching w s r p consumer portlets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the w s r p consumer portlets where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of w s r p consumer portlets
	* @param end the upper bound of the range of w s r p consumer portlets (not inclusive)
	* @return the range of matching w s r p consumer portlets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the w s r p consumer portlets where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of w s r p consumer portlets
	* @param end the upper bound of the range of w s r p consumer portlets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching w s r p consumer portlets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first w s r p consumer portlet in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching w s r p consumer portlet
	* @throws com.liferay.wsrp.NoSuchConsumerPortletException if a matching w s r p consumer portlet could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPConsumerPortlet findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerPortletException;

	/**
	* Returns the first w s r p consumer portlet in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching w s r p consumer portlet, or <code>null</code> if a matching w s r p consumer portlet could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPConsumerPortlet fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last w s r p consumer portlet in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching w s r p consumer portlet
	* @throws com.liferay.wsrp.NoSuchConsumerPortletException if a matching w s r p consumer portlet could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPConsumerPortlet findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerPortletException;

	/**
	* Returns the last w s r p consumer portlet in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching w s r p consumer portlet, or <code>null</code> if a matching w s r p consumer portlet could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPConsumerPortlet fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the w s r p consumer portlets before and after the current w s r p consumer portlet in the ordered set where uuid = &#63;.
	*
	* @param wsrpConsumerPortletId the primary key of the current w s r p consumer portlet
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next w s r p consumer portlet
	* @throws com.liferay.wsrp.NoSuchConsumerPortletException if a w s r p consumer portlet with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPConsumerPortlet[] findByUuid_PrevAndNext(
		long wsrpConsumerPortletId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerPortletException;

	/**
	* Returns all the w s r p consumer portlets where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching w s r p consumer portlets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> findByUuid_C(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the w s r p consumer portlets where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of w s r p consumer portlets
	* @param end the upper bound of the range of w s r p consumer portlets (not inclusive)
	* @return the range of matching w s r p consumer portlets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the w s r p consumer portlets where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of w s r p consumer portlets
	* @param end the upper bound of the range of w s r p consumer portlets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching w s r p consumer portlets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first w s r p consumer portlet in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching w s r p consumer portlet
	* @throws com.liferay.wsrp.NoSuchConsumerPortletException if a matching w s r p consumer portlet could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPConsumerPortlet findByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerPortletException;

	/**
	* Returns the first w s r p consumer portlet in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching w s r p consumer portlet, or <code>null</code> if a matching w s r p consumer portlet could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPConsumerPortlet fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last w s r p consumer portlet in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching w s r p consumer portlet
	* @throws com.liferay.wsrp.NoSuchConsumerPortletException if a matching w s r p consumer portlet could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPConsumerPortlet findByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerPortletException;

	/**
	* Returns the last w s r p consumer portlet in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching w s r p consumer portlet, or <code>null</code> if a matching w s r p consumer portlet could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPConsumerPortlet fetchByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the w s r p consumer portlets before and after the current w s r p consumer portlet in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param wsrpConsumerPortletId the primary key of the current w s r p consumer portlet
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next w s r p consumer portlet
	* @throws com.liferay.wsrp.NoSuchConsumerPortletException if a w s r p consumer portlet with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPConsumerPortlet[] findByUuid_C_PrevAndNext(
		long wsrpConsumerPortletId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerPortletException;

	/**
	* Returns all the w s r p consumer portlets where wsrpConsumerId = &#63;.
	*
	* @param wsrpConsumerId the wsrp consumer ID
	* @return the matching w s r p consumer portlets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> findByWsrpConsumerId(
		long wsrpConsumerId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the w s r p consumer portlets where wsrpConsumerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param wsrpConsumerId the wsrp consumer ID
	* @param start the lower bound of the range of w s r p consumer portlets
	* @param end the upper bound of the range of w s r p consumer portlets (not inclusive)
	* @return the range of matching w s r p consumer portlets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> findByWsrpConsumerId(
		long wsrpConsumerId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the w s r p consumer portlets where wsrpConsumerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param wsrpConsumerId the wsrp consumer ID
	* @param start the lower bound of the range of w s r p consumer portlets
	* @param end the upper bound of the range of w s r p consumer portlets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching w s r p consumer portlets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> findByWsrpConsumerId(
		long wsrpConsumerId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first w s r p consumer portlet in the ordered set where wsrpConsumerId = &#63;.
	*
	* @param wsrpConsumerId the wsrp consumer ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching w s r p consumer portlet
	* @throws com.liferay.wsrp.NoSuchConsumerPortletException if a matching w s r p consumer portlet could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPConsumerPortlet findByWsrpConsumerId_First(
		long wsrpConsumerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerPortletException;

	/**
	* Returns the first w s r p consumer portlet in the ordered set where wsrpConsumerId = &#63;.
	*
	* @param wsrpConsumerId the wsrp consumer ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching w s r p consumer portlet, or <code>null</code> if a matching w s r p consumer portlet could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPConsumerPortlet fetchByWsrpConsumerId_First(
		long wsrpConsumerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last w s r p consumer portlet in the ordered set where wsrpConsumerId = &#63;.
	*
	* @param wsrpConsumerId the wsrp consumer ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching w s r p consumer portlet
	* @throws com.liferay.wsrp.NoSuchConsumerPortletException if a matching w s r p consumer portlet could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPConsumerPortlet findByWsrpConsumerId_Last(
		long wsrpConsumerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerPortletException;

	/**
	* Returns the last w s r p consumer portlet in the ordered set where wsrpConsumerId = &#63;.
	*
	* @param wsrpConsumerId the wsrp consumer ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching w s r p consumer portlet, or <code>null</code> if a matching w s r p consumer portlet could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPConsumerPortlet fetchByWsrpConsumerId_Last(
		long wsrpConsumerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the w s r p consumer portlets before and after the current w s r p consumer portlet in the ordered set where wsrpConsumerId = &#63;.
	*
	* @param wsrpConsumerPortletId the primary key of the current w s r p consumer portlet
	* @param wsrpConsumerId the wsrp consumer ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next w s r p consumer portlet
	* @throws com.liferay.wsrp.NoSuchConsumerPortletException if a w s r p consumer portlet with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPConsumerPortlet[] findByWsrpConsumerId_PrevAndNext(
		long wsrpConsumerPortletId, long wsrpConsumerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerPortletException;

	/**
	* Returns the w s r p consumer portlet where wsrpConsumerId = &#63; and portletHandle = &#63; or throws a {@link com.liferay.wsrp.NoSuchConsumerPortletException} if it could not be found.
	*
	* @param wsrpConsumerId the wsrp consumer ID
	* @param portletHandle the portlet handle
	* @return the matching w s r p consumer portlet
	* @throws com.liferay.wsrp.NoSuchConsumerPortletException if a matching w s r p consumer portlet could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPConsumerPortlet findByW_P(
		long wsrpConsumerId, java.lang.String portletHandle)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerPortletException;

	/**
	* Returns the w s r p consumer portlet where wsrpConsumerId = &#63; and portletHandle = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param wsrpConsumerId the wsrp consumer ID
	* @param portletHandle the portlet handle
	* @return the matching w s r p consumer portlet, or <code>null</code> if a matching w s r p consumer portlet could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPConsumerPortlet fetchByW_P(
		long wsrpConsumerId, java.lang.String portletHandle)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the w s r p consumer portlet where wsrpConsumerId = &#63; and portletHandle = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param wsrpConsumerId the wsrp consumer ID
	* @param portletHandle the portlet handle
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching w s r p consumer portlet, or <code>null</code> if a matching w s r p consumer portlet could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPConsumerPortlet fetchByW_P(
		long wsrpConsumerId, java.lang.String portletHandle,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the w s r p consumer portlets.
	*
	* @return the w s r p consumer portlets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the w s r p consumer portlets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of w s r p consumer portlets
	* @param end the upper bound of the range of w s r p consumer portlets (not inclusive)
	* @return the range of w s r p consumer portlets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the w s r p consumer portlets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of w s r p consumer portlets
	* @param end the upper bound of the range of w s r p consumer portlets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of w s r p consumer portlets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the w s r p consumer portlets where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the w s r p consumer portlets where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the w s r p consumer portlets where wsrpConsumerId = &#63; from the database.
	*
	* @param wsrpConsumerId the wsrp consumer ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByWsrpConsumerId(long wsrpConsumerId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the w s r p consumer portlet where wsrpConsumerId = &#63; and portletHandle = &#63; from the database.
	*
	* @param wsrpConsumerId the wsrp consumer ID
	* @param portletHandle the portlet handle
	* @return the w s r p consumer portlet that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPConsumerPortlet removeByW_P(
		long wsrpConsumerId, java.lang.String portletHandle)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerPortletException;

	/**
	* Removes all the w s r p consumer portlets from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of w s r p consumer portlets where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching w s r p consumer portlets
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of w s r p consumer portlets where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching w s r p consumer portlets
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of w s r p consumer portlets where wsrpConsumerId = &#63;.
	*
	* @param wsrpConsumerId the wsrp consumer ID
	* @return the number of matching w s r p consumer portlets
	* @throws SystemException if a system exception occurred
	*/
	public int countByWsrpConsumerId(long wsrpConsumerId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of w s r p consumer portlets where wsrpConsumerId = &#63; and portletHandle = &#63;.
	*
	* @param wsrpConsumerId the wsrp consumer ID
	* @param portletHandle the portlet handle
	* @return the number of matching w s r p consumer portlets
	* @throws SystemException if a system exception occurred
	*/
	public int countByW_P(long wsrpConsumerId, java.lang.String portletHandle)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of w s r p consumer portlets.
	*
	* @return the number of w s r p consumer portlets
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}