/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.privatemessaging.util;

import com.liferay.portal.NoSuchRoleException;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.comparator.UserFirstNameComparator;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.portlet.messageboards.util.comparator.MessageCreateDateComparator;
import com.liferay.portlet.sites.util.SitesUtil;
import com.liferay.portlet.social.model.SocialRelationConstants;
import com.liferay.privatemessaging.NoSuchUserThreadException;
import com.liferay.privatemessaging.model.UserThread;
import com.liferay.privatemessaging.service.UserThreadLocalServiceUtil;
import com.liferay.privatemessaging.service.UserThreadServiceUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Scott Lee
 * @author Eudaldo Alonso
 */
public class PrivateMessagingUtil {

	public static JSONObject getJSONRecipients(
			long userId, String type, String keywords, int start, int end)
		throws PortalException, SystemException {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		User user = UserLocalServiceUtil.getUser(userId);

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		if (type.equals("site")) {
			params.put("inherit", Boolean.TRUE);

			LinkedHashMap<String, Object> groupParams =
				new LinkedHashMap<String, Object>();

			groupParams.put("inherit", Boolean.FALSE);
			groupParams.put("site", Boolean.TRUE);
			groupParams.put("usersGroups", userId);

			List<Group> groups = GroupLocalServiceUtil.search(
				user.getCompanyId(), groupParams, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

			params.put(
				"usersGroups",
				SitesUtil.filterGroups(
					groups,
					PortletPropsValues.AUTOCOMPLETE_RECIPIENT_SITE_EXCLUDES));
		}
		else if (!type.equals("all")) {
			params.put(
				"socialRelationType",
				new Long[] {
					userId, new Long(SocialRelationConstants.TYPE_BI_CONNECTION)
				});
		}

		try {
			Role role = RoleLocalServiceUtil.getRole(
				user.getCompanyId(), RoleConstants.SOCIAL_OFFICE_USER);

			if (role != null) {
				params.put("usersRoles", new Long(role.getRoleId()));
			}
		}
		catch (NoSuchRoleException nsre) {
		}

		int total = UserLocalServiceUtil.searchCount(
			user.getCompanyId(), keywords, WorkflowConstants.STATUS_APPROVED,
			params);

		jsonObject.put("total", total);

		List<User> users = UserLocalServiceUtil.search(
			user.getCompanyId(), keywords, WorkflowConstants.STATUS_APPROVED,
			params, start, end, new UserFirstNameComparator(true));

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (User curUser : users) {
			JSONObject userJSONObject = JSONFactoryUtil.createJSONObject();

			StringBundler sb = new StringBundler(5);

			sb.append(curUser.getFullName());
			sb.append(CharPool.SPACE);
			sb.append(CharPool.LESS_THAN);
			sb.append(curUser.getScreenName());
			sb.append(CharPool.GREATER_THAN);

			userJSONObject.put("name", sb.toString());

			jsonArray.put(userJSONObject);
		}

		jsonObject.put("users", jsonArray);

		return jsonObject;
	}

	/**
	 * Each thread has a user that represents that thread. This person is either
	 * the last user to post on that thread (exluding himself), or if he is the
	 * only person to have posted on the thread, then he will the represenative.
	 */
	public static long getThreadRepresentativeUserId(
			long userId, long mbThreadId)
		throws SystemException {

		List<MBMessage> mbMessages =
			MBMessageLocalServiceUtil.getThreadMessages(
				mbThreadId, WorkflowConstants.STATUS_ANY,
				new MessageCreateDateComparator(false));

		for (MBMessage mbMessage : mbMessages) {
			if (userId != mbMessage.getUserId()) {
				return mbMessage.getUserId();
			}
		}

		List<UserThread> userThreads =
			UserThreadLocalServiceUtil.getMBThreadUserThreads(mbThreadId);

		for (UserThread userThread : userThreads) {
			if (userId != userThread.getUserId()) {
				return userThread.getUserId();
			}
		}

		return userId;
	}

	public static String getThreadSubject(long mbThreadId)
		throws SystemException {

		List<MBMessage> mbMessages =
			MBMessageLocalServiceUtil.getThreadMessages(
				mbThreadId, WorkflowConstants.STATUS_ANY, 0, 1);

		return mbMessages.get(0).getSubject();
	}

	public static List<User> getThreadUsers(long userId, long mbThreadId)
		throws PortalException, SystemException {

		List<User> users = new ArrayList<User>();

		// Users who have contributed to the thread

		List<MBMessage> mbMessages = UserThreadServiceUtil.getThreadMessages(
			mbThreadId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, false);

		for (MBMessage mbMessage : mbMessages) {
			if (userId == mbMessage.getUserId()) {
				continue;
			}

			User user = UserLocalServiceUtil.fetchUser(mbMessage.getUserId());

			if (user == null) {
				user = UserLocalServiceUtil.createUser(mbMessage.getUserId());

				user.setFirstName(mbMessage.getUserName());
				user.setStatus(WorkflowConstants.STATUS_INACTIVE);
			}

			if (!users.contains(user)) {
				users.add(user);
			}
		}

		// Users who can view the thread

		List<UserThread> userThreads =
			UserThreadLocalServiceUtil.getMBThreadUserThreads(mbThreadId);

		for (UserThread userThread : userThreads) {
			if (userId == userThread.getUserId()) {
				continue;
			}

			User user = UserLocalServiceUtil.fetchUser(userThread.getUserId());

			if (user == null) {
				user = UserLocalServiceUtil.createUser(userThread.getUserId());

				user.setFirstName(userThread.getUserName());
				user.setStatus(WorkflowConstants.STATUS_INACTIVE);
			}

			if (!users.contains(user)) {
				users.add(user);
			}
		}

		return users;
	}

	public static boolean isUserPartOfThread(long userId, long mbThreadId)
		throws PortalException, SystemException {

		try {
			UserThreadLocalServiceUtil.getUserThread(userId, mbThreadId);

			return true;
		}
		catch (NoSuchUserThreadException nsute) {
			return false;
		}
	}

}