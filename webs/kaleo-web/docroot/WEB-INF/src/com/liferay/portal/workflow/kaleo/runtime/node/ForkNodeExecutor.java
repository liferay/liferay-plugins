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

package com.liferay.portal.workflow.kaleo.runtime.node;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoNode;
import com.liferay.portal.workflow.kaleo.model.KaleoTimer;
import com.liferay.portal.workflow.kaleo.model.KaleoTransition;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.graph.PathElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class ForkNodeExecutor extends BaseNodeExecutor {

	@Override
	protected boolean doEnter(
		KaleoNode currentKaleoNode, ExecutionContext executionContext) {

		return true;
	}

	@Override
	protected void doExecute(
			KaleoNode currentKaleoNode, ExecutionContext executionContext,
			List<PathElement> remainingPathElements)
		throws PortalException {

		List<KaleoTransition> kaleoTransitions =
			currentKaleoNode.getKaleoTransitions();

		Map<String, KaleoInstanceToken> childKaleoInstanceTokens =
			new HashMap<>();

		for (KaleoTransition kaleoTransition : kaleoTransitions) {
			KaleoInstanceToken parentKaleoInstanceToken =
				executionContext.getKaleoInstanceToken();

			KaleoInstanceToken childKaleoInstanceToken =
				kaleoInstanceTokenLocalService.addKaleoInstanceToken(
					parentKaleoInstanceToken.getKaleoInstanceTokenId(),
					executionContext.getWorkflowContext(),
					executionContext.getServiceContext());

			childKaleoInstanceTokens.put(
				kaleoTransition.getName(), childKaleoInstanceToken);
		}

		for (KaleoTransition kaleoTransition : kaleoTransitions) {
			KaleoInstanceToken childKaleoInstanceToken =
				childKaleoInstanceTokens.get(kaleoTransition.getName());

			ExecutionContext forkedExecutionContext = new ExecutionContext(
				childKaleoInstanceToken, executionContext.getWorkflowContext(),
				executionContext.getServiceContext());

			PathElement pathElement = new PathElement(
				currentKaleoNode, kaleoTransition.getTargetKaleoNode(),
				forkedExecutionContext);

			remainingPathElements.add(pathElement);
		}
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