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

import com.liferay.portal.kernel.resource.ResourceRetriever;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * <a href="WorkflowDefinitionImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Micha Kiener
 * @author Brian Wing Shun Chan
 */
public class WorkflowDefinitionImpl implements WorkflowDefinition {
	
	public WorkflowDefinitionImpl(
		com.liferay.portal.workflow.edoras.model.WorkflowDefinition workflowDefinition) {
		
		_workflowDefinitionName = workflowDefinition.getName();
		_workflowDefinitionVersion = workflowDefinition.getVersion();
		
		_attributes = new HashMap<String, Object>();
		_attributes.put(
			AdditionalWorkflowParameterUtil.WF_TENANT_ID,
			workflowDefinition.getCompanyId());
	}

	public Map<String, Object> getAttributes() {
		return Collections.EMPTY_MAP;
	}

	public ResourceRetriever getJar() {
		return null;
	}

	public String getWorkflowDefinitionName() {
		return _workflowDefinitionName;
	}

	public int getWorkflowDefinitionVersion() {
		return _workflowDefinitionVersion;
	}

	private Map<String, Object> _attributes;
	private String _workflowDefinitionName;
	private int _workflowDefinitionVersion;
}