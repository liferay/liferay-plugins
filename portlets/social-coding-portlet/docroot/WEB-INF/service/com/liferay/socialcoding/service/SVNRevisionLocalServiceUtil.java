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
 * {@link SVNRevisionLocalService} bean. The static methods of
 * this class calls the same methods of the bean instance. It's convenient to be
 * able to just write one line to call a method on a bean instead of writing a
 * lookup call and a method call.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SVNRevisionLocalService
 * @generated
 */
public class SVNRevisionLocalServiceUtil {
	public static com.liferay.socialcoding.model.SVNRevision addSVNRevision(
		com.liferay.socialcoding.model.SVNRevision svnRevision)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addSVNRevision(svnRevision);
	}

	public static com.liferay.socialcoding.model.SVNRevision createSVNRevision(
		long svnRevisionId) {
		return getService().createSVNRevision(svnRevisionId);
	}

	public static void deleteSVNRevision(long svnRevisionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteSVNRevision(svnRevisionId);
	}

	public static void deleteSVNRevision(
		com.liferay.socialcoding.model.SVNRevision svnRevision)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteSVNRevision(svnRevision);
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

	public static com.liferay.socialcoding.model.SVNRevision getSVNRevision(
		long svnRevisionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getSVNRevision(svnRevisionId);
	}

	public static java.util.List<com.liferay.socialcoding.model.SVNRevision> getSVNRevisions(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSVNRevisions(start, end);
	}

	public static int getSVNRevisionsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSVNRevisionsCount();
	}

	public static com.liferay.socialcoding.model.SVNRevision updateSVNRevision(
		com.liferay.socialcoding.model.SVNRevision svnRevision)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateSVNRevision(svnRevision);
	}

	public static com.liferay.socialcoding.model.SVNRevision updateSVNRevision(
		com.liferay.socialcoding.model.SVNRevision svnRevision, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateSVNRevision(svnRevision, merge);
	}

	public static com.liferay.socialcoding.model.SVNRevision addSVNRevision(
		java.lang.String svnUserId, java.util.Date createDate,
		long svnRepositoryId, long revisionNumber, java.lang.String comments)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addSVNRevision(svnUserId, createDate, svnRepositoryId,
			revisionNumber, comments);
	}

	public static com.liferay.socialcoding.model.SVNRevision getFirstSVNRevision(
		java.lang.String svnUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getFirstSVNRevision(svnUserId);
	}

	public static com.liferay.socialcoding.model.SVNRevision getLastSVNRevision(
		java.lang.String svnUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getLastSVNRevision(svnUserId);
	}

	public static java.util.List<com.liferay.socialcoding.model.SVNRevision> getSVNRevisions(
		java.lang.String svnUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSVNRevisions(svnUserId, start, end);
	}

	public static java.util.List<com.liferay.socialcoding.model.SVNRevision> getSVNRevisions(
		long svnRepositoryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSVNRevisions(svnRepositoryId, start, end);
	}

	public static java.util.List<com.liferay.socialcoding.model.SVNRevision> getSVNRevisions(
		java.lang.String svnUserId, long svnRepositoryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getSVNRevisions(svnUserId, svnRepositoryId, start, end);
	}

	public static int getSVNRevisionsCount(java.lang.String svnUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSVNRevisionsCount(svnUserId);
	}

	public static int getSVNRevisionsCount(long svnRepositoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSVNRevisionsCount(svnRepositoryId);
	}

	public static int getSVNRevisionsCount(java.lang.String svnUserId,
		long svnRepositoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSVNRevisionsCount(svnUserId, svnRepositoryId);
	}

	public static void clearService() {
		_service = null;
	}

	public static SVNRevisionLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					SVNRevisionLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					SVNRevisionLocalService.class.getName(), portletClassLoader);

			_service = new SVNRevisionLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(SVNRevisionLocalService service) {
		_service = service;
	}

	private static SVNRevisionLocalService _service;
}