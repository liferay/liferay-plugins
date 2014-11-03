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

import com.liferay.portal.kernel.io.unsync.UnsyncStringReader;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;

import java.util.Locale;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

/**
 * @author Brian Wing Shun Chan
 */
public class LocalizationUtil
	extends com.liferay.portal.kernel.util.LocalizationUtil {

	public static String getDefaultLanguageId(
		String xml, Locale defaultLocale) {

		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		return _getRootAttributeValue(xml, _DEFAULT_LOCALE, defaultLanguageId);
	}

	private static String _getRootAttributeValue(
		Document document, String name, String defaultValue) {

		Element rootElement = document.getRootElement();

		return rootElement.attributeValue(name, defaultValue);
	}

	private static String _getRootAttributeValue(
		String xml, String name, String defaultValue) {

		String value = null;

		XMLStreamReader xmlStreamReader = null;

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(portalClassLoader);
			}

			XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

			xmlStreamReader = xmlInputFactory.createXMLStreamReader(
				new UnsyncStringReader(xml));

			if (xmlStreamReader.hasNext()) {
				xmlStreamReader.nextTag();

				value = xmlStreamReader.getAttributeValue(null, name);
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}

			if (xmlStreamReader != null) {
				try {
					xmlStreamReader.close();
				}
				catch (Exception e) {
				}
			}
		}

		if (Validator.isNull(value)) {
			value = defaultValue;
		}

		return value;
	}

	private static final String _DEFAULT_LOCALE = "default-locale";

	private static Log _log = LogFactoryUtil.getLog(LocalizationUtil.class);

}