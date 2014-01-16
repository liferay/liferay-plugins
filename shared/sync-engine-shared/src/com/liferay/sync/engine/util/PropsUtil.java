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

package com.liferay.sync.engine.util;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shinn Lok
 */
public class PropsUtil {

	public static String get(String key) {
		return _instance._get(key);
	}

	public static void set(String key, String value) {
		_instance._set(key, value);
	}

	private PropsUtil() {
		_properties = new Properties();

		Thread thread = Thread.currentThread();

		ClassLoader classLoader = thread.getContextClassLoader();

		try {
			_properties.load(
				classLoader.getResourceAsStream("sync.properties"));
		}
		catch (Exception e) {
			_logger.error("Unable to initialize PropsUtil", e);
		}
	}

	private String _get(String key) {
		String value = _properties.getProperty(key);

		if (value == null) {
			return "";
		}

		return value.replace("${user.home}", System.getProperty("user.home"));
	}

	private void _set(String key, String value) {
		_properties.setProperty(key, value);
	}

	private static PropsUtil _instance = new PropsUtil();

	private static Logger _logger = LoggerFactory.getLogger(PropsUtil.class);

	private Properties _properties;

}