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

package com.liferay.opensocial.service.base;

import com.liferay.counter.service.CounterLocalService;

import com.liferay.opensocial.model.Gadget;
import com.liferay.opensocial.service.GadgetLocalService;
import com.liferay.opensocial.service.persistence.GadgetPersistence;

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
 * <a href="GadgetLocalServiceBaseImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public abstract class GadgetLocalServiceBaseImpl implements GadgetLocalService {
	public Gadget addGadget(Gadget gadget) throws SystemException {
		gadget.setNew(true);

		return gadgetPersistence.update(gadget, false);
	}

	public Gadget createGadget(long gadgetId) {
		return gadgetPersistence.create(gadgetId);
	}

	public void deleteGadget(long gadgetId)
		throws PortalException, SystemException {
		gadgetPersistence.remove(gadgetId);
	}

	public void deleteGadget(Gadget gadget)
		throws PortalException, SystemException {
		gadgetPersistence.remove(gadget);
	}

	@SuppressWarnings("unchecked")
	public List dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return gadgetPersistence.findWithDynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return gadgetPersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	@SuppressWarnings("unchecked")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return gadgetPersistence.findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	public long dynamicQueryCount(DynamicQuery dynamicQuery)
		throws SystemException {
		return gadgetPersistence.countWithDynamicQuery(dynamicQuery);
	}

	public Gadget getGadget(long gadgetId)
		throws PortalException, SystemException {
		return gadgetPersistence.findByPrimaryKey(gadgetId);
	}

	public List<Gadget> getGadgets(int start, int end)
		throws SystemException {
		return gadgetPersistence.findAll(start, end);
	}

	public int getGadgetsCount() throws SystemException {
		return gadgetPersistence.countAll();
	}

	public Gadget updateGadget(Gadget gadget) throws SystemException {
		gadget.setNew(false);

		return gadgetPersistence.update(gadget, true);
	}

	public Gadget updateGadget(Gadget gadget, boolean merge)
		throws SystemException {
		gadget.setNew(false);

		return gadgetPersistence.update(gadget, merge);
	}

	public GadgetLocalService getGadgetLocalService() {
		return gadgetLocalService;
	}

	public void setGadgetLocalService(GadgetLocalService gadgetLocalService) {
		this.gadgetLocalService = gadgetLocalService;
	}

	public GadgetPersistence getGadgetPersistence() {
		return gadgetPersistence;
	}

	public void setGadgetPersistence(GadgetPersistence gadgetPersistence) {
		this.gadgetPersistence = gadgetPersistence;
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
			DataSource dataSource = gadgetPersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = GadgetLocalService.class)
	protected GadgetLocalService gadgetLocalService;
	@BeanReference(type = GadgetPersistence.class)
	protected GadgetPersistence gadgetPersistence;
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