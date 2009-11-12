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

package com.liferay.socialcoding.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.socialcoding.model.SVNRevision;

/**
 * <a href="SVNRevisionPersistence.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public interface SVNRevisionPersistence extends BasePersistence<SVNRevision> {
	public void cacheResult(
		com.liferay.socialcoding.model.SVNRevision svnRevision);

	public void cacheResult(
		java.util.List<com.liferay.socialcoding.model.SVNRevision> svnRevisions);

	public com.liferay.socialcoding.model.SVNRevision create(long svnRevisionId);

	public com.liferay.socialcoding.model.SVNRevision remove(long svnRevisionId)
		throws com.liferay.portal.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException;

	public com.liferay.socialcoding.model.SVNRevision updateImpl(
		com.liferay.socialcoding.model.SVNRevision svnRevision, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.socialcoding.model.SVNRevision findByPrimaryKey(
		long svnRevisionId)
		throws com.liferay.portal.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException;

	public com.liferay.socialcoding.model.SVNRevision fetchByPrimaryKey(
		long svnRevisionId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.socialcoding.model.SVNRevision> findBySVNUserId(
		java.lang.String svnUserId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.socialcoding.model.SVNRevision> findBySVNUserId(
		java.lang.String svnUserId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.socialcoding.model.SVNRevision> findBySVNUserId(
		java.lang.String svnUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.socialcoding.model.SVNRevision findBySVNUserId_First(
		java.lang.String svnUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException;

	public com.liferay.socialcoding.model.SVNRevision findBySVNUserId_Last(
		java.lang.String svnUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException;

	public com.liferay.socialcoding.model.SVNRevision[] findBySVNUserId_PrevAndNext(
		long svnRevisionId, java.lang.String svnUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException;

	public java.util.List<com.liferay.socialcoding.model.SVNRevision> findBySVNRepositoryId(
		long svnRepositoryId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.socialcoding.model.SVNRevision> findBySVNRepositoryId(
		long svnRepositoryId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.socialcoding.model.SVNRevision> findBySVNRepositoryId(
		long svnRepositoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.socialcoding.model.SVNRevision findBySVNRepositoryId_First(
		long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException;

	public com.liferay.socialcoding.model.SVNRevision findBySVNRepositoryId_Last(
		long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException;

	public com.liferay.socialcoding.model.SVNRevision[] findBySVNRepositoryId_PrevAndNext(
		long svnRevisionId, long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException;

	public java.util.List<com.liferay.socialcoding.model.SVNRevision> findBySVNU_SVNR(
		java.lang.String svnUserId, long svnRepositoryId)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.socialcoding.model.SVNRevision> findBySVNU_SVNR(
		java.lang.String svnUserId, long svnRepositoryId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.socialcoding.model.SVNRevision> findBySVNU_SVNR(
		java.lang.String svnUserId, long svnRepositoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.socialcoding.model.SVNRevision findBySVNU_SVNR_First(
		java.lang.String svnUserId, long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException;

	public com.liferay.socialcoding.model.SVNRevision findBySVNU_SVNR_Last(
		java.lang.String svnUserId, long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException;

	public com.liferay.socialcoding.model.SVNRevision[] findBySVNU_SVNR_PrevAndNext(
		long svnRevisionId, java.lang.String svnUserId, long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException;

	public java.util.List<com.liferay.socialcoding.model.SVNRevision> findAll()
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.socialcoding.model.SVNRevision> findAll(
		int start, int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.socialcoding.model.SVNRevision> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public void removeBySVNUserId(java.lang.String svnUserId)
		throws com.liferay.portal.SystemException;

	public void removeBySVNRepositoryId(long svnRepositoryId)
		throws com.liferay.portal.SystemException;

	public void removeBySVNU_SVNR(java.lang.String svnUserId,
		long svnRepositoryId) throws com.liferay.portal.SystemException;

	public void removeAll() throws com.liferay.portal.SystemException;

	public int countBySVNUserId(java.lang.String svnUserId)
		throws com.liferay.portal.SystemException;

	public int countBySVNRepositoryId(long svnRepositoryId)
		throws com.liferay.portal.SystemException;

	public int countBySVNU_SVNR(java.lang.String svnUserId, long svnRepositoryId)
		throws com.liferay.portal.SystemException;

	public int countAll() throws com.liferay.portal.SystemException;
}