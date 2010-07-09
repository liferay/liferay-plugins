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

package com.liferay.chat.service.base;

import com.liferay.chat.model.Entry;
import com.liferay.chat.service.EntryLocalService;
import com.liferay.chat.service.StatusLocalService;
import com.liferay.chat.service.persistence.EntryFinder;
import com.liferay.chat.service.persistence.EntryPersistence;
import com.liferay.chat.service.persistence.StatusFinder;
import com.liferay.chat.service.persistence.StatusPersistence;

import com.liferay.counter.service.CounterLocalService;

import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.ResourceLocalService;
import com.liferay.portal.service.ResourceService;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserService;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;

import java.util.List;

import javax.sql.DataSource;

/**
 * @author Brian Wing Shun Chan
 */
public abstract class EntryLocalServiceBaseImpl implements EntryLocalService {
	public Entry addEntry(Entry entry) throws SystemException {
		entry.setNew(true);

		return entryPersistence.update(entry, false);
	}

	public Entry createEntry(long entryId) {
		return entryPersistence.create(entryId);
	}

	public void deleteEntry(long entryId)
		throws PortalException, SystemException {
		entryPersistence.remove(entryId);
	}

	public void deleteEntry(Entry entry) throws SystemException {
		entryPersistence.remove(entry);
	}

	@SuppressWarnings("unchecked")
	public List dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return entryPersistence.findWithDynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return entryPersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	@SuppressWarnings("unchecked")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return entryPersistence.findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	public long dynamicQueryCount(DynamicQuery dynamicQuery)
		throws SystemException {
		return entryPersistence.countWithDynamicQuery(dynamicQuery);
	}

	public Entry getEntry(long entryId) throws PortalException, SystemException {
		return entryPersistence.findByPrimaryKey(entryId);
	}

	public List<Entry> getEntries(int start, int end) throws SystemException {
		return entryPersistence.findAll(start, end);
	}

	public int getEntriesCount() throws SystemException {
		return entryPersistence.countAll();
	}

	public Entry updateEntry(Entry entry) throws SystemException {
		entry.setNew(false);

		return entryPersistence.update(entry, true);
	}

	public Entry updateEntry(Entry entry, boolean merge)
		throws SystemException {
		entry.setNew(false);

		return entryPersistence.update(entry, merge);
	}

	public EntryLocalService getEntryLocalService() {
		return entryLocalService;
	}

	public void setEntryLocalService(EntryLocalService entryLocalService) {
		this.entryLocalService = entryLocalService;
	}

	public EntryPersistence getEntryPersistence() {
		return entryPersistence;
	}

	public void setEntryPersistence(EntryPersistence entryPersistence) {
		this.entryPersistence = entryPersistence;
	}

	public EntryFinder getEntryFinder() {
		return entryFinder;
	}

	public void setEntryFinder(EntryFinder entryFinder) {
		this.entryFinder = entryFinder;
	}

	public StatusLocalService getStatusLocalService() {
		return statusLocalService;
	}

	public void setStatusLocalService(StatusLocalService statusLocalService) {
		this.statusLocalService = statusLocalService;
	}

	public StatusPersistence getStatusPersistence() {
		return statusPersistence;
	}

	public void setStatusPersistence(StatusPersistence statusPersistence) {
		this.statusPersistence = statusPersistence;
	}

	public StatusFinder getStatusFinder() {
		return statusFinder;
	}

	public void setStatusFinder(StatusFinder statusFinder) {
		this.statusFinder = statusFinder;
	}

	public CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	public void setCounterLocalService(CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
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
			DataSource dataSource = entryPersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = EntryLocalService.class)
	protected EntryLocalService entryLocalService;
	@BeanReference(type = EntryPersistence.class)
	protected EntryPersistence entryPersistence;
	@BeanReference(type = EntryFinder.class)
	protected EntryFinder entryFinder;
	@BeanReference(type = StatusLocalService.class)
	protected StatusLocalService statusLocalService;
	@BeanReference(type = StatusPersistence.class)
	protected StatusPersistence statusPersistence;
	@BeanReference(type = StatusFinder.class)
	protected StatusFinder statusFinder;
	@BeanReference(type = CounterLocalService.class)
	protected CounterLocalService counterLocalService;
	@BeanReference(type = ResourceLocalService.class)
	protected ResourceLocalService resourceLocalService;
	@BeanReference(type = ResourceService.class)
	protected ResourceService resourceService;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserLocalService.class)
	protected UserLocalService userLocalService;
	@BeanReference(type = UserService.class)
	protected UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
}