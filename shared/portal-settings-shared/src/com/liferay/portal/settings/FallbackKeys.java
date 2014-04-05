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

package com.liferay.portal.settings;

import com.liferay.portal.kernel.util.StringPool;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Iván Zaera
 */
public class FallbackKeys {

	public void add(String key, String... fallbackKeysArray) {
		if (_fallbackKeysMap.containsKey(key)) {
			throw new IllegalArgumentException("Duplicate key " + key);
		}

		_fallbackKeysMap.put(key, fallbackKeysArray);
	}

	public String[] get(String key) {
		String[] fallbackKeysArray = _fallbackKeysMap.get(key);

		if (fallbackKeysArray == null) {
			return StringPool.EMPTY_ARRAY;
		}

		return fallbackKeysArray;
	}

	private Map<String, String[]> _fallbackKeysMap =
		new HashMap<String, String[]>();

}