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
import com.liferay.portal.kernel.dao.orm.DynamicQuery;

import com.liferay.wol.model.MeetupsRegistration;
import com.liferay.wol.service.JIRAActionLocalService;
import com.liferay.wol.service.JIRAChangeGroupLocalService;
import com.liferay.wol.service.JIRAChangeItemLocalService;
import com.liferay.wol.service.JIRAIssueLocalService;
import com.liferay.wol.service.MeetupsEntryLocalService;
import com.liferay.wol.service.MeetupsRegistrationLocalService;
import com.liferay.wol.service.SVNRepositoryLocalService;
import com.liferay.wol.service.SVNRevisionLocalService;
import com.liferay.wol.service.WallEntryLocalService;
import com.liferay.wol.service.persistence.JIRAActionFinder;
import com.liferay.wol.service.persistence.JIRAActionPersistence;
import com.liferay.wol.service.persistence.JIRAChangeGroupFinder;
import com.liferay.wol.service.persistence.JIRAChangeGroupPersistence;
import com.liferay.wol.service.persistence.JIRAChangeItemPersistence;
import com.liferay.wol.service.persistence.JIRAIssueFinder;
import com.liferay.wol.service.persistence.JIRAIssuePersistence;
import com.liferay.wol.service.persistence.MeetupsEntryPersistence;
import com.liferay.wol.service.persistence.MeetupsRegistrationPersistence;
import com.liferay.wol.service.persistence.SVNRepositoryPersistence;
import com.liferay.wol.service.persistence.SVNRevisionPersistence;
import com.liferay.wol.service.persistence.WallEntryFinder;
import com.liferay.wol.service.persistence.WallEntryPersistence;

import java.util.List;

/**
 * <a href="MeetupsRegistrationLocalServiceBaseImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public abstract class MeetupsRegistrationLocalServiceBaseImpl
	implements MeetupsRegistrationLocalService {
	public MeetupsRegistration addMeetupsRegistration(
		MeetupsRegistration meetupsRegistration) throws SystemException {
		meetupsRegistration.setNew(true);

		return meetupsRegistrationPersistence.update(meetupsRegistration, false);
	}

	public MeetupsRegistration createMeetupsRegistration(
		long meetupsRegistrationId) {
		return meetupsRegistrationPersistence.create(meetupsRegistrationId);
	}

	public void deleteMeetupsRegistration(long meetupsRegistrationId)
		throws PortalException, SystemException {
		meetupsRegistrationPersistence.remove(meetupsRegistrationId);
	}

	public void deleteMeetupsRegistration(
		MeetupsRegistration meetupsRegistration) throws SystemException {
		meetupsRegistrationPersistence.remove(meetupsRegistration);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return meetupsRegistrationPersistence.findWithDynamicQuery(dynamicQuery);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) throws SystemException {
		return meetupsRegistrationPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	public MeetupsRegistration getMeetupsRegistration(
		long meetupsRegistrationId) throws PortalException, SystemException {
		return meetupsRegistrationPersistence.findByPrimaryKey(meetupsRegistrationId);
	}

	public List<MeetupsRegistration> getMeetupsRegistrations(int start, int end)
		throws SystemException {
		return meetupsRegistrationPersistence.findAll(start, end);
	}

	public int getMeetupsRegistrationsCount() throws SystemException {
		return meetupsRegistrationPersistence.countAll();
	}

	public MeetupsRegistration updateMeetupsRegistration(
		MeetupsRegistration meetupsRegistration) throws SystemException {
		meetupsRegistration.setNew(false);

		return meetupsRegistrationPersistence.update(meetupsRegistration, true);
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

	@javax.annotation.Resource(name = "com.liferay.wol.service.JIRAActionLocalService.impl")
	protected JIRAActionLocalService jiraActionLocalService;
	@javax.annotation.Resource(name = "com.liferay.wol.service.persistence.JIRAActionPersistence.impl")
	protected JIRAActionPersistence jiraActionPersistence;
	@javax.annotation.Resource(name = "com.liferay.wol.service.persistence.JIRAActionFinder.impl")
	protected JIRAActionFinder jiraActionFinder;
	@javax.annotation.Resource(name = "com.liferay.wol.service.JIRAChangeGroupLocalService.impl")
	protected JIRAChangeGroupLocalService jiraChangeGroupLocalService;
	@javax.annotation.Resource(name = "com.liferay.wol.service.persistence.JIRAChangeGroupPersistence.impl")
	protected JIRAChangeGroupPersistence jiraChangeGroupPersistence;
	@javax.annotation.Resource(name = "com.liferay.wol.service.persistence.JIRAChangeGroupFinder.impl")
	protected JIRAChangeGroupFinder jiraChangeGroupFinder;
	@javax.annotation.Resource(name = "com.liferay.wol.service.JIRAChangeItemLocalService.impl")
	protected JIRAChangeItemLocalService jiraChangeItemLocalService;
	@javax.annotation.Resource(name = "com.liferay.wol.service.persistence.JIRAChangeItemPersistence.impl")
	protected JIRAChangeItemPersistence jiraChangeItemPersistence;
	@javax.annotation.Resource(name = "com.liferay.wol.service.JIRAIssueLocalService.impl")
	protected JIRAIssueLocalService jiraIssueLocalService;
	@javax.annotation.Resource(name = "com.liferay.wol.service.persistence.JIRAIssuePersistence.impl")
	protected JIRAIssuePersistence jiraIssuePersistence;
	@javax.annotation.Resource(name = "com.liferay.wol.service.persistence.JIRAIssueFinder.impl")
	protected JIRAIssueFinder jiraIssueFinder;
	@javax.annotation.Resource(name = "com.liferay.wol.service.MeetupsEntryLocalService.impl")
	protected MeetupsEntryLocalService meetupsEntryLocalService;
	@javax.annotation.Resource(name = "com.liferay.wol.service.persistence.MeetupsEntryPersistence.impl")
	protected MeetupsEntryPersistence meetupsEntryPersistence;
	@javax.annotation.Resource(name = "com.liferay.wol.service.MeetupsRegistrationLocalService.impl")
	protected MeetupsRegistrationLocalService meetupsRegistrationLocalService;
	@javax.annotation.Resource(name = "com.liferay.wol.service.persistence.MeetupsRegistrationPersistence.impl")
	protected MeetupsRegistrationPersistence meetupsRegistrationPersistence;
	@javax.annotation.Resource(name = "com.liferay.wol.service.SVNRepositoryLocalService.impl")
	protected SVNRepositoryLocalService svnRepositoryLocalService;
	@javax.annotation.Resource(name = "com.liferay.wol.service.persistence.SVNRepositoryPersistence.impl")
	protected SVNRepositoryPersistence svnRepositoryPersistence;
	@javax.annotation.Resource(name = "com.liferay.wol.service.SVNRevisionLocalService.impl")
	protected SVNRevisionLocalService svnRevisionLocalService;
	@javax.annotation.Resource(name = "com.liferay.wol.service.persistence.SVNRevisionPersistence.impl")
	protected SVNRevisionPersistence svnRevisionPersistence;
	@javax.annotation.Resource(name = "com.liferay.wol.service.WallEntryLocalService.impl")
	protected WallEntryLocalService wallEntryLocalService;
	@javax.annotation.Resource(name = "com.liferay.wol.service.persistence.WallEntryPersistence.impl")
	protected WallEntryPersistence wallEntryPersistence;
	@javax.annotation.Resource(name = "com.liferay.wol.service.persistence.WallEntryFinder.impl")
	protected WallEntryFinder wallEntryFinder;
}