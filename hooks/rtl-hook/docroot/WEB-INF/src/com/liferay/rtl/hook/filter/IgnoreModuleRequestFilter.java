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

package com.liferay.rtl.hook.filter;

import com.liferay.portal.util.PortalUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Raymond Augé
 * @author Eduardo Garcia
 * @see com.liferay.portal.servlet.filters.IgnoreModuleRequestFilter
 */
public abstract class IgnoreModuleRequestFilter extends BasePortalFilter {

	@Override
	public boolean isFilterEnabled(
		HttpServletRequest request, HttpServletResponse response) {

		if (isModuleRequest(request)) {
			return false;
		}

		return super.isFilterEnabled(request, response);
	}

	protected boolean isModuleRequest(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		String requestURI = request.getRequestURI();

		String resourcePath = requestURI;

		int index = requestURI.indexOf(contextPath);

		if (index == 0) {
			resourcePath = resourcePath.substring(contextPath.length());
		}

		if (resourcePath.startsWith(PortalUtil.getPathModule())) {
			return true;
		}

		return false;
	}

}