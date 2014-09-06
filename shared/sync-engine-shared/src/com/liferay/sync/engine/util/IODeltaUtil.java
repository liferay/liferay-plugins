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

import com.liferay.io.delta.ByteChannelReader;
import com.liferay.io.delta.ByteChannelWriter;
import com.liferay.io.delta.DeltaUtil;
import com.liferay.sync.engine.model.SyncFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shinn Lok
 */
public class IODeltaUtil {

	public static Path checksums(SyncFile syncFile) {
		if (isIgnoredFilePatchingExtension(syncFile)) {
			return null;
		}

		Path syncFilePath = Paths.get(syncFile.getFilePathName());

		if (Files.isDirectory(syncFilePath) || Files.notExists(syncFilePath)) {
			return null;
		}

		FileInputStream fileInputStream = null;
		FileChannel fileChannel = null;
		OutputStream outputStream = null;
		WritableByteChannel writableByteChannel = null;

		try {
			fileInputStream = new FileInputStream(syncFile.getFilePathName());

			fileChannel = fileInputStream.getChannel();

			Path checksumsFilePath = getChecksumsFilePath(syncFile);

			if (Files.notExists(checksumsFilePath)) {
				Files.createFile(checksumsFilePath);
			}

			outputStream = Files.newOutputStream(checksumsFilePath);

			writableByteChannel = Channels.newChannel(outputStream);

			ByteChannelWriter byteChannelWriter = new ByteChannelWriter(
				writableByteChannel);

			DeltaUtil.checksums(fileChannel, byteChannelWriter);

			byteChannelWriter.finish();

			return checksumsFilePath;
		}
		catch (IOException ioe) {
			_logger.error(ioe.getMessage(), ioe);

			return null;
		}
		finally {
			StreamUtil.cleanUp(fileInputStream);
			StreamUtil.cleanUp(outputStream);
			StreamUtil.cleanUp(fileChannel);
			StreamUtil.cleanUp(writableByteChannel);
		}
	}

	public static Path delta(
		Path targetFilePath, Path checksumsFilePath, Path deltaFilePath) {

		if (Files.notExists(targetFilePath) ||
			Files.notExists(checksumsFilePath) ||
			Files.notExists(deltaFilePath)) {

			return null;
		}

		InputStream targetInputStream = null;
		ReadableByteChannel targetReadableByteChannel = null;
		InputStream checksumsInputStream = null;
		ReadableByteChannel checksumsReadableByteChannel = null;
		OutputStream deltaOutputStream = null;
		WritableByteChannel deltaWritableByteChannel = null;

		try {
			targetInputStream = Files.newInputStream(targetFilePath);

			targetReadableByteChannel = Channels.newChannel(targetInputStream);

			checksumsInputStream = Files.newInputStream(checksumsFilePath);

			checksumsReadableByteChannel = Channels.newChannel(
				checksumsInputStream);

			ByteChannelReader checksumsByteChannelReader =
				new ByteChannelReader(checksumsReadableByteChannel);

			deltaOutputStream = Files.newOutputStream(deltaFilePath);

			deltaWritableByteChannel = Channels.newChannel(deltaOutputStream);

			ByteChannelWriter deltaByteChannelWriter = new ByteChannelWriter(
				deltaWritableByteChannel);

			DeltaUtil.delta(
				targetReadableByteChannel, checksumsByteChannelReader,
				deltaByteChannelWriter);

			deltaByteChannelWriter.finish();

			return deltaFilePath;
		}
		catch (IOException ioe) {
			_logger.error(ioe.getMessage(), ioe);

			return null;
		}
		finally {
			StreamUtil.cleanUp(targetInputStream);
			StreamUtil.cleanUp(targetReadableByteChannel);
			StreamUtil.cleanUp(checksumsInputStream);
			StreamUtil.cleanUp(checksumsReadableByteChannel);
			StreamUtil.cleanUp(deltaOutputStream);
			StreamUtil.cleanUp(deltaWritableByteChannel);
		}
	}

	public static Path getChecksumsFilePath(SyncFile syncFile) {
		return FileUtil.getFilePath(
			PropsValues.SYNC_CONFIGURATION_DIRECTORY, "files",
			String.valueOf(syncFile.getSyncFileId()));
	}

	public static boolean isIgnoredFilePatchingExtension(SyncFile syncFile) {
		return _syncFilePatchingIgnoreFileExtensions.contains(
			syncFile.getExtension());
	}

	public static Path patch(
		Path targetFilePath, InputStream deltaInputStream) {

		if (Files.notExists(targetFilePath)) {
			return null;
		}

		FileInputStream targetInputStream = null;
		FileChannel targetFileChannel = null;
		Path patchedFilePath = null;
		OutputStream patchedFileOutputStream = null;
		WritableByteChannel patchedWritableByteChannel = null;
		ReadableByteChannel deltaReadableByteChannel = null;

		try {
			targetInputStream = new FileInputStream(targetFilePath.toString());

			targetFileChannel = targetInputStream.getChannel();

			patchedFilePath = Files.createTempFile(
				String.valueOf(targetFilePath.getFileName()), ".tmp");

			patchedFileOutputStream = Files.newOutputStream(patchedFilePath);

			patchedWritableByteChannel = Channels.newChannel(
				patchedFileOutputStream);

			deltaReadableByteChannel = Channels.newChannel(deltaInputStream);

			ByteChannelReader deltaByteChannelReader = new ByteChannelReader(
				deltaReadableByteChannel);

			DeltaUtil.patch(
				targetFileChannel, patchedWritableByteChannel,
				deltaByteChannelReader);
		}
		catch (IOException ioe) {
			_logger.error(ioe.getMessage(), ioe);

			return null;
		}
		finally {
			StreamUtil.cleanUp(targetInputStream);
			StreamUtil.cleanUp(targetFileChannel);
			StreamUtil.cleanUp(patchedFileOutputStream);
			StreamUtil.cleanUp(patchedWritableByteChannel);
			StreamUtil.cleanUp(deltaReadableByteChannel);
		}

		try {
			Files.move(
				patchedFilePath, targetFilePath,
				StandardCopyOption.REPLACE_EXISTING);

			return targetFilePath;
		}
		catch (IOException ioe) {
			_logger.error(ioe.getMessage(), ioe);

			return null;
		}
	}

	private static Logger _logger = LoggerFactory.getLogger(IODeltaUtil.class);

	private static Set<String> _syncFilePatchingIgnoreFileExtensions =
		new HashSet<String>(
			Arrays.asList(
				PropsValues.SYNC_FILE_PATCHING_IGNORE_FILE_EXTENSIONS));

}