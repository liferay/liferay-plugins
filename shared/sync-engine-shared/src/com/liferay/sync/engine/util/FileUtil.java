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

import java.io.IOException;
import java.io.InputStream;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.UserDefinedFileAttributeView;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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

	public static String getChecksum(Path filePath) throws IOException {
		if (!Files.exists(filePath) ||
			(Files.size(filePath) >
				PropsValues.SYNC_FILE_CHECKSUM_THRESHOLD_SIZE)) {

			return "";
		}

		InputStream fileInputStream = null;

		try {
			fileInputStream = Files.newInputStream(filePath);

			byte[] bytes = DigestUtils.sha1(fileInputStream);

			return Base64.encodeBase64String(bytes);
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
			if (OSDetector.isWindows()) {
				UserDefinedFileAttributeView userDefinedFileAttributeView =
					Files.getFileAttributeView(
						filePath, UserDefinedFileAttributeView.class);

				List<String> list = userDefinedFileAttributeView.list();

				if (!list.contains("fileKey")) {
					return "";
				}

				ByteBuffer byteBuffer = ByteBuffer.allocate(
					userDefinedFileAttributeView.size("fileKey"));

				userDefinedFileAttributeView.read("fileKey", byteBuffer);

				CharBuffer charBuffer = _CHARSET.decode(
					(ByteBuffer)byteBuffer.flip());

				return charBuffer.toString();
			}
			else {
				BasicFileAttributes basicFileAttributes = Files.readAttributes(
					filePath, BasicFileAttributes.class);

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

	public static String getFilePathName(String first, String... more) {
		FileSystem fileSystem = FileSystems.getDefault();

		Path filePath = fileSystem.getPath(first, more);

		return filePath.toString();
	}

	public static boolean hasFileChanged(SyncFile syncFile) throws IOException {
		if (syncFile.getFilePathName() == null) {
			return true;
		}

		Path filePath = Paths.get(syncFile.getFilePathName());

		return hasFileChanged(syncFile, filePath);
	}

	public static boolean hasFileChanged(SyncFile syncFile, Path filePath)
		throws IOException {

		if (filePath == null) {
			return true;
		}

		if ((syncFile.getSize() > 0) &&
			(syncFile.getSize() != Files.size(filePath))) {

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

	public static boolean isValidFileName(Path filePath) {
		String fileName = String.valueOf(filePath.getFileName());

		if ((Files.isDirectory(filePath) && (fileName.length() > 100)) ||
			(!Files.isDirectory(filePath) && (fileName.length() > 255))) {

			return false;
		}

		return isValidFileName(fileName);
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

	public static void writeFileKey(Path filePath, String fileKey) {
		if (!OSDetector.isWindows()) {
			return;
		}

		UserDefinedFileAttributeView userDefinedFileAttributeView =
			Files.getFileAttributeView(
				filePath, UserDefinedFileAttributeView.class);

		try {
			userDefinedFileAttributeView.write(
				"fileKey", _CHARSET.encode(CharBuffer.wrap(fileKey)));
		}
		catch (Exception e) {
			_logger.error(e.getMessage(), e);
		}
	}

	private static final Charset _CHARSET = Charset.forName("UTF-8");

	private static Logger _logger = LoggerFactory.getLogger(FileUtil.class);

	private static Set<String> _syncFileIgnoreNames = new HashSet<String>(
		Arrays.asList(PropsValues.SYNC_FILE_IGNORE_NAMES));

}