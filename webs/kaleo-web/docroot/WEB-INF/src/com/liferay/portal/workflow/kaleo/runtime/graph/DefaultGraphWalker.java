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

import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.annotation.Isolation;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.workflow.kaleo.definition.ActionType;
import com.liferay.portal.workflow.kaleo.definition.NodeType;
import com.liferay.portal.workflow.kaleo.model.KaleoAction;
import com.liferay.portal.workflow.kaleo.model.KaleoInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoNode;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.action.ActionExecutor;
import com.liferay.portal.workflow.kaleo.runtime.action.ActionExecutorFactory;
import com.liferay.portal.workflow.kaleo.runtime.node.NodeExecutor;
import com.liferay.portal.workflow.kaleo.runtime.node.NodeExecutorFactory;
import com.liferay.portal.workflow.kaleo.service.KaleoActionLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.service.KaleoLogLocalService;

import java.util.List;

/**
 * <a href="DefaultGraphWalker.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
@Transactional(
	isolation = Isolation.PORTAL, propagation = Propagation.REQUIRES_NEW,
	rollbackFor = {PortalException.class, SystemException.class})
public class DefaultGraphWalker implements GraphWalker {

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

			executeKaleoActions(
				sourceKaleoNode.getKaleoNodeId(), ActionType.ON_EXIT,
				executionContext);
		}

		if (targetKaleoNode != null) {
			kaleoLogLocalService.addNodeEntryKaleoLog(
				executionContext.getKaleoInstanceToken(), sourceKaleoNode,
				targetKaleoNode, executionContext.getServiceContext());

			executeKaleoActions(
				targetKaleoNode.getKaleoNodeId(), ActionType.ON_ENTRY,
				executionContext);

			NodeExecutor nodeExecutor = NodeExecutorFactory.getNodeExecutor(
				NodeType.valueOf(targetKaleoNode.getType()));

			nodeExecutor.enter(
				targetKaleoNode, executionContext, remainingPathElement);
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

	protected void executeKaleoActions(
			long kaleoNodeId, ActionType actionType,
			ExecutionContext executionContext)
		throws PortalException, SystemException {

		List<KaleoAction> kaleoActions =
			KaleoActionLocalServiceUtil.getKaleoActions(
				kaleoNodeId, actionType.getType());

		for (KaleoAction kaleoAction : kaleoActions) {
			long startTime = System.currentTimeMillis();
			String comment = _ACTION_SUCCESS_MESG;

			try {
				ActionExecutor actionExecutor =
					ActionExecutorFactory.getActionExecutor(
						kaleoAction.getLanguage());

				actionExecutor.execute(kaleoAction, executionContext);
			}
			catch (Exception e) {
				comment = e.getMessage();
			}
			finally {
				kaleoLogLocalService.addActionExecutionKaleoLog(
					executionContext.getKaleoInstanceToken(), kaleoAction,
					startTime, System.currentTimeMillis(), comment,
					executionContext.getServiceContext());
			}
		}
	}

	@BeanReference(type = KaleoLogLocalService.class)
	protected KaleoLogLocalService kaleoLogLocalService;

	private static final String _ACTION_SUCCESS_MESG =
		"Action completed successfully.";
}