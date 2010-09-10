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

package com.liferay.so.hook.listeners;

import com.liferay.portal.ModelListenerException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModelListener;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.model.LayoutTemplate;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.model.PortletConstants;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.service.ResourceLocalServiceUtil;
import com.liferay.portal.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.permission.PortletPermissionUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.so.util.PortletPropsKeys;
import com.liferay.so.util.PortletPropsValues;
import com.liferay.util.portlet.PortletProps;

import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.portlet.PortletPreferences;

/**
 * @author Brian Wing Shun Chan
 */
public class LayoutSetListener extends BaseModelListener<LayoutSet> {

	public void onAfterCreate(LayoutSet layoutSet)
		throws ModelListenerException {

		try {
			if (layoutSet.isPrivateLayout()) {
				return;
			}

			Group group = GroupLocalServiceUtil.getGroup (
				layoutSet.getGroupId());

			String name = group.getName();

			if (name.equals(GroupConstants.GUEST)) {
				return;
			}

			if (group.isCommunity()) {
				addCommunityLayouts(group);
				updateFriendlyURL(group);
			}
			else if (group.isUser()) {
				addUserLayouts(group);
			}
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	protected void addCommunityLayouts(Group group) throws Exception {

		// Look and Feel

		updateLookAndFeel(group);

		// Home

		Layout layout = addLayout(
			group, "Home", "/home", PortletPropsValues.SITE_LAYOUT_TEMPLATE);

		addPortlets(group, layout);

		configureRSS(layout);

		updatePermissions(layout, true);

		// Calendar

		layout = addLayout(group, "Calendar", "/calendar", "1_column");

		addResources(layout, PortletKeys.CALENDAR);

		removePortletBorder(layout, PortletKeys.CALENDAR);

		updatePermissions(layout, true);

		// Documents

		layout = addLayout(group, "Documents", "/documents", "2_columns_iii");

		addResources(layout, PortletKeys.DOCUMENT_LIBRARY);
		addResources(layout, "101_INSTANCE_abcd");

		removePortletBorder(layout, PortletKeys.DOCUMENT_LIBRARY);

		configureAssetPublisher(layout);

		updatePermissions(layout, true);

		// Forums

		layout = addLayout(group, "Forums", "/forums", "2_columns_iii");

		addResources(layout, PortletKeys.MESSAGE_BOARDS);
		addResources(layout, "101_INSTANCE_abcd");

		removePortletBorder(layout, PortletKeys.MESSAGE_BOARDS);

		configureAssetPublisher(layout);
		configureMessageBoards(layout);

		updatePermissions(layout, true);

		// Blog

		layout = addLayout(group, "Blog", "/blog", "2_columns_iii");

		addResources(layout, PortletKeys.BLOGS);
		addResources(layout, "101_INSTANCE_abcd");

		removePortletBorder(layout, PortletKeys.BLOGS);

		configureAssetPublisher(layout);

		updatePermissions(layout, true);

		// Wiki

		layout = addLayout(group, "Wiki", "/wiki", "2_columns_iii");

		addResources(layout, PortletKeys.WIKI);
		addResources(layout, "101_INSTANCE_abcd");

		removePortletBorder(layout, PortletKeys.WIKI);

		configureAssetPublisher(layout);

		updatePermissions(layout, true);

		// Members

		layout = addLayout(group, "Members", "/members", "2_columns_ii");

		addResources(layout, "2_WAR_soportlet");
		addResources(layout, "3_WAR_soportlet");
		addResources(layout, "4_WAR_soportlet");

		removePortletBorder(layout, "2_WAR_soportlet");
		removePortletBorder(layout, "3_WAR_soportlet");
		removePortletBorder(layout, "4_WAR_soportlet");

		updatePermissions(layout, true);
	}

	protected void addUserLayouts(Group group) throws Exception {

		// Look and Feel

		updateLookAndFeel(group);

		// Home

		Layout layout = addLayout(
			group, "Home", "/home", PortletPropsValues.USER_LAYOUT_TEMPLATE);

		addPortlets(group, layout);

		updatePermissions(layout, false);

		// Profile

		layout = addLayout(group, "Profile", "/profile", "2_columns_ii");

		addResources(layout, "1_WAR_soportlet");
		addResources(layout, "4_WAR_soportlet");

		removePortletBorder(layout, "4_WAR_soportlet");

		updatePermissions(layout, true);

		// Mail

		layout = addLayout(group, "Mail", "/mail", "1_column");

		addResources(layout, "1_WAR_mailportlet");

		updatePermissions(layout, false);
	}

	protected Layout addLayout(
			Group group, String name, String friendlyURL,
			String layouteTemplateId)
		throws Exception {

		boolean privateLayout = false;

		if (group.getType() == GroupConstants.TYPE_COMMUNITY_PRIVATE) {
			privateLayout = true;
		}

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

	protected void addPortlets(Group group, Layout layout) throws Exception {
		String prefix = PortletPropsKeys.SITE_LAYOUT_PORTLETS;

		if (group.isUser()) {
			prefix = PortletPropsKeys.USER_LAYOUT_PORTLETS;
		}

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		LayoutTemplate layoutTemplate = layoutTypePortlet.getLayoutTemplate();

		List<String> columns = layoutTemplate.getColumns();

		for (String column : columns) {
			layoutTypePortlet.setPortletIds(
				column, PortletProps.get(prefix + column));
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

	protected void addResources(Layout layout, String portletId)
		throws Exception{

		String rootPortletId = PortletConstants.getRootPortletId(portletId);

		String portletPrimaryKey = PortletPermissionUtil.getPrimaryKey(
			layout.getPlid(), portletId);

		ResourceLocalServiceUtil.addResources(
			layout.getCompanyId(), layout.getGroupId(), 0, rootPortletId,
			portletPrimaryKey, true, true, true);
	}

	protected void configureAssetPublisher(Layout layout) throws Exception {
		PortletPreferences portletSetup =
			PortletPreferencesFactoryUtil.getLayoutPortletSetup(
				layout, "101_INSTANCE_abcd");

		portletSetup.setValue("display-style", "title-list");
		portletSetup.setValue("asset-link-behaviour", "viewInPortlet");

		portletSetup.store();
	}

	protected void configureMessageBoards(Layout layout) throws Exception {
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

	protected void configureRSS(Layout layout) throws Exception {
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

	protected void removePortletBorder(Layout layout, String portletId)
		throws Exception {

		PortletPreferences portletSetup =
			PortletPreferencesFactoryUtil.getLayoutPortletSetup(
				layout, portletId);

		portletSetup.setValue(
			"portlet-setup-show-borders", String.valueOf(Boolean.FALSE));

		portletSetup.store();
	}

	protected void updateFriendlyURL(Group group) {
		String name = group.getName();

		Pattern pattern = Pattern.compile("[^0-9a-zA-Z]");

		Matcher matcher = pattern.matcher(name);

		String friendlyURL = matcher.replaceAll("");

		friendlyURL = "/" + friendlyURL.toLowerCase();

		updateFriendlyURL(group.getGroupId(), friendlyURL, 0);
	}

	protected void updateFriendlyURL(
		long groupId, String friendlyURL, int index) {

		try {
			if (index == 0) {
				GroupLocalServiceUtil.updateFriendlyURL(groupId, friendlyURL);
			}
			else {
				GroupLocalServiceUtil.updateFriendlyURL(
					groupId, friendlyURL + index);
			}
		}
		catch (Exception e) {
			updateFriendlyURL(groupId, friendlyURL, index + 1);
		}
	}

	protected void updateLookAndFeel(Group group) throws Exception {
		LayoutSetLocalServiceUtil.updateLookAndFeel(
			group.getGroupId(), "so_WAR_sotheme", "01", "", false);
	}

	protected void updatePermissions(Layout layout, boolean addDefaultActionIds)
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

	protected void updatePortletTitle(
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