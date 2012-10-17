/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.client.json.ipgeocoder.model;

/**
 * @author Brian Wing Shun Chan
 */
public class IPInfo {

	public IPInfo(String ipAddress, float latitude, float longitude) {
		_ipAddress = ipAddress;
		_latitude = latitude;
		_longitude = longitude;
	}

	public String getIpAddress() {
		return _ipAddress;
	}

	public float getLatitude() {
		return _latitude;
	}

	public float getLongitude() {
		return _longitude;
	}

	private String _ipAddress;
	private float _latitude;
	private float _longitude;

}