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

package com.liferay.portal.workflow.kaleo.runtime.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;

import java.util.Map;

/**
 * @author Michael C. Han
 */
public class ScriptingContextBuilderUtil {

	public static Map<String, Object> buildScriptingContext(
			ExecutionContext executionContext)
		throws PortalException, SystemException {

		return getScriptingContextBuilder().buildScriptingContext(
			executionContext);
	}

	public static ScriptingContextBuilder getScriptingContextBuilder() {
		return _scriptingContextBuilder;
	}

	public void setScriptingContextBuilder(
		ScriptingContextBuilder scriptingContextBuilder) {

		_scriptingContextBuilder = scriptingContextBuilder;
	}

	private static ScriptingContextBuilder _scriptingContextBuilder;

}