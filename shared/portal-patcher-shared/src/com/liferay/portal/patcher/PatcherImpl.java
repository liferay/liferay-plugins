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

package com.liferay.portal.patcher;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.util.Properties;

/**
 * @author Zsolt Balogh
 * @author Brian Wing Shun Chan
 * @author Igor Beslic
 */
public class PatcherImpl implements Patcher {

	@Override
	public boolean applyPatch(File patchFile) {
		File patchDirectory = getPatchDirectory();

		if (patchDirectory == null) {
			return false;
		}

		try {
			FileUtil.copyFile(
				patchFile,
				new File(
					patchDirectory + StringPool.SLASH + patchFile.getName()));

			return true;
		}
		catch (Exception e) {
			_log.error(
				"Unable to copy " + patchFile.getAbsolutePath() + " to " +
					patchDirectory.getAbsolutePath());

			return false;
		}
	}

	@Override
	public String[] getFixedIssues() {
		if (_fixedIssueKeys != null) {
			return _fixedIssueKeys;
		}

		Properties properties = getProperties();

		_fixedIssueKeys = StringUtil.split(
			properties.getProperty(PROPERTY_FIXED_ISSUES));

		return _fixedIssueKeys;
	}

	@Override
	public String[] getInstalledPatches() {
		if (_installedPatchNames != null) {
			return _installedPatchNames;
		}

		Properties properties = getProperties();

		_installedPatchNames = StringUtil.split(
			properties.getProperty(PROPERTY_INSTALLED_PATCHES));

		return _installedPatchNames;
	}

	@Override
	public File getPatchDirectory() {
		if (_patchDirectory != null) {
			return _patchDirectory;
		}

		Properties properties = getProperties();

		String patchDirectoryName = properties.getProperty(
			PROPERTY_PATCH_DIRECTORY);

		if (Validator.isNotNull(patchDirectoryName)) {
			_patchDirectory = new File(patchDirectoryName);

			if (!_patchDirectory.exists()) {
				_log.error("The patch directory does not exist");
			}
		}
		else {
			_log.error("The patch directory is not specified");
		}

		return _patchDirectory;
	}

	@Override
	public int getPatchingToolVersion() {
		if (_patchingToolVersion != 0) {
			return _patchingToolVersion;
		}

		Properties properties = getProperties();

		if (properties.containsKey(PROPERTY_PATCHING_TOOL_VERSION)) {
			_patchingToolVersion = GetterUtil.getInteger(
				properties.getProperty(PROPERTY_PATCHING_TOOL_VERSION));
		}

		return _patchingToolVersion;
	}

	@Override
	public String[] getPatchLevels() {
		if (_patchLevels != null) {
			return _patchLevels;
		}

		Properties properties = getProperties();

		_patchLevels = StringUtil.split(
			properties.getProperty(PROPERTY_PATCH_LEVELS));

		return _patchLevels;
	}

	@Override
	public Properties getProperties() {
		if (_properties != null) {
			return _properties;
		}

		Properties properties = new Properties();

		ClassLoader classLoader = PortalClassLoaderUtil.getClassLoader();

		InputStream inputStream = classLoader.getResourceAsStream(
			PATCHER_PROPERTIES);

		if (inputStream == null) {
			if (_log.isDebugEnabled()) {
				_log.debug("Unable to load " + PATCHER_PROPERTIES);
			}
		}
		else {
			try {
				properties.load(inputStream);

				_configured = true;
			}
			catch (IOException ioe) {
				_log.error(ioe, ioe);
			}
			finally {
				StreamUtil.cleanUp(inputStream);
			}
		}

		_properties = properties;

		return _properties;
	}

	@Override
	public boolean isConfigured() {
		return _configured;
	}

	private static Log _log = LogFactoryUtil.getLog(PatcherImpl.class);

	private boolean _configured;
	private String[] _fixedIssueKeys;
	private String[] _installedPatchNames;
	private File _patchDirectory;
	private int _patchingToolVersion;
	private String[] _patchLevels;
	private Properties _properties;

}