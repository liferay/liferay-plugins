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

import com.liferay.portal.ModelListenerException;
import com.liferay.portal.NoSuchGroupException;
import com.liferay.portal.kernel.cache.thread.local.Lifecycle;
import com.liferay.portal.kernel.cache.thread.local.ThreadLocalCacheManager;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.model.BaseModelListener;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.persistence.impl.TableMapper;
import com.liferay.so.service.SocialOfficeServiceUtil;
import com.liferay.so.util.LayoutSetPrototypeUtil;
import com.liferay.so.util.RoleConstants;
import com.liferay.so.util.SocialOfficeConstants;
import com.liferay.so.util.SocialOfficeUtil;

/**
 * @author Jonathan Lee
 * @author Eudaldo Alonso
 */
public class UserModelListener extends BaseModelListener<User> {

	@Override
	public void onAfterAddAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException {

		try {
			User user = UserLocalServiceUtil.getUser((Long)classPK);

			if (associationClassName.equals(Group.class.getName()) ||
				associationClassName.equals(Organization.class.getName()) ||
				associationClassName.equals(UserGroup.class.getName())) {

				Role role = RoleLocalServiceUtil.fetchRole(
					user.getCompanyId(), RoleConstants.SOCIAL_OFFICE_USER);

				if (role == null) {
					return;
				}

				Group group = null;

				if (associationClassName.equals(Group.class.getName())) {
					group = GroupLocalServiceUtil.getGroup(
						(Long)associationClassPK);
				}
				else if (associationClassName.equals(
							Organization.class.getName())) {

					group = GroupLocalServiceUtil.getOrganizationGroup(
						user.getCompanyId(), (Long)associationClassPK);
				}
				else if (associationClassName.equals(
							UserGroup.class.getName())) {

					group = GroupLocalServiceUtil.getUserGroupGroup(
						user.getCompanyId(), (Long)associationClassPK);
				}

				if (GroupLocalServiceUtil.hasRoleGroup(
						role.getRoleId(), group.getGroupId())) {

					enableSocialOffice(user.getGroup());
				}
			}
			else if (associationClassName.equals(Role.class.getName())) {
				Role role = RoleLocalServiceUtil.getRole(
					(Long)associationClassPK);

				String name = role.getName();

				if (name.equals(RoleConstants.SOCIAL_OFFICE_USER)) {
					enableSocialOffice(user.getGroup());
				}
			}
		}
		catch (NoSuchGroupException nsge) {
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
			User user = UserLocalServiceUtil.getUser((Long)classPK);

			FinderCacheUtil.clearCache(
				_MAPPING_TABLE_USERS_ROLES_NAME_LEFT_TO_RIGHT);
			FinderCacheUtil.clearCache(
				_MAPPING_TABLE_USERS_ROLES_NAME_RIGHT_TO_LEFT);

			ThreadLocalCacheManager.clearAll(Lifecycle.REQUEST);

			if (UserLocalServiceUtil.hasRoleUser(
					user.getCompanyId(), RoleConstants.SOCIAL_OFFICE_USER,
					user.getUserId(), true)) {

				return;
			}

			if (associationClassName.equals(Group.class.getName()) ||
				associationClassName.equals(Organization.class.getName()) ||
				associationClassName.equals(UserGroup.class.getName())) {

				Role role = RoleLocalServiceUtil.getRole(
					user.getCompanyId(), RoleConstants.SOCIAL_OFFICE_USER);

				Group group = null;

				if (associationClassName.equals(Group.class.getName())) {
					group = GroupLocalServiceUtil.getGroup(
						(Long)associationClassPK);
				}
				else if (associationClassName.equals(
							Organization.class.getName())) {

					group = GroupLocalServiceUtil.getOrganizationGroup(
						user.getCompanyId(), (Long)associationClassPK);
				}
				else if (associationClassName.equals(
							UserGroup.class.getName())) {

					group = GroupLocalServiceUtil.getUserGroupGroup(
						user.getCompanyId(), (Long)associationClassPK);
				}

				if (GroupLocalServiceUtil.hasRoleGroup(
						role.getRoleId(), group.getGroupId())) {

					disableSocialOffice(user.getGroup());
				}
			}
			else if (associationClassName.equals(Role.class.getName())) {
				Role role = RoleLocalServiceUtil.getRole(
					(Long)associationClassPK);

				String name = role.getName();

				if (name.equals(RoleConstants.SOCIAL_OFFICE_USER)) {
					disableSocialOffice(user.getGroup());
				}
			}
		}
		catch (NoSuchGroupException nsge) {
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	protected void disableSocialOffice(Group group) throws Exception {
		if (!SocialOfficeServiceUtil.isSocialOfficeGroup(group.getGroupId())) {
			return;
		}

		LayoutSetPrototypeUtil.removeLayoutSetPrototype(
			group, false,
			SocialOfficeConstants.LAYOUT_SET_PROTOTYPE_KEY_USER_PUBLIC);
		LayoutSetPrototypeUtil.removeLayoutSetPrototype(
			group, true,
			SocialOfficeConstants.LAYOUT_SET_PROTOTYPE_KEY_USER_PRIVATE);

		SocialOfficeUtil.disableSocialOffice(group);
	}

	protected void enableSocialOffice(Group group) throws Exception {
		if (SocialOfficeServiceUtil.isSocialOfficeGroup(group.getGroupId())) {
			return;
		}

		LayoutSetPrototypeUtil.updateLayoutSetPrototype(
			group, false,
			SocialOfficeConstants.LAYOUT_SET_PROTOTYPE_KEY_USER_PUBLIC);
		LayoutSetPrototypeUtil.updateLayoutSetPrototype(
			group, true,
			SocialOfficeConstants.LAYOUT_SET_PROTOTYPE_KEY_USER_PRIVATE);

		SocialOfficeUtil.enableSocialOffice(group);
	}

	/**
	 * {@link com.liferay.portal.service.persistence.impl.TableMapperImpl}
	 */
	private static final String _MAPPING_TABLE_USERS_ROLES_NAME_LEFT_TO_RIGHT =
		TableMapper.class.getName() + "-Users_Roles-LeftToRight";

	/**
	 * {@link com.liferay.portal.service.persistence.impl.TableMapperImpl}
	 */
	private static final String _MAPPING_TABLE_USERS_ROLES_NAME_RIGHT_TO_LEFT =
		TableMapper.class.getName() + "-Users_Roles-RightToLeft";

}