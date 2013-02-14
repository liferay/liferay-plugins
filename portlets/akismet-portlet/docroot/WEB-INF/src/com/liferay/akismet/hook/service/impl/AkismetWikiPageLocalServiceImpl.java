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

import com.liferay.akismet.model.AkismetData;
import com.liferay.akismet.service.AkismetDataLocalServiceUtil;
import com.liferay.akismet.util.AkismetConstants;
import com.liferay.akismet.util.AkismetUtil;
import com.liferay.akismet.util.PortletPropsKeys;
import com.liferay.akismet.util.PrefsPortletPropsUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.service.WikiPageLocalService;
import com.liferay.portlet.wiki.service.WikiPageLocalServiceWrapper;

import java.util.Map;

/**
 * @author Peter Shin
 */
public class AkismetWikiPageLocalServiceImpl
	extends WikiPageLocalServiceWrapper {

	public AkismetWikiPageLocalServiceImpl(
		WikiPageLocalService wikiPageLocalService) {

		super(wikiPageLocalService);
	}

	@Override
	public WikiPage addPage(
			long userId, long nodeId, String title, double version,
			String content, String summary, boolean minorEdit, String format,
			boolean head, String parentTitle, String redirectTitle,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		boolean enabled = isWikiEnabled(userId, nodeId, serviceContext);

		if (enabled) {
			serviceContext.setWorkflowAction(
				WorkflowConstants.ACTION_SAVE_DRAFT);
		}

		WikiPage page = super.addPage(
			userId, nodeId, title, version, content, summary, minorEdit, format,
			head, parentTitle, redirectTitle, serviceContext);

		AkismetData akismetData = updateAkismetData(page, serviceContext);

		if (!enabled) {
			return page;
		}

		String akismetContent = page.getTitle() + "\n\n" + page.getContent();

		int status = WorkflowConstants.STATUS_APPROVED;

		if (AkismetUtil.isSpam(userId, akismetContent, akismetData)) {
			status = WorkflowConstants.STATUS_DENIED;
		}

		return super.updateStatus(
			userId, page.getResourcePrimKey(), status, serviceContext);
	}

	@Override
	public WikiPage updatePage(
			long userId, long nodeId, String title, double version,
			String content, String summary, boolean minorEdit, String format,
			String parentTitle, String redirectTitle,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		boolean enabled = isWikiEnabled(userId, nodeId, serviceContext);

		if (enabled) {
			serviceContext.setWorkflowAction(
				WorkflowConstants.ACTION_SAVE_DRAFT);
		}

		WikiPage page = super.updatePage(
			userId, nodeId, title, version, content, summary, minorEdit, format,
			parentTitle, redirectTitle, serviceContext);

		AkismetData akismetData = updateAkismetData(page, serviceContext);

		if (!enabled) {
			return page;
		}

		String akismetContent = page.getTitle() + "\n\n" + page.getContent();

		int status = WorkflowConstants.STATUS_APPROVED;

		if (AkismetUtil.isSpam(userId, akismetContent, akismetData)) {
			status = WorkflowConstants.STATUS_DENIED;
		}

		return super.updateStatus(
			userId, page.getResourcePrimKey(), status, serviceContext);
	}

	protected String getPermalink(
		WikiPage page, ServiceContext serviceContext) {

		StringBundler sb = new StringBundler(4);

		sb.append(serviceContext.getPortalURL());
		sb.append(serviceContext.getPathMain());
		sb.append("/wiki/find_page?pageResourcePrimKey=");
		sb.append(page.getResourcePrimKey());

		return sb.toString();
	}

	protected boolean isWikiEnabled(
			long userId, long nodeId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		if (!AkismetUtil.hasRequiredInfo(serviceContext)) {
			return false;
		}

		User user = UserLocalServiceUtil.getUser(userId);

		if (!AkismetUtil.isWikiEnabled(user.getCompanyId())) {
			return false;
		}

		int checkThreshold = PrefsPortletPropsUtil.getInteger(
			user.getCompanyId(), PortletPropsKeys.AKISMET_CHECK_THRESHOLD);

		if (checkThreshold > 0) {
			int count = super.getPagesCount(
				userId, nodeId, WorkflowConstants.STATUS_APPROVED);

			if (count > checkThreshold) {
				return false;
			}
		}

		return true;
	}

	protected AkismetData updateAkismetData(
			WikiPage page, ServiceContext serviceContext)
		throws SystemException {

		if (!AkismetUtil.hasRequiredInfo(serviceContext)) {
			return null;
		}

		String permalink = getPermalink(page, serviceContext);

		Map<String, String> headers = serviceContext.getHeaders();

		String referrer = headers.get("referer");
		String userAgent = headers.get(HttpHeaders.USER_AGENT.toLowerCase());

		String userIP = serviceContext.getRemoteAddr();

		return AkismetDataLocalServiceUtil.updateAkismetData(
			WikiPage.class.getName(), page.getPageId(),
			AkismetConstants.TYPE_WIKI, permalink, referrer, userAgent, userIP,
			StringPool.BLANK);
	}

}