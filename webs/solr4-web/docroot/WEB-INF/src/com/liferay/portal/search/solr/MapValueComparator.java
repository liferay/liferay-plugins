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

	public MapValueComparator(Map<String, Float> values) {
		_values = values;
	}

	@Override
	public int compare(String string1, String string2) {
		Float value1 = _values.get(string1);

		if (value1 == null) {
			value1 = 0f;
		}

		Float value2 = _values.get(string2);

		if (value2 == null) {
			value2 = 0f;
		}

		if (value1 >= value2) {
			return -1;
		}
		else {
			return 1;
		}
	}

	private Map<String, Float> _values;

}