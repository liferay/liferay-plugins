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

package com.liferay.kb.knowledgebaseaggregator.action;

import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
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
 * @author Peter Shin
 *
 */
public class ConfigurationActionImpl implements ConfigurationAction {

	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String tabs2 = ParamUtil.getString(actionRequest, "tabs2");

		String portletResource = ParamUtil.getString(
			actionRequest, "portletResource");

		PortletPreferences prefs =
			PortletPreferencesFactoryUtil.getPortletSetup(
				actionRequest, portletResource);

		if (tabs2.equals("display-settings")) {
			updateDisplaySettings(actionRequest, prefs);
		}
		else if (tabs2.equals("rss")) {
			updateRSS(actionRequest, prefs);
		}

		prefs.store();

		SessionMessages.add(
			actionRequest, portletConfig.getPortletName() + ".doConfigure");
	}

	public String render(
			PortletConfig portletConfig, RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws Exception {

		return "/knowledge_base_aggregator/configuration.jsp";
	}

	protected void updateDisplaySettings(
			ActionRequest actionRequest, PortletPreferences prefs)
		throws Exception {

		String groupId = ParamUtil.getString(actionRequest, "groupId");
		String companyId = ParamUtil.getString(actionRequest, "companyId");
		String displayStyle = ParamUtil.getString(
			actionRequest, "displayStyle");
		int maxItems = ParamUtil.getInteger(actionRequest, "maxItems");

		prefs.setValue("company-id", companyId);
		prefs.setValue("group-id", groupId);
		prefs.setValue("display-style", displayStyle);
		prefs.setValue("max-items", String.valueOf(maxItems));
	}

	protected void updateRSS(
			ActionRequest actionRequest, PortletPreferences prefs)
		throws Exception {

		String[] rssTypes = actionRequest.getParameterValues("rssTypes");
		int rssMaxItems = ParamUtil.getInteger(actionRequest, "rssMaxItems");
		String rssDisplayStyle = ParamUtil.getString(
			actionRequest, "rssDisplayStyle");
		int rssAbstractLength = ParamUtil.getInteger(
			actionRequest, "rssAbstractLength");

		prefs.setValues("rss-types", rssTypes);
		prefs.setValue("rss-max-items", String.valueOf(rssMaxItems));
		prefs.setValue("rss-display-style", rssDisplayStyle);
		prefs.setValue(
			"rss-abstract-length", String.valueOf(rssAbstractLength));
	}

}