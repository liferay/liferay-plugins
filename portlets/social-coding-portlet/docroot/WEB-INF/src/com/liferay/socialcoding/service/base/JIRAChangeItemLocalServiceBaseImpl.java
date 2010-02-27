/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.socialcoding.service.base;

import com.liferay.counter.service.CounterLocalService;
import com.liferay.counter.service.CounterService;

import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ResourceLocalService;
import com.liferay.portal.service.ResourceService;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserService;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;

import com.liferay.socialcoding.model.JIRAChangeItem;
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
 * <a href="JIRAChangeItemLocalServiceBaseImpl.java.html"><b><i>View Source</i>
 * </b></a>
 *
 * @author Brian Wing Shun Chan
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

	public CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	public void setCounterLocalService(CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	public CounterService getCounterService() {
		return counterService;
	}

	public void setCounterService(CounterService counterService) {
		this.counterService = counterService;
	}

	public ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	public void setResourceLocalService(
		ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	public ResourceService getResourceService() {
		return resourceService;
	}

	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	public ResourcePersistence getResourcePersistence() {
		return resourcePersistence;
	}

	public void setResourcePersistence(ResourcePersistence resourcePersistence) {
		this.resourcePersistence = resourcePersistence;
	}

	public UserLocalService getUserLocalService() {
		return userLocalService;
	}

	public void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
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
	@BeanReference(name = "com.liferay.counter.service.CounterLocalService")
	protected CounterLocalService counterLocalService;
	@BeanReference(name = "com.liferay.counter.service.CounterService")
	protected CounterService counterService;
	@BeanReference(name = "com.liferay.portal.service.ResourceLocalService")
	protected ResourceLocalService resourceLocalService;
	@BeanReference(name = "com.liferay.portal.service.ResourceService")
	protected ResourceService resourceService;
	@BeanReference(name = "com.liferay.portal.service.persistence.ResourcePersistence")
	protected ResourcePersistence resourcePersistence;
	@BeanReference(name = "com.liferay.portal.service.UserLocalService")
	protected UserLocalService userLocalService;
	@BeanReference(name = "com.liferay.portal.service.UserService")
	protected UserService userService;
	@BeanReference(name = "com.liferay.portal.service.persistence.UserPersistence")
	protected UserPersistence userPersistence;
}