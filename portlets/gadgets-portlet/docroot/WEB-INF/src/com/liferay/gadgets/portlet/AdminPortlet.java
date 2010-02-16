/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.gadgets.portlet;

import com.liferay.gadgets.service.GadgetsEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.bridges.mvc.MVCPortlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;

/**
 * <a href="AdminPortlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael Young
 */
public class AdminPortlet extends MVCPortlet {

	public void deleteGadgetsEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		checkPermissions(actionRequest);

		long gadgetsEntryId = ParamUtil.getLong(
			actionRequest, "gadgetsEntryId");

		GadgetsEntryLocalServiceUtil.deleteGadgetsEntry(gadgetsEntryId);
	}

	public void updateGadgetsEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		checkPermissions(actionRequest);

		try {
			doUpdateGadgetsEntry(actionRequest, actionResponse);
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

	protected void doUpdateGadgetsEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long gadgetsEntryId = ParamUtil.getLong(
			actionRequest, "gadgetsEntryId");

		String name = ParamUtil.getString(actionRequest, "name");
		String url = ParamUtil.getString(actionRequest, "url");
		String xml = ParamUtil.getString(actionRequest, "xml");

		if (gadgetsEntryId <= 0) {
			GadgetsEntryLocalServiceUtil.addGadgetsEntry(
				themeDisplay.getCompanyId(), name, url, xml);
		}
		else {
			GadgetsEntryLocalServiceUtil.updateGadgetsEntry(
				gadgetsEntryId, name, xml);
		}
	}

}