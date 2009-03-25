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

package com.liferay.sn.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * <a href="JIRAChangeItemLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class JIRAChangeItemLocalServiceUtil {
	public static com.liferay.sn.model.JIRAChangeItem addJIRAChangeItem(
		com.liferay.sn.model.JIRAChangeItem jiraChangeItem)
		throws com.liferay.portal.SystemException {
		return getService().addJIRAChangeItem(jiraChangeItem);
	}

	public static com.liferay.sn.model.JIRAChangeItem createJIRAChangeItem(
		long jiraChangeItemId) {
		return getService().createJIRAChangeItem(jiraChangeItemId);
	}

	public static void deleteJIRAChangeItem(long jiraChangeItemId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteJIRAChangeItem(jiraChangeItemId);
	}

	public static void deleteJIRAChangeItem(
		com.liferay.sn.model.JIRAChangeItem jiraChangeItem)
		throws com.liferay.portal.SystemException {
		getService().deleteJIRAChangeItem(jiraChangeItem);
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

	public static com.liferay.sn.model.JIRAChangeItem getJIRAChangeItem(
		long jiraChangeItemId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getJIRAChangeItem(jiraChangeItemId);
	}

	public static java.util.List<com.liferay.sn.model.JIRAChangeItem> getJIRAChangeItems(
		int start, int end) throws com.liferay.portal.SystemException {
		return getService().getJIRAChangeItems(start, end);
	}

	public static int getJIRAChangeItemsCount()
		throws com.liferay.portal.SystemException {
		return getService().getJIRAChangeItemsCount();
	}

	public static com.liferay.sn.model.JIRAChangeItem updateJIRAChangeItem(
		com.liferay.sn.model.JIRAChangeItem jiraChangeItem)
		throws com.liferay.portal.SystemException {
		return getService().updateJIRAChangeItem(jiraChangeItem);
	}

	public static com.liferay.sn.model.JIRAChangeItem updateJIRAChangeItem(
		com.liferay.sn.model.JIRAChangeItem jiraChangeItem, boolean merge)
		throws com.liferay.portal.SystemException {
		return getService().updateJIRAChangeItem(jiraChangeItem, merge);
	}

	public static java.util.List<com.liferay.sn.model.JIRAChangeItem> getJIRAChangeItems(
		long jiraChangeGroupId) throws com.liferay.portal.SystemException {
		return getService().getJIRAChangeItems(jiraChangeGroupId);
	}

	public static JIRAChangeItemLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate("sn-portlet",
					JIRAChangeItemLocalServiceUtil.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate("sn-portlet",
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					portletClassLoader);

			_service = new JIRAChangeItemLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(JIRAChangeItemLocalService service) {
		_service = service;
	}

	private static JIRAChangeItemLocalService _service;
}