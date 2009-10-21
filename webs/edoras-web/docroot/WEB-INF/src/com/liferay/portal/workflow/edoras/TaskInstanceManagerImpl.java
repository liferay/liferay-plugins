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
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.TaskInstanceInfo;
import com.liferay.portal.kernel.workflow.TaskInstanceManager;
import com.liferay.portal.kernel.workflow.UserCredential;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.workflow.edoras.dao.model.WorkflowTaskBridge;
import com.liferay.portal.workflow.edoras.model.WorkflowTask;
import com.liferay.portal.workflow.edoras.service.persistence.WorkflowTaskUtil;

import java.util.List;
import java.util.Map;

import org.edorasframework.process.api.dao.ProcessDao;
import org.edorasframework.process.api.engine.ProcessEngine;
import org.edorasframework.process.api.entity.ProcessInstance;
import org.edorasframework.process.api.setup.ProcessSetup;
import org.edorasframework.process.workflow.api.MutableWorkflowTask;
import org.edorasframework.process.workflow.api.WorkflowTaskHandler;

/**
 * <a href="TaskInstanceManagerImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Micha Kiener
 * @author Brian Wing Shun Chan
 */
public class TaskInstanceManagerImpl
	extends AbstractWorkflowManager implements TaskInstanceManager {

	public TaskInstanceInfo assignTaskInstanceToRole(
			long taskInstanceId, long roleId, String comment,
			Map<String, Object> attributes, long callingUserId,
			Map<String, Object> parameters)
		throws WorkflowException {

		WorkflowTaskHandler taskHandler =
			(WorkflowTaskHandler) getSetup().getTaskHandler();
		
		MutableWorkflowTask workflowTask = loadWorkflowTask(taskInstanceId);

		if (attributes != null) {
			ProcessInstance processInstance = workflowTask.getProcessInstance();
			processInstance.getAttributeMap().copyFrom(attributes);
		}
		
		taskHandler.assignTaskToRole(workflowTask, roleId);
		
		return new TaskInstanceInfoImpl(workflowTask);
	}

	public TaskInstanceInfo assignTaskInstanceToUser(
			long taskInstanceId, UserCredential userCredential, String comment,
			Map<String, Object> attributes, long callingUserId,
			Map<String, Object> parameters)
		throws WorkflowException {

		WorkflowTaskHandler taskHandler =
			(WorkflowTaskHandler) getSetup().getTaskHandler();

		MutableWorkflowTask workflowTask = loadWorkflowTask(taskInstanceId);

		if (attributes != null) {
			ProcessInstance processInstance = workflowTask.getProcessInstance();
			processInstance.getAttributeMap().copyFrom(attributes);
		}
		
		taskHandler.assignTaskToUser(workflowTask, userCredential.getUserId());

		return new TaskInstanceInfoImpl(workflowTask);
	}

	public TaskInstanceInfo completeTaskInstance(
			long taskInstanceId, long userId, String comment,
			Map<String, Object> attributes, Map<String, Object> parameters)
		throws WorkflowException {

		WorkflowTaskHandler taskHandler =
			(WorkflowTaskHandler) getSetup().getTaskHandler();

		MutableWorkflowTask workflowTask = loadWorkflowTask(taskInstanceId);

		if (attributes != null) {
			ProcessInstance processInstance = workflowTask.getProcessInstance();
			processInstance.getAttributeMap().copyFrom(attributes);
		}
		
		taskHandler.completeTask(workflowTask);

		return new TaskInstanceInfoImpl(workflowTask);
	}

	public TaskInstanceInfo completeTaskInstance(
			long taskInstanceId, long userId, String pathName,
			String comment, Map<String, Object> attributes,
			Map<String, Object> parameters)
		throws WorkflowException {

		WorkflowTaskHandler taskHandler =
			(WorkflowTaskHandler) getSetup().getTaskHandler();

		MutableWorkflowTask workflowTask = loadWorkflowTask(taskInstanceId);

		if (attributes != null) {
			ProcessInstance processInstance = workflowTask.getProcessInstance();
			processInstance.getAttributeMap().copyFrom(attributes);
		}
		
		taskHandler.completeTask(workflowTask, pathName);

		return new TaskInstanceInfoImpl(workflowTask);
	}

	public List<String> getPossibleNextPathNames(
			long taskInstanceId, long userId, Map<String, Object> parameters)
		throws WorkflowException {

		WorkflowTask workflowTask;
		try {
			workflowTask = WorkflowTaskUtil.findByPrimaryKey(taskInstanceId);
		}
		catch (Exception e) {
			throw new WorkflowException(e.getMessage(), e);
		}

		ProcessSetup processSetup = getSetup();

		ProcessDao processDao = processSetup.getProcessDao();

		ProcessInstance processInstance =
			processDao.loadProcessInstance(
				workflowTask.getWorkflowInstanceId(), false);

		ProcessEngine processEngine = processSetup.getEngine();

		return processEngine.getNextPathNames(processInstance);
	}

	public int getTaskInstanceInfoCountByCredential(
			UserCredential userCredential)
		throws WorkflowException {

		DynamicQuery dynamicQuery =
			createUserCredentialQuery(userCredential, true);

		try {
			List<Object> count =
				WorkflowTaskUtil.findWithDynamicQuery(dynamicQuery);
			return (Integer) count.get(0);
		}
		catch (SystemException se) {
			throw new WorkflowException(se.getMessage(), se);
		}
	}

	public int getTaskInstanceInfoCountByCredential(
			UserCredential userCredential, boolean completed)
		throws WorkflowException {

		DynamicQuery dynamicQuery =
			createUserCredentialQuery(userCredential, true);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("completed", completed));

		try {
			List<Object> count =
				WorkflowTaskUtil.findWithDynamicQuery(dynamicQuery);
			return (Integer) count.get(0);
		}
		catch (SystemException se) {
			throw new WorkflowException(se.getMessage(), se);
		}
	}

	public int getTaskInstanceInfoCountByRole(long roleId)
		throws WorkflowException {

		try {
			return WorkflowTaskUtil.countByRoleId(roleId);
		}
		catch (SystemException se) {
			throw new WorkflowException(se.getMessage(), se);
		}
	}

	public int getTaskInstanceInfoCountByRole(long roleId, boolean completed)
		throws WorkflowException {

		try {
			return WorkflowTaskUtil.countByR_C(roleId, completed);
		}
		catch (SystemException se) {
			throw new WorkflowException(se.getMessage(), se);
		}
	}

	public int getTaskInstanceInfoCountByUser(long userId)
		throws WorkflowException {

		try {
			return WorkflowTaskUtil.countByAssigneeUserId(userId);
		}
		catch (SystemException se) {
			throw new WorkflowException(se.getMessage(), se);
		}
	}

	public int getTaskInstanceInfoCountByUser(long userId, boolean completed)
		throws WorkflowException {

		try {
			return WorkflowTaskUtil.countByA_C(userId, completed);
		}
		catch (SystemException se) {
			throw new WorkflowException(se.getMessage(), se);
		}
	}

	public int getTaskInstanceInfoCountByWorkflowInstance(
			long workflowInstanceId)
		throws WorkflowException {

		try {
			return WorkflowTaskUtil.countByWorkflowInstanceId(workflowInstanceId);
		}
		catch (SystemException se) {
			throw new WorkflowException(se.getMessage(), se);
		}
	}

	public int getTaskInstanceInfoCountByWorkflowInstance(
			long workflowInstanceId, boolean completed)
		throws WorkflowException {

		try {
			return WorkflowTaskUtil.countByW_C(workflowInstanceId, completed);
		}
		catch (SystemException se) {
			throw new WorkflowException(se.getMessage(), se);
		}
	}

	@SuppressWarnings("unchecked")
	public List<TaskInstanceInfo> getTaskInstanceInfosByCredential(
			UserCredential userCredential, boolean completed, int start,
			int end, OrderByComparator orderByComparator)
		throws WorkflowException {

		DynamicQuery dynamicQuery =
			createUserCredentialQuery(userCredential, true);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("completed", completed));

		applyOrder(dynamicQuery, orderByComparator);
		
		try {
			List workflowTasks =
				WorkflowTaskUtil.findWithDynamicQuery(dynamicQuery, start, end);
			
			return WorkflowManagerUtil.wrapWorkflowTasks(workflowTasks);
		}
		catch (SystemException se) {
			throw new WorkflowException(se.getMessage(), se);
		}
	}

	public List<TaskInstanceInfo> getTaskInstanceInfosByCredential(
			UserCredential userCredential, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		DynamicQuery dynamicQuery =
			createUserCredentialQuery(userCredential, true);

		applyOrder(dynamicQuery, orderByComparator);

		try {
			List workflowTasks =
				WorkflowTaskUtil.findWithDynamicQuery(dynamicQuery, start, end);

			return WorkflowManagerUtil.wrapWorkflowTasks(workflowTasks);
		}
		catch (SystemException se) {
			throw new WorkflowException(se.getMessage(), se);
		}
	}

	public List<TaskInstanceInfo> getTaskInstanceInfosByRole(
			long roleId, boolean completed, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		try {
			List<WorkflowTask> taskList =
				WorkflowTaskUtil.findByR_C(
					roleId, completed, start, end, orderByComparator);
			
			return WorkflowManagerUtil.wrapWorkflowTasks(taskList);
		}
		catch (SystemException e) {
			throw new WorkflowException(e.getMessage(), e);
		}
	}

	public List<TaskInstanceInfo> getTaskInstanceInfosByRole(
			long roleId, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		try {
			List<WorkflowTask> taskList =
				WorkflowTaskUtil.findByRoleId(
					roleId, start, end, orderByComparator);

			return WorkflowManagerUtil.wrapWorkflowTasks(taskList);
		}
		catch (SystemException e) {
			throw new WorkflowException(e.getMessage(), e);
		}
	}

	public List<TaskInstanceInfo> getTaskInstanceInfosByUser(
			long userId, boolean completed, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		try {
			List<WorkflowTask> taskList =
				WorkflowTaskUtil.findByA_C(
					userId, completed, start, end, orderByComparator);

			return WorkflowManagerUtil.wrapWorkflowTasks(taskList);
		}
		catch (SystemException e) {
			throw new WorkflowException(e.getMessage(), e);
		}
	}

	public List<TaskInstanceInfo> getTaskInstanceInfosByUser(
			long userId, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		try {
			List<WorkflowTask> taskList =
				WorkflowTaskUtil.findByAssigneeUserId(
					userId, start, end, orderByComparator);

			return WorkflowManagerUtil.wrapWorkflowTasks(taskList);
		}
		catch (SystemException e) {
			throw new WorkflowException(e.getMessage(), e);
		}
	}

	public List<TaskInstanceInfo> getTaskInstanceInfosByWorkflowInstance(
			long workflowInstanceId, boolean completed, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		try {
			List<WorkflowTask> taskList =
				WorkflowTaskUtil.findByW_C(
					workflowInstanceId, completed, start, end,
					orderByComparator);

			return WorkflowManagerUtil.wrapWorkflowTasks(taskList);
		}
		catch (SystemException e) {
			throw new WorkflowException(e.getMessage(), e);
		}
	}

	public List<TaskInstanceInfo> getTaskInstanceInfosByWorkflowInstance(
			long workflowInstanceId, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		try {
			List<WorkflowTask> taskList =
				WorkflowTaskUtil.findByWorkflowInstanceId(
					workflowInstanceId, start, end, orderByComparator);

			return WorkflowManagerUtil.wrapWorkflowTasks(taskList);
		}
		catch (SystemException e) {
			throw new WorkflowException(e.getMessage(), e);
		}
	}
	
	protected void applyOrder(
		DynamicQuery dynamicQuery, OrderByComparator orderByComparator) {

		boolean ascending = orderByComparator.isAscending();
		String[] orderByFields = orderByComparator.getOrderByFields();
		
		for (String fieldName : orderByFields) {
			if (ascending) {
				dynamicQuery.addOrder(OrderFactoryUtil.asc(fieldName));
			} else {
				dynamicQuery.addOrder(OrderFactoryUtil.desc(fieldName));
			}
		}
	}
	
	protected DynamicQuery createUserCredentialQuery(
		UserCredential userCredential, boolean createCountQuery) {

		DynamicQuery dynamicQuery =
			DynamicQueryFactoryUtil.forClass(WorkflowTask.class);

		if (createCountQuery) {
			dynamicQuery.setProjection(ProjectionFactoryUtil.rowCount());
		}

		Criterion roleCriterion =
			RestrictionsFactoryUtil.in(
				"assigneeRoleId", userCredential.getRoleIds());
		Criterion userCriterion =
			RestrictionsFactoryUtil.eq(
				"assigneeUserId", userCredential.getUserId());

		dynamicQuery.add(RestrictionsFactoryUtil.or(
			userCriterion, roleCriterion));

		return dynamicQuery;
	}

	protected MutableWorkflowTask loadWorkflowTask(long workflowTaskId)
		throws WorkflowException {
		try {
			WorkflowTask workflowTask =
				WorkflowTaskUtil.findByPrimaryKey(workflowTaskId);
			
			return new WorkflowTaskBridge(workflowTask);
		}
		catch (Exception e) {
			throw new WorkflowException(e.getMessage(), e);
		}
	}
}