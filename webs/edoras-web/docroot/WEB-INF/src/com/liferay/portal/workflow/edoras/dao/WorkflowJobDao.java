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
import com.liferay.portal.workflow.edoras.NoSuchWorkflowJobException;
import com.liferay.portal.workflow.edoras.dao.model.WorkflowEntityBridgeUtil;
import com.liferay.portal.workflow.edoras.dao.model.WorkflowJobBridge;
import com.liferay.portal.workflow.edoras.model.WorkflowJob;
import com.liferay.portal.workflow.edoras.service.persistence.WorkflowJobUtil;

import java.util.List;

import org.edorasframework.process.api.entity.ProcessInstance;
import org.edorasframework.process.api.ex.ProcessException;
import org.edorasframework.process.api.job.MutableProcessJob;
import org.edorasframework.process.api.job.queue.JobQueueDao;


/**
 * <a href="WorkflowJobDao.java.html"><b><i>View Source</i></b></a>
 *
 * @author Micha Kiener
 */
public class WorkflowJobDao
	extends AbstractWorkflowDao<WorkflowJob, WorkflowJobBridge>
	implements JobQueueDao {

	public void clearCache() {
		WorkflowJobUtil.clearCache();
	}

	public <T> void delete(T entity) {
		WorkflowJobBridge job = (WorkflowJobBridge) entity;

		try {
			WorkflowJobUtil.remove(job.getPrimaryKey());
		}
		catch (Exception e) {
			throw new ProcessException(
				"Could not delete workflow job with id [" +
					job.getPrimaryKey() + "]",
				e);
		}
	}

	public void deleteJobs(ProcessInstance instance) {
		try {
			WorkflowJobUtil.removeByWorkflowInstanceId(instance.getPrimaryKey());
		}
		catch (SystemException se) {
			throw new ProcessException(
				"Could not remove workflow jobs for workflow instance with id [" +
					instance.getPrimaryKey() + "]",
				se);
		}
	}

	public <T> T find(Class<T> clazz, Object identity) {
		Long primaryKey = (Long) identity;

		try {
			WorkflowJob workflowJob =
				WorkflowJobUtil.findByPrimaryKey(primaryKey.longValue());

			return (T) new WorkflowJobBridge(workflowJob);
		}
		catch (NoSuchWorkflowJobException e) {
			return null;
		}
		catch (SystemException se) {
			throw new ProcessException("Could not find workflow job with id [" +
				primaryKey + "]",
			se);
		}
	}

	public <T> T find(T entity, Object identity) {
		return (T) find(WorkflowJob.class, identity);
	}

	public List<MutableProcessJob> getJobs(
		ProcessInstance instance, boolean onlyOpen) {

		try {
			List<WorkflowJob> jobList =
				WorkflowJobUtil.findByWorkflowInstanceId(instance.getPrimaryKey());

			return WorkflowEntityBridgeUtil.wrapWorkflowJobList(jobList);
		}
		catch (SystemException se) {
			throw new ProcessException(
				"Could not load open jobs related to workflow instance with id [" +
					instance.getPrimaryKey() + "]",
				se);
		}
	}

	public List<MutableProcessJob> getOpenJobs() {
		try {
			List<WorkflowJob> jobList =
				WorkflowJobUtil.findAll();

			return WorkflowEntityBridgeUtil.wrapWorkflowJobList(jobList);
		}
		catch (SystemException se) {
			throw new ProcessException(
				"Could not load open jobs", se);
		}
	}

	public Class<?> getSetupId() {
		return WorkflowEntityBridgeUtil.getSetupClassForName(_setupId);
	}

	public <T> T merge(T entity) {
		return entity;
	}

	public <T> void refresh(T entity) {
	}

	public void reload(Object entity) {
	}

	public <T> void save(T workflowJob) {
		saveInternally((WorkflowJobBridge) workflowJob);
	}

	public void setSetupId(Class<?> setupId) {
		_setupId = setupId.getName();
	}
	
	protected void saveThroughPersistenceUtil(WorkflowJobBridge workflowJob)
		throws SystemException {

		WorkflowJobUtil.update(workflowJob.unwrap());
	}

	private String _setupId;
}
