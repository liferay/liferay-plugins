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

package com.liferay.sync.engine.util;

import java.net.URL;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.log4j.xml.DOMConfigurator;

/**
 * @author Michael Young
 */
public class LoggerUtil {

	public static void initLogger() {
		String loggerConfigurationFilePathName = FileUtil.getFilePathName(
			PropsValues.SYNC_CONFIGURATION_DIRECTORY,
			PropsValues.SYNC_LOGGER_CONFIGURATION_FILE);

		if (Files.exists(Paths.get(loggerConfigurationFilePathName))) {
			DOMConfigurator.configureAndWatch(loggerConfigurationFilePathName);
		}
		else {
			ClassLoader classLoader = LoggerUtil.class.getClassLoader();

			URL url = classLoader.getResource(
				PropsValues.SYNC_LOGGER_CONFIGURATION_FILE);

			DOMConfigurator.configure(url);
		}
	}

}