/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.sync.filter;

import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.PortalUtil;
import com.liferay.sync.SyncClientMinBuildException;
import com.liferay.sync.SyncServicesUnavailableException;
import com.liferay.sync.util.PortletPropsKeys;
import com.liferay.sync.util.PortletPropsValues;
import com.liferay.sync.util.SyncUtil;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Shinn Lok
 */
public class SyncJSONFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(
			ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain)
		throws IOException, ServletException {

		String uri = (String)servletRequest.getAttribute(
			WebKeys.INVOKER_FILTER_URI);

		if (!uri.startsWith("/api/jsonws/sync-web.")) {
			filterChain.doFilter(servletRequest, servletResponse);

			return;
		}

		HttpServletRequest httpServletRequest =
			(HttpServletRequest)servletRequest;

		Throwable throwable = null;

		if (PrefsPropsUtil.getBoolean(
				PortalUtil.getCompanyId(httpServletRequest),
				PortletPropsKeys.SYNC_SERVICES_ENABLED,
				PortletPropsValues.SYNC_SERVICES_ENABLED)) {

			int build = httpServletRequest.getIntHeader("Sync-Build");

			int syncClientMinBuild = PrefsPropsUtil.getInteger(
				PortalUtil.getCompanyId(httpServletRequest),
				PortletPropsKeys.SYNC_CLIENT_MIN_BUILD,
				PortletPropsValues.SYNC_CLIENT_MIN_BUILD);

			if (syncClientMinBuild < _ABSOLUTE_MINIMUM_BUILD) {
				syncClientMinBuild = _ABSOLUTE_MINIMUM_BUILD;
			}

			if (build >= syncClientMinBuild) {
				filterChain.doFilter(servletRequest, servletResponse);

				return;
			}
			else {
				throwable = new SyncClientMinBuildException(
					"Sync client does not meet minimum build " +
						syncClientMinBuild);
			}
		}
		else {
			throwable = new SyncServicesUnavailableException();
		}

		servletResponse.setCharacterEncoding(StringPool.UTF8);
		servletResponse.setContentType(ContentTypes.APPLICATION_JSON);

		OutputStream outputStream = servletResponse.getOutputStream();

		String json = SyncUtil.buildExceptionMessage(throwable);

		json = "{\"exception\": \"" + json + "\"}";

		outputStream.write(json.getBytes(StringPool.UTF8));

		outputStream.close();
	}

	@Override
	public void init(FilterConfig filterConfig) {
	}

	private static final int _ABSOLUTE_MINIMUM_BUILD = 3009;

}