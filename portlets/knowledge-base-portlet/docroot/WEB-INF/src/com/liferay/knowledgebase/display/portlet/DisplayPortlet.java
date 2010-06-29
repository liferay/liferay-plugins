/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.knowledgebase.display.portlet;

import com.liferay.knowledgebase.aggregator.portlet.AggregatorPortlet;

import javax.portlet.ResourceRequest;

/**
 * <a href="DisplayPortlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class DisplayPortlet extends AggregatorPortlet {

	protected String getJspPath() {
		return _JSP_PATH;
	}

	protected boolean isServeRSSMaximized(ResourceRequest resourceRequest) {
		return _SERVE_RSS_MAXIMIZED;
	}

	private static final String _JSP_PATH = "/display/";

	private static final boolean _SERVE_RSS_MAXIMIZED = false;

}