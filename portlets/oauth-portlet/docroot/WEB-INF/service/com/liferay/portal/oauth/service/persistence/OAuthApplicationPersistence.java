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

import com.liferay.portal.oauth.model.OAuthApplication;
import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the o auth application service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OAuthApplicationPersistenceImpl
 * @see OAuthApplicationUtil
 * @generated
 */
public interface OAuthApplicationPersistence extends BasePersistence<OAuthApplication> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link OAuthApplicationUtil} to access the o auth application persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the o auth application in the entity cache if it is enabled.
	*
	* @param oAuthApplication the o auth application
	*/
	public void cacheResult(
		com.liferay.portal.oauth.model.OAuthApplication oAuthApplication);

	/**
	* Caches the o auth applications in the entity cache if it is enabled.
	*
	* @param oAuthApplications the o auth applications
	*/
	public void cacheResult(
		java.util.List<com.liferay.portal.oauth.model.OAuthApplication> oAuthApplications);

	/**
	* Creates a new o auth application with the primary key. Does not add the o auth application to the database.
	*
	* @param applicationId the primary key for the new o auth application
	* @return the new o auth application
	*/
	public com.liferay.portal.oauth.model.OAuthApplication create(
		long applicationId);

	/**
	* Removes the o auth application with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param applicationId the primary key of the o auth application
	* @return the o auth application that was removed
	* @throws com.liferay.portal.oauth.NoSuchApplicationException if a o auth application with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.OAuthApplication remove(
		long applicationId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplicationException;

	public com.liferay.portal.oauth.model.OAuthApplication updateImpl(
		com.liferay.portal.oauth.model.OAuthApplication oAuthApplication,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the o auth application with the primary key or throws a {@link com.liferay.portal.oauth.NoSuchApplicationException} if it could not be found.
	*
	* @param applicationId the primary key of the o auth application
	* @return the o auth application
	* @throws com.liferay.portal.oauth.NoSuchApplicationException if a o auth application with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.OAuthApplication findByPrimaryKey(
		long applicationId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplicationException;

	/**
	* Returns the o auth application with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param applicationId the primary key of the o auth application
	* @return the o auth application, or <code>null</code> if a o auth application with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.OAuthApplication fetchByPrimaryKey(
		long applicationId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the o auth applications where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.OAuthApplication> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the o auth applications where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of o auth applications
	* @param end the upper bound of the range of o auth applications (not inclusive)
	* @return the range of matching o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.OAuthApplication> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the o auth applications where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of o auth applications
	* @param end the upper bound of the range of o auth applications (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.OAuthApplication> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first o auth application in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching o auth application
	* @throws com.liferay.portal.oauth.NoSuchApplicationException if a matching o auth application could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.OAuthApplication findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplicationException;

	/**
	* Returns the last o auth application in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching o auth application
	* @throws com.liferay.portal.oauth.NoSuchApplicationException if a matching o auth application could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.OAuthApplication findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplicationException;

	/**
	* Returns the o auth applications before and after the current o auth application in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param applicationId the primary key of the current o auth application
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next o auth application
	* @throws com.liferay.portal.oauth.NoSuchApplicationException if a o auth application with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.OAuthApplication[] findByCompanyId_PrevAndNext(
		long applicationId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplicationException;

	/**
	* Returns all the o auth applications that the user has permission to view where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching o auth applications that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.OAuthApplication> filterFindByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the o auth applications that the user has permission to view where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of o auth applications
	* @param end the upper bound of the range of o auth applications (not inclusive)
	* @return the range of matching o auth applications that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.OAuthApplication> filterFindByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the o auth applications that the user has permissions to view where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of o auth applications
	* @param end the upper bound of the range of o auth applications (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching o auth applications that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.OAuthApplication> filterFindByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the o auth applications before and after the current o auth application in the ordered set of o auth applications that the user has permission to view where companyId = &#63;.
	*
	* @param applicationId the primary key of the current o auth application
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next o auth application
	* @throws com.liferay.portal.oauth.NoSuchApplicationException if a o auth application with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.OAuthApplication[] filterFindByCompanyId_PrevAndNext(
		long applicationId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplicationException;

	/**
	* Returns the o auth application where consumerKey = &#63; or throws a {@link com.liferay.portal.oauth.NoSuchApplicationException} if it could not be found.
	*
	* @param consumerKey the consumer key
	* @return the matching o auth application
	* @throws com.liferay.portal.oauth.NoSuchApplicationException if a matching o auth application could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.OAuthApplication findByConsumerKey(
		java.lang.String consumerKey)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplicationException;

	/**
	* Returns the o auth application where consumerKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param consumerKey the consumer key
	* @return the matching o auth application, or <code>null</code> if a matching o auth application could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.OAuthApplication fetchByConsumerKey(
		java.lang.String consumerKey)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the o auth application where consumerKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param consumerKey the consumer key
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching o auth application, or <code>null</code> if a matching o auth application could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.OAuthApplication fetchByConsumerKey(
		java.lang.String consumerKey, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the o auth applications where ownerId = &#63;.
	*
	* @param ownerId the owner ID
	* @return the matching o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.OAuthApplication> findByOwnerId(
		long ownerId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the o auth applications where ownerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ownerId the owner ID
	* @param start the lower bound of the range of o auth applications
	* @param end the upper bound of the range of o auth applications (not inclusive)
	* @return the range of matching o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.OAuthApplication> findByOwnerId(
		long ownerId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the o auth applications where ownerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ownerId the owner ID
	* @param start the lower bound of the range of o auth applications
	* @param end the upper bound of the range of o auth applications (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.OAuthApplication> findByOwnerId(
		long ownerId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first o auth application in the ordered set where ownerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ownerId the owner ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching o auth application
	* @throws com.liferay.portal.oauth.NoSuchApplicationException if a matching o auth application could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.OAuthApplication findByOwnerId_First(
		long ownerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplicationException;

	/**
	* Returns the last o auth application in the ordered set where ownerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ownerId the owner ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching o auth application
	* @throws com.liferay.portal.oauth.NoSuchApplicationException if a matching o auth application could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.OAuthApplication findByOwnerId_Last(
		long ownerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplicationException;

	/**
	* Returns the o auth applications before and after the current o auth application in the ordered set where ownerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param applicationId the primary key of the current o auth application
	* @param ownerId the owner ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next o auth application
	* @throws com.liferay.portal.oauth.NoSuchApplicationException if a o auth application with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.OAuthApplication[] findByOwnerId_PrevAndNext(
		long applicationId, long ownerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplicationException;

	/**
	* Returns all the o auth applications that the user has permission to view where ownerId = &#63;.
	*
	* @param ownerId the owner ID
	* @return the matching o auth applications that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.OAuthApplication> filterFindByOwnerId(
		long ownerId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the o auth applications that the user has permission to view where ownerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ownerId the owner ID
	* @param start the lower bound of the range of o auth applications
	* @param end the upper bound of the range of o auth applications (not inclusive)
	* @return the range of matching o auth applications that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.OAuthApplication> filterFindByOwnerId(
		long ownerId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the o auth applications that the user has permissions to view where ownerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ownerId the owner ID
	* @param start the lower bound of the range of o auth applications
	* @param end the upper bound of the range of o auth applications (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching o auth applications that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.OAuthApplication> filterFindByOwnerId(
		long ownerId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the o auth applications before and after the current o auth application in the ordered set of o auth applications that the user has permission to view where ownerId = &#63;.
	*
	* @param applicationId the primary key of the current o auth application
	* @param ownerId the owner ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next o auth application
	* @throws com.liferay.portal.oauth.NoSuchApplicationException if a o auth application with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.OAuthApplication[] filterFindByOwnerId_PrevAndNext(
		long applicationId, long ownerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplicationException;

	/**
	* Returns all the o auth applications where website LIKE &#63;.
	*
	* @param website the website
	* @return the matching o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.OAuthApplication> findByWebsite(
		java.lang.String website)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the o auth applications where website LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param website the website
	* @param start the lower bound of the range of o auth applications
	* @param end the upper bound of the range of o auth applications (not inclusive)
	* @return the range of matching o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.OAuthApplication> findByWebsite(
		java.lang.String website, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the o auth applications where website LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param website the website
	* @param start the lower bound of the range of o auth applications
	* @param end the upper bound of the range of o auth applications (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.OAuthApplication> findByWebsite(
		java.lang.String website, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first o auth application in the ordered set where website LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param website the website
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching o auth application
	* @throws com.liferay.portal.oauth.NoSuchApplicationException if a matching o auth application could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.OAuthApplication findByWebsite_First(
		java.lang.String website,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplicationException;

	/**
	* Returns the last o auth application in the ordered set where website LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param website the website
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching o auth application
	* @throws com.liferay.portal.oauth.NoSuchApplicationException if a matching o auth application could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.OAuthApplication findByWebsite_Last(
		java.lang.String website,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplicationException;

	/**
	* Returns the o auth applications before and after the current o auth application in the ordered set where website LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param applicationId the primary key of the current o auth application
	* @param website the website
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next o auth application
	* @throws com.liferay.portal.oauth.NoSuchApplicationException if a o auth application with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.OAuthApplication[] findByWebsite_PrevAndNext(
		long applicationId, java.lang.String website,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplicationException;

	/**
	* Returns all the o auth applications that the user has permission to view where website LIKE &#63;.
	*
	* @param website the website
	* @return the matching o auth applications that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.OAuthApplication> filterFindByWebsite(
		java.lang.String website)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the o auth applications that the user has permission to view where website LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param website the website
	* @param start the lower bound of the range of o auth applications
	* @param end the upper bound of the range of o auth applications (not inclusive)
	* @return the range of matching o auth applications that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.OAuthApplication> filterFindByWebsite(
		java.lang.String website, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the o auth applications that the user has permissions to view where website LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param website the website
	* @param start the lower bound of the range of o auth applications
	* @param end the upper bound of the range of o auth applications (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching o auth applications that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.OAuthApplication> filterFindByWebsite(
		java.lang.String website, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the o auth applications before and after the current o auth application in the ordered set of o auth applications that the user has permission to view where website LIKE &#63;.
	*
	* @param applicationId the primary key of the current o auth application
	* @param website the website
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next o auth application
	* @throws com.liferay.portal.oauth.NoSuchApplicationException if a o auth application with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.OAuthApplication[] filterFindByWebsite_PrevAndNext(
		long applicationId, java.lang.String website,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplicationException;

	/**
	* Returns all the o auth applications where companyId = &#63; and name LIKE &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the matching o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.OAuthApplication> findByC_N(
		long companyId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the o auth applications where companyId = &#63; and name LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param start the lower bound of the range of o auth applications
	* @param end the upper bound of the range of o auth applications (not inclusive)
	* @return the range of matching o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.OAuthApplication> findByC_N(
		long companyId, java.lang.String name, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the o auth applications where companyId = &#63; and name LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param start the lower bound of the range of o auth applications
	* @param end the upper bound of the range of o auth applications (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.OAuthApplication> findByC_N(
		long companyId, java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first o auth application in the ordered set where companyId = &#63; and name LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching o auth application
	* @throws com.liferay.portal.oauth.NoSuchApplicationException if a matching o auth application could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.OAuthApplication findByC_N_First(
		long companyId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplicationException;

	/**
	* Returns the last o auth application in the ordered set where companyId = &#63; and name LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching o auth application
	* @throws com.liferay.portal.oauth.NoSuchApplicationException if a matching o auth application could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.OAuthApplication findByC_N_Last(
		long companyId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplicationException;

	/**
	* Returns the o auth applications before and after the current o auth application in the ordered set where companyId = &#63; and name LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param applicationId the primary key of the current o auth application
	* @param companyId the company ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next o auth application
	* @throws com.liferay.portal.oauth.NoSuchApplicationException if a o auth application with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.OAuthApplication[] findByC_N_PrevAndNext(
		long applicationId, long companyId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplicationException;

	/**
	* Returns all the o auth applications that the user has permission to view where companyId = &#63; and name LIKE &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the matching o auth applications that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.OAuthApplication> filterFindByC_N(
		long companyId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the o auth applications that the user has permission to view where companyId = &#63; and name LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param start the lower bound of the range of o auth applications
	* @param end the upper bound of the range of o auth applications (not inclusive)
	* @return the range of matching o auth applications that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.OAuthApplication> filterFindByC_N(
		long companyId, java.lang.String name, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the o auth applications that the user has permissions to view where companyId = &#63; and name LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param start the lower bound of the range of o auth applications
	* @param end the upper bound of the range of o auth applications (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching o auth applications that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.OAuthApplication> filterFindByC_N(
		long companyId, java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the o auth applications before and after the current o auth application in the ordered set of o auth applications that the user has permission to view where companyId = &#63; and name LIKE &#63;.
	*
	* @param applicationId the primary key of the current o auth application
	* @param companyId the company ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next o auth application
	* @throws com.liferay.portal.oauth.NoSuchApplicationException if a o auth application with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.OAuthApplication[] filterFindByC_N_PrevAndNext(
		long applicationId, long companyId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplicationException;

	/**
	* Returns all the o auth applications where ownerId = &#63; and name LIKE &#63;.
	*
	* @param ownerId the owner ID
	* @param name the name
	* @return the matching o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.OAuthApplication> findByO_N(
		long ownerId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the o auth applications where ownerId = &#63; and name LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ownerId the owner ID
	* @param name the name
	* @param start the lower bound of the range of o auth applications
	* @param end the upper bound of the range of o auth applications (not inclusive)
	* @return the range of matching o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.OAuthApplication> findByO_N(
		long ownerId, java.lang.String name, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the o auth applications where ownerId = &#63; and name LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ownerId the owner ID
	* @param name the name
	* @param start the lower bound of the range of o auth applications
	* @param end the upper bound of the range of o auth applications (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.OAuthApplication> findByO_N(
		long ownerId, java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first o auth application in the ordered set where ownerId = &#63; and name LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ownerId the owner ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching o auth application
	* @throws com.liferay.portal.oauth.NoSuchApplicationException if a matching o auth application could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.OAuthApplication findByO_N_First(
		long ownerId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplicationException;

	/**
	* Returns the last o auth application in the ordered set where ownerId = &#63; and name LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ownerId the owner ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching o auth application
	* @throws com.liferay.portal.oauth.NoSuchApplicationException if a matching o auth application could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.OAuthApplication findByO_N_Last(
		long ownerId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplicationException;

	/**
	* Returns the o auth applications before and after the current o auth application in the ordered set where ownerId = &#63; and name LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param applicationId the primary key of the current o auth application
	* @param ownerId the owner ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next o auth application
	* @throws com.liferay.portal.oauth.NoSuchApplicationException if a o auth application with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.OAuthApplication[] findByO_N_PrevAndNext(
		long applicationId, long ownerId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplicationException;

	/**
	* Returns all the o auth applications that the user has permission to view where ownerId = &#63; and name LIKE &#63;.
	*
	* @param ownerId the owner ID
	* @param name the name
	* @return the matching o auth applications that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.OAuthApplication> filterFindByO_N(
		long ownerId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the o auth applications that the user has permission to view where ownerId = &#63; and name LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ownerId the owner ID
	* @param name the name
	* @param start the lower bound of the range of o auth applications
	* @param end the upper bound of the range of o auth applications (not inclusive)
	* @return the range of matching o auth applications that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.OAuthApplication> filterFindByO_N(
		long ownerId, java.lang.String name, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the o auth applications that the user has permissions to view where ownerId = &#63; and name LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ownerId the owner ID
	* @param name the name
	* @param start the lower bound of the range of o auth applications
	* @param end the upper bound of the range of o auth applications (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching o auth applications that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.OAuthApplication> filterFindByO_N(
		long ownerId, java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the o auth applications before and after the current o auth application in the ordered set of o auth applications that the user has permission to view where ownerId = &#63; and name LIKE &#63;.
	*
	* @param applicationId the primary key of the current o auth application
	* @param ownerId the owner ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next o auth application
	* @throws com.liferay.portal.oauth.NoSuchApplicationException if a o auth application with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.OAuthApplication[] filterFindByO_N_PrevAndNext(
		long applicationId, long ownerId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplicationException;

	/**
	* Returns all the o auth applications.
	*
	* @return the o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.OAuthApplication> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the o auth applications.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of o auth applications
	* @param end the upper bound of the range of o auth applications (not inclusive)
	* @return the range of o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.OAuthApplication> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the o auth applications.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of o auth applications
	* @param end the upper bound of the range of o auth applications (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.OAuthApplication> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the o auth applications where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the o auth application where consumerKey = &#63; from the database.
	*
	* @param consumerKey the consumer key
	* @return the o auth application that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.OAuthApplication removeByConsumerKey(
		java.lang.String consumerKey)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.oauth.NoSuchApplicationException;

	/**
	* Removes all the o auth applications where ownerId = &#63; from the database.
	*
	* @param ownerId the owner ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByOwnerId(long ownerId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the o auth applications where website LIKE &#63; from the database.
	*
	* @param website the website
	* @throws SystemException if a system exception occurred
	*/
	public void removeByWebsite(java.lang.String website)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the o auth applications where companyId = &#63; and name LIKE &#63; from the database.
	*
	* @param companyId the company ID
	* @param name the name
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_N(long companyId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the o auth applications where ownerId = &#63; and name LIKE &#63; from the database.
	*
	* @param ownerId the owner ID
	* @param name the name
	* @throws SystemException if a system exception occurred
	*/
	public void removeByO_N(long ownerId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the o auth applications from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of o auth applications where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of o auth applications that the user has permission to view where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching o auth applications that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of o auth applications where consumerKey = &#63;.
	*
	* @param consumerKey the consumer key
	* @return the number of matching o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public int countByConsumerKey(java.lang.String consumerKey)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of o auth applications where ownerId = &#63;.
	*
	* @param ownerId the owner ID
	* @return the number of matching o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public int countByOwnerId(long ownerId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of o auth applications that the user has permission to view where ownerId = &#63;.
	*
	* @param ownerId the owner ID
	* @return the number of matching o auth applications that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByOwnerId(long ownerId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of o auth applications where website LIKE &#63;.
	*
	* @param website the website
	* @return the number of matching o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public int countByWebsite(java.lang.String website)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of o auth applications that the user has permission to view where website LIKE &#63;.
	*
	* @param website the website
	* @return the number of matching o auth applications that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByWebsite(java.lang.String website)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of o auth applications where companyId = &#63; and name LIKE &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the number of matching o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_N(long companyId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of o auth applications that the user has permission to view where companyId = &#63; and name LIKE &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the number of matching o auth applications that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByC_N(long companyId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of o auth applications where ownerId = &#63; and name LIKE &#63;.
	*
	* @param ownerId the owner ID
	* @param name the name
	* @return the number of matching o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public int countByO_N(long ownerId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of o auth applications that the user has permission to view where ownerId = &#63; and name LIKE &#63;.
	*
	* @param ownerId the owner ID
	* @param name the name
	* @return the number of matching o auth applications that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByO_N(long ownerId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of o auth applications.
	*
	* @return the number of o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}