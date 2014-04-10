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

package com.liferay.tasks.notifications;

import com.liferay.compat.portal.kernel.notifications.BaseUserNotificationHandler;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.UserNotificationEvent;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserNotificationEventLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.AssetRendererFactoryRegistryUtil;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.asset.model.AssetRendererFactory;
import com.liferay.tasks.model.TasksEntry;
import com.liferay.tasks.service.TasksEntryLocalServiceUtil;
import com.liferay.tasks.util.PortletKeys;

/**
 * @author Jonathan Lee
 */
public class TasksUserNotificationHandler extends BaseUserNotificationHandler {

	public TasksUserNotificationHandler() {
		setPortletId(PortletKeys.TASKS);
	}

	@Override
	protected String getBody(
			UserNotificationEvent userNotificationEvent,
			ServiceContext serviceContext)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
			userNotificationEvent.getPayload());

		long tasksEntryId = jsonObject.getLong("classPK");

		TasksEntry tasksEntry = TasksEntryLocalServiceUtil.fetchTasksEntry(
			tasksEntryId);

		if (tasksEntry == null) {
			UserNotificationEventLocalServiceUtil.deleteUserNotificationEvent(
				userNotificationEvent.getUserNotificationEventId());

			return null;
		}

		String title = serviceContext.translate(
			jsonObject.getString("title"),
			HtmlUtil.escape(
				PortalUtil.getUserName(
					jsonObject.getLong("userId"), StringPool.BLANK)));

		return StringUtil.replace(
			getBodyTemplate(), new String[] {"[$BODY$]", "[$TITLE$]"},
			new String[] {
				HtmlUtil.escape(
					StringUtil.shorten(HtmlUtil.escape(tasksEntry.getTitle()))),
				title
			});
	}

	@Override
	protected String getLink(
			UserNotificationEvent userNotificationEvent,
			ServiceContext serviceContext)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
			userNotificationEvent.getPayload());

		long tasksEntryId = jsonObject.getLong("classPK");

		AssetRendererFactory assetRendererFactory =
			AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(
				TasksEntry.class.getName());

		AssetRenderer assetRenderer = assetRendererFactory.getAssetRenderer(
			tasksEntryId);

		return assetRenderer.getURLViewInContext(
			serviceContext.getLiferayPortletRequest(),
			serviceContext.getLiferayPortletResponse(), null);
	}

}