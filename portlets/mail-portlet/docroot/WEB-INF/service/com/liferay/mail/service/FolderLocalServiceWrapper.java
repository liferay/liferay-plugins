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

package com.liferay.mail.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link FolderLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see FolderLocalService
 * @generated
 */
@ProviderType
public class FolderLocalServiceWrapper implements FolderLocalService,
	ServiceWrapper<FolderLocalService> {
	public FolderLocalServiceWrapper(FolderLocalService folderLocalService) {
		_folderLocalService = folderLocalService;
	}

	/**
	* Adds the folder to the database. Also notifies the appropriate model listeners.
	*
	* @param folder the folder
	* @return the folder that was added
	*/
	@Override
	public com.liferay.mail.model.Folder addFolder(
		com.liferay.mail.model.Folder folder) {
		return _folderLocalService.addFolder(folder);
	}

	@Override
	public com.liferay.mail.model.Folder addFolder(long userId, long accountId,
		java.lang.String fullName, java.lang.String displayName,
		int remoteMessageCount)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _folderLocalService.addFolder(userId, accountId, fullName,
			displayName, remoteMessageCount);
	}

	/**
	* Creates a new folder with the primary key. Does not add the folder to the database.
	*
	* @param folderId the primary key for the new folder
	* @return the new folder
	*/
	@Override
	public com.liferay.mail.model.Folder createFolder(long folderId) {
		return _folderLocalService.createFolder(folderId);
	}

	/**
	* Deletes the folder from the database. Also notifies the appropriate model listeners.
	*
	* @param folder the folder
	* @return the folder that was removed
	* @throws PortalException
	*/
	@Override
	public com.liferay.mail.model.Folder deleteFolder(
		com.liferay.mail.model.Folder folder)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _folderLocalService.deleteFolder(folder);
	}

	/**
	* Deletes the folder with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param folderId the primary key of the folder
	* @return the folder that was removed
	* @throws PortalException if a folder with the primary key could not be found
	*/
	@Override
	public com.liferay.mail.model.Folder deleteFolder(long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _folderLocalService.deleteFolder(folderId);
	}

	@Override
	public com.liferay.mail.model.Folder fetchFolder(long folderId) {
		return _folderLocalService.fetchFolder(folderId);
	}

	@Override
	public com.liferay.mail.model.Folder getFolder(long accountId,
		java.lang.String fullName)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _folderLocalService.getFolder(accountId, fullName);
	}

	/**
	* Returns the folder with the primary key.
	*
	* @param folderId the primary key of the folder
	* @return the folder
	* @throws PortalException if a folder with the primary key could not be found
	*/
	@Override
	public com.liferay.mail.model.Folder getFolder(long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _folderLocalService.getFolder(folderId);
	}

	/**
	* Updates the folder in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param folder the folder
	* @return the folder that was updated
	*/
	@Override
	public com.liferay.mail.model.Folder updateFolder(
		com.liferay.mail.model.Folder folder) {
		return _folderLocalService.updateFolder(folder);
	}

	@Override
	public com.liferay.mail.model.Folder updateFolder(long folderId,
		java.lang.String fullName, java.lang.String displayName,
		int remoteMessageCount)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _folderLocalService.updateFolder(folderId, fullName,
			displayName, remoteMessageCount);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _folderLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _folderLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _folderLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _folderLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _folderLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of folders.
	*
	* @return the number of folders
	*/
	@Override
	public int getFoldersCount() {
		return _folderLocalService.getFoldersCount();
	}

	@Override
	public int getLocalPageCount(long folderId, int messagesPerPage) {
		return _folderLocalService.getLocalPageCount(folderId, messagesPerPage);
	}

	@Override
	public int getPercentDownloaded(long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _folderLocalService.getPercentDownloaded(folderId);
	}

	@Override
	public int getRemotePageCount(long folderId, int messagesPerPage)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _folderLocalService.getRemotePageCount(folderId, messagesPerPage);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _folderLocalService.invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _folderLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _folderLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.mail.model.impl.FolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _folderLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.mail.model.impl.FolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _folderLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the folders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.mail.model.impl.FolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of folders
	* @param end the upper bound of the range of folders (not inclusive)
	* @return the range of folders
	*/
	@Override
	public java.util.List<com.liferay.mail.model.Folder> getFolders(int start,
		int end) {
		return _folderLocalService.getFolders(start, end);
	}

	@Override
	public java.util.List<com.liferay.mail.model.Folder> getFolders(
		long accountId) {
		return _folderLocalService.getFolders(accountId);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _folderLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _folderLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public void deleteFolders(long accountId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_folderLocalService.deleteFolders(accountId);
	}

	@Override
	public FolderLocalService getWrappedService() {
		return _folderLocalService;
	}

	@Override
	public void setWrappedService(FolderLocalService folderLocalService) {
		_folderLocalService = folderLocalService;
	}

	private FolderLocalService _folderLocalService;
}