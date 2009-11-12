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

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;

import com.liferay.socialcoding.model.SVNRevision;

import java.util.List;

/**
 * <a href="SVNRevisionUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class SVNRevisionUtil {
	public static void clearCache() {
		getPersistence().clearCache();
	}

	public static List<Object> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	public static List<Object> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	public static SVNRevision remove(SVNRevision svnRevision)
		throws SystemException {
		return getPersistence().remove(svnRevision);
	}

	public static SVNRevision update(SVNRevision svnRevision, boolean merge)
		throws SystemException {
		return getPersistence().update(svnRevision, merge);
	}

	public static void cacheResult(
		com.liferay.socialcoding.model.SVNRevision svnRevision) {
		getPersistence().cacheResult(svnRevision);
	}

	public static void cacheResult(
		java.util.List<com.liferay.socialcoding.model.SVNRevision> svnRevisions) {
		getPersistence().cacheResult(svnRevisions);
	}

	public static com.liferay.socialcoding.model.SVNRevision create(
		long svnRevisionId) {
		return getPersistence().create(svnRevisionId);
	}

	public static com.liferay.socialcoding.model.SVNRevision remove(
		long svnRevisionId)
		throws com.liferay.portal.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException {
		return getPersistence().remove(svnRevisionId);
	}

	public static com.liferay.socialcoding.model.SVNRevision updateImpl(
		com.liferay.socialcoding.model.SVNRevision svnRevision, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(svnRevision, merge);
	}

	public static com.liferay.socialcoding.model.SVNRevision findByPrimaryKey(
		long svnRevisionId)
		throws com.liferay.portal.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException {
		return getPersistence().findByPrimaryKey(svnRevisionId);
	}

	public static com.liferay.socialcoding.model.SVNRevision fetchByPrimaryKey(
		long svnRevisionId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(svnRevisionId);
	}

	public static java.util.List<com.liferay.socialcoding.model.SVNRevision> findBySVNUserId(
		java.lang.String svnUserId) throws com.liferay.portal.SystemException {
		return getPersistence().findBySVNUserId(svnUserId);
	}

	public static java.util.List<com.liferay.socialcoding.model.SVNRevision> findBySVNUserId(
		java.lang.String svnUserId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findBySVNUserId(svnUserId, start, end);
	}

	public static java.util.List<com.liferay.socialcoding.model.SVNRevision> findBySVNUserId(
		java.lang.String svnUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findBySVNUserId(svnUserId, start, end, obc);
	}

	public static com.liferay.socialcoding.model.SVNRevision findBySVNUserId_First(
		java.lang.String svnUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException {
		return getPersistence().findBySVNUserId_First(svnUserId, obc);
	}

	public static com.liferay.socialcoding.model.SVNRevision findBySVNUserId_Last(
		java.lang.String svnUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException {
		return getPersistence().findBySVNUserId_Last(svnUserId, obc);
	}

	public static com.liferay.socialcoding.model.SVNRevision[] findBySVNUserId_PrevAndNext(
		long svnRevisionId, java.lang.String svnUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException {
		return getPersistence()
				   .findBySVNUserId_PrevAndNext(svnRevisionId, svnUserId, obc);
	}

	public static java.util.List<com.liferay.socialcoding.model.SVNRevision> findBySVNRepositoryId(
		long svnRepositoryId) throws com.liferay.portal.SystemException {
		return getPersistence().findBySVNRepositoryId(svnRepositoryId);
	}

	public static java.util.List<com.liferay.socialcoding.model.SVNRevision> findBySVNRepositoryId(
		long svnRepositoryId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findBySVNRepositoryId(svnRepositoryId, start, end);
	}

	public static java.util.List<com.liferay.socialcoding.model.SVNRevision> findBySVNRepositoryId(
		long svnRepositoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findBySVNRepositoryId(svnRepositoryId, start, end, obc);
	}

	public static com.liferay.socialcoding.model.SVNRevision findBySVNRepositoryId_First(
		long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException {
		return getPersistence().findBySVNRepositoryId_First(svnRepositoryId, obc);
	}

	public static com.liferay.socialcoding.model.SVNRevision findBySVNRepositoryId_Last(
		long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException {
		return getPersistence().findBySVNRepositoryId_Last(svnRepositoryId, obc);
	}

	public static com.liferay.socialcoding.model.SVNRevision[] findBySVNRepositoryId_PrevAndNext(
		long svnRevisionId, long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException {
		return getPersistence()
				   .findBySVNRepositoryId_PrevAndNext(svnRevisionId,
			svnRepositoryId, obc);
	}

	public static java.util.List<com.liferay.socialcoding.model.SVNRevision> findBySVNU_SVNR(
		java.lang.String svnUserId, long svnRepositoryId)
		throws com.liferay.portal.SystemException {
		return getPersistence().findBySVNU_SVNR(svnUserId, svnRepositoryId);
	}

	public static java.util.List<com.liferay.socialcoding.model.SVNRevision> findBySVNU_SVNR(
		java.lang.String svnUserId, long svnRepositoryId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findBySVNU_SVNR(svnUserId, svnRepositoryId, start, end);
	}

	public static java.util.List<com.liferay.socialcoding.model.SVNRevision> findBySVNU_SVNR(
		java.lang.String svnUserId, long svnRepositoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findBySVNU_SVNR(svnUserId, svnRepositoryId, start, end, obc);
	}

	public static com.liferay.socialcoding.model.SVNRevision findBySVNU_SVNR_First(
		java.lang.String svnUserId, long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException {
		return getPersistence()
				   .findBySVNU_SVNR_First(svnUserId, svnRepositoryId, obc);
	}

	public static com.liferay.socialcoding.model.SVNRevision findBySVNU_SVNR_Last(
		java.lang.String svnUserId, long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException {
		return getPersistence()
				   .findBySVNU_SVNR_Last(svnUserId, svnRepositoryId, obc);
	}

	public static com.liferay.socialcoding.model.SVNRevision[] findBySVNU_SVNR_PrevAndNext(
		long svnRevisionId, java.lang.String svnUserId, long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException {
		return getPersistence()
				   .findBySVNU_SVNR_PrevAndNext(svnRevisionId, svnUserId,
			svnRepositoryId, obc);
	}

	public static java.util.List<com.liferay.socialcoding.model.SVNRevision> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.socialcoding.model.SVNRevision> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.socialcoding.model.SVNRevision> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end, obc);
	}

	public static void removeBySVNUserId(java.lang.String svnUserId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeBySVNUserId(svnUserId);
	}

	public static void removeBySVNRepositoryId(long svnRepositoryId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeBySVNRepositoryId(svnRepositoryId);
	}

	public static void removeBySVNU_SVNR(java.lang.String svnUserId,
		long svnRepositoryId) throws com.liferay.portal.SystemException {
		getPersistence().removeBySVNU_SVNR(svnUserId, svnRepositoryId);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countBySVNUserId(java.lang.String svnUserId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countBySVNUserId(svnUserId);
	}

	public static int countBySVNRepositoryId(long svnRepositoryId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countBySVNRepositoryId(svnRepositoryId);
	}

	public static int countBySVNU_SVNR(java.lang.String svnUserId,
		long svnRepositoryId) throws com.liferay.portal.SystemException {
		return getPersistence().countBySVNU_SVNR(svnUserId, svnRepositoryId);
	}

	public static int countAll() throws com.liferay.portal.SystemException {
		return getPersistence().countAll();
	}

	public static SVNRevisionPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (SVNRevisionPersistence)PortletBeanLocatorUtil.locate(com.liferay.socialcoding.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					SVNRevisionPersistence.class.getName());
		}

		return _persistence;
	}

	public void setPersistence(SVNRevisionPersistence persistence) {
		_persistence = persistence;
	}

	private static SVNRevisionPersistence _persistence;
}