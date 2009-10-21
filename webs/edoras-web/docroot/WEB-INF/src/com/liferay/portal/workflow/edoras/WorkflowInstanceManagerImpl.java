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

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.kernel.workflow.WorkflowInstanceHistory;
import com.liferay.portal.kernel.workflow.WorkflowInstanceInfo;
import com.liferay.portal.kernel.workflow.WorkflowInstanceManager;
import com.liferay.portal.workflow.edoras.model.WorkflowInstance;
import com.liferay.portal.workflow.edoras.model.WorkflowLog;
import com.liferay.portal.workflow.edoras.service.persistence.WorkflowInstanceUtil;
import com.liferay.portal.workflow.edoras.service.persistence.WorkflowLogUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.edorasframework.process.api.ProcessSystemUtil;
import org.edorasframework.process.api.dao.ProcessDao;
import org.edorasframework.process.api.engine.ProcessEngine;
import org.edorasframework.process.api.entity.MutableProcessInstance;
import org.edorasframework.process.api.entity.ProcessInstance;
import org.edorasframework.process.api.model.Activity;
import org.edorasframework.process.api.session.ProcessSession;
import org.edorasframework.process.api.setup.ProcessSetup;
import org.edorasframework.process.core.util.ProcessUtil;

/**
 * <a href="WorkflowInstanceManagerImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Micha Kiener
 * @author Brian Wing Shun Chan
 */
public class WorkflowInstanceManagerImpl
	extends AbstractWorkflowManager implements WorkflowInstanceManager {

	public WorkflowInstanceInfo addContextInformation(
		long workflowInstanceId, Map<String, Object> context) {

		ProcessSetup processSetup = getSetup();

		ProcessEngine processEngine = processSetup.getEngine();

		processEngine.addProcessInstanceAttributes(workflowInstanceId, context);

		return getWorkflowInstanceInfo(workflowInstanceId, false);
	}

	public List<String> getPossibleNextActivityNames(
		long workflowInstanceId, long userId, Map<String, Object> parameters) {

		ProcessSetup processSetup = getSetup();

		ProcessDao processDao = processSetup.getProcessDao();

		ProcessInstance processInstance = processDao.loadProcessInstance(
			workflowInstanceId, false);

		List<String> activityNames = new ArrayList<String>();

		ProcessEngine processEngine = processSetup.getEngine();

		List<Activity> activities = processEngine.getNextActivities(
			processInstance, true);

		for (Activity activity : activities) {
			activityNames.add(activity.getName());
		}

		return activityNames;
	}

	public List<String> getPossibleNextPathNames(
		long workflowInstanceId, long userId, Map<String, Object> parameters) {

		ProcessSetup processSetup = getSetup();

		ProcessDao processDao = processSetup.getProcessDao();

		ProcessInstance processInstance = processDao.loadProcessInstance(
			workflowInstanceId, false);

		ProcessEngine processEngine = processSetup.getEngine();

		return processEngine.getNextPathNames(processInstance);
	}

	public List<WorkflowInstanceHistory> getWorkflowInstanceHistory(
			long workflowInstanceId, boolean includeChildren, int start,
			int end, OrderByComparator orderByComparator)
		throws WorkflowException {

		if (!includeChildren) {
			try {
				List<WorkflowLog> workflowLogs =
					WorkflowLogUtil.findByWorkflowInstanceId(
						workflowInstanceId, start, end, orderByComparator);

				return WorkflowManagerUtil.wrapWorkflowHistory(workflowLogs);
			}
			catch (Exception e) {
				throw new WorkflowException(e.getMessage(), e);
			}
		}

		ProcessSetup processSetup = getSetup();

		ProcessDao processDao = processSetup.getProcessDao();

		ProcessInstance processInstance = processDao.loadProcessInstance(
			workflowInstanceId, true);

		List<ProcessInstance> processInstances =
			new ArrayList<ProcessInstance>();

		WorkflowManagerUtil.getFlatProcessInstances(
			processInstance, processInstances);

		List<WorkflowLog> workflowLogs = new ArrayList<WorkflowLog>();

		try {
			for (ProcessInstance curProcessInstance : processInstances) {
				List<WorkflowLog> curWorkflowLogs =
					WorkflowLogUtil.findByWorkflowInstanceId(
							curProcessInstance.getPrimaryKey());

				workflowLogs.addAll(curWorkflowLogs);
			}

			List<WorkflowInstanceHistory> workflowInstanceHistory =
				WorkflowManagerUtil.wrapWorkflowHistory(workflowLogs);

			if (orderByComparator != null) {
				Collections.sort(workflowInstanceHistory, orderByComparator);
			}

			if ((start != QueryUtil.ALL_POS) && (end != QueryUtil.ALL_POS)) {
				workflowInstanceHistory = ListUtil.subList(
					workflowInstanceHistory, start, end);
			}

			return workflowInstanceHistory;
		}
		catch (Exception e) {
			throw new WorkflowException(e.getMessage(), e);
		}
	}

	public int getWorkflowInstanceHistoryCount(
			long workflowInstanceId, boolean includeChildren)
		throws WorkflowException {

		try {
			if (!includeChildren) {
				return WorkflowLogUtil.countByWorkflowInstanceId(
					workflowInstanceId);
			}

			ProcessSetup processSetup = getSetup();

			ProcessDao processDao = processSetup.getProcessDao();

			ProcessInstance processInstance = processDao.loadProcessInstance(
				workflowInstanceId, true);

			List<ProcessInstance> processInstances =
				new ArrayList<ProcessInstance>();

			WorkflowManagerUtil.getFlatProcessInstances(
				processInstance, processInstances);

			int count = 0;

			for (ProcessInstance instance : processInstances) {
				count += WorkflowLogUtil.countByWorkflowInstanceId(
					instance.getPrimaryKey());
			}

			return count;
		}
		catch (Exception e) {
			throw new WorkflowException(e.getMessage(), e);
		}
	}

	public WorkflowInstanceInfo getWorkflowInstanceInfo(
		long workflowInstanceId, boolean retrieveChildrenInfo) {

		ProcessSetup processSetup = getSetup();

		ProcessDao processDao = processSetup.getProcessDao();

		ProcessInstance processInstance = processDao.loadProcessInstance(
			workflowInstanceId, retrieveChildrenInfo);

		return new WorkflowInstanceInfoImpl(
			processInstance, retrieveChildrenInfo);
	}

	public WorkflowInstanceInfo getWorkflowInstanceInfo(
		String relationType, long relationId, boolean retrieveChildrenInfo) {

		ProcessSetup processSetup = getSetup();

		ProcessDao processDao = processSetup.getProcessDao();

		List<? extends MutableProcessInstance> mutableProcessInstances =
			processDao.loadProcessInstances(
				relationType, relationId, retrieveChildrenInfo);

		if (!mutableProcessInstances.isEmpty()) {
			return new WorkflowInstanceInfoImpl(
				mutableProcessInstances.get(0), retrieveChildrenInfo);
		}

		return null;
	}

	public int getWorkflowInstanceInfoCount(
			String workflowDefinitionName, Integer workflowDefinitionVersion)
		throws WorkflowException {

		try {
			if (workflowDefinitionVersion == null) {
				return WorkflowInstanceUtil.countByWorkflowDefinitionName(
					workflowDefinitionName);
			}
			else {
				return WorkflowInstanceUtil.countByN_V(
					workflowDefinitionName, workflowDefinitionVersion);
			}
		}
		catch (Exception e) {
			throw new WorkflowException(e.getMessage(), e);
		}
	}

	public int getWorkflowInstanceInfoCount(
			String workflowDefinitionName, Integer workflowDefinitionVersion,
			boolean completed)
		throws WorkflowException {

		try {
			if (workflowDefinitionVersion == null) {
				return WorkflowInstanceUtil.countByN_F(
					workflowDefinitionName, completed);
			}
			else {
				return WorkflowInstanceUtil.countByN_V_F(
					workflowDefinitionName, workflowDefinitionVersion,
					completed);
			}
		}
		catch (Exception e) {
			throw new WorkflowException(e.getMessage(), e);
		}
	}

	public int getWorkflowInstanceInfoCount(
			String relationType, long relationId)
		throws WorkflowException {

		try {
			return WorkflowInstanceUtil.countByC_C(relationType, relationId);
		}
		catch (Exception e) {
			throw new WorkflowException(e.getMessage(), e);
		}
	}

	public List<WorkflowInstanceInfo> getWorkflowInstanceInfos(
			String workflowDefinitionName, Integer workflowDefinitionVersion,
			boolean completed, boolean retrieveChildrenInfo, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		try {
			List<WorkflowInstance> workflowInstances = null;

			if (workflowDefinitionVersion == null) {
				workflowInstances = WorkflowInstanceUtil.findByN_F(
					workflowDefinitionName, completed, start, end,
					orderByComparator);
			}
			else {
				workflowInstances = WorkflowInstanceUtil.findByN_V_F(
					workflowDefinitionName, workflowDefinitionVersion,
					completed, start, end, orderByComparator);
			}

			return WorkflowManagerUtil.wrapWorkflowInstances(
				workflowInstances, retrieveChildrenInfo);
		}
		catch (Exception e) {
			throw new WorkflowException(e.getMessage(), e);
		}
	}

	public List<WorkflowInstanceInfo> getWorkflowInstanceInfos(
			String workflowDefinitionName, Integer workflowDefinitionVersion,
			boolean retrieveChildrenInfo, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		try {
			List<WorkflowInstance> workflowInstances = null;

			if (workflowDefinitionVersion == null) {
				workflowInstances =
					WorkflowInstanceUtil.findByWorkflowDefinitionName(
						workflowDefinitionName, start, end, orderByComparator);
			}
			else {
				workflowInstances = WorkflowInstanceUtil.findByN_V(
					workflowDefinitionName, workflowDefinitionVersion,
					start, end, orderByComparator);
			}

			return WorkflowManagerUtil.wrapWorkflowInstances(
				workflowInstances, retrieveChildrenInfo);
		}
		catch (Exception e) {
			throw new WorkflowException(e.getMessage(), e);
		}
	}

	public List<WorkflowInstanceInfo> getWorkflowInstanceInfos(
			String relationType, long relationId, boolean retrieveChildrenInfo,
			int start, int end, OrderByComparator orderByComparator)
		throws WorkflowException {

		try {
			List<WorkflowInstance> workflowInstances =
				WorkflowInstanceUtil.findByC_C(
					relationType, relationId, start, end, orderByComparator);

			return WorkflowManagerUtil.wrapWorkflowInstances(
				workflowInstances, retrieveChildrenInfo);
		}
		catch (Exception e) {
			throw new WorkflowException(e.getMessage(), e);
		}
	}

	public void removeWorkflowInstance(long workflowInstanceId) {
		ProcessSession processSession = ProcessSystemUtil.getCurrentSession();

		ProcessUtil.removeProcessInstance(workflowInstanceId, processSession);
	}

	public WorkflowInstanceInfo signalWorkflowInstance(
		long workflowInstanceId, Map<String, Object> attributes,
		long callingUserId, Map<String, Object> parameters) {

		ProcessSession processSession = ProcessSystemUtil.getCurrentSession();

		ProcessDao processDao = processSession.getProcessDao();

		ProcessInstance processInstance = processDao.loadProcessInstance(
			workflowInstanceId, true);

		if (attributes != null) {
			processInstance.getAttributeMap().copyFrom(attributes);
		}

		ProcessEngine processEngine = processSession.getEngine();

		processEngine.executeNextActivity(processInstance);

		return new WorkflowInstanceInfoImpl(processInstance, true);
	}

	public WorkflowInstanceInfo signalWorkflowInstanceByActivity(
		long workflowInstanceId, String activityName,
		Map<String, Object> attributes, long callingUserId,
		Map<String, Object> parameters) {

		ProcessSession processSession = ProcessSystemUtil.getCurrentSession();

		ProcessDao processDao = processSession.getProcessDao();

		ProcessInstance processInstance = processDao.loadProcessInstance(
			workflowInstanceId, true);

		if (attributes != null) {
			processInstance.getAttributeMap().copyFrom(attributes);
		}

		ProcessEngine processEngine = processSession.getEngine();

		processEngine.executeActivity(activityName, processInstance);

		return new WorkflowInstanceInfoImpl(processInstance, true);

	}

	public WorkflowInstanceInfo signalWorkflowInstanceByPath(
		long workflowInstanceId, String pathName,
		Map<String, Object> attributes, long callingUserId,
		Map<String, Object> parameters) {

		ProcessSession processSession = ProcessSystemUtil.getCurrentSession();

		ProcessDao processDao = processSession.getProcessDao();

		ProcessInstance processInstance = processDao.loadProcessInstance(
			workflowInstanceId, true);

		if (attributes != null) {
			processInstance.getAttributeMap().copyFrom(attributes);
		}

		ProcessEngine processEngine = processSession.getEngine();

		processEngine.executeActivityByPath(pathName, processInstance);

		return new WorkflowInstanceInfoImpl(processInstance, true);
	}

	public WorkflowInstanceInfo startWorkflowInstance(
		String workflowDefinitionName, Integer workflowDefinitionVersion,
		Map<String, Object> context, long callingUserId,
		Map<String, Object> parameters) {

		ProcessSession processSession = ProcessSystemUtil.getCurrentSession();

		ProcessEngine processEngine = processSession.getEngine();

		ProcessInstance processInstance = processEngine.createProcessInstance(
			workflowDefinitionName, workflowDefinitionVersion, context);

		processSession.getEngine().executeNextActivity(processInstance);

		return new WorkflowInstanceInfoImpl(processInstance, true);
	}

	public WorkflowInstanceInfo startWorkflowInstance(
		String workflowDefinitionName, Integer workflowDefinitionVersion,
		Map<String, Object> context, long callingUserId,
		String activityName, Map<String, Object> parameters) {

		ProcessSession processSession = ProcessSystemUtil.getCurrentSession();

		ProcessEngine processEngine = processSession.getEngine();

		ProcessInstance processInstance = processEngine.createProcessInstance(
			workflowDefinitionName, workflowDefinitionVersion, context);

		processEngine.executeActivity(activityName, processInstance);

		return new WorkflowInstanceInfoImpl(processInstance, true);
	}

	public WorkflowInstanceInfo startWorkflowInstance(
		String workflowDefinitionName, Integer workflowDefinitionVersion,
		String relationType, long relationId, Map<String, Object> context,
		long callingUserId, Map<String, Object> parameters) {

		ProcessSession processSession = ProcessSystemUtil.getCurrentSession();

		ProcessEngine processEngine = processSession.getEngine();

		ProcessInstance processInstance = processEngine.createProcessInstance(
			workflowDefinitionName, workflowDefinitionVersion, context,
			relationType, relationId);

		processEngine.executeNextActivity(processInstance);

		return new WorkflowInstanceInfoImpl(processInstance, true);
	}

	public WorkflowInstanceInfo startWorkflowInstance(
		String workflowDefinitionName, Integer workflowDefinitionVersion,
		String relationType, long relationId, Map<String, Object> context,
		long callingUserId, String activityName,
		Map<String, Object> parameters) {

		ProcessSession processSession = ProcessSystemUtil.getCurrentSession();

		ProcessEngine processEngine = processSession.getEngine();

		ProcessInstance processInstance = processEngine.createProcessInstance(
			workflowDefinitionName, workflowDefinitionVersion, context,
			relationType, relationId);

		processEngine.executeActivity(activityName, processInstance);

		return new WorkflowInstanceInfoImpl(processInstance, true);
	}

}