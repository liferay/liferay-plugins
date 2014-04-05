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

package com.liferay.portal.settings;

import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Iván Zaera
 */
public class LocalizedValuesMapTest {

	@Test
	public void testGetLocalizationXml() {
		LocalizedValuesMap localizedValuesMap = new LocalizedValuesMap(
			_KEY, _DEFAULT_LOCALE, _AVAILABLE_LOCALES);

		for (Locale locale : _AVAILABLE_LOCALES) {
			localizedValuesMap.put(locale, "value " +locale.toString() );
		}

		String xml = localizedValuesMap.getLocalizationXml();

		Assert.assertEquals(
			"<?xml version='1.0' encoding='UTF-8'?>" +
				"<root available-locales=\"es_ES,en_GB,en_US\" " +
						"default-locale=\"es_ES\">" +
					"<key language-id=\"es_ES\">value es_ES</key>" +
					"<key language-id=\"en_GB\">value en_GB</key>" +
					"<key language-id=\"en_US\">value en_US</key>" +
				"</root>",
			xml);
	}

	private static final Locale[] _AVAILABLE_LOCALES = {
		LocaleUtil.SPAIN, LocaleUtil.UK, LocaleUtil.US
	};

	private static final Locale _DEFAULT_LOCALE = LocaleUtil.SPAIN;

	private static final String _KEY = "key";

}