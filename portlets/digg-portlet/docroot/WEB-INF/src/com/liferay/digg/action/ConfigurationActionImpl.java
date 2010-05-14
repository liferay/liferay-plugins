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

package com.liferay.digg.action;

import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.Constants;
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
 * @author David Truong
 *
 */
public class ConfigurationActionImpl implements ConfigurationAction {

	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		if (!cmd.equals(Constants.UPDATE)) {
			return;
		}

		boolean fallback = ParamUtil.getBoolean(actionRequest, "fallback");
		boolean newWindow = ParamUtil.getBoolean(actionRequest, "newWindow");
		boolean showDiggs = ParamUtil.getBoolean(actionRequest, "showDiggs");
		boolean showDesc = ParamUtil.getBoolean(actionRequest, "showDesc");
		boolean showFooter = ParamUtil.getBoolean(actionRequest, "showFooter");
		boolean showThumbs = ParamUtil.getBoolean(actionRequest, "showThumbs");

		String apiSearch = ParamUtil.getString(actionRequest, "apiSearch");
		String friendsUsername = ParamUtil.getString(actionRequest, "friendsUsername");
		String height = ParamUtil.getString(actionRequest, "height");
		String minDate = ParamUtil.getString(actionRequest, "minDate");
		String newsFriends = ParamUtil.getString(actionRequest, "newsFriends");
		String newsFront = ParamUtil.getString(actionRequest, "newsFront");
		String newsTop = ParamUtil.getString(actionRequest, "newsTop");
		String newsType = ParamUtil.getString(actionRequest, "newsType");
		String newsUser = ParamUtil.getString(actionRequest, "newsUser");
		String searchSort = ParamUtil.getString(actionRequest, "searchSort");
		String searchTopics = ParamUtil.getString(actionRequest, "searchTopics");
		String sourcePopOrUp = ParamUtil.getString(actionRequest, "sourcePopOrUp");
		String storyCount = ParamUtil.getString(actionRequest, "storyCount");
		String url = ParamUtil.getString(actionRequest, "url");
		String urlSort = ParamUtil.getString(actionRequest, "urlSort");
		String userUsername = ParamUtil.getString(actionRequest, "userUsername");
		String width = ParamUtil.getString(actionRequest, "width");

		String portletResource = ParamUtil.getString(actionRequest, "portletResource");

		PortletPreferences preferences = PortletPreferencesFactoryUtil.getPortletSetup(actionRequest, portletResource);

		preferences.setValue("fallback", String.valueOf(fallback));
		preferences.setValue("newWindow", String.valueOf(newWindow));
		preferences.setValue("showDiggs", String.valueOf(showDiggs));
		preferences.setValue("showDesc", String.valueOf(showDesc));
		preferences.setValue("showFooter", String.valueOf(showFooter));
		preferences.setValue("showThumbs", String.valueOf(showThumbs));

		preferences.setValue("apiSearch", apiSearch);
		preferences.setValue("friendsUsername", friendsUsername);
		preferences.setValue("height", height);
		preferences.setValue("minDate", minDate);
		preferences.setValue("newsFriends", newsFriends);
		preferences.setValue("newsFront", newsFront);
		preferences.setValue("newsTop", newsTop);
		preferences.setValue("newsType", newsType);
		preferences.setValue("newsUser", newsUser);
		preferences.setValue("searchSort", searchSort);
		preferences.setValue("searchTopics", searchTopics);
		preferences.setValue("sourcePopOrUp", sourcePopOrUp);
		preferences.setValue("storyCount", storyCount);
		preferences.setValue("url", url);
		preferences.setValue("urlSort", urlSort);
		preferences.setValue("userUsername", userUsername);
		preferences.setValue("width", width);

		preferences.store();

		SessionMessages.add(actionRequest, portletConfig.getPortletName() + ".doConfigure");
	}

	public String render(
			PortletConfig portletConfig, RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws Exception {

		return "/configuration.jsp";
	}

}