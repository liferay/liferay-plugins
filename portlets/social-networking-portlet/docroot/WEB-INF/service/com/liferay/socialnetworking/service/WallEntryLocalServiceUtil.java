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

package com.liferay.socialnetworking.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * <p>
 * This class provides static methods for the
 * {@link WallEntryLocalService} bean. The static methods of
 * this class calls the same methods of the bean instance. It's convenient to be
 * able to just write one line to call a method on a bean instead of writing a
 * lookup call and a method call.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       WallEntryLocalService
 * @generated
 */
public class WallEntryLocalServiceUtil {
	public static com.liferay.socialnetworking.model.WallEntry addWallEntry(
		com.liferay.socialnetworking.model.WallEntry wallEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addWallEntry(wallEntry);
	}

	public static com.liferay.socialnetworking.model.WallEntry createWallEntry(
		long wallEntryId) {
		return getService().createWallEntry(wallEntryId);
	}

	public static void deleteWallEntry(long wallEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteWallEntry(wallEntryId);
	}

	public static void deleteWallEntry(
		com.liferay.socialnetworking.model.WallEntry wallEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteWallEntry(wallEntry);
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

	public static com.liferay.socialnetworking.model.WallEntry getWallEntry(
		long wallEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getWallEntry(wallEntryId);
	}

	public static java.util.List<com.liferay.socialnetworking.model.WallEntry> getWallEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getWallEntries(start, end);
	}

	public static int getWallEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getWallEntriesCount();
	}

	public static com.liferay.socialnetworking.model.WallEntry updateWallEntry(
		com.liferay.socialnetworking.model.WallEntry wallEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateWallEntry(wallEntry);
	}

	public static com.liferay.socialnetworking.model.WallEntry updateWallEntry(
		com.liferay.socialnetworking.model.WallEntry wallEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateWallEntry(wallEntry, merge);
	}

	public static com.liferay.socialnetworking.model.WallEntry addWallEntry(
		long groupId, long userId, java.lang.String comments,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().addWallEntry(groupId, userId, comments, themeDisplay);
	}

	public static void deleteWallEntries(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteWallEntries(groupId);
	}

	public static java.util.List<com.liferay.socialnetworking.model.WallEntry> getWallEntries(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getWallEntries(groupId, start, end);
	}

	public static int getWallEntriesCount(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getWallEntriesCount(groupId);
	}

	public static java.util.List<com.liferay.socialnetworking.model.WallEntry> getWallToWallEntries(
		long groupId1, long groupId2, long userId1, long userId2, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getWallToWallEntries(groupId1, groupId2, userId1, userId2,
			start, end);
	}

	public static int getWallToWallEntriesCount(long groupId1, long groupId2,
		long userId1, long userId2)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getWallToWallEntriesCount(groupId1, groupId2, userId1,
			userId2);
	}

	public static com.liferay.socialnetworking.model.WallEntry updateWallEntry(
		long wallEntryId, java.lang.String comments)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().updateWallEntry(wallEntryId, comments);
	}

	public static void clearService() {
		_service = null;
	}

	public static WallEntryLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					WallEntryLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					WallEntryLocalService.class.getName(), portletClassLoader);

			_service = new WallEntryLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(WallEntryLocalService service) {
		_service = service;
	}

	private static WallEntryLocalService _service;
}