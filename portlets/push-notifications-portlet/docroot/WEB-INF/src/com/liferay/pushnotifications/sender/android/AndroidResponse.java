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

import com.google.android.gcm.server.Result;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.pushnotifications.sender.BaseResponse;
import com.liferay.pushnotifications.util.PushNotificationsConstants;

/**
 * @author Bruno Farache
 */
public class AndroidResponse extends BaseResponse {

	public AndroidResponse(
		Result result, String token, JSONObject payloadJSONObject) {

		super(PushNotificationsConstants.PLATFORM_ANDROID);

		this.token = token;

		canonicalRegistrationId = result.getCanonicalRegistrationId();
		id = result.getMessageId();
		payload = payloadJSONObject.toString();
		status = result.getErrorCodeName();

		if (Validator.isNull(status)) {
			succeeded = true;
		}
	}

	public String getCanonicalRegistrationId() {
		return canonicalRegistrationId;
	}

	protected String canonicalRegistrationId;

}