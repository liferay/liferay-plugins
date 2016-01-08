/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.so.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupServiceUtil;
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.so.service.base.SocialOfficeServiceBaseImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonathan Lee
 */
public class SocialOfficeServiceImpl extends SocialOfficeServiceBaseImpl {

	public long[] getUserSocialOfficeGroupIds() throws PortalException {
		List<Group> groups = new ArrayList<>();

		for (Group group : GroupServiceUtil.getUserSitesGroups()) {
			if (isSocialOfficeGroup(group.getGroupId())) {
				groups.add(group);
			}
		}

		return StringUtil.split(ListUtil.toString(groups, "groupId"), 0L);
	}

	public boolean isSocialOfficeGroup(long groupId) throws PortalException {
		Group group = groupPersistence.findByPrimaryKey(groupId);

		ExpandoBridge expandoBridge = group.getExpandoBridge();

		return GetterUtil.getBoolean(
			expandoBridge.getAttribute("socialOfficeEnabled", false));
	}

}