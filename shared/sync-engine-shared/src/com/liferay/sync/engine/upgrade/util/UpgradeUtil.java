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

package com.liferay.sync.engine.upgrade.util;

import com.liferay.sync.engine.service.SyncAccountService;
import com.liferay.sync.engine.service.SyncFileService;
import com.liferay.sync.engine.service.SyncPropService;
import com.liferay.sync.engine.service.SyncSiteService;
import com.liferay.sync.engine.service.SyncWatchEventService;
import com.liferay.sync.engine.service.persistence.SyncAccountPersistence;
import com.liferay.sync.engine.service.persistence.SyncFilePersistence;
import com.liferay.sync.engine.service.persistence.SyncPropPersistence;
import com.liferay.sync.engine.service.persistence.SyncSitePersistence;
import com.liferay.sync.engine.service.persistence.SyncWatchEventPersistence;
import com.liferay.sync.engine.util.LoggerUtil;
import com.liferay.sync.engine.util.PropsValues;
import com.liferay.sync.engine.util.ReleaseInfo;

import java.io.InputStream;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Shinn Lok
 */
public class UpgradeUtil {

	public static void upgrade() throws Exception {
		int buildNumber = SyncPropService.getInteger("buildNumber");

		if (buildNumber == 0) {
			createTables();

			Path configurationFilePath = Paths.get(
				PropsValues.SYNC_CONFIGURATION_DIRECTORY);

			Files.createDirectories(configurationFilePath.resolve("files"));

			Path loggerConfigurationFilePath = configurationFilePath.resolve(
				PropsValues.SYNC_LOGGER_CONFIGURATION_FILE);

			if (!Files.exists(loggerConfigurationFilePath)) {
				ClassLoader classLoader = LoggerUtil.class.getClassLoader();

				InputStream inputStream = classLoader.getResourceAsStream(
					PropsValues.SYNC_LOGGER_CONFIGURATION_FILE);

				Files.copy(inputStream, loggerConfigurationFilePath);
			}
		}
		else if (buildNumber == ReleaseInfo.getBuildNumber()) {
			return;
		}

		SyncPropService.updateSyncProp(
			"buildNumber", ReleaseInfo.getBuildNumber());
	}

	protected static void createTables() throws Exception {
		SyncAccountPersistence syncAccountPersistence =
			SyncAccountService.getSyncAccountPersistence();

		if (!syncAccountPersistence.isTableExists()) {
			syncAccountPersistence.createTable();
		}

		SyncFilePersistence syncFilePersistence =
			SyncFileService.getSyncFilePersistence();

		if (!syncFilePersistence.isTableExists()) {
			syncFilePersistence.createTable();
		}

		SyncPropPersistence syncPropPersistence =
			SyncPropService.getSyncPropPersistence();

		if (!syncPropPersistence.isTableExists()) {
			syncPropPersistence.createTable();
		}

		SyncSitePersistence syncSitePersistence =
			SyncSiteService.getSyncSitePersistence();

		if (!syncSitePersistence.isTableExists()) {
			syncSitePersistence.createTable();
		}

		SyncWatchEventPersistence syncWatchEventPersistence =
			SyncWatchEventService.getSyncWatchEventPersistence();

		if (!syncWatchEventPersistence.isTableExists()) {
			syncWatchEventPersistence.createTable();
		}
	}

}