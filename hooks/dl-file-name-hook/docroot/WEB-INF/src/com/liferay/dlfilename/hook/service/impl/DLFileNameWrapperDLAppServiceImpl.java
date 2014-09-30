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
import com.liferay.portlet.documentlibrary.service.DLAppService;
import com.liferay.portlet.documentlibrary.service.DLAppServiceWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Preston Crary
 */
public class DLFileNameWrapperDLAppServiceImpl extends DLAppServiceWrapper {

	public DLFileNameWrapperDLAppServiceImpl(DLAppService dlAppService) {
		super(dlAppService);
	}

	@Override
	public List<FileEntry> getFileEntries(
			long repositoryId, long folderId, int start, int end)
		throws PortalException, SystemException {

		List<FileEntry> fileEntries = super.getFileEntries(
			repositoryId, folderId, start, end);

		if (!FileNameUtil.isThreadLocalEnabled("getFileEntries")) {
			return fileEntries;
		}

		List<FileEntry> dlFileNameFileEntries = new ArrayList<FileEntry>(
			fileEntries.size());

		for (FileEntry fileEntry : fileEntries) {
			dlFileNameFileEntries.add(
				new DLFileNameWrapperFileEntryImpl(fileEntry));
		}

		return dlFileNameFileEntries;
	}

}