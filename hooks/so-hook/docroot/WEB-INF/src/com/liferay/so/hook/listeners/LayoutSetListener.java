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

package com.liferay.so.hook.listeners;

import com.liferay.portal.ModelListenerException;
import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
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
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.so.util.PortletPropsKeys;
import com.liferay.so.util.PortletPropsValues;
import com.liferay.util.portlet.PortletProps;

import java.util.List;
import java.util.Locale;

import javax.portlet.PortletPreferences;

/**
 * @author Brian Wing Shun Chan
 * @author Ryan Park
 * @author Jonathan Lee
 */
public class LayoutSetListener extends BaseModelListener<LayoutSet> {

	public void onAfterCreate(LayoutSet layoutSet)
		throws ModelListenerException {

		try {
			if (layoutSet.isPrivateLayout()) {
				return;
			}

			Group group = GroupLocalServiceUtil.getGroup(
				layoutSet.getGroupId());

			if (group.isUser()) {
				addUserLayouts(group);
			}
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	protected void addUserLayouts(Group group) throws Exception {

		// Look and Feel

		LayoutSetLocalServiceUtil.updateLookAndFeel(
			group.getGroupId(), "so_WAR_sotheme", "01", "", false);

		// Home

		Layout layout = addLayout(
			group, "Home", "/home", PortletPropsValues.USER_LAYOUT_TEMPLATE);

		addPortlets(group, layout, "/home");

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

	protected void addPortlets(Group group, Layout layout, String name)
		throws Exception {

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		LayoutTemplate layoutTemplate = layoutTypePortlet.getLayoutTemplate();

		List<String> columns = layoutTemplate.getColumns();

		for (String column : columns) {
			String keyPrefix = PortletPropsKeys.SITE_PROTOTYPE_PORTLETS;

			if (group.isUser()) {
				keyPrefix = PortletPropsKeys.USER_LAYOUT_PORTLETS;
			}

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

	protected void addResources(Layout layout, String portletId)
		throws Exception{

		String rootPortletId = PortletConstants.getRootPortletId(portletId);

		String portletPrimaryKey = PortletPermissionUtil.getPrimaryKey(
			layout.getPlid(), portletId);

		ResourceLocalServiceUtil.addResources(
			layout.getCompanyId(), layout.getGroupId(), 0, rootPortletId,
			portletPrimaryKey, true, true, true);
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