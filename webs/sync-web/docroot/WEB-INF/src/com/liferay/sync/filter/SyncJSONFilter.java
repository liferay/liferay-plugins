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

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.PortalUtil;
import com.liferay.sync.SyncServicesUnavailableException;
import com.liferay.sync.util.PortletPropsKeys;
import com.liferay.sync.util.PortletPropsValues;

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

		if (PrefsPropsUtil.getBoolean(
				PortalUtil.getCompanyId(httpServletRequest),
				PortletPropsKeys.SYNC_SERVICES_ENABLED,
				PortletPropsValues.SYNC_SERVICES_ENABLED)) {

			filterChain.doFilter(servletRequest, servletResponse);

			return;
		}

		servletResponse.setCharacterEncoding(StringPool.UTF8);
		servletResponse.setContentType(ContentTypes.APPLICATION_JSON);

		OutputStream outputStream = servletResponse.getOutputStream();

		String json = JSONFactoryUtil.serializeThrowable(
			new SyncServicesUnavailableException(
				SyncServicesUnavailableException.class.getName()));

		outputStream.write(json.getBytes(StringPool.UTF8));

		outputStream.close();
	}

	@Override
	public void init(FilterConfig filterConfig) {
	}

}