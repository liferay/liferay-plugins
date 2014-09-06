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
import com.liferay.google.groups.util.PortletPropsValues;
import com.liferay.googleapps.GGroupManager;
import com.liferay.googleapps.GoogleAppsFactoryUtil;
import com.liferay.portal.ModelListenerException;
import com.liferay.portal.kernel.util.StringPool;
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

		if (!associationClassName.equals(UserGroup.class.getName()) &&
			!associationClassName.equals(Organization.class.getName())) {

			return;
		}

		try {
			Group group = GroupLocalServiceUtil.getGroup((Long)classPK);

			if (!GoogleGroupsUtil.isSync(group)) {
				return;
			}

			GGroupManager gGroupManager =
				GoogleAppsFactoryUtil.getGGroupManager(group.getCompanyId());

			String groupEmailAddress = GoogleGroupsUtil.getGroupEmailAddress(
				group);

			List<User> users = new ArrayList<User>();

			if (associationClassName.equals(UserGroup.class.getName())) {
				users = UserLocalServiceUtil.getUserGroupUsers(
					(Long)associationClassPK);
			}
			else {
				users = UserLocalServiceUtil.getOrganizationUsers(
					(Long)associationClassPK);
			}

			for (User user : users) {
				gGroupManager.addGGroupMember(
					groupEmailAddress, user.getEmailAddress());
			}
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	@Override
	public void onAfterCreate(Group group) throws ModelListenerException {
		if (!GoogleGroupsUtil.isSync(group)) {
			return;
		}

		try {
			GGroupManager gGroupManager =
				GoogleAppsFactoryUtil.getGGroupManager(group.getCompanyId());

			gGroupManager.addGGroup(
				GoogleGroupsUtil.getGroupEmailAddress(group),
				group.getDescriptiveName(), StringPool.BLANK,
				PortletPropsValues.GOOGLE_GROUPS_EMAIL_PERMISSION);
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	@Override
	public void onAfterRemove(Group group) throws ModelListenerException {
		if (!GoogleGroupsUtil.isSync(group)) {
			return;
		}

		try {
			GGroupManager gGroupManager =
				GoogleAppsFactoryUtil.getGGroupManager(group.getCompanyId());

			gGroupManager.deleteGGroup(
				GoogleGroupsUtil.getGroupEmailAddress(group));
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

		if (!associationClassName.equals(UserGroup.class.getName()) &&
			!associationClassName.equals(Organization.class.getName())) {

			return;
		}

		try {
			Group group = GroupLocalServiceUtil.getGroup((Long)classPK);

			if (!GoogleGroupsUtil.isSync(group)) {
				return;
			}

			GGroupManager gGroupManager =
				GoogleAppsFactoryUtil.getGGroupManager(group.getCompanyId());

			String groupEmailAddress = GoogleGroupsUtil.getGroupEmailAddress(
				group);

			List<User> users = new ArrayList<User>();

			if (associationClassName.equals(UserGroup.class.getName())) {
				users = UserLocalServiceUtil.getUserGroupUsers(
					(Long)associationClassPK);
			}
			else {
				users = UserLocalServiceUtil.getOrganizationUsers(
					(Long)associationClassPK);
			}

			for (User user : users) {
				if (GroupLocalServiceUtil.hasUserGroup(
						user.getUserId(), group.getGroupId(), true)) {

					continue;
				}

				gGroupManager.deleteGGroupMember(
					groupEmailAddress, user.getEmailAddress());
			}
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

}