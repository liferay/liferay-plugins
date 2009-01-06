/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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

package com.liferay.sampleservicebuilder.portlet;

import com.liferay.portal.PortalException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PortalUtil;
import com.liferay.sampleservicebuilder.service.FooLocalServiceUtil;
import com.liferay.util.bridges.jsp.JSPPortlet;

import java.io.IOException;

import java.util.Calendar;
import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

/**
 * <a href="ServiceBuilderPortlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Alexander Chow
 *
 */
public class ServiceBuilderPortlet extends JSPPortlet {

	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		try {
			String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

			if (cmd.equals(Constants.ADD)) {
				addFoo(actionRequest);
			}
			else if (cmd.equals(Constants.DELETE)) {
				deleteFoo(actionRequest);
			}

			if (Validator.isNotNull(cmd)) {
				if (SessionErrors.isEmpty(actionRequest)) {
					SessionMessages.add(actionRequest, "request_processed");
				}

				String redirect = ParamUtil.getString(
					actionRequest, "redirect");

				actionResponse.sendRedirect(redirect);
			}
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	protected void addFoo(ActionRequest actionRequest) throws Exception {
		String field1 = ParamUtil.getString(actionRequest, "field1");
		boolean field2 = ParamUtil.getBoolean(actionRequest, "field2");
		int field3 = ParamUtil.getInteger(actionRequest, "field3");
		String field5 = ParamUtil.getString(actionRequest, "field5");

		int dateMonth = ParamUtil.getInteger(actionRequest, "field4Month");
		int dateDay = ParamUtil.getInteger(actionRequest, "field4Day");
		int dateYear = ParamUtil.getInteger(actionRequest, "field4Year");
		int dateHour = ParamUtil.getInteger(actionRequest, "field4Hour");
		int dateMinute = ParamUtil.getInteger(actionRequest, "field4Minute");
		int dateAmPm = ParamUtil.getInteger(actionRequest, "field4AmPm");

		if (dateAmPm == Calendar.PM) {
			dateHour += 12;
		}

		Date field4 = PortalUtil.getDate(
			dateMonth, dateDay, dateYear, dateHour, dateMinute,
			new PortalException());

		FooLocalServiceUtil.addFoo(field1, field2, field3, field4, field5);
	}

	protected void deleteFoo(ActionRequest actionRequest) throws Exception {
		long fooId = ParamUtil.getLong(actionRequest, "fooId");

		FooLocalServiceUtil.deleteFoo(fooId);
	}

}