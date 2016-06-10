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

package com.liferay.geonames.util;

/**
 * @author Matthew Kong
 */
public class GeoName {

	public GeoName(double latitude, double longitude) {
		setCoordinates(latitude, longitude);
	}

	public GeoName(String[] data) {
		_countryCode = data[8];
		_name = data[1];

		double latitude = Double.parseDouble(data[4]);
		double longitude = Double.parseDouble(data[5]);

		setCoordinates(latitude, longitude);
	}

	public String getCountryCode() {
		return _countryCode;
	}

	public String getName() {
		return _name;
	}

	public double getX() {
		return _x;
	}

	public double getY() {
		return _y;
	}

	public double getZ() {
		return _z;
	}

	protected void setCoordinates(double latitude, double longitude) {
		_x =
			Math.cos(Math.toRadians(latitude)) *
				Math.cos(Math.toRadians(longitude));
		_y =
			Math.cos(Math.toRadians(latitude)) *
				Math.sin(Math.toRadians(longitude));
		_z = Math.sin(Math.toRadians(latitude));
	}

	private String _countryCode;
	private String _name;
	private double _x;
	private double _y;
	private double _z;

}