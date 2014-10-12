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

package com.liferay.dlfilename.hook.model.impl;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.model.BackgroundTask;
import com.liferay.portal.model.BackgroundTaskWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Preston Crary
 */
public class DLFileNameBackgroundTaskImpl extends BackgroundTaskWrapper {

	public DLFileNameBackgroundTaskImpl(BackgroundTask backgroundTask) {
		super(backgroundTask);
	}

	@Override
	public List<FileEntry> getAttachmentsFileEntries(int start, int end)
		throws SystemException {

		List<FileEntry> fileEntries = super.getAttachmentsFileEntries(
			start, end);

		List<FileEntry> dlFileNameFileEntries = new ArrayList<FileEntry>(
			fileEntries.size());

		for (FileEntry fileEntry : fileEntries) {
			dlFileNameFileEntries.add(new DLFileNameFileEntryImpl(fileEntry));
		}

		return dlFileNameFileEntries;
	}

}