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

package com.liferay.sync.engine.documentlibrary.handler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.sync.engine.documentlibrary.event.AddFileEntryEvent;
import com.liferay.sync.engine.documentlibrary.event.Event;
import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.service.SyncFileService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shinn Lok
 */
public class AddFileFolderHandler extends BaseJSONHandler {

	public AddFileFolderHandler(Event event) {
		super(event);
	}

	@Override
	protected boolean handlePortalException(String exception) throws Exception {
		if (exception.equals(
				"com.liferay.sync.SyncDLObjectChecksumException")) {

			if (_logger.isDebugEnabled()) {
				_logger.debug("Handling exception {}", exception);
			}

			AddFileEntryEvent addFileEntryEvent = new AddFileEntryEvent(
				getSyncAccountId(), getParameters());

			addFileEntryEvent.run();

			return true;
		}

		return super.handlePortalException(exception);
	}

	@Override
	protected void processResponse(String response) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();

		SyncFile remoteSyncFile = objectMapper.readValue(
			response, new TypeReference<SyncFile>() {});

		SyncFile localSyncFile = (SyncFile)getParameterValue("syncFile");

		localSyncFile.setCompanyId(remoteSyncFile.getCompanyId());
		localSyncFile.setCreateTime(remoteSyncFile.getCreateTime());
		localSyncFile.setExtension(remoteSyncFile.getExtension());
		localSyncFile.setExtraSettings(remoteSyncFile.getExtraSettings());
		localSyncFile.setLockExpirationDate(
			remoteSyncFile.getLockExpirationDate());
		localSyncFile.setLockUserId(remoteSyncFile.getLockUserId());
		localSyncFile.setLockUserName(remoteSyncFile.getLockUserName());
		localSyncFile.setModifiedTime(remoteSyncFile.getModifiedTime());
		localSyncFile.setParentFolderId(remoteSyncFile.getParentFolderId());
		localSyncFile.setSize(remoteSyncFile.getSize());
		localSyncFile.setState(SyncFile.STATE_SYNCED);
		localSyncFile.setSyncAccountId(getSyncAccountId());
		localSyncFile.setTypePK(remoteSyncFile.getTypePK());
		localSyncFile.setTypeUuid(remoteSyncFile.getTypeUuid());
		localSyncFile.setUiEvent(SyncFile.UI_EVENT_UPLOADED);
		localSyncFile.setVersion(remoteSyncFile.getVersion());

		SyncFileService.update(localSyncFile);
	}

	private static Logger _logger = LoggerFactory.getLogger(
		AddFileFolderHandler.class);

}