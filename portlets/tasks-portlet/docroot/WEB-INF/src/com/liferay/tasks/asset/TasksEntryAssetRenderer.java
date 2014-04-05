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

package com.liferay.tasks.asset;

import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletURLFactoryUtil;
import com.liferay.portlet.asset.model.BaseAssetRenderer;
import com.liferay.tasks.model.TasksEntry;
import com.liferay.tasks.service.permission.TasksEntryPermission;
import com.liferay.tasks.util.PortletKeys;
import com.liferay.tasks.util.WebKeys;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Matthew Kong
 */
public class TasksEntryAssetRenderer extends BaseAssetRenderer {

	public TasksEntryAssetRenderer(TasksEntry entry) {
		_entry = entry;
	}

	@Override
	public String getClassName() {
		return TasksEntry.class.getName();
	}

	@Override
	public long getClassPK() {
		return _entry.getTasksEntryId();
	}

	@Override
	public long getGroupId() {
		return _entry.getGroupId();
	}

	@Override
	public String getSummary(Locale locale) {
		return _entry.getTitle();
	}

	@Override
	public String getTitle(Locale locale) {
		return _entry.getTitle();
	}

	@Override
	public String getURLViewInContext(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse,
		String noSuchEntryRedirect) {

		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)liferayPortletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			User user = themeDisplay.getUser();

			long portletPlid = PortalUtil.getPlidFromPortletId(
				user.getGroupId(), true, PortletKeys.TASKS);

			PortletURL portletURL = PortletURLFactoryUtil.create(
				liferayPortletRequest, PortletKeys.TASKS, portletPlid,
				PortletRequest.RENDER_PHASE);

			portletURL.setParameter("mvcPath", "/tasks/view.jsp");

			return portletURL.toString();
		}
		catch (Exception e) {
		}

		return null;
	}

	@Override
	public long getUserId() {
		return _entry.getUserId();
	}

	@Override
	public String getUserName() {
		return _entry.getUserName();
	}

	@Override
	public String getUuid() {
		return null;
	}

	@Override
	public boolean hasViewPermission(PermissionChecker permissionChecker) {
		return TasksEntryPermission.contains(
			permissionChecker, _entry, ActionKeys.VIEW);
	}

	@Override
	public String render(
		RenderRequest renderRequest, RenderResponse renderResponse,
		String template) {

		if (template.equals(TEMPLATE_ABSTRACT) ||
			template.equals(TEMPLATE_FULL_CONTENT)) {

			renderRequest.setAttribute(WebKeys.TASKS_ENTRY, _entry);

			return "/tasks/asset/" + template + ".jsp";
		}
		else {
			return null;
		}
	}

	private TasksEntry _entry;

}