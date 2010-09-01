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

package com.liferay.privatemessaging.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.model.MBMessageConstants;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBThreadLocalServiceUtil;
import com.liferay.privatemessaging.model.UserThread;
import com.liferay.privatemessaging.service.base.UserThreadLocalServiceBaseImpl;
import com.liferay.privatemessaging.util.PrivateMessagingConstants;

import java.util.Date;
import java.util.List;

/**
 * @author Scott Lee
 */
public class UserThreadLocalServiceImpl extends UserThreadLocalServiceBaseImpl {

	public MBMessage addPrivateMessage(
			long userId, long mbThreadId, String to, String subject,
			String body, List<ObjectValuePair<String, byte[]>> files)
		throws PortalException, SystemException {

		long parentMBMessageId = MBMessageConstants.DEFAULT_PARENT_MESSAGE_ID;

		if (mbThreadId != 0) {
			List<MBMessage> mbMessages =
				MBMessageLocalServiceUtil.getThreadMessages(
					mbThreadId, WorkflowConstants.STATUS_ANY);

			MBMessage lastMBMessage = mbMessages.get(mbMessages.size() - 1);

			parentMBMessageId = lastMBMessage.getMessageId();
			subject = lastMBMessage.getSubject();
		}

		long[] recipientUserIds = GetterUtil.getLongValues(
			StringUtil.split(to));

		return addPrivateMessage(
			userId, mbThreadId, parentMBMessageId, recipientUserIds, subject,
			body, files);
	}

	public MBMessage addPrivateMessageBranch(
			long userId, long parentMBMessageId, String body,
			List<ObjectValuePair<String, byte[]>> files)
		throws PortalException, SystemException {

		long mbThreadId = 0;

		MBMessage parentMessage = MBMessageLocalServiceUtil.getMBMessage(
			parentMBMessageId);

		long[] recipientUserIds = {parentMessage.getUserId()};

		return addPrivateMessage(
			userId, mbThreadId, parentMBMessageId, recipientUserIds,
			parentMessage.getSubject(), body, files);
	}

	public void addUserThread(
			long userId, long mbThreadId, long topMBMessageId, boolean read,
			boolean deleted)
		throws PortalException, SystemException {

		long userThreadId = counterLocalService.increment();

		User user = UserLocalServiceUtil.getUser(userId);

		UserThread userThread = userThreadPersistence.create(userThreadId);

		userThread.setCompanyId(user.getCompanyId());
		userThread.setUserId(userId);
		userThread.setCreateDate(new Date());
		userThread.setModifiedDate(new Date());
		userThread.setMbThreadId(mbThreadId);
		userThread.setTopMBMessageId(topMBMessageId);
		userThread.setRead(read);
		userThread.setDeleted(deleted);

		userThreadPersistence.update(userThread, false);
	}

	public void deleteUser(long userId)
		throws PortalException, SystemException {

		List<UserThread> userThreads = userThreadPersistence.findByUserId(
			userId);

		for (UserThread userThread : userThreads) {
			MBThreadLocalServiceUtil.deleteMBThread(userThread.getMbThreadId());

			userThreadPersistence.remove(userThread.getUserThreadId());
		}
	}

	public void deleteUserThread(long userId, long mbThreadId)
		throws PortalException, SystemException {

		UserThread userThread = userThreadPersistence.findByU_M(
			userId, mbThreadId);

		userThread.setDeleted(true);

		userThreadPersistence.update(userThread, false);
	}

	public void markUserThreadAsRead(long userId, long mbThreadId)
		throws PortalException, SystemException {

		UserThread userThread = userThreadPersistence.findByU_M(
			userId, mbThreadId);

		userThread.setRead(true);

		userThreadPersistence.update(userThread, false);
	}

	public void markUserThreadAsUnread(long userId, long mbThreadId)
		throws PortalException, SystemException {

		UserThread userThread = userThreadPersistence.findByU_M(
			userId, mbThreadId);

		userThread.setRead(false);

		userThreadPersistence.update(userThread, false);
	}

	protected MBMessage addPrivateMessage(
			long userId, long mbThreadId, long parentMBMessageId,
			long[] recipientUserIds, String subject, String body,
			List<ObjectValuePair<String, byte[]>> files)
		throws PortalException, SystemException {

		User user = UserLocalServiceUtil.getUser(userId);
		long groupId = 0;
		long categoryId =
			PrivateMessagingConstants.PRIVATE_MESSAGING_CATEGORY_ID;

		if (Validator.isNull(subject)) {
			subject = StringUtil.shorten(body, 50);
		}

		boolean anonymous = false;
		double priority = 0.0;
		boolean allowPingbacks = false;

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setWorkflowAction(WorkflowConstants.ACTION_SAVE_DRAFT);

		MBMessage mbMessage = MBMessageLocalServiceUtil.addMessage(
			userId, user.getScreenName(), groupId, categoryId, mbThreadId,
			parentMBMessageId, subject, body, files, anonymous, priority,
			allowPingbacks, serviceContext);

		if (mbThreadId == 0) {
			for (long recipientUserId : recipientUserIds) {
				if (recipientUserId != userId) {
					addUserThread(
						recipientUserId, mbMessage.getThreadId(),
						mbMessage.getMessageId(), false, false);
				}
			}

			addUserThread(
				userId, mbMessage.getThreadId(), mbMessage.getMessageId(),
				true, false);
		}
		else {
			List<UserThread> userThreads =
				userThreadPersistence.findByMBThreadId(mbMessage.getThreadId());

			for (UserThread userThread : userThreads) {
				userThread.setModifiedDate(new Date());

				if (userThread.getUserId() == userId) {
					userThread.setRead(true);
				}
				else {
					userThread.setRead(false);
				}

				if (userThread.isDeleted()) {
					userThread.setTopMBMessageId(mbMessage.getMessageId());
					userThread.setDeleted(false);
				}

				userThreadPersistence.update(userThread, false);
			}
		}

		return mbMessage;
	}

}