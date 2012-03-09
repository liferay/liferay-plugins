/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.so.hook.action;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.struts.BaseStrutsPortletAction;
import com.liferay.portal.kernel.struts.StrutsPortletAction;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.so.model.ProjectsEntry;
import com.liferay.so.service.ProjectsEntryLocalServiceUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Ryan Park
 * @author Jonathan Lee
 */
public class EditUserAction extends BaseStrutsPortletAction {

	@Override
	public void processAction(
			StrutsPortletAction originalStrutsPortletAction,
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		if (cmd.equals("updateFieldGroup")) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			try {
				updateProjectsEntries(actionRequest, actionResponse);

				String redirect = ParamUtil.getString(
					actionRequest, "redirect");

				jsonObject.put("redirect", redirect);
				jsonObject.put("success", true);
			}
			catch (Exception e) {
				ThemeDisplay themeDisplay =
					(ThemeDisplay)actionRequest.getAttribute(
						WebKeys.THEME_DISPLAY);

				String message = LanguageUtil.get(
					themeDisplay.getLocale(),
					"your-request-failed-to-complete");

				jsonObject.put("message", message);
				jsonObject.put("success", false);
			}

			HttpServletResponse response = PortalUtil.getHttpServletResponse(
				actionResponse);

			response.setContentType(ContentTypes.TEXT_JAVASCRIPT);

			ServletResponseUtil.write(response, jsonObject.toString());
		}
		else {
			updateProjectsEntries(actionRequest, actionResponse);

			originalStrutsPortletAction.processAction(
					portletConfig, actionRequest, actionResponse);
		}
	}

	@Override
	public String render(
			StrutsPortletAction originalStrutsPortletAction,
			PortletConfig portletConfig, RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws Exception {

		return originalStrutsPortletAction.render(
			portletConfig, renderRequest, renderResponse);
	}

	@Override
	public void serveResource(
			StrutsPortletAction originalStrutsPortletAction,
			PortletConfig portletConfig, ResourceRequest resourceRequest,
			ResourceResponse resourceResponse)
		throws Exception {

		originalStrutsPortletAction.serveResource(
			portletConfig, resourceRequest, resourceResponse);
	}

	protected void updateProjectsEntries(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		User user = PortalUtil.getSelectedUser(actionRequest);

		String projectsEntriesIndexesString = ParamUtil.getString(
			actionRequest, "projectsEntriesIndexes");

		if (Validator.isNull(projectsEntriesIndexesString)) {
			return;
		}

		Set<Long> projectsEntryIds = new HashSet<Long>();

		int[] projectsEntriesIndexes = StringUtil.split(
			projectsEntriesIndexesString, 0);

		for (int projectsEntriesIndex : projectsEntriesIndexes) {
			long projectsEntryId = ParamUtil.getLong(
				actionRequest, "projectsEntryId" + projectsEntriesIndex);
			String title = ParamUtil.getString(
				actionRequest, "projectsEntryTitle" + projectsEntriesIndex);
			String description = ParamUtil.getString(
				actionRequest,
				"projectsEntryDescription" + projectsEntriesIndex);

			if (Validator.isNull(title)) {
				continue;
			}

			int startDateMonth = ParamUtil.getInteger(
				actionRequest,
				"projectsEntryStartDateMonth" + projectsEntriesIndex);
			int startDateDay = 1;
			int startDateYear = ParamUtil.getInteger(
				actionRequest,
				"projectsEntryStartDateYear" + projectsEntriesIndex);
			int endDateMonth = ParamUtil.getInteger(
				actionRequest,
				"projectsEntryEndDateMonth" + projectsEntriesIndex);
			int endDateDay = 1;
			int endDateYear = ParamUtil.getInteger(
				actionRequest,
				"projectsEntryEndDateYear" + projectsEntriesIndex);

			boolean current = ParamUtil.getBoolean(
				actionRequest, "projectsEntryCurrent" + projectsEntriesIndex);
			String otherMembers = ParamUtil.getString(
				actionRequest,
				"projectsEntryOtherMembers" + projectsEntriesIndex);

			if (projectsEntryId <= 0) {
				ProjectsEntry projectsEntry =
					ProjectsEntryLocalServiceUtil.addProjectsEntry(
						user.getUserId(), title, description, startDateMonth,
						startDateDay, startDateYear, endDateMonth, endDateDay,
						endDateYear, current, otherMembers);

				projectsEntryId = projectsEntry.getProjectsEntryId();
			}
			else {
				ProjectsEntryLocalServiceUtil.updateProjectsEntry(
					projectsEntryId, title, description, startDateMonth,
					startDateDay, startDateYear, endDateMonth, endDateDay,
					endDateYear, current, otherMembers);
			}

			projectsEntryIds.add(projectsEntryId);
		}

		List<ProjectsEntry> projectsEntries =
			ProjectsEntryLocalServiceUtil.getUserProjectsEntries(
				user.getUserId());

		for (ProjectsEntry projectsEntry : projectsEntries) {
			if (!projectsEntryIds.contains(
				projectsEntry.getProjectsEntryId())) {

				ProjectsEntryLocalServiceUtil.deleteProjectsEntry(
					projectsEntry.getProjectsEntryId());
			}
		}
	}

}