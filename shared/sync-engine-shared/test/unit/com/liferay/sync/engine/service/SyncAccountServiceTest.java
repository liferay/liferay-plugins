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

package com.liferay.sync.engine.service;

import com.liferay.sync.engine.BaseTestCase;
import com.liferay.sync.engine.model.SyncAccount;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Shinn Lok
 */
@RunWith(PowerMockRunner.class)
public class SyncAccountServiceTest extends BaseTestCase {

	@After
	public void tearDown() {
		SyncAccountService.deleteSyncAccount(_syncAccount.getSyncAccountId());
	}

	@Test
	public void testAddAccount() throws Exception {
		SyncAccount syncAccount = SyncAccountService.addSyncAccount(
			null, "test@liferay.com", "test",
			"http://localhost:8080/api/jsonws");

		_syncAccount = SyncAccountService.fetchSyncAccount(
			syncAccount.getSyncAccountId());

		Assert.assertNotNull(_syncAccount);
	}

	private SyncAccount _syncAccount;

}