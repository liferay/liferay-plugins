/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.testworkflow.test;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.kernel.workflow.WorkflowDefinitionManagerUtil;
import com.liferay.portal.kernel.workflow.WorkflowInstance;
import com.liferay.portal.kernel.workflow.WorkflowInstanceManagerUtil;
import com.liferay.portal.kernel.workflow.WorkflowTask;
import com.liferay.portal.kernel.workflow.WorkflowTaskManagerUtil;
import com.liferay.portal.kernel.workflow.comparator.WorkflowComparatorFactoryUtil;

import java.io.ByteArrayInputStream;

import java.util.List;

/**
 * @author Shuyang Zhou
 */
public class WorkflowTaskManagerTestCase extends WorkflowTestCase {

	public void setUp() throws Exception {
		super.setUp();

		_workflowDefinition =
			WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
				companyId, defaultUserId, DEFINITION_NAME_3,
				new ByteArrayInputStream(definitionBytes3));
	}

	public void tearDown() throws Exception {
		super.tearDown();

		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			companyId, defaultUserId, DEFINITION_NAME_3,
			_workflowDefinition.getVersion());
	}

	public void testAssignWorkflowTaskToRole() throws Exception {
		List<WorkflowTask> workflowTasks =
			WorkflowTaskManagerUtil.getWorkflowTasksByRole(
				companyId, guestRoleId, null, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS,
			WorkflowComparatorFactoryUtil.getTaskNameComparator(true));

		assertEquals(0, workflowTasks.size());

		WorkflowInstance workflowInstance =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				companyId, defaultUserId, DEFINITION_NAME_3,
				_workflowDefinition.getVersion(), null, null);

		List<WorkflowInstance> childrenWorkflowInstances =
			workflowInstance.getChildrenWorkflowInstances();

		for (WorkflowInstance childWorkflowInstance :
				childrenWorkflowInstances) {

			List<WorkflowTask> childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					companyId, childWorkflowInstance.getWorkflowInstanceId(),
					null, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					WorkflowComparatorFactoryUtil.getTaskNameComparator(true));

			assertEquals(1, childWorkflowTasks.size());

			WorkflowTask childWorkflowTask = childWorkflowTasks.get(0);

			assertEquals(0L, childWorkflowTask.getAssigneeRoleId());

			childWorkflowTask =
				WorkflowTaskManagerUtil.assignWorkflowTaskToRole(
					companyId, defaultUserId,
					childWorkflowTask.getWorkflowTaskId(), guestRoleId, null,
					null, null);

			assertEquals(guestRoleId, childWorkflowTask.getAssigneeRoleId());
		}

		workflowTasks = WorkflowTaskManagerUtil.getWorkflowTasksByRole(
			companyId, guestRoleId, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			WorkflowComparatorFactoryUtil.getTaskNameComparator(true));

		assertEquals(3, workflowTasks.size());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			companyId, workflowInstance.getWorkflowInstanceId());
	}

	public void testAssignWorkflowTaskTouser() throws Exception {
		List<WorkflowTask> workflowTasks =
			WorkflowTaskManagerUtil.getWorkflowTasksByUser(
				companyId, defaultUserId, null, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS,
			WorkflowComparatorFactoryUtil.getTaskNameComparator(true));

		assertEquals(0, workflowTasks.size());

		WorkflowInstance workflowInstance =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				companyId, defaultUserId, DEFINITION_NAME_3,
				_workflowDefinition.getVersion(), null, null);

		List<WorkflowInstance> childrenWorkflowInstances =
			workflowInstance.getChildrenWorkflowInstances();

		for (WorkflowInstance childWorkflowInstance :
				childrenWorkflowInstances) {

			List<WorkflowTask> childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					companyId, childWorkflowInstance.getWorkflowInstanceId(),
					null, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					WorkflowComparatorFactoryUtil.getTaskNameComparator(true));

			assertEquals(1, childWorkflowTasks.size());

			WorkflowTask childWorkflowTask = childWorkflowTasks.get(0);

			assertEquals(100L, childWorkflowTask.getAssigneeUserId());

			childWorkflowTask =
				WorkflowTaskManagerUtil.assignWorkflowTaskToRole(
					companyId, defaultUserId,
					childWorkflowTask.getWorkflowTaskId(), guestRoleId, null,
					null, null);

			childWorkflowTask =
				WorkflowTaskManagerUtil.assignWorkflowTaskToUser(
					companyId, defaultUserId,
					childWorkflowTask.getWorkflowTaskId(), defaultUserId, null,
					null, null);

			assertEquals(defaultUserId, childWorkflowTask.getAssigneeUserId());
		}

		workflowTasks = WorkflowTaskManagerUtil.getWorkflowTasksByUser(
			companyId, defaultUserId, null, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS,
			WorkflowComparatorFactoryUtil.getTaskNameComparator(true));

		assertEquals(3, workflowTasks.size());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			companyId, workflowInstance.getWorkflowInstanceId());
	}

	public void testCompleteWorkflowTask() throws Exception {
		WorkflowInstance workflowInstance =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				companyId, defaultUserId, DEFINITION_NAME_3,
				_workflowDefinition.getVersion(), null, null);

		workflowInstance = WorkflowInstanceManagerUtil.getWorkflowInstance(
			companyId, workflowInstance.getWorkflowInstanceId());

		List<WorkflowInstance> childrenWorkflowInstances =
			workflowInstance.getChildrenWorkflowInstances();

		for (WorkflowInstance childWorkflowInstance :
				childrenWorkflowInstances) {

			List<WorkflowTask> childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					companyId, childWorkflowInstance.getWorkflowInstanceId(),
					Boolean.TRUE, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					WorkflowComparatorFactoryUtil.getTaskNameComparator(true));

			assertEquals(0, childWorkflowTasks.size());

			childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					companyId, childWorkflowInstance.getWorkflowInstanceId(),
					Boolean.FALSE, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					WorkflowComparatorFactoryUtil.getTaskNameComparator(true));

			assertEquals(1, childWorkflowTasks.size());

			WorkflowTask childWorkflowTask = childWorkflowTasks.get(0);

			assertEquals(100L, childWorkflowTask.getAssigneeUserId());

			childWorkflowTask =
				WorkflowTaskManagerUtil.assignWorkflowTaskToRole(
					companyId, defaultUserId,
					childWorkflowTask.getWorkflowTaskId(), guestRoleId, null,
					null, null);

			childWorkflowTask =
				WorkflowTaskManagerUtil.assignWorkflowTaskToUser(
					companyId, defaultUserId,
					childWorkflowTask.getWorkflowTaskId(), defaultUserId, null,
					null, null);

			childWorkflowTask = WorkflowTaskManagerUtil.completeWorkflowTask(
				companyId, defaultUserId, childWorkflowTask.getWorkflowTaskId(),
				null, null, null);

			childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					companyId, childWorkflowInstance.getWorkflowInstanceId(),
					Boolean.TRUE, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					WorkflowComparatorFactoryUtil.getTaskNameComparator(true));

			assertEquals(1, childWorkflowTasks.size());

			WorkflowTask completedWorkflowTask = childWorkflowTasks.get(0);

			assertEquals(
				childWorkflowTask.getWorkflowTaskId(),
				completedWorkflowTask.getWorkflowTaskId());
		}

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			companyId, workflowInstance.getWorkflowInstanceId());
	}

	public void testGetNextTransitionNames() throws Exception {
		WorkflowInstance workflowInstance =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				companyId, defaultUserId, DEFINITION_NAME_3,
				_workflowDefinition.getVersion(), null, null);

		List<WorkflowInstance> childrenWorkflowInstances =
			workflowInstance.getChildrenWorkflowInstances();

		assertEquals(3, childrenWorkflowInstances.size());

		WorkflowInstance childWorkflowInstance1 = childrenWorkflowInstances.get(
			0);

		List<WorkflowTask> childWorkflowTasks1 =
			WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
				companyId, childWorkflowInstance1.getWorkflowInstanceId(), null,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				WorkflowComparatorFactoryUtil.getTaskNameComparator(true));

		assertEquals(1, childWorkflowTasks1.size());

		WorkflowTask workflowTask1 = childWorkflowTasks1.get(0);

		workflowTask1 = WorkflowTaskManagerUtil.assignWorkflowTaskToRole(
			companyId, defaultUserId, workflowTask1.getWorkflowTaskId(),
			guestRoleId, null, null, null);

		workflowTask1 = WorkflowTaskManagerUtil.assignWorkflowTaskToUser(
			companyId, defaultUserId, workflowTask1.getWorkflowTaskId(),
			defaultUserId, null, null, null);

		List<String> nextTransitionNames1 =
			WorkflowTaskManagerUtil.getNextTransitionNames(
				companyId, defaultUserId, workflowTask1.getWorkflowTaskId());

		assertEquals(1, nextTransitionNames1.size());
		assertEquals("toJoinNode", nextTransitionNames1.get(0));

		WorkflowInstance childWorkflowInstance2 = childrenWorkflowInstances.get(
			1);

		List<WorkflowTask> childWorkflowTasks2 =
			WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
				companyId, childWorkflowInstance2.getWorkflowInstanceId(), null,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				WorkflowComparatorFactoryUtil.getTaskNameComparator(true));

		assertEquals(1, childWorkflowTasks2.size());

		WorkflowTask workflowTask2 = childWorkflowTasks2.get(0);

		workflowTask2 = WorkflowTaskManagerUtil.assignWorkflowTaskToRole(
			companyId, defaultUserId, workflowTask2.getWorkflowTaskId(),
			guestRoleId, null, null, null);

		workflowTask2 = WorkflowTaskManagerUtil.assignWorkflowTaskToUser(
			companyId, defaultUserId, workflowTask2.getWorkflowTaskId(),
			defaultUserId, null, null, null);

		List<String> nextTransitionNames2 =
			WorkflowTaskManagerUtil.getNextTransitionNames(
				companyId, defaultUserId, workflowTask2.getWorkflowTaskId());

		assertEquals(1, nextTransitionNames2.size());
		assertEquals("toTaskNode4", nextTransitionNames2.get(0));

		WorkflowInstance childWorkflowInstance3 = childrenWorkflowInstances.get(
			2);

		List<WorkflowTask> childWorkflowTasks3 =
			WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
				companyId, childWorkflowInstance3.getWorkflowInstanceId(), null,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				WorkflowComparatorFactoryUtil.getTaskNameComparator(true));

		assertEquals(1, childWorkflowTasks3.size());

		WorkflowTask workflowTask3 = childWorkflowTasks3.get(0);

		workflowTask3 = WorkflowTaskManagerUtil.assignWorkflowTaskToRole(
			companyId, defaultUserId, workflowTask3.getWorkflowTaskId(),
			guestRoleId, null, null, null);

		workflowTask3 = WorkflowTaskManagerUtil.assignWorkflowTaskToUser(
			companyId, defaultUserId, workflowTask3.getWorkflowTaskId(),
			defaultUserId,null, null, null);

		List<String> nextTransitionNames3 =
			WorkflowTaskManagerUtil.getNextTransitionNames(
				companyId, defaultUserId, workflowTask3.getWorkflowTaskId());

		assertEquals(1, nextTransitionNames3.size());
		assertEquals("toJoinNode", nextTransitionNames3.get(0));

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			companyId, workflowInstance.getWorkflowInstanceId());
	}

	public void testGetWorkflowTaskCountByRole() throws Exception {
		int count = WorkflowTaskManagerUtil.getWorkflowTaskCountByRole(
			companyId, guestRoleId, null);

		assertEquals(0, count);

		WorkflowInstance workflowInstance =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				companyId, defaultUserId, DEFINITION_NAME_3,
				_workflowDefinition.getVersion(), null, null);

		List<WorkflowInstance> childrenWorkflowInstances =
			workflowInstance.getChildrenWorkflowInstances();

		for (WorkflowInstance childWorkflowInstance :
				childrenWorkflowInstances) {

			List<WorkflowTask> childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					companyId, childWorkflowInstance.getWorkflowInstanceId(),
					null, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					WorkflowComparatorFactoryUtil.getTaskNameComparator(true));

			assertEquals(1, childWorkflowTasks.size());

			WorkflowTask childWorkflowTask = childWorkflowTasks.get(0);

			assertEquals(0L, childWorkflowTask.getAssigneeRoleId());

			childWorkflowTask =
				WorkflowTaskManagerUtil.assignWorkflowTaskToRole(
					companyId, defaultUserId,
					childWorkflowTask.getWorkflowTaskId(), guestRoleId, null,
					null, null);

			childWorkflowTask =
				WorkflowTaskManagerUtil.assignWorkflowTaskToUser(
					companyId, defaultUserId,
					childWorkflowTask.getWorkflowTaskId(), defaultUserId, null,
					null, null);

			assertEquals(guestRoleId, childWorkflowTask.getAssigneeRoleId());
		}

		count = WorkflowTaskManagerUtil.getWorkflowTaskCountByRole(
			companyId, guestRoleId, null);

		assertEquals(3, count);

		count = WorkflowTaskManagerUtil.getWorkflowTaskCountByRole(
			companyId, guestRoleId, Boolean.TRUE);

		assertEquals(0, count);

		count = WorkflowTaskManagerUtil.getWorkflowTaskCountByRole(
			companyId, guestRoleId, Boolean.FALSE);

		assertEquals(3, count);

		for (WorkflowInstance childWorkflowInstance :
				childrenWorkflowInstances) {

			List<WorkflowTask> childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					companyId, childWorkflowInstance.getWorkflowInstanceId(),
					null, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					WorkflowComparatorFactoryUtil.getTaskNameComparator(true));

			assertEquals(1, childWorkflowTasks.size());

			WorkflowTask childWorkflowTask = childWorkflowTasks.get(0);

			WorkflowTaskManagerUtil.completeWorkflowTask(
				companyId, defaultUserId, childWorkflowTask.getWorkflowTaskId(),
				null, null, null);
		}

		count = WorkflowTaskManagerUtil.getWorkflowTaskCountByRole(
			companyId, guestRoleId, null);

		assertEquals(3, count);

		count = WorkflowTaskManagerUtil.getWorkflowTaskCountByRole(
			companyId, guestRoleId, Boolean.TRUE);

		assertEquals(3, count);

		count = WorkflowTaskManagerUtil.getWorkflowTaskCountByRole(
			companyId, guestRoleId, Boolean.FALSE);

		assertEquals(0, count);

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			companyId, workflowInstance.getWorkflowInstanceId());
	}

	public void testGetWorkflowTaskCountByUser() throws Exception {
		int count = WorkflowTaskManagerUtil.getWorkflowTaskCountByUser(
			companyId, defaultUserId, null);

		assertEquals(0, count);

		WorkflowInstance workflowInstance =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				companyId, defaultUserId, DEFINITION_NAME_3,
				_workflowDefinition.getVersion(), null, null);

		List<WorkflowInstance> childrenWorkflowInstances =
			workflowInstance.getChildrenWorkflowInstances();

		for (WorkflowInstance childWorkflowInstance :
				childrenWorkflowInstances) {

			List<WorkflowTask> childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					companyId, childWorkflowInstance.getWorkflowInstanceId(),
					null, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					WorkflowComparatorFactoryUtil.getTaskNameComparator(true));

			assertEquals(1, childWorkflowTasks.size());

			WorkflowTask childWorkflowTask = childWorkflowTasks.get(0);

			assertEquals(0L, childWorkflowTask.getAssigneeRoleId());

			childWorkflowTask =
				WorkflowTaskManagerUtil.assignWorkflowTaskToRole(
					companyId, defaultUserId,
					childWorkflowTask.getWorkflowTaskId(), guestRoleId, null,
					null, null);

			childWorkflowTask =
				WorkflowTaskManagerUtil.assignWorkflowTaskToUser(
					companyId, defaultUserId,
					childWorkflowTask.getWorkflowTaskId(), defaultUserId, null,
					null, null);

			assertEquals(guestRoleId, childWorkflowTask.getAssigneeRoleId());
		}

		count = WorkflowTaskManagerUtil.getWorkflowTaskCountByUser(
			companyId, defaultUserId, null);

		assertEquals(3, count);

		count = WorkflowTaskManagerUtil.getWorkflowTaskCountByUser(
			companyId, defaultUserId, Boolean.TRUE);

		assertEquals(0, count);

		count = WorkflowTaskManagerUtil.getWorkflowTaskCountByUser(
			companyId, defaultUserId, Boolean.FALSE);

		assertEquals(3, count);

		for (WorkflowInstance childWorkflowInstance :
				childrenWorkflowInstances) {

			List<WorkflowTask> childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					companyId, childWorkflowInstance.getWorkflowInstanceId(),
					null, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					WorkflowComparatorFactoryUtil.getTaskNameComparator(true));

			assertEquals(1, childWorkflowTasks.size());

			WorkflowTask childWorkflowTask = childWorkflowTasks.get(0);

			WorkflowTaskManagerUtil.completeWorkflowTask(
				companyId, defaultUserId, childWorkflowTask.getWorkflowTaskId(),
				null, null, null);
		}

		count = WorkflowTaskManagerUtil.getWorkflowTaskCountByUser(
			companyId, defaultUserId, null);

		assertEquals(4, count);

		count = WorkflowTaskManagerUtil.getWorkflowTaskCountByUser(
			companyId, defaultUserId, Boolean.TRUE);

		assertEquals(3, count);

		count = WorkflowTaskManagerUtil.getWorkflowTaskCountByUser(
			companyId, defaultUserId, Boolean.FALSE);

		assertEquals(1, count);

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			companyId, workflowInstance.getWorkflowInstanceId());
	}

	public void testGetWorkflowTaskCountByWorkflowInstance() throws Exception {
		WorkflowInstance workflowInstance =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				companyId, defaultUserId, DEFINITION_NAME_3,
				_workflowDefinition.getVersion(), null, null);

		int count =
			WorkflowTaskManagerUtil.getWorkflowTaskCountByWorkflowInstance(
				companyId, workflowInstance.getWorkflowInstanceId(), null);

		assertEquals(0, count);

		List<WorkflowInstance> childrenWorkflowInstances =
			workflowInstance.getChildrenWorkflowInstances();

		for (WorkflowInstance childWorkflowInstance :
				childrenWorkflowInstances) {

			List<WorkflowTask> childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					companyId, childWorkflowInstance.getWorkflowInstanceId(),
					null, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					WorkflowComparatorFactoryUtil.getTaskNameComparator(true));

			assertEquals(1, childWorkflowTasks.size());

			childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					companyId, childWorkflowInstance.getWorkflowInstanceId(),
					Boolean.TRUE, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					WorkflowComparatorFactoryUtil.getTaskNameComparator(true));

			assertEquals(0, childWorkflowTasks.size());

			childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					companyId, childWorkflowInstance.getWorkflowInstanceId(),
					Boolean.FALSE, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					WorkflowComparatorFactoryUtil.getTaskNameComparator(true));

			assertEquals(1, childWorkflowTasks.size());
		}

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			companyId, workflowInstance.getWorkflowInstanceId());
	}

	public void testGetWorkflowTasksByRole() throws Exception {
		List<WorkflowTask> workflowTasks =
			WorkflowTaskManagerUtil.getWorkflowTasksByRole(
				companyId, guestRoleId, null, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS,
			WorkflowComparatorFactoryUtil.getTaskNameComparator(true));

		assertEquals(0, workflowTasks.size());

		WorkflowInstance workflowInstance =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				companyId, defaultUserId, DEFINITION_NAME_3,
				_workflowDefinition.getVersion(), null, null);

		List<WorkflowInstance> childrenWorkflowInstances =
			workflowInstance.getChildrenWorkflowInstances();

		for (WorkflowInstance childWorkflowInstance :
				childrenWorkflowInstances) {

			List<WorkflowTask> childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					companyId, childWorkflowInstance.getWorkflowInstanceId(),
					null, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					WorkflowComparatorFactoryUtil.getTaskNameComparator(true));

			assertEquals(1, childWorkflowTasks.size());

			WorkflowTask childWorkflowTask = childWorkflowTasks.get(0);

			assertEquals(0L, childWorkflowTask.getAssigneeRoleId());

			childWorkflowTask =
				WorkflowTaskManagerUtil.assignWorkflowTaskToRole(
					companyId, defaultUserId,
					childWorkflowTask.getWorkflowTaskId(), guestRoleId, null,
					null, null);

			childWorkflowTask =
				WorkflowTaskManagerUtil.assignWorkflowTaskToUser(
					companyId, defaultUserId,
					childWorkflowTask.getWorkflowTaskId(), defaultUserId, null,
					null, null);

			assertEquals(guestRoleId, childWorkflowTask.getAssigneeRoleId());
		}

		workflowTasks = WorkflowTaskManagerUtil.getWorkflowTasksByRole(
			companyId, guestRoleId, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			WorkflowComparatorFactoryUtil.getTaskNameComparator(true));

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
			companyId, guestRoleId, Boolean.TRUE, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS,
			WorkflowComparatorFactoryUtil.getTaskNameComparator(true));

		assertEquals(0, workflowTasks.size());

		workflowTasks = WorkflowTaskManagerUtil.getWorkflowTasksByRole(
			companyId, guestRoleId, Boolean.FALSE, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS,
			WorkflowComparatorFactoryUtil.getTaskNameComparator(true));

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
					companyId, childWorkflowInstance.getWorkflowInstanceId(),
					null, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					WorkflowComparatorFactoryUtil.getTaskNameComparator(true));

			assertEquals(1, childWorkflowTasks.size());

			WorkflowTask childWorkflowTask = childWorkflowTasks.get(0);

			WorkflowTaskManagerUtil.completeWorkflowTask(
				companyId, defaultUserId, childWorkflowTask.getWorkflowTaskId(),
				null, null, null);
		}

		workflowTasks = WorkflowTaskManagerUtil.getWorkflowTasksByRole(
			companyId, guestRoleId, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			WorkflowComparatorFactoryUtil.getTaskNameComparator(true));

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
			companyId, guestRoleId, Boolean.TRUE, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS,
			WorkflowComparatorFactoryUtil.getTaskNameComparator(true));

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
			companyId, guestRoleId, Boolean.FALSE, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS,
			WorkflowComparatorFactoryUtil.getTaskNameComparator(true));

		assertEquals(0, workflowTasks.size());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			companyId, workflowInstance.getWorkflowInstanceId());
	}

	public void testGetWorkflowTasksByUser() throws Exception {
		List<WorkflowTask> workflowTasks =
			WorkflowTaskManagerUtil.getWorkflowTasksByUser(
				companyId, defaultUserId, null, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS,
			WorkflowComparatorFactoryUtil.getTaskNameComparator(true));

		assertEquals(0, workflowTasks.size());

		WorkflowInstance workflowInstance =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				companyId, defaultUserId, DEFINITION_NAME_3,
				_workflowDefinition.getVersion(), null, null);

		List<WorkflowInstance> childrenWorkflowInstances =
			workflowInstance.getChildrenWorkflowInstances();

		for (WorkflowInstance childWorkflowInstance :
				childrenWorkflowInstances) {

			List<WorkflowTask> childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					companyId, childWorkflowInstance.getWorkflowInstanceId(),
					null, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					WorkflowComparatorFactoryUtil.getTaskNameComparator(true));

			assertEquals(1, childWorkflowTasks.size());

			WorkflowTask childWorkflowTask = childWorkflowTasks.get(0);

			assertEquals(0L, childWorkflowTask.getAssigneeRoleId());

			childWorkflowTask =
				WorkflowTaskManagerUtil.assignWorkflowTaskToRole(
					companyId, defaultUserId,
					childWorkflowTask.getWorkflowTaskId(), guestRoleId, null,
					null, null);

			childWorkflowTask =
				WorkflowTaskManagerUtil.assignWorkflowTaskToUser(
					companyId, defaultUserId,
					childWorkflowTask.getWorkflowTaskId(), defaultUserId, null,
					null, null);

			assertEquals(defaultUserId, childWorkflowTask.getAssigneeUserId());
		}

		workflowTasks = WorkflowTaskManagerUtil.getWorkflowTasksByUser(
			companyId, defaultUserId, null, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS,
			WorkflowComparatorFactoryUtil.getTaskNameComparator(true));

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
			companyId, defaultUserId, Boolean.TRUE, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS,
			WorkflowComparatorFactoryUtil.getTaskNameComparator(true));

		assertEquals(0, workflowTasks.size());

		workflowTasks = WorkflowTaskManagerUtil.getWorkflowTasksByUser(
			companyId, defaultUserId, Boolean.FALSE, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS,
			WorkflowComparatorFactoryUtil.getTaskNameComparator(true));

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
					companyId, childWorkflowInstance.getWorkflowInstanceId(),
					null, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					WorkflowComparatorFactoryUtil.getTaskNameComparator(true));

			assertEquals(1, childWorkflowTasks.size());

			WorkflowTask childWorkflowTask = childWorkflowTasks.get(0);

			WorkflowTaskManagerUtil.completeWorkflowTask(
				companyId, defaultUserId, childWorkflowTask.getWorkflowTaskId(),
				null, null, null);
		}

		workflowTasks = WorkflowTaskManagerUtil.getWorkflowTasksByUser(
			companyId, defaultUserId, null, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS,
			WorkflowComparatorFactoryUtil.getTaskNameComparator(true));

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
			companyId, defaultUserId, Boolean.TRUE, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS,
			WorkflowComparatorFactoryUtil.getTaskNameComparator(true));

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
			companyId, defaultUserId, Boolean.FALSE, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS,
			WorkflowComparatorFactoryUtil.getTaskNameComparator(true));

		assertEquals(1, workflowTasks.size());

		workflowTask4 = workflowTasks.get(0);

		assertEquals(0L, workflowTask4.getAssigneeUserId());
		assertNull(workflowTask4.getCompletionDate());
		assertEquals("Task3-4", workflowTask4.getName());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			companyId, workflowInstance.getWorkflowInstanceId());
	}

	public void testGetWorkflowTasksByWorkflowInstance() throws Exception {
		WorkflowInstance workflowInstance =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				companyId, defaultUserId, DEFINITION_NAME_3,
				_workflowDefinition.getVersion(), null, null);

		List<WorkflowTask> workflowTasks =
			WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
				companyId, workflowInstance.getWorkflowInstanceId(), null,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				WorkflowComparatorFactoryUtil.getTaskNameComparator(true));

		assertEquals(0, workflowTasks.size());

		List<WorkflowInstance> childrenWorkflowInstances =
			workflowInstance.getChildrenWorkflowInstances();

		for (WorkflowInstance childWorkflowInstance :
				childrenWorkflowInstances) {

			List<WorkflowTask> childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					companyId, childWorkflowInstance.getWorkflowInstanceId(),
					null, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					WorkflowComparatorFactoryUtil.getTaskNameComparator(true));

			assertEquals(1, childWorkflowTasks.size());

			WorkflowTask childWorkflowTask = childWorkflowTasks.get(0);

			assertEquals(
				childWorkflowInstance.getWorkflowInstanceId(),
				childWorkflowTask.getWorkflowInstanceId());

			childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					companyId, childWorkflowInstance.getWorkflowInstanceId(),
					Boolean.TRUE, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					WorkflowComparatorFactoryUtil.getTaskNameComparator(true));

			assertEquals(0, childWorkflowTasks.size());

			childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					companyId, childWorkflowInstance.getWorkflowInstanceId(),
					Boolean.FALSE, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					WorkflowComparatorFactoryUtil.getTaskNameComparator(true));

			assertEquals(1, childWorkflowTasks.size());

			childWorkflowTask = childWorkflowTasks.get(0);

			assertEquals(
				childWorkflowInstance.getWorkflowInstanceId(),
				childWorkflowTask.getWorkflowInstanceId());
		}

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			companyId, workflowInstance.getWorkflowInstanceId());
	}

	private WorkflowDefinition _workflowDefinition;

}