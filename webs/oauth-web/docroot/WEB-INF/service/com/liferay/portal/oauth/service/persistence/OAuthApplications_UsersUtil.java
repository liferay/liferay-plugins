/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.oauth.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.oauth.model.OAuthApplications_Users;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the o auth applications_ users service. This utility wraps {@link OAuthApplications_UsersPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OAuthApplications_UsersPersistence
 * @see OAuthApplications_UsersPersistenceImpl
 * @generated
 */
public class OAuthApplications_UsersUtil {
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
	public static void clearCache(
		OAuthApplications_Users oAuthApplications_Users) {
		getPersistence().clearCache(oAuthApplications_Users);
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
	public static List<OAuthApplications_Users> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<OAuthApplications_Users> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<OAuthApplications_Users> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static OAuthApplications_Users update(
		OAuthApplications_Users oAuthApplications_Users, boolean merge)
		throws SystemException {
		return getPersistence().update(oAuthApplications_Users, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static OAuthApplications_Users update(
		OAuthApplications_Users oAuthApplications_Users, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence()
				   .update(oAuthApplications_Users, merge, serviceContext);
	}

	/**
	* Caches the o auth applications_ users in the entity cache if it is enabled.
	*
	* @param oAuthApplications_Users the o auth applications_ users
	*/
	public static void cacheResult(
		com.liferay.portal.oauth.model.OAuthApplications_Users oAuthApplications_Users) {
		getPersistence().cacheResult(oAuthApplications_Users);
	}

	/**
	* Caches the o auth applications_ userses in the entity cache if it is enabled.
	*
	* @param oAuthApplications_Userses the o auth applications_ userses
	*/
	public static void cacheResult(
		java.util.List<com.liferay.portal.oauth.model.OAuthApplications_Users> oAuthApplications_Userses) {
		getPersistence().cacheResult(oAuthApplications_Userses);
	}

	/**
	* Creates a new o auth applications_ users with the primary key. Does not add the o auth applications_ users to the database.
	*
	* @param oaauid the primary key for the new o auth applications_ users
	* @return the new o auth applications_ users
	*/
	public static com.liferay.portal.oauth.model.OAuthApplications_Users create(
		long oaauid) {
		return getPersistence().create(oaauid);
	}

	/**
	* Removes the o auth applications_ users with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param oaauid the primary key of the o auth applications_ users
	* @return the o auth applications_ users that was removed
	* @throws com.liferay.portal.oauth.NoSuchApplications_UsersException if a o auth applications_ users with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.oauth.model.OAuthApplications_Users remove(
		long oaauid)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplications_UsersException {
		return getPersistence().remove(oaauid);
	}

	public static com.liferay.portal.oauth.model.OAuthApplications_Users updateImpl(
		com.liferay.portal.oauth.model.OAuthApplications_Users oAuthApplications_Users,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(oAuthApplications_Users, merge);
	}

	/**
	* Returns the o auth applications_ users with the primary key or throws a {@link com.liferay.portal.oauth.NoSuchApplications_UsersException} if it could not be found.
	*
	* @param oaauid the primary key of the o auth applications_ users
	* @return the o auth applications_ users
	* @throws com.liferay.portal.oauth.NoSuchApplications_UsersException if a o auth applications_ users with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.oauth.model.OAuthApplications_Users findByPrimaryKey(
		long oaauid)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplications_UsersException {
		return getPersistence().findByPrimaryKey(oaauid);
	}

	/**
	* Returns the o auth applications_ users with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param oaauid the primary key of the o auth applications_ users
	* @return the o auth applications_ users, or <code>null</code> if a o auth applications_ users with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.oauth.model.OAuthApplications_Users fetchByPrimaryKey(
		long oaauid) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(oaauid);
	}

	/**
	* Returns the o auth applications_ users where applicationId = &#63; and userId = &#63; or throws a {@link com.liferay.portal.oauth.NoSuchApplications_UsersException} if it could not be found.
	*
	* @param applicationId the application ID
	* @param userId the user ID
	* @return the matching o auth applications_ users
	* @throws com.liferay.portal.oauth.NoSuchApplications_UsersException if a matching o auth applications_ users could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.oauth.model.OAuthApplications_Users findByA_U(
		long applicationId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplications_UsersException {
		return getPersistence().findByA_U(applicationId, userId);
	}

	/**
	* Returns the o auth applications_ users where applicationId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param applicationId the application ID
	* @param userId the user ID
	* @return the matching o auth applications_ users, or <code>null</code> if a matching o auth applications_ users could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.oauth.model.OAuthApplications_Users fetchByA_U(
		long applicationId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByA_U(applicationId, userId);
	}

	/**
	* Returns the o auth applications_ users where applicationId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param applicationId the application ID
	* @param userId the user ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching o auth applications_ users, or <code>null</code> if a matching o auth applications_ users could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.oauth.model.OAuthApplications_Users fetchByA_U(
		long applicationId, long userId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByA_U(applicationId, userId, retrieveFromCache);
	}

	/**
	* Returns the o auth applications_ users where accessToken = &#63; or throws a {@link com.liferay.portal.oauth.NoSuchApplications_UsersException} if it could not be found.
	*
	* @param accessToken the access token
	* @return the matching o auth applications_ users
	* @throws com.liferay.portal.oauth.NoSuchApplications_UsersException if a matching o auth applications_ users could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.oauth.model.OAuthApplications_Users findByAccessToken(
		java.lang.String accessToken)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplications_UsersException {
		return getPersistence().findByAccessToken(accessToken);
	}

	/**
	* Returns the o auth applications_ users where accessToken = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param accessToken the access token
	* @return the matching o auth applications_ users, or <code>null</code> if a matching o auth applications_ users could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.oauth.model.OAuthApplications_Users fetchByAccessToken(
		java.lang.String accessToken)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByAccessToken(accessToken);
	}

	/**
	* Returns the o auth applications_ users where accessToken = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param accessToken the access token
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching o auth applications_ users, or <code>null</code> if a matching o auth applications_ users could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.oauth.model.OAuthApplications_Users fetchByAccessToken(
		java.lang.String accessToken, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAccessToken(accessToken, retrieveFromCache);
	}

	/**
	* Returns all the o auth applications_ userses.
	*
	* @return the o auth applications_ userses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.oauth.model.OAuthApplications_Users> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the o auth applications_ userses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of o auth applications_ userses
	* @param end the upper bound of the range of o auth applications_ userses (not inclusive)
	* @return the range of o auth applications_ userses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.oauth.model.OAuthApplications_Users> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the o auth applications_ userses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of o auth applications_ userses
	* @param end the upper bound of the range of o auth applications_ userses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of o auth applications_ userses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.oauth.model.OAuthApplications_Users> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes the o auth applications_ users where applicationId = &#63; and userId = &#63; from the database.
	*
	* @param applicationId the application ID
	* @param userId the user ID
	* @return the o auth applications_ users that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.oauth.model.OAuthApplications_Users removeByA_U(
		long applicationId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplications_UsersException {
		return getPersistence().removeByA_U(applicationId, userId);
	}

	/**
	* Removes the o auth applications_ users where accessToken = &#63; from the database.
	*
	* @param accessToken the access token
	* @return the o auth applications_ users that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.oauth.model.OAuthApplications_Users removeByAccessToken(
		java.lang.String accessToken)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplications_UsersException {
		return getPersistence().removeByAccessToken(accessToken);
	}

	/**
	* Removes all the o auth applications_ userses from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of o auth applications_ userses where applicationId = &#63; and userId = &#63;.
	*
	* @param applicationId the application ID
	* @param userId the user ID
	* @return the number of matching o auth applications_ userses
	* @throws SystemException if a system exception occurred
	*/
	public static int countByA_U(long applicationId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByA_U(applicationId, userId);
	}

	/**
	* Returns the number of o auth applications_ userses where accessToken = &#63;.
	*
	* @param accessToken the access token
	* @return the number of matching o auth applications_ userses
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAccessToken(java.lang.String accessToken)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAccessToken(accessToken);
	}

	/**
	* Returns the number of o auth applications_ userses.
	*
	* @return the number of o auth applications_ userses
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static OAuthApplications_UsersPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (OAuthApplications_UsersPersistence)PortletBeanLocatorUtil.locate(com.liferay.portal.oauth.service.ClpSerializer.getServletContextName(),
					OAuthApplications_UsersPersistence.class.getName());

			ReferenceRegistry.registerReference(OAuthApplications_UsersUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(OAuthApplications_UsersPersistence persistence) {
	}

	private static OAuthApplications_UsersPersistence _persistence;
}