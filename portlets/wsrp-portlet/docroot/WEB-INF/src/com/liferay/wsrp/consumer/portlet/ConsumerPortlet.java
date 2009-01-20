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

package com.liferay.wsrp.consumer.portlet;

import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import com.sun.portal.wsrp.consumer.portlets.AdminPortletAction;
import com.sun.portal.wsrp.consumer.portlets.WSRPConsumerAdminPortlet;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

/**
 * <a href="ConsumerPortlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class ConsumerPortlet extends WSRPConsumerAdminPortlet {

	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortletException {

		if (Validator.isNotNull(actionRequest.getParameter("action"))) {
			super.processAction(actionRequest, actionResponse);
		}

		int action = ParamUtil.getInteger(actionRequest, "action");

		if ((action == AdminPortletAction.CREATE) ||
			(action == AdminPortletAction.CREATE_CHANNEL) ||
			(action == AdminPortletAction.DELETE) ||
			(action == AdminPortletAction.DELETE_CHANNELS) ||
			(action == AdminPortletAction.UPDATE)) {

			if (actionRequest.getAttribute("CONSUMER_ADMIN_ERROR") != null) {
				setRenderParameters(actionRequest, actionResponse);

				return;
			}

			SessionMessages.add(actionRequest, "request_processed");

			String redirect = ParamUtil.getString(actionRequest, "redirect");

			if (Validator.isNotNull(redirect)) {
				try {
					actionResponse.sendRedirect(redirect);

					return;
				}
				catch (IOException ioe) {
					throw new PortletException(ioe);
				}
			}
		}

		setRenderParameters(actionRequest, actionResponse);
	}

	protected void setRenderParameters(
		ActionRequest actionRequest, ActionResponse actionResponse) {

		String redirect = ParamUtil.getString(actionRequest, "redirect");

		if (Validator.isNotNull(redirect)) {
			actionResponse.setRenderParameter("redirect", redirect);
		}
	}

}