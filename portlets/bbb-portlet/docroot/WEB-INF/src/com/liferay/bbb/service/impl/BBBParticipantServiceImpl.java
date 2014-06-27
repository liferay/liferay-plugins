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
import com.liferay.bbb.service.base.BBBParticipantServiceBaseImpl;
import com.liferay.bbb.service.permission.BBBMeetingPermission;
import com.liferay.bbb.util.ActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * @author Shinn Lok
 */
public class BBBParticipantServiceImpl extends BBBParticipantServiceBaseImpl {

	@Override
	public BBBParticipant deleteBBBParticipant(BBBParticipant bbbParticipant)
		throws PortalException {

		BBBMeetingPermission.check(
			getPermissionChecker(), bbbParticipant.getBbbMeetingId(),
			ActionKeys.UPDATE);

		return bbbParticipantPersistence.remove(bbbParticipant);
	}

	@Override
	public List<BBBParticipant> getBBBParticipants(long bbbMeetingId)
		throws PortalException {

		BBBMeetingPermission.check(
			getPermissionChecker(), bbbMeetingId, ActionKeys.VIEW);

		return bbbParticipantLocalService.getBBBParticipants(bbbMeetingId);
	}

	@Override
	public int getBBBParticipantsCount(long bbbMeetingId)
		throws PortalException {

		BBBMeetingPermission.check(
			getPermissionChecker(), bbbMeetingId, ActionKeys.VIEW);

		return bbbParticipantLocalService.getBBBParticipantsCount(bbbMeetingId);
	}

	@Override
	public BBBParticipant updateBBBParticipant(
			long bbbParticipantId, long bbbMeetingId, String name,
			String emailAddress, int type, ServiceContext serviceContext)
		throws PortalException {

		BBBMeetingPermission.check(
			getPermissionChecker(), bbbMeetingId, ActionKeys.UPDATE);

		return bbbParticipantLocalService.updateBBBParticipant(
			bbbParticipantId, bbbMeetingId, name, emailAddress, type,
			serviceContext);
	}

}