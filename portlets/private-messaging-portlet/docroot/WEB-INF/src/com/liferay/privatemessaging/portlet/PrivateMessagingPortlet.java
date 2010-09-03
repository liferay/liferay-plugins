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

package com.liferay.privatemessaging.portlet;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.privatemessaging.service.UserThreadLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Scott Lee
 */
public class PrivateMessagingPortlet extends MVCPortlet {

	public void deleteMessages(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortalException, SystemException {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long[] mbThreadIds = ParamUtil.getLongValues(
			actionRequest, "mbThreadIds");

		for (long mbThreadId : mbThreadIds) {
			UserThreadLocalServiceUtil.deleteUserThread(
				themeDisplay.getUserId(), mbThreadId);
		}
	}

	public void markMessagesAsRead(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortalException, SystemException {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long[] mbThreadIds = ParamUtil.getLongValues(
			actionRequest, "mbThreadIds");

		for (long mbThreadId : mbThreadIds) {
			UserThreadLocalServiceUtil.markUserThreadAsRead(
				themeDisplay.getUserId(), mbThreadId);
		}
	}

	public void markMessagesAsUnread(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortalException, SystemException {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long[] mbThreadIds = ParamUtil.getLongValues(
			actionRequest, "mbThreadIds");

		for (long mbThreadId : mbThreadIds) {
			UserThreadLocalServiceUtil.markUserThreadAsUnread(
				themeDisplay.getUserId(), mbThreadId);
		}
	}

	public void sendMessage(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortalException, SystemException {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long userId = ParamUtil.getLong(actionRequest, "userId");
		long mbThreadId = ParamUtil.getLong(actionRequest, "mbThreadId");
		String to = ParamUtil.getString(actionRequest, "to");
		String subject = ParamUtil.getString(actionRequest, "subject");
		String body = ParamUtil.getString(actionRequest, "body");
		List<ObjectValuePair<String, byte[]>> files =
			new ArrayList<ObjectValuePair<String, byte[]>>();

		UserThreadLocalServiceUtil.addPrivateMessage(
			userId, mbThreadId, to, subject, body, files, themeDisplay);
	}

}