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

package com.liferay.bbb.service.impl;

import com.liferay.bbb.model.BBBParticipant;
import com.liferay.bbb.service.base.BBBParticipantLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;

import java.util.Date;
import java.util.List;

/**
 * @author Shinn Lok
 */
public class BBBParticipantLocalServiceImpl
	extends BBBParticipantLocalServiceBaseImpl {

	@Override
	public BBBParticipant addBBBParticipant(
			long userId, long groupId, long bbbMeetingId, String name,
			String emailAddress, int type, int status,
			ServiceContext serviceContext)
		throws PortalException {

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		long bbbParticipantId = counterLocalService.increment();

		BBBParticipant bbbParticipant = bbbParticipantPersistence.create(
			bbbParticipantId);

		bbbParticipant.setGroupId(groupId);
		bbbParticipant.setCompanyId(user.getCompanyId());
		bbbParticipant.setUserId(user.getUserId());
		bbbParticipant.setUserName(user.getFullName());
		bbbParticipant.setCreateDate(serviceContext.getCreateDate(now));
		bbbParticipant.setModifiedDate(serviceContext.getModifiedDate(now));
		bbbParticipant.setBbbMeetingId(bbbMeetingId);
		bbbParticipant.setName(name);
		bbbParticipant.setEmailAddress(emailAddress);
		bbbParticipant.setType(type);
		bbbParticipant.setStatus(status);

		bbbParticipantPersistence.update(bbbParticipant);

		return bbbParticipant;
	}

	@Override
	public BBBParticipant deleteBBBParticipant(BBBParticipant bbbParticipant) {
		return bbbParticipantPersistence.remove(bbbParticipant);
	}

	@Override
	public BBBParticipant fetchBBBParticipant(
		long bbbMeetingId, String emailAddress) {

		return bbbParticipantPersistence.fetchByBMI_EA(
			bbbMeetingId, emailAddress);
	}

	@Override
	public List<BBBParticipant> getBBBParticipants(long bbbMeetingId) {
		return bbbParticipantPersistence.findByBbbMeetingId(bbbMeetingId);
	}

	@Override
	public int getBBBParticipantsCount(long bbbMeetingId) {
		return bbbParticipantPersistence.countByBbbMeetingId(bbbMeetingId);
	}

	@Override
	public BBBParticipant updateBBBParticipant(
			long bbbParticipantId, long bbbMeetingId, String name,
			String emailAddress, int type, ServiceContext serviceContext)
		throws PortalException {

		BBBParticipant bbbParticipant =
			bbbParticipantPersistence.findByPrimaryKey(bbbParticipantId);

		bbbParticipant.setModifiedDate(serviceContext.getModifiedDate(null));

		if (bbbMeetingId > 0) {
			bbbParticipant.setBbbMeetingId(bbbMeetingId);
		}

		bbbParticipant.setName(name);

		if (Validator.isNotNull(emailAddress)) {
			bbbParticipant.setEmailAddress(emailAddress);
		}

		bbbParticipant.setType(type);

		return bbbParticipantPersistence.update(bbbParticipant);
	}

	@Override
	public BBBParticipant updateStatus(long bbbParticipantId, int status)
		throws PortalException {

		BBBParticipant bbbParticipant =
			bbbParticipantPersistence.findByPrimaryKey(bbbParticipantId);

		bbbParticipant.setStatus(status);

		bbbParticipantPersistence.update(bbbParticipant);

		return bbbParticipant;
	}

}