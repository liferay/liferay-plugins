/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.servermanager.executor;

import com.liferay.portal.kernel.deploy.DeployManagerUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.plugin.PluginPackage;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.ServerDetector;
import com.liferay.portal.kernel.util.SystemProperties;
import com.liferay.servermanager.util.FileUploadUtil;
import com.liferay.servermanager.util.JSONKeys;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class PluginExecutor extends BaseExecutor {

	@Override
	public void executeCreate(
			HttpServletRequest request, JSONObject responseJSONObject,
			Queue<String> arguments)
		throws Exception {

		File tempFile = getTempFile(request, responseJSONObject);

		if (tempFile == null) {
			return;
		}

		String context = arguments.poll();

		DeployManagerUtil.deploy(tempFile, context);

		boolean success = FileUtils.deleteQuietly(tempFile.getParentFile());

		if (success) {
			return;
		}

		String message =
			"Unable to remove temp directory " + tempFile.getParentFile();

		_log.error(message);

		responseJSONObject.put(JSONKeys.ERROR, message);

		success = FileUtils.deleteQuietly(tempFile);

		if (success) {
			return;
		}

		message = "Unable to remove temp file " + tempFile;

		_log.error(message);

		responseJSONObject.put(JSONKeys.ERROR, message);
	}

	@Override
	public void executeDelete(
			HttpServletRequest request, JSONObject responseJSONObject,
			Queue<String> arguments)
		throws Exception {

		String context = arguments.poll();

		DeployManagerUtil.undeploy(context);
	}

	@Override
	public void executeRead(
		HttpServletRequest request, JSONObject responseJSONObject,
		Queue<String> arguments) {

		JSONObject pluginPackageJSONObject = JSONFactoryUtil.createJSONObject();

		String context = arguments.poll();

		PluginPackage pluginPackage =
			DeployManagerUtil.getInstalledPluginPackage(context);

		boolean installed = true;

		if (pluginPackage == null) {
			installed = false;
		}

		pluginPackageJSONObject.put("installed", installed);

		boolean started = true;

		if (pluginPackage == null) {
			started = false;
		}

		pluginPackageJSONObject.put("started", started);

		List<String> types = new ArrayList<String>();

		if (pluginPackage != null) {
			types = pluginPackage.getTypes();
		}

		JSONArray typesJSONArray = JSONFactoryUtil.createJSONArray();

		for (String type : types) {
			typesJSONArray.put(type);
		}

		pluginPackageJSONObject.put("types", typesJSONArray);

		responseJSONObject.put(JSONKeys.OUTPUT, pluginPackageJSONObject);
	}

	@Override
	public void executeUpdate(
			HttpServletRequest request, JSONObject responseJSONObject,
			Queue<String> arguments)
		throws Exception {

		String context = arguments.poll();

		List<File> deployDirs = getDeployDirectories(context);

		for (File deployDir : deployDirs) {
			if (!deployDir.exists()) {
				responseJSONObject.put(
					JSONKeys.ERROR,
					"Context directory " + deployDir.getAbsolutePath() +
						" does not exist");
				responseJSONObject.put(JSONKeys.STATUS, 1);

				return;
			}
		}

		File tempFile = getTempFile(request, responseJSONObject);

		if (tempFile == null) {
			return;
		}

		for (File deployDirectory : deployDirs) {
			FileUtil.unzip(tempFile, deployDirectory);
		}

		File partialAppDeletePropsFile = new File(
			deployDirs.get(0), "META-INF/liferay-partialapp-delete.props");

		if (!partialAppDeletePropsFile.exists()) {
			return;
		}

		BufferedReader bufferedReader = new BufferedReader(
			new FileReader(partialAppDeletePropsFile));

		String line = null;

		while ((line = bufferedReader.readLine()) != null) {
			for (File deployDirectory : deployDirs) {
				File staleFile = new File(deployDirectory, line.trim());

				if (!staleFile.exists()) {
					continue;
				}

				boolean success = FileUtils.deleteQuietly(staleFile);

				if (success) {
					continue;
				}

				String message =
					"Unable to delete file " + staleFile.getAbsolutePath();

				_log.error(message);

				responseJSONObject.put(JSONKeys.ERROR, message);
			}
		}

		FileUtils.deleteQuietly(partialAppDeletePropsFile);

		if (_log.isInfoEnabled()) {
			_log.info("Successfully updated " + context);
		}
	}

	protected List<File> getDeployDirectories(final String context)
		throws Exception {

		List<File> deployDirs = new ArrayList<File>();

		String deployDirName = DeployManagerUtil.getDeployDir();

		File deployDir = new File(deployDirName, context);

		if (deployDir.exists()) {
			deployDirs.add(deployDir);
		}
		else {
			File deployWarDir = new File(deployDirName, context + ".war");

			deployDirs.add(deployWarDir);
		}

		if (ServerDetector.isTomcat()) {
			File tempDir = new File(
				SystemProperties.get(SystemProperties.TMP_DIR));

			File[] tempContextDirs = tempDir.listFiles(
				new FilenameFilter() {

					public boolean accept(File dir, String name) {
						if (name.endsWith("-" + context)) {
							return true;
						}

						return false;
					}

				}
			);

			if ((tempContextDirs != null) && (tempContextDirs.length > 0)) {
				Arrays.sort(
					tempContextDirs,
					new Comparator<File>() {

						public int compare(File file1, File file2) {
							String fileName1 = file1.getName();
							String fileName2 = file2.getName();

							return fileName1.compareTo(fileName2);
						}

					}
				);

				File tempContextDir = tempContextDirs[
					tempContextDirs.length - 1];

				deployDirs.add(tempContextDir);
			}
		}

		return deployDirs;
	}

	protected File getTempFile(
		HttpServletRequest request, JSONObject responseJSONObject) {

		File tempFile = null;

		String message = "Unable to create temp file for uploaded plugin";

		try {
			tempFile = FileUploadUtil.getFileItemTempFile(request);
		}
		catch (Exception e) {
			_log.error(message, e);
		}

		if (tempFile != null) {
			return tempFile;
		}

		responseJSONObject.put(JSONKeys.ERROR, message);
		responseJSONObject.put(JSONKeys.STATUS, 1);

		return null;
	}

	private static Log _log = LogFactoryUtil.getLog(PluginExecutor.class);

}