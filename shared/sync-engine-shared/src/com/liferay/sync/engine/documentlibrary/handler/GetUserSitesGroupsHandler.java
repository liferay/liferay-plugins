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

import com.liferay.sync.engine.documentlibrary.event.Event;
import com.liferay.sync.engine.model.SyncAccount;
import com.liferay.sync.engine.model.SyncSite;
import com.liferay.sync.engine.service.SyncAccountService;
import com.liferay.sync.engine.service.SyncSiteService;
import com.liferay.sync.engine.util.FileUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Shinn Lok
 */
public class GetUserSitesGroupsHandler extends BaseJSONHandler {

	public GetUserSitesGroupsHandler(Event event) {
		super(event);
	}

	@Override
	protected void processResponse(String response) throws Exception {
		Set<Long> remoteSyncSiteIds = new HashSet<Long>();

		ObjectMapper objectMapper = new ObjectMapper();

		List<SyncSite> remoteSyncSites = objectMapper.readValue(
			response, new TypeReference<List<SyncSite>>() {});

		for (SyncSite remoteSyncSite : remoteSyncSites) {
			SyncSite localSyncSite = SyncSiteService.fetchSyncSite(
				remoteSyncSite.getGroupId(), getSyncAccountId());

			if (localSyncSite == null) {
				SyncAccount syncAccount = SyncAccountService.fetchSyncAccount(
					getSyncAccountId());

				String name = remoteSyncSite.getName();

				if (!FileUtil.isValidFileName(name)) {
					name = String.valueOf(remoteSyncSite.getGroupId());
				}

				remoteSyncSite.setFilePathName(
					syncAccount.getFilePathName() + "/" + name);

				remoteSyncSite.setSyncAccountId(getSyncAccountId());

				SyncSiteService.update(remoteSyncSite);

				remoteSyncSiteIds.add(remoteSyncSite.getSyncSiteId());
			}
			else {
				localSyncSite.setDescription(remoteSyncSite.getDescription());
				localSyncSite.setFriendlyURL(remoteSyncSite.getFriendlyURL());
				localSyncSite.setName(remoteSyncSite.getName());
				localSyncSite.setType(remoteSyncSite.getType());
				localSyncSite.setTypeSettings(remoteSyncSite.getTypeSettings());
				localSyncSite.setSite(remoteSyncSite.getSite());

				SyncSiteService.update(localSyncSite);

				remoteSyncSiteIds.add(localSyncSite.getSyncSiteId());
			}
		}

		List<SyncSite> localSyncSites = SyncSiteService.findSyncSites(
			getSyncAccountId());

		for (SyncSite localSyncSite : localSyncSites) {
			if (remoteSyncSiteIds.contains(localSyncSite.getSyncSiteId())) {
				continue;
			}

			SyncSiteService.deleteSyncSite(localSyncSite.getSyncSiteId());
		}
	}

}