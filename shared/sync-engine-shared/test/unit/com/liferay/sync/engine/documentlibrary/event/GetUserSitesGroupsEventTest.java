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
import com.liferay.sync.engine.model.SyncSite;
import com.liferay.sync.engine.service.SyncAccountService;
import com.liferay.sync.engine.service.SyncSiteService;

import java.util.List;

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
public class GetUserSitesGroupsEventTest extends BaseTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		_syncAccount = SyncAccountService.addSyncAccount(
			null, "test@liferay.com", "test",
			"http://localhost:8080/api/jsonws");
	}

	@After
	public void tearDown() throws Exception {
		SyncAccountService.deleteSyncAccount(_syncAccount.getSyncAccountId());

		for (SyncSite syncSite : _syncSites) {
			SyncSiteService.deleteSyncSite(syncSite.getSyncSiteId());
		}
	}

	@Test
	public void testRun() throws Exception {
		setMockPostResponse("dependencies/get_user_sites_groups.json");

		GetUserSitesGroupsEvent getUserSitesGroupsEvent =
			new GetUserSitesGroupsEvent(_syncAccount.getSyncAccountId(), null);

		getUserSitesGroupsEvent.run();

		_syncSites = SyncSiteService.findSyncSites(
			_syncAccount.getSyncAccountId());

		Assert.assertEquals(_syncSites.size(), 2);
	}

	private SyncAccount _syncAccount;
	private List<SyncSite> _syncSites;

}