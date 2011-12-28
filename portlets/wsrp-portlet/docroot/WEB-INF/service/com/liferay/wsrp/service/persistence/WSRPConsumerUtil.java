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

import com.liferay.wsrp.model.WSRPConsumer;

import java.util.List;

/**
 * The persistence utility for the w s r p consumer service. This utility wraps {@link WSRPConsumerPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WSRPConsumerPersistence
 * @see WSRPConsumerPersistenceImpl
 * @generated
 */
public class WSRPConsumerUtil {
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
	public static void clearCache(WSRPConsumer wsrpConsumer) {
		getPersistence().clearCache(wsrpConsumer);
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
	public static List<WSRPConsumer> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<WSRPConsumer> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<WSRPConsumer> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static WSRPConsumer update(WSRPConsumer wsrpConsumer, boolean merge)
		throws SystemException {
		return getPersistence().update(wsrpConsumer, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static WSRPConsumer update(WSRPConsumer wsrpConsumer, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(wsrpConsumer, merge, serviceContext);
	}

	/**
	* Caches the w s r p consumer in the entity cache if it is enabled.
	*
	* @param wsrpConsumer the w s r p consumer
	*/
	public static void cacheResult(
		com.liferay.wsrp.model.WSRPConsumer wsrpConsumer) {
		getPersistence().cacheResult(wsrpConsumer);
	}

	/**
	* Caches the w s r p consumers in the entity cache if it is enabled.
	*
	* @param wsrpConsumers the w s r p consumers
	*/
	public static void cacheResult(
		java.util.List<com.liferay.wsrp.model.WSRPConsumer> wsrpConsumers) {
		getPersistence().cacheResult(wsrpConsumers);
	}

	/**
	* Creates a new w s r p consumer with the primary key. Does not add the w s r p consumer to the database.
	*
	* @param wsrpConsumerId the primary key for the new w s r p consumer
	* @return the new w s r p consumer
	*/
	public static com.liferay.wsrp.model.WSRPConsumer create(
		long wsrpConsumerId) {
		return getPersistence().create(wsrpConsumerId);
	}

	/**
	* Removes the w s r p consumer with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param wsrpConsumerId the primary key of the w s r p consumer
	* @return the w s r p consumer that was removed
	* @throws com.liferay.wsrp.NoSuchConsumerException if a w s r p consumer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.wsrp.model.WSRPConsumer remove(
		long wsrpConsumerId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerException {
		return getPersistence().remove(wsrpConsumerId);
	}

	public static com.liferay.wsrp.model.WSRPConsumer updateImpl(
		com.liferay.wsrp.model.WSRPConsumer wsrpConsumer, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(wsrpConsumer, merge);
	}

	/**
	* Returns the w s r p consumer with the primary key or throws a {@link com.liferay.wsrp.NoSuchConsumerException} if it could not be found.
	*
	* @param wsrpConsumerId the primary key of the w s r p consumer
	* @return the w s r p consumer
	* @throws com.liferay.wsrp.NoSuchConsumerException if a w s r p consumer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.wsrp.model.WSRPConsumer findByPrimaryKey(
		long wsrpConsumerId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerException {
		return getPersistence().findByPrimaryKey(wsrpConsumerId);
	}

	/**
	* Returns the w s r p consumer with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param wsrpConsumerId the primary key of the w s r p consumer
	* @return the w s r p consumer, or <code>null</code> if a w s r p consumer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.wsrp.model.WSRPConsumer fetchByPrimaryKey(
		long wsrpConsumerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(wsrpConsumerId);
	}

	/**
	* Returns all the w s r p consumers where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching w s r p consumers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.wsrp.model.WSRPConsumer> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid);
	}

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
	public static java.util.List<com.liferay.wsrp.model.WSRPConsumer> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static java.util.List<com.liferay.wsrp.model.WSRPConsumer> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the first w s r p consumer in the ordered set where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching w s r p consumer
	* @throws com.liferay.wsrp.NoSuchConsumerException if a matching w s r p consumer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.wsrp.model.WSRPConsumer findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last w s r p consumer in the ordered set where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching w s r p consumer
	* @throws com.liferay.wsrp.NoSuchConsumerException if a matching w s r p consumer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.wsrp.model.WSRPConsumer findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the w s r p consumers before and after the current w s r p consumer in the ordered set where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param wsrpConsumerId the primary key of the current w s r p consumer
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next w s r p consumer
	* @throws com.liferay.wsrp.NoSuchConsumerException if a w s r p consumer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.wsrp.model.WSRPConsumer[] findByUuid_PrevAndNext(
		long wsrpConsumerId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerException {
		return getPersistence()
				   .findByUuid_PrevAndNext(wsrpConsumerId, uuid,
			orderByComparator);
	}

	/**
	* Returns all the w s r p consumers where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching w s r p consumers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.wsrp.model.WSRPConsumer> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId);
	}

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
	public static java.util.List<com.liferay.wsrp.model.WSRPConsumer> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

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
	public static java.util.List<com.liferay.wsrp.model.WSRPConsumer> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first w s r p consumer in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching w s r p consumer
	* @throws com.liferay.wsrp.NoSuchConsumerException if a matching w s r p consumer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.wsrp.model.WSRPConsumer findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last w s r p consumer in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching w s r p consumer
	* @throws com.liferay.wsrp.NoSuchConsumerException if a matching w s r p consumer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.wsrp.model.WSRPConsumer findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the w s r p consumers before and after the current w s r p consumer in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param wsrpConsumerId the primary key of the current w s r p consumer
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next w s r p consumer
	* @throws com.liferay.wsrp.NoSuchConsumerException if a w s r p consumer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.wsrp.model.WSRPConsumer[] findByCompanyId_PrevAndNext(
		long wsrpConsumerId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(wsrpConsumerId, companyId,
			orderByComparator);
	}

	/**
	* Returns all the w s r p consumers.
	*
	* @return the w s r p consumers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.wsrp.model.WSRPConsumer> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<com.liferay.wsrp.model.WSRPConsumer> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.liferay.wsrp.model.WSRPConsumer> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the w s r p consumers where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Removes all the w s r p consumers where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Removes all the w s r p consumers from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of w s r p consumers where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching w s r p consumers
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the number of w s r p consumers where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching w s r p consumers
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Returns the number of w s r p consumers.
	*
	* @return the number of w s r p consumers
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static WSRPConsumerPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (WSRPConsumerPersistence)PortletBeanLocatorUtil.locate(com.liferay.wsrp.service.ClpSerializer.getServletContextName(),
					WSRPConsumerPersistence.class.getName());

			ReferenceRegistry.registerReference(WSRPConsumerUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(WSRPConsumerPersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(WSRPConsumerUtil.class,
			"_persistence");
	}

	private static WSRPConsumerPersistence _persistence;
}