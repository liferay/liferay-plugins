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
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.pushnotifications.PushNotificationsException;
import com.liferay.pushnotifications.sender.PushNotificationsSender;
import com.liferay.pushnotifications.util.PortletPropsKeys;
import com.liferay.pushnotifications.util.PortletPropsValues;
import com.liferay.pushnotifications.util.PushNotificationsConstants;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.factory.SmsFactory;
import com.twilio.sdk.resource.instance.Account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Bruno Farache
 */
public class TwilioSMSSender implements PushNotificationsSender {

	public TwilioSMSSender() {
	}

	public TwilioSMSSender(String accountSID, String authToken, String number) {
		_accountSID = accountSID;
		_authToken = authToken;
		_number = number;
	}

	@Override
	public PushNotificationsSender create(Map<String, Object> configuration) {
		String accountSID = MapUtil.getString(
			configuration, PortletPropsKeys.SMS_TWILIO_ACCOUNT_SID, null);
		String authToken = MapUtil.getString(
			configuration, PortletPropsKeys.SMS_TWILIO_AUTH_TOKEN, null);
		String number = MapUtil.getString(
			configuration, PortletPropsKeys.SMS_TWILIO_NUMBER, null);

		return new TwilioSMSSender(accountSID, authToken, number);
	}

	public String getAccountSID() {
		if (Validator.isNull(_accountSID)) {
			_accountSID = PrefsPropsUtil.getString(
				PortletPropsKeys.SMS_TWILIO_ACCOUNT_SID,
				PortletPropsValues.SMS_TWILIO_ACCOUNT_SID);
		}

		return _accountSID;
	}

	public String getAuthToken() {
		if (Validator.isNull(_authToken)) {
			_authToken = PrefsPropsUtil.getString(
				PortletPropsKeys.SMS_TWILIO_AUTH_TOKEN,
				PortletPropsValues.SMS_TWILIO_AUTH_TOKEN);
		}

		return _authToken;
	}

	public String getNumber() {
		if (Validator.isNull(_number)) {
			_number = PrefsPropsUtil.getString(
				PortletPropsKeys.SMS_TWILIO_NUMBER,
				PortletPropsValues.SMS_TWILIO_NUMBER);
		}

		return _number;
	}

	@Override
	public synchronized void reset() {
		_accountSID = null;
		_authToken = null;
		_number = null;
		_twilioRestClient = null;
	}

	@Override
	public void send(
			String platform, List<String> phoneNumbers,
			JSONObject payloadJSONObject)
		throws Exception {

		TwilioRestClient twilioRestClient = getTwilioRestClient();

		if (twilioRestClient == null) {
			return;
		}

		Account account = twilioRestClient.getAccount();

		SmsFactory smsFactory = account.getSmsFactory();

		String body = payloadJSONObject.getString(
			PushNotificationsConstants.KEY_BODY);

		String from = payloadJSONObject.getString(
			PushNotificationsConstants.KEY_FROM);

		if (Validator.isNull(from)) {
			from = getNumber();
		}

		for (String phoneNumber : phoneNumbers) {
			Map<String, String> params = new HashMap<>();

			params.put("To", phoneNumber);
			params.put("From", from);
			params.put("Body", body);

			smsFactory.create(params);
		}
	}

	public void setAccountSID(String accountSID) {
		_accountSID = accountSID;
	}

	public void setAuthToken(String authToken) {
		_authToken = authToken;
	}

	public void setNumber(String number) {
		_number = number;
	}

	protected synchronized TwilioRestClient getTwilioRestClient()
		throws Exception {

		if (_twilioRestClient == null) {
			String accountSID = getAccountSID();

			if (Validator.isNull(accountSID)) {
				throw new PushNotificationsException(
					"The property \"sms.twilio.account.sid\" is not set in " +
						"portlet.properties");
			}

			String authToken = getAuthToken();

			if (Validator.isNull(authToken)) {
				throw new PushNotificationsException(
					"The property \"sms.twilio.auth.token\" is not set in " +
						"portlet.properties");
			}

			_twilioRestClient = new TwilioRestClient(accountSID, authToken);
		}

		return _twilioRestClient;
	}

	private String _accountSID;
	private String _authToken;
	private String _number;
	private TwilioRestClient _twilioRestClient;

}