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

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * @author Shinn Lok
 */
public class LocaleUtil {

	public static final Locale BRAZIL = new Locale("pt", "BR");

	public static final Locale CANADA = Locale.CANADA;

	public static final Locale CANADA_FRENCH = Locale.CANADA_FRENCH;

	public static final Locale CHINA = Locale.CHINA;

	public static final Locale CHINESE = Locale.CHINESE;

	public static final Locale ENGLISH = Locale.ENGLISH;

	public static final Locale FRANCE = Locale.FRANCE;

	public static final Locale FRENCH = Locale.FRENCH;

	public static final Locale GERMAN = Locale.GERMAN;

	public static final Locale GERMANY = Locale.GERMANY;

	public static final Locale HUNGARY = new Locale("hu", "HU");

	public static final Locale ITALIAN = Locale.ITALIAN;

	public static final Locale ITALY = Locale.ITALY;

	public static final Locale JAPAN = Locale.JAPAN;

	public static final Locale JAPANESE = Locale.JAPANESE;

	public static final Locale KOREA = Locale.KOREA;

	public static final Locale KOREAN = Locale.KOREAN;

	public static final Locale NETHERLANDS = new Locale("nl", "NL");

	public static final Locale PORTUGAL = new Locale("pt", "PT");

	public static final Locale PRC = Locale.PRC;

	public static final Locale ROOT = Locale.ROOT;

	public static final Locale SIMPLIFIED_CHINESE = Locale.SIMPLIFIED_CHINESE;

	public static final Locale SPAIN = new Locale("es", "ES");

	public static final Locale TAIWAN = Locale.TAIWAN;

	public static final Locale TRADITIONAL_CHINESE = Locale.TRADITIONAL_CHINESE;

	public static final Locale UK = Locale.UK;

	public static final Locale US = Locale.US;

	public static boolean equals(Locale locale1, Locale locale2) {
		return com.liferay.portal.kernel.util.LocaleUtil.equals(
			locale1, locale2);
	}

	public static Locale fromLanguageId(String languageId) {
		return fromLanguageId(languageId, true);
	}

	public static Locale fromLanguageId(String languageId, boolean validate) {
		return fromLanguageId(languageId, validate, true);
	}

	public static Locale fromLanguageId(
		String languageId, boolean validate, boolean useDefault) {

		if (languageId == null) {
			if (useDefault) {
				return US;
			}

			return null;
		}

		if (!validate) {
			return com.liferay.portal.kernel.util.LocaleUtil.fromLanguageId(
				languageId);
		}

		Locale locale = null;

		int pos = languageId.indexOf(CharPool.UNDERLINE);

		if (pos == -1) {
			locale = new Locale(languageId);
		}
		else {
			String[] languageIdParts = StringUtil.split(
				languageId, CharPool.UNDERLINE);

			String languageCode = languageIdParts[0];
			String countryCode = languageIdParts[1];

			String variant = null;

			if (languageIdParts.length > 2) {
				variant = languageIdParts[2];
			}

			if (Validator.isNotNull(variant)) {
				locale = new Locale(languageCode, countryCode, variant);
			}
			else {
				locale = new Locale(languageCode, countryCode);
			}
		}

		if (!LanguageUtil.isAvailableLocale(locale)) {
			throw new IllegalArgumentException("Invalid locale " + locale);
		}

		return com.liferay.portal.kernel.util.LocaleUtil.fromLanguageId(
			languageId);
	}

	public static Locale[] fromLanguageIds(List<String> languageIds) {
		return com.liferay.portal.kernel.util.LocaleUtil.fromLanguageIds(
			languageIds);
	}

	public static Locale[] fromLanguageIds(String[] languageIds) {
		return com.liferay.portal.kernel.util.LocaleUtil.fromLanguageIds(
			languageIds);
	}

	public static Locale getDefault() {
		return com.liferay.portal.kernel.util.LocaleUtil.getDefault();
	}

	public static com.liferay.portal.kernel.util.LocaleUtil getInstance() {
		return com.liferay.portal.kernel.util.LocaleUtil.getInstance();
	}

	public static Map<String, String> getISOLanguages(Locale locale) {
		return com.liferay.portal.kernel.util.LocaleUtil.getISOLanguages(
			locale);
	}

	public static String getLongDisplayName(
		Locale locale, Set<String> duplicateLanguages) {

		return _getDisplayName(
			locale.getDisplayLanguage(locale), locale.getDisplayCountry(locale),
			locale, duplicateLanguages);
	}

	public static Locale getMostRelevantLocale() {
		return
			com.liferay.portal.kernel.util.LocaleUtil.getMostRelevantLocale();
	}

	public static String getShortDisplayName(
		Locale locale, Set<String> duplicateLanguages) {

		String language = locale.getDisplayLanguage(locale);

		if (language.length() > 3) {
			language = locale.getLanguage();
			language = language.toUpperCase();
		}

		String country = locale.getCountry();

		return _getDisplayName(
			language, country.toUpperCase(), locale, duplicateLanguages);
	}

	public static void setDefault(
		String userLanguage, String userCountry, String userVariant) {

		com.liferay.portal.kernel.util.LocaleUtil.setDefault(
			userLanguage, userCountry, userVariant);
	}

	public static String toLanguageId(Locale locale) {
		return com.liferay.portal.kernel.util.LocaleUtil.toLanguageId(locale);
	}

	public static String[] toLanguageIds(Locale[] locales) {
		return com.liferay.portal.kernel.util.LocaleUtil.toLanguageIds(locales);
	}

	public static String toW3cLanguageId(Locale locale) {
		return com.liferay.portal.kernel.util.LocaleUtil.toW3cLanguageId(
			locale);
	}

	public static String toW3cLanguageId(String languageId) {
		return com.liferay.portal.kernel.util.LocaleUtil.toW3cLanguageId(
			languageId);
	}

	public static String[] toW3cLanguageIds(Locale[] locales) {
		return com.liferay.portal.kernel.util.LocaleUtil.toW3cLanguageIds(
			locales);
	}

	public static String[] toW3cLanguageIds(String[] languageIds) {
		return com.liferay.portal.kernel.util.LocaleUtil.toW3cLanguageIds(
			languageIds);
	}

	private static String _getDisplayName(
		String language, String country, Locale locale,
		Set<String> duplicateLanguages) {

		StringBundler sb = new StringBundler(6);

		sb.append(language);

		if (duplicateLanguages.contains(locale.getLanguage())) {
			sb.append(StringPool.SPACE);
			sb.append(StringPool.OPEN_PARENTHESIS);
			sb.append(country);
			sb.append(StringPool.CLOSE_PARENTHESIS);
		}

		if (LanguageUtil.isBetaLocale(locale)) {
			sb.append(_BETA_SUFFIX);
		}

		return sb.toString();
	}

	private static final String _BETA_SUFFIX = " [Beta]";

}