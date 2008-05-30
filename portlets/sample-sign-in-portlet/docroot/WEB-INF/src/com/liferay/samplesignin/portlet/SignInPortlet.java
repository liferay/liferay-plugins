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

package com.liferay.samplesignin.portlet;

import com.liferay.portal.kernel.portlet.NoRedirectActionResponse;
import com.liferay.portal.kernel.struts.PortletActionInvoker;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.bridges.jsp.JSPPortlet;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <a href="SignInPortlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class SignInPortlet extends JSPPortlet {

	public void processAction(ActionRequest req, ActionResponse res)
		throws IOException, PortletException {

		String className = "com.liferay.portlet.login.action.ViewAction";
		PortletConfig config = getPortletConfig();
		NoRedirectActionResponse noRedirectRes =
			new NoRedirectActionResponse(res);

		try {
			PortletActionInvoker.processAction(
				className, config, req, noRedirectRes);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		String login = ParamUtil.getString(req, "login");
		String password = ParamUtil.getString(req, "password");
		String rememberMe = ParamUtil.getString(req, "rememberMe");

		if (Validator.isNull(noRedirectRes.getRedirectLocation())) {
			res.setRenderParameter("login", login);
			res.setRenderParameter("rememberMe", rememberMe);
		}
		else {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)req.getAttribute(WebKeys.THEME_DISPLAY);

			String redirect =
				themeDisplay.getPathMain() +
					"/portal/login?cmd=already-registered&login=" + login +
						"&password=" + password;

			res.sendRedirect(redirect);
		}
	}

	private static Log _log = LogFactory.getLog(SignInPortlet.class);

}