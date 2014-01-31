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

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * @author Dennis Ju
 */
public class FilePathNameUtil {

	public static String fixFilePathName(String filePathName) {
		if (filePathName == null) {
			return "";
		}

		filePathName = filePathName.replace("\\", "/");

		if (filePathName.endsWith("/")) {
			filePathName = filePathName.substring(0, filePathName.length() - 1);
		}

		return filePathName;
	}

	public static String getFilePathName(Path filePath) {
		return fixFilePathName(filePath.toString());
	}

	public static String getFilePathName(
		String parentFilePathName, String fileName) {

		StringBuilder sb = new StringBuilder(3);

		sb.append(parentFilePathName);

		FileSystem fileSystem = FileSystems.getDefault();

		sb.append(fileSystem.getSeparator());

		sb.append(fileName);

		return fixFilePathName(sb.toString());
	}

}