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

package com.liferay.compat.portal.kernel.util;

import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.Locale;

/**
 * @author Brian Wing Shun Chan
 */
public class StringUtil extends com.liferay.portal.kernel.util.StringUtil {

	public static String toLowerCase(String s) {
		return toLowerCase(s, null);
	}

	public static String toLowerCase(String s, Locale locale) {
		if (s == null) {
			return null;
		}

		StringBuilder sb = null;

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c > 127) {

				// Found non-ascii char, fallback to the slow unicode detection

				if (locale == null) {
					locale = LocaleUtil.getDefault();
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
		if (s == null) {
			return null;
		}

		StringBuilder sb = null;

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c > 127) {

				// Found non-ascii char, fallback to the slow unicode detection

				if (locale == null) {
					locale = LocaleUtil.getDefault();
				}

				return s.toUpperCase(locale);
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

	public static boolean wildcardMatches(
		String s, String wildcard, char singleWildcardCharacter,
		char multipleWildcardCharacter, char escapeWildcardCharacter,
		boolean caseSensitive) {

		if (!caseSensitive) {
			s = toLowerCase(s);
			wildcard = toLowerCase(wildcard);
		}

		// Update the wildcard, single whildcard character, and multiple
		// wildcard character so that they no longer have escaped wildcard
		// characters

		int index = wildcard.indexOf(escapeWildcardCharacter);

		if (index != -1) {

			// Search for safe wildcard replacement

			char newSingleWildcardCharacter = 0;

			while (wildcard.indexOf(newSingleWildcardCharacter) != -1) {
				newSingleWildcardCharacter++;
			}

			char newMultipleWildcardCharacter =
				(char)(newSingleWildcardCharacter + 1);

			while (wildcard.indexOf(newMultipleWildcardCharacter) != -1) {
				newMultipleWildcardCharacter++;
			}

			// Purify

			StringBuilder sb = new StringBuilder(wildcard);

			for (int i = 0; i < sb.length(); i++) {
				char c = sb.charAt(i);

				if (c == escapeWildcardCharacter) {
					sb.deleteCharAt(i);
				}
				else if (c == singleWildcardCharacter) {
					sb.setCharAt(i, newSingleWildcardCharacter);
				}
				else if (c == multipleWildcardCharacter) {
					sb.setCharAt(i, newMultipleWildcardCharacter);
				}
			}

			wildcard = sb.toString();

			singleWildcardCharacter = newSingleWildcardCharacter;
			multipleWildcardCharacter = newMultipleWildcardCharacter;
		}

		// Align head

		for (index = 0; index < s.length(); index++) {
			if (index >= wildcard.length()) {
				return false;
			}

			char c = wildcard.charAt(index);

			if (c == multipleWildcardCharacter) {
				break;
			}

			if ((s.charAt(index) != c) && (c != singleWildcardCharacter)) {
				return false;
			}
		}

		// Match body

		int sIndex = index;
		int wildcardIndex = index;

		int matchPoint = 0;
		int comparePoint = 0;

		while (sIndex < s.length()) {
			if (wildcardIndex == wildcard.length()) {

				// Wildcard exhausted before s

				return false;
			}

			char c = wildcard.charAt(wildcardIndex);

			if (c == multipleWildcardCharacter) {
				if (++wildcardIndex == wildcard.length()) {
					return true;
				}

				matchPoint = wildcardIndex;
				comparePoint = sIndex + 1;
			}
			else if ((c == s.charAt(sIndex)) ||
					 (c == singleWildcardCharacter)) {

				sIndex++;
				wildcardIndex++;
			}
			else {
				wildcardIndex = matchPoint;
				sIndex = comparePoint++;
			}
		}

		// Match tail

		while (wildcardIndex < wildcard.length()) {
			if (wildcard.charAt(wildcardIndex) != multipleWildcardCharacter) {
				break;
			}

			wildcardIndex++;
		}

		if (wildcardIndex == wildcard.length()) {
			return true;
		}
		else {
			return false;
		}
	}

}