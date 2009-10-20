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

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.kernel.workflow.WorkflowDefinitionManager;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.workflow.edoras.service.persistence.WorkflowDefinitionUtil;

import java.util.List;
import java.util.Map;

import org.edorasframework.process.api.ProcessSystemUtil;
import org.edorasframework.process.api.service.ProcessModelDeployer;
import org.edorasframework.process.api.service.ProcessService;
import org.edorasframework.process.api.setup.ProcessSetup;

/**
 * <a href="WorkflowDefinitionManagerImpl.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Micha Kiener
 * @author Brian Wing Shun Chan
 */
public class WorkflowDefinitionManagerImpl
	extends AbstractWorkflowManager implements WorkflowDefinitionManager {

	public void deployWorkflowDefinition(
			WorkflowDefinition workflowDefinition, long callingUserId,
			boolean autoIncrementVersionNumber,
			Map<String, Object> parameters)
		throws WorkflowException {

		Class<?> setupId = AdditionalWorkflowParameterUtil.getDeclaredSetupId(
			parameters);

		ProcessSetup processSetup = ProcessSystemUtil.getSetup(setupId);

		ProcessModelDeployer processModelDeployer = processSetup.getDeployer();

		processModelDeployer.deployFromJar(
			workflowDefinition.getJar().getInputStream(), null);
	}

	public int getWorkflowDefinitionCount() throws WorkflowException {
		try {
			return WorkflowDefinitionUtil.countAll();
		}
		catch (SystemException e) {
			throw new WorkflowException(e.getMessage(), e);
		}
	}

	public int getWorkflowDefinitionCount(String workflowDefinitionName)
		throws WorkflowException {

		try {
			return WorkflowDefinitionUtil.countByName(workflowDefinitionName);
		}
		catch (SystemException e) {
			throw new WorkflowException(e.getMessage(), e);
		}
	}

	public List<WorkflowDefinition> getWorkflowDefinitions(
			int start, int end, OrderByComparator orderByComparator)
		throws WorkflowException {

		 try {
			List<com.liferay.portal.workflow.edoras.model.WorkflowDefinition>
				workflowDefinitions = WorkflowDefinitionUtil.findAll(
					start, end, orderByComparator);

			return WorkflowManagerUtil.wrapWorkflowDefinitions(
				workflowDefinitions);
		}
		catch (SystemException e) {
			throw new WorkflowException(e.getMessage(), e);
		}
	}

	public List<WorkflowDefinition> getWorkflowDefinitions(
			String workflowDefinitionName, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		 try {
			List<com.liferay.portal.workflow.edoras.model.WorkflowDefinition>
				workflowDefinitions = WorkflowDefinitionUtil.findByName(
					workflowDefinitionName, start, end, orderByComparator);

			return WorkflowManagerUtil.wrapWorkflowDefinitions(
				workflowDefinitions);
		}
		catch (SystemException e) {
			throw new WorkflowException(e.getMessage(), e);
		}
	}

	public void undeployWorkflowDefinition(
		WorkflowDefinition workflowDefinition, long callingUserId,
		Map<String, Object> parameters) {

		Class<?> setupId = AdditionalWorkflowParameterUtil.getDeclaredSetupId(
			parameters);

		ProcessSetup processSetup = ProcessSystemUtil.getSetup(setupId);

		ProcessService processService = processSetup.getService();

		Long tenantId = AdditionalWorkflowParameterUtil.getDeclaredTenantId(
			parameters);

		processService.undeployProcessModel(
			workflowDefinition.getWorkflowDefinitionName(),
			workflowDefinition.getWorkflowDefinitionVersion(), tenantId);
	}

}