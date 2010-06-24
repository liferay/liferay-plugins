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

package com.liferay.knowledgebase.admin.portlet;

import com.liferay.portal.kernel.portlet.BaseFriendlyURLMapper;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PortletKeys;

import java.util.Map;

import javax.portlet.PortletMode;
import javax.portlet.ResourceURL;
import javax.portlet.WindowState;

/**
 * <a href="AdminFriendlyURLMapper.java.html"><b><i>View Source</i></b></a>
 *
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class AdminFriendlyURLMapper extends BaseFriendlyURLMapper {

	public String buildPath(LiferayPortletURL liferayPortletURL) {
		String friendlyURLPath = null;

		String resourceID = GetterUtil.getString(
			liferayPortletURL.getResourceID());

		String jspPage = GetterUtil.getString(
			liferayPortletURL.getParameter("jspPage"));

		if (resourceID.equals("rss")) {
			friendlyURLPath = "/knowledge_base/rss";

			liferayPortletURL.addParameterIncludedInPath("p_p_cacheability");
			liferayPortletURL.addParameterIncludedInPath("p_p_lifecycle");
			liferayPortletURL.addParameterIncludedInPath("p_p_resource_id");
		}
		else if (jspPage.equals("/admin/view_article.jsp")) {
			String resourcePrimKey = GetterUtil.getString(
				liferayPortletURL.getParameter("resourcePrimKey"));

			friendlyURLPath = "/knowledge_base/article/" + resourcePrimKey;

			WindowState windowState = liferayPortletURL.getWindowState();

			if (!Validator.equals(windowState, WindowState.NORMAL)) {
				friendlyURLPath += StringPool.SLASH + windowState;
			}

			liferayPortletURL.addParameterIncludedInPath("jspPage");
			liferayPortletURL.addParameterIncludedInPath("resourcePrimKey");
		}

		if (friendlyURLPath != null) {
			liferayPortletURL.addParameterIncludedInPath("p_p_id");
		}

		return friendlyURLPath;
	}

	public String getMapping() {
		return _MAPPING;
	}

	public String getPortletId() {
		return _PORTLET_ID;
	}

	public void populateParams(
		String friendlyURLPath, Map<String, String[]> parameterMap,
		Map<String, Object> requestContext) {

		addParameter(parameterMap, "p_p_id", _PORTLET_ID);
		addParameter(parameterMap, "p_p_lifecycle", "0");
		addParameter(parameterMap, "p_p_mode", PortletMode.VIEW);

		String[] urlParams = StringUtil.split(
			friendlyURLPath, StringPool.SLASH);

		if (urlParams.length < 3) {
			return;
		}

		if (urlParams[2].equals("rss")) {
			addParameter(parameterMap, "p_p_cacheability", ResourceURL.PAGE);
			addParameter(parameterMap, "p_p_lifecycle", "2");
			addParameter(parameterMap, "p_p_resource_id", "rss");
		}
		else if (urlParams[2].equals("article")) {
			if (urlParams.length >= 4) {
				addParameter(
					parameterMap, "jspPage", "/admin/view_article.jsp");
				addParameter(parameterMap, "resourcePrimKey", urlParams[3]);
			}

			if (urlParams.length >= 5) {
				addParameter(parameterMap, "p_p_state", urlParams[4]);
			}
		}
	}

	private static final String _MAPPING = "knowledge_base";

	private static final String _PORTLET_ID = PortletKeys.KNOWLEDGE_BASE_ADMIN;

}