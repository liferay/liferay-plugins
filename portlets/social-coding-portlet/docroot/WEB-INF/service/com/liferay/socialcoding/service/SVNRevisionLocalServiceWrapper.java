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


/**
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a wrapper for {@link SVNRevisionLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SVNRevisionLocalService
 * @generated
 */
public class SVNRevisionLocalServiceWrapper implements SVNRevisionLocalService {
	public SVNRevisionLocalServiceWrapper(
		SVNRevisionLocalService svnRevisionLocalService) {
		_svnRevisionLocalService = svnRevisionLocalService;
	}

	public com.liferay.socialcoding.model.SVNRevision addSVNRevision(
		com.liferay.socialcoding.model.SVNRevision svnRevision)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _svnRevisionLocalService.addSVNRevision(svnRevision);
	}

	public com.liferay.socialcoding.model.SVNRevision createSVNRevision(
		long svnRevisionId) {
		return _svnRevisionLocalService.createSVNRevision(svnRevisionId);
	}

	public void deleteSVNRevision(long svnRevisionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_svnRevisionLocalService.deleteSVNRevision(svnRevisionId);
	}

	public void deleteSVNRevision(
		com.liferay.socialcoding.model.SVNRevision svnRevision)
		throws com.liferay.portal.kernel.exception.SystemException {
		_svnRevisionLocalService.deleteSVNRevision(svnRevision);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _svnRevisionLocalService.dynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _svnRevisionLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _svnRevisionLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _svnRevisionLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.socialcoding.model.SVNRevision getSVNRevision(
		long svnRevisionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _svnRevisionLocalService.getSVNRevision(svnRevisionId);
	}

	public java.util.List<com.liferay.socialcoding.model.SVNRevision> getSVNRevisions(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _svnRevisionLocalService.getSVNRevisions(start, end);
	}

	public int getSVNRevisionsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _svnRevisionLocalService.getSVNRevisionsCount();
	}

	public com.liferay.socialcoding.model.SVNRevision updateSVNRevision(
		com.liferay.socialcoding.model.SVNRevision svnRevision)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _svnRevisionLocalService.updateSVNRevision(svnRevision);
	}

	public com.liferay.socialcoding.model.SVNRevision updateSVNRevision(
		com.liferay.socialcoding.model.SVNRevision svnRevision, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _svnRevisionLocalService.updateSVNRevision(svnRevision, merge);
	}

	public com.liferay.socialcoding.model.SVNRevision addSVNRevision(
		java.lang.String svnUserId, java.util.Date createDate,
		long svnRepositoryId, long revisionNumber, java.lang.String comments)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _svnRevisionLocalService.addSVNRevision(svnUserId, createDate,
			svnRepositoryId, revisionNumber, comments);
	}

	public com.liferay.socialcoding.model.SVNRevision getFirstSVNRevision(
		java.lang.String svnUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _svnRevisionLocalService.getFirstSVNRevision(svnUserId);
	}

	public com.liferay.socialcoding.model.SVNRevision getLastSVNRevision(
		java.lang.String svnUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _svnRevisionLocalService.getLastSVNRevision(svnUserId);
	}

	public java.util.List<com.liferay.socialcoding.model.SVNRevision> getSVNRevisions(
		java.lang.String svnUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _svnRevisionLocalService.getSVNRevisions(svnUserId, start, end);
	}

	public java.util.List<com.liferay.socialcoding.model.SVNRevision> getSVNRevisions(
		long svnRepositoryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _svnRevisionLocalService.getSVNRevisions(svnRepositoryId, start,
			end);
	}

	public java.util.List<com.liferay.socialcoding.model.SVNRevision> getSVNRevisions(
		java.lang.String svnUserId, long svnRepositoryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _svnRevisionLocalService.getSVNRevisions(svnUserId,
			svnRepositoryId, start, end);
	}

	public int getSVNRevisionsCount(java.lang.String svnUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _svnRevisionLocalService.getSVNRevisionsCount(svnUserId);
	}

	public int getSVNRevisionsCount(long svnRepositoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _svnRevisionLocalService.getSVNRevisionsCount(svnRepositoryId);
	}

	public int getSVNRevisionsCount(java.lang.String svnUserId,
		long svnRepositoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _svnRevisionLocalService.getSVNRevisionsCount(svnUserId,
			svnRepositoryId);
	}

	public SVNRevisionLocalService getWrappedSVNRevisionLocalService() {
		return _svnRevisionLocalService;
	}

	private SVNRevisionLocalService _svnRevisionLocalService;
}