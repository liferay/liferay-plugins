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

import com.liferay.meeting.webex.request.WebExMeetingRequestType;

import java.util.Map;

/**
 * @author Anant Singh
 */
public class MeetingResponseProcessorRegistry {

	public static MeetingResponseProcessor<?> getResponseProcessor(
		WebExMeetingRequestType webExRequestType) {

		MeetingResponseProcessor<?> meetingResponseProcessor =
			_meetingResponseProcessors.get(webExRequestType);

		if (meetingResponseProcessor == null) {
			throw new IllegalArgumentException(
				"Invalid WebEx request type " + webExRequestType);
		}

		return meetingResponseProcessor;
	}

	public void setMeetingResponseProcessors(
		Map<WebExMeetingRequestType, MeetingResponseProcessor<?>>
			meetingResponseProcessors) {

		_meetingResponseProcessors = meetingResponseProcessors;
	}

	private static Map<WebExMeetingRequestType, MeetingResponseProcessor<?>>
		_meetingResponseProcessors;

}