/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.wol.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * <a href="WallEntryLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class WallEntryLocalServiceUtil {
	public static com.liferay.wol.model.WallEntry addWallEntry(
		com.liferay.wol.model.WallEntry wallEntry)
		throws com.liferay.portal.SystemException {
		return getService().addWallEntry(wallEntry);
	}

	public static com.liferay.wol.model.WallEntry createWallEntry(
		long wallEntryId) {
		return getService().createWallEntry(wallEntryId);
	}

	public static void deleteWallEntry(long wallEntryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteWallEntry(wallEntryId);
	}

	public static void deleteWallEntry(
		com.liferay.wol.model.WallEntry wallEntry)
		throws com.liferay.portal.SystemException {
		getService().deleteWallEntry(wallEntry);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	public static com.liferay.wol.model.WallEntry getWallEntry(long wallEntryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getWallEntry(wallEntryId);
	}

	public static java.util.List<com.liferay.wol.model.WallEntry> getWallEntries(
		int start, int end) throws com.liferay.portal.SystemException {
		return getService().getWallEntries(start, end);
	}

	public static int getWallEntriesCount()
		throws com.liferay.portal.SystemException {
		return getService().getWallEntriesCount();
	}

	public static com.liferay.wol.model.WallEntry updateWallEntry(
		com.liferay.wol.model.WallEntry wallEntry)
		throws com.liferay.portal.SystemException {
		return getService().updateWallEntry(wallEntry);
	}

	public static com.liferay.wol.model.WallEntry addWallEntry(long groupId,
		long userId, java.lang.String comments,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().addWallEntry(groupId, userId, comments, themeDisplay);
	}

	public static void deleteWallEntries(long groupId)
		throws com.liferay.portal.SystemException {
		getService().deleteWallEntries(groupId);
	}

	public static java.util.List<com.liferay.wol.model.WallEntry> getWallEntries(
		long groupId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getService().getWallEntries(groupId, start, end);
	}

	public static int getWallEntriesCount(long groupId)
		throws com.liferay.portal.SystemException {
		return getService().getWallEntriesCount(groupId);
	}

	public static java.util.List<com.liferay.wol.model.WallEntry> getWallToWallEntries(
		long groupId1, long groupId2, long userId1, long userId2, int start,
		int end) throws com.liferay.portal.SystemException {
		return getService()
				   .getWallToWallEntries(groupId1, groupId2, userId1, userId2,
			start, end);
	}

	public static int getWallToWallEntriesCount(long groupId1, long groupId2,
		long userId1, long userId2) throws com.liferay.portal.SystemException {
		return getService()
				   .getWallToWallEntriesCount(groupId1, groupId2, userId1,
			userId2);
	}

	public static com.liferay.wol.model.WallEntry updateWallEntry(
		long wallEntryId, java.lang.String comments)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().updateWallEntry(wallEntryId, comments);
	}

	public static WallEntryLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate("wol-portlet",
					WallEntryLocalServiceUtil.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate("wol-portlet",
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					portletClassLoader);

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