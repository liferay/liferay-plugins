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

import com.google.api.services.admin.directory.Directory;

import com.liferay.google.mail.groups.util.GoogleMailGroupsUtil;
import com.liferay.portal.ModelListenerException;
import com.liferay.portal.model.BaseModelListener;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Matthew Kong
 */
public class GroupModelListener extends BaseModelListener<Group> {

	@Override
	public void onAfterAddAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException {

		try {
			new OnAssociation(
				classPK, associationClassName, associationClassPK) {

				@Override
				public void onAssociation(
						User user, Group group, Directory directory)
					throws Exception {

					GoogleMailGroupsUtil.addGGroupMember(
						directory,
						GoogleMailGroupsUtil.getGroupEmailAddress(group),
						GoogleMailGroupsUtil.getUserEmailAddress(user));
				}

			};
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	@Override
	public void onAfterCreate(Group group) throws ModelListenerException {
		if (!GoogleMailGroupsUtil.isSync(group)) {
			return;
		}

		try {
			GoogleMailGroupsUtil.addGGroup(
				GoogleMailGroupsUtil.getDirectory(), group.getDescriptiveName(),
				GoogleMailGroupsUtil.getGroupEmailAddress(group));
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	@Override
	public void onAfterRemove(Group group) throws ModelListenerException {
		if (!GoogleMailGroupsUtil.isSync(group)) {
			return;
		}

		try {
			GoogleMailGroupsUtil.deleteGGroup(
				GoogleMailGroupsUtil.getDirectory(),
				GoogleMailGroupsUtil.getGroupEmailAddress(group));
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
			new OnAssociation(
				classPK, associationClassName, associationClassPK) {

				@Override
				public void onAssociation(
						User user, Group group, Directory directory)
					throws Exception {

					if (GroupLocalServiceUtil.hasUserGroup(
							user.getUserId(), group.getGroupId(), true)) {

						return;
					}

					GoogleMailGroupsUtil.deleteGGroupMember(
						directory,
						GoogleMailGroupsUtil.getGroupEmailAddress(group),
						GoogleMailGroupsUtil.getUserEmailAddress(user));
				}

			};
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	private abstract class OnAssociation {

		public OnAssociation(
				Object classPK, String associationClassName,
				Object associationClassPK)
			throws Exception {

			if (!associationClassName.equals(Organization.class.getName()) &&
				!associationClassName.equals(UserGroup.class.getName())) {

				return;
			}

			Group group = GroupLocalServiceUtil.getGroup((Long)classPK);

			if (!GoogleMailGroupsUtil.isSync(group)) {
				return;
			}

			List<User> users = new ArrayList<User>();

			if (associationClassName.equals(Organization.class.getName())) {
				users = UserLocalServiceUtil.getOrganizationUsers(
					(Long)associationClassPK);
			}
			else {
				users = UserLocalServiceUtil.getUserGroupUsers(
					(Long)associationClassPK);
			}

			Directory directory = GoogleMailGroupsUtil.getDirectory();

			for (User user : users) {
				onAssociation(user, group, directory);
			}
		}

		public abstract void onAssociation(
				User user, Group group, Directory directory)
			throws Exception;
	}

}