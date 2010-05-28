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

package com.liferay.socialnetworking.groupusersactivities.portlet;

import com.liferay.portal.kernel.portlet.RSSFriendlyURLMapper;

/**
 * <a href="GroupUsersActivitiesFriendlyURLMapper.java.html"><b><i>View Source
 * </i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class GroupUsersActivitiesFriendlyURLMapper
	extends RSSFriendlyURLMapper {

	public String getMapping() {
		return _MAPPING;
	}

	public String getPortletId() {
		return _PORTLET_ID;
	}

	private static final String _MAPPING = "group_users_activities";

	private static final String _PORTLET_ID = "8_WAR_socialnetworkingportlet";

}