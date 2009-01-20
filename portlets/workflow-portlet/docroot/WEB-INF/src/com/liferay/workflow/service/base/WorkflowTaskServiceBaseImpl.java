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

package com.liferay.workflow.service.base;

import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.service.base.PrincipalBean;

import com.liferay.workflow.service.WorkflowComponentService;
import com.liferay.workflow.service.WorkflowDefinitionService;
import com.liferay.workflow.service.WorkflowInstanceService;
import com.liferay.workflow.service.WorkflowTaskService;

/**
 * <a href="WorkflowTaskServiceBaseImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public abstract class WorkflowTaskServiceBaseImpl extends PrincipalBean
	implements WorkflowTaskService {
	public WorkflowComponentService getWorkflowComponentService() {
		return workflowComponentService;
	}

	public void setWorkflowComponentService(
		WorkflowComponentService workflowComponentService) {
		this.workflowComponentService = workflowComponentService;
	}

	public WorkflowDefinitionService getWorkflowDefinitionService() {
		return workflowDefinitionService;
	}

	public void setWorkflowDefinitionService(
		WorkflowDefinitionService workflowDefinitionService) {
		this.workflowDefinitionService = workflowDefinitionService;
	}

	public WorkflowInstanceService getWorkflowInstanceService() {
		return workflowInstanceService;
	}

	public void setWorkflowInstanceService(
		WorkflowInstanceService workflowInstanceService) {
		this.workflowInstanceService = workflowInstanceService;
	}

	public WorkflowTaskService getWorkflowTaskService() {
		return workflowTaskService;
	}

	public void setWorkflowTaskService(WorkflowTaskService workflowTaskService) {
		this.workflowTaskService = workflowTaskService;
	}

	@BeanReference(name = "com.liferay.workflow.service.WorkflowComponentService.impl")
	protected WorkflowComponentService workflowComponentService;
	@BeanReference(name = "com.liferay.workflow.service.WorkflowDefinitionService.impl")
	protected WorkflowDefinitionService workflowDefinitionService;
	@BeanReference(name = "com.liferay.workflow.service.WorkflowInstanceService.impl")
	protected WorkflowInstanceService workflowInstanceService;
	@BeanReference(name = "com.liferay.workflow.service.WorkflowTaskService.impl")
	protected WorkflowTaskService workflowTaskService;
}