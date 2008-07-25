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
import com.liferay.portal.kernel.bean.InitializingBean;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;

import com.liferay.wol.model.SVNRepository;
import com.liferay.wol.service.JIRAActionLocalService;
import com.liferay.wol.service.JIRAActionLocalServiceFactory;
import com.liferay.wol.service.JIRAChangeGroupLocalService;
import com.liferay.wol.service.JIRAChangeGroupLocalServiceFactory;
import com.liferay.wol.service.JIRAChangeItemLocalService;
import com.liferay.wol.service.JIRAChangeItemLocalServiceFactory;
import com.liferay.wol.service.JIRAIssueLocalService;
import com.liferay.wol.service.JIRAIssueLocalServiceFactory;
import com.liferay.wol.service.MeetupsEntryLocalService;
import com.liferay.wol.service.MeetupsEntryLocalServiceFactory;
import com.liferay.wol.service.MeetupsRegistrationLocalService;
import com.liferay.wol.service.MeetupsRegistrationLocalServiceFactory;
import com.liferay.wol.service.SVNRepositoryLocalService;
import com.liferay.wol.service.SVNRevisionLocalService;
import com.liferay.wol.service.SVNRevisionLocalServiceFactory;
import com.liferay.wol.service.WallEntryLocalService;
import com.liferay.wol.service.WallEntryLocalServiceFactory;
import com.liferay.wol.service.persistence.JIRAActionFinder;
import com.liferay.wol.service.persistence.JIRAActionFinderUtil;
import com.liferay.wol.service.persistence.JIRAActionPersistence;
import com.liferay.wol.service.persistence.JIRAActionUtil;
import com.liferay.wol.service.persistence.JIRAChangeGroupFinder;
import com.liferay.wol.service.persistence.JIRAChangeGroupFinderUtil;
import com.liferay.wol.service.persistence.JIRAChangeGroupPersistence;
import com.liferay.wol.service.persistence.JIRAChangeGroupUtil;
import com.liferay.wol.service.persistence.JIRAChangeItemPersistence;
import com.liferay.wol.service.persistence.JIRAChangeItemUtil;
import com.liferay.wol.service.persistence.JIRAIssueFinder;
import com.liferay.wol.service.persistence.JIRAIssueFinderUtil;
import com.liferay.wol.service.persistence.JIRAIssuePersistence;
import com.liferay.wol.service.persistence.JIRAIssueUtil;
import com.liferay.wol.service.persistence.MeetupsEntryPersistence;
import com.liferay.wol.service.persistence.MeetupsEntryUtil;
import com.liferay.wol.service.persistence.MeetupsRegistrationPersistence;
import com.liferay.wol.service.persistence.MeetupsRegistrationUtil;
import com.liferay.wol.service.persistence.SVNRepositoryPersistence;
import com.liferay.wol.service.persistence.SVNRepositoryUtil;
import com.liferay.wol.service.persistence.SVNRevisionPersistence;
import com.liferay.wol.service.persistence.SVNRevisionUtil;
import com.liferay.wol.service.persistence.WallEntryFinder;
import com.liferay.wol.service.persistence.WallEntryFinderUtil;
import com.liferay.wol.service.persistence.WallEntryPersistence;
import com.liferay.wol.service.persistence.WallEntryUtil;

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
		throws SystemException {
		svnRepositoryPersistence.remove(svnRepository);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return svnRepositoryPersistence.findWithDynamicQuery(dynamicQuery);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) throws SystemException {
		return svnRepositoryPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	public SVNRepository getSVNRepository(long svnRepositoryId)
		throws PortalException, SystemException {
		return svnRepositoryPersistence.findByPrimaryKey(svnRepositoryId);
	}

	public List<SVNRepository> getSVNRepositories(int start, int end)
		throws SystemException {
		return svnRepositoryPersistence.findAll(start, end);
	}

	public int getSVNRepositoriesCount() throws SystemException {
		return svnRepositoryPersistence.countAll();
	}

	public SVNRepository updateSVNRepository(SVNRepository svnRepository)
		throws SystemException {
		svnRepository.setNew(false);

		return svnRepositoryPersistence.update(svnRepository, true);
	}

	public JIRAActionLocalService getJIRAActionLocalService() {
		return jiraActionLocalService;
	}

	public void setJIRAActionLocalService(
		JIRAActionLocalService jiraActionLocalService) {
		this.jiraActionLocalService = jiraActionLocalService;
	}

	public JIRAActionPersistence getJIRAActionPersistence() {
		return jiraActionPersistence;
	}

	public void setJIRAActionPersistence(
		JIRAActionPersistence jiraActionPersistence) {
		this.jiraActionPersistence = jiraActionPersistence;
	}

	public JIRAActionFinder getJIRAActionFinder() {
		return jiraActionFinder;
	}

	public void setJIRAActionFinder(JIRAActionFinder jiraActionFinder) {
		this.jiraActionFinder = jiraActionFinder;
	}

	public JIRAChangeGroupLocalService getJIRAChangeGroupLocalService() {
		return jiraChangeGroupLocalService;
	}

	public void setJIRAChangeGroupLocalService(
		JIRAChangeGroupLocalService jiraChangeGroupLocalService) {
		this.jiraChangeGroupLocalService = jiraChangeGroupLocalService;
	}

	public JIRAChangeGroupPersistence getJIRAChangeGroupPersistence() {
		return jiraChangeGroupPersistence;
	}

	public void setJIRAChangeGroupPersistence(
		JIRAChangeGroupPersistence jiraChangeGroupPersistence) {
		this.jiraChangeGroupPersistence = jiraChangeGroupPersistence;
	}

	public JIRAChangeGroupFinder getJIRAChangeGroupFinder() {
		return jiraChangeGroupFinder;
	}

	public void setJIRAChangeGroupFinder(
		JIRAChangeGroupFinder jiraChangeGroupFinder) {
		this.jiraChangeGroupFinder = jiraChangeGroupFinder;
	}

	public JIRAChangeItemLocalService getJIRAChangeItemLocalService() {
		return jiraChangeItemLocalService;
	}

	public void setJIRAChangeItemLocalService(
		JIRAChangeItemLocalService jiraChangeItemLocalService) {
		this.jiraChangeItemLocalService = jiraChangeItemLocalService;
	}

	public JIRAChangeItemPersistence getJIRAChangeItemPersistence() {
		return jiraChangeItemPersistence;
	}

	public void setJIRAChangeItemPersistence(
		JIRAChangeItemPersistence jiraChangeItemPersistence) {
		this.jiraChangeItemPersistence = jiraChangeItemPersistence;
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

	public JIRAIssueFinder getJIRAIssueFinder() {
		return jiraIssueFinder;
	}

	public void setJIRAIssueFinder(JIRAIssueFinder jiraIssueFinder) {
		this.jiraIssueFinder = jiraIssueFinder;
	}

	public MeetupsEntryLocalService getMeetupsEntryLocalService() {
		return meetupsEntryLocalService;
	}

	public void setMeetupsEntryLocalService(
		MeetupsEntryLocalService meetupsEntryLocalService) {
		this.meetupsEntryLocalService = meetupsEntryLocalService;
	}

	public MeetupsEntryPersistence getMeetupsEntryPersistence() {
		return meetupsEntryPersistence;
	}

	public void setMeetupsEntryPersistence(
		MeetupsEntryPersistence meetupsEntryPersistence) {
		this.meetupsEntryPersistence = meetupsEntryPersistence;
	}

	public MeetupsRegistrationLocalService getMeetupsRegistrationLocalService() {
		return meetupsRegistrationLocalService;
	}

	public void setMeetupsRegistrationLocalService(
		MeetupsRegistrationLocalService meetupsRegistrationLocalService) {
		this.meetupsRegistrationLocalService = meetupsRegistrationLocalService;
	}

	public MeetupsRegistrationPersistence getMeetupsRegistrationPersistence() {
		return meetupsRegistrationPersistence;
	}

	public void setMeetupsRegistrationPersistence(
		MeetupsRegistrationPersistence meetupsRegistrationPersistence) {
		this.meetupsRegistrationPersistence = meetupsRegistrationPersistence;
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
		if (jiraActionLocalService == null) {
			jiraActionLocalService = JIRAActionLocalServiceFactory.getImpl();
		}

		if (jiraActionPersistence == null) {
			jiraActionPersistence = JIRAActionUtil.getPersistence();
		}

		if (jiraActionFinder == null) {
			jiraActionFinder = JIRAActionFinderUtil.getFinder();
		}

		if (jiraChangeGroupLocalService == null) {
			jiraChangeGroupLocalService = JIRAChangeGroupLocalServiceFactory.getImpl();
		}

		if (jiraChangeGroupPersistence == null) {
			jiraChangeGroupPersistence = JIRAChangeGroupUtil.getPersistence();
		}

		if (jiraChangeGroupFinder == null) {
			jiraChangeGroupFinder = JIRAChangeGroupFinderUtil.getFinder();
		}

		if (jiraChangeItemLocalService == null) {
			jiraChangeItemLocalService = JIRAChangeItemLocalServiceFactory.getImpl();
		}

		if (jiraChangeItemPersistence == null) {
			jiraChangeItemPersistence = JIRAChangeItemUtil.getPersistence();
		}

		if (jiraIssueLocalService == null) {
			jiraIssueLocalService = JIRAIssueLocalServiceFactory.getImpl();
		}

		if (jiraIssuePersistence == null) {
			jiraIssuePersistence = JIRAIssueUtil.getPersistence();
		}

		if (jiraIssueFinder == null) {
			jiraIssueFinder = JIRAIssueFinderUtil.getFinder();
		}

		if (meetupsEntryLocalService == null) {
			meetupsEntryLocalService = MeetupsEntryLocalServiceFactory.getImpl();
		}

		if (meetupsEntryPersistence == null) {
			meetupsEntryPersistence = MeetupsEntryUtil.getPersistence();
		}

		if (meetupsRegistrationLocalService == null) {
			meetupsRegistrationLocalService = MeetupsRegistrationLocalServiceFactory.getImpl();
		}

		if (meetupsRegistrationPersistence == null) {
			meetupsRegistrationPersistence = MeetupsRegistrationUtil.getPersistence();
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

	protected JIRAActionLocalService jiraActionLocalService;
	protected JIRAActionPersistence jiraActionPersistence;
	protected JIRAActionFinder jiraActionFinder;
	protected JIRAChangeGroupLocalService jiraChangeGroupLocalService;
	protected JIRAChangeGroupPersistence jiraChangeGroupPersistence;
	protected JIRAChangeGroupFinder jiraChangeGroupFinder;
	protected JIRAChangeItemLocalService jiraChangeItemLocalService;
	protected JIRAChangeItemPersistence jiraChangeItemPersistence;
	protected JIRAIssueLocalService jiraIssueLocalService;
	protected JIRAIssuePersistence jiraIssuePersistence;
	protected JIRAIssueFinder jiraIssueFinder;
	protected MeetupsEntryLocalService meetupsEntryLocalService;
	protected MeetupsEntryPersistence meetupsEntryPersistence;
	protected MeetupsRegistrationLocalService meetupsRegistrationLocalService;
	protected MeetupsRegistrationPersistence meetupsRegistrationPersistence;
	protected SVNRepositoryPersistence svnRepositoryPersistence;
	protected SVNRevisionLocalService svnRevisionLocalService;
	protected SVNRevisionPersistence svnRevisionPersistence;
	protected WallEntryLocalService wallEntryLocalService;
	protected WallEntryPersistence wallEntryPersistence;
	protected WallEntryFinder wallEntryFinder;
}