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

package com.liferay.wol.service;

/**
 * <a href="SVNRepositoryLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class SVNRepositoryLocalServiceUtil {
	public static com.liferay.wol.model.SVNRepository addSVNRepository(
		com.liferay.wol.model.SVNRepository svnRepository)
		throws com.liferay.portal.SystemException {
		SVNRepositoryLocalService svnRepositoryLocalService = SVNRepositoryLocalServiceFactory.getService();

		return svnRepositoryLocalService.addSVNRepository(svnRepository);
	}

	public static void deleteSVNRepository(long svnRepositoryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		SVNRepositoryLocalService svnRepositoryLocalService = SVNRepositoryLocalServiceFactory.getService();

		svnRepositoryLocalService.deleteSVNRepository(svnRepositoryId);
	}

	public static void deleteSVNRepository(
		com.liferay.wol.model.SVNRepository svnRepository)
		throws com.liferay.portal.SystemException {
		SVNRepositoryLocalService svnRepositoryLocalService = SVNRepositoryLocalServiceFactory.getService();

		svnRepositoryLocalService.deleteSVNRepository(svnRepository);
	}

	public static java.util.List<com.liferay.wol.model.SVNRepository> dynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer)
		throws com.liferay.portal.SystemException {
		SVNRepositoryLocalService svnRepositoryLocalService = SVNRepositoryLocalServiceFactory.getService();

		return svnRepositoryLocalService.dynamicQuery(queryInitializer);
	}

	public static java.util.List<com.liferay.wol.model.SVNRepository> dynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer,
		int start, int end) throws com.liferay.portal.SystemException {
		SVNRepositoryLocalService svnRepositoryLocalService = SVNRepositoryLocalServiceFactory.getService();

		return svnRepositoryLocalService.dynamicQuery(queryInitializer, start,
			end);
	}

	public static com.liferay.wol.model.SVNRepository getSVNRepository(
		long svnRepositoryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		SVNRepositoryLocalService svnRepositoryLocalService = SVNRepositoryLocalServiceFactory.getService();

		return svnRepositoryLocalService.getSVNRepository(svnRepositoryId);
	}

	public static com.liferay.wol.model.SVNRepository updateSVNRepository(
		com.liferay.wol.model.SVNRepository svnRepository)
		throws com.liferay.portal.SystemException {
		SVNRepositoryLocalService svnRepositoryLocalService = SVNRepositoryLocalServiceFactory.getService();

		return svnRepositoryLocalService.updateSVNRepository(svnRepository);
	}

	public static com.liferay.wol.model.SVNRepository getSVNRepository(
		java.lang.String url)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		SVNRepositoryLocalService svnRepositoryLocalService = SVNRepositoryLocalServiceFactory.getService();

		return svnRepositoryLocalService.getSVNRepository(url);
	}

	public static void updateSVNRepository(java.lang.String url)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		SVNRepositoryLocalService svnRepositoryLocalService = SVNRepositoryLocalServiceFactory.getService();

		svnRepositoryLocalService.updateSVNRepository(url);
	}
}