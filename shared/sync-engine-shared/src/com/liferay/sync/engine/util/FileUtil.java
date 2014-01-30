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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.nio.file.Path;
import java.nio.file.Paths;

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
			fileInputStream = new FileInputStream(filePath.toFile());

			byte[] bytes = DigestUtils.sha1(fileInputStream);

			return Base64.encodeBase64String(bytes);
		}
		catch (Exception e) {
			_logger.error(e.getMessage(), e);
		}
		finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				}
				catch (IOException ioe) {
					_logger.error(ioe.getMessage(), ioe);
				}
			}
		}

		return null;
	}

	public static String getChecksum(String filePathName) {
		Path filePath = Paths.get(filePathName);

		return getChecksum(filePath);
	}

	private static Logger _logger = LoggerFactory.getLogger(FileUtil.class);

}