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

package com.liferay.pushnotifications.service.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.pushnotifications.model.PushNotificationsEntry;
import com.liferay.pushnotifications.service.base.PushNotificationsEntryServiceBaseImpl;
import com.liferay.pushnotifications.service.permission.PushNotificationsPermission;
import com.liferay.pushnotifications.util.ActionKeys;
import com.liferay.pushnotifications.util.PushNotificationsConstants;

import java.util.List;

/**
 * @author Bruno Farache
 */
@ProviderType
public class PushNotificationsEntryServiceImpl
	extends PushNotificationsEntryServiceBaseImpl {

	@Override
	public List<PushNotificationsEntry> getPushNotificationsEntries(
		long parentPushNotificationsEntryId, long lastAccessTime, int start,
		int end) {

		return pushNotificationsEntryLocalService.getPushNotificationsEntries(
			parentPushNotificationsEntryId, lastAccessTime, start, end);
	}

	@Override
	public void sendPushNotification(long toUserId, String payload)
		throws PortalException {

		PushNotificationsPermission.check(
			getPermissionChecker(), ActionKeys.SEND_NOTIFICATION);

		JSONObject jsonObject = createJSONObject(payload);

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Sending message " + jsonObject + " to user " + toUserId);
		}

		pushNotificationsEntryLocalService.sendPushNotification(
			toUserId, jsonObject, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	@Override
	public void sendPushNotification(String payload) throws PortalException {
		PushNotificationsPermission.check(
			getPermissionChecker(), ActionKeys.SEND_NOTIFICATION);

		JSONObject jsonObject = createJSONObject(payload);

		if (_log.isDebugEnabled()) {
			_log.debug("Sending message " + jsonObject + " to all users");
		}

		pushNotificationsEntryLocalService.sendPushNotification(
			jsonObject, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	protected JSONObject createJSONObject(String payload)
		throws PortalException {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put(
			PushNotificationsConstants.KEY_PAYLOAD,
			JSONFactoryUtil.createJSONObject(payload));

		JSONObject fromUserJSONObject = JSONFactoryUtil.createJSONObject();

		fromUserJSONObject.put(
			PushNotificationsConstants.KEY_USER_ID, getUserId());

		jsonObject.put(
			PushNotificationsConstants.KEY_FROM_USER, fromUserJSONObject);

		return jsonObject;
	}

	private static Log _log = LogFactoryUtil.getLog(
		PushNotificationsEntryServiceImpl.class);

}