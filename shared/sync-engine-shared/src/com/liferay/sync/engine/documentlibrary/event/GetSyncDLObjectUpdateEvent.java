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

import com.liferay.sync.engine.documentlibrary.handler.GetSyncDLObjectUpdateHandler;
import com.liferay.sync.engine.documentlibrary.handler.Handler;
import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.model.SyncSite;
import com.liferay.sync.engine.service.SyncFileService;
import com.liferay.sync.engine.service.SyncSiteService;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Shinn Lok
 */
public class GetSyncDLObjectUpdateEvent extends BaseEvent {

	public GetSyncDLObjectUpdateEvent(
		long syncAccountId, Map<String, Object> parameters) {

		super(syncAccountId, _URL_PATH, parameters);
	}

	@Override
	protected Handler<Void> getHandler() {
		return new GetSyncDLObjectUpdateHandler(this);
	}

	@Override
	protected void processRequest() throws Exception {
		SyncSite syncSite = (SyncSite)getParameterValue("syncSite");

		// Refetch for updated last remote sync time

		syncSite = SyncSiteService.fetchSyncSite(
			syncSite.getGroupId(), syncSite.getSyncAccountId());

		if (syncSite.getRemoteSyncTime() == 0) {
			String filePathName = syncSite.getFilePathName();

			SyncFile syncFile = SyncFileService.fetchSyncFile(
				filePathName, getSyncAccountId());

			if (syncFile == null) {
				Files.createDirectories(Paths.get(filePathName));

				SyncFileService.addSyncFile(
					null, null, null, filePathName, null, filePathName, 0,
					syncSite.getGroupId(), syncSite.getSyncAccountId(),
					SyncFile.TYPE_SYSTEM);
			}
		}

		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("companyId", syncSite.getCompanyId());
		parameters.put("lastAccessTime", syncSite.getRemoteSyncTime());
		parameters.put("repositoryId", syncSite.getGroupId());

		executePost(_URL_PATH, parameters);
	}

	private static final String _URL_PATH =
		"/sync-web.syncdlobject/get-sync-dl-object-update";

}