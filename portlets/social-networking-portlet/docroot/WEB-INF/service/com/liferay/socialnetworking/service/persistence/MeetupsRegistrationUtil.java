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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.liferay.socialnetworking.model.MeetupsRegistration;

import java.util.List;

/**
 * The persistence utility for the meetups registration service. This utility wraps {@link MeetupsRegistrationPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MeetupsRegistrationPersistence
 * @see MeetupsRegistrationPersistenceImpl
 * @generated
 */
public class MeetupsRegistrationUtil {
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
	public static void clearCache(MeetupsRegistration meetupsRegistration) {
		getPersistence().clearCache(meetupsRegistration);
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
	public static List<MeetupsRegistration> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<MeetupsRegistration> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<MeetupsRegistration> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static MeetupsRegistration update(
		MeetupsRegistration meetupsRegistration, boolean merge)
		throws SystemException {
		return getPersistence().update(meetupsRegistration, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static MeetupsRegistration update(
		MeetupsRegistration meetupsRegistration, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence()
				   .update(meetupsRegistration, merge, serviceContext);
	}

	/**
	* Caches the meetups registration in the entity cache if it is enabled.
	*
	* @param meetupsRegistration the meetups registration
	*/
	public static void cacheResult(
		com.liferay.socialnetworking.model.MeetupsRegistration meetupsRegistration) {
		getPersistence().cacheResult(meetupsRegistration);
	}

	/**
	* Caches the meetups registrations in the entity cache if it is enabled.
	*
	* @param meetupsRegistrations the meetups registrations
	*/
	public static void cacheResult(
		java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration> meetupsRegistrations) {
		getPersistence().cacheResult(meetupsRegistrations);
	}

	/**
	* Creates a new meetups registration with the primary key. Does not add the meetups registration to the database.
	*
	* @param meetupsRegistrationId the primary key for the new meetups registration
	* @return the new meetups registration
	*/
	public static com.liferay.socialnetworking.model.MeetupsRegistration create(
		long meetupsRegistrationId) {
		return getPersistence().create(meetupsRegistrationId);
	}

	/**
	* Removes the meetups registration with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param meetupsRegistrationId the primary key of the meetups registration
	* @return the meetups registration that was removed
	* @throws com.liferay.socialnetworking.NoSuchMeetupsRegistrationException if a meetups registration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialnetworking.model.MeetupsRegistration remove(
		long meetupsRegistrationId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsRegistrationException {
		return getPersistence().remove(meetupsRegistrationId);
	}

	public static com.liferay.socialnetworking.model.MeetupsRegistration updateImpl(
		com.liferay.socialnetworking.model.MeetupsRegistration meetupsRegistration,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(meetupsRegistration, merge);
	}

	/**
	* Returns the meetups registration with the primary key or throws a {@link com.liferay.socialnetworking.NoSuchMeetupsRegistrationException} if it could not be found.
	*
	* @param meetupsRegistrationId the primary key of the meetups registration
	* @return the meetups registration
	* @throws com.liferay.socialnetworking.NoSuchMeetupsRegistrationException if a meetups registration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialnetworking.model.MeetupsRegistration findByPrimaryKey(
		long meetupsRegistrationId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsRegistrationException {
		return getPersistence().findByPrimaryKey(meetupsRegistrationId);
	}

	/**
	* Returns the meetups registration with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param meetupsRegistrationId the primary key of the meetups registration
	* @return the meetups registration, or <code>null</code> if a meetups registration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialnetworking.model.MeetupsRegistration fetchByPrimaryKey(
		long meetupsRegistrationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(meetupsRegistrationId);
	}

	/**
	* Returns all the meetups registrations where meetupsEntryId = &#63;.
	*
	* @param meetupsEntryId the meetups entry ID
	* @return the matching meetups registrations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration> findByMeetupsEntryId(
		long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByMeetupsEntryId(meetupsEntryId);
	}

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
	public static java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration> findByMeetupsEntryId(
		long meetupsEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByMeetupsEntryId(meetupsEntryId, start, end);
	}

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
	public static java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration> findByMeetupsEntryId(
		long meetupsEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByMeetupsEntryId(meetupsEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first meetups registration in the ordered set where meetupsEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param meetupsEntryId the meetups entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching meetups registration
	* @throws com.liferay.socialnetworking.NoSuchMeetupsRegistrationException if a matching meetups registration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialnetworking.model.MeetupsRegistration findByMeetupsEntryId_First(
		long meetupsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsRegistrationException {
		return getPersistence()
				   .findByMeetupsEntryId_First(meetupsEntryId, orderByComparator);
	}

	/**
	* Returns the last meetups registration in the ordered set where meetupsEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param meetupsEntryId the meetups entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching meetups registration
	* @throws com.liferay.socialnetworking.NoSuchMeetupsRegistrationException if a matching meetups registration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialnetworking.model.MeetupsRegistration findByMeetupsEntryId_Last(
		long meetupsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsRegistrationException {
		return getPersistence()
				   .findByMeetupsEntryId_Last(meetupsEntryId, orderByComparator);
	}

	/**
	* Returns the meetups registrations before and after the current meetups registration in the ordered set where meetupsEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param meetupsRegistrationId the primary key of the current meetups registration
	* @param meetupsEntryId the meetups entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next meetups registration
	* @throws com.liferay.socialnetworking.NoSuchMeetupsRegistrationException if a meetups registration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialnetworking.model.MeetupsRegistration[] findByMeetupsEntryId_PrevAndNext(
		long meetupsRegistrationId, long meetupsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsRegistrationException {
		return getPersistence()
				   .findByMeetupsEntryId_PrevAndNext(meetupsRegistrationId,
			meetupsEntryId, orderByComparator);
	}

	/**
	* Returns the meetups registration where userId = &#63; and meetupsEntryId = &#63; or throws a {@link com.liferay.socialnetworking.NoSuchMeetupsRegistrationException} if it could not be found.
	*
	* @param userId the user ID
	* @param meetupsEntryId the meetups entry ID
	* @return the matching meetups registration
	* @throws com.liferay.socialnetworking.NoSuchMeetupsRegistrationException if a matching meetups registration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialnetworking.model.MeetupsRegistration findByU_ME(
		long userId, long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsRegistrationException {
		return getPersistence().findByU_ME(userId, meetupsEntryId);
	}

	/**
	* Returns the meetups registration where userId = &#63; and meetupsEntryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param meetupsEntryId the meetups entry ID
	* @return the matching meetups registration, or <code>null</code> if a matching meetups registration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialnetworking.model.MeetupsRegistration fetchByU_ME(
		long userId, long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByU_ME(userId, meetupsEntryId);
	}

	/**
	* Returns the meetups registration where userId = &#63; and meetupsEntryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param meetupsEntryId the meetups entry ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching meetups registration, or <code>null</code> if a matching meetups registration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialnetworking.model.MeetupsRegistration fetchByU_ME(
		long userId, long meetupsEntryId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByU_ME(userId, meetupsEntryId, retrieveFromCache);
	}

	/**
	* Returns all the meetups registrations where meetupsEntryId = &#63; and status = &#63;.
	*
	* @param meetupsEntryId the meetups entry ID
	* @param status the status
	* @return the matching meetups registrations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration> findByME_S(
		long meetupsEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByME_S(meetupsEntryId, status);
	}

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
	public static java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration> findByME_S(
		long meetupsEntryId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByME_S(meetupsEntryId, status, start, end);
	}

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
	public static java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration> findByME_S(
		long meetupsEntryId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByME_S(meetupsEntryId, status, start, end,
			orderByComparator);
	}

	/**
	* Returns the first meetups registration in the ordered set where meetupsEntryId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param meetupsEntryId the meetups entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching meetups registration
	* @throws com.liferay.socialnetworking.NoSuchMeetupsRegistrationException if a matching meetups registration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialnetworking.model.MeetupsRegistration findByME_S_First(
		long meetupsEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsRegistrationException {
		return getPersistence()
				   .findByME_S_First(meetupsEntryId, status, orderByComparator);
	}

	/**
	* Returns the last meetups registration in the ordered set where meetupsEntryId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param meetupsEntryId the meetups entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching meetups registration
	* @throws com.liferay.socialnetworking.NoSuchMeetupsRegistrationException if a matching meetups registration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialnetworking.model.MeetupsRegistration findByME_S_Last(
		long meetupsEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsRegistrationException {
		return getPersistence()
				   .findByME_S_Last(meetupsEntryId, status, orderByComparator);
	}

	/**
	* Returns the meetups registrations before and after the current meetups registration in the ordered set where meetupsEntryId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param meetupsRegistrationId the primary key of the current meetups registration
	* @param meetupsEntryId the meetups entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next meetups registration
	* @throws com.liferay.socialnetworking.NoSuchMeetupsRegistrationException if a meetups registration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialnetworking.model.MeetupsRegistration[] findByME_S_PrevAndNext(
		long meetupsRegistrationId, long meetupsEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsRegistrationException {
		return getPersistence()
				   .findByME_S_PrevAndNext(meetupsRegistrationId,
			meetupsEntryId, status, orderByComparator);
	}

	/**
	* Returns all the meetups registrations.
	*
	* @return the meetups registrations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the meetups registrations where meetupsEntryId = &#63; from the database.
	*
	* @param meetupsEntryId the meetups entry ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByMeetupsEntryId(long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByMeetupsEntryId(meetupsEntryId);
	}

	/**
	* Removes the meetups registration where userId = &#63; and meetupsEntryId = &#63; from the database.
	*
	* @param userId the user ID
	* @param meetupsEntryId the meetups entry ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByU_ME(long userId, long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsRegistrationException {
		getPersistence().removeByU_ME(userId, meetupsEntryId);
	}

	/**
	* Removes all the meetups registrations where meetupsEntryId = &#63; and status = &#63; from the database.
	*
	* @param meetupsEntryId the meetups entry ID
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByME_S(long meetupsEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByME_S(meetupsEntryId, status);
	}

	/**
	* Removes all the meetups registrations from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of meetups registrations where meetupsEntryId = &#63;.
	*
	* @param meetupsEntryId the meetups entry ID
	* @return the number of matching meetups registrations
	* @throws SystemException if a system exception occurred
	*/
	public static int countByMeetupsEntryId(long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByMeetupsEntryId(meetupsEntryId);
	}

	/**
	* Returns the number of meetups registrations where userId = &#63; and meetupsEntryId = &#63;.
	*
	* @param userId the user ID
	* @param meetupsEntryId the meetups entry ID
	* @return the number of matching meetups registrations
	* @throws SystemException if a system exception occurred
	*/
	public static int countByU_ME(long userId, long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByU_ME(userId, meetupsEntryId);
	}

	/**
	* Returns the number of meetups registrations where meetupsEntryId = &#63; and status = &#63;.
	*
	* @param meetupsEntryId the meetups entry ID
	* @param status the status
	* @return the number of matching meetups registrations
	* @throws SystemException if a system exception occurred
	*/
	public static int countByME_S(long meetupsEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByME_S(meetupsEntryId, status);
	}

	/**
	* Returns the number of meetups registrations.
	*
	* @return the number of meetups registrations
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static MeetupsRegistrationPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (MeetupsRegistrationPersistence)PortletBeanLocatorUtil.locate(com.liferay.socialnetworking.service.ClpSerializer.getServletContextName(),
					MeetupsRegistrationPersistence.class.getName());

			ReferenceRegistry.registerReference(MeetupsRegistrationUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(MeetupsRegistrationPersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(MeetupsRegistrationUtil.class,
			"_persistence");
	}

	private static MeetupsRegistrationPersistence _persistence;
}