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

package com.liferay.so.hook.upgrade.v2_0_2;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.PortalClassInvoker;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.so.util.LayoutSetPrototypeUtil;
import com.liferay.so.util.SocialOfficeConstants;
import com.liferay.so.util.SocialOfficeUtil;

import java.util.List;

import javax.portlet.PortletPreferences;

/**
 * @author Jonathan Lee
 * @author Josef Sustacek
 */
public class UpgradeGroup extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		List<Group> groups = GroupLocalServiceUtil.getGroups(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (Group group : groups) {
			if (!group.isRegularSite() || group.isGuest()) {
				continue;
			}

			boolean privateLayout = group.hasPrivateLayouts();

			LayoutSet layoutSet = LayoutSetLocalServiceUtil.getLayoutSet(
				group.getGroupId(), privateLayout);

			String themeId = layoutSet.getThemeId();

			if (!themeId.equals("so_WAR_sotheme")) {
				continue;
			}

			PortletPreferences portletPreferences = getPortletPreferences(
				group.getGroupId(), privateLayout);

			LayoutLocalServiceUtil.deleteLayouts(
				group.getGroupId(), privateLayout, new ServiceContext());

			LayoutSetPrototypeUtil.updateLayoutSetPrototype(
				group, privateLayout,
				SocialOfficeConstants.LAYOUT_SET_PROTOTYPE_KEY_SITE);

			layoutSet = LayoutSetLocalServiceUtil.getLayoutSet(
				group.getGroupId(), privateLayout);

			PortalClassInvoker.invoke(
				true, _mergeLayoutSetPrototypeLayoutsMethodKey, group,
				layoutSet);

			updatePortletPreferences(
				group.getGroupId(), privateLayout, portletPreferences);

			SocialOfficeUtil.enableSocialOffice(group);
		}
	}

	protected PortletPreferences getPortletPreferences(
			long groupId, boolean privateLayout)
		throws Exception {

		Layout layout = LayoutLocalServiceUtil.fetchFirstLayout(
			groupId, privateLayout, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID);

		if (layout.getLayoutType() instanceof LayoutTypePortlet) {
			try {
				return PortletPreferencesFactoryUtil.getLayoutPortletSetup(
					layout, _OLD_WELCOME_PORTLET_ID);
			}
			catch (Exception e) {
			}
		}

		return null;
	}

	protected void updatePortletPreferences(
			long groupId, boolean privateLayout,
			PortletPreferences portletPreferences)
		throws Exception {

		if (portletPreferences == null) {
			return;
		}

		Layout layout = LayoutLocalServiceUtil.fetchFirstLayout(
			groupId, privateLayout, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID);

		PortletPreferences newPortletPreferences =
			PortletPreferencesFactoryUtil.getLayoutPortletSetup(
				layout, _NEW_WELCOME_PORTLET_ID);

		newPortletPreferences.setValue(
			"message",
			portletPreferences.getValue("message", StringPool.BLANK));

		newPortletPreferences.store();
	}

	private static final String _NEW_WELCOME_PORTLET_ID =
		"1_WAR_wysiwygportlet_INSTANCE_abcd";

	private static final String _OLD_WELCOME_PORTLET_ID =
		"1_WAR_wysiwygportlet";

	private static MethodKey _mergeLayoutSetPrototypeLayoutsMethodKey =
		new MethodKey(
			"com.liferay.portlet.sites.util.SitesUtil",
			"mergeLayoutSetProtypeLayouts", Group.class, LayoutSet.class);

}