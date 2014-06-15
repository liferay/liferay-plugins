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

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the b b b server service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Shinn Lok
 * @see BBBServerPersistenceImpl
 * @see BBBServerUtil
 * @generated
 */
public interface BBBServerPersistence extends BasePersistence<BBBServer> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link BBBServerUtil} to access the b b b server persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the b b b servers where active = &#63;.
	*
	* @param active the active
	* @return the matching b b b servers
	*/
	public java.util.List<com.liferay.bbb.model.BBBServer> findByActive(
		boolean active);

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
	public java.util.List<com.liferay.bbb.model.BBBServer> findByActive(
		boolean active, int start, int end);

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
	public java.util.List<com.liferay.bbb.model.BBBServer> findByActive(
		boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator);

	/**
	* Returns the first b b b server in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching b b b server
	* @throws com.liferay.bbb.NoSuchServerException if a matching b b b server could not be found
	*/
	public com.liferay.bbb.model.BBBServer findByActive_First(boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.bbb.NoSuchServerException;

	/**
	* Returns the first b b b server in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching b b b server, or <code>null</code> if a matching b b b server could not be found
	*/
	public com.liferay.bbb.model.BBBServer fetchByActive_First(boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator);

	/**
	* Returns the last b b b server in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching b b b server
	* @throws com.liferay.bbb.NoSuchServerException if a matching b b b server could not be found
	*/
	public com.liferay.bbb.model.BBBServer findByActive_Last(boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.bbb.NoSuchServerException;

	/**
	* Returns the last b b b server in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching b b b server, or <code>null</code> if a matching b b b server could not be found
	*/
	public com.liferay.bbb.model.BBBServer fetchByActive_Last(boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator);

	/**
	* Returns the b b b servers before and after the current b b b server in the ordered set where active = &#63;.
	*
	* @param bbbServerId the primary key of the current b b b server
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next b b b server
	* @throws com.liferay.bbb.NoSuchServerException if a b b b server with the primary key could not be found
	*/
	public com.liferay.bbb.model.BBBServer[] findByActive_PrevAndNext(
		long bbbServerId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.bbb.NoSuchServerException;

	/**
	* Removes all the b b b servers where active = &#63; from the database.
	*
	* @param active the active
	*/
	public void removeByActive(boolean active);

	/**
	* Returns the number of b b b servers where active = &#63;.
	*
	* @param active the active
	* @return the number of matching b b b servers
	*/
	public int countByActive(boolean active);

	/**
	* Caches the b b b server in the entity cache if it is enabled.
	*
	* @param bbbServer the b b b server
	*/
	public void cacheResult(com.liferay.bbb.model.BBBServer bbbServer);

	/**
	* Caches the b b b servers in the entity cache if it is enabled.
	*
	* @param bbbServers the b b b servers
	*/
	public void cacheResult(
		java.util.List<com.liferay.bbb.model.BBBServer> bbbServers);

	/**
	* Creates a new b b b server with the primary key. Does not add the b b b server to the database.
	*
	* @param bbbServerId the primary key for the new b b b server
	* @return the new b b b server
	*/
	public com.liferay.bbb.model.BBBServer create(long bbbServerId);

	/**
	* Removes the b b b server with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param bbbServerId the primary key of the b b b server
	* @return the b b b server that was removed
	* @throws com.liferay.bbb.NoSuchServerException if a b b b server with the primary key could not be found
	*/
	public com.liferay.bbb.model.BBBServer remove(long bbbServerId)
		throws com.liferay.bbb.NoSuchServerException;

	public com.liferay.bbb.model.BBBServer updateImpl(
		com.liferay.bbb.model.BBBServer bbbServer);

	/**
	* Returns the b b b server with the primary key or throws a {@link com.liferay.bbb.NoSuchServerException} if it could not be found.
	*
	* @param bbbServerId the primary key of the b b b server
	* @return the b b b server
	* @throws com.liferay.bbb.NoSuchServerException if a b b b server with the primary key could not be found
	*/
	public com.liferay.bbb.model.BBBServer findByPrimaryKey(long bbbServerId)
		throws com.liferay.bbb.NoSuchServerException;

	/**
	* Returns the b b b server with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param bbbServerId the primary key of the b b b server
	* @return the b b b server, or <code>null</code> if a b b b server with the primary key could not be found
	*/
	public com.liferay.bbb.model.BBBServer fetchByPrimaryKey(long bbbServerId);

	@Override
	public java.util.Map<java.io.Serializable, com.liferay.bbb.model.BBBServer> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the b b b servers.
	*
	* @return the b b b servers
	*/
	public java.util.List<com.liferay.bbb.model.BBBServer> findAll();

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
	public java.util.List<com.liferay.bbb.model.BBBServer> findAll(int start,
		int end);

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
	public java.util.List<com.liferay.bbb.model.BBBServer> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator);

	/**
	* Removes all the b b b servers from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of b b b servers.
	*
	* @return the number of b b b servers
	*/
	public int countAll();
}