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

package com.liferay.so.hook.upgrade.v1_5_1;

import com.liferay.portal.NoSuchLayoutException;
import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.model.LayoutTemplate;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.so.util.PortletPropsKeys;
import com.liferay.util.portlet.PortletProps;

import java.util.List;

import javax.portlet.PortletPreferences;

/**
 * @author Ryan Park
 */
public class UpgradeGroup extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		List<Group> groups = GroupLocalServiceUtil.getGroups(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (Group group : groups) {
			Layout layout = null;

			try {
				layout = LayoutLocalServiceUtil.getLayout(
					group.getGroupId(), group.hasPrivateLayouts(), 1);
			}
			catch (NoSuchLayoutException nsle) {
				continue;
			}

			String friendlyURL = layout.getFriendlyURL();

			if (!friendlyURL.equals("/home")) {
				continue;
			}

			updatePortlets(group, layout);
			updateLayouts(group);
		}
	}

	protected void updateLayouts(Group group) throws Exception {
		if (!group.isSite()) {
			return;
		}

		if ((group.getType() == GroupConstants.TYPE_SITE_PRIVATE) &&
			group.hasPrivateLayouts()) {

			return;
		}

		if ((group.getType() == GroupConstants.TYPE_SITE_OPEN) &&
			group.hasPublicLayouts()) {

			return;
		}

		long companyId = group.getCompanyId();
		long userId = PortalUtil.getValidUserId(
			companyId, group.getCreatorUserId());
		boolean privateLayout = group.hasPrivateLayouts();

		List<Layout> sourceLayouts = LayoutLocalServiceUtil.getLayouts(
			group.getGroupId(), privateLayout);

		ServiceContext serviceContext = new ServiceContext();

		for (Layout sourceLayout : sourceLayouts) {
			Layout targetLayout = LayoutLocalServiceUtil.addLayout(
				userId, group.getGroupId(), !privateLayout,
				LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
				sourceLayout.getName(LocaleUtil.getDefault().toString()),
				StringPool.BLANK, StringPool.BLANK,
				LayoutConstants.TYPE_PORTLET, false,
				sourceLayout.getFriendlyURL(), serviceContext);

			LayoutLocalServiceUtil.updateLayout(
				targetLayout.getGroupId(), targetLayout.isPrivateLayout(),
				targetLayout.getLayoutId(), sourceLayout.getTypeSettings());

			LayoutTypePortlet sourceLayoutTypePortlet =
				(LayoutTypePortlet)sourceLayout.getLayoutType();

			List<Portlet> sourcePortlets =
				sourceLayoutTypePortlet.getAllPortlets();

			for (Portlet sourcePortlet : sourcePortlets) {
				String sourcePortletId = sourcePortlet.getPortletId();

				PortletPreferencesLocalServiceUtil.getPreferences(
					companyId, PortletKeys.PREFS_OWNER_ID_DEFAULT,
					PortletKeys.PREFS_OWNER_TYPE_LAYOUT, targetLayout.getPlid(),
					sourcePortletId);

				PortletPreferences sourcePreferences =
					PortletPreferencesLocalServiceUtil.getPreferences(
						companyId, PortletKeys.PREFS_OWNER_ID_DEFAULT,
						PortletKeys.PREFS_OWNER_TYPE_LAYOUT,
						sourceLayout.getPlid(), sourcePortletId);

				PortletPreferencesLocalServiceUtil.updatePreferences(
					PortletKeys.PREFS_OWNER_ID_DEFAULT,
					PortletKeys.PREFS_OWNER_TYPE_LAYOUT, targetLayout.getPlid(),
					sourcePortletId, sourcePreferences);
			}

			LayoutLocalServiceUtil.deleteLayout(sourceLayout.getPlid());
		}
	}

	protected void updatePortlets(Group group, Layout layout) throws Exception {
		String prefix = PortletPropsKeys.SITE_PROTOTYPE_PORTLETS;

		Filter filter = new Filter(layout.getFriendlyURL());

		if (group.isUser()) {
			prefix = PortletPropsKeys.USER_PRIVATE_LAYOUT_PORTLETS;
		}

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		LayoutTemplate layoutTemplate = layoutTypePortlet.getLayoutTemplate();

		List<String> columns = layoutTemplate.getColumns();

		for (String column : columns) {
			String[] portletIds = PortletProps.getArray(
				prefix + column, filter);

			String portlets = StringPool.BLANK;

			for (String portletId : portletIds) {
				portlets = StringUtil.add(portlets, portletId);
			}

			layoutTypePortlet.setPortletIds(column, portlets);
		}

		if (!layoutTypePortlet.hasPortletId("1_WAR_soportlet")) {
			return;
		}

		PortletPreferences portletSetup =
			PortletPreferencesFactoryUtil.getLayoutPortletSetup(
				layout, "1_WAR_soportlet");

		portletSetup.setValue(
			"portletSetupShowBorders", String.valueOf(Boolean.TRUE));

		portletSetup.store();
	}

}