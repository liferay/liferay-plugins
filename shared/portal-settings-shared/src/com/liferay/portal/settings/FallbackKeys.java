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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Iván Zaera
 */
public class FallbackKeys {

	public void addPath(String firstKey, String... pathKeys) {
		if (_paths.containsKey(firstKey)) {
			throw new IllegalArgumentException(
				"A path with first key '" + firstKey + "' already exists");
		}

		List<String> path = new ArrayList<String>();

		Collections.addAll(path, pathKeys);

		_paths.put(firstKey, path);
	}

	public List<String> getPath(String firstKey) {
		List<String> path = _paths.get(firstKey);

		if (path == null) {
			return Collections.emptyList();
		}

		return path;
	}

	private Map<String, List<String>> _paths =
		new HashMap<String, List<String>>();

}