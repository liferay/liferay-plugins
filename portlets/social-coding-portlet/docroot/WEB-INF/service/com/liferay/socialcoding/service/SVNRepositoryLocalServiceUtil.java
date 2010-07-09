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

package com.liferay.socialcoding.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * {@link SVNRepositoryLocalService} bean. The static methods of
 * this class calls the same methods of the bean instance. It's convenient to be
 * able to just write one line to call a method on a bean instead of writing a
 * lookup call and a method call.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SVNRepositoryLocalService
 * @generated
 */
public class SVNRepositoryLocalServiceUtil {
	public static com.liferay.socialcoding.model.SVNRepository addSVNRepository(
		com.liferay.socialcoding.model.SVNRepository svnRepository)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addSVNRepository(svnRepository);
	}

	public static com.liferay.socialcoding.model.SVNRepository createSVNRepository(
		long svnRepositoryId) {
		return getService().createSVNRepository(svnRepositoryId);
	}

	public static void deleteSVNRepository(long svnRepositoryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteSVNRepository(svnRepositoryId);
	}

	public static void deleteSVNRepository(
		com.liferay.socialcoding.model.SVNRepository svnRepository)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteSVNRepository(svnRepository);
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

	public static com.liferay.socialcoding.model.SVNRepository getSVNRepository(
		long svnRepositoryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getSVNRepository(svnRepositoryId);
	}

	public static java.util.List<com.liferay.socialcoding.model.SVNRepository> getSVNRepositories(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSVNRepositories(start, end);
	}

	public static int getSVNRepositoriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSVNRepositoriesCount();
	}

	public static com.liferay.socialcoding.model.SVNRepository updateSVNRepository(
		com.liferay.socialcoding.model.SVNRepository svnRepository)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateSVNRepository(svnRepository);
	}

	public static com.liferay.socialcoding.model.SVNRepository updateSVNRepository(
		com.liferay.socialcoding.model.SVNRepository svnRepository,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateSVNRepository(svnRepository, merge);
	}

	public static com.liferay.socialcoding.model.SVNRepository getSVNRepository(
		java.lang.String url)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getSVNRepository(url);
	}

	public static void updateSVNRepository(java.lang.String url)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().updateSVNRepository(url);
	}

	public static void clearService() {
		_service = null;
	}

	public static SVNRepositoryLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					SVNRepositoryLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					SVNRepositoryLocalService.class.getName(),
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