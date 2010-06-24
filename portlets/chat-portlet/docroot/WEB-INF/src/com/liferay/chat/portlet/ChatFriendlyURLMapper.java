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

package com.liferay.chat.portlet;

import com.liferay.portal.kernel.portlet.BaseFriendlyURLMapper;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.util.Validator;

import java.util.Map;

import javax.portlet.PortletMode;
import javax.portlet.WindowState;

/**
 * <a href="ChatFriendlyURLMapper.java.html"><b><i>View Source</i></b></a>
 *
 * @author Scott Lee
 *
 */
public class ChatFriendlyURLMapper extends BaseFriendlyURLMapper {

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
			y = friendlyURLPath.length();
		}

		String jspPage = friendlyURLPath.substring(x + 1, y);

		if (Validator.isNull(jspPage)) {
			return;
		}

		addParameter(parameterMap, "p_p_id", _PORTLET_ID);
		addParameter(parameterMap, "p_p_lifecycle", "2");
		addParameter(parameterMap, "p_p_state", WindowState.NORMAL);
		addParameter(parameterMap, "p_p_mode", PortletMode.VIEW);

		addParameter(parameterMap, "jspPage", "/" + jspPage + ".jsp");
	}

	private static final String _MAPPING = "chat";

	private static final String _PORTLET_ID = "1_WAR_chatportlet";

}