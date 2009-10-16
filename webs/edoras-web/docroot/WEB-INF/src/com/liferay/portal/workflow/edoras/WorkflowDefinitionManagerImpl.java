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

package com.liferay.portal.workflow.edoras;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.kernel.workflow.WorkflowDefinitionManager;
import com.liferay.portal.kernel.workflow.WorkflowException;

import java.util.List;
import java.util.Map;

/**
 * <a href="WorkflowDefinitionManagerImpl.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Micha Kiener
 * @author Brian Wing Shun Chan
 */
public class WorkflowDefinitionManagerImpl
	implements WorkflowDefinitionManager {

	public void deployWorkflowDefinition(
			WorkflowDefinition workflowDefinition, long callingUserId,
			boolean autoIncrementVersionNumber,
			Map<String, Object> parameters)
		throws WorkflowException {
	}

	public int getWorkflowDefinitionCount() throws WorkflowException {
		return 0;
	}

	public int getWorkflowDefinitionCount(String workflowDefinitionName) {
		return 0;
	}

	public List<WorkflowDefinition> getWorkflowDefinitions(
		int start, int end, OrderByComparator orderByComparator) {

		return null;
	}

	public List<WorkflowDefinition> getWorkflowDefinitions(
		String workflowDefinitionName, int start, int end,
		OrderByComparator orderByComparator) {

		return null;
	}

	public boolean isSupportsVersioning() {
		return _SUPPORTS_VERSIONING;
	}
	
	public void undeployWorkflowDefinition(
		WorkflowDefinition workflowDefinition, long callingUserId,
		Map<String, Object> parameters) {

	}

	private static final boolean _SUPPORTS_VERSIONING = true;

}