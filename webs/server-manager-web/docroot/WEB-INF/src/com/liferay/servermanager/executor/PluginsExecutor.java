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
import com.liferay.portal.kernel.plugin.PluginPackage;
import com.liferay.servermanager.util.JSONKeys;

import java.util.List;
import java.util.Queue;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class PluginsExecutor extends BaseExecutor {

	@Override
	public void executeCreate(
			HttpServletRequest request, JSONObject responseJSONObject,
			Queue<String> arguments)
		throws Exception {

		_pluginExecutor.executeCreate(request, responseJSONObject, arguments);
	}

	@Override
	public void executeRead(
		HttpServletRequest request, JSONObject responseJSONObject,
		Queue<String> arguments) {

		JSONArray pluginPackagesJSONArray = JSONFactoryUtil.createJSONArray();

		List<PluginPackage> pluginPackages =
			DeployManagerUtil.getInstalledPluginPackages();

		for (PluginPackage pluginPackage : pluginPackages) {
			pluginPackagesJSONArray.put(pluginPackage.getContext());
		}

		responseJSONObject.put(JSONKeys.OUTPUT, pluginPackagesJSONArray);
	}

	@Override
	public Executor getNextExecutor(Queue<String> arguments) {
		String context = arguments.peek();

		if (context == null) {
			return null;
		}

		return _pluginExecutor;
	}

	private Executor _pluginExecutor = new PluginExecutor();

}