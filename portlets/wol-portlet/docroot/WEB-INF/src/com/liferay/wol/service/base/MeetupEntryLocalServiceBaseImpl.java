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

import com.liferay.wol.model.MeetupEntry;
import com.liferay.wol.service.JIRAActionLocalService;
import com.liferay.wol.service.JIRAActionLocalServiceFactory;
import com.liferay.wol.service.JIRAChangeGroupLocalService;
import com.liferay.wol.service.JIRAChangeGroupLocalServiceFactory;
import com.liferay.wol.service.JIRAChangeItemLocalService;
import com.liferay.wol.service.JIRAChangeItemLocalServiceFactory;
import com.liferay.wol.service.JIRAIssueLocalService;
import com.liferay.wol.service.JIRAIssueLocalServiceFactory;
import com.liferay.wol.service.MeetupEntryLocalService;
import com.liferay.wol.service.MeetupRegistrationLocalService;
import com.liferay.wol.service.MeetupRegistrationLocalServiceFactory;
import com.liferay.wol.service.SVNRepositoryLocalService;
import com.liferay.wol.service.SVNRepositoryLocalServiceFactory;
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
import com.liferay.wol.service.persistence.MeetupEntryPersistence;
import com.liferay.wol.service.persistence.MeetupEntryUtil;
import com.liferay.wol.service.persistence.MeetupRegistrationPersistence;
import com.liferay.wol.service.persistence.MeetupRegistrationUtil;
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
 * <a href="MeetupEntryLocalServiceBaseImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public abstract class MeetupEntryLocalServiceBaseImpl
	implements MeetupEntryLocalService, InitializingBean {
	public MeetupEntry addMeetupEntry(MeetupEntry meetupEntry)
		throws SystemException {
		meetupEntry.setNew(true);

		return meetupEntryPersistence.update(meetupEntry, false);
	}

	public void deleteMeetupEntry(long meetupEntryId)
		throws PortalException, SystemException {
		meetupEntryPersistence.remove(meetupEntryId);
	}

	public void deleteMeetupEntry(MeetupEntry meetupEntry)
		throws SystemException {
		meetupEntryPersistence.remove(meetupEntry);
	}

	public List<MeetupEntry> dynamicQuery(
		DynamicQueryInitializer queryInitializer) throws SystemException {
		return meetupEntryPersistence.findWithDynamicQuery(queryInitializer);
	}

	public List<MeetupEntry> dynamicQuery(
		DynamicQueryInitializer queryInitializer, int start, int end)
		throws SystemException {
		return meetupEntryPersistence.findWithDynamicQuery(queryInitializer,
			start, end);
	}

	public MeetupEntry getMeetupEntry(long meetupEntryId)
		throws PortalException, SystemException {
		return meetupEntryPersistence.findByPrimaryKey(meetupEntryId);
	}

	public MeetupEntry updateMeetupEntry(MeetupEntry meetupEntry)
		throws SystemException {
		meetupEntry.setNew(false);

		return meetupEntryPersistence.update(meetupEntry, true);
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

	public MeetupEntryPersistence getMeetupEntryPersistence() {
		return meetupEntryPersistence;
	}

	public void setMeetupEntryPersistence(
		MeetupEntryPersistence meetupEntryPersistence) {
		this.meetupEntryPersistence = meetupEntryPersistence;
	}

	public MeetupRegistrationLocalService getMeetupRegistrationLocalService() {
		return meetupRegistrationLocalService;
	}

	public void setMeetupRegistrationLocalService(
		MeetupRegistrationLocalService meetupRegistrationLocalService) {
		this.meetupRegistrationLocalService = meetupRegistrationLocalService;
	}

	public MeetupRegistrationPersistence getMeetupRegistrationPersistence() {
		return meetupRegistrationPersistence;
	}

	public void setMeetupRegistrationPersistence(
		MeetupRegistrationPersistence meetupRegistrationPersistence) {
		this.meetupRegistrationPersistence = meetupRegistrationPersistence;
	}

	public SVNRepositoryLocalService getSVNRepositoryLocalService() {
		return svnRepositoryLocalService;
	}

	public void setSVNRepositoryLocalService(
		SVNRepositoryLocalService svnRepositoryLocalService) {
		this.svnRepositoryLocalService = svnRepositoryLocalService;
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

		if (meetupEntryPersistence == null) {
			meetupEntryPersistence = MeetupEntryUtil.getPersistence();
		}

		if (meetupRegistrationLocalService == null) {
			meetupRegistrationLocalService = MeetupRegistrationLocalServiceFactory.getImpl();
		}

		if (meetupRegistrationPersistence == null) {
			meetupRegistrationPersistence = MeetupRegistrationUtil.getPersistence();
		}

		if (svnRepositoryLocalService == null) {
			svnRepositoryLocalService = SVNRepositoryLocalServiceFactory.getImpl();
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
	protected MeetupEntryPersistence meetupEntryPersistence;
	protected MeetupRegistrationLocalService meetupRegistrationLocalService;
	protected MeetupRegistrationPersistence meetupRegistrationPersistence;
	protected SVNRepositoryLocalService svnRepositoryLocalService;
	protected SVNRepositoryPersistence svnRepositoryPersistence;
	protected SVNRevisionLocalService svnRevisionLocalService;
	protected SVNRevisionPersistence svnRevisionPersistence;
	protected WallEntryLocalService wallEntryLocalService;
	protected WallEntryPersistence wallEntryPersistence;
	protected WallEntryFinder wallEntryFinder;
}