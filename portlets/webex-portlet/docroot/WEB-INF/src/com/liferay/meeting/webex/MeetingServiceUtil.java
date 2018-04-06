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

package com.liferay.meeting.webex;

import com.liferay.meeting.Meeting;
import com.liferay.meeting.MeetingConfirmation;
import com.liferay.meeting.MeetingContext;
import com.liferay.meeting.MeetingException;
import com.liferay.meeting.MeetingParticipant;
import com.liferay.meeting.MeetingService;
import com.liferay.meeting.MeetingSummary;

import java.util.Calendar;
import java.util.Set;
import java.util.TimeZone;

/**
 * @author Daniela Zapata Riesco
 */
public class MeetingServiceUtil {

	public static MeetingConfirmation addMeeting(
			Meeting meeting, MeetingContext meetingContext)
		throws MeetingException {

		return _meetingService.addMeeting(meeting, meetingContext);
	}

	public static void deleteMeeting(
			long meetingKey, MeetingContext meetingContext)
		throws MeetingException {

		_meetingService.deleteMeeting(meetingKey, meetingContext);
	}

	public static String getHostURL(
			long meetingKey, MeetingContext meetingContext)
		throws MeetingException {

		return _meetingService.getHostURL(meetingKey, meetingContext);
	}

	public static String getJoinURL(
			long meetingKey, String meetingPassword,
			MeetingParticipant meetingParticipant,
			MeetingContext meetingContext)
		throws MeetingException {

		return _meetingService.getJoinURL(
			meetingKey, meetingPassword, meetingParticipant, meetingContext);
	}

	public static Meeting getMeeting(
			long meetingKey, MeetingContext meetingContext)
		throws MeetingException {

		return _meetingService.getMeeting(meetingKey, meetingContext);
	}

	public static Set<MeetingSummary> getMeetingSummaries(
			Calendar startCalendar, Calendar endCalendar, TimeZone timeZone,
			int max, MeetingContext meetingContext)
		throws MeetingException {

		return _meetingService.getMeetingSummaries(
			startCalendar, endCalendar, timeZone, max, meetingContext);
	}

	public static Set<MeetingSummary> getMeetingSummaries(
			TimeZone timeZone, int start, int end,
			MeetingContext meetingContext)
		throws MeetingException {

		return _meetingService.getMeetingSummaries(
			timeZone, start, end, meetingContext);
	}

	public static Set<MeetingSummary> getMeetingSummaries(
			TimeZone timeZone, int max, MeetingContext meetingContext)
		throws MeetingException {

		return _meetingService.getMeetingSummaries(
			timeZone, max, meetingContext);
	}

	public static void updateMeeting(
			Meeting meeting, MeetingContext meetingContext)
		throws MeetingException {

		_meetingService.updateMeeting(meeting, meetingContext);
	}

	public void setMeetingService(MeetingService meetingService) {
		_meetingService = meetingService;
	}

	private static MeetingService _meetingService;

}