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

import com.liferay.sync.engine.model.SyncAccount;
import com.liferay.sync.engine.service.SyncAccountService;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shinn Lok
 */
public class GetSyncContextEvent extends BaseEvent {

	public GetSyncContextEvent(
		long syncAccountId, Map<String, Object> parameters) {

		super(syncAccountId, _URL_PATH, parameters);
	}

	@Override
	public void run() {
		try {
			String response = processRequest();

			if (handleRemoteException(response)) {
				return;
			}

			processResponse(response);
		}
		catch (Exception e) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(e.getMessage(), e);
			}
		}
	}

	@Override
	protected void processResponse(String response) throws Exception {
		SyncAccount syncAccount = SyncAccountService.fetchSyncAccount(
			getSyncAccountId());

		syncAccount.setState(SyncAccount.STATE_CONNECTED);

		SyncAccountService.update(syncAccount);
	}

	private static final String _URL_PATH =
		"/sync-web.syncdlobject/get-sync-context";

	private static Logger _logger = LoggerFactory.getLogger(
		GetSyncContextEvent.class);

}