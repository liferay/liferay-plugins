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
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.service.base.KaleoTaskInstanceTokenLocalServiceBaseImpl;
import com.liferay.portal.workflow.kaleo.util.ContextUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Calendar;
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
			long kaleoInstanceTokenId, long kaleoTaskId,
			Map<String, Serializable> context, ServiceContext serviceContext)
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

		kaleoTaskInstanceToken.setCompanyId(user.getCompanyId());
		kaleoTaskInstanceToken.setUserId(user.getUserId());
		kaleoTaskInstanceToken.setUserName(user.getScreenName());
		kaleoTaskInstanceToken.setCreateDate(now);
		kaleoTaskInstanceToken.setModifiedDate(now);
		kaleoTaskInstanceToken.setKaleoInstanceId(
			kaleoInstanceToken.getKaleoInstanceId());

		KaleoInstanceToken childKaleoInstanceToken =
			kaleoInstanceTokenLocalService.addKaleoInstanceToken(
				kaleoInstanceToken.getKaleoInstanceTokenId(), context,
				serviceContext);

		kaleoTaskInstanceToken.setKaleoInstanceTokenId(
			childKaleoInstanceToken.getKaleoInstanceTokenId());

		kaleoTaskInstanceToken.setKaleoTaskId(kaleoTaskId);
		kaleoTaskInstanceToken.setContext(ContextUtil.convert(context));

		kaleoTaskInstanceTokenPersistence.update(kaleoTaskInstanceToken, false);

		return kaleoTaskInstanceToken;
	}

	public KaleoTaskInstanceToken completeKaleoTaskInstanceToken(
			long kaleoTaskInstanceTokenId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Kaleo instance token

		KaleoTaskInstanceToken kaleoTaskInstanceToken =
			kaleoTaskInstanceTokenPersistence.findByPrimaryKey(
				kaleoTaskInstanceTokenId);

		kaleoTaskInstanceToken.setCompletionUserId(serviceContext.getUserId());
		kaleoTaskInstanceToken.setCompletionDate(new Date());

		kaleoTaskInstanceTokenPersistence.update(kaleoTaskInstanceToken, false);

		// Kaleo instance

		kaleoInstanceTokenLocalService.completeKaleoInstanceToken(
			kaleoTaskInstanceToken.getKaleoInstanceTokenId());

		return kaleoTaskInstanceToken;
	}

	public List<KaleoTaskInstanceToken> getKaleoTaskInstanceTokens(
			Boolean completed, int start, int end,
			OrderByComparator orderByComparator, ServiceContext serviceContext)
		throws PortalException, SystemException {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			KaleoTaskInstanceToken.class);

		dynamicQuery.add(
			PropertyFactoryUtil.forName("companyId").eq(
				serviceContext.getCompanyId()));

		addCompletedCriterion(dynamicQuery, completed);

		List<Object> results = dynamicQuery(
			dynamicQuery, start, end, orderByComparator);

		return toKaleoTaskInstanceTokens(results);
	}

	public List<KaleoTaskInstanceToken> getKaleoTaskInstanceTokens(
			List<Long> roleIds, Boolean completed, int start, int end,
			OrderByComparator orderByComparator, ServiceContext serviceContext)
		throws PortalException, SystemException {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			KaleoTaskInstanceAssignment.class);

		dynamicQuery.add(
			PropertyFactoryUtil.forName("companyId").eq(
				serviceContext.getCompanyId()));
		dynamicQuery.add(
			PropertyFactoryUtil.forName("assigneeClassName").eq(
				Role.class.getName()));

		Long[] roleIdsArray = roleIds.toArray(new Long[roleIds.size()]);

		dynamicQuery.add(
			PropertyFactoryUtil.forName("assigneeClassPK").in(roleIdsArray));

		addCompletedCriterion(dynamicQuery, completed);

		List<Object> results = dynamicQuery(
			dynamicQuery, start, end, orderByComparator);

		return toKaleoTaskInstanceTokens(results);
	}

	public List<KaleoTaskInstanceToken> getKaleoTaskInstanceTokens(
			long kaleoInstanceId, Boolean completed, int start, int end,
			OrderByComparator orderByComparator, ServiceContext serviceContext)
		throws PortalException, SystemException {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			KaleoTaskInstanceToken.class);

		dynamicQuery.add(
			PropertyFactoryUtil.forName("companyId").eq(
				serviceContext.getCompanyId()));
		dynamicQuery.add(
			PropertyFactoryUtil.forName("kaleoInstanceId").eq(
				kaleoInstanceId));

		addCompletedCriterion(dynamicQuery, completed);

		List<Object> results = dynamicQuery(
			dynamicQuery, start, end, orderByComparator);

		return toKaleoTaskInstanceTokens(results);
	}

	public List<KaleoTaskInstanceToken> getKaleoTaskInstanceTokens(
			String assigneeClassName, long assigneeClassPK, Boolean completed,
			int start, int end, OrderByComparator orderByComparator,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			KaleoTaskInstanceAssignment.class);

		dynamicQuery.add(
			PropertyFactoryUtil.forName("companyId").eq(
				serviceContext.getCompanyId()));
		dynamicQuery.add(
			PropertyFactoryUtil.forName("assigneeClassName").eq(
				assigneeClassName));
		dynamicQuery.add(
			PropertyFactoryUtil.forName("assigneeClassPK").eq(
				assigneeClassPK));

		addCompletedCriterion(dynamicQuery, completed);

		List<Object> results = dynamicQuery(
			dynamicQuery, start, end, orderByComparator);

		return toKaleoTaskInstanceTokens(results);
	}

	public int getKaleoTaskInstanceTokensCount(
			Boolean completed, ServiceContext serviceContext)
		throws SystemException {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			KaleoTaskInstanceToken.class);

		dynamicQuery.add(
			PropertyFactoryUtil.forName("companyId").eq(
				serviceContext.getCompanyId()));

		addCompletedCriterion(dynamicQuery, completed);

		return dynamicQueryCount(dynamicQuery);
	}

	public int getKaleoTaskInstanceTokensCount(
			List<Long> roleIds, Boolean completed,
			ServiceContext serviceContext)
		throws SystemException {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			KaleoTaskInstanceAssignment.class);

		dynamicQuery.add(
			PropertyFactoryUtil.forName("companyId").eq(
				serviceContext.getCompanyId()));
		dynamicQuery.add(
			PropertyFactoryUtil.forName("assigneeClassName").eq(
				Role.class.getName()));

		Long[] roleIdsArray = roleIds.toArray(new Long[roleIds.size()]);

		dynamicQuery.add(
			PropertyFactoryUtil.forName("assigneeClassPK").in(roleIdsArray));

		addCompletedCriterion(dynamicQuery, completed);

		return dynamicQueryCount(dynamicQuery);
	}

	public int getKaleoTaskInstanceTokensCount(
			long kaleoInstanceId, Boolean completed,
			ServiceContext serviceContext)
		throws SystemException {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			KaleoTaskInstanceToken.class);

		dynamicQuery.add(
			PropertyFactoryUtil.forName("companyId").eq(
				serviceContext.getCompanyId()));
		dynamicQuery.add(
			PropertyFactoryUtil.forName("kaleoInstanceId").eq(
				kaleoInstanceId));

		addCompletedCriterion(dynamicQuery, completed);

		return dynamicQueryCount(dynamicQuery);
	}

	public int getKaleoTaskInstanceTokensCount(
			String assigneeClassName, long assigneeClassPK,
			Boolean completed, ServiceContext serviceContext)
		throws SystemException {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			KaleoTaskInstanceAssignment.class);

		dynamicQuery.add(
			PropertyFactoryUtil.forName("companyId").eq(
				serviceContext.getCompanyId()));
		dynamicQuery.add(
			PropertyFactoryUtil.forName("assigneeClassName").eq(
				assigneeClassName));
		dynamicQuery.add(
			PropertyFactoryUtil.forName("assigneeClassPK").eq(
				assigneeClassPK));

		addCompletedCriterion(dynamicQuery, completed);

		return dynamicQueryCount(dynamicQuery);
	}

	public KaleoTaskInstanceToken updateDueDate(
			long kaleoTaskInstanceTokenId, Date dueDate,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		KaleoTaskInstanceToken kaleoTaskInstance =
			kaleoTaskInstanceTokenPersistence.fetchByPrimaryKey(
				kaleoTaskInstanceTokenId);

		if (dueDate != null) {
			Calendar cal = CalendarFactoryUtil.getCalendar(
				LocaleUtil.getDefault());
			cal.setTime(dueDate);

			kaleoTaskInstance.setDueDate(cal.getTime());
		}

		kaleoTaskInstance.setModifiedDate(new Date());
		kaleoTaskInstance.setUserId(serviceContext.getUserId());

		kaleoTaskInstanceTokenPersistence.update(
			kaleoTaskInstance, false);

		return kaleoTaskInstance;

	}

	protected void addCompletedCriterion(
		DynamicQuery dynamicQuery, Boolean completed) {

		if (completed == null) {
			return;
		}

		if (completed) {
			dynamicQuery.add(
				PropertyFactoryUtil.forName("completionDate").isNotNull());
		}
		else {
			dynamicQuery.add(
				PropertyFactoryUtil.forName("completionDate").isNull());
		}
	}

	protected List<KaleoTaskInstanceToken> toKaleoTaskInstanceTokens(
			List<Object> results)
		throws PortalException, SystemException {

		List<KaleoTaskInstanceToken> kaleoTaskInstanceTokens =
			new ArrayList<KaleoTaskInstanceToken>(results.size());

		for (Object result : results) {
			KaleoTaskInstanceToken kaleoTaskInstanceToken = null;

			if (result instanceof KaleoTaskInstanceAssignment) {
				KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment =
					(KaleoTaskInstanceAssignment)result;

				kaleoTaskInstanceToken =
					kaleoTaskInstanceAssignment.getKaleoTaskInstanceToken();
			}
			else {
				kaleoTaskInstanceToken = (KaleoTaskInstanceToken)result;
			}

			kaleoTaskInstanceTokens.add(kaleoTaskInstanceToken);
		}

		return kaleoTaskInstanceTokens;
	}

}