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

package com.liferay.wol.service.persistence;

/**
 * <a href="SVNRevisionUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class SVNRevisionUtil {
	public static com.liferay.wol.model.SVNRevision create(long svnRevisionId) {
		return getPersistence().create(svnRevisionId);
	}

	public static com.liferay.wol.model.SVNRevision remove(long svnRevisionId)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchSVNRevisionException {
		return getPersistence().remove(svnRevisionId);
	}

	public static com.liferay.wol.model.SVNRevision remove(
		com.liferay.wol.model.SVNRevision svnRevision)
		throws com.liferay.portal.SystemException {
		return getPersistence().remove(svnRevision);
	}

	public static com.liferay.wol.model.SVNRevision update(
		com.liferay.wol.model.SVNRevision svnRevision)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(svnRevision);
	}

	public static com.liferay.wol.model.SVNRevision update(
		com.liferay.wol.model.SVNRevision svnRevision, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(svnRevision, merge);
	}

	public static com.liferay.wol.model.SVNRevision updateImpl(
		com.liferay.wol.model.SVNRevision svnRevision, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(svnRevision, merge);
	}

	public static com.liferay.wol.model.SVNRevision findByPrimaryKey(
		long svnRevisionId)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchSVNRevisionException {
		return getPersistence().findByPrimaryKey(svnRevisionId);
	}

	public static com.liferay.wol.model.SVNRevision fetchByPrimaryKey(
		long svnRevisionId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(svnRevisionId);
	}

	public static java.util.List<com.liferay.wol.model.SVNRevision> findBySVNUserId(
		java.lang.String svnUserId) throws com.liferay.portal.SystemException {
		return getPersistence().findBySVNUserId(svnUserId);
	}

	public static java.util.List<com.liferay.wol.model.SVNRevision> findBySVNUserId(
		java.lang.String svnUserId, int begin, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findBySVNUserId(svnUserId, begin, end);
	}

	public static java.util.List<com.liferay.wol.model.SVNRevision> findBySVNUserId(
		java.lang.String svnUserId, int begin, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findBySVNUserId(svnUserId, begin, end, obc);
	}

	public static com.liferay.wol.model.SVNRevision findBySVNUserId_First(
		java.lang.String svnUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchSVNRevisionException {
		return getPersistence().findBySVNUserId_First(svnUserId, obc);
	}

	public static com.liferay.wol.model.SVNRevision findBySVNUserId_Last(
		java.lang.String svnUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchSVNRevisionException {
		return getPersistence().findBySVNUserId_Last(svnUserId, obc);
	}

	public static com.liferay.wol.model.SVNRevision[] findBySVNUserId_PrevAndNext(
		long svnRevisionId, java.lang.String svnUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchSVNRevisionException {
		return getPersistence()
				   .findBySVNUserId_PrevAndNext(svnRevisionId, svnUserId, obc);
	}

	public static java.util.List<com.liferay.wol.model.SVNRevision> findBySVNRepositoryId(
		long svnRepositoryId) throws com.liferay.portal.SystemException {
		return getPersistence().findBySVNRepositoryId(svnRepositoryId);
	}

	public static java.util.List<com.liferay.wol.model.SVNRevision> findBySVNRepositoryId(
		long svnRepositoryId, int begin, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findBySVNRepositoryId(svnRepositoryId, begin, end);
	}

	public static java.util.List<com.liferay.wol.model.SVNRevision> findBySVNRepositoryId(
		long svnRepositoryId, int begin, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findBySVNRepositoryId(svnRepositoryId, begin, end, obc);
	}

	public static com.liferay.wol.model.SVNRevision findBySVNRepositoryId_First(
		long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchSVNRevisionException {
		return getPersistence().findBySVNRepositoryId_First(svnRepositoryId, obc);
	}

	public static com.liferay.wol.model.SVNRevision findBySVNRepositoryId_Last(
		long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchSVNRevisionException {
		return getPersistence().findBySVNRepositoryId_Last(svnRepositoryId, obc);
	}

	public static com.liferay.wol.model.SVNRevision[] findBySVNRepositoryId_PrevAndNext(
		long svnRevisionId, long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchSVNRevisionException {
		return getPersistence()
				   .findBySVNRepositoryId_PrevAndNext(svnRevisionId,
			svnRepositoryId, obc);
	}

	public static java.util.List<com.liferay.wol.model.SVNRevision> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer)
		throws com.liferay.portal.SystemException {
		return getPersistence().findWithDynamicQuery(queryInitializer);
	}

	public static java.util.List<com.liferay.wol.model.SVNRevision> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer,
		int begin, int end) throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findWithDynamicQuery(queryInitializer, begin, end);
	}

	public static java.util.List<com.liferay.wol.model.SVNRevision> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.wol.model.SVNRevision> findAll(
		int begin, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(begin, end);
	}

	public static java.util.List<com.liferay.wol.model.SVNRevision> findAll(
		int begin, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(begin, end, obc);
	}

	public static void removeBySVNUserId(java.lang.String svnUserId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeBySVNUserId(svnUserId);
	}

	public static void removeBySVNRepositoryId(long svnRepositoryId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeBySVNRepositoryId(svnRepositoryId);
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

	public static int countAll() throws com.liferay.portal.SystemException {
		return getPersistence().countAll();
	}

	public static SVNRevisionPersistence getPersistence() {
		return _getUtil()._persistence;
	}

	public void setPersistence(SVNRevisionPersistence persistence) {
		_persistence = persistence;
	}

	private static SVNRevisionUtil _getUtil() {
		if (_util == null) {
			_util = (SVNRevisionUtil)com.liferay.portlet.service.BeanLocatorUtil.locate(_UTIL);
		}

		return _util;
	}

	private static final String _UTIL = SVNRevisionUtil.class.getName();
	private static SVNRevisionUtil _util;
	private SVNRevisionPersistence _persistence;
}