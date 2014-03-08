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
import com.liferay.sync.engine.session.Session;
import com.liferay.sync.engine.session.SessionManager;
import com.liferay.sync.engine.upgrade.util.UpgradeUtil;
import com.liferay.sync.engine.util.FilePathNameUtil;
import com.liferay.sync.engine.util.LoggerUtil;
import com.liferay.sync.engine.util.PropsKeys;
import com.liferay.sync.engine.util.PropsUtil;
import com.liferay.sync.engine.util.StreamUtil;

import java.io.IOException;
import java.io.InputStream;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.ResponseHandler;

import org.junit.After;
import org.junit.Before;

import org.mockito.Mockito;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shinn Lok
 */
@PowerMockIgnore("javax.crypto.*")
@PrepareForTest(SessionManager.class)
public abstract class BaseTestCase {

	@Before
	public void setUp() throws Exception {
		PropsUtil.set(PropsKeys.SYNC_DATABASE_NAME, "sync-test");
		PropsUtil.set(
			PropsKeys.SYNC_LOGGER_CONFIGURATION_FILE, "sync-test-log4j.xml");

		LoggerUtil.initLogger();

		UpgradeUtil.upgrade();

		filePathName = FilePathNameUtil.fixFilePathName(
			System.getProperty("user.home") + "/liferay-sync-test");

		syncAccount = SyncAccountService.addSyncAccount(
			filePathName, 10, "test@liferay.com", "test",
			"http://localhost:8080/api/jsonws", false);

		syncAccount.setActive(true);

		SyncAccountService.update(syncAccount);

		PowerMockito.mockStatic(SessionManager.class);

		_session = Mockito.mock(Session.class);
	}

	@After
	public void tearDown() throws Exception {
		Path filePath = Paths.get(filePathName);

		FileUtils.deleteDirectory(filePath.toFile());

		SyncAccountService.deleteSyncAccount(syncAccount.getSyncAccountId());
	}

	protected String readResponse(String fileName) {
		InputStream inputStream = null;

		try {
			Class<?> clazz = getClass();

			inputStream = clazz.getResourceAsStream(fileName);

			return IOUtils.toString(inputStream);
		}
		catch (IOException ioe) {
			_logger.error(ioe.getMessage(), ioe);

			return null;
		}
		finally {
			StreamUtil.cleanUp(inputStream);
		}
	}

	protected void setGetResponse(String fileName) throws Exception {
		Mockito.when(
			SessionManager.getSession(Mockito.anyLong())
		).thenReturn(
			_session
		);

		Mockito.when(
			_session.executeGet(
				Mockito.anyString(), Mockito.any(ResponseHandler.class))
		).thenReturn(
			readResponse(fileName)
		);
	}

	protected void setPostResponse(String fileName) throws Exception {
		Mockito.when(
			SessionManager.getSession(Mockito.anyLong())
		).thenReturn(
			_session
		);

		Mockito.when(
			_session.executePost(
				Mockito.anyString(), Mockito.anyMap(),
				Mockito.any(ResponseHandler.class))
		).thenReturn(
			readResponse(fileName)
		);
	}

	protected String filePathName;
	protected SyncAccount syncAccount;

	private static Logger _logger = LoggerFactory.getLogger(BaseTestCase.class);

	private Session _session;

}