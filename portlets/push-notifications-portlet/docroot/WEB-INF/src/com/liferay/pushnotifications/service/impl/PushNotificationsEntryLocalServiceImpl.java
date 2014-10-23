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

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.model.User;
import com.liferay.pushnotifications.model.PushNotificationsDevice;
import com.liferay.pushnotifications.model.PushNotificationsEntry;
import com.liferay.pushnotifications.sender.PushNotificationsSender;
import com.liferay.pushnotifications.service.base.PushNotificationsEntryLocalServiceBaseImpl;
import com.liferay.pushnotifications.util.PushNotificationsConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Bruno Farache
 */
@ProviderType
public class PushNotificationsEntryLocalServiceImpl
	extends PushNotificationsEntryLocalServiceBaseImpl {

	@Override
	public PushNotificationsEntry addPushNotificationsEntry(
		long userId, long parentPushNotificationsEntryId,
		JSONObject payloadJSONObject) {

		long pushNotificationsEntryId = counterLocalService.increment();

		PushNotificationsEntry pushNotificationsEntry =
			pushNotificationsEntryPersistence.create(pushNotificationsEntryId);

		pushNotificationsEntry.setUserId(userId);
		pushNotificationsEntry.setCreateTime(System.currentTimeMillis());
		pushNotificationsEntry.setParentPushNotificationsEntryId(
			parentPushNotificationsEntryId);
		pushNotificationsEntry.setPayload(payloadJSONObject.toString());

		pushNotificationsEntryPersistence.update(pushNotificationsEntry);

		return pushNotificationsEntry;
	}

	@Override
	public List<PushNotificationsEntry> getPushNotificationsEntries(
		long parentPushNotificationsEntryId, long lastAccessTime, int start,
		int end) {

		return pushNotificationsEntryPersistence.findByC_P(
			lastAccessTime, parentPushNotificationsEntryId, start, end);
	}

	@Override
	public void sendPushNotification(JSONObject jsonObject, int start, int end)
		throws PortalException {

		sendPushNotification(0, jsonObject, start, end);
	}

	@Override
	public void sendPushNotification(
			long toUserId, JSONObject jsonObject, int start, int end)
		throws PortalException {

		long fromUserId = addFromUserDetails(jsonObject);

		JSONObject payloadJSONObject = jsonObject.getJSONObject(
			PushNotificationsConstants.KEY_PAYLOAD);

		long parentPushNotificationsEntryId = jsonObject.getLong(
			PushNotificationsConstants.KEY_PARENT_PUSH_NOTIFICATIONS_ENTRY_ID,
			PushNotificationsConstants.
				VALUE_PARENT_PUSH_NOTIFICATIONS_ENTRY_ID_DEFAULT);

		addPushNotificationsEntry(
			fromUserId, parentPushNotificationsEntryId, payloadJSONObject);

		for (Map.Entry<String, PushNotificationsSender> entry :
				_pushNotificationsSenders.entrySet()) {

			List<String> tokens = new ArrayList<String>();

			List<PushNotificationsDevice> pushNotificationsDevices =
				pushNotificationsDeviceLocalService.getPushNotificationsDevices(
					toUserId, entry.getKey(), start, end);

			for (PushNotificationsDevice pushNotificationsDevice :
					pushNotificationsDevices) {

				tokens.add(pushNotificationsDevice.getToken());
			}

			if (tokens.isEmpty()) {
				continue;
			}

			PushNotificationsSender pushNotificationsSender = entry.getValue();

			try {
				pushNotificationsSender.send(tokens, jsonObject);
			}
			catch (PortalException pe) {
				throw pe;
			}
			catch (Exception e) {
				throw new PortalException(e);
			}
		}
	}

	protected long addFromUserDetails(JSONObject jsonObject)
		throws PortalException {

		JSONObject fromUserJSONObject = jsonObject.getJSONObject(
			PushNotificationsConstants.KEY_FROM_USER);

		long fromUserId = fromUserJSONObject.getLong(
			PushNotificationsConstants.KEY_USER_ID);

		User user = userLocalService.getUser(fromUserId);

		fromUserJSONObject.put(
			PushNotificationsConstants.KEY_FULL_NAME, user.getFullName());
		fromUserJSONObject.put(
			PushNotificationsConstants.KEY_PORTRAIT_ID, user.getPortraitId());
		fromUserJSONObject.put(
			PushNotificationsConstants.KEY_UUID, user.getUuid());

		return fromUserId;
	}

	@BeanReference(name = "pushNotificationsSenders")
	private Map<String, PushNotificationsSender> _pushNotificationsSenders;

}