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

import com.liferay.socialnetworking.model.MeetupsRegistration;
import com.liferay.socialnetworking.service.MeetupsEntryLocalService;
import com.liferay.socialnetworking.service.MeetupsRegistrationLocalService;
import com.liferay.socialnetworking.service.WallEntryLocalService;
import com.liferay.socialnetworking.service.persistence.MeetupsEntryPersistence;
import com.liferay.socialnetworking.service.persistence.MeetupsRegistrationPersistence;
import com.liferay.socialnetworking.service.persistence.WallEntryFinder;
import com.liferay.socialnetworking.service.persistence.WallEntryPersistence;

import java.util.List;

/**
 * <a href="MeetupsRegistrationLocalServiceBaseImpl.java.html"><b><i>View Source
 * </i></b></a>
 *
 * @author Brian Wing Shun Chan
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

	public MeetupsRegistration updateMeetupsRegistration(
		MeetupsRegistration meetupsRegistration, boolean merge)
		throws SystemException {
		meetupsRegistration.setNew(false);

		return meetupsRegistrationPersistence.update(meetupsRegistration, merge);
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

	@BeanReference(name = "com.liferay.socialnetworking.service.MeetupsEntryLocalService")
	protected MeetupsEntryLocalService meetupsEntryLocalService;
	@BeanReference(name = "com.liferay.socialnetworking.service.persistence.MeetupsEntryPersistence")
	protected MeetupsEntryPersistence meetupsEntryPersistence;
	@BeanReference(name = "com.liferay.socialnetworking.service.MeetupsRegistrationLocalService")
	protected MeetupsRegistrationLocalService meetupsRegistrationLocalService;
	@BeanReference(name = "com.liferay.socialnetworking.service.persistence.MeetupsRegistrationPersistence")
	protected MeetupsRegistrationPersistence meetupsRegistrationPersistence;
	@BeanReference(name = "com.liferay.socialnetworking.service.WallEntryLocalService")
	protected WallEntryLocalService wallEntryLocalService;
	@BeanReference(name = "com.liferay.socialnetworking.service.persistence.WallEntryPersistence")
	protected WallEntryPersistence wallEntryPersistence;
	@BeanReference(name = "com.liferay.socialnetworking.service.persistence.WallEntryFinder")
	protected WallEntryFinder wallEntryFinder;
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