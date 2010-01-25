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

package com.liferay.portal.workflow.jbpm;

import com.liferay.portal.kernel.workflow.DefaultWorkflowInstance;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.graph.exe.Token;

/**
 * <a href="WorkflowInstanceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class WorkflowInstanceImpl extends DefaultWorkflowInstance {

	public WorkflowInstanceImpl(Token token) {
		ProcessInstance processInstance = token.getProcessInstance();
		ProcessDefinition processDefinition =
			processInstance.getProcessDefinition();

		Map<String, Object> context =
			processInstance.getContextInstance().getVariables(token);

		if (context == null) {
			context = new HashMap<String, Object>();
		}

		setContext(Collections.unmodifiableMap(context));

		setEndDate(token.getEnd());
		setStartDate(token.getStart());
		setState(token.getNode().getName());
		setWorkflowDefinitionName(processDefinition.getName());
		setWorkflowDefinitionVersion(processDefinition.getVersion());
		setWorkflowInstanceId(token.getId());
	}

}