/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.search.solr;

import java.util.Comparator;
import java.util.Map;

/**
 * @author Daniela Zapata Riesco
 * @author David Gonzalez
 */
public class MapValueComparator implements Comparator<String> {

	public MapValueComparator(Map<String, Float> baseValues) {
		_baseValues = baseValues;
	}

	@Override
	public int compare(String string1, String string2) {
		Float baseValue1 = _baseValues.get(string1);

		Float baseValue2 = _baseValues.get(string2);

		if (baseValue1 == null) {
			baseValue1 = 0f;
		}

		if (baseValue2 == null) {
			baseValue2 = 0f;
		}

		if (baseValue1 >= baseValue2) {
			return -1;
		}
		else {
			return 1;
		}
	}

	private Map<String, Float> _baseValues;

}