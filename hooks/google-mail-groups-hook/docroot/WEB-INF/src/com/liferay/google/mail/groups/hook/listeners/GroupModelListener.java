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

import com.liferay.google.apps.connector.util.GoogleDirectoryUtil;
import com.liferay.google.mail.groups.util.GoogleMailGroupsUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.process.ProcessCallable;
import com.liferay.portal.kernel.process.ProcessException;
import com.liferay.portal.kernel.transaction.TransactionCommitCallbackUtil;
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
import java.util.concurrent.Callable;

/**
 * @author Matthew Kong
 */
public class GroupModelListener extends BaseModelListener<Group> {

	@Override
	public void onAfterAddAssociation(
		Object classPK, String associationClassName,
		Object associationClassPK) {

		try {
			final Group group = GroupLocalServiceUtil.getGroup((Long)classPK);

			final List<User> users = getUsers(
				classPK, associationClassName, associationClassPK);

			Callable<Void> callable = new Callable<Void>() {

				@Override
				public Void call() throws Exception {
					MessageBusUtil.sendMessage(
						DestinationNames.ASYNC_SERVICE,
						new OnAssociationProcessCallable(
							group, users, "addGroupMembers"));

					return null;
				}

			};

			TransactionCommitCallbackUtil.registerCallback(callable);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	@Override
	public void onAfterCreate(Group group) {
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
	public void onAfterRemove(Group group) {
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

		try {
			final Group group = GroupLocalServiceUtil.getGroup((Long)classPK);

			final List<User> users = getUsers(
				classPK, associationClassName, associationClassPK);

			Callable<Void> callable = new Callable<Void>() {

				@Override
				public Void call() throws Exception {
					MessageBusUtil.sendMessage(
						DestinationNames.ASYNC_SERVICE,
						new OnAssociationProcessCallable(
							group, users, "deleteGroupMembers"));

					return null;
				}

			};

			TransactionCommitCallbackUtil.registerCallback(callable);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	protected List<User> getUsers(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws PortalException {

		if (!associationClassName.equals(
				Organization.class.getName()) &&
			!associationClassName.equals(UserGroup.class.getName())) {

			return new ArrayList<>();
		}

		Group group = GroupLocalServiceUtil.getGroup((Long)classPK);

		if (!GoogleMailGroupsUtil.isSync(group)) {
			return new ArrayList<>();
		}

		if (associationClassName.equals(Organization.class.getName())) {
			return UserLocalServiceUtil.getOrganizationUsers(
				(Long)associationClassPK);
		}

		return UserLocalServiceUtil.getUserGroupUsers((Long)associationClassPK);
	}

	private static Log _log = LogFactoryUtil.getLog(GroupModelListener.class);

	private static class OnAssociationProcessCallable
		implements ProcessCallable<Serializable> {

		public OnAssociationProcessCallable(
			Group group, List<User> users, String action) {

			_group = group;
			_users = users;
			_action = action;
		}

		@Override
		public Serializable call() throws ProcessException {
			try {
				if (_action.equals("addGroupMembers")) {
					for (User user : _users) {
						GoogleDirectoryUtil.addGroupMember(
							GoogleMailGroupsUtil.getGroupEmailAddress(_group),
							GoogleMailGroupsUtil.getUserEmailAddress(user));
					}
				}
				else {
					for (User user : _users) {
						if (GroupLocalServiceUtil.hasUserGroup(
								user.getUserId(), _group.getGroupId(), true)) {

							continue;
						}

						GoogleDirectoryUtil.deleteGroupMember(
							GoogleMailGroupsUtil.getGroupEmailAddress(_group),
							GoogleMailGroupsUtil.getUserEmailAddress(user));
					}
				}

				GoogleMailGroupsUtil.checkLargeGroup(_group);
			}
			catch (Exception e) {
				throw new ProcessException(e);
			}

			return null;
		}

		private static final long serialVersionUID = 1L;

		private String _action;
		private Group _group;
		private List<User> _users;

	}

}