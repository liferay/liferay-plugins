/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.so.hook.events;

import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.struts.LastPath;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.so.service.SocialOfficeServiceUtil;
import com.liferay.so.util.PortletPropsValues;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Matthew Kong
 */
public class SOUserDefaultLandingPageAction extends Action {

	@Override
	public void run(HttpServletRequest request, HttpServletResponse response)
		throws ActionException {

		try {
			doRun(request, response);
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

	protected void doRun(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		String path = PortletPropsValues.SO_USER_DEFAULT_LANDING_PAGE_PATH;

		if (Validator.isNull(path)) {
			return;
		}

		HttpSession session = request.getSession();

		User user = (User)session.getAttribute(WebKeys.USER);

		if ((user == null) ||
			!SocialOfficeServiceUtil.isSocialOfficeGroup(user.getGroupId())) {

			return;
		}

		if (path.contains("${liferay:screenName}") ||
			path.contains("${liferay:userId}")) {

			path = StringUtil.replace(
				path,
				new String[] {"${liferay:screenName}", "${liferay:userId}"},
				new String[] {
					HtmlUtil.escapeURL(user.getScreenName()),
					String.valueOf(user.getUserId())
				});
		}

		LastPath lastPath = new LastPath(StringPool.BLANK, path);

		session.setAttribute(WebKeys.LAST_PATH, lastPath);
	}

}