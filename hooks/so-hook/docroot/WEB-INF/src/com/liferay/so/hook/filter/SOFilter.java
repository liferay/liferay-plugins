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

package com.liferay.so.hook.filter;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.servlet.BaseFilter;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.so.util.PortletPropsValues;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Sherry Yang
 */
public class SOFilter extends BaseFilter {

	@Override
	public boolean isFilterEnabled(
		HttpServletRequest request, HttpServletResponse response) {

		try {
			User user = PortalUtil.getUser(request);

			if (user == null) {
				return false;
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}

			return false;
		}

		String uri = request.getRequestURI();

		if (uri.equals("/user") ||
			uri.equals(PortletPropsValues.LOGIN_REDIRECT)) {

			return true;
		}

		return false;
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	@Override
	protected void processFilter(
			HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
		throws Exception {

		try {
			User user = PortalUtil.getUser(request);

			if ((user == null) || !user.hasPrivateLayouts()) {
				filterChain.doFilter(request, response);

				return;
			}

			String portalURL = PortalUtil.getPortalURL(
				request, PortalUtil.isSecure(request));

			String redirect = user.getDisplayURL(
				portalURL, PortalUtil.getPathMain(), true);

			response.sendRedirect(redirect);
		}
		catch (Exception e) {
			filterChain.doFilter(request, response);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(SOFilter.class);

}