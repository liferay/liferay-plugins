/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.notifications.BaseUserNotificationHandler;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.UserNotificationEvent;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserNotificationEventLocalServiceUtil;
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

		long tasksEntryId = jsonObject.getLong("tasksEntryId");

		TasksEntry tasksEntry = TasksEntryLocalServiceUtil.fetchTasksEntry(
			tasksEntryId);

		if (tasksEntry == null) {
			UserNotificationEventLocalServiceUtil.deleteUserNotificationEvent(
				userNotificationEvent.getUserNotificationEventId());

			return null;
		}

		StringBundler sb = new StringBundler(5);

		sb.append("<div class=\"title\">");
		sb.append(HtmlUtil.escape(jsonObject.getString("title")));
		sb.append("</div><div class=\"body\">");
		sb.append(HtmlUtil.escape(tasksEntry.getTitle()));
		sb.append("</div>");

		return sb.toString();
	}

	@Override
	protected String getLink(
			UserNotificationEvent userNotificationEvent,
			ServiceContext serviceContext)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
			userNotificationEvent.getPayload());

		long tasksEntryId = jsonObject.getLong("tasksEntryId");

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