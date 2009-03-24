/**
 * Copyright (c) 2008-2009 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.BaseModelListener;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.model.Resource;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.service.PermissionLocalServiceUtil;
import com.liferay.portal.service.ResourceLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.portlet.PortletPreferences;

/**
 * <a href="LayoutSetListener.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
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

		//	Look and Feel

		updateLookAndFeel(group);

		// Home

		Layout layout = addLayout(group, "Home", "/home", "1_column");

		removePortletBorder(layout, "1_WAR_soportlet");

		// Calendar

		layout = addLayout(group, "Calendar", "/calendar", "1_column");

		removePortletBorder(layout, "8");

		// Documents

		layout = addLayout(group, "Documents", "/documents", "2_columns_iii");

		removePortletBorder(layout, "20");

		configureAssetPublisher(layout);

		// Forums

		layout = addLayout(group, "Forums", "/forums", "2_columns_iii");

		removePortletBorder(layout, "19");

		configureAssetPublisher(layout);

		// Blog

		layout = addLayout(group, "Blog", "/blog", "2_columns_iii");

		removePortletBorder(layout, "33");

		configureAssetPublisher(layout);

		// Wiki

		layout = addLayout(group, "Wiki", "/wiki", "2_columns_iii");

		removePortletBorder(layout, "36");

		configureAssetPublisher(layout);

		// Members

		layout = addLayout(group, "Members", "/members", "2_columns_ii");

		removePortletBorder(layout, "2_WAR_soportlet");
		removePortletBorder(layout, "3_WAR_soportlet");
		removePortletBorder(layout, "4_WAR_soportlet");
	}

	protected void addUserLayouts(Group group) throws Exception {

		// Look and Feel

		updateLookAndFeel(group);

		// Home

		Layout layout = addLayout(group, "Home", "/home", "2_columns_ii");

		removePortletBorder(layout, "29");

		updatePermissions(layout);

		// Profile

		layout = addLayout(group, "Profile", "/profile", "2_columns_ii");

		removePortletBorder(layout, "4_WAR_soportlet");

		// Mail

		layout = addLayout(group, "Mail", "/mail", "1_column");

		updatePermissions(layout);
	}

	protected Layout addLayout(
			Group group, String name, String friendlyURL,
			String layouteTemplateId)
		throws Exception {

		boolean privateLayout = false;

		if (group.getType() == GroupConstants.TYPE_COMMUNITY_PRIVATE) {
			privateLayout = true;
		}

		Layout layout = LayoutLocalServiceUtil.addLayout(
			group.getCreatorUserId(), group.getGroupId(), privateLayout,
			LayoutConstants.DEFAULT_PARENT_LAYOUT_ID, name, StringPool.BLANK,
			StringPool.BLANK, LayoutConstants.TYPE_PORTLET, false, friendlyURL);

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		layoutTypePortlet.setLayoutTemplateId(0, layouteTemplateId, false);

		return LayoutLocalServiceUtil.updateLayout(
			layout.getGroupId(), layout.isPrivateLayout(), layout.getLayoutId(),
			layout.getTypeSettings());
	}

	protected void configureAssetPublisher(Layout layout) throws Exception {
		String portletId = "101_INSTANCE_abcd";

		removePortletBorder(layout, portletId);

		PortletPreferences portletSetup =
			PortletPreferencesFactoryUtil.getLayoutPortletSetup(
				layout, portletId);

		portletSetup.setValue("displayStyle", "title-list");

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

	protected void updatePermissions(Layout layout) throws Exception {
		long companyId = layout.getCompanyId();
		String name = Layout.class.getName();
		int scope =	ResourceConstants.SCOPE_INDIVIDUAL;
		String primKey = String.valueOf(layout.getPrimaryKey());

		Resource resource = ResourceLocalServiceUtil.getResource(
			companyId, name, scope, primKey);

		User defaultUser = UserLocalServiceUtil.getDefaultUser(companyId);

		long userId = defaultUser.getUserId();
		String[] actionIds = new String[] {ActionKeys.VIEW};
		long resourceId = resource.getResourceId();

		PermissionLocalServiceUtil.unsetUserPermissions(
			userId, actionIds, resourceId);
	}

}