/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.workflow.kaleo.runtime.node;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.BaseKaleoBean;
import com.liferay.portal.workflow.kaleo.definition.ExecutionType;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoNode;
import com.liferay.portal.workflow.kaleo.model.KaleoTimer;
import com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.action.ActionExecutorUtil;
import com.liferay.portal.workflow.kaleo.runtime.graph.PathElement;
import com.liferay.portal.workflow.kaleo.runtime.notification.NotificationUtil;
import com.liferay.portal.workflow.kaleo.runtime.util.ExecutionUtil;

import java.util.List;

/**
 * @author Michael C. Han
 */
public abstract class BaseNodeExecutor
	extends BaseKaleoBean implements NodeExecutor {

	public void enter(
			KaleoNode currentKaleoNode, ExecutionContext executionContext)
		throws PortalException, SystemException {

		KaleoInstanceToken kaleoInstanceToken =
			executionContext.getKaleoInstanceToken();

		kaleoInstanceToken.setCurrentKaleoNode(currentKaleoNode);

		doEnter(currentKaleoNode, executionContext);

		ActionExecutorUtil.executeKaleoActions(
			KaleoNode.class.getName(), currentKaleoNode.getKaleoNodeId(),
			ExecutionType.ON_ENTRY, executionContext);

		NotificationUtil.sendKaleoNotifications(
			KaleoNode.class.getName(), currentKaleoNode.getKaleoNodeId(),
			ExecutionType.ON_ENTRY, executionContext);

		List<KaleoTimer> kaleoTimers = kaleoTimerLocalService.getKaleoTimers(
			KaleoNode.class.getName(), currentKaleoNode.getKaleoNodeId());

		kaleoTimerInstanceTokenLocalService.addKaleoTimerInstanceTokens(
			executionContext.getKaleoInstanceToken(),
			executionContext.getKaleoTaskInstanceToken(),
			kaleoTimers, executionContext.getWorkflowContext(),
			executionContext.getServiceContext());
	}

	public void execute(
			KaleoNode currentKaleoNode, ExecutionContext executionContext,
			List<PathElement> remainingPathElements)
		throws PortalException, SystemException {

		if (ExecutionUtil.isKaleoInstanceBlocked(executionContext)) {
			return;
		}

		doExecute(currentKaleoNode, executionContext, remainingPathElements);
	}

	public void executeTimer(
			KaleoNode currentKaleoNode,
			ExecutionContext executionContext)
		throws PortalException, SystemException {

		ServiceContext serviceContext = executionContext.getServiceContext();

		KaleoTimerInstanceToken kaleoTimerInstanceToken =
			executionContext.getKaleoTimerInstanceToken();

		KaleoTimer kaleoTimer = kaleoTimerInstanceToken.getKaleoTimer();

		ActionExecutorUtil.executeKaleoActions(
			KaleoTimer.class.getName(), kaleoTimer.getKaleoTimerId(),
			ExecutionType.ON_TIMER, executionContext);

		NotificationUtil.sendKaleoNotifications(
			KaleoTimer.class.getName(), kaleoTimer.getKaleoTimerId(),
			ExecutionType.ON_TIMER, executionContext);

		doExecuteTimer(currentKaleoNode, kaleoTimer, executionContext);

		if (!kaleoTimer.isRecurring()) {
			kaleoTimerInstanceTokenLocalService.completeKaleoTimerInstanceToken(
				kaleoTimerInstanceToken.getKaleoTimerInstanceTokenId(),
				serviceContext);
		}
	}

	public void exit(
			KaleoNode currentKaleoNode, ExecutionContext executionContext,
			List<PathElement> remainingPathElements)
		throws PortalException, SystemException {

		ExecutionUtil.completeKaleoTimerInstances(executionContext);

		doExit(currentKaleoNode, executionContext, remainingPathElements);

		ActionExecutorUtil.executeKaleoActions(
			KaleoNode.class.getName(), currentKaleoNode.getKaleoNodeId(),
			ExecutionType.ON_EXIT, executionContext);

		NotificationUtil.sendKaleoNotifications(
			KaleoNode.class.getName(), currentKaleoNode.getKaleoNodeId(),
			ExecutionType.ON_EXIT, executionContext);
	}

	protected abstract void doEnter(
			KaleoNode currentKaleoNode, ExecutionContext executionContext)
		throws PortalException, SystemException;

	protected abstract void doExecute(
			KaleoNode currentKaleoNode, ExecutionContext executionContext,
			List<PathElement> remainingPathElements)
		throws PortalException, SystemException;

	protected abstract void doExecuteTimer(
			KaleoNode currentKaleoNode, KaleoTimer kaleoTimer,
			ExecutionContext executionContext)
		throws PortalException, SystemException;

	protected abstract void doExit(
			KaleoNode currentKaleoNode, ExecutionContext executionContext,
			List<PathElement> remainingPathElements)
		throws PortalException, SystemException;

}