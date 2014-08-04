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

package com.liferay.sync.engine.model;

import com.liferay.sync.engine.BaseTestCase;
import com.liferay.sync.engine.service.SyncSiteService;
import com.liferay.sync.engine.util.FileUtil;
import com.liferay.sync.engine.util.SyncSiteTestUtil;

import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Shinn Lok
 */
public class SyncSiteModelListenerTest extends BaseTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		_syncSiteModelListener = new SyncSiteModelListener();

		SyncSiteService.registerModelListener(_syncSiteModelListener);

		_syncSite = SyncSiteTestUtil.addSyncSite(
			10158, FileUtil.getFilePathName(filePathName, "test-site"), 10185,
			syncAccount.getSyncAccountId());

		_syncSite.setActive(true);

		SyncSiteService.update(_syncSite);
	}

	@After
	@Override
	public void tearDown() throws Exception {
		SyncSiteService.unregisterModelListener(_syncSiteModelListener);

		SyncSiteService.deleteSyncSite(_syncSite.getSyncSiteId());

		super.tearDown();
	}

	@Test
	public void testOnRemove() throws Exception {
		Set<Long> activeSyncSiteIds = SyncSiteService.getActiveSyncSiteIds(
			syncAccount.getSyncAccountId());

		Assert.assertEquals(1, activeSyncSiteIds.size());

		SyncSiteService.deleteSyncSite(_syncSite.getSyncSiteId());

		activeSyncSiteIds = SyncSiteService.getActiveSyncSiteIds(
			syncAccount.getSyncAccountId());

		Assert.assertEquals(0, activeSyncSiteIds.size());
	}

	@Test
	public void testOnUpdate() throws Exception {
		Set<Long> activeSyncSiteIds = SyncSiteService.getActiveSyncSiteIds(
			syncAccount.getSyncAccountId());

		Assert.assertEquals(1, activeSyncSiteIds.size());

		_syncSite.setActive(false);

		SyncSiteService.update(_syncSite);

		activeSyncSiteIds = SyncSiteService.getActiveSyncSiteIds(
			syncAccount.getSyncAccountId());

		Assert.assertEquals(0, activeSyncSiteIds.size());
	}

	private SyncSite _syncSite;
	private SyncSiteModelListener _syncSiteModelListener;

}