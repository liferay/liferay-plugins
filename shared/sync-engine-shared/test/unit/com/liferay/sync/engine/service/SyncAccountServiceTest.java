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
import com.liferay.sync.engine.model.SyncAccount;
import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.model.SyncSite;
import com.liferay.sync.engine.util.FileUtil;
import com.liferay.sync.engine.util.SyncFileTestUtil;
import com.liferay.sync.engine.util.SyncSiteTestUtil;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Shinn Lok
 */
public class SyncAccountServiceTest extends BaseTestCase {

	@After
	@Override
	public void tearDown() throws Exception {
		super.tearDown();

		SyncAccountService.deleteSyncAccount(_syncAccount.getSyncAccountId());
	}

	@Test
	public void testAddAccount() throws Exception {
		SyncAccount syncAccount = SyncAccountService.addSyncAccount(
			filePathName, "test@liferay.com", 1, "test", "test", 5, null, false,
			"http://localhost:8080");

		_syncAccount = SyncAccountService.fetchSyncAccount(
			syncAccount.getSyncAccountId());

		Assert.assertNotNull(_syncAccount);
	}

	@Test
	public void testSetFilePathName() throws Exception {
		_syncAccount = SyncAccountService.addSyncAccount(
			filePathName, "test@liferay.com", 1, "test", "test", 5, null, false,
			"http://localhost:8080");

		SyncSite syncSite = SyncSiteTestUtil.addSyncSite(
			10158, FileUtil.getFilePathName(filePathName, "test-site"), 10185,
			_syncAccount.getSyncAccountId());

		SyncFile syncFile = SyncFileTestUtil.addFileSyncFile(
			FileUtil.getFilePathName(syncSite.getFilePathName(), "test.txt"), 0,
			_syncAccount.getSyncAccountId());

		String targetFilePathName =
			FileUtil.getFilePathName(
				System.getProperty("user.home"), "liferay-sync-test2");

		SyncAccountService.setFilePathName(
			_syncAccount.getSyncAccountId(), targetFilePathName);

		syncSite = SyncSiteService.fetchSyncSite(syncSite.getSyncSiteId());

		Assert.assertEquals(
			FileUtil.getFilePathName(targetFilePathName + "test-site"),
			syncSite.getFilePathName());

		syncFile = SyncFileService.fetchSyncFile(syncFile.getSyncFileId());

		Assert.assertEquals(
			FileUtil.getFilePathName(
				targetFilePathName, "test-site", "test.txt"),
			syncFile.getFilePathName());
	}

	private SyncAccount _syncAccount;

}