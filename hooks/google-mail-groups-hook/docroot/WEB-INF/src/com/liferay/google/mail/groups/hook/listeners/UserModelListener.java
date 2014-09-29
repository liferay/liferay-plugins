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

import com.liferay.google.mail.groups.util.GoogleDirectoryUtil;
import com.liferay.google.mail.groups.util.GoogleMailGroupsUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.process.ProcessCallable;
import com.liferay.portal.kernel.process.ProcessException;
import com.liferay.portal.model.BaseModelListener;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Matthew Kong
 */
public class UserModelListener extends BaseModelListener<User> {

	@Override
	public void onAfterAddAssociation(
		Object classPK, String associationClassName,
		Object associationClassPK) {

		MessageBusUtil.sendMessage(
			DestinationNames.ASYNC_SERVICE,
			new OnAssociationProcessCallable(
				classPK, associationClassName, associationClassPK,
				"addGroupMembers"));
	}

	@Override
	public void onAfterRemoveAssociation(
		Object classPK, String associationClassName,
		Object associationClassPK) {

		MessageBusUtil.sendMessage(
			DestinationNames.ASYNC_SERVICE,
			new OnAssociationProcessCallable(
				classPK, associationClassName, associationClassPK,
				"deleteGroupMembers"));
	}

	private static Log _log = LogFactoryUtil.getLog(UserModelListener.class);

	private static class OnAssociationProcessCallable
		implements ProcessCallable<Serializable> {

		public OnAssociationProcessCallable(
			Object classPK, String associationClassName,
			Object associationClassPK, String action) {

			_action = action;
			_associationClassName = associationClassName;
			_associationClassPK = (Long)associationClassPK;
			_classPK = (Long)classPK;
		}

		@Override
		public Serializable call() throws ProcessException {
			try {
				List<Group> groups = new ArrayList<Group>();

				if (_associationClassName.equals(Group.class.getName())) {
					groups.add(
						GroupLocalServiceUtil.getGroup(_associationClassPK));
				}
				else if (_associationClassName.equals(
							Organization.class.getName())) {

					Organization organization =
						OrganizationLocalServiceUtil.getOrganization(
							_associationClassPK);

					groups.add(organization.getGroup());
				}
				else if (_associationClassName.equals(
							UserGroup.class.getName())) {

					groups = GroupLocalServiceUtil.getUserGroupGroups(
						_associationClassPK);
				}

				User user = UserLocalServiceUtil.getUser(_classPK);

				for (Group group : groups) {
					if (!GoogleMailGroupsUtil.isSync(group)) {
						continue;
					}

					if (_action.equals("addGroupMembers")) {
						GoogleDirectoryUtil.addGroupMember(
							GoogleMailGroupsUtil.getGroupEmailAddress(group),
							GoogleMailGroupsUtil.getUserEmailAddress(user));
					}
					else {
						if (GroupLocalServiceUtil.hasUserGroup(
								user.getUserId(), group.getGroupId(), true)) {

							continue;
						}

						GoogleDirectoryUtil.deleteGroupMember(
							GoogleMailGroupsUtil.getGroupEmailAddress(group),
							GoogleMailGroupsUtil.getUserEmailAddress(user));
					}

					GoogleMailGroupsUtil.checkLargeGroup(group);
				}
			}
			catch (Exception e) {
				throw new ProcessException(e);
			}

			return null;
		}

		private static final long serialVersionUID = 1L;

		private String _action;
		private String _associationClassName;
		private long _associationClassPK;
		private long _classPK;

	}

}