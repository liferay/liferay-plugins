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

import aQute.bnd.annotation.ProviderType;

import com.liferay.opensocial.model.OAuthConsumer;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the o auth consumer service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.opensocial.service.persistence.impl.OAuthConsumerPersistenceImpl
 * @see OAuthConsumerUtil
 * @generated
 */
@ProviderType
public interface OAuthConsumerPersistence extends BasePersistence<OAuthConsumer> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link OAuthConsumerUtil} to access the o auth consumer persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the o auth consumers where gadgetKey = &#63;.
	*
	* @param gadgetKey the gadget key
	* @return the matching o auth consumers
	*/
	public java.util.List<OAuthConsumer> findByGadgetKey(
		java.lang.String gadgetKey);

	/**
	* Returns a range of all the o auth consumers where gadgetKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OAuthConsumerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param gadgetKey the gadget key
	* @param start the lower bound of the range of o auth consumers
	* @param end the upper bound of the range of o auth consumers (not inclusive)
	* @return the range of matching o auth consumers
	*/
	public java.util.List<OAuthConsumer> findByGadgetKey(
		java.lang.String gadgetKey, int start, int end);

	/**
	* Returns an ordered range of all the o auth consumers where gadgetKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OAuthConsumerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param gadgetKey the gadget key
	* @param start the lower bound of the range of o auth consumers
	* @param end the upper bound of the range of o auth consumers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching o auth consumers
	*/
	public java.util.List<OAuthConsumer> findByGadgetKey(
		java.lang.String gadgetKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OAuthConsumer> orderByComparator);

	/**
	* Returns an ordered range of all the o auth consumers where gadgetKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OAuthConsumerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param gadgetKey the gadget key
	* @param start the lower bound of the range of o auth consumers
	* @param end the upper bound of the range of o auth consumers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching o auth consumers
	*/
	public java.util.List<OAuthConsumer> findByGadgetKey(
		java.lang.String gadgetKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OAuthConsumer> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first o auth consumer in the ordered set where gadgetKey = &#63;.
	*
	* @param gadgetKey the gadget key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching o auth consumer
	* @throws NoSuchOAuthConsumerException if a matching o auth consumer could not be found
	*/
	public OAuthConsumer findByGadgetKey_First(java.lang.String gadgetKey,
		com.liferay.portal.kernel.util.OrderByComparator<OAuthConsumer> orderByComparator)
		throws com.liferay.opensocial.NoSuchOAuthConsumerException;

	/**
	* Returns the first o auth consumer in the ordered set where gadgetKey = &#63;.
	*
	* @param gadgetKey the gadget key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching o auth consumer, or <code>null</code> if a matching o auth consumer could not be found
	*/
	public OAuthConsumer fetchByGadgetKey_First(java.lang.String gadgetKey,
		com.liferay.portal.kernel.util.OrderByComparator<OAuthConsumer> orderByComparator);

	/**
	* Returns the last o auth consumer in the ordered set where gadgetKey = &#63;.
	*
	* @param gadgetKey the gadget key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching o auth consumer
	* @throws NoSuchOAuthConsumerException if a matching o auth consumer could not be found
	*/
	public OAuthConsumer findByGadgetKey_Last(java.lang.String gadgetKey,
		com.liferay.portal.kernel.util.OrderByComparator<OAuthConsumer> orderByComparator)
		throws com.liferay.opensocial.NoSuchOAuthConsumerException;

	/**
	* Returns the last o auth consumer in the ordered set where gadgetKey = &#63;.
	*
	* @param gadgetKey the gadget key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching o auth consumer, or <code>null</code> if a matching o auth consumer could not be found
	*/
	public OAuthConsumer fetchByGadgetKey_Last(java.lang.String gadgetKey,
		com.liferay.portal.kernel.util.OrderByComparator<OAuthConsumer> orderByComparator);

	/**
	* Returns the o auth consumers before and after the current o auth consumer in the ordered set where gadgetKey = &#63;.
	*
	* @param oAuthConsumerId the primary key of the current o auth consumer
	* @param gadgetKey the gadget key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next o auth consumer
	* @throws NoSuchOAuthConsumerException if a o auth consumer with the primary key could not be found
	*/
	public OAuthConsumer[] findByGadgetKey_PrevAndNext(long oAuthConsumerId,
		java.lang.String gadgetKey,
		com.liferay.portal.kernel.util.OrderByComparator<OAuthConsumer> orderByComparator)
		throws com.liferay.opensocial.NoSuchOAuthConsumerException;

	/**
	* Removes all the o auth consumers where gadgetKey = &#63; from the database.
	*
	* @param gadgetKey the gadget key
	*/
	public void removeByGadgetKey(java.lang.String gadgetKey);

	/**
	* Returns the number of o auth consumers where gadgetKey = &#63;.
	*
	* @param gadgetKey the gadget key
	* @return the number of matching o auth consumers
	*/
	public int countByGadgetKey(java.lang.String gadgetKey);

	/**
	* Returns the o auth consumer where gadgetKey = &#63; and serviceName = &#63; or throws a {@link NoSuchOAuthConsumerException} if it could not be found.
	*
	* @param gadgetKey the gadget key
	* @param serviceName the service name
	* @return the matching o auth consumer
	* @throws NoSuchOAuthConsumerException if a matching o auth consumer could not be found
	*/
	public OAuthConsumer findByG_S(java.lang.String gadgetKey,
		java.lang.String serviceName)
		throws com.liferay.opensocial.NoSuchOAuthConsumerException;

	/**
	* Returns the o auth consumer where gadgetKey = &#63; and serviceName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param gadgetKey the gadget key
	* @param serviceName the service name
	* @return the matching o auth consumer, or <code>null</code> if a matching o auth consumer could not be found
	*/
	public OAuthConsumer fetchByG_S(java.lang.String gadgetKey,
		java.lang.String serviceName);

	/**
	* Returns the o auth consumer where gadgetKey = &#63; and serviceName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param gadgetKey the gadget key
	* @param serviceName the service name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching o auth consumer, or <code>null</code> if a matching o auth consumer could not be found
	*/
	public OAuthConsumer fetchByG_S(java.lang.String gadgetKey,
		java.lang.String serviceName, boolean retrieveFromCache);

	/**
	* Removes the o auth consumer where gadgetKey = &#63; and serviceName = &#63; from the database.
	*
	* @param gadgetKey the gadget key
	* @param serviceName the service name
	* @return the o auth consumer that was removed
	*/
	public OAuthConsumer removeByG_S(java.lang.String gadgetKey,
		java.lang.String serviceName)
		throws com.liferay.opensocial.NoSuchOAuthConsumerException;

	/**
	* Returns the number of o auth consumers where gadgetKey = &#63; and serviceName = &#63;.
	*
	* @param gadgetKey the gadget key
	* @param serviceName the service name
	* @return the number of matching o auth consumers
	*/
	public int countByG_S(java.lang.String gadgetKey,
		java.lang.String serviceName);

	/**
	* Caches the o auth consumer in the entity cache if it is enabled.
	*
	* @param oAuthConsumer the o auth consumer
	*/
	public void cacheResult(OAuthConsumer oAuthConsumer);

	/**
	* Caches the o auth consumers in the entity cache if it is enabled.
	*
	* @param oAuthConsumers the o auth consumers
	*/
	public void cacheResult(java.util.List<OAuthConsumer> oAuthConsumers);

	/**
	* Creates a new o auth consumer with the primary key. Does not add the o auth consumer to the database.
	*
	* @param oAuthConsumerId the primary key for the new o auth consumer
	* @return the new o auth consumer
	*/
	public OAuthConsumer create(long oAuthConsumerId);

	/**
	* Removes the o auth consumer with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param oAuthConsumerId the primary key of the o auth consumer
	* @return the o auth consumer that was removed
	* @throws NoSuchOAuthConsumerException if a o auth consumer with the primary key could not be found
	*/
	public OAuthConsumer remove(long oAuthConsumerId)
		throws com.liferay.opensocial.NoSuchOAuthConsumerException;

	public OAuthConsumer updateImpl(OAuthConsumer oAuthConsumer);

	/**
	* Returns the o auth consumer with the primary key or throws a {@link NoSuchOAuthConsumerException} if it could not be found.
	*
	* @param oAuthConsumerId the primary key of the o auth consumer
	* @return the o auth consumer
	* @throws NoSuchOAuthConsumerException if a o auth consumer with the primary key could not be found
	*/
	public OAuthConsumer findByPrimaryKey(long oAuthConsumerId)
		throws com.liferay.opensocial.NoSuchOAuthConsumerException;

	/**
	* Returns the o auth consumer with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param oAuthConsumerId the primary key of the o auth consumer
	* @return the o auth consumer, or <code>null</code> if a o auth consumer with the primary key could not be found
	*/
	public OAuthConsumer fetchByPrimaryKey(long oAuthConsumerId);

	@Override
	public java.util.Map<java.io.Serializable, OAuthConsumer> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the o auth consumers.
	*
	* @return the o auth consumers
	*/
	public java.util.List<OAuthConsumer> findAll();

	/**
	* Returns a range of all the o auth consumers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OAuthConsumerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of o auth consumers
	* @param end the upper bound of the range of o auth consumers (not inclusive)
	* @return the range of o auth consumers
	*/
	public java.util.List<OAuthConsumer> findAll(int start, int end);

	/**
	* Returns an ordered range of all the o auth consumers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OAuthConsumerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of o auth consumers
	* @param end the upper bound of the range of o auth consumers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of o auth consumers
	*/
	public java.util.List<OAuthConsumer> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OAuthConsumer> orderByComparator);

	/**
	* Returns an ordered range of all the o auth consumers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OAuthConsumerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of o auth consumers
	* @param end the upper bound of the range of o auth consumers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of o auth consumers
	*/
	public java.util.List<OAuthConsumer> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OAuthConsumer> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the o auth consumers from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of o auth consumers.
	*
	* @return the number of o auth consumers
	*/
	public int countAll();
}