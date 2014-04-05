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

import com.liferay.sync.engine.service.SyncSiteService;

import java.util.Map;
import java.util.Set;

/**
 * @author Shinn Lok
 */
public class SyncSiteModelListener implements ModelListener<SyncSite> {

	@Override
	public void onCreate(SyncSite syncSite) {
	}

	@Override
	public void onRemove(SyncSite syncSite) {
		Set<Long> activeSyncSiteIds = SyncSiteService.getActiveSyncSiteIds(
			syncSite.getSyncAccountId());

		activeSyncSiteIds.remove(syncSite.getSyncSiteId());
	}

	@Override
	public void onUpdate(
		SyncSite syncSite, Map<String, Object> originalValues) {

		if (!originalValues.containsKey("active")) {
			return;
		}

		Set<Long> activeSyncSiteIds = SyncSiteService.getActiveSyncSiteIds(
			syncSite.getSyncAccountId());

		if ((Boolean)originalValues.get("active")) {
			activeSyncSiteIds.remove(syncSite.getSyncSiteId());
		}
		else {
			activeSyncSiteIds.add(syncSite.getSyncSiteId());
		}
	}

}