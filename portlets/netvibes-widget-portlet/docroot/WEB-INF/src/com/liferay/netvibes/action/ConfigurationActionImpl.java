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

package com.liferay.netvibes.action;

import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * <a href="ConfigurationActionImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 * @author Julio Camarero
 *
 */
public class ConfigurationActionImpl implements ConfigurationAction {

	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		if (cmd.equals(Constants.SEARCH)) {
			return;
		}
		else if (cmd.equals(Constants.UPDATE)) {
			String widgetUrl = ParamUtil.getString(actionRequest, "widgetUrl");
			String widgetTitle = ParamUtil.getString(
				actionRequest, "widgetTitle");
			String widgetAuthor = ParamUtil.getString(
				actionRequest, "widgetAuthor");
			String widgetSummary = ParamUtil.getString(
				actionRequest, "widgetSummary");
			String widgetThumbnail = ParamUtil.getString(
				actionRequest, "widgetThumbnail");

			savePreferences(
				portletConfig, actionRequest, actionResponse,widgetUrl,
				widgetTitle, widgetAuthor, widgetSummary, widgetThumbnail);
		}
		else if (cmd.equals(Constants.ADD)) {
			String widgetUrl = ParamUtil.getString(actionRequest, "addWidget");
			String widgetTitle = "Netvibes widget";
			String widgetThumbnail =
				"http://eco.netvibes.com/img/thumbnail/nopreview-160-120.png";

			savePreferences(portletConfig, actionRequest, actionResponse,
				widgetUrl, widgetTitle, StringPool.BLANK, StringPool.BLANK,
				widgetThumbnail);
		}
	}

	public String render(
			PortletConfig portletConfig, RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws Exception {

		return "/configuration.jsp";
	}

	protected static void savePreferences(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse, String widgetUrl, String widgetTitle,
			String widgetAuthor, String widgetSummary, String widgetThumbnail)
		throws Exception{

		String portletResource = ParamUtil.getString(
			actionRequest, "portletResource");

		PortletPreferences preferences =
			PortletPreferencesFactoryUtil.getPortletSetup(
				actionRequest, portletResource);

		preferences.setValue("widgetUrl", widgetUrl);
		preferences.setValue("widgetTitle", widgetTitle);
		preferences.setValue("widgetAuthor", widgetAuthor);
		preferences.setValue("widgetSummary", widgetSummary);
		preferences.setValue("widgetThumbnail", widgetThumbnail);

		preferences.store();

		SessionMessages.add(
			actionRequest, portletConfig.getPortletName() + ".doConfigure");
	}

}