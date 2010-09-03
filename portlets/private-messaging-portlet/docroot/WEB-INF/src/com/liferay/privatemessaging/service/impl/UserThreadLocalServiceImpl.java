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

import com.liferay.mail.service.MailServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.mail.MailMessage;
import com.liferay.portal.kernel.servlet.ImageServletTokenUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.model.MBMessageConstants;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBThreadLocalServiceUtil;
import com.liferay.privatemessaging.model.UserThread;
import com.liferay.privatemessaging.portlet.PrivateMessagingPortlet;
import com.liferay.privatemessaging.service.UserThreadLocalServiceUtil;
import com.liferay.privatemessaging.service.base.UserThreadLocalServiceBaseImpl;
import com.liferay.privatemessaging.util.PrivateMessagingConstants;

import java.text.Format;

import java.util.Date;
import java.util.List;

import javax.mail.internet.InternetAddress;

/**
 * @author Scott Lee
 */
public class UserThreadLocalServiceImpl extends UserThreadLocalServiceBaseImpl {

	public MBMessage addPrivateMessage(
			long userId, long mbThreadId, String to, String subject,
			String body, List<ObjectValuePair<String, byte[]>> files,
			ThemeDisplay themeDisplay)
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
			body, files, themeDisplay);
	}

	public MBMessage addPrivateMessageBranch(
			long userId, long parentMBMessageId, String body,
			List<ObjectValuePair<String, byte[]>> files,
			ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		long mbThreadId = 0;

		MBMessage parentMessage = MBMessageLocalServiceUtil.getMBMessage(
			parentMBMessageId);

		long[] recipientUserIds = {parentMessage.getUserId()};

		return addPrivateMessage(
			userId, mbThreadId, parentMBMessageId, recipientUserIds,
			parentMessage.getSubject(), body, files, themeDisplay);
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

	public List<UserThread> getMBThreadUserThreads(long mbThreadId)
		throws SystemException {

		return userThreadPersistence.findByMBThreadId(mbThreadId);
	}

	public UserThread getUserThread(long userId, long mbThreadId)
		throws PortalException, SystemException {

		return userThreadPersistence.findByU_M(userId, mbThreadId);
	}

	public int getUserUserThreadCount(long userId, boolean deleted)
		throws SystemException {

		return userThreadPersistence.countByU_D(userId, deleted);
	}

	public int getUserUserThreadCount(
			long userId, boolean read, boolean deleted)
		throws SystemException {

		return userThreadPersistence.countByU_R_D(userId, read, deleted);
	}

	public List<UserThread> getUserUserThreads(long userId, boolean deleted)
		throws SystemException {

		return userThreadPersistence.findByU_D(userId, deleted);
	}

	public List<UserThread> getUserUserThreads(
			long userId, boolean read, boolean deleted)
		throws SystemException {

		return userThreadPersistence.findByU_R_D(userId, read, deleted);
	}

	public List<UserThread> getUserUserThreads(
			long userId, boolean deleted, int start, int end)
		throws SystemException {

		return userThreadPersistence.findByU_D(userId, deleted, start, end);
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
			List<ObjectValuePair<String, byte[]>> files,
			ThemeDisplay themeDisplay)
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

		// Email

		try {
			sendEmail(mbMessage.getMessageId(), themeDisplay);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}

		return mbMessage;
	}

	protected void sendEmail(long mbMessageId, ThemeDisplay themeDisplay)
		throws Exception {

		MBMessage mbMessage = MBMessageLocalServiceUtil.getMBMessage(
			mbMessageId);

		String layoutFullURL = PortalUtil.getLayoutFullURL(themeDisplay);

		if (Validator.isNull(layoutFullURL)) {
			return;
		}

		User sender = UserLocalServiceUtil.getUser(mbMessage.getUserId());

		Company company = CompanyLocalServiceUtil.getCompany(
			sender.getCompanyId());

		InternetAddress notificationFrom = new InternetAddress(
			company.getEmailAddress());
		String notificationSubject = StringUtil.read(
			PrivateMessagingPortlet.class.getResourceAsStream(
				"dependencies/notification_message_subject.tmpl"));
		String notificationBody = StringUtil.read(
			PrivateMessagingPortlet.class.getResourceAsStream(
				"dependencies/notification_message_body.tmpl"));

		notificationSubject = StringUtil.replace(
			notificationSubject,
			new String[] {
				"[$COMPANY_NAME$]",
				"[$FROM_NAME$]"
			},
			new String[] {
				company.getName(),
				sender.getFullName()
			});

		String threadURL =
			layoutFullURL + "/-/private_messaging/thread/" +
				mbMessage.getThreadId();

		long portraitId = sender.getPortraitId();
		String tokenId = ImageServletTokenUtil.getToken(sender.getPortraitId());
		String portraitURL =
			themeDisplay.getPortalURL() + themeDisplay.getPathImage() + "/user_" +
				(sender.isFemale() ? "female" : "male") + "_portrait?img_id=" +
					portraitId + "&t=" + tokenId;

		notificationBody = StringUtil.replace(
			notificationBody,
			new String[] {
				"[$COMPANY_NAME$]",
				"[$SUBJECT$]",
				"[$BODY$]",
				"[$FROM_NAME$]",
				"[$FROM_PROFILE_URL$]",
				"[$FROM_AVATAR$]",
				"[$THREAD_URL$]"
			},
			new String[] {
				company.getName(),
				mbMessage.getSubject(),
				mbMessage.getBody(),
				sender.getFullName(),
				sender.getDisplayURL(themeDisplay),
				portraitURL,
				threadURL
			});

		boolean htmlFormat = true;

		List<UserThread> userThreads =
			UserThreadLocalServiceUtil.getMBThreadUserThreads(
				mbMessage.getThreadId());

		for (UserThread userThread : userThreads) {
			if (userThread.getUserId() == mbMessage.getUserId()) {
				continue;
			}

			User recipient = UserLocalServiceUtil.getUser(
				userThread.getUserId());

			Format dateFormatDateTime =
				FastDateFormatFactoryUtil.getSimpleDateFormat(
					"MMMMM d 'at' h:mm a", recipient.getLocale(),
					recipient.getTimeZone());

			notificationBody = StringUtil.replace(
				notificationBody, "[$SENT_DATE$]",
				dateFormatDateTime.format(mbMessage.getCreateDate()));

			InternetAddress notificationTo = new InternetAddress(
				recipient.getEmailAddress());

			MailMessage notificationMessage = new MailMessage(
				notificationFrom, notificationTo, notificationSubject,
				notificationBody, htmlFormat);

			MailServiceUtil.sendEmail(notificationMessage);
		}
	}

}