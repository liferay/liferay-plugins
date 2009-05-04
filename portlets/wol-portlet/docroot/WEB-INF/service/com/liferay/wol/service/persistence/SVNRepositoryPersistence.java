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

package com.liferay.wol.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * <a href="SVNRepositoryPersistence.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public interface SVNRepositoryPersistence extends BasePersistence {
	public void cacheResult(com.liferay.wol.model.SVNRepository svnRepository);

	public void cacheResult(
		java.util.List<com.liferay.wol.model.SVNRepository> svnRepositories);

	public void clearCache();

	public com.liferay.wol.model.SVNRepository create(long svnRepositoryId);

	public com.liferay.wol.model.SVNRepository remove(long svnRepositoryId)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchSVNRepositoryException;

	public com.liferay.wol.model.SVNRepository remove(
		com.liferay.wol.model.SVNRepository svnRepository)
		throws com.liferay.portal.SystemException;

	public com.liferay.wol.model.SVNRepository update(
		com.liferay.wol.model.SVNRepository svnRepository)
		throws com.liferay.portal.SystemException;

	public com.liferay.wol.model.SVNRepository update(
		com.liferay.wol.model.SVNRepository svnRepository, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.wol.model.SVNRepository updateImpl(
		com.liferay.wol.model.SVNRepository svnRepository, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.wol.model.SVNRepository findByPrimaryKey(
		long svnRepositoryId)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchSVNRepositoryException;

	public com.liferay.wol.model.SVNRepository fetchByPrimaryKey(
		long svnRepositoryId) throws com.liferay.portal.SystemException;

	public com.liferay.wol.model.SVNRepository findByUrl(java.lang.String url)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchSVNRepositoryException;

	public com.liferay.wol.model.SVNRepository fetchByUrl(java.lang.String url)
		throws com.liferay.portal.SystemException;

	public com.liferay.wol.model.SVNRepository fetchByUrl(
		java.lang.String url, boolean retrieveFromCache)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wol.model.SVNRepository> findAll()
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wol.model.SVNRepository> findAll(
		int start, int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wol.model.SVNRepository> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public void removeByUrl(java.lang.String url)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchSVNRepositoryException;

	public void removeAll() throws com.liferay.portal.SystemException;

	public int countByUrl(java.lang.String url)
		throws com.liferay.portal.SystemException;

	public int countAll() throws com.liferay.portal.SystemException;
}