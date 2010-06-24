/**
 * Copyright (c) 2008-2010 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.so.profiles.portlet;

import com.liferay.portal.kernel.portlet.BaseFriendlyURLMapper;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.util.Validator;

import java.util.Map;

import javax.portlet.PortletMode;

/**
 * <a href="ProfilesFriendlyURLMapper.java.html"><b><i>View Source</i></b></a>
 *
 * @author Ryan Park
 */
public class ProfilesFriendlyURLMapper extends BaseFriendlyURLMapper {

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
		int z = friendlyURLPath.indexOf("/", y + 1);

		if (y == -1) {
			y = friendlyURLPath.length();
		}

		String jspPage = friendlyURLPath.substring(x + 1, y);

		if (Validator.isNull(jspPage)) {
			return;
		}

		addParameter(parameterMap, "p_p_id", _PORTLET_ID);
		addParameter(parameterMap, "p_p_lifecycle", "0");
		addParameter(parameterMap, "p_p_state", LiferayWindowState.EXCLUSIVE);
		addParameter(parameterMap, "p_p_mode", PortletMode.VIEW);

		addParameter(parameterMap, "jspPage", "/profiles/" + jspPage + ".jsp");

		if ((z > 0) && jspPage.equals("user_profile.jsp")) {
			addParameter(
				parameterMap, "userId", friendlyURLPath.substring(y + 1, z));
		}
	}

	private static final String _MAPPING = "profiles";

	private static final String _PORTLET_ID = "4_WAR_soportlet";

}