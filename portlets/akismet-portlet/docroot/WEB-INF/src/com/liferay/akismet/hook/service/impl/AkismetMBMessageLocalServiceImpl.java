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

package com.liferay.akismet.hook.service.impl;

import com.liferay.akismet.util.AkismetConstants;
import com.liferay.akismet.util.AkismetUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.messageboards.MessageBodyException;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.service.MBMessageLocalService;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceWrapper;

import java.io.InputStream;

import java.util.List;
import java.util.Map;

/**
 * @author Amos Fong
 */
public class AkismetMBMessageLocalServiceImpl
	extends MBMessageLocalServiceWrapper {

	public AkismetMBMessageLocalServiceImpl(
		MBMessageLocalService mbMessageLocalService) {

		super(mbMessageLocalService);
	}

	@Override
	public MBMessage addDiscussionMessage(
			long userId, String userName, long groupId, String className,
			long classPK, long threadId, long parentMessageId, String subject,
			String body, ServiceContext serviceContext)
		throws PortalException, SystemException {

		if (isSpam(userId, body, serviceContext)) {
			throw new MessageBodyException();
		}

		return super.addDiscussionMessage(
			userId, userName, groupId, className, classPK, threadId,
			parentMessageId, subject, body, serviceContext);
	}

	@Override
	public MBMessage addMessage(
			long userId, String userName, long groupId, long categoryId,
			long threadId, long parentMessageId, String subject, String body,
			String format,
			List<ObjectValuePair<String, InputStream>> inputStreamOVPs,
			boolean anonymous, double priority, boolean allowPingbacks,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		if (isSpam(userId, body, serviceContext)) {
			throw new MessageBodyException();
		}

		return super.addMessage(
			userId, userName, groupId, categoryId, threadId, parentMessageId,
			subject, body, format, inputStreamOVPs, anonymous, priority,
			allowPingbacks, serviceContext);
	}

	@Override
	public MBMessage addMessage(
			long userId, String userName, long groupId, long categoryId,
			String subject, String body, String format,
			List<ObjectValuePair<String, InputStream>> inputStreamOVPs,
			boolean anonymous, double priority, boolean allowPingbacks,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		if (isSpam(userId, body, serviceContext)) {
			throw new MessageBodyException();
		}

		return super.addMessage(
			userId, userName, groupId, categoryId, subject, body, format,
			inputStreamOVPs, anonymous, priority, allowPingbacks,
			serviceContext);
	}

	@Override
	public MBMessage updateDiscussionMessage(
			long userId, long messageId, String className, long classPK,
			String subject, String body, ServiceContext serviceContext)
		throws PortalException, SystemException {

		if (isSpam(userId, body, serviceContext)) {
			throw new MessageBodyException();
		}

		return super.updateDiscussionMessage(
			userId, messageId, className, classPK, subject, body,
			serviceContext);
	}

	@Override
	public MBMessage updateMessage(
			long userId, long messageId, String subject, String body,
			List<ObjectValuePair<String, InputStream>> inputStreamOVPs,
			List<String> existingFiles, double priority, boolean allowPingbacks,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		if (isSpam(userId, body, serviceContext)) {
			throw new MessageBodyException();
		}

		return super.updateMessage(
			userId, messageId, subject, body, inputStreamOVPs, existingFiles,
			priority, allowPingbacks, serviceContext);
	}

	protected boolean isSpam(
			long userId, String body, ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = UserLocalServiceUtil.getUser(userId);

		if (!AkismetUtil.isEnabled(user.getCompanyId())) {
			return false;
		}

		String ipAddress = serviceContext.getRemoteAddr();

		Map<String, String> headers = serviceContext.getHeaders();

		String userAgent = headers.get(HttpHeaders.USER_AGENT.toLowerCase());
		String referrer = headers.get("referer");

		if (AkismetUtil.isSpam(
				user.getCompanyId(), ipAddress, userAgent, referrer,
				StringPool.BLANK, AkismetConstants.TYPE_COMMENT,
				user.getFullName(), user.getEmailAddress(), body)) {

			return true;
		}
		else {
			return false;
		}
	}

}