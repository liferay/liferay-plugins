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
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.kernel.workflow.WorkflowInstance;
import com.liferay.portal.kernel.workflow.WorkflowInstanceManagerUtil;
import com.liferay.portal.kernel.workflow.comparator.WorkflowComparatorFactoryUtil;

import java.io.ByteArrayInputStream;
import java.io.Serializable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Shuyang Zhou
 */
public class WorkflowInstanceManagerTestCase extends WorkflowTestCase {

	public void setUp() throws Exception {
		super.setUp();

		_workflowDefinition1 =
			WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
				companyId, defaultUserId, DEFINITION_NAME_1,
				new ByteArrayInputStream(definitionBytes1));

		_workflowDefinition2 =
			WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
				companyId, defaultUserId, DEFINITION_NAME_2,
				new ByteArrayInputStream(definitionBytes2));

		_workflowDefinition3 =
			WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
				companyId, defaultUserId, DEFINITION_NAME_3,
				new ByteArrayInputStream(definitionBytes3));

		_workflowDefinition4 =
			WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
				companyId, defaultUserId, DEFINITION_NAME_4,
				new ByteArrayInputStream(definitionBytes4));
	}

	public void tearDown() throws Exception {
		super.tearDown();

		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			companyId, defaultUserId, DEFINITION_NAME_1,
			_workflowDefinition1.getVersion());

		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			companyId, defaultUserId, DEFINITION_NAME_2,
			_workflowDefinition2.getVersion());

		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			companyId, defaultUserId, DEFINITION_NAME_3,
			_workflowDefinition3.getVersion());

		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			companyId, defaultUserId, DEFINITION_NAME_4,
			_workflowDefinition4.getVersion());
	}

	public void testDeleteWorkflowInstance() throws Exception {
		WorkflowInstance workflowInstance =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				companyId, defaultUserId, _workflowDefinition1.getName(),
				_workflowDefinition1.getVersion(), null, null);

		assertNotNull(workflowInstance);

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			companyId, workflowInstance.getWorkflowInstanceId());

		try{
			WorkflowInstanceManagerUtil.getWorkflowInstance(
				companyId, workflowInstance.getWorkflowInstanceId());

			fail();
		}
		catch(WorkflowException we) {
		}
	}

	public void testGetNextTransitionNames() throws Exception {

		// Prepare

		WorkflowInstance workflowInstance1 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				companyId, defaultUserId, _workflowDefinition1.getName(),
				_workflowDefinition1.getVersion(), null, null);

		WorkflowInstance workflowInstance2 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				companyId, defaultUserId, _workflowDefinition2.getName(),
				_workflowDefinition2.getVersion(), null, null);

		WorkflowInstance workflowInstance3 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				companyId, defaultUserId, _workflowDefinition3.getName(),
				_workflowDefinition3.getVersion(), null, null);

		// Workflow instance 1

		List<String> nextTransitionNames1 =
			WorkflowInstanceManagerUtil.getNextTransitionNames(
				companyId, defaultUserId,
				workflowInstance1.getWorkflowInstanceId());

		assertEquals(1, nextTransitionNames1.size());
		assertEquals("toTaskNode", nextTransitionNames1.get(0));

		// Workflow instance 2

		List<String> nextTransitionNames2 =
			WorkflowInstanceManagerUtil.getNextTransitionNames(
				companyId, defaultUserId,
				workflowInstance2.getWorkflowInstanceId());

		assertEquals(1, nextTransitionNames2.size());
		assertEquals("toEnd", nextTransitionNames2.get(0));

		// Workflow instance 3

		List<String> nextTransitionNames3 =
			WorkflowInstanceManagerUtil.getNextTransitionNames(
				companyId, defaultUserId,
				workflowInstance3.getWorkflowInstanceId());

		assertEquals(3, nextTransitionNames3.size());
		assertTrue(nextTransitionNames3.contains("branch1"));
		assertTrue(nextTransitionNames3.contains("branch2"));
		assertTrue(nextTransitionNames3.contains("branch3"));

		// Clean up

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			companyId, workflowInstance1.getWorkflowInstanceId());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			companyId, workflowInstance2.getWorkflowInstanceId());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			companyId, workflowInstance3.getWorkflowInstanceId());
	}

	public void testGetWorkflowInstance() throws Exception {

		// Prepare

		WorkflowInstance workflowInstance1 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				companyId, defaultUserId, _workflowDefinition1.getName(),
				_workflowDefinition1.getVersion(), null, null);

		WorkflowInstance workflowInstance2 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				companyId, defaultUserId, _workflowDefinition2.getName(),
				_workflowDefinition2.getVersion(), null, null);

		WorkflowInstance workflowInstance3 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				companyId, defaultUserId, _workflowDefinition3.getName(),
				_workflowDefinition3.getVersion(), null, null);

		// Workflow instance 1

		long workflowInstanceId1 = workflowInstance1.getWorkflowInstanceId();

		workflowInstance1 = WorkflowInstanceManagerUtil.getWorkflowInstance(
			companyId, workflowInstance1.getWorkflowInstanceId());

		assertEquals(0, workflowInstance1.getChildrenWorkflowInstanceCount());

		Map<String, Serializable> workflowContext1 =
			workflowInstance1.getWorkflowContext();

		assertNotNull(workflowContext1);
		assertEquals(1, workflowContext1.size());
		assertEquals("success", workflowContext1.get("javaNode11"));

		assertEquals("State1-1", workflowInstance1.getState());
		assertNull(workflowInstance1.getEndDate());
		assertNull(workflowInstance1.getParentWorkflowInstance());
		assertNotNull(workflowInstance1.getStartDate());
		assertEquals(
			DEFINITION_NAME_1, workflowInstance1.getWorkflowDefinitionName());
		assertEquals(
			_workflowDefinition1.getVersion(),
			workflowInstance1.getWorkflowDefinitionVersion());
		assertEquals(
			workflowInstanceId1, workflowInstance1.getWorkflowInstanceId());

		// Workflow instance 2

		long workflowInstanceId2 = workflowInstance2.getWorkflowInstanceId();

		workflowInstance2 = WorkflowInstanceManagerUtil.getWorkflowInstance(
			companyId, workflowInstance2.getWorkflowInstanceId());

		assertEquals(0, workflowInstance2.getChildrenWorkflowInstanceCount());

		Map<String, Serializable> workflowContext2 =
			workflowInstance2.getWorkflowContext();

		assertNotNull(workflowContext2);
		assertEquals(1, workflowContext2.size());
		assertEquals("success", workflowContext2.get("javaNode21"));

		assertEquals("taskNode3", workflowInstance2.getState());
		assertNull(workflowInstance2.getEndDate());
		assertNull(workflowInstance2.getParentWorkflowInstance());
		assertNotNull(workflowInstance2.getStartDate());
		assertEquals(
			DEFINITION_NAME_2, workflowInstance2.getWorkflowDefinitionName());
		assertEquals(
			_workflowDefinition2.getVersion(),
			workflowInstance2.getWorkflowDefinitionVersion());
		assertEquals(
			workflowInstanceId2, workflowInstance2.getWorkflowInstanceId());

		// Workflow instance 3

		long workflowInstanceId3 = workflowInstance3.getWorkflowInstanceId();

		workflowInstance3 = WorkflowInstanceManagerUtil.getWorkflowInstance(
			companyId, workflowInstance3.getWorkflowInstanceId());

		List<WorkflowInstance> childrenInstances =
			workflowInstance3.getChildrenWorkflowInstances();

		assertEquals(3, childrenInstances.size());

		// Child workflow instance 1

		WorkflowInstance childWorkflowInstance3_1 = childrenInstances.get(0);

		assertEquals(
			0, childWorkflowInstance3_1.getChildrenWorkflowInstanceCount());

		Map<String, Serializable> workflowContext3_1 =
			childWorkflowInstance3_1.getWorkflowContext();

		assertNotNull(workflowContext3_1);
		assertEquals(1, workflowContext3_1.size());
		assertEquals("success", workflowContext3_1.get("javaNode31"));

		assertEquals("taskNode1", childWorkflowInstance3_1.getState());
		assertNull(childWorkflowInstance3_1.getEndDate());
		assertEquals(
			workflowInstanceId3,
			childWorkflowInstance3_1.getParentWorkflowInstanceId());
		assertNotNull(childWorkflowInstance3_1.getStartDate());
		assertEquals(
			DEFINITION_NAME_3,
			childWorkflowInstance3_1.getWorkflowDefinitionName());
		assertEquals(
			_workflowDefinition3.getVersion(),
			childWorkflowInstance3_1.getWorkflowDefinitionVersion());

		// Child workflow instance 2

		WorkflowInstance childWorkflowInstance3_2 = childrenInstances.get(1);

		assertEquals(
			0, childWorkflowInstance3_2.getChildrenWorkflowInstanceCount());

		Map<String, Serializable> workflowContext3_2 =
			childWorkflowInstance3_2.getWorkflowContext();

		assertNotNull(workflowContext3_2);
		assertEquals(1, workflowContext3_2.size());
		assertEquals("success", workflowContext3_2.get("javaNode31"));

		assertEquals("taskNode2", childWorkflowInstance3_2.getState());
		assertNull(childWorkflowInstance3_2.getEndDate());
		assertEquals(
			workflowInstanceId3,
			childWorkflowInstance3_2.getParentWorkflowInstanceId());
		assertNotNull(childWorkflowInstance3_2.getStartDate());
		assertEquals(
			DEFINITION_NAME_3,
			childWorkflowInstance3_2.getWorkflowDefinitionName());
		assertEquals(
			_workflowDefinition3.getVersion(),
			childWorkflowInstance3_2.getWorkflowDefinitionVersion());

		// Child workflow instance 3

		WorkflowInstance childWorkflowInstance3_3 = childrenInstances.get(2);

		assertEquals(
			0, childWorkflowInstance3_3.getChildrenWorkflowInstanceCount());

		Map<String, Serializable> workflowContext3_3 =
			childWorkflowInstance3_3.getWorkflowContext();

		assertNotNull(workflowContext3_3);
		assertEquals(1, workflowContext3_3.size());
		assertEquals("success", workflowContext3_3.get("javaNode31"));

		assertEquals("taskNode3", childWorkflowInstance3_3.getState());
		assertNull(childWorkflowInstance3_3.getEndDate());
		assertEquals(
			workflowInstanceId3,
			childWorkflowInstance3_3.getParentWorkflowInstanceId());
		assertNotNull(childWorkflowInstance3_3.getStartDate());
		assertEquals(
			DEFINITION_NAME_3,
			childWorkflowInstance3_3.getWorkflowDefinitionName());
		assertEquals(
			_workflowDefinition3.getVersion(),
			childWorkflowInstance3_3.getWorkflowDefinitionVersion());

		Map<String, Serializable> workflowContext3 =
			workflowInstance3.getWorkflowContext();

		assertNotNull(workflowContext3);
		assertEquals(1, workflowContext3.size());
		assertEquals("success", workflowContext3.get("javaNode31"));

		assertEquals("forkNode", workflowInstance3.getState());
		assertNull(workflowInstance3.getEndDate());
		assertNull(workflowInstance3.getParentWorkflowInstance());
		assertNotNull(workflowInstance3.getStartDate());
		assertEquals(
			DEFINITION_NAME_3, workflowInstance3.getWorkflowDefinitionName());
		assertEquals(
			_workflowDefinition3.getVersion(),
			workflowInstance3.getWorkflowDefinitionVersion());
		assertEquals(
			workflowInstanceId3, workflowInstance3.getWorkflowInstanceId());

		// Clean up

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			companyId, workflowInstance1.getWorkflowInstanceId());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			companyId, workflowInstance2.getWorkflowInstanceId());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			companyId, workflowInstance3.getWorkflowInstanceId());
	}

	public void testGetWorkflowInstanceCount() throws Exception {

		// Prepare

		WorkflowInstance workflowInstance1_1 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				companyId, defaultUserId, _workflowDefinition1.getName(),
				_workflowDefinition1.getVersion(), null, null);

		workflowInstance1_1 =
			WorkflowInstanceManagerUtil.signalWorkflowInstance(
				companyId, defaultUserId,
				workflowInstance1_1.getWorkflowInstanceId(), "toTaskNode",
				null);

		workflowInstance1_1 =
			WorkflowInstanceManagerUtil.signalWorkflowInstance(
				companyId, defaultUserId,
				workflowInstance1_1.getWorkflowInstanceId(), "toEnd", null);

		WorkflowInstance workflowInstance1_2 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				companyId, defaultUserId, _workflowDefinition1.getName(),
				_workflowDefinition1.getVersion(), null, null);

		workflowInstance1_2 =
			WorkflowInstanceManagerUtil.signalWorkflowInstance(
				companyId, defaultUserId,
				workflowInstance1_2.getWorkflowInstanceId(), "toTaskNode",
				null);

		workflowInstance1_2 =
			WorkflowInstanceManagerUtil.signalWorkflowInstance(
				companyId, defaultUserId,
				workflowInstance1_2.getWorkflowInstanceId(), "toEnd", null);

		WorkflowInstance workflowInstance1_3 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				companyId, defaultUserId, _workflowDefinition1.getName(),
				_workflowDefinition1.getVersion(), null, null);

		WorkflowInstance workflowInstance2_1 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				companyId, defaultUserId, _workflowDefinition2.getName(),
				_workflowDefinition2.getVersion(), null, null);

		workflowInstance2_1 =
			WorkflowInstanceManagerUtil.signalWorkflowInstance(
				companyId, defaultUserId,
				workflowInstance2_1.getWorkflowInstanceId(), "toEnd", null);

		WorkflowInstance workflowInstance2_2 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				companyId, defaultUserId, _workflowDefinition2.getName(),
				_workflowDefinition2.getVersion(), null, null);

		// Workflow definition 1

		int count = WorkflowInstanceManagerUtil.getWorkflowInstanceCount(
			companyId, DEFINITION_NAME_1, _workflowDefinition1.getVersion(),
			Boolean.TRUE);

		assertEquals(2, count);

		count = WorkflowInstanceManagerUtil.getWorkflowInstanceCount(
			companyId, DEFINITION_NAME_1, _workflowDefinition1.getVersion(),
			Boolean.FALSE);

		assertEquals(1, count);

		// Workflow definition 2

		count = WorkflowInstanceManagerUtil.getWorkflowInstanceCount(
			companyId, DEFINITION_NAME_2, _workflowDefinition2.getVersion(),
			Boolean.TRUE);

		assertEquals(1, count);

		count = WorkflowInstanceManagerUtil.getWorkflowInstanceCount(
			companyId, DEFINITION_NAME_2, _workflowDefinition2.getVersion(),
			Boolean.FALSE);

		assertEquals(1, count);

		// Clean up

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			companyId, workflowInstance1_1.getWorkflowInstanceId());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			companyId, workflowInstance1_2.getWorkflowInstanceId());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			companyId, workflowInstance1_3.getWorkflowInstanceId());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			companyId, workflowInstance2_1.getWorkflowInstanceId());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			companyId, workflowInstance2_2.getWorkflowInstanceId());
	}

	public void testGetWorkflowInstances() throws Exception {

		// Prepare

		WorkflowInstance workflowInstance1_1 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				companyId, defaultUserId, _workflowDefinition1.getName(),
				_workflowDefinition1.getVersion(), null, null);

		workflowInstance1_1 =
			WorkflowInstanceManagerUtil.signalWorkflowInstance(
				companyId, defaultUserId,
				workflowInstance1_1.getWorkflowInstanceId(), "toTaskNode",
				null);

		workflowInstance1_1 =
			WorkflowInstanceManagerUtil.signalWorkflowInstance(
				companyId, defaultUserId,
				workflowInstance1_1.getWorkflowInstanceId(), "toEnd", null);

		WorkflowInstance workflowInstance1_2 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				companyId, defaultUserId, _workflowDefinition1.getName(),
				_workflowDefinition1.getVersion(), null, null);

		workflowInstance1_2 =
			WorkflowInstanceManagerUtil.signalWorkflowInstance(
				companyId, defaultUserId,
				workflowInstance1_2.getWorkflowInstanceId(), "toTaskNode",
				null);

		workflowInstance1_2 =
			WorkflowInstanceManagerUtil.signalWorkflowInstance(
				companyId, defaultUserId,
				workflowInstance1_2.getWorkflowInstanceId(), "toEnd", null);

		WorkflowInstance workflowInstance1_3 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				companyId, defaultUserId, _workflowDefinition1.getName(),
				_workflowDefinition1.getVersion(), null, null);

		WorkflowInstance workflowInstance2_1 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				companyId, defaultUserId, _workflowDefinition2.getName(),
				_workflowDefinition2.getVersion(), null, null);

		workflowInstance2_1 =
			WorkflowInstanceManagerUtil.signalWorkflowInstance(
				companyId, defaultUserId,
				workflowInstance2_1.getWorkflowInstanceId(), "toEnd", null);

		WorkflowInstance workflowInstance2_2 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				companyId, defaultUserId, _workflowDefinition2.getName(),
				_workflowDefinition2.getVersion(), null, null);

		// Workflow definition 1, completed, all

		List<WorkflowInstance> workflowInstances =
			WorkflowInstanceManagerUtil.getWorkflowInstances(
				companyId, DEFINITION_NAME_1, _workflowDefinition1.getVersion(),
				Boolean.TRUE, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				WorkflowComparatorFactoryUtil.getInstanceStartDateComparator(
					true));

		assertEquals(2, workflowInstances.size());

		WorkflowInstance workflowInstance1 = workflowInstances.get(0);

		assertEquals(0, workflowInstance1.getChildrenWorkflowInstanceCount());

		Map<String, Serializable> workflowContext1 =
			workflowInstance1.getWorkflowContext();

		assertEquals(1, workflowContext1.size());
		assertEquals("success", workflowContext1.get("javaNode11"));

		assertEquals("end", workflowInstance1.getState());
		assertNotNull(workflowInstance1.getEndDate());
		assertNull(workflowInstance1.getParentWorkflowInstance());
		assertNotNull(workflowInstance1.getStartDate());
		assertEquals(
			DEFINITION_NAME_1, workflowInstance1.getWorkflowDefinitionName());
		assertEquals(
			_workflowDefinition1.getVersion(),
			workflowInstance1.getWorkflowDefinitionVersion());
		assertEquals(
			workflowInstance1_1.getWorkflowInstanceId(),
			workflowInstance1.getWorkflowInstanceId());

		WorkflowInstance workflowInstance2 = workflowInstances.get(1);

		assertEquals(0, workflowInstance2.getChildrenWorkflowInstanceCount());

		Map<String, Serializable> workflowContext2 =
			workflowInstance2.getWorkflowContext();

		assertEquals(1, workflowContext2.size());
		assertEquals("success", workflowContext2.get("javaNode11"));

		assertEquals("end", workflowInstance2.getState());
		assertNotNull(workflowInstance2.getEndDate());
		assertNull(workflowInstance2.getParentWorkflowInstance());
		assertNotNull(workflowInstance2.getStartDate());
		assertEquals(
			DEFINITION_NAME_1, workflowInstance2.getWorkflowDefinitionName());
		assertEquals(
			_workflowDefinition1.getVersion(),
			workflowInstance2.getWorkflowDefinitionVersion());
		assertEquals(
			workflowInstance1_2.getWorkflowInstanceId(),
			workflowInstance2.getWorkflowInstanceId());

		// Workflow definition 1, completed, by range

		workflowInstances = WorkflowInstanceManagerUtil.getWorkflowInstances(
			companyId, DEFINITION_NAME_1, _workflowDefinition1.getVersion(),
			Boolean.TRUE, 1, 2,
			WorkflowComparatorFactoryUtil.getInstanceStartDateComparator(true));

		assertEquals(1, workflowInstances.size());

		WorkflowInstance workflowInstance3 = workflowInstances.get(0);

		assertEquals(0, workflowInstance3.getChildrenWorkflowInstanceCount());

		Map<String, Serializable> workflowContext3 =
			workflowInstance3.getWorkflowContext();

		assertEquals(1, workflowContext3.size());
		assertEquals("success", workflowContext3.get("javaNode11"));

		assertEquals("end", workflowInstance3.getState());
		assertNotNull(workflowInstance3.getEndDate());
		assertNull(workflowInstance3.getParentWorkflowInstance());
		assertNotNull(workflowInstance3.getStartDate());
		assertEquals(
			DEFINITION_NAME_1, workflowInstance3.getWorkflowDefinitionName());
		assertEquals(
			_workflowDefinition1.getVersion(),
			workflowInstance3.getWorkflowDefinitionVersion());
		assertEquals(
			workflowInstance1_2.getWorkflowInstanceId(),
			workflowInstance3.getWorkflowInstanceId());

		// Workflow definition 1, uncompleted, all

		workflowInstances = WorkflowInstanceManagerUtil.getWorkflowInstances(
			companyId, DEFINITION_NAME_1, _workflowDefinition1.getVersion(),
			Boolean.FALSE, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			WorkflowComparatorFactoryUtil.getInstanceStartDateComparator(true));

		assertEquals(1, workflowInstances.size());

		WorkflowInstance workflowInstance4 = workflowInstances.get(0);

		assertEquals(0, workflowInstance4.getChildrenWorkflowInstanceCount());

		Map<String, Serializable> workflowContext4 =
			workflowInstance4.getWorkflowContext();

		assertEquals(1, workflowContext4.size());
		assertEquals("success", workflowContext4.get("javaNode11"));

		assertEquals("State1-1", workflowInstance4.getState());
		assertNull(workflowInstance4.getEndDate());
		assertNull(workflowInstance4.getParentWorkflowInstance());
		assertNotNull(workflowInstance4.getStartDate());
		assertEquals(
			DEFINITION_NAME_1, workflowInstance4.getWorkflowDefinitionName());
		assertEquals(
			_workflowDefinition1.getVersion(),
			workflowInstance4.getWorkflowDefinitionVersion());
		assertEquals(
			workflowInstance1_3.getWorkflowInstanceId(),
			workflowInstance4.getWorkflowInstanceId());

		// Workflow definition 2, completed, all

		workflowInstances = WorkflowInstanceManagerUtil.getWorkflowInstances(
			companyId, DEFINITION_NAME_2, _workflowDefinition2.getVersion(),
			Boolean.TRUE, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			WorkflowComparatorFactoryUtil.getInstanceStartDateComparator(true));

		assertEquals(1, workflowInstances.size());

		WorkflowInstance workflowInstance5 = workflowInstances.get(0);

		assertEquals(0, workflowInstance5.getChildrenWorkflowInstanceCount());

		Map<String, Serializable> workflowContext5 =
			workflowInstance5.getWorkflowContext();

		assertEquals(1, workflowContext5.size());
		assertEquals("success", workflowContext5.get("javaNode21"));

		assertEquals("end", workflowInstance5.getState());
		assertNotNull(workflowInstance5.getEndDate());
		assertNull(workflowInstance5.getParentWorkflowInstance());
		assertNotNull(workflowInstance5.getStartDate());
		assertEquals(
			DEFINITION_NAME_2, workflowInstance5.getWorkflowDefinitionName());
		assertEquals(
			_workflowDefinition2.getVersion(),
			workflowInstance5.getWorkflowDefinitionVersion());
		assertEquals(
			workflowInstance2_1.getWorkflowInstanceId(),
			workflowInstance5.getWorkflowInstanceId());

		// Workflow definition 2, uncompleted, all

		workflowInstances = WorkflowInstanceManagerUtil.getWorkflowInstances(
			companyId, DEFINITION_NAME_2, _workflowDefinition2.getVersion(),
			Boolean.FALSE, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			WorkflowComparatorFactoryUtil.getInstanceStartDateComparator(true));

		assertEquals(1, workflowInstances.size());

		WorkflowInstance workflowInstance6 = workflowInstances.get(0);

		assertEquals(0, workflowInstance6.getChildrenWorkflowInstanceCount());

		Map<String, Serializable> workflowContext6 =
			workflowInstance6.getWorkflowContext();

		assertEquals(1, workflowContext6.size());
		assertEquals("success", workflowContext6.get("javaNode21"));

		assertEquals("taskNode3", workflowInstance6.getState());
		assertNull(workflowInstance6.getEndDate());
		assertNull(workflowInstance6.getParentWorkflowInstance());
		assertNotNull(workflowInstance6.getStartDate());
		assertEquals(
			DEFINITION_NAME_2, workflowInstance6.getWorkflowDefinitionName());
		assertEquals(
			_workflowDefinition2.getVersion(),
			workflowInstance6.getWorkflowDefinitionVersion());
		assertEquals(
			workflowInstance2_2.getWorkflowInstanceId(),
			workflowInstance6.getWorkflowInstanceId());

		// Clean up

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			companyId, workflowInstance1_1.getWorkflowInstanceId());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			companyId, workflowInstance1_2.getWorkflowInstanceId());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			companyId, workflowInstance1_3.getWorkflowInstanceId());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			companyId, workflowInstance2_1.getWorkflowInstanceId());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			companyId, workflowInstance2_2.getWorkflowInstanceId());
	}

	public void testSignalWorkflowInstance() throws Exception {

		// Path 1

		WorkflowInstance workflowInstance =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				companyId, defaultUserId, _workflowDefinition4.getName(),
				_workflowDefinition4.getVersion(), null, null);

		assertEquals("Switch", workflowInstance.getState());

		workflowInstance = WorkflowInstanceManagerUtil.signalWorkflowInstance(
			companyId, defaultUserId, workflowInstance.getWorkflowInstanceId(),
			"toState1", null);

		assertEquals("State1", workflowInstance.getState());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			companyId, workflowInstance.getWorkflowInstanceId());

		// Path 2

		workflowInstance = WorkflowInstanceManagerUtil.startWorkflowInstance(
			companyId, defaultUserId, _workflowDefinition4.getName(),
			_workflowDefinition4.getVersion(), null, null);

		assertEquals("Switch", workflowInstance.getState());

		workflowInstance = WorkflowInstanceManagerUtil.signalWorkflowInstance(
			companyId, defaultUserId, workflowInstance.getWorkflowInstanceId(),
			"toState2", null);

		assertEquals("State2", workflowInstance.getState());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			companyId, workflowInstance.getWorkflowInstanceId());

		// Path 3

		workflowInstance = WorkflowInstanceManagerUtil.startWorkflowInstance(
			companyId, defaultUserId, _workflowDefinition4.getName(),
			_workflowDefinition4.getVersion(), null, null);

		assertEquals("Switch", workflowInstance.getState());

		workflowInstance = WorkflowInstanceManagerUtil.signalWorkflowInstance(
			companyId, defaultUserId, workflowInstance.getWorkflowInstanceId(),
			"toState3", null);

		assertEquals("State3", workflowInstance.getState());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			companyId, workflowInstance.getWorkflowInstanceId());
	}

	public void testStartWorkflowInstance() throws Exception {

		// Workflow instance 1

		WorkflowInstance workflowInstance1 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				companyId, defaultUserId, _workflowDefinition1.getName(),
				_workflowDefinition1.getVersion(), null, null);

		assertNotNull(workflowInstance1);
		assertEquals(0, workflowInstance1.getChildrenWorkflowInstanceCount());

		Map<String, Serializable> workflowContext1 =
			workflowInstance1.getWorkflowContext();

		assertNotNull(workflowContext1);
		assertEquals(1, workflowContext1.size());
		assertEquals("success", workflowContext1.get("javaNode11"));

		assertEquals("State1-1", workflowInstance1.getState());
		assertNull(workflowInstance1.getEndDate());
		assertNull(workflowInstance1.getParentWorkflowInstance());
		assertNotNull(workflowInstance1.getStartDate());
		assertEquals(
			DEFINITION_NAME_1, workflowInstance1.getWorkflowDefinitionName());
		assertEquals(
			_workflowDefinition1.getVersion(),
			workflowInstance1.getWorkflowDefinitionVersion());

		// Workflow instance 2

		WorkflowInstance workflowInstance2 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				companyId, defaultUserId, _workflowDefinition2.getName(),
				_workflowDefinition2.getVersion(), null, null);

		assertNotNull(workflowInstance2);
		assertEquals(0, workflowInstance2.getChildrenWorkflowInstanceCount());

		Map<String, Serializable> workflowContext2 =
			workflowInstance2.getWorkflowContext();

		assertNotNull(workflowContext2);
		assertEquals(1, workflowContext2.size());
		assertEquals("success", workflowContext2.get("javaNode21"));

		assertEquals("taskNode3", workflowInstance2.getState());
		assertNull(workflowInstance2.getEndDate());
		assertNull(workflowInstance2.getParentWorkflowInstance());
		assertNotNull(workflowInstance2.getStartDate());
		assertEquals(
			DEFINITION_NAME_2, workflowInstance2.getWorkflowDefinitionName());
		assertEquals(
			_workflowDefinition2.getVersion(),
			workflowInstance2.getWorkflowDefinitionVersion());

		// Workflow instance 3

		WorkflowInstance workflowInstance3 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				companyId, defaultUserId, _workflowDefinition3.getName(),
				_workflowDefinition3.getVersion(), null, null);

		assertNotNull(workflowInstance3);
		assertEquals(0, workflowInstance3.getChildrenWorkflowInstanceCount());

		Map<String, Serializable> workflowContext3 =
			workflowInstance3.getWorkflowContext();

		assertNotNull(workflowContext3);
		assertEquals(1, workflowContext3.size());
		assertEquals("success", workflowContext3.get("javaNode31"));

		assertEquals("forkNode", workflowInstance3.getState());
		assertNull(workflowInstance3.getEndDate());
		assertNull(workflowInstance3.getParentWorkflowInstance());
		assertNotNull(workflowInstance3.getStartDate());
		assertEquals(
			DEFINITION_NAME_3, workflowInstance3.getWorkflowDefinitionName());
		assertEquals(
			_workflowDefinition3.getVersion(),
			workflowInstance3.getWorkflowDefinitionVersion());

		// Clean up

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			companyId, workflowInstance1.getWorkflowInstanceId());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			companyId, workflowInstance2.getWorkflowInstanceId());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			companyId, workflowInstance3.getWorkflowInstanceId());
	}

	public void testUpdateWorkflowContext() throws Exception {

		// Prepare

		WorkflowInstance workflowInstance =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				companyId, defaultUserId, _workflowDefinition1.getName(),
				_workflowDefinition1.getVersion(), null, null);

		Map<String, Serializable> workflowContext =
			workflowInstance.getWorkflowContext();

		assertNotNull(workflowContext);
		assertEquals(1, workflowContext.size());
		assertEquals("success", workflowContext.get("javaNode11"));

		String testKey = "testKey";
		String testValue1 = "testValue1";
		String testValue2 = "testValue2";

		Map<String, Serializable> updateContext =
			new HashMap<String, Serializable>();

		updateContext.put(testKey, testValue1);

		// Add new variable

		workflowInstance = WorkflowInstanceManagerUtil.updateWorkflowContext(
			companyId, workflowInstance.getWorkflowInstanceId(), updateContext);

		workflowContext = workflowInstance.getWorkflowContext();

		assertNotNull(workflowContext);
		assertEquals(2, workflowContext.size());
		assertEquals("success", workflowContext.get("javaNode11"));
		assertEquals(testValue1, workflowContext.get(testKey));

		updateContext = new HashMap<String, Serializable>();

		updateContext.put(testKey, testValue2);

		// Update variable

		workflowInstance = WorkflowInstanceManagerUtil.updateWorkflowContext(
			companyId, workflowInstance.getWorkflowInstanceId(), updateContext);

		workflowContext = workflowInstance.getWorkflowContext();

		assertNotNull(workflowContext);
		assertEquals(2, workflowContext.size());
		assertEquals("success", workflowContext.get("javaNode11"));
		assertEquals(testValue2, workflowContext.get(testKey));

		// Clean up

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			companyId, workflowInstance.getWorkflowInstanceId());
	}

	private WorkflowDefinition _workflowDefinition1;
	private WorkflowDefinition _workflowDefinition2;
	private WorkflowDefinition _workflowDefinition3;
	private WorkflowDefinition _workflowDefinition4;

}