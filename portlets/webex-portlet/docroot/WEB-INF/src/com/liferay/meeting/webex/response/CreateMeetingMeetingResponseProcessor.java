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

package com.liferay.meeting.webex.response;

import com.liferay.meeting.MeetingConfirmation;
import com.liferay.meeting.MeetingException;

import com.webex.schemas.x2002.x06.service.BodyContentType;
import com.webex.schemas.x2002.x06.service.ICalendarURLType;
import com.webex.schemas.x2002.x06.service.meeting.CreateMeetingResponse;
import com.webex.schemas.x2002.x06.service.meeting.impl.CreateMeetingResponseImpl;

/**
 * @author Anant Singh
 */
public class CreateMeetingMeetingResponseProcessor
	extends BaseMeetingResponseProcessor<MeetingConfirmation> {

	public MeetingConfirmation process(String xml) throws MeetingException {
		MeetingConfirmation neetingConfirmation = new MeetingConfirmation();

		BodyContentType[] bodyContentTypes = getBodyContentTypes(xml);

		CreateMeetingResponse createMeetingResponse =
			(CreateMeetingResponseImpl)bodyContentTypes[0];

		ICalendarURLType iCalendarURLType =
			createMeetingResponse.getICalendarURL();

		neetingConfirmation.setAttendeeURL(iCalendarURLType.getAttendee());
		neetingConfirmation.setHostURL(iCalendarURLType.getHost());

		neetingConfirmation.setMeetingKey(
			createMeetingResponse.getMeetingkey());

		return neetingConfirmation;
	}

}