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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.liferay.wsrp.model.WSRPProducer;

import java.util.List;

/**
 * The persistence utility for the w s r p producer service. This utility wraps {@link com.liferay.wsrp.service.persistence.impl.WSRPProducerPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WSRPProducerPersistence
 * @see com.liferay.wsrp.service.persistence.impl.WSRPProducerPersistenceImpl
 * @generated
 */
@ProviderType
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
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<WSRPProducer> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<WSRPProducer> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<WSRPProducer> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<WSRPProducer> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static WSRPProducer update(WSRPProducer wsrpProducer) {
		return getPersistence().update(wsrpProducer);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static WSRPProducer update(WSRPProducer wsrpProducer,
		ServiceContext serviceContext) {
		return getPersistence().update(wsrpProducer, serviceContext);
	}

	/**
	* Returns all the w s r p producers where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching w s r p producers
	*/
	public static List<WSRPProducer> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the w s r p producers where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPProducerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of w s r p producers
	* @param end the upper bound of the range of w s r p producers (not inclusive)
	* @return the range of matching w s r p producers
	*/
	public static List<WSRPProducer> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the w s r p producers where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPProducerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of w s r p producers
	* @param end the upper bound of the range of w s r p producers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching w s r p producers
	*/
	public static List<WSRPProducer> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<WSRPProducer> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the first w s r p producer in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching w s r p producer
	* @throws NoSuchProducerException if a matching w s r p producer could not be found
	*/
	public static WSRPProducer findByUuid_First(java.lang.String uuid,
		OrderByComparator<WSRPProducer> orderByComparator)
		throws com.liferay.wsrp.NoSuchProducerException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first w s r p producer in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching w s r p producer, or <code>null</code> if a matching w s r p producer could not be found
	*/
	public static WSRPProducer fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<WSRPProducer> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last w s r p producer in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching w s r p producer
	* @throws NoSuchProducerException if a matching w s r p producer could not be found
	*/
	public static WSRPProducer findByUuid_Last(java.lang.String uuid,
		OrderByComparator<WSRPProducer> orderByComparator)
		throws com.liferay.wsrp.NoSuchProducerException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last w s r p producer in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching w s r p producer, or <code>null</code> if a matching w s r p producer could not be found
	*/
	public static WSRPProducer fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<WSRPProducer> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the w s r p producers before and after the current w s r p producer in the ordered set where uuid = &#63;.
	*
	* @param wsrpProducerId the primary key of the current w s r p producer
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next w s r p producer
	* @throws NoSuchProducerException if a w s r p producer with the primary key could not be found
	*/
	public static WSRPProducer[] findByUuid_PrevAndNext(long wsrpProducerId,
		java.lang.String uuid, OrderByComparator<WSRPProducer> orderByComparator)
		throws com.liferay.wsrp.NoSuchProducerException {
		return getPersistence()
				   .findByUuid_PrevAndNext(wsrpProducerId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the w s r p producers where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of w s r p producers where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching w s r p producers
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the w s r p producer where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchProducerException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching w s r p producer
	* @throws NoSuchProducerException if a matching w s r p producer could not be found
	*/
	public static WSRPProducer findByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.wsrp.NoSuchProducerException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the w s r p producer where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching w s r p producer, or <code>null</code> if a matching w s r p producer could not be found
	*/
	public static WSRPProducer fetchByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the w s r p producer where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching w s r p producer, or <code>null</code> if a matching w s r p producer could not be found
	*/
	public static WSRPProducer fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the w s r p producer where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the w s r p producer that was removed
	*/
	public static WSRPProducer removeByUUID_G(java.lang.String uuid,
		long groupId) throws com.liferay.wsrp.NoSuchProducerException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of w s r p producers where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching w s r p producers
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the w s r p producers where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching w s r p producers
	*/
	public static List<WSRPProducer> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the w s r p producers where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPProducerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of w s r p producers
	* @param end the upper bound of the range of w s r p producers (not inclusive)
	* @return the range of matching w s r p producers
	*/
	public static List<WSRPProducer> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the w s r p producers where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPProducerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of w s r p producers
	* @param end the upper bound of the range of w s r p producers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching w s r p producers
	*/
	public static List<WSRPProducer> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<WSRPProducer> orderByComparator) {
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
	* @throws NoSuchProducerException if a matching w s r p producer could not be found
	*/
	public static WSRPProducer findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<WSRPProducer> orderByComparator)
		throws com.liferay.wsrp.NoSuchProducerException {
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
	*/
	public static WSRPProducer fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<WSRPProducer> orderByComparator) {
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
	* @throws NoSuchProducerException if a matching w s r p producer could not be found
	*/
	public static WSRPProducer findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<WSRPProducer> orderByComparator)
		throws com.liferay.wsrp.NoSuchProducerException {
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
	*/
	public static WSRPProducer fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<WSRPProducer> orderByComparator) {
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
	* @throws NoSuchProducerException if a w s r p producer with the primary key could not be found
	*/
	public static WSRPProducer[] findByUuid_C_PrevAndNext(long wsrpProducerId,
		java.lang.String uuid, long companyId,
		OrderByComparator<WSRPProducer> orderByComparator)
		throws com.liferay.wsrp.NoSuchProducerException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(wsrpProducerId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the w s r p producers where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of w s r p producers where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching w s r p producers
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the w s r p producers where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching w s r p producers
	*/
	public static List<WSRPProducer> findByCompanyId(long companyId) {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	* Returns a range of all the w s r p producers where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPProducerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of w s r p producers
	* @param end the upper bound of the range of w s r p producers (not inclusive)
	* @return the range of matching w s r p producers
	*/
	public static List<WSRPProducer> findByCompanyId(long companyId, int start,
		int end) {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the w s r p producers where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPProducerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of w s r p producers
	* @param end the upper bound of the range of w s r p producers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching w s r p producers
	*/
	public static List<WSRPProducer> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<WSRPProducer> orderByComparator) {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first w s r p producer in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching w s r p producer
	* @throws NoSuchProducerException if a matching w s r p producer could not be found
	*/
	public static WSRPProducer findByCompanyId_First(long companyId,
		OrderByComparator<WSRPProducer> orderByComparator)
		throws com.liferay.wsrp.NoSuchProducerException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the first w s r p producer in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching w s r p producer, or <code>null</code> if a matching w s r p producer could not be found
	*/
	public static WSRPProducer fetchByCompanyId_First(long companyId,
		OrderByComparator<WSRPProducer> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last w s r p producer in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching w s r p producer
	* @throws NoSuchProducerException if a matching w s r p producer could not be found
	*/
	public static WSRPProducer findByCompanyId_Last(long companyId,
		OrderByComparator<WSRPProducer> orderByComparator)
		throws com.liferay.wsrp.NoSuchProducerException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the last w s r p producer in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching w s r p producer, or <code>null</code> if a matching w s r p producer could not be found
	*/
	public static WSRPProducer fetchByCompanyId_Last(long companyId,
		OrderByComparator<WSRPProducer> orderByComparator) {
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
	* @throws NoSuchProducerException if a w s r p producer with the primary key could not be found
	*/
	public static WSRPProducer[] findByCompanyId_PrevAndNext(
		long wsrpProducerId, long companyId,
		OrderByComparator<WSRPProducer> orderByComparator)
		throws com.liferay.wsrp.NoSuchProducerException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(wsrpProducerId, companyId,
			orderByComparator);
	}

	/**
	* Removes all the w s r p producers where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public static void removeByCompanyId(long companyId) {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Returns the number of w s r p producers where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching w s r p producers
	*/
	public static int countByCompanyId(long companyId) {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Caches the w s r p producer in the entity cache if it is enabled.
	*
	* @param wsrpProducer the w s r p producer
	*/
	public static void cacheResult(WSRPProducer wsrpProducer) {
		getPersistence().cacheResult(wsrpProducer);
	}

	/**
	* Caches the w s r p producers in the entity cache if it is enabled.
	*
	* @param wsrpProducers the w s r p producers
	*/
	public static void cacheResult(List<WSRPProducer> wsrpProducers) {
		getPersistence().cacheResult(wsrpProducers);
	}

	/**
	* Creates a new w s r p producer with the primary key. Does not add the w s r p producer to the database.
	*
	* @param wsrpProducerId the primary key for the new w s r p producer
	* @return the new w s r p producer
	*/
	public static WSRPProducer create(long wsrpProducerId) {
		return getPersistence().create(wsrpProducerId);
	}

	/**
	* Removes the w s r p producer with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param wsrpProducerId the primary key of the w s r p producer
	* @return the w s r p producer that was removed
	* @throws NoSuchProducerException if a w s r p producer with the primary key could not be found
	*/
	public static WSRPProducer remove(long wsrpProducerId)
		throws com.liferay.wsrp.NoSuchProducerException {
		return getPersistence().remove(wsrpProducerId);
	}

	public static WSRPProducer updateImpl(WSRPProducer wsrpProducer) {
		return getPersistence().updateImpl(wsrpProducer);
	}

	/**
	* Returns the w s r p producer with the primary key or throws a {@link NoSuchProducerException} if it could not be found.
	*
	* @param wsrpProducerId the primary key of the w s r p producer
	* @return the w s r p producer
	* @throws NoSuchProducerException if a w s r p producer with the primary key could not be found
	*/
	public static WSRPProducer findByPrimaryKey(long wsrpProducerId)
		throws com.liferay.wsrp.NoSuchProducerException {
		return getPersistence().findByPrimaryKey(wsrpProducerId);
	}

	/**
	* Returns the w s r p producer with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param wsrpProducerId the primary key of the w s r p producer
	* @return the w s r p producer, or <code>null</code> if a w s r p producer with the primary key could not be found
	*/
	public static WSRPProducer fetchByPrimaryKey(long wsrpProducerId) {
		return getPersistence().fetchByPrimaryKey(wsrpProducerId);
	}

	public static java.util.Map<java.io.Serializable, WSRPProducer> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the w s r p producers.
	*
	* @return the w s r p producers
	*/
	public static List<WSRPProducer> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the w s r p producers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPProducerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of w s r p producers
	* @param end the upper bound of the range of w s r p producers (not inclusive)
	* @return the range of w s r p producers
	*/
	public static List<WSRPProducer> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the w s r p producers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPProducerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of w s r p producers
	* @param end the upper bound of the range of w s r p producers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of w s r p producers
	*/
	public static List<WSRPProducer> findAll(int start, int end,
		OrderByComparator<WSRPProducer> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the w s r p producers from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of w s r p producers.
	*
	* @return the number of w s r p producers
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
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
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setPersistence(WSRPProducerPersistence persistence) {
	}

	private static WSRPProducerPersistence _persistence;
}