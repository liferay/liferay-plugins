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

import com.liferay.wsrp.model.WSRPProducer;

/**
 * The persistence interface for the w s r p producer service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WSRPProducerPersistenceImpl
 * @see WSRPProducerUtil
 * @generated
 */
public interface WSRPProducerPersistence extends BasePersistence<WSRPProducer> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WSRPProducerUtil} to access the w s r p producer persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the w s r p producer in the entity cache if it is enabled.
	*
	* @param wsrpProducer the w s r p producer
	*/
	public void cacheResult(com.liferay.wsrp.model.WSRPProducer wsrpProducer);

	/**
	* Caches the w s r p producers in the entity cache if it is enabled.
	*
	* @param wsrpProducers the w s r p producers
	*/
	public void cacheResult(
		java.util.List<com.liferay.wsrp.model.WSRPProducer> wsrpProducers);

	/**
	* Creates a new w s r p producer with the primary key. Does not add the w s r p producer to the database.
	*
	* @param wsrpProducerId the primary key for the new w s r p producer
	* @return the new w s r p producer
	*/
	public com.liferay.wsrp.model.WSRPProducer create(long wsrpProducerId);

	/**
	* Removes the w s r p producer with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param wsrpProducerId the primary key of the w s r p producer
	* @return the w s r p producer that was removed
	* @throws com.liferay.wsrp.NoSuchProducerException if a w s r p producer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPProducer remove(long wsrpProducerId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchProducerException;

	public com.liferay.wsrp.model.WSRPProducer updateImpl(
		com.liferay.wsrp.model.WSRPProducer wsrpProducer, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the w s r p producer with the primary key or throws a {@link com.liferay.wsrp.NoSuchProducerException} if it could not be found.
	*
	* @param wsrpProducerId the primary key of the w s r p producer
	* @return the w s r p producer
	* @throws com.liferay.wsrp.NoSuchProducerException if a w s r p producer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPProducer findByPrimaryKey(
		long wsrpProducerId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchProducerException;

	/**
	* Returns the w s r p producer with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param wsrpProducerId the primary key of the w s r p producer
	* @return the w s r p producer, or <code>null</code> if a w s r p producer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPProducer fetchByPrimaryKey(
		long wsrpProducerId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the w s r p producers where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching w s r p producers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.wsrp.model.WSRPProducer> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the w s r p producers where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of w s r p producers
	* @param end the upper bound of the range of w s r p producers (not inclusive)
	* @return the range of matching w s r p producers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.wsrp.model.WSRPProducer> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the w s r p producers where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of w s r p producers
	* @param end the upper bound of the range of w s r p producers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching w s r p producers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.wsrp.model.WSRPProducer> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first w s r p producer in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching w s r p producer
	* @throws com.liferay.wsrp.NoSuchProducerException if a matching w s r p producer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPProducer findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchProducerException;

	/**
	* Returns the first w s r p producer in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching w s r p producer, or <code>null</code> if a matching w s r p producer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPProducer fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last w s r p producer in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching w s r p producer
	* @throws com.liferay.wsrp.NoSuchProducerException if a matching w s r p producer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPProducer findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchProducerException;

	/**
	* Returns the last w s r p producer in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching w s r p producer, or <code>null</code> if a matching w s r p producer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPProducer fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the w s r p producers before and after the current w s r p producer in the ordered set where uuid = &#63;.
	*
	* @param wsrpProducerId the primary key of the current w s r p producer
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next w s r p producer
	* @throws com.liferay.wsrp.NoSuchProducerException if a w s r p producer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPProducer[] findByUuid_PrevAndNext(
		long wsrpProducerId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchProducerException;

	/**
	* Returns the w s r p producer where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.wsrp.NoSuchProducerException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching w s r p producer
	* @throws com.liferay.wsrp.NoSuchProducerException if a matching w s r p producer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPProducer findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchProducerException;

	/**
	* Returns the w s r p producer where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching w s r p producer, or <code>null</code> if a matching w s r p producer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPProducer fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the w s r p producer where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching w s r p producer, or <code>null</code> if a matching w s r p producer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPProducer fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the w s r p producers where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching w s r p producers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.wsrp.model.WSRPProducer> findByUuid_C(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the w s r p producers where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of w s r p producers
	* @param end the upper bound of the range of w s r p producers (not inclusive)
	* @return the range of matching w s r p producers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.wsrp.model.WSRPProducer> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the w s r p producers where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of w s r p producers
	* @param end the upper bound of the range of w s r p producers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching w s r p producers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.wsrp.model.WSRPProducer> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first w s r p producer in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching w s r p producer
	* @throws com.liferay.wsrp.NoSuchProducerException if a matching w s r p producer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPProducer findByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchProducerException;

	/**
	* Returns the first w s r p producer in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching w s r p producer, or <code>null</code> if a matching w s r p producer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPProducer fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last w s r p producer in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching w s r p producer
	* @throws com.liferay.wsrp.NoSuchProducerException if a matching w s r p producer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPProducer findByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchProducerException;

	/**
	* Returns the last w s r p producer in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching w s r p producer, or <code>null</code> if a matching w s r p producer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPProducer fetchByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the w s r p producers before and after the current w s r p producer in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param wsrpProducerId the primary key of the current w s r p producer
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next w s r p producer
	* @throws com.liferay.wsrp.NoSuchProducerException if a w s r p producer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPProducer[] findByUuid_C_PrevAndNext(
		long wsrpProducerId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchProducerException;

	/**
	* Returns all the w s r p producers where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching w s r p producers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.wsrp.model.WSRPProducer> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the w s r p producers where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of w s r p producers
	* @param end the upper bound of the range of w s r p producers (not inclusive)
	* @return the range of matching w s r p producers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.wsrp.model.WSRPProducer> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the w s r p producers where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of w s r p producers
	* @param end the upper bound of the range of w s r p producers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching w s r p producers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.wsrp.model.WSRPProducer> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first w s r p producer in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching w s r p producer
	* @throws com.liferay.wsrp.NoSuchProducerException if a matching w s r p producer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPProducer findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchProducerException;

	/**
	* Returns the first w s r p producer in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching w s r p producer, or <code>null</code> if a matching w s r p producer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPProducer fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last w s r p producer in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching w s r p producer
	* @throws com.liferay.wsrp.NoSuchProducerException if a matching w s r p producer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPProducer findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchProducerException;

	/**
	* Returns the last w s r p producer in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching w s r p producer, or <code>null</code> if a matching w s r p producer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPProducer fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the w s r p producers before and after the current w s r p producer in the ordered set where companyId = &#63;.
	*
	* @param wsrpProducerId the primary key of the current w s r p producer
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next w s r p producer
	* @throws com.liferay.wsrp.NoSuchProducerException if a w s r p producer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPProducer[] findByCompanyId_PrevAndNext(
		long wsrpProducerId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchProducerException;

	/**
	* Returns all the w s r p producers.
	*
	* @return the w s r p producers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.wsrp.model.WSRPProducer> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the w s r p producers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of w s r p producers
	* @param end the upper bound of the range of w s r p producers (not inclusive)
	* @return the range of w s r p producers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.wsrp.model.WSRPProducer> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the w s r p producers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of w s r p producers
	* @param end the upper bound of the range of w s r p producers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of w s r p producers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.wsrp.model.WSRPProducer> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the w s r p producers where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the w s r p producer where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the w s r p producer that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPProducer removeByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchProducerException;

	/**
	* Removes all the w s r p producers where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the w s r p producers where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the w s r p producers from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of w s r p producers where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching w s r p producers
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of w s r p producers where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching w s r p producers
	* @throws SystemException if a system exception occurred
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of w s r p producers where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching w s r p producers
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of w s r p producers where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching w s r p producers
	* @throws SystemException if a system exception occurred
	*/
	public int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of w s r p producers.
	*
	* @return the number of w s r p producers
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}