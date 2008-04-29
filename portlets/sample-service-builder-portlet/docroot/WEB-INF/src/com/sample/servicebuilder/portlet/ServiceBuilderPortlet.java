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

package com.sample.servicebuilder.portlet;

import com.liferay.portal.PortalException;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.jsp.JSPPortlet;
import com.liferay.util.servlet.SessionErrors;
import com.liferay.util.servlet.SessionMessages;

import com.sample.servicebuilder.service.FooLocalServiceUtil;

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

	public void processAction(ActionRequest req, ActionResponse res)
		throws IOException, PortletException {

		try {
			String cmd = ParamUtil.getString(req, Constants.CMD);

			if (cmd.equals(Constants.ADD)) {
				addFoo(req);
			}
			else if (cmd.equals(Constants.DELETE)) {
				deleteFoo(req);
			}

			if (Validator.isNotNull(cmd)) {
				if (SessionErrors.isEmpty(req)) {
					SessionMessages.add(req, "request_processed");
				}

				String redirect = ParamUtil.getString(req, "redirect");

				res.sendRedirect(redirect);
			}
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	protected void addFoo(ActionRequest req) throws Exception {
		String field1 = ParamUtil.getString(req, "field1");
		boolean field2 = ParamUtil.getBoolean(req, "field2");
		int field3 = ParamUtil.getInteger(req, "field3");
		String field5 = ParamUtil.getString(req, "field5");

		int dateMonth = ParamUtil.getInteger(req, "field4Month");
		int dateDay = ParamUtil.getInteger(req, "field4Day");
		int dateYear = ParamUtil.getInteger(req, "field4Year");
		int dateHour = ParamUtil.getInteger(req, "field4Hour");
		int dateMinute = ParamUtil.getInteger(req, "field4Minute");
		int dateAmPm = ParamUtil.getInteger(req, "field4AmPm");

		if (dateAmPm == Calendar.PM) {
			dateHour += 12;
		}

		Date field4 = PortalUtil.getDate(
			dateMonth, dateDay, dateYear, dateHour, dateMinute,
			new PortalException());

		FooLocalServiceUtil.addFoo(field1, field2, field3, field4, field5);
	}

	protected void deleteFoo(ActionRequest req) throws Exception {
		long fooId = ParamUtil.getLong(req, "fooId");

		FooLocalServiceUtil.deleteFoo(fooId);
	}

}