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

package com.liferay.sync.engine.model;

import com.liferay.io.delta.ByteChannelWriter;
import com.liferay.io.delta.DeltaUtil;
import com.liferay.sync.engine.util.PropsValues;
import com.liferay.sync.engine.util.StreamUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shinn Lok
 */
public class SyncFileModelListener implements ModelListener<SyncFile> {

	@Override
	public void onCreate(SyncFile syncFile) {
		updateChecksumsFile(syncFile);
	}

	@Override
	public void onRemove(SyncFile syncFile) {
		Path filePath = Paths.get(syncFile.getFilePathName());

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

		updateChecksumsFile(syncFile);
	}

	protected void updateChecksumsFile(SyncFile syncFile) {
		Path syncFilePath = Paths.get(syncFile.getFilePathName());

		if (Files.notExists(syncFilePath)) {
			return;
		}

		FileInputStream fileInputStream = null;
		FileChannel fileChannel = null;
		OutputStream outputStream = null;
		WritableByteChannel writableByteChannel = null;

		try {
			fileInputStream = new FileInputStream(syncFile.getFilePathName());

			fileChannel = fileInputStream.getChannel();

			Path checksumsFilePath = Paths.get(
				PropsValues.SYNC_CONFIGURATION_DIRECTORY + "/files/" +
					syncFile.getSyncFileId());

			if (Files.notExists(checksumsFilePath)) {
				Files.createFile(checksumsFilePath);
			}

			outputStream = Files.newOutputStream(checksumsFilePath);

			writableByteChannel = Channels.newChannel(outputStream);

			ByteChannelWriter byteChannelWriter = new ByteChannelWriter(
				writableByteChannel);

			DeltaUtil.checksums(fileChannel, byteChannelWriter);

			byteChannelWriter.finish();
		}
		catch (IOException ioe) {
			_logger.error(ioe.getMessage(), ioe);
		}
		finally {
			StreamUtil.cleanUp(fileInputStream);
			StreamUtil.cleanUp(outputStream);
			StreamUtil.cleanUp(fileChannel);
			StreamUtil.cleanUp(writableByteChannel);
		}
	}

	private static Logger _logger = LoggerFactory.getLogger(
		SyncFileModelListener.class);

}