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

package com.liferay.sync.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import com.liferay.sync.service.SyncDLObjectServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link SyncDLObjectServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.sync.model.SyncDLObjectSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.sync.model.SyncDLObject}, that is translated to a
 * {@link com.liferay.sync.model.SyncDLObjectSoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SyncDLObjectServiceHttp
 * @see com.liferay.sync.model.SyncDLObjectSoap
 * @see SyncDLObjectServiceUtil
 * @generated
 */
@ProviderType
public class SyncDLObjectServiceSoap {
	public static com.liferay.sync.model.SyncDLObjectSoap addFolder(
		long repositoryId, long parentFolderId, java.lang.String name,
		java.lang.String description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.sync.model.SyncDLObject returnValue = SyncDLObjectServiceUtil.addFolder(repositoryId,
					parentFolderId, name, description, serviceContext);

			return com.liferay.sync.model.SyncDLObjectSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.sync.model.SyncDLObjectSoap cancelCheckOut(
		long fileEntryId) throws RemoteException {
		try {
			com.liferay.sync.model.SyncDLObject returnValue = SyncDLObjectServiceUtil.cancelCheckOut(fileEntryId);

			return com.liferay.sync.model.SyncDLObjectSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.sync.model.SyncDLObjectSoap checkInFileEntry(
		long fileEntryId, boolean majorVersion, java.lang.String changeLog,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.sync.model.SyncDLObject returnValue = SyncDLObjectServiceUtil.checkInFileEntry(fileEntryId,
					majorVersion, changeLog, serviceContext);

			return com.liferay.sync.model.SyncDLObjectSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.sync.model.SyncDLObjectSoap checkOutFileEntry(
		long fileEntryId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.sync.model.SyncDLObject returnValue = SyncDLObjectServiceUtil.checkOutFileEntry(fileEntryId,
					serviceContext);

			return com.liferay.sync.model.SyncDLObjectSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.sync.model.SyncDLObjectSoap checkOutFileEntry(
		long fileEntryId, java.lang.String owner, long expirationTime,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.sync.model.SyncDLObject returnValue = SyncDLObjectServiceUtil.checkOutFileEntry(fileEntryId,
					owner, expirationTime, serviceContext);

			return com.liferay.sync.model.SyncDLObjectSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.sync.model.SyncDLObjectSoap copyFileEntry(
		long sourceFileEntryId, long repositoryId, long folderId,
		java.lang.String sourceFileName, java.lang.String title,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.sync.model.SyncDLObject returnValue = SyncDLObjectServiceUtil.copyFileEntry(sourceFileEntryId,
					repositoryId, folderId, sourceFileName, title,
					serviceContext);

			return com.liferay.sync.model.SyncDLObjectSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.sync.model.SyncDLObjectSoap[] getAllFolderSyncDLObjects(
		long repositoryId) throws RemoteException {
		try {
			java.util.List<com.liferay.sync.model.SyncDLObject> returnValue = SyncDLObjectServiceUtil.getAllFolderSyncDLObjects(repositoryId);

			return com.liferay.sync.model.SyncDLObjectSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.sync.model.SyncDLObjectSoap getFileEntrySyncDLObject(
		long repositoryId, long folderId, java.lang.String title)
		throws RemoteException {
		try {
			com.liferay.sync.model.SyncDLObject returnValue = SyncDLObjectServiceUtil.getFileEntrySyncDLObject(repositoryId,
					folderId, title);

			return com.liferay.sync.model.SyncDLObjectSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.sync.model.SyncDLObjectSoap[] getFileEntrySyncDLObjects(
		long repositoryId, long folderId) throws RemoteException {
		try {
			java.util.List<com.liferay.sync.model.SyncDLObject> returnValue = SyncDLObjectServiceUtil.getFileEntrySyncDLObjects(repositoryId,
					folderId);

			return com.liferay.sync.model.SyncDLObjectSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.sync.model.SyncDLObjectSoap getFolderSyncDLObject(
		long folderId) throws RemoteException {
		try {
			com.liferay.sync.model.SyncDLObject returnValue = SyncDLObjectServiceUtil.getFolderSyncDLObject(folderId);

			return com.liferay.sync.model.SyncDLObjectSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.sync.model.SyncDLObjectSoap getFolderSyncDLObject(
		long repositoryId, long parentFolderId, java.lang.String name)
		throws RemoteException {
		try {
			com.liferay.sync.model.SyncDLObject returnValue = SyncDLObjectServiceUtil.getFolderSyncDLObject(repositoryId,
					parentFolderId, name);

			return com.liferay.sync.model.SyncDLObjectSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.sync.model.SyncDLObjectSoap[] getFolderSyncDLObjects(
		long repositoryId, long parentFolderId) throws RemoteException {
		try {
			java.util.List<com.liferay.sync.model.SyncDLObject> returnValue = SyncDLObjectServiceUtil.getFolderSyncDLObjects(repositoryId,
					parentFolderId);

			return com.liferay.sync.model.SyncDLObjectSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.model.Group getGroup(long groupId)
		throws RemoteException {
		try {
			com.liferay.portal.model.Group returnValue = SyncDLObjectServiceUtil.getGroup(groupId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static long getLatestModifiedTime() throws RemoteException {
		try {
			long returnValue = SyncDLObjectServiceUtil.getLatestModifiedTime();

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.sync.model.SyncContext getSyncContext()
		throws RemoteException {
		try {
			com.liferay.sync.model.SyncContext returnValue = SyncDLObjectServiceUtil.getSyncContext();

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String getSyncDLObjectUpdate(long repositoryId,
		long lastAccessTime, int max) throws RemoteException {
		try {
			java.lang.String returnValue = SyncDLObjectServiceUtil.getSyncDLObjectUpdate(repositoryId,
					lastAccessTime, max);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String getSyncDLObjectUpdate(long repositoryId,
		long lastAccessTime, int max, boolean retrieveFromCache)
		throws RemoteException {
		try {
			java.lang.String returnValue = SyncDLObjectServiceUtil.getSyncDLObjectUpdate(repositoryId,
					lastAccessTime, max, retrieveFromCache);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.sync.model.SyncDLObjectUpdate getSyncDLObjectUpdate(
		long repositoryId, long parentFolderId, long lastAccessTime)
		throws RemoteException {
		try {
			com.liferay.sync.model.SyncDLObjectUpdate returnValue = SyncDLObjectServiceUtil.getSyncDLObjectUpdate(repositoryId,
					parentFolderId, lastAccessTime);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.model.GroupSoap[] getUserSitesGroups()
		throws RemoteException {
		try {
			java.util.List<com.liferay.portal.model.Group> returnValue = SyncDLObjectServiceUtil.getUserSitesGroups();

			return com.liferay.portal.model.GroupSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.sync.model.SyncDLObjectSoap moveFileEntry(
		long fileEntryId, long newFolderId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.sync.model.SyncDLObject returnValue = SyncDLObjectServiceUtil.moveFileEntry(fileEntryId,
					newFolderId, serviceContext);

			return com.liferay.sync.model.SyncDLObjectSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.sync.model.SyncDLObjectSoap moveFileEntryToTrash(
		long fileEntryId) throws RemoteException {
		try {
			com.liferay.sync.model.SyncDLObject returnValue = SyncDLObjectServiceUtil.moveFileEntryToTrash(fileEntryId);

			return com.liferay.sync.model.SyncDLObjectSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.sync.model.SyncDLObjectSoap moveFolder(
		long folderId, long parentFolderId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.sync.model.SyncDLObject returnValue = SyncDLObjectServiceUtil.moveFolder(folderId,
					parentFolderId, serviceContext);

			return com.liferay.sync.model.SyncDLObjectSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.sync.model.SyncDLObjectSoap moveFolderToTrash(
		long folderId) throws RemoteException {
		try {
			com.liferay.sync.model.SyncDLObject returnValue = SyncDLObjectServiceUtil.moveFolderToTrash(folderId);

			return com.liferay.sync.model.SyncDLObjectSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.sync.model.SyncDLObjectSoap restoreFileEntryFromTrash(
		long fileEntryId) throws RemoteException {
		try {
			com.liferay.sync.model.SyncDLObject returnValue = SyncDLObjectServiceUtil.restoreFileEntryFromTrash(fileEntryId);

			return com.liferay.sync.model.SyncDLObjectSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.sync.model.SyncDLObjectSoap restoreFolderFromTrash(
		long folderId) throws RemoteException {
		try {
			com.liferay.sync.model.SyncDLObject returnValue = SyncDLObjectServiceUtil.restoreFolderFromTrash(folderId);

			return com.liferay.sync.model.SyncDLObjectSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.sync.model.SyncDLObjectSoap updateFolder(
		long folderId, java.lang.String name, java.lang.String description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.sync.model.SyncDLObject returnValue = SyncDLObjectServiceUtil.updateFolder(folderId,
					name, description, serviceContext);

			return com.liferay.sync.model.SyncDLObjectSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(SyncDLObjectServiceSoap.class);
}