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

package com.liferay.knowledgebase.search.action;

import com.liferay.knowledgebase.util.PortletKeys;

/**
 * <a href="ConfigurationActionImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class ConfigurationActionImpl
	extends com.liferay.knowledgebase.aggregator.action.ConfigurationActionImpl {

	protected String getJspPath() {
		return _JSP_PATH;
	}

	protected String getRootPortletId() {
		return _ROOT_PORTLET_ID;
	}

	private static final String _JSP_PATH = "/search/";

	private static final String _ROOT_PORTLET_ID =
		PortletKeys.KNOWLEDGE_BASE_SEARCH;

}