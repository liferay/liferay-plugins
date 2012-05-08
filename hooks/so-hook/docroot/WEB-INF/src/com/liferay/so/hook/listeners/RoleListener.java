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
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.model.BaseModelListener;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
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
public class RoleListener extends BaseModelListener<Role> {

	@Override
	public void onAfterAddAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException {

		try {
			Role role = RoleLocalServiceUtil.getRole((Long)classPK);

			String name = role.getName();

			if (!name.equals(RoleConstants.SOCIAL_OFFICE_USER)) {
				return;
			}

			if (associationClassName.equals(Group.class.getName())) {
				Group group = GroupLocalServiceUtil.getGroup(
					(Long)associationClassPK);

				List<User> users = null;

				String className = group.getClassName();

				if (className.equals(UserGroup.class.getName())) {
					users = UserLocalServiceUtil.getUserGroupUsers(
						group.getClassPK());
				}
				else if (className.equals(Organization.class.getName())) {
					users = UserLocalServiceUtil.getOrganizationUsers(
						group.getClassPK());
				}
				else if (className.equals(Group.class.getName())) {
					users = UserLocalServiceUtil.getGroupUsers(
						group.getClassPK());
				}

				if (users != null) {
					for (User user : users) {
						Group userGroup = user.getGroup();

						LayoutSetPrototypeUtil.updateLayoutStePrototype(
							userGroup, false);
						LayoutSetPrototypeUtil.updateLayoutStePrototype(
							userGroup, true);

						enableSocialOffice(userGroup);
					}
				}
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
			Role role = RoleLocalServiceUtil.getRole((Long)classPK);

			String name = role.getName();

			if (!name.equals(RoleConstants.SOCIAL_OFFICE_USER)) {
				return;
			}

			if (associationClassName.equals(Group.class.getName())) {
				Group group = GroupLocalServiceUtil.getGroup(
					(Long)associationClassPK);

				List<User> users = null;

				String className = group.getClassName();

				if (className.equals(UserGroup.class.getName())) {
					users = UserLocalServiceUtil.getUserGroupUsers(
						group.getClassPK());
				}
				else if (className.equals(Organization.class.getName())) {
					users = UserLocalServiceUtil.getOrganizationUsers(
						group.getClassPK());
				}
				else if (className.equals(Group.class.getName())) {
					users = UserLocalServiceUtil.getGroupUsers(
						group.getClassPK());
				}

				if (users != null) {
					for (User user : users) {
						Group userGroup = user.getGroup();

						LayoutSetPrototypeUtil.removeLayoutSetPrototype(
							userGroup, false);
						LayoutSetPrototypeUtil.removeLayoutSetPrototype(
							userGroup, true);

						disableSocialOffice(userGroup);
					}
				}
			}
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

}