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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for Folder. This utility wraps
 * {@link com.liferay.mail.service.impl.FolderLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see FolderLocalService
 * @see com.liferay.mail.service.base.FolderLocalServiceBaseImpl
 * @see com.liferay.mail.service.impl.FolderLocalServiceImpl
 * @generated
 */
@ProviderType
public class FolderLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.mail.service.impl.FolderLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the folder to the database. Also notifies the appropriate model listeners.
	*
	* @param folder the folder
	* @return the folder that was added
	*/
	public static com.liferay.mail.model.Folder addFolder(
		com.liferay.mail.model.Folder folder) {
		return getService().addFolder(folder);
	}

	public static com.liferay.mail.model.Folder addFolder(long userId,
		long accountId, java.lang.String fullName,
		java.lang.String displayName, int remoteMessageCount)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addFolder(userId, accountId, fullName, displayName,
			remoteMessageCount);
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
	* Deletes the folder from the database. Also notifies the appropriate model listeners.
	*
	* @param folder the folder
	* @return the folder that was removed
	* @throws PortalException
	*/
	public static com.liferay.mail.model.Folder deleteFolder(
		com.liferay.mail.model.Folder folder)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteFolder(folder);
	}

	/**
	* Deletes the folder with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param folderId the primary key of the folder
	* @return the folder that was removed
	* @throws PortalException if a folder with the primary key could not be found
	*/
	public static com.liferay.mail.model.Folder deleteFolder(long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteFolder(folderId);
	}

	public static void deleteFolders(long accountId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteFolders(accountId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.mail.model.Folder fetchFolder(long folderId) {
		return getService().fetchFolder(folderId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.mail.model.Folder getFolder(long accountId,
		java.lang.String fullName)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getFolder(accountId, fullName);
	}

	/**
	* Returns the folder with the primary key.
	*
	* @param folderId the primary key of the folder
	* @return the folder
	* @throws PortalException if a folder with the primary key could not be found
	*/
	public static com.liferay.mail.model.Folder getFolder(long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getFolder(folderId);
	}

	public static java.util.List<com.liferay.mail.model.Folder> getFolders(
		long accountId) {
		return getService().getFolders(accountId);
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
	public static java.util.List<com.liferay.mail.model.Folder> getFolders(
		int start, int end) {
		return getService().getFolders(start, end);
	}

	/**
	* Returns the number of folders.
	*
	* @return the number of folders
	*/
	public static int getFoldersCount() {
		return getService().getFoldersCount();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	public static int getLocalPageCount(long folderId, int messagesPerPage) {
		return getService().getLocalPageCount(folderId, messagesPerPage);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static int getPercentDownloaded(long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPercentDownloaded(folderId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	public static int getRemotePageCount(long folderId, int messagesPerPage)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getRemotePageCount(folderId, messagesPerPage);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Updates the folder in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param folder the folder
	* @return the folder that was updated
	*/
	public static com.liferay.mail.model.Folder updateFolder(
		com.liferay.mail.model.Folder folder) {
		return getService().updateFolder(folder);
	}

	public static com.liferay.mail.model.Folder updateFolder(long folderId,
		java.lang.String fullName, java.lang.String displayName,
		int remoteMessageCount)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateFolder(folderId, fullName, displayName,
			remoteMessageCount);
	}

	public static void clearService() {
		_service = null;
	}

	public static FolderLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					FolderLocalService.class.getName());

			if (invokableLocalService instanceof FolderLocalService) {
				_service = (FolderLocalService)invokableLocalService;
			}
			else {
				_service = new FolderLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(FolderLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setService(FolderLocalService service) {
	}

	private static FolderLocalService _service;
}