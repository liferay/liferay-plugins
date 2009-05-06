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
 * <a href="WeatherWebCacheItem.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
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

			String iconURL =
				"http://www.google.com" + iconEl.attributeValue("data");

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