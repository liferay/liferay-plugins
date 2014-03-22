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

package com.liferay.portal.settings;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Iván Zaera
 */
public class FallbackPaths {

	public void addPath(String key, String... pathKeys) {
		if (_pathKeysMap.containsKey(key)) {
			throw new IllegalArgumentException("Duplicate key " + key);
		}

		_pathKeysMap.put(key, Arrays.asList(pathKeys));
	}

	public List<String> getPathKeys(String key) {
		List<String> pathKeys = _pathKeysMap.get(key);

		if (pathKeys == null) {
			return Collections.emptyList();
		}

		return pathKeys;
	}

	private Map<String, List<String>> _pathKeysMap =
		new HashMap<String, List<String>>();

}