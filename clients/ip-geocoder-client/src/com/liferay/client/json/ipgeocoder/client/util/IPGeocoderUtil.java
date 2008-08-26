/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

package com.liferay.client.json.ipgeocoder.util;

import com.liferay.client.json.ipgeocoder.model.IPInfo;
import com.liferay.portal.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.util.GetterUtil;

/**
 * <a href="IPGeocoderUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class IPGeocoderUtil {

	public static IPInfo getIPInfo(String ipAddress) throws PortalException {
		JSONObject ipGeocoderRequestJSON = JSONFactoryUtil.createJSONObject();

		ipGeocoderRequestJSON.put("ipAddress", ipAddress);

		String ipGeocoderResponse = MessageBusUtil.sendSynchronizedMessage(
			DestinationNames.IP_GEOCODER, ipGeocoderRequestJSON.toString());

		if (ipGeocoderResponse == null) {
			return null;
		}

		JSONObject ipGeocoderResponseJSON = JSONFactoryUtil.createJSONObject(
			ipGeocoderResponse);

		JSONObject ipInfoJSON = ipGeocoderResponseJSON.getJSONObject("ipInfo");

		if (ipInfoJSON == null) {
			return null;
		}

		float latitude = GetterUtil.getFloat(ipInfoJSON.getString("latitude"));
		float longitude = GetterUtil.getFloat(
			ipInfoJSON.getString("longitude"));

		if ((latitude == 0) && (longitude == 0)) {
			return null;
		}

		return new IPInfo(ipAddress, latitude, longitude);
	}

}