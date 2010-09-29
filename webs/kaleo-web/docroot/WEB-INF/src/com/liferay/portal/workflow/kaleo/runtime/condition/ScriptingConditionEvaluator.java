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

package com.liferay.portal.workflow.kaleo.runtime.condition;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.scripting.ScriptingUtil;
import com.liferay.portal.workflow.kaleo.model.KaleoCondition;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.util.ScriptingContextBuilder;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <a href="ScriptingConditionEvaluator.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class ScriptingConditionEvaluator implements ConditionEvaluator {
	
	public boolean evaluate(
			KaleoCondition kaleoCondition, ExecutionContext executionContext)
		throws SystemException, PortalException {

		Map<String, Object> inputObjects =
			ScriptingContextBuilder.buildScriptingContext(executionContext);

		Map<String, Object> returnResults = ScriptingUtil.eval(
			null, inputObjects, CONDITION_RETURN_PARAMTERS,
			kaleoCondition.getScriptLanguage(), kaleoCondition.getScript());

		Boolean conditionValue = (Boolean)returnResults.get(
			RETURN_PARAMETER_NAME);

		if (conditionValue == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Conditional script did not return value: " +
					conditionValue + ": " + kaleoCondition.getScript());
			}
			
			return false;
		}
		
		return conditionValue;
	}

	private static final String RETURN_PARAMETER_NAME = "returnValue";
	
	private static final Set<String> CONDITION_RETURN_PARAMTERS =
		new HashSet<String>();

	static {
		CONDITION_RETURN_PARAMTERS.add(RETURN_PARAMETER_NAME);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ScriptingConditionEvaluator.class);
}
