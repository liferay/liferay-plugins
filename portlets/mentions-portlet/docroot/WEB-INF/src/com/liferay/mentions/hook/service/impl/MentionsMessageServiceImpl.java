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

package com.liferay.mentions.hook.service.impl;

import com.liferay.mentions.util.MentionsNotifier;
import com.liferay.mentions.util.MentionsUtil;
import com.liferay.mentions.util.PortletPropsValues;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.parsers.bbcode.BBCodeTranslatorUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.service.MBMessageLocalService;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceWrapper;
import com.liferay.util.ContentUtil;

import java.io.Serializable;

import java.util.Map;

/**
 * @author Sergio González
 * @author Iván Zaera
 */
public class MentionsMessageServiceImpl extends MBMessageLocalServiceWrapper {

	public MentionsMessageServiceImpl(
		MBMessageLocalService mbMessageLocalService) {

		super(mbMessageLocalService);
	}

	@Override
	public MBMessage updateStatus(
			long userId, long messageId, int status,
			ServiceContext serviceContext,
			Map<String, Serializable> workflowContext)
		throws PortalException {

		MBMessage message = MBMessageLocalServiceUtil.getMessage(messageId);

		int oldStatus = message.getStatus();

		message = super.updateStatus(
			userId, messageId, status, serviceContext, workflowContext);

		if ((status != WorkflowConstants.STATUS_APPROVED) ||
			(oldStatus == WorkflowConstants.STATUS_APPROVED) ||
			(oldStatus == WorkflowConstants.STATUS_IN_TRASH)) {

			return message;
		}

		long siteGroupId = PortalUtil.getSiteGroupId(message.getGroupId());

		if (!MentionsUtil.isMentionsEnabled(siteGroupId)) {
			return message;
		}

		MentionsNotifier mentionsNotifier = new MentionsNotifier();

		String content = message.getBody();

		if (message.isFormatBBCode()) {
			content = BBCodeTranslatorUtil.getHTML(content);
		}

		String subject = ContentUtil.get(
			PortletPropsValues.COMMENT_MENTION_EMAIL_SUBJECT);
		String body = ContentUtil.get(
			PortletPropsValues.COMMENT_MENTION_EMAIL_BODY);

		if (!message.isDiscussion()) {
			subject = ContentUtil.get(
				PortletPropsValues.ASSET_ENTRY_MENTION_EMAIL_SUBJECT);
			body = ContentUtil.get(
				PortletPropsValues.ASSET_ENTRY_MENTION_EMAIL_BODY);
		}

		String contentURL = (String)serviceContext.getAttribute("contentURL");

		if (Validator.isNull(contentURL)) {
			serviceContext.setAttribute(
				"contentURL", workflowContext.get("url"));
		}

		mentionsNotifier.notify(
			message.getUserId(), message.getGroupId(), message.getSubject(),
			content, message.getModelClassName(), message.getMessageId(),
			subject, body, serviceContext);

		return message;
	}

}