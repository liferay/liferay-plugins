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

package com.liferay.opensocial.service.persistence;

import com.liferay.opensocial.model.OAuthToken;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the o auth token service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OAuthTokenPersistenceImpl
 * @see OAuthTokenUtil
 * @generated
 */
public interface OAuthTokenPersistence extends BasePersistence<OAuthToken> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link OAuthTokenUtil} to access the o auth token persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the o auth token in the entity cache if it is enabled.
	*
	* @param oAuthToken the o auth token to cache
	*/
	public void cacheResult(com.liferay.opensocial.model.OAuthToken oAuthToken);

	/**
	* Caches the o auth tokens in the entity cache if it is enabled.
	*
	* @param oAuthTokens the o auth tokens to cache
	*/
	public void cacheResult(
		java.util.List<com.liferay.opensocial.model.OAuthToken> oAuthTokens);

	/**
	* Creates a new o auth token with the primary key. Does not add the o auth token to the database.
	*
	* @param oAuthTokenId the primary key for the new o auth token
	* @return the new o auth token
	*/
	public com.liferay.opensocial.model.OAuthToken create(long oAuthTokenId);

	/**
	* Removes the o auth token with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param oAuthTokenId the primary key of the o auth token to remove
	* @return the o auth token that was removed
	* @throws com.liferay.opensocial.NoSuchOAuthTokenException if a o auth token with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.opensocial.model.OAuthToken remove(long oAuthTokenId)
		throws com.liferay.opensocial.NoSuchOAuthTokenException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.opensocial.model.OAuthToken updateImpl(
		com.liferay.opensocial.model.OAuthToken oAuthToken, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the o auth token with the primary key or throws a {@link com.liferay.opensocial.NoSuchOAuthTokenException} if it could not be found.
	*
	* @param oAuthTokenId the primary key of the o auth token to find
	* @return the o auth token
	* @throws com.liferay.opensocial.NoSuchOAuthTokenException if a o auth token with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.opensocial.model.OAuthToken findByPrimaryKey(
		long oAuthTokenId)
		throws com.liferay.opensocial.NoSuchOAuthTokenException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the o auth token with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param oAuthTokenId the primary key of the o auth token to find
	* @return the o auth token, or <code>null</code> if a o auth token with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.opensocial.model.OAuthToken fetchByPrimaryKey(
		long oAuthTokenId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the o auth tokens where gadgetId = &#63; and serviceName = &#63;.
	*
	* @param gadgetId the gadget ID to search with
	* @param serviceName the service name to search with
	* @return the matching o auth tokens
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.opensocial.model.OAuthToken> findByG_S(
		long gadgetId, java.lang.String serviceName)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the o auth tokens where gadgetId = &#63; and serviceName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param gadgetId the gadget ID to search with
	* @param serviceName the service name to search with
	* @param start the lower bound of the range of o auth tokens to return
	* @param end the upper bound of the range of o auth tokens to return (not inclusive)
	* @return the range of matching o auth tokens
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.opensocial.model.OAuthToken> findByG_S(
		long gadgetId, java.lang.String serviceName, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the o auth tokens where gadgetId = &#63; and serviceName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param gadgetId the gadget ID to search with
	* @param serviceName the service name to search with
	* @param start the lower bound of the range of o auth tokens to return
	* @param end the upper bound of the range of o auth tokens to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching o auth tokens
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.opensocial.model.OAuthToken> findByG_S(
		long gadgetId, java.lang.String serviceName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the first o auth token in the ordered set where gadgetId = &#63; and serviceName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param gadgetId the gadget ID to search with
	* @param serviceName the service name to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching o auth token
	* @throws com.liferay.opensocial.NoSuchOAuthTokenException if a matching o auth token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.opensocial.model.OAuthToken findByG_S_First(
		long gadgetId, java.lang.String serviceName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.opensocial.NoSuchOAuthTokenException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the last o auth token in the ordered set where gadgetId = &#63; and serviceName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param gadgetId the gadget ID to search with
	* @param serviceName the service name to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching o auth token
	* @throws com.liferay.opensocial.NoSuchOAuthTokenException if a matching o auth token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.opensocial.model.OAuthToken findByG_S_Last(
		long gadgetId, java.lang.String serviceName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.opensocial.NoSuchOAuthTokenException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the o auth tokens before and after the current o auth token in the ordered set where gadgetId = &#63; and serviceName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param oAuthTokenId the primary key of the current o auth token
	* @param gadgetId the gadget ID to search with
	* @param serviceName the service name to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next o auth token
	* @throws com.liferay.opensocial.NoSuchOAuthTokenException if a o auth token with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.opensocial.model.OAuthToken[] findByG_S_PrevAndNext(
		long oAuthTokenId, long gadgetId, java.lang.String serviceName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.opensocial.NoSuchOAuthTokenException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the o auth token where userId = &#63; and gadgetId = &#63; and serviceName = &#63; and moduleId = &#63; and tokenName = &#63; or throws a {@link com.liferay.opensocial.NoSuchOAuthTokenException} if it could not be found.
	*
	* @param userId the user ID to search with
	* @param gadgetId the gadget ID to search with
	* @param serviceName the service name to search with
	* @param moduleId the module ID to search with
	* @param tokenName the token name to search with
	* @return the matching o auth token
	* @throws com.liferay.opensocial.NoSuchOAuthTokenException if a matching o auth token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.opensocial.model.OAuthToken findByU_G_S_M_T(
		long userId, long gadgetId, java.lang.String serviceName,
		long moduleId, java.lang.String tokenName)
		throws com.liferay.opensocial.NoSuchOAuthTokenException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the o auth token where userId = &#63; and gadgetId = &#63; and serviceName = &#63; and moduleId = &#63; and tokenName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID to search with
	* @param gadgetId the gadget ID to search with
	* @param serviceName the service name to search with
	* @param moduleId the module ID to search with
	* @param tokenName the token name to search with
	* @return the matching o auth token, or <code>null</code> if a matching o auth token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.opensocial.model.OAuthToken fetchByU_G_S_M_T(
		long userId, long gadgetId, java.lang.String serviceName,
		long moduleId, java.lang.String tokenName)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the o auth token where userId = &#63; and gadgetId = &#63; and serviceName = &#63; and moduleId = &#63; and tokenName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID to search with
	* @param gadgetId the gadget ID to search with
	* @param serviceName the service name to search with
	* @param moduleId the module ID to search with
	* @param tokenName the token name to search with
	* @return the matching o auth token, or <code>null</code> if a matching o auth token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.opensocial.model.OAuthToken fetchByU_G_S_M_T(
		long userId, long gadgetId, java.lang.String serviceName,
		long moduleId, java.lang.String tokenName, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the o auth tokens.
	*
	* @return the o auth tokens
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.opensocial.model.OAuthToken> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the o auth tokens.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of o auth tokens to return
	* @param end the upper bound of the range of o auth tokens to return (not inclusive)
	* @return the range of o auth tokens
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.opensocial.model.OAuthToken> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the o auth tokens.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of o auth tokens to return
	* @param end the upper bound of the range of o auth tokens to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of o auth tokens
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.opensocial.model.OAuthToken> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the o auth tokens where gadgetId = &#63; and serviceName = &#63; from the database.
	*
	* @param gadgetId the gadget ID to search with
	* @param serviceName the service name to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByG_S(long gadgetId, java.lang.String serviceName)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the o auth token where userId = &#63; and gadgetId = &#63; and serviceName = &#63; and moduleId = &#63; and tokenName = &#63; from the database.
	*
	* @param userId the user ID to search with
	* @param gadgetId the gadget ID to search with
	* @param serviceName the service name to search with
	* @param moduleId the module ID to search with
	* @param tokenName the token name to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByU_G_S_M_T(long userId, long gadgetId,
		java.lang.String serviceName, long moduleId, java.lang.String tokenName)
		throws com.liferay.opensocial.NoSuchOAuthTokenException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the o auth tokens from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the o auth tokens where gadgetId = &#63; and serviceName = &#63;.
	*
	* @param gadgetId the gadget ID to search with
	* @param serviceName the service name to search with
	* @return the number of matching o auth tokens
	* @throws SystemException if a system exception occurred
	*/
	public int countByG_S(long gadgetId, java.lang.String serviceName)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the o auth tokens where userId = &#63; and gadgetId = &#63; and serviceName = &#63; and moduleId = &#63; and tokenName = &#63;.
	*
	* @param userId the user ID to search with
	* @param gadgetId the gadget ID to search with
	* @param serviceName the service name to search with
	* @param moduleId the module ID to search with
	* @param tokenName the token name to search with
	* @return the number of matching o auth tokens
	* @throws SystemException if a system exception occurred
	*/
	public int countByU_G_S_M_T(long userId, long gadgetId,
		java.lang.String serviceName, long moduleId, java.lang.String tokenName)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the o auth tokens.
	*
	* @return the number of o auth tokens
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public OAuthToken remove(OAuthToken oAuthToken) throws SystemException;
}