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

import com.liferay.pushnotifications.sender.BaseResponse;

import com.notnoop.apns.ApnsNotification;
import com.notnoop.apns.DeliveryError;

/**
 * @author Bruno Farache
 */
public class AppleResponse extends BaseResponse {

	public AppleResponse(ApnsNotification notification, boolean resent) {
		this(notification);
		this.resent = resent;
		succeeded = true;
	}

	public AppleResponse(ApnsNotification notification, Throwable throwable) {
		this(notification);
		status = throwable.getMessage();
	}

	public AppleResponse(int identifier, DeliveryError deliveryError) {
		id = String.valueOf(identifier);
		status = deliveryError.name();
	}

	public int getExpiry() {
		return expiry;
	}

	public boolean isResent() {
		return resent;
	}

	protected int expiry;
	protected boolean resent;

	private AppleResponse(ApnsNotification notification) {
		if (notification != null) {
			expiry = notification.getExpiry();
			id = String.valueOf(notification.getIdentifier());
			token = _getHexadecimalString(notification.getDeviceToken());
		}
	}

	private String _getHexadecimalString(byte[] token) {
		StringBuilder sb = new StringBuilder();

		for (byte b : token) {
			sb.append(String.format("%02X", b));
		}

		return sb.toString();
	}

}