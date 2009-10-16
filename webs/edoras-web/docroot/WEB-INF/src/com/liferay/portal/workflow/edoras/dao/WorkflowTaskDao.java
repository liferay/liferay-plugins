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

package com.liferay.portal.workflow.edoras.dao;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Order;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.workflow.edoras.NoSuchWorkflowTaskException;
import com.liferay.portal.workflow.edoras.dao.model.WorkflowEntityBridgeUtil;
import com.liferay.portal.workflow.edoras.dao.model.WorkflowTaskBridge;
import com.liferay.portal.workflow.edoras.model.WorkflowTask;
import com.liferay.portal.workflow.edoras.service.persistence.WorkflowTaskUtil;

import java.util.List;

import org.edorasframework.process.api.dao.QueryContext;
import org.edorasframework.process.api.entity.ProcessInstance;
import org.edorasframework.process.api.ex.ProcessException;
import org.edorasframework.process.api.security.UserCredential;
import org.edorasframework.process.api.task.ProcessTask;
import org.edorasframework.process.api.task.TaskDao;


/**
 * <a href="WorkflowTaskDao.java.html"><b><i>View Source</i></b></a>
 *
 * @author Micha Kiener
 */
public class WorkflowTaskDao extends AbstractWorkflowDao<WorkflowTaskBridge>
	implements TaskDao<WorkflowTaskBridge> {

	public void clearCache() {
		WorkflowTaskUtil.clearCache();
	}

	public <T> void delete(T workflowTask) {
		ProcessTask processTask = (ProcessTask) workflowTask;
		
		try {
			WorkflowTaskUtil.remove(processTask.getPrimaryKey());
		}
		catch (Exception e) {
			throw new ProcessException(
				"Could not delete workflow task with id [" +
					processTask.getPrimaryKey() + "]",
				e);
		}
	}

	public void deleteTasks(ProcessInstance processInstance) {
		try {
			WorkflowTaskUtil.removeByWorkflowInstanceId(
				processInstance.getPrimaryKey());
		}
		catch (SystemException e) {
			throw new ProcessException(
				"Could not remove tasks for process instance with id [" +
					processInstance.getPrimaryKey() + "]",
				e);
		}
	}

	public <T> T find(Class<T> entityClass, Object identity) {
		Long primaryKey = (Long) identity;
		return (T) getTask(primaryKey.longValue());
	}

	public <T> T find(T entity, Object identity) {
		Long primaryKey = (Long) identity;
		return (T) getTask(primaryKey.longValue());
	}

	public List<WorkflowTaskBridge> getOpenTasks(
		QueryContext<WorkflowTaskBridge> queryContext) {

		try {
			List<WorkflowTask> taskList = null;
			if (queryContext != null && queryContext.isPaging()) {
				taskList =
					WorkflowTaskUtil.findByCompleted(false,
						queryContext.getBeginRowIndex(),
						queryContext.getEndRowIndex());
			}
			else {
				taskList = WorkflowTaskUtil.findByCompleted(false);
			}
			
			return (List<WorkflowTaskBridge>) WorkflowEntityBridgeUtil.wrapWorkflowTaskList(taskList);
		}
		catch (SystemException se) {
			throw new ProcessException("Could not load open tasks", se);
		}
	}

	public WorkflowTaskBridge getTask(long primaryKey) {
		try {
			WorkflowTask workflowTask =
				WorkflowTaskUtil.findByPrimaryKey(primaryKey);
			
			WorkflowTaskBridge workflowTaskBridge =
				new WorkflowTaskBridge(workflowTask);
			
			return workflowTaskBridge;
		}
		catch (NoSuchWorkflowTaskException e) {
			return null;
		}
		catch (SystemException se) {
			throw new ProcessException(
				"Could not load workflow task with id [" + primaryKey + "]",
			se);
		}
	}

	public int getTaskCountForUser(
		UserCredential userCredential, Boolean completed) {

		// TODO Auto-generated method stub
		return 0;
	}

	public List<WorkflowTaskBridge> getTasksForInstance(
		ProcessInstance processInstance, boolean onlyOpenTasks,
		QueryContext<WorkflowTaskBridge> queryContext) {

		DynamicQuery query =
			DynamicQueryFactoryUtil.forClass(WorkflowTask.class);
		
		if (onlyOpenTasks) {
			query.add(RestrictionsFactoryUtil.eq("completed", false));
		}
		
		if (queryContext != null) {
			if (queryContext.isPaging()) {
				query.setLimit(
					queryContext.getBeginRowIndex(),
					queryContext.getEndRowIndex());
			}
			
			
			if (queryContext.hasOrder()) {
				List<Order> orderList =
					WorkflowEntityBridgeUtil.createOrderList(
						WorkflowTask.class, queryContext.getOrderComparator());
				
				for (Order order : orderList) {
					query.addOrder(order);
				}
			}
		}
		
		try {
			List taskList = WorkflowTaskUtil.findWithDynamicQuery(query);
			return (List<WorkflowTaskBridge>) WorkflowEntityBridgeUtil.wrapWorkflowTaskList(taskList);
		}
		catch (SystemException se) {
			throw new ProcessException(
				"Could not find tasks for workflow instance with id [" +
					processInstance.getPrimaryKey() + "]",
				se);
		}
	}

	public <T> T merge(T workflowTask) {
		return workflowTask;
	}

	public <T> void refresh(T workflowTask) {
		
	}

	public void reload(Object workflowTask) {
		
	}

	public <T> void save(T workflowTask) {
		WorkflowTaskBridge workflowTaskBridge =
			(WorkflowTaskBridge) workflowTask;

		if (super.checkAndInitializeNewInstance(workflowTaskBridge)) {
			workflowTaskBridge.initializeForInsert();
		}
		else {
			workflowTaskBridge.initializeForUpdate();
		}

		try {
			WorkflowTaskUtil.update(workflowTaskBridge.unwrap());
		}
		catch (SystemException se) {
			throw new ProcessException(
				"Could not update workflow task",
			se);
		}	
	}

}
