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
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoNode;
import com.liferay.portal.workflow.kaleo.model.KaleoTimer;
import com.liferay.portal.workflow.kaleo.model.KaleoTransition;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.graph.PathElement;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class JoinNodeExecutor extends BaseNodeExecutor {

	@Override
	protected void doEnter(
			KaleoNode currentKaleoNode, ExecutionContext executionContext)
		throws PortalException, SystemException {

		KaleoInstanceToken kaleoInstanceToken =
			executionContext.getKaleoInstanceToken();

		kaleoInstanceToken =
			kaleoInstanceTokenLocalService.completeKaleoInstanceToken(
				kaleoInstanceToken.getKaleoInstanceTokenId());
	}

	@Override
	protected void doExecute(
			KaleoNode currentKaleoNode, ExecutionContext executionContext,
			List<PathElement> remainingPathElements)
		throws PortalException, SystemException {

		Map<String, Serializable> workflowContext =
			executionContext.getWorkflowContext();
		ServiceContext serviceContext = executionContext.getServiceContext();

		KaleoInstanceToken kaleoInstanceToken =
			executionContext.getKaleoInstanceToken();

		KaleoInstanceToken parentKaleoInstanceToken =
			kaleoInstanceToken.getParentKaleoInstanceToken();

		if (parentKaleoInstanceToken.
				hasIncompleteChildrenKaleoInstanceToken()) {

			return;
		}

		parentKaleoInstanceToken =
			kaleoInstanceTokenLocalService.updateKaleoInstanceToken(
				parentKaleoInstanceToken.getKaleoInstanceTokenId(),
				currentKaleoNode.getKaleoNodeId());

		KaleoTransition kaleoTransition =
			currentKaleoNode.getDefaultKaleoTransition();

		ExecutionContext newExecutionContext = new ExecutionContext(
			parentKaleoInstanceToken, workflowContext, serviceContext);

		PathElement pathElement = new PathElement(
			currentKaleoNode, kaleoTransition.getTargetKaleoNode(),
			newExecutionContext);

		remainingPathElements.add(pathElement);
	}

	@Override
	protected void doExecuteTimer(
		KaleoNode currentKaleoNode, KaleoTimer kaleoTimer,
		ExecutionContext executionContext) {
	}

	@Override
	protected void doExit(
		KaleoNode currentKaleoNode, ExecutionContext executionContext,
		List<PathElement> remainingPathElements) {
	}

}