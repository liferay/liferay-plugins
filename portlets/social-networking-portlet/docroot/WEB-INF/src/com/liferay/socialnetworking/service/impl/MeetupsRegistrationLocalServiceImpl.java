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

package com.liferay.socialnetworking.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.socialnetworking.model.MeetupsRegistration;
import com.liferay.socialnetworking.service.base.MeetupsRegistrationLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class MeetupsRegistrationLocalServiceImpl
	extends MeetupsRegistrationLocalServiceBaseImpl {

	public List<MeetupsRegistration> getMeetupsRegistrations(
			long meetupsEntryId, int status, int start, int end)
		throws SystemException {

		return meetupsRegistrationPersistence.findByME_S(
			meetupsEntryId, status, start, end);
	}

	public MeetupsRegistration getMeetupsRegistration(
			long userId, long meetupsEntryId)
		throws PortalException, SystemException {

		return meetupsRegistrationPersistence.findByU_ME(
			userId, meetupsEntryId);
	}

	public int getMeetupsRegistrationsCount(long meetupsEntryId, int status)
		throws SystemException {

		return meetupsRegistrationPersistence.countByME_S(
			meetupsEntryId, status);
	}

	public MeetupsRegistration updateMeetupsRegistration(
			long userId, long meetupsEntryId, int status, String comments)
		throws PortalException, SystemException {

		User user = userLocalService.getUserById(userId);
		Date now = new Date();

		MeetupsRegistration meetupsRegistration =
			meetupsRegistrationPersistence.fetchByU_ME(userId, meetupsEntryId);

		if (meetupsRegistration == null) {
			long meetupsRegistrationId = counterLocalService.increment();

			meetupsRegistration = meetupsRegistrationPersistence.create(
				meetupsRegistrationId);

			meetupsRegistration.setCompanyId(user.getCompanyId());
			meetupsRegistration.setUserId(user.getUserId());
			meetupsRegistration.setUserName(user.getFullName());
			meetupsRegistration.setCreateDate(now);
			meetupsRegistration.setMeetupsEntryId(meetupsEntryId);
		}

		meetupsRegistration.setModifiedDate(now);
		meetupsRegistration.setStatus(status);
		meetupsRegistration.setComments(comments);

		meetupsRegistrationPersistence.update(meetupsRegistration, false);

		return meetupsRegistration;
	}

}