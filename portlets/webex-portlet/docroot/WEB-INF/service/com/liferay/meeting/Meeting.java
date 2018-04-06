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
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;

/**
 * @author Brian Wing Shun Chan
 */
public class Meeting implements Serializable {

	public void addMeetingParticipant(MeetingParticipant meetingParticipant) {
		_meetingParticipants.add(meetingParticipant);
	}

	public void addMeetingParticipants(
		Set<MeetingParticipant> meetingParticipants) {

		_meetingParticipants.addAll(meetingParticipants);
	}

	public String getAgenda() {
		return _agenda;
	}

	public String getDescription() {
		return _description;
	}

	public long getDuration() {
		return _duration;
	}

	public String getGreeting() {
		return _greeting;
	}

	public String getHostId() {
		return _hostId;
	}

	public String getHostKey() {
		return _hostKey;
	}

	public String getHostURL() {
		return _hostURL;
	}

	public String getInvitation() {
		return _invitation;
	}

	public String getJoinURL() {
		return _joinURL;
	}

	public Locale getLocale() {
		return _locale;
	}

	public String getLocation() {
		return _location;
	}

	public int getMaxAttendees() {
		return _maxAttendees;
	}

	public MeetingAttendeeOptions getMeetingAttendeeOptions() {
		return _meetingAttendeeOptions;
	}

	public long getMeetingKey() {
		return _meetingKey;
	}

	public MeetingOptions getMeetingOptions() {
		return _meetingOptions;
	}

	public Set<MeetingParticipant> getMeetingParticipants() {
		return Collections.unmodifiableSet(_meetingParticipants);
	}

	public MeetingTelephonyOptions getMeetingTelephonyOptions() {
		return _meetingTelephonyOptions;
	}

	public String getName() {
		return _name;
	}

	public int getOpenTime() {
		return _openTime;
	}

	public String getParticipantURL() {
		return _participantURL;
	}

	public String getPassword() {
		return _password;
	}

	public String getRegistrationURL() {
		return _registrationURL;
	}

	public Calendar getStartCalendar() {
		return _startCalendar;
	}

	public String getStatus() {
		return _status;
	}

	public TimeZone getTimeZone() {
		return _timeZone;
	}

	public boolean isHostJoined() {
		return _hostJoined;
	}

	public boolean isParticipantJoined() {
		return _participantJoined;
	}

	public boolean isPublicMeeting() {
		return _publicMeeting;
	}

	public boolean isShowOnPublicMeetingList() {
		return _showOnPublicMeetingList;
	}

	public void setAgenda(String agenda) {
		_agenda = agenda;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public void setDuration(long duration) {
		_duration = duration;
	}

	public void setGreeting(String greeting) {
		_greeting = greeting;
	}

	public void setHostId(String hostId) {
		_hostId = hostId;
	}

	public void setHostJoined(boolean hostJoined) {
		_hostJoined = hostJoined;
	}

	public void setHostKey(String hostKey) {
		_hostKey = hostKey;
	}

	public void setHostURL(String hostURL) {
		_hostURL = hostURL;
	}

	public void setInvitation(String invitation) {
		_invitation = invitation;
	}

	public void setJoinMeetingURL(String joinMeetingURL) {
		_joinURL = joinMeetingURL;
	}

	public void setLocale(Locale locale) {
		_locale = locale;
	}

	public void setLocation(String location) {
		_location = location;
	}

	public void setMaxAttendees(int maxAttendees) {
		_maxAttendees = maxAttendees;
	}

	public void setMeetingAttendeeOptions(
		MeetingAttendeeOptions meetingAttendeeOptions) {

		_meetingAttendeeOptions = meetingAttendeeOptions;
	}

	public void setMeetingKey(long meetingKey) {
		_meetingKey = meetingKey;
	}

	public void setMeetingOptions(MeetingOptions meetingOptions) {
		_meetingOptions = meetingOptions;
	}

	public void setMeetingTelephonyOptions(
		MeetingTelephonyOptions meetingTelephonyOptions) {

		_meetingTelephonyOptions = meetingTelephonyOptions;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setOpenTime(int openTime) {
		_openTime = openTime;
	}

	public void setParticipantJoined(boolean participantJoined) {
		_participantJoined = participantJoined;
	}

	public void setParticipantURL(String participantURL) {
		_participantURL = participantURL;
	}

	public void setPassword(String password) {
		_password = password;
	}

	public void setPublicMeeting(boolean publicMeeting) {
		_publicMeeting = publicMeeting;
	}

	public void setRegistrationURL(String registrationURL) {
		_registrationURL = registrationURL;
	}

	public void setShowOnPublicMeetingList(boolean showOnPublicMeetingList) {
		_showOnPublicMeetingList = showOnPublicMeetingList;
	}

	public void setStartCalendar(Calendar startCalendar) {
		_startCalendar = startCalendar;
	}

	public void setStatus(String status) {
		_status = status;
	}

	public void setTimeZone(TimeZone timeZone) {
		_timeZone = timeZone;
	}

	private String _agenda;
	private String _description;
	private long _duration;
	private String _greeting;
	private String _hostId;
	private boolean _hostJoined;
	private String _hostKey;
	private String _hostURL;
	private String _invitation;
	private String _joinURL;
	private Locale _locale;
	private String _location;
	private int _maxAttendees;
	private MeetingAttendeeOptions _meetingAttendeeOptions;
	private long _meetingKey;
	private MeetingOptions _meetingOptions;
	private Set<MeetingParticipant> _meetingParticipants = new HashSet<>();
	private MeetingTelephonyOptions _meetingTelephonyOptions;
	private String _name;
	private int _openTime;
	private boolean _participantJoined;
	private String _participantURL;
	private String _password;
	private boolean _publicMeeting;
	private String _registrationURL;
	private boolean _showOnPublicMeetingList;
	private Calendar _startCalendar;
	private String _status;
	private TimeZone _timeZone;

}