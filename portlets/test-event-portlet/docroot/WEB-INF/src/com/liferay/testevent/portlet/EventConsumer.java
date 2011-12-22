/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.testevent.portlet;

import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.HashMap;

import javax.portlet.Event;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Shinn Lok
 */
public class EventConsumer extends GenericPortlet {

	@Override
	public void processEvent(
		EventRequest eventRequest, EventResponse eventResponse) {

		Event event = eventRequest.getEvent();

		HashMap<String, String> hashMap =
			(HashMap<String, String>)event.getValue();

		String value = hashMap.get("hello");

		if (Validator.equals(value, "world")) {
			_result = true;
		}
		else {
			_result = false;
		}
	}

	@Override
	public void doView(RenderRequest request, RenderResponse response)
		throws IOException {

		if (_result == null) {
			return;
		}

		PrintWriter printWriter = response.getWriter();

		if (_result) {
			printWriter.write("PASSED");
		}
		else {
			printWriter.write("FAILED");
		}

		printWriter.close();
	}

	private Boolean _result;

}