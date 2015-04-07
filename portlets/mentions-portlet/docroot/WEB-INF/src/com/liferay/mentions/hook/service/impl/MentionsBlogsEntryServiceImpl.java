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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.blogs.service.BlogsEntryLocalService;
import com.liferay.portlet.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.portlet.blogs.service.BlogsEntryLocalServiceWrapper;
import com.liferay.util.ContentUtil;

import java.io.Serializable;

import java.util.Map;

/**
 * @author Sergio González
 */
public class MentionsBlogsEntryServiceImpl
	extends BlogsEntryLocalServiceWrapper {

	public MentionsBlogsEntryServiceImpl(
		BlogsEntryLocalService blogsEntryLocalService) {

		super(blogsEntryLocalService);
	}

	@Override
	public BlogsEntry updateStatus(
			long userId, long entryId, int status,
			ServiceContext serviceContext,
			Map<String, Serializable> workflowContext)
		throws PortalException {

		BlogsEntry entry = BlogsEntryLocalServiceUtil.getEntry(entryId);

		int oldStatus = entry.getStatus();

		entry = super.updateStatus(
			userId, entryId, status, serviceContext, workflowContext);

		if ((status != WorkflowConstants.STATUS_APPROVED) ||
			(oldStatus == WorkflowConstants.STATUS_APPROVED) ||
			(oldStatus == WorkflowConstants.STATUS_IN_TRASH)) {

			return entry;
		}

		long siteGroupId = PortalUtil.getSiteGroupId(entry.getGroupId());

		if (!MentionsUtil.isMentionsEnabled(siteGroupId)) {
			return entry;
		}

		MentionsNotifier mentionsNotifier = new MentionsNotifier();

		String contentURL = (String)serviceContext.getAttribute("contentURL");

		if (Validator.isNull(contentURL)) {
			serviceContext.setAttribute(
				"contentURL", workflowContext.get("url"));
		}

		mentionsNotifier.notify(
			entry.getUserId(), entry.getGroupId(), entry.getTitle(),
			entry.getContent(), BlogsEntry.class.getName(), entry.getEntryId(),
			ContentUtil.get(
				PortletPropsValues.ASSET_ENTRY_MENTION_EMAIL_SUBJECT),
			ContentUtil.get(PortletPropsValues.ASSET_ENTRY_MENTION_EMAIL_BODY),
			serviceContext);

		return entry;
	}

}