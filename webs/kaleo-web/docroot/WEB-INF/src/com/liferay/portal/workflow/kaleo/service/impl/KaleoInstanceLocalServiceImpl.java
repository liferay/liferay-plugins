/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.workflow.kaleo.service.impl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.NoSuchInstanceException;
import com.liferay.portal.workflow.kaleo.model.KaleoInstance;
import com.liferay.portal.workflow.kaleo.service.base.KaleoInstanceLocalServiceBaseImpl;
import com.liferay.portal.workflow.kaleo.util.GroupUtil;
import com.liferay.portal.workflow.kaleo.util.WorkflowContextUtil;

import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class KaleoInstanceLocalServiceImpl
	extends KaleoInstanceLocalServiceBaseImpl {

	public KaleoInstance addKaleoInstance(
			long kaleoDefinitionId, String kaleoDefinitionName,
			int kaleoDefinitionVersion,
			Map<String, Serializable> workflowContext,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(
			serviceContext.getUserId());
		Date now = new Date();

		long kaleoInstanceId = counterLocalService.increment();

		KaleoInstance kaleoInstance =
			kaleoInstancePersistence.create(kaleoInstanceId);

		long groupId = GroupUtil.getGroupId(serviceContext);

		kaleoInstance.setGroupId(groupId);

		kaleoInstance.setCompanyId(user.getCompanyId());
		kaleoInstance.setUserId(user.getUserId());
		kaleoInstance.setUserName(user.getFullName());
		kaleoInstance.setCreateDate(now);
		kaleoInstance.setModifiedDate(now);
		kaleoInstance.setKaleoDefinitionId(kaleoDefinitionId);
		kaleoInstance.setKaleoDefinitionName(kaleoDefinitionName);
		kaleoInstance.setKaleoDefinitionVersion(kaleoDefinitionVersion);
		kaleoInstance.setClassName(
			(String)workflowContext.get(
				WorkflowConstants.CONTEXT_ENTRY_CLASS_NAME));

		if (workflowContext.containsKey(
				WorkflowConstants.CONTEXT_ENTRY_CLASS_PK)) {

			kaleoInstance.setClassPK(
				GetterUtil.getLong(
					(String)workflowContext.get(
						WorkflowConstants.CONTEXT_ENTRY_CLASS_PK)));
		}

		kaleoInstance.setCompleted(false);
		kaleoInstance.setWorkflowContext(
			WorkflowContextUtil.convert(workflowContext));

		kaleoInstancePersistence.update(kaleoInstance, false);

		return kaleoInstance;
	}

	public KaleoInstance completeKaleoInstance(long kaleoInstanceId)
		throws PortalException, SystemException {

		KaleoInstance kaleoInstance = kaleoInstancePersistence.findByPrimaryKey(
			kaleoInstanceId);

		kaleoInstance.setCompleted(true);
		kaleoInstance.setCompletionDate(new Date());

		kaleoInstancePersistence.update(kaleoInstance, false);

		return kaleoInstance;
	}

	public void deleteCompanyKaleoInstances(long companyId)
		throws SystemException {

		// Kaleo instances

		kaleoInstancePersistence.removeByCompanyId(companyId);

		// Kaleo instance tokens

		kaleoInstanceTokenLocalService.
			deleteKaleoDefinitionKaleoInstanceTokens(companyId);

		// Kaleo logs

		kaleoLogLocalService.deleteKaleoDefinitionKaleoLogs(companyId);

		// Kaleo task instance tokens

		kaleoTaskInstanceTokenLocalService.
			deleteKaleoDefinitionKaleoTaskInstanceTokens(companyId);
	}

	public void deleteKaleoDefinitionKaleoInstances(long kaleoDefinitionId)
		throws SystemException {

		// Kaleo instances

		kaleoInstancePersistence.removeByKaleoDefinitionId(kaleoDefinitionId);

		// Kaleo instance tokens

		kaleoInstanceTokenLocalService.
			deleteKaleoDefinitionKaleoInstanceTokens(kaleoDefinitionId);

		// Kaleo logs

		kaleoLogLocalService.deleteKaleoDefinitionKaleoLogs(kaleoDefinitionId);

		// Kaleo task instance tokens

		kaleoTaskInstanceTokenLocalService.
			deleteKaleoDefinitionKaleoTaskInstanceTokens(kaleoDefinitionId);
	}

	@Override
	public void deleteKaleoInstance(long kaleoInstanceId)
		throws SystemException {

		try {
			kaleoInstancePersistence.remove(kaleoInstanceId);
		}
		catch (NoSuchInstanceException nsie) {
			return;
		}

		// Kaleo instance tokens

		kaleoInstanceTokenLocalService.deleteKaleoInstanceKaleoInstanceTokens(
			kaleoInstanceId);

		// Kaleo logs

		kaleoLogLocalService.deleteKaleoInstanceKaleoLogs(kaleoInstanceId);

		// Kaleo task instance tokens

		kaleoTaskInstanceTokenLocalService.
			deleteKaleoInstanceKaleoTaskInstanceTokens(kaleoInstanceId);

		// Kaleo timer instance tokens

		kaleoTimerInstanceTokenLocalService.deleteKaleoTimerInstanceTokens(
			kaleoInstanceId);
	}

	public List<KaleoInstance> getKaleoInstances(
			Long userId, String assetClassName, Long assetClassPK,
			Boolean completed, int start, int end,
			OrderByComparator orderByComparator, ServiceContext serviceContext)
		throws SystemException {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			userId, assetClassName, assetClassPK, completed, serviceContext);

		return dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	public List<KaleoInstance> getKaleoInstances(
			String kaleoDefinitionName, int kaleoDefinitionVersion,
			boolean completed, int start, int end,
			OrderByComparator orderByComparator, ServiceContext serviceContext)
		throws SystemException {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			kaleoDefinitionName, kaleoDefinitionVersion, completed,
			serviceContext);

		return dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	public int getKaleoInstancesCount(long kaleoDefinitionId, boolean completed)
		throws SystemException {

		return kaleoInstancePersistence.countByKDI_C(
			kaleoDefinitionId, completed);
	}

	public int getKaleoInstancesCount(
			Long userId, String assetClassName, Long assetClassPK,
			Boolean completed, ServiceContext serviceContext)
		throws SystemException {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			userId, assetClassName, assetClassPK, completed, serviceContext);

		return (int)dynamicQueryCount(dynamicQuery);
	}

	public int getKaleoInstancesCount(
			String kaleoDefinitionName, int kaleoDefinitionVersion,
			boolean completed, ServiceContext serviceContext)
		throws SystemException {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			kaleoDefinitionName, kaleoDefinitionVersion, completed,
			serviceContext);

		return (int)dynamicQueryCount(dynamicQuery);
	}

	public KaleoInstance updateKaleoInstance(
			long kaleoInstanceId, Map<String, Serializable> workflowContext,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		KaleoInstance kaleoInstance = kaleoInstancePersistence.findByPrimaryKey(
			kaleoInstanceId);

		kaleoInstance.setWorkflowContext(
			WorkflowContextUtil.convert(workflowContext));

		return kaleoInstance;
	}

	protected DynamicQuery buildDynamicQuery(
		Long userId, String assetClassName, Long assetClassPK,
		Boolean completed, ServiceContext serviceContext) {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			KaleoInstance.class, getClass().getClassLoader());

		dynamicQuery.add(
			PropertyFactoryUtil.forName("companyId").eq(
				serviceContext.getCompanyId()));

		if (userId != null) {
			dynamicQuery.add(
				PropertyFactoryUtil.forName("userId").eq(userId));
		}

		if (Validator.isNotNull(assetClassName)) {
			dynamicQuery.add(
				PropertyFactoryUtil.forName("className").like(assetClassName));
		}

		if (Validator.isNotNull(assetClassPK)) {
			dynamicQuery.add(
				PropertyFactoryUtil.forName("classPK").eq(assetClassPK));
		}

		if (completed != null) {
			if (completed) {
				dynamicQuery.add(
					PropertyFactoryUtil.forName("completionDate").isNotNull());
			}
			else {
				dynamicQuery.add(
					PropertyFactoryUtil.forName("completionDate").isNull());
			}
		}

		return dynamicQuery;
	}

	protected DynamicQuery buildDynamicQuery(
		String kaleoDefinitionName, int kaleoDefinitionVersion,
		boolean completed, ServiceContext serviceContext) {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			KaleoInstance.class, getClass().getClassLoader());

		dynamicQuery.add(
			PropertyFactoryUtil.forName("companyId").eq(
				serviceContext.getCompanyId()));
		dynamicQuery.add(
			PropertyFactoryUtil.forName("kaleoDefinitionName").eq(
				kaleoDefinitionName));
		dynamicQuery.add(
			PropertyFactoryUtil.forName("kaleoDefinitionVersion").eq(
				kaleoDefinitionVersion));

		if (completed) {
			dynamicQuery.add(
				PropertyFactoryUtil.forName("completionDate").isNotNull());
		}
		else {
			dynamicQuery.add(
				PropertyFactoryUtil.forName("completionDate").isNull());
		}

		return dynamicQuery;
	}

}