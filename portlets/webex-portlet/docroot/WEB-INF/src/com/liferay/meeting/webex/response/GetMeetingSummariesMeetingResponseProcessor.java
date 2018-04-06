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

import com.liferay.meeting.MeetingException;
import com.liferay.meeting.MeetingSummary;
import com.liferay.meeting.webex.util.CalendarUtil;

import com.webex.schemas.x2002.x06.common.ListingType;
import com.webex.schemas.x2002.x06.service.BodyContentType;
import com.webex.schemas.x2002.x06.service.meeting.LstsummaryMeetingResponse;
import com.webex.schemas.x2002.x06.service.meeting.MeetingSummaryInstanceType;
import com.webex.schemas.x2002.x06.service.meeting.impl.LstsummaryMeetingResponseImpl;

import java.math.BigInteger;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Anant Singh
 */
public class GetMeetingSummariesMeetingResponseProcessor
	extends BaseMeetingResponseProcessor<Set<MeetingSummary>> {

	public Set<MeetingSummary> process(String xml) throws MeetingException {
		BodyContentType[] bodyContentTypes = getBodyContentTypes(xml);

		LstsummaryMeetingResponse lstsummaryMeetingResponse =
			(LstsummaryMeetingResponseImpl)bodyContentTypes[0];

		MeetingSummaryInstanceType[] meetingSummaryInstanceTypes =
			lstsummaryMeetingResponse.getMeetingArray();

		Set<MeetingSummary> meetingSummaries = new HashSet<>(
			meetingSummaryInstanceTypes.length);

		for (MeetingSummaryInstanceType meetingSummaryInstanceType :
				meetingSummaryInstanceTypes) {

			MeetingSummary meetingSummary = getMeetingSummary(
				meetingSummaryInstanceType);

			meetingSummaries.add(meetingSummary);
		}

		return meetingSummaries;
	}

	protected MeetingSummary getMeetingSummary(
			MeetingSummaryInstanceType meetingSummaryInstanceType)
		throws MeetingException {

		MeetingSummary meetingSummary = new MeetingSummary();

		BigInteger duration = meetingSummaryInstanceType.getDuration();

		meetingSummary.setDuration(duration.intValue());

		meetingSummary.setHostJoined(
			meetingSummaryInstanceType.getHostJoined());
		meetingSummary.setHostKey(meetingSummaryInstanceType.getHostWebExID());

		ListingType.Enum listingTypeEnum =
			meetingSummaryInstanceType.getListStatus();

		meetingSummary.setListingStatus(listingTypeEnum.toString());

		meetingSummary.setMeetingKey(
			meetingSummaryInstanceType.getMeetingKey());
		meetingSummary.setMeetingName(meetingSummaryInstanceType.getConfName());
		meetingSummary.setMeetingStatus(meetingSummaryInstanceType.getStatus());
		meetingSummary.setOtherHostKey(
			meetingSummaryInstanceType.getOtherHostWebExID());
		meetingSummary.setParticipantsJoined(
			meetingSummaryInstanceType.getParticipantsJoined());
		meetingSummary.setStartCalendar(
			CalendarUtil.getCalendar(
				meetingSummaryInstanceType.getTimeZone(),
				meetingSummaryInstanceType.getStartDate()));
		meetingSummary.setTimeZone(meetingSummaryInstanceType.getTimeZone());

		return meetingSummary;
	}

}