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
import com.liferay.portal.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.BaseModelListener;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Matthew Kong
 */
public class UserModelListener extends BaseModelListener<User> {

	@Override
	public void onAfterAddAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException {

		try {
			new OnAssociation(
				classPK, associationClassName, associationClassPK) {

				@Override
				public void onAssociation(User user, Group group)
					throws Exception {

					GoogleMailGroupsUtil.addGGroupMember(
						GoogleMailGroupsUtil.getGroupEmailAddress(group),
						GoogleMailGroupsUtil.getUserEmailAddress(user));

					GoogleMailGroupsUtil.checkLargeGroup(group);
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
				public void onAssociation(User user, Group group)
					throws Exception {

					if (GroupLocalServiceUtil.hasUserGroup(
							user.getUserId(), group.getGroupId(), true)) {

						return;
					}

					GoogleMailGroupsUtil.deleteGGroupMember(
						GoogleMailGroupsUtil.getGroupEmailAddress(group),
						GoogleMailGroupsUtil.getUserEmailAddress(user));

					GoogleMailGroupsUtil.checkLargeGroup(group);
				}

			};
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(UserModelListener.class);

	private abstract class OnAssociation {

		public OnAssociation(
				Object classPK, String associationClassName,
				Object associationClassPK)
			throws Exception {

			List<Group> groups = new ArrayList<Group>();

			if (associationClassName.equals(Group.class.getName())) {
				groups.add(
					GroupLocalServiceUtil.getGroup((Long)associationClassPK));
			}
			else if (associationClassName.equals(
						Organization.class.getName())) {

				Organization organization =
					OrganizationLocalServiceUtil.getOrganization(
						(Long)associationClassPK);

				groups.add(organization.getGroup());
			}
			else if (associationClassName.equals(UserGroup.class.getName())) {
				groups = GroupLocalServiceUtil.getUserGroupGroups(
					(Long)associationClassPK);
			}

			User user = UserLocalServiceUtil.getUser((Long)classPK);

			for (Group group : groups) {
				if (!GoogleMailGroupsUtil.isSync(group)) {
					continue;
				}

				onAssociation(user, group);
			}
		}

		public abstract void onAssociation(User user, Group group)
			throws Exception;

	}

}