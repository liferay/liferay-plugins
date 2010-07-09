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

package com.liferay.socialnetworking.service.base;

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

import com.liferay.socialnetworking.model.WallEntry;
import com.liferay.socialnetworking.service.MeetupsEntryLocalService;
import com.liferay.socialnetworking.service.MeetupsRegistrationLocalService;
import com.liferay.socialnetworking.service.WallEntryLocalService;
import com.liferay.socialnetworking.service.persistence.MeetupsEntryPersistence;
import com.liferay.socialnetworking.service.persistence.MeetupsRegistrationPersistence;
import com.liferay.socialnetworking.service.persistence.WallEntryFinder;
import com.liferay.socialnetworking.service.persistence.WallEntryPersistence;

import java.util.List;

import javax.sql.DataSource;

/**
 * @author Brian Wing Shun Chan
 */
public abstract class WallEntryLocalServiceBaseImpl
	implements WallEntryLocalService {
	public WallEntry addWallEntry(WallEntry wallEntry)
		throws SystemException {
		wallEntry.setNew(true);

		return wallEntryPersistence.update(wallEntry, false);
	}

	public WallEntry createWallEntry(long wallEntryId) {
		return wallEntryPersistence.create(wallEntryId);
	}

	public void deleteWallEntry(long wallEntryId)
		throws PortalException, SystemException {
		wallEntryPersistence.remove(wallEntryId);
	}

	public void deleteWallEntry(WallEntry wallEntry) throws SystemException {
		wallEntryPersistence.remove(wallEntry);
	}

	@SuppressWarnings("unchecked")
	public List dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return wallEntryPersistence.findWithDynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return wallEntryPersistence.findWithDynamicQuery(dynamicQuery, start,
			end);
	}

	@SuppressWarnings("unchecked")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return wallEntryPersistence.findWithDynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	public long dynamicQueryCount(DynamicQuery dynamicQuery)
		throws SystemException {
		return wallEntryPersistence.countWithDynamicQuery(dynamicQuery);
	}

	public WallEntry getWallEntry(long wallEntryId)
		throws PortalException, SystemException {
		return wallEntryPersistence.findByPrimaryKey(wallEntryId);
	}

	public List<WallEntry> getWallEntries(int start, int end)
		throws SystemException {
		return wallEntryPersistence.findAll(start, end);
	}

	public int getWallEntriesCount() throws SystemException {
		return wallEntryPersistence.countAll();
	}

	public WallEntry updateWallEntry(WallEntry wallEntry)
		throws SystemException {
		wallEntry.setNew(false);

		return wallEntryPersistence.update(wallEntry, true);
	}

	public WallEntry updateWallEntry(WallEntry wallEntry, boolean merge)
		throws SystemException {
		wallEntry.setNew(false);

		return wallEntryPersistence.update(wallEntry, merge);
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
			DataSource dataSource = wallEntryPersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = MeetupsEntryLocalService.class)
	protected MeetupsEntryLocalService meetupsEntryLocalService;
	@BeanReference(type = MeetupsEntryPersistence.class)
	protected MeetupsEntryPersistence meetupsEntryPersistence;
	@BeanReference(type = MeetupsRegistrationLocalService.class)
	protected MeetupsRegistrationLocalService meetupsRegistrationLocalService;
	@BeanReference(type = MeetupsRegistrationPersistence.class)
	protected MeetupsRegistrationPersistence meetupsRegistrationPersistence;
	@BeanReference(type = WallEntryLocalService.class)
	protected WallEntryLocalService wallEntryLocalService;
	@BeanReference(type = WallEntryPersistence.class)
	protected WallEntryPersistence wallEntryPersistence;
	@BeanReference(type = WallEntryFinder.class)
	protected WallEntryFinder wallEntryFinder;
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