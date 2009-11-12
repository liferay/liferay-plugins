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

package com.liferay.socialcoding.service.base;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;

import com.liferay.socialcoding.model.JIRAChangeGroup;
import com.liferay.socialcoding.service.JIRAActionLocalService;
import com.liferay.socialcoding.service.JIRAChangeGroupLocalService;
import com.liferay.socialcoding.service.JIRAChangeItemLocalService;
import com.liferay.socialcoding.service.JIRAIssueLocalService;
import com.liferay.socialcoding.service.SVNRepositoryLocalService;
import com.liferay.socialcoding.service.SVNRevisionLocalService;
import com.liferay.socialcoding.service.persistence.JIRAActionFinder;
import com.liferay.socialcoding.service.persistence.JIRAActionPersistence;
import com.liferay.socialcoding.service.persistence.JIRAChangeGroupFinder;
import com.liferay.socialcoding.service.persistence.JIRAChangeGroupPersistence;
import com.liferay.socialcoding.service.persistence.JIRAChangeItemPersistence;
import com.liferay.socialcoding.service.persistence.JIRAIssueFinder;
import com.liferay.socialcoding.service.persistence.JIRAIssuePersistence;
import com.liferay.socialcoding.service.persistence.SVNRepositoryPersistence;
import com.liferay.socialcoding.service.persistence.SVNRevisionPersistence;

import java.util.List;

/**
 * <a href="JIRAChangeGroupLocalServiceBaseImpl.java.html"><b><i>View Source</i>
 * </b></a>
 *
 * @author Brian Wing Shun Chan
 */
public abstract class JIRAChangeGroupLocalServiceBaseImpl
	implements JIRAChangeGroupLocalService {
	public JIRAChangeGroup addJIRAChangeGroup(JIRAChangeGroup jiraChangeGroup)
		throws SystemException {
		jiraChangeGroup.setNew(true);

		return jiraChangeGroupPersistence.update(jiraChangeGroup, false);
	}

	public JIRAChangeGroup createJIRAChangeGroup(long jiraChangeGroupId) {
		return jiraChangeGroupPersistence.create(jiraChangeGroupId);
	}

	public void deleteJIRAChangeGroup(long jiraChangeGroupId)
		throws PortalException, SystemException {
		jiraChangeGroupPersistence.remove(jiraChangeGroupId);
	}

	public void deleteJIRAChangeGroup(JIRAChangeGroup jiraChangeGroup)
		throws SystemException {
		jiraChangeGroupPersistence.remove(jiraChangeGroup);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return jiraChangeGroupPersistence.findWithDynamicQuery(dynamicQuery);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) throws SystemException {
		return jiraChangeGroupPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	public JIRAChangeGroup getJIRAChangeGroup(long jiraChangeGroupId)
		throws PortalException, SystemException {
		return jiraChangeGroupPersistence.findByPrimaryKey(jiraChangeGroupId);
	}

	public List<JIRAChangeGroup> getJIRAChangeGroups(int start, int end)
		throws SystemException {
		return jiraChangeGroupPersistence.findAll(start, end);
	}

	public int getJIRAChangeGroupsCount() throws SystemException {
		return jiraChangeGroupPersistence.countAll();
	}

	public JIRAChangeGroup updateJIRAChangeGroup(
		JIRAChangeGroup jiraChangeGroup) throws SystemException {
		jiraChangeGroup.setNew(false);

		return jiraChangeGroupPersistence.update(jiraChangeGroup, true);
	}

	public JIRAChangeGroup updateJIRAChangeGroup(
		JIRAChangeGroup jiraChangeGroup, boolean merge)
		throws SystemException {
		jiraChangeGroup.setNew(false);

		return jiraChangeGroupPersistence.update(jiraChangeGroup, merge);
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

	protected void runSQL(String sql) throws SystemException {
		try {
			DB db = DBFactoryUtil.getDB();

			db.runSQL(sql);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(name = "com.liferay.socialcoding.service.JIRAActionLocalService")
	protected JIRAActionLocalService jiraActionLocalService;
	@BeanReference(name = "com.liferay.socialcoding.service.persistence.JIRAActionPersistence")
	protected JIRAActionPersistence jiraActionPersistence;
	@BeanReference(name = "com.liferay.socialcoding.service.persistence.JIRAActionFinder")
	protected JIRAActionFinder jiraActionFinder;
	@BeanReference(name = "com.liferay.socialcoding.service.JIRAChangeGroupLocalService")
	protected JIRAChangeGroupLocalService jiraChangeGroupLocalService;
	@BeanReference(name = "com.liferay.socialcoding.service.persistence.JIRAChangeGroupPersistence")
	protected JIRAChangeGroupPersistence jiraChangeGroupPersistence;
	@BeanReference(name = "com.liferay.socialcoding.service.persistence.JIRAChangeGroupFinder")
	protected JIRAChangeGroupFinder jiraChangeGroupFinder;
	@BeanReference(name = "com.liferay.socialcoding.service.JIRAChangeItemLocalService")
	protected JIRAChangeItemLocalService jiraChangeItemLocalService;
	@BeanReference(name = "com.liferay.socialcoding.service.persistence.JIRAChangeItemPersistence")
	protected JIRAChangeItemPersistence jiraChangeItemPersistence;
	@BeanReference(name = "com.liferay.socialcoding.service.JIRAIssueLocalService")
	protected JIRAIssueLocalService jiraIssueLocalService;
	@BeanReference(name = "com.liferay.socialcoding.service.persistence.JIRAIssuePersistence")
	protected JIRAIssuePersistence jiraIssuePersistence;
	@BeanReference(name = "com.liferay.socialcoding.service.persistence.JIRAIssueFinder")
	protected JIRAIssueFinder jiraIssueFinder;
	@BeanReference(name = "com.liferay.socialcoding.service.SVNRepositoryLocalService")
	protected SVNRepositoryLocalService svnRepositoryLocalService;
	@BeanReference(name = "com.liferay.socialcoding.service.persistence.SVNRepositoryPersistence")
	protected SVNRepositoryPersistence svnRepositoryPersistence;
	@BeanReference(name = "com.liferay.socialcoding.service.SVNRevisionLocalService")
	protected SVNRevisionLocalService svnRevisionLocalService;
	@BeanReference(name = "com.liferay.socialcoding.service.persistence.SVNRevisionPersistence")
	protected SVNRevisionPersistence svnRevisionPersistence;
}