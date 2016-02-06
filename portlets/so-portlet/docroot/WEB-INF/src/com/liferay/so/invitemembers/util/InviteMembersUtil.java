/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.so.invitemembers.util;

import com.liferay.portal.kernel.dao.orm.CustomSQLParam;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.comparator.UserFirstNameComparator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Jonathan Lee
 */
public class InviteMembersUtil {

	public static List<User> getAvailableUsers(
			long companyId, long groupId, String keywords, int start, int end)
		throws Exception {

		LinkedHashMap usersParams = new LinkedHashMap();

		usersParams.put(
			"usersInvited",
			new CustomSQLParam(
				CustomSQLUtil.get(
					"com.liferay.portal.kernel.service.persistence.UserFinder." +
						"filterByUsersGroupsGroupId"),
				groupId));

		return UserLocalServiceUtil.search(
			companyId, keywords, WorkflowConstants.STATUS_APPROVED, usersParams,
			start, end, new UserFirstNameComparator(true));
	}

	public static int getAvailableUsersCount(
			long companyId, long groupId, String keywords)
		throws Exception {

		LinkedHashMap usersParams = new LinkedHashMap();

		usersParams.put(
			"usersInvited",
			new CustomSQLParam(
				CustomSQLUtil.get(
					"com.liferay.portal.kernel.service.persistence.UserFinder." +
						"filterByUsersGroupsGroupId"),
				groupId));

		return UserLocalServiceUtil.searchCount(
			companyId, keywords, WorkflowConstants.STATUS_APPROVED,
			usersParams);
	}

}