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

package com.liferay.sync.engine.documentlibrary.event;

import com.liferay.sync.engine.BaseTestCase;
import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.model.SyncSite;
import com.liferay.sync.engine.service.SyncFileService;
import com.liferay.sync.engine.service.SyncSiteService;
import com.liferay.sync.engine.util.FileUtil;
import com.liferay.sync.engine.util.SyncFileTestUtil;
import com.liferay.sync.engine.util.SyncSiteTestUtil;

import java.nio.file.Files;
import java.nio.file.Path;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Shinn Lok
 */
public class GetSyncDLObjectUpdateEventTest extends BaseTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		_syncSite = SyncSiteTestUtil.addSyncSite(
			10158, FileUtil.getFilePathName(filePathName, "test-site"), 10185,
			syncAccount.getSyncAccountId());

		_syncSite.setRemoteSyncTime(System.currentTimeMillis());
	}

	@After
	@Override
	public void tearDown() throws Exception {
		super.tearDown();

		SyncSiteService.deleteSyncSite(_syncSite.getSyncSiteId());

		for (SyncFile syncFile : _syncFiles) {
			SyncFileService.deleteSyncFile(syncFile);
		}
	}

	@Test
	public void testRunAddFile() throws Exception {
		setResponse("dependencies/get_sync_dl_object_update_event_add.json");

		run();

		_syncFiles = SyncFileService.findSyncFiles(
			syncAccount.getSyncAccountId());

		Assert.assertEquals(3, _syncFiles.size());

		Path filePath = FileUtil.getFilePath(
			_syncSite.getFilePathName(), "test.txt");

		Assert.assertTrue(Files.exists(filePath));
	}

	@Test
	public void testRunMoveFile() throws Exception {
		setResponse("dependencies/get_sync_dl_object_update_event_move.json");

		Path sourceFilePath = FileUtil.getFilePath(
			_syncSite.getFilePathName(), "Document_1.txt");

		Files.createFile(sourceFilePath);

		SyncFileTestUtil.addFileSyncFile(
			sourceFilePath.toString(), 0, _syncSite.getGroupId(),
			syncAccount.getSyncAccountId(), 27382);

		Path folderFilePath = FileUtil.getFilePath(
			_syncSite.getFilePathName(), "test-folder");

		Files.createDirectory(folderFilePath);

		SyncFileTestUtil.addFolderSyncFile(
			folderFilePath.toString(), 0, _syncSite.getGroupId(),
			syncAccount.getSyncAccountId(), 27488);

		run();

		_syncFiles = SyncFileService.findSyncFiles(
			syncAccount.getSyncAccountId());

		Assert.assertEquals(4, _syncFiles.size());

		Path targetFilePath = FileUtil.getFilePath(
			folderFilePath.toString(), "test.txt");

		Assert.assertTrue(Files.exists(targetFilePath));
	}

	@Test
	public void testRunMoveFileToTrash() throws Exception {
		setResponse("dependencies/get_sync_dl_object_update_event_trash.json");

		Path filePath = FileUtil.getFilePath(
			_syncSite.getFilePathName(), "test.txt");

		Files.createFile(filePath);

		SyncFileTestUtil.addFileSyncFile(
			filePath.toString(), 0, _syncSite.getGroupId(),
			syncAccount.getSyncAccountId(), 27382);

		run();

		_syncFiles = SyncFileService.findSyncFiles(
			syncAccount.getSyncAccountId());

		Assert.assertEquals(2, _syncFiles.size());

		Assert.assertFalse(Files.exists(filePath));
	}

	@Test
	public void testRunRestoreFileFromTrash() throws Exception {
		setResponse(
			"dependencies/get_sync_dl_object_update_event_restore.json");

		run();

		_syncFiles = SyncFileService.findSyncFiles(
			syncAccount.getSyncAccountId());

		Assert.assertEquals(3, _syncFiles.size());

		Path filePath = FileUtil.getFilePath(
			_syncSite.getFilePathName(), "test.txt");

		Assert.assertTrue(Files.exists(filePath));
	}

	@Test
	public void testRunUpdateFile() throws Exception {
		setResponse("dependencies/get_sync_dl_object_update_event_update.json");

		Path sourceFilePath = FileUtil.getFilePath(
			_syncSite.getFilePathName(), "test.txt");

		Files.createFile(sourceFilePath);

		SyncFileTestUtil.addFileSyncFile(
			sourceFilePath.toString(), 0, _syncSite.getGroupId(),
			syncAccount.getSyncAccountId(), 27382);

		run();

		_syncFiles = SyncFileService.findSyncFiles(
			syncAccount.getSyncAccountId());

		Assert.assertEquals(3, _syncFiles.size());

		Path targetFilePath = FileUtil.getFilePath(
			_syncSite.getFilePathName(), "test2.txt");

		Assert.assertTrue(Files.exists(targetFilePath));

		SyncFile syncFile = SyncFileService.fetchSyncFile(
			targetFilePath.toString());

		Assert.assertEquals("Updated Description", syncFile.getDescription());
	}

	protected void run() {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("companyId", _syncSite.getCompanyId());
		parameters.put("repositoryId", _syncSite.getGroupId());
		parameters.put("syncSite", _syncSite);

		GetSyncDLObjectUpdateEvent getSyncDLObjectUpdateEvent =
			new GetSyncDLObjectUpdateEvent(
				syncAccount.getSyncAccountId(), parameters);

		getSyncDLObjectUpdateEvent.run();
	}

	private List<SyncFile> _syncFiles;
	private SyncSite _syncSite;

}