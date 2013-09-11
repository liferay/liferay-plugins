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

package com.liferay.compat.portal.kernel.util;

import java.util.Locale;

/**
 * @author Brian Wing Shun Chan
 */
public class StringUtil extends com.liferay.portal.kernel.util.StringUtil {

	public static boolean equalsIgnoreCase(String s1, String s2) {
		if (s1 == s2) {
			return true;
		}

		if ((s1 == null) || (s2 == null)) {
			return false;
		}

		if (s1.length() != s2.length()) {
			return false;
		}

		for (int i = 0; i < s1.length(); i++) {
			char c1 = s1.charAt(i);

			char c2 = s2.charAt(i);

			if (c1 == c2) {
				continue;
			}

			if ((c1 > 127) || (c2 > 127)) {
				return s1.equalsIgnoreCase(s2);
			}

			int delta = c1 - c2;

			if ((delta != 32) && (delta != -32)) {
				return false;
			}
		}

		return true;
	}

	public static String toLowerCase(String s) {
		return toLowerCase(s, null);
	}

	public static String toLowerCase(String s, Locale locale) {
		StringBuilder sb = null;

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c > 127) {

				// Found non-ascii char, fallback to the slow unicode detection

				if (locale == null) {
					locale = Locale.getDefault();
				}

				return s.toLowerCase(locale);
			}

			if ((c >= 'A') && (c <= 'Z')) {
				if (sb == null) {
					sb = new StringBuilder(s);
				}

				sb.setCharAt(i, (char)(c + 32));
			}
		}

		if (sb == null) {
			return s;
		}

		return sb.toString();
	}

	public static String toUpperCase(String s) {
		return toUpperCase(s, null);
	}

	public static String toUpperCase(String s, Locale locale) {
		StringBuilder sb = null;

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c > 127) {

				// Found non-ascii char, fallback to the slow unicode detection

				if (locale == null) {
					locale = Locale.getDefault();
				}

				return s.toLowerCase(locale);
			}

			if ((c >= 'a') && (c <= 'z')) {
				if (sb == null) {
					sb = new StringBuilder(s);
				}

				sb.setCharAt(i, (char)(c - 32));
			}
		}

		if (sb == null) {
			return s;
		}

		return sb.toString();
	}

}