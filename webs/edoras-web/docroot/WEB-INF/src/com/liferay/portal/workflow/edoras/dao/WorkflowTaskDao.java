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
import org.edorasframework.process.api.task.TaskDao;

/**
 * <a href="WorkflowTaskDao.java.html"><b><i>View Source</i></b></a>
 *
 * @author Micha Kiener
 */
public class WorkflowTaskDao
	extends AbstractWorkflowDao<WorkflowTask, WorkflowTaskBridge>
	implements TaskDao<WorkflowTaskBridge> {

	public void clearCache() {
		WorkflowTaskUtil.clearCache();
	}

	public <T> void delete(T workflowEntityBridge) {
		try {
			WorkflowTaskBridge workflowTaskBridge =
				(WorkflowTaskBridge)workflowEntityBridge;

			WorkflowTaskUtil.remove(workflowTaskBridge.getPrimaryKey());
		}
		catch (Exception e) {
			throw new ProcessException(e.getMessage(), e);
		}
	}

	public void deleteTasks(ProcessInstance processInstance) {
		try {
			WorkflowTaskUtil.removeByWorkflowInstanceId(
				processInstance.getPrimaryKey());
		}
		catch (Exception e) {
			throw new ProcessException(e.getMessage(), e);
		}
	}

	public <T> T find(Class<T> clazz, Object identity) {
		return (T)getTask((Long)identity);
	}

	public <T> T find(T workflowEntityBridge, Object identity) {
		return (T)getTask((Long)identity);
	}

	public List<WorkflowTaskBridge> getOpenTasks(
		QueryContext<WorkflowTaskBridge> queryContext) {

		try {
			List<WorkflowTask> workflowTasks = null;

			if ((queryContext != null) && queryContext.isPaging()) {
				workflowTasks = WorkflowTaskUtil.findByCompleted(
					false, queryContext.getBeginRowIndex(),
					queryContext.getEndRowIndex());
			}
			else {
				workflowTasks = WorkflowTaskUtil.findByCompleted(false);
			}

			return (List<WorkflowTaskBridge>)
				WorkflowEntityBridgeUtil.wrapWorkflowTasks(workflowTasks);
		}
		catch (Exception e) {
			throw new ProcessException(e.getMessage(), e);
		}
	}

	public WorkflowTaskBridge getTask(long primaryKey) {
		try {
			WorkflowTask workflowTask = WorkflowTaskUtil.findByPrimaryKey(
				primaryKey);

			WorkflowTaskBridge workflowTaskBridge = new WorkflowTaskBridge(
				workflowTask);

			return workflowTaskBridge;
		}
		catch (NoSuchWorkflowTaskException nswte) {
			return null;
		}
		catch (Exception e) {
			throw new ProcessException(e.getMessage(), e);
		}
	}

	public int getTaskCountForUser(
		UserCredential userCredential, Boolean completed) {

		// TODO

		return 0;
	}

	@SuppressWarnings("unchecked")
	public List<WorkflowTaskBridge> getTasksForInstance(
		ProcessInstance processInstance, boolean onlyOpenTasks,
		QueryContext<WorkflowTaskBridge> queryContext) {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			WorkflowTask.class);

		if (onlyOpenTasks) {
			dynamicQuery.add(RestrictionsFactoryUtil.eq("completed", false));
		}

		if (queryContext != null) {
			if (queryContext.isPaging()) {
				dynamicQuery.setLimit(
					queryContext.getBeginRowIndex(),
					queryContext.getEndRowIndex());
			}

			if (queryContext.hasOrder()) {
				List<Order> orders = WorkflowEntityBridgeUtil.createOrders(
					WorkflowTask.class, queryContext.getOrderComparator());

				for (Order order : orders) {
					dynamicQuery.addOrder(order);
				}
			}
		}

		try {
			List workflowTasks = WorkflowTaskUtil.findWithDynamicQuery(
				dynamicQuery);

			return (List<WorkflowTaskBridge>)
				WorkflowEntityBridgeUtil.wrapWorkflowTasks(workflowTasks);
		}
		catch (Exception e) {
			throw new ProcessException(e.getMessage(), e);
		}
	}

	public <T> T merge(T workflowEntityBridge) {
		return workflowEntityBridge;
	}

	public <T> void refresh(T workflowEntityBridge) {
	}

	public void reload(Object workflowEntityBridge) {
	}

	public <T> void save(T workflowEntityBridge) {
		saveInternally((WorkflowTaskBridge)workflowEntityBridge);
	}

	protected void saveThroughPersistenceUtil(
			WorkflowTaskBridge workflowTaskBridge)
		throws SystemException {

		WorkflowTaskUtil.update(workflowTaskBridge.unwrap());
	}

}