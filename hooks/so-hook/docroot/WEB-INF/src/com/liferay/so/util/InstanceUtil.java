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

package com.liferay.so.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.model.LayoutSetPrototype;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.service.LayoutSetPrototypeLocalServiceUtil;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.expando.model.ExpandoColumn;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;
import com.liferay.site.my.sites.web.constants.MySitesPortletKeys;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 * @author Ryan Park
 * @author Jonathan Lee
 */
public class InstanceUtil {

	public static void initInstance(long companyId) {
		try {
			setupRole(companyId);

			setupExpando(companyId);

			initLayoutSetPrototype(companyId);

			setInitialized(companyId, true);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	public static void initLayoutSetPrototype(long companyId) throws Exception {
		setupLayoutSetPrototypeSite(companyId);
		setupLayoutSetPrototypeUserPrivate(companyId);
		setupLayoutSetPrototypeUserPublic(companyId);
	}

	public static void initRuntime(long companyId) {

		// Directory

		Portlet portlet = PortletLocalServiceUtil.getPortletById(
			PortletKeys.DIRECTORY);

		portlet.setAddDefaultResource(true);

		// My Sites

		portlet = PortletLocalServiceUtil.getPortletById(
			MySitesPortletKeys.MY_SITES);

		portlet.setAddDefaultResource(true);

		// Clear cache

		PortletLocalServiceUtil.clearCache();
	}

	public static boolean isInitialized(long companyId) {
		try {
			Group group = GroupLocalServiceUtil.getCompanyGroup(companyId);

			UnicodeProperties typeSettingsProperties =
				group.getTypeSettingsProperties();

			return GetterUtil.getBoolean(
				typeSettingsProperties.getProperty(
					"social-office-initialized"));
		}
		catch (Exception e) {
			return false;
		}
	}

	public static void setInitialized(long companyId, boolean initialized)
		throws Exception {

		Group group = GroupLocalServiceUtil.getCompanyGroup(companyId);

		UnicodeProperties typeSettingsProperties =
			group.getTypeSettingsProperties();

		typeSettingsProperties.setProperty(
			"social-office-initialized", String.valueOf(initialized));

		GroupLocalServiceUtil.updateGroup(
			group.getGroupId(), typeSettingsProperties.toString());
	}

	protected static LayoutSetPrototype addLayoutSetPrototype(
			long companyId, String name)
		throws Exception {

		Map<Locale, String> localeNamesMap = new HashMap<>();

		Locale defaultLocale = LocaleUtil.getDefault();

		localeNamesMap.put(defaultLocale, name);

		String description = name;
		boolean active = true;
		long defaultUserId = UserLocalServiceUtil.getDefaultUserId(companyId);

		LayoutSetPrototype layoutSetPrototype =
			LayoutSetPrototypeLocalServiceUtil.addLayoutSetPrototype(
				defaultUserId, companyId, localeNamesMap, description, active,
				true, new ServiceContext());

		UnicodeProperties settingsProperties =
			layoutSetPrototype.getSettingsProperties();

		settingsProperties.setProperty(
			"customJspServletContextName", "so-hook");

		layoutSetPrototype =
			LayoutSetPrototypeLocalServiceUtil.updateLayoutSetPrototype(
				layoutSetPrototype.getLayoutSetPrototypeId(),
				settingsProperties.toString());

		return layoutSetPrototype;
	}

	protected static Map<Locale, String> getLocalizationMap(String key) {
		return LocalizationUtil.getLocalizationMap(
			"content.Language", InstanceUtil.class.getClassLoader(), key,
			false);
	}

	protected static void setupExpando(long companyId) throws Exception {
		ExpandoTable expandoTable = null;

		// Group

		try {
			expandoTable = ExpandoTableLocalServiceUtil.addTable(
				companyId, Group.class.getName(),
				ExpandoTableConstants.DEFAULT_TABLE_NAME);
		}
		catch (Exception e) {
			expandoTable = ExpandoTableLocalServiceUtil.getTable(
				companyId, Group.class.getName(),
				ExpandoTableConstants.DEFAULT_TABLE_NAME);
		}

		try {
			ExpandoColumn expandoColumn =
				ExpandoColumnLocalServiceUtil.addColumn(
					expandoTable.getTableId(), "socialOfficeEnabled",
					ExpandoColumnConstants.BOOLEAN);

			updatePermissions(expandoColumn);
		}
		catch (Exception e) {
		}

		// Layout set prototype

		try {
			expandoTable = ExpandoTableLocalServiceUtil.addTable(
				companyId, LayoutSetPrototype.class.getName(),
				ExpandoTableConstants.DEFAULT_TABLE_NAME);
		}
		catch (Exception e) {
			expandoTable = ExpandoTableLocalServiceUtil.getTable(
				companyId, LayoutSetPrototype.class.getName(),
				ExpandoTableConstants.DEFAULT_TABLE_NAME);
		}

		try {
			ExpandoColumn expandoColumn =
				ExpandoColumnLocalServiceUtil.addColumn(
					expandoTable.getTableId(),
					SocialOfficeConstants.LAYOUT_SET_PROTOTYPE_KEY,
					ExpandoColumnConstants.STRING);

			updatePermissions(expandoColumn);
		}
		catch (Exception e) {
		}
	}

	protected static void setupLayoutSetPrototypeSite(long companyId)
		throws Exception {

		LayoutSetPrototype layoutSetPrototype = addLayoutSetPrototype(
			companyId, "Default Social Office Site");

		ExpandoValueLocalServiceUtil.addValue(
			companyId, LayoutSetPrototype.class.getName(),
			ExpandoTableConstants.DEFAULT_TABLE_NAME,
			SocialOfficeConstants.LAYOUT_SET_PROTOTYPE_KEY,
			layoutSetPrototype.getLayoutSetPrototypeId(),
			SocialOfficeConstants.LAYOUT_SET_PROTOTYPE_KEY_SITE);

		Role role = RoleLocalServiceUtil.getRole(
			layoutSetPrototype.getCompanyId(),
			RoleConstants.SOCIAL_OFFICE_USER);

		ResourcePermissionLocalServiceUtil.setResourcePermissions(
			layoutSetPrototype.getCompanyId(),
			LayoutSetPrototype.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL,
			String.valueOf(layoutSetPrototype.getPrimaryKey()),
			role.getRoleId(), new String[] {ActionKeys.VIEW});

		Group group = layoutSetPrototype.getGroup();

		LayoutLocalServiceUtil.deleteLayouts(
			group.getGroupId(), true, new ServiceContext());

		LayoutSetLocalServiceUtil.updateLookAndFeel(
			group.getGroupId(), "so_WAR_sotheme", "01", "", false);

		// Home

		Layout layout = LayoutUtil.addLayout(
			group, true, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
			getLocalizationMap("home"), null,
			PortletPropsValues.SITE_PROTOTYPE_LAYOUT_TEMPLATE);

		LayoutUtil.addPortlets(
			group, layout, "/home", PortletPropsKeys.SITE_PROTOTYPE_PORTLETS);

		LayoutUtil.configureRSS(layout);

		LayoutUtil.updatePermissions(layout, true);

		// Calendar

		layout = LayoutUtil.addLayout(
			group, true, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
			getLocalizationMap("calendar"), null, "1_column");

		LayoutUtil.addPortlets(
			group, layout, "/calendar",
			PortletPropsKeys.SITE_PROTOTYPE_PORTLETS);

		LayoutUtil.updatePermissions(layout, true);

		// Documents

		layout = LayoutUtil.addLayout(
			group, true, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
			getLocalizationMap("documents"), null, "1_column");

		LayoutUtil.addPortlets(
			group, layout, "/documents",
			PortletPropsKeys.SITE_PROTOTYPE_PORTLETS);

		LayoutUtil.configureAssetPublisher(layout);

		LayoutUtil.updatePermissions(layout, true);

		// Forums

		layout = LayoutUtil.addLayout(
			group, true, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
			getLocalizationMap("forums"), null, "1_column");

		LayoutUtil.addPortlets(
			group, layout, "/forums", PortletPropsKeys.SITE_PROTOTYPE_PORTLETS);

		LayoutUtil.removePortletBorder(layout, "73");
		LayoutUtil.removePortletBorder(layout, "19");

		LayoutUtil.configureAssetPublisher(layout);
		LayoutUtil.configureMessageBoards(layout);

		LayoutUtil.updatePermissions(layout, true);

		// Blog

		layout = LayoutUtil.addLayout(
			group, true, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
			getLocalizationMap("blogs"), null, "2_columns_iii");

		LayoutUtil.addPortlets(
			group, layout, "/blog", PortletPropsKeys.SITE_PROTOTYPE_PORTLETS);

		LayoutUtil.configureAssetPublisher(layout);

		LayoutUtil.updatePermissions(layout, true);

		// Wiki

		layout = LayoutUtil.addLayout(
			group, true, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
			getLocalizationMap("wiki"), null, "1_column");

		LayoutUtil.addPortlets(
			group, layout, "/wiki", PortletPropsKeys.SITE_PROTOTYPE_PORTLETS);

		LayoutUtil.configureAssetPublisher(layout);

		LayoutUtil.updatePermissions(layout, true);

		// Members

		layout = LayoutUtil.addLayout(
			group, true, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
			getLocalizationMap("members"), null, "1_column");

		LayoutUtil.addPortlets(
			group, layout, "/members",
			PortletPropsKeys.SITE_PROTOTYPE_PORTLETS);

		LayoutUtil.removePortletBorder(layout, "2_WAR_soportlet");

		LayoutUtil.updatePermissions(layout, true);
	}

	protected static void setupLayoutSetPrototypeUserPrivate(long companyId)
		throws Exception {

		LayoutSetPrototype layoutSetPrototype = addLayoutSetPrototype(
			companyId, "Social Office User Home");

		ExpandoValueLocalServiceUtil.addValue(
			companyId, LayoutSetPrototype.class.getName(),
			ExpandoTableConstants.DEFAULT_TABLE_NAME,
			SocialOfficeConstants.LAYOUT_SET_PROTOTYPE_KEY,
			layoutSetPrototype.getLayoutSetPrototypeId(),
			SocialOfficeConstants.LAYOUT_SET_PROTOTYPE_KEY_USER_PRIVATE);

		Group group = layoutSetPrototype.getGroup();

		LayoutLocalServiceUtil.deleteLayouts(
			group.getGroupId(), true, new ServiceContext());

		LayoutSetLocalServiceUtil.updateLookAndFeel(
			group.getGroupId(), "so_WAR_sotheme", "01", "", false);

		// Dashboard

		Layout layout = LayoutUtil.addLayout(
			group, true, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
			getLocalizationMap("dashboard"), "/so/dashboard", "2_columns_iii");

		LayoutUtil.addPortlets(
			group, layout, "/home",
			PortletPropsKeys.USER_PRIVATE_LAYOUT_PORTLETS);

		LayoutUtil.updatePermissions(layout, false);

		LayoutLocalServiceUtil.updatePriority(layout, 0);

		// Contacts Center

		layout = LayoutUtil.addLayout(
			group, true, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
			getLocalizationMap("contacts-center"), "/so/contacts-center",
			"1_column");

		LayoutUtil.addPortlets(
			group, layout, "/contacts-center",
			PortletPropsKeys.USER_PRIVATE_LAYOUT_PORTLETS);

		LayoutUtil.updatePermissions(layout, true);

		LayoutLocalServiceUtil.updatePriority(layout, 1);

		// Microblogs

		layout = LayoutUtil.addLayout(
			group, true, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
			getLocalizationMap("microblogs"), "/so/microblogs", "1_column");

		LayoutUtil.addPortlets(
			group, layout, "/microblogs",
			PortletPropsKeys.USER_PRIVATE_LAYOUT_PORTLETS);

		LayoutUtil.updatePermissions(layout, true);

		LayoutLocalServiceUtil.updatePriority(layout, 2);

		// Messages

		layout = LayoutUtil.addLayout(
			group, true, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
			getLocalizationMap("messages"), "/so/messages", "1_column");

		LayoutUtil.addPortlets(
			group, layout, "/messages",
			PortletPropsKeys.USER_PRIVATE_LAYOUT_PORTLETS);

		LayoutUtil.updatePermissions(layout, true);

		LayoutLocalServiceUtil.updatePriority(layout, 3);

		// My Documents

		layout = LayoutUtil.addLayout(
			group, true, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
			getLocalizationMap("my-documents"), "/so/my-documents", "1_column");

		LayoutUtil.addPortlets(
			group, layout, "/my-documents",
			PortletPropsKeys.USER_PRIVATE_LAYOUT_PORTLETS);

		LayoutUtil.updatePermissions(layout, true);

		LayoutLocalServiceUtil.updatePriority(layout, 4);

		// Tasks

		layout = LayoutUtil.addLayout(
			group, true, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
			getLocalizationMap("tasks"), "/so/tasks", "1_column");

		LayoutUtil.addPortlets(
			group, layout, "/tasks",
			PortletPropsKeys.USER_PRIVATE_LAYOUT_PORTLETS);

		LayoutUtil.updatePermissions(layout, true);

		LayoutLocalServiceUtil.updatePriority(layout, 5);
	}

	protected static void setupLayoutSetPrototypeUserPublic(long companyId)
		throws Exception {

		LayoutSetPrototype layoutSetPrototype = addLayoutSetPrototype(
			companyId, "Social Office User Profile");

		ExpandoValueLocalServiceUtil.addValue(
			companyId, LayoutSetPrototype.class.getName(),
			ExpandoTableConstants.DEFAULT_TABLE_NAME,
			SocialOfficeConstants.LAYOUT_SET_PROTOTYPE_KEY,
			layoutSetPrototype.getLayoutSetPrototypeId(),
			SocialOfficeConstants.LAYOUT_SET_PROTOTYPE_KEY_USER_PUBLIC);

		Group group = layoutSetPrototype.getGroup();

		LayoutLocalServiceUtil.deleteLayouts(
			group.getGroupId(), true, new ServiceContext());

		LayoutSetLocalServiceUtil.updateLookAndFeel(
			group.getGroupId(), "so_WAR_sotheme", "01", "", false);

		// Profile

		Layout layout = LayoutUtil.addLayout(
			group, true, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
			getLocalizationMap("profile"), "/so/profile", "1_2_columns_ii");

		LayoutUtil.addPortlets(
			group, layout, "/profile",
			PortletPropsKeys.USER_PUBLIC_LAYOUT_PORTLETS);

		LayoutUtil.updatePermissions(layout, true);

		LayoutLocalServiceUtil.updatePriority(layout, 0);

		// Contacts

		layout = LayoutUtil.addLayout(
			group, true, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
			getLocalizationMap("contacts"), "/so/contacts", "1_column");

		LayoutUtil.addPortlets(
			group, layout, "/contacts",
			PortletPropsKeys.USER_PUBLIC_LAYOUT_PORTLETS);

		LayoutUtil.updatePermissions(layout, true);

		LayoutLocalServiceUtil.updatePriority(layout, 1);

		// Microblogs

		layout = LayoutUtil.addLayout(
			group, true, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
			getLocalizationMap("microblogs"), "/so/microblogs", "1_column");

		LayoutUtil.addPortlets(
			group, layout, "/microblogs",
			PortletPropsKeys.USER_PUBLIC_LAYOUT_PORTLETS);

		LayoutUtil.updatePermissions(layout, true);

		LayoutLocalServiceUtil.updatePriority(layout, 2);
	}

	protected static void setupRole(long companyId) throws Exception {
		long defaultUserId = UserLocalServiceUtil.getDefaultUserId(companyId);

		Map<Locale, String> descriptionMap = new HashMap<>();

		descriptionMap.put(
			LocaleUtil.getDefault(),
			"Social Office Users have access to the Social Office Suite.");

		Role role = RoleLocalServiceUtil.addRole(
			defaultUserId, companyId, RoleConstants.SOCIAL_OFFICE_USER, null,
			descriptionMap, RoleConstants.TYPE_REGULAR);

		ResourcePermissionLocalServiceUtil.setResourcePermissions(
			companyId, PortletKeys.PORTAL, ResourceConstants.SCOPE_COMPANY,
			String.valueOf(companyId), role.getRoleId(),
			new String[] {ActionKeys.ADD_COMMUNITY});

		ResourcePermissionLocalServiceUtil.setResourcePermissions(
			companyId, User.class.getName(), ResourceConstants.SCOPE_COMPANY,
			String.valueOf(companyId), role.getRoleId(),
			new String[] {ActionKeys.VIEW});
	}

	protected static void updatePermissions(ExpandoColumn expandoColumn)
		throws Exception {

		Role role = RoleLocalServiceUtil.getRole(
			expandoColumn.getCompanyId(), RoleConstants.USER);

		String name = ExpandoColumn.class.getName();
		int scope = ResourceConstants.SCOPE_INDIVIDUAL;
		String primKey = String.valueOf(expandoColumn.getPrimaryKey());
		String[] actionIds = {ActionKeys.VIEW};

		ResourcePermissionLocalServiceUtil.setResourcePermissions(
			expandoColumn.getCompanyId(), name, scope, primKey,
			role.getRoleId(), actionIds);
	}

	private static Log _log = LogFactoryUtil.getLog(InstanceUtil.class);

}