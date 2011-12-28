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
import com.liferay.portal.kernel.scripting.ScriptingUtil;
import com.liferay.portal.workflow.kaleo.model.KaleoCondition;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.util.ScriptingContextBuilder;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Michael C. Han
 */
public class ScriptingConditionEvaluator implements ConditionEvaluator {

	public String evaluate(
			KaleoCondition kaleoCondition, ExecutionContext executionContext)
		throws PortalException, SystemException {

		Map<String, Object> inputObjects =
			ScriptingContextBuilder.buildScriptingContext(executionContext);

		Map<String, Object> results = ScriptingUtil.eval(
			null, inputObjects, _outputNames,
			kaleoCondition.getScriptLanguage(), kaleoCondition.getScript());

		String returnValue = (String)results.get(_RETURN_VALUE);

		if (returnValue != null) {
			return returnValue;
		}

		throw new IllegalStateException(
			"Conditional did not return value for script " +
				kaleoCondition.getScript());
	}

	private static final String _RETURN_VALUE = "returnValue";

	private static Set<String> _outputNames = new HashSet<String>();

	static {
		_outputNames.add(_RETURN_VALUE);
	}

}