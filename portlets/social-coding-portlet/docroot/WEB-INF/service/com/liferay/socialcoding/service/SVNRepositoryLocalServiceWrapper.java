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
 * This class is a wrapper for {@link SVNRepositoryLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SVNRepositoryLocalService
 * @generated
 */
public class SVNRepositoryLocalServiceWrapper
	implements SVNRepositoryLocalService {
	public SVNRepositoryLocalServiceWrapper(
		SVNRepositoryLocalService svnRepositoryLocalService) {
		_svnRepositoryLocalService = svnRepositoryLocalService;
	}

	public com.liferay.socialcoding.model.SVNRepository addSVNRepository(
		com.liferay.socialcoding.model.SVNRepository svnRepository)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _svnRepositoryLocalService.addSVNRepository(svnRepository);
	}

	public com.liferay.socialcoding.model.SVNRepository createSVNRepository(
		long svnRepositoryId) {
		return _svnRepositoryLocalService.createSVNRepository(svnRepositoryId);
	}

	public void deleteSVNRepository(long svnRepositoryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_svnRepositoryLocalService.deleteSVNRepository(svnRepositoryId);
	}

	public void deleteSVNRepository(
		com.liferay.socialcoding.model.SVNRepository svnRepository)
		throws com.liferay.portal.kernel.exception.SystemException {
		_svnRepositoryLocalService.deleteSVNRepository(svnRepository);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _svnRepositoryLocalService.dynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _svnRepositoryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _svnRepositoryLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _svnRepositoryLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.socialcoding.model.SVNRepository getSVNRepository(
		long svnRepositoryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _svnRepositoryLocalService.getSVNRepository(svnRepositoryId);
	}

	public java.util.List<com.liferay.socialcoding.model.SVNRepository> getSVNRepositories(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _svnRepositoryLocalService.getSVNRepositories(start, end);
	}

	public int getSVNRepositoriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _svnRepositoryLocalService.getSVNRepositoriesCount();
	}

	public com.liferay.socialcoding.model.SVNRepository updateSVNRepository(
		com.liferay.socialcoding.model.SVNRepository svnRepository)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _svnRepositoryLocalService.updateSVNRepository(svnRepository);
	}

	public com.liferay.socialcoding.model.SVNRepository updateSVNRepository(
		com.liferay.socialcoding.model.SVNRepository svnRepository,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _svnRepositoryLocalService.updateSVNRepository(svnRepository,
			merge);
	}

	public com.liferay.socialcoding.model.SVNRepository getSVNRepository(
		java.lang.String url)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _svnRepositoryLocalService.getSVNRepository(url);
	}

	public void updateSVNRepository(java.lang.String url)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_svnRepositoryLocalService.updateSVNRepository(url);
	}

	public SVNRepositoryLocalService getWrappedSVNRepositoryLocalService() {
		return _svnRepositoryLocalService;
	}

	private SVNRepositoryLocalService _svnRepositoryLocalService;
}