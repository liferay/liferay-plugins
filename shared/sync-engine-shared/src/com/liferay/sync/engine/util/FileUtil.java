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

import java.io.InputStream;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shinn Lok
 */
public class FileUtil {

	public static String getChecksum(Path filePath) {
		InputStream fileInputStream = null;

		try {
			fileInputStream = Files.newInputStream(filePath);

			byte[] bytes = DigestUtils.sha1(fileInputStream);

			return Base64.encodeBase64String(bytes);
		}
		catch (Exception e) {
			_logger.error(e.getMessage(), e);

			return null;
		}
		finally {
			StreamUtil.cleanUp(fileInputStream);
		}
	}

	public static String getFileKey(Path filePath) {
		try {
			BasicFileAttributes basicFileAttributes = Files.readAttributes(
				filePath, BasicFileAttributes.class);

			if (OSDetector.isWindows()) {
				return String.valueOf(basicFileAttributes.creationTime());
			}
			else {
				Object fileKey = basicFileAttributes.fileKey();

				return fileKey.toString();
			}
		}
		catch (Exception e) {
			_logger.error(e.getMessage(), e);

			return null;
		}
	}

	public static String getFileKey(String filePathName) {
		Path filePath = Paths.get(filePathName);

		return getFileKey(filePath);
	}

	public static boolean isIgnoredFilePath(Path filePath) throws Exception {
		String fileName = String.valueOf(filePath.getFileName());

		if (_syncIgnoreFileNames.contains(fileName) ||
			(PropsValues.SYNC_IGNORE_HIDDEN_FILES &&
			 Files.isHidden(filePath)) ||
			Files.isSymbolicLink(filePath) || fileName.endsWith(".lnk")) {

			return true;
		}

		return false;
	}

	private static Logger _logger = LoggerFactory.getLogger(FileUtil.class);

	private static Set<String> _syncIgnoreFileNames = new HashSet<String>(
		Arrays.asList(PropsValues.SYNC_IGNORE_FILE_NAMES));

}