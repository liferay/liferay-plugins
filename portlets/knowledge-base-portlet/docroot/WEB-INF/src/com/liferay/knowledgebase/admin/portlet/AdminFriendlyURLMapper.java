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

import java.util.Map;

import javax.portlet.PortletMode;
import javax.portlet.WindowState;

/**
 * <a href="AdminFriendlyURLMapper.java.html"><b><i>View Source</i></b></a>
 *
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class AdminFriendlyURLMapper extends BaseFriendlyURLMapper {

	public String buildPath(LiferayPortletURL portletURL) {
		String friendlyURLPath = null;

		String jspPage = GetterUtil.getString(
			portletURL.getParameter("jspPage"));

		if (jspPage.equals("/admin/view_article.jsp")) {
			String resourcePrimKey = GetterUtil.getString(
				portletURL.getParameter("resourcePrimKey"));

			friendlyURLPath = "/admin/article/" + resourcePrimKey;

			portletURL.addParameterIncludedInPath("jspPage");
			portletURL.addParameterIncludedInPath("resourcePrimKey");
		}

		if (friendlyURLPath != null) {
			WindowState windowState = portletURL.getWindowState();

			if (!windowState.equals(WindowState.NORMAL)) {
				friendlyURLPath += StringPool.SLASH + windowState;
			}

			portletURL.addParameterIncludedInPath("p_p_id");
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
		String friendlyURLPath, Map<String, String[]> params) {

		addParam(params, "p_p_id", _PORTLET_ID);
		addParam(params, "p_p_lifecycle", "0");
		addParam(params, "p_p_mode", PortletMode.VIEW);

		String[] urlParams = StringUtil.split(
			friendlyURLPath, StringPool.SLASH);

		if (urlParams.length < 3) {
			return;
		}

		if (urlParams[2].equals("article")) {
			if (urlParams.length >= 4) {
				addParam(params, "jspPage", "/admin/view_article.jsp");
				addParam(params, "resourcePrimKey", urlParams[3]);
			}

			if (urlParams.length >= 5) {
				addParam(params, "p_p_state", urlParams[4]);
			}
		}
	}

	private static final String _MAPPING = "admin";

	private static final String _PORTLET_ID = "1_WAR_knowledgebaseportlet";

}