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

import com.liferay.sync.engine.documentlibrary.model.SyncContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Shinn Lok
 */
public class ReleaseInfo {

	public static final int getBuildNumber() {
		return _BUILD_NUMBER;
	}

	public static final String getVersion() {
		return _VERSION;
	}

	public static boolean isServerCompatible(SyncContext syncContext) {
		String pluginVersion = syncContext.getPluginVersion();

		Matcher matcher = _pattern.matcher(pluginVersion);

		if (!matcher.find()) {
			return false;
		}

		if (pluginVersion.startsWith("6.2") &&
			(Integer.parseInt(matcher.group(1)) < 3)) {

			return false;
		}

		return true;
	}

	private static final String _BUILD = "2000";

	private static final int _BUILD_NUMBER = Integer.parseInt(_BUILD);

	private static final String _VERSION = "2.0.0";

	private static Pattern _pattern = Pattern.compile(
		"(?:[0-9]+\\.){3}([0-9]+)");

}