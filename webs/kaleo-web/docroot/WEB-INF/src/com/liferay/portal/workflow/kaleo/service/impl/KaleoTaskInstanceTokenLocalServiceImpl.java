/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.service.base.KaleoTaskInstanceTokenLocalServiceBaseImpl;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoTaskInstanceTokenQuery;
import com.liferay.portal.workflow.kaleo.util.WorkflowContextUtil;
import com.liferay.portlet.exportimport.staging.StagingUtil;

import java.io.Serializable;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 * @author Marcellus Tavares
 */
public class KaleoTaskInstanceTokenLocalServiceImpl
	extends KaleoTaskInstanceTokenLocalServiceBaseImpl {

	@Override
	public KaleoTaskInstanceToken addKaleoTaskInstanceToken(
			long kaleoInstanceTokenId, long kaleoTaskId, String kaleoTaskName,
			Collection<KaleoTaskAssignment> kaleoTaskAssignments, Date dueDate,
			Map<String, Serializable> workflowContext,
			ServiceContext serviceContext)
		throws PortalException {

		KaleoInstanceToken kaleoInstanceToken =
			kaleoInstanceTokenPersistence.findByPrimaryKey(
				kaleoInstanceTokenId);

		User user = userPersistence.findByPrimaryKey(
			serviceContext.getGuestOrUserId());
		Date now = new Date();

		long kaleoTaskInstanceTokenId = counterLocalService.increment();

		KaleoTaskInstanceToken kaleoTaskInstanceToken =
			kaleoTaskInstanceTokenPersistence.create(kaleoTaskInstanceTokenId);

		long groupId = StagingUtil.getLiveGroupId(
			serviceContext.getScopeGroupId());

		kaleoTaskInstanceToken.setGroupId(groupId);

		kaleoTaskInstanceToken.setCompanyId(user.getCompanyId());
		kaleoTaskInstanceToken.setUserId(user.getUserId());
		kaleoTaskInstanceToken.setUserName(user.getFullName());
		kaleoTaskInstanceToken.setCreateDate(now);
		kaleoTaskInstanceToken.setModifiedDate(now);
		kaleoTaskInstanceToken.setDueDate(dueDate);
		kaleoTaskInstanceToken.setKaleoDefinitionId(
			kaleoInstanceToken.getKaleoDefinitionId());
		kaleoTaskInstanceToken.setKaleoInstanceId(
			kaleoInstanceToken.getKaleoInstanceId());
		kaleoTaskInstanceToken.setKaleoInstanceTokenId(kaleoInstanceTokenId);

		kaleoTaskInstanceToken.setKaleoTaskId(kaleoTaskId);
		kaleoTaskInstanceToken.setKaleoTaskName(kaleoTaskName);

		if (workflowContext != null) {
			kaleoTaskInstanceToken.setClassName(
				(String)workflowContext.get(
					WorkflowConstants.CONTEXT_ENTRY_CLASS_NAME));

			if (workflowContext.containsKey(
					WorkflowConstants.CONTEXT_ENTRY_CLASS_PK)) {

				kaleoTaskInstanceToken.setClassPK(
					GetterUtil.getLong(
						(String)workflowContext.get(
							WorkflowConstants.CONTEXT_ENTRY_CLASS_PK)));
			}
		}

		kaleoTaskInstanceToken.setCompleted(false);
		kaleoTaskInstanceToken.setWorkflowContext(
			WorkflowContextUtil.convert(workflowContext));

		kaleoTaskInstanceTokenPersistence.update(kaleoTaskInstanceToken);

		kaleoTaskAssignmentInstanceLocalService.addTaskAssignmentInstances(
			kaleoTaskInstanceToken, kaleoTaskAssignments, workflowContext,
			serviceContext);

		return kaleoTaskInstanceToken;
	}

	@Override
	public KaleoTaskInstanceToken assignKaleoTaskInstanceToken(
			long kaleoTaskInstanceTokenId, String assigneeClassName,
			long assigneeClassPK, Map<String, Serializable> workflowContext,
			ServiceContext serviceContext)
		throws PortalException {

		KaleoTaskInstanceToken kaleoTaskInstanceToken =
			kaleoTaskInstanceTokenPersistence.findByPrimaryKey(
				kaleoTaskInstanceTokenId);

		kaleoTaskInstanceToken.setModifiedDate(new Date());
		kaleoTaskInstanceToken.setWorkflowContext(
			WorkflowContextUtil.convert(workflowContext));

		kaleoTaskInstanceTokenPersistence.update(kaleoTaskInstanceToken);

		kaleoTaskAssignmentInstanceLocalService.
			assignKaleoTaskAssignmentInstance(
				kaleoTaskInstanceToken, assigneeClassName, assigneeClassPK,
				serviceContext);

		return kaleoTaskInstanceToken;
	}

	@Override
	public KaleoTaskInstanceToken completeKaleoTaskInstanceToken(
			long kaleoTaskInstanceTokenId, ServiceContext serviceContext)
		throws PortalException {

		// Kaleo task instance token

		KaleoTaskInstanceToken kaleoTaskInstanceToken =
			kaleoTaskInstanceTokenPersistence.findByPrimaryKey(
				kaleoTaskInstanceTokenId);

		kaleoTaskInstanceToken.setCompletionUserId(serviceContext.getUserId());
		kaleoTaskInstanceToken.setCompleted(true);
		kaleoTaskInstanceToken.setCompletionDate(new Date());

		kaleoTaskInstanceTokenPersistence.update(kaleoTaskInstanceToken);

		// Kaleo task assignment instance

		kaleoTaskAssignmentInstanceLocalService.completeKaleoTaskInstanceToken(
			kaleoTaskInstanceTokenId, serviceContext);

		// Kaleo timers

		kaleoTimerInstanceTokenLocalService.completeKaleoTimerInstanceTokens(
			kaleoTaskInstanceToken.getKaleoInstanceTokenId(), serviceContext);

		return kaleoTaskInstanceToken;
	}

	@Override
	public void deleteCompanyKaleoTaskInstanceTokens(long companyId) {

		// Kaleo task instance tokens

		kaleoTaskInstanceTokenPersistence.removeByCompanyId(companyId);

		// Kaleo task assignment instances

		kaleoTaskAssignmentInstanceLocalService.
			deleteCompanyKaleoTaskAssignmentInstances(companyId);
	}

	@Override
	public void deleteKaleoDefinitionKaleoTaskInstanceTokens(
		long kaleoDefinitionId) {

		// Kaleo task instance tokens

		kaleoTaskInstanceTokenPersistence.removeByKaleoDefinitionId(
			kaleoDefinitionId);

		// Kaleo task assignment instances

		kaleoTaskAssignmentInstanceLocalService.
			deleteKaleoDefinitionKaleoTaskAssignmentInstances(
				kaleoDefinitionId);
	}

	@Override
	public void deleteKaleoInstanceKaleoTaskInstanceTokens(
		long kaleoInstanceId) {

		// Kaleo task instance tokens

		kaleoTaskInstanceTokenPersistence.removeByKaleoInstanceId(
			kaleoInstanceId);

		// Kaleo task assignment instances

		kaleoTaskAssignmentInstanceLocalService.
			deleteKaleoInstanceKaleoTaskAssignmentInstances(kaleoInstanceId);
	}

	@Override
	public List<KaleoTaskInstanceToken> getCompanyKaleoTaskInstanceTokens(
		long companyId, int start, int end) {

		return kaleoTaskInstanceTokenPersistence.findByCompanyId(
			companyId, start, end);
	}

	@Override
	public int getCompanyKaleoTaskInstanceTokensCount(long companyId) {
		return kaleoTaskInstanceTokenPersistence.countByCompanyId(companyId);
	}

	@Override
	public List<KaleoTaskInstanceToken> getKaleoTaskInstanceTokens(
		Boolean completed, int start, int end,
		OrderByComparator<KaleoTaskInstanceToken> orderByComparator,
		ServiceContext serviceContext) {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			completed, serviceContext);

		return dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	@Override
	public List<KaleoTaskInstanceToken> getKaleoTaskInstanceTokens(
		List<Long> roleIds, Boolean completed, int start, int end,
		OrderByComparator<KaleoTaskInstanceToken> orderByComparator,
		ServiceContext serviceContext) {

		KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery =
			new KaleoTaskInstanceTokenQuery(serviceContext);

		kaleoTaskInstanceTokenQuery.setCompleted(completed);
		kaleoTaskInstanceTokenQuery.setEnd(end);
		kaleoTaskInstanceTokenQuery.setOrderByComparator(orderByComparator);
		kaleoTaskInstanceTokenQuery.setRoleIds(roleIds);
		kaleoTaskInstanceTokenQuery.setStart(start);

		return kaleoTaskInstanceTokenFinder.findKaleoTaskInstanceTokens(
			kaleoTaskInstanceTokenQuery);
	}

	@Override
	public List<KaleoTaskInstanceToken> getKaleoTaskInstanceTokens(
		long kaleoInstanceId, Boolean completed, int start, int end,
		OrderByComparator<KaleoTaskInstanceToken> orderByComparator,
		ServiceContext serviceContext) {

		long userId = serviceContext.getUserId();

		if (userId == 0) {
			DynamicQuery dynamicQuery = buildDynamicQuery(
				kaleoInstanceId, completed, serviceContext);

			return dynamicQuery(dynamicQuery, start, end, orderByComparator);
		}

		KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery =
			new KaleoTaskInstanceTokenQuery(serviceContext);

		kaleoTaskInstanceTokenQuery.setAssigneeClassName(User.class.getName());
		kaleoTaskInstanceTokenQuery.setAssigneeClassPK(
			serviceContext.getUserId());
		kaleoTaskInstanceTokenQuery.setCompleted(completed);
		kaleoTaskInstanceTokenQuery.setEnd(end);
		kaleoTaskInstanceTokenQuery.setKaleoInstanceId(kaleoInstanceId);
		kaleoTaskInstanceTokenQuery.setOrderByComparator(orderByComparator);
		kaleoTaskInstanceTokenQuery.setStart(start);

		return kaleoTaskInstanceTokenFinder.findKaleoTaskInstanceTokens(
			kaleoTaskInstanceTokenQuery);
	}

	@Override
	public KaleoTaskInstanceToken getKaleoTaskInstanceTokens(
			long kaleoInstanceId, long kaleoTaskId)
		throws PortalException {

		return kaleoTaskInstanceTokenPersistence.findByKII_KTI(
			kaleoInstanceId, kaleoTaskId);
	}

	@Override
	public List<KaleoTaskInstanceToken> getKaleoTaskInstanceTokens(
		String assigneeClassName, long assigneeClassPK, Boolean completed,
		int start, int end,
		OrderByComparator<KaleoTaskInstanceToken> orderByComparator,
		ServiceContext serviceContext) {

		KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery =
			new KaleoTaskInstanceTokenQuery(serviceContext);

		kaleoTaskInstanceTokenQuery.setAssigneeClassName(assigneeClassName);
		kaleoTaskInstanceTokenQuery.setAssigneeClassPK(assigneeClassPK);
		kaleoTaskInstanceTokenQuery.setCompleted(completed);
		kaleoTaskInstanceTokenQuery.setEnd(end);
		kaleoTaskInstanceTokenQuery.setOrderByComparator(orderByComparator);
		kaleoTaskInstanceTokenQuery.setStart(start);

		return kaleoTaskInstanceTokenFinder.findKaleoTaskInstanceTokens(
			kaleoTaskInstanceTokenQuery);
	}

	@Override
	public int getKaleoTaskInstanceTokensCount(
		Boolean completed, ServiceContext serviceContext) {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			completed, serviceContext);

		return (int)dynamicQueryCount(dynamicQuery);
	}

	@Override
	public int getKaleoTaskInstanceTokensCount(
		List<Long> roleIds, Boolean completed, ServiceContext serviceContext) {

		KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery =
			new KaleoTaskInstanceTokenQuery(serviceContext);

		kaleoTaskInstanceTokenQuery.setCompleted(completed);
		kaleoTaskInstanceTokenQuery.setAssigneeClassName(Role.class.getName());

		kaleoTaskInstanceTokenQuery.setRoleIds(roleIds);

		return kaleoTaskInstanceTokenFinder.countKaleoTaskInstanceTokens(
			kaleoTaskInstanceTokenQuery);
	}

	@Override
	public int getKaleoTaskInstanceTokensCount(
		long kaleoInstanceId, Boolean completed,
		ServiceContext serviceContext) {

		long userId = serviceContext.getUserId();

		if (userId == 0) {
			DynamicQuery dynamicQuery = buildDynamicQuery(
				kaleoInstanceId, completed, serviceContext);

			return (int)dynamicQueryCount(dynamicQuery);
		}

		KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery =
			new KaleoTaskInstanceTokenQuery(serviceContext);

		kaleoTaskInstanceTokenQuery.setAssigneeClassName(User.class.getName());
		kaleoTaskInstanceTokenQuery.setAssigneeClassPK(
			serviceContext.getUserId());
		kaleoTaskInstanceTokenQuery.setCompleted(completed);

		return kaleoTaskInstanceTokenFinder.countKaleoTaskInstanceTokens(
			kaleoTaskInstanceTokenQuery);
	}

	@Override
	public int getKaleoTaskInstanceTokensCount(
		String assigneeClassName, long assigneeClassPK, Boolean completed,
		ServiceContext serviceContext) {

		KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery =
			new KaleoTaskInstanceTokenQuery(serviceContext);

		kaleoTaskInstanceTokenQuery.setAssigneeClassName(assigneeClassName);
		kaleoTaskInstanceTokenQuery.setAssigneeClassPK(assigneeClassPK);
		kaleoTaskInstanceTokenQuery.setCompleted(completed);

		return kaleoTaskInstanceTokenFinder.countKaleoTaskInstanceTokens(
			kaleoTaskInstanceTokenQuery);
	}

	@Override
	public List<KaleoTaskInstanceToken>
		getSubmittingUserKaleoTaskInstanceTokens(
			long userId, Boolean completed, int start, int end,
			OrderByComparator<KaleoTaskInstanceToken> orderByComparator,
			ServiceContext serviceContext) {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			KaleoTaskInstanceToken.class, getClassLoader());

		Property companyIdProperty = PropertyFactoryUtil.forName("companyId");

		dynamicQuery.add(companyIdProperty.eq(serviceContext.getCompanyId()));

		Property workflowContextProperty = PropertyFactoryUtil.forName(
			"workflowContext");

		dynamicQuery.add(
			workflowContextProperty.like("%\"userId\":\"" + userId + "\"%"));

		addCompletedCriterion(dynamicQuery, completed);

		return dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	@Override
	public int getSubmittingUserKaleoTaskInstanceTokensCount(
		long userId, Boolean completed, ServiceContext serviceContext) {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			KaleoTaskInstanceToken.class, getClassLoader());

		Property companyIdProperty = PropertyFactoryUtil.forName("companyId");

		dynamicQuery.add(companyIdProperty.eq(serviceContext.getCompanyId()));

		Property workflowContextProperty = PropertyFactoryUtil.forName(
			"workflowContext");

		dynamicQuery.add(
			workflowContextProperty.like("%\"userId\":\"" + userId + "\"%"));

		addCompletedCriterion(dynamicQuery, completed);

		return (int)dynamicQueryCount(dynamicQuery);
	}

	@Override
	public List<KaleoTaskInstanceToken> search(
		String keywords, Boolean completed, Boolean searchByUserRoles,
		int start, int end,
		OrderByComparator<KaleoTaskInstanceToken> orderByComparator,
		ServiceContext serviceContext) {

		return search(
			keywords, keywords, null, null, null, completed, searchByUserRoles,
			false, start, end, orderByComparator, serviceContext);
	}

	@Override
	public List<KaleoTaskInstanceToken> search(
		String taskName, String assetType, Long[] assetPrimaryKeys,
		Date dueDateGT, Date dueDateLT, Boolean completed,
		Boolean searchByUserRoles, boolean andOperator, int start, int end,
		OrderByComparator<KaleoTaskInstanceToken> orderByComparator,
		ServiceContext serviceContext) {

		return search(
			taskName, getAssetTypes(assetType), assetPrimaryKeys, dueDateGT,
			dueDateLT, completed, searchByUserRoles, andOperator, start, end,
			orderByComparator, serviceContext);
	}

	@Override
	public List<KaleoTaskInstanceToken> search(
		String keywords, String[] assetTypes, Boolean completed,
		Boolean searchByUserRoles, int start, int end,
		OrderByComparator<KaleoTaskInstanceToken> orderByComparator,
		ServiceContext serviceContext) {

		return search(
			keywords, assetTypes, null, null, null, completed,
			searchByUserRoles, true, start, end, orderByComparator,
			serviceContext);
	}

	@Override
	public List<KaleoTaskInstanceToken> search(
		String taskName, String[] assetTypes, Long[] assetPrimaryKeys,
		Date dueDateGT, Date dueDateLT, Boolean completed,
		Boolean searchByUserRoles, boolean andOperator, int start, int end,
		OrderByComparator<KaleoTaskInstanceToken> orderByComparator,
		ServiceContext serviceContext) {

		KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery =
			new KaleoTaskInstanceTokenQuery(serviceContext);

		kaleoTaskInstanceTokenQuery.setAssetPrimaryKeys(assetPrimaryKeys);
		kaleoTaskInstanceTokenQuery.setAssetTypes(assetTypes);
		kaleoTaskInstanceTokenQuery.setCompleted(completed);
		kaleoTaskInstanceTokenQuery.setDueDateGT(dueDateGT);
		kaleoTaskInstanceTokenQuery.setDueDateLT(dueDateLT);
		kaleoTaskInstanceTokenQuery.setEnd(end);
		kaleoTaskInstanceTokenQuery.setOrderByComparator(orderByComparator);
		kaleoTaskInstanceTokenQuery.setSearchByUserRoles(searchByUserRoles);
		kaleoTaskInstanceTokenQuery.setStart(start);
		kaleoTaskInstanceTokenQuery.setTaskName(taskName);
		kaleoTaskInstanceTokenQuery.setAndOperator(andOperator);

		return kaleoTaskInstanceTokenFinder.findKaleoTaskInstanceTokens(
			kaleoTaskInstanceTokenQuery);
	}

	@Override
	public int searchCount(
		String keywords, Boolean completed, Boolean searchByUserRoles,
		ServiceContext serviceContext) {

		return searchCount(
			keywords, keywords, null, null, null, completed, searchByUserRoles,
			false, serviceContext);
	}

	@Override
	public int searchCount(
		String taskName, String assetType, Long[] assetPrimaryKeys,
		Date dueDateGT, Date dueDateLT, Boolean completed,
		Boolean searchByUserRoles, boolean andOperator,
		ServiceContext serviceContext) {

		return searchCount(
			taskName, getAssetTypes(assetType), assetPrimaryKeys, dueDateGT,
			dueDateLT, completed, searchByUserRoles, andOperator,
			serviceContext);
	}

	@Override
	public int searchCount(
		String keywords, String[] assetTypes, Boolean completed,
		Boolean searchByUserRoles, ServiceContext serviceContext) {

		return searchCount(
			keywords, assetTypes, null, null, null, completed,
			searchByUserRoles, true, serviceContext);
	}

	@Override
	public int searchCount(
		String taskName, String[] assetTypes, Long[] assetPrimaryKeys,
		Date dueDateGT, Date dueDateLT, Boolean completed,
		Boolean searchByUserRoles, boolean andOperator,
		ServiceContext serviceContext) {

		KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery =
			new KaleoTaskInstanceTokenQuery(serviceContext);

		kaleoTaskInstanceTokenQuery.setAssetPrimaryKeys(assetPrimaryKeys);
		kaleoTaskInstanceTokenQuery.setAssetTypes(assetTypes);
		kaleoTaskInstanceTokenQuery.setCompleted(completed);
		kaleoTaskInstanceTokenQuery.setDueDateGT(dueDateGT);
		kaleoTaskInstanceTokenQuery.setDueDateLT(dueDateLT);
		kaleoTaskInstanceTokenQuery.setSearchByUserRoles(searchByUserRoles);
		kaleoTaskInstanceTokenQuery.setTaskName(taskName);
		kaleoTaskInstanceTokenQuery.setAndOperator(andOperator);

		return kaleoTaskInstanceTokenFinder.countKaleoTaskInstanceTokens(
			kaleoTaskInstanceTokenQuery);
	}

	@Override
	public KaleoTaskInstanceToken updateDueDate(
			long kaleoTaskInstanceTokenId, Date dueDate,
			ServiceContext serviceContext)
		throws PortalException {

		KaleoTaskInstanceToken kaleoTaskInstance =
			kaleoTaskInstanceTokenPersistence.findByPrimaryKey(
				kaleoTaskInstanceTokenId);

		kaleoTaskInstance.setModifiedDate(new Date());

		if (dueDate != null) {
			Calendar cal = CalendarFactoryUtil.getCalendar(
				LocaleUtil.getDefault());

			cal.setTime(dueDate);

			kaleoTaskInstance.setDueDate(cal.getTime());
		}

		kaleoTaskInstanceTokenPersistence.update(kaleoTaskInstance);

		return kaleoTaskInstance;
	}

	protected void addCompletedCriterion(
		DynamicQuery dynamicQuery, Boolean completed) {

		if (completed == null) {
			return;
		}

		Property property = PropertyFactoryUtil.forName("completed");

		dynamicQuery.add(property.eq(completed));
	}

	protected DynamicQuery buildDynamicQuery(
		Boolean completed, ServiceContext serviceContext) {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			KaleoTaskInstanceToken.class, getClassLoader());

		Property property = PropertyFactoryUtil.forName("companyId");

		dynamicQuery.add(property.eq(serviceContext.getCompanyId()));

		addCompletedCriterion(dynamicQuery, completed);

		return dynamicQuery;
	}

	protected DynamicQuery buildDynamicQuery(
		long kaleoInstanceId, Boolean completed,
		ServiceContext serviceContext) {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			KaleoTaskInstanceToken.class, getClassLoader());

		Property companyIdProperty = PropertyFactoryUtil.forName("companyId");

		dynamicQuery.add(companyIdProperty.eq(serviceContext.getCompanyId()));

		Property kaleoInstanceIdProperty = PropertyFactoryUtil.forName(
			"kaleoInstanceId");

		dynamicQuery.add(kaleoInstanceIdProperty.eq(kaleoInstanceId));

		addCompletedCriterion(dynamicQuery, completed);

		return dynamicQuery;
	}

	protected String[] getAssetTypes(String assetType) {
		if (Validator.isNull(assetType)) {
			return null;
		}

		return new String[] {assetType};
	}

}