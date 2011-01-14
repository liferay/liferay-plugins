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

package com.liferay.saml2.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropsUtil {

	static {
		InputStream is = PropsUtil.class.getResourceAsStream("/config.properties");

		Properties props = new Properties();
		try {
			props.load(is);
			properties = props;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String name) {
		return properties.getProperty(name);
	}

	private static Properties properties;
}
