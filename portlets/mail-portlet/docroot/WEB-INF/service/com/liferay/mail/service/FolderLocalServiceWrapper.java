/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.mail.service;


/**
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a wrapper for {@link FolderLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       FolderLocalService
 * @generated
 */
public class FolderLocalServiceWrapper implements FolderLocalService {
	public FolderLocalServiceWrapper(FolderLocalService folderLocalService) {
		_folderLocalService = folderLocalService;
	}

	public com.liferay.mail.model.Folder addFolder(
		com.liferay.mail.model.Folder folder)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _folderLocalService.addFolder(folder);
	}

	public com.liferay.mail.model.Folder createFolder(long folderId) {
		return _folderLocalService.createFolder(folderId);
	}

	public void deleteFolder(long folderId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_folderLocalService.deleteFolder(folderId);
	}

	public void deleteFolder(com.liferay.mail.model.Folder folder)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_folderLocalService.deleteFolder(folder);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _folderLocalService.dynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _folderLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _folderLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _folderLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.mail.model.Folder getFolder(long folderId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _folderLocalService.getFolder(folderId);
	}

	public java.util.List<com.liferay.mail.model.Folder> getFolders(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _folderLocalService.getFolders(start, end);
	}

	public int getFoldersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _folderLocalService.getFoldersCount();
	}

	public com.liferay.mail.model.Folder updateFolder(
		com.liferay.mail.model.Folder folder)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _folderLocalService.updateFolder(folder);
	}

	public com.liferay.mail.model.Folder updateFolder(
		com.liferay.mail.model.Folder folder, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _folderLocalService.updateFolder(folder, merge);
	}

	public com.liferay.mail.model.Folder addFolder(long userId, long accountId,
		java.lang.String fullName, java.lang.String displayName,
		int remoteMessageCount)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _folderLocalService.addFolder(userId, accountId, fullName,
			displayName, remoteMessageCount);
	}

	public void deleteFolders(long accountId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_folderLocalService.deleteFolders(accountId);
	}

	public com.liferay.mail.model.Folder getFolder(long accountId,
		java.lang.String fullName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _folderLocalService.getFolder(accountId, fullName);
	}

	public java.util.List<com.liferay.mail.model.Folder> getFolders(
		long accountId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _folderLocalService.getFolders(accountId);
	}

	public int getLocalPageCount(long folderId, int messagesPerPage)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _folderLocalService.getLocalPageCount(folderId, messagesPerPage);
	}

	public int getPercentDownloaded(long folderId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _folderLocalService.getPercentDownloaded(folderId);
	}

	public int getRemotePageCount(long folderId, int messagesPerPage)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _folderLocalService.getRemotePageCount(folderId, messagesPerPage);
	}

	public com.liferay.mail.model.Folder updateFolder(long folderId,
		java.lang.String fullName, java.lang.String displayName,
		int remoteMessageCount)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _folderLocalService.updateFolder(folderId, fullName,
			displayName, remoteMessageCount);
	}

	public FolderLocalService getWrappedFolderLocalService() {
		return _folderLocalService;
	}

	private FolderLocalService _folderLocalService;
}