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

package com.liferay.localization.zh.util;

import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.io.InputStream;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 * @author Samuel Kong
 */
public class LocalizationZHUtil {

	public static String getSimplifiedText(String traditionalText) {
		return _instance._getSimplifiedText(traditionalText);
	}

	public static String getTraditionalText(String simplifiedText) {
		return _instance._getTraditionalText(simplifiedText);
	}

	public static boolean isCJKUnifiedIdeographString(String s) {
		if (Validator.isNull(s)) {
			return false;
		}

		for (int i = 0; i < s.length(); i++) {
			int codePoint = s.codePointAt(i);

			if ((codePoint >= _CJK_UNIFIED_IDEOGRAPHS_START) &&
				(codePoint <= _CJK_UNIFIED_IDEOGRAPHS_END)) {

				return true;
			}

			if ((codePoint >= _CJK_UNIFIED_IDEOGRAPHS_EXT_A_START) &&
				(codePoint <= _CJK_UNIFIED_IDEOGRAPHS_EXT_A_END)) {

				return true;
			}

			if ((codePoint >= _CJK_UNIFIED_IDEOGRAPHS_EXT_B_START) &&
				(codePoint <= _CJK_UNIFIED_IDEOGRAPHS_EXT_B_END)) {

				return true;
			}
		}

		return false;
	}

	private LocalizationZHUtil() {
		try {
			Class<?> clazz = getClass();

			InputStream inputStream = clazz.getResourceAsStream(
				"dependencies/zh_CN-to-zh_TW.txt");

			String[] lines = StringUtil.split(
				StringUtil.read(inputStream), StringPool.NEW_LINE);

			for (String line : lines) {
				char simplifiedChar = line.charAt(0);
				char traditionalChar = line.charAt(2);

				_simplifiedCharactersToTraditionalCharactersMap.put(
					simplifiedChar, traditionalChar);
				_traditionalCharactersToSimplifiedCharactersMap.put(
					traditionalChar, simplifiedChar);
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private String _getSimplifiedText(String traditionalText) {
		return _translateText(
			_traditionalCharactersToSimplifiedCharactersMap, traditionalText);
	}

	private String _getTraditionalText(String simplifiedText) {
		return _translateText(
			_simplifiedCharactersToTraditionalCharactersMap, simplifiedText);
	}

	private String _translateText(Map<Character, Character> map, String text) {
		StringBundler sb = new StringBundler(text.length());

		char[] chars = text.toCharArray();

		for (char c : chars) {
			Character translatedCharacter = map.get(c);

			if (translatedCharacter == null) {
				sb.append(c);
			}
			else {
				sb.append(translatedCharacter);
			}
		}

		return sb.toString();
	}

	private static final int _CJK_UNIFIED_IDEOGRAPHS_END = 40959;

	private static final int _CJK_UNIFIED_IDEOGRAPHS_EXT_A_END = 19903;

	private static final int _CJK_UNIFIED_IDEOGRAPHS_EXT_A_START = 13312;

	private static final int _CJK_UNIFIED_IDEOGRAPHS_EXT_B_END = 173791;

	private static final int _CJK_UNIFIED_IDEOGRAPHS_EXT_B_START = 131072;

	private static final int _CJK_UNIFIED_IDEOGRAPHS_START = 19968;

	private static Log _log = LogFactoryUtil.getLog(LocalizationZHUtil.class);

	private static LocalizationZHUtil _instance = new LocalizationZHUtil();

	private Map<Character, Character>
		_simplifiedCharactersToTraditionalCharactersMap =
			new HashMap<Character, Character>();
	private Map<Character, Character>
		_traditionalCharactersToSimplifiedCharactersMap =
			new HashMap<Character, Character>();

}