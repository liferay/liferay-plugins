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
import com.liferay.meeting.MeetingRequest;
import com.liferay.meeting.MeetingService;
import com.liferay.meeting.MeetingSummary;
import com.liferay.meeting.webex.request.MeetingRequestTranslator;
import com.liferay.meeting.webex.request.MeetingRequestTranslatorRegistry;
import com.liferay.meeting.webex.request.WebExMeetingRequestType;
import com.liferay.meeting.webex.response.MeetingResponseProcessor;
import com.liferay.meeting.webex.response.MeetingResponseProcessorRegistry;
import com.liferay.meeting.webex.transport.Transport;

import java.util.Calendar;
import java.util.Set;
import java.util.TimeZone;

/**
 * @author Brian Wing Shun Chan
 */
public class MeetingServiceImpl implements MeetingService {

	public MeetingConfirmation addMeeting(
			Meeting meeting, MeetingContext meetingContext)
		throws MeetingException {

		MeetingRequest meetingRequest = new MeetingRequest();

		meetingRequest.setMeeting(meeting);

		return (MeetingConfirmation)execute(
			WebExMeetingRequestType.CREATE_MEETING, meetingRequest,
			meetingContext);
	}

	public void deleteMeeting(long meetingKey, MeetingContext meetingContext)
		throws MeetingException {

		MeetingRequest meetingRequest = new MeetingRequest();

		meetingRequest.setMeetingKey(meetingKey);

		execute(
			WebExMeetingRequestType.DELETE_MEETING, meetingRequest,
			meetingContext);
	}

	public String getHostURL(long meetingKey, MeetingContext meetingContext)
		throws MeetingException {

		MeetingRequest meetingRequest = new MeetingRequest();

		meetingRequest.setMeetingKey(meetingKey);

		return (String)execute(
			WebExMeetingRequestType.GET_HOST_URL, meetingRequest,
			meetingContext);
	}

	public String getJoinURL(
			long meetingKey, String meetingPassword,
			MeetingParticipant meetingParticipant,
			MeetingContext meetingContext)
		throws MeetingException {

		MeetingRequest meetingRequest = new MeetingRequest();

		meetingRequest.setMeetingKey(meetingKey);
		meetingRequest.setMeetingParticipant(meetingParticipant);
		meetingRequest.setMeetingPassword(meetingPassword);

		return (String)execute(
			WebExMeetingRequestType.GET_JOIN_URL, meetingRequest,
			meetingContext);
	}

	public Meeting getMeeting(long meetingKey, MeetingContext meetingContext)
		throws MeetingException {

		MeetingRequest meetingRequest = new MeetingRequest();

		meetingRequest.setMeetingKey(meetingKey);

		return (Meeting)execute(
			WebExMeetingRequestType.GET_MEETING, meetingRequest,
			meetingContext);
	}

	public Set<MeetingSummary> getMeetingSummaries(
			Calendar startCalendar, Calendar endCalendar, TimeZone timeZone,
			int max, MeetingContext meetingContext)
		throws MeetingException {

		MeetingRequest meetingRequest = new MeetingRequest();

		meetingRequest.setEndCalendar(endCalendar);
		meetingRequest.setMaxResponses(max);
		meetingRequest.setStartCalendar(startCalendar);
		meetingRequest.setTimeZone(timeZone);

		return (Set<MeetingSummary>)execute(
			WebExMeetingRequestType.GET_MEETING_SUMMARIES, meetingRequest,
			meetingContext);
	}

	public Set<MeetingSummary> getMeetingSummaries(
			TimeZone timeZone, int start, int max,
			MeetingContext meetingContext)
		throws MeetingException {

		MeetingRequest meetingRequest = new MeetingRequest();

		meetingRequest.setMaxResponses(max);
		meetingRequest.setStartFrom(start);
		meetingRequest.setTimeZone(timeZone);

		return (Set<MeetingSummary>)execute(
			WebExMeetingRequestType.GET_MEETING_SUMMARIES, meetingRequest,
			meetingContext);
	}

	public Set<MeetingSummary> getMeetingSummaries(
			TimeZone timeZone, int max, MeetingContext meetingContext)
		throws MeetingException {

		MeetingRequest meetingRequest = new MeetingRequest();

		meetingRequest.setMaxResponses(max);
		meetingRequest.setTimeZone(timeZone);

		return (Set<MeetingSummary>)execute(
			WebExMeetingRequestType.GET_MEETING_SUMMARIES, meetingRequest,
			meetingContext);
	}

	public void setTransport(Transport transport) {
		_transport = transport;
	}

	public void updateMeeting(Meeting meeting, MeetingContext meetingContext)
		throws MeetingException {

		MeetingRequest meetingRequest = new MeetingRequest();

		meetingRequest.setMeeting(meeting);
		meetingRequest.setMeetingKey(meeting.getMeetingKey());

		execute(
			WebExMeetingRequestType.UPDATE_MEETING, meetingRequest,
			meetingContext);
	}

	protected Object execute(
			WebExMeetingRequestType webExRequestType,
			MeetingRequest meetingRequest, MeetingContext meetingContext)
		throws MeetingException {

		MeetingRequestTranslator meetingRequestTranslator =
			MeetingRequestTranslatorRegistry.getRequestTranslator(
				webExRequestType);

		String siteURL = meetingRequestTranslator.getSiteURL(meetingContext);

		String requestXml = meetingRequestTranslator.translate(
			meetingRequest, meetingContext);

		String responseXml = _transport.send(siteURL, requestXml);

		MeetingResponseProcessor<?> meetingResponseProcessor =
			MeetingResponseProcessorRegistry.getResponseProcessor(
				webExRequestType);

		return meetingResponseProcessor.process(responseXml);
	}

	private Transport _transport;

}