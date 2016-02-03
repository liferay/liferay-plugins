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

import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.RepositoryException;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.repository.external.ExtRepositoryAdapter;
import com.liferay.repository.external.ExtRepositoryObject;
import com.liferay.repository.external.ExtRepositoryObject.ExtRepositoryPermission;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Iván Zaera
 * @author Sergio González
 */
public abstract class ExtRepositoryObjectAdapter<T>
	extends ExtRepositoryModelAdapter<T> {

	@SuppressWarnings("unused")
	public boolean containsPermission(
			PermissionChecker permissionChecker, String actionId)
		throws PortalException {

		if (_unsupportedActionIds.containsKey(actionId)) {
			return _unsupportedActionIds.get(actionId);
		}

		try {
			ExtRepositoryPermission extRepositoryPermission =
				ExtRepositoryPermission.valueOf(actionId);

			return _extRepositoryObject.containsPermission(
				extRepositoryPermission);
		}
		catch (IllegalArgumentException iae) {
			throw new RepositoryException(
				"Unexpected permission action " + actionId);
		}
	}

	public List<Long> getAncestorFolderIds() throws PortalException {
		List<Long> folderIds = new ArrayList<>();

		Folder folder = getParentFolder();

		while (!folder.isRoot()) {
			folderIds.add(folder.getFolderId());

			folder = folder.getParentFolder();
		}

		return folderIds;
	}

	public List<Folder> getAncestors() throws PortalException {
		List<Folder> folders = new ArrayList<>();

		Folder folder = getParentFolder();

		while ((folder != null) && !folder.isRoot()) {
			folders.add(folder);

			folder = folder.getParentFolder();
		}

		if (folder != null) {
			folders.add(folder);
		}

		return folders;
	}

	public String getExtension() {
		return _extRepositoryObject.getExtension();
	}

	@Override
	public ExtRepositoryObject getExtRepositoryModel() {
		return _extRepositoryObject;
	}

	@Override
	public Date getModifiedDate() {
		return _extRepositoryObject.getModifiedDate();
	}

	public abstract String getName();

	public Folder getParentFolder() throws PortalException {
		ExtRepositoryAdapter extRepositoryAdapter = getRepository();

		Folder parentFolder = extRepositoryAdapter.getParentFolder(this);

		if (parentFolder.isRoot()) {
			return DLAppLocalServiceUtil.getMountFolder(getRepositoryId());
		}
		else {
			return parentFolder;
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

	protected ExtRepositoryObjectAdapter(
		ExtRepositoryAdapter extRepositoryAdapter, long extRepositoryObjectId,
		String uuid, ExtRepositoryObject extRepositoryObject) {

		super(
			extRepositoryAdapter, extRepositoryObjectId, uuid,
			extRepositoryObject);

		_extRepositoryObject = extRepositoryObject;
	}

	private static Map<String, Boolean> _unsupportedActionIds = new HashMap<>();

	static {
		_unsupportedActionIds.put(ActionKeys.OVERRIDE_CHECKOUT, Boolean.FALSE);
		_unsupportedActionIds.put(ActionKeys.SUBSCRIBE, Boolean.FALSE);
	}

	private ExtRepositoryObject _extRepositoryObject;

}