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

package com.liferay.pushnotifications.sender.android;

import com.google.android.gcm.server.Constants;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Message.Builder;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.pushnotifications.PushNotificationsException;
import com.liferay.pushnotifications.messaging.DestinationNames;
import com.liferay.pushnotifications.sender.PushNotificationsSender;
import com.liferay.pushnotifications.sender.Response;
import com.liferay.pushnotifications.service.PushNotificationsDeviceLocalServiceUtil;
import com.liferay.pushnotifications.util.PortletPropsKeys;
import com.liferay.pushnotifications.util.PortletPropsValues;
import com.liferay.pushnotifications.util.PushNotificationsConstants;

import java.util.List;
import java.util.Map;

/**
 * @author Silvio Santos
 * @author Bruno Farache
 */
public class AndroidPushNotificationsSender implements PushNotificationsSender {

	public AndroidPushNotificationsSender() {
	}

	public AndroidPushNotificationsSender(String apiKey, Integer retries) {
		_apiKey = apiKey;
		_retries = retries;
	}

	@Override
	public PushNotificationsSender create(Map<String, Object> configuration) {
		String apiKey = MapUtil.getString(
			configuration, PortletPropsKeys.ANDROID_API_KEY, null);
		Integer retries = (Integer)configuration.get(
			PortletPropsKeys.ANDROID_RETRIES);

		return new AndroidPushNotificationsSender(apiKey, retries);
	}

	public String getAPIKey() {
		if (Validator.isNull(_apiKey)) {
			_apiKey = PrefsPropsUtil.getString(
				PortletPropsKeys.ANDROID_API_KEY,
				PortletPropsValues.ANDROID_API_KEY);
		}

		return _apiKey;
	}

	public int getRetries() {
		if (_retries == null) {
			_retries = PrefsPropsUtil.getInteger(
				PortletPropsKeys.ANDROID_RETRIES,
				PortletPropsValues.ANDROID_RETRIES);
		}

		return _retries;
	}

	@Override
	public synchronized void reset() {
		_apiKey = null;
		_retries = null;
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

		Message message = buildMessage(payloadJSONObject);

		MulticastResult multicastResult = sender.send(
			message, tokens, getRetries());

		validateMulticastResult(tokens, payloadJSONObject, multicastResult);
	}

	public void setAPIKey(String apiKey) {
		_apiKey = apiKey;
	}

	public void setRetries(int retries) {
		_retries = retries;
	}

	protected Message buildMessage(JSONObject payloadJSONObject) {
		Builder builder = new Builder();

		builder.addData(
			PushNotificationsConstants.KEY_PAYLOAD,
			payloadJSONObject.toString());

		return builder.build();
	}

	protected synchronized Sender getSender() throws Exception {
		if (_sender == null) {
			String apiKey = getAPIKey();

			if (Validator.isNull(apiKey)) {
				throw new PushNotificationsException(
					"The property \"android.api.key\" is not set in " +
						"portlet.properties");
			}

			_sender = new Sender(apiKey);
		}

		return _sender;
	}

	protected void validateMulticastResult(
		List<String> tokens, JSONObject payloadJSONObject,
		MulticastResult multicastResult) {

		List<Result> results = multicastResult.getResults();

		for (int i = 0; i < results.size(); i++) {
			Result result = results.get(i);
			String token = tokens.get(i);

			Response response = new AndroidResponse(
				result, token, payloadJSONObject);

			MessageBusUtil.sendMessage(
				DestinationNames.PUSH_NOTIFICATION_RESPONSE, response);

			if ((multicastResult.getCanonicalIds() == 0) &&
				(multicastResult.getFailure() == 0)) {

				continue;
			}

			String canonicalRegistrationId =
				result.getCanonicalRegistrationId();
			String messageId = result.getMessageId();

			if (Validator.isNotNull(canonicalRegistrationId) &&
				Validator.isNotNull(messageId)) {

				try {
					PushNotificationsDeviceLocalServiceUtil.updateToken(
						token, canonicalRegistrationId);
				}
				catch (Exception e) {
					if (_log.isWarnEnabled()) {
						_log.warn("Unable to update token " + token);
					}
				}
			}

			String errorCodeName = result.getErrorCodeName();

			if (Validator.isNotNull(errorCodeName)) {
				if (errorCodeName.equals(
						Constants.ERROR_INVALID_REGISTRATION) ||
					errorCodeName.equals(Constants.ERROR_MISMATCH_SENDER_ID) ||
					errorCodeName.equals(Constants.ERROR_NOT_REGISTERED)) {

					try {
						PushNotificationsDeviceLocalServiceUtil.
							deletePushNotificationsDevice(token);
					}
					catch (Exception e) {
						if (_log.isWarnEnabled()) {
							_log.warn(
								"Unable to delete invalid token " + token);
						}
					}
				}
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		AndroidPushNotificationsSender.class);

	private String _apiKey;
	private Integer _retries;
	private Sender _sender;

}