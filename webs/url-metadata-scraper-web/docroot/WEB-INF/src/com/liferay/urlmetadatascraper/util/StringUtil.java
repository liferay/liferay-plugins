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
package com.liferay.urlmetadatascraper.util;

/**
 * @author Evan Thibodeau
 */
public class StringUtil {

	/**
	 * Returns a string representing the original string appended with suffix
	 * "..." and then shortened to the specified length.
	 *
	 * <p>
	 * The suffix is only added if the original string exceeds the specified
	 * length. If the original string exceeds the specified length and it
	 * contains whitespace, the string is shortened at the first whitespace
	 * character.
	 * </p>
	 *
	 * <p>
	 * Examples:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * shorten("123456789", 8) returns "12345..."
	 * shorten("1 3456789", 8) returns "1..."
	 * shorten(" 23456789", 8) returns "..."
	 * shorten("12345678", 8) returns "12345678"
	 * shorten(" 1234567", 8) returns " 1234567"
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  s the original string
	 * @param  length the number of characters to limit from the original string
	 * @return a string representing the original string shortened to the
	 *         specified length, with suffix "..." appended to it
	 */
	public static String shorten(String s, int length) {
		return shorten(s, length, "...");
	}

	/**
	 * Returns a string representing the original string appended with the
	 * specified suffix and then shortened to the specified length.
	 *
	 * <p>
	 * The suffix is only added if the original string exceeds the specified
	 * length. If the original string exceeds the specified length and it
	 * contains whitespace, the string is shortened at the first whitespace
	 * character.
	 * </p>
	 *
	 * <p>
	 * Examples:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * shorten("12345678901234", 13, "... etc.") returns "12345... etc."
	 * shorten("1 345678901234", 13, "... etc.") returns "1... etc."
	 * shorten(" 2345678901234", 13, "... etc.") returns "... etc."
	 * shorten("1234567890123", 13, "... etc.") returns "1234567890123"
	 * shorten(" 123456789012", 13, "... etc.") returns " 123456789012"
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  s the original string
	 * @param  length the number of characters to limit from the original string
	 * @param  suffix the suffix to append
	 * @return a string representing the original string shortened to the
	 *         specified length, with the specified suffix appended to it
	 */
	public static String shorten(String s, int length, String suffix) {
		if ((s == null) || (suffix == null)) {
			return null;
		}

		if (s.length() <= length) {
			return s;
		}

		if (length < suffix.length()) {
			return s.substring(0, length);
		}

		int curLength = length;

		for (int j = (curLength - suffix.length()); j >= 0; j--) {
			if (Character.isWhitespace(s.charAt(j))) {
				curLength = j;

				break;
			}
		}

		if (curLength == length) {
			curLength = length - suffix.length();
		}

		String temp = s.substring(0, curLength);

		return temp.concat(suffix);
	}

}