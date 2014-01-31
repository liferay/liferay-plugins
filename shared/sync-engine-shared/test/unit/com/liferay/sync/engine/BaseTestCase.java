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

package com.liferay.sync.engine;

import com.liferay.sync.engine.model.SyncAccount;
import com.liferay.sync.engine.service.SyncAccountService;
import com.liferay.sync.engine.upgrade.UpgradeProcessSuite;
import com.liferay.sync.engine.util.FilePathNameUtil;
import com.liferay.sync.engine.util.HttpUtil;
import com.liferay.sync.engine.util.LoggerUtil;
import com.liferay.sync.engine.util.PropsKeys;
import com.liferay.sync.engine.util.PropsUtil;

import java.io.InputStream;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import org.junit.After;
import org.junit.Before;

import org.mockito.Mockito;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;

/**
 * @author Shinn Lok
 */
@PowerMockIgnore("javax.crypto.*")
@PrepareForTest(HttpUtil.class)
public abstract class BaseTestCase {

	@Before
	public void setUp() throws Exception {
		PropsUtil.set(PropsKeys.SYNC_DATABASE_NAME, "sync-test");
		PropsUtil.set(
			PropsKeys.SYNC_LOGGER_CONFIGURATION_FILE, "sync-test-log4j.xml");

		LoggerUtil.initLogger();

		UpgradeProcessSuite upgradeProcessSuite = new UpgradeProcessSuite();

		upgradeProcessSuite.upgrade();

		filePathName = FilePathNameUtil.fixFilePathName(
			System.getProperty("user.home") + "/liferay-sync-test");

		syncAccount = SyncAccountService.addSyncAccount(
			filePathName, "test@liferay.com", "test",
			"http://localhost:8080/api/jsonws");
	}

	@After
	public void tearDown() throws Exception {
		Path filePath = Paths.get(filePathName);

		FileUtils.deleteDirectory(filePath.toFile());

		SyncAccountService.deleteSyncAccount(syncAccount.getSyncAccountId());
	}

	protected void setMockPostResponse(String fileName) throws Exception {
		PowerMockito.mockStatic(HttpUtil.class);

		Class<?> clazz = getClass();

		InputStream inputStream = clazz.getResourceAsStream(fileName);

		String response = IOUtils.toString(inputStream);

		inputStream.close();

		Mockito.when(
			HttpUtil.executePost(
				Mockito.anyLong(), Mockito.anyString(), Mockito.anyMap())
		).thenReturn(
			response
		);
	}

	protected String filePathName;
	protected SyncAccount syncAccount;

}