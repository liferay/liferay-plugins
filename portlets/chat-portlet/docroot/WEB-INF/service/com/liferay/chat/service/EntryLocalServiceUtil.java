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

package com.liferay.chat.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * <a href="EntryLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class EntryLocalServiceUtil {
	public static com.liferay.chat.model.Entry addEntry(
		com.liferay.chat.model.Entry entry)
		throws com.liferay.portal.SystemException {
		return getService().addEntry(entry);
	}

	public static com.liferay.chat.model.Entry createEntry(long entryId) {
		return getService().createEntry(entryId);
	}

	public static void deleteEntry(long entryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteEntry(entryId);
	}

	public static void deleteEntry(com.liferay.chat.model.Entry entry)
		throws com.liferay.portal.SystemException {
		getService().deleteEntry(entry);
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

	public static com.liferay.chat.model.Entry getEntry(long entryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getEntry(entryId);
	}

	public static java.util.List<com.liferay.chat.model.Entry> getEntries(
		int start, int end) throws com.liferay.portal.SystemException {
		return getService().getEntries(start, end);
	}

	public static int getEntriesCount()
		throws com.liferay.portal.SystemException {
		return getService().getEntriesCount();
	}

	public static com.liferay.chat.model.Entry updateEntry(
		com.liferay.chat.model.Entry entry)
		throws com.liferay.portal.SystemException {
		return getService().updateEntry(entry);
	}

	public static com.liferay.chat.model.Entry updateEntry(
		com.liferay.chat.model.Entry entry, boolean merge)
		throws com.liferay.portal.SystemException {
		return getService().updateEntry(entry, merge);
	}

	public static com.liferay.chat.model.Entry addEntry(long fromUserId,
		long toUserId, java.lang.String content)
		throws com.liferay.portal.SystemException {
		return getService().addEntry(fromUserId, toUserId, content);
	}

	public static com.liferay.chat.model.Entry addEntry(long createDate,
		long fromUserId, long toUserId, java.lang.String content)
		throws com.liferay.portal.SystemException {
		return getService().addEntry(createDate, fromUserId, toUserId, content);
	}

	public static void deleteEntries(long userId)
		throws com.liferay.portal.SystemException {
		getService().deleteEntries(userId);
	}

	public static java.util.List<com.liferay.chat.model.Entry> getNewEntries(
		long userId, long createDate, int start, int end)
		throws com.liferay.portal.SystemException {
		return getService().getNewEntries(userId, createDate, start, end);
	}

	public static java.util.List<com.liferay.chat.model.Entry> getOldEntries(
		long createDate, int start, int end)
		throws com.liferay.portal.SystemException {
		return getService().getOldEntries(createDate, start, end);
	}

	public static EntryLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate("chat-portlet",
					EntryLocalServiceUtil.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate("chat-portlet",
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					portletClassLoader);

			_service = new EntryLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(EntryLocalService service) {
		_service = service;
	}

	private static EntryLocalService _service;
}