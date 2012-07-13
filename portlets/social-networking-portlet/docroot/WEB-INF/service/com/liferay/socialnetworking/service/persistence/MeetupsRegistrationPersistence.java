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

package com.liferay.socialnetworking.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.socialnetworking.model.MeetupsRegistration;

/**
 * The persistence interface for the meetups registration service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MeetupsRegistrationPersistenceImpl
 * @see MeetupsRegistrationUtil
 * @generated
 */
public interface MeetupsRegistrationPersistence extends BasePersistence<MeetupsRegistration> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MeetupsRegistrationUtil} to access the meetups registration persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the meetups registration in the entity cache if it is enabled.
	*
	* @param meetupsRegistration the meetups registration
	*/
	public void cacheResult(
		com.liferay.socialnetworking.model.MeetupsRegistration meetupsRegistration);

	/**
	* Caches the meetups registrations in the entity cache if it is enabled.
	*
	* @param meetupsRegistrations the meetups registrations
	*/
	public void cacheResult(
		java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration> meetupsRegistrations);

	/**
	* Creates a new meetups registration with the primary key. Does not add the meetups registration to the database.
	*
	* @param meetupsRegistrationId the primary key for the new meetups registration
	* @return the new meetups registration
	*/
	public com.liferay.socialnetworking.model.MeetupsRegistration create(
		long meetupsRegistrationId);

	/**
	* Removes the meetups registration with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param meetupsRegistrationId the primary key of the meetups registration
	* @return the meetups registration that was removed
	* @throws com.liferay.socialnetworking.NoSuchMeetupsRegistrationException if a meetups registration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.MeetupsRegistration remove(
		long meetupsRegistrationId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsRegistrationException;

	public com.liferay.socialnetworking.model.MeetupsRegistration updateImpl(
		com.liferay.socialnetworking.model.MeetupsRegistration meetupsRegistration,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the meetups registration with the primary key or throws a {@link com.liferay.socialnetworking.NoSuchMeetupsRegistrationException} if it could not be found.
	*
	* @param meetupsRegistrationId the primary key of the meetups registration
	* @return the meetups registration
	* @throws com.liferay.socialnetworking.NoSuchMeetupsRegistrationException if a meetups registration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.MeetupsRegistration findByPrimaryKey(
		long meetupsRegistrationId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsRegistrationException;

	/**
	* Returns the meetups registration with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param meetupsRegistrationId the primary key of the meetups registration
	* @return the meetups registration, or <code>null</code> if a meetups registration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.MeetupsRegistration fetchByPrimaryKey(
		long meetupsRegistrationId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the meetups registrations where meetupsEntryId = &#63;.
	*
	* @param meetupsEntryId the meetups entry ID
	* @return the matching meetups registrations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration> findByMeetupsEntryId(
		long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the meetups registrations where meetupsEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param meetupsEntryId the meetups entry ID
	* @param start the lower bound of the range of meetups registrations
	* @param end the upper bound of the range of meetups registrations (not inclusive)
	* @return the range of matching meetups registrations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration> findByMeetupsEntryId(
		long meetupsEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the meetups registrations where meetupsEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param meetupsEntryId the meetups entry ID
	* @param start the lower bound of the range of meetups registrations
	* @param end the upper bound of the range of meetups registrations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching meetups registrations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration> findByMeetupsEntryId(
		long meetupsEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first meetups registration in the ordered set where meetupsEntryId = &#63;.
	*
	* @param meetupsEntryId the meetups entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching meetups registration
	* @throws com.liferay.socialnetworking.NoSuchMeetupsRegistrationException if a matching meetups registration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.MeetupsRegistration findByMeetupsEntryId_First(
		long meetupsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsRegistrationException;

	/**
	* Returns the first meetups registration in the ordered set where meetupsEntryId = &#63;.
	*
	* @param meetupsEntryId the meetups entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching meetups registration, or <code>null</code> if a matching meetups registration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.MeetupsRegistration fetchByMeetupsEntryId_First(
		long meetupsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last meetups registration in the ordered set where meetupsEntryId = &#63;.
	*
	* @param meetupsEntryId the meetups entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching meetups registration
	* @throws com.liferay.socialnetworking.NoSuchMeetupsRegistrationException if a matching meetups registration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.MeetupsRegistration findByMeetupsEntryId_Last(
		long meetupsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsRegistrationException;

	/**
	* Returns the last meetups registration in the ordered set where meetupsEntryId = &#63;.
	*
	* @param meetupsEntryId the meetups entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching meetups registration, or <code>null</code> if a matching meetups registration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.MeetupsRegistration fetchByMeetupsEntryId_Last(
		long meetupsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the meetups registrations before and after the current meetups registration in the ordered set where meetupsEntryId = &#63;.
	*
	* @param meetupsRegistrationId the primary key of the current meetups registration
	* @param meetupsEntryId the meetups entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next meetups registration
	* @throws com.liferay.socialnetworking.NoSuchMeetupsRegistrationException if a meetups registration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.MeetupsRegistration[] findByMeetupsEntryId_PrevAndNext(
		long meetupsRegistrationId, long meetupsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsRegistrationException;

	/**
	* Returns the meetups registration where userId = &#63; and meetupsEntryId = &#63; or throws a {@link com.liferay.socialnetworking.NoSuchMeetupsRegistrationException} if it could not be found.
	*
	* @param userId the user ID
	* @param meetupsEntryId the meetups entry ID
	* @return the matching meetups registration
	* @throws com.liferay.socialnetworking.NoSuchMeetupsRegistrationException if a matching meetups registration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.MeetupsRegistration findByU_ME(
		long userId, long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsRegistrationException;

	/**
	* Returns the meetups registration where userId = &#63; and meetupsEntryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param meetupsEntryId the meetups entry ID
	* @return the matching meetups registration, or <code>null</code> if a matching meetups registration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.MeetupsRegistration fetchByU_ME(
		long userId, long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the meetups registration where userId = &#63; and meetupsEntryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param meetupsEntryId the meetups entry ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching meetups registration, or <code>null</code> if a matching meetups registration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.MeetupsRegistration fetchByU_ME(
		long userId, long meetupsEntryId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the meetups registrations where meetupsEntryId = &#63; and status = &#63;.
	*
	* @param meetupsEntryId the meetups entry ID
	* @param status the status
	* @return the matching meetups registrations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration> findByME_S(
		long meetupsEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the meetups registrations where meetupsEntryId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param meetupsEntryId the meetups entry ID
	* @param status the status
	* @param start the lower bound of the range of meetups registrations
	* @param end the upper bound of the range of meetups registrations (not inclusive)
	* @return the range of matching meetups registrations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration> findByME_S(
		long meetupsEntryId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the meetups registrations where meetupsEntryId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param meetupsEntryId the meetups entry ID
	* @param status the status
	* @param start the lower bound of the range of meetups registrations
	* @param end the upper bound of the range of meetups registrations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching meetups registrations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration> findByME_S(
		long meetupsEntryId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first meetups registration in the ordered set where meetupsEntryId = &#63; and status = &#63;.
	*
	* @param meetupsEntryId the meetups entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching meetups registration
	* @throws com.liferay.socialnetworking.NoSuchMeetupsRegistrationException if a matching meetups registration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.MeetupsRegistration findByME_S_First(
		long meetupsEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsRegistrationException;

	/**
	* Returns the first meetups registration in the ordered set where meetupsEntryId = &#63; and status = &#63;.
	*
	* @param meetupsEntryId the meetups entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching meetups registration, or <code>null</code> if a matching meetups registration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.MeetupsRegistration fetchByME_S_First(
		long meetupsEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last meetups registration in the ordered set where meetupsEntryId = &#63; and status = &#63;.
	*
	* @param meetupsEntryId the meetups entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching meetups registration
	* @throws com.liferay.socialnetworking.NoSuchMeetupsRegistrationException if a matching meetups registration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.MeetupsRegistration findByME_S_Last(
		long meetupsEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsRegistrationException;

	/**
	* Returns the last meetups registration in the ordered set where meetupsEntryId = &#63; and status = &#63;.
	*
	* @param meetupsEntryId the meetups entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching meetups registration, or <code>null</code> if a matching meetups registration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.MeetupsRegistration fetchByME_S_Last(
		long meetupsEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the meetups registrations before and after the current meetups registration in the ordered set where meetupsEntryId = &#63; and status = &#63;.
	*
	* @param meetupsRegistrationId the primary key of the current meetups registration
	* @param meetupsEntryId the meetups entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next meetups registration
	* @throws com.liferay.socialnetworking.NoSuchMeetupsRegistrationException if a meetups registration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.MeetupsRegistration[] findByME_S_PrevAndNext(
		long meetupsRegistrationId, long meetupsEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsRegistrationException;

	/**
	* Returns all the meetups registrations.
	*
	* @return the meetups registrations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the meetups registrations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of meetups registrations
	* @param end the upper bound of the range of meetups registrations (not inclusive)
	* @return the range of meetups registrations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the meetups registrations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of meetups registrations
	* @param end the upper bound of the range of meetups registrations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of meetups registrations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the meetups registrations where meetupsEntryId = &#63; from the database.
	*
	* @param meetupsEntryId the meetups entry ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByMeetupsEntryId(long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the meetups registration where userId = &#63; and meetupsEntryId = &#63; from the database.
	*
	* @param userId the user ID
	* @param meetupsEntryId the meetups entry ID
	* @return the meetups registration that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.MeetupsRegistration removeByU_ME(
		long userId, long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsRegistrationException;

	/**
	* Removes all the meetups registrations where meetupsEntryId = &#63; and status = &#63; from the database.
	*
	* @param meetupsEntryId the meetups entry ID
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public void removeByME_S(long meetupsEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the meetups registrations from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of meetups registrations where meetupsEntryId = &#63;.
	*
	* @param meetupsEntryId the meetups entry ID
	* @return the number of matching meetups registrations
	* @throws SystemException if a system exception occurred
	*/
	public int countByMeetupsEntryId(long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of meetups registrations where userId = &#63; and meetupsEntryId = &#63;.
	*
	* @param userId the user ID
	* @param meetupsEntryId the meetups entry ID
	* @return the number of matching meetups registrations
	* @throws SystemException if a system exception occurred
	*/
	public int countByU_ME(long userId, long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of meetups registrations where meetupsEntryId = &#63; and status = &#63;.
	*
	* @param meetupsEntryId the meetups entry ID
	* @param status the status
	* @return the number of matching meetups registrations
	* @throws SystemException if a system exception occurred
	*/
	public int countByME_S(long meetupsEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of meetups registrations.
	*
	* @return the number of meetups registrations
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}