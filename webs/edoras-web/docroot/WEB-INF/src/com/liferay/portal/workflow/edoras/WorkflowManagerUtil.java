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

import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.kernel.workflow.WorkflowInstanceHistory;
import com.liferay.portal.kernel.workflow.WorkflowInstanceInfo;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.workflow.edoras.model.WorkflowInstance;
import com.liferay.portal.workflow.edoras.model.WorkflowLog;

import java.util.ArrayList;
import java.util.List;

import org.edorasframework.process.api.entity.ProcessInstance;

/**
 * <a href="WorkflowManagerUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Micha Kiener
 */
public class WorkflowManagerUtil {

	public static long getCompanyId(Long tenantId) {
		if (tenantId == null) {
			return CompanyConstants.SYSTEM;
		}

		return tenantId.longValue();
	}

	public static List<ProcessInstance> getFlatProcessInstances(
		ProcessInstance processInstance,
		List<ProcessInstance> processInstances) {

		if (processInstances == null) {
			processInstances = new ArrayList<ProcessInstance>();
		}

		processInstances.add(processInstance);

		List<ProcessInstance> childrenProcessInstances =
			processInstance.getChildren();

		for (ProcessInstance childProcessInstance : childrenProcessInstances) {
			getFlatProcessInstances(childProcessInstance, processInstances);
		}

		return processInstances;
	}

	public static Long getTenantId(long companyId) {
		if (companyId == CompanyConstants.SYSTEM) {
			return null;
		}

		return Long.valueOf(companyId);
	}

	public static List<WorkflowDefinition> wrapWorkflowDefinitions(
		List<com.liferay.portal.workflow.edoras.model.WorkflowDefinition>
		workflowDefinitions) {

		List<WorkflowDefinition> wrappedDefinitions =
			new ArrayList<WorkflowDefinition>(workflowDefinitions.size());

		for (com.liferay.portal.workflow.edoras.model.WorkflowDefinition
				workflowDefinition : workflowDefinitions) {

			wrappedDefinitions.add(
				new WorkflowDefinitionImpl(workflowDefinition));
		}

		return wrappedDefinitions;
	}

	public static List<WorkflowInstanceHistory> wrapWorkflowHistory(
		List<WorkflowLog> workflowLogs) {

		List<WorkflowInstanceHistory> workflowInstanceHistory =
			new ArrayList<WorkflowInstanceHistory>(workflowLogs.size());

		for (WorkflowLog workflowLog : workflowLogs) {
			workflowInstanceHistory.add(
				new WorkflowInstanceHistoryImpl(workflowLog));
		}

		return workflowInstanceHistory;
	}
	
	public static List<WorkflowInstanceInfo> wrapProcessInstances(
		List<ProcessInstance> processInstances, boolean includeChildren) {
		
		List<WorkflowInstanceInfo> workflowInstances =
			new ArrayList<WorkflowInstanceInfo>(processInstances.size());
		
		for (ProcessInstance processInstance : processInstances) {
			workflowInstances.add(new WorkflowInstanceInfoImpl(
				processInstance, includeChildren));
		}
		
		return workflowInstances;
	}
	
	public static List<WorkflowInstanceInfo> wrapWorkflowInstances(
		List<WorkflowInstance> workflowInstances, boolean includeChildren) {

		List<WorkflowInstanceInfo> wrappedWorkflowInstances =
			new ArrayList<WorkflowInstanceInfo>(workflowInstances.size());

		for (WorkflowInstance workflowInstance : workflowInstances) {
			wrappedWorkflowInstances.add(new WorkflowInstanceInfoImpl(
				workflowInstance, includeChildren));
		}

		return wrappedWorkflowInstances;
	}

}