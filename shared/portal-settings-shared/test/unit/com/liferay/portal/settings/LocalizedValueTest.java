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

package com.liferay.portal.settings;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Iván Zaera
 */
public class LocalizedValueTest {

	@Test
	public void testGetLocalizationXml() {
		LocalizedValue localizedValue = new LocalizedValue(
			_KEY, _DEFAULT_LOCALE, _AVAILABLE_LOCALES);

		for (Locale locale : _AVAILABLE_LOCALES) {
			localizedValue.put(locale, "value " +locale.toString() );
		}

		String xml = localizedValue.getLocalizationXml();

		Assert.assertEquals(
			"<?xml version='1.0' encoding='UTF-8'?>" +
				"<root available-locales=\"es_ES,en_US,en_GB\" " +
						"default-locale=\"es_ES\">" +
					"<key language-id=\"es_ES\">value es_ES</key>" +
					"<key language-id=\"en_US\">value en_US</key>" +
					"<key language-id=\"en_GB\">value en_GB</key>" +
				"</root>",
			xml);
	}

	private static final Locale[] _AVAILABLE_LOCALES = {
		new Locale("es", "ES"), new Locale("en", "US"), new Locale("en", "GB")
	};

	private static final Locale _DEFAULT_LOCALE = new Locale("es", "ES");

	private static final String _KEY = "key";

}