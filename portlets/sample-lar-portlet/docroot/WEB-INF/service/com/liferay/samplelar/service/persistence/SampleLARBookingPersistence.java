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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.liferay.samplelar.exception.NoSuchBookingException;
import com.liferay.samplelar.model.SampleLARBooking;

/**
 * The persistence interface for the sample l a r booking service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Mate Thurzo
 * @see com.liferay.samplelar.service.persistence.impl.SampleLARBookingPersistenceImpl
 * @see SampleLARBookingUtil
 * @generated
 */
@ProviderType
public interface SampleLARBookingPersistence extends BasePersistence<SampleLARBooking> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SampleLARBookingUtil} to access the sample l a r booking persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the sample l a r bookings where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching sample l a r bookings
	*/
	public java.util.List<SampleLARBooking> findByUuid(java.lang.String uuid);

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
	public java.util.List<SampleLARBooking> findByUuid(java.lang.String uuid,
		int start, int end);

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
	public java.util.List<SampleLARBooking> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SampleLARBooking> orderByComparator);

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
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sample l a r bookings
	*/
	public java.util.List<SampleLARBooking> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SampleLARBooking> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first sample l a r booking in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sample l a r booking
	* @throws NoSuchBookingException if a matching sample l a r booking could not be found
	*/
	public SampleLARBooking findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SampleLARBooking> orderByComparator)
		throws NoSuchBookingException;

	/**
	* Returns the first sample l a r booking in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sample l a r booking, or <code>null</code> if a matching sample l a r booking could not be found
	*/
	public SampleLARBooking fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SampleLARBooking> orderByComparator);

	/**
	* Returns the last sample l a r booking in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sample l a r booking
	* @throws NoSuchBookingException if a matching sample l a r booking could not be found
	*/
	public SampleLARBooking findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SampleLARBooking> orderByComparator)
		throws NoSuchBookingException;

	/**
	* Returns the last sample l a r booking in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sample l a r booking, or <code>null</code> if a matching sample l a r booking could not be found
	*/
	public SampleLARBooking fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SampleLARBooking> orderByComparator);

	/**
	* Returns the sample l a r bookings before and after the current sample l a r booking in the ordered set where uuid = &#63;.
	*
	* @param sampleLARBookingId the primary key of the current sample l a r booking
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sample l a r booking
	* @throws NoSuchBookingException if a sample l a r booking with the primary key could not be found
	*/
	public SampleLARBooking[] findByUuid_PrevAndNext(long sampleLARBookingId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SampleLARBooking> orderByComparator)
		throws NoSuchBookingException;

	/**
	* Removes all the sample l a r bookings where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of sample l a r bookings where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching sample l a r bookings
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the sample l a r booking where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchBookingException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching sample l a r booking
	* @throws NoSuchBookingException if a matching sample l a r booking could not be found
	*/
	public SampleLARBooking findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchBookingException;

	/**
	* Returns the sample l a r booking where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching sample l a r booking, or <code>null</code> if a matching sample l a r booking could not be found
	*/
	public SampleLARBooking fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the sample l a r booking where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching sample l a r booking, or <code>null</code> if a matching sample l a r booking could not be found
	*/
	public SampleLARBooking fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the sample l a r booking where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the sample l a r booking that was removed
	*/
	public SampleLARBooking removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchBookingException;

	/**
	* Returns the number of sample l a r bookings where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching sample l a r bookings
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the sample l a r bookings where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching sample l a r bookings
	*/
	public java.util.List<SampleLARBooking> findByUuid_C(
		java.lang.String uuid, long companyId);

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
	public java.util.List<SampleLARBooking> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end);

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
	public java.util.List<SampleLARBooking> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SampleLARBooking> orderByComparator);

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
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sample l a r bookings
	*/
	public java.util.List<SampleLARBooking> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SampleLARBooking> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first sample l a r booking in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sample l a r booking
	* @throws NoSuchBookingException if a matching sample l a r booking could not be found
	*/
	public SampleLARBooking findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SampleLARBooking> orderByComparator)
		throws NoSuchBookingException;

	/**
	* Returns the first sample l a r booking in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sample l a r booking, or <code>null</code> if a matching sample l a r booking could not be found
	*/
	public SampleLARBooking fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SampleLARBooking> orderByComparator);

	/**
	* Returns the last sample l a r booking in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sample l a r booking
	* @throws NoSuchBookingException if a matching sample l a r booking could not be found
	*/
	public SampleLARBooking findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SampleLARBooking> orderByComparator)
		throws NoSuchBookingException;

	/**
	* Returns the last sample l a r booking in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sample l a r booking, or <code>null</code> if a matching sample l a r booking could not be found
	*/
	public SampleLARBooking fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SampleLARBooking> orderByComparator);

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
	public SampleLARBooking[] findByUuid_C_PrevAndNext(
		long sampleLARBookingId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SampleLARBooking> orderByComparator)
		throws NoSuchBookingException;

	/**
	* Removes all the sample l a r bookings where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of sample l a r bookings where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching sample l a r bookings
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the sample l a r bookings where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching sample l a r bookings
	*/
	public java.util.List<SampleLARBooking> findByGroupId(long groupId);

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
	public java.util.List<SampleLARBooking> findByGroupId(long groupId,
		int start, int end);

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
	public java.util.List<SampleLARBooking> findByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SampleLARBooking> orderByComparator);

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
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sample l a r bookings
	*/
	public java.util.List<SampleLARBooking> findByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SampleLARBooking> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first sample l a r booking in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sample l a r booking
	* @throws NoSuchBookingException if a matching sample l a r booking could not be found
	*/
	public SampleLARBooking findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<SampleLARBooking> orderByComparator)
		throws NoSuchBookingException;

	/**
	* Returns the first sample l a r booking in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sample l a r booking, or <code>null</code> if a matching sample l a r booking could not be found
	*/
	public SampleLARBooking fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<SampleLARBooking> orderByComparator);

	/**
	* Returns the last sample l a r booking in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sample l a r booking
	* @throws NoSuchBookingException if a matching sample l a r booking could not be found
	*/
	public SampleLARBooking findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<SampleLARBooking> orderByComparator)
		throws NoSuchBookingException;

	/**
	* Returns the last sample l a r booking in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sample l a r booking, or <code>null</code> if a matching sample l a r booking could not be found
	*/
	public SampleLARBooking fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<SampleLARBooking> orderByComparator);

	/**
	* Returns the sample l a r bookings before and after the current sample l a r booking in the ordered set where groupId = &#63;.
	*
	* @param sampleLARBookingId the primary key of the current sample l a r booking
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sample l a r booking
	* @throws NoSuchBookingException if a sample l a r booking with the primary key could not be found
	*/
	public SampleLARBooking[] findByGroupId_PrevAndNext(
		long sampleLARBookingId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<SampleLARBooking> orderByComparator)
		throws NoSuchBookingException;

	/**
	* Removes all the sample l a r bookings where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of sample l a r bookings where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching sample l a r bookings
	*/
	public int countByGroupId(long groupId);

	/**
	* Caches the sample l a r booking in the entity cache if it is enabled.
	*
	* @param sampleLARBooking the sample l a r booking
	*/
	public void cacheResult(SampleLARBooking sampleLARBooking);

	/**
	* Caches the sample l a r bookings in the entity cache if it is enabled.
	*
	* @param sampleLARBookings the sample l a r bookings
	*/
	public void cacheResult(java.util.List<SampleLARBooking> sampleLARBookings);

	/**
	* Creates a new sample l a r booking with the primary key. Does not add the sample l a r booking to the database.
	*
	* @param sampleLARBookingId the primary key for the new sample l a r booking
	* @return the new sample l a r booking
	*/
	public SampleLARBooking create(long sampleLARBookingId);

	/**
	* Removes the sample l a r booking with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param sampleLARBookingId the primary key of the sample l a r booking
	* @return the sample l a r booking that was removed
	* @throws NoSuchBookingException if a sample l a r booking with the primary key could not be found
	*/
	public SampleLARBooking remove(long sampleLARBookingId)
		throws NoSuchBookingException;

	public SampleLARBooking updateImpl(SampleLARBooking sampleLARBooking);

	/**
	* Returns the sample l a r booking with the primary key or throws a {@link NoSuchBookingException} if it could not be found.
	*
	* @param sampleLARBookingId the primary key of the sample l a r booking
	* @return the sample l a r booking
	* @throws NoSuchBookingException if a sample l a r booking with the primary key could not be found
	*/
	public SampleLARBooking findByPrimaryKey(long sampleLARBookingId)
		throws NoSuchBookingException;

	/**
	* Returns the sample l a r booking with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param sampleLARBookingId the primary key of the sample l a r booking
	* @return the sample l a r booking, or <code>null</code> if a sample l a r booking with the primary key could not be found
	*/
	public SampleLARBooking fetchByPrimaryKey(long sampleLARBookingId);

	@Override
	public java.util.Map<java.io.Serializable, SampleLARBooking> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the sample l a r bookings.
	*
	* @return the sample l a r bookings
	*/
	public java.util.List<SampleLARBooking> findAll();

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
	public java.util.List<SampleLARBooking> findAll(int start, int end);

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
	public java.util.List<SampleLARBooking> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SampleLARBooking> orderByComparator);

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
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of sample l a r bookings
	*/
	public java.util.List<SampleLARBooking> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SampleLARBooking> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the sample l a r bookings from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of sample l a r bookings.
	*
	* @return the number of sample l a r bookings
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}