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
import com.liferay.portal.NoSuchGroupException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.model.BaseModelListener;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.model.LayoutSetPrototype;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.service.LayoutSetPrototypeLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.portlet.expando.model.ExpandoValue;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;
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

			Role role = RoleLocalServiceUtil.getRole((Long)associationClassPK);

			String name = role.getName();

			if (!name.equals(RoleConstants.SOCIAL_OFFICE_USER)) {
				return;
			}

			User user = UserLocalServiceUtil.getUser((Long)classPK);

			Group group = user.getGroup();

			LayoutSetPrototype publicLayoutSetPrototype =
				LayoutSetPrototypeUtil.fetchLayoutSetPrototype(user, false);

			if (publicLayoutSetPrototype != null) {
				LayoutSetLocalServiceUtil.updateLayoutSetPrototypeLinkEnabled(
					group.getGroupId(), false, true,
					publicLayoutSetPrototype.getUuid());

				LayoutLocalServiceUtil.updatePriorities(
					group.getGroupId(), false);
			}

			LayoutSetPrototype privateLayoutSetPrototype =
				LayoutSetPrototypeUtil.fetchLayoutSetPrototype(user, true);

			if (privateLayoutSetPrototype != null) {
				LayoutSetLocalServiceUtil.updateLayoutSetPrototypeLinkEnabled(
					group.getGroupId(), true, true,
					privateLayoutSetPrototype.getUuid());

				LayoutLocalServiceUtil.updatePriorities(
					group.getGroupId(), true);
			}

			enableSocialOffice(group);
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

			Role role = RoleLocalServiceUtil.getRole((Long)associationClassPK);

			String name = role.getName();

			if (!name.equals(RoleConstants.SOCIAL_OFFICE_USER)) {
				return;
			}

			User user = UserLocalServiceUtil.getUser((Long)classPK);

			LayoutSetPrototype publicLayoutSetPrototype =
				LayoutSetPrototypeUtil.fetchLayoutSetPrototype(user, false);

			removeUserLayouts(user, false, publicLayoutSetPrototype.getUuid());

			LayoutSetPrototype privateLayoutSetPrototype =
				LayoutSetPrototypeUtil.fetchLayoutSetPrototype(user, true);

			removeUserLayouts(user, true, privateLayoutSetPrototype.getUuid());

			disableSocialOffice(user.getGroup());
		}
		catch (NoSuchGroupException nsge) {
			return;
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	protected void disableSocialOffice(Group group) throws Exception {
		UnicodeProperties typeSettingsProperties =
			group.getTypeSettingsProperties();

		typeSettingsProperties.remove("customJspServletContextName");

		GroupLocalServiceUtil.updateGroup(
			group.getGroupId(), typeSettingsProperties.toString());

		ExpandoValue expandoValue = ExpandoValueLocalServiceUtil.getValue(
			group.getCompanyId(), Group.class.getName(),
			ExpandoTableConstants.DEFAULT_TABLE_NAME, "socialOfficeEnabled",
			group.getGroupId());

		expandoValue.setBoolean(false);

		ExpandoValueLocalServiceUtil.updateExpandoValue(expandoValue);
	}

	protected void enableSocialOffice(Group group) throws Exception {
		UnicodeProperties typeSettingsProperties =
			group.getTypeSettingsProperties();

		typeSettingsProperties.setProperty(
			"customJspServletContextName", "so-hook");

		GroupLocalServiceUtil.updateGroup(
			group.getGroupId(), typeSettingsProperties.toString());

		ExpandoValueLocalServiceUtil.addValue(
			group.getCompanyId(), Group.class.getName(),
			ExpandoTableConstants.DEFAULT_TABLE_NAME, "socialOfficeEnabled",
			group.getGroupId(), true);
	}

	protected void removeUserLayouts(
			User user, boolean privateLayout, String layoutSetPrototypeUuid)
		throws PortalException, SystemException {

		Group group = user.getGroup();

		LayoutSet layoutSet = LayoutSetLocalServiceUtil.getLayoutSet(
			group.getGroupId(), privateLayout);

		UnicodeProperties settingsProperties =
			layoutSet.getSettingsProperties();

		settingsProperties.remove("last-merge-time");

		layoutSet.setSettingsProperties(settingsProperties);

		layoutSet.setLayoutSetPrototypeUuid(StringPool.BLANK);
		layoutSet.setLayoutSetPrototypeLinkEnabled(false);

		LayoutSetLocalServiceUtil.updateLayoutSet(layoutSet);

		LayoutSetLocalServiceUtil.updateLookAndFeel(
			group.getGroupId(), null, null, StringPool.BLANK, false);

		LayoutSetPrototype layoutSetPrototype =
			LayoutSetPrototypeLocalServiceUtil.getLayoutSetPrototypeByUuid(
				layoutSetPrototypeUuid);

		Group layoutSetPrototypeGroup = layoutSetPrototype.getGroup();

		List<Layout> layouts = LayoutLocalServiceUtil.getLayouts(
			layoutSetPrototypeGroup.getGroupId(), true);

		String[] layoutUuids = new String[layouts.size()];

		for (int i = 0; i < layouts.size(); i++) {
			Layout layout = layouts.get(i);

			layoutUuids[i] = layout.getUuid();
		}

		layouts = LayoutLocalServiceUtil.getLayouts(
			group.getGroupId(), privateLayout);

		for (Layout layout : layouts) {
			if (ArrayUtil.contains(
					layoutUuids, layout.getSourcePrototypeLayoutUuid())) {

				LayoutLocalServiceUtil.deleteLayout(
					layout.getGroupId(), privateLayout, layout.getLayoutId(),
					new ServiceContext());
			}
		}
	}

}