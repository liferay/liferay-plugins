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
import com.liferay.sync.engine.util.HttpUtil;

import java.io.File;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Shinn Lok
 */
@PowerMockIgnore({"javax.crypto.*"})
@PrepareForTest(HttpUtil.class)
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
		SyncFileService.deleteSyncFile(_syncFile.getSyncFileId());
	}

	@Test
	public void testRun() throws Exception {
		PowerMockito.mockStatic(HttpUtil.class);
		
		Class<?> clazz = getClass();

		InputStream inputStream = clazz.getResourceAsStream(
			"dependencies/test_1.json");

		String response = IOUtils.toString(inputStream);

		inputStream.close();

		Mockito.when(
			HttpUtil.executePost(
				Mockito.anyLong(), Mockito.anyString(), Mockito.anyMap())
		).thenReturn(
			response
		);

		GetAllSyncDLObjectsEvent getAllSyncDLObjectsEvent =
			new GetAllSyncDLObjectsEvent(_syncAccount.getSyncAccountId(), null);

		getAllSyncDLObjectsEvent.run();

		_syncFile = SyncFileService.fetchSyncFile(
			_filePath + "/Document_1.txt", _syncAccount.getSyncAccountId());

		Assert.assertNotNull(_syncFile);

		File file = new File(_filePath + "/Document_1.txt");

		Assert.assertTrue(file.exists());
	}

	private String _filePath;
	private SyncFile _filePathSyncFile;
	private SyncAccount _syncAccount;
	private SyncFile _syncFile;

}