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
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.pushnotifications.sender.PushNotificationsSender;
import com.liferay.pushnotifications.util.PushNotificationsConstants;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jboss.aerogear.windows.mpns.MPNS;
import org.jboss.aerogear.windows.mpns.MpnsService;
import org.jboss.aerogear.windows.mpns.MpnsServiceBuilder;
import org.jboss.aerogear.windows.mpns.notifications.TileNotification;
import org.jboss.aerogear.windows.mpns.notifications.ToastNotification;

/**
 * @author Javier Gamarra
 * @author Salva Tejero
 */
public class MicrosoftPushNotificationsSender
	implements PushNotificationsSender {

	@Override
	public PushNotificationsSender create(Map<String, Object> configuration) {
		return new MicrosoftPushNotificationsSender();
	}

	@Override
	public synchronized void reset() {
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

		String from = StringPool.BLANK;

		if (payloadJSONObject.has(PushNotificationsConstants.KEY_FROM)) {
			from = payloadJSONObject.getString(
				PushNotificationsConstants.KEY_FROM);
		}

		payloadJSONObject.remove(PushNotificationsConstants.KEY_FROM);

		String body = payloadJSONObject.getString(
			PushNotificationsConstants.KEY_BODY);

		payloadJSONObject.remove(PushNotificationsConstants.KEY_BODY);

		String attributes = getAttributes(payloadJSONObject);

		TileNotification tileNotification = buildTileNotification(
			from, body, attributes);
		ToastNotification toastNotification = buildToastNotification(
			from, body, attributes);

		for (String token : tokens) {
			mpnsService.push(token, tileNotification);
			mpnsService.push(token, toastNotification);
		}
	}

	protected TileNotification buildTileNotification(
		String from, String body, String attributes) {

		TileNotification.Builder builder = MPNS.newNotification().tile();

		builder.backContent(body);
		builder.backTitle(from);
		builder.callbackUri(attributes);
		builder.count(1);
		builder.title(from);

		return builder.build();
	}

	protected ToastNotification buildToastNotification(
		String from, String body, String attributes) {

		ToastNotification.Builder builder = MPNS.newNotification().toast();

		builder.parameter(attributes);
		builder.subtitle(body);
		builder.title(from);

		return builder.build();
	}

	protected String getAttributes(JSONObject payloadJSONObject) {
		StringBuilder sb = new StringBuilder();

		Iterator<String> itr = payloadJSONObject.keys();

		while (itr.hasNext()) {
			String key = itr.next();

			sb.append(key);
			sb.append(CharPool.EQUAL);
			sb.append(payloadJSONObject.getString(key));
		}

		return sb.toString();
	}

	protected synchronized MpnsService getMpnsService() throws Exception {
		if (_mpnsService == null) {
			MpnsServiceBuilder mpnsServiceBuilder = MPNS.newService();

			_mpnsService = mpnsServiceBuilder.build();
		}

		return _mpnsService;
	}

	private MpnsService _mpnsService;

}