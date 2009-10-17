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
 *
 * @author Micha Kiener
 */
public class WorkflowEntityBridgeUtil {

	public static <T> List<Order> createOrderList(Class<?> workflowEntityClass,
		OrderComparator<T> orderComparator) {

		String[] fields = orderComparator.getFields();
		boolean[] fieldOrders = orderComparator.getFieldOrders();
		
		List<Order> orderList = new ArrayList<Order>(fields.length);
		for (int ii = 0; ii < fields.length; ii++) {
			String fieldName = fields[ii];
			
			if (fieldOrders[ii]) {
				orderList.add(OrderFactoryUtil.asc(fieldName));
			} else {
				orderList.add(OrderFactoryUtil.desc(fieldName));
			}
		}
		
		return orderList;
	}
	
	public static String getBridgedPropertyName(
		Class<?> entityClass, String propertyName) {

		Map<String, String> map = _propertyMappings.get(entityClass);
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

	public static List<? extends ProcessModelDefinition> wrapWorkflowDefinitionList(
		List<WorkflowDefinition> workflowDefinitions) {

		List<ProcessModelDefinition> processDefinitions =
			new ArrayList<ProcessModelDefinition>();

		if (workflowDefinitions == null) {
			return processDefinitions;
		}

		for (WorkflowDefinition workflowDefinition : workflowDefinitions) {
			processDefinitions.add(new WorkflowDefinitionBridge(
				workflowDefinition));
		}

		return processDefinitions;
	}

	public static List<? extends ProcessInstance> wrapWorkflowInstanceList(
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

	public static List<MutableProcessJob> wrapWorkflowJobList(
		List<WorkflowJob> workflowJobs) {

		List<MutableProcessJob> processJobs =
			new ArrayList<MutableProcessJob>();

		if (workflowJobs == null) {
			return processJobs;
		}

		for (WorkflowJob workflowJob : workflowJobs) {
			processJobs.add(new WorkflowJobBridge(workflowJob));
		}

		return processJobs;
	}
	
	public static AbstractProcessLog wrapWorkflowLog(WorkflowLog workflowLog) {
		int logEntityType = workflowLog.getLogEntityType();
		ProcessLogType logType = ProcessLogType.getLogType(logEntityType);

		switch (logType) {
			case ACTIVITY:
				return new ActivityLogBridge(workflowLog);
				
			case COMMENT:
				return new CommentLogBridge(workflowLog);
				
			case TRANSITION:
				return new TransitionLogBridge(workflowLog);
				
			case TASK:
				return new TaskLogBridge(workflowLog);
		}
		
		throw new IllegalArgumentException("The log entity type [" +
			logEntityType + "] is not valid");
	}
	
	public static List<? extends AbstractProcessLog> wrapWorkflowLogList(
		List<WorkflowLog> logList) {
		List<AbstractProcessLog> wrappedLogList =
			new ArrayList<AbstractProcessLog>();
		
		if (logList == null) {
			return wrappedLogList;
		}

		for (WorkflowLog workflowLog : logList) {
			wrappedLogList.add(wrapWorkflowLog(workflowLog));
		}

		return wrappedLogList;
	}

	public static List<? extends ProcessTask> wrapWorkflowTaskList(
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

	private static final Map<Class<?>, Map<String, String>> _propertyMappings =
		new HashMap<Class<?>, Map<String, String>>();
	private static final Map<String, Class<?>> _setupClassMap =
		new HashMap<String, Class<?>>();

	static {
		Map<String, String> workflowTaskMappings =
			new HashMap<String, String>();
		workflowTaskMappings.put("assignedGroup", "assignedGroupName");
		workflowTaskMappings.put("creationDate", "createDate");
		workflowTaskMappings.put("assignee", "assigneeUserName");

		_propertyMappings.put(WorkflowTask.class, workflowTaskMappings);
	}
}