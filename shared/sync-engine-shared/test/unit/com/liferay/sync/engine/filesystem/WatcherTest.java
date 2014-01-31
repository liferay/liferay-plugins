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

package com.liferay.sync.engine.filesystem;

import com.liferay.sync.engine.BaseTestCase;
import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.model.SyncSite;
import com.liferay.sync.engine.model.SyncWatchEvent;
import com.liferay.sync.engine.service.SyncAccountService;
import com.liferay.sync.engine.service.SyncFileService;
import com.liferay.sync.engine.service.SyncSiteService;
import com.liferay.sync.engine.service.SyncWatchEventService;
import com.liferay.sync.engine.util.FilePathNameUtil;

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
 * @author Michael Young
 */
@RunWith(PowerMockRunner.class)
public class WatcherTest extends BaseTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		_syncSite = SyncSiteService.addSyncSite(
			filePathName + "/test-site", 10184, syncAccount.getSyncAccountId());
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();

		SyncAccountService.deleteSyncAccount(syncAccount.getSyncAccountId());

		SyncSiteService.deleteSyncSite(_syncSite.getSyncSiteId());

		for (SyncFile syncFile : _syncFiles) {
			SyncFileService.deleteSyncFile(syncFile.getSyncFileId());
		}

		for (SyncWatchEvent syncWatchEvent : SyncWatchEventService.findAll()) {
			SyncWatchEventService.deleteSyncWatchEvent(
				syncWatchEvent.getSyncWatchEventId());
		}
	}

	@Test
	public void testRun() throws Exception {
		setMockPostResponse("dependencies/watcher_test_add_file.json");

		SyncWatchEventProcessor syncWatchEventProcessor =
			new SyncWatchEventProcessor();

		syncWatchEventProcessor.process();

		WatchEventListener watchEventListener = new SyncSiteWatchEventListener(
			syncAccount.getSyncAccountId());

		Path filePath = Paths.get(syncAccount.getFilePathName());

		Watcher watcher = new Watcher(filePath, true, watchEventListener);

		Thread thread = new Thread(watcher);

		thread.start();

		Path addFilePath = Paths.get(_syncSite.getFilePathName() + "/test.txt");

		Files.createFile(addFilePath);

		thread.sleep(5000);

		_syncFiles = SyncFileService.findSyncFiles(
			syncAccount.getSyncAccountId());

		Assert.assertEquals(3, _syncFiles.size());

		setMockPostResponse("dependencies/watcher_test_rename_file.json");

		Path renameFilePath = Paths.get(
			_syncSite.getFilePathName() + "/test2.txt");

		Files.move(addFilePath, renameFilePath);

		thread.sleep(5000);

		_syncFiles = SyncFileService.findSyncFiles(
			syncAccount.getSyncAccountId());

		Assert.assertEquals(3, _syncFiles.size());

		Assert.assertNotNull(
			SyncFileService.fetchSyncFile(
				FilePathNameUtil.getFilePathName(renameFilePath),
				syncAccount.getSyncAccountId()));
	}

	private List<SyncFile> _syncFiles;
	private SyncSite _syncSite;

}