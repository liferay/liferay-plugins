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
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.definition.Action;
import com.liferay.portal.workflow.kaleo.definition.Assignment;
import com.liferay.portal.workflow.kaleo.definition.DelayDuration;
import com.liferay.portal.workflow.kaleo.definition.Notification;
import com.liferay.portal.workflow.kaleo.definition.Timer;
import com.liferay.portal.workflow.kaleo.model.KaleoTimer;
import com.liferay.portal.workflow.kaleo.service.base.KaleoTimerLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author Marcellus Tavares
 */
public class KaleoTimerLocalServiceImpl extends KaleoTimerLocalServiceBaseImpl {

	@Override
	public KaleoTimer addKaleoTimer(
			String kaleoClassName, long kaleoClassPK, long kaleoDefinitionId,
			Timer timer, ServiceContext serviceContext)
		throws PortalException {

		// Kaleo timer

		User user = userPersistence.findByPrimaryKey(
			serviceContext.getGuestOrUserId());
		Date now = new Date();

		long kaleoTimerId = counterLocalService.increment();

		KaleoTimer kaleoTimer = kaleoTimerPersistence.create(kaleoTimerId);

		kaleoTimer.setCompanyId(user.getCompanyId());
		kaleoTimer.setUserId(user.getUserId());
		kaleoTimer.setUserName(user.getFullName());
		kaleoTimer.setCreateDate(now);
		kaleoTimer.setModifiedDate(now);
		kaleoTimer.setKaleoClassName(kaleoClassName);
		kaleoTimer.setKaleoClassPK(kaleoClassPK);
		kaleoTimer.setKaleoDefinitionId(kaleoDefinitionId);
		kaleoTimer.setName(timer.getName());
		kaleoTimer.setBlocking(timer.isBlocking());

		DelayDuration delayDuration = timer.getDelayDuration();

		kaleoTimer.setDuration(delayDuration.getDuration());
		kaleoTimer.setScale(delayDuration.getDurationScale().getValue());

		DelayDuration recurrenceDelayDuration = timer.getRecurrence();

		if (recurrenceDelayDuration != null) {
			kaleoTimer.setRecurrenceDuration(
				recurrenceDelayDuration.getDuration());
			kaleoTimer.setRecurrenceScale(
				recurrenceDelayDuration.getDurationScale().getValue());
		}

		kaleoTimerPersistence.update(kaleoTimer);

		// Kaleo actions

		Set<Action> actions = timer.getActions();

		for (Action action : actions) {
			kaleoActionLocalService.addKaleoAction(
				KaleoTimer.class.getName(), kaleoTimerId, kaleoDefinitionId,
				timer.getName(), action, serviceContext);
		}

		// Kaleo assignments

		Set<Assignment> reassignments = timer.getReassignments();

		for (Assignment reassignment : reassignments) {
			kaleoTaskAssignmentLocalService.addKaleoTaskAssignment(
				KaleoTimer.class.getName(), kaleoTimerId, kaleoDefinitionId,
				reassignment, serviceContext);
		}

		// Kaleo notifications

		Set<Notification> notifications = timer.getNotifications();

		for (Notification notification : notifications) {
			kaleoNotificationLocalService.addKaleoNotification(
				KaleoTimer.class.getName(), kaleoTimerId, kaleoDefinitionId,
				timer.getName(), notification, serviceContext);
		}

		return kaleoTimer;
	}

	@Override
	public List<KaleoTimer> getKaleoTimers(
		String kaleoClassName, long kaleoClassPK) {

		return kaleoTimerPersistence.findByKCN_KCPK(
			kaleoClassName, kaleoClassPK);
	}

	@Override
	public List<KaleoTimer> getKaleoTimers(
		String kaleoClassName, long kaleoClassPK, boolean blocking) {

		return kaleoTimerPersistence.findByKCN_KCPK_Blocking(
			kaleoClassName, kaleoClassPK, blocking);
	}

}