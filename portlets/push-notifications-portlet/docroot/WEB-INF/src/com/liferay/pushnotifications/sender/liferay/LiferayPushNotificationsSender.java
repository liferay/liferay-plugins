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

package com.liferay.pushnotifications.sender.liferay;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.pushnotifications.PushNotificationsException;
import com.liferay.pushnotifications.sender.PushNotificationsSender;
import com.liferay.pushnotifications.util.PortletPropsKeys;
import com.liferay.pushnotifications.util.PortletPropsValues;
import com.liferay.pushnotifications.util.PushNotificationsConstants;

import java.util.List;
import java.util.Map;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

/**
 * @author Bruno Farache
 */
public class LiferayPushNotificationsSender implements PushNotificationsSender {

	public LiferayPushNotificationsSender() {
	}

	public LiferayPushNotificationsSender(
		String username, String password, String server, Integer timeout) {

		_username = username;
		_password = password;
		_server = server;
		_timeout = timeout;
	}

	@Override
	public PushNotificationsSender create(Map<String, Object> configuration) {
		String username = MapUtil.getString(
			configuration, PortletPropsKeys.LIFERAY_USERNAME, null);
		String password = MapUtil.getString(
			configuration, PortletPropsKeys.LIFERAY_PASSWORD, null);
		String server = MapUtil.getString(
			configuration, PortletPropsKeys.LIFERAY_SERVER, null);
		Integer timeout = (Integer)configuration.get(
			PortletPropsKeys.LIFERAY_TIMEOUT);

		return new LiferayPushNotificationsSender(
			username, password, server, timeout);
	}

	public String getPassword() {
		if (Validator.isNull(_password)) {
			_password = PortletPropsValues.LIFERAY_PASSWORD;
		}

		return _password;
	}

	public String getServer() {
		if (Validator.isNull(_server)) {
			_server = PortletPropsValues.LIFERAY_SERVER;
		}

		return _server;
	}

	public int getTimeout() {
		if (_timeout == null) {
			_timeout = PortletPropsValues.LIFERAY_TIMEOUT;
		}

		return _timeout;
	}

	public String getUsername() {
		if (Validator.isNull(_username)) {
			_username = PortletPropsValues.LIFERAY_USERNAME;
		}

		return _username;
	}

	@Override
	public void reset() {
		_password = null;
		_server = null;
		_timeout = null;
		_username = null;
	}

	@Override
	public void send(
			String platform, List<String> tokens, JSONObject payloadJSONObject)
		throws Exception {

		HttpRequest httpRequest = HttpRequest.post(getServer());

		httpRequest.basicAuthentication(getUsername(), getPassword());
		httpRequest.form(
			PushNotificationsConstants.KEY_PAYLOAD,
			payloadJSONObject.toString());
		httpRequest.form("platform", platform);

		JSONArray tokensJSONArray = JSONFactoryUtil.createJSONArray();

		for (String token : tokens) {
			tokensJSONArray.put(token);
		}

		httpRequest.form("tokens", tokensJSONArray.toString());

		httpRequest.path(
			"/api/jsonws/push-notifications-portlet.pushnotificationsdevice" +
				"/send-push-notification");
		httpRequest.timeout(getTimeout());

		HttpResponse httpResponse = httpRequest.send();

		if (httpResponse.statusCode() != 200) {
			throw new PushNotificationsException(
				"Unable to send push notification to Liferay Push " +
					httpResponse.bodyText());
		}
	}

	public void setPassword(String password) {
		_password = password;
	}

	public void setServer(String server) {
		_server = server;
	}

	public void setTimeout(int timeout) {
		_timeout = timeout;
	}

	public void setUsername(String username) {
		_username = username;
	}

	private String _password;
	private String _server;
	private Integer _timeout;
	private String _username;

}