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

import com.liferay.wsrp.model.WSRPConsumer;

/**
 * The persistence interface for the w s r p consumer service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WSRPConsumerPersistenceImpl
 * @see WSRPConsumerUtil
 * @generated
 */
public interface WSRPConsumerPersistence extends BasePersistence<WSRPConsumer> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WSRPConsumerUtil} to access the w s r p consumer persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the w s r p consumer in the entity cache if it is enabled.
	*
	* @param wsrpConsumer the w s r p consumer
	*/
	public void cacheResult(com.liferay.wsrp.model.WSRPConsumer wsrpConsumer);

	/**
	* Caches the w s r p consumers in the entity cache if it is enabled.
	*
	* @param wsrpConsumers the w s r p consumers
	*/
	public void cacheResult(
		java.util.List<com.liferay.wsrp.model.WSRPConsumer> wsrpConsumers);

	/**
	* Creates a new w s r p consumer with the primary key. Does not add the w s r p consumer to the database.
	*
	* @param wsrpConsumerId the primary key for the new w s r p consumer
	* @return the new w s r p consumer
	*/
	public com.liferay.wsrp.model.WSRPConsumer create(long wsrpConsumerId);

	/**
	* Removes the w s r p consumer with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param wsrpConsumerId the primary key of the w s r p consumer
	* @return the w s r p consumer that was removed
	* @throws com.liferay.wsrp.NoSuchConsumerException if a w s r p consumer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPConsumer remove(long wsrpConsumerId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerException;

	public com.liferay.wsrp.model.WSRPConsumer updateImpl(
		com.liferay.wsrp.model.WSRPConsumer wsrpConsumer, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the w s r p consumer with the primary key or throws a {@link com.liferay.wsrp.NoSuchConsumerException} if it could not be found.
	*
	* @param wsrpConsumerId the primary key of the w s r p consumer
	* @return the w s r p consumer
	* @throws com.liferay.wsrp.NoSuchConsumerException if a w s r p consumer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPConsumer findByPrimaryKey(
		long wsrpConsumerId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerException;

	/**
	* Returns the w s r p consumer with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param wsrpConsumerId the primary key of the w s r p consumer
	* @return the w s r p consumer, or <code>null</code> if a w s r p consumer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPConsumer fetchByPrimaryKey(
		long wsrpConsumerId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the w s r p consumers where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching w s r p consumers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.wsrp.model.WSRPConsumer> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the w s r p consumers where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of w s r p consumers
	* @param end the upper bound of the range of w s r p consumers (not inclusive)
	* @return the range of matching w s r p consumers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.wsrp.model.WSRPConsumer> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the w s r p consumers where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of w s r p consumers
	* @param end the upper bound of the range of w s r p consumers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching w s r p consumers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.wsrp.model.WSRPConsumer> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first w s r p consumer in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching w s r p consumer
	* @throws com.liferay.wsrp.NoSuchConsumerException if a matching w s r p consumer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPConsumer findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerException;

	/**
	* Returns the first w s r p consumer in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching w s r p consumer, or <code>null</code> if a matching w s r p consumer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPConsumer fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last w s r p consumer in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching w s r p consumer
	* @throws com.liferay.wsrp.NoSuchConsumerException if a matching w s r p consumer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPConsumer findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerException;

	/**
	* Returns the last w s r p consumer in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching w s r p consumer, or <code>null</code> if a matching w s r p consumer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPConsumer fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the w s r p consumers before and after the current w s r p consumer in the ordered set where uuid = &#63;.
	*
	* @param wsrpConsumerId the primary key of the current w s r p consumer
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next w s r p consumer
	* @throws com.liferay.wsrp.NoSuchConsumerException if a w s r p consumer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPConsumer[] findByUuid_PrevAndNext(
		long wsrpConsumerId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerException;

	/**
	* Returns all the w s r p consumers where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching w s r p consumers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.wsrp.model.WSRPConsumer> findByUuid_C(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the w s r p consumers where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of w s r p consumers
	* @param end the upper bound of the range of w s r p consumers (not inclusive)
	* @return the range of matching w s r p consumers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.wsrp.model.WSRPConsumer> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the w s r p consumers where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of w s r p consumers
	* @param end the upper bound of the range of w s r p consumers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching w s r p consumers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.wsrp.model.WSRPConsumer> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first w s r p consumer in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching w s r p consumer
	* @throws com.liferay.wsrp.NoSuchConsumerException if a matching w s r p consumer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPConsumer findByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerException;

	/**
	* Returns the first w s r p consumer in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching w s r p consumer, or <code>null</code> if a matching w s r p consumer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPConsumer fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last w s r p consumer in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching w s r p consumer
	* @throws com.liferay.wsrp.NoSuchConsumerException if a matching w s r p consumer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPConsumer findByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerException;

	/**
	* Returns the last w s r p consumer in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching w s r p consumer, or <code>null</code> if a matching w s r p consumer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPConsumer fetchByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the w s r p consumers before and after the current w s r p consumer in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param wsrpConsumerId the primary key of the current w s r p consumer
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next w s r p consumer
	* @throws com.liferay.wsrp.NoSuchConsumerException if a w s r p consumer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPConsumer[] findByUuid_C_PrevAndNext(
		long wsrpConsumerId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerException;

	/**
	* Returns all the w s r p consumers where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching w s r p consumers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.wsrp.model.WSRPConsumer> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the w s r p consumers where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of w s r p consumers
	* @param end the upper bound of the range of w s r p consumers (not inclusive)
	* @return the range of matching w s r p consumers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.wsrp.model.WSRPConsumer> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the w s r p consumers where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of w s r p consumers
	* @param end the upper bound of the range of w s r p consumers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching w s r p consumers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.wsrp.model.WSRPConsumer> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first w s r p consumer in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching w s r p consumer
	* @throws com.liferay.wsrp.NoSuchConsumerException if a matching w s r p consumer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPConsumer findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerException;

	/**
	* Returns the first w s r p consumer in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching w s r p consumer, or <code>null</code> if a matching w s r p consumer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPConsumer fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last w s r p consumer in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching w s r p consumer
	* @throws com.liferay.wsrp.NoSuchConsumerException if a matching w s r p consumer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPConsumer findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerException;

	/**
	* Returns the last w s r p consumer in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching w s r p consumer, or <code>null</code> if a matching w s r p consumer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPConsumer fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the w s r p consumers before and after the current w s r p consumer in the ordered set where companyId = &#63;.
	*
	* @param wsrpConsumerId the primary key of the current w s r p consumer
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next w s r p consumer
	* @throws com.liferay.wsrp.NoSuchConsumerException if a w s r p consumer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPConsumer[] findByCompanyId_PrevAndNext(
		long wsrpConsumerId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerException;

	/**
	* Returns all the w s r p consumers.
	*
	* @return the w s r p consumers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.wsrp.model.WSRPConsumer> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the w s r p consumers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of w s r p consumers
	* @param end the upper bound of the range of w s r p consumers (not inclusive)
	* @return the range of w s r p consumers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.wsrp.model.WSRPConsumer> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the w s r p consumers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of w s r p consumers
	* @param end the upper bound of the range of w s r p consumers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of w s r p consumers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.wsrp.model.WSRPConsumer> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the w s r p consumers where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the w s r p consumers where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the w s r p consumers where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the w s r p consumers from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of w s r p consumers where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching w s r p consumers
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of w s r p consumers where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching w s r p consumers
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of w s r p consumers where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching w s r p consumers
	* @throws SystemException if a system exception occurred
	*/
	public int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of w s r p consumers.
	*
	* @return the number of w s r p consumers
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}