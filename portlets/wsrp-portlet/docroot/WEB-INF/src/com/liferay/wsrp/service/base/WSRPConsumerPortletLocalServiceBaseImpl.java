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

package com.liferay.wsrp.service.base;

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

import com.liferay.wsrp.model.WSRPConsumerPortlet;
import com.liferay.wsrp.service.WSRPConsumerLocalService;
import com.liferay.wsrp.service.WSRPConsumerPortletLocalService;
import com.liferay.wsrp.service.WSRPProducerLocalService;
import com.liferay.wsrp.service.persistence.WSRPConsumerPersistence;
import com.liferay.wsrp.service.persistence.WSRPConsumerPortletPersistence;
import com.liferay.wsrp.service.persistence.WSRPProducerPersistence;

import java.util.List;

import javax.sql.DataSource;

/**
 * @author Brian Wing Shun Chan
 */
public abstract class WSRPConsumerPortletLocalServiceBaseImpl
	implements WSRPConsumerPortletLocalService {
	public WSRPConsumerPortlet addWSRPConsumerPortlet(
		WSRPConsumerPortlet wsrpConsumerPortlet) throws SystemException {
		wsrpConsumerPortlet.setNew(true);

		return wsrpConsumerPortletPersistence.update(wsrpConsumerPortlet, false);
	}

	public WSRPConsumerPortlet createWSRPConsumerPortlet(
		long wsrpConsumerPortletId) {
		return wsrpConsumerPortletPersistence.create(wsrpConsumerPortletId);
	}

	public void deleteWSRPConsumerPortlet(long wsrpConsumerPortletId)
		throws PortalException, SystemException {
		wsrpConsumerPortletPersistence.remove(wsrpConsumerPortletId);
	}

	public void deleteWSRPConsumerPortlet(
		WSRPConsumerPortlet wsrpConsumerPortlet)
		throws PortalException, SystemException {
		wsrpConsumerPortletPersistence.remove(wsrpConsumerPortlet);
	}

	@SuppressWarnings("unchecked")
	public List dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return wsrpConsumerPortletPersistence.findWithDynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return wsrpConsumerPortletPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	@SuppressWarnings("unchecked")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return wsrpConsumerPortletPersistence.findWithDynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	public long dynamicQueryCount(DynamicQuery dynamicQuery)
		throws SystemException {
		return wsrpConsumerPortletPersistence.countWithDynamicQuery(dynamicQuery);
	}

	public WSRPConsumerPortlet getWSRPConsumerPortlet(
		long wsrpConsumerPortletId) throws PortalException, SystemException {
		return wsrpConsumerPortletPersistence.findByPrimaryKey(wsrpConsumerPortletId);
	}

	public List<WSRPConsumerPortlet> getWSRPConsumerPortlets(int start, int end)
		throws SystemException {
		return wsrpConsumerPortletPersistence.findAll(start, end);
	}

	public int getWSRPConsumerPortletsCount() throws SystemException {
		return wsrpConsumerPortletPersistence.countAll();
	}

	public WSRPConsumerPortlet updateWSRPConsumerPortlet(
		WSRPConsumerPortlet wsrpConsumerPortlet) throws SystemException {
		wsrpConsumerPortlet.setNew(false);

		return wsrpConsumerPortletPersistence.update(wsrpConsumerPortlet, true);
	}

	public WSRPConsumerPortlet updateWSRPConsumerPortlet(
		WSRPConsumerPortlet wsrpConsumerPortlet, boolean merge)
		throws SystemException {
		wsrpConsumerPortlet.setNew(false);

		return wsrpConsumerPortletPersistence.update(wsrpConsumerPortlet, merge);
	}

	public WSRPConsumerLocalService getWSRPConsumerLocalService() {
		return wsrpConsumerLocalService;
	}

	public void setWSRPConsumerLocalService(
		WSRPConsumerLocalService wsrpConsumerLocalService) {
		this.wsrpConsumerLocalService = wsrpConsumerLocalService;
	}

	public WSRPConsumerPersistence getWSRPConsumerPersistence() {
		return wsrpConsumerPersistence;
	}

	public void setWSRPConsumerPersistence(
		WSRPConsumerPersistence wsrpConsumerPersistence) {
		this.wsrpConsumerPersistence = wsrpConsumerPersistence;
	}

	public WSRPConsumerPortletLocalService getWSRPConsumerPortletLocalService() {
		return wsrpConsumerPortletLocalService;
	}

	public void setWSRPConsumerPortletLocalService(
		WSRPConsumerPortletLocalService wsrpConsumerPortletLocalService) {
		this.wsrpConsumerPortletLocalService = wsrpConsumerPortletLocalService;
	}

	public WSRPConsumerPortletPersistence getWSRPConsumerPortletPersistence() {
		return wsrpConsumerPortletPersistence;
	}

	public void setWSRPConsumerPortletPersistence(
		WSRPConsumerPortletPersistence wsrpConsumerPortletPersistence) {
		this.wsrpConsumerPortletPersistence = wsrpConsumerPortletPersistence;
	}

	public WSRPProducerLocalService getWSRPProducerLocalService() {
		return wsrpProducerLocalService;
	}

	public void setWSRPProducerLocalService(
		WSRPProducerLocalService wsrpProducerLocalService) {
		this.wsrpProducerLocalService = wsrpProducerLocalService;
	}

	public WSRPProducerPersistence getWSRPProducerPersistence() {
		return wsrpProducerPersistence;
	}

	public void setWSRPProducerPersistence(
		WSRPProducerPersistence wsrpProducerPersistence) {
		this.wsrpProducerPersistence = wsrpProducerPersistence;
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
			DataSource dataSource = wsrpConsumerPortletPersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = WSRPConsumerLocalService.class)
	protected WSRPConsumerLocalService wsrpConsumerLocalService;
	@BeanReference(type = WSRPConsumerPersistence.class)
	protected WSRPConsumerPersistence wsrpConsumerPersistence;
	@BeanReference(type = WSRPConsumerPortletLocalService.class)
	protected WSRPConsumerPortletLocalService wsrpConsumerPortletLocalService;
	@BeanReference(type = WSRPConsumerPortletPersistence.class)
	protected WSRPConsumerPortletPersistence wsrpConsumerPortletPersistence;
	@BeanReference(type = WSRPProducerLocalService.class)
	protected WSRPProducerLocalService wsrpProducerLocalService;
	@BeanReference(type = WSRPProducerPersistence.class)
	protected WSRPProducerPersistence wsrpProducerPersistence;
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