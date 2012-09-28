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

package com.liferay.akismet.moderation.portlet;

import com.liferay.akismet.util.AkismetUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Amos Fong
 */
public class ModerationPortlet extends MVCPortlet {

	public void deleteDiscussionMBMessages(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		checkPermission(themeDisplay.getScopeGroupId());

		long[] mbMessageIds = ParamUtil.getLongValues(
			actionRequest, "deleteMBMessageIds");

		for (long mbMessageId : mbMessageIds) {
			MBMessageLocalServiceUtil.deleteDiscussionMessage(mbMessageId);
		}
	}

	public void deleteMBMessages(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		checkPermission(themeDisplay.getScopeGroupId());

		long[] mbMessageIds = ParamUtil.getLongValues(
			actionRequest, "deleteMBMessageIds");

		for (long mbMessageId : mbMessageIds) {
			MBMessageLocalServiceUtil.deleteMessage(mbMessageId);
		}
	}

	public void markNotSpam(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		checkPermission(themeDisplay.getScopeGroupId());

		long[] mbMessageIds = ParamUtil.getLongValues(
			actionRequest, "notSpamMBMessageIds");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			actionRequest);

		for (long mbMessageId : mbMessageIds) {
			MBMessageLocalServiceUtil.updateStatus(
				themeDisplay.getUserId(), mbMessageId,
				WorkflowConstants.STATUS_APPROVED, serviceContext);

			AkismetUtil.submitHam(mbMessageId);
		}
	}

	protected void checkPermission(long scopeGroupId) throws PortalException {
		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if (!permissionChecker.hasPermission(
				scopeGroupId, "com.liferay.portlet.messageboards", scopeGroupId,
				ActionKeys.BAN_USER)) {

			throw new PrincipalException();
		}
	}

}