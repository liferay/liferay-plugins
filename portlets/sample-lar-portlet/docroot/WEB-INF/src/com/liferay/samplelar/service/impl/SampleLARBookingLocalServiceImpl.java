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

package com.liferay.samplelar.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.samplelar.SampleLARBookingBookingNumberException;
import com.liferay.samplelar.model.SampleLARBooking;
import com.liferay.samplelar.service.base.SampleLARBookingLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

/**
 * @author Mate Thurzo
 */
public class SampleLARBookingLocalServiceImpl
	extends SampleLARBookingLocalServiceBaseImpl {

	public SampleLARBooking addSampleLARBooking(
			long userId, long groupId, String bookingNumber,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		validate(bookingNumber);

		long sampleLARBookingId = counterLocalService.increment();

		SampleLARBooking sampleLARBooking = sampleLARBookingPersistence.create(
			sampleLARBookingId);

		sampleLARBooking.setGroupId(groupId);
		sampleLARBooking.setCompanyId(user.getCompanyId());
		sampleLARBooking.setUserId(userId);
		sampleLARBooking.setUserName(user.getFullName());
		sampleLARBooking.setCreateDate(serviceContext.getCreateDate(now));
		sampleLARBooking.setModifiedDate(serviceContext.getModifiedDate(now));
		sampleLARBooking.setBookingNumber(bookingNumber);

		sampleLARBookingPersistence.update(sampleLARBooking);

		return sampleLARBooking;
	}

	public void deleteSampleLARBookings(long groupId) throws SystemException {
		List<SampleLARBooking> sampleLARBookings =
			sampleLARBookingPersistence.findByGroupId(groupId);

		for (SampleLARBooking sampleLARBooking : sampleLARBookings) {
			sampleLARBookingLocalService.deleteSampleLARBooking(
				sampleLARBooking);
		}
	}

	public SampleLARBooking updateSampleLARBooking(
			long userId, long sampleLARBookingId, String bookingNumber,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);

		validate(bookingNumber);

		SampleLARBooking sampleLARBooking =
			sampleLARBookingPersistence.findByPrimaryKey(sampleLARBookingId);

		sampleLARBooking.setUserId(userId);
		sampleLARBooking.setUserName(user.getFullName());
		sampleLARBooking.setModifiedDate(
			serviceContext.getModifiedDate(new Date()));
		sampleLARBooking.setBookingNumber(bookingNumber);

		return sampleLARBookingPersistence.update(sampleLARBooking);
	}

	protected void validate(String bookingNumber) throws PortalException {
		if (Validator.isNull(bookingNumber)) {
			throw new SampleLARBookingBookingNumberException();
		}
	}

}