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

import com.liferay.portal.kernel.util.ContentTypes;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.HashMap;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.xml.namespace.QName;

/**
 * @author Shinn Lok
 */
public class EventProducer extends GenericPortlet {

	@Override
	public void doView(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException {

		renderResponse.setContentType(ContentTypes.TEXT_HTML);

		PrintWriter printWriter = renderResponse.getWriter();

		printWriter.write("<a href=\"");
		printWriter.write(String.valueOf(renderResponse.createActionURL()));
		printWriter.write("\">Send Event</a>");

		printWriter.close();
	}

	@Override
	public void processAction(
		ActionRequest actionRequest, ActionResponse actionResponse) {

		QName qName = new QName("http://www.liferay.com", "LPS-18191");

		HashMap<String, String> hashMap = new HashMap<String, String>();

		hashMap.put("hello", "world");

		actionResponse.setEvent(qName, hashMap);
	}

}