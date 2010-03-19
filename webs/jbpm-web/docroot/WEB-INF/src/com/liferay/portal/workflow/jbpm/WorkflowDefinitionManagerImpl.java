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

package com.liferay.portal.workflow.jbpm;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.RequiredWorkflowDefinitionException;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.kernel.workflow.WorkflowDefinitionFileException;
import com.liferay.portal.kernel.workflow.WorkflowDefinitionManager;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.workflow.jbpm.dao.CustomSession;

import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.hibernate.Session;

import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.jbpm.db.GraphSession;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.jpdl.JpdlException;
import org.jbpm.jpdl.par.ProcessArchive;

/**
 * <a href="WorkflowDefinitionManagerImpl.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 * @author Marcellus Tavares
 */
public class WorkflowDefinitionManagerImpl
	implements WorkflowDefinitionManager {

	public WorkflowDefinition deployWorkflowDefinition(
			long companyId, long userId, InputStream inputStream)
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
		catch (JpdlException jpdle) {
			throw new WorkflowDefinitionFileException(jpdle);
		}
		finally {
			try {
				processStream.close();
			}
			catch (IOException ioe) {
			}
		}

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			jbpmContext.deployProcessDefinition(processDefinition);

			WorkflowDefinitionStatusImpl workflowDefinitionStatus =
				new WorkflowDefinitionStatusImpl(processDefinition, true);

			Session session = jbpmContext.getSession();

			session.save(workflowDefinitionStatus);
		}
		finally {
			jbpmContext.close();
		}

		return new WorkflowDefinitionImpl(processDefinition, true);
	}

	public List<WorkflowDefinition> getActiveWorkflowDefinitions(
			long companyId, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		return getWorkflowDefinitions(companyId, start, end, orderByComparator);
	}

	public List<WorkflowDefinition> getActiveWorkflowDefinitions(
			long companyId, String name, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		return getWorkflowDefinitions(
			companyId, name, start, end, orderByComparator);
	}

	public int getActiveWorkflowDefinitionCount(long companyId)
		throws WorkflowException {

		return getWorkflowDefinitionCount(companyId);
	}

	public int getActiveWorkflowDefinitionCount(long companyId, String name)
		throws WorkflowException {

		return getWorkflowDefinitionCount(companyId, name);
	}

	public WorkflowDefinition getWorkflowDefinition(
			long companyId, String name, int version)
		throws WorkflowException{

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			GraphSession graphSession = jbpmContext.getGraphSession();

			ProcessDefinition processDefinition =
				graphSession.findProcessDefinition(name, version);

			if (processDefinition != null) {
				CustomSession customSession = new CustomSession(jbpmContext);

				WorkflowDefinitionStatusImpl workflowDefinitionStatus =
					customSession.findWorkflowDefinitonStatus(
						processDefinition.getId());

				return new WorkflowDefinitionImpl(
					processDefinition, workflowDefinitionStatus.isActive());
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

	public int getWorkflowDefinitionCount(long companyId) {
		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			CustomSession customSession = new CustomSession(jbpmContext);

			return customSession.countProcessDefinitions(null, true);
		}
		finally {
			jbpmContext.close();
		}
	}

	public int getWorkflowDefinitionCount(long companyId, String name) {
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
		long companyId, int start, int end,
		OrderByComparator orderByComparator) {

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
		long companyId, String name, int start, int end,
		OrderByComparator orderByComparator) {

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
			long companyId, long userId, String name, int version)
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
						throw new RequiredWorkflowDefinitionException(
							"Process instance " + processInstance.getId() +
								" is required");
					}
				}

				CustomSession customSession = new CustomSession(jbpmContext);

				customSession.deleteWorkflowDefinitionStatus(
					processDefinition.getId());

				for (ProcessInstance processInstance : processInstances) {
					customSession.deleteWorkflowLogs(processInstance.getId());
				}

				graphSession.deleteProcessDefinition(processDefinition);
			}
		}
		finally {
			jbpmContext.close();
		}
	}

	public WorkflowDefinition updateActive(
			long companyId, long userId, String name, int version,
			boolean active)
		throws WorkflowException{

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			GraphSession graphSession = jbpmContext.getGraphSession();

			ProcessDefinition processDefinition =
				graphSession.findProcessDefinition(name, version);

			if (processDefinition != null) {
				CustomSession customSession = new CustomSession(jbpmContext);

				WorkflowDefinitionStatusImpl workflowDefinitionStatus =
					customSession.findWorkflowDefinitonStatus(
						processDefinition.getId());

				workflowDefinitionStatus.setActive(active);

				Session session = jbpmContext.getSession();

				session.update(workflowDefinitionStatus);

				return new WorkflowDefinitionImpl(
					processDefinition, workflowDefinitionStatus.isActive());
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

	protected List<WorkflowDefinition> toWorkflowDefinitions(
		List<ProcessDefinition> processDefinitions) {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			List<WorkflowDefinition> workflowDefinitions =
				new ArrayList<WorkflowDefinition>(processDefinitions.size());

			for (ProcessDefinition processDefinition : processDefinitions) {
				CustomSession customSession = new CustomSession(jbpmContext);

				WorkflowDefinitionStatusImpl workflowDefinitionStatus =
					customSession.findWorkflowDefinitonStatus(
						processDefinition.getId());

				WorkflowDefinition workflowDefinition =
					new WorkflowDefinitionImpl(
						processDefinition, workflowDefinitionStatus.isActive());

				workflowDefinitions.add(workflowDefinition);
			}

			return workflowDefinitions;
		}
		finally {
			jbpmContext.close();
		}
	}

	private JbpmConfiguration _jbpmConfiguration;

}