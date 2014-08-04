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
import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.model.SyncSite;
import com.liferay.sync.engine.service.SyncAccountService;
import com.liferay.sync.engine.service.SyncFileService;
import com.liferay.sync.engine.service.SyncSiteService;
import com.liferay.sync.engine.util.FileUtil;
import com.liferay.sync.engine.util.LoggerUtil;
import com.liferay.sync.engine.util.PropsKeys;
import com.liferay.sync.engine.util.PropsUtil;
import com.liferay.sync.engine.util.SyncSystemTestUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import java.net.URL;

import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shinn Lok
 */
@RunWith(Parameterized.class)
public class SyncSystemTest {

	@BeforeClass
	public static void setUpClass() throws Exception {
		PropsUtil.set(PropsKeys.SYNC_DATABASE_NAME, "sync-test");
		PropsUtil.set(
			PropsKeys.SYNC_LOGGER_CONFIGURATION_FILE, "sync-test-log4j.xml");

		LoggerUtil.initLogger();

		_liferayStarted = SyncSystemTestUtil.startLiferay();
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		if (_liferayStarted) {
			cleanUp(10);

			SyncSystemTestUtil.stopLiferay();
		}
	}

	@Parameters
	public static Collection<Object[]> testFilePaths() throws Exception {
		Collection<Object[]> testFilePaths = new LinkedList<Object[]>();

		Path testsFilePath = getResourceFilePath("tests");

		DirectoryStream<Path> directoryStream = Files.newDirectoryStream(
			testsFilePath, "*.json");

		for (Path testFilePath : directoryStream) {
			testFilePaths.add(new Object[] {testFilePath});
		}

		return testFilePaths;
	}

	public SyncSystemTest(Path testFilePath) {
		_testFilePath = testFilePath;
	}

	@Test
	public void run() throws Exception {
		SyncEngine.start();

		_rootFilePathName = FileUtil.getFilePathName(
			System.getProperty("user.home"), "liferay-sync-test");

		_syncAccount = SyncAccountService.addSyncAccount(
			FileUtil.getFilePathName(_rootFilePathName, "test"),
			"test@liferay.com", Integer.MAX_VALUE, "test", "test", 5, null,
			false, "http://localhost:8080");

		SyncAccountService.update(_syncAccount);

		long guestGroupId = SyncSystemTestUtil.getGuestGroupId(
			_syncAccount.getSyncAccountId());

		_syncSiteIds.put("Guest", guestGroupId);

		BufferedReader bufferedReader = Files.newBufferedReader(
			_testFilePath, Charset.defaultCharset());

		ObjectMapper objectMapper = new ObjectMapper();

		JsonNode rootJsonNode = objectMapper.readTree(bufferedReader);

		executeSteps(_testFilePath, rootJsonNode);

		Path testFileNameFilePath = _testFilePath.getFileName();

		String testFileName = testFileNameFilePath.toString();

		_logger.info(
			"Test {} passed.", FilenameUtils.removeExtension(testFileName));
	}

	protected static void cleanUp(long delay) throws Exception {
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
			Paths.get(_rootFilePathName),
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

	protected static Path getResourceFilePath(String name) throws Exception {
		Class<?> clazz = SyncSystemTest.class;

		URL url = clazz.getResource(name);

		return Paths.get(url.toURI());
	}

	protected static void pause(long delay) throws Exception {
		Thread.sleep(delay * 1000);
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

		SyncSystemTestUtil.addUser(name, true, _syncAccount.getSyncAccountId());

		String filePathName = FileUtil.getFilePathName(
			System.getProperty("user.home"), "liferay-sync-test", name);

		SyncAccount syncAccount = SyncAccountService.addSyncAccount(
			filePathName, name + "@liferay.com", 1, name, "test", 5, null,
			false, "http://localhost:8080");

		SyncAccountService.activateSyncAccount(
			syncAccount.getSyncAccountId(), false);

		_syncAccountIds.put(name, syncAccount.getSyncAccountId());
	}

	protected void addFile(Path testFilePath, JsonNode stepJsonNode)
		throws Exception {

		SyncSite syncSite = getSyncSite(stepJsonNode);

		String dependency = getString(stepJsonNode, "dependency");

		Path dependencyFilePath = getDependencyFilePath(
			testFilePath, dependency);

		FileSystem fileSystem = FileSystems.getDefault();

		dependency = getString(
			stepJsonNode, "target",
			dependency.replace("common" + fileSystem.getSeparator(), ""));

		Path targetFilePath = Paths.get(
			FileUtil.getFilePathName(syncSite.getFilePathName(), dependency));

		Files.copy(dependencyFilePath, targetFilePath);
	}

	protected void addFolder(Path testFilePath, JsonNode stepJsonNode)
		throws Exception {

		SyncSite syncSite = getSyncSite(stepJsonNode);

		String dependency = getString(stepJsonNode, "dependency");

		final Path dependencyFilePath = getDependencyFilePath(
			testFilePath, dependency);

		FileSystem fileSystem = FileSystems.getDefault();

		final Path targetFilePath = Paths.get(
			FileUtil.getFilePathName(
				syncSite.getFilePathName(),
				dependency.replace("common" + fileSystem.getSeparator(), "")));

		Files.walkFileTree(
			dependencyFilePath,
			new SimpleFileVisitor<Path>() {

				@Override
				public FileVisitResult preVisitDirectory(
						Path filePath, BasicFileAttributes basicFileAttributes)
					throws IOException {

					Path relativeFilePath = dependencyFilePath.relativize(
						filePath);

					Files.createDirectories(
						targetFilePath.resolve(relativeFilePath));

					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFile(
						Path filePath, BasicFileAttributes basicFileAttributes)
					throws IOException {

					Path relativeFilePath = dependencyFilePath.relativize(
						filePath);

					Files.copy(
						filePath, targetFilePath.resolve(relativeFilePath));

					return FileVisitResult.CONTINUE;
				}

			});
	}

	protected void checkInFile(JsonNode stepJsonNode) throws Exception {
		SyncSite syncSite = getSyncSite(stepJsonNode);

		String source = getString(stepJsonNode, "source");

		SyncFile syncFile = SyncFileService.fetchSyncFile(
			FileUtil.getFilePathName(syncSite.getFilePathName(), source),
			syncSite.getSyncAccountId());

		SyncFileService.checkInSyncFile(syncSite.getSyncAccountId(), syncFile);
	}

	protected void checkOutFile(JsonNode stepJsonNode) throws Exception {
		SyncSite syncSite = getSyncSite(stepJsonNode);

		String source = getString(stepJsonNode, "source");

		SyncFile syncFile = SyncFileService.fetchSyncFile(
			FileUtil.getFilePathName(syncSite.getFilePathName(), source),
			syncSite.getSyncAccountId());

		SyncFileService.checkOutSyncFile(syncSite.getSyncAccountId(), syncFile);
	}

	protected void deleteFile(JsonNode stepJsonNode) throws Exception {
		SyncSite syncSite = getSyncSite(stepJsonNode);

		String source = getString(stepJsonNode, "source");

		Path targetFilePath = Paths.get(
			FileUtil.getFilePathName(syncSite.getFilePathName(), source));

		Files.deleteIfExists(targetFilePath);
	}

	protected void executeSteps(Path testFilePath, JsonNode rootJsonNode)
		throws Exception {

		JsonNode stepsJsonNode = rootJsonNode.get("steps");

		for (JsonNode stepJsonNode : stepsJsonNode) {
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
			else if (action.equals("checkInFile")) {
				checkInFile(stepJsonNode);
			}
			else if (action.equals("checkOutFile")) {
				checkOutFile(stepJsonNode);
			}
			else if (action.equals("cleanUp")) {
				long delay = getLong(stepJsonNode, "delay", 5);

				cleanUp(delay);
			}
			else if (action.equals("deleteFile")) {
				deleteFile(stepJsonNode);
			}
			else if (action.equals("moveFile")) {
				moveFile(stepJsonNode);
			}
			else if (action.equals("pause")) {
				long delay = getLong(stepJsonNode, "delay", 5);

				pause(delay);
			}
			else if (action.equals("startEngine")) {
				SyncEngine.start();
			}
			else if (action.equals("stopEngine")) {
				SyncEngine.stop();
			}
			else if (action.equals("startLiferay")) {
				SyncSystemTestUtil.startLiferay();
			}
			else if (action.equals("stopLiferay")) {
				SyncSystemTestUtil.stopLiferay();
			}
			else if (action.equals("verifyFile")) {
				verifyFile(stepJsonNode);
			}
		}
	}

	protected Path getDependencyFilePath(Path testFilePath, String dependency)
		throws Exception {

		String filePathName = null;

		if (dependency.contains("common")) {
			filePathName = FileUtil.getFilePathName(
				"tests", "dependencies", dependency);
		}
		else {
			Path testFileNameFilePath = testFilePath.getFileName();

			String testFileName = testFileNameFilePath.toString();

			filePathName = FileUtil.getFilePathName(
				"tests", "dependencies",
				FilenameUtils.removeExtension(testFileName), dependency);
		}

		return getResourceFilePath(filePathName);
	}

	protected long getLong(JsonNode jsonNode, String key, long defaultValue) {
		JsonNode childJsonNode = jsonNode.get(key);

		if (childJsonNode == null) {
			return defaultValue;
		}

		return childJsonNode.longValue();
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

		FileSystem fileSystem = FileSystems.getDefault();

		String value = childJsonNode.textValue();

		return value.replace("/", fileSystem.getSeparator());
	}

	protected SyncSite getSyncSite(JsonNode stepJsonNode) {
		String syncSiteName = getString(stepJsonNode, "site", "Guest");

		String doAsSyncAccount = getString(stepJsonNode, "doAsSyncAccount");

		return SyncSiteService.fetchSyncSite(
			_syncSiteIds.get(syncSiteName),
			_syncAccountIds.get(doAsSyncAccount));
	}

	protected Path getTargetFilePath(JsonNode jsonNode) {
		SyncSite syncSite = getSyncSite(jsonNode);

		String source = getString(jsonNode, "source");

		return Paths.get(
			FileUtil.getFilePathName(syncSite.getFilePathName(), source));
	}

	protected void moveFile(JsonNode stepJsonNode) throws Exception {
		SyncSite syncSite = getSyncSite(stepJsonNode);

		String source = getString(stepJsonNode, "source");
		String target = getString(stepJsonNode, "target");

		Path sourceFilePath = Paths.get(
			FileUtil.getFilePathName(syncSite.getFilePathName(), source));
		Path targetFilePath = Paths.get(
			FileUtil.getFilePathName(syncSite.getFilePathName(), target));

		Files.move(sourceFilePath, targetFilePath);
	}

	protected void verifyFile(JsonNode stepJsonNode) throws Exception {
		boolean testPassed = true;

		String operation = getString(stepJsonNode, "operation", "exists");

		if (operation.equals("equals")) {
			List<File> files = new ArrayList<File>();

			JsonNode filesJsonNode = stepJsonNode.get("files");

			for (JsonNode fileJsonNode : filesJsonNode) {
				Path targetFilePath = getTargetFilePath(fileJsonNode);

				files.add(targetFilePath.toFile());
			}

			if (!FileUtils.contentEquals(files.get(0), files.get(1))) {
				testPassed = false;
			}
		}
		else if (operation.equals("exists")) {
			if (Files.notExists(getTargetFilePath(stepJsonNode))) {
				testPassed = false;
			}
		}
		else if (operation.equals("notExists")) {
			if (Files.exists(getTargetFilePath(stepJsonNode))) {
				testPassed = false;
			}
		}

		if (!testPassed) {
			Path testFileNameFilePath = _testFilePath.getFileName();

			Assert.fail("Test " + testFileNameFilePath.toString() + " failed.");
		}
	}

	private static Logger _logger = LoggerFactory.getLogger(
		SyncSystemTest.class);

	private static boolean _liferayStarted;
	private static String _rootFilePathName;
	private static SyncAccount _syncAccount;
	private static Map<String, Long> _syncAccountIds =
		new HashMap<String, Long>();

	private Map<String, Long> _syncSiteIds = new HashMap<String, Long>();
	private Path _testFilePath;

}