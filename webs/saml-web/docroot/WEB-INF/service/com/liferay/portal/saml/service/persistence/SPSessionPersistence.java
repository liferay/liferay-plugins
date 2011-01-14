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

import com.liferay.portal.saml.model.SPSession;
import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the s p session service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SPSessionPersistenceImpl
 * @see SPSessionUtil
 * @generated
 */
public interface SPSessionPersistence extends BasePersistence<SPSession> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SPSessionUtil} to access the s p session persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the s p session in the entity cache if it is enabled.
	*
	* @param spSession the s p session to cache
	*/
	public void cacheResult(com.liferay.portal.saml.model.SPSession spSession);

	/**
	* Caches the s p sessions in the entity cache if it is enabled.
	*
	* @param spSessions the s p sessions to cache
	*/
	public void cacheResult(
		java.util.List<com.liferay.portal.saml.model.SPSession> spSessions);

	/**
	* Creates a new s p session with the primary key. Does not add the s p session to the database.
	*
	* @param spSessionId the primary key for the new s p session
	* @return the new s p session
	*/
	public com.liferay.portal.saml.model.SPSession create(long spSessionId);

	/**
	* Removes the s p session with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param spSessionId the primary key of the s p session to remove
	* @return the s p session that was removed
	* @throws com.liferay.portal.saml.NoSuchSPSessionException if a s p session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.saml.model.SPSession remove(long spSessionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.saml.NoSuchSPSessionException;

	public com.liferay.portal.saml.model.SPSession updateImpl(
		com.liferay.portal.saml.model.SPSession spSession, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the s p session with the primary key or throws a {@link com.liferay.portal.saml.NoSuchSPSessionException} if it could not be found.
	*
	* @param spSessionId the primary key of the s p session to find
	* @return the s p session
	* @throws com.liferay.portal.saml.NoSuchSPSessionException if a s p session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.saml.model.SPSession findByPrimaryKey(
		long spSessionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.saml.NoSuchSPSessionException;

	/**
	* Finds the s p session with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param spSessionId the primary key of the s p session to find
	* @return the s p session, or <code>null</code> if a s p session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.saml.model.SPSession fetchByPrimaryKey(
		long spSessionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the s p sessions where ssoSessionId = &#63;.
	*
	* @param ssoSessionId the sso session ID to search with
	* @return the matching s p sessions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.saml.model.SPSession> findBySSOSessionId(
		long ssoSessionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the s p sessions where ssoSessionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ssoSessionId the sso session ID to search with
	* @param start the lower bound of the range of s p sessions to return
	* @param end the upper bound of the range of s p sessions to return (not inclusive)
	* @return the range of matching s p sessions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.saml.model.SPSession> findBySSOSessionId(
		long ssoSessionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the s p sessions where ssoSessionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ssoSessionId the sso session ID to search with
	* @param start the lower bound of the range of s p sessions to return
	* @param end the upper bound of the range of s p sessions to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching s p sessions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.saml.model.SPSession> findBySSOSessionId(
		long ssoSessionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the first s p session in the ordered set where ssoSessionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ssoSessionId the sso session ID to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching s p session
	* @throws com.liferay.portal.saml.NoSuchSPSessionException if a matching s p session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.saml.model.SPSession findBySSOSessionId_First(
		long ssoSessionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.saml.NoSuchSPSessionException;

	/**
	* Finds the last s p session in the ordered set where ssoSessionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ssoSessionId the sso session ID to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching s p session
	* @throws com.liferay.portal.saml.NoSuchSPSessionException if a matching s p session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.saml.model.SPSession findBySSOSessionId_Last(
		long ssoSessionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.saml.NoSuchSPSessionException;

	/**
	* Finds the s p sessions before and after the current s p session in the ordered set where ssoSessionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param spSessionId the primary key of the current s p session
	* @param ssoSessionId the sso session ID to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next s p session
	* @throws com.liferay.portal.saml.NoSuchSPSessionException if a s p session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.saml.model.SPSession[] findBySSOSessionId_PrevAndNext(
		long spSessionId, long ssoSessionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.saml.NoSuchSPSessionException;

	/**
	* Finds the s p session where ssoSessionId = &#63; and issuer = &#63; or throws a {@link com.liferay.portal.saml.NoSuchSPSessionException} if it could not be found.
	*
	* @param ssoSessionId the sso session ID to search with
	* @param issuer the issuer to search with
	* @return the matching s p session
	* @throws com.liferay.portal.saml.NoSuchSPSessionException if a matching s p session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.saml.model.SPSession findByS_I(
		long ssoSessionId, java.lang.String issuer)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.saml.NoSuchSPSessionException;

	/**
	* Finds the s p session where ssoSessionId = &#63; and issuer = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param ssoSessionId the sso session ID to search with
	* @param issuer the issuer to search with
	* @return the matching s p session, or <code>null</code> if a matching s p session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.saml.model.SPSession fetchByS_I(
		long ssoSessionId, java.lang.String issuer)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the s p session where ssoSessionId = &#63; and issuer = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param ssoSessionId the sso session ID to search with
	* @param issuer the issuer to search with
	* @return the matching s p session, or <code>null</code> if a matching s p session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.saml.model.SPSession fetchByS_I(
		long ssoSessionId, java.lang.String issuer, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the s p sessions.
	*
	* @return the s p sessions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.saml.model.SPSession> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the s p sessions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of s p sessions to return
	* @param end the upper bound of the range of s p sessions to return (not inclusive)
	* @return the range of s p sessions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.saml.model.SPSession> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the s p sessions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of s p sessions to return
	* @param end the upper bound of the range of s p sessions to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of s p sessions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.saml.model.SPSession> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the s p sessions where ssoSessionId = &#63; from the database.
	*
	* @param ssoSessionId the sso session ID to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeBySSOSessionId(long ssoSessionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the s p session where ssoSessionId = &#63; and issuer = &#63; from the database.
	*
	* @param ssoSessionId the sso session ID to search with
	* @param issuer the issuer to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByS_I(long ssoSessionId, java.lang.String issuer)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.saml.NoSuchSPSessionException;

	/**
	* Removes all the s p sessions from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the s p sessions where ssoSessionId = &#63;.
	*
	* @param ssoSessionId the sso session ID to search with
	* @return the number of matching s p sessions
	* @throws SystemException if a system exception occurred
	*/
	public int countBySSOSessionId(long ssoSessionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the s p sessions where ssoSessionId = &#63; and issuer = &#63;.
	*
	* @param ssoSessionId the sso session ID to search with
	* @param issuer the issuer to search with
	* @return the number of matching s p sessions
	* @throws SystemException if a system exception occurred
	*/
	public int countByS_I(long ssoSessionId, java.lang.String issuer)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the s p sessions.
	*
	* @return the number of s p sessions
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}