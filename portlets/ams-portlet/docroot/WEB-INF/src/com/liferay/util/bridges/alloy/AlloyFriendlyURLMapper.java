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

package com.liferay.util.bridges.alloy;

import com.liferay.portal.kernel.portlet.BaseFriendlyURLMapper;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletMode;
import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * <a href="AlloyFriendlyURLMapper.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class AlloyFriendlyURLMapper extends BaseFriendlyURLMapper {

	public String buildPath(LiferayPortletURL portletURL) {
		portletURL.addParameterIncludedInPath("p_p_id");
		portletURL.addParameterIncludedInPath("p_p_lifecycle");

		// Prepare router parameters

		Map<String, String[]> portletURLParameters =
			portletURL.getParameterMap();

		if (portletURLParameters.isEmpty()) {
			return StringPool.SLASH.concat(_MAPPING);
		}

		Map<String, String[]> parameters = new HashMap<String, String[]>(
			portletURL.getParameterMap());

		String lifecycle = portletURL.getLifecycle();

		if (lifecycle.equals(PortletRequest.ACTION_PHASE)) {
			parameters.put("method", new String[] {HttpMethods.POST});
		}
		else {
			parameters.put("method", new String[] {HttpMethods.GET});
		}

		// Map URL with router

		String url = null;

		try {
			url = router.parametersToUrl(parameters);
		}
		catch (Exception e) {
			return StringPool.SLASH.concat(_MAPPING);
		}

		// Remove mapped parameters from URL

		Map<String, String[]> extraParameters = HttpUtil.parameterMapFromString(
			HttpUtil.getQueryString(url));

		for (String name : portletURLParameters.keySet()) {
			if (!extraParameters.containsKey(name)) {
				portletURL.addParameterIncludedInPath(name);
			}
		}

		String friendlyURLPath = url;

		// Remove method

		int pos = friendlyURLPath.indexOf(StringPool.SLASH);

		friendlyURLPath = friendlyURLPath.substring(pos);

		// Add mapping

		friendlyURLPath = StringPool.SLASH.concat(_MAPPING).concat(
			friendlyURLPath);

		// Remove query string

		pos = friendlyURLPath.indexOf(StringPool.QUESTION);

		if (pos != -1) {
			friendlyURLPath = friendlyURLPath.substring(0, pos);
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

		HttpServletRequest request = (HttpServletRequest)requestContext.get(
			"request");

		String method = request.getMethod();

		addParameter(parameterMap, "p_p_id", _PORTLET_ID);
		addParameter(parameterMap, "p_p_lifecycle", getLifecycle(method));
		addParameter(parameterMap, "p_p_mode", PortletMode.VIEW);

		Map<String, String> routeParameters = router.urlToParameters(
			method + friendlyURLPath.substring(_MAPPING.length() + 1));

		for (Map.Entry<String, String> entry : routeParameters.entrySet()) {
			String name = entry.getKey();
			String value = entry.getValue();

			addParameter(parameterMap, name, value);
		}
	}

	protected String getLifecycle(String method) {
		if (method.equalsIgnoreCase(HttpMethods.POST)) {
			return "1";
		}
		else {
			return "0";
		}
	}

	private static final String _MAPPING = "ams";

	private static final String _PORTLET_ID = "1_WAR_amsportlet";

}