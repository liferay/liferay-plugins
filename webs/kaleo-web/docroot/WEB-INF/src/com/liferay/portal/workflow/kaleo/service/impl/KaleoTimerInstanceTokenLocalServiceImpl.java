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

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.scheduler.CronText;
import com.liferay.portal.kernel.scheduler.CronTrigger;
import com.liferay.portal.kernel.scheduler.SchedulerEngine;
import com.liferay.portal.kernel.scheduler.SchedulerEngineUtil;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.messaging.SchedulerEventMessageListenerWrapper;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.definition.DelayDuration;
import com.liferay.portal.workflow.kaleo.definition.DurationScale;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoTimer;
import com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken;
import com.liferay.portal.workflow.kaleo.runtime.calendar.DefaultDueDateCalculator;
import com.liferay.portal.workflow.kaleo.runtime.calendar.DueDateCalculator;
import com.liferay.portal.workflow.kaleo.runtime.timer.messaging.TimerMessageListener;
import com.liferay.portal.workflow.kaleo.service.base.KaleoTimerInstanceTokenLocalServiceBaseImpl;
import com.liferay.portal.workflow.kaleo.util.GroupUtil;
import com.liferay.portal.workflow.kaleo.util.SchedulerUtil;
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
			long kaleoInstanceTokenId, long kaleoTaskInstanceTokenId,
			long kaleoTimerId, String kaleoTimerName,
			Map<String, Serializable> workflowContext,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(
			serviceContext.getUserId());
		KaleoInstanceToken kaleoInstanceToken =
			kaleoInstanceTokenPersistence.findByPrimaryKey(
				kaleoInstanceTokenId);
		KaleoTimer kaleoTimer = kaleoTimerPersistence.findByPrimaryKey(
			kaleoTimerId);
		Date now = new Date();

		long kaleoTimerInstanceTokenId = counterLocalService.increment();

		KaleoTimerInstanceToken kaleoTimerInstanceToken =
			kaleoTimerInstanceTokenPersistence.create(
				kaleoTimerInstanceTokenId);

		long groupId = GroupUtil.getGroupId(serviceContext);

		kaleoTimerInstanceToken.setGroupId(groupId);

		kaleoTimerInstanceToken.setCompanyId(user.getCompanyId());
		kaleoTimerInstanceToken.setUserId(user.getUserId());
		kaleoTimerInstanceToken.setUserName(user.getFullName());
		kaleoTimerInstanceToken.setCreateDate(now);
		kaleoTimerInstanceToken.setModifiedDate(now);
		kaleoTimerInstanceToken.setKaleoClassName(
			kaleoTimer.getKaleoClassName());
		kaleoTimerInstanceToken.setKaleoClassPK(kaleoTimer.getKaleoClassPK());
		kaleoTimerInstanceToken.setKaleoDefinitionId(
			kaleoInstanceToken.getKaleoDefinitionId());
		kaleoTimerInstanceToken.setKaleoInstanceId(
			kaleoInstanceToken.getKaleoInstanceId());
		kaleoTimerInstanceToken.setKaleoInstanceTokenId(kaleoInstanceTokenId);
		kaleoTimerInstanceToken.setKaleoTaskInstanceTokenId(
			kaleoTaskInstanceTokenId);
		kaleoTimerInstanceToken.setKaleoTimerId(kaleoTimerId);
		kaleoTimerInstanceToken.setKaleoTimerName(kaleoTimerName);
		kaleoTimerInstanceToken.setBlocking(kaleoTimer.isBlocking());
		kaleoTimerInstanceToken.setCompleted(false);
		kaleoTimerInstanceToken.setWorkflowContext(
			WorkflowContextUtil.convert(workflowContext));

		kaleoTimerInstanceTokenPersistence.update(
			kaleoTimerInstanceToken, false);

		scheduleTimer(kaleoTimerInstanceToken, kaleoTimer);

		return kaleoTimerInstanceToken;
	}

	public List<KaleoTimerInstanceToken> addKaleoTimerInstanceTokens(
			KaleoInstanceToken kaleoInstanceToken,
			KaleoTaskInstanceToken kaleoTaskInstanceToken,
			Collection<KaleoTimer> kaleoTimers,
			Map<String, Serializable> workflowContext,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		List<KaleoTimerInstanceToken> kaleoTimerInstanceTokens =
			new ArrayList<KaleoTimerInstanceToken>(kaleoTimers.size());

		long kaleoTaskInstanceTokenId = 0;

		if (kaleoTaskInstanceToken != null) {
			kaleoTaskInstanceTokenId =
				kaleoTaskInstanceToken.getKaleoTaskInstanceTokenId();
		}

		for (KaleoTimer kaleoTimer : kaleoTimers) {
			KaleoTimerInstanceToken kaleoTimerInstanceToken =
				addKaleoTimerInstanceToken(
					kaleoInstanceToken.getKaleoInstanceTokenId(),
					kaleoTaskInstanceTokenId, kaleoTimer.getKaleoTimerId(),
					kaleoTimer.getName(), workflowContext, serviceContext);

			kaleoTimerInstanceTokens.add(kaleoTimerInstanceToken);
		}

		return kaleoTimerInstanceTokens;
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

		deleteScheduledTimer(kaleoTimerInstanceToken);

		return kaleoTimerInstanceToken;
	}

	public void completeKaleoTimerInstanceTokens(
			List<KaleoTimerInstanceToken> kaleoTimerInstanceTokens,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		for (KaleoTimerInstanceToken kaleoTimerInstanceToken :
				kaleoTimerInstanceTokens) {

			completeKaleoTimerInstanceToken(
				kaleoTimerInstanceToken.getKaleoTimerInstanceTokenId(),
				serviceContext);
		}
	}

	public void completeKaleoTimerInstanceTokens(
			long kaleoInstanceTokenId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		List<KaleoTimerInstanceToken> kaleoTimerInstanceTokens =
			kaleoTimerInstanceTokenPersistence.findByKITI_C(
				kaleoInstanceTokenId, false);

		completeKaleoTimerInstanceTokens(
			kaleoTimerInstanceTokens, serviceContext);
	}

	public void deleteKaleoTimerInstanceToken(
			long kaleoInstanceTokenId, long kaleoTimerId)
		throws PortalException, SystemException {

		KaleoTimerInstanceToken kaleoTimerInstanceToken =
			getKaleoTimerInstanceToken(kaleoInstanceTokenId, kaleoTimerId);

		deleteScheduledTimer(kaleoTimerInstanceToken);

		kaleoTimerInstanceTokenPersistence.remove(kaleoTimerInstanceToken);
	}

	public void deleteKaleoTimerInstanceTokens(long kaleoInstanceId)
		throws SystemException {

		List<KaleoTimerInstanceToken> kaleoTimerInstanceTokens =
			kaleoTimerInstanceTokenPersistence.findByKaleoInstanceId(
				kaleoInstanceId);

		for (KaleoTimerInstanceToken kaleoTimerInstanceToken :
				kaleoTimerInstanceTokens) {

			if (kaleoTimerInstanceToken.isCompleted()) {
				continue;
			}

			try {
				deleteScheduledTimer(kaleoTimerInstanceToken);
			}
			catch (PortalException pe) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to unschedule " + kaleoTimerInstanceToken, pe);
				}
			}

			kaleoTimerInstanceTokenPersistence.remove(kaleoTimerInstanceToken);
		}
	}

	public KaleoTimerInstanceToken getKaleoTimerInstanceToken(
			long kaleoInstanceTokenId, long kaleoTimerId)
		throws PortalException, SystemException {

		return kaleoTimerInstanceTokenPersistence.findByKITI_KTI(
			kaleoInstanceTokenId, kaleoTimerId);
	}

	public List<KaleoTimerInstanceToken> getKaleoTimerInstanceTokens(
			long kaleoInstanceTokenId, boolean completed, boolean blocking,
			ServiceContext serviceContext)
		throws SystemException {

		return kaleoTimerInstanceTokenPersistence.findByKITI_C_B(
			kaleoInstanceTokenId, completed, blocking);
	}

	public int getKaleoTimerInstanceTokensCount(
			long kaleoInstanceTokenId, boolean completed, boolean blocking,
			ServiceContext serviceContext)
		throws SystemException {

		return kaleoTimerInstanceTokenPersistence.countByKITI_C_B(
			kaleoInstanceTokenId, completed, blocking);
	}

	protected void deleteScheduledTimer(
			KaleoTimerInstanceToken kaleoTimerInstanceToken)
		throws PortalException {

		String groupName = getSchedulerGroupName(kaleoTimerInstanceToken);

		SchedulerEngineUtil.delete(groupName, StorageType.PERSISTED);
	}

	protected String getSchedulerGroupName(
		KaleoTimerInstanceToken kaleoTimerInstanceToken) {

		return SchedulerUtil.getGroupName(
			kaleoTimerInstanceToken.getKaleoTimerInstanceTokenId());
	}

	protected SchedulerEventMessageListenerWrapper registerMessageListener(
		String groupName) {

		SchedulerEventMessageListenerWrapper schedulerEventListenerWrapper =
			new SchedulerEventMessageListenerWrapper();

		schedulerEventListenerWrapper.setClassName(groupName);
		schedulerEventListenerWrapper.setMessageListener(timerMessageListener);

		schedulerEventListenerWrapper.afterPropertiesSet();

		MessageBusUtil.registerMessageListener(
			DestinationNames.SCHEDULER_DISPATCH, schedulerEventListenerWrapper);

		return schedulerEventListenerWrapper;
	}

	protected void scheduleTimer(
			KaleoTimerInstanceToken kaleoTimerInstanceToken,
			KaleoTimer kaleoTimer)
		throws PortalException {

		deleteScheduledTimer(kaleoTimerInstanceToken);

		String groupName = getSchedulerGroupName(kaleoTimerInstanceToken);

		SchedulerEventMessageListenerWrapper schedulerEventListenerWrapper =
			registerMessageListener(groupName);

		DelayDuration delayDuration = new DelayDuration(
			kaleoTimer.getDuration(),
			DurationScale.parse(kaleoTimer.getScale()));

		DueDateCalculator dueDateCalculator = new DefaultDueDateCalculator();

		Date dueDate = dueDateCalculator.getDueDate(new Date(), delayDuration);

		Calendar dueDateCalendar = CalendarFactoryUtil.getCalendar();

		dueDateCalendar.setTime(dueDate);

		CronText cronText = null;

		if (!kaleoTimer.isRecurring()) {
			cronText = new CronText(dueDateCalendar);
		}
		else {
			DelayDuration recurrenceDelayDuration = new DelayDuration(
				kaleoTimer.getRecurrenceDuration(),
				DurationScale.parse(kaleoTimer.getRecurrenceScale()));

			DurationScale durationScale =
				recurrenceDelayDuration.getDurationScale();

			cronText = new CronText(
				dueDateCalendar, durationScale.getIntegerValue(),
				(int)recurrenceDelayDuration.getDuration());
		}

		Trigger trigger = new CronTrigger(
			groupName, groupName, cronText.toString());

		Message message = new Message();

		message.put(
			SchedulerEngine.MESSAGE_LISTENER_UUID,
			schedulerEventListenerWrapper.getMessageListenerUUID());
		message.put(
			"kaleoTimerInstanceTokenId",
			kaleoTimerInstanceToken.getKaleoTimerInstanceTokenId());

		SchedulerEngineUtil.schedule(
			trigger, StorageType.PERSISTED, null,
			DestinationNames.SCHEDULER_DISPATCH, message, 0);
	}

	@BeanReference(type = TimerMessageListener.class)
	protected TimerMessageListener timerMessageListener;

	private static Log _log = LogFactoryUtil.getLog(
		KaleoTimerInstanceTokenLocalServiceImpl.class);

}