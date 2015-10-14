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

package com.liferay.pushnotifications.sender.apple;

import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.pushnotifications.messaging.DestinationNames;

import com.notnoop.apns.ApnsDelegate;
import com.notnoop.apns.ApnsNotification;
import com.notnoop.apns.DeliveryError;

/**
 * @author Bruno Farache
 */
public class AppleDelegate implements ApnsDelegate {

	@Override
	public void cacheLengthExceeded(int newCacheLength) {
	}

	@Override
	public void connectionClosed(DeliveryError deliveryError, int identifier) {
		sendResponse(new AppleResponse(identifier, deliveryError));
	}

	@Override
	public void messageSendFailed(
		ApnsNotification notification, Throwable throwable) {

		sendResponse(new AppleResponse(notification, throwable));
	}

	@Override
	public void messageSent(ApnsNotification notification, boolean resent) {
		sendResponse(new AppleResponse(notification, resent));
	}

	@Override
	public void notificationsResent(int resendCount) {
	}

	protected void sendResponse(AppleResponse appleResponse) {
		MessageBusUtil.sendMessage(
			DestinationNames.PUSH_NOTIFICATION_RESPONSE, appleResponse);
	}

}