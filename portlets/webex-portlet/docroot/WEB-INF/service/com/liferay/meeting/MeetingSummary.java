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

/**
 * @author Brian Wing Shun Chan
 */
public class MeetingSummary implements Serializable {

	public int getDuration() {
		return _duration;
	}

	public String getHostKey() {
		return _hostKey;
	}

	public String getListingStatus() {
		return _listingStatus;
	}

	public long getMeetingKey() {
		return _meetingKey;
	}

	public String getMeetingName() {
		return _meetingName;
	}

	public String getMeetingStatus() {
		return _meetingStatus;
	}

	public String getOtherHostKey() {
		return _otherHostKey;
	}

	public Calendar getStartCalendar() {
		return _startCalendar;
	}

	public String getTimeZone() {
		return _timeZone;
	}

	public boolean isHostJoined() {
		return _hostJoined;
	}

	public boolean isParticipantsJoined() {
		return _participantsJoined;
	}

	public void setDuration(int duration) {
		_duration = duration;
	}

	public void setHostJoined(boolean hostJoined) {
		_hostJoined = hostJoined;
	}

	public void setHostKey(String hostKey) {
		_hostKey = hostKey;
	}

	public void setListingStatus(String listingStatus) {
		_listingStatus = listingStatus;
	}

	public void setMeetingKey(long meetingKey) {
		_meetingKey = meetingKey;
	}

	public void setMeetingName(String meetingName) {
		_meetingName = meetingName;
	}

	public void setMeetingStatus(String meetingStatus) {
		_meetingStatus = meetingStatus;
	}

	public void setOtherHostKey(String otherHostKey) {
		_otherHostKey = otherHostKey;
	}

	public void setParticipantsJoined(boolean participantsJoined) {
		_participantsJoined = participantsJoined;
	}

	public void setStartCalendar(Calendar startCalendar) {
		_startCalendar = startCalendar;
	}

	public void setTimeZone(String timeZone) {
		_timeZone = timeZone;
	}

	private int _duration;
	private boolean _hostJoined;
	private String _hostKey;
	private String _listingStatus;
	private long _meetingKey;
	private String _meetingName;
	private String _meetingStatus;
	private String _otherHostKey;
	private boolean _participantsJoined;
	private Calendar _startCalendar;
	private String _timeZone;

}