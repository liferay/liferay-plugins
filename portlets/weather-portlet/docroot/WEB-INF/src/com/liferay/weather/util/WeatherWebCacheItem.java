/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.weather.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.webcache.WebCacheException;
import com.liferay.portal.kernel.webcache.WebCacheItem;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.weather.model.Weather;

/**
 * @author Brian Wing Shun Chan
 * @author Samuel Kong
 */
public class WeatherWebCacheItem implements WebCacheItem {

	public WeatherWebCacheItem(String apiKey, String zip) {
		_apiKey = apiKey;

		_zip = zip;

		if (_zip.equals("Frankfurt/Main")) {
			_zip = "Frankfurt, Germany";
		}
	}

	public Object convert(String key) throws WebCacheException {
		Weather weather = null;

		try {
			weather = doConvert(key);
		}
		catch (Exception e) {
			throw new WebCacheException(_zip);
		}

		return weather;
	}

	public long getRefreshTime() {
		return _REFRESH_TIME;
	}

	protected Weather doConvert(String key) throws Exception {
		StringBundler sb = new StringBundler(5);

		sb.append("http://api.worldweatheronline.com/free/v1/weather.ashx?key=");
		sb.append(_apiKey);
		sb.append("&q=");
		sb.append(HttpUtil.encodeURL(_zip));
		sb.append("&format=xml");

		String xml = HttpUtil.URLtoString(sb.toString());

		Document document = SAXReaderUtil.read(xml);

		Element rootElement = document.getRootElement();

		Element currentConditionElement = rootElement.element(
			"current_condition");

		Element temperatureElement = currentConditionElement.element("temp_F");

		float temperature = GetterUtil.getFloat(temperatureElement.getData());

		Element iconElement = currentConditionElement.element("weatherIconUrl");

		String iconURL = iconElement.getText();

		return new Weather(_zip, iconURL, temperature);
	}

	private static final long _REFRESH_TIME = Time.MINUTE * 60;

	private String _apiKey;
	private String _zip;

}