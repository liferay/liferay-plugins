/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portlet.workflow.test;

import com.liferay.portal.kernel.workflow.WorkflowEngineManagerUtil;

import javax.servlet.ServletContext;

/**
 * <a href="WorkflowEngineManagerTestCase.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Shuyang Zhou
 */
public class WorkflowEngineManagerTestCase extends BaseTestCase {

	public WorkflowEngineManagerTestCase(ServletContext servletContext) {
		super(servletContext);
	}

	public void testGetAdditionalInformation() throws Exception {
		WorkflowEngineManagerUtil.getAdditionalInformation();
	}

	public void testGetDelegate() throws Exception {
		WorkflowEngineManagerUtil.getDelegate();
	}

	public void testGetVersion() throws Exception {
		String version = WorkflowEngineManagerUtil.getVersion();

		if (version == null) {
			throw new Exception("Unable to get workflow engine version");
		}
	}

	public void testGetWorkflowEngineKey() throws Exception {
		String key = WorkflowEngineManagerUtil.getWorkflowEngineKey();

		if (key == null) {
			throw new Exception("Unable to get workflow engine key");
		}

		if (!key.equals(key.toLowerCase())) {
			throw new Exception(
				"Workflow engine key has capital letters " + key);
		}
	}

	public void testGetWorkflowEngineName() throws Exception {
		String name = WorkflowEngineManagerUtil.getWorkflowEngineName();

		if (name == null) {
			throw new Exception("Unable to get workflow engine name");
		}
	}

	public void testIsSupportsWorkflowDefinitionVersioning() throws Exception {
		WorkflowEngineManagerUtil.isSupportsWorkflowDefinitionVersioning();
	}

}