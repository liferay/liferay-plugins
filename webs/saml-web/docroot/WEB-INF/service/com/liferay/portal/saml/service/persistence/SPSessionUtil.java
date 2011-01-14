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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.saml.model.SPSession;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the s p session service. This utility wraps {@link SPSessionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SPSessionPersistence
 * @see SPSessionPersistenceImpl
 * @generated
 */
public class SPSessionUtil {
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
	public static void clearCache(SPSession spSession) {
		getPersistence().clearCache(spSession);
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
	public static List<SPSession> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SPSession> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SPSession> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static SPSession remove(SPSession spSession)
		throws SystemException {
		return getPersistence().remove(spSession);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static SPSession update(SPSession spSession, boolean merge)
		throws SystemException {
		return getPersistence().update(spSession, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static SPSession update(SPSession spSession, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(spSession, merge, serviceContext);
	}

	/**
	* Caches the s p session in the entity cache if it is enabled.
	*
	* @param spSession the s p session to cache
	*/
	public static void cacheResult(
		com.liferay.portal.saml.model.SPSession spSession) {
		getPersistence().cacheResult(spSession);
	}

	/**
	* Caches the s p sessions in the entity cache if it is enabled.
	*
	* @param spSessions the s p sessions to cache
	*/
	public static void cacheResult(
		java.util.List<com.liferay.portal.saml.model.SPSession> spSessions) {
		getPersistence().cacheResult(spSessions);
	}

	/**
	* Creates a new s p session with the primary key. Does not add the s p session to the database.
	*
	* @param spSessionId the primary key for the new s p session
	* @return the new s p session
	*/
	public static com.liferay.portal.saml.model.SPSession create(
		long spSessionId) {
		return getPersistence().create(spSessionId);
	}

	/**
	* Removes the s p session with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param spSessionId the primary key of the s p session to remove
	* @return the s p session that was removed
	* @throws com.liferay.portal.saml.NoSuchSPSessionException if a s p session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.saml.model.SPSession remove(
		long spSessionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.saml.NoSuchSPSessionException {
		return getPersistence().remove(spSessionId);
	}

	public static com.liferay.portal.saml.model.SPSession updateImpl(
		com.liferay.portal.saml.model.SPSession spSession, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(spSession, merge);
	}

	/**
	* Finds the s p session with the primary key or throws a {@link com.liferay.portal.saml.NoSuchSPSessionException} if it could not be found.
	*
	* @param spSessionId the primary key of the s p session to find
	* @return the s p session
	* @throws com.liferay.portal.saml.NoSuchSPSessionException if a s p session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.saml.model.SPSession findByPrimaryKey(
		long spSessionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.saml.NoSuchSPSessionException {
		return getPersistence().findByPrimaryKey(spSessionId);
	}

	/**
	* Finds the s p session with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param spSessionId the primary key of the s p session to find
	* @return the s p session, or <code>null</code> if a s p session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.saml.model.SPSession fetchByPrimaryKey(
		long spSessionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(spSessionId);
	}

	/**
	* Finds all the s p sessions where ssoSessionId = &#63;.
	*
	* @param ssoSessionId the sso session ID to search with
	* @return the matching s p sessions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.saml.model.SPSession> findBySSOSessionId(
		long ssoSessionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBySSOSessionId(ssoSessionId);
	}

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
	public static java.util.List<com.liferay.portal.saml.model.SPSession> findBySSOSessionId(
		long ssoSessionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBySSOSessionId(ssoSessionId, start, end);
	}

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
	public static java.util.List<com.liferay.portal.saml.model.SPSession> findBySSOSessionId(
		long ssoSessionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySSOSessionId(ssoSessionId, start, end,
			orderByComparator);
	}

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
	public static com.liferay.portal.saml.model.SPSession findBySSOSessionId_First(
		long ssoSessionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.saml.NoSuchSPSessionException {
		return getPersistence()
				   .findBySSOSessionId_First(ssoSessionId, orderByComparator);
	}

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
	public static com.liferay.portal.saml.model.SPSession findBySSOSessionId_Last(
		long ssoSessionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.saml.NoSuchSPSessionException {
		return getPersistence()
				   .findBySSOSessionId_Last(ssoSessionId, orderByComparator);
	}

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
	public static com.liferay.portal.saml.model.SPSession[] findBySSOSessionId_PrevAndNext(
		long spSessionId, long ssoSessionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.saml.NoSuchSPSessionException {
		return getPersistence()
				   .findBySSOSessionId_PrevAndNext(spSessionId, ssoSessionId,
			orderByComparator);
	}

	/**
	* Finds the s p session where ssoSessionId = &#63; and issuer = &#63; or throws a {@link com.liferay.portal.saml.NoSuchSPSessionException} if it could not be found.
	*
	* @param ssoSessionId the sso session ID to search with
	* @param issuer the issuer to search with
	* @return the matching s p session
	* @throws com.liferay.portal.saml.NoSuchSPSessionException if a matching s p session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.saml.model.SPSession findByS_I(
		long ssoSessionId, java.lang.String issuer)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.saml.NoSuchSPSessionException {
		return getPersistence().findByS_I(ssoSessionId, issuer);
	}

	/**
	* Finds the s p session where ssoSessionId = &#63; and issuer = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param ssoSessionId the sso session ID to search with
	* @param issuer the issuer to search with
	* @return the matching s p session, or <code>null</code> if a matching s p session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.saml.model.SPSession fetchByS_I(
		long ssoSessionId, java.lang.String issuer)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByS_I(ssoSessionId, issuer);
	}

	/**
	* Finds the s p session where ssoSessionId = &#63; and issuer = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param ssoSessionId the sso session ID to search with
	* @param issuer the issuer to search with
	* @return the matching s p session, or <code>null</code> if a matching s p session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.saml.model.SPSession fetchByS_I(
		long ssoSessionId, java.lang.String issuer, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByS_I(ssoSessionId, issuer, retrieveFromCache);
	}

	/**
	* Finds all the s p sessions.
	*
	* @return the s p sessions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.saml.model.SPSession> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<com.liferay.portal.saml.model.SPSession> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.liferay.portal.saml.model.SPSession> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the s p sessions where ssoSessionId = &#63; from the database.
	*
	* @param ssoSessionId the sso session ID to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeBySSOSessionId(long ssoSessionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeBySSOSessionId(ssoSessionId);
	}

	/**
	* Removes the s p session where ssoSessionId = &#63; and issuer = &#63; from the database.
	*
	* @param ssoSessionId the sso session ID to search with
	* @param issuer the issuer to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByS_I(long ssoSessionId, java.lang.String issuer)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.saml.NoSuchSPSessionException {
		getPersistence().removeByS_I(ssoSessionId, issuer);
	}

	/**
	* Removes all the s p sessions from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Counts all the s p sessions where ssoSessionId = &#63;.
	*
	* @param ssoSessionId the sso session ID to search with
	* @return the number of matching s p sessions
	* @throws SystemException if a system exception occurred
	*/
	public static int countBySSOSessionId(long ssoSessionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countBySSOSessionId(ssoSessionId);
	}

	/**
	* Counts all the s p sessions where ssoSessionId = &#63; and issuer = &#63;.
	*
	* @param ssoSessionId the sso session ID to search with
	* @param issuer the issuer to search with
	* @return the number of matching s p sessions
	* @throws SystemException if a system exception occurred
	*/
	public static int countByS_I(long ssoSessionId, java.lang.String issuer)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByS_I(ssoSessionId, issuer);
	}

	/**
	* Counts all the s p sessions.
	*
	* @return the number of s p sessions
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static SPSessionPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (SPSessionPersistence)PortletBeanLocatorUtil.locate(com.liferay.portal.saml.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					SPSessionPersistence.class.getName());

			ReferenceRegistry.registerReference(SPSessionUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(SPSessionPersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(SPSessionUtil.class, "_persistence");
	}

	private static SPSessionPersistence _persistence;
}