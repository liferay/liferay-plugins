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

package com.liferay.ipgeocoder.model;

import com.maxmind.geoip.Location;

/**
 * @author Brian Wing Shun Chan
 */
public class IPInfo {

	public IPInfo(String ipAddress, Location location) {
		_ipAddress = ipAddress;
		_location = location;
	}

	public String getCity() {
		if (_location == null) {
			return null;
		}
		else {
			return _location.city;
		}
	}

	public String getCountryCode() {
		if (_location == null) {
			return null;
		}
		else {
			return _location.countryCode;
		}
	}

	public String getCountryName() {
		if (_location == null) {
			return null;
		}
		else {
			return _location.countryName;
		}
	}

	public String getIpAddress() {
		return _ipAddress;
	}

	public float getLatitude() {
		if (_location == null) {
			return 0;
		}
		else {
			return _location.latitude;
		}
	}

	public float getLongitude() {
		if (_location == null) {
			return 0;
		}
		else {
			return _location.longitude;
		}
	}

	public String getPostalCode() {
		if (_location == null) {
			return null;
		}
		else {
			return _location.postalCode;
		}
	}

	public String getRegion() {
		if (_location == null) {
			return null;
		}
		else {
			return _location.region;
		}
	}

	private String _ipAddress;
	private Location _location;

}