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

package com.liferay.tms.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * <a href="ProjectEntryLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class ProjectEntryLocalServiceUtil {
	public static com.liferay.tms.model.ProjectEntry addProjectEntry(
		com.liferay.tms.model.ProjectEntry projectEntry)
		throws com.liferay.portal.SystemException {
		return getService().addProjectEntry(projectEntry);
	}

	public static com.liferay.tms.model.ProjectEntry createProjectEntry(
		long projectEntryId) {
		return getService().createProjectEntry(projectEntryId);
	}

	public static void deleteProjectEntry(long projectEntryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteProjectEntry(projectEntryId);
	}

	public static void deleteProjectEntry(
		com.liferay.tms.model.ProjectEntry projectEntry)
		throws com.liferay.portal.SystemException {
		getService().deleteProjectEntry(projectEntry);
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

	public static com.liferay.tms.model.ProjectEntry getProjectEntry(
		long projectEntryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getProjectEntry(projectEntryId);
	}

	public static java.util.List<com.liferay.tms.model.ProjectEntry> getProjectEntries(
		int start, int end) throws com.liferay.portal.SystemException {
		return getService().getProjectEntries(start, end);
	}

	public static int getProjectEntriesCount()
		throws com.liferay.portal.SystemException {
		return getService().getProjectEntriesCount();
	}

	public static com.liferay.tms.model.ProjectEntry updateProjectEntry(
		com.liferay.tms.model.ProjectEntry projectEntry)
		throws com.liferay.portal.SystemException {
		return getService().updateProjectEntry(projectEntry);
	}

	public static ProjectEntryLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate("tms-portlet",
					ProjectEntryLocalServiceUtil.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate("tms-portlet",
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					portletClassLoader);

			_service = new ProjectEntryLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(ProjectEntryLocalService service) {
		_service = service;
	}

	private static ProjectEntryLocalService _service;
}