/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link FolderLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       FolderLocalService
 * @generated
 */
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
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.mail.model.Folder addFolder(
		com.liferay.mail.model.Folder folder)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _folderLocalService.addFolder(folder);
	}

	/**
	* Creates a new folder with the primary key. Does not add the folder to the database.
	*
	* @param folderId the primary key for the new folder
	* @return the new folder
	*/
	public com.liferay.mail.model.Folder createFolder(long folderId) {
		return _folderLocalService.createFolder(folderId);
	}

	/**
	* Deletes the folder with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param folderId the primary key of the folder
	* @throws PortalException if a folder with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteFolder(long folderId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_folderLocalService.deleteFolder(folderId);
	}

	/**
	* Deletes the folder from the database. Also notifies the appropriate model listeners.
	*
	* @param folder the folder
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public void deleteFolder(com.liferay.mail.model.Folder folder)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_folderLocalService.deleteFolder(folder);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _folderLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _folderLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _folderLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _folderLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the folder with the primary key.
	*
	* @param folderId the primary key of the folder
	* @return the folder
	* @throws PortalException if a folder with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.mail.model.Folder getFolder(long folderId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _folderLocalService.getFolder(folderId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _folderLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the folders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of folders
	* @param end the upper bound of the range of folders (not inclusive)
	* @return the range of folders
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.mail.model.Folder> getFolders(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _folderLocalService.getFolders(start, end);
	}

	/**
	* Returns the number of folders.
	*
	* @return the number of folders
	* @throws SystemException if a system exception occurred
	*/
	public int getFoldersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _folderLocalService.getFoldersCount();
	}

	/**
	* Updates the folder in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param folder the folder
	* @return the folder that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.mail.model.Folder updateFolder(
		com.liferay.mail.model.Folder folder)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _folderLocalService.updateFolder(folder);
	}

	/**
	* Updates the folder in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param folder the folder
	* @param merge whether to merge the folder with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the folder that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.mail.model.Folder updateFolder(
		com.liferay.mail.model.Folder folder, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _folderLocalService.updateFolder(folder, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _folderLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_folderLocalService.setBeanIdentifier(beanIdentifier);
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

	public com.liferay.mail.model.Folder fetchFolder(long folderId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _folderLocalService.fetchFolder(folderId);
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

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public FolderLocalService getWrappedFolderLocalService() {
		return _folderLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedFolderLocalService(
		FolderLocalService folderLocalService) {
		_folderLocalService = folderLocalService;
	}

	public FolderLocalService getWrappedService() {
		return _folderLocalService;
	}

	public void setWrappedService(FolderLocalService folderLocalService) {
		_folderLocalService = folderLocalService;
	}

	private FolderLocalService _folderLocalService;
}