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

package com.liferay.so.util;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.model.LayoutSetPrototype;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
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
import com.liferay.portlet.PortletPreferencesThreadLocal;
import com.liferay.portlet.expando.model.ExpandoColumn;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

import java.util.HashMap;
import java.util.List;
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
			PortletPreferencesThreadLocal.setStrict(false);

			setupExpando(companyId);
			setupLayoutSetPrototype(companyId);
			setupUsers(companyId);

			setInitialized(companyId);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
		finally {
			PortletPreferencesThreadLocal.setStrict(true);
		}
	}

	public static void initLayoutSetPrototype(long companyId) throws Exception {
		setupLayoutSetPrototype(companyId);
	}

	public static void initRuntime(long companyId) {

		// Communities

		Portlet portlet = PortletLocalServiceUtil.getPortletById(
			PortletKeys.MY_SITES);

		portlet.setAddDefaultResource(true);

		// Directory

		portlet = PortletLocalServiceUtil.getPortletById(PortletKeys.DIRECTORY);

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

	protected static LayoutSetPrototype addLayoutSetPrototype(
			long companyId, String name)
		throws Exception {

		Map<Locale, String> localeNamesMap = new HashMap<Locale, String>();

		Locale defaultLocale = LocaleUtil.getDefault();

		localeNamesMap.put(defaultLocale, name);

		String description = name;
		boolean active = true;
		long defaultUserId = UserLocalServiceUtil.getDefaultUserId(companyId);

		ServiceContext serviceContext = new ServiceContext();

		LayoutSetPrototype layoutSetPrototype =
			LayoutSetPrototypeLocalServiceUtil.addLayoutSetPrototype(
				defaultUserId, companyId, localeNamesMap, description, active,
				true, serviceContext);

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
					expandoTable.getTableId(), "socialOfficeDefault",
					ExpandoColumnConstants.BOOLEAN);

			updatePermissions(expandoColumn);
		}
		catch (Exception e) {
		}
	}

	protected static void setInitialized(long companyId) throws Exception {
		Group group = GroupLocalServiceUtil.getCompanyGroup(companyId);

		UnicodeProperties typeSettingsProperties =
			group.getTypeSettingsProperties();

		typeSettingsProperties.setProperty(
			"social-office-initialized", Boolean.TRUE.toString());

		GroupLocalServiceUtil.updateGroup(
			group.getGroupId(), typeSettingsProperties.toString());
	}

	protected static void setupLayoutSetPrototype(long companyId)
		throws Exception {

		LayoutSetPrototype layoutSetPrototype = addLayoutSetPrototype(
			companyId, "Default Social Office Site");

		ExpandoValueLocalServiceUtil.addValue(
			companyId, LayoutSetPrototype.class.getName(),
			ExpandoTableConstants.DEFAULT_TABLE_NAME, "socialOfficeDefault",
			layoutSetPrototype.getLayoutSetPrototypeId(), true);

		Group group = layoutSetPrototype.getGroup();

		ServiceContext serviceContext = new ServiceContext();

		LayoutLocalServiceUtil.deleteLayouts(
			group.getGroupId(), true, serviceContext);

		LayoutSetLocalServiceUtil.updateLookAndFeel(
			group.getGroupId(), "so_WAR_sotheme", "01", "", false);

		// Home

		Layout layout = LayoutUtil.addLayout(
			group, true, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID, "Home",
			PortletPropsValues.SITE_PROTOTYPE_LAYOUT_TEMPLATE);

		LayoutUtil.addPortlets(
			group, layout, "/home", PortletPropsKeys.SITE_PROTOTYPE_PORTLETS);

		LayoutUtil.configureRSS(layout);

		LayoutUtil.updatePermissions(layout, true);

		// Calendar

		layout = LayoutUtil.addLayout(
			group, true, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID, "Calendar",
			"1_column");

		LayoutUtil.addPortlets(
			group, layout, "/calendar",
			PortletPropsKeys.SITE_PROTOTYPE_PORTLETS);

		LayoutUtil.updatePermissions(layout, true);

		// Documents

		layout = LayoutUtil.addLayout(
			group, true, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID, "Documents",
			"1_column");

		LayoutUtil.addPortlets(
			group, layout, "/documents",
			PortletPropsKeys.SITE_PROTOTYPE_PORTLETS);

		LayoutUtil.configureAssetPublisher(layout);

		LayoutUtil.updatePermissions(layout, true);

		// Forums

		layout = LayoutUtil.addLayout(
			group, true, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID, "Forums",
			"2_columns_iii");

		LayoutUtil.addPortlets(
			group, layout, "/forums", PortletPropsKeys.SITE_PROTOTYPE_PORTLETS);

		LayoutUtil.configureAssetPublisher(layout);
		LayoutUtil.configureMessageBoards(layout);

		LayoutUtil.updatePermissions(layout, true);

		// Blog

		layout = LayoutUtil.addLayout(
			group, true, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID, "Blog",
			"2_columns_iii");

		LayoutUtil.addPortlets(
			group, layout, "/blog", PortletPropsKeys.SITE_PROTOTYPE_PORTLETS);

		LayoutUtil.configureAssetPublisher(layout);

		LayoutUtil.updatePermissions(layout, true);

		// Wiki

		layout = LayoutUtil.addLayout(
			group, true, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID, "Wiki",
			"2_columns_iii");

		LayoutUtil.addPortlets(
			group, layout, "/wiki", PortletPropsKeys.SITE_PROTOTYPE_PORTLETS);

		LayoutUtil.configureAssetPublisher(layout);

		LayoutUtil.updatePermissions(layout, true);

		// Members

		layout = LayoutUtil.addLayout(
			group, true, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID, "Members",
			"1_column");

		LayoutUtil.addPortlets(
			group, layout, "/members",
			PortletPropsKeys.SITE_PROTOTYPE_PORTLETS);

		LayoutUtil.removePortletBorder(layout, "2_WAR_soportlet");

		LayoutUtil.updatePermissions(layout, true);
	}

	protected static void setupUsers(long companyId) throws Exception {
		List<User> users = UserLocalServiceUtil.search(
			companyId, null, WorkflowConstants.STATUS_ANY, null,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, (OrderByComparator)null);

		for (User user : users) {
			Group group = user.getGroup();

			ServiceContext serviceContext = new ServiceContext();

			LayoutSetLocalServiceUtil.deleteLayoutSet(
				group.getGroupId(), true, serviceContext);
			LayoutSetLocalServiceUtil.deleteLayoutSet(
				group.getGroupId(), false, serviceContext);

			LayoutSetLocalServiceUtil.addLayoutSet(group.getGroupId(), true);
			LayoutSetLocalServiceUtil.addLayoutSet(group.getGroupId(), false);
		}
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