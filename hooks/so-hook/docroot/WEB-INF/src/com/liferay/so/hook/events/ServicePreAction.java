/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.so.hook.events;

import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.so.util.LayoutUtil;
import com.liferay.so.util.PortletPropsKeys;
import com.liferay.so.util.RoleConstants;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Brett Swaim
 * @author Ryan Park
 */
public class ServicePreAction extends Action {

	@Override
	public void run(HttpServletRequest request, HttpServletResponse response) {
		try {
			updateUserLayouts(request, response);
			doRun(request, response);
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}
	}

	protected void doRun(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		String currentURL = PortalUtil.getCurrentURL(request);

		if (Validator.isNotNull(themeDisplay.getI18nLanguageId())) {
			int x = currentURL.indexOf(StringPool.SLASH, 1);

			currentURL = currentURL.substring(x);
		}

		String redirect = null;

		if (isMyPlacesView(themeDisplay, currentURL)) {
			redirect = getRedirect(themeDisplay, request);
		}
		else if (isDirectoryView(request, currentURL)) {
			redirect = getUserRedirect(
				themeDisplay, ParamUtil.getLong(request, "_11_p_u_i_d"));
		}

		if (Validator.isNotNull(redirect)) {
			response.sendRedirect(redirect);
		}
	}

	protected boolean isDirectoryView(
		HttpServletRequest request, String currentURL) throws Exception {

		String action = ParamUtil.getString(request, "_11_struts_action");

		if (!action.equals("/directory/view_user")) {
			return false;
		}

		long userId = ParamUtil.getLong(request, "_11_p_u_i_d");

		if (userId <= 0) {
			return false;
		}

		return true;
	}

	protected boolean isMyPlacesView(
		ThemeDisplay themeDisplay, String currentURL) {

		String urlFragment1 =
			themeDisplay.getPathMain() + "/my_sites/view?groupId=";

		if (!StringUtil.startsWith(currentURL, urlFragment1)) {
			return false;
		}

		String urlFragment2 = "&privateLayout=0";

		if (!StringUtil.endsWith(currentURL, urlFragment2)) {
			return false;
		}

		String s = currentURL;

		s = StringUtil.remove(s, urlFragment1, StringPool.BLANK);
		s = StringUtil.remove(s, urlFragment2, StringPool.BLANK);

		return Validator.isNumber(s);
	}

	protected String getGroupRedirect(ThemeDisplay themeDisplay, long groupId)
		throws Exception {

		List<Layout> layouts = LayoutLocalServiceUtil.getLayouts(
			groupId, false, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID, false,
			0, 1);

		if (layouts.size() > 0) {
			Layout layout = layouts.get(0);

			return PortalUtil.getLayoutURL(layout, themeDisplay);
		}

		return null;
	}

	protected String getRedirect(
			ThemeDisplay themeDisplay, HttpServletRequest request)
		throws Exception {

		long groupId = ParamUtil.getLong(request, "groupId");

		if (groupId > 0) {
			Group group = GroupLocalServiceUtil.getGroup(groupId);

			if (group.isUser()) {
				return getUserRedirect(themeDisplay, group.getClassPK());
			}

			return getGroupRedirect(themeDisplay, group.getGroupId());
		}

		return null;
	}

	protected String getUserRedirect(ThemeDisplay themeDisplay, long userId)
		throws Exception {

		User user = UserLocalServiceUtil.getUser(userId);

		return themeDisplay.getPathFriendlyURLPublic() + "/" +
			user.getScreenName() + "/profile";
	}

	protected void setCustomJspServletContextName(Group group)
		throws Exception {

		UnicodeProperties typeSettingsProperties =
			group.getTypeSettingsProperties();

		typeSettingsProperties.setProperty(
			"customJspServletContextName", "so-hook");

		GroupLocalServiceUtil.updateGroup(
			group.getGroupId(), typeSettingsProperties.toString());
	}

	protected void updateUserLayouts(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		User user = null;

		try {
			user = PortalUtil.getUser(request);
		}
		catch (NoSuchUserException nsue) {
		}

		if (user != null) {
			Role role = RoleLocalServiceUtil.fetchRole(
				user.getCompanyId(), RoleConstants.SOCIAL_OFFICE_USER);

			if (!UserLocalServiceUtil.hasRoleUser(
					role.getRoleId(), user.getUserId())) {

				return;
			}

			Group group = user.getGroup();

			String customJspServletContextName = GetterUtil.getString(
				group.getTypeSettingsProperty("customJspServletContextName"));

			if (customJspServletContextName.equals("so-hook")) {
				System.out.println("Already SO");
				return;
			}

			if (!LayoutLocalServiceUtil.hasLayouts(user, false)) {
				LayoutSetLocalServiceUtil.addLayoutSet(
					group.getGroupId(), false);
			}
			else {
				List<Layout> layouts = LayoutLocalServiceUtil.getLayouts(
					group.getGroupId(), false);

				ServiceContext serviceContext = new ServiceContext();

				for (Layout layout : layouts) {
					LayoutLocalServiceUtil.updateLayout(
						layout.getGroupId(), layout.isPrivateLayout(),
						layout.getLayoutId(), layout.getParentLayoutId(),
						layout.getNameMap(), layout.getTitleMap(),
						layout.getDescriptionMap(), layout.getKeywordsMap(),
						layout.getRobotsMap(), layout.getType(), true,
						layout.getFriendlyURL(), null, null, serviceContext);
				}
			}

			if (!LayoutLocalServiceUtil.hasLayouts(user, true)) {
				LayoutSetLocalServiceUtil.addLayoutSet(
					group.getGroupId(), true);
			}

			updatePrivateUserLayouts(group);
			updatePublicUserLayouts(group);

			setCustomJspServletContextName(group);
		}
	}

	protected void updatePrivateUserLayouts(Group group) throws Exception {
		LayoutSetLocalServiceUtil.updateLookAndFeel(
			group.getGroupId(), true, "so_WAR_sotheme", "01", StringPool.BLANK,
			false);

		// Home

		Layout layout = LayoutUtil.addLayout(
			group, true, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID, "Home",
			"2_columns_iii");

		LayoutUtil.addPortlets(
			group, layout, "/home",
			PortletPropsKeys.USER_PRIVATE_LAYOUT_PORTLETS);

		LayoutUtil.updatePermissions(layout, false);

		LayoutLocalServiceUtil.updatePriority(layout, 0);

		// Contacts Center

		layout = LayoutUtil.addLayout(
			group, true, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
			"Contacts Center", "1_column");

		LayoutUtil.addPortlets(
			group, layout, "/contacts-center",
			PortletPropsKeys.USER_PRIVATE_LAYOUT_PORTLETS);

		LayoutUtil.updatePermissions(layout, true);

		LayoutLocalServiceUtil.updatePriority(layout, 1);

		// Microblogs

		layout = LayoutUtil.addLayout(
			group, true, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
			"Microblogs", "1_column");

		LayoutUtil.addPortlets(
			group, layout, "/microblogs",
			PortletPropsKeys.USER_PRIVATE_LAYOUT_PORTLETS);

		LayoutUtil.updatePermissions(layout, true);

		LayoutLocalServiceUtil.updatePriority(layout, 2);

		// Messages

		layout = LayoutUtil.addLayout(
			group, true, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
			"Messages", "1_column");

		LayoutUtil.addPortlets(
			group, layout, "/messages",
			PortletPropsKeys.USER_PRIVATE_LAYOUT_PORTLETS);

		LayoutUtil.updatePermissions(layout, true);

		LayoutLocalServiceUtil.updatePriority(layout, 3);

		// Tasks

		layout = LayoutUtil.addLayout(
			group, true, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
			"Tasks", "1_column");

		LayoutUtil.addPortlets(
			group, layout, "/tasks",
			PortletPropsKeys.USER_PRIVATE_LAYOUT_PORTLETS);

		LayoutUtil.updatePermissions(layout, true);

		LayoutLocalServiceUtil.updatePriority(layout, 4);
	}

	protected void updatePublicUserLayouts(Group group) throws Exception {
		LayoutSetLocalServiceUtil.updateLookAndFeel(
			group.getGroupId(), false, "so_WAR_sotheme", "01", StringPool.BLANK,
			false);

		Layout layout = LayoutUtil.addLayout(
			group, false, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID, "Profile",
			"1_2_columns_ii");

		LayoutUtil.addPortlets(
			group, layout, "/profile",
			PortletPropsKeys.USER_PUBLIC_LAYOUT_PORTLETS);

		LayoutUtil.updatePermissions(layout, true);

		LayoutLocalServiceUtil.updatePriority(layout, 0);

		// Contacts

		layout = LayoutUtil.addLayout(
			group, false, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
			"Contacts", "1_column");

		LayoutUtil.addPortlets(
			group, layout, "/contacts",
			PortletPropsKeys.USER_PUBLIC_LAYOUT_PORTLETS);

		LayoutUtil.updatePermissions(layout, true);

		LayoutLocalServiceUtil.updatePriority(layout, 1);

		// Microblogs

		layout = LayoutUtil.addLayout(
			group, false, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
			"Microblogs", "1_column");

		LayoutUtil.addPortlets(
			group, layout, "/microblogs",
			PortletPropsKeys.USER_PUBLIC_LAYOUT_PORTLETS);

		LayoutUtil.updatePermissions(layout, true);

		LayoutLocalServiceUtil.updatePriority(layout, 2);
	}

	private static Log _log = LogFactoryUtil.getLog(ServicePreAction.class);

}