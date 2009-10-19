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
import com.liferay.portal.workflow.edoras.NoSuchWorkflowLogException;
import com.liferay.portal.workflow.edoras.dao.model.WorkflowEntity;
import com.liferay.portal.workflow.edoras.dao.model.WorkflowEntityBridge;
import com.liferay.portal.workflow.edoras.dao.model.WorkflowEntityBridgeUtil;
import com.liferay.portal.workflow.edoras.model.WorkflowLog;
import com.liferay.portal.workflow.edoras.service.persistence.WorkflowLogUtil;

import java.util.List;

import org.edorasframework.process.api.entity.ProcessInstance;
import org.edorasframework.process.api.ex.ProcessException;
import org.edorasframework.process.api.log.ActivityLog;
import org.edorasframework.process.api.log.CommentLog;
import org.edorasframework.process.api.log.LogDao;
import org.edorasframework.process.api.log.LogEntryFilter;
import org.edorasframework.process.api.log.ProcessLog;
import org.edorasframework.process.api.log.ProcessLogType;
import org.edorasframework.process.api.log.TaskLog;
import org.edorasframework.process.api.log.TransitionLog;
import org.edorasframework.process.core.log.dao.LogUtil;

/**
 * <a href="WorkflowLogDao.java.html"><b><i>View Source</i></b></a>
 *
 * @author Micha Kiener
 */
public class WorkflowLogDao
	extends AbstractWorkflowDao<WorkflowLog, WorkflowEntityBridge<WorkflowLog>>
	implements LogDao {

	public void clearCache() {
		WorkflowLogUtil.clearCache();
	}

	public <T> void delete(T workflowEntityBridge) {
		try {
			WorkflowEntity workflowEntity =
				(WorkflowEntity)workflowEntityBridge;

			WorkflowLogUtil.remove(workflowEntity.getPrimaryKey());
		}
		catch (Exception e) {
			throw new ProcessException(e.getMessage(), e);
		}
	}

	public <T> T find(Class<T> clazz, Object identity) {
		try {
			long primaryKey = (Long)identity;

			WorkflowLog workflowLog = WorkflowLogUtil.findByPrimaryKey(
				primaryKey);

			return (T)WorkflowEntityBridgeUtil.wrapWorkflowLog(workflowLog);
		}
		catch (NoSuchWorkflowLogException nswle) {
			return null;
		}
		catch (Exception e) {
			throw new ProcessException(e.getMessage(), e);
		}
	}

	public <T> T find(T workflowEntityBridge, Object identity) {
		return (T)find(workflowEntityBridge.getClass(), identity);
	}

	public <T> T merge(T workflowEntityBridge) {
		return workflowEntityBridge;
	}

	public <T> void refresh(T workflowEntityBridge) {
	}

	public void reload(Object workflowEntityBridge) {
	}

	public <T> void save(T workflowEntityBridge) {
		saveInternally((WorkflowEntityBridge<WorkflowLog>)workflowEntityBridge);
	}

	protected void saveThroughPersistenceUtil(
			WorkflowEntityBridge<WorkflowLog> workflowEntityBridge)
		throws SystemException {

		WorkflowLogUtil.update(workflowEntityBridge.unwrap());
	}

	public void deleteLogs(ProcessInstance processInstance) {
		try {
			WorkflowLogUtil.removeByWorkflowInstanceId(
				processInstance.getPrimaryKey());
		}
		catch (Exception e) {
			throw new ProcessException(e.getMessage(), e);
		}
	}

	public List<? extends ActivityLog> getProcessActivities(
		ProcessInstance processInstance) {

		return (List<? extends ActivityLog>)getProcessLogs(
			ProcessLogType.ACTIVITY, processInstance);
	}

	public List<? extends CommentLog> getProcessComments(
		ProcessInstance processInstance, Integer commentType) {

		try {
			List<WorkflowLog> workflowLogs = null;

			if (commentType == null) {
				workflowLogs = WorkflowLogUtil.findByW_T(
					processInstance.getPrimaryKey(),
					ProcessLogType.COMMENT.getId());
			}
			else {
				workflowLogs = WorkflowLogUtil.findByW_T_T(
					processInstance.getPrimaryKey(),
					ProcessLogType.COMMENT.getId(), commentType.intValue());
			}

			return (List<? extends CommentLog>)
				WorkflowEntityBridgeUtil.wrapWorkflowLogs(workflowLogs);
		}
		catch (Exception e) {
			throw new ProcessException(e.getMessage(), e);
		}
	}

	public List<? extends ProcessLog> getProcessLogs(
		ProcessInstance processInstance, boolean includingChildren,
		LogEntryFilter logEntryFilter) {

		return LogUtil.getProcessLogs(
			this, processInstance, includingChildren, logEntryFilter);
	}

	public List<? extends ProcessLog> getProcessLogs(
		ProcessInstance processInstance) {

		try {
			List<WorkflowLog> workflowLogs =
				WorkflowLogUtil.findByWorkflowInstanceId(
					processInstance.getPrimaryKey());

			return WorkflowEntityBridgeUtil.wrapWorkflowLogs(workflowLogs);
		}
		catch (Exception e) {
			throw new ProcessException(e.getMessage(), e);
		}
	}

	public List<? extends ProcessLog> getProcessLogs(
		ProcessLogType processLogType, ProcessInstance processInstance) {

		try {
			List<WorkflowLog> workflowLogs = WorkflowLogUtil.findByW_T(
				processInstance.getPrimaryKey(), processLogType.getId());

			return WorkflowEntityBridgeUtil.wrapWorkflowLogs(workflowLogs);
		}
		catch (Exception e) {
			throw new ProcessException(e.getMessage(), e);
		}
	}

	public List<? extends TaskLog> getProcessTaskLogs(
		ProcessInstance processInstance) {

		return (List<? extends TaskLog>)getProcessLogs(
			ProcessLogType.TASK, processInstance);
	}

	public List<? extends TransitionLog> getProcessTransitions(
		ProcessInstance processInstance) {

		return (List<? extends TransitionLog>)getProcessLogs(
			ProcessLogType.TRANSITION, processInstance);
	}

}