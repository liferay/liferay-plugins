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

import com.liferay.sync.engine.SyncEngine;
import com.liferay.sync.engine.service.SyncAccountService;
import com.liferay.sync.engine.session.SessionManager;

import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shinn Lok
 */
public class SyncAccountModelListener implements ModelListener<SyncAccount> {

	@Override
	public void onCreate(SyncAccount syncAccount) {
	}

	@Override
	public void onRemove(SyncAccount syncAccount) {
		deactivateSyncAccount(syncAccount);

		SessionManager.removeSession(syncAccount.getSyncAccountId());
	}

	@Override
	public void onUpdate(
		SyncAccount syncAccount, Map<String, Object> originalValues) {

		if (originalValues.containsKey("active")) {
			if ((Boolean)originalValues.get("active")) {
				deactivateSyncAccount(syncAccount);
			}
			else {
				activateSyncAccount(syncAccount);
			}
		}

		if (originalValues.containsKey("login") ||
			originalValues.containsKey("password")) {

			SessionManager.removeSession(syncAccount.getSyncAccountId());
		}
	}

	protected void activateSyncAccount(SyncAccount syncAccount) {
		Set<Long> activeSyncAccountIds =
			SyncAccountService.getActiveSyncAccountIds();

		activeSyncAccountIds.add(syncAccount.getSyncAccountId());

		SyncEngine.scheduleSyncAccountTasks(syncAccount.getSyncAccountId());
	}

	protected void deactivateSyncAccount(SyncAccount syncAccount) {
		Set<Long> activeSyncAccountIds =
			SyncAccountService.getActiveSyncAccountIds();

		activeSyncAccountIds.remove(syncAccount.getSyncAccountId());

		try {
			SyncEngine.cancelSyncAccountTasks(syncAccount.getSyncAccountId());
		}
		catch (Exception e) {
			_logger.error(e.getMessage(), e);
		}
	}

	private static Logger _logger = LoggerFactory.getLogger(SyncEngine.class);

}