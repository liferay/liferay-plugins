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

package com.liferay.mentions.portlet.notifications;

import com.liferay.compat.portal.kernel.notifications.BaseUserNotificationHandler;
import com.liferay.mentions.util.PortletKeys;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.UserNotificationEvent;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserNotificationEventLocalServiceUtil;
import com.liferay.portlet.asset.AssetRendererFactoryRegistryUtil;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.asset.model.AssetRendererFactory;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;

/**
 * @author Iván Zaera
 * @author Sergio González
 */
public class MentionsUserNotificationHandler
	extends BaseUserNotificationHandler {

	public MentionsUserNotificationHandler() {
		setPortletId(PortletKeys.MENTIONS);
	}

	protected AssetRenderer getAssetRenderer(JSONObject jsonObject)
		throws SystemException {

		MBMessage mbMessage = MBMessageLocalServiceUtil.fetchMBMessage(
			jsonObject.getLong("classPK"));

		if ((mbMessage != null) && mbMessage.isDiscussion()) {
			return getAssetRenderer(
				mbMessage.getClassName(), mbMessage.getClassPK());
		}
		else {
			return getAssetRenderer(
				jsonObject.getString("className"),
				jsonObject.getLong("classPK"));
		}
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
		throws SystemException {

		MBMessage mbMessage = MBMessageLocalServiceUtil.fetchMBMessage(
			jsonObject.getLong("classPK"));

		AssetRendererFactory assetRendererFactory =
			AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(
				assetRenderer.getClassName());

		String typeName = assetRendererFactory.getTypeName(
			serviceContext.getLocale(), false);

		if ((mbMessage != null) && mbMessage.isDiscussion()) {
			return LanguageUtil.format(
				serviceContext.getLocale(),
				"x-mentioned-you-in-a-comment-in-a-x",
				new String[] {
					HtmlUtil.escape(assetRenderer.getUserName()),
					StringUtil.toLowerCase(HtmlUtil.escape(typeName))
				},
				false);
		}
		else {
			return LanguageUtil.format(
				serviceContext.getLocale(), "x-mentioned-you-in-a-x",
				new String[] {
					HtmlUtil.escape(assetRenderer.getUserName()),
					StringUtil.toLowerCase(HtmlUtil.escape(typeName))
				},
				false);
		}
	}

}