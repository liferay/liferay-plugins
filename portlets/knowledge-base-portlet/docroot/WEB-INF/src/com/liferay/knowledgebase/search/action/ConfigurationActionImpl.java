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

import com.liferay.knowledgebase.base.action.BaseAction;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.PortletPreferences;

/**
 * <a href="ConfigurationActionImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class ConfigurationActionImpl extends BaseAction {

	protected String getJspPath() {
		return _JSP_PATH;
	}

	protected void postProcessDisplaySettings(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		int articlesDelta = ParamUtil.getInteger(
			actionRequest, "articlesDelta");

		preferences.setValue("articles-delta", String.valueOf(articlesDelta));
	}

	private static final String _JSP_PATH = "/search/";

}