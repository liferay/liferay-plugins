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

package com.liferay.so.hook.listeners;

import com.liferay.portal.ModelListenerException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.model.BaseModelListener;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutSetLocalServiceUtil;
import com.liferay.so.util.LayoutUtil;
import com.liferay.so.util.PortletPropsKeys;

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

	protected void addPrivateUserLayouts(Group group) throws Exception {
		LayoutSetLocalServiceUtil.updateLookAndFeel(
			group.getGroupId(), true, "so_WAR_sotheme", "01", StringPool.BLANK,
			false);

		// Home

		Layout layout = LayoutUtil.addLayout(
			group, true, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID, "Home",
			"1_column");

		LayoutUtil.addPortlets(
			group, layout, "/home",
			PortletPropsKeys.USER_PRIVATE_LAYOUT_PORTLETS);

		LayoutUtil.updatePermissions(layout, false);

		// Contacts Center

		layout = LayoutUtil.addLayout(
			group, true, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
			"Contacts Center", "1_column");

		LayoutUtil.addPortlets(
			group, layout, "/contacts-center",
			PortletPropsKeys.USER_PRIVATE_LAYOUT_PORTLETS);

		LayoutUtil.updatePermissions(layout, true);

		// Microblogs

		layout = LayoutUtil.addLayout(
			group, true, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
			"Microblogs", "1_column");

		LayoutUtil.addPortlets(
			group, layout, "/microblogs",
			PortletPropsKeys.USER_PRIVATE_LAYOUT_PORTLETS);

		LayoutUtil.updatePermissions(layout, true);

		// Messages

		layout = LayoutUtil.addLayout(
			group, true, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
			"Messages", "1_column");

		LayoutUtil.addPortlets(
			group, layout, "/messages",
			PortletPropsKeys.USER_PRIVATE_LAYOUT_PORTLETS);

		LayoutUtil.updatePermissions(layout, true);

		// Tasks

		layout = LayoutUtil.addLayout(
			group, true, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
			"Tasks", "1_column");

		LayoutUtil.addPortlets(
			group, layout, "/tasks",
			PortletPropsKeys.USER_PRIVATE_LAYOUT_PORTLETS);

		LayoutUtil.updatePermissions(layout, true);
	}

	protected void addPublicUserLayouts(Group group) throws Exception {
		LayoutSetLocalServiceUtil.updateLookAndFeel(
			group.getGroupId(), false, "so_WAR_sotheme", "01", StringPool.BLANK,
			false);

		Layout layout = LayoutUtil.addLayout(
			group, false, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID, "Profile",
			"1_2_columns_ii");

		LayoutUtil.addPortlets(
			group, layout, "/profile",
			PortletPropsKeys.USER_PUBLIC_LAYOUT_PORTLETS);

		LayoutUtil.updatePermissions(layout, true);

		// Contacts

		layout = LayoutUtil.addLayout(
			group, false, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
			"Contacts", "1_column");

		LayoutUtil.addPortlets(
			group, layout, "/contacts",
			PortletPropsKeys.USER_PUBLIC_LAYOUT_PORTLETS);

		LayoutUtil.updatePermissions(layout, true);

		// Microblogs

		layout = LayoutUtil.addLayout(
			group, false, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
			"Microblogs", "1_column");

		LayoutUtil.addPortlets(
			group, layout, "/microblogs",
			PortletPropsKeys.USER_PUBLIC_LAYOUT_PORTLETS);

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