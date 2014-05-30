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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 * @author Eduardo Lundgren
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
		return getInstance()._fromLanguageId(languageId, true);
	}

	public static Locale fromLanguageId(String languageId, boolean validate) {
		return getInstance()._fromLanguageId(languageId, validate);
	}

	public static Locale fromLanguageId(
		String languageId, boolean validate, boolean useDefault) {

		return getInstance()._fromLanguageId(languageId, validate, useDefault);
	}

	public static Locale[] fromLanguageIds(List<String> languageIds) {
		return getInstance()._fromLanguageIds(languageIds);
	}

	public static Locale[] fromLanguageIds(String[] languageIds) {
		return getInstance()._fromLanguageIds(languageIds);
	}

	public static Locale getDefault() {
		return com.liferay.portal.kernel.util.LocaleUtil.getDefault();
	}

	public static LocaleUtil getInstance() {
		PortalRuntimePermission.checkGetBeanProperty(LocaleUtil.class);

		return _instance;
	}

	public static Map<String, String> getISOLanguages(Locale locale) {
		return com.liferay.portal.kernel.util.LocaleUtil.getISOLanguages(
			locale);
	}

	public static String getLongDisplayName(
		Locale locale, Set<String> duplicateLanguages) {

		return com.liferay.portal.kernel.util.LocaleUtil.getLongDisplayName(
			locale, duplicateLanguages);
	}

	public static Locale getMostRelevantLocale() {
		return
			com.liferay.portal.kernel.util.LocaleUtil.getMostRelevantLocale();
	}

	public static String getShortDisplayName(
		Locale locale, Set<String> duplicateLanguages) {

		return com.liferay.portal.kernel.util.LocaleUtil.getShortDisplayName(
			locale, duplicateLanguages);
	}

	public static Locale getSiteDefault() {
		return com.liferay.portal.kernel.util.LocaleUtil.getSiteDefault();
	}

	public static void setDefault(
		String userLanguage, String userCountry, String userVariant) {

		com.liferay.portal.kernel.util.LocaleUtil.setDefault(
			userLanguage, userCountry, userVariant);
	}

	public static String toBCP47LanguageId(Locale locale) {
		return com.liferay.portal.kernel.util.LocaleUtil.toBCP47LanguageId(
			locale);
	}

	public static String toBCP47LanguageId(String languageId) {
		return com.liferay.portal.kernel.util.LocaleUtil.toBCP47LanguageId(
			languageId);
	}

	public static String[] toBCP47LanguageIds(Locale[] locales) {
		return com.liferay.portal.kernel.util.LocaleUtil.toBCP47LanguageIds(
			locales);
	}

	public static String[] toBCP47LanguageIds(String[] languageIds) {
		return com.liferay.portal.kernel.util.LocaleUtil.toBCP47LanguageIds(
			languageIds);
	}

	public static String[] toDisplayNames(Locale[] locales, Locale locale) {
		return com.liferay.portal.kernel.util.LocaleUtil.toDisplayNames(
			locales, locale);
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

	private LocaleUtil() {
		_locale = new Locale("en", "US");
	}

	private Locale _fromLanguageId(String languageId, boolean validate) {
		return _fromLanguageId(languageId, validate, true);
	}

	private Locale _fromLanguageId(
		String languageId, boolean validate, boolean useDefault) {

		if (languageId == null) {
			if (useDefault) {
				return _locale;
			}
			else {
				return null;
			}
		}

		Locale locale = _locales.get(languageId);

		if (locale != null) {
			return locale;
		}

		try {
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

			if (validate && !LanguageUtil.isAvailableLocale(locale)) {
				throw new IllegalArgumentException("Invalid locale " + locale);
			}

			_locales.put(languageId, locale);
		}
		catch (Exception e) {
			locale = null;

			if (_log.isWarnEnabled()) {
				_log.warn(languageId + " is not a valid language id");
			}
		}

		if ((locale == null) && useDefault) {
			locale = _locale;
		}

		return locale;
	}

	private Locale[] _fromLanguageIds(List<String> languageIds) {
		Locale[] locales = new Locale[languageIds.size()];

		for (int i = 0; i < languageIds.size(); i++) {
			locales[i] = _fromLanguageId(languageIds.get(i), true);
		}

		return locales;
	}

	private Locale[] _fromLanguageIds(String[] languageIds) {
		Locale[] locales = new Locale[languageIds.length];

		for (int i = 0; i < languageIds.length; i++) {
			locales[i] = _fromLanguageId(languageIds[i], true);
		}

		return locales;
	}

	private static Log _log = LogFactoryUtil.getLog(LocaleUtil.class);

	private static LocaleUtil _instance = new LocaleUtil();

	private Locale _locale;
	private Map<String, Locale> _locales = new HashMap<String, Locale>();

}