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

import com.liferay.sync.engine.SyncEngine;
import com.liferay.sync.engine.service.SyncAccountService;

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
		Set<Long> activeSyncAccountIds =
			SyncAccountService.getActiveSyncAccountIds();

		activeSyncAccountIds.remove(syncAccount.getSyncAccountId());

		SyncAccountService.setActiveSyncAccountIds(activeSyncAccountIds);

		try {
			SyncEngine.cancelSyncAccountTasks(syncAccount.getSyncAccountId());
		}
		catch (Exception e) {
			_logger.error(e.getMessage(), e);
		}
	}

	@Override
	public void onUpdate(
		SyncAccount syncAccount, Map<String, Object> originalValues) {

		if (!originalValues.containsKey("active")) {
			return;
		}

		Set<Long> activeSyncAccountIds =
			SyncAccountService.getActiveSyncAccountIds();

		if ((Boolean)originalValues.get("active")) {
			activeSyncAccountIds.remove(syncAccount.getSyncAccountId());

			try {
				SyncEngine.cancelSyncAccountTasks(
					syncAccount.getSyncAccountId());
			}
			catch (Exception e) {
				_logger.error(e.getMessage(), e);
			}
		}
		else {
			activeSyncAccountIds.add(syncAccount.getSyncAccountId());

			try {
				SyncEngine.scheduleSyncAccountTasks(
					syncAccount.getSyncAccountId());
			}
			catch (Exception e) {
				_logger.error(e.getMessage(), e);
			}
		}

		SyncAccountService.setActiveSyncAccountIds(activeSyncAccountIds);
	}

	private static Logger _logger = LoggerFactory.getLogger(SyncEngine.class);

}