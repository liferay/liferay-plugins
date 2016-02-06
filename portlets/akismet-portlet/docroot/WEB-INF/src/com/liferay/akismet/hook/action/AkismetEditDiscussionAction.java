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

package com.liferay.akismet.hook.action;

import com.liferay.akismet.util.AkismetUtil;
import com.liferay.message.boards.kernel.model.MBMessage;
import com.liferay.message.boards.kernel.service.MBMessageLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.struts.BaseStrutsAction;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Amos Fong
 */
public class AkismetEditDiscussionAction extends BaseStrutsAction {

	@Override
	public String execute(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		try {
			checkPermission(request);

			updateStatus(request, response);

			String redirect = PortalUtil.escapeRedirect(
				ParamUtil.getString(request, "redirect"));

			response.sendRedirect(redirect);
		}
		catch (Exception e) {
			PortalUtil.sendError(e, request, response);
		}

		return null;
	}

	protected void checkPermission(HttpServletRequest request)
		throws PortalException {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		if (permissionChecker.isCompanyAdmin()) {
			return;
		}

		if (permissionChecker.isGroupAdmin(themeDisplay.getScopeGroupId())) {
			return;
		}

		throw new PrincipalException();
	}

	protected void updateStatus(
			HttpServletRequest request, HttpServletResponse response)
		throws PortalException {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		long messageId = ParamUtil.getLong(request, "messageId");

		boolean spam = ParamUtil.getBoolean(request, "spam");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			request);

		if (spam) {
			MBMessage message = MBMessageLocalServiceUtil.updateStatus(
				themeDisplay.getUserId(), messageId,
				WorkflowConstants.STATUS_DENIED, serviceContext);

			if (AkismetUtil.isMessageBoardsEnabled(message.getCompanyId())) {
				AkismetUtil.submitSpam(message);
			}
		}
		else {
			MBMessage message = MBMessageLocalServiceUtil.updateStatus(
				themeDisplay.getUserId(), messageId,
				WorkflowConstants.STATUS_APPROVED, serviceContext);

			if (AkismetUtil.isMessageBoardsEnabled(message.getCompanyId())) {
				AkismetUtil.submitHam(message);
			}
		}
	}

}