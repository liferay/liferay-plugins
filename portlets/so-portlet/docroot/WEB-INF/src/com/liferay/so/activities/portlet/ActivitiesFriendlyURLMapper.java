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

package com.liferay.so.activities.portlet;

import com.liferay.portal.kernel.portlet.RSSFriendlyURLMapper;

/**
 * <a href="ActivitiesFriendlyURLMapper.java.html"><b><i>View Source</i></b></a>
 *
 * @author Ryan Park
 */
public class ActivitiesFriendlyURLMapper extends RSSFriendlyURLMapper {

	public String getMapping() {
		return _MAPPING;
	}

	public String getPortletId() {
		return _PORTLET_ID;
	}

	private static final String _MAPPING = "user_activities";

	private static final String _PORTLET_ID = "1_WAR_soportlet";

}