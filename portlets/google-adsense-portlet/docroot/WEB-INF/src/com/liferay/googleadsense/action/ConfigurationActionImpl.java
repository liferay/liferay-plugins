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

package com.liferay.googleadsense.action;

import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.util.servlet.SessionMessages;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * <a href="ConfigurationActionImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class ConfigurationActionImpl implements ConfigurationAction {

	public void processAction(
			PortletConfig config, ActionRequest req, ActionResponse res)
		throws Exception {

		String cmd = ParamUtil.getString(req, Constants.CMD);

		if (!cmd.equals(Constants.UPDATE)) {
			return;
		}

		int adFormat = ParamUtil.getInteger(req, "adFormat");
		int adType = ParamUtil.getInteger(req, "adType");
		String adClient = ParamUtil.getString(req, "adClient");
		String adChannel = ParamUtil.getString(req, "adChannel");

		String colorBorder = ParamUtil.getString(req, "colorBorder");
		String colorBg = ParamUtil.getString(req, "colorBg");
		String colorLink = ParamUtil.getString(req, "colorLink");
		String colorText = ParamUtil.getString(req, "colorText");
		String colorUrl = ParamUtil.getString(req, "colorUrl");

		String portletResource = ParamUtil.getString(
			req, "portletResource");

		PortletPreferences prefs =
			PortletPreferencesFactoryUtil.getPortletSetup(
				req, portletResource);

		prefs.setValue("ad-format", String.valueOf(adFormat));
		prefs.setValue("ad-type", String.valueOf(adType));
		prefs.setValue("ad-client", adClient);
		prefs.setValue("ad-channel", adChannel);

		prefs.setValue("color-border", colorBorder);
		prefs.setValue("color-bg", colorBg);
		prefs.setValue("color-link", colorLink);
		prefs.setValue("color-text", colorText);
		prefs.setValue("color-url", colorUrl);

		prefs.store();

		SessionMessages.add(req, config.getPortletName() + ".doConfigure");
	}

	public String render(
			PortletConfig config, RenderRequest req, RenderResponse res)
		throws Exception {

		return "/configuration.jsp";
	}

}