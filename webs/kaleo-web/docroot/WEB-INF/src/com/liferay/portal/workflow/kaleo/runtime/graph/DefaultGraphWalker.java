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

package com.liferay.portal.workflow.kaleo.runtime.graph;

import com.liferay.portal.kernel.annotation.Isolation;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.workflow.kaleo.BaseKaleoBean;
import com.liferay.portal.workflow.kaleo.definition.ExecutionType;
import com.liferay.portal.workflow.kaleo.definition.NodeType;
import com.liferay.portal.workflow.kaleo.model.KaleoInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoNode;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.action.ActionExecutorUtil;
import com.liferay.portal.workflow.kaleo.runtime.node.NodeExecutor;
import com.liferay.portal.workflow.kaleo.runtime.node.NodeExecutorFactory;
import com.liferay.portal.workflow.kaleo.runtime.notification.NotificationUtil;

import java.util.List;

/**
 * @author Michael C. Han
 */
@Transactional(
	isolation = Isolation.PORTAL, propagation = Propagation.REQUIRES_NEW,
	rollbackFor = {PortalException.class, SystemException.class})
public class DefaultGraphWalker extends BaseKaleoBean implements GraphWalker {

	public void follow(
			KaleoNode sourceKaleoNode, KaleoNode targetKaleoNode,
			List<PathElement> remainingPathElement,
			ExecutionContext executionContext)
		throws PortalException, SystemException {

		if (sourceKaleoNode != null) {
			NodeExecutor nodeExecutor = NodeExecutorFactory.getNodeExecutor(
				NodeType.valueOf(sourceKaleoNode.getType()));

			nodeExecutor.exit(
				sourceKaleoNode, executionContext, remainingPathElement);

			ActionExecutorUtil.executeKaleoActions(
				sourceKaleoNode.getKaleoNodeId(), ExecutionType.ON_EXIT,
				executionContext);

			NotificationUtil.sendKaleoNotifications(
				sourceKaleoNode.getKaleoNodeId(), ExecutionType.ON_EXIT,
				executionContext);
		}

		if (targetKaleoNode != null) {
			kaleoLogLocalService.addNodeEntryKaleoLog(
				executionContext.getKaleoInstanceToken(), sourceKaleoNode,
				targetKaleoNode, executionContext.getServiceContext());

			ActionExecutorUtil.executeKaleoActions(
				targetKaleoNode.getKaleoNodeId(), ExecutionType.ON_ENTRY,
				executionContext);

			NodeExecutor nodeExecutor = NodeExecutorFactory.getNodeExecutor(
				NodeType.valueOf(targetKaleoNode.getType()));

			nodeExecutor.enter(
				targetKaleoNode, executionContext, remainingPathElement);

			NotificationUtil.sendKaleoNotifications(
				targetKaleoNode.getKaleoNodeId(), ExecutionType.ON_ENTRY,
				executionContext);
		}

		checkKaleoInstanceComplete(executionContext);
	}

	protected void checkKaleoInstanceComplete(ExecutionContext executionContext)
		throws PortalException, SystemException {

		KaleoInstanceToken kaleoInstanceToken =
			executionContext.getKaleoInstanceToken();

		if (!kaleoInstanceToken.isCompleted()) {
			return;
		}

		KaleoInstance kaleoInstance = kaleoInstanceToken.getKaleoInstance();

		if (!kaleoInstance.isCompleted()) {
			return;
		}

		kaleoLogLocalService.addWorkflowInstanceEndKaleoLog(
			kaleoInstanceToken, executionContext.getServiceContext());
	}

}