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
 * <a href="SVNRepositoryLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class SVNRepositoryLocalServiceUtil {
	public static com.liferay.wol.model.SVNRepository addSVNRepository(
		com.liferay.wol.model.SVNRepository svnRepository)
		throws com.liferay.portal.SystemException {
		return getService().addSVNRepository(svnRepository);
	}

	public static com.liferay.wol.model.SVNRepository createSVNRepository(
		long svnRepositoryId) {
		return getService().createSVNRepository(svnRepositoryId);
	}

	public static void deleteSVNRepository(long svnRepositoryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteSVNRepository(svnRepositoryId);
	}

	public static void deleteSVNRepository(
		com.liferay.wol.model.SVNRepository svnRepository)
		throws com.liferay.portal.SystemException {
		getService().deleteSVNRepository(svnRepository);
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

	public static com.liferay.wol.model.SVNRepository getSVNRepository(
		long svnRepositoryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getSVNRepository(svnRepositoryId);
	}

	public static java.util.List<com.liferay.wol.model.SVNRepository> getSVNRepositories(
		int start, int end) throws com.liferay.portal.SystemException {
		return getService().getSVNRepositories(start, end);
	}

	public static int getSVNRepositoriesCount()
		throws com.liferay.portal.SystemException {
		return getService().getSVNRepositoriesCount();
	}

	public static com.liferay.wol.model.SVNRepository updateSVNRepository(
		com.liferay.wol.model.SVNRepository svnRepository)
		throws com.liferay.portal.SystemException {
		return getService().updateSVNRepository(svnRepository);
	}

	public static com.liferay.wol.model.SVNRepository updateSVNRepository(
		com.liferay.wol.model.SVNRepository svnRepository, boolean merge)
		throws com.liferay.portal.SystemException {
		return getService().updateSVNRepository(svnRepository, merge);
	}

	public static com.liferay.wol.model.SVNRepository getSVNRepository(
		java.lang.String url)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getSVNRepository(url);
	}

	public static void updateSVNRepository(java.lang.String url)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().updateSVNRepository(url);
	}

	public static SVNRepositoryLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate("wol-portlet",
					SVNRepositoryLocalServiceUtil.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate("wol-portlet",
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					portletClassLoader);

			_service = new SVNRepositoryLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(SVNRepositoryLocalService service) {
		_service = service;
	}

	private static SVNRepositoryLocalService _service;
}