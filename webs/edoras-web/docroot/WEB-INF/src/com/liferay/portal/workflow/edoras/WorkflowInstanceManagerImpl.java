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
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.kernel.workflow.WorkflowInstanceHistory;
import com.liferay.portal.kernel.workflow.WorkflowInstanceInfo;
import com.liferay.portal.kernel.workflow.WorkflowInstanceManager;
import com.liferay.portal.workflow.edoras.model.WorkflowLog;
import com.liferay.portal.workflow.edoras.service.persistence.WorkflowLogUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.edorasframework.process.api.entity.ProcessInstance;
import org.edorasframework.process.api.model.Activity;
import org.edorasframework.process.api.setup.ProcessSetup;

/**
 * <a href="WorkflowInstanceManagerImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Micha Kiener
 * @author Brian Wing Shun Chan
 */
public class WorkflowInstanceManagerImpl
	extends AbstractWorkflowManager implements WorkflowInstanceManager {

	public WorkflowInstanceInfo addContextInformation(
			long workflowInstanceId, Map<String, Object> context)
		throws WorkflowException {

		getSetup().getEngine().addProcessInstanceAttributes(
			workflowInstanceId, context);

		return getWorkflowInstanceInfo(workflowInstanceId, false);
	}

	public List<String> getPossibleNextActivityNames(
			long workflowInstanceId, long userId,
			Map<String, Object> parameters)
		throws WorkflowException {

		ProcessSetup setup = getSetup();

		ProcessInstance instance =
			setup.getProcessDao().loadProcessInstance(workflowInstanceId, false);

		List<String> activityNames = new ArrayList<String>();
		List<Activity> activities =
			getSetup().getEngine().getNextActivities(instance, true);

		for (Activity activity : activities) {
			activityNames.add(activity.getName());
		}

		return activityNames;
	}

	public List<String> getPossibleNextPathNames(
		long workflowInstanceId, long userId, Map<String, Object> parameters)
		throws WorkflowException {

		ProcessSetup setup = getSetup();

		ProcessInstance instance =
			setup.getProcessDao().loadProcessInstance(workflowInstanceId, false);

		return setup.getEngine().getNextPathNames(instance);
	}

	public List<WorkflowInstanceHistory> getWorkflowInstanceHistory(
			long workflowInstanceId, boolean includeChildren, int start,
			int end, OrderByComparator orderByComparator)
		throws WorkflowException {

		if (!includeChildren) {
			try {
				List<WorkflowLog> logs =
					WorkflowLogUtil.findByWorkflowInstanceId(
						workflowInstanceId, start, end, orderByComparator);

				return WorkflowManagerUtil.wrapWorkflowHistory(logs);
			}
			catch (SystemException se) {
				throw new WorkflowException(se.getMessage(), se);
			}
		}

		ProcessInstance processInstance = getSetup().getProcessDao().loadProcessInstance(
			workflowInstanceId, true);

		List<ProcessInstance> processInstances = new ArrayList<ProcessInstance>();
		WorkflowManagerUtil.getFlatProcessInstances(
			processInstance, processInstances);

		List<WorkflowLog> allLogs = new ArrayList<WorkflowLog>();

		try {
			for (ProcessInstance instance : processInstances) {
				List<WorkflowLog> logs =
					WorkflowLogUtil.findByWorkflowInstanceId(instance.getPrimaryKey());

				allLogs.addAll(logs);
			}

			List<WorkflowInstanceHistory> history =
				WorkflowManagerUtil.wrapWorkflowHistory(allLogs);

			if (orderByComparator != null) {
				Collections.sort(history, orderByComparator);
			}

			if ((start != QueryUtil.ALL_POS) && (end != QueryUtil.ALL_POS)) {
				history = ListUtil.subList(history, start, end);
			}

			return history;
		}
		catch (SystemException se) {
			throw new WorkflowException(se.getMessage(), se);
		}
	}

	public int getWorkflowInstanceHistoryCount(
			long workflowInstanceId, boolean includeChildren)
		throws WorkflowException {

		try {
			if (!includeChildren) {
				return WorkflowLogUtil.countByWorkflowInstanceId(workflowInstanceId);
			}

			ProcessInstance processInstance =
				getSetup().getProcessDao().loadProcessInstance(
					workflowInstanceId, true);

			List<ProcessInstance> processInstances =
				new ArrayList<ProcessInstance>();
			WorkflowManagerUtil.getFlatProcessInstances(
				processInstance, processInstances);

			int count = 0;
			for (ProcessInstance instance : processInstances) {
				count +=
					WorkflowLogUtil.countByWorkflowInstanceId(instance.getPrimaryKey());
			}

			return count;
		}
		catch (SystemException e) {
			throw new WorkflowException(e.getMessage(), e);
		}
	}

	public WorkflowInstanceInfo getWorkflowInstanceInfo(
			long workflowInstanceId, boolean retrieveChildrenInfo)
		throws WorkflowException {

		return null;
	}

	public WorkflowInstanceInfo getWorkflowInstanceInfo(
			String relationType, long relationId, boolean retrieveChildrenInfo)
		throws WorkflowException {

		return null;
	}

	public int getWorkflowInstanceInfoCount(
			String workflowDefinitionName, Integer workflowDefinitionVersion)
		throws WorkflowException {

		return 0;
	}

	public int getWorkflowInstanceInfoCount(
			String workflowDefinitionName, Integer workflowDefinitionVersion,
			boolean completed)
		throws WorkflowException {

		return 0;
	}

	public int getWorkflowInstanceInfoCount(
			String relationType, long relationId)
		throws WorkflowException {

		return 0;
	}

	public List<WorkflowInstanceInfo> getWorkflowInstanceInfos(
			String workflowDefinitionName, Integer workflowDefinitionVersion,
			boolean completed, boolean retrieveChildrenInfo, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		return null;
	}

	public List<WorkflowInstanceInfo> getWorkflowInstanceInfos(
			String workflowDefinitionName, Integer workflowDefinitionVersion,
			boolean retrieveChildrenInfo, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		return null;
	}

	public List<WorkflowInstanceInfo> getWorkflowInstanceInfos(
			String relationType, long relationId, boolean retrieveChildrenInfo,
			int start, int end, OrderByComparator orderByComparator)
		throws WorkflowException {

		return null;
	}

	public void removeWorkflowInstance(long workflowInstanceId)
		throws WorkflowException {
	}

	public WorkflowInstanceInfo signalWorkflowInstance(
			long workflowInstanceId, Map<String, Object> attributes,
			long callingUserId, Map<String, Object> parameters)
		throws WorkflowException {

		return null;
	}

	public WorkflowInstanceInfo signalWorkflowInstanceByActivity(
			long workflowInstanceId, String activityName,
			Map<String, Object> attributes, long callingUserId,
			Map<String, Object> parameters)
		throws WorkflowException {

		return null;

	}

	public WorkflowInstanceInfo signalWorkflowInstanceByPath(
			long workflowInstanceId, String pathName,
			Map<String, Object> attributes, long callingUserId,
			Map<String, Object> parameters)
		throws WorkflowException {

		return null;

	}

	public WorkflowInstanceInfo startWorkflowInstance(
			String workflowDefinitionName, Integer workflowDefinitionVersion,
			Map<String, Object> context, long callingUserId,
			Map<String, Object> parameters)
		throws WorkflowException {

		return null;
	}

	public WorkflowInstanceInfo startWorkflowInstance(
			String workflowDefinitionName, Integer workflowDefinitionVersion,
			Map<String, Object> context, long callingUserId,
			String activityName, Map<String, Object> parameters)
		throws WorkflowException {

		return null;

	}

	public WorkflowInstanceInfo startWorkflowInstance(
			String workflowDefinitionName, Integer workflowDefinitionVersion,
			String relationType, long relationId, Map<String, Object> context,
			long callingUserId, Map<String, Object> parameters)
		throws WorkflowException {

		return null;
	}

	public WorkflowInstanceInfo startWorkflowInstance(
			String workflowDefinitionName, Integer workflowDefinitionVersion,
			String relationType, long relationId, Map<String, Object> context,
			long callingUserId, String activityName,
			Map<String, Object> parameters)
		throws WorkflowException {

		return null;
	}

}