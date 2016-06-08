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
public class GeonameYComparator implements Comparator<Geoname> {

	@Override
	public int compare(Geoname geoname1, Geoname geoname2) {
		return Double.compare(geoname1.getY(), geoname2.getY());
	}

}