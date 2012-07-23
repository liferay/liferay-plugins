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
	* Returns all the o auth applications_ userses where applicationId = &#63; and authorized = &#63;.
	*
	* @param applicationId the application ID
	* @param authorized the authorized
	* @return the matching o auth applications_ userses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.oauth.model.OAuthApplications_Users> findByA_A(
		long applicationId, boolean authorized)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByA_A(applicationId, authorized);
	}

	/**
	* Returns a range of all the o auth applications_ userses where applicationId = &#63; and authorized = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param applicationId the application ID
	* @param authorized the authorized
	* @param start the lower bound of the range of o auth applications_ userses
	* @param end the upper bound of the range of o auth applications_ userses (not inclusive)
	* @return the range of matching o auth applications_ userses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.oauth.model.OAuthApplications_Users> findByA_A(
		long applicationId, boolean authorized, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByA_A(applicationId, authorized, start, end);
	}

	/**
	* Returns an ordered range of all the o auth applications_ userses where applicationId = &#63; and authorized = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param applicationId the application ID
	* @param authorized the authorized
	* @param start the lower bound of the range of o auth applications_ userses
	* @param end the upper bound of the range of o auth applications_ userses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching o auth applications_ userses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.oauth.model.OAuthApplications_Users> findByA_A(
		long applicationId, boolean authorized, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByA_A(applicationId, authorized, start, end,
			orderByComparator);
	}

	/**
	* Returns the first o auth applications_ users in the ordered set where applicationId = &#63; and authorized = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param applicationId the application ID
	* @param authorized the authorized
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching o auth applications_ users
	* @throws com.liferay.portal.oauth.NoSuchApplications_UsersException if a matching o auth applications_ users could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.oauth.model.OAuthApplications_Users findByA_A_First(
		long applicationId, boolean authorized,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplications_UsersException {
		return getPersistence()
				   .findByA_A_First(applicationId, authorized, orderByComparator);
	}

	/**
	* Returns the last o auth applications_ users in the ordered set where applicationId = &#63; and authorized = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param applicationId the application ID
	* @param authorized the authorized
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching o auth applications_ users
	* @throws com.liferay.portal.oauth.NoSuchApplications_UsersException if a matching o auth applications_ users could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.oauth.model.OAuthApplications_Users findByA_A_Last(
		long applicationId, boolean authorized,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplications_UsersException {
		return getPersistence()
				   .findByA_A_Last(applicationId, authorized, orderByComparator);
	}

	/**
	* Returns the o auth applications_ userses before and after the current o auth applications_ users in the ordered set where applicationId = &#63; and authorized = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param oaauid the primary key of the current o auth applications_ users
	* @param applicationId the application ID
	* @param authorized the authorized
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next o auth applications_ users
	* @throws com.liferay.portal.oauth.NoSuchApplications_UsersException if a o auth applications_ users with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.oauth.model.OAuthApplications_Users[] findByA_A_PrevAndNext(
		long oaauid, long applicationId, boolean authorized,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplications_UsersException {
		return getPersistence()
				   .findByA_A_PrevAndNext(oaauid, applicationId, authorized,
			orderByComparator);
	}

	/**
	* Returns all the o auth applications_ userses that the user has permission to view where applicationId = &#63; and authorized = &#63;.
	*
	* @param applicationId the application ID
	* @param authorized the authorized
	* @return the matching o auth applications_ userses that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.oauth.model.OAuthApplications_Users> filterFindByA_A(
		long applicationId, boolean authorized)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByA_A(applicationId, authorized);
	}

	/**
	* Returns a range of all the o auth applications_ userses that the user has permission to view where applicationId = &#63; and authorized = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param applicationId the application ID
	* @param authorized the authorized
	* @param start the lower bound of the range of o auth applications_ userses
	* @param end the upper bound of the range of o auth applications_ userses (not inclusive)
	* @return the range of matching o auth applications_ userses that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.oauth.model.OAuthApplications_Users> filterFindByA_A(
		long applicationId, boolean authorized, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByA_A(applicationId, authorized, start, end);
	}

	/**
	* Returns an ordered range of all the o auth applications_ userses that the user has permissions to view where applicationId = &#63; and authorized = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param applicationId the application ID
	* @param authorized the authorized
	* @param start the lower bound of the range of o auth applications_ userses
	* @param end the upper bound of the range of o auth applications_ userses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching o auth applications_ userses that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.oauth.model.OAuthApplications_Users> filterFindByA_A(
		long applicationId, boolean authorized, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByA_A(applicationId, authorized, start, end,
			orderByComparator);
	}

	/**
	* Returns the o auth applications_ userses before and after the current o auth applications_ users in the ordered set of o auth applications_ userses that the user has permission to view where applicationId = &#63; and authorized = &#63;.
	*
	* @param oaauid the primary key of the current o auth applications_ users
	* @param applicationId the application ID
	* @param authorized the authorized
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next o auth applications_ users
	* @throws com.liferay.portal.oauth.NoSuchApplications_UsersException if a o auth applications_ users with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.oauth.model.OAuthApplications_Users[] filterFindByA_A_PrevAndNext(
		long oaauid, long applicationId, boolean authorized,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplications_UsersException {
		return getPersistence()
				   .filterFindByA_A_PrevAndNext(oaauid, applicationId,
			authorized, orderByComparator);
	}

	/**
	* Returns all the o auth applications_ userses where accessToken = &#63;.
	*
	* @param accessToken the access token
	* @return the matching o auth applications_ userses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.oauth.model.OAuthApplications_Users> findByAccessToken(
		java.lang.String accessToken)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAccessToken(accessToken);
	}

	/**
	* Returns a range of all the o auth applications_ userses where accessToken = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accessToken the access token
	* @param start the lower bound of the range of o auth applications_ userses
	* @param end the upper bound of the range of o auth applications_ userses (not inclusive)
	* @return the range of matching o auth applications_ userses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.oauth.model.OAuthApplications_Users> findByAccessToken(
		java.lang.String accessToken, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAccessToken(accessToken, start, end);
	}

	/**
	* Returns an ordered range of all the o auth applications_ userses where accessToken = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accessToken the access token
	* @param start the lower bound of the range of o auth applications_ userses
	* @param end the upper bound of the range of o auth applications_ userses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching o auth applications_ userses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.oauth.model.OAuthApplications_Users> findByAccessToken(
		java.lang.String accessToken, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAccessToken(accessToken, start, end, orderByComparator);
	}

	/**
	* Returns the first o auth applications_ users in the ordered set where accessToken = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accessToken the access token
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching o auth applications_ users
	* @throws com.liferay.portal.oauth.NoSuchApplications_UsersException if a matching o auth applications_ users could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.oauth.model.OAuthApplications_Users findByAccessToken_First(
		java.lang.String accessToken,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplications_UsersException {
		return getPersistence()
				   .findByAccessToken_First(accessToken, orderByComparator);
	}

	/**
	* Returns the last o auth applications_ users in the ordered set where accessToken = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accessToken the access token
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching o auth applications_ users
	* @throws com.liferay.portal.oauth.NoSuchApplications_UsersException if a matching o auth applications_ users could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.oauth.model.OAuthApplications_Users findByAccessToken_Last(
		java.lang.String accessToken,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplications_UsersException {
		return getPersistence()
				   .findByAccessToken_Last(accessToken, orderByComparator);
	}

	/**
	* Returns the o auth applications_ userses before and after the current o auth applications_ users in the ordered set where accessToken = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param oaauid the primary key of the current o auth applications_ users
	* @param accessToken the access token
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next o auth applications_ users
	* @throws com.liferay.portal.oauth.NoSuchApplications_UsersException if a o auth applications_ users with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.oauth.model.OAuthApplications_Users[] findByAccessToken_PrevAndNext(
		long oaauid, java.lang.String accessToken,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplications_UsersException {
		return getPersistence()
				   .findByAccessToken_PrevAndNext(oaauid, accessToken,
			orderByComparator);
	}

	/**
	* Returns all the o auth applications_ userses that the user has permission to view where accessToken = &#63;.
	*
	* @param accessToken the access token
	* @return the matching o auth applications_ userses that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.oauth.model.OAuthApplications_Users> filterFindByAccessToken(
		java.lang.String accessToken)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByAccessToken(accessToken);
	}

	/**
	* Returns a range of all the o auth applications_ userses that the user has permission to view where accessToken = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accessToken the access token
	* @param start the lower bound of the range of o auth applications_ userses
	* @param end the upper bound of the range of o auth applications_ userses (not inclusive)
	* @return the range of matching o auth applications_ userses that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.oauth.model.OAuthApplications_Users> filterFindByAccessToken(
		java.lang.String accessToken, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByAccessToken(accessToken, start, end);
	}

	/**
	* Returns an ordered range of all the o auth applications_ userses that the user has permissions to view where accessToken = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accessToken the access token
	* @param start the lower bound of the range of o auth applications_ userses
	* @param end the upper bound of the range of o auth applications_ userses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching o auth applications_ userses that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.oauth.model.OAuthApplications_Users> filterFindByAccessToken(
		java.lang.String accessToken, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByAccessToken(accessToken, start, end,
			orderByComparator);
	}

	/**
	* Returns the o auth applications_ userses before and after the current o auth applications_ users in the ordered set of o auth applications_ userses that the user has permission to view where accessToken = &#63;.
	*
	* @param oaauid the primary key of the current o auth applications_ users
	* @param accessToken the access token
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next o auth applications_ users
	* @throws com.liferay.portal.oauth.NoSuchApplications_UsersException if a o auth applications_ users with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.oauth.model.OAuthApplications_Users[] filterFindByAccessToken_PrevAndNext(
		long oaauid, java.lang.String accessToken,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplications_UsersException {
		return getPersistence()
				   .filterFindByAccessToken_PrevAndNext(oaauid, accessToken,
			orderByComparator);
	}

	/**
	* Returns all the o auth applications_ userses where applicationId = &#63;.
	*
	* @param applicationId the application ID
	* @return the matching o auth applications_ userses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.oauth.model.OAuthApplications_Users> findByApplicationId(
		long applicationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByApplicationId(applicationId);
	}

	/**
	* Returns a range of all the o auth applications_ userses where applicationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param applicationId the application ID
	* @param start the lower bound of the range of o auth applications_ userses
	* @param end the upper bound of the range of o auth applications_ userses (not inclusive)
	* @return the range of matching o auth applications_ userses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.oauth.model.OAuthApplications_Users> findByApplicationId(
		long applicationId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByApplicationId(applicationId, start, end);
	}

	/**
	* Returns an ordered range of all the o auth applications_ userses where applicationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param applicationId the application ID
	* @param start the lower bound of the range of o auth applications_ userses
	* @param end the upper bound of the range of o auth applications_ userses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching o auth applications_ userses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.oauth.model.OAuthApplications_Users> findByApplicationId(
		long applicationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByApplicationId(applicationId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first o auth applications_ users in the ordered set where applicationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param applicationId the application ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching o auth applications_ users
	* @throws com.liferay.portal.oauth.NoSuchApplications_UsersException if a matching o auth applications_ users could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.oauth.model.OAuthApplications_Users findByApplicationId_First(
		long applicationId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplications_UsersException {
		return getPersistence()
				   .findByApplicationId_First(applicationId, orderByComparator);
	}

	/**
	* Returns the last o auth applications_ users in the ordered set where applicationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param applicationId the application ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching o auth applications_ users
	* @throws com.liferay.portal.oauth.NoSuchApplications_UsersException if a matching o auth applications_ users could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.oauth.model.OAuthApplications_Users findByApplicationId_Last(
		long applicationId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplications_UsersException {
		return getPersistence()
				   .findByApplicationId_Last(applicationId, orderByComparator);
	}

	/**
	* Returns the o auth applications_ userses before and after the current o auth applications_ users in the ordered set where applicationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param oaauid the primary key of the current o auth applications_ users
	* @param applicationId the application ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next o auth applications_ users
	* @throws com.liferay.portal.oauth.NoSuchApplications_UsersException if a o auth applications_ users with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.oauth.model.OAuthApplications_Users[] findByApplicationId_PrevAndNext(
		long oaauid, long applicationId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplications_UsersException {
		return getPersistence()
				   .findByApplicationId_PrevAndNext(oaauid, applicationId,
			orderByComparator);
	}

	/**
	* Returns all the o auth applications_ userses that the user has permission to view where applicationId = &#63;.
	*
	* @param applicationId the application ID
	* @return the matching o auth applications_ userses that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.oauth.model.OAuthApplications_Users> filterFindByApplicationId(
		long applicationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByApplicationId(applicationId);
	}

	/**
	* Returns a range of all the o auth applications_ userses that the user has permission to view where applicationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param applicationId the application ID
	* @param start the lower bound of the range of o auth applications_ userses
	* @param end the upper bound of the range of o auth applications_ userses (not inclusive)
	* @return the range of matching o auth applications_ userses that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.oauth.model.OAuthApplications_Users> filterFindByApplicationId(
		long applicationId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByApplicationId(applicationId, start, end);
	}

	/**
	* Returns an ordered range of all the o auth applications_ userses that the user has permissions to view where applicationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param applicationId the application ID
	* @param start the lower bound of the range of o auth applications_ userses
	* @param end the upper bound of the range of o auth applications_ userses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching o auth applications_ userses that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.oauth.model.OAuthApplications_Users> filterFindByApplicationId(
		long applicationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByApplicationId(applicationId, start, end,
			orderByComparator);
	}

	/**
	* Returns the o auth applications_ userses before and after the current o auth applications_ users in the ordered set of o auth applications_ userses that the user has permission to view where applicationId = &#63;.
	*
	* @param oaauid the primary key of the current o auth applications_ users
	* @param applicationId the application ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next o auth applications_ users
	* @throws com.liferay.portal.oauth.NoSuchApplications_UsersException if a o auth applications_ users with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.oauth.model.OAuthApplications_Users[] filterFindByApplicationId_PrevAndNext(
		long oaauid, long applicationId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplications_UsersException {
		return getPersistence()
				   .filterFindByApplicationId_PrevAndNext(oaauid,
			applicationId, orderByComparator);
	}

	/**
	* Returns all the o auth applications_ userses where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching o auth applications_ userses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.oauth.model.OAuthApplications_Users> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId);
	}

	/**
	* Returns a range of all the o auth applications_ userses where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of o auth applications_ userses
	* @param end the upper bound of the range of o auth applications_ userses (not inclusive)
	* @return the range of matching o auth applications_ userses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.oauth.model.OAuthApplications_Users> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	* Returns an ordered range of all the o auth applications_ userses where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of o auth applications_ userses
	* @param end the upper bound of the range of o auth applications_ userses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching o auth applications_ userses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.oauth.model.OAuthApplications_Users> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
	}

	/**
	* Returns the first o auth applications_ users in the ordered set where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching o auth applications_ users
	* @throws com.liferay.portal.oauth.NoSuchApplications_UsersException if a matching o auth applications_ users could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.oauth.model.OAuthApplications_Users findByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplications_UsersException {
		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the last o auth applications_ users in the ordered set where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching o auth applications_ users
	* @throws com.liferay.portal.oauth.NoSuchApplications_UsersException if a matching o auth applications_ users could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.oauth.model.OAuthApplications_Users findByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplications_UsersException {
		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the o auth applications_ userses before and after the current o auth applications_ users in the ordered set where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param oaauid the primary key of the current o auth applications_ users
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next o auth applications_ users
	* @throws com.liferay.portal.oauth.NoSuchApplications_UsersException if a o auth applications_ users with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.oauth.model.OAuthApplications_Users[] findByUserId_PrevAndNext(
		long oaauid, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplications_UsersException {
		return getPersistence()
				   .findByUserId_PrevAndNext(oaauid, userId, orderByComparator);
	}

	/**
	* Returns all the o auth applications_ userses that the user has permission to view where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching o auth applications_ userses that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.oauth.model.OAuthApplications_Users> filterFindByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByUserId(userId);
	}

	/**
	* Returns a range of all the o auth applications_ userses that the user has permission to view where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of o auth applications_ userses
	* @param end the upper bound of the range of o auth applications_ userses (not inclusive)
	* @return the range of matching o auth applications_ userses that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.oauth.model.OAuthApplications_Users> filterFindByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByUserId(userId, start, end);
	}

	/**
	* Returns an ordered range of all the o auth applications_ userses that the user has permissions to view where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of o auth applications_ userses
	* @param end the upper bound of the range of o auth applications_ userses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching o auth applications_ userses that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.oauth.model.OAuthApplications_Users> filterFindByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByUserId(userId, start, end, orderByComparator);
	}

	/**
	* Returns the o auth applications_ userses before and after the current o auth applications_ users in the ordered set of o auth applications_ userses that the user has permission to view where userId = &#63;.
	*
	* @param oaauid the primary key of the current o auth applications_ users
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next o auth applications_ users
	* @throws com.liferay.portal.oauth.NoSuchApplications_UsersException if a o auth applications_ users with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.oauth.model.OAuthApplications_Users[] filterFindByUserId_PrevAndNext(
		long oaauid, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplications_UsersException {
		return getPersistence()
				   .filterFindByUserId_PrevAndNext(oaauid, userId,
			orderByComparator);
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
	* Removes all the o auth applications_ userses where applicationId = &#63; and authorized = &#63; from the database.
	*
	* @param applicationId the application ID
	* @param authorized the authorized
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByA_A(long applicationId, boolean authorized)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByA_A(applicationId, authorized);
	}

	/**
	* Removes all the o auth applications_ userses where accessToken = &#63; from the database.
	*
	* @param accessToken the access token
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByAccessToken(java.lang.String accessToken)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByAccessToken(accessToken);
	}

	/**
	* Removes all the o auth applications_ userses where applicationId = &#63; from the database.
	*
	* @param applicationId the application ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByApplicationId(long applicationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByApplicationId(applicationId);
	}

	/**
	* Removes all the o auth applications_ userses where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUserId(userId);
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
	* Returns the number of o auth applications_ userses where applicationId = &#63; and authorized = &#63;.
	*
	* @param applicationId the application ID
	* @param authorized the authorized
	* @return the number of matching o auth applications_ userses
	* @throws SystemException if a system exception occurred
	*/
	public static int countByA_A(long applicationId, boolean authorized)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByA_A(applicationId, authorized);
	}

	/**
	* Returns the number of o auth applications_ userses that the user has permission to view where applicationId = &#63; and authorized = &#63;.
	*
	* @param applicationId the application ID
	* @param authorized the authorized
	* @return the number of matching o auth applications_ userses that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByA_A(long applicationId, boolean authorized)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByA_A(applicationId, authorized);
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
	* Returns the number of o auth applications_ userses that the user has permission to view where accessToken = &#63;.
	*
	* @param accessToken the access token
	* @return the number of matching o auth applications_ userses that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByAccessToken(java.lang.String accessToken)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByAccessToken(accessToken);
	}

	/**
	* Returns the number of o auth applications_ userses where applicationId = &#63;.
	*
	* @param applicationId the application ID
	* @return the number of matching o auth applications_ userses
	* @throws SystemException if a system exception occurred
	*/
	public static int countByApplicationId(long applicationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByApplicationId(applicationId);
	}

	/**
	* Returns the number of o auth applications_ userses that the user has permission to view where applicationId = &#63;.
	*
	* @param applicationId the application ID
	* @return the number of matching o auth applications_ userses that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByApplicationId(long applicationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByApplicationId(applicationId);
	}

	/**
	* Returns the number of o auth applications_ userses where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching o auth applications_ userses
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Returns the number of o auth applications_ userses that the user has permission to view where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching o auth applications_ userses that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByUserId(userId);
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