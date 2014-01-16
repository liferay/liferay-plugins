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

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

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
		try {
			_configuration = new PropertiesConfiguration("sync.properties");
		}
		catch (ConfigurationException ce) {
			_logger.error("Unable to initialize", ce);
		}
	}

	private String _get(String key) {
		String value = _configuration.getString(key);

		if (value == null) {
			return "";
		}

		return value.replace("${user.home}", System.getProperty("user.home"));
	}

	private void _set(String key, String value) {
		_configuration.setProperty(key, value);
	}

	private static PropsUtil _instance = new PropsUtil();

	private static Logger _logger = LoggerFactory.getLogger(PropsUtil.class);

	private Configuration _configuration;

}