/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.testmisc.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.IOException;
import java.io.PrintWriter;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.filter.FilterChain;
import javax.portlet.filter.FilterConfig;
import javax.portlet.filter.RenderFilter;

/**
 * @author Brian Wing Shun Chan
 */
public class TestRenderFilter implements RenderFilter {

	public void init(FilterConfig filterConfig) {
		Log log = LogFactoryUtil.getLog(TestRenderFilter.class);

		log.info("Init");
	}

	public void doFilter(
			RenderRequest renderRequest, RenderResponse renderResponse,
			FilterChain filterChain)
		throws IOException, PortletException {

		Log log = LogFactoryUtil.getLog(TestRenderFilter.class);

		log.info("Before filter");

		TestRenderResponse testRenderResponse = new TestRenderResponse(
			renderResponse);

		filterChain.doFilter(renderRequest, testRenderResponse);

		PrintWriter printWriter = renderResponse.getWriter();

		printWriter.print(testRenderResponse.getString());

		log.info("After filter");
	}

	public void destroy() {
		Log log = LogFactoryUtil.getLog(TestRenderFilter.class);

		log.info("Destroy");
	}

}