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

package com.liferay.so.util;

import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.model.LayoutSetPrototype;
import com.liferay.portal.model.LayoutTemplate;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.PortletApp;
import com.liferay.portal.model.PortletConstants;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.CompanyLocalServiceUtil;
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
import com.liferay.portlet.calendar.model.CalEvent;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.wiki.model.WikiNode;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.util.portlet.PortletProps;

import java.io.InputStream;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.portlet.PortletPreferences;

/**
 * @author Brian Wing Shun Chan
 * @author Ryan Park
 * @author Jonathan Lee
 */
public class InstanceUtil {

	public static void initCommunity(long companyId) throws Exception {
		setupCommunity(companyId);
	}

	public static void initInstance(long companyId) {
		try {
			PortletPreferencesThreadLocal.setStrict(false);

			setupCompany(companyId);
			setupPermissions(companyId);
			setupLoginLayout(companyId);
			setupCommunity(companyId);
			setupUsers(companyId);
		}
		catch (Exception e) {
		}
		finally {
			PortletPreferencesThreadLocal.setStrict(true);
		}
	}

	public static void initRuntime(long companyId) {
		Set<String> applicationsAllowed = SetUtil.fromArray(
			PortletPropsValues.APPLICATIONS_ALLOWED);
		Set<String> controlPanelItems = SetUtil.fromArray(
			PortletPropsValues.CONTROL_PANEL_ITEMS);

		List<Portlet> portlets = PortletLocalServiceUtil.getPortlets();

		for (Portlet portlet : portlets) {
			PortletApp portletApp = portlet.getPortletApp();

			if (portletApp.isWARFile()) {
				continue;
			}

			String portletId = portlet.getPortletId();

			if (applicationsAllowed.contains(portletId)) {
				portlet.setSystem(false);
			}
			else {
				portlet.setSystem(true);
			}

			if (!controlPanelItems.contains(portletId)) {
				portlet.setControlPanelEntryCategory(StringPool.BLANK);
			}
		}

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

		LayoutSetPrototype layoutSetPrototype =
			LayoutSetPrototypeLocalServiceUtil.addLayoutSetPrototype(
			defaultUserId, companyId, localeNamesMap, description, active);

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

	protected static void setupCommunity(long companyId)
		throws Exception {

		LayoutSetPrototype layoutSetPrototype = addLayoutSetPrototype(
			companyId, "Social Office Community");

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

		removePortletBorder(layout, PortletKeys.CALENDAR);

		updatePermissions(layout, true);

		// Documents

		layout = addLayout(group, "Documents", "/documents", "2_columns_iii");

		addPortlets(group, layout, "/documents");

		removePortletBorder(layout, PortletKeys.DOCUMENT_LIBRARY);

		configureAssetPublisher(layout);

		updatePermissions(layout, true);

		// Forums

		layout = addLayout(group, "Forums", "/forums", "2_columns_iii");

		addPortlets(group, layout, "/forums");

		removePortletBorder(layout, PortletKeys.MESSAGE_BOARDS);

		configureAssetPublisher(layout);
		configureMessageBoards(layout);

		updatePermissions(layout, true);

		// Blog

		layout = addLayout(group, "Blog", "/blog", "2_columns_iii");

		addPortlets(group, layout, "/blog");

		removePortletBorder(layout, PortletKeys.BLOGS);

		configureAssetPublisher(layout);

		updatePermissions(layout, true);

		// Wiki

		layout = addLayout(group, "Wiki", "/wiki", "2_columns_iii");

		addPortlets(group, layout, "/wiki");

		removePortletBorder(layout, PortletKeys.WIKI);

		configureAssetPublisher(layout);

		updatePermissions(layout, true);

		// Members

		layout = addLayout(group, "Members", "/members", "2_columns_ii");

		addPortlets(group, layout, "/members");

		removePortletBorder(layout, "2_WAR_soportlet");
		removePortletBorder(layout, "3_WAR_soportlet");
		removePortletBorder(layout, "4_WAR_soportlet");

		updatePermissions(layout, true);
	}

	protected static void setupCompany(long companyId) throws Exception {

		// Security settings

		String authType = CompanyConstants.AUTH_TYPE_SN;
		boolean autoLogin = true;
		boolean sendPassword = true;
		boolean strangers = true;
		boolean strangersWithMx = true;
		boolean strangersVerify = false;
		boolean communityLogo = false;

		CompanyLocalServiceUtil.updateSecurity(
			companyId, authType, autoLogin, sendPassword, strangers,
			strangersWithMx, strangersVerify, communityLogo);

		// Company logo

		ClassLoader classLoader = InstanceUtil.class.getClassLoader();

		InputStream inputStream = classLoader.getResourceAsStream(
			"/resources/logo.png");

		CompanyLocalServiceUtil.updateLogo(companyId, inputStream);
	}

	protected static void setupLoginLayout(long companyId)
		throws Exception {

		Group group = GroupLocalServiceUtil.getGroup(
			companyId, GroupConstants.GUEST);

		Layout layout = LayoutLocalServiceUtil.getLayout(
			group.getGroupId(), false, 1);

		long defaultUserId = UserLocalServiceUtil.getDefaultUserId(
			layout.getCompanyId());

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		layoutTypePortlet.setLayoutTemplateId(0, "1_column", false);

		if (layoutTypePortlet.hasPortletId("47")) {
			layoutTypePortlet.removePortletId(defaultUserId, "47");
		}

		if (!layoutTypePortlet.hasPortletId(PortletKeys.LOGIN)) {
			LayoutTemplate layoutTemplate =
				layoutTypePortlet.getLayoutTemplate();

			List<String> columns = layoutTemplate.getColumns();

			layoutTypePortlet.setPortletIds(columns.get(0), PortletKeys.LOGIN);
		}

		LayoutLocalServiceUtil.updateLayout(
			layout.getGroupId(), layout.isPrivateLayout(), layout.getLayoutId(),
			layout.getTypeSettings());

		LayoutLocalServiceUtil.updateFriendlyURL(layout.getPlid(), "/login");

		LayoutSetLocalServiceUtil.updateLookAndFeel(
			layout.getGroupId(), false, "so_WAR_sotheme", "01", "", false);
	}

	protected static void setupPermissions(long companyId) throws Exception {

		// Community Member - Blogs

		Role role = RoleLocalServiceUtil.getRole(
			companyId, RoleConstants.COMMUNITY_MEMBER);

		long roleId = role.getRoleId();
		String name = "com.liferay.portlet.blogs";
		int scope = ResourceConstants.SCOPE_GROUP_TEMPLATE;
		String primKey = String.valueOf(GroupConstants.DEFAULT_PARENT_GROUP_ID);
		String[] actionIds = new String[] {ActionKeys.ADD_ENTRY};

		ResourcePermissionLocalServiceUtil.setResourcePermissions(
			companyId, name, scope, primKey, roleId, actionIds);

		// Community Member - Calendar

		name = "com.liferay.portlet.calendar";
		actionIds = new String[] {
			ActionKeys.ADD_EVENT, ActionKeys.EXPORT_ALL_EVENTS
		};

		ResourcePermissionLocalServiceUtil.setResourcePermissions(
			companyId, name, scope, primKey, roleId, actionIds);

		// Community Member - Calendar Event

		name = CalEvent.class.getName();
		actionIds = new String[] {ActionKeys.VIEW};

		ResourcePermissionLocalServiceUtil.setResourcePermissions(
			companyId, name, scope, primKey, roleId, actionIds);

		// Community Member - Community

		name = Group.class.getName();
		actionIds = new String[] {ActionKeys.ASSIGN_MEMBERS};

		ResourcePermissionLocalServiceUtil.setResourcePermissions(
			companyId, name, scope, primKey, roleId, actionIds);

		// Community Member - Document Library

		name = "com.liferay.portlet.documentlibrary";
		actionIds = new String[] {
			ActionKeys.ADD_FOLDER, ActionKeys.ADD_DOCUMENT
		};

		ResourcePermissionLocalServiceUtil.setResourcePermissions(
			companyId, name, scope, primKey, roleId, actionIds);

		// Community Member - Document Library Document

		name = DLFileEntry.class.getName();
		actionIds = new String[] {
			ActionKeys.ADD_DISCUSSION, ActionKeys.UPDATE, ActionKeys.VIEW
		};

		ResourcePermissionLocalServiceUtil.setResourcePermissions(
			companyId, name, scope, primKey, roleId, actionIds);

		// Community Member - Document Library Folder

		name = DLFolder.class.getName();
		actionIds = new String[] {
			ActionKeys.ADD_DOCUMENT, ActionKeys.ADD_SUBFOLDER, ActionKeys.VIEW
		};

		ResourcePermissionLocalServiceUtil.setResourcePermissions(
			companyId, name, scope, primKey, roleId, actionIds);

		// Community Member - Message Boards

		name = "com.liferay.portlet.messageboards";
		actionIds = new String[] {ActionKeys.ADD_CATEGORY};

		ResourcePermissionLocalServiceUtil.setResourcePermissions(
			companyId, name, scope, primKey, roleId, actionIds);

		// Community Member - Message Boards Category

		name = MBCategory.class.getName();
		actionIds = new String[] {
			ActionKeys.ADD_FILE, ActionKeys.ADD_MESSAGE,
			ActionKeys.ADD_SUBCATEGORY, ActionKeys.MOVE_THREAD,
			ActionKeys.REPLY_TO_MESSAGE, ActionKeys.SUBSCRIBE, ActionKeys.VIEW
		};

		ResourcePermissionLocalServiceUtil.setResourcePermissions(
			companyId, name, scope, primKey, roleId, actionIds);

		// Community Member - Message Boards Message

		name = MBMessage.class.getName();
		actionIds = new String[] {ActionKeys.VIEW};

		ResourcePermissionLocalServiceUtil.setResourcePermissions(
			companyId, name, scope, primKey, roleId, actionIds);

		// Community Member - Wiki

		name = "com.liferay.portlet.wiki";
		actionIds = new String[] {ActionKeys.ADD_NODE};

		ResourcePermissionLocalServiceUtil.setResourcePermissions(
			companyId, name, scope, primKey, roleId, actionIds);

		// Community Member - Wiki Node

		name = WikiNode.class.getName();
		actionIds = new String[] {
			ActionKeys.ADD_ATTACHMENT, ActionKeys.ADD_PAGE,
			ActionKeys.SUBSCRIBE, ActionKeys.VIEW
		};

		ResourcePermissionLocalServiceUtil.setResourcePermissions(
			companyId, name, scope, primKey, roleId, actionIds);

		// Community Member - Wiki Page

		name = WikiPage.class.getName();
		actionIds = new String[] {
			ActionKeys.ADD_DISCUSSION, ActionKeys.DELETE, ActionKeys.SUBSCRIBE,
			ActionKeys.UPDATE, ActionKeys.VIEW
		};

		ResourcePermissionLocalServiceUtil.setResourcePermissions(
			companyId, name, scope, primKey, roleId, actionIds);

		// Community Owner - Announcements

		role = RoleLocalServiceUtil.getRole(
			companyId, RoleConstants.COMMUNITY_OWNER);

		roleId = role.getRoleId();
		name = PortletKeys.ANNOUNCEMENTS;
		actionIds = new String[] {ActionKeys.ADD_ENTRY};

		ResourcePermissionLocalServiceUtil.setResourcePermissions(
			companyId, name, scope, primKey, roleId, actionIds);

		// Power User - Add Community

		role = RoleLocalServiceUtil.getRole(
			companyId, RoleConstants.POWER_USER);

		roleId = role.getRoleId();
		name = PortletKeys.PORTAL;
		scope = ResourceConstants.SCOPE_COMPANY;
		primKey = String.valueOf(companyId);
		actionIds = new String[] {ActionKeys.ADD_COMMUNITY};

		ResourcePermissionLocalServiceUtil.setResourcePermissions(
			companyId, name, scope, primKey, roleId, actionIds);

		// Power User - Directory

		name = PortletKeys.DIRECTORY;
		actionIds = new String[] {ActionKeys.VIEW};

		ResourcePermissionLocalServiceUtil.setResourcePermissions(
			companyId, name, scope, primKey, roleId, actionIds);

		// Power User - Document Library

		name = "com.liferay.portlet.documentlibrary";

		ResourcePermissionLocalServiceUtil.setResourcePermissions(
			companyId, name, scope, primKey, roleId, actionIds);
	}

	protected static void setupUsers(long companyId) throws Exception {
		List<User> users = UserLocalServiceUtil.search(
			companyId, null, null, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			(OrderByComparator)null);

		for (User user : users) {
			UserLocalServiceUtil.deleteUser(user.getUserId());
		}

		long creatorUserId = 0;
		boolean autoPassword = false;
		String password1 = "admin";
		String password2 = password1;
		boolean autoScreenName = false;
		String screenName = "admin";
		String emailAddress = "admin@admin.com";
		long facebookId = 0;
		String openId = StringPool.BLANK;
		Locale locale = Locale.US;
		String firstName = "Admin";
		String middleName = StringPool.BLANK;
		String lastName = "Admin";
		int prefixId = 0;
		int suffixId = 0;
		boolean male = true;
		int birthdayMonth = Calendar.JANUARY;
		int birthdayDay = 1;
		int birthdayYear = 1970;
		String jobTitle = StringPool.BLANK;
		long[] groupIds = null;
		long[] organizationIds = null;
		long[] userGroupIds = null;

		Role adminRole = RoleLocalServiceUtil.getRole(
			companyId, RoleConstants.ADMINISTRATOR);

		Role powerUserRole = RoleLocalServiceUtil.getRole(
			companyId, RoleConstants.POWER_USER);

		long[] roleIds = new long[] {
			adminRole.getRoleId(), powerUserRole.getRoleId()
		};

		boolean sendEmail = false;
		ServiceContext serviceContext = null;

		UserLocalServiceUtil.addUser(
			creatorUserId, companyId, autoPassword, password1, password2,
			autoScreenName, screenName, emailAddress, facebookId, openId,
			locale, firstName, middleName, lastName, prefixId, suffixId, male,
			birthdayMonth, birthdayDay, birthdayYear, jobTitle, groupIds,
			organizationIds, roleIds, userGroupIds, sendEmail, serviceContext);
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

}