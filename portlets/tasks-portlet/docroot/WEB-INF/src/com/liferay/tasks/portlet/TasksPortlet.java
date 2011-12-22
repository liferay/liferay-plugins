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

package com.liferay.tasks.portlet;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletURLFactoryUtil;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.service.MBMessageServiceUtil;
import com.liferay.tasks.model.TasksEntry;
import com.liferay.tasks.service.TasksEntryLocalServiceUtil;
import com.liferay.tasks.service.TasksEntryServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.IOException;

import java.util.Calendar;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Ryan Park
 */
public class TasksPortlet extends MVCPortlet {

	public void deleteTasksEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long tasksEntryId = ParamUtil.getLong(actionRequest, "tasksEntryId");

		TasksEntryLocalServiceUtil.deleteTasksEntry(tasksEntryId);

		String redirect = ParamUtil.getString(actionRequest, "redirect");

		if (Validator.isNotNull(redirect)) {
			actionResponse.sendRedirect(redirect);
		}
		else {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("success", true);

			HttpServletResponse response = PortalUtil.getHttpServletResponse(
				actionResponse);

			ServletResponseUtil.write(response, jsonObject.toString());
		}
	}

	@Override
	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		if (!isProcessActionRequest(actionRequest)) {
			return;
		}

		if (!callActionMethod(actionRequest, actionResponse)) {
			return;
		}

		if (SessionErrors.isEmpty(actionRequest)) {
			SessionMessages.add(actionRequest, "request_processed");
		}

		String actionName = ParamUtil.getString(
			actionRequest, ActionRequest.ACTION_NAME);

		if (actionName.equals("updateTasksEntry")) {
			return;
		}

		String redirect = ParamUtil.getString(actionRequest, "redirect");

		if (Validator.isNotNull(redirect)) {
			actionResponse.sendRedirect(redirect);
		}
	}

	public void updateMessage(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		long groupId = PortalUtil.getScopeGroupId(actionRequest);
		String className = ParamUtil.getString(actionRequest, "className");
		long classPK = ParamUtil.getLong(actionRequest, "classPK");
		long messageId = ParamUtil.getLong(actionRequest, "messageId");
		long threadId = ParamUtil.getLong(actionRequest, "threadId");
		long parentMessageId = ParamUtil.getLong(
			actionRequest, "parentMessageId");
		String subject = ParamUtil.getString(actionRequest, "subject");
		String body = ParamUtil.getString(actionRequest, "body");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			MBMessage.class.getName(), actionRequest);

		if (cmd.equals(Constants.DELETE)) {
			MBMessageServiceUtil.deleteDiscussionMessage(
				groupId, className, classPK, className, classPK,
				themeDisplay.getUserId(), messageId);
		}
		else if (messageId <= 0) {
			MBMessageServiceUtil.addDiscussionMessage(
				groupId, className, classPK, className, classPK,
				themeDisplay.getUserId(), threadId, parentMessageId, subject,
				body, serviceContext);
		}
		else {
			MBMessageServiceUtil.updateDiscussionMessage(
				className, classPK, className, classPK,
				themeDisplay.getUserId(), messageId, subject, body,
				serviceContext);
		}
	}

	public void updateTasksEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long tasksEntryId = ParamUtil.getLong(actionRequest, "tasksEntryId");

		String title = ParamUtil.getString(actionRequest, "title");
		int priority = ParamUtil.getInteger(actionRequest, "priority");
		long assigneeUserId = ParamUtil.getLong(
			actionRequest, "assigneeUserId");
		long resolverUserId = ParamUtil.getLong(
			actionRequest, "resolverUserId");

		int dueDateMonth = ParamUtil.getInteger(actionRequest, "dueDateMonth");
		int dueDateDay = ParamUtil.getInteger(actionRequest, "dueDateDay");
		int dueDateYear = ParamUtil.getInteger(actionRequest, "dueDateYear");
		int dueDateHour = ParamUtil.getInteger(actionRequest, "dueDateHour");
		int dueDateMinute = ParamUtil.getInteger(
			actionRequest, "dueDateMinute");
		int dueDateAmPm = ParamUtil.getInteger(actionRequest, "dueDateAmPm");

		if (dueDateAmPm == Calendar.PM) {
			dueDateHour += 12;
		}

		boolean neverDue = ParamUtil.getBoolean(actionRequest, "neverDue");
		int status = ParamUtil.getInteger(actionRequest, "status");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			TasksEntry.class.getName(), actionRequest);

		TasksEntry taskEntry = null;

		if (tasksEntryId <= 0) {
			taskEntry = TasksEntryServiceUtil.addTasksEntry(
				title, priority, assigneeUserId, dueDateMonth, dueDateDay,
				dueDateYear, dueDateHour, dueDateMinute, neverDue,
				serviceContext);
		}
		else {
			taskEntry = TasksEntryServiceUtil.updateTasksEntry(
				tasksEntryId, title, priority, assigneeUserId, resolverUserId,
				dueDateMonth, dueDateDay, dueDateYear, dueDateHour,
				dueDateMinute, neverDue, status, serviceContext);
		}

		PortletURL portletURL = PortletURLFactoryUtil.create(
			PortalUtil.getHttpServletRequest(actionRequest),
			"1_WAR_tasksportlet", themeDisplay.getLayout().getPlid(),
			PortletRequest.RENDER_PHASE);

		portletURL.setWindowState(LiferayWindowState.EXCLUSIVE);

		portletURL.setParameter("jspPage", "/view_task.jsp");
		portletURL.setParameter(
			"tasksEntryId", String.valueOf(taskEntry.getTasksEntryId()));

		actionResponse.sendRedirect(portletURL.toString());
	}

	public void updateTasksEntryStatus(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long tasksEntryId = ParamUtil.getLong(actionRequest, "tasksEntryId");

		long resolverUserId = ParamUtil.getLong(actionRequest,
			"resolverUserId");
		int status = ParamUtil.getInteger(actionRequest, "status");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			TasksEntry.class.getName(), actionRequest);

		TasksEntryLocalServiceUtil.updateTasksEntryStatus(
			tasksEntryId, resolverUserId, status, serviceContext);
	}

}