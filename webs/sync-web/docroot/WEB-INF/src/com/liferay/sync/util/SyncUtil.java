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

package com.liferay.sync.util;

import com.liferay.io.delta.ByteChannelReader;
import com.liferay.io.delta.ByteChannelWriter;
import com.liferay.io.delta.DeltaUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.lock.Lock;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.ClassUtil;
import com.liferay.portal.kernel.util.Digester;
import com.liferay.portal.kernel.util.DigesterUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.documentlibrary.NoSuchFileVersionException;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFileEntryConstants;
import com.liferay.portlet.documentlibrary.model.DLFileVersion;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.service.DLFileVersionLocalServiceUtil;
import com.liferay.sync.SyncSiteUnavailableException;
import com.liferay.sync.model.SyncConstants;
import com.liferay.sync.model.SyncDLObject;
import com.liferay.sync.model.impl.SyncDLObjectImpl;
import com.liferay.sync.shared.util.SyncPermissionsConstants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import java.lang.reflect.InvocationTargetException;

import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

import java.util.Date;

/**
 * @author Dennis Ju
 */
public class SyncUtil {

	public static String buildExceptionMessage(Throwable throwable) {

		// SYNC-1253

		StringBundler sb = new StringBundler(13);

		if (throwable instanceof InvocationTargetException) {
			throwable = throwable.getCause();
		}

		String throwableMessage = throwable.getMessage();

		if (Validator.isNull(throwableMessage)) {
			throwableMessage = throwable.toString();
		}

		sb.append(StringPool.QUOTE);
		sb.append(throwableMessage);
		sb.append(StringPool.QUOTE);
		sb.append(StringPool.COMMA_AND_SPACE);
		sb.append("\"error\": ");

		JSONObject errorJSONObject = JSONFactoryUtil.createJSONObject();

		errorJSONObject.put("message", throwableMessage);
		errorJSONObject.put("type", ClassUtil.getClassName(throwable));

		sb.append(errorJSONObject.toString());

		sb.append(StringPool.COMMA_AND_SPACE);
		sb.append("\"throwable\": \"");
		sb.append(throwable.toString());
		sb.append(StringPool.QUOTE);

		if (throwable.getCause() == null) {
			return StringUtil.unquote(sb.toString());
		}

		sb.append(StringPool.COMMA_AND_SPACE);
		sb.append("\"rootCause\": ");

		Throwable rootCauseThrowable = throwable;

		while (rootCauseThrowable.getCause() != null) {
			rootCauseThrowable = rootCauseThrowable.getCause();
		}

		JSONObject rootCauseJSONObject = JSONFactoryUtil.createJSONObject();

		throwableMessage = rootCauseThrowable.getMessage();

		if (Validator.isNull(throwableMessage)) {
			throwableMessage = rootCauseThrowable.toString();
		}

		rootCauseJSONObject.put("message", throwableMessage);

		rootCauseJSONObject.put(
			"type", ClassUtil.getClassName(rootCauseThrowable));

		sb.append(rootCauseJSONObject);

		return StringUtil.unquote(sb.toString());
	}

	public static void checkSyncEnabled(long groupId) throws PortalException {
		Group group = GroupLocalServiceUtil.fetchGroup(groupId);

		if ((group == null) || !isSyncEnabled(group)) {
			throw new SyncSiteUnavailableException();
		}
	}

	public static String getChecksum(DLFileVersion dlFileVersion)
		throws PortalException {

		if (dlFileVersion.getSize() >
				PortletPropsValues.SYNC_FILE_CHECKSUM_THRESHOLD_SIZE) {

			return StringPool.BLANK;
		}

		return DigesterUtil.digestBase64(
			Digester.SHA_1, dlFileVersion.getContentStream(false));
	}

	public static String getChecksum(File file) throws PortalException {
		if (file.length() >
				PortletPropsValues.SYNC_FILE_CHECKSUM_THRESHOLD_SIZE) {

			return StringPool.BLANK;
		}

		FileInputStream fileInputStream = null;

		try {
			fileInputStream = new FileInputStream(file);

			return DigesterUtil.digestBase64(Digester.SHA_1, fileInputStream);
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
		finally {
			StreamUtil.cleanUp(fileInputStream);
		}
	}

	public static File getFileDelta(File sourceFile, File targetFile)
		throws PortalException {

		File deltaFile = null;

		FileInputStream sourceFileInputStream = null;
		FileChannel sourceFileChannel = null;
		File checksumsFile = FileUtil.createTempFile();
		OutputStream checksumsOutputStream = null;
		WritableByteChannel checksumsWritableByteChannel = null;

		try {
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

		FileInputStream targetFileInputStream = null;
		ReadableByteChannel targetReadableByteChannel = null;
		InputStream checksumsInputStream = null;
		ReadableByteChannel checksumsReadableByteChannel = null;
		OutputStream deltaOutputStream = null;
		WritableByteChannel deltaOutputStreamWritableByteChannel = null;

		try {
			targetFileInputStream = new FileInputStream(targetFile);

			targetReadableByteChannel = targetFileInputStream.getChannel();

			checksumsInputStream = new FileInputStream(checksumsFile);

			checksumsReadableByteChannel = Channels.newChannel(
				checksumsInputStream);

			ByteChannelReader checksumsByteChannelReader =
				new ByteChannelReader(checksumsReadableByteChannel);

			deltaFile = FileUtil.createTempFile();

			deltaOutputStream = new FileOutputStream(deltaFile);

			deltaOutputStreamWritableByteChannel = Channels.newChannel(
				deltaOutputStream);

			ByteChannelWriter deltaByteChannelWriter = new ByteChannelWriter(
				deltaOutputStreamWritableByteChannel);

			DeltaUtil.delta(
				targetReadableByteChannel, checksumsByteChannelReader,
				deltaByteChannelWriter);

			deltaByteChannelWriter.finish();
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
		finally {
			StreamUtil.cleanUp(targetFileInputStream);
			StreamUtil.cleanUp(targetReadableByteChannel);
			StreamUtil.cleanUp(checksumsInputStream);
			StreamUtil.cleanUp(checksumsReadableByteChannel);
			StreamUtil.cleanUp(deltaOutputStream);
			StreamUtil.cleanUp(deltaOutputStreamWritableByteChannel);

			FileUtil.delete(checksumsFile);
		}

		return deltaFile;
	}

	public static boolean isSupportedFolder(DLFolder dlFolder) {
		if (dlFolder.isHidden() || dlFolder.isMountPoint()) {
			return false;
		}

		return true;
	}

	public static boolean isSupportedFolder(Folder folder) {
		if (!(folder.getModel() instanceof DLFolder)) {
			return false;
		}

		DLFolder dlFolder = (DLFolder)folder.getModel();

		return isSupportedFolder(dlFolder);
	}

	public static boolean isSyncEnabled(Group group) {
		return GetterUtil.getBoolean(
			group.getTypeSettingsProperty("syncEnabled"), true);
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

	public static void setFilePermissions(
		Group group, boolean folder, ServiceContext serviceContext) {

		int syncSiteMemberFilePermissions = GetterUtil.getInteger(
			group.getTypeSettingsProperty("syncSiteMemberFilePermissions"));

		if (syncSiteMemberFilePermissions ==
				SyncPermissionsConstants.PERMISSIONS_DEFAULT) {

			serviceContext.setDeriveDefaultPermissions(true);

			return;
		}

		String[] resourceActions = null;

		if (folder) {
			resourceActions = SyncPermissionsConstants.getFolderResourceActions(
				syncSiteMemberFilePermissions);
		}
		else {
			resourceActions = SyncPermissionsConstants.getFileResourceActions(
				syncSiteMemberFilePermissions);
		}

		serviceContext.setGroupPermissions(resourceActions);
	}

	public static SyncDLObject toSyncDLObject(
			DLFileEntry dlFileEntry, String event, boolean calculateChecksum)
		throws PortalException {

		return toSyncDLObject(dlFileEntry, event, calculateChecksum, false);
	}

	public static SyncDLObject toSyncDLObject(
			DLFileEntry dlFileEntry, String event, boolean calculateChecksum,
			boolean excludeWorkingCopy)
		throws PortalException {

		DLFileVersion dlFileVersion = null;

		Date lockExpirationDate = null;
		long lockUserId = 0;
		String lockUserName = StringPool.BLANK;
		String type = null;

		Lock lock = dlFileEntry.getLock();

		if ((lock == null) || excludeWorkingCopy) {
			dlFileVersion = DLFileVersionLocalServiceUtil.getFileVersion(
				dlFileEntry.getFileEntryId(), dlFileEntry.getVersion());

			type = SyncConstants.TYPE_FILE;
		}
		else {
			try {
				dlFileVersion = DLFileVersionLocalServiceUtil.getFileVersion(
					dlFileEntry.getFileEntryId(),
					DLFileEntryConstants.PRIVATE_WORKING_COPY_VERSION);

				lockExpirationDate = lock.getExpirationDate();
				lockUserId = lock.getUserId();
				lockUserName = lock.getUserName();
				type = SyncConstants.TYPE_PRIVATE_WORKING_COPY;
			}
			catch (NoSuchFileVersionException nsfve) {

				// Publishing a checked out file entry on a staged site will
				// get the staged file entry's lock even though the live
				// file entry is not checked out

				dlFileVersion = DLFileVersionLocalServiceUtil.getFileVersion(
					dlFileEntry.getFileEntryId(), dlFileEntry.getVersion());

				type = SyncConstants.TYPE_FILE;
			}
		}

		SyncDLObject syncDLObject = new SyncDLObjectImpl();

		syncDLObject.setCompanyId(dlFileVersion.getCompanyId());

		long userId = 0;
		String userName = StringPool.BLANK;

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if (permissionChecker != null) {
			User user = permissionChecker.getUser();

			userId = user.getUserId();
			userName = user.getFullName();
		}

		syncDLObject.setUserId(userId);
		syncDLObject.setUserName(userName);

		syncDLObject.setCreateDate(dlFileVersion.getCreateDate());
		syncDLObject.setModifiedDate(dlFileVersion.getModifiedDate());
		syncDLObject.setRepositoryId(dlFileVersion.getRepositoryId());
		syncDLObject.setParentFolderId(dlFileVersion.getFolderId());
		syncDLObject.setName(dlFileVersion.getTitle());
		syncDLObject.setExtension(dlFileVersion.getExtension());
		syncDLObject.setMimeType(dlFileVersion.getMimeType());
		syncDLObject.setDescription(dlFileVersion.getDescription());
		syncDLObject.setChangeLog(dlFileVersion.getChangeLog());
		syncDLObject.setExtraSettings(StringPool.BLANK);
		syncDLObject.setVersion(dlFileVersion.getVersion());
		syncDLObject.setVersionId(dlFileVersion.getFileVersionId());
		syncDLObject.setSize(dlFileVersion.getSize());

		if (calculateChecksum) {
			if (Validator.isNull(dlFileVersion.getChecksum())) {
				syncDLObject.setChecksum(getChecksum(dlFileVersion));
			}
			else {
				syncDLObject.setChecksum(dlFileVersion.getChecksum());
			}
		}

		syncDLObject.setEvent(event);
		syncDLObject.setLockExpirationDate(lockExpirationDate);
		syncDLObject.setLockUserId(lockUserId);
		syncDLObject.setLockUserName(lockUserName);
		syncDLObject.setType(type);
		syncDLObject.setTypePK(dlFileEntry.getFileEntryId());
		syncDLObject.setTypeUuid(dlFileEntry.getUuid());

		return syncDLObject;
	}

	public static SyncDLObject toSyncDLObject(DLFolder dlFolder, String event) {
		SyncDLObject syncDLObject = new SyncDLObjectImpl();

		syncDLObject.setCompanyId(dlFolder.getCompanyId());

		long userId = 0;
		String userName = StringPool.BLANK;

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if (permissionChecker != null) {
			User user = permissionChecker.getUser();

			userId = user.getUserId();
			userName = user.getFullName();
		}

		syncDLObject.setUserId(userId);
		syncDLObject.setUserName(userName);
		syncDLObject.setCreateDate(dlFolder.getCreateDate());
		syncDLObject.setModifiedDate(dlFolder.getModifiedDate());
		syncDLObject.setRepositoryId(dlFolder.getRepositoryId());
		syncDLObject.setParentFolderId(dlFolder.getParentFolderId());
		syncDLObject.setName(dlFolder.getName());
		syncDLObject.setExtension(StringPool.BLANK);
		syncDLObject.setMimeType(StringPool.BLANK);
		syncDLObject.setDescription(dlFolder.getDescription());
		syncDLObject.setChangeLog(StringPool.BLANK);
		syncDLObject.setExtraSettings(StringPool.BLANK);
		syncDLObject.setVersion(StringPool.BLANK);
		syncDLObject.setVersionId(0);
		syncDLObject.setSize(0);
		syncDLObject.setChecksum(StringPool.BLANK);
		syncDLObject.setEvent(event);
		syncDLObject.setLockExpirationDate(null);
		syncDLObject.setLockUserId(0);
		syncDLObject.setLockUserName(StringPool.BLANK);
		syncDLObject.setType(SyncConstants.TYPE_FOLDER);
		syncDLObject.setTypePK(dlFolder.getFolderId());
		syncDLObject.setTypeUuid(dlFolder.getUuid());

		return syncDLObject;
	}

	public static SyncDLObject toSyncDLObject(FileEntry fileEntry, String event)
		throws PortalException {

		return toSyncDLObject(fileEntry, event, false);
	}

	public static SyncDLObject toSyncDLObject(
			FileEntry fileEntry, String event, boolean calculateChecksum)
		throws PortalException {

		if (fileEntry.getModel() instanceof DLFileEntry) {
			DLFileEntry dlFileEntry = (DLFileEntry)fileEntry.getModel();

			return toSyncDLObject(dlFileEntry, event, calculateChecksum);
		}

		throw new PortalException(
			"FileEntry must be an instance of DLFileEntry");
	}

	public static SyncDLObject toSyncDLObject(Folder folder, String event)
		throws PortalException {

		if (folder.getModel() instanceof DLFolder) {
			DLFolder dlFolder = (DLFolder)folder.getModel();

			return toSyncDLObject(dlFolder, event);
		}

		throw new PortalException("Folder must be an instance of DLFolder");
	}

}