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

package com.liferay.portal.workflow.kaleo.service.impl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Junction;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroupRole;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.service.base.KaleoTaskInstanceTokenLocalServiceBaseImpl;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoTaskInstanceTokenQuery;
import com.liferay.portal.workflow.kaleo.util.RoleRetrievalUtil;
import com.liferay.portal.workflow.kaleo.util.WorkflowContextUtil;

import java.io.Serializable;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <a href="KaleoTaskInstanceTokenLocalServiceImpl.java.html"><b><i>View Source
 * </i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class KaleoTaskInstanceTokenLocalServiceImpl
	extends KaleoTaskInstanceTokenLocalServiceBaseImpl {

	public KaleoTaskInstanceToken addKaleoTaskInstanceToken(
			long kaleoInstanceTokenId, long kaleoTaskId, String kaleoTaskName,
			Collection<KaleoTaskAssignment> kaleoTaskAssignments, Date dueDate,
			Map<String, Serializable> workflowContext,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		KaleoInstanceToken kaleoInstanceToken =
			kaleoInstanceTokenPersistence.findByPrimaryKey(
				kaleoInstanceTokenId);
		User user = userPersistence.findByPrimaryKey(
			serviceContext.getUserId());
		Date now = new Date();

		long kaleoTaskInstanceTokenId = counterLocalService.increment();

		KaleoTaskInstanceToken kaleoTaskInstanceToken =
			kaleoTaskInstanceTokenPersistence.create(kaleoTaskInstanceTokenId);

		kaleoTaskInstanceToken.setGroupId(serviceContext.getScopeGroupId());
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

		KaleoInstanceToken childKaleoInstanceToken =
			kaleoInstanceTokenLocalService.addKaleoInstanceToken(
				kaleoInstanceToken.getKaleoInstanceTokenId(), workflowContext,
				serviceContext);

		kaleoTaskInstanceToken.setKaleoInstanceTokenId(
			childKaleoInstanceToken.getKaleoInstanceTokenId());

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

		kaleoTaskInstanceTokenPersistence.update(kaleoTaskInstanceToken, false);

		kaleoTaskAssignmentInstanceLocalService.addTaskAssignmentInstances(
			kaleoTaskInstanceToken, kaleoTaskAssignments, serviceContext);

		return kaleoTaskInstanceToken;
	}

	public KaleoTaskInstanceToken assignKaleoTaskInstanceToken(
			long kaleoTaskInstanceTokenId, String assigneeClassName,
			long assigneeClassPK, Map<String, Serializable> workflowContext,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		KaleoTaskInstanceToken kaleoTaskInstanceToken =
			kaleoTaskInstanceTokenPersistence.findByPrimaryKey(
				kaleoTaskInstanceTokenId);

		kaleoTaskInstanceToken.setModifiedDate(new Date());
		kaleoTaskInstanceToken.setWorkflowContext(
			WorkflowContextUtil.convert(workflowContext));

		kaleoTaskInstanceTokenPersistence.update(kaleoTaskInstanceToken, false);

		kaleoTaskAssignmentInstanceLocalService.
			assignKaleoTaskAssignmentInstance(
				kaleoTaskInstanceToken, assigneeClassName,
				assigneeClassPK, serviceContext);

		return kaleoTaskInstanceToken;
	}

	public KaleoTaskInstanceToken completeKaleoTaskInstanceToken(
			long kaleoTaskInstanceTokenId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Kaleo task instance token

		KaleoTaskInstanceToken kaleoTaskInstanceToken =
			kaleoTaskInstanceTokenPersistence.findByPrimaryKey(
				kaleoTaskInstanceTokenId);

		kaleoTaskInstanceToken.setCompletionUserId(serviceContext.getUserId());
		kaleoTaskInstanceToken.setCompleted(true);
		kaleoTaskInstanceToken.setCompletionDate(new Date());

		kaleoTaskInstanceTokenPersistence.update(kaleoTaskInstanceToken, false);

		// Kaleo task assignment instance

		kaleoTaskAssignmentInstanceLocalService.completeKaleoTaskInstanceToken(
			kaleoTaskInstanceTokenId, serviceContext);

		// Kaleo instance

		kaleoInstanceTokenLocalService.completeKaleoInstanceToken(
			kaleoTaskInstanceToken.getKaleoInstanceTokenId());

		return kaleoTaskInstanceToken;
	}

	public void deleteCompanyKaleoTaskInstanceTokens(long companyId)
		throws SystemException {

		// Kaleo task instance tokens

		kaleoTaskInstanceTokenPersistence.removeByCompanyId(companyId);

		// Kaleo task assignment instances

		kaleoTaskAssignmentInstanceLocalService.
			deleteCompanyKaleoTaskAssignmentInstances(companyId);
	}

	public void deleteKaleoDefinitionKaleoTaskInstanceTokens(
			long kaleoDefinitionId)
		throws SystemException {

		// Kaleo task instance tokens

		kaleoTaskInstanceTokenPersistence.removeByKaleoDefinitionId(
			kaleoDefinitionId);

		// Kaleo task assignment instances

		kaleoTaskAssignmentInstanceLocalService.
			deleteKaleoDefinitionKaleoTaskAssignmentInstances(
				kaleoDefinitionId);
	}

	public void deleteKaleoInstanceKaleoTaskInstanceTokens(long kaleoInstanceId)
		throws SystemException {

		// Kaleo task instance tokens

		kaleoTaskInstanceTokenPersistence.removeByKaleoInstanceId(
			kaleoInstanceId);

		// Kaleo task assignment instances

		kaleoTaskAssignmentInstanceLocalService.
			deleteKaleoInstanceKaleoTaskAssignmentInstances(kaleoInstanceId);
	}

	public List<KaleoTaskInstanceToken> getCompanyKaleoTaskInstanceTokens(
			long companyId, int start, int end)
		throws SystemException {

		return kaleoTaskInstanceTokenPersistence.findByCompanyId(
			companyId, start, end);
	}

	public int getCompanyKaleoTaskInstanceTokensCount(long companyId)
		throws SystemException {

		return kaleoTaskInstanceTokenPersistence.countByCompanyId(companyId);
	}

	public List<KaleoTaskInstanceToken> getKaleoTaskInstanceTokens(
			Boolean completed, int start, int end,
			OrderByComparator orderByComparator, ServiceContext serviceContext)
		throws SystemException {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			completed, serviceContext);

		return dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	public List<KaleoTaskInstanceToken> getKaleoTaskInstanceTokens(
			List<Long> roleIds, Boolean completed, int start, int end,
			OrderByComparator orderByComparator, ServiceContext serviceContext)
		throws SystemException {

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

	public List<KaleoTaskInstanceToken> getKaleoTaskInstanceTokens(
			long kaleoInstanceId, Boolean completed, int start, int end,
			OrderByComparator orderByComparator, ServiceContext serviceContext)
		throws SystemException {

		long userId = serviceContext.getUserId();

		if (userId == 0) {
			DynamicQuery dynamicQuery = buildDynamicQuery(
				kaleoInstanceId, completed, serviceContext);

			return dynamicQuery(dynamicQuery, start, end, orderByComparator);
		}
		else {
			KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery =
				new KaleoTaskInstanceTokenQuery(serviceContext);

			kaleoTaskInstanceTokenQuery.setAssigneeClassName(
				User.class.getName());
			kaleoTaskInstanceTokenQuery.setAssigneeClassPK(
				serviceContext.getUserId());
			kaleoTaskInstanceTokenQuery.setCompleted(completed);
			kaleoTaskInstanceTokenQuery.setEnd(start);
			kaleoTaskInstanceTokenQuery.setOrderByComparator(orderByComparator);
			kaleoTaskInstanceTokenQuery.setStart(start);

			return kaleoTaskInstanceTokenFinder.findKaleoTaskInstanceTokens(
				kaleoTaskInstanceTokenQuery);
		}
	}

	public List<KaleoTaskInstanceToken> getKaleoTaskInstanceTokens(
			String assigneeClassName, long assigneeClassPK, Boolean completed,
			int start, int end, OrderByComparator orderByComparator,
			ServiceContext serviceContext)
		throws SystemException {

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

	public int getKaleoTaskInstanceTokensCount(
			Boolean completed, ServiceContext serviceContext)
		throws SystemException {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			completed, serviceContext);

		return (int)dynamicQueryCount(dynamicQuery);
	}

	public int getKaleoTaskInstanceTokensCount(
			List<Long> roleIds, Boolean completed,
			ServiceContext serviceContext)
		throws SystemException {

		KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery =
			new KaleoTaskInstanceTokenQuery(serviceContext);

		kaleoTaskInstanceTokenQuery.setCompleted(completed);
		kaleoTaskInstanceTokenQuery.setAssigneeClassName(Role.class.getName());

		kaleoTaskInstanceTokenQuery.setRoleIds(roleIds);

		return kaleoTaskInstanceTokenFinder.countKaleoTaskInstanceTokens(
			kaleoTaskInstanceTokenQuery);
	}

	public int getKaleoTaskInstanceTokensCount(
			long kaleoInstanceId, Boolean completed,
			ServiceContext serviceContext)
		throws SystemException {

		long userId = serviceContext.getUserId();

		if (userId == 0) {
			DynamicQuery dynamicQuery = buildDynamicQuery(
				kaleoInstanceId, completed, serviceContext);

			return (int)dynamicQueryCount(dynamicQuery);
		}
		else {
			KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery =
				new KaleoTaskInstanceTokenQuery(serviceContext);

			kaleoTaskInstanceTokenQuery.setAssigneeClassName(
				User.class.getName());
			kaleoTaskInstanceTokenQuery.setAssigneeClassPK(
				serviceContext.getUserId());
			kaleoTaskInstanceTokenQuery.setCompleted(completed);

			return kaleoTaskInstanceTokenFinder.countKaleoTaskInstanceTokens(
				kaleoTaskInstanceTokenQuery);
		}
	}

	public int getKaleoTaskInstanceTokensCount(
			String assigneeClassName, long assigneeClassPK,
			Boolean completed, ServiceContext serviceContext)
		throws SystemException {

		KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery =
			new KaleoTaskInstanceTokenQuery(serviceContext);

		kaleoTaskInstanceTokenQuery.setAssigneeClassName(assigneeClassName);
		kaleoTaskInstanceTokenQuery.setAssigneeClassPK(assigneeClassPK);
		kaleoTaskInstanceTokenQuery.setCompleted(completed);

		return kaleoTaskInstanceTokenFinder.countKaleoTaskInstanceTokens(
			kaleoTaskInstanceTokenQuery);
	}

	public List<KaleoTaskInstanceToken>
			getSubmittingUserKaleoTaskInstanceTokens(
				long userId, Boolean completed, int start, int end,
				OrderByComparator orderByComparator,
				ServiceContext serviceContext)
		throws SystemException {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			KaleoTaskInstanceToken.class, getClass().getClassLoader());

		dynamicQuery.add(
			PropertyFactoryUtil.forName("companyId").eq(
				serviceContext.getCompanyId()));
		dynamicQuery.add(
			PropertyFactoryUtil.forName("workflowContext").like(
				"\"userId\":" + userId));

		addCompletedCriterion(dynamicQuery, completed);

		return dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	public int getSubmittingUserKaleoTaskInstanceTokensCount(
			long userId, Boolean completed, ServiceContext serviceContext)
		throws SystemException {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			KaleoTaskInstanceToken.class, getClass().getClassLoader());

		dynamicQuery.add(
			PropertyFactoryUtil.forName("companyId").eq(
				serviceContext.getCompanyId()));
		dynamicQuery.add(
			PropertyFactoryUtil.forName("workflowContext").like(
				"\"userId\":" + userId));

		addCompletedCriterion(dynamicQuery, completed);

		return (int)dynamicQueryCount(dynamicQuery);
	}

	public List<KaleoTaskInstanceToken> search(
			String keywords, Boolean completed, Boolean searchByUserRoles,
			int start, int end, OrderByComparator orderByComparator,
			ServiceContext serviceContext)
		throws SystemException {

		return search(
			keywords, keywords, null, null, completed, searchByUserRoles, false,
			start, end, orderByComparator, serviceContext);
	}

	public List<KaleoTaskInstanceToken> search(
			String taskName, String assetType, Date dueDateGT,
			Date dueDateLT, Boolean completed, Boolean searchByUserRoles,
			boolean andOperator, int start, int end,
			OrderByComparator orderByComparator, ServiceContext serviceContext)
		throws SystemException {

		KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery =
			new KaleoTaskInstanceTokenQuery(serviceContext);

		kaleoTaskInstanceTokenQuery.setAssetType(assetType);
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

	public int searchCount(
			String keywords, Boolean completed, Boolean searchByUserRoles,
			ServiceContext serviceContext)
		throws SystemException {

		return searchCount(
			keywords, keywords, null, null, completed, searchByUserRoles, false,
			serviceContext);
	}

	public int searchCount(
			String taskName, String assetType, Date dueDateGT,
			Date dueDateLT, Boolean completed, Boolean searchByUserRoles,
			boolean andOperator, ServiceContext serviceContext)
		throws SystemException {

		KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery =
			new KaleoTaskInstanceTokenQuery(serviceContext);

		kaleoTaskInstanceTokenQuery.setAssetType(assetType);
		kaleoTaskInstanceTokenQuery.setCompleted(completed);
		kaleoTaskInstanceTokenQuery.setDueDateGT(dueDateGT);
		kaleoTaskInstanceTokenQuery.setDueDateLT(dueDateLT);
		kaleoTaskInstanceTokenQuery.setSearchByUserRoles(searchByUserRoles);
		kaleoTaskInstanceTokenQuery.setTaskName(taskName);

		return kaleoTaskInstanceTokenFinder.countKaleoTaskInstanceTokens(
			kaleoTaskInstanceTokenQuery);
	}

	public KaleoTaskInstanceToken updateDueDate(
			long kaleoTaskInstanceTokenId, Date dueDate,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

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

		kaleoTaskInstanceTokenPersistence.update(kaleoTaskInstance, false);

		return kaleoTaskInstance;
	}

	protected void addCompletedCriterion(
		DynamicQuery dynamicQuery, Boolean completed) {

		if (completed == null) {
			return;
		}

		dynamicQuery.add(
			PropertyFactoryUtil.forName("completed").eq(completed));
	}

	protected void addSearchByUserRolesCriterion(
			DynamicQuery dynamicQuery, Boolean searchByUserRoles,
			ServiceContext serviceContext)
		throws SystemException {

		if (searchByUserRoles == null) {
			return;
		}

		if (!searchByUserRoles) {
			dynamicQuery.add(
				PropertyFactoryUtil.forName("assigneeClassName").eq(
					User.class.getName()));
			dynamicQuery.add(
				PropertyFactoryUtil.forName("assigneeClassPK").eq(
					serviceContext.getUserId()));
			return;
		}

		List<Long> roleIds = RoleRetrievalUtil.getRoleIds(serviceContext);

		List<UserGroupRole> userGroupRoles =
			UserGroupRoleLocalServiceUtil.getUserGroupRoles(
				serviceContext.getUserId());

		if (userGroupRoles.isEmpty()) {
			dynamicQuery.add(
				PropertyFactoryUtil.forName("assigneeClassName").eq(
					Role.class.getName()));

			dynamicQuery.add(
				PropertyFactoryUtil.forName("assigneeClassPK").in(
					roleIds.toArray(new Long[roleIds.size()])));
		}
		else {
			Junction junction = RestrictionsFactoryUtil.disjunction();

			junction.add(
				RestrictionsFactoryUtil.and(
					PropertyFactoryUtil.forName("assigneeClassName").eq(
						Role.class.getName()),
					PropertyFactoryUtil.forName("assigneeClassPK").in(
						roleIds.toArray(new Long[roleIds.size()]))));

			for (UserGroupRole userGroupRole : userGroupRoles) {
				junction.add(
					RestrictionsFactoryUtil.and(
						PropertyFactoryUtil.forName("groupId").eq(
							userGroupRole.getGroupId()),
						RestrictionsFactoryUtil.and(
							PropertyFactoryUtil.forName("assigneeClassName").eq(
								Role.class.getName()),
							PropertyFactoryUtil.forName("assigneeClassPK").eq(
								userGroupRole.getRoleId()))));
			}

			dynamicQuery.add(junction);
		}
	}

	protected DynamicQuery buildDynamicQuery(
		Boolean completed, ServiceContext serviceContext) {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			KaleoTaskInstanceToken.class, getClass().getClassLoader());

		dynamicQuery.add(
			PropertyFactoryUtil.forName("companyId").eq(
				serviceContext.getCompanyId()));

		addCompletedCriterion(dynamicQuery, completed);

		return dynamicQuery;
	}

	protected DynamicQuery buildDynamicQuery(
		long kaleoInstanceId, Boolean completed,
		ServiceContext serviceContext) {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			KaleoTaskInstanceToken.class, getClass().getClassLoader());

		dynamicQuery.add(
			PropertyFactoryUtil.forName("companyId").eq(
				serviceContext.getCompanyId()));
		dynamicQuery.add(
			PropertyFactoryUtil.forName("kaleoInstanceId").eq(
				kaleoInstanceId));

		addCompletedCriterion(dynamicQuery, completed);

		return dynamicQuery;
	}

	protected DynamicQuery buildDynamicQuery(
			String taskName, String assetType, Date dueDateGT, Date dueDateLT,
			Boolean completed, Boolean searchByUserRoles, boolean andOperator,
			ServiceContext serviceContext)
		throws SystemException {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			KaleoTaskInstanceToken.class, getClass().getClassLoader());

		dynamicQuery.add(
			PropertyFactoryUtil.forName("companyId").eq(
				serviceContext.getCompanyId()));

		if (Validator.isNotNull(taskName) || Validator.isNotNull(assetType) ||
			(dueDateGT != null) || (dueDateLT != null)) {

			Junction junction = null;

			if (andOperator) {
				junction = RestrictionsFactoryUtil.conjunction();
			}
			else {
				junction = RestrictionsFactoryUtil.disjunction();
			}

			if (Validator.isNotNull(taskName)) {
				String[] taskNameKeywords = StringUtil.split(
					taskName, StringPool.SPACE);

				for (String taskNameKeyword : taskNameKeywords) {
					junction.add(
						PropertyFactoryUtil.forName("kaleoTaskName").like(
							taskNameKeyword));
				}
			}

			if (Validator.isNotNull(assetType)) {
				String[] assetTypeKeywords = StringUtil.split(
					assetType, StringPool.SPACE);

				for (String assetTypeKeyword : assetTypeKeywords) {
					junction.add(
						PropertyFactoryUtil.forName("workflowContext").like(
							assetTypeKeyword));
				}
			}

			if (dueDateGT != null) {
				junction.add(
					PropertyFactoryUtil.forName("dueDate").ge(dueDateGT));
			}

			if (dueDateLT != null) {
				junction.add(
					PropertyFactoryUtil.forName("dueDate").lt(dueDateLT));
			}

			dynamicQuery.add(junction);
		}

		addSearchByUserRolesCriterion(
			dynamicQuery, searchByUserRoles, serviceContext);

		addCompletedCriterion(dynamicQuery, completed);

		return dynamicQuery;
	}

}