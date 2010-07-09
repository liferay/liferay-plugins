/**
 * Copyright (c) 2008-2010 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.so.hook.events;

import com.liferay.portal.NoSuchLayoutSetException;
import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Brett Swaim
 *
 */
public class ServicePreAction extends Action {

	public void run(HttpServletRequest request, HttpServletResponse response)
		throws ActionException {

		try {
			doRun(request, response);
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}
	}

	protected void doRun(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		String currentURL = PortalUtil.getCurrentURL(request);

		if (isDisplayURL(themeDisplay, currentURL)) {

			// SOS-9

			long groupId = ParamUtil.getLong(request, "groupId");

			String redirect = getRedirect(themeDisplay, groupId);

			if (redirect == null) {
				redirect = ParamUtil.getString(request, "redirect");

				SessionErrors.add(
					request, NoSuchLayoutSetException.class.getName(),
					new NoSuchLayoutSetException(
						"{groupId=" + groupId + ", privateLayout=0}"));
			}

			response.sendRedirect(redirect);

			return;
		}

		if (!themeDisplay.isSignedIn()) {
			return;
		}

		Layout layout = themeDisplay.getLayout();

		if ((layout == null) || (layout.getLayoutId() != 1)) {
			return;
		}

		Group group = layout.getGroup();

		if (!group.getName().equals(GroupConstants.GUEST) ||
			currentURL.startsWith("/c/")) {

			return;
		}

		User user = themeDisplay.getUser();

		String redirect =
			themeDisplay.getPathFriendlyURLPublic() + "/" +
				user.getScreenName() + "/home";

		response.sendRedirect(redirect);
	}

	protected boolean isDisplayURL(
		ThemeDisplay themeDisplay, String currentURL) {

		String urlFragment1 =
			themeDisplay.getPathMain() + "/my_places/view?groupId=";

		if (!StringUtil.startsWith(currentURL, urlFragment1)) {
			return false;
		}

		String urlFragment2 = "&privateLayout=0";

		if (!StringUtil.endsWith(currentURL, urlFragment2)) {
			return false;
		}

		String s = currentURL;

		s = StringUtil.remove(s, urlFragment1, StringPool.BLANK);
		s = StringUtil.remove(s, urlFragment2, StringPool.BLANK);

		return Validator.isNumber(s);
	}

	protected String getRedirect(ThemeDisplay themeDisplay, long groupId)
		throws Exception {

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		if (group.isUser()) {
			long userId = group.getClassPK();

			User user = UserLocalServiceUtil.getUser(userId);

			return themeDisplay.getPathFriendlyURLPublic() + "/" +
				user.getScreenName() + "/profile";
		}

		List<Layout> layouts = LayoutLocalServiceUtil.getLayouts(
			groupId, false, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID, 0, 1);

		String redirect = null;

		if (layouts.size() > 0) {
			Layout layout = layouts.get(0);

			redirect = PortalUtil.getLayoutURL(layout, themeDisplay);
		}

		return redirect;
	}

	private static Log _log = LogFactoryUtil.getLog(ServicePreAction.class);

}