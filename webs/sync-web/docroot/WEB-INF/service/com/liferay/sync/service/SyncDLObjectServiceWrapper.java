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

package com.liferay.sync.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SyncDLObjectService}.
 *
 * @author Brian Wing Shun Chan
 * @see SyncDLObjectService
 * @generated
 */
public class SyncDLObjectServiceWrapper implements SyncDLObjectService,
	ServiceWrapper<SyncDLObjectService> {
	public SyncDLObjectServiceWrapper(SyncDLObjectService syncDLObjectService) {
		_syncDLObjectService = syncDLObjectService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _syncDLObjectService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_syncDLObjectService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _syncDLObjectService.invokeMethod(name, parameterTypes, arguments);
	}

	@Override
	public com.liferay.sync.model.SyncDLObject addFileEntry(long repositoryId,
		long folderId, java.lang.String sourceFileName,
		java.lang.String mimeType, java.lang.String title,
		java.lang.String description, java.lang.String changeLog,
		java.io.File file, java.lang.String checksum,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _syncDLObjectService.addFileEntry(repositoryId, folderId,
			sourceFileName, mimeType, title, description, changeLog, file,
			checksum, serviceContext);
	}

	@Override
	public com.liferay.sync.model.SyncDLObject addFolder(long repositoryId,
		long parentFolderId, java.lang.String name,
		java.lang.String description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _syncDLObjectService.addFolder(repositoryId, parentFolderId,
			name, description, serviceContext);
	}

	@Override
	public com.liferay.sync.model.SyncDLObject cancelCheckOut(long fileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _syncDLObjectService.cancelCheckOut(fileEntryId);
	}

	@Override
	public com.liferay.sync.model.SyncDLObject checkInFileEntry(
		long fileEntryId, boolean majorVersion, java.lang.String changeLog,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _syncDLObjectService.checkInFileEntry(fileEntryId, majorVersion,
			changeLog, serviceContext);
	}

	@Override
	public com.liferay.sync.model.SyncDLObject checkOutFileEntry(
		long fileEntryId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _syncDLObjectService.checkOutFileEntry(fileEntryId,
			serviceContext);
	}

	@Override
	public com.liferay.sync.model.SyncDLObject checkOutFileEntry(
		long fileEntryId, java.lang.String owner, long expirationTime,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _syncDLObjectService.checkOutFileEntry(fileEntryId, owner,
			expirationTime, serviceContext);
	}

	@Override
	public com.liferay.sync.model.SyncDLObjectUpdate getAllSyncDLObjects(
		long repositoryId, long folderId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _syncDLObjectService.getAllSyncDLObjects(repositoryId, folderId);
	}

	@Override
	public com.liferay.sync.model.SyncDLObject getFileEntrySyncDLObject(
		long groupId, long folderId, java.lang.String title)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _syncDLObjectService.getFileEntrySyncDLObject(groupId, folderId,
			title);
	}

	@Override
	public java.util.List<com.liferay.sync.model.SyncDLObject> getFileEntrySyncDLObjects(
		long repositoryId, long folderId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _syncDLObjectService.getFileEntrySyncDLObjects(repositoryId,
			folderId);
	}

	@Override
	public com.liferay.sync.model.SyncDLObject getFolderSyncDLObject(
		long folderId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _syncDLObjectService.getFolderSyncDLObject(folderId);
	}

	@Override
	public java.util.List<com.liferay.sync.model.SyncDLObject> getFolderSyncDLObjects(
		long repositoryId, long parentFolderId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _syncDLObjectService.getFolderSyncDLObjects(repositoryId,
			parentFolderId);
	}

	@Override
	public com.liferay.portal.model.Group getGroup(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _syncDLObjectService.getGroup(groupId);
	}

	@Override
	public long getLatestModifiedTime()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _syncDLObjectService.getLatestModifiedTime();
	}

	@Override
	public com.liferay.sync.model.SyncContext getSyncContext(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _syncDLObjectService.getSyncContext(uuid);
	}

	@Override
	public com.liferay.sync.model.SyncDLObjectUpdate getSyncDLObjectUpdate(
		long companyId, long repositoryId, long lastAccessTime)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _syncDLObjectService.getSyncDLObjectUpdate(companyId,
			repositoryId, lastAccessTime);
	}

	@Override
	public java.util.List<com.liferay.portal.model.Group> getUserSitesGroups()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _syncDLObjectService.getUserSitesGroups();
	}

	@Override
	public com.liferay.sync.model.SyncDLObject moveFileEntry(long fileEntryId,
		long newFolderId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _syncDLObjectService.moveFileEntry(fileEntryId, newFolderId,
			serviceContext);
	}

	@Override
	public com.liferay.sync.model.SyncDLObject moveFileEntryToTrash(
		long fileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _syncDLObjectService.moveFileEntryToTrash(fileEntryId);
	}

	@Override
	public com.liferay.sync.model.SyncDLObject moveFolder(long folderId,
		long parentFolderId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _syncDLObjectService.moveFolder(folderId, parentFolderId,
			serviceContext);
	}

	@Override
	public com.liferay.sync.model.SyncDLObject moveFolderToTrash(long folderId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _syncDLObjectService.moveFolderToTrash(folderId);
	}

	@Override
	public com.liferay.sync.model.SyncDLObject patchFileEntry(
		long fileEntryId, java.lang.String sourceVersion,
		java.lang.String sourceFileName, java.lang.String mimeType,
		java.lang.String title, java.lang.String description,
		java.lang.String changeLog, boolean majorVersion,
		java.io.File deltaFile, java.lang.String checksum,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _syncDLObjectService.patchFileEntry(fileEntryId, sourceVersion,
			sourceFileName, mimeType, title, description, changeLog,
			majorVersion, deltaFile, checksum, serviceContext);
	}

	@Override
	public com.liferay.sync.model.SyncDLObject restoreFileEntryFromTrash(
		long fileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _syncDLObjectService.restoreFileEntryFromTrash(fileEntryId);
	}

	@Override
	public com.liferay.sync.model.SyncDLObject restoreFolderFromTrash(
		long folderId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _syncDLObjectService.restoreFolderFromTrash(folderId);
	}

	@Override
	public com.liferay.sync.model.SyncDLObject updateFileEntry(
		long fileEntryId, java.lang.String sourceFileName,
		java.lang.String mimeType, java.lang.String title,
		java.lang.String description, java.lang.String changeLog,
		boolean majorVersion, java.io.File file, java.lang.String checksum,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _syncDLObjectService.updateFileEntry(fileEntryId,
			sourceFileName, mimeType, title, description, changeLog,
			majorVersion, file, checksum, serviceContext);
	}

	@Override
	public com.liferay.sync.model.SyncDLObject updateFolder(long folderId,
		java.lang.String name, java.lang.String description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _syncDLObjectService.updateFolder(folderId, name, description,
			serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public SyncDLObjectService getWrappedSyncDLObjectService() {
		return _syncDLObjectService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedSyncDLObjectService(
		SyncDLObjectService syncDLObjectService) {
		_syncDLObjectService = syncDLObjectService;
	}

	@Override
	public SyncDLObjectService getWrappedService() {
		return _syncDLObjectService;
	}

	@Override
	public void setWrappedService(SyncDLObjectService syncDLObjectService) {
		_syncDLObjectService = syncDLObjectService;
	}

	private SyncDLObjectService _syncDLObjectService;
}