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

import com.liferay.util.bean.PortletBeanLocatorUtil;

import com.liferay.wol.model.JIRAChangeItem;
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
 * <a href="JIRAChangeItemLocalServiceBaseImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public abstract class JIRAChangeItemLocalServiceBaseImpl
	implements JIRAChangeItemLocalService, InitializingBean {
	public JIRAChangeItem addJIRAChangeItem(JIRAChangeItem jiraChangeItem)
		throws SystemException {
		jiraChangeItem.setNew(true);

		return jiraChangeItemPersistence.update(jiraChangeItem, false);
	}

	public void deleteJIRAChangeItem(long jiraChangeItemId)
		throws PortalException, SystemException {
		jiraChangeItemPersistence.remove(jiraChangeItemId);
	}

	public void deleteJIRAChangeItem(JIRAChangeItem jiraChangeItem)
		throws SystemException {
		jiraChangeItemPersistence.remove(jiraChangeItem);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return jiraChangeItemPersistence.findWithDynamicQuery(dynamicQuery);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) throws SystemException {
		return jiraChangeItemPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	public JIRAChangeItem getJIRAChangeItem(long jiraChangeItemId)
		throws PortalException, SystemException {
		return jiraChangeItemPersistence.findByPrimaryKey(jiraChangeItemId);
	}

	public List<JIRAChangeItem> getJIRAChangeItems(int start, int end)
		throws SystemException {
		return jiraChangeItemPersistence.findAll(start, end);
	}

	public int getJIRAChangeItemsCount() throws SystemException {
		return jiraChangeItemPersistence.countAll();
	}

	public JIRAChangeItem updateJIRAChangeItem(JIRAChangeItem jiraChangeItem)
		throws SystemException {
		jiraChangeItem.setNew(false);

		return jiraChangeItemPersistence.update(jiraChangeItem, true);
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

	public void afterPropertiesSet() {
		if (jiraActionLocalService == null) {
			jiraActionLocalService = (JIRAActionLocalService)PortletBeanLocatorUtil.locate(JIRAActionLocalService.class.getName() +
					".impl");
		}

		if (jiraActionPersistence == null) {
			jiraActionPersistence = (JIRAActionPersistence)PortletBeanLocatorUtil.locate(JIRAActionPersistence.class.getName() +
					".impl");
		}

		if (jiraActionFinder == null) {
			jiraActionFinder = (JIRAActionFinder)PortletBeanLocatorUtil.locate(JIRAActionFinder.class.getName() +
					".impl");
		}

		if (jiraChangeGroupLocalService == null) {
			jiraChangeGroupLocalService = (JIRAChangeGroupLocalService)PortletBeanLocatorUtil.locate(JIRAChangeGroupLocalService.class.getName() +
					".impl");
		}

		if (jiraChangeGroupPersistence == null) {
			jiraChangeGroupPersistence = (JIRAChangeGroupPersistence)PortletBeanLocatorUtil.locate(JIRAChangeGroupPersistence.class.getName() +
					".impl");
		}

		if (jiraChangeGroupFinder == null) {
			jiraChangeGroupFinder = (JIRAChangeGroupFinder)PortletBeanLocatorUtil.locate(JIRAChangeGroupFinder.class.getName() +
					".impl");
		}

		if (jiraChangeItemPersistence == null) {
			jiraChangeItemPersistence = (JIRAChangeItemPersistence)PortletBeanLocatorUtil.locate(JIRAChangeItemPersistence.class.getName() +
					".impl");
		}

		if (jiraIssueLocalService == null) {
			jiraIssueLocalService = (JIRAIssueLocalService)PortletBeanLocatorUtil.locate(JIRAIssueLocalService.class.getName() +
					".impl");
		}

		if (jiraIssuePersistence == null) {
			jiraIssuePersistence = (JIRAIssuePersistence)PortletBeanLocatorUtil.locate(JIRAIssuePersistence.class.getName() +
					".impl");
		}

		if (jiraIssueFinder == null) {
			jiraIssueFinder = (JIRAIssueFinder)PortletBeanLocatorUtil.locate(JIRAIssueFinder.class.getName() +
					".impl");
		}

		if (meetupsEntryLocalService == null) {
			meetupsEntryLocalService = (MeetupsEntryLocalService)PortletBeanLocatorUtil.locate(MeetupsEntryLocalService.class.getName() +
					".impl");
		}

		if (meetupsEntryPersistence == null) {
			meetupsEntryPersistence = (MeetupsEntryPersistence)PortletBeanLocatorUtil.locate(MeetupsEntryPersistence.class.getName() +
					".impl");
		}

		if (meetupsRegistrationLocalService == null) {
			meetupsRegistrationLocalService = (MeetupsRegistrationLocalService)PortletBeanLocatorUtil.locate(MeetupsRegistrationLocalService.class.getName() +
					".impl");
		}

		if (meetupsRegistrationPersistence == null) {
			meetupsRegistrationPersistence = (MeetupsRegistrationPersistence)PortletBeanLocatorUtil.locate(MeetupsRegistrationPersistence.class.getName() +
					".impl");
		}

		if (svnRepositoryLocalService == null) {
			svnRepositoryLocalService = (SVNRepositoryLocalService)PortletBeanLocatorUtil.locate(SVNRepositoryLocalService.class.getName() +
					".impl");
		}

		if (svnRepositoryPersistence == null) {
			svnRepositoryPersistence = (SVNRepositoryPersistence)PortletBeanLocatorUtil.locate(SVNRepositoryPersistence.class.getName() +
					".impl");
		}

		if (svnRevisionLocalService == null) {
			svnRevisionLocalService = (SVNRevisionLocalService)PortletBeanLocatorUtil.locate(SVNRevisionLocalService.class.getName() +
					".impl");
		}

		if (svnRevisionPersistence == null) {
			svnRevisionPersistence = (SVNRevisionPersistence)PortletBeanLocatorUtil.locate(SVNRevisionPersistence.class.getName() +
					".impl");
		}

		if (wallEntryLocalService == null) {
			wallEntryLocalService = (WallEntryLocalService)PortletBeanLocatorUtil.locate(WallEntryLocalService.class.getName() +
					".impl");
		}

		if (wallEntryPersistence == null) {
			wallEntryPersistence = (WallEntryPersistence)PortletBeanLocatorUtil.locate(WallEntryPersistence.class.getName() +
					".impl");
		}

		if (wallEntryFinder == null) {
			wallEntryFinder = (WallEntryFinder)PortletBeanLocatorUtil.locate(WallEntryFinder.class.getName() +
					".impl");
		}
	}

	protected JIRAActionLocalService jiraActionLocalService;
	protected JIRAActionPersistence jiraActionPersistence;
	protected JIRAActionFinder jiraActionFinder;
	protected JIRAChangeGroupLocalService jiraChangeGroupLocalService;
	protected JIRAChangeGroupPersistence jiraChangeGroupPersistence;
	protected JIRAChangeGroupFinder jiraChangeGroupFinder;
	protected JIRAChangeItemPersistence jiraChangeItemPersistence;
	protected JIRAIssueLocalService jiraIssueLocalService;
	protected JIRAIssuePersistence jiraIssuePersistence;
	protected JIRAIssueFinder jiraIssueFinder;
	protected MeetupsEntryLocalService meetupsEntryLocalService;
	protected MeetupsEntryPersistence meetupsEntryPersistence;
	protected MeetupsRegistrationLocalService meetupsRegistrationLocalService;
	protected MeetupsRegistrationPersistence meetupsRegistrationPersistence;
	protected SVNRepositoryLocalService svnRepositoryLocalService;
	protected SVNRepositoryPersistence svnRepositoryPersistence;
	protected SVNRevisionLocalService svnRevisionLocalService;
	protected SVNRevisionPersistence svnRevisionPersistence;
	protected WallEntryLocalService wallEntryLocalService;
	protected WallEntryPersistence wallEntryPersistence;
	protected WallEntryFinder wallEntryFinder;
}