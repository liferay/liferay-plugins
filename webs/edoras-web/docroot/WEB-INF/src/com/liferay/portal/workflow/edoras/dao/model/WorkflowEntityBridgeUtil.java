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

package com.liferay.portal.workflow.edoras.dao.model;

import com.liferay.portal.kernel.dao.orm.Order;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.workflow.edoras.model.WorkflowDefinition;
import com.liferay.portal.workflow.edoras.model.WorkflowInstance;
import com.liferay.portal.workflow.edoras.model.WorkflowJob;
import com.liferay.portal.workflow.edoras.model.WorkflowLog;
import com.liferay.portal.workflow.edoras.model.WorkflowTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.edorasframework.process.api.dao.OrderComparator;
import org.edorasframework.process.api.entity.ProcessInstance;
import org.edorasframework.process.api.ex.ProcessException;
import org.edorasframework.process.api.job.MutableProcessJob;
import org.edorasframework.process.api.log.ProcessLogType;
import org.edorasframework.process.api.service.ProcessModelDefinition;
import org.edorasframework.process.api.task.ProcessTask;
import org.edorasframework.process.core.log.model.AbstractProcessLog;

/**
 * <a href="WorkflowEntityBridgeUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Micha Kiener
 */
public class WorkflowEntityBridgeUtil {

	public static <T> List<Order> createOrders(
		Class<?> clazz, OrderComparator<T> orderComparator) {

		String[] fields = orderComparator.getFields();
		boolean[] fieldOrders = orderComparator.getFieldOrders();

		List<Order> orders = new ArrayList<Order>(fields.length);

		for (int i = 0; i < fields.length; i++) {
			String fieldName = fields[i];

			if (fieldOrders[i]) {
				orders.add(OrderFactoryUtil.asc(fieldName));
			}
			else {
				orders.add(OrderFactoryUtil.desc(fieldName));
			}
		}

		return orders;
	}

	public static String getBridgedPropertyName(
		Class<?> clazz, String propertyName) {

		Map<String, String> map = _bridgedPropertyMap.get(clazz);

		if (map == null) {
			return propertyName;
		}

		return map.get(propertyName);
	}

	public static Class<?> getSetupClassForName(String setupId) {
		Class<?> setupClass = _setupClassMap.get(setupId);

		if (setupClass != null) {
			return setupClass;
		}

		try {
			ClassLoader classLoader =
				WorkflowEntityBridgeUtil.class.getClassLoader();

			setupClass = classLoader.loadClass(setupId);

			_setupClassMap.put(setupId, setupClass);
		}
		catch (ClassNotFoundException cnfe) {
			throw new ProcessException(
				"Could not load setup id class " + setupId, cnfe);
		}

		return setupClass;
	}

	public static List<? extends ProcessModelDefinition>
		wrapWorkflowDefinitions(
			List<WorkflowDefinition> workflowDefinitions) {

		List<ProcessModelDefinition> processModelDefinitions =
			new ArrayList<ProcessModelDefinition>();

		if (workflowDefinitions == null) {
			return processModelDefinitions;
		}

		for (WorkflowDefinition workflowDefinition : workflowDefinitions) {
			processModelDefinitions.add(
				new WorkflowDefinitionBridge(workflowDefinition));
		}

		return processModelDefinitions;
	}

	public static List<? extends ProcessInstance> wrapWorkflowInstances(
		List<WorkflowInstance> workflowInstances,
		WorkflowInstanceBridge workflowInstanceBridge, boolean loadChildren) {

		List<ProcessInstance> processInstances =
			new ArrayList<ProcessInstance>();

		if (workflowInstances == null) {
			return processInstances;
		}

		for (WorkflowInstance workflowInstance : workflowInstances) {
			processInstances.add(
				new WorkflowInstanceBridge(
					workflowInstance, workflowInstanceBridge, loadChildren));
		}

		return processInstances;
	}

	public static List<MutableProcessJob> wrapWorkflowJobs(
		List<WorkflowJob> workflowJobs) {

		List<MutableProcessJob> mutableProcessJobs =
			new ArrayList<MutableProcessJob>();

		if (workflowJobs == null) {
			return mutableProcessJobs;
		}

		for (WorkflowJob workflowJob : workflowJobs) {
			mutableProcessJobs.add(new WorkflowJobBridge(workflowJob));
		}

		return mutableProcessJobs;
	}

	public static AbstractProcessLog wrapWorkflowLog(WorkflowLog workflowLog) {
		int logEntityType = workflowLog.getLogEntityType();

		ProcessLogType processLogType = ProcessLogType.getLogType(
			logEntityType);

		if (processLogType.equals(ProcessLogType.ACTIVITY)) {
			return new ActivityLogBridge(workflowLog);
		}
		else if (processLogType.equals(ProcessLogType.COMMENT)) {
			return new CommentLogBridge(workflowLog);
		}
		else if (processLogType.equals(ProcessLogType.TASK)) {
			return new TaskLogBridge(workflowLog);
		}
		else if (processLogType.equals(ProcessLogType.TRANSITION)) {
			return new TransitionLogBridge(workflowLog);
		}
		else {
			throw new IllegalArgumentException(
				"The log entity type "  + logEntityType + " is invalid");
		}
	}

	public static List<? extends AbstractProcessLog> wrapWorkflowLogs(
		List<WorkflowLog> workflowLogs) {

		List<AbstractProcessLog> abstractProcessLogs =
			new ArrayList<AbstractProcessLog>();

		if (workflowLogs == null) {
			return abstractProcessLogs;
		}

		for (WorkflowLog workflowLog : workflowLogs) {
			abstractProcessLogs.add(wrapWorkflowLog(workflowLog));
		}

		return abstractProcessLogs;
	}

	public static List<? extends ProcessTask> wrapWorkflowTasks(
		List<WorkflowTask> workflowTasks) {

		List<ProcessTask> processTasks = new ArrayList<ProcessTask>();

		if (workflowTasks == null) {
			return processTasks;
		}

		for (WorkflowTask workflowTask : workflowTasks) {
			processTasks.add(new WorkflowTaskBridge(workflowTask));
		}

		return processTasks;
	}

	private static Map<Class<?>, Map<String, String>> _bridgedPropertyMap =
		new HashMap<Class<?>, Map<String, String>>();
	private static Map<String, Class<?>> _setupClassMap =
		new HashMap<String, Class<?>>();

	static {
		Map<String, String> workflowTaskMappings =
			new HashMap<String, String>();

		workflowTaskMappings.put("assignedGroup", "assignedGroupName");
		workflowTaskMappings.put("assignee", "assigneeUserName");
		workflowTaskMappings.put("creationDate", "createDate");

		_bridgedPropertyMap.put(WorkflowTask.class, workflowTaskMappings);
	}

}