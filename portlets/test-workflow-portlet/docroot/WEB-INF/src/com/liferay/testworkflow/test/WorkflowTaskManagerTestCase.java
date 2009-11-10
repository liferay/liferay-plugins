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

package com.liferay.testworkflow.test;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.kernel.workflow.WorkflowDefinitionManagerUtil;
import com.liferay.portal.kernel.workflow.WorkflowInstance;
import com.liferay.portal.kernel.workflow.WorkflowInstanceManagerUtil;
import com.liferay.portal.kernel.workflow.WorkflowTask;
import com.liferay.portal.kernel.workflow.WorkflowTaskManagerUtil;
import com.liferay.portal.kernel.workflow.comparator.WorkflowTaskNameComparator;

import java.io.ByteArrayInputStream;

import java.util.List;

/**
 * <a href="WorkflowTaskManagerTestCase.java.html"><b><i>View Source</i></b></a>
 *
 * @author Shuyang Zhou
 */
public class WorkflowTaskManagerTestCase extends WorkflowTestCase {

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

	public void testAssignWorkflowTaskToRole() throws Exception {
		List<WorkflowTask> workflowTasks =
			WorkflowTaskManagerUtil.getWorkflowTasksByRole(
				guestRoleId, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new WorkflowTaskNameComparator(true));

		assertEquals(0, workflowTasks.size());

		WorkflowInstance workflowInstance =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				defaultUserId, DEFINITION_NAME_3,
				_workflowDefinition.getVersion(), null, null);

		List<WorkflowInstance> childrenWorkflowInstances =
			workflowInstance.getChildrenWorkflowInstances();

		for (WorkflowInstance childWorkflowInstance :
				childrenWorkflowInstances) {

			List<WorkflowTask> childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					childWorkflowInstance.getWorkflowInstanceId(), null,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					new WorkflowTaskNameComparator(true));

			assertEquals(1, childWorkflowTasks.size());

			WorkflowTask childWorkflowTask = childWorkflowTasks.get(0);

			assertEquals(0L, childWorkflowTask.getAssigneeRoleId());

			childWorkflowTask =
				WorkflowTaskManagerUtil.assignWorkflowTaskToRole(
					defaultUserId, childWorkflowTask.getWorkflowTaskId(),
					guestRoleId, null, null);

			assertEquals(guestRoleId, childWorkflowTask.getAssigneeRoleId());
		}

		workflowTasks = WorkflowTaskManagerUtil.getWorkflowTasksByRole(
			guestRoleId, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new WorkflowTaskNameComparator(true));

		assertEquals(3, workflowTasks.size());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance.getWorkflowInstanceId());
	}

	public void testAssignWorkflowTaskTouser() throws Exception {
		List<WorkflowTask> workflowTasks =
			WorkflowTaskManagerUtil.getWorkflowTasksByUser(
				defaultUserId, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new WorkflowTaskNameComparator(true));

		assertEquals(0, workflowTasks.size());

		WorkflowInstance workflowInstance =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				defaultUserId, DEFINITION_NAME_3,
				_workflowDefinition.getVersion(), null, null);

		List<WorkflowInstance> childrenWorkflowInstances =
			workflowInstance.getChildrenWorkflowInstances();

		for (WorkflowInstance childWorkflowInstance :
				childrenWorkflowInstances) {

			List<WorkflowTask> childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					childWorkflowInstance.getWorkflowInstanceId(), null,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					new WorkflowTaskNameComparator(true));

			assertEquals(1, childWorkflowTasks.size());

			WorkflowTask childWorkflowTask = childWorkflowTasks.get(0);

			assertEquals(100L, childWorkflowTask.getAssigneeUserId());

			childWorkflowTask =
				WorkflowTaskManagerUtil.assignWorkflowTaskToRole(
					defaultUserId, childWorkflowTask.getWorkflowTaskId(),
					guestRoleId, null, null);

			childWorkflowTask =
				WorkflowTaskManagerUtil.assignWorkflowTaskToUser(
					defaultUserId, childWorkflowTask.getWorkflowTaskId(),
					defaultUserId, null, null);

			assertEquals(defaultUserId, childWorkflowTask.getAssigneeUserId());
		}

		workflowTasks = WorkflowTaskManagerUtil.getWorkflowTasksByUser(
			defaultUserId, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new WorkflowTaskNameComparator(true));

		assertEquals(3, workflowTasks.size());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance.getWorkflowInstanceId());
	}

	public void testCompleteWorkflowTask() throws Exception {
		WorkflowInstance workflowInstance =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				defaultUserId, DEFINITION_NAME_3,
				_workflowDefinition.getVersion(), null, null);

		workflowInstance = WorkflowInstanceManagerUtil.getWorkflowInstance(
			workflowInstance.getWorkflowInstanceId());

		List<WorkflowInstance> childrenWorkflowInstances =
			workflowInstance.getChildrenWorkflowInstances();

		for (WorkflowInstance childWorkflowInstance :
				childrenWorkflowInstances) {

			List<WorkflowTask> childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					childWorkflowInstance.getWorkflowInstanceId(), Boolean.TRUE,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					new WorkflowTaskNameComparator(true));

			assertEquals(0, childWorkflowTasks.size());

			childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					childWorkflowInstance.getWorkflowInstanceId(),
					Boolean.FALSE, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					new WorkflowTaskNameComparator(true));

			assertEquals(1, childWorkflowTasks.size());

			WorkflowTask childWorkflowTask = childWorkflowTasks.get(0);

			assertEquals(100L, childWorkflowTask.getAssigneeUserId());

			childWorkflowTask =
				WorkflowTaskManagerUtil.assignWorkflowTaskToRole(
					defaultUserId, childWorkflowTask.getWorkflowTaskId(),
					guestRoleId, null, null);

			childWorkflowTask =
				WorkflowTaskManagerUtil.assignWorkflowTaskToUser(
					defaultUserId, childWorkflowTask.getWorkflowTaskId(),
					defaultUserId, null, null);

			childWorkflowTask = WorkflowTaskManagerUtil.completeWorkflowTask(
				defaultUserId, childWorkflowTask.getWorkflowTaskId(), null,
				null, null);

			childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					childWorkflowInstance.getWorkflowInstanceId(), Boolean.TRUE,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					new WorkflowTaskNameComparator(true));

			assertEquals(1, childWorkflowTasks.size());

			WorkflowTask completedWorkflowTask = childWorkflowTasks.get(0);

			assertEquals(
				childWorkflowTask.getWorkflowTaskId(),
				completedWorkflowTask.getWorkflowTaskId());
		}

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance.getWorkflowInstanceId());
	}

	public void testGetNextTransitionNames() throws Exception {
		WorkflowInstance workflowInstance =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				defaultUserId, DEFINITION_NAME_3,
				_workflowDefinition.getVersion(), null, null);

		List<WorkflowInstance> childrenWorkflowInstances =
			workflowInstance.getChildrenWorkflowInstances();

		assertEquals(3, childrenWorkflowInstances.size());

		WorkflowInstance childWorkflowInstance1 = childrenWorkflowInstances.get(
			0);

		List<WorkflowTask> childWorkflowTasks1 =
			WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
				childWorkflowInstance1.getWorkflowInstanceId(), null,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new WorkflowTaskNameComparator(true));

		assertEquals(1, childWorkflowTasks1.size());

		WorkflowTask workflowTask1 = childWorkflowTasks1.get(0);

		workflowTask1 = WorkflowTaskManagerUtil.assignWorkflowTaskToRole(
			defaultUserId, workflowTask1.getWorkflowTaskId(), guestRoleId, null,
			null);

		workflowTask1 = WorkflowTaskManagerUtil.assignWorkflowTaskToUser(
			defaultUserId, workflowTask1.getWorkflowTaskId(), defaultUserId,
			null, null);

		List<String> nextTransitionNames1 =
			WorkflowTaskManagerUtil.getNextTransitionNames(
				defaultUserId, workflowTask1.getWorkflowTaskId());

		assertEquals(1, nextTransitionNames1.size());
		assertEquals("toJoinNode", nextTransitionNames1.get(0));

		WorkflowInstance childWorkflowInstance2 = childrenWorkflowInstances.get(
			1);

		List<WorkflowTask> childWorkflowTasks2 =
			WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
				childWorkflowInstance2.getWorkflowInstanceId(), null,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new WorkflowTaskNameComparator(true));

		assertEquals(1, childWorkflowTasks2.size());

		WorkflowTask workflowTask2 = childWorkflowTasks2.get(0);

		workflowTask2 = WorkflowTaskManagerUtil.assignWorkflowTaskToRole(
			defaultUserId, workflowTask2.getWorkflowTaskId(), guestRoleId,
			null, null);

		workflowTask2 = WorkflowTaskManagerUtil.assignWorkflowTaskToUser(
			defaultUserId, workflowTask2.getWorkflowTaskId(),
			defaultUserId, null, null);

		List<String> nextTransitionNames2 =
			WorkflowTaskManagerUtil.getNextTransitionNames(
				defaultUserId, workflowTask2.getWorkflowTaskId());

		assertEquals(1, nextTransitionNames2.size());
		assertEquals("toTaskNode4", nextTransitionNames2.get(0));

		WorkflowInstance childWorkflowInstance3 = childrenWorkflowInstances.get(
			2);

		List<WorkflowTask> childWorkflowTasks3 =
			WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
				childWorkflowInstance3.getWorkflowInstanceId(), null,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new WorkflowTaskNameComparator(true));

		assertEquals(1, childWorkflowTasks3.size());

		WorkflowTask workflowTask3 = childWorkflowTasks3.get(0);

		workflowTask3 = WorkflowTaskManagerUtil.assignWorkflowTaskToRole(
			defaultUserId, workflowTask3.getWorkflowTaskId(), guestRoleId,
			null, null);

		workflowTask3 = WorkflowTaskManagerUtil.assignWorkflowTaskToUser(
			defaultUserId, workflowTask3.getWorkflowTaskId(), defaultUserId,
			null, null);

		List<String> nextTransitionNames3 =
			WorkflowTaskManagerUtil.getNextTransitionNames(
				defaultUserId, workflowTask3.getWorkflowTaskId());

		assertEquals(1, nextTransitionNames3.size());
		assertEquals("toJoinNode", nextTransitionNames3.get(0));

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance.getWorkflowInstanceId());
	}

	public void testGetWorkflowTaskCountByRole() throws Exception {
		int count = WorkflowTaskManagerUtil.getWorkflowTaskCountByRole(
			guestRoleId, null);

		assertEquals(0, count);

		WorkflowInstance workflowInstance =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				defaultUserId, DEFINITION_NAME_3,
				_workflowDefinition.getVersion(), null, null);

		List<WorkflowInstance> childrenWorkflowInstances =
			workflowInstance.getChildrenWorkflowInstances();

		for (WorkflowInstance childWorkflowInstance :
				childrenWorkflowInstances) {

			List<WorkflowTask> childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					childWorkflowInstance.getWorkflowInstanceId(), null,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					new WorkflowTaskNameComparator(true));

			assertEquals(1, childWorkflowTasks.size());

			WorkflowTask childWorkflowTask = childWorkflowTasks.get(0);

			assertEquals(0L, childWorkflowTask.getAssigneeRoleId());

			childWorkflowTask =
				WorkflowTaskManagerUtil.assignWorkflowTaskToRole(
					defaultUserId, childWorkflowTask.getWorkflowTaskId(),
					guestRoleId, null, null);

			childWorkflowTask =
				WorkflowTaskManagerUtil.assignWorkflowTaskToUser(
					defaultUserId, childWorkflowTask.getWorkflowTaskId(),
					defaultUserId, null, null);

			assertEquals(guestRoleId, childWorkflowTask.getAssigneeRoleId());
		}

		count = WorkflowTaskManagerUtil.getWorkflowTaskCountByRole(
			guestRoleId, null);

		assertEquals(3, count);

		count = WorkflowTaskManagerUtil.getWorkflowTaskCountByRole(
			guestRoleId, Boolean.TRUE);

		assertEquals(0, count);

		count = WorkflowTaskManagerUtil.getWorkflowTaskCountByRole(
			guestRoleId, Boolean.FALSE);

		assertEquals(3, count);

		for (WorkflowInstance childWorkflowInstance :
				childrenWorkflowInstances) {

			List<WorkflowTask> childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					childWorkflowInstance.getWorkflowInstanceId(), null,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					new WorkflowTaskNameComparator(true));

			assertEquals(1, childWorkflowTasks.size());

			WorkflowTask childWorkflowTask = childWorkflowTasks.get(0);

			WorkflowTaskManagerUtil.completeWorkflowTask(
				defaultUserId, childWorkflowTask.getWorkflowTaskId(), null,
				null, null);
		}

		count = WorkflowTaskManagerUtil.getWorkflowTaskCountByRole(
			guestRoleId, null);

		assertEquals(3, count);

		count = WorkflowTaskManagerUtil.getWorkflowTaskCountByRole(
			guestRoleId, Boolean.TRUE);

		assertEquals(3, count);

		count = WorkflowTaskManagerUtil.getWorkflowTaskCountByRole(
			guestRoleId, Boolean.FALSE);

		assertEquals(0, count);

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance.getWorkflowInstanceId());
	}

	public void testGetWorkflowTaskCountByUser() throws Exception {
		int count = WorkflowTaskManagerUtil.getWorkflowTaskCountByUser(
			defaultUserId, null);

		assertEquals(0, count);

		WorkflowInstance workflowInstance =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				defaultUserId, DEFINITION_NAME_3,
				_workflowDefinition.getVersion(), null, null);

		List<WorkflowInstance> childrenWorkflowInstances =
			workflowInstance.getChildrenWorkflowInstances();

		for (WorkflowInstance childWorkflowInstance :
				childrenWorkflowInstances) {

			List<WorkflowTask> childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					childWorkflowInstance.getWorkflowInstanceId(), null,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					new WorkflowTaskNameComparator(true));

			assertEquals(1, childWorkflowTasks.size());

			WorkflowTask childWorkflowTask = childWorkflowTasks.get(0);

			assertEquals(0L, childWorkflowTask.getAssigneeRoleId());

			childWorkflowTask =
				WorkflowTaskManagerUtil.assignWorkflowTaskToRole(
					defaultUserId, childWorkflowTask.getWorkflowTaskId(),
					guestRoleId, null, null);

			childWorkflowTask =
				WorkflowTaskManagerUtil.assignWorkflowTaskToUser(
					defaultUserId, childWorkflowTask.getWorkflowTaskId(),
					defaultUserId, null, null);

			assertEquals(guestRoleId, childWorkflowTask.getAssigneeRoleId());
		}

		count = WorkflowTaskManagerUtil.getWorkflowTaskCountByUser(
			defaultUserId, null);

		assertEquals(3, count);

		count = WorkflowTaskManagerUtil.getWorkflowTaskCountByUser(
			defaultUserId, Boolean.TRUE);

		assertEquals(0, count);

		count = WorkflowTaskManagerUtil.getWorkflowTaskCountByUser(
			defaultUserId, Boolean.FALSE);

		assertEquals(3, count);

		for (WorkflowInstance childWorkflowInstance :
				childrenWorkflowInstances) {

			List<WorkflowTask> childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					childWorkflowInstance.getWorkflowInstanceId(), null,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					new WorkflowTaskNameComparator(true));

			assertEquals(1, childWorkflowTasks.size());

			WorkflowTask childWorkflowTask = childWorkflowTasks.get(0);

			WorkflowTaskManagerUtil.completeWorkflowTask(
				defaultUserId, childWorkflowTask.getWorkflowTaskId(),
				null, null, null);
		}

		count = WorkflowTaskManagerUtil.getWorkflowTaskCountByUser(
			defaultUserId, null);

		assertEquals(4, count);

		count = WorkflowTaskManagerUtil.getWorkflowTaskCountByUser(
			defaultUserId, Boolean.TRUE);

		assertEquals(3, count);

		count = WorkflowTaskManagerUtil.getWorkflowTaskCountByUser(
			defaultUserId, Boolean.FALSE);

		assertEquals(1, count);

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance.getWorkflowInstanceId());
	}

	public void testGetWorkflowTaskCountByWorkflowInstance() throws Exception {
		WorkflowInstance workflowInstance =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				defaultUserId, DEFINITION_NAME_3,
				_workflowDefinition.getVersion(), null, null);

		int count =
			WorkflowTaskManagerUtil.getWorkflowTaskCountByWorkflowInstance(
				workflowInstance.getWorkflowInstanceId(), null);

		assertEquals(0, count);

		List<WorkflowInstance> childrenWorkflowInstances =
			workflowInstance.getChildrenWorkflowInstances();

		for (WorkflowInstance childWorkflowInstance :
				childrenWorkflowInstances) {

			List<WorkflowTask> childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					childWorkflowInstance.getWorkflowInstanceId(), null,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					new WorkflowTaskNameComparator(true));

			assertEquals(1, childWorkflowTasks.size());

			childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					childWorkflowInstance.getWorkflowInstanceId(), Boolean.TRUE,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					new WorkflowTaskNameComparator(true));

			assertEquals(0, childWorkflowTasks.size());

			childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					childWorkflowInstance.getWorkflowInstanceId(),
					Boolean.FALSE, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					new WorkflowTaskNameComparator(true));

			assertEquals(1, childWorkflowTasks.size());
		}

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance.getWorkflowInstanceId());
	}

	public void testGetWorkflowTasksByRole() throws Exception {
		List<WorkflowTask> workflowTasks =
			WorkflowTaskManagerUtil.getWorkflowTasksByRole(
				guestRoleId, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new WorkflowTaskNameComparator(true));

		assertEquals(0, workflowTasks.size());

		WorkflowInstance workflowInstance =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				defaultUserId, DEFINITION_NAME_3,
				_workflowDefinition.getVersion(), null, null);

		List<WorkflowInstance> childrenWorkflowInstances =
			workflowInstance.getChildrenWorkflowInstances();

		for (WorkflowInstance childWorkflowInstance :
				childrenWorkflowInstances) {

			List<WorkflowTask> childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					childWorkflowInstance.getWorkflowInstanceId(), null,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					new WorkflowTaskNameComparator(true));

			assertEquals(1, childWorkflowTasks.size());

			WorkflowTask childWorkflowTask = childWorkflowTasks.get(0);

			assertEquals(0L, childWorkflowTask.getAssigneeRoleId());

			childWorkflowTask =
				WorkflowTaskManagerUtil.assignWorkflowTaskToRole(
					defaultUserId, childWorkflowTask.getWorkflowTaskId(),
					guestRoleId, null, null);

			childWorkflowTask =
				WorkflowTaskManagerUtil.assignWorkflowTaskToUser(
					defaultUserId, childWorkflowTask.getWorkflowTaskId(),
					defaultUserId, null, null);

			assertEquals(guestRoleId, childWorkflowTask.getAssigneeRoleId());
		}

		workflowTasks = WorkflowTaskManagerUtil.getWorkflowTasksByRole(
			guestRoleId, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new WorkflowTaskNameComparator(true));

		assertEquals(3, workflowTasks.size());

		WorkflowTask workflowTask1 = workflowTasks.get(0);

		assertEquals(guestRoleId, workflowTask1.getAssigneeRoleId());
		assertNull(workflowTask1.getCompletionDate());
		assertEquals("Task3-1", workflowTask1.getName());

		WorkflowTask workflowTask2 = workflowTasks.get(1);

		assertEquals(guestRoleId, workflowTask2.getAssigneeRoleId());
		assertNull(workflowTask2.getCompletionDate());
		assertEquals("Task3-2", workflowTask2.getName());

		WorkflowTask workflowTask3 = workflowTasks.get(2);

		assertEquals(guestRoleId, workflowTask3.getAssigneeRoleId());
		assertNull(workflowTask3.getCompletionDate());
		assertEquals("Task3-3", workflowTask3.getName());

		workflowTasks = WorkflowTaskManagerUtil.getWorkflowTasksByRole(
			guestRoleId, Boolean.TRUE, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new WorkflowTaskNameComparator(true));

		assertEquals(0, workflowTasks.size());

		workflowTasks = WorkflowTaskManagerUtil.getWorkflowTasksByRole(
			guestRoleId, Boolean.FALSE, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new WorkflowTaskNameComparator(true));

		assertEquals(3, workflowTasks.size());

		workflowTask1 = workflowTasks.get(0);

		assertEquals(guestRoleId, workflowTask1.getAssigneeRoleId());
		assertNull(workflowTask1.getCompletionDate());
		assertEquals("Task3-1", workflowTask1.getName());

		workflowTask2 = workflowTasks.get(1);

		assertEquals(guestRoleId, workflowTask2.getAssigneeRoleId());
		assertNull(workflowTask2.getCompletionDate());
		assertEquals("Task3-2", workflowTask2.getName());

		workflowTask3 = workflowTasks.get(2);

		assertEquals(guestRoleId, workflowTask3.getAssigneeRoleId());
		assertNull(workflowTask3.getCompletionDate());
		assertEquals("Task3-3", workflowTask3.getName());

		for (WorkflowInstance childWorkflowInstance :
				childrenWorkflowInstances) {

			List<WorkflowTask> childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					childWorkflowInstance.getWorkflowInstanceId(), null,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					new WorkflowTaskNameComparator(true));

			assertEquals(1, childWorkflowTasks.size());

			WorkflowTask childWorkflowTask = childWorkflowTasks.get(0);

			WorkflowTaskManagerUtil.completeWorkflowTask(
				defaultUserId, childWorkflowTask.getWorkflowTaskId(),
				null, null, null);
		}

		workflowTasks = WorkflowTaskManagerUtil.getWorkflowTasksByRole(
			guestRoleId, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new WorkflowTaskNameComparator(true));

		assertEquals(3, workflowTasks.size());

		workflowTask1 = workflowTasks.get(0);

		assertEquals(guestRoleId, workflowTask1.getAssigneeRoleId());
		assertNotNull(workflowTask1.getCompletionDate());
		assertEquals("Task3-1", workflowTask1.getName());

		workflowTask2 = workflowTasks.get(1);

		assertEquals(guestRoleId, workflowTask2.getAssigneeRoleId());
		assertNotNull(workflowTask2.getCompletionDate());
		assertEquals("Task3-2", workflowTask2.getName());

		workflowTask3 = workflowTasks.get(2);

		assertEquals(guestRoleId, workflowTask3.getAssigneeRoleId());
		assertNotNull(workflowTask3.getCompletionDate());
		assertEquals("Task3-3", workflowTask3.getName());

		workflowTasks = WorkflowTaskManagerUtil.getWorkflowTasksByRole(
			guestRoleId, Boolean.TRUE, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new WorkflowTaskNameComparator(true));

		assertEquals(3, workflowTasks.size());

		workflowTask1 = workflowTasks.get(0);

		assertEquals(guestRoleId, workflowTask1.getAssigneeRoleId());
		assertNotNull(workflowTask1.getCompletionDate());
		assertEquals("Task3-1", workflowTask1.getName());

		workflowTask2 = workflowTasks.get(1);

		assertEquals(guestRoleId, workflowTask2.getAssigneeRoleId());
		assertNotNull(workflowTask2.getCompletionDate());
		assertEquals("Task3-2", workflowTask2.getName());

		workflowTask3 = workflowTasks.get(2);

		assertEquals(guestRoleId, workflowTask3.getAssigneeRoleId());
		assertNotNull(workflowTask3.getCompletionDate());
		assertEquals("Task3-3", workflowTask3.getName());

		workflowTasks = WorkflowTaskManagerUtil.getWorkflowTasksByRole(
			guestRoleId, Boolean.FALSE, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new WorkflowTaskNameComparator(true));

		assertEquals(0, workflowTasks.size());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance.getWorkflowInstanceId());
	}

	public void testGetWorkflowTasksByUser() throws Exception {
		List<WorkflowTask> workflowTasks =
			WorkflowTaskManagerUtil.getWorkflowTasksByUser(
				defaultUserId, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new WorkflowTaskNameComparator(true));

		assertEquals(0, workflowTasks.size());

		WorkflowInstance workflowInstance =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				defaultUserId, DEFINITION_NAME_3,
				_workflowDefinition.getVersion(), null, null);

		List<WorkflowInstance> childrenWorkflowInstances =
			workflowInstance.getChildrenWorkflowInstances();

		for (WorkflowInstance childWorkflowInstance :
				childrenWorkflowInstances) {

			List<WorkflowTask> childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					childWorkflowInstance.getWorkflowInstanceId(), null,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					new WorkflowTaskNameComparator(true));

			assertEquals(1, childWorkflowTasks.size());

			WorkflowTask childWorkflowTask = childWorkflowTasks.get(0);

			assertEquals(0L, childWorkflowTask.getAssigneeRoleId());

			childWorkflowTask =
				WorkflowTaskManagerUtil.assignWorkflowTaskToRole(
					defaultUserId, childWorkflowTask.getWorkflowTaskId(),
					guestRoleId, null, null);

			childWorkflowTask =
				WorkflowTaskManagerUtil.assignWorkflowTaskToUser(
					defaultUserId, childWorkflowTask.getWorkflowTaskId(),
					defaultUserId, null, null);

			assertEquals(defaultUserId, childWorkflowTask.getAssigneeUserId());
		}

		workflowTasks = WorkflowTaskManagerUtil.getWorkflowTasksByUser(
			defaultUserId, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new WorkflowTaskNameComparator(true));

		assertEquals(3, workflowTasks.size());

		WorkflowTask workflowTask1 = workflowTasks.get(0);

		assertEquals(defaultUserId, workflowTask1.getAssigneeUserId());
		assertNull(workflowTask1.getCompletionDate());
		assertEquals("Task3-1", workflowTask1.getName());

		WorkflowTask workflowTask2 = workflowTasks.get(1);

		assertEquals(defaultUserId, workflowTask2.getAssigneeUserId());
		assertNull(workflowTask2.getCompletionDate());
		assertEquals("Task3-2", workflowTask2.getName());

		WorkflowTask workflowTask3 = workflowTasks.get(2);

		assertEquals(defaultUserId, workflowTask3.getAssigneeUserId());
		assertNull(workflowTask3.getCompletionDate());
		assertEquals("Task3-3", workflowTask3.getName());

		workflowTasks = WorkflowTaskManagerUtil.getWorkflowTasksByUser(
			defaultUserId, Boolean.TRUE, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, new WorkflowTaskNameComparator(true));

		assertEquals(0, workflowTasks.size());

		workflowTasks = WorkflowTaskManagerUtil.getWorkflowTasksByUser(
			defaultUserId, Boolean.FALSE, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, new WorkflowTaskNameComparator(true));

		assertEquals(3, workflowTasks.size());

		workflowTask1 = workflowTasks.get(0);

		assertEquals(defaultUserId, workflowTask1.getAssigneeUserId());
		assertNull(workflowTask1.getCompletionDate());
		assertEquals("Task3-1", workflowTask1.getName());

		workflowTask2 = workflowTasks.get(1);

		assertEquals(defaultUserId, workflowTask2.getAssigneeUserId());
		assertNull(workflowTask2.getCompletionDate());
		assertEquals("Task3-2", workflowTask2.getName());

		workflowTask3 = workflowTasks.get(2);

		assertEquals(defaultUserId, workflowTask3.getAssigneeUserId());
		assertNull(workflowTask3.getCompletionDate());
		assertEquals("Task3-3", workflowTask3.getName());

		for (WorkflowInstance childWorkflowInstance :
				childrenWorkflowInstances) {

			List<WorkflowTask> childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					childWorkflowInstance.getWorkflowInstanceId(), null,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					new WorkflowTaskNameComparator(true));

			assertEquals(1, childWorkflowTasks.size());

			WorkflowTask childWorkflowTask = childWorkflowTasks.get(0);

			WorkflowTaskManagerUtil.completeWorkflowTask(
				defaultUserId, childWorkflowTask.getWorkflowTaskId(), null,
				null, null);
		}

		workflowTasks = WorkflowTaskManagerUtil.getWorkflowTasksByUser(
			defaultUserId, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new WorkflowTaskNameComparator(true));

		assertEquals(4, workflowTasks.size());

		workflowTask1 = workflowTasks.get(0);

		assertEquals(defaultUserId, workflowTask1.getAssigneeUserId());
		assertNotNull(workflowTask1.getCompletionDate());
		assertEquals("Task3-1", workflowTask1.getName());

		workflowTask2 = workflowTasks.get(1);

		assertEquals(defaultUserId, workflowTask2.getAssigneeUserId());
		assertNotNull(workflowTask2.getCompletionDate());
		assertEquals("Task3-2", workflowTask2.getName());

		workflowTask3 = workflowTasks.get(2);

		assertEquals(defaultUserId, workflowTask3.getAssigneeUserId());
		assertNotNull(workflowTask3.getCompletionDate());
		assertEquals("Task3-3", workflowTask3.getName());

		WorkflowTask workflowTask4 = workflowTasks.get(3);

		assertEquals(0L, workflowTask4.getAssigneeUserId());
		assertNull(workflowTask4.getCompletionDate());
		assertEquals("Task3-4", workflowTask4.getName());

		workflowTasks = WorkflowTaskManagerUtil.getWorkflowTasksByUser(
			defaultUserId, Boolean.TRUE, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, new WorkflowTaskNameComparator(true));

		assertEquals(3, workflowTasks.size());

		workflowTask1 = workflowTasks.get(0);

		assertEquals(defaultUserId, workflowTask1.getAssigneeUserId());
		assertNotNull(workflowTask1.getCompletionDate());
		assertEquals("Task3-1", workflowTask1.getName());

		workflowTask2 = workflowTasks.get(1);

		assertEquals(defaultUserId, workflowTask2.getAssigneeUserId());
		assertNotNull(workflowTask2.getCompletionDate());
		assertEquals("Task3-2", workflowTask2.getName());

		workflowTask3 = workflowTasks.get(2);

		assertEquals(defaultUserId, workflowTask3.getAssigneeUserId());
		assertNotNull(workflowTask3.getCompletionDate());
		assertEquals("Task3-3", workflowTask3.getName());

		workflowTasks = WorkflowTaskManagerUtil.getWorkflowTasksByUser(
			defaultUserId, Boolean.FALSE, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, new WorkflowTaskNameComparator(true));

		assertEquals(1, workflowTasks.size());

		workflowTask4 = workflowTasks.get(0);

		assertEquals(0L, workflowTask4.getAssigneeUserId());
		assertNull(workflowTask4.getCompletionDate());
		assertEquals("Task3-4", workflowTask4.getName());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance.getWorkflowInstanceId());
	}

	public void testGetWorkflowTasksByWorkflowInstance() throws Exception {
		WorkflowInstance workflowInstance =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				defaultUserId, DEFINITION_NAME_3,
				_workflowDefinition.getVersion(), null, null);

		List<WorkflowTask> workflowTasks =
			WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
				workflowInstance.getWorkflowInstanceId(), null,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new WorkflowTaskNameComparator(true));

		assertEquals(0, workflowTasks.size());

		List<WorkflowInstance> childrenWorkflowInstances =
			workflowInstance.getChildrenWorkflowInstances();

		for (WorkflowInstance childWorkflowInstance :
				childrenWorkflowInstances) {

			List<WorkflowTask> childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					childWorkflowInstance.getWorkflowInstanceId(), null,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					new WorkflowTaskNameComparator(true));

			assertEquals(1, childWorkflowTasks.size());

			WorkflowTask childWorkflowTask = childWorkflowTasks.get(0);

			assertEquals(
				childWorkflowInstance.getWorkflowInstanceId(),
				childWorkflowTask.getWorkflowInstanceId());

			childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					childWorkflowInstance.getWorkflowInstanceId(), Boolean.TRUE,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					new WorkflowTaskNameComparator(true));

			assertEquals(0, childWorkflowTasks.size());

			childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					childWorkflowInstance.getWorkflowInstanceId(),
					Boolean.FALSE, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					new WorkflowTaskNameComparator(true));

			assertEquals(1, childWorkflowTasks.size());

			childWorkflowTask = childWorkflowTasks.get(0);

			assertEquals(
				childWorkflowInstance.getWorkflowInstanceId(),
				childWorkflowTask.getWorkflowInstanceId());
		}

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance.getWorkflowInstanceId());
	}

	private WorkflowDefinition _workflowDefinition;

}