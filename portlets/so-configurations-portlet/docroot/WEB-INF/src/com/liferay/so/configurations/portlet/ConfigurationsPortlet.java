/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.so.configurations.portlet;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.persistence.UserActionableDynamicQuery;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.so.util.RoleConstants;
import com.liferay.util.bridges.mvc.MVCPortlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Jonathan Lee
 * @author Evan Thibodeau
 */
public class ConfigurationsPortlet extends MVCPortlet {

	public void addRoleAllUsers(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		final Role role = RoleLocalServiceUtil.getRole(
			themeDisplay.getCompanyId(), RoleConstants.SOCIAL_OFFICE_USER);

		ActionableDynamicQuery actionableDynamicQuery =
			new UserActionableDynamicQuery() {

			@Override
			protected void performAction(Object object)
				throws PortalException, SystemException {

				User user = (User)object;

				if (!user.isDefaultUser()) {
					UserLocalServiceUtil.addRoleUsers(
						role.getRoleId(), new long[] {user.getUserId()});
				}
			}

		};

		actionableDynamicQuery.setCompanyId(themeDisplay.getCompanyId());

		actionableDynamicQuery.performActions();
	}

	public void updateGroupsRole(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long[] addGroupIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "addIds"), 0L);
		long[] removeGroupIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "removeIds"), 0L);

		Role role = RoleLocalServiceUtil.getRole(
			themeDisplay.getCompanyId(), RoleConstants.SOCIAL_OFFICE_USER);

		GroupLocalServiceUtil.addRoleGroups(role.getRoleId(), addGroupIds);
		GroupLocalServiceUtil.unsetRoleGroups(role.getRoleId(), removeGroupIds);
	}

	public void updateUsersRole(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long[] addUserIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "addIds"), 0L);
		long[] removeUserIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "removeIds"), 0L);

		Role role = RoleLocalServiceUtil.getRole(
			themeDisplay.getCompanyId(), RoleConstants.SOCIAL_OFFICE_USER);

		UserLocalServiceUtil.addRoleUsers(role.getRoleId(), addUserIds);
		UserLocalServiceUtil.unsetRoleUsers(role.getRoleId(), removeUserIds);
	}

}