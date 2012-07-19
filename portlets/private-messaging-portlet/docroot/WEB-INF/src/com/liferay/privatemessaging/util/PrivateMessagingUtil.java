/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.privatemessaging.util;

import com.liferay.portal.NoSuchRoleException;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.DateUtil;
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
import com.liferay.portlet.social.model.SocialRelationConstants;
import com.liferay.privatemessaging.NoSuchUserThreadException;
import com.liferay.privatemessaging.model.UserThread;
import com.liferay.privatemessaging.service.UserThreadLocalServiceUtil;

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
			List<Group> usersGroups = GroupLocalServiceUtil.getUserGroups(
				userId, true);

			long[] usersGroupsIds = new long[usersGroups.size()];

			for (int i = 0; i < usersGroups.size(); i++) {
				Group group = usersGroups.get(i);

				usersGroupsIds[i] = group.getGroupId();
			}

			params.put("usersGroups", usersGroupsIds);
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

	public static MBMessage getLastThreadMessage(long userId, long mbThreadId)
		throws PortalException, SystemException {

		List<MBMessage> mbMessages = getThreadMessages(
			userId, mbThreadId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, false);

		MBMessage lastMBMessage = mbMessages.get(0);

		return lastMBMessage;
	}

	public static List<MBMessage> getThreadMessages(
			long userId, long mbThreadId, int start, int end, boolean ascending)
		throws PortalException, SystemException {

		UserThread userThread = UserThreadLocalServiceUtil.getUserThread(
			userId, mbThreadId);

		MBMessage topMBMessage = MBMessageLocalServiceUtil.getMBMessage(
			userThread.getTopMBMessageId());

		List<MBMessage> mbMessages =
			MBMessageLocalServiceUtil.getThreadMessages(
				mbThreadId, WorkflowConstants.STATUS_ANY,
				new MessageCreateDateComparator(ascending));

		List<MBMessage> filteredMBMessages = new ArrayList<MBMessage>();

		for (MBMessage mbMessage : mbMessages) {
			int compareTo = DateUtil.compareTo(
				topMBMessage.getCreateDate(), mbMessage.getCreateDate());

			if (compareTo <= 0) {
				filteredMBMessages.add(mbMessage);
			}
		}

		if (filteredMBMessages.isEmpty()) {
			return filteredMBMessages;
		}
		else if ((start == QueryUtil.ALL_POS) || (end == QueryUtil.ALL_POS)) {
			return filteredMBMessages;
		}
		else if (end > filteredMBMessages.size()) {
			end = filteredMBMessages.size();
		}

		return filteredMBMessages.subList(start, end);
	}

	public static int getThreadMessagesCount(long userId, long mbThreadId)
		throws PortalException, SystemException {

		List<MBMessage> mbMessages = getThreadMessages(
			userId, mbThreadId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, true);

		return mbMessages.size();
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

		List<MBMessage> mbMessages = getThreadMessages(
			userId, mbThreadId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, false);

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