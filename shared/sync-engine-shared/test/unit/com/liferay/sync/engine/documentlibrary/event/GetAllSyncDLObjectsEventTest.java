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
public class GetAllSyncDLObjectsEventTest extends BaseTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		_syncSite = SyncSiteTestUtil.addSyncSite(
			10158, FileUtil.getFilePathName(filePathName, "test-site"), 10185,
			syncAccount.getSyncAccountId());
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
	public void testRun() throws Exception {
		setResponse("dependencies/get_all_sync_dl_objects.json");

		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("companyId", _syncSite.getCompanyId());
		parameters.put("lastAccessTime", 0);
		parameters.put("repositoryId", _syncSite.getGroupId());
		parameters.put("syncSite", _syncSite);

		GetSyncDLObjectUpdateEvent getSyncDLObjectUpdateEvent =
			new GetSyncDLObjectUpdateEvent(
				syncAccount.getSyncAccountId(), parameters);

		getSyncDLObjectUpdateEvent.run();

		_syncFiles = SyncFileService.findSyncFiles(
			syncAccount.getSyncAccountId());

		Assert.assertEquals(3, _syncFiles.size());

		Path filePath = FileUtil.getFilePath(
			_syncSite.getFilePathName(), "test.txt");

		Assert.assertTrue(Files.exists(filePath));
	}

	private List<SyncFile> _syncFiles;
	private SyncSite _syncSite;

}