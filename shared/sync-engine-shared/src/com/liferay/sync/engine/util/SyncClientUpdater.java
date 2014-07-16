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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.sync.engine.model.SyncVersion;
import com.liferay.sync.engine.service.SyncPropService;

import java.awt.Desktop;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shinn Lok
 */
public class SyncClientUpdater {

	public static void cancelAutoUpdateChecker() {
		if (_autoUpdateChecker != null) {
			_autoUpdateChecker.cancel(true);

			_autoUpdateChecker = null;
		}
	}

	public static SyncVersion getLatestSyncVersion() throws Exception {
		HttpResponse httpResponse = execute(PropsValues.SYNC_UPDATE_CHECK_URL);

		if (httpResponse == null) {
			return null;
		}

		HttpEntity httpEntity = httpResponse.getEntity();

		ObjectMapper objectMapper = new ObjectMapper();

		return objectMapper.readValue(
			httpEntity.getContent(), new TypeReference<SyncVersion>() {});
	}

	public static void ignoreUpdateBuild(SyncVersion syncVersion)
		throws Exception {

		SyncPropService.updateSyncProp(
			"ignoreUpdateBuild", syncVersion.getBuildNumber());
	}

	public static boolean isUpdateAvailable(SyncVersion syncVersion)
		throws Exception {

		if (syncVersion == null) {
			syncVersion = getLatestSyncVersion();
		}

		if ((syncVersion != null) &&
			(syncVersion.getBuildNumber() > ReleaseInfo.getBuildNumber())) {

			return true;
		}

		return false;
	}

	public static void scheduleAutoUpdateChecker(int updateCheckInterval) {
		if (_autoUpdateChecker != null) {
			_autoUpdateChecker.cancel(true);
		}

		if (updateCheckInterval <= 0) {
			return;
		}

		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				try {
					SyncVersion latestSyncVersion = getLatestSyncVersion();

					if ((SyncPropService.getInteger("ignoreUpdateBuild") !=
							latestSyncVersion.getBuildNumber()) &&
						isUpdateAvailable(latestSyncVersion)) {

						SyncEngineUtil.fireSyncEngineStateChanged(
							SyncEngineUtil.SYNC_ENGINE_UPDATE_AVAILABLE);
					}
				}
				catch (Exception e) {
					_logger.error(e.getMessage(), e);
				}
			}

		};

		_autoUpdateChecker = _scheduledExecutorService.scheduleAtFixedRate(
			runnable, 0, updateCheckInterval, TimeUnit.SECONDS);
	}

	public static void update(SyncVersion syncVersion) throws Exception {
		if (syncVersion == null) {
			syncVersion = getLatestSyncVersion();
		}

		if (syncVersion == null) {
			return;
		}

		HttpResponse httpResponse = execute(syncVersion.getUrl());

		if (httpResponse == null) {
			return;
		}

		Header header = httpResponse.getFirstHeader("Content-Type");

		HeaderElement headerElement = header.getElements()[0];

		String mimeType = headerElement.getName();

		Path filePath = null;

		if (mimeType.equals("application/x-msdownload")) {
			filePath = Files.createTempFile(null, ".msi");
		}
		else {
			filePath = Files.createTempFile(null, ".dmg");
		}

		HttpEntity entity = httpResponse.getEntity();

		Files.copy(
			entity.getContent(), filePath, StandardCopyOption.REPLACE_EXISTING);

		Desktop desktop = Desktop.getDesktop();

		desktop.open(filePath.toFile());
	}

	protected static HttpResponse execute(String url) {
		Builder builder = RequestConfig.custom();

		builder.setSocketTimeout(30000);
		builder.setConnectTimeout(30000);

		HttpGet httpGet = new HttpGet(url);

		RequestConfig requestConfig = builder.build();

		httpGet.setConfig(requestConfig);

		HttpClient httpClient = HttpClients.createDefault();

		try {
			return httpClient.execute(httpGet);
		}
		catch (Exception e) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(e.getMessage(), e);
			}

			return null;
		}
	}

	private static Logger _logger = LoggerFactory.getLogger(
		SyncClientUpdater.class);

	private static Future<?> _autoUpdateChecker;
	private static ScheduledExecutorService _scheduledExecutorService =
		Executors.newSingleThreadScheduledExecutor();

}