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

package com.liferay.ipgeocoder.util;

import com.liferay.ipgeocoder.model.IPInfo;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.util.portlet.PortletProps;

import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;

import java.io.IOException;

/**
 * @author Brian Wing Shun Chan
 */
public class IPGeocoderUtil {

	public static IPInfo getIPInfo(String ipAddress) {
		return _instance._getIPInfo(ipAddress);
	}

	private IPGeocoderUtil() {
		_init();
	}

	private void _init() {
		try {
			if (_lookupService == null) {
				_lookupService = new LookupService(
					PortletProps.get("maxmind.database.file"),
					LookupService.GEOIP_MEMORY_CACHE);
			}
		}
		catch (IOException ioe) {
			_log.error(ioe.getMessage());
		}
	}

	private IPInfo _getIPInfo(String ipAddress) {
		_init();

		if (_lookupService != null) {
			Location location = _lookupService.getLocation(ipAddress);

			return new IPInfo(ipAddress, location);
		}
		else {
			return null;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(IPGeocoderUtil.class);

	private static IPGeocoderUtil _instance = new IPGeocoderUtil();

	private LookupService _lookupService;

}