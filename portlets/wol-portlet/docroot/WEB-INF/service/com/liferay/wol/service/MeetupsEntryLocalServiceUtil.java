/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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
 * <a href="MeetupsEntryLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class MeetupsEntryLocalServiceUtil {
	public static com.liferay.wol.model.MeetupsEntry addMeetupsEntry(
		com.liferay.wol.model.MeetupsEntry meetupsEntry)
		throws com.liferay.portal.SystemException {
		return getService().addMeetupsEntry(meetupsEntry);
	}

	public static com.liferay.wol.model.MeetupsEntry createMeetupsEntry(
		long meetupsEntryId) {
		return getService().createMeetupsEntry(meetupsEntryId);
	}

	public static void deleteMeetupsEntry(long meetupsEntryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteMeetupsEntry(meetupsEntryId);
	}

	public static void deleteMeetupsEntry(
		com.liferay.wol.model.MeetupsEntry meetupsEntry)
		throws com.liferay.portal.SystemException {
		getService().deleteMeetupsEntry(meetupsEntry);
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

	public static com.liferay.wol.model.MeetupsEntry getMeetupsEntry(
		long meetupsEntryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getMeetupsEntry(meetupsEntryId);
	}

	public static java.util.List<com.liferay.wol.model.MeetupsEntry> getMeetupsEntries(
		int start, int end) throws com.liferay.portal.SystemException {
		return getService().getMeetupsEntries(start, end);
	}

	public static int getMeetupsEntriesCount()
		throws com.liferay.portal.SystemException {
		return getService().getMeetupsEntriesCount();
	}

	public static com.liferay.wol.model.MeetupsEntry updateMeetupsEntry(
		com.liferay.wol.model.MeetupsEntry meetupsEntry)
		throws com.liferay.portal.SystemException {
		return getService().updateMeetupsEntry(meetupsEntry);
	}

	public static com.liferay.wol.model.MeetupsEntry addMeetupsEntry(
		long userId, java.lang.String title, java.lang.String description,
		int startDateMonth, int startDateDay, int startDateYear,
		int startDateHour, int startDateMinute, int endDateMonth,
		int endDateDay, int endDateYear, int endDateHour, int endDateMinute,
		int totalAttendees, int maxAttendees, double price, byte[] thumbnail)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService()
				   .addMeetupsEntry(userId, title, description, startDateMonth,
			startDateDay, startDateYear, startDateHour, startDateMinute,
			endDateMonth, endDateDay, endDateYear, endDateHour, endDateMinute,
			totalAttendees, maxAttendees, price, thumbnail);
	}

	public static java.util.List<com.liferay.wol.model.MeetupsEntry> getMeetupsEntries(
		long companyId) throws com.liferay.portal.SystemException {
		return getService().getMeetupsEntries(companyId);
	}

	public static com.liferay.wol.model.MeetupsEntry updateMeetupsEntry(
		long userId, long meetupsEntryId, java.lang.String title,
		java.lang.String description, int startDateMonth, int startDateDay,
		int startDateYear, int startDateHour, int startDateMinute,
		int endDateMonth, int endDateDay, int endDateYear, int endDateHour,
		int endDateMinute, int totalAttendees, int maxAttendees, double price,
		byte[] thumbnail)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService()
				   .updateMeetupsEntry(userId, meetupsEntryId, title,
			description, startDateMonth, startDateDay, startDateYear,
			startDateHour, startDateMinute, endDateMonth, endDateDay,
			endDateYear, endDateHour, endDateMinute, totalAttendees,
			maxAttendees, price, thumbnail);
	}

	public static MeetupsEntryLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate("wol-portlet",
					MeetupsEntryLocalServiceUtil.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate("wol-portlet",
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					portletClassLoader);

			_service = new MeetupsEntryLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(MeetupsEntryLocalService service) {
		_service = service;
	}

	private static MeetupsEntryLocalService _service;
}