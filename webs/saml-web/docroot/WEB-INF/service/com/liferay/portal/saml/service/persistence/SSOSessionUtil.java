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
import com.liferay.portal.saml.model.SSOSession;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the s s o session service. This utility wraps {@link SSOSessionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SSOSessionPersistence
 * @see SSOSessionPersistenceImpl
 * @generated
 */
public class SSOSessionUtil {
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
	public static void clearCache(SSOSession ssoSession) {
		getPersistence().clearCache(ssoSession);
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
	public static List<SSOSession> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SSOSession> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SSOSession> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static SSOSession remove(SSOSession ssoSession)
		throws SystemException {
		return getPersistence().remove(ssoSession);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static SSOSession update(SSOSession ssoSession, boolean merge)
		throws SystemException {
		return getPersistence().update(ssoSession, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static SSOSession update(SSOSession ssoSession, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(ssoSession, merge, serviceContext);
	}

	/**
	* Caches the s s o session in the entity cache if it is enabled.
	*
	* @param ssoSession the s s o session to cache
	*/
	public static void cacheResult(
		com.liferay.portal.saml.model.SSOSession ssoSession) {
		getPersistence().cacheResult(ssoSession);
	}

	/**
	* Caches the s s o sessions in the entity cache if it is enabled.
	*
	* @param ssoSessions the s s o sessions to cache
	*/
	public static void cacheResult(
		java.util.List<com.liferay.portal.saml.model.SSOSession> ssoSessions) {
		getPersistence().cacheResult(ssoSessions);
	}

	/**
	* Creates a new s s o session with the primary key. Does not add the s s o session to the database.
	*
	* @param ssoSessionId the primary key for the new s s o session
	* @return the new s s o session
	*/
	public static com.liferay.portal.saml.model.SSOSession create(
		long ssoSessionId) {
		return getPersistence().create(ssoSessionId);
	}

	/**
	* Removes the s s o session with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ssoSessionId the primary key of the s s o session to remove
	* @return the s s o session that was removed
	* @throws com.liferay.portal.saml.NoSuchSSOSessionException if a s s o session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.saml.model.SSOSession remove(
		long ssoSessionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.saml.NoSuchSSOSessionException {
		return getPersistence().remove(ssoSessionId);
	}

	public static com.liferay.portal.saml.model.SSOSession updateImpl(
		com.liferay.portal.saml.model.SSOSession ssoSession, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(ssoSession, merge);
	}

	/**
	* Finds the s s o session with the primary key or throws a {@link com.liferay.portal.saml.NoSuchSSOSessionException} if it could not be found.
	*
	* @param ssoSessionId the primary key of the s s o session to find
	* @return the s s o session
	* @throws com.liferay.portal.saml.NoSuchSSOSessionException if a s s o session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.saml.model.SSOSession findByPrimaryKey(
		long ssoSessionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.saml.NoSuchSSOSessionException {
		return getPersistence().findByPrimaryKey(ssoSessionId);
	}

	/**
	* Finds the s s o session with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ssoSessionId the primary key of the s s o session to find
	* @return the s s o session, or <code>null</code> if a s s o session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.saml.model.SSOSession fetchByPrimaryKey(
		long ssoSessionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(ssoSessionId);
	}

	/**
	* Finds the s s o session where key = &#63; or throws a {@link com.liferay.portal.saml.NoSuchSSOSessionException} if it could not be found.
	*
	* @param key the key to search with
	* @return the matching s s o session
	* @throws com.liferay.portal.saml.NoSuchSSOSessionException if a matching s s o session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.saml.model.SSOSession findByKey(
		java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.saml.NoSuchSSOSessionException {
		return getPersistence().findByKey(key);
	}

	/**
	* Finds the s s o session where key = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param key the key to search with
	* @return the matching s s o session, or <code>null</code> if a matching s s o session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.saml.model.SSOSession fetchByKey(
		java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByKey(key);
	}

	/**
	* Finds the s s o session where key = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param key the key to search with
	* @return the matching s s o session, or <code>null</code> if a matching s s o session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.saml.model.SSOSession fetchByKey(
		java.lang.String key, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByKey(key, retrieveFromCache);
	}

	/**
	* Finds all the s s o sessions.
	*
	* @return the s s o sessions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.saml.model.SSOSession> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<com.liferay.portal.saml.model.SSOSession> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.liferay.portal.saml.model.SSOSession> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes the s s o session where key = &#63; from the database.
	*
	* @param key the key to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByKey(java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.saml.NoSuchSSOSessionException {
		getPersistence().removeByKey(key);
	}

	/**
	* Removes all the s s o sessions from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Counts all the s s o sessions where key = &#63;.
	*
	* @param key the key to search with
	* @return the number of matching s s o sessions
	* @throws SystemException if a system exception occurred
	*/
	public static int countByKey(java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByKey(key);
	}

	/**
	* Counts all the s s o sessions.
	*
	* @return the number of s s o sessions
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static SSOSessionPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (SSOSessionPersistence)PortletBeanLocatorUtil.locate(com.liferay.portal.saml.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					SSOSessionPersistence.class.getName());

			ReferenceRegistry.registerReference(SSOSessionUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(SSOSessionPersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(SSOSessionUtil.class, "_persistence");
	}

	private static SSOSessionPersistence _persistence;
}