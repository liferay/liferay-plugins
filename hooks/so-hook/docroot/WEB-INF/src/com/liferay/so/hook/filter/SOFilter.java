/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Sherry Yang
 */

public class SOFilter implements Filter {

	@Override
	public void destroy() {

		if (_log.isInfoEnabled()) {
			_log.info("called SOFilter destroy()");
		}
	}

	@Override
	public void doFilter(
			ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain)
		throws IOException, ServletException {

		try {
			HttpServletRequest request = (HttpServletRequest)servletRequest;

			long companyId = PortalUtil.getCompanyId(request);

			String path = PrefsPropsUtil.getString(
				companyId, PropsKeys.DEFAULT_LANDING_PAGE_PATH);

			String homeURL = PortalUtil.getRelativeHomeURL(request);

			if (!homeURL.equals("/user") && (!path.equals("/user"))) {
				filterChain.doFilter(servletRequest, servletResponse);
				return;
			}

			User user = PortalUtil.initUser(request);

			if (!user.hasPrivateLayouts()) {
				filterChain.doFilter(servletRequest, servletResponse);
				return;
			}

			Boolean secure = PortalUtil.isSecure(request);

			String portalURL = PortalUtil.getPortalURL(request, secure);

			String redirect = user.getDisplayURL(
				portalURL, PortalUtil.getPathMain(), Boolean.TRUE);

			HttpServletResponse response = (HttpServletResponse)servletResponse;

			response.sendRedirect(redirect.toString());
		}
		catch (Exception e) {
			filterChain.doFilter(servletRequest, servletResponse);

			if (_log.isDebugEnabled())
			_log.debug(e, e);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		if (_log.isInfoEnabled()) {
			_log.info("called SOFilter init()");
		}
	}

	private static Log _log = LogFactoryUtil.getLog(SOFilter.class);

}