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

package com.liferay.meeting.webex.transport;

import com.liferay.meeting.MeetingException;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import com.webex.schemas.x2002.x06.service.MessageDocument;
import com.webex.schemas.x2002.x06.service.MessageType;
import com.webex.schemas.x2002.x06.service.ResponseType;
import com.webex.schemas.x2002.x06.service.ResultTypeType.Enum;

import org.apache.xmlbeans.XmlException;

/**
 * @author Michael C. Han
 * @author Anant Singh
 */
public class HttpPostTransport implements Transport {

	public String send(String hostURL, String requestXml)
		throws MeetingException {

		String responseXml = null;

		try {
			Http.Options options = new Http.Options();

			options.setBody(requestXml, ContentTypes.TEXT_XML, StringPool.UTF8);
			options.setLocation(hostURL);
			options.setPost(true);

			responseXml = HttpUtil.URLtoString(options);
		}
		catch (Exception e) {
			throw new MeetingException(e.getMessage(), e);
		}

		if (Validator.isNull(responseXml)) {
			throw new MeetingException("No response from server");
		}

		try {
			MessageDocument messageDocument = MessageDocument.Factory.parse(
				responseXml);

			MessageType messageType = messageDocument.getMessage();

			MessageType.Header header = messageType.getHeader();

			ResponseType responseType = header.getResponseArray(0);

			if (responseType.getResult() == Enum.forString("SUCCESS")) {
				return responseXml;
			}

			throw new MeetingException(responseType.getReason());
		}
		catch (XmlException xe) {
			throw new MeetingException(xe);
		}
	}

}