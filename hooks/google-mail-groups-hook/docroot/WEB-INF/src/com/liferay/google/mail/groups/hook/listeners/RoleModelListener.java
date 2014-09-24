/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.google.mail.groups.hook.listeners;

import com.liferay.google.mail.groups.util.GoogleMailGroupsUtil;
import com.liferay.google.mail.groups.util.PortletPropsValues;
import com.liferay.portal.ModelListenerException;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.BaseModelListener;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Matthew Kong
 */
public class RoleModelListener extends BaseModelListener<Role> {

	@Override
	public void onAfterAddAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException {

		try {
			new OnAssociation(
				classPK, associationClassName, associationClassPK) {

				@Override
				public void onAssociation(List<User> users) throws Exception {
					GoogleMailGroupsUtil.addGGroupManagers(users);
				}

			};
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	@Override
	public void onAfterRemoveAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException {

		try {
			new OnAssociation(
				classPK, associationClassName, associationClassPK) {

				@Override
				public void onAssociation(List<User> users) throws Exception {
					GoogleMailGroupsUtil.removeGGroupManagers(users);
				}

			};
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(RoleModelListener.class);

	private abstract class OnAssociation {

		public OnAssociation(
				Object classPK, String associationClassName,
				Object associationClassPK)
			throws Exception {

			Role role = RoleLocalServiceUtil.getRole((Long)classPK);

			String roleName = role.getName();

			if (!roleName.equals(PortletPropsValues.EMAIL_LARGE_GROUP_ROLE)) {
				return;
			}

			List<User> users = new ArrayList<User>();

			if (associationClassName.equals(Group.class.getName())) {
				LinkedHashMap<String, Object> userParams =
					new LinkedHashMap<String, Object>();

				userParams.put("inherit", Boolean.TRUE);
				userParams.put("usersGroups", associationClassPK);

				users = UserLocalServiceUtil.search(
					role.getCompanyId(), null,
					WorkflowConstants.STATUS_APPROVED, userParams,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					(OrderByComparator)null);
			}
			else if (associationClassName.equals(
						Organization.class.getName())) {

				users = UserLocalServiceUtil.getOrganizationUsers(
					(Long)associationClassPK);
			}
			else if (associationClassName.equals(User.class.getName())) {
				users.add(
					UserLocalServiceUtil.getUser((Long)associationClassPK));
			}
			else if (associationClassName.equals(UserGroup.class.getName())) {
				users = UserLocalServiceUtil.getUserGroupUsers(
					(Long)associationClassPK);
			}

			onAssociation(users);
		}

		public abstract void onAssociation(List<User> users) throws Exception;

	}

}