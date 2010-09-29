/*
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

package com.liferay.portal.workflow.kaleo.runtime.node.transition;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.workflow.kaleo.model.KaleoCondition;
import com.liferay.portal.workflow.kaleo.model.KaleoNode;
import com.liferay.portal.workflow.kaleo.model.KaleoTransition;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.condition.ConditionEvaluator;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="DefaultTransitionSelector.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class DefaultTransitionSelector implements TransitionSelector {

	public KaleoTransition selectTransition(
			KaleoNode kaleoNode, ExecutionContext executionContext)
		throws PortalException, SystemException {

		List<KaleoTransition> availableTransitions =
			kaleoNode.getKaleoTransitions();

		KaleoTransition selectedTransition = null;

		for (KaleoTransition kaleoTransition : availableTransitions) {
			KaleoCondition kaleoCondition = kaleoTransition.getCondition();

			if (kaleoCondition == null) {
				continue;
			}

			boolean conditionalValue = _conditionEvaluator.evaluate(
				kaleoCondition, executionContext);

			if (conditionalValue) {
				selectedTransition = kaleoTransition;
				break;
			}
		}

		if (selectedTransition == null) {
			selectedTransition = kaleoNode.getDefaultKaleoTransition();
		}
		
		return selectedTransition;
	}

	public List<KaleoTransition> selectTransitions(
			KaleoNode kaleoNode, ExecutionContext executionContext)
		throws PortalException, SystemException {

		List<KaleoTransition> availableTransitions =
			kaleoNode.getKaleoTransitions();

		List<KaleoTransition> selectedTransitions =
			new ArrayList<KaleoTransition>(availableTransitions.size());

		for (KaleoTransition kaleoTransition : availableTransitions) {
			KaleoCondition kaleoCondition = kaleoTransition.getCondition();

			if (kaleoCondition == null) {
				selectedTransitions.add(kaleoTransition);

				continue;
			}

			boolean conditionalValue = _conditionEvaluator.evaluate(
				kaleoCondition, executionContext);

			if (conditionalValue) {
				selectedTransitions.add(kaleoTransition);
			}
		}

		if (selectedTransitions.isEmpty()) {
			selectedTransitions.add(kaleoNode.getDefaultKaleoTransition());
		}

		return selectedTransitions;

	}

	public void setConditionEvaluator(ConditionEvaluator conditionEvaluator) {
		_conditionEvaluator = conditionEvaluator;
	}

	private ConditionEvaluator _conditionEvaluator;
}
