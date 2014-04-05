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
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
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
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static OAuthConsumer update(OAuthConsumer oAuthConsumer)
		throws SystemException {
		return getPersistence().update(oAuthConsumer);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static OAuthConsumer update(OAuthConsumer oAuthConsumer,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(oAuthConsumer, serviceContext);
	}

	/**
	* Returns all the o auth consumers where gadgetKey = &#63;.
	*
	* @param gadgetKey the gadget key
	* @return the matching o auth consumers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.opensocial.model.OAuthConsumer> findByGadgetKey(
		java.lang.String gadgetKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGadgetKey(gadgetKey);
	}

	/**
	* Returns a range of all the o auth consumers where gadgetKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.opensocial.model.impl.OAuthConsumerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param gadgetKey the gadget key
	* @param start the lower bound of the range of o auth consumers
	* @param end the upper bound of the range of o auth consumers (not inclusive)
	* @return the range of matching o auth consumers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.opensocial.model.OAuthConsumer> findByGadgetKey(
		java.lang.String gadgetKey, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGadgetKey(gadgetKey, start, end);
	}

	/**
	* Returns an ordered range of all the o auth consumers where gadgetKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.opensocial.model.impl.OAuthConsumerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param gadgetKey the gadget key
	* @param start the lower bound of the range of o auth consumers
	* @param end the upper bound of the range of o auth consumers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching o auth consumers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.opensocial.model.OAuthConsumer> findByGadgetKey(
		java.lang.String gadgetKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGadgetKey(gadgetKey, start, end, orderByComparator);
	}

	/**
	* Returns the first o auth consumer in the ordered set where gadgetKey = &#63;.
	*
	* @param gadgetKey the gadget key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching o auth consumer
	* @throws com.liferay.opensocial.NoSuchOAuthConsumerException if a matching o auth consumer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.OAuthConsumer findByGadgetKey_First(
		java.lang.String gadgetKey,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.opensocial.NoSuchOAuthConsumerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGadgetKey_First(gadgetKey, orderByComparator);
	}

	/**
	* Returns the first o auth consumer in the ordered set where gadgetKey = &#63;.
	*
	* @param gadgetKey the gadget key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching o auth consumer, or <code>null</code> if a matching o auth consumer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.OAuthConsumer fetchByGadgetKey_First(
		java.lang.String gadgetKey,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGadgetKey_First(gadgetKey, orderByComparator);
	}

	/**
	* Returns the last o auth consumer in the ordered set where gadgetKey = &#63;.
	*
	* @param gadgetKey the gadget key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching o auth consumer
	* @throws com.liferay.opensocial.NoSuchOAuthConsumerException if a matching o auth consumer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.OAuthConsumer findByGadgetKey_Last(
		java.lang.String gadgetKey,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.opensocial.NoSuchOAuthConsumerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGadgetKey_Last(gadgetKey, orderByComparator);
	}

	/**
	* Returns the last o auth consumer in the ordered set where gadgetKey = &#63;.
	*
	* @param gadgetKey the gadget key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching o auth consumer, or <code>null</code> if a matching o auth consumer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.OAuthConsumer fetchByGadgetKey_Last(
		java.lang.String gadgetKey,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGadgetKey_Last(gadgetKey, orderByComparator);
	}

	/**
	* Returns the o auth consumers before and after the current o auth consumer in the ordered set where gadgetKey = &#63;.
	*
	* @param oAuthConsumerId the primary key of the current o auth consumer
	* @param gadgetKey the gadget key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next o auth consumer
	* @throws com.liferay.opensocial.NoSuchOAuthConsumerException if a o auth consumer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.OAuthConsumer[] findByGadgetKey_PrevAndNext(
		long oAuthConsumerId, java.lang.String gadgetKey,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.opensocial.NoSuchOAuthConsumerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGadgetKey_PrevAndNext(oAuthConsumerId, gadgetKey,
			orderByComparator);
	}

	/**
	* Removes all the o auth consumers where gadgetKey = &#63; from the database.
	*
	* @param gadgetKey the gadget key
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByGadgetKey(java.lang.String gadgetKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByGadgetKey(gadgetKey);
	}

	/**
	* Returns the number of o auth consumers where gadgetKey = &#63;.
	*
	* @param gadgetKey the gadget key
	* @return the number of matching o auth consumers
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGadgetKey(java.lang.String gadgetKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByGadgetKey(gadgetKey);
	}

	/**
	* Returns the o auth consumer where gadgetKey = &#63; and serviceName = &#63; or throws a {@link com.liferay.opensocial.NoSuchOAuthConsumerException} if it could not be found.
	*
	* @param gadgetKey the gadget key
	* @param serviceName the service name
	* @return the matching o auth consumer
	* @throws com.liferay.opensocial.NoSuchOAuthConsumerException if a matching o auth consumer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.OAuthConsumer findByG_S(
		java.lang.String gadgetKey, java.lang.String serviceName)
		throws com.liferay.opensocial.NoSuchOAuthConsumerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_S(gadgetKey, serviceName);
	}

	/**
	* Returns the o auth consumer where gadgetKey = &#63; and serviceName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param gadgetKey the gadget key
	* @param serviceName the service name
	* @return the matching o auth consumer, or <code>null</code> if a matching o auth consumer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.OAuthConsumer fetchByG_S(
		java.lang.String gadgetKey, java.lang.String serviceName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByG_S(gadgetKey, serviceName);
	}

	/**
	* Returns the o auth consumer where gadgetKey = &#63; and serviceName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param gadgetKey the gadget key
	* @param serviceName the service name
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching o auth consumer, or <code>null</code> if a matching o auth consumer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.OAuthConsumer fetchByG_S(
		java.lang.String gadgetKey, java.lang.String serviceName,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_S(gadgetKey, serviceName, retrieveFromCache);
	}

	/**
	* Removes the o auth consumer where gadgetKey = &#63; and serviceName = &#63; from the database.
	*
	* @param gadgetKey the gadget key
	* @param serviceName the service name
	* @return the o auth consumer that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.OAuthConsumer removeByG_S(
		java.lang.String gadgetKey, java.lang.String serviceName)
		throws com.liferay.opensocial.NoSuchOAuthConsumerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByG_S(gadgetKey, serviceName);
	}

	/**
	* Returns the number of o auth consumers where gadgetKey = &#63; and serviceName = &#63;.
	*
	* @param gadgetKey the gadget key
	* @param serviceName the service name
	* @return the number of matching o auth consumers
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_S(java.lang.String gadgetKey,
		java.lang.String serviceName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_S(gadgetKey, serviceName);
	}

	/**
	* Caches the o auth consumer in the entity cache if it is enabled.
	*
	* @param oAuthConsumer the o auth consumer
	*/
	public static void cacheResult(
		com.liferay.opensocial.model.OAuthConsumer oAuthConsumer) {
		getPersistence().cacheResult(oAuthConsumer);
	}

	/**
	* Caches the o auth consumers in the entity cache if it is enabled.
	*
	* @param oAuthConsumers the o auth consumers
	*/
	public static void cacheResult(
		java.util.List<com.liferay.opensocial.model.OAuthConsumer> oAuthConsumers) {
		getPersistence().cacheResult(oAuthConsumers);
	}

	/**
	* Creates a new o auth consumer with the primary key. Does not add the o auth consumer to the database.
	*
	* @param oAuthConsumerId the primary key for the new o auth consumer
	* @return the new o auth consumer
	*/
	public static com.liferay.opensocial.model.OAuthConsumer create(
		long oAuthConsumerId) {
		return getPersistence().create(oAuthConsumerId);
	}

	/**
	* Removes the o auth consumer with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param oAuthConsumerId the primary key of the o auth consumer
	* @return the o auth consumer that was removed
	* @throws com.liferay.opensocial.NoSuchOAuthConsumerException if a o auth consumer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.OAuthConsumer remove(
		long oAuthConsumerId)
		throws com.liferay.opensocial.NoSuchOAuthConsumerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(oAuthConsumerId);
	}

	public static com.liferay.opensocial.model.OAuthConsumer updateImpl(
		com.liferay.opensocial.model.OAuthConsumer oAuthConsumer)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(oAuthConsumer);
	}

	/**
	* Returns the o auth consumer with the primary key or throws a {@link com.liferay.opensocial.NoSuchOAuthConsumerException} if it could not be found.
	*
	* @param oAuthConsumerId the primary key of the o auth consumer
	* @return the o auth consumer
	* @throws com.liferay.opensocial.NoSuchOAuthConsumerException if a o auth consumer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.OAuthConsumer findByPrimaryKey(
		long oAuthConsumerId)
		throws com.liferay.opensocial.NoSuchOAuthConsumerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(oAuthConsumerId);
	}

	/**
	* Returns the o auth consumer with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param oAuthConsumerId the primary key of the o auth consumer
	* @return the o auth consumer, or <code>null</code> if a o auth consumer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.OAuthConsumer fetchByPrimaryKey(
		long oAuthConsumerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(oAuthConsumerId);
	}

	/**
	* Returns all the o auth consumers.
	*
	* @return the o auth consumers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.opensocial.model.OAuthConsumer> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the o auth consumers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.opensocial.model.impl.OAuthConsumerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of o auth consumers
	* @param end the upper bound of the range of o auth consumers (not inclusive)
	* @return the range of o auth consumers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.opensocial.model.OAuthConsumer> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the o auth consumers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.opensocial.model.impl.OAuthConsumerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of o auth consumers
	* @param end the upper bound of the range of o auth consumers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
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
	* Removes all the o auth consumers from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of o auth consumers.
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
			_persistence = (OAuthConsumerPersistence)PortletBeanLocatorUtil.locate(com.liferay.opensocial.service.ClpSerializer.getServletContextName(),
					OAuthConsumerPersistence.class.getName());

			ReferenceRegistry.registerReference(OAuthConsumerUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(OAuthConsumerPersistence persistence) {
	}

	private static OAuthConsumerPersistence _persistence;
}