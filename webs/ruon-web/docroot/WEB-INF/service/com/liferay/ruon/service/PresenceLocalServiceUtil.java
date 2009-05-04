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

package com.liferay.ruon.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * <a href="PresenceLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class PresenceLocalServiceUtil {
	public static com.liferay.ruon.model.Presence addPresence(
		com.liferay.ruon.model.Presence presence)
		throws com.liferay.portal.SystemException {
		return getService().addPresence(presence);
	}

	public static com.liferay.ruon.model.Presence createPresence(
		long presenceId) {
		return getService().createPresence(presenceId);
	}

	public static void deletePresence(long presenceId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deletePresence(presenceId);
	}

	public static void deletePresence(com.liferay.ruon.model.Presence presence)
		throws com.liferay.portal.SystemException {
		getService().deletePresence(presence);
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

	public static com.liferay.ruon.model.Presence getPresence(long presenceId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getPresence(presenceId);
	}

	public static java.util.List<com.liferay.ruon.model.Presence> getPresences(
		int start, int end) throws com.liferay.portal.SystemException {
		return getService().getPresences(start, end);
	}

	public static int getPresencesCount()
		throws com.liferay.portal.SystemException {
		return getService().getPresencesCount();
	}

	public static com.liferay.ruon.model.Presence updatePresence(
		com.liferay.ruon.model.Presence presence)
		throws com.liferay.portal.SystemException {
		return getService().updatePresence(presence);
	}

	public static com.liferay.ruon.model.Presence updatePresence(
		com.liferay.ruon.model.Presence presence, boolean merge)
		throws com.liferay.portal.SystemException {
		return getService().updatePresence(presence, merge);
	}

	public static com.liferay.ruon.model.Presence getPresence(long userId,
		java.lang.String networkName)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getPresence(userId, networkName);
	}

	public static java.util.List<com.liferay.ruon.model.Presence> getPresences(
		long userId, boolean online)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getPresences(userId, online);
	}

	public static com.liferay.ruon.model.Presence updatePresence(long userId,
		java.lang.String networkName, boolean online)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().updatePresence(userId, networkName, online);
	}

	public static void clearService() {
		_service = null;
	}

	public static PresenceLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					PresenceLocalServiceUtil.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					portletClassLoader);

			_service = new PresenceLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(PresenceLocalService service) {
		_service = service;
	}

	private static PresenceLocalService _service;
}