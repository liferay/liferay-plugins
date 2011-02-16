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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.NoSuchTimerException;
import com.liferay.portal.workflow.kaleo.definition.Assignment;
import com.liferay.portal.workflow.kaleo.definition.DelayDuration;
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

	public KaleoTimer addKaleoTimer(
			long kaleoDefinitionId, long kaleoNodeId, long parentKaleoNodeId,
			Timer timer, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Kaleo timer

		User user = userPersistence.findByPrimaryKey(
			serviceContext.getUserId());
		Date now = new Date();

		long kaleoTimerId = counterLocalService.increment();

		KaleoTimer kaleoTimer = kaleoTimerPersistence.create(kaleoTimerId);

		kaleoTimer.setCompanyId(user.getCompanyId());
		kaleoTimer.setUserId(user.getUserId());
		kaleoTimer.setUserName(user.getFullName());
		kaleoTimer.setCreateDate(now);
		kaleoTimer.setModifiedDate(now);
		kaleoTimer.setKaleoDefinitionId(kaleoDefinitionId);
		kaleoTimer.setKaleoNodeId(kaleoNodeId);
		kaleoTimer.setParentKaleoNodeId(parentKaleoNodeId);
		kaleoTimer.setName(timer.getName());
		kaleoTimer.setDefaultTimer(timer.isDefault());

		DelayDuration delayDuration = timer.getDelayDuration();

		kaleoTimer.setDuration(delayDuration.getDuration());
		kaleoTimer.setScale(delayDuration.getDurationScale().getValue());

		kaleoTimerPersistence.update(kaleoTimer, false);

		// Kaleo assignments

		Set<Assignment> reassignments = timer.getReassignments();

		for (Assignment reassignment : reassignments) {
			kaleoTaskAssignmentLocalService.addKaleoTaskAssignment(
				kaleoDefinitionId, kaleoNodeId, parentKaleoNodeId, reassignment,
				serviceContext);
		}

		return kaleoTimer;
	}

	public KaleoTimer getDefaultKaleoTimer(long parentKaleoNodeId)
		throws PortalException, SystemException {

		List<KaleoTimer> kaleoTimers = kaleoTimerPersistence.findByPKNI_DT(
			parentKaleoNodeId, true);

		if (!kaleoTimers.isEmpty()) {
			return kaleoTimers.get(0);
		}

		throw new NoSuchTimerException();
	}

	public List<KaleoTimer> getKaleoTimers(long parentKaleoNodeId)
		throws SystemException {

		return kaleoTimerPersistence.findByparentKaleoNodeId(parentKaleoNodeId);
	}

}