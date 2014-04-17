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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.sync.engine.model.SyncAccount;
import com.liferay.sync.engine.model.SyncSite;
import com.liferay.sync.engine.service.SyncAccountService;
import com.liferay.sync.engine.service.SyncSiteService;
import com.liferay.sync.engine.util.FilePathNameUtil;
import com.liferay.sync.engine.util.LoggerUtil;
import com.liferay.sync.engine.util.PropsKeys;
import com.liferay.sync.engine.util.PropsUtil;
import com.liferay.sync.engine.util.SyncSystemTestUtil;

import java.io.BufferedReader;
import java.io.IOException;

import java.net.URL;

import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shinn Lok
 */
public class SyncSystemTest {

	@Test
	public void run() throws Exception {
		Path testsFilePath = getResourceFilePath("tests");

		DirectoryStream<Path> directoryStream = Files.newDirectoryStream(
			testsFilePath);

		for (Path testFilePath : directoryStream) {
			runTest(testFilePath);
		}
	}

	@Before
	public void setUp() throws Exception {
		PropsUtil.set(PropsKeys.SYNC_DATABASE_NAME, "sync-test");
		PropsUtil.set(
			PropsKeys.SYNC_LOGGER_CONFIGURATION_FILE, "sync-test-log4j.xml");

		LoggerUtil.initLogger();

		_liferayStarted = SyncSystemTestUtil.startLiferay();
	}

	@After
	public void tearDown() throws Exception {
		if (_liferayStarted) {
			cleanUp(10);

			SyncSystemTestUtil.stopLiferay();
		}
	}

	protected void activateSite(JsonNode stepJsonNode) throws Exception {
		String doAsSyncAccount = getString(stepJsonNode, "doAsSyncAccount");

		long syncAccountId = _syncAccountIds.get(doAsSyncAccount);

		String syncSiteName = getString(stepJsonNode, "name", "Guest");

		SyncSite syncSite = SyncSiteService.fetchSyncSite(
			_syncSiteIds.get(syncSiteName), syncAccountId);

		syncSite.setActive(true);

		SyncSiteService.update(syncSite);
	}

	protected void addAccount(JsonNode stepJsonNode) throws Exception {
		if (stepJsonNode == null) {
			return;
		}

		String name = getString(stepJsonNode, "name");

		SyncSystemTestUtil.addUser(name, _syncAccount.getSyncAccountId());

		String filePathName = FilePathNameUtil.fixFilePathName(
			System.getProperty("user.home") + "/liferay-sync-test/" + name);

		SyncAccount syncAccount = SyncAccountService.addSyncAccount(
			filePathName, 3, name + "@liferay.com", name, "test", false,
			"http://localhost:8080/api/jsonws");

		syncAccount.setActive(true);

		SyncAccountService.update(syncAccount);

		_syncAccountIds.put(name, syncAccount.getSyncAccountId());
	}

	protected void addFile(Path testFilePath, JsonNode stepJsonNode)
		throws Exception {

		SyncSite syncSite = getSyncSite(stepJsonNode);

		String dependency = getString(stepJsonNode, "dependency");

		Path targetFilePath = Paths.get(
			syncSite.getFilePathName() + "/" + dependency);

		Files.copy(getSourceFilePath(testFilePath, dependency), targetFilePath);
	}

	protected void addFolder(Path testFilePath, JsonNode stepJsonNode)
		throws Exception {

		SyncSite syncSite = getSyncSite(stepJsonNode);

		String dependency = getString(stepJsonNode, "dependency");

		final Path sourceFilePath = getSourceFilePath(testFilePath, dependency);
		final Path targetFilePath = Paths.get(
			syncSite.getFilePathName() + "/" + dependency);

		Files.walkFileTree(
			sourceFilePath,
			new SimpleFileVisitor<Path>() {

				@Override
				public FileVisitResult preVisitDirectory(
						Path filePath, BasicFileAttributes basicFileAttributes)
					throws IOException {

					Path relativeFilePath  = sourceFilePath.relativize(
						filePath);

					Files.createDirectories(
						targetFilePath.resolve(relativeFilePath));

					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFile(
						Path filePath, BasicFileAttributes basicFileAttributes)
					throws IOException {

					Path relativeFilePath  = sourceFilePath.relativize(
						filePath);

					Files.copy(
						filePath, targetFilePath.resolve(relativeFilePath));

					return FileVisitResult.CONTINUE;
				}

			});
	}

	protected void cleanUp(long delay) throws Exception {
		for (long syncAccountId : _syncAccountIds.values()) {
			SyncAccount syncAccount = SyncAccountService.fetchSyncAccount(
				syncAccountId);

			if (syncAccount == null) {
				return;
			}

			Files.walkFileTree(
				Paths.get(syncAccount.getFilePathName()),
				new SimpleFileVisitor<Path>() {

					@Override
					public FileVisitResult postVisitDirectory(
							Path filePath, IOException ioe)
						throws IOException {

						Files.deleteIfExists(filePath);

						return FileVisitResult.CONTINUE;
					}

					@Override
					public FileVisitResult visitFile(
							Path filePath,
							BasicFileAttributes basicFileAttributes)
						throws IOException {

						Files.deleteIfExists(filePath);

						return FileVisitResult.CONTINUE;
					}

				});
		}

		pause(delay);

		SyncEngine.stop();

		Files.walkFileTree(
			Paths.get(_filePathName),
			new SimpleFileVisitor<Path>() {

				@Override
				public FileVisitResult postVisitDirectory(
						Path filePath, IOException ioe)
					throws IOException {

					Files.delete(filePath);

					return FileVisitResult.CONTINUE;
				}

			});

		for (long syncAccountId : _syncAccountIds.values()) {
			SyncAccount syncAccount = SyncAccountService.fetchSyncAccount(
				syncAccountId);

			SyncSystemTestUtil.deleteUser(
				syncAccount.getUserId(), _syncAccount.getSyncAccountId());

			SyncAccountService.deleteSyncAccount(syncAccountId);
		}

		SyncAccountService.deleteSyncAccount(_syncAccount.getSyncAccountId());
	}

	protected void executeSteps(Path testFilePath, JsonNode rootJsonNode)
		throws Exception {

		JsonNode stepsJsonNode = rootJsonNode.get("steps");

		Iterator<JsonNode> stepsJsonNodeIterator = stepsJsonNode.elements();

		while (stepsJsonNodeIterator.hasNext()) {
			JsonNode stepJsonNode = stepsJsonNodeIterator.next();

			String action = getString(stepJsonNode, "action");

			if (action.equals("activateSite")) {
				activateSite(stepJsonNode);
			}
			else if (action.equals("addAccount")) {
				addAccount(stepJsonNode);
			}
			else if (action.equals("addFile")) {
				addFile(testFilePath, stepJsonNode);
			}
			else if (action.equals("addFolder")) {
				addFolder(testFilePath, stepJsonNode);
			}
			else if (action.equals("cleanUp")) {
				long delay = getLong(stepJsonNode, "delay", 5);

				cleanUp(delay);
			}
			else if (action.equals("pause")) {
				long delay = getLong(stepJsonNode, "delay", 5);

				pause(delay);
			}
			else if (action.equals("verifyFile")) {
				verifyFile(stepJsonNode);
			}
		}
	}

	protected long getLong(JsonNode jsonNode, String key, long defaultValue) {
		JsonNode childJsonNode = jsonNode.get(key);

		if (childJsonNode == null) {
			return defaultValue;
		}

		return childJsonNode.longValue();
	}

	protected Path getResourceFilePath(String name) throws Exception {
		Class<?> clazz = getClass();

		URL url = clazz.getResource(name);

		return Paths.get(url.toURI());
	}

	protected Path getSourceFilePath(Path testFilePath, String dependency)
		throws Exception {

		StringBuilder sb = new StringBuilder();

		sb.append("tests/dependencies/");

		Path testFileNameFilePath = testFilePath.getFileName();

		String testFileName = testFileNameFilePath.toString();

		sb.append(FilenameUtils.removeExtension(testFileName));

		sb.append("/");

		sb.append(dependency);

		return getResourceFilePath(sb.toString());
	}

	protected String getString(JsonNode jsonNode, String key) {
		return getString(jsonNode, key, null);
	}

	protected String getString(
		JsonNode jsonNode, String key, String defaultValue) {

		JsonNode childJsonNode = jsonNode.get(key);

		if (childJsonNode == null) {
			return defaultValue;
		}

		return childJsonNode.textValue();
	}

	protected SyncSite getSyncSite(JsonNode stepJsonNode) {
		String syncSiteName = getString(stepJsonNode, "site", "Guest");

		String doAsSyncAccount = getString(stepJsonNode, "doAsSyncAccount");

		return SyncSiteService.fetchSyncSite(
			_syncSiteIds.get(syncSiteName),
			_syncAccountIds.get(doAsSyncAccount));
	}

	protected void pause(long delay) throws Exception {
		Thread.sleep(delay * 1000);
	}

	protected void runTest(Path testFilePath) throws Exception {
		if (Files.isDirectory(testFilePath)) {
			return;
		}

		_testFileName = String.valueOf(testFilePath.getFileName());

		SyncEngine.start();

		_filePathName = FilePathNameUtil.fixFilePathName(
			System.getProperty("user.home") + "/liferay-sync-test");

		_syncAccount = SyncAccountService.addSyncAccount(
			_filePathName + "/test", 10, "test@liferay.com", "test", "test",
			false, "http://localhost:8080/api/jsonws");

		long guestGroupId = SyncSystemTestUtil.getGuestGroupId(
			_syncAccount.getSyncAccountId());

		_syncSiteIds.put("Guest", guestGroupId);

		BufferedReader bufferedReader = Files.newBufferedReader(
			testFilePath, Charset.defaultCharset());

		ObjectMapper objectMapper = new ObjectMapper();

		JsonNode rootJsonNode = objectMapper.readTree(bufferedReader);

		executeSteps(testFilePath, rootJsonNode);
	}

	protected void verifyFile(JsonNode stepJsonNode) throws Exception {
		SyncSite syncSite = getSyncSite(stepJsonNode);

		String source = getString(stepJsonNode, "source");

		Path targetFilePath = Paths.get(
			syncSite.getFilePathName() + "/" + source);

		String operation = getString(stepJsonNode, "operation", "exists");

		String testName = FilenameUtils.removeExtension(_testFileName);

		if ((operation.equals("exists") && Files.notExists(targetFilePath)) ||
			(operation.equals("notExists") && Files.exists(targetFilePath))) {

			Assert.fail("Test " + testName + " failed.");
		}
		else {
			_logger.info("Test {} passed.", testName);
		}
	}

	private static Logger _logger = LoggerFactory.getLogger(
		SyncSystemTest.class);

	private static String _filePathName;
	private static boolean _liferayStarted;
	private static SyncAccount _syncAccount;
	private static Map<String, Long> _syncAccountIds =
		new HashMap<String, Long>();
	private static Map<String, Long> _syncSiteIds = new HashMap<String, Long>();
	private static String _testFileName;

}