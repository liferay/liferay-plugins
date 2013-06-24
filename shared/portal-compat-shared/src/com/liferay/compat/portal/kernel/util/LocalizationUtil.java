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

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ryan Park
 */
public class LocalizationUtil
	extends com.liferay.portal.kernel.util.LocalizationUtil {

	public static Map<Locale, String> getLocalizationMap(
		HttpServletRequest request, String parameter) {

		Locale[] locales = LanguageUtil.getAvailableLocales();

		Map<Locale, String> map = new HashMap<Locale, String>();

		for (Locale locale : locales) {
			String languageId = LocaleUtil.toLanguageId(locale);

			String localeParameter = parameter.concat(
				StringPool.UNDERLINE).concat(languageId);

			map.put(locale, ParamUtil.getString(request, localeParameter));
		}

		return map;
	}

}