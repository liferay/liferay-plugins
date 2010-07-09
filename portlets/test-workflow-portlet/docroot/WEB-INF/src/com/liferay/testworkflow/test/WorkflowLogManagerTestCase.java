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
import com.liferay.portal.kernel.workflow.WorkflowLog;
import com.liferay.portal.kernel.workflow.WorkflowLogManagerUtil;
import com.liferay.portal.kernel.workflow.WorkflowTask;
import com.liferay.portal.kernel.workflow.WorkflowTaskManagerUtil;
import com.liferay.portal.kernel.workflow.comparator.WorkflowComparatorFactoryUtil;

import java.io.ByteArrayInputStream;

import java.util.List;

/**
 * @author Shuyang Zhou
 * @author Marcellus Tavares
 */
public class WorkflowLogManagerTestCase extends WorkflowTestCase {

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

	public void testGetWorkflowLogCount() throws Exception {
		WorkflowInstance workflowInstance =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				companyId, defaultUserId, _workflowDefinition.getName(),
				_workflowDefinition.getVersion(), null, null);

		workflowInstance = WorkflowInstanceManagerUtil.getWorkflowInstance(
			companyId, workflowInstance.getWorkflowInstanceId());

		List<WorkflowInstance> childrenWorkflowInstances =
			workflowInstance.getChildrenWorkflowInstances();

		assertEquals(3, childrenWorkflowInstances.size());

		for (WorkflowInstance childWorkflowInstance :
				childrenWorkflowInstances) {

			List<WorkflowTask> childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					companyId, childWorkflowInstance.getWorkflowInstanceId(),
					Boolean.FALSE, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					WorkflowComparatorFactoryUtil.getTaskNameComparator(true));

			assertEquals(1, childWorkflowTasks.size());

			WorkflowTask childWorkflowTask = childWorkflowTasks.get(0);

			int childCount = WorkflowLogManagerUtil.getWorkflowLogCount(
				companyId, childWorkflowTask.getWorkflowTaskId());

			assertEquals(0, childCount);

			WorkflowTaskManagerUtil.completeWorkflowTask(
				companyId, childWorkflowTask.getAssigneeUserId(),
				childWorkflowTask.getWorkflowTaskId(), null, null, null);

			childCount = WorkflowLogManagerUtil.getWorkflowLogCount(
				companyId, childWorkflowTask.getWorkflowTaskId());

			assertEquals(1, childCount);
		}

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			companyId, workflowInstance.getWorkflowInstanceId());
	}

	public void testGetWorkflowLogs() throws Exception {
		WorkflowInstance workflowInstance =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				companyId, defaultUserId, _workflowDefinition.getName(),
				_workflowDefinition.getVersion(), null, null);

		workflowInstance = WorkflowInstanceManagerUtil.getWorkflowInstance(
			companyId, workflowInstance.getWorkflowInstanceId());

		List<WorkflowInstance> childrenWorkflowInstances =
			workflowInstance.getChildrenWorkflowInstances();

		assertEquals(3, childrenWorkflowInstances.size());

		for (WorkflowInstance childWorkflowInstance :
				childrenWorkflowInstances) {

			List<WorkflowTask> childWorkflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
					companyId, childWorkflowInstance.getWorkflowInstanceId(),
					Boolean.FALSE, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					WorkflowComparatorFactoryUtil.getTaskNameComparator(true));

			assertEquals(1, childWorkflowTasks.size());

			WorkflowTask childWorkflowTask = childWorkflowTasks.get(0);

			List<WorkflowLog> childWorkflowLogs =
				WorkflowLogManagerUtil.getWorkflowLogs(
					companyId, childWorkflowTask.getWorkflowTaskId(),
					QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					WorkflowComparatorFactoryUtil.getLogCreateDateComparator(
						true));

			assertTrue(childWorkflowLogs.isEmpty());

			WorkflowTaskManagerUtil.completeWorkflowTask(
				companyId, childWorkflowTask.getAssigneeUserId(),
				childWorkflowTask.getWorkflowTaskId(), null, null, null);

			childWorkflowLogs = WorkflowLogManagerUtil.getWorkflowLogs(
				companyId, childWorkflowTask.getWorkflowTaskId(),
				QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				WorkflowComparatorFactoryUtil.getLogCreateDateComparator(true));

			assertTrue(!childWorkflowLogs.isEmpty());
		}

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			companyId, workflowInstance.getWorkflowInstanceId());
	}

	private WorkflowDefinition _workflowDefinition;

}