/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.so.util;

import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.model.LayoutSetPrototype;
import com.liferay.portal.model.LayoutTemplate;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.PortletConstants;
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
import com.liferay.portal.service.ResourceLocalServiceUtil;
import com.liferay.portal.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.permission.PortletPermissionUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portlet.PortletPreferencesThreadLocal;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;
import com.liferay.util.portlet.PortletProps;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletPreferences;

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
			PortletKeys.COMMUNITIES);

		portlet.setAddDefaultResource(true);

		// Directory

		portlet = PortletLocalServiceUtil.getPortletById(
			PortletKeys.DIRECTORY);

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

	protected static Layout addLayout(
			Group group, String name, String friendlyURL,
			String layouteTemplateId)
		throws Exception {

		boolean privateLayout = true;

		ServiceContext serviceContext = new ServiceContext();

		Layout layout = LayoutLocalServiceUtil.addLayout(
			group.getCreatorUserId(), group.getGroupId(), privateLayout,
			LayoutConstants.DEFAULT_PARENT_LAYOUT_ID, name, StringPool.BLANK,
			StringPool.BLANK, LayoutConstants.TYPE_PORTLET, false, friendlyURL,
			serviceContext);

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		layoutTypePortlet.setLayoutTemplateId(0, layouteTemplateId, false);

		return LayoutLocalServiceUtil.updateLayout(
			layout.getGroupId(), layout.isPrivateLayout(), layout.getLayoutId(),
			layout.getTypeSettings());
	}

	protected static LayoutSetPrototype addLayoutSetPrototype(
			long companyId, String name)
		throws Exception {

		Map<Locale, String> localeNamesMap = new HashMap<Locale, String>();

		Locale defaultLocale = LocaleUtil.getDefault();

		localeNamesMap.put(defaultLocale, name);

		String description = name + " Site Template";
		boolean active = true;
		long defaultUserId = UserLocalServiceUtil.getDefaultUserId(companyId);

		ServiceContext serviceContext = new ServiceContext();

		LayoutSetPrototype layoutSetPrototype =
			LayoutSetPrototypeLocalServiceUtil.addLayoutSetPrototype(
			defaultUserId, companyId, localeNamesMap, description, active,
			serviceContext);

		return layoutSetPrototype;
	}

	protected static void addPortlets(Group group, Layout layout, String name)
		throws Exception {

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		LayoutTemplate layoutTemplate = layoutTypePortlet.getLayoutTemplate();

		List<String> columns = layoutTemplate.getColumns();

		for (String column : columns) {
			String keyPrefix = PortletPropsKeys.SITE_PROTOTYPE_PORTLETS;
			Filter filter = new Filter(name);

			String[] portletIds = PortletProps.getArray(
				keyPrefix + column, filter);

			String portlets = StringPool.BLANK;

			for (String portletId : portletIds) {
				portlets = StringUtil.add(portlets, portletId);
			}

			layoutTypePortlet.setPortletIds(column, portlets);
		}

		LayoutLocalServiceUtil.updateLayout(
			layout.getGroupId(), layout.isPrivateLayout(), layout.getLayoutId(),
			layout.getTypeSettings());

		List<String> portletIds = layoutTypePortlet.getPortletIds();

		for (String portletId : portletIds) {
			addResources(layout, portletId);

			if (portletId.equals("1_WAR_wysiwygportlet")) {
				updatePortletTitle(layout, "1_WAR_wysiwygportlet", "Welcome");
			}
			else if (portletId.equals(PortletKeys.ALERTS)) {
				updatePortletTitle(layout, PortletKeys.ALERTS, "Announcements");
			}
		}
	}

	protected static void addResources(Layout layout, String portletId)
		throws Exception {

		String rootPortletId = PortletConstants.getRootPortletId(portletId);

		String portletPrimaryKey = PortletPermissionUtil.getPrimaryKey(
			layout.getPlid(), portletId);

		ResourceLocalServiceUtil.addResources(
			layout.getCompanyId(), layout.getGroupId(), 0, rootPortletId,
			portletPrimaryKey, true, true, true);
	}

	protected static void configureAssetPublisher(Layout layout)
		throws Exception {

		PortletPreferences portletSetup =
			PortletPreferencesFactoryUtil.getLayoutPortletSetup(
				layout, "101_INSTANCE_abcd");

		portletSetup.setValue("display-style", "title-list");
		portletSetup.setValue("asset-link-behaviour", "viewInPortlet");

		portletSetup.store();
	}

	protected static void configureMessageBoards(Layout layout)
		throws Exception {

		PortletPreferences portletSetup =
			PortletPreferencesFactoryUtil.getLayoutPortletSetup(
				layout, PortletKeys.MESSAGE_BOARDS);

		String[] ranks = {
			"Bronze=0",
			"Silver=25",
			"Gold=100",
			"Platinum=250",
			"Moderator=community-role:Message Boards Administrator",
			"Moderator=organization:Message Boards Administrator",
			"Moderator=organization-role:Message Boards Administrator",
			"Moderator=regular-role:Message Boards Administrator",
			"Moderator=user-group:Message Boards Administrator"
		};

		portletSetup.setValues("ranks", ranks);

		portletSetup.store();
	}

	protected static void configureRSS(Layout layout) throws Exception {
		PortletPreferences portletSetup =
			PortletPreferencesFactoryUtil.getLayoutPortletSetup(
				layout, "39_INSTANCE_abcd");

		portletSetup.setValue("items-per-channel", "3");
		portletSetup.setValue("show-feed-title", "false");
		portletSetup.setValue("show-feed-published-date", "false");
		portletSetup.setValue("show-feed-description", "false");
		portletSetup.setValue("show-feed-image", "false");
		portletSetup.setValue("show-feed-item-author", "false");
		portletSetup.setValue(
			"urls",
			"http://www.economist.com/rss/daily_news_and_views_rss.xml");

		portletSetup.store();
	}

	protected static void removePortletBorder(Layout layout, String portletId)
		throws Exception {

		PortletPreferences portletSetup =
			PortletPreferencesFactoryUtil.getLayoutPortletSetup(
				layout, portletId);

		portletSetup.setValue(
			"portlet-setup-show-borders", String.valueOf(Boolean.FALSE));

		portletSetup.store();
	}

	protected static void setupExpando(long companyId) throws Exception {
		ExpandoTable expandoTable = null;

		// Group

		try {
			expandoTable = ExpandoTableLocalServiceUtil.addTable(
				Group.class.getName(),
				ExpandoTableConstants.DEFAULT_TABLE_NAME);
		}
		catch (Exception e) {
			expandoTable = ExpandoTableLocalServiceUtil.getTable(
				Group.class.getName(),
				ExpandoTableConstants.DEFAULT_TABLE_NAME);
		}

		try {
			ExpandoColumnLocalServiceUtil.addColumn(
				expandoTable.getTableId(), "socialOfficeEnabled",
				ExpandoColumnConstants.BOOLEAN);
		}
		catch (Exception e) {
		}

		// Layout set prototype

		try {
			expandoTable = ExpandoTableLocalServiceUtil.addTable(
				LayoutSetPrototype.class.getName(),
				ExpandoTableConstants.DEFAULT_TABLE_NAME);
		}
		catch (Exception e) {
			expandoTable = ExpandoTableLocalServiceUtil.getTable(
				LayoutSetPrototype.class.getName(),
				ExpandoTableConstants.DEFAULT_TABLE_NAME);
		}

		try {
			ExpandoColumnLocalServiceUtil.addColumn(
				expandoTable.getTableId(), "socialOfficeDefault",
				ExpandoColumnConstants.BOOLEAN);
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
			companyId, "Social Office Community");

		ExpandoValueLocalServiceUtil.addValue(
			LayoutSetPrototype.class.getName(),
			ExpandoTableConstants.DEFAULT_TABLE_NAME, "socialOfficeDefault",
			layoutSetPrototype.getLayoutSetPrototypeId(), true);

		Group group = layoutSetPrototype.getGroup();

		LayoutLocalServiceUtil.deleteLayouts(group.getGroupId(), true);

		LayoutSetLocalServiceUtil.updateLookAndFeel(
			group.getGroupId(), "so_WAR_sotheme", "01", "", false);

		// Home

		Layout layout = addLayout(
			group, "Home", "/home",
			PortletPropsValues.SITE_PROTOTYPE_LAYOUT_TEMPLATE);

		addPortlets(group, layout, "/home");

		configureRSS(layout);

		updatePermissions(layout, true);

		// Calendar

		layout = addLayout(group, "Calendar", "/calendar", "1_column");

		addPortlets(group, layout, "/calendar");

		updatePermissions(layout, true);

		// Documents

		layout = addLayout(group, "Documents", "/documents", "2_columns_iii");

		addPortlets(group, layout, "/documents");

		configureAssetPublisher(layout);

		updatePermissions(layout, true);

		// Forums

		layout = addLayout(group, "Forums", "/forums", "2_columns_iii");

		addPortlets(group, layout, "/forums");

		configureAssetPublisher(layout);
		configureMessageBoards(layout);

		updatePermissions(layout, true);

		// Blog

		layout = addLayout(group, "Blog", "/blog", "2_columns_iii");

		addPortlets(group, layout, "/blog");

		configureAssetPublisher(layout);

		updatePermissions(layout, true);

		// Wiki

		layout = addLayout(group, "Wiki", "/wiki", "2_columns_iii");

		addPortlets(group, layout, "/wiki");

		configureAssetPublisher(layout);

		updatePermissions(layout, true);

		// Members

		layout = addLayout(group, "Members", "/members", "1_column");

		addPortlets(group, layout, "/members");

		removePortletBorder(layout, "2_WAR_soportlet");

		updatePermissions(layout, true);
	}

	protected static void setupUsers(long companyId) throws Exception {
		List<User> users = UserLocalServiceUtil.search(
			companyId, null, WorkflowConstants.STATUS_ANY, null,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, (OrderByComparator)null);

		for (User user : users) {
			Group group = user.getGroup();

			LayoutSetLocalServiceUtil.deleteLayoutSet(group.getGroupId(), true);
			LayoutSetLocalServiceUtil.deleteLayoutSet(
				group.getGroupId(), false);

			LayoutSetLocalServiceUtil.addLayoutSet(group.getGroupId(), true);
			LayoutSetLocalServiceUtil.addLayoutSet(group.getGroupId(), false);
		}
	}

	protected static void updatePermissions(
			Layout layout, boolean addDefaultActionIds)
		throws Exception {

		long companyId = layout.getCompanyId();

		Role role = RoleLocalServiceUtil.getRole(
			companyId, RoleConstants.GUEST);

		String[] actionIds = new String[0];

		String name = Layout.class.getName();
		int scope = ResourceConstants.SCOPE_INDIVIDUAL;
		String primKey = String.valueOf(layout.getPrimaryKey());

		ResourcePermissionLocalServiceUtil.setResourcePermissions(
			companyId, name, scope, primKey, role.getRoleId(), actionIds);

		role = RoleLocalServiceUtil.getRole(
			companyId, RoleConstants.POWER_USER);

		ResourcePermissionLocalServiceUtil.setResourcePermissions(
			companyId, name, scope, primKey, role.getRoleId(), actionIds);

		if (addDefaultActionIds) {
			actionIds = new String[] {ActionKeys.VIEW};
		}

		role = RoleLocalServiceUtil.getRole(companyId, RoleConstants.USER);

		ResourcePermissionLocalServiceUtil.setResourcePermissions(
			companyId, name, scope, primKey, role.getRoleId(), actionIds);
	}

	protected static void updatePortletTitle(
			Layout layout, String portletId, String title)
		throws Exception {

		PortletPreferences portletSetup =
			PortletPreferencesFactoryUtil.getLayoutPortletSetup(
				layout, portletId);

		Locale[] locales = LanguageUtil.getAvailableLocales();

		for (Locale locale : locales) {
			String languageId = LocaleUtil.toLanguageId(locale);

			if (Validator.isNotNull(languageId)) {
				String localizedTitle = LanguageUtil.get(locale, title);

				portletSetup.setValue(
					"portlet-setup-title-" + languageId, localizedTitle);
			}
		}

		portletSetup.setValue(
			"portlet-setup-use-custom-title", String.valueOf(Boolean.TRUE));

		portletSetup.store();
	}

	private static Log _log = LogFactoryUtil.getLog(InstanceUtil.class);

}