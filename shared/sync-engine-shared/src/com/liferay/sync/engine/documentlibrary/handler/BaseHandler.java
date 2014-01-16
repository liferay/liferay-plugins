/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Gail Hernandez
 */
public class BaseHandler implements ResponseHandler<String> {

	@Override
	public String handleResponse(HttpResponse httpResponse)
		throws HttpResponseException, IOException {

		StatusLine statusLine = httpResponse.getStatusLine();

		if (statusLine.getStatusCode() != 200) {
			_logger.error("Status code {}", statusLine.getStatusCode());

			throw new HttpResponseException(
				statusLine.getStatusCode(), statusLine.getReasonPhrase());
		}

		HttpEntity httpEntity = httpResponse.getEntity();

		return EntityUtils.toString(httpEntity);
	}

	private static Logger _logger = LoggerFactory.getLogger(BaseHandler.class);

}