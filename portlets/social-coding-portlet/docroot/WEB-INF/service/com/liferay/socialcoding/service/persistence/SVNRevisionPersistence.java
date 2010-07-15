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

import com.liferay.socialcoding.model.SVNRevision;

/**
 * @author    Brian Wing Shun Chan
 * @see       SVNRevisionPersistenceImpl
 * @see       SVNRevisionUtil
 * @generated
 */
public interface SVNRevisionPersistence extends BasePersistence<SVNRevision> {
	public void cacheResult(
		com.liferay.socialcoding.model.SVNRevision svnRevision);

	public void cacheResult(
		java.util.List<com.liferay.socialcoding.model.SVNRevision> svnRevisions);

	public com.liferay.socialcoding.model.SVNRevision create(long svnRevisionId);

	public com.liferay.socialcoding.model.SVNRevision remove(long svnRevisionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException;

	public com.liferay.socialcoding.model.SVNRevision updateImpl(
		com.liferay.socialcoding.model.SVNRevision svnRevision, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.socialcoding.model.SVNRevision findByPrimaryKey(
		long svnRevisionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException;

	public com.liferay.socialcoding.model.SVNRevision fetchByPrimaryKey(
		long svnRevisionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.SVNRevision> findBySVNUserId(
		java.lang.String svnUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.SVNRevision> findBySVNUserId(
		java.lang.String svnUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.SVNRevision> findBySVNUserId(
		java.lang.String svnUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.socialcoding.model.SVNRevision findBySVNUserId_First(
		java.lang.String svnUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException;

	public com.liferay.socialcoding.model.SVNRevision findBySVNUserId_Last(
		java.lang.String svnUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException;

	public com.liferay.socialcoding.model.SVNRevision[] findBySVNUserId_PrevAndNext(
		long svnRevisionId, java.lang.String svnUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException;

	public java.util.List<com.liferay.socialcoding.model.SVNRevision> findBySVNRepositoryId(
		long svnRepositoryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.SVNRevision> findBySVNRepositoryId(
		long svnRepositoryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.SVNRevision> findBySVNRepositoryId(
		long svnRepositoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.socialcoding.model.SVNRevision findBySVNRepositoryId_First(
		long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException;

	public com.liferay.socialcoding.model.SVNRevision findBySVNRepositoryId_Last(
		long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException;

	public com.liferay.socialcoding.model.SVNRevision[] findBySVNRepositoryId_PrevAndNext(
		long svnRevisionId, long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException;

	public java.util.List<com.liferay.socialcoding.model.SVNRevision> findBySVNU_SVNR(
		java.lang.String svnUserId, long svnRepositoryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.SVNRevision> findBySVNU_SVNR(
		java.lang.String svnUserId, long svnRepositoryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.SVNRevision> findBySVNU_SVNR(
		java.lang.String svnUserId, long svnRepositoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.socialcoding.model.SVNRevision findBySVNU_SVNR_First(
		java.lang.String svnUserId, long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException;

	public com.liferay.socialcoding.model.SVNRevision findBySVNU_SVNR_Last(
		java.lang.String svnUserId, long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException;

	public com.liferay.socialcoding.model.SVNRevision[] findBySVNU_SVNR_PrevAndNext(
		long svnRevisionId, java.lang.String svnUserId, long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException;

	public java.util.List<com.liferay.socialcoding.model.SVNRevision> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.SVNRevision> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.SVNRevision> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeBySVNUserId(java.lang.String svnUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeBySVNRepositoryId(long svnRepositoryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeBySVNU_SVNR(java.lang.String svnUserId,
		long svnRepositoryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countBySVNUserId(java.lang.String svnUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countBySVNRepositoryId(long svnRepositoryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countBySVNU_SVNR(java.lang.String svnUserId, long svnRepositoryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}