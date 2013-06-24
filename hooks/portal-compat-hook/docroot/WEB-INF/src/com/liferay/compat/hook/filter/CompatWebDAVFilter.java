/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.compat.hook.filter;

import com.liferay.compat.portal.kernel.webdav.WebDAVUtil;
import com.liferay.portal.kernel.util.HttpUtil;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class CompatWebDAVFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(
			ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain)
		throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest)servletRequest;

		String pathInfo = HttpUtil.fixPath(request.getPathInfo(), false, true);

		String strippedPathInfo = WebDAVUtil.stripManualCheckInRequiredPath(
			pathInfo);

		if (strippedPathInfo.length() != pathInfo.length()) {
			pathInfo = strippedPathInfo;

			try {
				CompatWebDAVThreadLocal.setManualCheckInRequired(true);

				filterChain.doFilter(
					new CompatHttpServletRequest(request, pathInfo),
					servletResponse);
			}
			finally {
				CompatWebDAVThreadLocal.setManualCheckInRequired(false);
			}
		}
		else {
			filterChain.doFilter(servletRequest, servletResponse);
		}
	}

	public void init(FilterConfig filterConfig) {
	}

}