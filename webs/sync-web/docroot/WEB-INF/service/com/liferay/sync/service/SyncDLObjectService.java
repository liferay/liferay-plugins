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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.service.InvokableService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import com.liferay.sync.model.SyncDLObject;

import java.io.File;

import java.util.List;
import java.util.Map;

/**
 * Provides the remote service interface for SyncDLObject. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see SyncDLObjectServiceUtil
 * @see com.liferay.sync.service.base.SyncDLObjectServiceBaseImpl
 * @see com.liferay.sync.service.impl.SyncDLObjectServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface SyncDLObjectService extends BaseService, InvokableService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SyncDLObjectServiceUtil} to access the sync d l object remote service. Add custom service methods to {@link com.liferay.sync.service.impl.SyncDLObjectServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Group getGroup(long groupId) throws PortalException;

	public SyncDLObject addFileEntry(long repositoryId, long folderId,
		java.lang.String sourceFileName, java.lang.String mimeType,
		java.lang.String title, java.lang.String description,
		java.lang.String changeLog, File file, java.lang.String checksum,
		ServiceContext serviceContext) throws PortalException;

	public SyncDLObject addFolder(long repositoryId, long parentFolderId,
		java.lang.String name, java.lang.String description,
		ServiceContext serviceContext) throws PortalException;

	public SyncDLObject cancelCheckOut(long fileEntryId)
		throws PortalException;

	public SyncDLObject checkInFileEntry(long fileEntryId,
		boolean majorVersion, java.lang.String changeLog,
		ServiceContext serviceContext) throws PortalException;

	public SyncDLObject checkOutFileEntry(long fileEntryId,
		ServiceContext serviceContext) throws PortalException;

	public SyncDLObject checkOutFileEntry(long fileEntryId,
		java.lang.String owner, long expirationTime,
		ServiceContext serviceContext) throws PortalException;

	public SyncDLObject copyFileEntry(long sourceFileEntryId,
		long repositoryId, long folderId, java.lang.String sourceFileName,
		java.lang.String title, ServiceContext serviceContext)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SyncDLObject getFileEntrySyncDLObject(long repositoryId,
		long folderId, java.lang.String title) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SyncDLObject getFolderSyncDLObject(long folderId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SyncDLObject getFolderSyncDLObject(long repositoryId,
		long parentFolderId, java.lang.String name) throws PortalException;

	public SyncDLObject moveFileEntry(long fileEntryId, long newFolderId,
		ServiceContext serviceContext) throws PortalException;

	public SyncDLObject moveFileEntryToTrash(long fileEntryId)
		throws PortalException;

	public SyncDLObject moveFolder(long folderId, long parentFolderId,
		ServiceContext serviceContext) throws PortalException;

	public SyncDLObject moveFolderToTrash(long folderId)
		throws PortalException;

	public SyncDLObject patchFileEntry(long fileEntryId, long sourceVersionId,
		java.lang.String sourceFileName, java.lang.String mimeType,
		java.lang.String title, java.lang.String description,
		java.lang.String changeLog, boolean majorVersion, File deltaFile,
		java.lang.String checksum, ServiceContext serviceContext)
		throws PortalException;

	public SyncDLObject restoreFileEntryFromTrash(long fileEntryId)
		throws PortalException;

	public SyncDLObject restoreFolderFromTrash(long folderId)
		throws PortalException;

	public SyncDLObject updateFileEntry(long fileEntryId,
		java.lang.String sourceFileName, java.lang.String mimeType,
		java.lang.String title, java.lang.String description,
		java.lang.String changeLog, boolean majorVersion, File file,
		java.lang.String checksum, ServiceContext serviceContext)
		throws PortalException;

	public SyncDLObject updateFolder(long folderId, java.lang.String name,
		java.lang.String description, ServiceContext serviceContext)
		throws PortalException;

	@AccessControlled(guestAccessEnabled = true)
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.lang.Object getSyncContext() throws PortalException;

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.lang.String getSyncDLObjectUpdate(long repositoryId,
		long lastAccessTime, int max) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.lang.String getSyncDLObjectUpdate(long repositoryId,
		long lastAccessTime, int max, boolean retrieveFromCache)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.lang.String getSyncDLObjectUpdate(long repositoryId,
		long parentFolderId, long lastAccessTime) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SyncDLObject> getAllFolderSyncDLObjects(long repositoryId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SyncDLObject> getFileEntrySyncDLObjects(long repositoryId,
		long folderId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SyncDLObject> getFolderSyncDLObjects(long repositoryId,
		long parentFolderId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Group> getUserSitesGroups() throws PortalException;

	@Transactional(enabled = false)
	public Map<java.lang.String, java.lang.Object> updateFileEntries(
		File zipFile) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long getLatestModifiedTime() throws PortalException;
}