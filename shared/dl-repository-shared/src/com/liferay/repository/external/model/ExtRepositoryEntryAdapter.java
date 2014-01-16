/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.RepositoryException;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.repository.external.ExtRepositoryAdapter;
import com.liferay.repository.external.ExtRepositoryEntry;
import com.liferay.repository.external.ExtRepositoryEntry.ExtRepositoryPermission;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Iván Zaera
 * @author Sergio González
 */
public abstract class ExtRepositoryEntryAdapter<T>
	extends ExtRepositoryModelAdapter<T> {

	@SuppressWarnings("unused")
	public boolean containsPermission(
			PermissionChecker permissionChecker, String actionId)
		throws PortalException, SystemException {

		try {
			ExtRepositoryPermission extRepositoryPermission =
				ExtRepositoryPermission.valueOf(actionId);

			return _extRepositoryEntry.containsPermission(
				extRepositoryPermission);
		}
		catch (IllegalArgumentException iae) {
			throw new RepositoryException(
				"Unexpected permission action " + actionId);
		}
	}

	public List<Long> getAncestorFolderIds()
		throws PortalException, SystemException {

		List<Long> folderIds = new ArrayList<Long>();

		Folder folder = getParentFolder();

		while (!folder.isRoot()) {
			folderIds.add(folder.getFolderId());

			folder = folder.getParentFolder();
		}

		return folderIds;
	}

	public List<Folder> getAncestors() throws PortalException, SystemException {
		List<Folder> folders = new ArrayList<Folder>();

		Folder folder = getParentFolder();

		while ((folder != null) && !folder.isRoot()) {
			folders.add(folder);

			folder = getParentFolder();
		}

		if (folder != null) {
			folders.add(folder);
		}

		return folders;
	}

	public String getExtension() {
		return _extRepositoryEntry.getExtension();
	}

	@Override
	public ExtRepositoryEntry getExtRepositoryModel() {
		return _extRepositoryEntry;
	}

	@Override
	public Date getModifiedDate() {
		return _extRepositoryEntry.getModifiedDate();
	}

	public abstract String getName();

	public Folder getParentFolder() throws PortalException, SystemException {
		String parentFolderPath = _extRepositoryEntry.getParentFolderId();

		if (parentFolderPath.equals(StringPool.FORWARD_SLASH)) {
			return DLAppLocalServiceUtil.getMountFolder(getRepositoryId());
		}
		else {
			return getRepository().getParentFolder(this);
		}
	}

	public boolean isInTrash() {
		return false;
	}

	public boolean isInTrashContainer() {
		return false;
	}

	public boolean isSupportsMetadata() {
		return false;
	}

	public boolean isSupportsSocial() {
		return false;
	}

	protected ExtRepositoryEntryAdapter(
		ExtRepositoryAdapter extRepositoryAdapter, long repositoryEntryId,
		String uuid, ExtRepositoryEntry extRepositoryEntry) {

		super(
			extRepositoryAdapter, repositoryEntryId, uuid, extRepositoryEntry);

		_extRepositoryEntry = extRepositoryEntry;
	}

	private ExtRepositoryEntry _extRepositoryEntry;

}