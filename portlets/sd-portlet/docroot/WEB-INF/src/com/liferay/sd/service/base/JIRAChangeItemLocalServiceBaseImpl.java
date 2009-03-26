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

package com.liferay.sd.service.base;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;

import com.liferay.sd.model.JIRAChangeItem;
import com.liferay.sd.service.JIRAActionLocalService;
import com.liferay.sd.service.JIRAChangeGroupLocalService;
import com.liferay.sd.service.JIRAChangeItemLocalService;
import com.liferay.sd.service.JIRAIssueLocalService;
import com.liferay.sd.service.SVNRepositoryLocalService;
import com.liferay.sd.service.SVNRevisionLocalService;
import com.liferay.sd.service.persistence.JIRAActionFinder;
import com.liferay.sd.service.persistence.JIRAActionPersistence;
import com.liferay.sd.service.persistence.JIRAChangeGroupFinder;
import com.liferay.sd.service.persistence.JIRAChangeGroupPersistence;
import com.liferay.sd.service.persistence.JIRAChangeItemPersistence;
import com.liferay.sd.service.persistence.JIRAIssueFinder;
import com.liferay.sd.service.persistence.JIRAIssuePersistence;
import com.liferay.sd.service.persistence.SVNRepositoryPersistence;
import com.liferay.sd.service.persistence.SVNRevisionPersistence;

import java.util.List;

/**
 * <a href="JIRAChangeItemLocalServiceBaseImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public abstract class JIRAChangeItemLocalServiceBaseImpl
	implements JIRAChangeItemLocalService {
	public JIRAChangeItem addJIRAChangeItem(JIRAChangeItem jiraChangeItem)
		throws SystemException {
		jiraChangeItem.setNew(true);

		return jiraChangeItemPersistence.update(jiraChangeItem, false);
	}

	public JIRAChangeItem createJIRAChangeItem(long jiraChangeItemId) {
		return jiraChangeItemPersistence.create(jiraChangeItemId);
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

	public JIRAChangeItem updateJIRAChangeItem(JIRAChangeItem jiraChangeItem,
		boolean merge) throws SystemException {
		jiraChangeItem.setNew(false);

		return jiraChangeItemPersistence.update(jiraChangeItem, merge);
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

	@BeanReference(name = "com.liferay.sd.service.JIRAActionLocalService.impl")
	protected JIRAActionLocalService jiraActionLocalService;
	@BeanReference(name = "com.liferay.sd.service.persistence.JIRAActionPersistence.impl")
	protected JIRAActionPersistence jiraActionPersistence;
	@BeanReference(name = "com.liferay.sd.service.persistence.JIRAActionFinder.impl")
	protected JIRAActionFinder jiraActionFinder;
	@BeanReference(name = "com.liferay.sd.service.JIRAChangeGroupLocalService.impl")
	protected JIRAChangeGroupLocalService jiraChangeGroupLocalService;
	@BeanReference(name = "com.liferay.sd.service.persistence.JIRAChangeGroupPersistence.impl")
	protected JIRAChangeGroupPersistence jiraChangeGroupPersistence;
	@BeanReference(name = "com.liferay.sd.service.persistence.JIRAChangeGroupFinder.impl")
	protected JIRAChangeGroupFinder jiraChangeGroupFinder;
	@BeanReference(name = "com.liferay.sd.service.JIRAChangeItemLocalService.impl")
	protected JIRAChangeItemLocalService jiraChangeItemLocalService;
	@BeanReference(name = "com.liferay.sd.service.persistence.JIRAChangeItemPersistence.impl")
	protected JIRAChangeItemPersistence jiraChangeItemPersistence;
	@BeanReference(name = "com.liferay.sd.service.JIRAIssueLocalService.impl")
	protected JIRAIssueLocalService jiraIssueLocalService;
	@BeanReference(name = "com.liferay.sd.service.persistence.JIRAIssuePersistence.impl")
	protected JIRAIssuePersistence jiraIssuePersistence;
	@BeanReference(name = "com.liferay.sd.service.persistence.JIRAIssueFinder.impl")
	protected JIRAIssueFinder jiraIssueFinder;
	@BeanReference(name = "com.liferay.sd.service.SVNRepositoryLocalService.impl")
	protected SVNRepositoryLocalService svnRepositoryLocalService;
	@BeanReference(name = "com.liferay.sd.service.persistence.SVNRepositoryPersistence.impl")
	protected SVNRepositoryPersistence svnRepositoryPersistence;
	@BeanReference(name = "com.liferay.sd.service.SVNRevisionLocalService.impl")
	protected SVNRevisionLocalService svnRevisionLocalService;
	@BeanReference(name = "com.liferay.sd.service.persistence.SVNRevisionPersistence.impl")
	protected SVNRevisionPersistence svnRevisionPersistence;
}