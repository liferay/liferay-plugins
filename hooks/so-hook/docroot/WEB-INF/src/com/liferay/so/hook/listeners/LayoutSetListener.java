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
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.so.hook.listeners;

import com.liferay.portal.ModelListenerException;
import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.model.BaseModelListener;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.so.util.LayoutUtil;
import com.liferay.so.util.PortletPropsKeys;
import com.liferay.so.util.PortletPropsValues;
import com.liferay.util.portlet.PortletProps;

/**
 * @author Brian Wing Shun Chan
 * @author Ryan Park
 * @author Jonathan Lee
 */
public class LayoutSetListener extends BaseModelListener<LayoutSet> {

	@Override
	public void onAfterCreate(LayoutSet layoutSet)
		throws ModelListenerException {

		try {
			Group group = GroupLocalServiceUtil.getGroup(
				layoutSet.getGroupId());

			if (!group.isUser()) {
				return;
			}

			if (layoutSet.isPrivateLayout()) {
				addPrivateUserLayouts(group);

				setCustomJspServletContextName(group);
			}
			else {
				addPublicUserLayouts(group);
			}
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	protected void addApplications(Group group, long parentLayoutId)
		throws Exception {

		for (String portletId : PortletPropsValues.USER_APPLICATIONS) {
			Portlet portlet = PortletLocalServiceUtil.getPortletById(
				group.getCompanyId(), portletId);

			if ((portlet == null) || portlet.isUndeployedPortlet()) {
				continue;
			}

			Layout layout = LayoutUtil.addLayout(
				group, true, parentLayoutId, portlet.getDisplayName(),
				PortletPropsValues.USER_NEW_LAYOUT_TEMPLATE);

			LayoutTypePortlet layoutTypePortlet =
				(LayoutTypePortlet)layout.getLayoutType();

			String[] commonPortletIds = PortletProps.getArray(
				PortletPropsKeys.USER_LAYOUT_PORTLETS + "column-1",
				new Filter("/home"));

			String portletIds = StringPool.BLANK;

			for (String commonPortletId : commonPortletIds) {
				portletIds = StringUtil.add(portletIds, commonPortletId);
			}

			layoutTypePortlet.setPortletIds("column-1", portletIds);
			layoutTypePortlet.setPortletIds("column-2", portletId);

			LayoutLocalServiceUtil.updateLayout(
				layout.getGroupId(), layout.isPrivateLayout(),
				layout.getLayoutId(), layout.getTypeSettings());

			LayoutUtil.addResources(layout, portletId);

			for (String commonPortletId : commonPortletIds) {
				LayoutUtil.addResources(layout, commonPortletId);

				if (commonPortletId.startsWith("71_INSTANCE_")) {
					LayoutUtil.removePortletBorder(layout, commonPortletId);
					LayoutUtil.configureNavigation(layout, commonPortletId);
				}
			}

			LayoutUtil.updatePermissions(layout, false);
		}
	}

	protected void addPrivateUserLayouts(Group group) throws Exception {
		LayoutSetLocalServiceUtil.updateLookAndFeel(
			group.getGroupId(), true, "so_WAR_sotheme", "01", StringPool.BLANK,
			false);

		Layout layout = LayoutUtil.addLayout(
			group, true, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID, "Home",
			PortletPropsValues.USER_NEW_LAYOUT_TEMPLATE);

		LayoutUtil.addPortlets(
			group, layout, "/home", PortletPropsKeys.USER_LAYOUT_PORTLETS);

		LayoutUtil.updatePermissions(layout, false);

		addApplications(group, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID);
	}

	protected void addPublicUserLayouts(Group group) throws Exception {
		LayoutSetLocalServiceUtil.updateLookAndFeel(
			group.getGroupId(), false, "so_WAR_sotheme", "01", StringPool.BLANK,
			false);

		Layout layout = LayoutUtil.addLayout(
			group, false, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID, "Profile",
			PortletPropsValues.USER_NEW_LAYOUT_TEMPLATE);

		LayoutUtil.addPortlets(
			group, layout, "/profile", PortletPropsKeys.USER_LAYOUT_PORTLETS);

		LayoutUtil.updatePermissions(layout, true);
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

}