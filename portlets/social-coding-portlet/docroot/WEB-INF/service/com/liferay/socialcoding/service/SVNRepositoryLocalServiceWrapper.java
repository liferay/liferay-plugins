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

package com.liferay.socialcoding.service;

/**
 * <a href="SVNRepositoryLocalServiceWrapper.java.html"><b><i>View Source</i>
 * </b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class SVNRepositoryLocalServiceWrapper
	implements SVNRepositoryLocalService {
	public SVNRepositoryLocalServiceWrapper(
		SVNRepositoryLocalService svnRepositoryLocalService) {
		_svnRepositoryLocalService = svnRepositoryLocalService;
	}

	public com.liferay.socialcoding.model.SVNRepository addSVNRepository(
		com.liferay.socialcoding.model.SVNRepository svnRepository)
		throws com.liferay.portal.SystemException {
		return _svnRepositoryLocalService.addSVNRepository(svnRepository);
	}

	public com.liferay.socialcoding.model.SVNRepository createSVNRepository(
		long svnRepositoryId) {
		return _svnRepositoryLocalService.createSVNRepository(svnRepositoryId);
	}

	public void deleteSVNRepository(long svnRepositoryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_svnRepositoryLocalService.deleteSVNRepository(svnRepositoryId);
	}

	public void deleteSVNRepository(
		com.liferay.socialcoding.model.SVNRepository svnRepository)
		throws com.liferay.portal.SystemException {
		_svnRepositoryLocalService.deleteSVNRepository(svnRepository);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return _svnRepositoryLocalService.dynamicQuery(dynamicQuery);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return _svnRepositoryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	public com.liferay.socialcoding.model.SVNRepository getSVNRepository(
		long svnRepositoryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _svnRepositoryLocalService.getSVNRepository(svnRepositoryId);
	}

	public java.util.List<com.liferay.socialcoding.model.SVNRepository> getSVNRepositories(
		int start, int end) throws com.liferay.portal.SystemException {
		return _svnRepositoryLocalService.getSVNRepositories(start, end);
	}

	public int getSVNRepositoriesCount()
		throws com.liferay.portal.SystemException {
		return _svnRepositoryLocalService.getSVNRepositoriesCount();
	}

	public com.liferay.socialcoding.model.SVNRepository updateSVNRepository(
		com.liferay.socialcoding.model.SVNRepository svnRepository)
		throws com.liferay.portal.SystemException {
		return _svnRepositoryLocalService.updateSVNRepository(svnRepository);
	}

	public com.liferay.socialcoding.model.SVNRepository updateSVNRepository(
		com.liferay.socialcoding.model.SVNRepository svnRepository,
		boolean merge) throws com.liferay.portal.SystemException {
		return _svnRepositoryLocalService.updateSVNRepository(svnRepository,
			merge);
	}

	public com.liferay.socialcoding.model.SVNRepository getSVNRepository(
		java.lang.String url)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _svnRepositoryLocalService.getSVNRepository(url);
	}

	public void updateSVNRepository(java.lang.String url)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_svnRepositoryLocalService.updateSVNRepository(url);
	}

	private SVNRepositoryLocalService _svnRepositoryLocalService;
}