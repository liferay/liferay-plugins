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

package com.liferay.testmisc.portlet;

import com.liferay.portal.kernel.portlet.BaseFriendlyURLMapper;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.Map;

import javax.portlet.PortletMode;
import javax.portlet.PortletRequest;
import javax.portlet.WindowState;

/**
 * <a href="TestFriendlyURLMapper.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class TestFriendlyURLMapper extends BaseFriendlyURLMapper {

	public String buildPath(LiferayPortletURL liferayPortletURL) {
		if (liferayPortletURL.getLifecycle().equals(
				PortletRequest.ACTION_PHASE)) {

			return null;
		}

		String resourceID = liferayPortletURL.getResourceID();

		if (Validator.isNotNull(resourceID)) {
			liferayPortletURL.addParameterIncludedInPath("p_p_id");
			liferayPortletURL.addParameterIncludedInPath("p_p_lifecycle");
			liferayPortletURL.addParameterIncludedInPath("p_p_resource_id");
			liferayPortletURL.addParameterIncludedInPath("p_p_cacheability");

			StringBuilder sb = new StringBuilder();

			sb.append(StringPool.SLASH);
			sb.append(_MAPPING);
			sb.append(StringPool.SLASH);
			sb.append(resourceID);

			return sb.toString();
		}

		WindowState windowState = liferayPortletURL.getWindowState();

		String jspPage = GetterUtil.getString(
			liferayPortletURL.getParameter("jspPage"));

		if ((windowState == null) || (windowState.equals(WindowState.NORMAL)) ||
			(Validator.isNotNull(jspPage))) {

			liferayPortletURL.addParameterIncludedInPath("p_p_id");

			liferayPortletURL.addParameterIncludedInPath("jspPage");

			StringBuilder sb = new StringBuilder();

			sb.append(StringPool.SLASH);
			sb.append(_MAPPING);
			sb.append(jspPage);

			return sb.toString();
		}

		return null;
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

		int x = friendlyURLPath.indexOf("/", 1);

		if ((x + 1) == friendlyURLPath.length()) {
			return;
		}

		String resourceID = friendlyURLPath.substring(x);

		if (resourceID.equals("/logo.png")) {
			addParameter(parameterMap, "p_p_lifecycle", "2");
			addParameter(
				parameterMap, "p_p_resource_id", resourceID.substring(1));

			return;
		}

		String jspPage = resourceID;

		addParameter(parameterMap, "jspPage", jspPage);
	}

	private static final String _MAPPING = "test_misc";

	private static final String _PORTLET_ID = "1_WAR_testmiscportlet";

}