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

package com.liferay.pushnotifications.sender.microsoft;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.pushnotifications.sender.PushNotificationsSender;
import com.liferay.pushnotifications.util.PushNotificationsConstants;

import java.util.Iterator;
import java.util.List;

import org.jboss.aerogear.windows.mpns.MPNS;
import org.jboss.aerogear.windows.mpns.MpnsNotification;
import org.jboss.aerogear.windows.mpns.MpnsService;
import org.jboss.aerogear.windows.mpns.MpnsServiceBuilder;

/**
 * @author Javier Gamarra
 * @author Salva Tejero
 */
public class MicrosoftPushNotificationsSender
	implements PushNotificationsSender {

	@Override
	public void reset() {
		_mpnsService = null;
	}

	@Override
	public void send(
			String platform, List<String> tokens, JSONObject payloadJSONObject)
		throws Exception {

		MpnsService mpnsService = getMpnsService();

		if (mpnsService == null) {
			return;
		}

		String body = payloadJSONObject.getString(
			PushNotificationsConstants.KEY_BODY);

		String from = "";

		if (payloadJSONObject.has(PushNotificationsConstants.KEY_FROM)) {
			from = payloadJSONObject.getString(
				PushNotificationsConstants.KEY_FROM);
		}

		payloadJSONObject.remove(PushNotificationsConstants.KEY_FROM);
		payloadJSONObject.remove(PushNotificationsConstants.KEY_BODY);
		String payload = buildToast(payloadJSONObject);

		MpnsNotification toast = MPNS.newNotification().toast().title(from)
				.subtitle(body)
				.parameter(payload)
				.build();

		MpnsNotification tiles = MPNS.newNotification().tile().title(from)
				.count(1).backContent(body).callbackUri(payload)
				.backTitle(from).build();

		for (String token : tokens) {
			mpnsService.push(token, tiles);
			mpnsService.push(token, toast);
		}
	}

	protected String buildToast(JSONObject payloadJSONObject) {
		StringBuilder mpnsParam = new StringBuilder();
		String sep = "";
		Iterator<String> keys = payloadJSONObject.keys();

		while (keys.hasNext()) {
			String key = keys.next();

			mpnsParam.append(sep);
			mpnsParam.append(key);
			mpnsParam.append('=');
			mpnsParam.append(payloadJSONObject.getString(key));
		}

		return mpnsParam.toString();
	}

	protected MpnsService getMpnsService() throws Exception {
		if (_mpnsService == null) {
			MpnsServiceBuilder mpnsServiceBuilder = MPNS.newService();

			_mpnsService = mpnsServiceBuilder.build();
		}

		return _mpnsService;
	}

	private MpnsService _mpnsService;

}