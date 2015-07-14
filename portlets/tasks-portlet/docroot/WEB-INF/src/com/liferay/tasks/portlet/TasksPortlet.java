/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.tasks.portlet;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Layout;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletURLFactoryUtil;
import com.liferay.portlet.asset.AssetTagException;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.tasks.model.TasksEntry;
import com.liferay.tasks.service.TasksEntryLocalServiceUtil;
import com.liferay.tasks.service.TasksEntryServiceUtil;
import com.liferay.tasks.util.PortletKeys;

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

			jsonObject.put("success", Boolean.TRUE);

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
			SessionMessages.add(
				actionRequest,
				PortalUtil.getPortletId(actionRequest) +
					SessionMessages.KEY_SUFFIX_REFRESH_PORTLET,
				PortletKeys.TASKS);
		}

		String redirect = ParamUtil.getString(actionRequest, "redirect");

		if (Validator.isNotNull(redirect)) {
			actionResponse.sendRedirect(redirect);
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

		boolean addDueDate = ParamUtil.getBoolean(actionRequest, "addDueDate");
		int status = ParamUtil.getInteger(actionRequest, "status");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			TasksEntry.class.getName(), actionRequest);

		TasksEntry taskEntry = null;

		try {
			if (tasksEntryId <= 0) {
				taskEntry = TasksEntryServiceUtil.addTasksEntry(
					title, priority, assigneeUserId, dueDateMonth, dueDateDay,
					dueDateYear, dueDateHour, dueDateMinute, addDueDate,
					serviceContext);
			}
			else {
				taskEntry = TasksEntryServiceUtil.updateTasksEntry(
					tasksEntryId, title, priority, assigneeUserId,
					resolverUserId, dueDateMonth, dueDateDay, dueDateYear,
					dueDateHour, dueDateMinute, addDueDate, status,
					serviceContext);
			}

			Layout layout = themeDisplay.getLayout();

			PortletURL portletURL = PortletURLFactoryUtil.create(
				actionRequest, PortletKeys.TASKS, layout.getPlid(),
				PortletRequest.RENDER_PHASE);

			portletURL.setParameter("mvcPath", "/tasks/view_task.jsp");
			portletURL.setParameter(
				"tasksEntryId", String.valueOf(taskEntry.getTasksEntryId()));
			portletURL.setWindowState(LiferayWindowState.POP_UP);

			actionResponse.sendRedirect(portletURL.toString());
		}
		catch (AssetTagException ate) {
			actionResponse.setRenderParameter(
				"mvcPath", "/tasks/edit_task.jsp");

			actionResponse.setRenderParameters(actionRequest.getParameterMap());

			SessionErrors.add(actionRequest, ate.getClass(), ate);
		}
	}

	public void updateTasksEntryStatus(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long tasksEntryId = ParamUtil.getLong(actionRequest, "tasksEntryId");

		long resolverUserId = ParamUtil.getLong(
			actionRequest, "resolverUserId");
		int status = ParamUtil.getInteger(actionRequest, "status");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			TasksEntry.class.getName(), actionRequest);

		TasksEntryLocalServiceUtil.updateTasksEntryStatus(
			tasksEntryId, resolverUserId, status, serviceContext);

		Layout layout = themeDisplay.getLayout();

		PortletURL portletURL = PortletURLFactoryUtil.create(
			actionRequest, PortletKeys.TASKS, layout.getPlid(),
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter("mvcPath", "/tasks/view_task.jsp");
		portletURL.setParameter("tasksEntryId", String.valueOf(tasksEntryId));
		portletURL.setWindowState(LiferayWindowState.POP_UP);

		actionResponse.sendRedirect(portletURL.toString());
	}

	public void updateTasksEntryViewCount(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long tasksEntryId = ParamUtil.getLong(actionRequest, "tasksEntryId");

		TasksEntry tasksEntry = TasksEntryLocalServiceUtil.fetchTasksEntry(
			tasksEntryId);

		if (tasksEntry == null) {
			return;
		}

		AssetEntryLocalServiceUtil.incrementViewCounter(
			0, TasksEntry.class.getName(), tasksEntryId);
	}

}