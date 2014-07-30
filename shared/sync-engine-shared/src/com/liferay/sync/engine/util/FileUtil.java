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

import com.liferay.sync.engine.model.SyncFile;

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
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shinn Lok
 */
public class FileUtil {

	public static String getChecksum(Path filePath) {
		if (!Files.exists(filePath)) {
			return "";
		}

		InputStream fileInputStream = null;

		try {
			if (Files.size(filePath) >
					PropsValues.SYNC_FILE_CHECKSUM_THRESHOLD_SIZE) {

				return "";
			}

			fileInputStream = Files.newInputStream(filePath);

			byte[] bytes = DigestUtils.sha1(fileInputStream);

			return Base64.encodeBase64String(bytes);
		}
		catch (Exception e) {
			_logger.error(e.getMessage(), e);

			return "";
		}
		finally {
			StreamUtil.cleanUp(fileInputStream);
		}
	}

	public static String getFileKey(Path filePath) {
		if (!Files.exists(filePath)) {
			return "";
		}

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

			return "";
		}
	}

	public static String getFileKey(String filePathName) {
		Path filePath = Paths.get(filePathName);

		return getFileKey(filePath);
	}

	public static boolean hasFileChanged(SyncFile syncFile) {
		if (syncFile.getFilePathName() == null) {
			return true;
		}

		Path filePath = Paths.get(syncFile.getFilePathName());

		if (filePath == null) {
			return true;
		}

		String checksum = getChecksum(filePath);

		return !checksum.equals(syncFile.getChecksum());
	}

	public static boolean hasFileChanged(SyncFile syncFile, Path filePath) {
		if (filePath == null) {
			return true;
		}

		String checksum = getChecksum(filePath);

		return !checksum.equals(syncFile.getChecksum());
	}

	public static boolean isIgnoredFilePath(Path filePath) throws Exception {
		String fileName = String.valueOf(filePath.getFileName());

		if (_syncFileIgnoreNames.contains(fileName) ||
			(PropsValues.SYNC_FILE_IGNORE_HIDDEN &&
			 Files.isHidden(filePath)) ||
			Files.isSymbolicLink(filePath) || fileName.endsWith(".lnk")) {

			return true;
		}

		return false;
	}

	public static boolean isValidFileName(String fileName) {
		if (StringUtils.isBlank(fileName)) {
			return false;
		}

		for (String blacklistChar : PropsValues.SYNC_FILE_BLACKLIST_CHARS) {
			if (fileName.contains(blacklistChar)) {
				return false;
			}
		}

		for (String blacklistLastChar :
				PropsValues.SYNC_FILE_BLACKLIST_CHARS_LAST) {

			if (blacklistLastChar.startsWith("\\u")) {
				blacklistLastChar = StringEscapeUtils.unescapeJava(
					blacklistLastChar);
			}

			if (fileName.endsWith(blacklistLastChar)) {
				return false;
			}
		}

		String nameWithoutExtension = FilenameUtils.removeExtension(fileName);

		for (String blacklistName : PropsValues.SYNC_FILE_BLACKLIST_NAMES) {
			if (nameWithoutExtension.equalsIgnoreCase(blacklistName)) {
				return false;
			}
		}

		return true;
	}

	private static Logger _logger = LoggerFactory.getLogger(FileUtil.class);

	private static Set<String> _syncFileIgnoreNames = new HashSet<String>(
		Arrays.asList(PropsValues.SYNC_FILE_IGNORE_NAMES));

}