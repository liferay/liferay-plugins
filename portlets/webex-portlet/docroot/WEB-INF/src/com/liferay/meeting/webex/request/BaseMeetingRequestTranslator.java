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

package com.liferay.meeting.webex.request;

import com.liferay.meeting.Meeting;
import com.liferay.meeting.MeetingAttendeeOptions;
import com.liferay.meeting.MeetingContext;
import com.liferay.meeting.MeetingOptions;
import com.liferay.meeting.MeetingParticipant;
import com.liferay.meeting.MeetingRequest;
import com.liferay.meeting.MeetingTelephonyOptions;
import com.liferay.meeting.webex.util.TimeZoneUtil;
import com.liferay.meeting.webex.util.WebExConstants;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import com.webex.schemas.x2002.x06.common.PersonType;
import com.webex.schemas.x2002.x06.common.TimeZoneType;
import com.webex.schemas.x2002.x06.service.BodyContentType;
import com.webex.schemas.x2002.x06.service.MessageDocument;
import com.webex.schemas.x2002.x06.service.MessageType;
import com.webex.schemas.x2002.x06.service.SecurityContextType;
import com.webex.schemas.x2002.x06.service.attendee.AttendeeType;
import com.webex.schemas.x2002.x06.service.meeting.AccessControlType;
import com.webex.schemas.x2002.x06.service.meeting.AttendeeOptionsType;
import com.webex.schemas.x2002.x06.service.meeting.EnableOptionsType;
import com.webex.schemas.x2002.x06.service.meeting.MetaDataType;
import com.webex.schemas.x2002.x06.service.meeting.ParticipantsType;
import com.webex.schemas.x2002.x06.service.meeting.ScheduleType;
import com.webex.schemas.x2002.x06.service.meeting.TelephonyType;

import java.math.BigInteger;

import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;

/**
 * @author Michael C. Han
 */
public abstract class BaseMeetingRequestTranslator
	implements MeetingRequestTranslator {

	public String getSiteURL(MeetingContext meetingContext) {
		StringBundler siteURL = new StringBundler(6);

		siteURL.append(Http.HTTP_WITH_SLASH);
		siteURL.append(meetingContext.getName());
		siteURL.append(StringPool.PERIOD);
		siteURL.append(WebExConstants.DOMAIN);
		siteURL.append(StringPool.SLASH);
		siteURL.append(meetingContext.getApiURL());

		return siteURL.toString();
	}

	public String translate(
		MeetingRequest meetingRequest, MeetingContext meetingContext) {

		MessageDocument messageDocument = MessageDocument.Factory.newInstance();

		MessageType messageType = MessageType.Factory.newInstance();

		messageType.setBody(
			getMessageTypeBody(messageType, meetingRequest, meetingContext));
		messageType.setHeader(
			getMessageTypeHeader(messageType, meetingContext));

		messageDocument.setMessage(messageType);

		return messageDocument.toString();
	}

	protected AccessControlType getAccessControlType(Meeting meeting) {
		AccessControlType accessControlType =
			AccessControlType.Factory.newInstance();

		if (Validator.isNotNull(meeting.getPassword())) {
			accessControlType.setEnforcePassword(true);
			accessControlType.setMeetingPassword(meeting.getPassword());
		}
		else {
			accessControlType.setEnforcePassword(false);
		}

		accessControlType.setIsPublic(meeting.isPublicMeeting());
		accessControlType.setListToPublic(meeting.isShowOnPublicMeetingList());

		return accessControlType;
	}

	protected AttendeeOptionsType getAttendeeOptionsType(Meeting meeting) {
		MeetingAttendeeOptions meetingAttendeeOptions =
			meeting.getMeetingAttendeeOptions();

		if (meetingAttendeeOptions == null) {
			return null;
		}

		AttendeeOptionsType attendeeOptionsType =
			AttendeeOptionsType.Factory.newInstance();

		attendeeOptionsType.setAuto(
			meetingAttendeeOptions.isAutoAcceptRegistration());
		attendeeOptionsType.setEmailInvitations(
			meetingAttendeeOptions.isSendEmailInvitation());
		attendeeOptionsType.setExcludePassword(
			meetingAttendeeOptions.isExcludePassword());
		attendeeOptionsType.setJoinRequiresAccount(
			meetingAttendeeOptions.isJoinRequiresAccount());
		attendeeOptionsType.setParticipantLimit(
			BigInteger.valueOf(meetingAttendeeOptions.getMaxParticipants()));
		attendeeOptionsType.setRegistration(
			meetingAttendeeOptions.isRegistrationRequired());
		attendeeOptionsType.setRequest(
			meetingAttendeeOptions.isRequestAttendeeDetails());

		return attendeeOptionsType;
	}

	protected abstract BodyContentType getBodyContentType(
		MeetingRequest meetingRequest, MeetingContext meetingContext);

	protected EnableOptionsType getEnableOptionsType(Meeting meeting) {
		MeetingOptions meetingOptions = meeting.getMeetingOptions();

		if (meetingOptions == null) {
			return null;
		}

		EnableOptionsType enableOptionsType =
			EnableOptionsType.Factory.newInstance();

		enableOptionsType.setApplicationShare(
			meetingOptions.isAllowApplicationSharing());
		enableOptionsType.setAttendeeRecordMeeting(
			meetingOptions.isSupportRecordMeetingByAttendee());
		enableOptionsType.setAudioVideo(meetingOptions.isEnableAudioVideo());
		enableOptionsType.setDesktopShare(
			meetingOptions.isAllowDesktopSharing());
		enableOptionsType.setChat(meetingOptions.isEnableChat());
		enableOptionsType.setChatAllAttendees(
			meetingOptions.isEnableChatBetweenAttendees());
		enableOptionsType.setChatHost(meetingOptions.isEnableChatWithHost());
		enableOptionsType.setChatPresenter(
			meetingOptions.isEnableChatWithPresenter());
		enableOptionsType.setEnableReg(meetingOptions.isEnableRegistration());
		enableOptionsType.setNotes(meetingOptions.isEnableNotes());
		enableOptionsType.setPoll(meetingOptions.isEnablePoll());
		enableOptionsType.setPresentation(
			meetingOptions.isSupportPresentation());
		enableOptionsType.setSupportFeedback(
			meetingOptions.isSupportFeedback());
		enableOptionsType.setSupportPanelists(
			meetingOptions.isSupportPanelists());
		enableOptionsType.setSupportQandA(meetingOptions.isSupportQAndA());
		enableOptionsType.setVoip(meetingOptions.isSupportVoip());

		return enableOptionsType;
	}

	protected MessageType.Body getMessageTypeBody(
		MessageType messageType, MeetingRequest meetingRequest,
		MeetingContext meetingContext) {

		BodyContentType bodyContentType = getBodyContentType(
			meetingRequest, meetingContext);

		MessageType.Body messageTypeBody =
			MessageType.Body.Factory.newInstance();

		messageTypeBody.setBodyContentArray(
			new BodyContentType[] {bodyContentType});

		return messageTypeBody;
	}

	protected MessageType.Header getMessageTypeHeader(
		MessageType messageType, MeetingContext meetingContext) {

		MessageType.Header messageTypeHeader =
			MessageType.Header.Factory.newInstance();

		SecurityContextType securityContextType =
			SecurityContextType.Factory.newInstance();

		securityContextType.setPartnerID(meetingContext.getPartnerKey());
		securityContextType.setPassword(meetingContext.getPassword());
		securityContextType.setSiteID(meetingContext.getSiteKey());
		securityContextType.setSiteName(meetingContext.getName());
		securityContextType.setWebExID(meetingContext.getLogin());

		messageTypeHeader.setSecurityContext(securityContextType);

		return messageTypeHeader;
	}

	protected MetaDataType getMetaDataType(Meeting meeting) {
		MetaDataType metaDataType = MetaDataType.Factory.newInstance();

		metaDataType.setAgenda(meeting.getDescription());
		metaDataType.setConfName(meeting.getName());
		metaDataType.setGreeting(meeting.getGreeting());
		metaDataType.setInvitation(meeting.getInvitation());
		metaDataType.setLocation(meeting.getLocation());

		return metaDataType;
	}

	protected ParticipantsType getParticipantsType(Meeting meeting) {
		ParticipantsType participantsType =
			ParticipantsType.Factory.newInstance();

		ParticipantsType.Attendees attendees =
			ParticipantsType.Attendees.Factory.newInstance();

		Set<MeetingParticipant> meetingParticipants =
			meeting.getMeetingParticipants();

		for (MeetingParticipant meetingParticipant : meetingParticipants) {
			AttendeeType attendeeType = attendees.addNewAttendee();

			attendeeType.setEmailInvitations(
				meetingParticipant.isSendInvitation());

			Locale locale = meetingParticipant.getLocale();

			if (locale != null) {
				attendeeType.setLocale(locale.getDisplayName());
			}

			PersonType personType = PersonType.Factory.newInstance();

			personType.setEmail(meetingParticipant.getEmailAddress());
			personType.setFirstName(meetingParticipant.getFirstName());
			personType.setLastName(meetingParticipant.getLastName());
			personType.setSendReminder(meetingParticipant.isSendReminder());
			personType.setWebExId(meetingParticipant.getLogin());

			attendeeType.setPerson(personType);

			TimeZone timeZone = meetingParticipant.getTimeZone();

			if (timeZone != null) {
				TimeZoneType.Enum timeZoneTypeEnum = TimeZoneUtil.convert(
					timeZone);

				attendeeType.setTimeZoneID(timeZoneTypeEnum.intValue());
			}
		}

		participantsType.setAttendees(attendees);

		participantsType.setMaxUserNumber(meeting.getMaxAttendees());

		return participantsType;
	}

	protected ScheduleType getScheduleType(
		Meeting meeting, MeetingContext meetingContext) {

		ScheduleType scheduleType = ScheduleType.Factory.newInstance();

		scheduleType.setDuration(meeting.getDuration());
		scheduleType.setHostWebExID(meetingContext.getLogin());
		scheduleType.setOpenTime(meeting.getOpenTime());

		String startDate = DateUtil.getDate(
			meeting.getStartCalendar().getTime(), WebExConstants.DATE_PATTERN,
			meeting.getLocale());

		scheduleType.setStartDate(startDate);

		TimeZoneType.Enum timeZoneTypeEnum = TimeZoneUtil.convert(
			meeting.getTimeZone());

		scheduleType.setTimeZone(timeZoneTypeEnum);

		return scheduleType;
	}

	protected TelephonyType getTelephonyType(Meeting meeting) {
		MeetingTelephonyOptions meetingTelephonyOptions =
			meeting.getMeetingTelephonyOptions();

		if (meetingTelephonyOptions == null) {
			return null;
		}

		TelephonyType telephonyType = TelephonyType.Factory.newInstance();

		telephonyType.setEnableTSP(meetingTelephonyOptions.isEnableTSP());
		telephonyType.setExtTelephonyDescription(
			meetingTelephonyOptions.getTelephonyDescription());
		telephonyType.setExtTelephonyURL(
			meetingTelephonyOptions.getTelephonyURL());
		telephonyType.setPersonalAccountIndex(
			BigInteger.valueOf(
				meetingTelephonyOptions.getPersonalAccountIndex()));
		telephonyType.setTeleconfLocation(
			meetingTelephonyOptions.getTeleconferenceLocation());
		telephonyType.setTspAccountIndex(
			BigInteger.valueOf(meetingTelephonyOptions.getTspAccountIndex()));

		return telephonyType;
	}

}