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
import com.liferay.sync.engine.service.SyncAccountService;

import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Shinn Lok
 */
public class SyncAccountModelListenerTest extends BaseTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		_syncAccountModelListener = new SyncAccountModelListener();

		SyncAccountService.registerModelListener(_syncAccountModelListener);
	}

	@After
	@Override
	public void tearDown() throws Exception {
		SyncAccountService.resetActiveSyncAccountIds();

		SyncAccountService.unregisterModelListener(_syncAccountModelListener);

		super.tearDown();
	}

	@Test
	public void testOnRemove() throws Exception {
		Set<Long> activeSyncAccountIds =
			SyncAccountService.getActiveSyncAccountIds();

		Assert.assertEquals(1, activeSyncAccountIds.size());

		SyncAccountService.deleteSyncAccount(syncAccount.getSyncAccountId());

		activeSyncAccountIds = SyncAccountService.getActiveSyncAccountIds();

		Assert.assertEquals(0, activeSyncAccountIds.size());
	}

	@Test
	public void testOnUpdate() throws Exception {
		Set<Long> activeSyncAccountIds =
			SyncAccountService.getActiveSyncAccountIds();

		Assert.assertEquals(1, activeSyncAccountIds.size());

		syncAccount.setActive(false);

		SyncAccountService.update(syncAccount);

		activeSyncAccountIds = SyncAccountService.getActiveSyncAccountIds();

		Assert.assertEquals(0, activeSyncAccountIds.size());
	}

	private SyncAccountModelListener _syncAccountModelListener;

}