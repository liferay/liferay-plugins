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
import com.liferay.sync.engine.model.SyncAccount;
import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.service.SyncAccountService;
import com.liferay.sync.engine.service.SyncFileService;

import java.io.File;

import java.util.List;

import org.apache.commons.io.FileUtils;

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

		_filePath = System.getProperty("user.home") + "/liferay-sync-test";

		_syncAccount = SyncAccountService.addSyncAccount(
			_filePath, "test@liferay.com", "test",
			"http://localhost:8080/api/jsonws");

		_filePathSyncFile = SyncFileService.addSyncFile(
			_syncAccount.getFilePath(), "test", 0, 0,
			_syncAccount.getSyncAccountId(), SyncFile.TYPE_FOLDER, 0);
	}

	@After
	public void tearDown() throws Exception {
		File file = new File(_filePath);

		FileUtils.deleteDirectory(file);

		SyncAccountService.deleteSyncAccount(_syncAccount.getSyncAccountId());
		SyncFileService.deleteSyncFile(_filePathSyncFile.getSyncFileId());

		for (SyncFile syncFile : _syncFiles) {
			SyncFileService.deleteSyncFile(syncFile.getSyncFileId());
		}
	}

	@Test
	public void testRun() throws Exception {
		setMockPostResponse("dependencies/get_all_sync_dl_objects.json");

		GetAllSyncDLObjectsEvent getAllSyncDLObjectsEvent =
			new GetAllSyncDLObjectsEvent(_syncAccount.getSyncAccountId(), null);

		getAllSyncDLObjectsEvent.run();

		_syncFiles = SyncFileService.findSyncFiles(
			_syncAccount.getSyncAccountId());

		Assert.assertEquals(_syncFiles.size(), 2);

		File file = new File(_filePath + "/Document_1.txt");

		Assert.assertTrue(file.exists());
	}

	private String _filePath;
	private SyncFile _filePathSyncFile;
	private SyncAccount _syncAccount;
	private List<SyncFile> _syncFiles;

}