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
import com.liferay.portal.ModelListenerException;
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
import com.liferay.portal.service.UserLocalServiceUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Matthew Kong
 */
public class GroupModelListener extends BaseModelListener<Group> {

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
	public void onAfterCreate(Group group) throws ModelListenerException {
		if (!GoogleMailGroupsUtil.isSync(group)) {
			return;
		}

		try {
			GoogleDirectoryUtil.addGroup(
				group.getDescriptiveName(),
				GoogleMailGroupsUtil.getGroupEmailAddress(group));
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	@Override
	public void onAfterRemove(Group group) throws ModelListenerException {
		if (!GoogleMailGroupsUtil.isSync(group)) {
			return;
		}

		try {
			GoogleDirectoryUtil.deleteGroup(
				GoogleMailGroupsUtil.getGroupEmailAddress(group));
		}
		catch (Exception e) {
			_log.error(e, e);
		}
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

	private static Log _log = LogFactoryUtil.getLog(GroupModelListener.class);

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
				if (!_associationClassName.equals(
						Organization.class.getName()) &&
					!_associationClassName.equals(UserGroup.class.getName())) {

					return null;
				}

				Group group = GroupLocalServiceUtil.getGroup(_classPK);

				if (!GoogleMailGroupsUtil.isSync(group)) {
					return null;
				}

				List<User> users = new ArrayList<User>();

				if (_associationClassName.equals(
						Organization.class.getName())) {

					users = UserLocalServiceUtil.getOrganizationUsers(
						_associationClassPK);
				}
				else {
					users = UserLocalServiceUtil.getUserGroupUsers(
						_associationClassPK);
				}

				if (_action.equals("addGroupMember")) {
					for (User user : users) {
						GoogleDirectoryUtil.addGroupMember(
							GoogleMailGroupsUtil.getGroupEmailAddress(group),
							GoogleMailGroupsUtil.getUserEmailAddress(user));
					}
				}
				else {
					for (User user : users) {
						if (GroupLocalServiceUtil.hasUserGroup(
								user.getUserId(), group.getGroupId(), true)) {

							continue;
						}

						GoogleDirectoryUtil.deleteGroupMember(
							GoogleMailGroupsUtil.getGroupEmailAddress(group),
							GoogleMailGroupsUtil.getUserEmailAddress(user));
					}
				}

				GoogleMailGroupsUtil.checkLargeGroup(group);
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