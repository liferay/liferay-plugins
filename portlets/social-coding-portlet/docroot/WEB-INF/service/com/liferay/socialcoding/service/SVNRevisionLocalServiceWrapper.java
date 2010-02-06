/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.socialcoding.service;

/**
 * <a href="SVNRevisionLocalServiceWrapper.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Brian Wing Shun Chan
 */
public class SVNRevisionLocalServiceWrapper implements SVNRevisionLocalService {
	public SVNRevisionLocalServiceWrapper(
		SVNRevisionLocalService svnRevisionLocalService) {
		_svnRevisionLocalService = svnRevisionLocalService;
	}

	public com.liferay.socialcoding.model.SVNRevision addSVNRevision(
		com.liferay.socialcoding.model.SVNRevision svnRevision)
		throws com.liferay.portal.SystemException {
		return _svnRevisionLocalService.addSVNRevision(svnRevision);
	}

	public com.liferay.socialcoding.model.SVNRevision createSVNRevision(
		long svnRevisionId) {
		return _svnRevisionLocalService.createSVNRevision(svnRevisionId);
	}

	public void deleteSVNRevision(long svnRevisionId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_svnRevisionLocalService.deleteSVNRevision(svnRevisionId);
	}

	public void deleteSVNRevision(
		com.liferay.socialcoding.model.SVNRevision svnRevision)
		throws com.liferay.portal.SystemException {
		_svnRevisionLocalService.deleteSVNRevision(svnRevision);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return _svnRevisionLocalService.dynamicQuery(dynamicQuery);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return _svnRevisionLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	public com.liferay.socialcoding.model.SVNRevision getSVNRevision(
		long svnRevisionId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _svnRevisionLocalService.getSVNRevision(svnRevisionId);
	}

	public java.util.List<com.liferay.socialcoding.model.SVNRevision> getSVNRevisions(
		int start, int end) throws com.liferay.portal.SystemException {
		return _svnRevisionLocalService.getSVNRevisions(start, end);
	}

	public int getSVNRevisionsCount() throws com.liferay.portal.SystemException {
		return _svnRevisionLocalService.getSVNRevisionsCount();
	}

	public com.liferay.socialcoding.model.SVNRevision updateSVNRevision(
		com.liferay.socialcoding.model.SVNRevision svnRevision)
		throws com.liferay.portal.SystemException {
		return _svnRevisionLocalService.updateSVNRevision(svnRevision);
	}

	public com.liferay.socialcoding.model.SVNRevision updateSVNRevision(
		com.liferay.socialcoding.model.SVNRevision svnRevision, boolean merge)
		throws com.liferay.portal.SystemException {
		return _svnRevisionLocalService.updateSVNRevision(svnRevision, merge);
	}

	public com.liferay.socialcoding.model.SVNRevision addSVNRevision(
		java.lang.String svnUserId, java.util.Date createDate,
		long svnRepositoryId, long revisionNumber, java.lang.String comments)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _svnRevisionLocalService.addSVNRevision(svnUserId, createDate,
			svnRepositoryId, revisionNumber, comments);
	}

	public com.liferay.socialcoding.model.SVNRevision getFirstSVNRevision(
		java.lang.String svnUserId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _svnRevisionLocalService.getFirstSVNRevision(svnUserId);
	}

	public com.liferay.socialcoding.model.SVNRevision getLastSVNRevision(
		java.lang.String svnUserId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _svnRevisionLocalService.getLastSVNRevision(svnUserId);
	}

	public java.util.List<com.liferay.socialcoding.model.SVNRevision> getSVNRevisions(
		java.lang.String svnUserId, int start, int end)
		throws com.liferay.portal.SystemException {
		return _svnRevisionLocalService.getSVNRevisions(svnUserId, start, end);
	}

	public java.util.List<com.liferay.socialcoding.model.SVNRevision> getSVNRevisions(
		long svnRepositoryId, int start, int end)
		throws com.liferay.portal.SystemException {
		return _svnRevisionLocalService.getSVNRevisions(svnRepositoryId, start,
			end);
	}

	public java.util.List<com.liferay.socialcoding.model.SVNRevision> getSVNRevisions(
		java.lang.String svnUserId, long svnRepositoryId, int start, int end)
		throws com.liferay.portal.SystemException {
		return _svnRevisionLocalService.getSVNRevisions(svnUserId,
			svnRepositoryId, start, end);
	}

	public int getSVNRevisionsCount(java.lang.String svnUserId)
		throws com.liferay.portal.SystemException {
		return _svnRevisionLocalService.getSVNRevisionsCount(svnUserId);
	}

	public int getSVNRevisionsCount(long svnRepositoryId)
		throws com.liferay.portal.SystemException {
		return _svnRevisionLocalService.getSVNRevisionsCount(svnRepositoryId);
	}

	public int getSVNRevisionsCount(java.lang.String svnUserId,
		long svnRepositoryId) throws com.liferay.portal.SystemException {
		return _svnRevisionLocalService.getSVNRevisionsCount(svnUserId,
			svnRepositoryId);
	}

	public SVNRevisionLocalService getWrappedSVNRevisionLocalService() {
		return _svnRevisionLocalService;
	}

	private SVNRevisionLocalService _svnRevisionLocalService;
}