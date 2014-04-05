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

package com.liferay.sync.engine.model;

import com.liferay.sync.engine.util.IODeltaUtil;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shinn Lok
 */
public class SyncFileModelListener implements ModelListener<SyncFile> {

	@Override
	public void onCreate(SyncFile syncFile) {
		IODeltaUtil.checksums(syncFile);
	}

	@Override
	public void onRemove(SyncFile syncFile) {
		if (syncFile.isFolder()) {
			return;
		}

		Path filePath = IODeltaUtil.getChecksumsFilePath(syncFile);

		try {
			Files.deleteIfExists(filePath);
		}
		catch (IOException ioe) {
			_logger.error(ioe.getMessage(), ioe);
		}
	}

	@Override
	public void onUpdate(
		SyncFile syncFile, Map<String, Object> originalValues) {

		if (!originalValues.containsKey("checksum")) {
			return;
		}

		IODeltaUtil.checksums(syncFile);
	}

	private static Logger _logger = LoggerFactory.getLogger(
		SyncFileModelListener.class);

}