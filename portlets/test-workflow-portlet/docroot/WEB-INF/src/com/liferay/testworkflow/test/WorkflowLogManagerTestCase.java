/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.testworkflow.test;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.kernel.workflow.WorkflowDefinitionManagerUtil;
import com.liferay.portal.kernel.workflow.WorkflowInstance;
import com.liferay.portal.kernel.workflow.WorkflowInstanceManagerUtil;
import com.liferay.portal.kernel.workflow.WorkflowLog;
import com.liferay.portal.kernel.workflow.WorkflowLogManagerUtil;
import com.liferay.portal.kernel.workflow.WorkflowTask;
import com.liferay.portal.kernel.workflow.WorkflowTaskManagerUtil;
import com.liferay.portal.kernel.workflow.comparator.WorkflowLogCreateDateComparator;
import com.liferay.portal.kernel.workflow.comparator.WorkflowTaskNameComparator;

import java.io.ByteArrayInputStream;

import java.util.List;

/**
 * <a href="WorkflowLogManagerTestCase.java.html"><b><i>View Source</i></b></a>
 *
 * @author Shuyang Zhou
 * @author Marcellus Tavares
 */
public class WorkflowLogManagerTestCase extends WorkflowTestCase {

	public void setUp() throws Exception {
		super.setUp();

		_workflowDefinition =
			WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
				defaultUserId, DEFINITION_NAME_3,
				new ByteArrayInputStream(definitionBytes3));
	}

	public void tearDown() throws Exception {
		super.tearDown();

		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			defaultUserId, DEFINITION_NAME_3,
			_workflowDefinition.getVersion());
	}

	public void testGetWorkflowLogCount() throws Exception {
		WorkflowInstance workflowInstance =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				defaultUserId, _workflowDefinition.getName(),
				_workflowDefinition.getVersion(), null, null);

		workflowInstance = WorkflowInstanceManagerUtil.getWorkflowInstance(
			workflowInstance.getWorkflowInstanceId());

		List<WorkflowInstance> childrenWorkflowInstances =
			workflowInstance.getChildrenWorkflowInstances();

		assertEquals(3, childrenWorkflowInstances.size());

		for (WorkflowInstance childWorkflowInstance :
				childrenWorkflowInstances) {

			List<WorkflowTask> childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					childWorkflowInstance.getWorkflowInstanceId(),
					Boolean.FALSE, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					new WorkflowTaskNameComparator(true));

			assertEquals(1, childWorkflowTasks.size());

			WorkflowTask childWorkflowTask = childWorkflowTasks.get(0);

			int childCount = WorkflowLogManagerUtil.getWorkflowLogCount(
				childWorkflowTask.getWorkflowTaskId());

			assertEquals(0, childCount);

			WorkflowTaskManagerUtil.completeWorkflowTask(
				childWorkflowTask.getAssigneeUserId(),
				childWorkflowTask.getWorkflowTaskId(), null, null, null);

			childCount = WorkflowLogManagerUtil.getWorkflowLogCount(
				childWorkflowTask.getWorkflowTaskId());

			assertEquals(1, childCount);
		}

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance.getWorkflowInstanceId());
	}

	public void testGetWorkflowLogs() throws Exception {
		WorkflowInstance workflowInstance =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				defaultUserId, _workflowDefinition.getName(),
				_workflowDefinition.getVersion(), null, null);

		workflowInstance = WorkflowInstanceManagerUtil.getWorkflowInstance(
			workflowInstance.getWorkflowInstanceId());

		List<WorkflowInstance> childrenWorkflowInstances =
			workflowInstance.getChildrenWorkflowInstances();

		assertEquals(3, childrenWorkflowInstances.size());

		for (WorkflowInstance childWorkflowInstance :
				childrenWorkflowInstances) {

			List<WorkflowTask> childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					childWorkflowInstance.getWorkflowInstanceId(),
					Boolean.FALSE, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					new WorkflowTaskNameComparator(true));

			assertEquals(1, childWorkflowTasks.size());

			WorkflowTask childWorkflowTask = childWorkflowTasks.get(0);

			List<WorkflowLog> childWorkflowLogs =
				WorkflowLogManagerUtil.getWorkflowLogs(
					childWorkflowTask.getWorkflowTaskId(), QueryUtil.ALL_POS,
					QueryUtil.ALL_POS,
					new WorkflowLogCreateDateComparator(true));

			assertTrue(childWorkflowLogs.isEmpty());

			WorkflowTaskManagerUtil.completeWorkflowTask(
				childWorkflowTask.getAssigneeUserId(),
				childWorkflowTask.getWorkflowTaskId(), null, null, null);

			childWorkflowLogs = WorkflowLogManagerUtil.getWorkflowLogs(
				childWorkflowTask.getWorkflowTaskId(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, new WorkflowLogCreateDateComparator(true));

			assertTrue(!childWorkflowLogs.isEmpty());
		}

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance.getWorkflowInstanceId());
	}

	private WorkflowDefinition _workflowDefinition;

}