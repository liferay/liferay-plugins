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

package com.liferay.sync.engine.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Shinn Lok
 */
public class BidirectionalMap<K, V> extends HashMap<K, V> {

	@Override
	public void clear() {
		_invertedMap.clear();

		super.clear();
	}

	public K getKey(Object value) {
		K key = _invertedMap.get(value);

		return key;
	}

	@Override
	public V put(K key, V value) {
		_invertedMap.put(value, key);

		return super.put(key, value);
	}

	public K removeValue(Object value) {
		K key = _invertedMap.remove(value);

		if (key != null) {
			super.remove(key);
		}

		return key;
	}

	private Map<V, K> _invertedMap = new HashMap<V, K>();

}