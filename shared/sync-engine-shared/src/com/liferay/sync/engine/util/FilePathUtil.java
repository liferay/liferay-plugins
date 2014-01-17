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

/**
 * @author Dennis Ju
 */
public class FilePathUtil {

	public static String fixFilePath(String filePath) {
		if (filePath == null) {
			return "";
		}

		filePath = filePath.replace("\\", "/");

		if (filePath.endsWith("/")) {
			filePath = filePath.substring(0, filePath.length() - 1);
		}

		return filePath;
	}

}