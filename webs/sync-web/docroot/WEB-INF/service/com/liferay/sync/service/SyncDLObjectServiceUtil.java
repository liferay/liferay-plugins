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

package com.liferay.sync.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableService;

/**
 * Provides the remote service utility for SyncDLObject. This utility wraps
 * {@link com.liferay.sync.service.impl.SyncDLObjectServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see SyncDLObjectService
 * @see com.liferay.sync.service.base.SyncDLObjectServiceBaseImpl
 * @see com.liferay.sync.service.impl.SyncDLObjectServiceImpl
 * @generated
 */
@ProviderType
public class SyncDLObjectServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.sync.service.impl.SyncDLObjectServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.sync.model.SyncDLObject addFileEntry(
		long repositoryId, long folderId, java.lang.String sourceFileName,
		java.lang.String mimeType, java.lang.String title,
		java.lang.String description, java.lang.String changeLog,
		java.io.File file, java.lang.String checksum,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addFileEntry(repositoryId, folderId, sourceFileName,
			mimeType, title, description, changeLog, file, checksum,
			serviceContext);
	}

	public static com.liferay.sync.model.SyncDLObject addFolder(
		long repositoryId, long parentFolderId, java.lang.String name,
		java.lang.String description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addFolder(repositoryId, parentFolderId, name, description,
			serviceContext);
	}

	public static com.liferay.sync.model.SyncDLObject cancelCheckOut(
		long fileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().cancelCheckOut(fileEntryId);
	}

	public static com.liferay.sync.model.SyncDLObject checkInFileEntry(
		long fileEntryId, boolean majorVersion, java.lang.String changeLog,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .checkInFileEntry(fileEntryId, majorVersion, changeLog,
			serviceContext);
	}

	public static com.liferay.sync.model.SyncDLObject checkOutFileEntry(
		long fileEntryId, java.lang.String owner, long expirationTime,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .checkOutFileEntry(fileEntryId, owner, expirationTime,
			serviceContext);
	}

	public static com.liferay.sync.model.SyncDLObject checkOutFileEntry(
		long fileEntryId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().checkOutFileEntry(fileEntryId, serviceContext);
	}

	public static java.util.List<com.liferay.sync.model.SyncDLObject> getAllFolderSyncDLObjects(
		long companyId, long repositoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getAllFolderSyncDLObjects(companyId, repositoryId);
	}

	/**
	* @deprecated As of 7.0.0, with no direct replacement
	*/
	@Deprecated
	public static com.liferay.sync.model.SyncDLObjectUpdate getAllSyncDLObjects(
		long repositoryId, long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getAllSyncDLObjects(repositoryId, folderId);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	public static com.liferay.sync.model.SyncDLObject getFileEntrySyncDLObject(
		long groupId, long folderId, java.lang.String title)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getFileEntrySyncDLObject(groupId, folderId, title);
	}

	public static java.util.List<com.liferay.sync.model.SyncDLObject> getFileEntrySyncDLObjects(
		long repositoryId, long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getFileEntrySyncDLObjects(repositoryId, folderId);
	}

	public static com.liferay.sync.model.SyncDLObject getFolderSyncDLObject(
		long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getFolderSyncDLObject(folderId);
	}

	public static com.liferay.sync.model.SyncDLObject getFolderSyncDLObject(
		long repositoryId, long parentFolderId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getFolderSyncDLObject(repositoryId, parentFolderId, name);
	}

	public static java.util.List<com.liferay.sync.model.SyncDLObject> getFolderSyncDLObjects(
		long repositoryId, long parentFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getFolderSyncDLObjects(repositoryId, parentFolderId);
	}

	public static com.liferay.portal.model.Group getGroup(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getGroup(groupId);
	}

	public static long getLatestModifiedTime() {
		return getService().getLatestModifiedTime();
	}

	public static com.liferay.sync.model.SyncContext getSyncContext()
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getSyncContext();
	}

	/**
	* @deprecated As of 7.0.0, replaced by {@link #getSyncContext()}
	*/
	@Deprecated
	public static com.liferay.sync.model.SyncContext getSyncContext(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getSyncContext(uuid);
	}

	public static com.liferay.sync.model.SyncDLObjectUpdate getSyncDLObjectUpdate(
		long companyId, long repositoryId, long lastAccessTime)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getSyncDLObjectUpdate(companyId, repositoryId,
			lastAccessTime);
	}

	public static com.liferay.sync.model.SyncDLObjectUpdate getSyncDLObjectUpdate(
		long companyId, long repositoryId, long parentFolderId,
		long lastAccessTime)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getSyncDLObjectUpdate(companyId, repositoryId,
			parentFolderId, lastAccessTime);
	}

	public static java.util.List<com.liferay.portal.model.Group> getUserSitesGroups()
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getUserSitesGroups();
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static com.liferay.sync.model.SyncDLObject moveFileEntry(
		long fileEntryId, long newFolderId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .moveFileEntry(fileEntryId, newFolderId, serviceContext);
	}

	public static com.liferay.sync.model.SyncDLObject moveFileEntryToTrash(
		long fileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().moveFileEntryToTrash(fileEntryId);
	}

	public static com.liferay.sync.model.SyncDLObject moveFolder(
		long folderId, long parentFolderId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().moveFolder(folderId, parentFolderId, serviceContext);
	}

	public static com.liferay.sync.model.SyncDLObject moveFolderToTrash(
		long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().moveFolderToTrash(folderId);
	}

	public static com.liferay.sync.model.SyncDLObject patchFileEntry(
		long fileEntryId, long sourceVersionId,
		java.lang.String sourceFileName, java.lang.String mimeType,
		java.lang.String title, java.lang.String description,
		java.lang.String changeLog, boolean majorVersion,
		java.io.File deltaFile, java.lang.String checksum,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .patchFileEntry(fileEntryId, sourceVersionId,
			sourceFileName, mimeType, title, description, changeLog,
			majorVersion, deltaFile, checksum, serviceContext);
	}

	public static com.liferay.sync.model.SyncDLObject restoreFileEntryFromTrash(
		long fileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().restoreFileEntryFromTrash(fileEntryId);
	}

	public static com.liferay.sync.model.SyncDLObject restoreFolderFromTrash(
		long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().restoreFolderFromTrash(folderId);
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.util.Map<java.lang.String, java.lang.Object> updateFileEntries(
		java.io.File zipFile)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateFileEntries(zipFile);
	}

	public static com.liferay.sync.model.SyncDLObject updateFileEntry(
		long fileEntryId, java.lang.String sourceFileName,
		java.lang.String mimeType, java.lang.String title,
		java.lang.String description, java.lang.String changeLog,
		boolean majorVersion, java.io.File file, java.lang.String checksum,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateFileEntry(fileEntryId, sourceFileName, mimeType,
			title, description, changeLog, majorVersion, file, checksum,
			serviceContext);
	}

	public static com.liferay.sync.model.SyncDLObject updateFolder(
		long folderId, java.lang.String name, java.lang.String description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateFolder(folderId, name, description, serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static SyncDLObjectService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					SyncDLObjectService.class.getName());

			if (invokableService instanceof SyncDLObjectService) {
				_service = (SyncDLObjectService)invokableService;
			}
			else {
				_service = new SyncDLObjectServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(SyncDLObjectServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setService(SyncDLObjectService service) {
	}

	private static SyncDLObjectService _service;
}