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

package com.liferay.compat.hook.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.LockLocalServiceUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFileVersion;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileEntryService;
import com.liferay.portlet.documentlibrary.service.DLFileEntryServiceWrapper;

/**
 * @author Jonathan Lee
 */
public class CompatDLFileEntryServiceImpl extends DLFileEntryServiceWrapper {

	public CompatDLFileEntryServiceImpl(DLFileEntryService dlFileEntryService) {
		super(dlFileEntryService);
	}

	@Override
	public DLFileVersion cancelCheckOut(long fileEntryId)
		throws PortalException, SystemException {

		DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.fetchDLFileEntry(
			fileEntryId);

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if (permissionChecker.isCompanyAdmin() ||
			permissionChecker.isGroupAdmin(dlFileEntry.getGroupId()) ||
			permissionChecker.isGroupOwner(dlFileEntry.getGroupId())) {

			LockLocalServiceUtil.unlock(
				DLFileEntry.class.getName(), fileEntryId);
		}

		return super.cancelCheckOut(fileEntryId);
	}

}