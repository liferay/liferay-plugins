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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.process.ProcessCallable;
import com.liferay.portal.kernel.process.ProcessException;
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

import java.io.Serializable;

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
		Object associationClassPK) {

		MessageBusUtil.sendMessage(
			DestinationNames.ASYNC_SERVICE,
			new OnAssociationProcessCallable(
				classPK, associationClassName, associationClassPK,
				"addGroupManagers"));
	}

	@Override
	public void onAfterRemoveAssociation(
		Object classPK, String associationClassName,
		Object associationClassPK) {

		MessageBusUtil.sendMessage(
			DestinationNames.ASYNC_SERVICE,
			new OnAssociationProcessCallable(
				classPK, associationClassName, associationClassPK,
				"removeGroupManagers"));
	}

	private static Log _log = LogFactoryUtil.getLog(RoleModelListener.class);

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
				Role role = RoleLocalServiceUtil.getRole(_classPK);

				String roleName = role.getName();

				if (!roleName.equals(
						PortletPropsValues.EMAIL_LARGE_GROUP_ROLE)) {

					return null;
				}

				List<User> users = new ArrayList<User>();

				if (_associationClassName.equals(Group.class.getName())) {
					LinkedHashMap<String, Object> userParams =
						new LinkedHashMap<String, Object>();

					userParams.put("inherit", Boolean.TRUE);
					userParams.put("usersGroups", _associationClassPK);

					users = UserLocalServiceUtil.search(
						role.getCompanyId(), null,
						WorkflowConstants.STATUS_APPROVED, userParams,
						QueryUtil.ALL_POS, QueryUtil.ALL_POS,
						(OrderByComparator)null);
				}
				else if (_associationClassName.equals(
							Organization.class.getName())) {

					users = UserLocalServiceUtil.getOrganizationUsers(
						_associationClassPK);
				}
				else if (_associationClassName.equals(User.class.getName())) {
					users.add(
						UserLocalServiceUtil.getUser(_associationClassPK));
				}
				else if (_associationClassName.equals(
							UserGroup.class.getName())) {

					users = UserLocalServiceUtil.getUserGroupUsers(
						_associationClassPK);
				}

				if (_action.equals("addGroupManagers")) {
					GoogleMailGroupsUtil.addGroupManagers(users);
				}
				else {
					GoogleMailGroupsUtil.removeGroupManagers(users);
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