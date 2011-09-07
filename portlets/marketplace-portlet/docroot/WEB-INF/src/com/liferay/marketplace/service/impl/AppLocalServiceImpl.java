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

package com.liferay.marketplace.service.impl;

import com.liferay.marketplace.DownloadNotFoundException;
import com.liferay.marketplace.DuplicateAppException;
import com.liferay.marketplace.VersionException;
import com.liferay.marketplace.model.App;
import com.liferay.marketplace.model.Module;
import com.liferay.marketplace.service.base.AppLocalServiceBaseImpl;
import com.liferay.portal.kernel.deploy.DeployManagerUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.ServerDetector;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.SystemProperties;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.User;
import com.liferay.portlet.documentlibrary.store.DLStoreUtil;

import java.io.File;
import java.io.InputStream;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author Ryan Park
 */
public class AppLocalServiceImpl extends AppLocalServiceBaseImpl {

	public App addApp(
			long userId, long marketplaceAppId, String version, InputStream is)
		throws PortalException, SystemException {

		// App

		User user = userPersistence.findByPrimaryKey(userId);

		validate(version, marketplaceAppId);

		Date now = new Date();

		long appId = counterLocalService.increment();

		App app = appPersistence.create(appId);

		app.setAppId(appId);
		app.setCompanyId(user.getCompanyId());
		app.setUserId(user.getUserId());
		app.setUserName(user.getFullName());
		app.setCreateDate(now);
		app.setModifiedDate(now);
		app.setMarketplaceAppId(marketplaceAppId);
		app.setVersion(version);

		appPersistence.update(app, false);

		// File

		DLStoreUtil.addFile(
			app.getCompanyId(), CompanyConstants.SYSTEM, app.getFilePath(),
			false, is);

		return app;
	}

	public void deleteApp(long appId) throws PortalException, SystemException {
		App app = appPersistence.findByPrimaryKey(appId);

		deleteApp(app);
	}

	public void deleteApp(App app) throws PortalException, SystemException {

		// App

		appPersistence.remove(app);

		// Module

		List<Module> modules = moduleLocalService.getAppModules(app.getAppId());

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
	}

	public void deleteAppByMarketplaceAppId(long marketplaceAppId)
		throws PortalException, SystemException {

		App app = appPersistence.findByMarketplaceAppId(marketplaceAppId);

		deleteApp(app);
	}

	public App fetchApp(long marketplaceAppId) throws SystemException {
		return appPersistence.fetchByMarketplaceAppId(marketplaceAppId);
	}

	public App getApp(long marketplaceAppId)
		throws PortalException, SystemException {

		return appPersistence.findByMarketplaceAppId(marketplaceAppId);
	}

	public List<App> getApps(long companyId, int start, int end)
		throws SystemException {

		return appPersistence.findByCompanyId(companyId, start, end);
	}

	public int getAppsCount(long companyId) throws SystemException {
		return appPersistence.countByCompanyId(companyId);
	}

	public boolean hasApp(long marketplaceAppId) throws SystemException {
		App app = appPersistence.fetchByMarketplaceAppId(marketplaceAppId);

		if (app == null) {
			return false;
		}

		return true;
	}

	public void installApp(long marketplaceAppId)
		throws PortalException, SystemException {

		App app = appPersistence.findByMarketplaceAppId(marketplaceAppId);

		if (!DLStoreUtil.hasFile(
				app.getCompanyId(), CompanyConstants.SYSTEM,
				app.getFilePath())) {

			throw new DownloadNotFoundException();
		}

		String tmpDir =
			SystemProperties.get(SystemProperties.TMP_DIR) + StringPool.SLASH +
				Time.getTimestamp();

		try {
			File liferayPackage = DLStoreUtil.getFile(
				app.getCompanyId(), CompanyConstants.SYSTEM, app.getFilePath());

			ZipFile zipFile = new ZipFile(liferayPackage);

			Enumeration enu = zipFile.entries();

			while (enu.hasMoreElements()) {
				ZipEntry entry = (ZipEntry)enu.nextElement();

				String fileName = entry.getName();

				if (_log.isInfoEnabled()) {
					_log.info(
						"Extracting " + fileName + " from appId " +
							app.getAppId());
				}

				InputStream is = zipFile.getInputStream(entry);

				File tempPluginPackage = new File(
					tmpDir + StringPool.SLASH + fileName);

				FileUtil.write(tempPluginPackage, is);

				String context = getContext(fileName);

				DeployManagerUtil.deploy(tempPluginPackage, context);

				moduleLocalService.addModule(
					app.getUserId(), app.getAppId(), context);
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}
		finally {
			FileUtil.deltree(tmpDir);
		}
	}

	public void uninstallApp(long marketplaceAppId)
		throws PortalException, SystemException {

		App app = appPersistence.findByMarketplaceAppId(marketplaceAppId);

		List<Module> modules = moduleLocalService.getAppModules(app.getAppId());

		String appServerType = ServerDetector.getServerId();

		boolean jbossAppServer = appServerType.startsWith(
			ServerDetector.JBOSS_ID);

		for (Module module : modules) {
			String context = module.getContextName();

			if (hasDependentApp(module)) {
				continue;
			}

			try {
				DeployManagerUtil.undeploy(context);
			}
			catch (Exception e) {
				_log.error(e, e);
			}
			finally {
				moduleLocalService.deleteModule(module.getModuleId());
			}
		}
	}

	public App updateApp(long appId, String version, InputStream is)
		throws PortalException, SystemException {

		// App

		validate(version, 0);

		App app = appPersistence.findByPrimaryKey(appId);

		app.setModifiedDate(new Date());
		app.setVersion(version);

		// File

		if (is != null) {
			try {
				DLStoreUtil.deleteFile(
					app.getCompanyId(), CompanyConstants.SYSTEM,
					app.getFilePath());
			}
			catch (Exception e) {
			}

			DLStoreUtil.addFile(
				app.getCompanyId(), CompanyConstants.SYSTEM, app.getFilePath(),
				false, is);
		}

		return app;
	}

	protected String getContext(String fileName) {
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

	protected void validate(String version, long marketplaceAppId)
		throws PortalException, SystemException {

		if (Validator.isNull(version)) {
			throw new VersionException();
		}

		if (marketplaceAppId > 0) {
			App app = fetchApp(marketplaceAppId);

			if (app != null) {
				throw new DuplicateAppException();
			}
		}
	}

	private boolean hasDependentApp(Module module)
		throws PortalException, SystemException {

		List<Module> modules = moduleLocalService.getModulesByContextName(
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

	private static Log _log = LogFactoryUtil.getLog(AppLocalServiceImpl.class);

}