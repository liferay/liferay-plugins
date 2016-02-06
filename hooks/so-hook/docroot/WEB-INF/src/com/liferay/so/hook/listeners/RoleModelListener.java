/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.persistence.impl.TableMapper;
import com.liferay.so.service.SocialOfficeServiceUtil;
import com.liferay.so.util.LayoutSetPrototypeUtil;
import com.liferay.so.util.RoleConstants;
import com.liferay.so.util.SocialOfficeConstants;
import com.liferay.so.util.SocialOfficeUtil;

import java.util.List;

/**
 * @author Jonathan Lee
 */
public class RoleModelListener extends BaseModelListener<Role> {

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

			if (!associationClassName.equals(Group.class.getName())) {
				return;
			}

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
				users = UserLocalServiceUtil.getGroupUsers(group.getClassPK());
			}

			if (users == null) {
				return;
			}

			for (User user : users) {
				Group userGroup = user.getGroup();

				if (SocialOfficeServiceUtil.isSocialOfficeGroup(
						userGroup.getGroupId())) {

					continue;
				}

				LayoutSetPrototypeUtil.updateLayoutSetPrototype(
					userGroup, false,
					SocialOfficeConstants.LAYOUT_SET_PROTOTYPE_KEY_USER_PUBLIC);
				LayoutSetPrototypeUtil.updateLayoutSetPrototype(
					userGroup, true,
					SocialOfficeConstants.
						LAYOUT_SET_PROTOTYPE_KEY_USER_PRIVATE);

				SocialOfficeUtil.enableSocialOffice(userGroup);
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

			if (!associationClassName.equals(Group.class.getName())) {
				return;
			}

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
				users = UserLocalServiceUtil.getGroupUsers(group.getClassPK());
			}

			if (users == null) {
				return;
			}

			for (User user : users) {
				Group userGroup = user.getGroup();

				if (!SocialOfficeServiceUtil.isSocialOfficeGroup(
						userGroup.getGroupId())) {

					continue;
				}

				FinderCacheUtil.clearCache(
					_MAPPING_TABLE_USERS_ROLES_NAME_LEFT_TO_RIGHT);
				FinderCacheUtil.clearCache(
					_MAPPING_TABLE_USERS_ROLES_NAME_RIGHT_TO_LEFT);

				if (UserLocalServiceUtil.hasRoleUser(
						user.getCompanyId(), RoleConstants.SOCIAL_OFFICE_USER,
						user.getUserId(), true)) {

					continue;
				}

				LayoutSetPrototypeUtil.removeLayoutSetPrototype(
					userGroup, false,
					SocialOfficeConstants.LAYOUT_SET_PROTOTYPE_KEY_USER_PUBLIC);
				LayoutSetPrototypeUtil.removeLayoutSetPrototype(
					userGroup, true,
					SocialOfficeConstants.
						LAYOUT_SET_PROTOTYPE_KEY_USER_PRIVATE);

				SocialOfficeUtil.disableSocialOffice(userGroup);
			}
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	/**
	 * {@link com.liferay.portal.kernel.service.persistence.impl.TableMapperImpl}
	 */
	private static final String _MAPPING_TABLE_USERS_ROLES_NAME_LEFT_TO_RIGHT =
		TableMapper.class.getName() + "-Users_Roles-LeftToRight";

	/**
	 * {@link com.liferay.portal.kernel.service.persistence.impl.TableMapperImpl}
	 */
	private static final String _MAPPING_TABLE_USERS_ROLES_NAME_RIGHT_TO_LEFT =
		TableMapper.class.getName() + "-Users_Roles-RightToLeft";

}