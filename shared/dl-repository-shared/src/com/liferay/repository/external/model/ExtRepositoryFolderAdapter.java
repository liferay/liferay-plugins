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

package com.liferay.repository.external.model;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.repository.model.RepositoryModelOperation;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.portlet.exportimport.lar.StagedModelType;
import com.liferay.repository.external.ExtRepositoryAdapter;
import com.liferay.repository.external.ExtRepositoryFolder;

import java.util.Date;

/**
 * @author Iván Zaera
 * @author Sergio González
 */
public class ExtRepositoryFolderAdapter
	extends ExtRepositoryObjectAdapter<Folder> implements Folder {

	public ExtRepositoryFolderAdapter(
		ExtRepositoryAdapter extRepositoryAdapter, long extRepositoryObjectId,
		String uuid, ExtRepositoryFolder extRepositoryFolder) {

		super(
			extRepositoryAdapter, extRepositoryObjectId, uuid,
			extRepositoryFolder);

		_extRepositoryFolder = extRepositoryFolder;
	}

	@Override
	public void execute(RepositoryModelOperation repositoryModelOperation)
		throws PortalException {

		repositoryModelOperation.execute(this);
	}

	@Override
	public ExtRepositoryFolder getExtRepositoryModel() {
		return _extRepositoryFolder;
	}

	@Override
	public long getFolderId() {
		return getPrimaryKey();
	}

	@Override
	public Date getLastPostDate() {
		return getModifiedDate();
	}

	@Override
	public Class<?> getModelClass() {
		return Folder.class;
	}

	@Override
	public String getName() {
		if (isRoot()) {
			try {
				Folder folder = DLAppLocalServiceUtil.getMountFolder(
					getRepositoryId());

				return folder.getName();
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		return _extRepositoryFolder.getName();
	}

	@Override
	public long getParentFolderId() {
		try {
			Folder parentFolder = getParentFolder();

			if (parentFolder != null) {
				return parentFolder.getFolderId();
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(DLFolderConstants.getClassName());
	}

	@Override
	public boolean hasInheritableLock() {
		return false;
	}

	@Override
	public boolean hasLock() {
		return false;
	}

	@Override
	public boolean isLocked() {
		return false;
	}

	@Override
	public boolean isMountPoint() {
		return false;
	}

	@Override
	public boolean isRoot() {
		return _extRepositoryFolder.isRoot();
	}

	@Override
	public boolean isSupportsLocking() {
		return false;
	}

	@Override
	public boolean isSupportsMultipleUpload() {
		return false;
	}

	@Override
	public boolean isSupportsShortcuts() {
		return false;
	}

	@Override
	public boolean isSupportsSubscribing() {
		return false;
	}

	private static Log _log = LogFactoryUtil.getLog(
		ExtRepositoryFolderAdapter.class);

	private ExtRepositoryFolder _extRepositoryFolder;

}