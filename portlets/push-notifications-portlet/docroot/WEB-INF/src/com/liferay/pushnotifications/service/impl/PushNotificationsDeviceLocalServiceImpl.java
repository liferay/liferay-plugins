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

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.pushnotifications.PushNotificationsException;
import com.liferay.pushnotifications.model.PushNotificationsDevice;
import com.liferay.pushnotifications.sender.PushNotificationsSender;
import com.liferay.pushnotifications.service.base.PushNotificationsDeviceLocalServiceBaseImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Silvio Santos
 * @author Bruno Farache
 */
public class PushNotificationsDeviceLocalServiceImpl
	extends PushNotificationsDeviceLocalServiceBaseImpl {

	@Override
	public PushNotificationsDevice addPushNotificationsDevice(
		long userId, String platform, String token) {

		long pushNotificationsDeviceId = counterLocalService.increment();

		PushNotificationsDevice pushNotificationsDevice =
			pushNotificationsDevicePersistence.create(
				pushNotificationsDeviceId);

		pushNotificationsDevice.setUserId(userId);
		pushNotificationsDevice.setCreateDate(new Date());
		pushNotificationsDevice.setPlatform(platform);
		pushNotificationsDevice.setToken(token);

		pushNotificationsDevicePersistence.update(pushNotificationsDevice);

		return pushNotificationsDevice;
	}

	@Override
	public PushNotificationsDevice deletePushNotificationsDevice(String token)
		throws PortalException {

		PushNotificationsDevice pushNotificationsDevice =
			pushNotificationsDevicePersistence.findByToken(token);

		pushNotificationsDevicePersistence.remove(pushNotificationsDevice);

		return pushNotificationsDevice;
	}

	public List<PushNotificationsDevice> getPushNotificationsDevices(
		int start, int end, OrderByComparator orderByComparator) {

		return pushNotificationsDevicePersistence.findAll(
			start, end, orderByComparator);
	}

	@Override
	public void resetPushNotificationSenders() {
		for (Map.Entry<String, PushNotificationsSender> entry :
				_pushNotificationsSenders.entrySet()) {

			PushNotificationsSender pushNotificationsSender = entry.getValue();

			pushNotificationsSender.reset();
		}
	}

	@Override
	public void sendPushNotification(
			long[] toUserIds, JSONObject payloadJSONObject)
		throws PortalException {

		for (String platform : _pushNotificationsSenders.keySet()) {
			List<String> tokens = new ArrayList<>();

			List<PushNotificationsDevice> pushNotificationsDevices =
				pushNotificationsDevicePersistence.findByU_P(
					toUserIds, platform, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (PushNotificationsDevice pushNotificationsDevice :
					pushNotificationsDevices) {

				tokens.add(pushNotificationsDevice.getToken());
			}

			if (tokens.isEmpty()) {
				continue;
			}

			sendPushNotification(platform, tokens, payloadJSONObject);
		}
	}

	@Override
	public void sendPushNotification(
			String platform, List<String> tokens, JSONObject payloadJSONObject)
		throws PortalException {

		PushNotificationsSender pushNotificationsSender =
			_pushNotificationsSenders.get(platform);

		if (pushNotificationsSender == null) {
			return;
		}

		try {
			pushNotificationsSender.send(platform, tokens, payloadJSONObject);
		}
		catch (PushNotificationsException pne) {
			if (_log.isWarnEnabled()) {
				_log.warn(pne.getMessage());
			}
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		PushNotificationsDeviceLocalServiceImpl.class);

	@BeanReference(name = "pushNotificationsSenders")
	private Map<String, PushNotificationsSender> _pushNotificationsSenders;

}