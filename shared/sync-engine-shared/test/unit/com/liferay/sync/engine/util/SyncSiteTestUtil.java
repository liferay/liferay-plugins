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

package com.liferay.sync.engine.util;

import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.model.SyncSite;
import com.liferay.sync.engine.service.SyncFileService;
import com.liferay.sync.engine.service.SyncSiteService;
import com.liferay.sync.engine.service.persistence.SyncSitePersistence;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Shinn Lok
 */
public class SyncSiteTestUtil {

	public static SyncSite addSyncSite(
			long companyId, String filePathName, long groupId,
			long syncAccountId)
		throws Exception {

		// Sync site

		SyncSite syncSite = new SyncSite();

		syncSite.setCompanyId(companyId);
		syncSite.setFilePathName(filePathName);
		syncSite.setFriendlyURL(filePathName);
		syncSite.setGroupId(groupId);
		syncSite.setName(filePathName);
		syncSite.setSyncAccountId(syncAccountId);

		SyncSitePersistence syncSitePersistence =
			SyncSiteService.getSyncSitePersistence();

		syncSitePersistence.create(syncSite);

		// Sync file

		Files.createDirectories(Paths.get(filePathName));

		SyncFileService.addSyncFile(
			null, null, filePathName, filePathName, null, filePathName, 0,
			groupId, syncSite.getSyncAccountId(), SyncFile.TYPE_SYSTEM);

		return syncSite;
	}

}