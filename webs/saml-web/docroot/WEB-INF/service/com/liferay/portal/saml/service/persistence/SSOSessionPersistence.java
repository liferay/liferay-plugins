/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.saml.service.persistence;

import com.liferay.portal.saml.model.SSOSession;
import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the s s o session service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SSOSessionPersistenceImpl
 * @see SSOSessionUtil
 * @generated
 */
public interface SSOSessionPersistence extends BasePersistence<SSOSession> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SSOSessionUtil} to access the s s o session persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the s s o session in the entity cache if it is enabled.
	*
	* @param ssoSession the s s o session to cache
	*/
	public void cacheResult(com.liferay.portal.saml.model.SSOSession ssoSession);

	/**
	* Caches the s s o sessions in the entity cache if it is enabled.
	*
	* @param ssoSessions the s s o sessions to cache
	*/
	public void cacheResult(
		java.util.List<com.liferay.portal.saml.model.SSOSession> ssoSessions);

	/**
	* Creates a new s s o session with the primary key. Does not add the s s o session to the database.
	*
	* @param ssoSessionId the primary key for the new s s o session
	* @return the new s s o session
	*/
	public com.liferay.portal.saml.model.SSOSession create(long ssoSessionId);

	/**
	* Removes the s s o session with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ssoSessionId the primary key of the s s o session to remove
	* @return the s s o session that was removed
	* @throws com.liferay.portal.saml.NoSuchSSOSessionException if a s s o session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.saml.model.SSOSession remove(long ssoSessionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.saml.NoSuchSSOSessionException;

	public com.liferay.portal.saml.model.SSOSession updateImpl(
		com.liferay.portal.saml.model.SSOSession ssoSession, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the s s o session with the primary key or throws a {@link com.liferay.portal.saml.NoSuchSSOSessionException} if it could not be found.
	*
	* @param ssoSessionId the primary key of the s s o session to find
	* @return the s s o session
	* @throws com.liferay.portal.saml.NoSuchSSOSessionException if a s s o session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.saml.model.SSOSession findByPrimaryKey(
		long ssoSessionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.saml.NoSuchSSOSessionException;

	/**
	* Finds the s s o session with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ssoSessionId the primary key of the s s o session to find
	* @return the s s o session, or <code>null</code> if a s s o session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.saml.model.SSOSession fetchByPrimaryKey(
		long ssoSessionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the s s o session where key = &#63; or throws a {@link com.liferay.portal.saml.NoSuchSSOSessionException} if it could not be found.
	*
	* @param key the key to search with
	* @return the matching s s o session
	* @throws com.liferay.portal.saml.NoSuchSSOSessionException if a matching s s o session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.saml.model.SSOSession findByKey(
		java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.saml.NoSuchSSOSessionException;

	/**
	* Finds the s s o session where key = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param key the key to search with
	* @return the matching s s o session, or <code>null</code> if a matching s s o session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.saml.model.SSOSession fetchByKey(
		java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the s s o session where key = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param key the key to search with
	* @return the matching s s o session, or <code>null</code> if a matching s s o session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.saml.model.SSOSession fetchByKey(
		java.lang.String key, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the s s o sessions.
	*
	* @return the s s o sessions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.saml.model.SSOSession> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the s s o sessions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of s s o sessions to return
	* @param end the upper bound of the range of s s o sessions to return (not inclusive)
	* @return the range of s s o sessions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.saml.model.SSOSession> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the s s o sessions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of s s o sessions to return
	* @param end the upper bound of the range of s s o sessions to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of s s o sessions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.saml.model.SSOSession> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the s s o session where key = &#63; from the database.
	*
	* @param key the key to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByKey(java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.saml.NoSuchSSOSessionException;

	/**
	* Removes all the s s o sessions from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the s s o sessions where key = &#63;.
	*
	* @param key the key to search with
	* @return the number of matching s s o sessions
	* @throws SystemException if a system exception occurred
	*/
	public int countByKey(java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the s s o sessions.
	*
	* @return the number of s s o sessions
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}