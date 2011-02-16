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

package com.liferay.portal.workflow.kaleo.runtime.node;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoNode;
import com.liferay.portal.workflow.kaleo.model.KaleoTransition;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceTokenImpl;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.graph.PathElement;
import com.liferay.portal.workflow.kaleo.runtime.util.ExecutionUtil;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class StateNodeExecutor extends BaseNodeExecutor {

	protected void doEnter(
			KaleoNode currentKaleoNode, ExecutionContext executionContext)
		throws PortalException, SystemException {

	}

	protected void doExecute(
			KaleoNode currentKaleoNode, ExecutionContext executionContext,
			List<PathElement> remainingPathElement)
		throws PortalException, SystemException {

		Map<String, Serializable> workflowContext =
			executionContext.getWorkflowContext();
		ServiceContext serviceContext = executionContext.getServiceContext();

		KaleoInstanceToken kaleoInstanceToken =
			executionContext.getKaleoInstanceToken();

		String transitionName = executionContext.getTransitionName();

		if (!currentKaleoNode.hasKaleoTransition()) {
			kaleoInstanceToken =
				kaleoInstanceTokenLocalService.completeKaleoInstanceToken(
					kaleoInstanceToken.getKaleoInstanceTokenId());

			if (kaleoInstanceToken.getParentKaleoInstanceTokenId() ==
					KaleoInstanceTokenImpl.
						DEFAULT_PARENT_KALEO_INSTANCE_TOKEN_ID) {

				kaleoInstanceLocalService.completeKaleoInstance(
					kaleoInstanceToken.getKaleoInstanceId());
			}

			return;
		}

		KaleoTransition kaleoTransition = null;

		if (Validator.isNull(transitionName)) {
			kaleoTransition = transitionSelector.selectKaleoTransition(
				currentKaleoNode, executionContext);
		}
		else {
			kaleoTransition = currentKaleoNode.getKaleoTransition(
				transitionName);
		}

		ExecutionContext newExecutionContext = new ExecutionContext(
			kaleoInstanceToken, workflowContext, serviceContext);

		PathElement pathElement = new PathElement(
			currentKaleoNode, kaleoTransition.getTargetKaleoNode(),
			newExecutionContext);

		remainingPathElement.add(pathElement);
	}

	protected void doExit(
		KaleoNode currentKaleoNode, ExecutionContext executionContext,
		List<PathElement> remainingPathElement) {
	}

}