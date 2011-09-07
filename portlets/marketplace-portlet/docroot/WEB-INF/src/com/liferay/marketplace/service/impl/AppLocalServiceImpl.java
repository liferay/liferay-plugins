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

import com.liferay.marketplace.AppVersionException;
import com.liferay.marketplace.DuplicateAppException;
import com.liferay.marketplace.model.App;
import com.liferay.marketplace.model.Module;
import com.liferay.marketplace.service.base.AppLocalServiceBaseImpl;
import com.liferay.portal.kernel.deploy.DeployManagerUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.SystemProperties;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.User;
import com.liferay.portlet.documentlibrary.NoSuchFileException;
import com.liferay.portlet.documentlibrary.store.DLStoreUtil;

import java.io.File;
import java.io.InputStream;

import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author Ryan Park
 */
public class AppLocalServiceImpl extends AppLocalServiceBaseImpl {

	public App addApp(
			long userId, long remoteAppId, String version,
			InputStream inputStream)
		throws PortalException, SystemException {

		// App

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		validate(remoteAppId, version);

		long appId = counterLocalService.increment();

		App app = appPersistence.create(appId);

		app.setCompanyId(user.getCompanyId());
		app.setUserId(user.getUserId());
		app.setUserName(user.getFullName());
		app.setCreateDate(now);
		app.setModifiedDate(now);
		app.setRemoteAppId(remoteAppId);
		app.setVersion(version);

		appPersistence.update(app, false);

		// File

		DLStoreUtil.addFile(
			app.getCompanyId(), CompanyConstants.SYSTEM, app.getFilePath(),
			false, inputStream);

		return app;
	}

	@Override
	public void deleteApp(App app) throws SystemException {

		// App

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
	}

	@Override
	public void deleteApp(long appId) throws PortalException, SystemException {
		App app = appPersistence.findByPrimaryKey(appId);

		deleteApp(app);
	}

	public App fetchRemoteApp(long remoteAppId)
		throws SystemException {

		return appPersistence.fetchByRemoteAppId(remoteAppId);
	}

	public void installApp(long remoteAppId)
		throws PortalException, SystemException {

		App app = appPersistence.findByRemoteAppId(remoteAppId);

		if (!DLStoreUtil.hasFile(
				app.getCompanyId(), CompanyConstants.SYSTEM,
				app.getFilePath())) {

			throw new NoSuchFileException();
		}

		String tmpDir =
			SystemProperties.get(SystemProperties.TMP_DIR) + StringPool.SLASH +
				Time.getTimestamp();

		try {
			File liferayPackageFile = DLStoreUtil.getFile(
				app.getCompanyId(), CompanyConstants.SYSTEM, app.getFilePath());

			ZipFile zipFile = new ZipFile(liferayPackageFile);

			Enumeration<ZipEntry> enu =
				(Enumeration<ZipEntry>)zipFile.entries();

			while (enu.hasMoreElements()) {
				ZipEntry zipEntry = enu.nextElement();

				String fileName = zipEntry.getName();

				if (_log.isInfoEnabled()) {
					_log.info(
						"Extracting " + fileName + " from app " +
							app.getAppId());
				}

				InputStream inputStream = zipFile.getInputStream(zipEntry);

				File pluginPackageFile = new File(
					tmpDir + StringPool.SLASH + fileName);

				FileUtil.write(pluginPackageFile, inputStream);

				String contextName = getContextName(fileName);

				DeployManagerUtil.deploy(pluginPackageFile, contextName);

				moduleLocalService.addModule(
					app.getUserId(), app.getAppId(), contextName);
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}
		finally {
			FileUtil.deltree(tmpDir);
		}
	}

	public void uninstallApp(long remoteAppId)
		throws PortalException, SystemException {

		App app = appPersistence.findByRemoteAppId(remoteAppId);

		List<Module> modules = modulePersistence.findByAppId(app.getAppId());

		for (Module module : modules) {
			String contextName = module.getContextName();

			if (hasDependentApp(module)) {
				continue;
			}

			try {
				DeployManagerUtil.undeploy(contextName);
			}
			catch (Exception e) {
				_log.error(e, e);
			}
			finally {
				moduleLocalService.deleteModule(module.getModuleId());
			}
		}
	}

	public App updateApp(long appId, String version, InputStream inputStream)
		throws PortalException, SystemException {

		// App

		validate(0, version);

		App app = appPersistence.findByPrimaryKey(appId);

		app.setModifiedDate(new Date());
		app.setVersion(version);

		appPersistence.update(app, false);

		// File

		if (inputStream != null) {
			try {
				DLStoreUtil.deleteFile(
					app.getCompanyId(), CompanyConstants.SYSTEM,
					app.getFilePath());
			}
			catch (Exception e) {
			}

			DLStoreUtil.addFile(
				app.getCompanyId(), CompanyConstants.SYSTEM, app.getFilePath(),
				false, inputStream);
		}

		return app;
	}

	protected String getContextName(String fileName) {
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

	protected boolean hasDependentApp(Module module)
		throws PortalException, SystemException {

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

	protected void validate(long remoteAppId, String version)
		throws PortalException, SystemException {

		if (Validator.isNull(version)) {
			throw new AppVersionException();
		}

		if (remoteAppId > 0) {
			App app = appPersistence.fetchByRemoteAppId(remoteAppId);

			if (app != null) {
				throw new DuplicateAppException();
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(AppLocalServiceImpl.class);

}