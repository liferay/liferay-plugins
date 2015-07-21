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

package com.liferay.shortlink.util;

/**
 * @author Miroslav Ligas
 */
public class ShortURLUtil {

	public static long decode(String encodedId) {
		long id = 0;

		for (int i = 0; i < encodedId.length(); i++) {
			char c = encodedId.charAt(i);

			int index = _ALPHABET.indexOf(c);

			id = (id * _ALPHABET.length()) + index;
		}

		return id;
	}

	public static String encode(long id) {
		StringBuilder sb = new StringBuilder();

		while (id > 0) {
			int index = (int)(id % _ALPHABET.length());

			sb.append(_ALPHABET.charAt(index));

			id = id / _ALPHABET.length();
		}

		sb.reverse();

		return sb.toString();
	}

	private static final String _ALPHABET =
		"1234567890bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ-_";

}