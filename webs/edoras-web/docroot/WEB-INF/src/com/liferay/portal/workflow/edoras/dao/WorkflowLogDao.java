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
 * <p>
 * TODO: add Class-Description here ...
 * </p>
 *
 * @author Micha Kiener
 *
 */
public class WorkflowLogDao
	extends AbstractWorkflowDao<WorkflowLog, WorkflowEntityBridge<WorkflowLog>>
	implements LogDao {

	public void clearCache() {
		WorkflowLogUtil.clearCache();
	}

	public <T> void delete(T entity) {
		WorkflowEntity workflowLog = (WorkflowEntity) entity;
		
		try {
			WorkflowLogUtil.remove(workflowLog.getPrimaryKey());
		}
		catch (Exception e) {
			throw new ProcessException(
				"Could not delete process log entry with id [" +
					workflowLog.getPrimaryKey() + "]",
				e);
		}
	}

	public <T> T find(Class<T> clazz, Object identity) {
		Long primaryKey = (Long) identity;

		try {
			WorkflowLog workflowLog =
				WorkflowLogUtil.findByPrimaryKey(primaryKey);

			return (T) WorkflowEntityBridgeUtil.wrapWorkflowLog(workflowLog);
		}
		catch (NoSuchWorkflowLogException e) {
			return null;
		}
		catch (SystemException se) {
			throw new ProcessException("Could not load log entry with id [" +
				primaryKey + "]",
			se);
		}
	}

	public <T> T find(T workflowLog, Object identity) {
		return (T) find(workflowLog.getClass(), identity);
	}

	public <T> T merge(T workflowLog) {
		return workflowLog;
	}

	public <T> void refresh(T entity) {
	}

	public void reload(Object entity) {
	}

	public <T> void save(T workflowLog) {
		saveInternally((WorkflowEntityBridge<WorkflowLog>) workflowLog);
	}

	protected void saveThroughPersistenceUtil(
		WorkflowEntityBridge<WorkflowLog> workflowLog)
		throws SystemException {

		WorkflowLogUtil.update(workflowLog.unwrap());
	}

	public void deleteLogs(ProcessInstance instance) {
		try {
			WorkflowLogUtil.removeByWorkflowInstanceId(instance.getPrimaryKey());
		}
		catch (SystemException se) {
			throw new ProcessException(
				"Could not delete log entries related to workflow instance with id [" +
					instance.getPrimaryKey() + "]",
				se);
		}
	}

	public List<? extends ActivityLog> getProcessActivities(
		ProcessInstance instance) {

		return (List<? extends ActivityLog>) getProcessLogs(
			ProcessLogType.ACTIVITY, instance);
	}

	public List<? extends CommentLog> getProcessComments(
		ProcessInstance instance, Integer commentType) {

		List<WorkflowLog> logList = null;
		try {
			if (commentType == null) {
				logList =
					WorkflowLogUtil.findByW_T(
						instance.getPrimaryKey(),
						ProcessLogType.COMMENT.getId());
			}
			else {
				logList =
					WorkflowLogUtil.findByW_T_T(
						instance.getPrimaryKey(),
						ProcessLogType.COMMENT.getId(), commentType.intValue());
			}

			return (List<? extends CommentLog>) WorkflowEntityBridgeUtil.wrapWorkflowLogList(logList);
		}
		catch (SystemException se) {
			throw new ProcessException(
				"Could not load comment logs for workflow instance with id [" +
					instance.getPrimaryKey() + "]", se);
		}
	}

	public List<? extends ProcessLog> getProcessLogs(
		ProcessInstance instance, boolean includingChildren,
		LogEntryFilter filter) {

        return LogUtil.getProcessLogs(this, instance, includingChildren, filter);
	}

	public List<? extends ProcessLog> getProcessLogs(ProcessInstance instance) {

		try {
			List<WorkflowLog> logList =
				WorkflowLogUtil.findByWorkflowInstanceId(instance.getPrimaryKey());

			return WorkflowEntityBridgeUtil.wrapWorkflowLogList(logList);
		}
		catch (SystemException se) {
			throw new ProcessException(
				"Could not load logs for workflow instance with id [" +
					instance.getPrimaryKey() + "]", se);
		}
	}

	public List<? extends ProcessLog> getProcessLogs(
		ProcessLogType type, ProcessInstance instance) {

		try {
			List<WorkflowLog> logList =
				WorkflowLogUtil.findByW_T(
					instance.getPrimaryKey(), type.getId());

			return WorkflowEntityBridgeUtil.wrapWorkflowLogList(logList);
		}
		catch (SystemException se) {
			throw new ProcessException(
				"Could not load [" + type +
				"] logs for workflow instance with id [" +
					instance.getPrimaryKey() + "]",
			se);
		}
	}

	public List<? extends TaskLog> getProcessTaskLogs(ProcessInstance instance) {

		return (List<? extends TaskLog>) getProcessLogs(
			ProcessLogType.TASK, instance);
	}

	public List<? extends TransitionLog> getProcessTransitions(
		ProcessInstance instance) {

		return (List<? extends TransitionLog>) getProcessLogs(
			ProcessLogType.TRANSITION, instance);
	}

}
