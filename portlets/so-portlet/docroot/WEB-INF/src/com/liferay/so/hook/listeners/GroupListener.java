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
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.util.PortletKeys;

import java.util.List;

import javax.portlet.PortletPreferences;

/**
 * <a href="GroupListener.java.html"><b><i>View Source</i></b></a>
 *
 * @author Ryan Park
 *
 */
public class GroupListener extends BaseModelListener<Group> {

	public void onAfterUpdate(Group group) throws ModelListenerException {
		try {
			if (!group.isCommunity()) {
				return;
			}

			if ((group.getType() == GroupConstants.TYPE_COMMUNITY_PRIVATE) &&
				group.hasPrivateLayouts()) {

				return;
			}

			if ((group.getType() == GroupConstants.TYPE_COMMUNITY_OPEN) &&
				group.hasPublicLayouts()) {

				return;
			}

			convertCommunity(group);
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	protected void convertCommunity(Group group) throws Exception {
		boolean privateLayout = false;

		if (group.getType() == GroupConstants.TYPE_COMMUNITY_PRIVATE) {
			privateLayout = true;
		}

		List<Layout> layouts = LayoutLocalServiceUtil.getLayouts(
			group.getGroupId(), !privateLayout);

		for (Layout layout : layouts) {
			Layout targetLayout = LayoutLocalServiceUtil.addLayout(
				group.getCreatorUserId(), group.getGroupId(), privateLayout,
				LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
				layout.getName("en_US"), StringPool.BLANK, StringPool.BLANK,
				LayoutConstants.TYPE_PORTLET, false, layout.getFriendlyURL());

			copyLayout(layout, targetLayout);

			LayoutLocalServiceUtil.deleteLayout(layout.getPlid());
		}
	}

	protected void copyLayout(Layout sourceLayout, Layout targetLayout)
		throws Exception {

		long companyId = targetLayout.getCompanyId();

		LayoutLocalServiceUtil.updateLayout(
			targetLayout.getGroupId(), targetLayout.isPrivateLayout(),
			targetLayout.getLayoutId(), sourceLayout.getTypeSettings());

		LayoutTypePortlet sourceLayoutTypePortlet =
			(LayoutTypePortlet)sourceLayout.getLayoutType();

		List<Portlet> sourcePortlets = sourceLayoutTypePortlet.getAllPortlets();

		for (Portlet sourcePortlet : sourcePortlets) {
			String sourcePortletId = sourcePortlet.getPortletId();

			PortletPreferencesLocalServiceUtil.getPreferences(
				companyId, PortletKeys.PREFS_OWNER_ID_DEFAULT,
				PortletKeys.PREFS_OWNER_TYPE_LAYOUT, targetLayout.getPlid(),
				sourcePortletId);

			PortletPreferences sourcePreferences =
				PortletPreferencesLocalServiceUtil.getPreferences(
					companyId, PortletKeys.PREFS_OWNER_ID_DEFAULT,
					PortletKeys.PREFS_OWNER_TYPE_LAYOUT, sourceLayout.getPlid(),
					sourcePortletId);

			PortletPreferencesLocalServiceUtil.updatePreferences(
				PortletKeys.PREFS_OWNER_ID_DEFAULT,
				PortletKeys.PREFS_OWNER_TYPE_LAYOUT, targetLayout.getPlid(),
				sourcePortletId, sourcePreferences);
		}
	}

}