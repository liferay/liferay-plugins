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

import com.liferay.bbb.model.BBBMeeting;
import com.liferay.bbb.model.BBBParticipant;
import com.liferay.bbb.service.base.BBBMeetingServiceBaseImpl;
import com.liferay.bbb.service.permission.BBBMeetingPermission;
import com.liferay.bbb.service.permission.MeetingsPermission;
import com.liferay.bbb.util.ActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * @author Shinn Lok
 */
public class BBBMeetingServiceImpl extends BBBMeetingServiceBaseImpl {

	@Override
	public BBBMeeting addBBBMeeting(
			long groupId, String portletId, long bbbServerId, String name,
			String description, String attendeePassword,
			String moderatorPassword, int status,
			List<BBBParticipant> bbbParticipants, ServiceContext serviceContext)
		throws PortalException {

		MeetingsPermission.check(
			getPermissionChecker(), groupId, ActionKeys.ADD_MEETING);

		return bbbMeetingLocalService.addBBBMeeting(
			getUserId(), groupId, bbbServerId, name, description,
			attendeePassword, moderatorPassword, status, bbbParticipants,
			serviceContext);
	}

	@Override
	public BBBMeeting deleteBBBMeeting(long bbbMeetingId)
		throws PortalException {

		BBBMeetingPermission.check(
			getPermissionChecker(), bbbMeetingId, ActionKeys.DELETE);

		return bbbMeetingLocalService.deleteBBBMeeting(bbbMeetingId);
	}

	@Override
	public BBBMeeting getBBBMeeting(long bbbMeetingId) throws PortalException {
		BBBMeetingPermission.check(
			getPermissionChecker(), bbbMeetingId, ActionKeys.VIEW);

		return bbbMeetingLocalService.getBBBMeeting(bbbMeetingId);
	}

	@Override
	public List<BBBMeeting> getBBBMeetings(
		long groupId, int start, int end, OrderByComparator<BBBMeeting> obc) {

		return bbbMeetingPersistence.filterFindByGroupId(
			groupId, start, end, obc);
	}

	@Override
	public int getBBBMeetingsCount(long groupId) {
		return bbbMeetingPersistence.filterCountByGroupId(groupId);
	}

	@Override
	public BBBMeeting updateBBBMeeting(
			long bbbMeetingId, long bbbServerId, String name,
			String description, String attendeePassword,
			String moderatorPassword, List<BBBParticipant> bbbParticipants,
			ServiceContext serviceContext)
		throws PortalException {

		BBBMeetingPermission.check(
			getPermissionChecker(), bbbMeetingId, ActionKeys.UPDATE);

		return bbbMeetingLocalService.updateBBBMeeting(
			bbbMeetingId, bbbServerId, name, description, attendeePassword,
			moderatorPassword, bbbParticipants, serviceContext);
	}

}