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

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * <a href="WorkflowTaskManagerTestCase.java.html"><b><i>View Source</i></b></a>
 *
 * @author Shuyang Zhou
 */
public class WorkflowTaskManagerTestCase extends BaseTestCase {

	public void setUp() throws Exception {
		_workflowDefinition =
			WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
			USER.getUserId(), NAME_3, new ByteArrayInputStream(BYTES_3));
	}

	public void tearDown() throws Exception {

		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			USER.getUserId(), NAME_3, _workflowDefinition.getVersion());

	}

	public void testAssignWorkflowTaskToRole() throws Exception {
		WorkflowInstance workflowInstance =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
			USER.getUserId(), NAME_3, _workflowDefinition.getVersion(),
			null, null);

		long[] roleIds = USER.getRoleIds();
		assertTrue(roleIds.length > 0);

		long testRoleId = roleIds[0];

		List<WorkflowTask> workflowTasks =
			WorkflowTaskManagerUtil.getWorkflowTasksByRole(
			testRoleId, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new WorkflowTaskNameComparator(true));
		assertEquals(0, workflowTasks.size());

		workflowInstance =
			WorkflowInstanceManagerUtil.getWorkflowInstance(
			workflowInstance.getWorkflowInstanceId());

		List<WorkflowInstance> childrenWorkflowInstances =
			workflowInstance.getChildren();

		for (WorkflowInstance childWorkflowInstance : childrenWorkflowInstances)
		{
			List<WorkflowTask> childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					childWorkflowInstance.getWorkflowInstanceId(), null,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					new WorkflowTaskNameComparator(true));
			assertEquals(1, childWorkflowTasks.size());
			WorkflowTask workflowTask = childWorkflowTasks.get(0);
			assertEquals(0L, workflowTask.getAssigneeRoleId());
			workflowTask = WorkflowTaskManagerUtil.assignWorkflowTaskToRole(
				USER.getUserId(), workflowTask.getWorkflowTaskId(), testRoleId,
				"Assign to role", null);
			assertEquals(testRoleId, workflowTask.getAssigneeRoleId());
		}

		workflowTasks =
			WorkflowTaskManagerUtil.getWorkflowTasksByRole(
			testRoleId, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new WorkflowTaskNameComparator(true));
		assertEquals(3, workflowTasks.size());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance.getWorkflowInstanceId());
	}

	public void testAssignWorkflowTaskToUser() throws Exception {
		WorkflowInstance workflowInstance =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
			USER.getUserId(), NAME_3, _workflowDefinition.getVersion(),
			null, null);

		List<WorkflowTask> workflowTasks =
			WorkflowTaskManagerUtil.getWorkflowTasksByUser(
			USER.getUserId(), null, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new WorkflowTaskNameComparator(true));
		assertEquals(0, workflowTasks.size());

		workflowInstance =
			WorkflowInstanceManagerUtil.getWorkflowInstance(
			workflowInstance.getWorkflowInstanceId());

		List<WorkflowInstance> childrenWorkflowInstances =
			workflowInstance.getChildren();

		long[] roleIds = USER.getRoleIds();
		assertTrue(roleIds.length > 0);

		long testRoleId = roleIds[0];

		for (WorkflowInstance childWorkflowInstance : childrenWorkflowInstances)
		{
			List<WorkflowTask> childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					childWorkflowInstance.getWorkflowInstanceId(), null,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					new WorkflowTaskNameComparator(true));
			assertEquals(1, childWorkflowTasks.size());
			WorkflowTask workflowTask = childWorkflowTasks.get(0);
			assertEquals(100L, workflowTask.getAssigneeUserId());
			workflowTask = WorkflowTaskManagerUtil.assignWorkflowTaskToRole(
				USER.getUserId(), workflowTask.getWorkflowTaskId(), testRoleId,
				"Assign to role", null);
			workflowTask = WorkflowTaskManagerUtil.assignWorkflowTaskToUser(
				USER.getUserId(), workflowTask.getWorkflowTaskId(),
				USER.getUserId(), "Assign to user", null);
			assertEquals(USER.getUserId(), workflowTask.getAssigneeUserId());
		}

		workflowTasks =
			WorkflowTaskManagerUtil.getWorkflowTasksByUser(
			USER.getUserId(), null, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new WorkflowTaskNameComparator(true));
		assertEquals(3, workflowTasks.size());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance.getWorkflowInstanceId());
	}

	public void testCompleteWorkflowTask() throws Exception {
		WorkflowInstance workflowInstance =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
			USER.getUserId(), NAME_3, _workflowDefinition.getVersion(),
			null, null);

		workflowInstance =
			WorkflowInstanceManagerUtil.getWorkflowInstance(
			workflowInstance.getWorkflowInstanceId());

		List<WorkflowInstance> childrenWorkflowInstances =
			workflowInstance.getChildren();

		long[] roleIds = USER.getRoleIds();
		assertTrue(roleIds.length > 0);

		long testRoleId = roleIds[0];

		for (WorkflowInstance childWorkflowInstance : childrenWorkflowInstances)
		{
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
			WorkflowTask workflowTask = childWorkflowTasks.get(0);
			assertEquals(100L, workflowTask.getAssigneeUserId());
			workflowTask = WorkflowTaskManagerUtil.assignWorkflowTaskToRole(
				USER.getUserId(), workflowTask.getWorkflowTaskId(), testRoleId,
				"Assign to role", null);
			workflowTask = WorkflowTaskManagerUtil.assignWorkflowTaskToUser(
				USER.getUserId(), workflowTask.getWorkflowTaskId(),
				USER.getUserId(), "Assign to user", null);
			workflowTask= WorkflowTaskManagerUtil.completeWorkflowTask(
				USER.getUserId(), workflowTask.getWorkflowTaskId(), null,
				"Complete workflow task", null);

			childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					childWorkflowInstance.getWorkflowInstanceId(), Boolean.TRUE,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					new WorkflowTaskNameComparator(true));
			assertEquals(1, childWorkflowTasks.size());

			WorkflowTask completedTask = childWorkflowTasks.get(0);
			assertEquals(
				workflowTask.getWorkflowTaskId(),
				completedTask.getWorkflowTaskId());
		}

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance.getWorkflowInstanceId());
	}

	public void testGetNextTransitionNames() throws Exception {
		WorkflowInstance workflowInstance =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
			USER.getUserId(), NAME_3, _workflowDefinition.getVersion(),
			null, null);

		workflowInstance =
			WorkflowInstanceManagerUtil.getWorkflowInstance(
			workflowInstance.getWorkflowInstanceId());

		List<WorkflowInstance> childrenWorkflowInstances =
			workflowInstance.getChildren();
		assertEquals(3, childrenWorkflowInstances.size());

		Collections.sort(
			childrenWorkflowInstances, new Comparator<WorkflowInstance>() {

			public int compare(
				WorkflowInstance workflowInstance1,
				WorkflowInstance workflowInstance2) {
				return workflowInstance1.getCurrentNodeName().compareTo(
					workflowInstance2.getCurrentNodeName());
			}
		});

		long[] roleIds = USER.getRoleIds();
		assertTrue(roleIds.length > 0);

		long testRoleId = roleIds[0];

		WorkflowInstance childInstance1 = childrenWorkflowInstances.get(0);

		List<WorkflowTask> childWorkflowTasks1 =
			WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
				childInstance1.getWorkflowInstanceId(), null,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new WorkflowTaskNameComparator(true));
		assertEquals(1, childWorkflowTasks1.size());
		WorkflowTask workflowTask1 = childWorkflowTasks1.get(0);

		workflowTask1 = WorkflowTaskManagerUtil.assignWorkflowTaskToRole(
			USER.getUserId(), workflowTask1.getWorkflowTaskId(), testRoleId,
			"Assign to role", null);
		workflowTask1 = WorkflowTaskManagerUtil.assignWorkflowTaskToUser(
			USER.getUserId(), workflowTask1.getWorkflowTaskId(),
			USER.getUserId(), "Assign to user", null);

		List<String> nextTransitionNames1 =
			WorkflowTaskManagerUtil.getNextTransitionNames(
				USER.getUserId(), workflowTask1.getWorkflowTaskId());
		assertEquals(1, nextTransitionNames1.size());
		assertEquals("toJoinNode", nextTransitionNames1.get(0));

		WorkflowInstance childInstance2 = childrenWorkflowInstances.get(1);

		List<WorkflowTask> childWorkflowTasks2 =
			WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
				childInstance2.getWorkflowInstanceId(), null,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new WorkflowTaskNameComparator(true));
		assertEquals(1, childWorkflowTasks2.size());
		WorkflowTask workflowTask2 = childWorkflowTasks2.get(0);

		workflowTask2 = WorkflowTaskManagerUtil.assignWorkflowTaskToRole(
			USER.getUserId(), workflowTask2.getWorkflowTaskId(), testRoleId,
			"Assign to role", null);
		workflowTask2 = WorkflowTaskManagerUtil.assignWorkflowTaskToUser(
			USER.getUserId(), workflowTask2.getWorkflowTaskId(),
			USER.getUserId(), "Assign to user", null);

		List<String> nextTransitionNames2 =
			WorkflowTaskManagerUtil.getNextTransitionNames(
				USER.getUserId(), workflowTask2.getWorkflowTaskId());
		assertEquals(1, nextTransitionNames2.size());
		assertEquals("toTaskNode4", nextTransitionNames2.get(0));

		WorkflowInstance childInstance3 = childrenWorkflowInstances.get(2);

		List<WorkflowTask> childWorkflowTasks3 =
			WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
				childInstance3.getWorkflowInstanceId(), null,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new WorkflowTaskNameComparator(true));
		assertEquals(1, childWorkflowTasks3.size());
		WorkflowTask workflowTask3 = childWorkflowTasks3.get(0);

		workflowTask3 = WorkflowTaskManagerUtil.assignWorkflowTaskToRole(
			USER.getUserId(), workflowTask3.getWorkflowTaskId(), testRoleId,
			"Assign to role", null);
		workflowTask3 = WorkflowTaskManagerUtil.assignWorkflowTaskToUser(
			USER.getUserId(), workflowTask3.getWorkflowTaskId(),
			USER.getUserId(), "Assign to user", null);

		List<String> nextTransitionNames3 =
			WorkflowTaskManagerUtil.getNextTransitionNames(
				USER.getUserId(), workflowTask3.getWorkflowTaskId());
		assertEquals(1, nextTransitionNames3.size());
		assertEquals("toJoinNode", nextTransitionNames3.get(0));

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance.getWorkflowInstanceId());
	}

	public void testGetWorkflowTaskCountByRole() throws Exception {
		WorkflowInstance workflowInstance =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
			USER.getUserId(), NAME_3, _workflowDefinition.getVersion(),
			null, null);

		long[] roleIds = USER.getRoleIds();
		assertTrue(roleIds.length > 0);

		long testRoleId = roleIds[0];

		int count = WorkflowTaskManagerUtil.getWorkflowTaskCountByRole(
			testRoleId, null);
		assertEquals(0, count);

		workflowInstance =
			WorkflowInstanceManagerUtil.getWorkflowInstance(
			workflowInstance.getWorkflowInstanceId());

		List<WorkflowInstance> childrenWorkflowInstances =
			workflowInstance.getChildren();

		for (WorkflowInstance childWorkflowInstance : childrenWorkflowInstances)
		{
			List<WorkflowTask> childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					childWorkflowInstance.getWorkflowInstanceId(), null,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					new WorkflowTaskNameComparator(true));
			assertEquals(1, childWorkflowTasks.size());
			WorkflowTask workflowTask = childWorkflowTasks.get(0);
			assertEquals(0L, workflowTask.getAssigneeRoleId());
			workflowTask = WorkflowTaskManagerUtil.assignWorkflowTaskToRole(
				USER.getUserId(), workflowTask.getWorkflowTaskId(), testRoleId,
				"Assign to role", null);
			workflowTask = WorkflowTaskManagerUtil.assignWorkflowTaskToUser(
				USER.getUserId(), workflowTask.getWorkflowTaskId(),
				USER.getUserId(), "Assign to user", null);
			assertEquals(testRoleId, workflowTask.getAssigneeRoleId());
		}

		count = WorkflowTaskManagerUtil.getWorkflowTaskCountByRole(
			testRoleId, null);
		assertEquals(3, count);

		count = WorkflowTaskManagerUtil.getWorkflowTaskCountByRole(
			testRoleId, Boolean.TRUE);
		assertEquals(0, count);

		count = WorkflowTaskManagerUtil.getWorkflowTaskCountByRole(
			testRoleId, Boolean.FALSE);
		assertEquals(3, count);

		for (WorkflowInstance childWorkflowInstance : childrenWorkflowInstances)
		{
			List<WorkflowTask> childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					childWorkflowInstance.getWorkflowInstanceId(), null,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					new WorkflowTaskNameComparator(true));
			assertEquals(1, childWorkflowTasks.size());
			WorkflowTask workflowTask = childWorkflowTasks.get(0);
			WorkflowTaskManagerUtil.completeWorkflowTask(
				USER.getUserId(), workflowTask.getWorkflowTaskId(), null,
				"Complete workflow task", null);
		}

		count = WorkflowTaskManagerUtil.getWorkflowTaskCountByRole(
			testRoleId, null);
		assertEquals(3, count);

		count = WorkflowTaskManagerUtil.getWorkflowTaskCountByRole(
			testRoleId, Boolean.TRUE);
		assertEquals(3, count);

		count = WorkflowTaskManagerUtil.getWorkflowTaskCountByRole(
			testRoleId, Boolean.FALSE);
		assertEquals(0, count);

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance.getWorkflowInstanceId());
	}

	public void testGetWorkflowTaskCountByUser() throws Exception {
		WorkflowInstance workflowInstance =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
			USER.getUserId(), NAME_3, _workflowDefinition.getVersion(),
			null, null);

		long[] roleIds = USER.getRoleIds();
		assertTrue(roleIds.length > 0);

		long testRoleId = roleIds[0];

		int count = WorkflowTaskManagerUtil.getWorkflowTaskCountByUser(
			USER.getUserId(), null);
		assertEquals(0, count);

		workflowInstance =
			WorkflowInstanceManagerUtil.getWorkflowInstance(
			workflowInstance.getWorkflowInstanceId());

		List<WorkflowInstance> childrenWorkflowInstances =
			workflowInstance.getChildren();

		for (WorkflowInstance childWorkflowInstance : childrenWorkflowInstances)
		{
			List<WorkflowTask> childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					childWorkflowInstance.getWorkflowInstanceId(), null,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					new WorkflowTaskNameComparator(true));
			assertEquals(1, childWorkflowTasks.size());
			WorkflowTask workflowTask = childWorkflowTasks.get(0);
			assertEquals(0L, workflowTask.getAssigneeRoleId());
			workflowTask = WorkflowTaskManagerUtil.assignWorkflowTaskToRole(
				USER.getUserId(), workflowTask.getWorkflowTaskId(), testRoleId,
				"Assign to role", null);
			workflowTask = WorkflowTaskManagerUtil.assignWorkflowTaskToUser(
				USER.getUserId(), workflowTask.getWorkflowTaskId(),
				USER.getUserId(), "Assign to user", null);
			assertEquals(testRoleId, workflowTask.getAssigneeRoleId());
		}

		count = WorkflowTaskManagerUtil.getWorkflowTaskCountByUser(
			USER.getUserId(), null);
		assertEquals(3, count);

		count = WorkflowTaskManagerUtil.getWorkflowTaskCountByUser(
			USER.getUserId(), Boolean.TRUE);
		assertEquals(0, count);

		count = WorkflowTaskManagerUtil.getWorkflowTaskCountByUser(
			USER.getUserId(), Boolean.FALSE);
		assertEquals(3, count);

		for (WorkflowInstance childWorkflowInstance : childrenWorkflowInstances)
		{
			List<WorkflowTask> childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					childWorkflowInstance.getWorkflowInstanceId(), null,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					new WorkflowTaskNameComparator(true));
			assertEquals(1, childWorkflowTasks.size());
			WorkflowTask workflowTask = childWorkflowTasks.get(0);
			WorkflowTaskManagerUtil.completeWorkflowTask(
				USER.getUserId(), workflowTask.getWorkflowTaskId(), null,
				"Complete workflow task", null);
		}

		count = WorkflowTaskManagerUtil.getWorkflowTaskCountByUser(
			USER.getUserId(), null);
		assertEquals(4, count);

		count = WorkflowTaskManagerUtil.getWorkflowTaskCountByUser(
			USER.getUserId(), Boolean.TRUE);
		assertEquals(3, count);

		count = WorkflowTaskManagerUtil.getWorkflowTaskCountByUser(
			USER.getUserId(), Boolean.FALSE);
		assertEquals(1, count);

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance.getWorkflowInstanceId());
	}

	public void testGetWorkflowTaskCountByWorkflowInstance() throws Exception {
		WorkflowInstance workflowInstance =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
			USER.getUserId(), NAME_3, _workflowDefinition.getVersion(),
			null, null);

		long[] roleIds = USER.getRoleIds();
		assertTrue(roleIds.length > 0);

		int count =
			WorkflowTaskManagerUtil.getWorkflowTaskCountByWorkflowInstance(
				workflowInstance.getWorkflowInstanceId(), null);
		assertEquals(0, count);

		workflowInstance =
			WorkflowInstanceManagerUtil.getWorkflowInstance(
			workflowInstance.getWorkflowInstanceId());

		List<WorkflowInstance> childrenWorkflowInstances =
			workflowInstance.getChildren();

		for (WorkflowInstance childWorkflowInstance : childrenWorkflowInstances)
		{
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
		WorkflowInstance workflowInstance =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
			USER.getUserId(), NAME_3, _workflowDefinition.getVersion(),
			null, null);

		long[] roleIds = USER.getRoleIds();
		assertTrue(roleIds.length > 0);

		long testRoleId = roleIds[0];

		List<WorkflowTask> workflowTasks =
			WorkflowTaskManagerUtil.getWorkflowTasksByRole(
				testRoleId, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new WorkflowTaskNameComparator(true));
		assertEquals(0, workflowTasks.size());

		workflowInstance =
			WorkflowInstanceManagerUtil.getWorkflowInstance(
			workflowInstance.getWorkflowInstanceId());

		List<WorkflowInstance> childrenWorkflowInstances =
			workflowInstance.getChildren();

		for (WorkflowInstance childWorkflowInstance : childrenWorkflowInstances)
		{
			List<WorkflowTask> childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					childWorkflowInstance.getWorkflowInstanceId(), null,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					new WorkflowTaskNameComparator(true));
			assertEquals(1, childWorkflowTasks.size());
			WorkflowTask workflowTask = childWorkflowTasks.get(0);
			assertEquals(0L, workflowTask.getAssigneeRoleId());
			workflowTask = WorkflowTaskManagerUtil.assignWorkflowTaskToRole(
				USER.getUserId(), workflowTask.getWorkflowTaskId(), testRoleId,
				"Assign to role", null);
			workflowTask = WorkflowTaskManagerUtil.assignWorkflowTaskToUser(
				USER.getUserId(), workflowTask.getWorkflowTaskId(),
				USER.getUserId(), "Assign to user", null);
			assertEquals(testRoleId, workflowTask.getAssigneeRoleId());
		}

		workflowTasks = WorkflowTaskManagerUtil.getWorkflowTasksByRole(
			testRoleId, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new WorkflowTaskNameComparator(true));
		assertEquals(3, workflowTasks.size());
		WorkflowTask workflowTask1 = workflowTasks.get(0);
		assertEquals(testRoleId, workflowTask1.getAssigneeRoleId());
		assertEquals("Task3-1", workflowTask1.getName());
		assertNull(workflowTask1.getCompletionDate());
		WorkflowTask workflowTask2 = workflowTasks.get(1);
		assertEquals(testRoleId, workflowTask2.getAssigneeRoleId());
		assertEquals("Task3-2", workflowTask2.getName());
		assertNull(workflowTask2.getCompletionDate());
		WorkflowTask workflowTask3 = workflowTasks.get(2);
		assertEquals(testRoleId, workflowTask3.getAssigneeRoleId());
		assertEquals("Task3-3", workflowTask3.getName());
		assertNull(workflowTask3.getCompletionDate());

		workflowTasks = WorkflowTaskManagerUtil.getWorkflowTasksByRole(
			testRoleId, Boolean.TRUE, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new WorkflowTaskNameComparator(true));
		assertEquals(0, workflowTasks.size());

		workflowTasks = WorkflowTaskManagerUtil.getWorkflowTasksByRole(
			testRoleId, Boolean.FALSE, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new WorkflowTaskNameComparator(true));
		assertEquals(3, workflowTasks.size());
		workflowTask1 = workflowTasks.get(0);
		assertEquals(testRoleId, workflowTask1.getAssigneeRoleId());
		assertEquals("Task3-1", workflowTask1.getName());
		assertNull(workflowTask1.getCompletionDate());
		workflowTask2 = workflowTasks.get(1);
		assertEquals(testRoleId, workflowTask2.getAssigneeRoleId());
		assertEquals("Task3-2", workflowTask2.getName());
		assertNull(workflowTask2.getCompletionDate());
		workflowTask3 = workflowTasks.get(2);
		assertEquals(testRoleId, workflowTask3.getAssigneeRoleId());
		assertEquals("Task3-3", workflowTask3.getName());
		assertNull(workflowTask3.getCompletionDate());

		for (WorkflowInstance childWorkflowInstance : childrenWorkflowInstances)
		{
			List<WorkflowTask> childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					childWorkflowInstance.getWorkflowInstanceId(), null,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					new WorkflowTaskNameComparator(true));
			assertEquals(1, childWorkflowTasks.size());
			WorkflowTask workflowTask = childWorkflowTasks.get(0);
			WorkflowTaskManagerUtil.completeWorkflowTask(
				USER.getUserId(), workflowTask.getWorkflowTaskId(), null,
				"Complete workflow task", null);
		}

		workflowTasks = WorkflowTaskManagerUtil.getWorkflowTasksByRole(
			testRoleId, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new WorkflowTaskNameComparator(true));
		assertEquals(3, workflowTasks.size());
		workflowTask1 = workflowTasks.get(0);
		assertEquals(testRoleId, workflowTask1.getAssigneeRoleId());
		assertEquals("Task3-1", workflowTask1.getName());
		assertNotNull(workflowTask1.getCompletionDate());
		workflowTask2 = workflowTasks.get(1);
		assertEquals(testRoleId, workflowTask2.getAssigneeRoleId());
		assertEquals("Task3-2", workflowTask2.getName());
		assertNotNull(workflowTask2.getCompletionDate());
		workflowTask3 = workflowTasks.get(2);
		assertEquals(testRoleId, workflowTask3.getAssigneeRoleId());
		assertEquals("Task3-3", workflowTask3.getName());
		assertNotNull(workflowTask3.getCompletionDate());

		workflowTasks = WorkflowTaskManagerUtil.getWorkflowTasksByRole(
			testRoleId, Boolean.TRUE, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new WorkflowTaskNameComparator(true));
		assertEquals(3, workflowTasks.size());
		workflowTask1 = workflowTasks.get(0);
		assertEquals(testRoleId, workflowTask1.getAssigneeRoleId());
		assertEquals("Task3-1", workflowTask1.getName());
		assertNotNull(workflowTask1.getCompletionDate());
		workflowTask2 = workflowTasks.get(1);
		assertEquals(testRoleId, workflowTask2.getAssigneeRoleId());
		assertEquals("Task3-2", workflowTask2.getName());
		assertNotNull(workflowTask2.getCompletionDate());
		workflowTask3 = workflowTasks.get(2);
		assertEquals(testRoleId, workflowTask3.getAssigneeRoleId());
		assertEquals("Task3-3", workflowTask3.getName());
		assertNotNull(workflowTask3.getCompletionDate());

		workflowTasks = WorkflowTaskManagerUtil.getWorkflowTasksByRole(
			testRoleId, Boolean.FALSE, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new WorkflowTaskNameComparator(true));
		assertEquals(0, workflowTasks.size());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance.getWorkflowInstanceId());
	}

	public void testGetWorkflowTasksByUser() throws Exception {
		WorkflowInstance workflowInstance =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
			USER.getUserId(), NAME_3, _workflowDefinition.getVersion(),
			null, null);

		long[] roleIds = USER.getRoleIds();
		assertTrue(roleIds.length > 0);

		long testRoleId = roleIds[0];

		List<WorkflowTask> workflowTasks =
			WorkflowTaskManagerUtil.getWorkflowTasksByUser(
				USER.getUserId(), null, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new WorkflowTaskNameComparator(true));
		assertEquals(0, workflowTasks.size());

		workflowInstance =
			WorkflowInstanceManagerUtil.getWorkflowInstance(
			workflowInstance.getWorkflowInstanceId());

		List<WorkflowInstance> childrenWorkflowInstances =
			workflowInstance.getChildren();

		for (WorkflowInstance childWorkflowInstance : childrenWorkflowInstances)
		{
			List<WorkflowTask> childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					childWorkflowInstance.getWorkflowInstanceId(), null,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					new WorkflowTaskNameComparator(true));
			assertEquals(1, childWorkflowTasks.size());
			WorkflowTask workflowTask = childWorkflowTasks.get(0);
			assertEquals(0L, workflowTask.getAssigneeRoleId());
			workflowTask = WorkflowTaskManagerUtil.assignWorkflowTaskToRole(
				USER.getUserId(), workflowTask.getWorkflowTaskId(), testRoleId,
				"Assign to role", null);
			workflowTask = WorkflowTaskManagerUtil.assignWorkflowTaskToUser(
				USER.getUserId(), workflowTask.getWorkflowTaskId(),
				USER.getUserId(), "Assign to user", null);
			assertEquals(testRoleId, workflowTask.getAssigneeRoleId());
		}

		workflowTasks =
			WorkflowTaskManagerUtil.getWorkflowTasksByUser(
				USER.getUserId(), null, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new WorkflowTaskNameComparator(true));
		assertEquals(3, workflowTasks.size());
		WorkflowTask workflowTask1 = workflowTasks.get(0);
		assertEquals(testRoleId, workflowTask1.getAssigneeRoleId());
		assertEquals("Task3-1", workflowTask1.getName());
		assertNull(workflowTask1.getCompletionDate());
		WorkflowTask workflowTask2 = workflowTasks.get(1);
		assertEquals(testRoleId, workflowTask2.getAssigneeRoleId());
		assertEquals("Task3-2", workflowTask2.getName());
		assertNull(workflowTask2.getCompletionDate());
		WorkflowTask workflowTask3 = workflowTasks.get(2);
		assertEquals(testRoleId, workflowTask3.getAssigneeRoleId());
		assertEquals("Task3-3", workflowTask3.getName());
		assertNull(workflowTask3.getCompletionDate());

		workflowTasks =
			WorkflowTaskManagerUtil.getWorkflowTasksByUser(
				USER.getUserId(), Boolean.TRUE, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, new WorkflowTaskNameComparator(true));
		assertEquals(0, workflowTasks.size());

		workflowTasks =
			WorkflowTaskManagerUtil.getWorkflowTasksByUser(
				USER.getUserId(), Boolean.FALSE, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, new WorkflowTaskNameComparator(true));
		assertEquals(3, workflowTasks.size());
		assertEquals(3, workflowTasks.size());
		workflowTask1 = workflowTasks.get(0);
		assertEquals(testRoleId, workflowTask1.getAssigneeRoleId());
		assertEquals("Task3-1", workflowTask1.getName());
		assertNull(workflowTask1.getCompletionDate());
		workflowTask2 = workflowTasks.get(1);
		assertEquals(testRoleId, workflowTask2.getAssigneeRoleId());
		assertEquals("Task3-2", workflowTask2.getName());
		assertNull(workflowTask2.getCompletionDate());
		workflowTask3 = workflowTasks.get(2);
		assertEquals(testRoleId, workflowTask3.getAssigneeRoleId());
		assertEquals("Task3-3", workflowTask3.getName());
		assertNull(workflowTask3.getCompletionDate());

		for (WorkflowInstance childWorkflowInstance : childrenWorkflowInstances)
		{
			List<WorkflowTask> childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					childWorkflowInstance.getWorkflowInstanceId(), null,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					new WorkflowTaskNameComparator(true));
			assertEquals(1, childWorkflowTasks.size());
			WorkflowTask workflowTask = childWorkflowTasks.get(0);
			WorkflowTaskManagerUtil.completeWorkflowTask(
				USER.getUserId(), workflowTask.getWorkflowTaskId(), null,
				"Complete workflow task", null);
		}

		workflowTasks =
			WorkflowTaskManagerUtil.getWorkflowTasksByUser(
				USER.getUserId(), null, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new WorkflowTaskNameComparator(true));
		assertEquals(4, workflowTasks.size());
		workflowTask1 = workflowTasks.get(0);
		assertEquals(testRoleId, workflowTask1.getAssigneeRoleId());
		assertEquals("Task3-1", workflowTask1.getName());
		assertNotNull(workflowTask1.getCompletionDate());
		workflowTask2 = workflowTasks.get(1);
		assertEquals(testRoleId, workflowTask2.getAssigneeRoleId());
		assertEquals("Task3-2", workflowTask2.getName());
		assertNotNull(workflowTask2.getCompletionDate());
		workflowTask3 = workflowTasks.get(2);
		assertEquals(testRoleId, workflowTask3.getAssigneeRoleId());
		assertEquals("Task3-3", workflowTask3.getName());
		assertNotNull(workflowTask3.getCompletionDate());
		WorkflowTask workflowTask4 = workflowTasks.get(3);
		assertEquals(0L, workflowTask4.getAssigneeRoleId());
		assertEquals("Task3-4", workflowTask4.getName());
		assertNull(workflowTask4.getCompletionDate());

		workflowTasks =
			WorkflowTaskManagerUtil.getWorkflowTasksByUser(
				USER.getUserId(), Boolean.TRUE, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, new WorkflowTaskNameComparator(true));
		assertEquals(3, workflowTasks.size());
		workflowTask1 = workflowTasks.get(0);
		assertEquals(testRoleId, workflowTask1.getAssigneeRoleId());
		assertEquals("Task3-1", workflowTask1.getName());
		assertNotNull(workflowTask1.getCompletionDate());
		workflowTask2 = workflowTasks.get(1);
		assertEquals(testRoleId, workflowTask2.getAssigneeRoleId());
		assertEquals("Task3-2", workflowTask2.getName());
		assertNotNull(workflowTask2.getCompletionDate());
		workflowTask3 = workflowTasks.get(2);
		assertEquals(testRoleId, workflowTask3.getAssigneeRoleId());
		assertEquals("Task3-3", workflowTask3.getName());
		assertNotNull(workflowTask3.getCompletionDate());

		workflowTasks =
			WorkflowTaskManagerUtil.getWorkflowTasksByUser(
				USER.getUserId(), Boolean.FALSE, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, new WorkflowTaskNameComparator(true));
		assertEquals(1, workflowTasks.size());
		workflowTask4 = workflowTasks.get(0);
		assertEquals(0L, workflowTask4.getAssigneeRoleId());
		assertEquals("Task3-4", workflowTask4.getName());
		assertNull(workflowTask4.getCompletionDate());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance.getWorkflowInstanceId());
	}

	public void testGetWorkflowTasksByWorkflowInstance() throws Exception {
		WorkflowInstance workflowInstance =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
			USER.getUserId(), NAME_3, _workflowDefinition.getVersion(),
			null, null);

		long[] roleIds = USER.getRoleIds();
		assertTrue(roleIds.length > 0);

		List<WorkflowTask> workflowTasks =
			WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
				workflowInstance.getWorkflowInstanceId(), null,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new WorkflowTaskNameComparator(true));
		assertEquals(0, workflowTasks.size());

		workflowInstance =
			WorkflowInstanceManagerUtil.getWorkflowInstance(
			workflowInstance.getWorkflowInstanceId());

		List<WorkflowInstance> childrenWorkflowInstances =
			workflowInstance.getChildren();

		for (WorkflowInstance childWorkflowInstance : childrenWorkflowInstances)
		{
			List<WorkflowTask> childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					childWorkflowInstance.getWorkflowInstanceId(), null,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					new WorkflowTaskNameComparator(true));
			System.out.println(childWorkflowInstance);
			System.out.println(childWorkflowTasks.get(0));
			assertEquals(1, childWorkflowTasks.size());
			WorkflowTask workflowTask = childWorkflowTasks.get(0);
			assertEquals(
				childWorkflowInstance.getWorkflowInstanceId(),
				workflowTask.getWorkflowInstanceId());
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
			workflowTask = childWorkflowTasks.get(0);
			assertEquals(
				childWorkflowInstance.getWorkflowInstanceId(),
				workflowTask.getWorkflowInstanceId());
		}

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance.getWorkflowInstanceId());
	}

	private WorkflowDefinition _workflowDefinition

}