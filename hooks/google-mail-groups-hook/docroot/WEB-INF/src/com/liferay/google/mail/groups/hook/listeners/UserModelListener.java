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
import com.liferay.portal.kernel.transaction.TransactionCommitCallbackRegistryUtil;
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
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author Matthew Kong
 */
public class UserModelListener extends BaseModelListener<User> {

	@Override
	public void onAfterAddAssociation(
		Object classPK, String associationClassName,
		Object associationClassPK) {

		try {
			final List<Group> groups = getGroups(
				associationClassName, associationClassPK);

			final User user = UserLocalServiceUtil.getUser((Long)classPK);

			Callable<Void> callable = new Callable<Void>() {

				@Override
				public Void call() throws Exception {
					MessageBusUtil.sendMessage(
						DestinationNames.ASYNC_SERVICE,
						new OnAssociationProcessCallable(
							groups, user, "addGroupMembers"));

					return null;
				}

			};

			TransactionCommitCallbackRegistryUtil.registerCallback(callable);
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
			final List<Group> groups = getGroups(
				associationClassName, associationClassPK);

			final User user = UserLocalServiceUtil.getUser((Long)classPK);

			Callable<Void> callable = new Callable<Void>() {

				@Override
				public Void call() throws Exception {
					MessageBusUtil.sendMessage(
						DestinationNames.ASYNC_SERVICE,
						new OnAssociationProcessCallable(
							groups, user, "deleteGroupMembers"));

					return null;
				}

			};

			TransactionCommitCallbackRegistryUtil.registerCallback(callable);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	protected List<Group> getGroups(
			String associationClassName, Object associationClassPK)
		throws PortalException {

		if (associationClassName.equals(Group.class.getName())) {
			return Arrays.asList(
				GroupLocalServiceUtil.getGroup((Long)associationClassPK));
		}

		if (associationClassName.equals(Organization.class.getName())) {
			Organization organization =
				OrganizationLocalServiceUtil.getOrganization(
					(Long)associationClassPK);

			return Arrays.asList(organization.getGroup());
		}

		if (associationClassName.equals(UserGroup.class.getName())) {
			return GroupLocalServiceUtil.getUserGroupGroups(
				(Long)associationClassPK);
		}

		return new ArrayList<>();
	}

	private static Log _log = LogFactoryUtil.getLog(UserModelListener.class);

	private static class OnAssociationProcessCallable
		implements ProcessCallable<Serializable> {

		public OnAssociationProcessCallable(
			List<Group> groups, User user, String action) {

			_groups = groups;
			_user = user;
			_action = action;
		}

		@Override
		public Serializable call() throws ProcessException {
			try {
				for (Group group : _groups) {
					if (!GoogleMailGroupsUtil.isSync(group)) {
						continue;
					}

					if (_action.equals("addGroupMembers")) {
						GoogleDirectoryUtil.addGroupMember(
							GoogleMailGroupsUtil.getGroupEmailAddress(group),
							GoogleMailGroupsUtil.getUserEmailAddress(_user));
					}
					else {
						if (GroupLocalServiceUtil.hasUserGroup(
								_user.getUserId(), group.getGroupId(), true)) {

							continue;
						}

						GoogleDirectoryUtil.deleteGroupMember(
							GoogleMailGroupsUtil.getGroupEmailAddress(group),
							GoogleMailGroupsUtil.getUserEmailAddress(_user));
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
		private List<Group> _groups;
		private User _user;

	}

}