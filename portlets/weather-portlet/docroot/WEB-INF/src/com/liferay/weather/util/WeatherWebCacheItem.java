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

package com.liferay.weather.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.webcache.WebCacheException;
import com.liferay.portal.kernel.webcache.WebCacheItem;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.weather.model.Weather;

/**
 * @author Brian Wing Shun Chan
 */
public class WeatherWebCacheItem implements WebCacheItem {

	public WeatherWebCacheItem(String zip) {
		_zip = zip;

		if (_zip.equals("Frankfurt/Main")) {
			_zip = "Frankfurt, Germany";
		}
	}

	public Object convert(String key) throws WebCacheException {
		Weather weather = null;

		try {
			String xml = HttpUtil.URLtoString(
				"http://www.google.com/ig/api?weather=" +
					HttpUtil.encodeURL(_zip));

			Document doc = SAXReaderUtil.read(xml);

			Element root = doc.getRootElement();

			Element weatherEl = root.element("weather");

			Element currentConditionsEl = weatherEl.element(
				"current_conditions");

			Element temperatureEl = currentConditionsEl.element("temp_f");

			float temperature = GetterUtil.getFloat(
				temperatureEl.attributeValue("data"));

			Element iconEl = currentConditionsEl.element("icon");

			String iconURL = "//www.google.com" + iconEl.attributeValue("data");

			weather = new Weather(_zip, iconURL, temperature);
		}
		catch (Exception e) {
			throw new WebCacheException(_zip);
		}

		return weather;
	}

	public long getRefreshTime() {
		return _REFRESH_TIME;
	}

	private static final long _REFRESH_TIME = Time.MINUTE * 20;

	private String _zip;

}