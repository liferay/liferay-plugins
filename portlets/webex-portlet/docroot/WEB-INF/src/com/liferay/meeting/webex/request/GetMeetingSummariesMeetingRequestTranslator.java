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

import com.liferay.meeting.MeetingContext;
import com.liferay.meeting.MeetingRequest;
import com.liferay.meeting.webex.util.WebExConstants;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;

import com.webex.schemas.x2002.x06.service.BodyContentType;
import com.webex.schemas.x2002.x06.service.LstControlType;
import com.webex.schemas.x2002.x06.service.meeting.DateScopeType;
import com.webex.schemas.x2002.x06.service.meeting.LstsummaryMeeting;

import java.math.BigInteger;

import java.text.Format;

import java.util.Calendar;

/**
 * @author Anant Singh
 */
public class GetMeetingSummariesMeetingRequestTranslator
	extends BaseMeetingRequestTranslator {

	public void setMax(int max) {
		_max = max;
	}

	protected void addDateScopeType(
		LstsummaryMeeting lstsummaryMeeting, Calendar startCalendar,
		Calendar endCalendar) {

		DateScopeType dateScopeType = lstsummaryMeeting.addNewDateScope();

		Format format = FastDateFormatFactoryUtil.getSimpleDateFormat(
			WebExConstants.DATE_PATTERN, startCalendar.getTimeZone());

		dateScopeType.setStartDateEnd(format.format(endCalendar));
		dateScopeType.setStartDateStart(format.format(startCalendar));
	}

	protected void addLstControlType(
		LstsummaryMeeting lstsummaryMeeting, int start, int max) {

		LstControlType lstControlType = lstsummaryMeeting.addNewListControl();

		if (max <= 0) {
			max = _max;
		}

		lstControlType.setMaximumNum(BigInteger.valueOf(max));

		lstControlType.setStartFrom(BigInteger.valueOf(start));
	}

	@Override
	protected BodyContentType getBodyContentType(
		MeetingRequest meetingRequest, MeetingContext meetingContext) {

		LstsummaryMeeting lstSummaryMeeting =
			LstsummaryMeeting.Factory.newInstance();

		Calendar startDate = meetingRequest.getStartCalendar();
		Calendar endDate = meetingRequest.getEndCalendar();

		if ((startDate != null) || (endDate != null)) {
			addDateScopeType(lstSummaryMeeting, startDate, endDate);
		}
		else {
			addLstControlType(
				lstSummaryMeeting, meetingRequest.getStartFrom(),
				meetingRequest.getMaxResponses());
		}

		return lstSummaryMeeting;
	}

	private int _max = 10;

}