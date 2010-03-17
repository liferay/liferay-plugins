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

package com.liferay.portal.workflow.kaleo.service.base;

import com.liferay.counter.service.CounterLocalService;
import com.liferay.counter.service.CounterService;

import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
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
import com.liferay.portal.workflow.kaleo.model.KaleoAction;
import com.liferay.portal.workflow.kaleo.service.KaleoActionLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoDefinitionLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoInstanceLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoInstanceTokenLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoLogLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoNodeLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskAssignmentLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskInstanceAssignmentLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskInstanceTokenLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoTransitionLocalService;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoActionPersistence;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoDefinitionPersistence;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoInstancePersistence;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoInstanceTokenPersistence;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoLogPersistence;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoNodePersistence;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoTaskAssignmentPersistence;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoTaskInstanceAssignmentPersistence;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoTaskInstanceTokenPersistence;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoTaskPersistence;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoTransitionPersistence;

import java.util.List;

/**
 * <a href="KaleoActionLocalServiceBaseImpl.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Brian Wing Shun Chan
 */
public abstract class KaleoActionLocalServiceBaseImpl
	implements KaleoActionLocalService {
	public KaleoAction addKaleoAction(KaleoAction kaleoAction)
		throws SystemException {
		kaleoAction.setNew(true);

		return kaleoActionPersistence.update(kaleoAction, false);
	}

	public KaleoAction createKaleoAction(long kaleoActionId) {
		return kaleoActionPersistence.create(kaleoActionId);
	}

	public void deleteKaleoAction(long kaleoActionId)
		throws PortalException, SystemException {
		kaleoActionPersistence.remove(kaleoActionId);
	}

	public void deleteKaleoAction(KaleoAction kaleoAction)
		throws SystemException {
		kaleoActionPersistence.remove(kaleoAction);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return kaleoActionPersistence.findWithDynamicQuery(dynamicQuery);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) throws SystemException {
		return kaleoActionPersistence.findWithDynamicQuery(dynamicQuery, start,
			end);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		return kaleoActionPersistence.findWithDynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	public int dynamicQueryCount(DynamicQuery dynamicQuery)
		throws SystemException {
		return kaleoActionPersistence.countWithDynamicQuery(dynamicQuery);
	}

	public KaleoAction getKaleoAction(long kaleoActionId)
		throws PortalException, SystemException {
		return kaleoActionPersistence.findByPrimaryKey(kaleoActionId);
	}

	public List<KaleoAction> getKaleoActions(int start, int end)
		throws SystemException {
		return kaleoActionPersistence.findAll(start, end);
	}

	public int getKaleoActionsCount() throws SystemException {
		return kaleoActionPersistence.countAll();
	}

	public KaleoAction updateKaleoAction(KaleoAction kaleoAction)
		throws SystemException {
		kaleoAction.setNew(false);

		return kaleoActionPersistence.update(kaleoAction, true);
	}

	public KaleoAction updateKaleoAction(KaleoAction kaleoAction, boolean merge)
		throws SystemException {
		kaleoAction.setNew(false);

		return kaleoActionPersistence.update(kaleoAction, merge);
	}

	public KaleoActionLocalService getKaleoActionLocalService() {
		return kaleoActionLocalService;
	}

	public void setKaleoActionLocalService(
		KaleoActionLocalService kaleoActionLocalService) {
		this.kaleoActionLocalService = kaleoActionLocalService;
	}

	public KaleoActionPersistence getKaleoActionPersistence() {
		return kaleoActionPersistence;
	}

	public void setKaleoActionPersistence(
		KaleoActionPersistence kaleoActionPersistence) {
		this.kaleoActionPersistence = kaleoActionPersistence;
	}

	public KaleoDefinitionLocalService getKaleoDefinitionLocalService() {
		return kaleoDefinitionLocalService;
	}

	public void setKaleoDefinitionLocalService(
		KaleoDefinitionLocalService kaleoDefinitionLocalService) {
		this.kaleoDefinitionLocalService = kaleoDefinitionLocalService;
	}

	public KaleoDefinitionPersistence getKaleoDefinitionPersistence() {
		return kaleoDefinitionPersistence;
	}

	public void setKaleoDefinitionPersistence(
		KaleoDefinitionPersistence kaleoDefinitionPersistence) {
		this.kaleoDefinitionPersistence = kaleoDefinitionPersistence;
	}

	public KaleoInstanceLocalService getKaleoInstanceLocalService() {
		return kaleoInstanceLocalService;
	}

	public void setKaleoInstanceLocalService(
		KaleoInstanceLocalService kaleoInstanceLocalService) {
		this.kaleoInstanceLocalService = kaleoInstanceLocalService;
	}

	public KaleoInstancePersistence getKaleoInstancePersistence() {
		return kaleoInstancePersistence;
	}

	public void setKaleoInstancePersistence(
		KaleoInstancePersistence kaleoInstancePersistence) {
		this.kaleoInstancePersistence = kaleoInstancePersistence;
	}

	public KaleoInstanceTokenLocalService getKaleoInstanceTokenLocalService() {
		return kaleoInstanceTokenLocalService;
	}

	public void setKaleoInstanceTokenLocalService(
		KaleoInstanceTokenLocalService kaleoInstanceTokenLocalService) {
		this.kaleoInstanceTokenLocalService = kaleoInstanceTokenLocalService;
	}

	public KaleoInstanceTokenPersistence getKaleoInstanceTokenPersistence() {
		return kaleoInstanceTokenPersistence;
	}

	public void setKaleoInstanceTokenPersistence(
		KaleoInstanceTokenPersistence kaleoInstanceTokenPersistence) {
		this.kaleoInstanceTokenPersistence = kaleoInstanceTokenPersistence;
	}

	public KaleoLogLocalService getKaleoLogLocalService() {
		return kaleoLogLocalService;
	}

	public void setKaleoLogLocalService(
		KaleoLogLocalService kaleoLogLocalService) {
		this.kaleoLogLocalService = kaleoLogLocalService;
	}

	public KaleoLogPersistence getKaleoLogPersistence() {
		return kaleoLogPersistence;
	}

	public void setKaleoLogPersistence(KaleoLogPersistence kaleoLogPersistence) {
		this.kaleoLogPersistence = kaleoLogPersistence;
	}

	public KaleoNodeLocalService getKaleoNodeLocalService() {
		return kaleoNodeLocalService;
	}

	public void setKaleoNodeLocalService(
		KaleoNodeLocalService kaleoNodeLocalService) {
		this.kaleoNodeLocalService = kaleoNodeLocalService;
	}

	public KaleoNodePersistence getKaleoNodePersistence() {
		return kaleoNodePersistence;
	}

	public void setKaleoNodePersistence(
		KaleoNodePersistence kaleoNodePersistence) {
		this.kaleoNodePersistence = kaleoNodePersistence;
	}

	public KaleoTaskLocalService getKaleoTaskLocalService() {
		return kaleoTaskLocalService;
	}

	public void setKaleoTaskLocalService(
		KaleoTaskLocalService kaleoTaskLocalService) {
		this.kaleoTaskLocalService = kaleoTaskLocalService;
	}

	public KaleoTaskPersistence getKaleoTaskPersistence() {
		return kaleoTaskPersistence;
	}

	public void setKaleoTaskPersistence(
		KaleoTaskPersistence kaleoTaskPersistence) {
		this.kaleoTaskPersistence = kaleoTaskPersistence;
	}

	public KaleoTaskAssignmentLocalService getKaleoTaskAssignmentLocalService() {
		return kaleoTaskAssignmentLocalService;
	}

	public void setKaleoTaskAssignmentLocalService(
		KaleoTaskAssignmentLocalService kaleoTaskAssignmentLocalService) {
		this.kaleoTaskAssignmentLocalService = kaleoTaskAssignmentLocalService;
	}

	public KaleoTaskAssignmentPersistence getKaleoTaskAssignmentPersistence() {
		return kaleoTaskAssignmentPersistence;
	}

	public void setKaleoTaskAssignmentPersistence(
		KaleoTaskAssignmentPersistence kaleoTaskAssignmentPersistence) {
		this.kaleoTaskAssignmentPersistence = kaleoTaskAssignmentPersistence;
	}

	public KaleoTaskInstanceAssignmentLocalService getKaleoTaskInstanceAssignmentLocalService() {
		return kaleoTaskInstanceAssignmentLocalService;
	}

	public void setKaleoTaskInstanceAssignmentLocalService(
		KaleoTaskInstanceAssignmentLocalService kaleoTaskInstanceAssignmentLocalService) {
		this.kaleoTaskInstanceAssignmentLocalService = kaleoTaskInstanceAssignmentLocalService;
	}

	public KaleoTaskInstanceAssignmentPersistence getKaleoTaskInstanceAssignmentPersistence() {
		return kaleoTaskInstanceAssignmentPersistence;
	}

	public void setKaleoTaskInstanceAssignmentPersistence(
		KaleoTaskInstanceAssignmentPersistence kaleoTaskInstanceAssignmentPersistence) {
		this.kaleoTaskInstanceAssignmentPersistence = kaleoTaskInstanceAssignmentPersistence;
	}

	public KaleoTaskInstanceTokenLocalService getKaleoTaskInstanceTokenLocalService() {
		return kaleoTaskInstanceTokenLocalService;
	}

	public void setKaleoTaskInstanceTokenLocalService(
		KaleoTaskInstanceTokenLocalService kaleoTaskInstanceTokenLocalService) {
		this.kaleoTaskInstanceTokenLocalService = kaleoTaskInstanceTokenLocalService;
	}

	public KaleoTaskInstanceTokenPersistence getKaleoTaskInstanceTokenPersistence() {
		return kaleoTaskInstanceTokenPersistence;
	}

	public void setKaleoTaskInstanceTokenPersistence(
		KaleoTaskInstanceTokenPersistence kaleoTaskInstanceTokenPersistence) {
		this.kaleoTaskInstanceTokenPersistence = kaleoTaskInstanceTokenPersistence;
	}

	public KaleoTransitionLocalService getKaleoTransitionLocalService() {
		return kaleoTransitionLocalService;
	}

	public void setKaleoTransitionLocalService(
		KaleoTransitionLocalService kaleoTransitionLocalService) {
		this.kaleoTransitionLocalService = kaleoTransitionLocalService;
	}

	public KaleoTransitionPersistence getKaleoTransitionPersistence() {
		return kaleoTransitionPersistence;
	}

	public void setKaleoTransitionPersistence(
		KaleoTransitionPersistence kaleoTransitionPersistence) {
		this.kaleoTransitionPersistence = kaleoTransitionPersistence;
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

	@BeanReference(name = "com.liferay.portal.workflow.kaleo.service.KaleoActionLocalService")
	protected KaleoActionLocalService kaleoActionLocalService;
	@BeanReference(name = "com.liferay.portal.workflow.kaleo.service.persistence.KaleoActionPersistence")
	protected KaleoActionPersistence kaleoActionPersistence;
	@BeanReference(name = "com.liferay.portal.workflow.kaleo.service.KaleoDefinitionLocalService")
	protected KaleoDefinitionLocalService kaleoDefinitionLocalService;
	@BeanReference(name = "com.liferay.portal.workflow.kaleo.service.persistence.KaleoDefinitionPersistence")
	protected KaleoDefinitionPersistence kaleoDefinitionPersistence;
	@BeanReference(name = "com.liferay.portal.workflow.kaleo.service.KaleoInstanceLocalService")
	protected KaleoInstanceLocalService kaleoInstanceLocalService;
	@BeanReference(name = "com.liferay.portal.workflow.kaleo.service.persistence.KaleoInstancePersistence")
	protected KaleoInstancePersistence kaleoInstancePersistence;
	@BeanReference(name = "com.liferay.portal.workflow.kaleo.service.KaleoInstanceTokenLocalService")
	protected KaleoInstanceTokenLocalService kaleoInstanceTokenLocalService;
	@BeanReference(name = "com.liferay.portal.workflow.kaleo.service.persistence.KaleoInstanceTokenPersistence")
	protected KaleoInstanceTokenPersistence kaleoInstanceTokenPersistence;
	@BeanReference(name = "com.liferay.portal.workflow.kaleo.service.KaleoLogLocalService")
	protected KaleoLogLocalService kaleoLogLocalService;
	@BeanReference(name = "com.liferay.portal.workflow.kaleo.service.persistence.KaleoLogPersistence")
	protected KaleoLogPersistence kaleoLogPersistence;
	@BeanReference(name = "com.liferay.portal.workflow.kaleo.service.KaleoNodeLocalService")
	protected KaleoNodeLocalService kaleoNodeLocalService;
	@BeanReference(name = "com.liferay.portal.workflow.kaleo.service.persistence.KaleoNodePersistence")
	protected KaleoNodePersistence kaleoNodePersistence;
	@BeanReference(name = "com.liferay.portal.workflow.kaleo.service.KaleoTaskLocalService")
	protected KaleoTaskLocalService kaleoTaskLocalService;
	@BeanReference(name = "com.liferay.portal.workflow.kaleo.service.persistence.KaleoTaskPersistence")
	protected KaleoTaskPersistence kaleoTaskPersistence;
	@BeanReference(name = "com.liferay.portal.workflow.kaleo.service.KaleoTaskAssignmentLocalService")
	protected KaleoTaskAssignmentLocalService kaleoTaskAssignmentLocalService;
	@BeanReference(name = "com.liferay.portal.workflow.kaleo.service.persistence.KaleoTaskAssignmentPersistence")
	protected KaleoTaskAssignmentPersistence kaleoTaskAssignmentPersistence;
	@BeanReference(name = "com.liferay.portal.workflow.kaleo.service.KaleoTaskInstanceAssignmentLocalService")
	protected KaleoTaskInstanceAssignmentLocalService kaleoTaskInstanceAssignmentLocalService;
	@BeanReference(name = "com.liferay.portal.workflow.kaleo.service.persistence.KaleoTaskInstanceAssignmentPersistence")
	protected KaleoTaskInstanceAssignmentPersistence kaleoTaskInstanceAssignmentPersistence;
	@BeanReference(name = "com.liferay.portal.workflow.kaleo.service.KaleoTaskInstanceTokenLocalService")
	protected KaleoTaskInstanceTokenLocalService kaleoTaskInstanceTokenLocalService;
	@BeanReference(name = "com.liferay.portal.workflow.kaleo.service.persistence.KaleoTaskInstanceTokenPersistence")
	protected KaleoTaskInstanceTokenPersistence kaleoTaskInstanceTokenPersistence;
	@BeanReference(name = "com.liferay.portal.workflow.kaleo.service.KaleoTransitionLocalService")
	protected KaleoTransitionLocalService kaleoTransitionLocalService;
	@BeanReference(name = "com.liferay.portal.workflow.kaleo.service.persistence.KaleoTransitionPersistence")
	protected KaleoTransitionPersistence kaleoTransitionPersistence;
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