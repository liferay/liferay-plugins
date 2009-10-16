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

import com.liferay.portal.kernel.resource.ResourceRetriever;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.kernel.workflow.WorkflowDefinitionManager;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.workflow.jbpm.dao.CustomSession;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.jbpm.db.GraphSession;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.jpdl.par.ProcessArchive;

/**
 * <a href="WorkflowDefinitionManagerImpl.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class WorkflowDefinitionManagerImpl
	implements WorkflowDefinitionManager {

	public void deployWorkflowDefinition(
			WorkflowDefinition workflowDefinition, long callingUserId,
			boolean autoIncrementVersionNumber, Map<String, Object> parameters)
		throws WorkflowException {

		ResourceRetriever resourceRetriever = workflowDefinition.getJar();
		String name = workflowDefinition.getWorkflowDefinitionName();
		int version = workflowDefinition.getWorkflowDefinitionVersion();

		if (!autoIncrementVersionNumber && (version < 0)) {
			throw new WorkflowException(
				"Workflow definition version number must be a positive number");
		}

		ProcessDefinition processDefinition = null;

		ZipInputStream processStream = new ZipInputStream(
			resourceRetriever.getInputStream());

		try {
			ProcessArchive processArchive = new ProcessArchive(processStream);

			processDefinition = processArchive.parseProcessDefinition();
		}
		catch (IOException ioe) {
			throw new WorkflowException(ioe);
		}
		finally {
			try {
				processStream.close();
			}
			catch (IOException ioe) {
			}
		}

		if (Validator.isNotNull(name)) {
			processDefinition.setName(name);
		}

		if (!autoIncrementVersionNumber) {
			processDefinition.setVersion(version);
		}

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			if (!autoIncrementVersionNumber) {
				GraphSession graphSession = jbpmContext.getGraphSession();

				ProcessDefinition oldProcessDefinition =
					graphSession.findProcessDefinition(name, version);

				if (oldProcessDefinition != null) {
					graphSession.deleteProcessDefinition(oldProcessDefinition);
				}

				graphSession.saveProcessDefinition(processDefinition);
			}
			else {
				jbpmContext.deployProcessDefinition(processDefinition);
			}
		}
		finally {
			jbpmContext.close();
		}
	}

	public int getWorkflowDefinitionCount() throws WorkflowException {
		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			CustomSession customSession = new CustomSession(jbpmContext);

			return customSession.countProcessDefinitions(null, true);
		}
		finally {
			jbpmContext.close();
		}
	}

	public int getWorkflowDefinitionCount(String workflowDefinitionName) {
		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			CustomSession customSession = new CustomSession(jbpmContext);

			return customSession.countProcessDefinitions(
				workflowDefinitionName, false);
		}
		finally {
			jbpmContext.close();
		}
	}

	public List<WorkflowDefinition> getWorkflowDefinitions(
		int start, int end, OrderByComparator orderByComparator) {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			CustomSession customSession = new CustomSession(jbpmContext);

			List<ProcessDefinition> processDefinitions =
				customSession.findProcessDefinitions(
					null, true, start, end, orderByComparator);

			return toWorkflowDefinitions(processDefinitions);
		}
		finally {
			jbpmContext.close();
		}
	}

	public List<WorkflowDefinition> getWorkflowDefinitions(
		String workflowDefinitionName, int start, int end,
		OrderByComparator orderByComparator) {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			CustomSession customSession = new CustomSession(jbpmContext);

			List<ProcessDefinition> processDefinitions =
				customSession.findProcessDefinitions(
					workflowDefinitionName, false, start, end,
				orderByComparator);

			return toWorkflowDefinitions(processDefinitions);
		}
		finally {
			jbpmContext.close();
		}
	}

	public void setJbpmConfiguration(JbpmConfiguration jbpmConfiguration) {
		_jbpmConfiguration = jbpmConfiguration;
	}

	public void undeployWorkflowDefinition(
			WorkflowDefinition workflowDefinition,
			long callingUserId, Map<String, Object> parameters)
		throws WorkflowException{

		String name = workflowDefinition.getWorkflowDefinitionName();
		int version = workflowDefinition.getWorkflowDefinitionVersion();

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			GraphSession graphSession = jbpmContext.getGraphSession();
			ProcessDefinition processDefinition =
				graphSession.findProcessDefinition(name, version);
			if (processDefinition != null) {
				List<ProcessInstance> processInstances =
					graphSession.findProcessInstances(
						processDefinition.getId());
				for(ProcessInstance processInstance : processInstances) {
					if (!processInstance.hasEnded()) {
						throw new WorkflowException(
							"There are stilling running process instances");
					}
				}
				graphSession.deleteProcessDefinition(processDefinition);
			}
		}
		finally {
			jbpmContext.close();
		}

	}

	protected List<WorkflowDefinition> toWorkflowDefinitions(
		List<ProcessDefinition> processDefinitions) {

		List<WorkflowDefinition> workflowDefinitions =
			new ArrayList<WorkflowDefinition>(processDefinitions.size());

		for (ProcessDefinition processDefinition : processDefinitions) {
			WorkflowDefinition workflowDefinition = new WorkflowDefinitionImpl(
				processDefinition);

			workflowDefinitions.add(workflowDefinition);
		}

		return workflowDefinitions;
	}

	private JbpmConfiguration _jbpmConfiguration;

}