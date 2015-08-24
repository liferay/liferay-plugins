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

package com.liferay.asset.entry.set.util;

import com.liferay.portal.kernel.util.StringPool;

import java.util.List;

import org.geonames.Toponym;
import org.geonames.WebService;

/**
 * @author Matthew Kong
 */
public class GeoNamesUtil {

	public static String getLocationName(double latitude, double longitude) {
		try {
			WebService webService = getWebService();

			List<Toponym> toponyms = webService.findNearbyPlaceName(
				latitude, longitude);

			if (toponyms.isEmpty()) {
				return StringPool.BLANK;
			}

			Toponym toponym = toponyms.get(0);

			return toponym.getName() + StringPool.COMMA_AND_SPACE +
				toponym.getCountryName();
		}
		catch (Exception e) {
			return StringPool.BLANK;
		}
	}

	protected static WebService getWebService() {
		if (_webService == null) {
			_webService = new WebService();

			_webService.setUserName(PortletPropsValues.GEONAMES_USERNAME);
		}

		return _webService;
	}

	private static WebService _webService;

}