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
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.process.ProcessCallable;
import com.liferay.portal.kernel.process.ProcessException;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.transaction.TransactionCommitCallbackUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author Matthew Kong
 */
public class RoleModelListener extends BaseModelListener<Role> {

	@Override
	public void onAfterAddAssociation(
		Object classPK, String associationClassName,
		Object associationClassPK) {

		try {
			final List<User> users = getUsers(
				classPK, associationClassName, associationClassPK);

			Callable<Void> callable = new Callable<Void>() {

				@Override
				public Void call() throws Exception {
					MessageBusUtil.sendMessage(
						DestinationNames.ASYNC_SERVICE,
						new OnAssociationProcessCallable(users, "MANAGER"));

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
	public void onAfterRemoveAssociation(
		Object classPK, String associationClassName,
		Object associationClassPK) {

		try {
			final List<User> users = getUsers(
				classPK, associationClassName, associationClassPK);

			Callable<Void> callable = new Callable<Void>() {

				@Override
				public Void call() throws Exception {
					MessageBusUtil.sendMessage(
						DestinationNames.ASYNC_SERVICE,
						new OnAssociationProcessCallable(users, "MEMBER"));

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

		Role role = RoleLocalServiceUtil.getRole((Long)classPK);

		String roleName = role.getName();

		if (!roleName.equals(PortletPropsValues.EMAIL_LARGE_GROUP_ROLE)) {
			return null;
		}

		if (associationClassName.equals(Group.class.getName())) {
			LinkedHashMap<String, Object> userParams = new LinkedHashMap<>();

			userParams.put("inherit", Boolean.TRUE);
			userParams.put("usersGroups", associationClassPK);

			return UserLocalServiceUtil.search(
				role.getCompanyId(), null, WorkflowConstants.STATUS_APPROVED,
				userParams, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				(OrderByComparator)null);
		}

		if (associationClassName.equals(Organization.class.getName())) {
			return UserLocalServiceUtil.getOrganizationUsers(
				(Long)associationClassPK);
		}

		if (associationClassName.equals(User.class.getName())) {
			return Arrays.asList(
				UserLocalServiceUtil.getUser((Long)associationClassPK));
		}

		if (associationClassName.equals(UserGroup.class.getName())) {
			return UserLocalServiceUtil.getUserGroupUsers(
				(Long)associationClassPK);
		}

		return new ArrayList<>();
	}

	private static Log _log = LogFactoryUtil.getLog(RoleModelListener.class);

	private static class OnAssociationProcessCallable
		implements ProcessCallable<Serializable> {

		public OnAssociationProcessCallable(
			List<User> users, String groupMemberRole) {

			_users = users;
			_groupMemberRole = groupMemberRole;
		}

		@Override
		public Serializable call() throws ProcessException {
			try {
				GoogleMailGroupsUtil.updateGroupMemberRoles(
					_users, _groupMemberRole);
			}
			catch (Exception e) {
				throw new ProcessException(e);
			}

			return null;
		}

		private static final long serialVersionUID = 1L;

		private String _groupMemberRole;
		private List<User> _users;

	}

}