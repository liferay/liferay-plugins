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

package com.liferay.bbb.service.persistence;

import com.liferay.bbb.model.BBBServer;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the b b b server service. This utility wraps {@link BBBServerPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Shinn Lok
 * @see BBBServerPersistence
 * @see BBBServerPersistenceImpl
 * @generated
 */
public class BBBServerUtil {
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
	public static void clearCache(BBBServer bbbServer) {
		getPersistence().clearCache(bbbServer);
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
	public static List<BBBServer> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<BBBServer> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<BBBServer> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static BBBServer update(BBBServer bbbServer) {
		return getPersistence().update(bbbServer);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static BBBServer update(BBBServer bbbServer,
		ServiceContext serviceContext) {
		return getPersistence().update(bbbServer, serviceContext);
	}

	/**
	* Returns all the b b b servers where active = &#63;.
	*
	* @param active the active
	* @return the matching b b b servers
	*/
	public static java.util.List<com.liferay.bbb.model.BBBServer> findByActive(
		boolean active) {
		return getPersistence().findByActive(active);
	}

	/**
	* Returns a range of all the b b b servers where active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBServerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param active the active
	* @param start the lower bound of the range of b b b servers
	* @param end the upper bound of the range of b b b servers (not inclusive)
	* @return the range of matching b b b servers
	*/
	public static java.util.List<com.liferay.bbb.model.BBBServer> findByActive(
		boolean active, int start, int end) {
		return getPersistence().findByActive(active, start, end);
	}

	/**
	* Returns an ordered range of all the b b b servers where active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBServerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param active the active
	* @param start the lower bound of the range of b b b servers
	* @param end the upper bound of the range of b b b servers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching b b b servers
	*/
	public static java.util.List<com.liferay.bbb.model.BBBServer> findByActive(
		boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator) {
		return getPersistence()
				   .findByActive(active, start, end, orderByComparator);
	}

	/**
	* Returns the first b b b server in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching b b b server
	* @throws com.liferay.bbb.NoSuchServerException if a matching b b b server could not be found
	*/
	public static com.liferay.bbb.model.BBBServer findByActive_First(
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.bbb.NoSuchServerException {
		return getPersistence().findByActive_First(active, orderByComparator);
	}

	/**
	* Returns the first b b b server in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching b b b server, or <code>null</code> if a matching b b b server could not be found
	*/
	public static com.liferay.bbb.model.BBBServer fetchByActive_First(
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator) {
		return getPersistence().fetchByActive_First(active, orderByComparator);
	}

	/**
	* Returns the last b b b server in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching b b b server
	* @throws com.liferay.bbb.NoSuchServerException if a matching b b b server could not be found
	*/
	public static com.liferay.bbb.model.BBBServer findByActive_Last(
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.bbb.NoSuchServerException {
		return getPersistence().findByActive_Last(active, orderByComparator);
	}

	/**
	* Returns the last b b b server in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching b b b server, or <code>null</code> if a matching b b b server could not be found
	*/
	public static com.liferay.bbb.model.BBBServer fetchByActive_Last(
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator) {
		return getPersistence().fetchByActive_Last(active, orderByComparator);
	}

	/**
	* Returns the b b b servers before and after the current b b b server in the ordered set where active = &#63;.
	*
	* @param bbbServerId the primary key of the current b b b server
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next b b b server
	* @throws com.liferay.bbb.NoSuchServerException if a b b b server with the primary key could not be found
	*/
	public static com.liferay.bbb.model.BBBServer[] findByActive_PrevAndNext(
		long bbbServerId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.bbb.NoSuchServerException {
		return getPersistence()
				   .findByActive_PrevAndNext(bbbServerId, active,
			orderByComparator);
	}

	/**
	* Removes all the b b b servers where active = &#63; from the database.
	*
	* @param active the active
	*/
	public static void removeByActive(boolean active) {
		getPersistence().removeByActive(active);
	}

	/**
	* Returns the number of b b b servers where active = &#63;.
	*
	* @param active the active
	* @return the number of matching b b b servers
	*/
	public static int countByActive(boolean active) {
		return getPersistence().countByActive(active);
	}

	/**
	* Caches the b b b server in the entity cache if it is enabled.
	*
	* @param bbbServer the b b b server
	*/
	public static void cacheResult(com.liferay.bbb.model.BBBServer bbbServer) {
		getPersistence().cacheResult(bbbServer);
	}

	/**
	* Caches the b b b servers in the entity cache if it is enabled.
	*
	* @param bbbServers the b b b servers
	*/
	public static void cacheResult(
		java.util.List<com.liferay.bbb.model.BBBServer> bbbServers) {
		getPersistence().cacheResult(bbbServers);
	}

	/**
	* Creates a new b b b server with the primary key. Does not add the b b b server to the database.
	*
	* @param bbbServerId the primary key for the new b b b server
	* @return the new b b b server
	*/
	public static com.liferay.bbb.model.BBBServer create(long bbbServerId) {
		return getPersistence().create(bbbServerId);
	}

	/**
	* Removes the b b b server with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param bbbServerId the primary key of the b b b server
	* @return the b b b server that was removed
	* @throws com.liferay.bbb.NoSuchServerException if a b b b server with the primary key could not be found
	*/
	public static com.liferay.bbb.model.BBBServer remove(long bbbServerId)
		throws com.liferay.bbb.NoSuchServerException {
		return getPersistence().remove(bbbServerId);
	}

	public static com.liferay.bbb.model.BBBServer updateImpl(
		com.liferay.bbb.model.BBBServer bbbServer) {
		return getPersistence().updateImpl(bbbServer);
	}

	/**
	* Returns the b b b server with the primary key or throws a {@link com.liferay.bbb.NoSuchServerException} if it could not be found.
	*
	* @param bbbServerId the primary key of the b b b server
	* @return the b b b server
	* @throws com.liferay.bbb.NoSuchServerException if a b b b server with the primary key could not be found
	*/
	public static com.liferay.bbb.model.BBBServer findByPrimaryKey(
		long bbbServerId) throws com.liferay.bbb.NoSuchServerException {
		return getPersistence().findByPrimaryKey(bbbServerId);
	}

	/**
	* Returns the b b b server with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param bbbServerId the primary key of the b b b server
	* @return the b b b server, or <code>null</code> if a b b b server with the primary key could not be found
	*/
	public static com.liferay.bbb.model.BBBServer fetchByPrimaryKey(
		long bbbServerId) {
		return getPersistence().fetchByPrimaryKey(bbbServerId);
	}

	public static java.util.Map<java.io.Serializable, com.liferay.bbb.model.BBBServer> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the b b b servers.
	*
	* @return the b b b servers
	*/
	public static java.util.List<com.liferay.bbb.model.BBBServer> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the b b b servers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBServerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of b b b servers
	* @param end the upper bound of the range of b b b servers (not inclusive)
	* @return the range of b b b servers
	*/
	public static java.util.List<com.liferay.bbb.model.BBBServer> findAll(
		int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the b b b servers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBServerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of b b b servers
	* @param end the upper bound of the range of b b b servers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of b b b servers
	*/
	public static java.util.List<com.liferay.bbb.model.BBBServer> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the b b b servers from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of b b b servers.
	*
	* @return the number of b b b servers
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static BBBServerPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (BBBServerPersistence)PortletBeanLocatorUtil.locate(com.liferay.bbb.service.ClpSerializer.getServletContextName(),
					BBBServerPersistence.class.getName());

			ReferenceRegistry.registerReference(BBBServerUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setPersistence(BBBServerPersistence persistence) {
	}

	private static BBBServerPersistence _persistence;
}