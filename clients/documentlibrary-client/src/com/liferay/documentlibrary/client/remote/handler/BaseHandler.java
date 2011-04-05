/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.documentlibrary.client.remote.handler;

import com.ning.http.client.AsyncHandler;
import com.ning.http.client.HttpResponseBodyPart;
import com.ning.http.client.HttpResponseHeaders;
import com.ning.http.client.HttpResponseStatus;
import com.ning.http.client.Response.ResponseBuilder;
import com.ning.http.client.Response;

/**
 * @author Gail Hernandez
 */
public abstract class BaseHandler implements AsyncHandler<Response> {

	public BaseHandler() {
		_responseBuilder = new Response.ResponseBuilder();
	}

	public STATE onBodyPartReceived(HttpResponseBodyPart bodyPart)
		throws Exception {

		_responseBuilder.accumulate(bodyPart);

		return STATE.CONTINUE;
	}

	public STATE onHeadersReceived(HttpResponseHeaders headers)
		throws Exception {

		_responseBuilder.accumulate(headers);

		return STATE.CONTINUE;
	}

	public STATE onStatusReceived(HttpResponseStatus responseStatus)
		throws Exception {

		_responseBuilder.accumulate(responseStatus);

		return STATE.CONTINUE;
	}

	public void onThrowable(Throwable t) {
	}

	protected ResponseBuilder getResponseBuilder() {
		return _responseBuilder;
	}

	private ResponseBuilder _responseBuilder;

}