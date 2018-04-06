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

import java.io.Serializable;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * @author Brian Wing Shun Chan
 */
public class MeetingRequest implements Serializable {

	public Calendar getEndCalendar() {
		return _endCalendar;
	}

	public int getMaxResponses() {
		return _maxResponses;
	}

	public Meeting getMeeting() {
		return _meeting;
	}

	public long getMeetingKey() {
		return _meetingKey;
	}

	public MeetingParticipant getMeetingParticipant() {
		return _meetingParticipant;
	}

	public String getMeetingPassword() {
		return _meetingPassword;
	}

	public Calendar getStartCalendar() {
		return _startCalendar;
	}

	public int getStartFrom() {
		return _startFrom;
	}

	public TimeZone getTimeZone() {
		return _timeZone;
	}

	public void setEndCalendar(Calendar endCalendar) {
		_endCalendar = endCalendar;
	}

	public void setMaxResponses(int maxResponses) {
		_maxResponses = maxResponses;
	}

	public void setMeeting(Meeting meeting) {
		_meeting = meeting;
	}

	public void setMeetingKey(long meetingKey) {
		_meetingKey = meetingKey;
	}

	public void setMeetingParticipant(MeetingParticipant meetingParticipant) {
		_meetingParticipant = meetingParticipant;
	}

	public void setMeetingPassword(String meetingPassword) {
		_meetingPassword = meetingPassword;
	}

	public void setStartCalendar(Calendar startCalendar) {
		_startCalendar = startCalendar;
	}

	public void setStartFrom(int startFrom) {
		_startFrom = startFrom;
	}

	public void setTimeZone(TimeZone timeZone) {
		_timeZone = timeZone;
	}

	private Calendar _endCalendar;
	private int _maxResponses;
	private Meeting _meeting;
	private long _meetingKey;
	private MeetingParticipant _meetingParticipant;
	private String _meetingPassword;
	private Calendar _startCalendar;
	private int _startFrom;
	private TimeZone _timeZone;

}