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

package com.liferay.client.json.ipgeocoder.util;

import com.liferay.client.json.ipgeocoder.model.IPInfo;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class IPGeocoderUtil {

	public static IPInfo getIPInfo(String ipAddress) throws PortalException {
		Object response = MessageBusUtil.sendSynchronousMessage(
			DestinationNames.IP_GEOCODER, ipAddress);

		if (!(response instanceof JSONObject)) {
			return null;
		}

		JSONObject ipInfoJSON = (JSONObject)response;

		if (ipInfoJSON == null) {
			return null;
		}

		float latitude = GetterUtil.getFloat(ipInfoJSON.getString("latitude"));
		float longitude = GetterUtil.getFloat(
			ipInfoJSON.getString("longitude"));

		return new IPInfo(ipAddress, latitude, longitude);
	}

}