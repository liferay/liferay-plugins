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

package com.liferay.geonames.util;

import java.util.Comparator;

/**
 * @author Matthew Kong
 */
public class GeoNameZComparator implements Comparator<GeoName> {

	@Override
	public int compare(GeoName geoName1, GeoName geoName2) {
		return Double.compare(geoName1.getZ(), geoName2.getZ());
	}

}