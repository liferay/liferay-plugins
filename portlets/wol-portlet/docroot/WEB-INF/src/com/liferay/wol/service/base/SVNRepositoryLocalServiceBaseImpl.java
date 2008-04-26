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

package com.liferay.wol.service.base;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.DynamicQueryInitializer;

import com.liferay.wol.model.SVNRepository;
import com.liferay.wol.service.JIRAIssueLocalService;
import com.liferay.wol.service.JIRAIssueLocalServiceFactory;
import com.liferay.wol.service.SVNRepositoryLocalService;
import com.liferay.wol.service.SVNRevisionLocalService;
import com.liferay.wol.service.SVNRevisionLocalServiceFactory;
import com.liferay.wol.service.WallEntryLocalService;
import com.liferay.wol.service.WallEntryLocalServiceFactory;
import com.liferay.wol.service.persistence.JIRAIssuePersistence;
import com.liferay.wol.service.persistence.JIRAIssueUtil;
import com.liferay.wol.service.persistence.SVNRepositoryPersistence;
import com.liferay.wol.service.persistence.SVNRepositoryUtil;
import com.liferay.wol.service.persistence.SVNRevisionPersistence;
import com.liferay.wol.service.persistence.SVNRevisionUtil;
import com.liferay.wol.service.persistence.WallEntryFinder;
import com.liferay.wol.service.persistence.WallEntryFinderUtil;
import com.liferay.wol.service.persistence.WallEntryPersistence;
import com.liferay.wol.service.persistence.WallEntryUtil;

import org.springframework.beans.factory.InitializingBean;

import java.util.List;

/**
 * <a href="SVNRepositoryLocalServiceBaseImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public abstract class SVNRepositoryLocalServiceBaseImpl
	implements SVNRepositoryLocalService, InitializingBean {
	public SVNRepository addSVNRepository(SVNRepository svnRepository)
		throws SystemException {
		svnRepository.setNew(true);

		return svnRepositoryPersistence.update(svnRepository, false);
	}

	public void deleteSVNRepository(long svnRepositoryId)
		throws PortalException, SystemException {
		svnRepositoryPersistence.remove(svnRepositoryId);
	}

	public void deleteSVNRepository(SVNRepository svnRepository)
		throws PortalException, SystemException {
		svnRepositoryPersistence.remove(svnRepository);
	}

	public List<SVNRepository> dynamicQuery(
		DynamicQueryInitializer queryInitializer) throws SystemException {
		return svnRepositoryPersistence.findWithDynamicQuery(queryInitializer);
	}

	public List<SVNRepository> dynamicQuery(
		DynamicQueryInitializer queryInitializer, int begin, int end)
		throws SystemException {
		return svnRepositoryPersistence.findWithDynamicQuery(queryInitializer,
			begin, end);
	}

	public SVNRepository updateSVNRepository(SVNRepository svnRepository)
		throws SystemException {
		svnRepository.setNew(false);

		return svnRepositoryPersistence.update(svnRepository, true);
	}

	public JIRAIssueLocalService getJIRAIssueLocalService() {
		return jiraIssueLocalService;
	}

	public void setJIRAIssueLocalService(
		JIRAIssueLocalService jiraIssueLocalService) {
		this.jiraIssueLocalService = jiraIssueLocalService;
	}

	public JIRAIssuePersistence getJIRAIssuePersistence() {
		return jiraIssuePersistence;
	}

	public void setJIRAIssuePersistence(
		JIRAIssuePersistence jiraIssuePersistence) {
		this.jiraIssuePersistence = jiraIssuePersistence;
	}

	public SVNRepositoryPersistence getSVNRepositoryPersistence() {
		return svnRepositoryPersistence;
	}

	public void setSVNRepositoryPersistence(
		SVNRepositoryPersistence svnRepositoryPersistence) {
		this.svnRepositoryPersistence = svnRepositoryPersistence;
	}

	public SVNRevisionLocalService getSVNRevisionLocalService() {
		return svnRevisionLocalService;
	}

	public void setSVNRevisionLocalService(
		SVNRevisionLocalService svnRevisionLocalService) {
		this.svnRevisionLocalService = svnRevisionLocalService;
	}

	public SVNRevisionPersistence getSVNRevisionPersistence() {
		return svnRevisionPersistence;
	}

	public void setSVNRevisionPersistence(
		SVNRevisionPersistence svnRevisionPersistence) {
		this.svnRevisionPersistence = svnRevisionPersistence;
	}

	public WallEntryLocalService getWallEntryLocalService() {
		return wallEntryLocalService;
	}

	public void setWallEntryLocalService(
		WallEntryLocalService wallEntryLocalService) {
		this.wallEntryLocalService = wallEntryLocalService;
	}

	public WallEntryPersistence getWallEntryPersistence() {
		return wallEntryPersistence;
	}

	public void setWallEntryPersistence(
		WallEntryPersistence wallEntryPersistence) {
		this.wallEntryPersistence = wallEntryPersistence;
	}

	public WallEntryFinder getWallEntryFinder() {
		return wallEntryFinder;
	}

	public void setWallEntryFinder(WallEntryFinder wallEntryFinder) {
		this.wallEntryFinder = wallEntryFinder;
	}

	public void afterPropertiesSet() {
		if (jiraIssueLocalService == null) {
			jiraIssueLocalService = JIRAIssueLocalServiceFactory.getImpl();
		}

		if (jiraIssuePersistence == null) {
			jiraIssuePersistence = JIRAIssueUtil.getPersistence();
		}

		if (svnRepositoryPersistence == null) {
			svnRepositoryPersistence = SVNRepositoryUtil.getPersistence();
		}

		if (svnRevisionLocalService == null) {
			svnRevisionLocalService = SVNRevisionLocalServiceFactory.getImpl();
		}

		if (svnRevisionPersistence == null) {
			svnRevisionPersistence = SVNRevisionUtil.getPersistence();
		}

		if (wallEntryLocalService == null) {
			wallEntryLocalService = WallEntryLocalServiceFactory.getImpl();
		}

		if (wallEntryPersistence == null) {
			wallEntryPersistence = WallEntryUtil.getPersistence();
		}

		if (wallEntryFinder == null) {
			wallEntryFinder = WallEntryFinderUtil.getFinder();
		}
	}

	protected JIRAIssueLocalService jiraIssueLocalService;
	protected JIRAIssuePersistence jiraIssuePersistence;
	protected SVNRepositoryPersistence svnRepositoryPersistence;
	protected SVNRevisionLocalService svnRevisionLocalService;
	protected SVNRevisionPersistence svnRevisionPersistence;
	protected WallEntryLocalService wallEntryLocalService;
	protected WallEntryPersistence wallEntryPersistence;
	protected WallEntryFinder wallEntryFinder;
}