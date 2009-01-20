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

package com.liferay.ipgeocoder.util;

import com.liferay.ipgeocoder.model.IPInfo;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.util.portlet.PortletProps;

import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;

import java.io.IOException;

/**
 * <a href="IPGeocoderUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
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