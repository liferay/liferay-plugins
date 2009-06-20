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

package com.liferay.wsrp.portlet;

import com.liferay.portal.PortalException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.liferay.wsrp.service.WSRPConsumerLocalServiceUtil;
import com.liferay.wsrp.service.WSRPConsumerPortletLocalServiceUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;

/**
 * <a href="AdminPortlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class AdminPortlet extends MVCPortlet {

	public void deleteWSRPConsumer(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		checkPermissions(actionRequest);

		long wsrpConsumerId = ParamUtil.getLong(
			actionRequest, "wsrpConsumerId");

		WSRPConsumerLocalServiceUtil.deleteWSRPConsumer(wsrpConsumerId);
	}

	public void deleteWSRPConsumerPortlet(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		checkPermissions(actionRequest);

		long wsrpConsumerPortletId = ParamUtil.getLong(
			actionRequest, "wsrpConsumerPortletId");

		WSRPConsumerPortletLocalServiceUtil.deleteWSRPConsumerPortlet(
			wsrpConsumerPortletId);
	}

	public void updateWSRPConsumer(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		checkPermissions(actionRequest);

		try {
			doUpdateWSRPConsumer(actionRequest, actionResponse);
		}
		catch (PortalException pe) {
			SessionErrors.add(actionRequest, pe.getClass().getName());
		}
	}

	public void updateWSRPConsumerPortlet(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		checkPermissions(actionRequest);

		try {
			doUpdateWSRPConsumerPortlet(actionRequest, actionResponse);
		}
		catch (PortalException pe) {
			SessionErrors.add(actionRequest, pe.getClass().getName());
		}
	}

	protected void checkPermissions(PortletRequest portletRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		if (!permissionChecker.isCompanyAdmin()) {
			throw new PrincipalException();
		}
	}

	protected void doUpdateWSRPConsumer(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long wsrpConsumerId = ParamUtil.getLong(
			actionRequest, "wsrpConsumerId");

		String name = ParamUtil.getString(actionRequest, "name");
		String url = ParamUtil.getString(actionRequest, "url");

		if (wsrpConsumerId <= 0) {
			WSRPConsumerLocalServiceUtil.addWSRPConsumer(name, url);
		}
		else {
			WSRPConsumerLocalServiceUtil.updateWSRPConsumer(
				wsrpConsumerId, name, url);
		}
	}

	protected void doUpdateWSRPConsumerPortlet(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long wsrpConsumerPortletId = ParamUtil.getLong(
			actionRequest, "wsrpConsumerPortletId");

		long wsrpConsumerId = ParamUtil.getLong(
			actionRequest, "wsrpConsumerId");
		String name = ParamUtil.getString(actionRequest, "name");
		String portletHandle = ParamUtil.getString(
			actionRequest, "portletHandle");

		if (wsrpConsumerPortletId <= 0) {
			WSRPConsumerPortletLocalServiceUtil.addWSRPConsumerPortlet(
				wsrpConsumerId, name, portletHandle);
		}
		else {
			WSRPConsumerPortletLocalServiceUtil.updateWSRPConsumerPortlet(
				wsrpConsumerPortletId, name);
		}
	}

}