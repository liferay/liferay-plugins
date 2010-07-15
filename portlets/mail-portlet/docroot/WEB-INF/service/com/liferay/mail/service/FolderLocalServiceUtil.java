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
 * <p>
 * This class provides static methods for the
 * {@link FolderLocalService} bean. The static methods of
 * this class calls the same methods of the bean instance. It's convenient to be
 * able to just write one line to call a method on a bean instead of writing a
 * lookup call and a method call.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       FolderLocalService
 * @generated
 */
public class FolderLocalServiceUtil {
	public static com.liferay.mail.model.Folder addFolder(
		com.liferay.mail.model.Folder folder)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addFolder(folder);
	}

	public static com.liferay.mail.model.Folder createFolder(long folderId) {
		return getService().createFolder(folderId);
	}

	public static void deleteFolder(long folderId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteFolder(folderId);
	}

	public static void deleteFolder(com.liferay.mail.model.Folder folder)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteFolder(folder);
	}

	@SuppressWarnings("unchecked")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	@SuppressWarnings("unchecked")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	public static com.liferay.mail.model.Folder getFolder(long folderId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getFolder(folderId);
	}

	public static java.util.List<com.liferay.mail.model.Folder> getFolders(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getFolders(start, end);
	}

	public static int getFoldersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getFoldersCount();
	}

	public static com.liferay.mail.model.Folder updateFolder(
		com.liferay.mail.model.Folder folder)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateFolder(folder);
	}

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