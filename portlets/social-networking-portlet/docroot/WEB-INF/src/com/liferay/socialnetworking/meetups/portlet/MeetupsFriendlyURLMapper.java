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

package com.liferay.socialnetworking.meetups.portlet;

import com.liferay.portal.kernel.portlet.BaseFriendlyURLMapper;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Map;

import javax.portlet.PortletMode;
import javax.portlet.WindowState;

/**
 * <a href="MeetupsFriendlyURLMapper.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class MeetupsFriendlyURLMapper extends BaseFriendlyURLMapper {

	public String buildPath(LiferayPortletURL portletURL) {
		String jspPage = GetterUtil.getString(
			portletURL.getParameter("jspPage"));
		long meetupsEntryId = GetterUtil.getLong(
			portletURL.getParameter("meetupsEntryId"));

		if ((jspPage.equals("/meetups/view_entry.jsp")) &&
			(meetupsEntryId > 0)) {

			portletURL.addParameterIncludedInPath("p_p_id");

			portletURL.addParameterIncludedInPath("jspPage");
			portletURL.addParameterIncludedInPath("meetupsEntryId");

			StringBuilder sb = new StringBuilder();

			sb.append(StringPool.SLASH);
			sb.append(_MAPPING);
			sb.append("/entry/");
			sb.append(meetupsEntryId);

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

		int x = friendlyURLPath.indexOf("/", 1);

		int y = friendlyURLPath.indexOf("/", x + 1);

		if (y == -1) {
			return;
		}

		String type = friendlyURLPath.substring(x + 1, y);

		if (!type.equals("entry")) {
			return;
		}

		x = friendlyURLPath.indexOf("/", x + 1);

		if (x == -1) {
			return;
		}

		long meetupsEntryId = GetterUtil.getLong(
			friendlyURLPath.substring(x + 1));

		if (meetupsEntryId <= 0) {
			return;
		}

		addParameter(parameterMap, "p_p_id", _PORTLET_ID);
		addParameter(parameterMap, "p_p_lifecycle", "0");
		addParameter(parameterMap, "p_p_state", WindowState.MAXIMIZED);
		addParameter(parameterMap, "p_p_mode", PortletMode.VIEW);

		addParameter(parameterMap, "jspPage", "/meetups/view_entry.jsp");
		addParameter(
			parameterMap, "meetupsEntryId", String.valueOf(meetupsEntryId));
	}

	private static final String _MAPPING = "meetups";

	private static final String _PORTLET_ID = "7_WAR_socialnetworkingportlet";

}