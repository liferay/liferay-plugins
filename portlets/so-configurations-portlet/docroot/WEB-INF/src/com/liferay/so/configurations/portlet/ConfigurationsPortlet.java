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

package com.liferay.so.configurations.portlet;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PropertiesParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.so.configurations.util.PortletKeys;
import com.liferay.so.util.RoleConstants;

import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;
import javax.portlet.ValidatorException;

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
			UserLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setCompanyId(themeDisplay.getCompanyId());
		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<User>() {

				@Override
				public void performAction(User user) throws PortalException {
					if (!user.isDefaultUser()) {
						UserLocalServiceUtil.addRoleUsers(
							role.getRoleId(), new long[] {user.getUserId()});
					}
				}

			});

		actionableDynamicQuery.performActions();
	}

	public void updateGeneralConfigurations(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		boolean addSitePermission = ParamUtil.get(
			actionRequest, "addSitePermission", true);

		updateRolePermissions(themeDisplay.getCompanyId(), addSitePermission);

		UnicodeProperties properties = PropertiesParamUtil.getProperties(
			actionRequest, "preferences--");

		PortletPreferences portletPreferences =
			PortletPreferencesLocalServiceUtil.getPreferences(
				themeDisplay.getCompanyId(), themeDisplay.getCompanyId(),
				PortletKeys.PREFS_OWNER_TYPE_COMPANY,
				LayoutConstants.DEFAULT_PLID, PortletKeys.SO_CONFIGURATIONS);

		for (Map.Entry<String, String> entry : properties.entrySet()) {
			portletPreferences.setValue(entry.getKey(), entry.getValue());
		}

		try {
			portletPreferences.store();
		}
		catch (ValidatorException ve) {
			SessionErrors.add(
				actionRequest, ValidatorException.class.getName(), ve);
		}
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

	protected void updateRolePermissions(
			long companyId, boolean addSitePermission)
		throws Exception {

		Role role = RoleLocalServiceUtil.getRole(
			companyId, RoleConstants.SOCIAL_OFFICE_USER);

		if (addSitePermission) {
			ResourcePermissionLocalServiceUtil.addResourcePermission(
				companyId, PortletKeys.PORTAL, ResourceConstants.SCOPE_COMPANY,
				String.valueOf(companyId), role.getRoleId(),
				ActionKeys.ADD_COMMUNITY);
		}
		else {
			ResourcePermissionLocalServiceUtil.removeResourcePermission(
				companyId, PortletKeys.PORTAL, ResourceConstants.SCOPE_COMPANY,
				String.valueOf(companyId), role.getRoleId(),
				ActionKeys.ADD_COMMUNITY);
		}
	}

}