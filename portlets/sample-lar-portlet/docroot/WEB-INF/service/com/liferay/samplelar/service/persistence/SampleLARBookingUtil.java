/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.samplelar.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.liferay.samplelar.model.SampleLARBooking;

import java.util.List;

/**
 * The persistence utility for the sample l a r booking service. This utility wraps {@link com.liferay.samplelar.service.persistence.impl.SampleLARBookingPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Mate Thurzo
 * @see SampleLARBookingPersistence
 * @see com.liferay.samplelar.service.persistence.impl.SampleLARBookingPersistenceImpl
 * @generated
 */
@ProviderType
public class SampleLARBookingUtil {
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
	public static void clearCache(SampleLARBooking sampleLARBooking) {
		getPersistence().clearCache(sampleLARBooking);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<SampleLARBooking> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SampleLARBooking> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SampleLARBooking> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SampleLARBooking> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static SampleLARBooking update(SampleLARBooking sampleLARBooking) {
		return getPersistence().update(sampleLARBooking);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static SampleLARBooking update(SampleLARBooking sampleLARBooking,
		ServiceContext serviceContext) {
		return getPersistence().update(sampleLARBooking, serviceContext);
	}

	/**
	* Returns all the sample l a r bookings where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching sample l a r bookings
	*/
	public static List<SampleLARBooking> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the sample l a r bookings where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SampleLARBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of sample l a r bookings
	* @param end the upper bound of the range of sample l a r bookings (not inclusive)
	* @return the range of matching sample l a r bookings
	*/
	public static List<SampleLARBooking> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the sample l a r bookings where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SampleLARBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of sample l a r bookings
	* @param end the upper bound of the range of sample l a r bookings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sample l a r bookings
	*/
	public static List<SampleLARBooking> findByUuid(java.lang.String uuid,
		int start, int end,
		OrderByComparator<SampleLARBooking> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the first sample l a r booking in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sample l a r booking
	* @throws NoSuchBookingException if a matching sample l a r booking could not be found
	*/
	public static SampleLARBooking findByUuid_First(java.lang.String uuid,
		OrderByComparator<SampleLARBooking> orderByComparator)
		throws com.liferay.samplelar.NoSuchBookingException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first sample l a r booking in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sample l a r booking, or <code>null</code> if a matching sample l a r booking could not be found
	*/
	public static SampleLARBooking fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<SampleLARBooking> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last sample l a r booking in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sample l a r booking
	* @throws NoSuchBookingException if a matching sample l a r booking could not be found
	*/
	public static SampleLARBooking findByUuid_Last(java.lang.String uuid,
		OrderByComparator<SampleLARBooking> orderByComparator)
		throws com.liferay.samplelar.NoSuchBookingException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last sample l a r booking in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sample l a r booking, or <code>null</code> if a matching sample l a r booking could not be found
	*/
	public static SampleLARBooking fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<SampleLARBooking> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the sample l a r bookings before and after the current sample l a r booking in the ordered set where uuid = &#63;.
	*
	* @param sampleLARBookingId the primary key of the current sample l a r booking
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sample l a r booking
	* @throws NoSuchBookingException if a sample l a r booking with the primary key could not be found
	*/
	public static SampleLARBooking[] findByUuid_PrevAndNext(
		long sampleLARBookingId, java.lang.String uuid,
		OrderByComparator<SampleLARBooking> orderByComparator)
		throws com.liferay.samplelar.NoSuchBookingException {
		return getPersistence()
				   .findByUuid_PrevAndNext(sampleLARBookingId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the sample l a r bookings where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of sample l a r bookings where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching sample l a r bookings
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the sample l a r booking where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchBookingException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching sample l a r booking
	* @throws NoSuchBookingException if a matching sample l a r booking could not be found
	*/
	public static SampleLARBooking findByUUID_G(java.lang.String uuid,
		long groupId) throws com.liferay.samplelar.NoSuchBookingException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the sample l a r booking where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching sample l a r booking, or <code>null</code> if a matching sample l a r booking could not be found
	*/
	public static SampleLARBooking fetchByUUID_G(java.lang.String uuid,
		long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the sample l a r booking where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching sample l a r booking, or <code>null</code> if a matching sample l a r booking could not be found
	*/
	public static SampleLARBooking fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the sample l a r booking where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the sample l a r booking that was removed
	*/
	public static SampleLARBooking removeByUUID_G(java.lang.String uuid,
		long groupId) throws com.liferay.samplelar.NoSuchBookingException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of sample l a r bookings where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching sample l a r bookings
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the sample l a r bookings where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching sample l a r bookings
	*/
	public static List<SampleLARBooking> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the sample l a r bookings where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SampleLARBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of sample l a r bookings
	* @param end the upper bound of the range of sample l a r bookings (not inclusive)
	* @return the range of matching sample l a r bookings
	*/
	public static List<SampleLARBooking> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the sample l a r bookings where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SampleLARBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of sample l a r bookings
	* @param end the upper bound of the range of sample l a r bookings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sample l a r bookings
	*/
	public static List<SampleLARBooking> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<SampleLARBooking> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first sample l a r booking in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sample l a r booking
	* @throws NoSuchBookingException if a matching sample l a r booking could not be found
	*/
	public static SampleLARBooking findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<SampleLARBooking> orderByComparator)
		throws com.liferay.samplelar.NoSuchBookingException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first sample l a r booking in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sample l a r booking, or <code>null</code> if a matching sample l a r booking could not be found
	*/
	public static SampleLARBooking fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<SampleLARBooking> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last sample l a r booking in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sample l a r booking
	* @throws NoSuchBookingException if a matching sample l a r booking could not be found
	*/
	public static SampleLARBooking findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<SampleLARBooking> orderByComparator)
		throws com.liferay.samplelar.NoSuchBookingException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last sample l a r booking in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sample l a r booking, or <code>null</code> if a matching sample l a r booking could not be found
	*/
	public static SampleLARBooking fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<SampleLARBooking> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the sample l a r bookings before and after the current sample l a r booking in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param sampleLARBookingId the primary key of the current sample l a r booking
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sample l a r booking
	* @throws NoSuchBookingException if a sample l a r booking with the primary key could not be found
	*/
	public static SampleLARBooking[] findByUuid_C_PrevAndNext(
		long sampleLARBookingId, java.lang.String uuid, long companyId,
		OrderByComparator<SampleLARBooking> orderByComparator)
		throws com.liferay.samplelar.NoSuchBookingException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(sampleLARBookingId, uuid,
			companyId, orderByComparator);
	}

	/**
	* Removes all the sample l a r bookings where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of sample l a r bookings where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching sample l a r bookings
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the sample l a r bookings where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching sample l a r bookings
	*/
	public static List<SampleLARBooking> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the sample l a r bookings where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SampleLARBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of sample l a r bookings
	* @param end the upper bound of the range of sample l a r bookings (not inclusive)
	* @return the range of matching sample l a r bookings
	*/
	public static List<SampleLARBooking> findByGroupId(long groupId, int start,
		int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the sample l a r bookings where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SampleLARBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of sample l a r bookings
	* @param end the upper bound of the range of sample l a r bookings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sample l a r bookings
	*/
	public static List<SampleLARBooking> findByGroupId(long groupId, int start,
		int end, OrderByComparator<SampleLARBooking> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns the first sample l a r booking in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sample l a r booking
	* @throws NoSuchBookingException if a matching sample l a r booking could not be found
	*/
	public static SampleLARBooking findByGroupId_First(long groupId,
		OrderByComparator<SampleLARBooking> orderByComparator)
		throws com.liferay.samplelar.NoSuchBookingException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first sample l a r booking in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sample l a r booking, or <code>null</code> if a matching sample l a r booking could not be found
	*/
	public static SampleLARBooking fetchByGroupId_First(long groupId,
		OrderByComparator<SampleLARBooking> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last sample l a r booking in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sample l a r booking
	* @throws NoSuchBookingException if a matching sample l a r booking could not be found
	*/
	public static SampleLARBooking findByGroupId_Last(long groupId,
		OrderByComparator<SampleLARBooking> orderByComparator)
		throws com.liferay.samplelar.NoSuchBookingException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last sample l a r booking in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sample l a r booking, or <code>null</code> if a matching sample l a r booking could not be found
	*/
	public static SampleLARBooking fetchByGroupId_Last(long groupId,
		OrderByComparator<SampleLARBooking> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the sample l a r bookings before and after the current sample l a r booking in the ordered set where groupId = &#63;.
	*
	* @param sampleLARBookingId the primary key of the current sample l a r booking
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sample l a r booking
	* @throws NoSuchBookingException if a sample l a r booking with the primary key could not be found
	*/
	public static SampleLARBooking[] findByGroupId_PrevAndNext(
		long sampleLARBookingId, long groupId,
		OrderByComparator<SampleLARBooking> orderByComparator)
		throws com.liferay.samplelar.NoSuchBookingException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(sampleLARBookingId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the sample l a r bookings where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of sample l a r bookings where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching sample l a r bookings
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Caches the sample l a r booking in the entity cache if it is enabled.
	*
	* @param sampleLARBooking the sample l a r booking
	*/
	public static void cacheResult(SampleLARBooking sampleLARBooking) {
		getPersistence().cacheResult(sampleLARBooking);
	}

	/**
	* Caches the sample l a r bookings in the entity cache if it is enabled.
	*
	* @param sampleLARBookings the sample l a r bookings
	*/
	public static void cacheResult(List<SampleLARBooking> sampleLARBookings) {
		getPersistence().cacheResult(sampleLARBookings);
	}

	/**
	* Creates a new sample l a r booking with the primary key. Does not add the sample l a r booking to the database.
	*
	* @param sampleLARBookingId the primary key for the new sample l a r booking
	* @return the new sample l a r booking
	*/
	public static SampleLARBooking create(long sampleLARBookingId) {
		return getPersistence().create(sampleLARBookingId);
	}

	/**
	* Removes the sample l a r booking with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param sampleLARBookingId the primary key of the sample l a r booking
	* @return the sample l a r booking that was removed
	* @throws NoSuchBookingException if a sample l a r booking with the primary key could not be found
	*/
	public static SampleLARBooking remove(long sampleLARBookingId)
		throws com.liferay.samplelar.NoSuchBookingException {
		return getPersistence().remove(sampleLARBookingId);
	}

	public static SampleLARBooking updateImpl(SampleLARBooking sampleLARBooking) {
		return getPersistence().updateImpl(sampleLARBooking);
	}

	/**
	* Returns the sample l a r booking with the primary key or throws a {@link NoSuchBookingException} if it could not be found.
	*
	* @param sampleLARBookingId the primary key of the sample l a r booking
	* @return the sample l a r booking
	* @throws NoSuchBookingException if a sample l a r booking with the primary key could not be found
	*/
	public static SampleLARBooking findByPrimaryKey(long sampleLARBookingId)
		throws com.liferay.samplelar.NoSuchBookingException {
		return getPersistence().findByPrimaryKey(sampleLARBookingId);
	}

	/**
	* Returns the sample l a r booking with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param sampleLARBookingId the primary key of the sample l a r booking
	* @return the sample l a r booking, or <code>null</code> if a sample l a r booking with the primary key could not be found
	*/
	public static SampleLARBooking fetchByPrimaryKey(long sampleLARBookingId) {
		return getPersistence().fetchByPrimaryKey(sampleLARBookingId);
	}

	public static java.util.Map<java.io.Serializable, SampleLARBooking> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the sample l a r bookings.
	*
	* @return the sample l a r bookings
	*/
	public static List<SampleLARBooking> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the sample l a r bookings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SampleLARBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sample l a r bookings
	* @param end the upper bound of the range of sample l a r bookings (not inclusive)
	* @return the range of sample l a r bookings
	*/
	public static List<SampleLARBooking> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the sample l a r bookings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SampleLARBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sample l a r bookings
	* @param end the upper bound of the range of sample l a r bookings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of sample l a r bookings
	*/
	public static List<SampleLARBooking> findAll(int start, int end,
		OrderByComparator<SampleLARBooking> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the sample l a r bookings from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of sample l a r bookings.
	*
	* @return the number of sample l a r bookings
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static SampleLARBookingPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (SampleLARBookingPersistence)PortletBeanLocatorUtil.locate(com.liferay.samplelar.service.ClpSerializer.getServletContextName(),
					SampleLARBookingPersistence.class.getName());

			ReferenceRegistry.registerReference(SampleLARBookingUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setPersistence(SampleLARBookingPersistence persistence) {
	}

	private static SampleLARBookingPersistence _persistence;
}