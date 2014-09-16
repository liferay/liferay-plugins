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

package com.liferay.marketplace.service.impl;

import com.liferay.marketplace.AppPropertiesException;
import com.liferay.marketplace.AppTitleException;
import com.liferay.marketplace.AppVersionException;
import com.liferay.marketplace.model.App;
import com.liferay.marketplace.model.Module;
import com.liferay.marketplace.service.base.AppLocalServiceBaseImpl;
import com.liferay.marketplace.util.comparator.AppTitleComparator;
import com.liferay.portal.kernel.deploy.DeployManagerUtil;
import com.liferay.portal.kernel.deploy.auto.context.AutoDeploymentContext;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.plugin.PluginPackage;
import com.liferay.portal.kernel.servlet.ServletContextPool;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.PropertiesUtil;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.SystemProperties;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.NoSuchFileException;
import com.liferay.portlet.documentlibrary.store.DLStoreUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import javax.servlet.ServletContext;

/**
 * @author Ryan Park
 * @author Joan Kim
 */
public class AppLocalServiceImpl extends AppLocalServiceBaseImpl {

	@Override
	public void clearInstalledAppsCache() {
		_bundledApps = null;
		_installedApps = null;
	}

	@Override
	public App deleteApp(App app) {

		// App

		clearInstalledAppsCache();

		appPersistence.remove(app);

		// Module

		List<Module> modules = modulePersistence.findByAppId(app.getAppId());

		for (Module module : modules) {
			moduleLocalService.deleteModule(module);
		}

		// File

		try {
			DLStoreUtil.deleteFile(
				app.getCompanyId(), CompanyConstants.SYSTEM, app.getFilePath());
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}

		return app;
	}

	@Override
	public App deleteApp(long appId) throws PortalException {
		App app = appPersistence.findByPrimaryKey(appId);

		return deleteApp(app);
	}

	@Override
	public App fetchRemoteApp(long remoteAppId) {
		return appPersistence.fetchByRemoteAppId(remoteAppId);
	}

	@Override
	public List<App> getApps(String category) {
		return appPersistence.findByCategory(category);
	}

	@Override
	public Map<String, String> getBundledApps() {
		if (_bundledApps != null) {
			return _bundledApps;
		}

		Map<String, String> bundledApps = new HashMap<String, String>();

		List<PluginPackage> pluginPackages =
			DeployManagerUtil.getInstalledPluginPackages();

		for (PluginPackage pluginPackage : pluginPackages) {
			ServletContext servletContext = ServletContextPool.get(
				pluginPackage.getContext());

			InputStream inputStream = null;

			try {
				inputStream = servletContext.getResourceAsStream(
					"/WEB-INF/liferay-releng.changelog.md5");

				if (inputStream == null) {
					continue;
				}

				String relengHash = StringUtil.read(inputStream);

				if (Validator.isNotNull(relengHash)) {
					bundledApps.put(pluginPackage.getContext(), relengHash);
				}
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to read plugin package MD5 checksum for " +
							pluginPackage.getContext());
				}
			}
			finally {
				StreamUtil.cleanUp(inputStream);
			}
		}

		_bundledApps = bundledApps;

		return _bundledApps;
	}

	@Override
	public List<App> getInstalledApps() {
		if (_installedApps != null) {
			return _installedApps;
		}

		List<App> installedApps = new ArrayList<App>();

		// Core app

		App coreApp = appPersistence.create(0);

		coreApp.setTitle("Liferay Core");
		coreApp.setDescription("Plugins bundled with Liferay Portal.");
		coreApp.setVersion(ReleaseInfo.getVersion());

		coreApp.addContextName(PortalUtil.getPathContext());

		installedApps.add(coreApp);

		// Deployed apps

		List<PluginPackage> pluginPackages =
			DeployManagerUtil.getInstalledPluginPackages();

		for (PluginPackage pluginPackage : pluginPackages) {
			List<Module> modules = modulePersistence.findByContextName(
				pluginPackage.getContext());

			boolean installedApp = false;

			for (Module module : modules) {
				App app = appPersistence.fetchByPrimaryKey(module.getAppId());

				if ((app != null) && app.isInstalled()) {
					installedApp = true;

					break;
				}
			}

			if (installedApp) {
				continue;
			}

			App app = appPersistence.create(0);

			app.setTitle(pluginPackage.getName());
			app.setDescription(pluginPackage.getLongDescription());
			app.setVersion(pluginPackage.getVersion());

			app.addContextName(pluginPackage.getContext());

			installedApps.add(app);
		}

		// Marketplace apps

		List<App> apps = appPersistence.findAll();

		for (App app : apps) {
			if (app.isInstalled()) {
				installedApps.add(app);
			}
		}

		installedApps = ListUtil.sort(installedApps, new AppTitleComparator());

		_installedApps = installedApps;

		return _installedApps;
	}

	@Override
	public void installApp(long remoteAppId) throws PortalException {
		App app = appPersistence.findByRemoteAppId(remoteAppId);

		if (!DLStoreUtil.hasFile(
				app.getCompanyId(), CompanyConstants.SYSTEM,
				app.getFilePath())) {

			throw new NoSuchFileException();
		}

		String tmpDir =
			SystemProperties.get(SystemProperties.TMP_DIR) + StringPool.SLASH +
				Time.getTimestamp();

		InputStream inputStream = null;

		ZipFile zipFile = null;

		try {
			inputStream = DLStoreUtil.getFileAsStream(
				app.getCompanyId(), CompanyConstants.SYSTEM, app.getFilePath());

			if (inputStream == null) {
				throw new IOException(
					"Unable to open file at " + app.getFilePath());
			}

			File liferayPackageFile = FileUtil.createTempFile(inputStream);

			zipFile = new ZipFile(liferayPackageFile);

			Enumeration<ZipEntry> enu =
				(Enumeration<ZipEntry>)zipFile.entries();

			while (enu.hasMoreElements()) {
				ZipEntry zipEntry = enu.nextElement();

				AutoDeploymentContext autoDeploymentContext =
					new AutoDeploymentContext();

				String fileName = zipEntry.getName();

				if (!fileName.endsWith(".jar") &&
					!fileName.endsWith(".war") &&
					!fileName.endsWith(".xml") &&
					!fileName.endsWith(".zip") &&
					!fileName.equals("liferay-marketplace.properties")) {

					continue;
				}

				String contextName = getContextName(fileName);

				autoDeploymentContext.setContext(contextName);

				if (_log.isInfoEnabled()) {
					_log.info(
						"Extracting " + fileName + " from app " +
							app.getAppId());
				}

				InputStream zipInputStream = null;

				try {
					zipInputStream = zipFile.getInputStream(zipEntry);

					if (fileName.equals("liferay-marketplace.properties")) {
						String propertiesString = StringUtil.read(
							zipInputStream);

						Properties properties = PropertiesUtil.load(
							propertiesString);

						processMarketplaceProperties(properties);
					}
					else {
						File pluginPackageFile = new File(
							tmpDir + StringPool.SLASH + fileName);

						FileUtil.write(pluginPackageFile, zipInputStream);

						autoDeploymentContext.setFile(pluginPackageFile);

						DeployManagerUtil.deploy(autoDeploymentContext);

						if (Validator.isNotNull(contextName)) {
							moduleLocalService.addModule(
								app.getUserId(), app.getAppId(),
								StringPool.BLANK, contextName);
						}
					}
				}
				finally {
					StreamUtil.cleanUp(zipInputStream);
				}
			}
		}
		catch (ZipException ze) {
			if (_log.isInfoEnabled()) {
				_log.info(
					"Deleting corrupt package from app " + app.getAppId(), ze);
			}

			deleteApp(app);
		}
		catch (IOException ioe) {
			throw new PortalException(ioe.getMessage());
		}
		catch (Exception e) {
			_log.error(e, e);
		}
		finally {
			FileUtil.deltree(tmpDir);

			if (zipFile != null) {
				try {
					zipFile.close();
				}
				catch (IOException ioe) {
				}
			}

			StreamUtil.cleanUp(inputStream);

			clearInstalledAppsCache();
		}
	}

	@Override
	public void processMarketplaceProperties(Properties properties)
		throws PortalException {

		long[] supersedesRemoteAppIds = StringUtil.split(
			properties.getProperty("supersedes-remote-app-ids"), 0L);

		for (long supersedesRemoteAppId : supersedesRemoteAppIds) {
			App supersedesApp = appPersistence.fetchByRemoteAppId(
				supersedesRemoteAppId);

			if ((supersedesApp != null) && supersedesApp.isInstalled()) {
				uninstallApp(supersedesRemoteAppId);
			}
		}
	}

	@Override
	public void uninstallApp(long remoteAppId) throws PortalException {
		clearInstalledAppsCache();

		App app = appPersistence.findByRemoteAppId(remoteAppId);

		List<Module> modules = modulePersistence.findByAppId(app.getAppId());

		for (Module module : modules) {
			moduleLocalService.deleteModule(module.getModuleId());

			if (hasDependentApp(module)) {
				continue;
			}

			try {
				DeployManagerUtil.undeploy(module.getContextName());
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
	}

	@Override
	public App updateApp(
			long userId, long remoteAppId, String version, File file)
		throws PortalException {

		Properties properties = getMarketplaceProperties(file);

		if (properties == null) {
			throw new AppPropertiesException(
				"Unable to read liferay-marketplace.properties");
		}

		String title = properties.getProperty("title");
		String description = properties.getProperty("description");
		String category = properties.getProperty("category");
		String iconURL = properties.getProperty("icon-url");

		return updateApp(
			userId, remoteAppId, title, description, category, iconURL, version,
			file);
	}

	@Override
	public App updateApp(
			long userId, long remoteAppId, String title, String description,
			String category, String iconURL, String version, File file)
		throws PortalException {

		// App

		User user = userPersistence.fetchByPrimaryKey(userId);
		Date now = new Date();

		validate(title, version);

		App app = appPersistence.fetchByRemoteAppId(remoteAppId);

		if (app == null) {
			long appId = counterLocalService.increment();

			app = appPersistence.create(appId);
		}

		if (user != null) {
			app.setCompanyId(user.getCompanyId());
			app.setUserId(user.getUserId());
			app.setUserName(user.getFullName());
		}

		app.setCreateDate(now);
		app.setModifiedDate(now);
		app.setRemoteAppId(remoteAppId);
		app.setTitle(title);
		app.setDescription(description);
		app.setCategory(category);
		app.setIconURL(iconURL);
		app.setVersion(version);

		appPersistence.update(app);

		// File

		if (file != null) {
			try {
				DLStoreUtil.deleteFile(
					app.getCompanyId(), CompanyConstants.SYSTEM,
					app.getFilePath());
			}
			catch (Exception e) {
			}

			DLStoreUtil.addFile(
				app.getCompanyId(), CompanyConstants.SYSTEM, app.getFilePath(),
				false, file);
		}

		clearInstalledAppsCache();

		return app;
	}

	protected String getContextName(String fileName) {
		if (fileName.endsWith(".jar")) {
			return StringPool.BLANK;
		}

		String context = fileName;

		while (context.contains(StringPool.DASH)) {
			if (context.endsWith("-ext") || context.endsWith("-hook") ||
				context.endsWith("-layouttpl") ||
				context.endsWith("-portlet") || context.endsWith("-theme") ||
				context.endsWith("-web")) {

				return context;
			}

			int pos = context.lastIndexOf(StringPool.DASH);

			if (pos > 0) {
				context = context.substring(0, pos);
			}
			else {
				break;
			}
		}

		return fileName;
	}

	protected Properties getMarketplaceProperties(File liferayPackageFile) {
		InputStream inputStream = null;
		ZipFile zipFile = null;

		try {
			zipFile = new ZipFile(liferayPackageFile);

			ZipEntry zipEntry = zipFile.getEntry(
				"liferay-marketplace.properties");

			inputStream = zipFile.getInputStream(zipEntry);

			String propertiesString = StringUtil.read(inputStream);

			return PropertiesUtil.load(propertiesString);
		}
		catch (IOException ioe) {
			return null;
		}
		finally {
			if (zipFile != null) {
				try {
					zipFile.close();
				}
				catch (IOException ioe) {
				}
			}

			StreamUtil.cleanUp(inputStream);
		}
	}

	protected boolean hasDependentApp(Module module) throws PortalException {
		List<Module> modules = modulePersistence.findByContextName(
			module.getContextName());

		for (Module curModule : modules) {
			App app = appPersistence.findByPrimaryKey(curModule.getAppId());

			if (curModule.getAppId() == module.getAppId()) {
				continue;
			}

			if (app.isInstalled()) {
				return true;
			}
		}

		return false;
	}

	protected void validate(String title, String version)
		throws PortalException {

		if (Validator.isNull(title)) {
			throw new AppTitleException();
		}

		if (Validator.isNull(version)) {
			throw new AppVersionException();
		}
	}

	private static Log _log = LogFactoryUtil.getLog(AppLocalServiceImpl.class);

	private Map<String, String> _bundledApps;
	private List<App> _installedApps;

}