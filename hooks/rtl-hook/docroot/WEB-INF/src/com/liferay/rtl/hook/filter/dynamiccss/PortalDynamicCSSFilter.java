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

package com.liferay.rtl.hook.filter.dynamiccss;

import com.liferay.portal.kernel.servlet.ServletContextPool;
import com.liferay.portal.util.PortalUtil;

import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;

/**
 * @author Eduardo Lundgren
 * @author Raymond Augé
 * @author Eduardo Garcia
 * @see com.liferay.rtl.hook.filter.dynamiccss.DynamicCSSFilter
 */
public class PortalDynamicCSSFilter extends DynamicCSSFilter {

	@Override
	public void init(FilterConfig filterConfig) {
		ServletContext servletContext = ServletContextPool.get(
			PortalUtil.getServletContextName());

		super.doInit(filterConfig, servletContext);
	}

}