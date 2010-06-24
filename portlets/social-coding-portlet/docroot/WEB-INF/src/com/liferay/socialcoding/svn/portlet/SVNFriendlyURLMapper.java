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

package com.liferay.socialcoding.svn.portlet;

import com.liferay.portal.kernel.portlet.BaseFriendlyURLMapper;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.LiferayWindowState;

import java.util.Map;

import javax.portlet.PortletMode;

/**
 * <a href="SVNFriendlyURLMapper.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class SVNFriendlyURLMapper extends BaseFriendlyURLMapper {

	public String buildPath(LiferayPortletURL liferayPortletURL) {
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

		int x = friendlyURLPath.indexOf("/", 1);

		int y = friendlyURLPath.indexOf("/", x + 1);

		if (y == -1) {
			return;
		}

		String rss = friendlyURLPath.substring(x + 1, y);

		if (!rss.equals("rss")) {
			return;
		}

		x = friendlyURLPath.indexOf("/", x + 1);

		if (x == -1) {
			return;
		}

		y = friendlyURLPath.indexOf("/", x + 1);

		if (y == -1) {
			return;
		}

		String type = friendlyURLPath.substring(x + 1, y);

		x = friendlyURLPath.indexOf("/", x + 1);

		if (x == -1) {
			return;
		}

		String url = friendlyURLPath.substring(x);

		if (!url.equals("/plugins/trunk") &&
			!url.equals("/portal/trunk")) {

			return;
		}

		addParameter(parameterMap, "p_p_id", _PORTLET_ID);
		addParameter(parameterMap, "p_p_lifecycle", "0");
		addParameter(parameterMap, "p_p_state", LiferayWindowState.EXCLUSIVE);
		addParameter(parameterMap, "p_p_mode", PortletMode.VIEW);

		addParameter(parameterMap, "url", url);
		addParameter(parameterMap, "all", type.equals("all"));
	}

	private static final String _MAPPING = "svn";

	private static final String _PORTLET_ID = "1_WAR_socialcodingportlet";

}