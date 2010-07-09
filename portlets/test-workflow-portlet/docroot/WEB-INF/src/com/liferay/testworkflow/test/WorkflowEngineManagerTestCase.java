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

package com.liferay.testworkflow.test;

import com.liferay.portal.kernel.workflow.WorkflowEngineManagerUtil;

/**
 * @author Shuyang Zhou
 */
public class WorkflowEngineManagerTestCase extends WorkflowTestCase {

	public void testGetKey() throws Exception {
		String key = WorkflowEngineManagerUtil.getKey();

		assertNotNull(key);
		assertEquals(key.toLowerCase(), key);
		assertEquals(key.trim(), key);
	}

	public void testGetName() throws Exception {
		String name = WorkflowEngineManagerUtil.getName();

		assertNotNull(name);
	}

	public void testGetOptionalAttributes() throws Exception {
		WorkflowEngineManagerUtil.getOptionalAttributes();
	}

	public void testGetVersion() throws Exception {
		String version = WorkflowEngineManagerUtil.getVersion();

		assertNotNull(version);
	}

}