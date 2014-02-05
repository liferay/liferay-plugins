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

package com.liferay.sync.engine.documentlibrary.event;

import com.liferay.sync.engine.BaseTestCase;
import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.service.SyncAccountService;
import com.liferay.sync.engine.service.SyncFileService;
import com.liferay.sync.engine.util.FileUtil;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Shinn Lok
 */
@RunWith(PowerMockRunner.class)
public class GetAllSyncDLObjectsEventTest extends BaseTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		_testFolderSyncFile = SyncFileService.addSyncFile(
			null, null, null,
			FileUtil.getFileKey(syncAccount.getFilePathName()),
			syncAccount.getFilePathName(), null, "test", 0, 0,
			syncAccount.getSyncAccountId(), SyncFile.TYPE_FOLDER);
	}

	@After
	@Override
	public void tearDown() throws Exception {
		super.tearDown();

		SyncAccountService.deleteSyncAccount(syncAccount.getSyncAccountId());
		SyncFileService.deleteSyncFile(_testFolderSyncFile.getSyncFileId());

		for (SyncFile syncFile : _syncFiles) {
			SyncFileService.deleteSyncFile(syncFile.getSyncFileId());
		}
	}

	@Test
	public void testRun() throws Exception {
		setMockPostResponse("dependencies/get_all_sync_dl_objects.json");

		GetAllSyncDLObjectsEvent getAllSyncDLObjectsEvent =
			new GetAllSyncDLObjectsEvent(syncAccount.getSyncAccountId(), null);

		getAllSyncDLObjectsEvent.run();

		_syncFiles = SyncFileService.findSyncFiles(
			syncAccount.getSyncAccountId());

		Assert.assertEquals(3, _syncFiles.size());

		Path filePath = Paths.get(filePathName + "/Document_1.txt");

		Assert.assertTrue(Files.exists(filePath));
	}

	private List<SyncFile> _syncFiles;
	private SyncFile _testFolderSyncFile;

}