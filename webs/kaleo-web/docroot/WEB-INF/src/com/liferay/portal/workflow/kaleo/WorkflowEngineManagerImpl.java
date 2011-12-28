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

package com.liferay.portal.workflow.kaleo;

import com.liferay.portal.kernel.workflow.WorkflowEngineManager;

import java.util.Collections;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class WorkflowEngineManagerImpl implements WorkflowEngineManager {

	public String getKey() {
		return _KEY;
	}

	public String getName() {
		return _NAME;
	}

	public Map<String, Object> getOptionalAttributes() {
		return _OPTIONAL_ATTRIBUTES;
	}

	public String getVersion() {
		return _VERSION;
	}

	private static final String _KEY = "liferay";

	private static final String _NAME = "Liferay Kaleo Workflow Engine";

	private static final Map<String, Object> _OPTIONAL_ATTRIBUTES =
		Collections.emptyMap();

	private static final String _VERSION = "6.0.0";

}