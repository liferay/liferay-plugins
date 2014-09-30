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

package com.liferay.dlfilename.hook.service.impl;

import com.liferay.dlfilename.hook.model.impl.DLFileNameWrapperFileEntryImpl;
import com.liferay.dlfilename.hook.util.FileNameUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portlet.documentlibrary.service.DLAppLocalService;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceWrapper;

/**
 * @author Preston Crary
 */
public class DLFileNameWrapperDLAppLocalServiceImpl
	extends DLAppLocalServiceWrapper {

	public DLFileNameWrapperDLAppLocalServiceImpl(
		DLAppLocalService dlAppLocalService) {

		super(dlAppLocalService);
	}

	@Override
	public FileEntry getFileEntry(long fileEntryId)
		throws PortalException, SystemException {

		FileEntry fileEntry = super.getFileEntry(fileEntryId);

		if (FileNameUtil.isThreadLocalEnabled("getFileEntry")) {
			return new DLFileNameWrapperFileEntryImpl(fileEntry);
		}

		return fileEntry;
	}

	@Override
	public FileEntry getFileEntry(long groupId, long folderId, String title)
		throws PortalException, SystemException {

		FileEntry fileEntry = super.getFileEntry(groupId, folderId, title);

		if (FileNameUtil.isThreadLocalEnabled("getFileEntry")) {
			return new DLFileNameWrapperFileEntryImpl(fileEntry);
		}

		return fileEntry;
	}

}