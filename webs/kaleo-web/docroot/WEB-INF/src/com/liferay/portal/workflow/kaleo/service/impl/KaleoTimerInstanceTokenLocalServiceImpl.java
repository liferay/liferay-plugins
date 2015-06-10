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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scheduler.CronText;
import com.liferay.portal.kernel.scheduler.CronTrigger;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelperUtil;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.Trigger;
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
import com.liferay.portal.workflow.kaleo.service.base.KaleoTimerInstanceTokenLocalServiceBaseImpl;
import com.liferay.portal.workflow.kaleo.util.SchedulerUtil;
import com.liferay.portal.workflow.kaleo.util.WorkflowContextUtil;
import com.liferay.portlet.exportimport.staging.StagingUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Marcellus Tavares
 */
public class KaleoTimerInstanceTokenLocalServiceImpl
	extends KaleoTimerInstanceTokenLocalServiceBaseImpl {

	@Override
	public KaleoTimerInstanceToken addKaleoTimerInstanceToken(
			long kaleoInstanceTokenId, long kaleoTaskInstanceTokenId,
			long kaleoTimerId, String kaleoTimerName,
			Map<String, Serializable> workflowContext,
			ServiceContext serviceContext)
		throws PortalException {

		User user = userPersistence.findByPrimaryKey(
			serviceContext.getGuestOrUserId());
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

		long groupId = StagingUtil.getLiveGroupId(
			serviceContext.getScopeGroupId());

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

		kaleoTimerInstanceTokenPersistence.update(kaleoTimerInstanceToken);

		scheduleTimer(kaleoTimerInstanceToken, kaleoTimer);

		return kaleoTimerInstanceToken;
	}

	@Override
	public List<KaleoTimerInstanceToken> addKaleoTimerInstanceTokens(
			KaleoInstanceToken kaleoInstanceToken,
			KaleoTaskInstanceToken kaleoTaskInstanceToken,
			Collection<KaleoTimer> kaleoTimers,
			Map<String, Serializable> workflowContext,
			ServiceContext serviceContext)
		throws PortalException {

		if (kaleoTimers.isEmpty()) {
			return Collections.emptyList();
		}

		List<KaleoTimerInstanceToken> kaleoTimerInstanceTokens =
			new ArrayList<>(kaleoTimers.size());

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

	@Override
	public KaleoTimerInstanceToken completeKaleoTimerInstanceToken(
			long kaleoTimerInstanceTokenId, ServiceContext serviceContext)
		throws PortalException {

		KaleoTimerInstanceToken kaleoTimerInstanceToken =
			kaleoTimerInstanceTokenPersistence.findByPrimaryKey(
				kaleoTimerInstanceTokenId);

		kaleoTimerInstanceToken.setCompletionUserId(serviceContext.getUserId());
		kaleoTimerInstanceToken.setCompleted(true);
		kaleoTimerInstanceToken.setCompletionDate(new Date());

		kaleoTimerInstanceTokenPersistence.update(kaleoTimerInstanceToken);

		deleteScheduledTimer(kaleoTimerInstanceToken);

		return kaleoTimerInstanceToken;
	}

	@Override
	public void completeKaleoTimerInstanceTokens(
			List<KaleoTimerInstanceToken> kaleoTimerInstanceTokens,
			ServiceContext serviceContext)
		throws PortalException {

		for (KaleoTimerInstanceToken kaleoTimerInstanceToken :
				kaleoTimerInstanceTokens) {

			completeKaleoTimerInstanceToken(
				kaleoTimerInstanceToken.getKaleoTimerInstanceTokenId(),
				serviceContext);
		}
	}

	@Override
	public void completeKaleoTimerInstanceTokens(
			long kaleoInstanceTokenId, ServiceContext serviceContext)
		throws PortalException {

		List<KaleoTimerInstanceToken> kaleoTimerInstanceTokens =
			kaleoTimerInstanceTokenPersistence.findByKITI_C(
				kaleoInstanceTokenId, false);

		completeKaleoTimerInstanceTokens(
			kaleoTimerInstanceTokens, serviceContext);
	}

	@Override
	public void deleteKaleoTimerInstanceToken(
			long kaleoInstanceTokenId, long kaleoTimerId)
		throws PortalException {

		KaleoTimerInstanceToken kaleoTimerInstanceToken =
			getKaleoTimerInstanceToken(kaleoInstanceTokenId, kaleoTimerId);

		deleteScheduledTimer(kaleoTimerInstanceToken);

		kaleoTimerInstanceTokenPersistence.remove(kaleoTimerInstanceToken);
	}

	@Override
	public void deleteKaleoTimerInstanceTokens(long kaleoInstanceId) {
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

	@Override
	public KaleoTimerInstanceToken getKaleoTimerInstanceToken(
			long kaleoInstanceTokenId, long kaleoTimerId)
		throws PortalException {

		return kaleoTimerInstanceTokenPersistence.findByKITI_KTI(
			kaleoInstanceTokenId, kaleoTimerId);
	}

	@Override
	public List<KaleoTimerInstanceToken> getKaleoTimerInstanceTokens(
		long kaleoInstanceTokenId, boolean completed, boolean blocking,
		ServiceContext serviceContext) {

		return kaleoTimerInstanceTokenPersistence.findByKITI_C_B(
			kaleoInstanceTokenId, completed, blocking);
	}

	@Override
	public int getKaleoTimerInstanceTokensCount(
		long kaleoInstanceTokenId, boolean completed, boolean blocking,
		ServiceContext serviceContext) {

		return kaleoTimerInstanceTokenPersistence.countByKITI_C_B(
			kaleoInstanceTokenId, completed, blocking);
	}

	protected void deleteScheduledTimer(
			KaleoTimerInstanceToken kaleoTimerInstanceToken)
		throws PortalException {

		String groupName = getSchedulerGroupName(kaleoTimerInstanceToken);

		SchedulerEngineHelperUtil.delete(groupName, StorageType.PERSISTED);
	}

	protected String getSchedulerGroupName(
		KaleoTimerInstanceToken kaleoTimerInstanceToken) {

		return SchedulerUtil.getGroupName(
			kaleoTimerInstanceToken.getKaleoTimerInstanceTokenId());
	}

	protected void scheduleTimer(
			KaleoTimerInstanceToken kaleoTimerInstanceToken,
			KaleoTimer kaleoTimer)
		throws PortalException {

		deleteScheduledTimer(kaleoTimerInstanceToken);

		String groupName = getSchedulerGroupName(kaleoTimerInstanceToken);

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
			"kaleoTimerInstanceTokenId",
			kaleoTimerInstanceToken.getKaleoTimerInstanceTokenId());

		SchedulerEngineHelperUtil.schedule(
			trigger, StorageType.PERSISTED, null,
			SchedulerUtil.WORKFLOW_TIMER_DESTINATION_NAME, message, 0);
	}

	private static Log _log = LogFactoryUtil.getLog(
		KaleoTimerInstanceTokenLocalServiceImpl.class);

}