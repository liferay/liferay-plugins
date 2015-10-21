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

package com.liferay.pushnotifications.sender.sms;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.pushnotifications.sender.BaseResponse;
import com.liferay.pushnotifications.util.PushNotificationsConstants;

import com.twilio.sdk.resource.instance.Sms;

/**
 * @author Bruno Farache
 */
public class TwilioResponse extends BaseResponse {

	public TwilioResponse(Sms sms, JSONObject payloadJSONObject) {
		super(PushNotificationsConstants.PLATFORM_SMS);

		accountSid = sms.getAccountSid();
		id = sms.getSid();
		payload = payloadJSONObject.toString();
		price = sms.getPrice();
		status = sms.getStatus();

		if (Validator.isNotNull(status) && status.equals("queued")) {
			succeeded = true;
		}

		token = sms.getTo();
	}

	public String getAccountSid() {
		return accountSid;
	}

	public String getPrice() {
		return price;
	}

	protected String accountSid;
	protected String price;

}