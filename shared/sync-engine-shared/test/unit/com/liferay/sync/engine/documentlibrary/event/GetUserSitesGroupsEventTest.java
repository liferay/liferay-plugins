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
import com.liferay.sync.engine.model.SyncSite;
import com.liferay.sync.engine.service.SyncAccountService;
import com.liferay.sync.engine.service.SyncSiteService;

import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Shinn Lok
 */
public class GetUserSitesGroupsEventTest extends BaseTestCase {

	@After
	@Override
	public void tearDown() throws Exception {
		super.tearDown();

		SyncAccountService.deleteSyncAccount(syncAccount.getSyncAccountId());

		for (SyncSite syncSite : _syncSites) {
			SyncSiteService.deleteSyncSite(syncSite.getSyncSiteId());
		}
	}

	@Test
	public void testRun() throws Exception {
		setResponse("dependencies/get_user_sites_groups.json");

		GetUserSitesGroupsEvent getUserSitesGroupsEvent =
			new GetUserSitesGroupsEvent(syncAccount.getSyncAccountId(),
			Collections.<String, Object>emptyMap());

		getUserSitesGroupsEvent.run();

		_syncSites = SyncSiteService.findSyncSites(
			syncAccount.getSyncAccountId());

		Assert.assertEquals(2, _syncSites.size());
	}

	private List<SyncSite> _syncSites;

}