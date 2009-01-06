/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.weather.model;

import java.io.Serializable;

/**
 * <a href="Weather.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
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