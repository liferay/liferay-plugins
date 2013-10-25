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

package com.liferay.sync.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.DigesterUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Lock;
import com.liferay.portlet.documentlibrary.model.DLFileVersion;
import com.liferay.portlet.documentlibrary.model.DLSyncConstants;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileVersionLocalServiceUtil;
import com.liferay.sync.io.delta.ByteChannelReader;
import com.liferay.sync.io.delta.ByteChannelWriter;
import com.liferay.sync.io.delta.DeltaUtil;
import com.liferay.sync.model.SyncDLObject;
import com.liferay.sync.model.impl.SyncDLObjectImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @author Dennis Ju
 */
public class SyncUtil {

	public static String getChecksum(DLFileVersion dlFileVersion)
		throws PortalException, SystemException {

		return getChecksum(dlFileVersion.getContentStream(false));
	}

	public static String getChecksum(File file) throws PortalException {
		FileInputStream fileInputStream = null;

		try {
			fileInputStream = new FileInputStream(file);

			return getChecksum(fileInputStream);
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
		finally {
			StreamUtil.cleanUp(fileInputStream);
		}
	}

	public static String getChecksum(InputStream inputStream) {
		return DigesterUtil.digestBase64(inputStream);
	}

	public static InputStream getFileDeltaAsStream(
			long userId, long fileEntryId, String sourceVersion,
			String destinationVersion)
		throws PortalException {

		InputStream deltaInputStream = null;

		FileInputStream sourceFileInputStream = null;
		FileChannel sourceFileChannel = null;
		File checksumsFile = FileUtil.createTempFile();
		OutputStream checksumsOutputStream = null;
		WritableByteChannel checksumsWritableByteChannel = null;

		try {
			File sourceFile = DLFileEntryLocalServiceUtil.getFile(
				userId, fileEntryId, sourceVersion, false);

			sourceFileInputStream = new FileInputStream(sourceFile);

			sourceFileChannel = sourceFileInputStream.getChannel();

			checksumsOutputStream = new FileOutputStream(checksumsFile);

			checksumsWritableByteChannel = Channels.newChannel(
				checksumsOutputStream);

			ByteChannelWriter checksumsByteChannelWriter =
				new ByteChannelWriter(checksumsWritableByteChannel);

			DeltaUtil.checksums(sourceFileChannel, checksumsByteChannelWriter);

			checksumsByteChannelWriter.finish();
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
		finally {
			StreamUtil.cleanUp(sourceFileInputStream);
			StreamUtil.cleanUp(sourceFileChannel);
			StreamUtil.cleanUp(checksumsOutputStream);
			StreamUtil.cleanUp(checksumsWritableByteChannel);
		}

		InputStream destinationInputStream = null;
		ReadableByteChannel destinationReadableByteChannel = null;
		InputStream checksumsInputStream = null;
		ReadableByteChannel checksumsReadableByteChannel = null;
		OutputStream deltaOutputStream = null;
		WritableByteChannel deltaOutputStreamWritableByteChannel = null;

		try {
			FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(
				fileEntryId);

			destinationInputStream = fileEntry.getContentStream(
				destinationVersion);

			destinationReadableByteChannel = Channels.newChannel(
				destinationInputStream);

			checksumsInputStream = new FileInputStream(checksumsFile);

			checksumsReadableByteChannel = Channels.newChannel(
				checksumsInputStream);

			ByteChannelReader checksumsByteChannelReader =
				new ByteChannelReader(checksumsReadableByteChannel);

			File deltaFile = FileUtil.createTempFile();

			deltaOutputStream = new FileOutputStream(deltaFile);

			deltaOutputStreamWritableByteChannel = Channels.newChannel(
				deltaOutputStream);

			ByteChannelWriter deltaByteChannelWriter = new ByteChannelWriter(
				deltaOutputStreamWritableByteChannel);

			DeltaUtil.delta(
				destinationReadableByteChannel, checksumsByteChannelReader,
				deltaByteChannelWriter);

			deltaByteChannelWriter.finish();

			deltaInputStream = new FileInputStream(deltaFile);
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
		finally {
			StreamUtil.cleanUp(destinationInputStream);
			StreamUtil.cleanUp(destinationReadableByteChannel);
			StreamUtil.cleanUp(checksumsInputStream);
			StreamUtil.cleanUp(checksumsReadableByteChannel);
			StreamUtil.cleanUp(deltaOutputStream);
			StreamUtil.cleanUp(deltaOutputStreamWritableByteChannel);

			FileUtil.delete(checksumsFile);
		}

		return deltaInputStream;
	}

	public static void patchFile(
			File originalFile, File deltaFile, File patchedFile)
		throws PortalException {

		FileInputStream originalFileInputStream = null;
		FileChannel originalFileChannel = null;
		FileOutputStream patchedFileOutputStream = null;
		WritableByteChannel patchedWritableByteChannel = null;
		ReadableByteChannel deltaReadableByteChannel = null;

		try {
			originalFileInputStream = new FileInputStream(originalFile);

			originalFileChannel = originalFileInputStream.getChannel();

			patchedFileOutputStream = new FileOutputStream(patchedFile);

			patchedWritableByteChannel = Channels.newChannel(
				patchedFileOutputStream);

			FileInputStream deltaInputStream = new FileInputStream(deltaFile);

			deltaReadableByteChannel = Channels.newChannel(deltaInputStream);

			ByteChannelReader deltaByteChannelReader = new ByteChannelReader(
				deltaReadableByteChannel);

			DeltaUtil.patch(
				originalFileChannel, patchedWritableByteChannel,
				deltaByteChannelReader);
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
		finally {
			StreamUtil.cleanUp(originalFileInputStream);
			StreamUtil.cleanUp(originalFileChannel);
			StreamUtil.cleanUp(patchedFileOutputStream);
			StreamUtil.cleanUp(patchedWritableByteChannel);
			StreamUtil.cleanUp(deltaReadableByteChannel);
		}
	}

	public static SyncDLObject toSyncDLObject(FileEntry fileEntry, String event)
		throws PortalException, SystemException {

		SyncDLObject syncDLObject = new SyncDLObjectImpl();

		syncDLObject.setCompanyId(fileEntry.getCompanyId());
		syncDLObject.setCreateDate(fileEntry.getCreateDate());
		syncDLObject.setModifiedDate(fileEntry.getModifiedDate());
		syncDLObject.setRepositoryId(fileEntry.getRepositoryId());
		syncDLObject.setParentFolderId(fileEntry.getFolderId());
		syncDLObject.setName(fileEntry.getTitle());
		syncDLObject.setExtension(fileEntry.getExtension());
		syncDLObject.setMimeType(fileEntry.getMimeType());
		syncDLObject.setDescription(fileEntry.getDescription());

		DLFileVersion dlFileVersion =
			DLFileVersionLocalServiceUtil.getLatestFileVersion(
				fileEntry.getFileEntryId(), false);

		syncDLObject.setChangeLog(dlFileVersion.getChangeLog());
		syncDLObject.setExtraSettings(dlFileVersion.getExtraSettings());

		syncDLObject.setVersion(fileEntry.getVersion());
		syncDLObject.setSize(fileEntry.getSize());
		syncDLObject.setChecksum(getChecksum(dlFileVersion));
		syncDLObject.setEvent(event);

		Lock lock = fileEntry.getLock();

		if (lock != null) {
			syncDLObject.setLockExpirationDate(lock.getExpirationDate());
			syncDLObject.setLockUserId(lock.getUserId());
			syncDLObject.setLockUserName(lock.getUserName());
		}
		else {
			syncDLObject.setLockExpirationDate(null);
			syncDLObject.setLockUserId(0);
			syncDLObject.setLockUserName(StringPool.BLANK);
		}

		syncDLObject.setType(DLSyncConstants.TYPE_FILE);
		syncDLObject.setTypePK(fileEntry.getFileEntryId());
		syncDLObject.setTypeUuid(fileEntry.getUuid());

		return syncDLObject;
	}

	public static SyncDLObject toSyncDLObject(Folder folder, String event) {
		SyncDLObject syncDLObject = new SyncDLObjectImpl();

		syncDLObject.setCompanyId(folder.getCompanyId());
		syncDLObject.setCreateDate(folder.getCreateDate());
		syncDLObject.setModifiedDate(folder.getModifiedDate());
		syncDLObject.setRepositoryId(folder.getRepositoryId());
		syncDLObject.setParentFolderId(folder.getParentFolderId());
		syncDLObject.setName(folder.getName());
		syncDLObject.setExtension(StringPool.BLANK);
		syncDLObject.setMimeType(StringPool.BLANK);
		syncDLObject.setDescription(folder.getDescription());
		syncDLObject.setChangeLog(StringPool.BLANK);
		syncDLObject.setExtraSettings(StringPool.BLANK);
		syncDLObject.setVersion(StringPool.BLANK);
		syncDLObject.setSize(-1);
		syncDLObject.setChecksum(StringPool.BLANK);
		syncDLObject.setEvent(event);
		syncDLObject.setLockExpirationDate(null);
		syncDLObject.setLockUserId(0);
		syncDLObject.setLockUserName(StringPool.BLANK);
		syncDLObject.setType(DLSyncConstants.TYPE_FOLDER);
		syncDLObject.setTypePK(folder.getFolderId());
		syncDLObject.setTypeUuid(folder.getUuid());

		return syncDLObject;
	}

}