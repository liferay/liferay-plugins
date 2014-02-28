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

package com.liferay.sync.engine.model;

import com.liferay.sync.engine.service.SyncAccountService;

import java.util.List;
import java.util.Map;

/**
 * @author Shinn Lok
 */
public class SyncAccountModelListener implements ModelListener<SyncAccount> {

	@Override
	public void onCreate(SyncAccount syncAccount) {
	}

	@Override
	public void onRemove(SyncAccount syncAccount) {
		List<Long> activeSyncAccountIds =
			SyncAccountService.getActiveSyncAccountIds();

		activeSyncAccountIds.remove(syncAccount.getSyncAccountId());

		SyncAccountService.setActiveSyncAccountIds(activeSyncAccountIds);
	}

	@Override
	public void onUpdate(
		SyncAccount syncAccount, Map<String, Object> originalValues) {

		if (!originalValues.containsKey("active")) {
			return;
		}

		List<Long> activeSyncAccountIds =
			SyncAccountService.getActiveSyncAccountIds();

		if ((Boolean)originalValues.get("active")) {
			activeSyncAccountIds.remove(syncAccount.getSyncAccountId());
		}
		else {
			activeSyncAccountIds.add(syncAccount.getSyncAccountId());
		}

		SyncAccountService.setActiveSyncAccountIds(activeSyncAccountIds);
	}

}