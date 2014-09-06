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

package com.liferay.google.groups.hook.listeners;

import com.liferay.google.groups.util.GoogleGroupsUtil;
import com.liferay.googleapps.GGroupManager;
import com.liferay.googleapps.GoogleAppsFactoryUtil;
import com.liferay.portal.ModelListenerException;
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

			GGroupManager gGroupManager =
				GoogleAppsFactoryUtil.getGGroupManager(user.getCompanyId());

			for (Group group : groups) {
				if (!GoogleGroupsUtil.isSync(group)) {
					continue;
				}

				gGroupManager.addGGroupMember(
					GoogleGroupsUtil.getGroupEmailAddress(group),
					user.getEmailAddress());
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

			GGroupManager gGroupManager =
				GoogleAppsFactoryUtil.getGGroupManager(user.getCompanyId());

			for (Group group : groups) {
				if (!GoogleGroupsUtil.isSync(group)) {
					continue;
				}

				if (GroupLocalServiceUtil.hasUserGroup(
						user.getUserId(), group.getGroupId(), true)) {

					continue;
				}

				gGroupManager.deleteGGroupMember(
					GoogleGroupsUtil.getGroupEmailAddress(group),
					user.getEmailAddress());
			}
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

}