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

package com.liferay.socialcoding.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.socialcoding.model.SVNRepository;

/**
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SVNRepositoryPersistenceImpl
 * @see       SVNRepositoryUtil
 * @generated
 */
public interface SVNRepositoryPersistence extends BasePersistence<SVNRepository> {
	public void cacheResult(
		com.liferay.socialcoding.model.SVNRepository svnRepository);

	public void cacheResult(
		java.util.List<com.liferay.socialcoding.model.SVNRepository> svnRepositories);

	public com.liferay.socialcoding.model.SVNRepository create(
		long svnRepositoryId);

	public com.liferay.socialcoding.model.SVNRepository remove(
		long svnRepositoryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchSVNRepositoryException;

	public com.liferay.socialcoding.model.SVNRepository updateImpl(
		com.liferay.socialcoding.model.SVNRepository svnRepository,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.socialcoding.model.SVNRepository findByPrimaryKey(
		long svnRepositoryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchSVNRepositoryException;

	public com.liferay.socialcoding.model.SVNRepository fetchByPrimaryKey(
		long svnRepositoryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.socialcoding.model.SVNRepository findByUrl(
		java.lang.String url)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchSVNRepositoryException;

	public com.liferay.socialcoding.model.SVNRepository fetchByUrl(
		java.lang.String url)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.socialcoding.model.SVNRepository fetchByUrl(
		java.lang.String url, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.SVNRepository> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.SVNRepository> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.SVNRepository> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByUrl(java.lang.String url)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchSVNRepositoryException;

	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByUrl(java.lang.String url)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}