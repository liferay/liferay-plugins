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

package com.liferay.sd.service.persistence;

/**
 * <a href="SVNRepositoryUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class SVNRepositoryUtil {
	public static com.liferay.sd.model.SVNRepository create(
		long svnRepositoryId) {
		return getPersistence().create(svnRepositoryId);
	}

	public static com.liferay.sd.model.SVNRepository remove(
		long svnRepositoryId)
		throws com.liferay.portal.SystemException,
			com.liferay.sd.NoSuchSVNRepositoryException {
		return getPersistence().remove(svnRepositoryId);
	}

	public static com.liferay.sd.model.SVNRepository remove(
		com.liferay.sd.model.SVNRepository svnRepository)
		throws com.liferay.portal.SystemException {
		return getPersistence().remove(svnRepository);
	}

	public static com.liferay.sd.model.SVNRepository update(
		com.liferay.sd.model.SVNRepository svnRepository)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(svnRepository);
	}

	public static com.liferay.sd.model.SVNRepository update(
		com.liferay.sd.model.SVNRepository svnRepository, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(svnRepository, merge);
	}

	public static com.liferay.sd.model.SVNRepository updateImpl(
		com.liferay.sd.model.SVNRepository svnRepository, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(svnRepository, merge);
	}

	public static com.liferay.sd.model.SVNRepository findByPrimaryKey(
		long svnRepositoryId)
		throws com.liferay.portal.SystemException,
			com.liferay.sd.NoSuchSVNRepositoryException {
		return getPersistence().findByPrimaryKey(svnRepositoryId);
	}

	public static com.liferay.sd.model.SVNRepository fetchByPrimaryKey(
		long svnRepositoryId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(svnRepositoryId);
	}

	public static com.liferay.sd.model.SVNRepository findByUrl(
		java.lang.String url)
		throws com.liferay.portal.SystemException,
			com.liferay.sd.NoSuchSVNRepositoryException {
		return getPersistence().findByUrl(url);
	}

	public static com.liferay.sd.model.SVNRepository fetchByUrl(
		java.lang.String url) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByUrl(url);
	}

	public static java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	public static java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	public static java.util.List<com.liferay.sd.model.SVNRepository> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.sd.model.SVNRepository> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.sd.model.SVNRepository> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end, obc);
	}

	public static void removeByUrl(java.lang.String url)
		throws com.liferay.portal.SystemException,
			com.liferay.sd.NoSuchSVNRepositoryException {
		getPersistence().removeByUrl(url);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countByUrl(java.lang.String url)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByUrl(url);
	}

	public static int countAll() throws com.liferay.portal.SystemException {
		return getPersistence().countAll();
	}

	public static SVNRepositoryPersistence getPersistence() {
		return _persistence;
	}

	public void setPersistence(SVNRepositoryPersistence persistence) {
		_persistence = persistence;
	}

	private static SVNRepositoryPersistence _persistence;
}