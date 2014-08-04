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

package com.liferay.sync.engine.service;

import com.liferay.sync.engine.BaseTestCase;
import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.service.persistence.SyncFilePersistence;
import com.liferay.sync.engine.util.FileUtil;
import com.liferay.sync.engine.util.SyncFileTestUtil;

import java.nio.file.Paths;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Shinn Lok
 */
public class SyncFileServiceTest extends BaseTestCase {

	@Test
	public void testDeleteFolderSyncFile() throws Exception {
		List<SyncFile> syncFiles = SyncFileService.findSyncFiles(
			syncAccount.getSyncAccountId());

		Assert.assertEquals(1, syncFiles.size());

		SyncFile folderSyncFileA = SyncFileTestUtil.addFolderSyncFile(
			FileUtil.getFilePathName(filePathName, "a"),
			syncAccount.getSyncAccountId());

		SyncFile folderSyncFileAA = SyncFileTestUtil.addFolderSyncFile(
			FileUtil.getFilePathName(filePathName, "a", "a"),
			folderSyncFileA.getTypePK(), syncAccount.getSyncAccountId());

		SyncFileTestUtil.addFolderSyncFile(
			FileUtil.getFilePathName(filePathName, "a", "b"),
			folderSyncFileA.getTypePK(), syncAccount.getSyncAccountId());

		SyncFileTestUtil.addFolderSyncFile(
			FileUtil.getFilePathName(filePathName, "a", "a", "a"),
			folderSyncFileAA.getTypePK(), syncAccount.getSyncAccountId());

		SyncFileTestUtil.addFileSyncFile(
			FileUtil.getFilePathName(filePathName, "a", "b"),
			folderSyncFileA.getTypePK(), syncAccount.getSyncAccountId());

		SyncFileTestUtil.addFileSyncFile(
			FileUtil.getFilePathName(filePathName, "a", "c"),
			folderSyncFileA.getTypePK(), syncAccount.getSyncAccountId());

		SyncFileTestUtil.addFileSyncFile(
			FileUtil.getFilePathName(filePathName, "a", "a", "a"),
			folderSyncFileAA.getTypePK(), syncAccount.getSyncAccountId());

		syncFiles = SyncFileService.findSyncFiles(
			syncAccount.getSyncAccountId());

		Assert.assertEquals(8, syncFiles.size());

		SyncFileService.deleteSyncFile(folderSyncFileA);

		syncFiles = SyncFileService.findSyncFiles(
			syncAccount.getSyncAccountId());

		Assert.assertEquals(1, syncFiles.size());
	}

	@Test
	public void testDoUpdateFolderSyncFile() throws Exception {
		SyncFile folderSyncFileA = SyncFileTestUtil.addFolderSyncFile(
			FileUtil.getFilePathName(filePathName, "a"),
			syncAccount.getSyncAccountId());

		SyncFile folderSyncFileB = SyncFileTestUtil.addFolderSyncFile(
			FileUtil.getFilePathName(filePathName, "b"),
			syncAccount.getSyncAccountId());

		SyncFile folderSyncFileAA = SyncFileTestUtil.addFolderSyncFile(
			FileUtil.getFilePathName(filePathName, "a", "a"),
			folderSyncFileA.getTypePK(), syncAccount.getSyncAccountId());

		SyncFile fileSyncFileAA = SyncFileTestUtil.addFileSyncFile(
			FileUtil.getFilePathName(filePathName, "a", "a.txt"),
			folderSyncFileA.getTypePK(), syncAccount.getSyncAccountId());

		SyncFileService.updateSyncFile(
			Paths.get(FileUtil.getFilePathName(filePathName, "b", "a")),
			folderSyncFileB.getTypePK(), folderSyncFileA);

		SyncFilePersistence syncFilePersistence =
			SyncFileService.getSyncFilePersistence();

		folderSyncFileAA = syncFilePersistence.queryForId(
			folderSyncFileAA.getTypePK());

		Assert.assertEquals(
			FileUtil.getFilePathName(filePathName, "b", "a", "a"),
			folderSyncFileAA.getFilePathName());

		fileSyncFileAA = syncFilePersistence.queryForId(
			fileSyncFileAA.getTypePK());

		Assert.assertEquals(
			FileUtil.getFilePathName(filePathName, "b", "a", "a.txt"),
			fileSyncFileAA.getFilePathName());
	}

}