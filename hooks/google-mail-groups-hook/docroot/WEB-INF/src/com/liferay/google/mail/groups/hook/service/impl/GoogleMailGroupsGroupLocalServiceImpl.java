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

package com.liferay.google.mail.groups.hook.service.impl;

import com.liferay.google.mail.groups.util.GoogleMailGroupsUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalService;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceWrapper;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Matthew Kong
 */
public class GoogleMailGroupsGroupLocalServiceImpl
	extends GroupLocalServiceWrapper {

	public GoogleMailGroupsGroupLocalServiceImpl(
		GroupLocalService groupLocalService) {

		super(groupLocalService);
	}

	@Override
	public Group updateFriendlyURL(long groupId, String friendlyURL)
		throws PortalException {

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		String oldFriendlyURL = group.getFriendlyURL();
		String oldGroupEmailAddress = GoogleMailGroupsUtil.getGroupEmailAddress(
			group);

		group = super.updateFriendlyURL(groupId, friendlyURL);

		try {
			updateGGroup(group, oldFriendlyURL, oldGroupEmailAddress);
		}
		catch (Exception e) {
			_log.error(e);
		}

		return group;
	}

	@Override
	public Group updateGroup(
			long groupId, long parentGroupId, String name, String description,
			int type, boolean manualMembership, int membershipRestriction,
			String friendlyURL, boolean active, ServiceContext serviceContext)
		throws PortalException {

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		String oldFriendlyURL = group.getFriendlyURL();
		String oldGroupEmailAddress = GoogleMailGroupsUtil.getGroupEmailAddress(
			group);

		group = super.updateGroup(
			groupId, parentGroupId, name, description, type, manualMembership,
			membershipRestriction, friendlyURL, active, serviceContext);

		try {
			updateGGroup(group, oldFriendlyURL, oldGroupEmailAddress);
		}
		catch (Exception e) {
			_log.error(e);
		}

		return group;
	}

	protected void updateGGroup(
			Group group, String oldFriendlyURL, String oldGroupEmailAddress)
		throws Exception {

		if (oldFriendlyURL.equals(group.getFriendlyURL())) {
			return;
		}

		if (!GoogleMailGroupsUtil.isSync(group)) {
			return;
		}

		GoogleMailGroupsUtil.deleteGGroup(oldGroupEmailAddress);

		String groupEmailAddress = GoogleMailGroupsUtil.getGroupEmailAddress(
			group);

		GoogleMailGroupsUtil.addGGroup(
			group.getDescriptiveName(), groupEmailAddress);

		LinkedHashMap<String, Object> userParams =
			new LinkedHashMap<String, Object>();

		userParams.put("inherit", Boolean.TRUE);
		userParams.put("usersGroups", new Long(group.getGroupId()));

		List<User> users = UserLocalServiceUtil.search(
			group.getCompanyId(), null, WorkflowConstants.STATUS_APPROVED,
			userParams, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			(OrderByComparator)null);

		for (User user : users) {
			GoogleMailGroupsUtil.addGGroupMember(
				groupEmailAddress,
				GoogleMailGroupsUtil.getUserEmailAddress(user));
		}

		GoogleMailGroupsUtil.checkLargeGroup(group);
	}

	private static Log _log = LogFactoryUtil.getLog(
		GoogleMailGroupsGroupLocalServiceImpl.class);

}