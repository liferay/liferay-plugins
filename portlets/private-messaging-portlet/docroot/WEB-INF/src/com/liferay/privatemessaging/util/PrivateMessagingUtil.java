/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.portlet.messageboards.util.comparator.MessageCreateDateComparator;
import com.liferay.privatemessaging.NoSuchUserThreadException;
import com.liferay.privatemessaging.model.UserThread;
import com.liferay.privatemessaging.service.UserThreadLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Scott Lee
 */
public class PrivateMessagingUtil {

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
			else {
				continue;
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

		return userId;
	}

	public static String getThreadSubject(long mbThreadId)
		throws SystemException {

		List<MBMessage> mbMessages =
			MBMessageLocalServiceUtil.getThreadMessages(
				mbThreadId, WorkflowConstants.STATUS_ANY, 0, 1);

		return mbMessages.get(0).getSubject();
	}

	public static List<User> getThreadUsers(
			long userId, long mbThreadId, boolean all)
		throws PortalException, SystemException {

		List<User> users = new ArrayList<User>();

		if (all) {

			// Users who can view the thread

			List<UserThread> userThreads =
				UserThreadLocalServiceUtil.getMBThreadUserThreads(mbThreadId);

			for (UserThread userThread : userThreads) {
				if (userId == userThread.getUserId()) {
					continue;
				}

				User user = UserLocalServiceUtil.getUser(
					userThread.getUserId());

				if (!users.contains(user)) {
					users.add(user);
				}
			}
		}
		else {

			// Users who have contributed to the thread

			List<MBMessage> mbMessages = getThreadMessages(
				userId, mbThreadId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				false);

			for (MBMessage mbMessage : mbMessages) {
				if (userId == mbMessage.getUserId()) {
					continue;
				}

				User user = UserLocalServiceUtil.getUser(mbMessage.getUserId());

				if (!users.contains(user)) {
					users.add(user);
				}
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