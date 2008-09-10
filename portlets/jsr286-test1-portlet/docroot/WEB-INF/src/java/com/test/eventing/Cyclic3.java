/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

/**
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at http://www.sun.com/cddl/cddl.html and
 * legal/CDDLv1.0.txt. See the License for the specific language governing
 * permission and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL Header Notice in each file
 * and include the License file at legal/CDDLv1.0.txt.
 *
 * If applicable, add the following below the CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Copyright 2008 Sun Microsystems Inc. All rights reserved.
 */

package com.test.eventing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.xml.namespace.QName;

/**
 * <a href="Cyclic3.java.html"><b><i>View Source</i></b></a>
 *
 * @author Sriram
 *
 */
public class Cyclic3 extends GenericPortlet {

	public void processAction(ActionRequest request, ActionResponse
		response)
			throws PortletException,IOException {
	}

	public void doView(RenderRequest request,RenderResponse response)
			throws PortletException,IOException {

		response.setContentType("text/html");

		PrintWriter writer = response.getWriter();

		writer.println("Recieved:"+request.getParameter("key3"));
	}

	public void doEdit(RenderRequest request,RenderResponse response)
			throws PortletException,IOException {

	}

	public void doHelp(RenderRequest request, RenderResponse response)
			throws PortletException,IOException {

		PortletRequestDispatcher dispatcher =
		getPortletContext().getRequestDispatcher
			("/WEB-INF/jsp/TC_003.jsp");
		dispatcher.include(request, response);
	}

	@javax.portlet.ProcessEvent(qname = "{http://suncyclic.com}l2")
	public void processEvent(javax.portlet.EventRequest request,
			javax.portlet.EventResponse response)
			throws javax.portlet.PortletException,
			java.io.IOException {

		javax.portlet.Event event = request.getEvent();

		java.lang.String value = (java.lang.String) event.getValue();

		response.setRenderParameter("key3",value);

		try {
			QName qName = new QName("http://suncyclic.com", "l3", "x");

			String l3_Data = "R3";

			response.setEvent(qName, l3_Data);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void serveResource(ResourceRequest request,
			ResourceResponse response) throws PortletException,
			IOException {

		response.setContentType("text/html");

		String content = getContents
			("/WEB-INF/jsp/PCAutomationTC.html");

		PrintWriter writer = response.getWriter();

		writer.print(content);
	}

	public String getContents(String path) {

		InputStream is = getPortletContext().getResourceAsStream(path);

		if (is == null) {
			return "Invoice Page Not found !!!";
		}

		StringBuffer contents = new StringBuffer();

		BufferedReader input = null;

		try {
			input = new BufferedReader(new InputStreamReader(is));
			String line = null; //not declared within while loop
			/*
			 * readLine is a bit quirky :
			 * it returns the content of a line MINUS the newline.
			 * it returns null only for the END of the stream.
			 * it returns an empty String if two newlines appear
			 in a row.
			 */
			while ((line = input.readLine()) != null) {
				contents.append(line);

				contents.append(System.getProperty
					("line.separator"));
			}
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}

		finally {
			try {
				if (input != null) {
					//flush and close both "input"
					//and its underlying FileReader
					input.close();
				}
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return contents.toString();
	}

}