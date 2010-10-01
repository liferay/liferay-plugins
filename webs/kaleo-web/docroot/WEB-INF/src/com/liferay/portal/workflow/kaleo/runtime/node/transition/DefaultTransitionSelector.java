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
 * @author Michael C. Han
 */
public class DefaultTransitionSelector implements TransitionSelector {

	public KaleoTransition selectKaleoTransition(
			KaleoNode kaleoNode, ExecutionContext executionContext)
		throws PortalException, SystemException {

		List<KaleoTransition> kaleoTransitions =
			kaleoNode.getKaleoTransitions();

		for (KaleoTransition kaleoTransition : kaleoTransitions) {
			KaleoCondition kaleoCondition = kaleoTransition.getKaleoCondition();

			if (kaleoCondition == null) {
				continue;
			}

			boolean conditionalValue = _conditionEvaluator.evaluate(
				kaleoCondition, executionContext);

			if (conditionalValue) {
				return kaleoTransition;
			}
		}

		return kaleoNode.getDefaultKaleoTransition();
	}

	public List<KaleoTransition> selectKaleoTransitions(
			KaleoNode kaleoNode, ExecutionContext executionContext)
		throws PortalException, SystemException {

		List<KaleoTransition> kaleoTransitions =
			kaleoNode.getKaleoTransitions();

		List<KaleoTransition> selectedKaleoTransitions =
			new ArrayList<KaleoTransition>(kaleoTransitions.size());

		for (KaleoTransition kaleoTransition : kaleoTransitions) {
			KaleoCondition kaleoCondition = kaleoTransition.getKaleoCondition();

			if (kaleoCondition == null) {
				selectedKaleoTransitions.add(kaleoTransition);

				continue;
			}

			boolean conditionalValue = _conditionEvaluator.evaluate(
				kaleoCondition, executionContext);

			if (conditionalValue) {
				selectedKaleoTransitions.add(kaleoTransition);
			}
		}

		if (selectedKaleoTransitions.isEmpty()) {
			selectedKaleoTransitions.add(kaleoNode.getDefaultKaleoTransition());
		}

		return selectedKaleoTransitions;
	}

	public void setConditionEvaluator(ConditionEvaluator conditionEvaluator) {
		_conditionEvaluator = conditionEvaluator;
	}

	private ConditionEvaluator _conditionEvaluator;

}