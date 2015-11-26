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

package com.liferay.weather.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.webcache.WebCacheException;
import com.liferay.portal.kernel.webcache.WebCacheItem;
import com.liferay.portal.kernel.xml.Attribute;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.weather.model.Weather;

/**
 * @author Brian Wing Shun Chan
 * @author Samuel Kong
 * @author Preston Crary
 */
public class WeatherWebCacheItem implements WebCacheItem {

	public WeatherWebCacheItem(String zip, String apiKey) {
		_zip = zip;

		if (_zip.equals("Frankfurt/Main")) {
			_zip = "Frankfurt, Germany";
		}

		_apiKey = apiKey;
	}

	@Override
	public Object convert(String key) throws WebCacheException {
		Weather weather = null;

		try {
			weather = doConvert();
		}
		catch (Exception e) {
			throw new WebCacheException(_zip);
		}

		return weather;
	}

	@Override
	public long getRefreshTime() {
		return _REFRESH_TIME;
	}

	protected Weather doConvert() throws Exception {
		String xml = HttpUtil.URLtoString(
			"http://api.openweathermap.org/data/2.5/weather?q=" +
				HttpUtil.encodeURL(_zip) + "&units=imperial&mode=xml&APPID=" +
					_apiKey);

		Document document = SAXReaderUtil.read(xml);

		Element rootElement = document.getRootElement();

		Element cityElement = rootElement.element("city");

		Attribute cityIdAttribute = cityElement.attribute("id");

		String cityId = cityIdAttribute.getText();

		Element temperatureElement = rootElement.element("temperature");

		Attribute temperatureAttribute = temperatureElement.attribute("value");

		float temperature = GetterUtil.getFloat(temperatureAttribute.getData());

		Element weatherElement = rootElement.element("weather");

		Attribute iconAttribute = weatherElement.attribute("icon");

		String iconURL =
			"http://openweathermap.org/img/w/" + iconAttribute.getText() +
				".png";

		return new Weather(_zip, cityId, iconURL, temperature);
	}

	private static final long _REFRESH_TIME = Time.MINUTE * 60;

	private String _apiKey;
	private String _zip;

}