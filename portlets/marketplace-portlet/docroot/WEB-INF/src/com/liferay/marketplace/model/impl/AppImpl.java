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

package com.liferay.marketplace.model.impl;

import com.liferay.marketplace.model.Module;
import com.liferay.marketplace.service.ModuleLocalServiceUtil;
import com.liferay.portal.kernel.deploy.DeployManagerUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portlet.documentlibrary.store.DLStoreUtil;
import com.liferay.portlet.documentlibrary.store.Store;

import java.util.List;

/**
 * @author Ryan Park
 */
public class AppImpl extends AppBaseImpl {

	public AppImpl() {
	}

	public String getFileDir() {
		return _DIR_NAME;
	}

	public String getFileName() {
		return getAppId() + StringPool.PERIOD + _EXTENSION;
	}

	public String getFilePath() {
		return getFileDir() + StringPool.SLASH + getFileName();
	}

	public boolean isDownloaded() throws PortalException, SystemException {
		return DLStoreUtil.hasFile(
			getCompanyId(), CompanyConstants.SYSTEM, getFilePath(),
			Store.VERSION_DEFAULT);
	}

	public boolean isInstalled() throws SystemException {
		List<Module> modules = ModuleLocalServiceUtil.getModules(getAppId());

		for (Module module : modules) {
			if (!DeployManagerUtil.isDeployed(module.getContextName())) {
				return false;
			}
		}

		return true;
	}

	private static final String _DIR_NAME = "marketplace";

	private static final String _EXTENSION = "lpkg";

}