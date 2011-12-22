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

package com.liferay.weather.model;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 */
public class Weather implements Serializable {

	public Weather() {
	}

	public Weather(String zip, float currentTemp) {
		this(zip, null, currentTemp);
	}

	public Weather(String zip, String iconURL, float currentTemp) {
		this(zip, iconURL, null, currentTemp, (float)0.0, (float)0.0, null);
	}

	public Weather(
		String zip, String iconURL, String conditions, float currentTemp,
		float humidity, float barometer, String barometerDirection) {

		_zip = zip;
		_iconURL = iconURL;
		_conditions = conditions;
		_currentTemp = currentTemp;
		_humidity = humidity;
		_barometer = barometer;
		_barometerDirection = barometerDirection;
	}

	public String getZip() {
		return _zip;
	}

	public void setZip(String zip) {
		_zip = zip;
	}

	public String getIconURL() {
		return _iconURL;
	}

	public void setIconURL(String iconURL) {
		_iconURL = iconURL;
	}

	public String getConditions() {
		return _conditions;
	}

	public void setConditions(String conditions) {
		_conditions = conditions;
	}

	public float getCurrentTemp() {
		return _currentTemp;
	}

	public void setCurrentTemp(float currentTemp) {
		_currentTemp = currentTemp;
	}

	public float getHumidity() {
		return _humidity;
	}

	public void setHumidity(float humidity) {
		_humidity = humidity;
	}

	public float getBarometer() {
		return _barometer;
	}

	public void setBarometer(float barometer) {
		_barometer = barometer;
	}

	public String getBarometerDirection() {
		return _barometerDirection;
	}

	public void setBarometerDirection(String barometerDirection) {
		_barometerDirection = barometerDirection;
	}

	private String _zip;
	private String _iconURL;
	private String _conditions;
	private float _currentTemp;
	private float _humidity;
	private float _barometer;
	private String _barometerDirection;

}