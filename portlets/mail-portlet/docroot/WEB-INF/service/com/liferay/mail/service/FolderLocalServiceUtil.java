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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * The utility for the folder local service. This utility wraps {@link com.liferay.mail.service.impl.FolderLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * Never modify this class directly. Add custom service methods to {@link com.liferay.mail.service.impl.FolderLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
 * </p>
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FolderLocalService
 * @see com.liferay.mail.service.base.FolderLocalServiceBaseImpl
 * @see com.liferay.mail.service.impl.FolderLocalServiceImpl
 * @generated
 */
public class FolderLocalServiceUtil {
	/**
	* Adds the folder to the database. Also notifies the appropriate model listeners.
	*
	* @param folder the folder to add
	* @return the folder that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.mail.model.Folder addFolder(
		com.liferay.mail.model.Folder folder)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addFolder(folder);
	}

	/**
	* Creates a new folder with the primary key. Does not add the folder to the database.
	*
	* @param folderId the primary key for the new folder
	* @return the new folder
	*/
	public static com.liferay.mail.model.Folder createFolder(long folderId) {
		return getService().createFolder(folderId);
	}

	/**
	* Deletes the folder with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param folderId the primary key of the folder to delete
	* @throws PortalException if a folder with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteFolder(long folderId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteFolder(folderId);
	}

	/**
	* Deletes the folder from the database. Also notifies the appropriate model listeners.
	*
	* @param folder the folder to delete
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteFolder(com.liferay.mail.model.Folder folder)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteFolder(folder);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("unchecked")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query to search with
	* @param start the lower bound of the range of model instances to return
	* @param end the upper bound of the range of model instances to return (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("unchecked")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query to search with
	* @param start the lower bound of the range of model instances to return
	* @param end the upper bound of the range of model instances to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("unchecked")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Counts the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Gets the folder with the primary key.
	*
	* @param folderId the primary key of the folder to get
	* @return the folder
	* @throws PortalException if a folder with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.mail.model.Folder getFolder(long folderId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getFolder(folderId);
	}

	/**
	* Gets a range of all the folders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of folders to return
	* @param end the upper bound of the range of folders to return (not inclusive)
	* @return the range of folders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.mail.model.Folder> getFolders(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getFolders(start, end);
	}

	/**
	* Gets the number of folders.
	*
	* @return the number of folders
	* @throws SystemException if a system exception occurred
	*/
	public static int getFoldersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getFoldersCount();
	}

	/**
	* Updates the folder in the database. Also notifies the appropriate model listeners.
	*
	* @param folder the folder to update
	* @return the folder that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.mail.model.Folder updateFolder(
		com.liferay.mail.model.Folder folder)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateFolder(folder);
	}

	/**
	* Updates the folder in the database. Also notifies the appropriate model listeners.
	*
	* @param folder the folder to update
	* @param merge whether to merge the folder with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the folder that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.mail.model.Folder updateFolder(
		com.liferay.mail.model.Folder folder, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateFolder(folder, merge);
	}

	public static com.liferay.mail.model.Folder addFolder(long userId,
		long accountId, java.lang.String fullName,
		java.lang.String displayName, int remoteMessageCount)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addFolder(userId, accountId, fullName, displayName,
			remoteMessageCount);
	}

	public static void deleteFolders(long accountId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteFolders(accountId);
	}

	public static com.liferay.mail.model.Folder getFolder(long accountId,
		java.lang.String fullName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getFolder(accountId, fullName);
	}

	public static java.util.List<com.liferay.mail.model.Folder> getFolders(
		long accountId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getFolders(accountId);
	}

	public static int getLocalPageCount(long folderId, int messagesPerPage)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getLocalPageCount(folderId, messagesPerPage);
	}

	public static int getPercentDownloaded(long folderId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPercentDownloaded(folderId);
	}

	public static int getRemotePageCount(long folderId, int messagesPerPage)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getRemotePageCount(folderId, messagesPerPage);
	}

	public static com.liferay.mail.model.Folder updateFolder(long folderId,
		java.lang.String fullName, java.lang.String displayName,
		int remoteMessageCount)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateFolder(folderId, fullName, displayName,
			remoteMessageCount);
	}

	public static void clearService() {
		_service = null;
	}

	public static FolderLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					FolderLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					FolderLocalService.class.getName(), portletClassLoader);

			_service = new FolderLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(FolderLocalService service) {
		_service = service;
	}

	private static FolderLocalService _service;
}