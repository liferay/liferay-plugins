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

package com.liferay.meeting;

import java.util.Calendar;
import java.util.Set;
import java.util.TimeZone;

/**
 * @author Brian Wing Shun Chan
 */
public interface MeetingService {

	public MeetingConfirmation addMeeting(
			Meeting meeting, MeetingContext meetingContext)
		throws MeetingException;

	public void deleteMeeting(long meetingKey, MeetingContext meetingContext)
		throws MeetingException;

	public String getHostURL(long meetingKey, MeetingContext meetingContext)
		throws MeetingException;

	public String getJoinURL(
			long meetingKey, String meetingPassword,
			MeetingParticipant meetingParticipant,
			MeetingContext meetingContext)
		throws MeetingException;

	public Meeting getMeeting(long meetingKey, MeetingContext meetingContext)
		throws MeetingException;

	public Set<MeetingSummary> getMeetingSummaries(
			Calendar startCalendar, Calendar endCalendar, TimeZone timeZone,
			int max, MeetingContext meetingContext)
		throws MeetingException;

	public Set<MeetingSummary> getMeetingSummaries(
			TimeZone timeZone, int start, int end,
			MeetingContext meetingContext)
		throws MeetingException;

	public Set<MeetingSummary> getMeetingSummaries(
			TimeZone timeZone, int max, MeetingContext meetingContext)
		throws MeetingException;

	public void updateMeeting(Meeting meeting, MeetingContext meetingContext)
		throws MeetingException;

}