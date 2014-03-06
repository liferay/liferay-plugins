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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.service.SyncFileService;

import java.util.Map;

/**
 * @author Shinn Lok
 */
public class BaseCheckInCheckOutEvent extends BaseEvent {

	public BaseCheckInCheckOutEvent(
		long syncAccountId, String urlPath, Map<String, Object> parameters) {

		super(syncAccountId, urlPath, parameters);
	}

	@Override
	protected String processRequest() throws Exception {
		SyncFile syncFile = (SyncFile)getParameterValue("syncFile");

		syncFile.setState(SyncFile.STATE_IN_PROGRESS);

		SyncFileService.update(syncFile);

		return super.processRequest();
	}

	@Override
	protected void processResponse(String response) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();

		SyncFile remoteSyncFile = objectMapper.readValue(
			response, new TypeReference<SyncFile>() {});

		SyncFile localSyncFile = (SyncFile)getParameterValue("syncFile");

		localSyncFile.setLockExpirationDate(
			remoteSyncFile.getLockExpirationDate());
		localSyncFile.setLockUserId(remoteSyncFile.getLockUserId());
		localSyncFile.setLockUserName(remoteSyncFile.getLockUserName());
		localSyncFile.setState(SyncFile.STATE_SYNCED);

		SyncFileService.update(localSyncFile);
	}

}