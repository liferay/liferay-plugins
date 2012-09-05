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

import com.liferay.portal.oauth.model.ApplicationUser;
import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the application user service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ApplicationUserPersistenceImpl
 * @see ApplicationUserUtil
 * @generated
 */
public interface ApplicationUserPersistence extends BasePersistence<ApplicationUser> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ApplicationUserUtil} to access the application user persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the application user in the entity cache if it is enabled.
	*
	* @param applicationUser the application user
	*/
	public void cacheResult(
		com.liferay.portal.oauth.model.ApplicationUser applicationUser);

	/**
	* Caches the application users in the entity cache if it is enabled.
	*
	* @param applicationUsers the application users
	*/
	public void cacheResult(
		java.util.List<com.liferay.portal.oauth.model.ApplicationUser> applicationUsers);

	/**
	* Creates a new application user with the primary key. Does not add the application user to the database.
	*
	* @param oaauId the primary key for the new application user
	* @return the new application user
	*/
	public com.liferay.portal.oauth.model.ApplicationUser create(long oaauId);

	/**
	* Removes the application user with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param oaauId the primary key of the application user
	* @return the application user that was removed
	* @throws com.liferay.portal.oauth.NoSuchApplicationUserException if a application user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.ApplicationUser remove(long oaauId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplicationUserException;

	public com.liferay.portal.oauth.model.ApplicationUser updateImpl(
		com.liferay.portal.oauth.model.ApplicationUser applicationUser,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the application user with the primary key or throws a {@link com.liferay.portal.oauth.NoSuchApplicationUserException} if it could not be found.
	*
	* @param oaauId the primary key of the application user
	* @return the application user
	* @throws com.liferay.portal.oauth.NoSuchApplicationUserException if a application user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.ApplicationUser findByPrimaryKey(
		long oaauId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplicationUserException;

	/**
	* Returns the application user with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param oaauId the primary key of the application user
	* @return the application user, or <code>null</code> if a application user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.ApplicationUser fetchByPrimaryKey(
		long oaauId) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the application users where accessToken = &#63;.
	*
	* @param accessToken the access token
	* @return the matching application users
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.ApplicationUser> findByAccessToken(
		java.lang.String accessToken)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the application users where accessToken = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accessToken the access token
	* @param start the lower bound of the range of application users
	* @param end the upper bound of the range of application users (not inclusive)
	* @return the range of matching application users
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.ApplicationUser> findByAccessToken(
		java.lang.String accessToken, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the application users where accessToken = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accessToken the access token
	* @param start the lower bound of the range of application users
	* @param end the upper bound of the range of application users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching application users
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.ApplicationUser> findByAccessToken(
		java.lang.String accessToken, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first application user in the ordered set where accessToken = &#63;.
	*
	* @param accessToken the access token
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching application user
	* @throws com.liferay.portal.oauth.NoSuchApplicationUserException if a matching application user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.ApplicationUser findByAccessToken_First(
		java.lang.String accessToken,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplicationUserException;

	/**
	* Returns the first application user in the ordered set where accessToken = &#63;.
	*
	* @param accessToken the access token
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching application user, or <code>null</code> if a matching application user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.ApplicationUser fetchByAccessToken_First(
		java.lang.String accessToken,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last application user in the ordered set where accessToken = &#63;.
	*
	* @param accessToken the access token
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching application user
	* @throws com.liferay.portal.oauth.NoSuchApplicationUserException if a matching application user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.ApplicationUser findByAccessToken_Last(
		java.lang.String accessToken,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplicationUserException;

	/**
	* Returns the last application user in the ordered set where accessToken = &#63;.
	*
	* @param accessToken the access token
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching application user, or <code>null</code> if a matching application user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.ApplicationUser fetchByAccessToken_Last(
		java.lang.String accessToken,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the application users before and after the current application user in the ordered set where accessToken = &#63;.
	*
	* @param oaauId the primary key of the current application user
	* @param accessToken the access token
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next application user
	* @throws com.liferay.portal.oauth.NoSuchApplicationUserException if a application user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.ApplicationUser[] findByAccessToken_PrevAndNext(
		long oaauId, java.lang.String accessToken,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplicationUserException;

	/**
	* Returns all the application users where applicationId = &#63;.
	*
	* @param applicationId the application ID
	* @return the matching application users
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.ApplicationUser> findByApplicationId(
		long applicationId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the application users where applicationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param applicationId the application ID
	* @param start the lower bound of the range of application users
	* @param end the upper bound of the range of application users (not inclusive)
	* @return the range of matching application users
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.ApplicationUser> findByApplicationId(
		long applicationId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the application users where applicationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param applicationId the application ID
	* @param start the lower bound of the range of application users
	* @param end the upper bound of the range of application users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching application users
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.ApplicationUser> findByApplicationId(
		long applicationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first application user in the ordered set where applicationId = &#63;.
	*
	* @param applicationId the application ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching application user
	* @throws com.liferay.portal.oauth.NoSuchApplicationUserException if a matching application user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.ApplicationUser findByApplicationId_First(
		long applicationId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplicationUserException;

	/**
	* Returns the first application user in the ordered set where applicationId = &#63;.
	*
	* @param applicationId the application ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching application user, or <code>null</code> if a matching application user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.ApplicationUser fetchByApplicationId_First(
		long applicationId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last application user in the ordered set where applicationId = &#63;.
	*
	* @param applicationId the application ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching application user
	* @throws com.liferay.portal.oauth.NoSuchApplicationUserException if a matching application user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.ApplicationUser findByApplicationId_Last(
		long applicationId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplicationUserException;

	/**
	* Returns the last application user in the ordered set where applicationId = &#63;.
	*
	* @param applicationId the application ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching application user, or <code>null</code> if a matching application user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.ApplicationUser fetchByApplicationId_Last(
		long applicationId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the application users before and after the current application user in the ordered set where applicationId = &#63;.
	*
	* @param oaauId the primary key of the current application user
	* @param applicationId the application ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next application user
	* @throws com.liferay.portal.oauth.NoSuchApplicationUserException if a application user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.ApplicationUser[] findByApplicationId_PrevAndNext(
		long oaauId, long applicationId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplicationUserException;

	/**
	* Returns all the application users where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching application users
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.ApplicationUser> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the application users where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of application users
	* @param end the upper bound of the range of application users (not inclusive)
	* @return the range of matching application users
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.ApplicationUser> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the application users where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of application users
	* @param end the upper bound of the range of application users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching application users
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.ApplicationUser> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first application user in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching application user
	* @throws com.liferay.portal.oauth.NoSuchApplicationUserException if a matching application user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.ApplicationUser findByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplicationUserException;

	/**
	* Returns the first application user in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching application user, or <code>null</code> if a matching application user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.ApplicationUser fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last application user in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching application user
	* @throws com.liferay.portal.oauth.NoSuchApplicationUserException if a matching application user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.ApplicationUser findByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplicationUserException;

	/**
	* Returns the last application user in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching application user, or <code>null</code> if a matching application user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.ApplicationUser fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the application users before and after the current application user in the ordered set where userId = &#63;.
	*
	* @param oaauId the primary key of the current application user
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next application user
	* @throws com.liferay.portal.oauth.NoSuchApplicationUserException if a application user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.ApplicationUser[] findByUserId_PrevAndNext(
		long oaauId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplicationUserException;

	/**
	* Returns all the application users where applicationId = &#63; and authorized = &#63;.
	*
	* @param applicationId the application ID
	* @param authorized the authorized
	* @return the matching application users
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.ApplicationUser> findByA_A(
		long applicationId, boolean authorized)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the application users where applicationId = &#63; and authorized = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param applicationId the application ID
	* @param authorized the authorized
	* @param start the lower bound of the range of application users
	* @param end the upper bound of the range of application users (not inclusive)
	* @return the range of matching application users
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.ApplicationUser> findByA_A(
		long applicationId, boolean authorized, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the application users where applicationId = &#63; and authorized = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param applicationId the application ID
	* @param authorized the authorized
	* @param start the lower bound of the range of application users
	* @param end the upper bound of the range of application users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching application users
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.ApplicationUser> findByA_A(
		long applicationId, boolean authorized, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first application user in the ordered set where applicationId = &#63; and authorized = &#63;.
	*
	* @param applicationId the application ID
	* @param authorized the authorized
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching application user
	* @throws com.liferay.portal.oauth.NoSuchApplicationUserException if a matching application user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.ApplicationUser findByA_A_First(
		long applicationId, boolean authorized,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplicationUserException;

	/**
	* Returns the first application user in the ordered set where applicationId = &#63; and authorized = &#63;.
	*
	* @param applicationId the application ID
	* @param authorized the authorized
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching application user, or <code>null</code> if a matching application user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.ApplicationUser fetchByA_A_First(
		long applicationId, boolean authorized,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last application user in the ordered set where applicationId = &#63; and authorized = &#63;.
	*
	* @param applicationId the application ID
	* @param authorized the authorized
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching application user
	* @throws com.liferay.portal.oauth.NoSuchApplicationUserException if a matching application user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.ApplicationUser findByA_A_Last(
		long applicationId, boolean authorized,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplicationUserException;

	/**
	* Returns the last application user in the ordered set where applicationId = &#63; and authorized = &#63;.
	*
	* @param applicationId the application ID
	* @param authorized the authorized
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching application user, or <code>null</code> if a matching application user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.ApplicationUser fetchByA_A_Last(
		long applicationId, boolean authorized,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the application users before and after the current application user in the ordered set where applicationId = &#63; and authorized = &#63;.
	*
	* @param oaauId the primary key of the current application user
	* @param applicationId the application ID
	* @param authorized the authorized
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next application user
	* @throws com.liferay.portal.oauth.NoSuchApplicationUserException if a application user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.ApplicationUser[] findByA_A_PrevAndNext(
		long oaauId, long applicationId, boolean authorized,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplicationUserException;

	/**
	* Returns the application user where userId = &#63; and applicationId = &#63; or throws a {@link com.liferay.portal.oauth.NoSuchApplicationUserException} if it could not be found.
	*
	* @param userId the user ID
	* @param applicationId the application ID
	* @return the matching application user
	* @throws com.liferay.portal.oauth.NoSuchApplicationUserException if a matching application user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.ApplicationUser findByU_AP(
		long userId, long applicationId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplicationUserException;

	/**
	* Returns the application user where userId = &#63; and applicationId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param applicationId the application ID
	* @return the matching application user, or <code>null</code> if a matching application user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.ApplicationUser fetchByU_AP(
		long userId, long applicationId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the application user where userId = &#63; and applicationId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param applicationId the application ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching application user, or <code>null</code> if a matching application user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.ApplicationUser fetchByU_AP(
		long userId, long applicationId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the application users where userId = &#63; and authorized = &#63;.
	*
	* @param userId the user ID
	* @param authorized the authorized
	* @return the matching application users
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.ApplicationUser> findByU_AU(
		long userId, boolean authorized)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the application users where userId = &#63; and authorized = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param authorized the authorized
	* @param start the lower bound of the range of application users
	* @param end the upper bound of the range of application users (not inclusive)
	* @return the range of matching application users
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.ApplicationUser> findByU_AU(
		long userId, boolean authorized, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the application users where userId = &#63; and authorized = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param authorized the authorized
	* @param start the lower bound of the range of application users
	* @param end the upper bound of the range of application users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching application users
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.ApplicationUser> findByU_AU(
		long userId, boolean authorized, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first application user in the ordered set where userId = &#63; and authorized = &#63;.
	*
	* @param userId the user ID
	* @param authorized the authorized
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching application user
	* @throws com.liferay.portal.oauth.NoSuchApplicationUserException if a matching application user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.ApplicationUser findByU_AU_First(
		long userId, boolean authorized,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplicationUserException;

	/**
	* Returns the first application user in the ordered set where userId = &#63; and authorized = &#63;.
	*
	* @param userId the user ID
	* @param authorized the authorized
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching application user, or <code>null</code> if a matching application user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.ApplicationUser fetchByU_AU_First(
		long userId, boolean authorized,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last application user in the ordered set where userId = &#63; and authorized = &#63;.
	*
	* @param userId the user ID
	* @param authorized the authorized
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching application user
	* @throws com.liferay.portal.oauth.NoSuchApplicationUserException if a matching application user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.ApplicationUser findByU_AU_Last(
		long userId, boolean authorized,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplicationUserException;

	/**
	* Returns the last application user in the ordered set where userId = &#63; and authorized = &#63;.
	*
	* @param userId the user ID
	* @param authorized the authorized
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching application user, or <code>null</code> if a matching application user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.ApplicationUser fetchByU_AU_Last(
		long userId, boolean authorized,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the application users before and after the current application user in the ordered set where userId = &#63; and authorized = &#63;.
	*
	* @param oaauId the primary key of the current application user
	* @param userId the user ID
	* @param authorized the authorized
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next application user
	* @throws com.liferay.portal.oauth.NoSuchApplicationUserException if a application user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.ApplicationUser[] findByU_AU_PrevAndNext(
		long oaauId, long userId, boolean authorized,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplicationUserException;

	/**
	* Returns all the application users.
	*
	* @return the application users
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.ApplicationUser> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the application users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of application users
	* @param end the upper bound of the range of application users (not inclusive)
	* @return the range of application users
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.ApplicationUser> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the application users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of application users
	* @param end the upper bound of the range of application users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of application users
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.ApplicationUser> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the application users where accessToken = &#63; from the database.
	*
	* @param accessToken the access token
	* @throws SystemException if a system exception occurred
	*/
	public void removeByAccessToken(java.lang.String accessToken)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the application users where applicationId = &#63; from the database.
	*
	* @param applicationId the application ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByApplicationId(long applicationId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the application users where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the application users where applicationId = &#63; and authorized = &#63; from the database.
	*
	* @param applicationId the application ID
	* @param authorized the authorized
	* @throws SystemException if a system exception occurred
	*/
	public void removeByA_A(long applicationId, boolean authorized)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the application user where userId = &#63; and applicationId = &#63; from the database.
	*
	* @param userId the user ID
	* @param applicationId the application ID
	* @return the application user that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.ApplicationUser removeByU_AP(
		long userId, long applicationId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplicationUserException;

	/**
	* Removes all the application users where userId = &#63; and authorized = &#63; from the database.
	*
	* @param userId the user ID
	* @param authorized the authorized
	* @throws SystemException if a system exception occurred
	*/
	public void removeByU_AU(long userId, boolean authorized)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the application users from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of application users where accessToken = &#63;.
	*
	* @param accessToken the access token
	* @return the number of matching application users
	* @throws SystemException if a system exception occurred
	*/
	public int countByAccessToken(java.lang.String accessToken)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of application users where applicationId = &#63;.
	*
	* @param applicationId the application ID
	* @return the number of matching application users
	* @throws SystemException if a system exception occurred
	*/
	public int countByApplicationId(long applicationId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of application users where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching application users
	* @throws SystemException if a system exception occurred
	*/
	public int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of application users where applicationId = &#63; and authorized = &#63;.
	*
	* @param applicationId the application ID
	* @param authorized the authorized
	* @return the number of matching application users
	* @throws SystemException if a system exception occurred
	*/
	public int countByA_A(long applicationId, boolean authorized)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of application users where userId = &#63; and applicationId = &#63;.
	*
	* @param userId the user ID
	* @param applicationId the application ID
	* @return the number of matching application users
	* @throws SystemException if a system exception occurred
	*/
	public int countByU_AP(long userId, long applicationId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of application users where userId = &#63; and authorized = &#63;.
	*
	* @param userId the user ID
	* @param authorized the authorized
	* @return the number of matching application users
	* @throws SystemException if a system exception occurred
	*/
	public int countByU_AU(long userId, boolean authorized)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of application users.
	*
	* @return the number of application users
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}