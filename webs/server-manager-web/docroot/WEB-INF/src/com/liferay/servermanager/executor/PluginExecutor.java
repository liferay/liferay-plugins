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
import com.liferay.servermanager.util.FileUploadUtil;
import com.liferay.servermanager.util.JSONKeys;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.util.ArrayList;
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
			"Could not remove temp directory " + tempFile.getParentFile();

		_log.error(message);

		responseJSONObject.put(JSONKeys.ERROR, message);

		success = FileUtils.deleteQuietly(tempFile);

		if (success) {
			return;
		}

		message = "Could not remove temp file " + tempFile;

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

		File contextDirectory = getDeployDirectory(context);

		if (!contextDirectory.exists()) {
			responseJSONObject.put(
				JSONKeys.ERROR,
				"Context directory " + contextDirectory.getAbsolutePath() +
					" does not exist");
			responseJSONObject.put(JSONKeys.STATUS, 1);

			return;
		}

		File tempFile = getTempFile(request, responseJSONObject);

		if (tempFile == null) {
			return;
		}

		FileUtil.unzip(tempFile, contextDirectory);

		File partialAppDeletePropsFile = new File(
			contextDirectory, "META-INF/liferay-partialapp-delete.props");

		if (!partialAppDeletePropsFile.exists()) {
			return;
		}

		BufferedReader bufferedReader = new BufferedReader(
			new FileReader(partialAppDeletePropsFile));

		String line = null;

		while ((line = bufferedReader.readLine()) != null) {
			File staleFile = new File(contextDirectory, line.trim());

			if (!staleFile.exists()) {
				continue;
			}

			boolean success = FileUtils.deleteQuietly(staleFile);

			if (!success) {
				String message =
					"Could not delete file " + staleFile.getAbsolutePath();

				_log.error(message);

				responseJSONObject.put(JSONKeys.ERROR, message);
			}
		}

		FileUtils.deleteQuietly(partialAppDeletePropsFile);
	}

	protected File getDeployDirectory(String context) throws Exception {
		String deployDirName = DeployManagerUtil.getDeployDir();

		File deployDir = new File(deployDirName, context);

		if (deployDir.exists()) {
			return deployDir;
		}

		File deployWarDir = new File(deployDirName, context + ".war");

		if (deployWarDir.exists()) {
			return deployWarDir;
		}

		return deployDir;
	}

	protected File getTempFile(
		HttpServletRequest request, JSONObject responseJSONObject) {

		File tempFile = null;

		String message = "Could not create temp file for uploaded plugin";

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