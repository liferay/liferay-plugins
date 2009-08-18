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
 * <a href="SVNRevisionLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class SVNRevisionLocalServiceUtil {
	public static com.liferay.wol.model.SVNRevision addSVNRevision(
		com.liferay.wol.model.SVNRevision svnRevision)
		throws com.liferay.portal.SystemException {
		return getService().addSVNRevision(svnRevision);
	}

	public static com.liferay.wol.model.SVNRevision createSVNRevision(
		long svnRevisionId) {
		return getService().createSVNRevision(svnRevisionId);
	}

	public static void deleteSVNRevision(long svnRevisionId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteSVNRevision(svnRevisionId);
	}

	public static void deleteSVNRevision(
		com.liferay.wol.model.SVNRevision svnRevision)
		throws com.liferay.portal.SystemException {
		getService().deleteSVNRevision(svnRevision);
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

	public static com.liferay.wol.model.SVNRevision getSVNRevision(
		long svnRevisionId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getSVNRevision(svnRevisionId);
	}

	public static java.util.List<com.liferay.wol.model.SVNRevision> getSVNRevisions(
		int start, int end) throws com.liferay.portal.SystemException {
		return getService().getSVNRevisions(start, end);
	}

	public static int getSVNRevisionsCount()
		throws com.liferay.portal.SystemException {
		return getService().getSVNRevisionsCount();
	}

	public static com.liferay.wol.model.SVNRevision updateSVNRevision(
		com.liferay.wol.model.SVNRevision svnRevision)
		throws com.liferay.portal.SystemException {
		return getService().updateSVNRevision(svnRevision);
	}

	public static com.liferay.wol.model.SVNRevision updateSVNRevision(
		com.liferay.wol.model.SVNRevision svnRevision, boolean merge)
		throws com.liferay.portal.SystemException {
		return getService().updateSVNRevision(svnRevision, merge);
	}

	public static com.liferay.wol.model.SVNRevision addSVNRevision(
		java.lang.String svnUserId, java.util.Date createDate,
		long svnRepositoryId, long revisionNumber, java.lang.String comments)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService()
				   .addSVNRevision(svnUserId, createDate, svnRepositoryId,
			revisionNumber, comments);
	}

	public static com.liferay.wol.model.SVNRevision getFirstSVNRevision(
		java.lang.String svnUserId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getFirstSVNRevision(svnUserId);
	}

	public static com.liferay.wol.model.SVNRevision getLastSVNRevision(
		java.lang.String svnUserId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getLastSVNRevision(svnUserId);
	}

	public static java.util.List<com.liferay.wol.model.SVNRevision> getSVNRevisions(
		java.lang.String svnUserId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getService().getSVNRevisions(svnUserId, start, end);
	}

	public static java.util.List<com.liferay.wol.model.SVNRevision> getSVNRevisions(
		long svnRepositoryId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getService().getSVNRevisions(svnRepositoryId, start, end);
	}

	public static java.util.List<com.liferay.wol.model.SVNRevision> getSVNRevisions(
		java.lang.String svnUserId, long svnRepositoryId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getService()
				   .getSVNRevisions(svnUserId, svnRepositoryId, start, end);
	}

	public static int getSVNRevisionsCount(java.lang.String svnUserId)
		throws com.liferay.portal.SystemException {
		return getService().getSVNRevisionsCount(svnUserId);
	}

	public static int getSVNRevisionsCount(long svnRepositoryId)
		throws com.liferay.portal.SystemException {
		return getService().getSVNRevisionsCount(svnRepositoryId);
	}

	public static int getSVNRevisionsCount(java.lang.String svnUserId,
		long svnRepositoryId) throws com.liferay.portal.SystemException {
		return getService().getSVNRevisionsCount(svnUserId, svnRepositoryId);
	}

	public static void clearService() {
		_service = null;
	}

	public static SVNRevisionLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					SVNRevisionLocalServiceUtil.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					portletClassLoader);

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