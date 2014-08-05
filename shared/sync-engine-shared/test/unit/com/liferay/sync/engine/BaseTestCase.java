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

package com.liferay.sync.engine;

import com.liferay.sync.engine.model.SyncAccount;
import com.liferay.sync.engine.service.SyncAccountService;
import com.liferay.sync.engine.upgrade.util.UpgradeUtil;
import com.liferay.sync.engine.util.FileUtil;
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
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shinn Lok
 */
@PowerMockIgnore("javax.crypto.*")
@PrepareForTest({EntityUtils.class, HttpClientBuilder.class, SyncEngine.class})
@RunWith(PowerMockRunner.class)
public abstract class BaseTestCase {

	@Before
	public void setUp() throws Exception {
		PropsUtil.set(PropsKeys.SYNC_DATABASE_NAME, "sync-test");
		PropsUtil.set(
			PropsKeys.SYNC_LOGGER_CONFIGURATION_FILE, "sync-test-log4j.xml");

		LoggerUtil.initLogger();

		UpgradeUtil.upgrade();

		filePathName = FileUtil.getFilePathName(
			System.getProperty("user.home"), "liferay-sync-test");

		syncAccount = SyncAccountService.addSyncAccount(
			filePathName, "test@liferay.com", 1, "test", "test", 5, null, false,
			"http://localhost:8080");

		syncAccount.setActive(true);
		syncAccount.setState(SyncAccount.STATE_CONNECTED);

		SyncAccountService.update(syncAccount);

		PowerMockito.mockStatic(SyncEngine.class);

		Mockito.when(
			SyncEngine.isRunning()
		).thenReturn(
			true
		);
	}

	@After
	public void tearDown() throws Exception {
		Path filePath = Paths.get(filePathName);

		FileUtils.deleteDirectory(filePath.toFile());

		SyncAccountService.deleteSyncAccount(syncAccount.getSyncAccountId());
	}

	protected final InputStream getInputStream(String fileName) {
		Class<?> clazz = getClass();

		return clazz.getResourceAsStream(fileName);
	}

	protected CloseableHttpClient mockCloseableHttpClient(String fileName)
		throws Exception {

		CloseableHttpClient closeableHttpClient = Mockito.mock(
			CloseableHttpClient.class);

		Mockito.when(
			closeableHttpClient.execute(
				Mockito.any(HttpHost.class), Mockito.any(HttpRequest.class),
				Mockito.any(ResponseHandler.class),
				Mockito.any(HttpContext.class))
		).thenCallRealMethod();

		CloseableHttpResponse closeableHttpResponse = mockCloseableHttpResponse(
			mockHttpEntity(fileName));

		Mockito.when(
			closeableHttpClient.execute(
				Mockito.any(HttpHost.class), Mockito.any(HttpRequest.class),
				Mockito.any(HttpContext.class))
		).thenReturn(
			closeableHttpResponse
		);

		return closeableHttpClient;
	}

	protected CloseableHttpResponse mockCloseableHttpResponse(
		HttpEntity httpEntity) {

		CloseableHttpResponse closeableHttpResponse = Mockito.mock(
			CloseableHttpResponse.class);

		Mockito.when(
			closeableHttpResponse.getEntity()
		).thenReturn(
			httpEntity
		);

		StatusLine statusLine = mockStatusLine();

		Mockito.when(
			closeableHttpResponse.getStatusLine()
		).thenReturn(
			statusLine
		);

		return closeableHttpResponse;
	}

	protected void mockEntityUtils(String fileName) throws Exception {
		PowerMockito.mockStatic(EntityUtils.class);

		Mockito.when(
			EntityUtils.toString(Mockito.any(HttpEntity.class))
		).thenReturn(
			readResponse(fileName)
		);
	}

	protected void mockHttpClientBuilder(String fileName) throws Exception {
		PowerMockito.mockStatic(HttpClientBuilder.class);

		HttpClientBuilder httpClientbuilder = Mockito.mock(
			HttpClientBuilder.class);

		CloseableHttpClient closeableHttpClient = mockCloseableHttpClient(
			fileName);

		Mockito.when(
			httpClientbuilder.build()
		).thenReturn(
			closeableHttpClient
		);

		Mockito.when(
			HttpClientBuilder.create()
		).thenReturn(
			httpClientbuilder
		);
	}

	protected HttpEntity mockHttpEntity(String fileName) throws Exception {
		HttpEntity httpEntity = Mockito.mock(HttpEntity.class);

		Mockito.when(
			httpEntity.getContent()
		).thenReturn(
			getInputStream(fileName)
		);

		return httpEntity;
	}

	protected StatusLine mockStatusLine() {
		StatusLine statusLine = Mockito.mock(StatusLine.class);

		Mockito.when(
			statusLine.getStatusCode()
		).thenReturn(
			HttpStatus.SC_OK
		);

		return statusLine;
	}

	protected String readResponse(String fileName) {
		InputStream inputStream = null;

		try {
			inputStream = getInputStream(fileName);

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

	protected void setResponse(String fileName) throws Exception {
		mockEntityUtils(fileName);
		mockHttpClientBuilder(fileName);
	}

	protected String filePathName;
	protected SyncAccount syncAccount;

	private static Logger _logger = LoggerFactory.getLogger(BaseTestCase.class);

}