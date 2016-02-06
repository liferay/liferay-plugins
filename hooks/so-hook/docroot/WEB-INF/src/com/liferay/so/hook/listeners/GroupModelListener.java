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

package com.liferay.so.hook.listeners;

import com.liferay.expando.kernel.model.ExpandoTableConstants;
import com.liferay.expando.kernel.service.ExpandoValueLocalServiceUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.TeamLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.so.util.PortletPropsValues;

import java.util.Set;

/**
 * @author Ryan Park
 */
public class GroupModelListener extends BaseModelListener<Group> {

	@Override
	public void onAfterCreate(Group group) throws ModelListenerException {
		try {
			if (!group.isSite()) {
				return;
			}

			addTeams(group);
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	@Override
	public void onAfterUpdate(Group group) throws ModelListenerException {
		try {
			setSocialOfficeEnabled(group);
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	protected void addTeams(Group group) throws Exception {
		long userId = PortalUtil.getValidUserId(
			group.getCompanyId(), group.getCreatorUserId());

		Set<String> names = SetUtil.fromArray(
			PortletPropsValues.SITE_AUTO_CREATE_TEAM_NAMES);

		for (String name : names) {
			TeamLocalServiceUtil.addTeam(
				userId, group.getGroupId(), name, StringPool.BLANK);
		}
	}

	protected void setSocialOfficeEnabled(Group group) throws Exception {
		boolean socialOfficeEnabled = false;

		String customJspServletContextName = GetterUtil.getString(
			group.getTypeSettingsProperty("customJspServletContextName"));

		if (customJspServletContextName.equals("so-hook")) {
			socialOfficeEnabled = true;
		}

		ExpandoValueLocalServiceUtil.addValue(
			group.getCompanyId(), Group.class.getName(),
			ExpandoTableConstants.DEFAULT_TABLE_NAME, "socialOfficeEnabled",
			group.getGroupId(), socialOfficeEnabled);
	}

}