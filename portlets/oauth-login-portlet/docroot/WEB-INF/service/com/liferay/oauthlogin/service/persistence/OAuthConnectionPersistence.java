/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.oauthlogin.service.persistence;

import com.liferay.oauthlogin.model.OAuthConnection;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the o auth connection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Andy Yang
 * @see OAuthConnectionPersistenceImpl
 * @see OAuthConnectionUtil
 * @generated
 */
public interface OAuthConnectionPersistence extends BasePersistence<OAuthConnection> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link OAuthConnectionUtil} to access the o auth connection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the o auth connections where enabled = &#63;.
	*
	* @param enabled the enabled
	* @return the matching o auth connections
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.oauthlogin.model.OAuthConnection> findByEnabled(
		boolean enabled)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the o auth connections where enabled = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauthlogin.model.impl.OAuthConnectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param enabled the enabled
	* @param start the lower bound of the range of o auth connections
	* @param end the upper bound of the range of o auth connections (not inclusive)
	* @return the range of matching o auth connections
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.oauthlogin.model.OAuthConnection> findByEnabled(
		boolean enabled, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the o auth connections where enabled = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauthlogin.model.impl.OAuthConnectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param enabled the enabled
	* @param start the lower bound of the range of o auth connections
	* @param end the upper bound of the range of o auth connections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching o auth connections
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.oauthlogin.model.OAuthConnection> findByEnabled(
		boolean enabled, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first o auth connection in the ordered set where enabled = &#63;.
	*
	* @param enabled the enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching o auth connection
	* @throws com.liferay.oauthlogin.NoSuchOAuthConnectionException if a matching o auth connection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.oauthlogin.model.OAuthConnection findByEnabled_First(
		boolean enabled,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.oauthlogin.NoSuchOAuthConnectionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first o auth connection in the ordered set where enabled = &#63;.
	*
	* @param enabled the enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching o auth connection, or <code>null</code> if a matching o auth connection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.oauthlogin.model.OAuthConnection fetchByEnabled_First(
		boolean enabled,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last o auth connection in the ordered set where enabled = &#63;.
	*
	* @param enabled the enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching o auth connection
	* @throws com.liferay.oauthlogin.NoSuchOAuthConnectionException if a matching o auth connection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.oauthlogin.model.OAuthConnection findByEnabled_Last(
		boolean enabled,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.oauthlogin.NoSuchOAuthConnectionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last o auth connection in the ordered set where enabled = &#63;.
	*
	* @param enabled the enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching o auth connection, or <code>null</code> if a matching o auth connection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.oauthlogin.model.OAuthConnection fetchByEnabled_Last(
		boolean enabled,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the o auth connections before and after the current o auth connection in the ordered set where enabled = &#63;.
	*
	* @param oAuthConnectionId the primary key of the current o auth connection
	* @param enabled the enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next o auth connection
	* @throws com.liferay.oauthlogin.NoSuchOAuthConnectionException if a o auth connection with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.oauthlogin.model.OAuthConnection[] findByEnabled_PrevAndNext(
		long oAuthConnectionId, boolean enabled,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.oauthlogin.NoSuchOAuthConnectionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the o auth connections where enabled = &#63; from the database.
	*
	* @param enabled the enabled
	* @throws SystemException if a system exception occurred
	*/
	public void removeByEnabled(boolean enabled)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of o auth connections where enabled = &#63;.
	*
	* @param enabled the enabled
	* @return the number of matching o auth connections
	* @throws SystemException if a system exception occurred
	*/
	public int countByEnabled(boolean enabled)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the o auth connection in the entity cache if it is enabled.
	*
	* @param oAuthConnection the o auth connection
	*/
	public void cacheResult(
		com.liferay.oauthlogin.model.OAuthConnection oAuthConnection);

	/**
	* Caches the o auth connections in the entity cache if it is enabled.
	*
	* @param oAuthConnections the o auth connections
	*/
	public void cacheResult(
		java.util.List<com.liferay.oauthlogin.model.OAuthConnection> oAuthConnections);

	/**
	* Creates a new o auth connection with the primary key. Does not add the o auth connection to the database.
	*
	* @param oAuthConnectionId the primary key for the new o auth connection
	* @return the new o auth connection
	*/
	public com.liferay.oauthlogin.model.OAuthConnection create(
		long oAuthConnectionId);

	/**
	* Removes the o auth connection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param oAuthConnectionId the primary key of the o auth connection
	* @return the o auth connection that was removed
	* @throws com.liferay.oauthlogin.NoSuchOAuthConnectionException if a o auth connection with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.oauthlogin.model.OAuthConnection remove(
		long oAuthConnectionId)
		throws com.liferay.oauthlogin.NoSuchOAuthConnectionException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.oauthlogin.model.OAuthConnection updateImpl(
		com.liferay.oauthlogin.model.OAuthConnection oAuthConnection)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the o auth connection with the primary key or throws a {@link com.liferay.oauthlogin.NoSuchOAuthConnectionException} if it could not be found.
	*
	* @param oAuthConnectionId the primary key of the o auth connection
	* @return the o auth connection
	* @throws com.liferay.oauthlogin.NoSuchOAuthConnectionException if a o auth connection with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.oauthlogin.model.OAuthConnection findByPrimaryKey(
		long oAuthConnectionId)
		throws com.liferay.oauthlogin.NoSuchOAuthConnectionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the o auth connection with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param oAuthConnectionId the primary key of the o auth connection
	* @return the o auth connection, or <code>null</code> if a o auth connection with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.oauthlogin.model.OAuthConnection fetchByPrimaryKey(
		long oAuthConnectionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the o auth connections.
	*
	* @return the o auth connections
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.oauthlogin.model.OAuthConnection> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the o auth connections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauthlogin.model.impl.OAuthConnectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of o auth connections
	* @param end the upper bound of the range of o auth connections (not inclusive)
	* @return the range of o auth connections
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.oauthlogin.model.OAuthConnection> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the o auth connections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauthlogin.model.impl.OAuthConnectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of o auth connections
	* @param end the upper bound of the range of o auth connections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of o auth connections
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.oauthlogin.model.OAuthConnection> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the o auth connections from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of o auth connections.
	*
	* @return the number of o auth connections
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}