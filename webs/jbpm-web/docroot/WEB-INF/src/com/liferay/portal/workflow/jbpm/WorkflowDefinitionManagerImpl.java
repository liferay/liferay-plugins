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

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.kernel.workflow.WorkflowDefinitionImpl;

import com.liferay.portal.kernel.workflow.WorkflowDefinitionManager;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.workflow.jbpm.dao.CustomSession;

import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;
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

	public WorkflowDefinition deployWorkflowDefinition(
			long userId, String name, InputStream inputStream)
		throws WorkflowException {

		ProcessDefinition processDefinition = null;

		ZipInputStream processStream = new ZipInputStream(inputStream);

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

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			jbpmContext.deployProcessDefinition(processDefinition);
		}
		finally {
			jbpmContext.close();
		}

		return new WorkflowDefinitionImpl(
			processDefinition.getName(), processDefinition.getVersion());
	}

	public WorkflowDefinition getWorkflowDefinition(String name, int version)
		throws WorkflowException{

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			GraphSession graphSession = jbpmContext.getGraphSession();

			ProcessDefinition processDefinition =
				graphSession.findProcessDefinition(name, version);

			if (processDefinition != null) {
				return new WorkflowDefinitionImpl(name, version);
			}
			else {
				throw new WorkflowException(
					"No workflow definition with name " + name +
						" and version " + version);
			}
		}
		finally {
			jbpmContext.close();
		}
	}

	public int getWorkflowDefinitionCount() {
		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			CustomSession customSession = new CustomSession(jbpmContext);

			return customSession.countProcessDefinitions(null, true);
		}
		finally {
			jbpmContext.close();
		}
	}

	public int getWorkflowDefinitionCount(String name) {
		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			CustomSession customSession = new CustomSession(jbpmContext);

			return customSession.countProcessDefinitions(
				name, false);
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
		String name, int start, int end, OrderByComparator orderByComparator) {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			CustomSession customSession = new CustomSession(jbpmContext);

			List<ProcessDefinition> processDefinitions =
				customSession.findProcessDefinitions(
					name, false, start, end,
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
			long userId, String name, int version)
		throws WorkflowException{

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			GraphSession graphSession = jbpmContext.getGraphSession();

			ProcessDefinition processDefinition =
				graphSession.findProcessDefinition(name, version);

			if (processDefinition != null) {
				List<ProcessInstance> processInstances =
					graphSession.findProcessInstances(
						processDefinition.getId());

				for (ProcessInstance processInstance : processInstances) {
					if (!processInstance.hasEnded()) {
						throw new WorkflowException(
							"Process instance " + processInstance.getId() +
								" is required");
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
				processDefinition.getName(), processDefinition.getVersion());

			workflowDefinitions.add(workflowDefinition);
		}

		return workflowDefinitions;
	}

	private JbpmConfiguration _jbpmConfiguration;

}