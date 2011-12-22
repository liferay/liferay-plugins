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

package com.liferay.so.hook.upgrade.v2_0_0;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Layout;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

import java.util.List;

import javax.portlet.PortletPreferences;

/**
 * @author Ryan Park
 */
public class UpgradeAssetPublisher extends UpgradeProcess {

	protected void configureAssetPublisher(Layout layout) throws Exception {
		PortletPreferences portletSetup =
			PortletPreferencesFactoryUtil.getLayoutPortletSetup(
				layout, "101_INSTANCE_abcd");

		portletSetup.setValue("display-style", "title-list");
		portletSetup.setValue("asset-link-behaviour", "viewInPortlet");

		portletSetup.store();
	}

	@Override
	protected void doUpgrade() throws Exception {
		List<Group> groups = GroupLocalServiceUtil.getGroups(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (Group group : groups) {
			if (!group.isSite() ||
				group.getName().equals(GroupConstants.CONTROL_PANEL) ||
				group.getName().equals(GroupConstants.GUEST)) {

				continue;
			}

			// Documents

			Layout layout = LayoutLocalServiceUtil.getLayout(
				group.getGroupId(), group.hasPrivateLayouts(), 3);

			configureAssetPublisher(layout);

			// Forums

			layout = LayoutLocalServiceUtil.getLayout(
				group.getGroupId(), group.hasPrivateLayouts(), 4);

			configureAssetPublisher(layout);

			// Blogs

			layout = LayoutLocalServiceUtil.getLayout(
				group.getGroupId(), group.hasPrivateLayouts(), 5);

			configureAssetPublisher(layout);

			// Wiki

			layout = LayoutLocalServiceUtil.getLayout(
				group.getGroupId(), group.hasPrivateLayouts(), 6);

			configureAssetPublisher(layout);
		}
	}

}