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

package com.liferay.portal.workflow.kaleo.runtime.condition;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.workflow.kaleo.definition.ScriptLanguage;
import com.liferay.portal.workflow.kaleo.model.KaleoCondition;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class MultiLanguageConditionEvaluator implements ConditionEvaluator {

	public String evaluate(
			KaleoCondition kaleoCondition, ExecutionContext executionContext)
		throws PortalException, SystemException {

		ScriptLanguage scriptLanguage = ScriptLanguage.parse(
			kaleoCondition.getScriptLanguage());

		ConditionEvaluator conditionEvaluator = _conditionEvaluators.get(
			scriptLanguage);

		if (conditionEvaluator == null) {
			throw new IllegalArgumentException(
				"No condition evaluator found for script language " +
					scriptLanguage);
		}

		return conditionEvaluator.evaluate(kaleoCondition, executionContext);
	}

	public void setConditionEvaluators(
		Map<String, ConditionEvaluator> conditionEvaluators) {

		for (Map.Entry<String, ConditionEvaluator> entry :
				conditionEvaluators.entrySet()) {

			ScriptLanguage scriptLanguage = ScriptLanguage.parse(
				entry.getKey());

			_conditionEvaluators.put(scriptLanguage, entry.getValue());
		}
	}

	private Map<ScriptLanguage, ConditionEvaluator> _conditionEvaluators =
		new HashMap<ScriptLanguage, ConditionEvaluator>();

}