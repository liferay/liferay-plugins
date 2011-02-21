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
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scheduler.CronText;
import com.liferay.portal.kernel.scheduler.CronTrigger;
import com.liferay.portal.kernel.scheduler.SchedulerEngineUtil;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.definition.DelayDuration;
import com.liferay.portal.workflow.kaleo.definition.DurationScale;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoTimer;
import com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken;
import com.liferay.portal.workflow.kaleo.runtime.calendar.DefaultDueDateCalculator;
import com.liferay.portal.workflow.kaleo.runtime.calendar.DueDateCalculator;
import com.liferay.portal.workflow.kaleo.service.base.KaleoTimerInstanceTokenLocalServiceBaseImpl;
import com.liferay.portal.workflow.kaleo.util.WorkflowContextUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Marcellus Tavares
 */
public class KaleoTimerInstanceTokenLocalServiceImpl
	extends KaleoTimerInstanceTokenLocalServiceBaseImpl {

	public KaleoTimerInstanceToken addKaleoTimerInstanceToken(
			long kaleoInstanceTokenId, long kaleoTimerId, String kaleoTimerName,
			Map<String, Serializable> workflowContext,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(
			serviceContext.getUserId());
		KaleoInstanceToken kaleoInstanceToken =
			kaleoInstanceTokenPersistence.findByPrimaryKey(
				kaleoInstanceTokenId);
		Date now = new Date();

		long kaleoTimerInstanceTokenId = counterLocalService.increment();

		KaleoTimerInstanceToken kaleoTimerInstanceToken =
			kaleoTimerInstanceTokenPersistence.create(
				kaleoTimerInstanceTokenId);

		kaleoTimerInstanceToken.setGroupId(serviceContext.getScopeGroupId());
		kaleoTimerInstanceToken.setCompanyId(user.getCompanyId());
		kaleoTimerInstanceToken.setUserId(user.getUserId());
		kaleoTimerInstanceToken.setUserName(user.getFullName());
		kaleoTimerInstanceToken.setCreateDate(now);
		kaleoTimerInstanceToken.setModifiedDate(now);
		kaleoTimerInstanceToken.setKaleoDefinitionId(
			kaleoInstanceToken.getKaleoDefinitionId());
		kaleoTimerInstanceToken.setKaleoInstanceId(
			kaleoInstanceToken.getKaleoInstanceId());
		kaleoTimerInstanceToken.setKaleoTimerId(kaleoTimerId);
		kaleoTimerInstanceToken.setKaleoTimerName(kaleoTimerName);
		kaleoTimerInstanceToken.setCompleted(false);
		kaleoTimerInstanceToken.setWorkflowContext(
			WorkflowContextUtil.convert(workflowContext));

		// TODO Do we need another instance token or is the timer sufficient?

		KaleoInstanceToken childKaleoInstanceToken =
			kaleoInstanceTokenLocalService.addKaleoInstanceToken(
				kaleoInstanceToken.getKaleoInstanceTokenId(), workflowContext,
				serviceContext);

		kaleoTimerInstanceToken.setKaleoInstanceTokenId(
			childKaleoInstanceToken.getKaleoInstanceTokenId());

		kaleoTimerInstanceTokenPersistence.update(
			kaleoTimerInstanceToken, false);

		scheduleTimer(kaleoTimerInstanceToken);

		return kaleoTimerInstanceToken;
	}

	public List<KaleoTimerInstanceToken> addKaleoTimerInstanceTokens(
			KaleoInstanceToken kaleoInstanceToken,
			Collection<KaleoTimer> kaleoTimers,
			Map<String, Serializable> workflowContext,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		List<KaleoTimerInstanceToken> kaleoTimerInstanceTokens =
			new ArrayList<KaleoTimerInstanceToken>(kaleoTimers.size());

		for (KaleoTimer kaleoTimer : kaleoTimers) {
			KaleoTimerInstanceToken kaleoTimerInstanceToken =
				addKaleoTimerInstanceToken(
					kaleoInstanceToken.getKaleoInstanceTokenId(),
					kaleoTimer.getKaleoTimerId(), kaleoTimer.getName(),
					workflowContext, serviceContext);

			kaleoTimerInstanceTokens.add(kaleoTimerInstanceToken);
		}

		return kaleoTimerInstanceTokens;
	}

	public void completeKaleoTimerInstanceTokens(
			long kaleoInstanceId, Collection<KaleoTimer> kaleoTimers,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		for (KaleoTimer kaleoTimer : kaleoTimers) {
			KaleoTimerInstanceToken kaleoTimerInstanceToken =
				getKaleoTimerInstanceToken(
					kaleoInstanceId, kaleoTimer.getKaleoTimerId());

			completeKaleoTimerInstanceToken(
				kaleoTimerInstanceToken.getKaleoTimerInstanceTokenId(),
				serviceContext);
		}
	}

	public KaleoTimerInstanceToken completeKaleoTimerInstanceToken(
			long kaleoTimerInstanceTokenId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		KaleoTimerInstanceToken kaleoTimerInstanceToken =
			kaleoTimerInstanceTokenPersistence.findByPrimaryKey(
				kaleoTimerInstanceTokenId);

		kaleoTimerInstanceToken.setCompletionUserId(serviceContext.getUserId());
		kaleoTimerInstanceToken.setCompleted(true);
		kaleoTimerInstanceToken.setCompletionDate(new Date());

		kaleoTimerInstanceTokenPersistence.update(
			kaleoTimerInstanceToken, false);

		kaleoInstanceTokenLocalService.completeKaleoInstanceToken(
			kaleoTimerInstanceToken.getKaleoInstanceTokenId());

		deleteTimer(kaleoTimerInstanceToken);

		return kaleoTimerInstanceToken;
	}

	public void deleteKaleoTimerInstanceToken(
			long kaleoInstanceId, long kaleoTimerId)
		throws PortalException, SystemException {

		KaleoTimerInstanceToken kaleoTimerInstanceToken =
			getKaleoTimerInstanceToken(kaleoInstanceId, kaleoTimerId);

		deleteTimer(kaleoTimerInstanceToken);

		kaleoTimerInstanceTokenPersistence.remove(kaleoTimerInstanceToken);
	}

	public KaleoTimerInstanceToken getKaleoTimerInstanceToken(
			long kaleoInstanceId, long kaleoTimerId)
		throws PortalException, SystemException {

		return kaleoTimerInstanceTokenPersistence.findByKII_KTI(
			kaleoInstanceId, kaleoTimerId);
	}

	public int getKaleoTimerInstanceTokensCount(
			long kaleoInstanceId, Boolean completed,
			ServiceContext serviceContext)
		throws SystemException {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			kaleoInstanceId, completed, serviceContext);

		return (int)dynamicQueryCount(dynamicQuery);
	}

	protected void addCompletedCriterion(
		DynamicQuery dynamicQuery, Boolean completed) {

		if (completed == null) {
			return;
		}

		dynamicQuery.add(
			PropertyFactoryUtil.forName("completed").eq(completed));
	}

	protected DynamicQuery buildDynamicQuery(
		long kaleoInstanceId, Boolean completed,
		ServiceContext serviceContext) {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			KaleoTimerInstanceToken.class, getClass().getClassLoader());

		dynamicQuery.add(
			PropertyFactoryUtil.forName("companyId").eq(
				serviceContext.getCompanyId()));
		dynamicQuery.add(
			PropertyFactoryUtil.forName("kaleoInstanceId").eq(
				kaleoInstanceId));

		addCompletedCriterion(dynamicQuery, completed);

		return dynamicQuery;
	}

	protected void deleteTimer(KaleoTimerInstanceToken kaleoTimerInstanceToken)
		throws PortalException {

		String groupName = getSchedulerGroupName(kaleoTimerInstanceToken);

		SchedulerEngineUtil.delete(groupName);
	}

	protected String getSchedulerGroupName(
		KaleoTimerInstanceToken kaleoTimerInstanceToken) {

		// TODO

		//return DestinationNames.WORKFLOW_TIMER.concat(StringPool.SLASH).concat(String.valueOf(kaleoTimerInstanceToken.getKaleoTimerInstanceTokenId()));
		return null;
	}

	protected void scheduleTimer(
			KaleoTimerInstanceToken kaleoTimerInstanceToken)
		throws PortalException, SystemException {

		deleteTimer(kaleoTimerInstanceToken);

		String groupName = getSchedulerGroupName(kaleoTimerInstanceToken);

		KaleoTimer kaleoTimer = kaleoTimerPersistence.findByPrimaryKey(
			kaleoTimerInstanceToken.getKaleoTimerId());

		DelayDuration delayDuration = new DelayDuration(
			kaleoTimer.getDuration(),
			DurationScale.parse(kaleoTimer.getScale()));

		DueDateCalculator dueDateCalculator = new DefaultDueDateCalculator();

		Date dueDate = dueDateCalculator.getDueDate(new Date(), delayDuration);

		Calendar dueDateCalendar = CalendarFactoryUtil.getCalendar();

		dueDateCalendar.setTime(dueDate);

		CronText cronText = new CronText(dueDateCalendar);

		Trigger trigger = new CronTrigger(
			groupName, groupName, dueDate, null, cronText.toString());

		Message message = new Message();

		message.put(
			"kaleoTimerInstanceTokenId",
			kaleoTimerInstanceToken.getKaleoTimerInstanceTokenId());

		// TODO

		// May be able to put into the default scheduler event destination
		// instead of using a dedicated...

		//SchedulerEngineUtil.schedule(trigger, null, DestinationNames.WORKFLOW_TIMER, message);
	}

}