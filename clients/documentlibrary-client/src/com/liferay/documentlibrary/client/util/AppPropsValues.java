/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.documentlibrary.client.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;

import java.io.InputStream;

import java.util.Properties;

/**
 * @author Brian Wing Shun Chan
 */
public class AppPropsValues {

	public static final String ROOT_FOLDER =
		_getProperty(AppPropsKeys.ROOT_FOLDER);

	public static final String SERVER_URL =
		_getProperty(AppPropsKeys.SERVER_URL);

	public static final String USERNAME = _getProperty(AppPropsKeys.USERNAME);

	public static final String PASSWORD = _getProperty(AppPropsKeys.PASSWORD);

	public static final String GROUP_ID = _getProperty(AppPropsKeys.GROUP_ID);

	public static final String REPOSITORY_ID =
		_getProperty(AppPropsKeys.REPOSITORY_ID);

	private static String _getProperty(String key) {
		if (_instance == null) {
			_instance = new AppPropsValues();
		}

		String property = _instance._properties.getProperty(key);

		return GetterUtil.getString(property);
	}

	private AppPropsValues() {
		_properties = new Properties();

		InputStream is = AppPropsValues.class.getResourceAsStream(
			"/app.properties");

		try {
			_properties.load(is);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static AppPropsValues _instance;

	private static Log _log = LogFactoryUtil.getLog(
		AppPropsValues.class.getName());

	private Properties _properties;

}