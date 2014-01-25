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
import com.liferay.sync.engine.model.SyncAccount;
import com.liferay.sync.engine.service.SyncAccountService;

import java.io.File;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Michael Young
 */
@RunWith(PowerMockRunner.class)
public class WatcherTestCase extends BaseTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		_filePath = System.getProperty("user.home") + "/liferay-sync-test";

		_file = new File(_filePath);

		_file.mkdir();

		_syncAccount = SyncAccountService.addSyncAccount(
			_filePath, "test@liferay.com", "test",
			"http://localhost:8080/api/jsonws");
	}

	@After
	public void tearDown() throws Exception {
		FileUtils.deleteDirectory(_file);

		SyncAccountService.deleteSyncAccount(_syncAccount.getSyncAccountId());
	}

	@Test
	public void testRun() throws Exception {
		SyncWatchEventProcessor syncWatchEventProcessor =
			new SyncWatchEventProcessor();

		syncWatchEventProcessor.process();

		WatchEventListener watchEventListener = new SyncSiteWatchEventListener(
			_syncAccount.getSyncAccountId());

		Path filePath = Paths.get(_syncAccount.getFilePath());

		Watcher watcher = new Watcher(filePath, true, watchEventListener);

		watcher.run();
	}

	private File _file;
	private String _filePath;
	private SyncAccount _syncAccount;

}