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

package com.liferay.sync.engine.documentlibrary.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.sync.engine.documentlibrary.event.Event;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpResponseException;
import org.apache.http.util.EntityUtils;

/**
 * @author Shinn Lok
 */
public class BaseJSONHandler extends BaseHandler {

	public BaseJSONHandler(Event event) {
		super(event);
	}

	@Override
	protected void doHandleResponse(HttpResponse httpResponse)
		throws Exception {

		HttpEntity httpEntity = httpResponse.getEntity();

		String response = EntityUtils.toString(httpEntity);

		handlePortalException(response);

		processResponse(response);
	}

	protected void handlePortalException(String response) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();

		JsonNode responseJsonNode = null;

		try {
			responseJsonNode = objectMapper.readTree(response);
		}
		catch (Exception e) {
			return;
		}

		JsonNode exceptionJsonNode = responseJsonNode.get("exception");

		if (exceptionJsonNode == null) {
			return;
		}

		String exception = exceptionJsonNode.asText();

		if (exception.equals("java.lang.SecurityException")) {
			JsonNode messageJsonNode = responseJsonNode.get("message");

			String message = messageJsonNode.asText();

			if (message.equals("Authenticated access required")) {
				throw new HttpResponseException(
					HttpServletResponse.SC_UNAUTHORIZED, message);
			}
		}
	}

	protected void processResponse(String response) throws Exception {
	}

}