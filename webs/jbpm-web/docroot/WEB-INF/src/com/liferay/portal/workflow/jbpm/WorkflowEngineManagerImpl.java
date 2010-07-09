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

package com.liferay.portal.workflow.jbpm;

import com.liferay.portal.kernel.workflow.WorkflowEngineManager;

import java.util.Collections;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
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

	private static final String _KEY = "jbpm";

	private static final String _NAME = "jBPM";

	private static final Map<String, Object> _OPTIONAL_ATTRIBUTES =
		Collections.EMPTY_MAP;

	private static final String _VERSION = "3.2.6 SP1";

}