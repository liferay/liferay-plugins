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

package com.liferay.sd.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * <a href="JIRAActionLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class JIRAActionLocalServiceUtil {
	public static com.liferay.sd.model.JIRAAction addJIRAAction(
		com.liferay.sd.model.JIRAAction jiraAction)
		throws com.liferay.portal.SystemException {
		return getService().addJIRAAction(jiraAction);
	}

	public static com.liferay.sd.model.JIRAAction createJIRAAction(
		long jiraActionId) {
		return getService().createJIRAAction(jiraActionId);
	}

	public static void deleteJIRAAction(long jiraActionId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteJIRAAction(jiraActionId);
	}

	public static void deleteJIRAAction(
		com.liferay.sd.model.JIRAAction jiraAction)
		throws com.liferay.portal.SystemException {
		getService().deleteJIRAAction(jiraAction);
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

	public static com.liferay.sd.model.JIRAAction getJIRAAction(
		long jiraActionId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getJIRAAction(jiraActionId);
	}

	public static java.util.List<com.liferay.sd.model.JIRAAction> getJIRAActions(
		int start, int end) throws com.liferay.portal.SystemException {
		return getService().getJIRAActions(start, end);
	}

	public static int getJIRAActionsCount()
		throws com.liferay.portal.SystemException {
		return getService().getJIRAActionsCount();
	}

	public static com.liferay.sd.model.JIRAAction updateJIRAAction(
		com.liferay.sd.model.JIRAAction jiraAction)
		throws com.liferay.portal.SystemException {
		return getService().updateJIRAAction(jiraAction);
	}

	public static com.liferay.sd.model.JIRAAction updateJIRAAction(
		com.liferay.sd.model.JIRAAction jiraAction, boolean merge)
		throws com.liferay.portal.SystemException {
		return getService().updateJIRAAction(jiraAction, merge);
	}

	public static JIRAActionLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate("sd-portlet",
					JIRAActionLocalServiceUtil.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate("sd-portlet",
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					portletClassLoader);

			_service = new JIRAActionLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(JIRAActionLocalService service) {
		_service = service;
	}

	private static JIRAActionLocalService _service;
}