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
 * <a href="StatusLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class StatusLocalServiceUtil {
	public static com.liferay.chat.model.Status addStatus(
		com.liferay.chat.model.Status status)
		throws com.liferay.portal.SystemException {
		return getService().addStatus(status);
	}

	public static com.liferay.chat.model.Status createStatus(long statusId) {
		return getService().createStatus(statusId);
	}

	public static void deleteStatus(long statusId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteStatus(statusId);
	}

	public static void deleteStatus(com.liferay.chat.model.Status status)
		throws com.liferay.portal.SystemException {
		getService().deleteStatus(status);
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

	public static com.liferay.chat.model.Status getStatus(long statusId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getStatus(statusId);
	}

	public static java.util.List<com.liferay.chat.model.Status> getStatuses(
		int start, int end) throws com.liferay.portal.SystemException {
		return getService().getStatuses(start, end);
	}

	public static int getStatusesCount()
		throws com.liferay.portal.SystemException {
		return getService().getStatusesCount();
	}

	public static com.liferay.chat.model.Status updateStatus(
		com.liferay.chat.model.Status status)
		throws com.liferay.portal.SystemException {
		return getService().updateStatus(status);
	}

	public static com.liferay.chat.model.Status updateStatus(
		com.liferay.chat.model.Status status, boolean merge)
		throws com.liferay.portal.SystemException {
		return getService().updateStatus(status, merge);
	}

	public static java.util.List<Object[]> getAllStatuses(long userId,
		long modifiedDate, int start, int end)
		throws com.liferay.portal.SystemException {
		return getService().getAllStatuses(userId, modifiedDate, start, end);
	}

	public static java.util.List<Object[]> getGroupStatuses(long userId,
		long modifiedDate, int start, int end)
		throws com.liferay.portal.SystemException {
		return getService().getGroupStatuses(userId, modifiedDate, start, end);
	}

	public static java.util.List<Object[]> getSocialStatuses(long userId,
		int type, long modifiedDate, int start, int end)
		throws com.liferay.portal.SystemException {
		return getService()
				   .getSocialStatuses(userId, type, modifiedDate, start, end);
	}

	public static com.liferay.chat.model.Status getUserStatus(long userId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getUserStatus(userId);
	}

	public static com.liferay.chat.model.Status updateStatus(long userId,
		long modifiedDate)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().updateStatus(userId, modifiedDate);
	}

	public static com.liferay.chat.model.Status updateStatus(long userId,
		long modifiedDate, int online, int awake,
		java.lang.String activePanelId, java.lang.String message, int playSound)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService()
				   .updateStatus(userId, modifiedDate, online, awake,
			activePanelId, message, playSound);
	}

	public static void clearService() {
		_service = null;
	}

	public static StatusLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					StatusLocalServiceUtil.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					portletClassLoader);

			_service = new StatusLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(StatusLocalService service) {
		_service = service;
	}

	private static StatusLocalService _service;
}