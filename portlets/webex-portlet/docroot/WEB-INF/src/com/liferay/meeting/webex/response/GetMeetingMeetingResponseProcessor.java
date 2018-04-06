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

import com.liferay.meeting.Meeting;
import com.liferay.meeting.MeetingAttendeeOptions;
import com.liferay.meeting.MeetingException;
import com.liferay.meeting.MeetingOptions;
import com.liferay.meeting.MeetingParticipant;
import com.liferay.meeting.MeetingTelephonyOptions;
import com.liferay.meeting.webex.util.CalendarUtil;

import com.webex.schemas.x2002.x06.common.PersonType;
import com.webex.schemas.x2002.x06.service.BodyContentType;
import com.webex.schemas.x2002.x06.service.attendee.AttendeeType;
import com.webex.schemas.x2002.x06.service.meeting.AccessControlType;
import com.webex.schemas.x2002.x06.service.meeting.AttendeeOptionsType;
import com.webex.schemas.x2002.x06.service.meeting.EnableOptionsType;
import com.webex.schemas.x2002.x06.service.meeting.GetMeetingResponse;
import com.webex.schemas.x2002.x06.service.meeting.MetaDataType;
import com.webex.schemas.x2002.x06.service.meeting.ParticipantsType;
import com.webex.schemas.x2002.x06.service.meeting.ParticipantsType.Attendees;
import com.webex.schemas.x2002.x06.service.meeting.ScheduleType;
import com.webex.schemas.x2002.x06.service.meeting.TelephonyType;
import com.webex.schemas.x2002.x06.service.meeting.impl.GetMeetingResponseImpl;

import java.math.BigInteger;

import java.util.Calendar;

/**
 * @author Anant Singh
 */
public class GetMeetingMeetingResponseProcessor
	extends BaseMeetingResponseProcessor<Meeting> {

	public Meeting process(String xml) throws MeetingException {
		Meeting meeting = new Meeting();

		BodyContentType[] bodyContentTypes = getBodyContentTypes(xml);

		GetMeetingResponse getMeetingResponse =
			(GetMeetingResponseImpl)bodyContentTypes[0];

		meeting.setMeetingKey(getMeetingResponse.getMeetingkey());
		meeting.setHostJoined(getMeetingResponse.getHostJoined());
		meeting.setHostKey(getMeetingResponse.getHostKey());
		meeting.setStatus(getMeetingResponse.getStatus());

		processAccessControlType(
			getMeetingResponse.getAccessControl(), meeting);
		processAttendeeOptionsType(
			getMeetingResponse.getAttendeeOptions(), meeting);
		processEnableOptionsType(
			getMeetingResponse.getEnableOptions(), meeting);
		processMetaDataType(getMeetingResponse.getMetaData(), meeting);
		processParticipantsType(getMeetingResponse.getParticipants(), meeting);
		processScheduleType(getMeetingResponse.getSchedule(), meeting);
		processTelephonyType(getMeetingResponse.getTelephony(), meeting);

		return meeting;
	}

	protected void processAccessControlType(
		AccessControlType accessControlType, Meeting meeting) {

		meeting.setPassword(accessControlType.getMeetingPassword());
		meeting.setPublicMeeting(accessControlType.getIsPublic());
		meeting.setShowOnPublicMeetingList(accessControlType.getListToPublic());
	}

	protected void processAttendeeOptionsType(
		AttendeeOptionsType attendeeOptionsType, Meeting meeting) {

		MeetingAttendeeOptions meetingAttendeeOptions =
			new MeetingAttendeeOptions();

		meetingAttendeeOptions.setAutoAcceptRegistration(
			attendeeOptionsType.getAuto());
		meetingAttendeeOptions.setExcludePassword(
			attendeeOptionsType.getExcludePassword());
		meetingAttendeeOptions.setJoinRequiresAccount(
			attendeeOptionsType.getJoinRequiresAccount());

		BigInteger participantLimit = attendeeOptionsType.getParticipantLimit();

		meetingAttendeeOptions.setMaxParticipants(participantLimit.intValue());

		meetingAttendeeOptions.setRegistrationRequired(
			attendeeOptionsType.getRegistration());
		meetingAttendeeOptions.setRequestAttendeeDetails(
			attendeeOptionsType.getRequest());
		meetingAttendeeOptions.setSendEmailInvitation(
			attendeeOptionsType.getEmailInvitations());

		meeting.setMeetingAttendeeOptions(meetingAttendeeOptions);
	}

	protected void processEnableOptionsType(
		EnableOptionsType enableOptionsType, Meeting meeting) {

		MeetingOptions meetingOptions = new MeetingOptions();

		meetingOptions.setAllowApplicationSharing(
			enableOptionsType.getApplicationShare());
		meetingOptions.setAllowDesktopSharing(
			enableOptionsType.getDesktopShare());
		meetingOptions.setEnableAudioVideo(enableOptionsType.getAudioVideo());
		meetingOptions.setEnableChat(enableOptionsType.getChat());
		meetingOptions.setEnableChatBetweenAttendees(
			enableOptionsType.getChatAllAttendees());
		meetingOptions.setEnableChatWithHost(enableOptionsType.getChatHost());
		meetingOptions.setEnableChatWithPresenter(
			enableOptionsType.getChatPresenter());
		meetingOptions.setEnableNotes(enableOptionsType.getNotes());
		meetingOptions.setEnablePoll(enableOptionsType.getPoll());
		meetingOptions.setEnableRegistration(enableOptionsType.getEnableReg());
		meetingOptions.setSupportFeedback(
			enableOptionsType.getSupportFeedback());
		meetingOptions.setSupportPanelists(
			enableOptionsType.getSupportPanelists());
		meetingOptions.setSupportPresentation(
			enableOptionsType.getPresentation());
		meetingOptions.setSupportQAndA(enableOptionsType.getSupportQandA());
		meetingOptions.setSupportRecordMeetingByAttendee(
			enableOptionsType.getAttendeeRecordMeeting());
		meetingOptions.setSupportVoip(enableOptionsType.getVoip());

		meeting.setMeetingOptions(meetingOptions);
	}

	protected void processMetaDataType(
		MetaDataType metaDataType, Meeting meeting) {

		meeting.setDescription(metaDataType.getAgenda());
		meeting.setGreeting(metaDataType.getGreeting());
		meeting.setInvitation(metaDataType.getInvitation());
		meeting.setLocation(metaDataType.getLocation());
		meeting.setName(metaDataType.getConfName());
	}

	protected void processParticipantsType(
		ParticipantsType participantsType, Meeting meeting) {

		Attendees attendees = participantsType.getAttendees();

		if (attendees == null) {
			return;
		}

		AttendeeType[] attendeeTypes = attendees.getAttendeeArray();

		if (attendeeTypes == null) {
			return;
		}

		for (AttendeeType attendeeType : attendeeTypes) {
			MeetingParticipant meetingParticipant = new MeetingParticipant();

			PersonType personType = attendeeType.getPerson();

			meetingParticipant.setEmailAddress(personType.getEmail());
			meetingParticipant.setFirstName(personType.getFirstName());
			meetingParticipant.setLastName(personType.getLastName());
			meetingParticipant.setLogin(personType.getWebExId());

			meeting.addMeetingParticipant(meetingParticipant);
		}
	}

	protected void processScheduleType(
			ScheduleType scheduleType, Meeting meeting)
		throws MeetingException {

		meeting.setDuration(scheduleType.getDuration());
		meeting.setHostId(scheduleType.getHostWebExID());

		Calendar startCalendaralendar = CalendarUtil.getCalendar(
			scheduleType.getTimeZone(), scheduleType.getStartDate());

		meeting.setStartCalendar(startCalendaralendar);
		meeting.setTimeZone(startCalendaralendar.getTimeZone());
	}

	protected void processTelephonyType(
		TelephonyType telephonyType, Meeting meeting) {

		MeetingTelephonyOptions meetingTelephonyOptions =
			new MeetingTelephonyOptions();

		meetingTelephonyOptions.setEnableTSP(telephonyType.getEnableTSP());

		BigInteger personalAccountIndex =
			telephonyType.getPersonalAccountIndex();

		if (personalAccountIndex != null) {
			meetingTelephonyOptions.setPersonalAccountIndex(
				personalAccountIndex.intValue());
		}

		meetingTelephonyOptions.setTeleconferenceLocation(
			telephonyType.getTeleconfLocation());
		meetingTelephonyOptions.setTelephonyDescription(
			telephonyType.getExtTelephonyDescription());
		meetingTelephonyOptions.setTelephonyURL(
			telephonyType.getExtTelephonyURL());

		BigInteger tspAccountIndex = telephonyType.getTspAccountIndex();

		if (tspAccountIndex != null) {
			meetingTelephonyOptions.setTspAccountIndex(
				tspAccountIndex.intValue());
		}

		meeting.setMeetingTelephonyOptions(meetingTelephonyOptions);
	}

}