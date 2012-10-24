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
import com.liferay.portal.kernel.util.Validator;
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
import java.util.HashMap;
import java.util.Map;

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

			// store old portlets' preferences of /home layout
			Layout oldHomeLayout = LayoutLocalServiceUtil.fetchFirstLayout(
					group.getGroupId(), privateLayout, 
					LayoutConstants.DEFAULT_PARENT_LAYOUT_ID);

			Map<String, PortletPreferences> oldPortletsPrefs = 
					_getPrefsOfAllPortlets(oldHomeLayout);

			LayoutLocalServiceUtil.deleteLayouts(
				group.getGroupId(), privateLayout, new ServiceContext());

			LayoutSetPrototypeUtil.updateLayoutSetPrototype(
				group, privateLayout,
				SocialOfficeConstants.LAYOUT_SET_PROTOTYPE_KEY_SITE);

			layoutSet = LayoutSetLocalServiceUtil.getLayoutSet(
				group.getGroupId(), privateLayout);

			PortalClassInvoker.invoke(
				true, _mergeLayoutSetProtypeLayoutsMethodKey, group, layoutSet);

			// merge preferences of old Welcome portlet into new /home layout 
			// of the site and its Welcome portlet 
			Layout newHomeLayout = LayoutLocalServiceUtil.fetchFirstLayout(
					group.getGroupId(), privateLayout, 
					LayoutConstants.DEFAULT_PARENT_LAYOUT_ID);

			if(oldPortletsPrefs.containsKey(_OLD_WELCOME_PORTLET_ID)) {

				_copyPortletSetup(newHomeLayout,
						_NEW_WELCOME_PORTLET_ID,
						oldPortletsPrefs.get(_OLD_WELCOME_PORTLET_ID),
						new String[] {"message"});
			}

			SocialOfficeUtil.enableSocialOffice(group);
		}
	}

	private Map<String, PortletPreferences> _getPrefsOfAllPortlets(
													Layout layout) 
														throws Exception {

		Map<String, PortletPreferences> portletsSetups = 
						new HashMap<String, PortletPreferences>(16);

        List<String> portletIds = _getPortletIds(layout);

		for(String portletId: portletIds) {
			PortletPreferences portletSetup =
					PortletPreferencesFactoryUtil.getLayoutPortletSetup(
							layout, portletId);

			portletsSetups.put(portletId, portletSetup);
		}

		return portletsSetups;
	}

	private List<String> _getPortletIds(Layout layout) {

		if(layout.getLayoutType() instanceof LayoutTypePortlet) {
			LayoutTypePortlet layoutTypePortlet = 
						((LayoutTypePortlet)layout.getLayoutType());

			List<String> portletIds = layoutTypePortlet.getPortletIds();

			return portletIds;

		} else {

			throw new IllegalArgumentException(String.format(
					"Given layout (plid= %s) is not a portlet layout, " +
					"no portlets could be fetched for given layout",
					layout.getPlid()));
		}
	}

	private static void _copyPortletSetup(Layout layout, String portletId, 
											PortletPreferences oldPortletSetup, 
											String[] keysToCopy)
									throws Exception {

		PortletPreferences newPortletSetup =
				PortletPreferencesFactoryUtil.getLayoutPortletSetup(
						layout, portletId);


		for (String key : keysToCopy) {

			String oldValue = oldPortletSetup.getValue(key, "");

			if (Validator.isNotNull(oldValue)) {

				newPortletSetup.setValue(
						key, oldValue);
			}
		}

		newPortletSetup.store();
	}

	private static final String _OLD_WELCOME_PORTLET_ID = 
										"1_WAR_wysiwygportlet";

	private static final String _NEW_WELCOME_PORTLET_ID = 
										"1_WAR_wysiwygportlet_INSTANCE_abcd";

	private static final String _CLASS_NAME =
		"com.liferay.portlet.sites.util.SitesUtil";

	private static MethodKey _mergeLayoutSetProtypeLayoutsMethodKey =
		new MethodKey(
			_CLASS_NAME, "mergeLayoutSetProtypeLayouts", Group.class,
			LayoutSet.class);

}