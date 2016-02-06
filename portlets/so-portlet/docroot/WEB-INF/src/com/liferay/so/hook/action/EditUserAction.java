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

package com.liferay.so.hook.action;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.LayoutSetPrototype;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.DynamicActionRequest;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.struts.BaseStrutsPortletAction;
import com.liferay.portal.kernel.struts.StrutsPortletAction;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.so.model.ProjectsEntry;
import com.liferay.so.service.ProjectsEntryLocalServiceUtil;
import com.liferay.so.util.LayoutSetPrototypeUtil;
import com.liferay.so.util.RoleConstants;
import com.liferay.so.util.SocialOfficeConstants;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletRequest;
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

		String actionName = ParamUtil.getString(
			actionRequest, ActionRequest.ACTION_NAME);

		if (actionName.equals("updateFieldGroup")) {
			updateFieldGroup(actionRequest, actionResponse);
		}
		else {
			updateUser(
				originalStrutsPortletAction, portletConfig, actionRequest,
				actionResponse);
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

	protected long[] getLongArray(PortletRequest portletRequest, String name) {
		String value = portletRequest.getParameter(name);

		if (value == null) {
			return null;
		}

		return StringUtil.split(GetterUtil.getString(value), 0L);
	}

	protected void updateFieldGroup(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		try {
			updateProjectsEntries(actionRequest, actionResponse);

			String redirect = ParamUtil.getString(actionRequest, "redirect");

			jsonObject.put("redirect", redirect);
			jsonObject.put("success", Boolean.TRUE);
		}
		catch (Exception e) {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			String message = LanguageUtil.get(
				themeDisplay.getLocale(), "your-request-failed-to-complete");

			jsonObject.put("message", message);
			jsonObject.put("success", Boolean.FALSE);
		}

		HttpServletResponse response = PortalUtil.getHttpServletResponse(
			actionResponse);

		response.setContentType(ContentTypes.TEXT_JAVASCRIPT);

		ServletResponseUtil.write(response, jsonObject.toString());
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

		Set<Long> projectsEntryIds = new HashSet<>();

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

		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(User.class);

		indexer.reindex(user);
	}

	protected void updateUser(
			StrutsPortletAction originalStrutsPortletAction,
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		updateProjectsEntries(actionRequest, actionResponse);

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		if (!cmd.equals(Constants.UPDATE)) {
			originalStrutsPortletAction.processAction(
				portletConfig, actionRequest, actionResponse);

			return;
		}

		DynamicActionRequest dynamicActionRequest = new DynamicActionRequest(
			actionRequest);

		User user = PortalUtil.getSelectedUser(actionRequest);

		Role role = RoleLocalServiceUtil.fetchRole(
			user.getCompanyId(), RoleConstants.SOCIAL_OFFICE_USER);

		if (role == null) {
			originalStrutsPortletAction.processAction(
				portletConfig, dynamicActionRequest, actionResponse);

			return;
		}

		long[] roleIds = getLongArray(
			actionRequest, "rolesSearchContainerPrimaryKeys");

		boolean newSocialOfficeUser = ArrayUtil.contains(
			roleIds, role.getRoleId());

		List<Role> roles = user.getRoles();

		if (newSocialOfficeUser && !roles.contains(role)) {
			LayoutSetPrototype publicLayoutSetPrototype =
				LayoutSetPrototypeUtil.fetchLayoutSetPrototype(
					user.getCompanyId(),
					SocialOfficeConstants.LAYOUT_SET_PROTOTYPE_KEY_USER_PUBLIC);

			if (publicLayoutSetPrototype != null) {
				dynamicActionRequest.setParameter(
					"publicLayoutSetPrototypeId",
					String.valueOf(
						publicLayoutSetPrototype.getLayoutSetPrototypeId()));
				dynamicActionRequest.setParameter(
					"publicLayoutSetPrototypeLinkEnabled",
					Boolean.TRUE.toString());
			}

			LayoutSetPrototype privateLayoutSetPrototype =
				LayoutSetPrototypeUtil.fetchLayoutSetPrototype(
					user.getCompanyId(),
					SocialOfficeConstants.
						LAYOUT_SET_PROTOTYPE_KEY_USER_PRIVATE);

			if (privateLayoutSetPrototype != null) {
				dynamicActionRequest.setParameter(
					"privateLayoutSetPrototypeId",
					String.valueOf(
						privateLayoutSetPrototype.getLayoutSetPrototypeId()));
				dynamicActionRequest.setParameter(
					"privateLayoutSetPrototypeLinkEnabled",
					Boolean.TRUE.toString());
			}
		}
		else if (!newSocialOfficeUser && roles.contains(role)) {
			dynamicActionRequest.setParameter(
				"publicLayoutSetPrototypeId", StringPool.BLANK);
			dynamicActionRequest.setParameter(
				"publicLayoutSetPrototypeLinkEnabled",
				Boolean.FALSE.toString());
			dynamicActionRequest.setParameter(
				"privateLayoutSetPrototypeId", StringPool.BLANK);
			dynamicActionRequest.setParameter(
				"privateLayoutSetPrototypeLinkEnabled",
				Boolean.FALSE.toString());
		}

		originalStrutsPortletAction.processAction(
			portletConfig, dynamicActionRequest, actionResponse);
	}

}