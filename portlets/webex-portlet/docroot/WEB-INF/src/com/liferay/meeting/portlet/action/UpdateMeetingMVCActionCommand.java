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

package com.liferay.meeting.portlet.action;

import com.liferay.meeting.Meeting;
import com.liferay.meeting.MeetingContext;
import com.liferay.meeting.webex.MeetingServiceUtil;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Daniela Zapata Riesco
 * @author Brian Wing Shun Chan
 */
public class UpdateMeetingMVCActionCommand extends AddMeetingMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		Meeting meeting = getMeeting(actionRequest);

		long meetingKey = ParamUtil.getLong(actionRequest, "meetingKey");

		meeting.setMeetingKey(meetingKey);

		MeetingContext meetingContext = getMeetingContext(actionRequest);

		MeetingServiceUtil.updateMeeting(meeting, meetingContext);
	}

}