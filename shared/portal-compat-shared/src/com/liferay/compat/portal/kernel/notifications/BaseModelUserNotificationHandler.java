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

package com.liferay.compat.portal.kernel.notifications;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserNotificationEvent;
import com.liferay.portal.security.permission.ResourceActionsUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.UserNotificationEventLocalServiceUtil;
import com.liferay.portlet.asset.AssetRendererFactoryRegistryUtil;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.asset.model.AssetRendererFactory;

/**
 * @author Brian Wing Shun Chan
 * @author Sergio González
 */
public abstract class BaseModelUserNotificationHandler
	extends BaseUserNotificationHandler {

	protected AssetRenderer getAssetRenderer(JSONObject jsonObject) {
		String className = jsonObject.getString("className");
		long classPK = jsonObject.getLong("classPK");

		return getAssetRenderer(className, classPK);
	}

	protected AssetRenderer getAssetRenderer(String className, long classPK) {
		AssetRendererFactory assetRendererFactory =
			AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(
				className);

		if (assetRendererFactory == null) {
			return null;
		}

		AssetRenderer assetRenderer = null;

		try {
			assetRenderer = assetRendererFactory.getAssetRenderer(classPK);
		}
		catch (Exception e) {
		}

		return assetRenderer;
	}

	@Override
	protected String getBody(
			UserNotificationEvent userNotificationEvent,
			ServiceContext serviceContext)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
			userNotificationEvent.getPayload());

		AssetRenderer assetRenderer = getAssetRenderer(jsonObject);

		if (assetRenderer == null) {
			UserNotificationEventLocalServiceUtil.deleteUserNotificationEvent(
				userNotificationEvent.getUserNotificationEventId());

			return null;
		}

		return StringUtil.replace(
			getBodyTemplate(), new String[] {"[$BODY$]", "[$TITLE$]"},
			new String[] {
				HtmlUtil.escape(
					StringUtil.shorten(jsonObject.getString("entryTitle"), 70)),
				getTitle(jsonObject, assetRenderer, serviceContext)
			});
	}

	@Override
	protected String getLink(
			UserNotificationEvent userNotificationEvent,
			ServiceContext serviceContext)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
			userNotificationEvent.getPayload());

		return jsonObject.getString("entryURL");
	}

	protected String getTitle(
			JSONObject jsonObject, AssetRenderer assetRenderer,
			ServiceContext serviceContext)
		throws Exception {

		String message = StringPool.BLANK;

		String typeName = ResourceActionsUtil.getModelResource(
			serviceContext.getLocale(), assetRenderer.getClassName());

		int notificationType = jsonObject.getInt("notificationType");

		if (notificationType ==
				UserNotificationDefinition.NOTIFICATION_TYPE_ADD_ENTRY) {

			message = "x-added-a-new-x";
		}
		else if (notificationType ==
					UserNotificationDefinition.
						NOTIFICATION_TYPE_UPDATE_ENTRY) {

			message = "x-updated-a-x";
		}

		String userName = assetRenderer.getUserName();

		long userId = jsonObject.getLong("userId");

		if (userId > 0) {
			User user = UserLocalServiceUtil.getUser(userId);

			userName = user.getFullName();
		}

		return serviceContext.translate(
			message, HtmlUtil.escape(userName),
			StringUtil.toLowerCase(HtmlUtil.escape(typeName)));
	}

}