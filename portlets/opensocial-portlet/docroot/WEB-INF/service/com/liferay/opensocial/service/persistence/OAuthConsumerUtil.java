/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.opensocial.service.persistence;

import com.liferay.opensocial.model.OAuthConsumer;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the o auth consumer service. This utility wraps {@link OAuthConsumerPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OAuthConsumerPersistence
 * @see OAuthConsumerPersistenceImpl
 * @generated
 */
public class OAuthConsumerUtil {
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
	public static void clearCache(OAuthConsumer oAuthConsumer) {
		getPersistence().clearCache(oAuthConsumer);
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
	public static List<OAuthConsumer> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<OAuthConsumer> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<OAuthConsumer> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static OAuthConsumer remove(OAuthConsumer oAuthConsumer)
		throws SystemException {
		return getPersistence().remove(oAuthConsumer);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static OAuthConsumer update(OAuthConsumer oAuthConsumer,
		boolean merge) throws SystemException {
		return getPersistence().update(oAuthConsumer, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static OAuthConsumer update(OAuthConsumer oAuthConsumer,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(oAuthConsumer, merge, serviceContext);
	}

	/**
	* Caches the o auth consumer in the entity cache if it is enabled.
	*
	* @param oAuthConsumer the o auth consumer to cache
	*/
	public static void cacheResult(
		com.liferay.opensocial.model.OAuthConsumer oAuthConsumer) {
		getPersistence().cacheResult(oAuthConsumer);
	}

	/**
	* Caches the o auth consumers in the entity cache if it is enabled.
	*
	* @param oAuthConsumers the o auth consumers to cache
	*/
	public static void cacheResult(
		java.util.List<com.liferay.opensocial.model.OAuthConsumer> oAuthConsumers) {
		getPersistence().cacheResult(oAuthConsumers);
	}

	/**
	* Creates a new o auth consumer with the primary key. Does not add the o auth consumer to the database.
	*
	* @param oauthConsumerId the primary key for the new o auth consumer
	* @return the new o auth consumer
	*/
	public static com.liferay.opensocial.model.OAuthConsumer create(
		long oauthConsumerId) {
		return getPersistence().create(oauthConsumerId);
	}

	/**
	* Removes the o auth consumer with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param oauthConsumerId the primary key of the o auth consumer to remove
	* @return the o auth consumer that was removed
	* @throws com.liferay.opensocial.NoSuchOAuthConsumerException if a o auth consumer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.OAuthConsumer remove(
		long oauthConsumerId)
		throws com.liferay.opensocial.NoSuchOAuthConsumerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(oauthConsumerId);
	}

	public static com.liferay.opensocial.model.OAuthConsumer updateImpl(
		com.liferay.opensocial.model.OAuthConsumer oAuthConsumer, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(oAuthConsumer, merge);
	}

	/**
	* Finds the o auth consumer with the primary key or throws a {@link com.liferay.opensocial.NoSuchOAuthConsumerException} if it could not be found.
	*
	* @param oauthConsumerId the primary key of the o auth consumer to find
	* @return the o auth consumer
	* @throws com.liferay.opensocial.NoSuchOAuthConsumerException if a o auth consumer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.OAuthConsumer findByPrimaryKey(
		long oauthConsumerId)
		throws com.liferay.opensocial.NoSuchOAuthConsumerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(oauthConsumerId);
	}

	/**
	* Finds the o auth consumer with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param oauthConsumerId the primary key of the o auth consumer to find
	* @return the o auth consumer, or <code>null</code> if a o auth consumer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.OAuthConsumer fetchByPrimaryKey(
		long oauthConsumerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(oauthConsumerId);
	}

	/**
	* Finds all the o auth consumers where gadgetId = &#63;.
	*
	* @param gadgetId the gadget id to search with
	* @return the matching o auth consumers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.opensocial.model.OAuthConsumer> findByGadgetId(
		long gadgetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGadgetId(gadgetId);
	}

	/**
	* Finds a range of all the o auth consumers where gadgetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param gadgetId the gadget id to search with
	* @param start the lower bound of the range of o auth consumers to return
	* @param end the upper bound of the range of o auth consumers to return (not inclusive)
	* @return the range of matching o auth consumers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.opensocial.model.OAuthConsumer> findByGadgetId(
		long gadgetId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGadgetId(gadgetId, start, end);
	}

	/**
	* Finds an ordered range of all the o auth consumers where gadgetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param gadgetId the gadget id to search with
	* @param start the lower bound of the range of o auth consumers to return
	* @param end the upper bound of the range of o auth consumers to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching o auth consumers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.opensocial.model.OAuthConsumer> findByGadgetId(
		long gadgetId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGadgetId(gadgetId, start, end, orderByComparator);
	}

	/**
	* Finds the first o auth consumer in the ordered set where gadgetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param gadgetId the gadget id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching o auth consumer
	* @throws com.liferay.opensocial.NoSuchOAuthConsumerException if a matching o auth consumer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.OAuthConsumer findByGadgetId_First(
		long gadgetId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.opensocial.NoSuchOAuthConsumerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGadgetId_First(gadgetId, orderByComparator);
	}

	/**
	* Finds the last o auth consumer in the ordered set where gadgetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param gadgetId the gadget id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching o auth consumer
	* @throws com.liferay.opensocial.NoSuchOAuthConsumerException if a matching o auth consumer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.OAuthConsumer findByGadgetId_Last(
		long gadgetId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.opensocial.NoSuchOAuthConsumerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGadgetId_Last(gadgetId, orderByComparator);
	}

	/**
	* Finds the o auth consumers before and after the current o auth consumer in the ordered set where gadgetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param oauthConsumerId the primary key of the current o auth consumer
	* @param gadgetId the gadget id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next o auth consumer
	* @throws com.liferay.opensocial.NoSuchOAuthConsumerException if a o auth consumer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.OAuthConsumer[] findByGadgetId_PrevAndNext(
		long oauthConsumerId, long gadgetId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.opensocial.NoSuchOAuthConsumerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGadgetId_PrevAndNext(oauthConsumerId, gadgetId,
			orderByComparator);
	}

	/**
	* Finds the o auth consumer where gadgetId = &#63; and serviceName = &#63; or throws a {@link com.liferay.opensocial.NoSuchOAuthConsumerException} if it could not be found.
	*
	* @param gadgetId the gadget id to search with
	* @param serviceName the service name to search with
	* @return the matching o auth consumer
	* @throws com.liferay.opensocial.NoSuchOAuthConsumerException if a matching o auth consumer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.OAuthConsumer findByG_S(
		long gadgetId, java.lang.String serviceName)
		throws com.liferay.opensocial.NoSuchOAuthConsumerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_S(gadgetId, serviceName);
	}

	/**
	* Finds the o auth consumer where gadgetId = &#63; and serviceName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param gadgetId the gadget id to search with
	* @param serviceName the service name to search with
	* @return the matching o auth consumer, or <code>null</code> if a matching o auth consumer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.OAuthConsumer fetchByG_S(
		long gadgetId, java.lang.String serviceName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByG_S(gadgetId, serviceName);
	}

	/**
	* Finds the o auth consumer where gadgetId = &#63; and serviceName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param gadgetId the gadget id to search with
	* @param serviceName the service name to search with
	* @return the matching o auth consumer, or <code>null</code> if a matching o auth consumer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.OAuthConsumer fetchByG_S(
		long gadgetId, java.lang.String serviceName, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_S(gadgetId, serviceName, retrieveFromCache);
	}

	/**
	* Finds all the o auth consumers.
	*
	* @return the o auth consumers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.opensocial.model.OAuthConsumer> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Finds a range of all the o auth consumers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of o auth consumers to return
	* @param end the upper bound of the range of o auth consumers to return (not inclusive)
	* @return the range of o auth consumers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.opensocial.model.OAuthConsumer> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Finds an ordered range of all the o auth consumers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of o auth consumers to return
	* @param end the upper bound of the range of o auth consumers to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of o auth consumers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.opensocial.model.OAuthConsumer> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the o auth consumers where gadgetId = &#63; from the database.
	*
	* @param gadgetId the gadget id to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByGadgetId(long gadgetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByGadgetId(gadgetId);
	}

	/**
	* Removes the o auth consumer where gadgetId = &#63; and serviceName = &#63; from the database.
	*
	* @param gadgetId the gadget id to search with
	* @param serviceName the service name to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_S(long gadgetId, java.lang.String serviceName)
		throws com.liferay.opensocial.NoSuchOAuthConsumerException,
			com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_S(gadgetId, serviceName);
	}

	/**
	* Removes all the o auth consumers from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Counts all the o auth consumers where gadgetId = &#63;.
	*
	* @param gadgetId the gadget id to search with
	* @return the number of matching o auth consumers
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGadgetId(long gadgetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByGadgetId(gadgetId);
	}

	/**
	* Counts all the o auth consumers where gadgetId = &#63; and serviceName = &#63;.
	*
	* @param gadgetId the gadget id to search with
	* @param serviceName the service name to search with
	* @return the number of matching o auth consumers
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_S(long gadgetId, java.lang.String serviceName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_S(gadgetId, serviceName);
	}

	/**
	* Counts all the o auth consumers.
	*
	* @return the number of o auth consumers
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static OAuthConsumerPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (OAuthConsumerPersistence)PortletBeanLocatorUtil.locate(com.liferay.opensocial.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					OAuthConsumerPersistence.class.getName());

			ReferenceRegistry.registerReference(OAuthConsumerUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(OAuthConsumerPersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(OAuthConsumerUtil.class,
			"_persistence");
	}

	private static OAuthConsumerPersistence _persistence;
}