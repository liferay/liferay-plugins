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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Dennis Ju
 */
public class PathUtil {

	public static String fixPath(String name) {
		if (name == null) {
			return "";
		}

		name = name.replace("\\", "/");

		if (name.endsWith("/")) {
			name = name.substring(0, name.length() - 1);
		}

		return name;
	}

	public static String getWebDavPath(String repositoryPath, String path) {
		path = fixPath(path);

		if (path.startsWith(repositoryPath)) {
			return path.substring(repositoryPath.length());
		}

		_logger.error("Unable to get web dav path for {}", path);

		return null;
	}

	private static Logger _logger = LoggerFactory.getLogger(PathUtil.class);

}