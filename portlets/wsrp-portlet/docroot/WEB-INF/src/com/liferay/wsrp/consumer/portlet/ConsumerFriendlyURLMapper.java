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

package com.liferay.wsrp.consumer.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.BaseFriendlyURLMapper;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.util.PortalUtil;

import java.util.Map;

import javax.portlet.PortletMode;
import javax.portlet.WindowState;

/**
 * @author Michael Young
 */
public class ConsumerFriendlyURLMapper extends BaseFriendlyURLMapper {

	public String buildPath(LiferayPortletURL liferayPortletURL) {
		StringBuilder sb = new StringBuilder();

		sb.append("/consumer");

		addPathElement(sb, liferayPortletURL.getPortletId());

		liferayPortletURL.addParameterIncludedInPath("p_p_id");

		WindowState windowState = liferayPortletURL.getWindowState();

		if (windowState != null) {
			addPathElement(sb, windowState.toString());
		}
		else {
			addPathElement(sb, null);
		}

		liferayPortletURL.addParameterIncludedInPath("p_p_state");

		PortletMode portletMode = liferayPortletURL.getPortletMode();

		if (portletMode != null) {
			addPathElement(sb, portletMode.toString());
		}
		else {
			addPathElement(sb, null);
		}

		liferayPortletURL.addParameterIncludedInPath("p_p_mode");

		addPathElement(sb, liferayPortletURL.getCacheability());

		liferayPortletURL.addParameterIncludedInPath("p_p_cacheability");

		Map<String, String[]> parameterMap =
			liferayPortletURL.getParameterMap();

		String[] navigationalState = parameterMap.get("wsrp-navigationalState");

		if ((navigationalState == null) || (navigationalState.length <= 0)) {
			navigationalState = new String[] {null};
		}

		addPathElement(sb, navigationalState[0]);

		liferayPortletURL.addParameterIncludedInPath("wsrp-navigationalState");

		return sb.toString();
	}

	@Override
	public String getMapping() {
		return _MAPPING;
	}

	@Override
	public String getPortletId() {
		return null;
	}

	public void populateParams(
		String friendlyURLPath, Map<String, String[]> parameterMap,
		Map<String, Object> requestContext) {

		int pos1 = friendlyURLPath.indexOf("/", 1);
		int pos2 = friendlyURLPath.indexOf("/", pos1 + 1);
		int pos3 = friendlyURLPath.indexOf("/", pos2 + 1);
		int pos4 = friendlyURLPath.indexOf("/", pos3 + 1);
		int pos5 = friendlyURLPath.indexOf("/", pos4 + 1);

		String portletId = friendlyURLPath.substring(pos1 + 1, pos2);

		addParameter(parameterMap, "p_p_id", portletId);
		addParameter(
			parameterMap, "p_p_state",
			friendlyURLPath.substring(pos2 + 1, pos3));
		addParameter(
			parameterMap, "p_p_mode",
			friendlyURLPath.substring(pos3 + 1, pos4));
		addParameter(
			parameterMap, "p_p_cacheability",
			friendlyURLPath.substring(pos4 + 1, pos5));

		String name =
			PortalUtil.getPortletNamespace(portletId) +
				"wsrp-navigationalState";

		addParameter(parameterMap, name, friendlyURLPath.substring(pos5 + 1));
	}

	protected void addPathElement(StringBuilder sb, String value) {
		sb.append(StringPool.SLASH);
		sb.append(GetterUtil.get(HttpUtil.encodeURL(value), StringPool.DASH));
	}

	@Override
	protected void addParameter(
		Map<String, String[]> parameterMap, String name, String value) {

		if (value.equals(StringPool.DASH)) {
			return;
		}

		try {
			parameterMap.put(name, new String[] {value});
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static final String _MAPPING = "consumer";

	private static Log _log = LogFactoryUtil.getLog(
		ConsumerFriendlyURLMapper.class);

}