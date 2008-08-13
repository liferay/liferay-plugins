/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

/**
 * <a href="SVNRevisionLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class SVNRevisionLocalServiceUtil {
	public static com.liferay.wol.model.SVNRevision addSVNRevision(
		com.liferay.wol.model.SVNRevision svnRevision)
		throws com.liferay.portal.SystemException {
		return _service.addSVNRevision(svnRevision);
	}

	public static void deleteSVNRevision(long svnRevisionId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_service.deleteSVNRevision(svnRevisionId);
	}

	public static void deleteSVNRevision(
		com.liferay.wol.model.SVNRevision svnRevision)
		throws com.liferay.portal.SystemException {
		_service.deleteSVNRevision(svnRevision);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return _service.dynamicQuery(dynamicQuery);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return _service.dynamicQuery(dynamicQuery, start, end);
	}

	public static com.liferay.wol.model.SVNRevision getSVNRevision(
		long svnRevisionId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _service.getSVNRevision(svnRevisionId);
	}

	public static java.util.List<com.liferay.wol.model.SVNRevision> getSVNRevisions(
		int start, int end) throws com.liferay.portal.SystemException {
		return _service.getSVNRevisions(start, end);
	}

	public static int getSVNRevisionsCount()
		throws com.liferay.portal.SystemException {
		return _service.getSVNRevisionsCount();
	}

	public static com.liferay.wol.model.SVNRevision updateSVNRevision(
		com.liferay.wol.model.SVNRevision svnRevision)
		throws com.liferay.portal.SystemException {
		return _service.updateSVNRevision(svnRevision);
	}

	public static com.liferay.wol.model.SVNRevision addSVNRevision(
		java.lang.String svnUserId, java.util.Date createDate,
		long svnRepositoryId, long revisionNumber, java.lang.String comments)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _service.addSVNRevision(svnUserId, createDate, svnRepositoryId,
			revisionNumber, comments);
	}

	public static com.liferay.wol.model.SVNRevision getFirstSVNRevision(
		java.lang.String svnUserId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _service.getFirstSVNRevision(svnUserId);
	}

	public static com.liferay.wol.model.SVNRevision getLastSVNRevision(
		java.lang.String svnUserId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _service.getLastSVNRevision(svnUserId);
	}

	public static java.util.List<com.liferay.wol.model.SVNRevision> getSVNRevisions(
		java.lang.String svnUserId, int start, int end)
		throws com.liferay.portal.SystemException {
		return _service.getSVNRevisions(svnUserId, start, end);
	}

	public static java.util.List<com.liferay.wol.model.SVNRevision> getSVNRevisions(
		long svnRepositoryId, int start, int end)
		throws com.liferay.portal.SystemException {
		return _service.getSVNRevisions(svnRepositoryId, start, end);
	}

	public static java.util.List<com.liferay.wol.model.SVNRevision> getSVNRevisions(
		java.lang.String svnUserId, long svnRepositoryId, int start, int end)
		throws com.liferay.portal.SystemException {
		return _service.getSVNRevisions(svnUserId, svnRepositoryId, start, end);
	}

	public static int getSVNRevisionsCount(java.lang.String svnUserId)
		throws com.liferay.portal.SystemException {
		return _service.getSVNRevisionsCount(svnUserId);
	}

	public static int getSVNRevisionsCount(long svnRepositoryId)
		throws com.liferay.portal.SystemException {
		return _service.getSVNRevisionsCount(svnRepositoryId);
	}

	public static int getSVNRevisionsCount(java.lang.String svnUserId,
		long svnRepositoryId) throws com.liferay.portal.SystemException {
		return _service.getSVNRevisionsCount(svnUserId, svnRepositoryId);
	}

	public static SVNRevisionLocalService getService() {
		return _service;
	}

	public void setService(SVNRevisionLocalService service) {
		_service = service;
	}

	private static SVNRevisionLocalService _service;
}