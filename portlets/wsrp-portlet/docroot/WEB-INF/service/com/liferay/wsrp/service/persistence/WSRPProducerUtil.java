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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.liferay.wsrp.model.WSRPProducer;

import java.util.List;

/**
 * The persistence utility for the w s r p producer service. This utility wraps {@link WSRPProducerPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WSRPProducerPersistence
 * @see WSRPProducerPersistenceImpl
 * @generated
 */
public class WSRPProducerUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(WSRPProducer wsrpProducer) {
		getPersistence().clearCache(wsrpProducer);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<WSRPProducer> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<WSRPProducer> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<WSRPProducer> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static WSRPProducer update(WSRPProducer wsrpProducer, boolean merge)
		throws SystemException {
		return getPersistence().update(wsrpProducer, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static WSRPProducer update(WSRPProducer wsrpProducer, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(wsrpProducer, merge, serviceContext);
	}

	/**
	* Caches the w s r p producer in the entity cache if it is enabled.
	*
	* @param wsrpProducer the w s r p producer
	*/
	public static void cacheResult(
		com.liferay.wsrp.model.WSRPProducer wsrpProducer) {
		getPersistence().cacheResult(wsrpProducer);
	}

	/**
	* Caches the w s r p producers in the entity cache if it is enabled.
	*
	* @param wsrpProducers the w s r p producers
	*/
	public static void cacheResult(
		java.util.List<com.liferay.wsrp.model.WSRPProducer> wsrpProducers) {
		getPersistence().cacheResult(wsrpProducers);
	}

	/**
	* Creates a new w s r p producer with the primary key. Does not add the w s r p producer to the database.
	*
	* @param wsrpProducerId the primary key for the new w s r p producer
	* @return the new w s r p producer
	*/
	public static com.liferay.wsrp.model.WSRPProducer create(
		long wsrpProducerId) {
		return getPersistence().create(wsrpProducerId);
	}

	/**
	* Removes the w s r p producer with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param wsrpProducerId the primary key of the w s r p producer
	* @return the w s r p producer that was removed
	* @throws com.liferay.wsrp.NoSuchProducerException if a w s r p producer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.wsrp.model.WSRPProducer remove(
		long wsrpProducerId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchProducerException {
		return getPersistence().remove(wsrpProducerId);
	}

	public static com.liferay.wsrp.model.WSRPProducer updateImpl(
		com.liferay.wsrp.model.WSRPProducer wsrpProducer, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(wsrpProducer, merge);
	}

	/**
	* Returns the w s r p producer with the primary key or throws a {@link com.liferay.wsrp.NoSuchProducerException} if it could not be found.
	*
	* @param wsrpProducerId the primary key of the w s r p producer
	* @return the w s r p producer
	* @throws com.liferay.wsrp.NoSuchProducerException if a w s r p producer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.wsrp.model.WSRPProducer findByPrimaryKey(
		long wsrpProducerId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchProducerException {
		return getPersistence().findByPrimaryKey(wsrpProducerId);
	}

	/**
	* Returns the w s r p producer with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param wsrpProducerId the primary key of the w s r p producer
	* @return the w s r p producer, or <code>null</code> if a w s r p producer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.wsrp.model.WSRPProducer fetchByPrimaryKey(
		long wsrpProducerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(wsrpProducerId);
	}

	/**
	* Returns all the w s r p producers where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching w s r p producers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.wsrp.model.WSRPProducer> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid);
	}

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
	public static java.util.List<com.liferay.wsrp.model.WSRPProducer> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static java.util.List<com.liferay.wsrp.model.WSRPProducer> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the first w s r p producer in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching w s r p producer
	* @throws com.liferay.wsrp.NoSuchProducerException if a matching w s r p producer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.wsrp.model.WSRPProducer findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchProducerException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first w s r p producer in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching w s r p producer, or <code>null</code> if a matching w s r p producer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.wsrp.model.WSRPProducer fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last w s r p producer in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching w s r p producer
	* @throws com.liferay.wsrp.NoSuchProducerException if a matching w s r p producer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.wsrp.model.WSRPProducer findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchProducerException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last w s r p producer in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching w s r p producer, or <code>null</code> if a matching w s r p producer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.wsrp.model.WSRPProducer fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

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
	public static com.liferay.wsrp.model.WSRPProducer[] findByUuid_PrevAndNext(
		long wsrpProducerId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchProducerException {
		return getPersistence()
				   .findByUuid_PrevAndNext(wsrpProducerId, uuid,
			orderByComparator);
	}

	/**
	* Returns the w s r p producer where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.wsrp.NoSuchProducerException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching w s r p producer
	* @throws com.liferay.wsrp.NoSuchProducerException if a matching w s r p producer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.wsrp.model.WSRPProducer findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchProducerException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the w s r p producer where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching w s r p producer, or <code>null</code> if a matching w s r p producer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.wsrp.model.WSRPProducer fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the w s r p producer where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching w s r p producer, or <code>null</code> if a matching w s r p producer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.wsrp.model.WSRPProducer fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Returns all the w s r p producers where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching w s r p producers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.wsrp.model.WSRPProducer> findByUuid_C(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

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
	public static java.util.List<com.liferay.wsrp.model.WSRPProducer> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

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
	public static java.util.List<com.liferay.wsrp.model.WSRPProducer> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

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
	public static com.liferay.wsrp.model.WSRPProducer findByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchProducerException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first w s r p producer in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching w s r p producer, or <code>null</code> if a matching w s r p producer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.wsrp.model.WSRPProducer fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

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
	public static com.liferay.wsrp.model.WSRPProducer findByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchProducerException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last w s r p producer in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching w s r p producer, or <code>null</code> if a matching w s r p producer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.wsrp.model.WSRPProducer fetchByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

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
	public static com.liferay.wsrp.model.WSRPProducer[] findByUuid_C_PrevAndNext(
		long wsrpProducerId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchProducerException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(wsrpProducerId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Returns all the w s r p producers where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching w s r p producers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.wsrp.model.WSRPProducer> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId);
	}

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
	public static java.util.List<com.liferay.wsrp.model.WSRPProducer> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

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
	public static java.util.List<com.liferay.wsrp.model.WSRPProducer> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first w s r p producer in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching w s r p producer
	* @throws com.liferay.wsrp.NoSuchProducerException if a matching w s r p producer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.wsrp.model.WSRPProducer findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchProducerException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the first w s r p producer in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching w s r p producer, or <code>null</code> if a matching w s r p producer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.wsrp.model.WSRPProducer fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last w s r p producer in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching w s r p producer
	* @throws com.liferay.wsrp.NoSuchProducerException if a matching w s r p producer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.wsrp.model.WSRPProducer findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchProducerException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the last w s r p producer in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching w s r p producer, or <code>null</code> if a matching w s r p producer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.wsrp.model.WSRPProducer fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCompanyId_Last(companyId, orderByComparator);
	}

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
	public static com.liferay.wsrp.model.WSRPProducer[] findByCompanyId_PrevAndNext(
		long wsrpProducerId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchProducerException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(wsrpProducerId, companyId,
			orderByComparator);
	}

	/**
	* Returns all the w s r p producers.
	*
	* @return the w s r p producers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.wsrp.model.WSRPProducer> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<com.liferay.wsrp.model.WSRPProducer> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.liferay.wsrp.model.WSRPProducer> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the w s r p producers where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Removes the w s r p producer where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the w s r p producer that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.wsrp.model.WSRPProducer removeByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchProducerException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Removes all the w s r p producers where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Removes all the w s r p producers where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Removes all the w s r p producers from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of w s r p producers where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching w s r p producers
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the number of w s r p producers where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching w s r p producers
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of w s r p producers where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching w s r p producers
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of w s r p producers where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching w s r p producers
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Returns the number of w s r p producers.
	*
	* @return the number of w s r p producers
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static WSRPProducerPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (WSRPProducerPersistence)PortletBeanLocatorUtil.locate(com.liferay.wsrp.service.ClpSerializer.getServletContextName(),
					WSRPProducerPersistence.class.getName());

			ReferenceRegistry.registerReference(WSRPProducerUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(WSRPProducerPersistence persistence) {
	}

	private static WSRPProducerPersistence _persistence;
}