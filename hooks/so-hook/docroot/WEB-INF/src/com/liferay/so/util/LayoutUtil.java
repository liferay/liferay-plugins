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

import com.liferay.asset.publisher.web.constants.AssetPublisherPortletKeys;
import com.liferay.breadcrumb.web.constants.BreadcrumbPortletKeys;
import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.model.LayoutTemplate;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.model.PortletConstants;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.ResourceLocalServiceUtil;
import com.liferay.portal.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.permission.PortletPermissionUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.util.portlet.PortletProps;
import com.liferay.wiki.constants.WikiPortletKeys;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletPreferences;

/**
 * @author Jonathan Lee
 */
public class LayoutUtil {

	public static Layout addLayout(
			Group group, boolean privateLayout, long parentLayoutId,
			Map<Locale, String> nameMap, String friendlyURL,
			String layoutTemplateId)
		throws Exception {

		ServiceContext serviceContext = new ServiceContext();

		Layout layout = LayoutLocalServiceUtil.addLayout(
			group.getCreatorUserId(), group.getGroupId(), privateLayout,
			parentLayoutId, nameMap, null, null, null, null,
			LayoutConstants.TYPE_PORTLET, false, friendlyURL, serviceContext);

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		layoutTypePortlet.setLayoutTemplateId(0, layoutTemplateId, false);

		return LayoutLocalServiceUtil.updateLayout(
			layout.getGroupId(), layout.isPrivateLayout(), layout.getLayoutId(),
			layout.getTypeSettings());
	}

	public static Layout addLayout(
			Group group, boolean privateLayout, long parentLayoutId,
			String name, String friendlyURL, String layoutTemplateId)
		throws Exception {

		ServiceContext serviceContext = new ServiceContext();

		Layout layout = LayoutLocalServiceUtil.addLayout(
			group.getCreatorUserId(), group.getGroupId(), privateLayout,
			parentLayoutId, name, StringPool.BLANK, StringPool.BLANK,
			LayoutConstants.TYPE_PORTLET, false, friendlyURL, serviceContext);

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		layoutTypePortlet.setLayoutTemplateId(0, layoutTemplateId, false);

		return LayoutLocalServiceUtil.updateLayout(
			layout.getGroupId(), layout.isPrivateLayout(), layout.getLayoutId(),
			layout.getTypeSettings());
	}

	public static void addPortlets(
			Group group, Layout layout, String name, String keyPrefix)
		throws Exception {

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		LayoutTemplate layoutTemplate = layoutTypePortlet.getLayoutTemplate();

		List<String> columns = layoutTemplate.getColumns();

		for (String column : columns) {
			String[] portletIds = null;

			if (Validator.isNull(name)) {
				portletIds = PortletProps.getArray(keyPrefix + column);
			}
			else {
				Filter filter = new Filter(name);

				portletIds = PortletProps.getArray(keyPrefix + column, filter);
			}

			layoutTypePortlet.addPortletIds(0, portletIds, column, false);
		}

		LayoutLocalServiceUtil.updateLayout(
			layout.getGroupId(), layout.isPrivateLayout(), layout.getLayoutId(),
			layout.getTypeSettings());

		List<String> portletIds = layoutTypePortlet.getPortletIds();

		for (String portletId : portletIds) {
			addResources(layout, portletId);

			if (portletId.startsWith("1_WAR_eventsdisplayportlet")) {
				updatePortletTitle(layout, portletId, "events");
			}
			else if (portletId.startsWith("1_WAR_soannouncementsportlet")) {
				updatePortletTitle(layout, portletId, "announcements");
			}
			else if (portletId.startsWith("1_WAR_wysiwygportlet")) {
				updatePortletTitle(layout, portletId, "welcome");
			}
			else if (portletId.contains("_WAR_contactsportlet")) {
				configureProfile(layout, portletId);
				removePortletBorder(layout, portletId);
			}
			else if (portletId.startsWith(
						AssetPublisherPortletKeys.ASSET_PUBLISHER)) {

				configureAssetPublisher(layout);
				updatePortletTitle(layout, portletId, "related-content");
			}
			else if (portletId.startsWith(BreadcrumbPortletKeys.BREADCRUMB)) {
				removePortletBorder(layout, portletId);
			}
			else if (portletId.startsWith(PortletKeys.BLOGS_AGGREGATOR)) {
				configureBlogsAggregator(layout);
				updatePortletTitle(layout, portletId, "recent-blogs");
			}
			else if (portletId.startsWith(PortletKeys.MESSAGE_BOARDS)) {
				configureMessageBoards(layout);
				removePortletBorder(layout, portletId);
			}
			else if (portletId.equals(PortletKeys.DOCUMENT_LIBRARY) ||
					 portletId.equals(PortletKeys.BLOGS) ||
					 portletId.equals(WikiPortletKeys.WIKI) ||
					 portletId.equals("1_WAR_calendarportlet") ||
					 portletId.contains("_WAR_microblogsportlet") ||
					 portletId.equals("1_WAR_privatemessagingportlet") ||
					 portletId.contains("1_WAR_tasksportlet")) {

				removePortletBorder(layout, portletId);
			}
		}
	}

	public static void addResources(Layout layout, String portletId)
		throws PortalException {

		String rootPortletId = PortletConstants.getRootPortletId(portletId);

		String portletPrimaryKey = PortletPermissionUtil.getPrimaryKey(
			layout.getPlid(), portletId);

		ResourceLocalServiceUtil.addResources(
			layout.getCompanyId(), layout.getGroupId(), 0, rootPortletId,
			portletPrimaryKey, true, true, true);
	}

	public static void configureAssetPublisher(Layout layout) throws Exception {
		PortletPreferences portletSetup =
			PortletPreferencesFactoryUtil.getLayoutPortletSetup(
				layout, "101_INSTANCE_abcd");

		portletSetup.setValue("displayStyle", "title-list");
		portletSetup.setValue("assetLinkBehaviour", "viewInPortlet");

		portletSetup.store();
	}

	public static void configureBlogsAggregator(Layout layout)
		throws Exception {

		PortletPreferences portletSetup =
			PortletPreferencesFactoryUtil.getLayoutPortletSetup(
				layout, PortletKeys.BLOGS_AGGREGATOR);

		portletSetup.setValue("enableRssSubscription", "false");
		portletSetup.setValue("selectionMethod", "scope");

		portletSetup.store();
	}

	public static void configureMessageBoards(Layout layout) throws Exception {
		PortletPreferences portletSetup =
			PortletPreferencesFactoryUtil.getLayoutPortletSetup(
				layout, PortletKeys.MESSAGE_BOARDS);

		String[] ranks = {
			"Bronze=0", "Silver=25", "Gold=100", "Platinum=250",
			"Moderator=organization:Message Boards Administrator",
			"Moderator=organization-role:Message Boards Administrator",
			"Moderator=regular-role:Message Boards Administrator",
			"Moderator=site-role:Message Boards Administrator",
			"Moderator=user-group:Message Boards Administrator"
		};

		portletSetup.setValues("ranks", ranks);

		portletSetup.store();
	}

	public static void configureProfile(Layout layout, String portletId)
		throws Exception {

		PortletPreferences portletSetup =
			PortletPreferencesFactoryUtil.getLayoutPortletSetup(
				layout, portletId);

		if (portletId.equals("2_WAR_contactsportlet_INSTANCE_abcd")) {
			portletSetup.setValue("displayStyle", "1");
			portletSetup.setValue("showIcon", "false");
		}
		else if (portletId.equals("2_WAR_contactsportlet_INSTANCE_efgh")) {
			portletSetup.setValue("displayStyle", "2");
			portletSetup.setValue("showCompleteYourProfile", "true");
			portletSetup.setValue("showSites", "false");
			portletSetup.setValue("showRecentActivity", "false");
		}
		else if (portletId.equals("2_WAR_contactsportlet_INSTANCE_ijkl")) {
			portletSetup.setValue("displayStyle", "2");
			portletSetup.setValue("showRecentActivity", "false");
			portletSetup.setValue("showSites", "true");
			portletSetup.setValue("showTags", "true");
			portletSetup.setValue("showUsersInformation", "false");
		}

		portletSetup.store();
	}

	public static void configureRSS(Layout layout) throws Exception {
		PortletPreferences portletSetup =
			PortletPreferencesFactoryUtil.getLayoutPortletSetup(
				layout, "39_INSTANCE_abcd");

		portletSetup.setValue(
			"urls", "http://partners.userland.com/nytRss/technology.xml");
		portletSetup.setValue("expandedEntriesPerFeed", "0");
		portletSetup.setValue("entriesPerFeed", "3");
		portletSetup.setValue("showFeedTitle", "false");
		portletSetup.setValue("showFeedPublishedDate", "false");
		portletSetup.setValue("showFeedDescription", "false");
		portletSetup.setValue("showFeedImage", "false");
		portletSetup.setValue("showFeedItemAuthor", "false");

		portletSetup.store();
	}

	public static void removePortletBorder(Layout layout, String portletId)
		throws Exception {

		PortletPreferences portletSetup =
			PortletPreferencesFactoryUtil.getLayoutPortletSetup(
				layout, portletId);

		portletSetup.setValue(
			"portletSetupShowBorders", String.valueOf(Boolean.FALSE));

		portletSetup.store();
	}

	public static void updatePermissions(
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

	public static void updatePortletTitle(
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
					"portletSetupTitle_" + languageId, localizedTitle);
			}
		}

		portletSetup.setValue(
			"portletSetupUseCustomTitle", String.valueOf(Boolean.TRUE));

		portletSetup.store();
	}

}