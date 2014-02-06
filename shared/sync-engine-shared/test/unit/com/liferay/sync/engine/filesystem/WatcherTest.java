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
import com.liferay.sync.engine.util.FileUtil;

import java.io.BufferedWriter;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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

		ScheduledExecutorService scheduledExecutorService =
			Executors.newSingleThreadScheduledExecutor();

		scheduledExecutorService.scheduleAtFixedRate(
			new SyncWatchEventProcessor(), 0, 1, TimeUnit.SECONDS);

		WatchEventListener watchEventListener = new SyncSiteWatchEventListener(
			syncAccount.getSyncAccountId());

		Path filePath = Paths.get(syncAccount.getFilePathName());

		_watcher = new Watcher(filePath, true, watchEventListener);

		Thread thread = new Thread(_watcher);

		thread.start();
	}

	@After
	@Override
	public void tearDown() throws Exception {
		_watcher.close();

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
	public void testRunAddFile() throws Exception {
		setMockPostResponse("dependencies/watcher_test_add_file.json");

		Path filePath = Paths.get(_syncSite.getFilePathName() + "/test.txt");

		Files.createFile(filePath);

		Thread.sleep(1000);

		_syncFiles = SyncFileService.findSyncFiles(
			syncAccount.getSyncAccountId());

		Assert.assertEquals(3, _syncFiles.size());
	}

	@Test
	public void testRunDeleteFile() throws Exception {
		setMockPostResponse("dependencies/watcher_test_delete_file.json");

		Path filePath = Paths.get(_syncSite.getFilePathName() + "/test.txt");

		Files.createFile(filePath);

		Thread.sleep(1000);

		Files.delete(filePath);

		Thread.sleep(1000);

		_syncFiles = SyncFileService.findSyncFiles(
			syncAccount.getSyncAccountId());

		Assert.assertEquals(2, _syncFiles.size());
		Assert.assertNull(
			SyncFileService.fetchSyncFile(
				FilePathNameUtil.getFilePathName(filePath),
				syncAccount.getSyncAccountId()));
	}

	@Test
	public void testRunModifyFile() throws Exception {
		setMockPostResponse("dependencies/watcher_test_modify_file.json");

		Path filePath = Paths.get(_syncSite.getFilePathName() + "/test.txt");

		Files.createFile(filePath);

		Thread.sleep(1000);

		BufferedWriter bufferedWriter = Files.newBufferedWriter(
			filePath, StandardCharsets.UTF_8);

		bufferedWriter.write("Hello World");

		bufferedWriter.close();

		Thread.sleep(1000);

		_syncFiles = SyncFileService.findSyncFiles(
			syncAccount.getSyncAccountId());

		Assert.assertEquals(3, _syncFiles.size());

		SyncFile syncFile = SyncFileService.fetchSyncFile(
			FilePathNameUtil.getFilePathName(filePath),
			syncAccount.getSyncAccountId());

		Assert.assertEquals(
			FileUtil.getChecksum(filePath), syncFile.getChecksum());
		Assert.assertEquals(Files.size(filePath), syncFile.getSize());
	}

	@Test
	public void testRunMoveFile() throws Exception {
		setMockPostResponse("dependencies/watcher_test_move_file.json");

		Path sourceFilePath = Paths.get(
			_syncSite.getFilePathName() + "/test.txt");

		Files.createFile(sourceFilePath);

		Path destinationFilePath = Paths.get(
			_syncSite.getFilePathName() + "/test");

		Files.createDirectory(destinationFilePath);

		Thread.sleep(1000);

		Files.move(
			sourceFilePath,
			destinationFilePath.resolve(sourceFilePath.getFileName()));

		Thread.sleep(1000);

		_syncFiles = SyncFileService.findSyncFiles(
			syncAccount.getSyncAccountId());

		Assert.assertEquals(4, _syncFiles.size());
		Assert.assertNotNull(
			SyncFileService.fetchSyncFile(
				FilePathNameUtil.getFilePathName(destinationFilePath),
				syncAccount.getSyncAccountId()));
	}

	@Test
	public void testRunRenameFile() throws Exception {
		setMockPostResponse("dependencies/watcher_test_rename_file.json");

		Path sourceFilePath = Paths.get(
			_syncSite.getFilePathName() + "/test.txt");

		Files.createFile(sourceFilePath);

		Thread.sleep(1000);

		Path destinationFilePath = Paths.get(
			_syncSite.getFilePathName() + "/test2.txt");

		Files.move(sourceFilePath, destinationFilePath);

		Thread.sleep(1000);

		_syncFiles = SyncFileService.findSyncFiles(
			syncAccount.getSyncAccountId());

		Assert.assertEquals(3, _syncFiles.size());
		Assert.assertNotNull(
			SyncFileService.fetchSyncFile(
				FilePathNameUtil.getFilePathName(destinationFilePath),
				syncAccount.getSyncAccountId()));
	}

	private List<SyncFile> _syncFiles;
	private SyncSite _syncSite;
	private Watcher _watcher;

}