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

package com.liferay.pushnotifications.sender.firebase;

import com.liferay.mobile.fcm.Message;
import com.liferay.mobile.fcm.Notification;
import com.liferay.mobile.fcm.Sender;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.pushnotifications.PushNotificationsException;
import com.liferay.pushnotifications.sender.PushNotificationsSender;
import com.liferay.pushnotifications.util.PortletPropsKeys;
import com.liferay.pushnotifications.util.PortletPropsValues;
import com.liferay.pushnotifications.util.PushNotificationsConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Bruno Farache
 */
public class FirebasePushNotificationsSender
	implements PushNotificationsSender {

	public FirebasePushNotificationsSender() {
	}

	public FirebasePushNotificationsSender(String apiKey) {
		_apiKey = apiKey;
	}

	@Override
	public PushNotificationsSender create(Map<String, Object> configuration) {
		String apiKey = MapUtil.getString(
			configuration, PortletPropsKeys.FIREBASE_API_KEY, null);

		return new FirebasePushNotificationsSender(apiKey);
	}

	@Override
	public void reset() {
		_apiKey = null;
		_sender = null;
	}

	@Override
	public void send(
			String platform, List<String> tokens, JSONObject payloadJSONObject)
		throws Exception {

		Sender sender = getSender();

		if (sender == null) {
			return;
		}

		sender.send(buildMessage(tokens, payloadJSONObject));
	}

	public void setAPIKey(String apiKey) {
		_apiKey = apiKey;
	}

	protected Message buildMessage(
		List<String> tokens, JSONObject payloadJSONObject) {

		Message.Builder builder = new Message.Builder();

		boolean silent = payloadJSONObject.getBoolean(
			PushNotificationsConstants.KEY_SILENT);

		if (silent) {
			builder.contentAvailable(silent);
		}

		builder.notification(buildNotification(payloadJSONObject));
		builder.to(tokens);

		JSONObject newPayloadJSONObject = JSONFactoryUtil.createJSONObject();

		Iterator<String> iterator = payloadJSONObject.keys();

		while (iterator.hasNext()) {
			String key = iterator.next();

			if (!key.equals(PushNotificationsConstants.KEY_BADGE) &&
				!key.equals(PushNotificationsConstants.KEY_BODY) &&
				!key.equals(PushNotificationsConstants.KEY_BODY_LOCALIZED) &&
				!key.equals(
					PushNotificationsConstants.KEY_BODY_LOCALIZED_ARGUMENTS) &&
				!key.equals(PushNotificationsConstants.KEY_SOUND) &&
				!key.equals(PushNotificationsConstants.KEY_SILENT)) {

				newPayloadJSONObject.put(key, payloadJSONObject.getString(key));
			}
		}

		if (newPayloadJSONObject.length() > 0) {
			Map<String, String> data = new HashMap<String, String>();

			data.put(
				PushNotificationsConstants.KEY_PAYLOAD,
				newPayloadJSONObject.toString());

			builder.data(data);
		}

		return builder.build();
	}

	protected Notification buildNotification(JSONObject payloadJSONObject) {
		Notification.Builder builder = new Notification.Builder();

		if (payloadJSONObject.has(PushNotificationsConstants.KEY_BADGE)) {
			builder.badge(
				payloadJSONObject.getInt(PushNotificationsConstants.KEY_BADGE));
		}

		String body = payloadJSONObject.getString(
			PushNotificationsConstants.KEY_BODY);

		if (Validator.isNotNull(body)) {
			builder.body(body);
		}

		String bodyLocalizedKey = payloadJSONObject.getString(
			PushNotificationsConstants.KEY_BODY_LOCALIZED);

		if (Validator.isNotNull(bodyLocalizedKey)) {
			builder.bodyLocalizationKey(bodyLocalizedKey);
		}

		JSONArray bodyLocalizedArgumentsJSONArray =
			payloadJSONObject.getJSONArray(
				PushNotificationsConstants.KEY_BODY_LOCALIZED_ARGUMENTS);

		if (bodyLocalizedArgumentsJSONArray != null) {
			List<String> bodyLocalizedArguments = new ArrayList<String>();

			for (int i = 0; i < bodyLocalizedArgumentsJSONArray.length(); i++) {
				bodyLocalizedArguments.add(
					bodyLocalizedArgumentsJSONArray.getString(i));
			}

			builder.bodyLocalizationArguments(bodyLocalizedArguments);
		}

		String sound = payloadJSONObject.getString(
			PushNotificationsConstants.KEY_SOUND);

		if (Validator.isNotNull(sound)) {
			builder.sound(sound);
		}

		return builder.build();
	}

	protected String getAPIKey() throws SystemException {
		if (Validator.isNull(_apiKey)) {
			_apiKey = PrefsPropsUtil.getString(
				PortletPropsKeys.FIREBASE_API_KEY,
				PortletPropsValues.FIREBASE_API_KEY);
		}

		return _apiKey;
	}

	protected synchronized Sender getSender() throws Exception {
		if (_sender == null) {
			String apiKey = getAPIKey();

			if (Validator.isNull(apiKey)) {
				throw new PushNotificationsException(
					"The property \"firebase.api.key\" is not set in " +
						"portlet.properties");
			}

			_sender = new Sender(apiKey);
		}

		return _sender;
	}

	private String _apiKey;
	private Sender _sender;

}