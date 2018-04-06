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

import com.webex.schemas.x2002.x06.service.BodyContentType;
import com.webex.schemas.x2002.x06.service.meeting.GetjoinurlMeetingResponse;
import com.webex.schemas.x2002.x06.service.meeting.impl.GetjoinurlMeetingResponseImpl;

/**
 * @author Anant Singh
 */
public class GetJoinURLMeetingResponseProcessor
	extends BaseMeetingResponseProcessor<String> {

	public String process(String xml) throws MeetingException {
		BodyContentType[] bodyContentTypes = getBodyContentTypes(xml);

		GetjoinurlMeetingResponse getjoinurlMeetingResponse =
			(GetjoinurlMeetingResponseImpl)bodyContentTypes[0];

		return getjoinurlMeetingResponse.getJoinMeetingURL();
	}

}