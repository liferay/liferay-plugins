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
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.PortalClassInvoker;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.model.BaseModelListener;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.model.LayoutSetPrototype;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.service.LayoutSetPrototypeLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.so.util.LayoutSetPrototypeUtil;
import com.liferay.so.util.RoleConstants;

import java.util.List;

/**
 * @author Jonathan Lee
 * @author Eudaldo Alonso
 */
public class UserListener extends BaseModelListener<User> {

	@Override
	public void onAfterAddAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException {

		try {
			if (!associationClassName.equals(Role.class.getName())) {
				return;
			}

			long roleId = (Long)associationClassPK;

			Role role = RoleLocalServiceUtil.getRole(roleId);

			String name = role.getName();

			if (name.equals(RoleConstants.SOCIAL_OFFICE_USER)) {
				long userId = (Long)classPK;

				User user = UserLocalServiceUtil.getUser(userId);

				Group group = user.getGroup();

				LayoutSetPrototype[] layoutSetPrototypes =
					LayoutSetPrototypeUtil.getLayoutSetPrototypes(user);

				long[] publicPlids = getUserLayoutPlids(group, false);

				LayoutSetLocalServiceUtil.updateLayoutSetPrototypeLinkEnabled(
					group.getGroupId(), false, true,
					layoutSetPrototypes[0].getUuid());

				LayoutSet layoutSet = LayoutSetLocalServiceUtil.getLayoutSet(
						group.getGroupId(), false);

				PortalClassInvoker.invoke(
					true, _mergeLayoutSetProtypeLayoutsMethodKey, group,
					layoutSet);

				orderLayouts(publicPlids);

				long[] privatePlids = getUserLayoutPlids(group, true);

				LayoutSetLocalServiceUtil.updateLayoutSetPrototypeLinkEnabled(
					group.getGroupId(), true, true,
					layoutSetPrototypes[1].getUuid());

				layoutSet = LayoutSetLocalServiceUtil.getLayoutSet(
						group.getGroupId(), true);

				PortalClassInvoker.invoke(
					true, _mergeLayoutSetProtypeLayoutsMethodKey, group,
					layoutSet);

				orderLayouts(privatePlids);
			}
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	@Override
	public void onAfterRemoveAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException {

		try {
			if (!associationClassName.equals(Role.class.getName())) {
				return;
			}

			long roleId = (Long)associationClassPK;

			Role role = RoleLocalServiceUtil.getRole(roleId);

			String name = role.getName();

			if (name.equals(RoleConstants.SOCIAL_OFFICE_USER)) {
				long userId = (Long)classPK;

				User user = UserLocalServiceUtil.getUser(userId);

				LayoutSetPrototype[] layoutSetPrototypes =
					LayoutSetPrototypeUtil.getLayoutSetPrototypes(user);

				removeUserLayouts(
					user, false, layoutSetPrototypes[0].getUuid());
				removeUserLayouts(user, true, layoutSetPrototypes[1].getUuid());
			}
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	protected long[] getUserLayoutPlids(Group group, boolean privateLayout)
		throws PortalException, SystemException {

		List<Layout> layouts =
			LayoutLocalServiceUtil.getLayouts(
				group.getGroupId(), privateLayout,
				LayoutConstants.DEFAULT_PARENT_LAYOUT_ID);

		int size = layouts.size();

		long[] plids = new long[size];

		for (int i = 0; i < size; i++) {
			Layout layout = layouts.get(i);

			plids[i] = layout.getPlid();
		}

		return plids;
	}

	protected void orderLayouts(long[] plids)
		throws PortalException, SystemException {

		for (int i = 0; i < plids.length; i++) {
			LayoutLocalServiceUtil.updatePriority(plids[i], _PRIORITY + i);
		}
	}

	protected void removeUserLayouts(
			User user, boolean privateLayout, String layoutSetPrototypeUuid)
		throws PortalException, SystemException {

		Group userGroup = user.getGroup();

		LayoutSet layoutSet = LayoutSetLocalServiceUtil.getLayoutSet(
			userGroup.getGroupId(), privateLayout);

		UnicodeProperties settingsProperties =
			layoutSet.getSettingsProperties();

		settingsProperties.remove("last-merge-time");

		layoutSet.setSettingsProperties(settingsProperties);

		layoutSet.setLayoutSetPrototypeLinkEnabled(false);
		layoutSet.setLayoutSetPrototypeUuid(StringPool.BLANK);

		LayoutSetLocalServiceUtil.updateLayoutSet(layoutSet);

		LayoutSetLocalServiceUtil.updateLookAndFeel(
			userGroup.getGroupId(), null, null, "", false);

		LayoutSetPrototype layoutSetPrototype =
			LayoutSetPrototypeLocalServiceUtil.getLayoutSetPrototypeByUuid(
				layoutSetPrototypeUuid);

		Group group = layoutSetPrototype.getGroup();

		List<Layout> layouts = LayoutLocalServiceUtil.getLayouts(
			group.getGroupId(), true);

		String[] layoutUuids = new String[layouts.size()];

		for (int i = 0; i < layouts.size(); i++) {
			Layout curLayout = layouts.get(i);

			layoutUuids[i] = curLayout.getUuid();
		}

		List<Layout> userLayouts = LayoutLocalServiceUtil.getLayouts(
			userGroup.getGroupId(), privateLayout);

		for (Layout userLayout : userLayouts) {
			if (ArrayUtil.contains(
					layoutUuids, userLayout.getSourcePrototypeLayoutUuid())) {

				LayoutLocalServiceUtil.deleteLayout(
					userLayout.getGroupId(), privateLayout,
					userLayout.getLayoutId(), new ServiceContext());
			}
		}
	}

	private static final String _CLASS_NAME =
		"com.liferay.portlet.sites.util.SitesUtil";

	private static final int _PRIORITY = 100;

	private static MethodKey _mergeLayoutSetProtypeLayoutsMethodKey =
		new MethodKey(
			_CLASS_NAME, "mergeLayoutSetProtypeLayouts", Group.class,
			LayoutSet.class);

}