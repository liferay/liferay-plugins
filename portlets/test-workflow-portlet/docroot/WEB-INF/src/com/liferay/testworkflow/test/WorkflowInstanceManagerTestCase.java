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
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.kernel.workflow.WorkflowInstance;
import com.liferay.portal.kernel.workflow.WorkflowInstanceManagerUtil;
import com.liferay.portal.kernel.workflow.comparator.WorkflowInstanceStartDateComparator;

import java.io.ByteArrayInputStream;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <a href="WorkflowInstanceManagerTestCase.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Shuyang Zhou
 */
public class WorkflowInstanceManagerTestCase extends WorkflowTestCase {

	public void setUp() throws Exception {
		super.setUp();

		_workflowDefinition1 =
			WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
				defaultUserId, DEFINITION_NAME_1,
				new ByteArrayInputStream(definitionBytes1));

		_workflowDefinition2 =
			WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
				defaultUserId, DEFINITION_NAME_2,
				new ByteArrayInputStream(definitionBytes2));

		_workflowDefinition3 =
			WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
				defaultUserId, DEFINITION_NAME_3,
				new ByteArrayInputStream(definitionBytes3));

		_workflowDefinition4 =
			WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
				defaultUserId, DEFINITION_NAME_4,
				new ByteArrayInputStream(definitionBytes4));
	}

	public void tearDown() throws Exception {
		super.tearDown();

		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			defaultUserId, DEFINITION_NAME_1,
			_workflowDefinition1.getVersion());

		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			defaultUserId, DEFINITION_NAME_2,
			_workflowDefinition2.getVersion());

		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			defaultUserId, DEFINITION_NAME_3,
			_workflowDefinition3.getVersion());

		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			defaultUserId, DEFINITION_NAME_4,
			_workflowDefinition4.getVersion());
	}

	public void testDeleteWorkflowInstance() throws Exception {
		WorkflowInstance workflowInstance =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				defaultUserId, _workflowDefinition1.getName(),
				_workflowDefinition1.getVersion(), null, null);

		assertNotNull(workflowInstance);

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance.getWorkflowInstanceId());

		try{
			WorkflowInstanceManagerUtil.getWorkflowInstance(
				workflowInstance.getWorkflowInstanceId());

			fail();
		}
		catch(WorkflowException we) {
		}
	}

	public void testGetNextTransitionNames() throws Exception {

		// Prepare

		WorkflowInstance workflowInstance1 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				defaultUserId, _workflowDefinition1.getName(),
				_workflowDefinition1.getVersion(), null, null);

		WorkflowInstance workflowInstance2 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				defaultUserId, _workflowDefinition2.getName(),
				_workflowDefinition2.getVersion(), null, null);

		WorkflowInstance workflowInstance3 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				defaultUserId, _workflowDefinition3.getName(),
				_workflowDefinition3.getVersion(), null, null);

		// Workflow instance 1

		List<String> nextTransitionNames1 =
			WorkflowInstanceManagerUtil.getNextTransitionNames(
				defaultUserId, workflowInstance1.getWorkflowInstanceId());

		assertEquals(1, nextTransitionNames1.size());
		assertEquals("toTaskNode", nextTransitionNames1.get(0));

		// Workflow instance 2

		List<String> nextTransitionNames2 =
			WorkflowInstanceManagerUtil.getNextTransitionNames(
				defaultUserId, workflowInstance2.getWorkflowInstanceId());

		assertEquals(1, nextTransitionNames2.size());
		assertEquals("toEnd", nextTransitionNames2.get(0));

		// Workflow instance 3

		List<String> nextTransitionNames3 =
			WorkflowInstanceManagerUtil.getNextTransitionNames(
				defaultUserId, workflowInstance3.getWorkflowInstanceId());

		assertEquals(3, nextTransitionNames3.size());
		assertTrue(nextTransitionNames3.contains("branch1"));
		assertTrue(nextTransitionNames3.contains("branch2"));
		assertTrue(nextTransitionNames3.contains("branch3"));

		// Clean up

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance1.getWorkflowInstanceId());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance2.getWorkflowInstanceId());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance3.getWorkflowInstanceId());
	}

	public void testGetWorkflowInstance() throws Exception {

		// Prepare

		WorkflowInstance workflowInstance1 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				defaultUserId, _workflowDefinition1.getName(),
				_workflowDefinition1.getVersion(), null, null);

		WorkflowInstance workflowInstance2 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				defaultUserId, _workflowDefinition2.getName(),
				_workflowDefinition2.getVersion(), null, null);

		WorkflowInstance workflowInstance3 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				defaultUserId, _workflowDefinition3.getName(),
				_workflowDefinition3.getVersion(), null, null);

		// Workflow instance 1

		long workflowInstanceId1 = workflowInstance1.getWorkflowInstanceId();

		workflowInstance1 = WorkflowInstanceManagerUtil.getWorkflowInstance(
			workflowInstance1.getWorkflowInstanceId());

		assertEquals(0, workflowInstance1.getChildrenWorkflowInstanceCount());

		Map<String, Object> context1 = workflowInstance1.getContext();

		assertNotNull(context1);
		assertEquals(1, context1.size());
		assertEquals("success", context1.get("javaNode11"));

		assertEquals("State1-1", workflowInstance1.getCurrentNodeName());
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
			workflowInstance2.getWorkflowInstanceId());

		assertEquals(0, workflowInstance2.getChildrenWorkflowInstanceCount());

		Map<String, Object> context2 = workflowInstance2.getContext();

		assertNotNull(context2);
		assertEquals(1, context2.size());
		assertEquals("success", context2.get("javaNode21"));

		assertEquals("taskNode3", workflowInstance2.getCurrentNodeName());
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
			workflowInstance3.getWorkflowInstanceId());

		List<WorkflowInstance> childrenInstances =
			workflowInstance3.getChildrenWorkflowInstances();

		assertEquals(3, childrenInstances.size());

		// Child workflow instance 1

		WorkflowInstance childWorkflowInstance3_1 = childrenInstances.get(0);

		assertEquals(
			0, childWorkflowInstance3_1.getChildrenWorkflowInstanceCount());

		Map<String, Object> context3_1 = childWorkflowInstance3_1.getContext();

		assertNotNull(context3_1);
		assertEquals(1, context3_1.size());
		assertEquals("success", context3_1.get("javaNode31"));

		assertEquals(
			"taskNode1", childWorkflowInstance3_1.getCurrentNodeName());
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

		Map<String, Object> context3_2 = childWorkflowInstance3_2.getContext();

		assertNotNull(context3_2);
		assertEquals(1, context3_2.size());
		assertEquals("success", context3_2.get("javaNode31"));

		assertEquals(
			"taskNode2", childWorkflowInstance3_2.getCurrentNodeName());
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

		Map<String, Object> context3_3 = childWorkflowInstance3_3.getContext();

		assertNotNull(context3_3);
		assertEquals(1, context3_3.size());
		assertEquals("success", context3_3.get("javaNode31"));

		assertEquals(
			"taskNode3", childWorkflowInstance3_3.getCurrentNodeName());
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

		Map<String, Object> context3 = workflowInstance3.getContext();

		assertNotNull(context3);
		assertEquals(1, context3.size());
		assertEquals("success", context3.get("javaNode31"));

		assertEquals("forkNode", workflowInstance3.getCurrentNodeName());
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
			workflowInstance1.getWorkflowInstanceId());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance2.getWorkflowInstanceId());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance3.getWorkflowInstanceId());
	}

	public void testGetWorkflowInstanceCount() throws Exception {

		// Prepare

		WorkflowInstance workflowInstance1_1 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				defaultUserId, _workflowDefinition1.getName(),
				_workflowDefinition1.getVersion(), null, null);

		workflowInstance1_1 =
			WorkflowInstanceManagerUtil.signalWorkflowInstance(
				defaultUserId, workflowInstance1_1.getWorkflowInstanceId(),
				"toTaskNode", null);

		workflowInstance1_1 =
			WorkflowInstanceManagerUtil.signalWorkflowInstance(
				defaultUserId, workflowInstance1_1.getWorkflowInstanceId(),
				"toEnd", null);

		WorkflowInstance workflowInstance1_2 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				defaultUserId, _workflowDefinition1.getName(),
				_workflowDefinition1.getVersion(), null, null);

		workflowInstance1_2 =
			WorkflowInstanceManagerUtil.signalWorkflowInstance(
				defaultUserId, workflowInstance1_2.getWorkflowInstanceId(),
				"toTaskNode", null);

		workflowInstance1_2 =
			WorkflowInstanceManagerUtil.signalWorkflowInstance(
				defaultUserId, workflowInstance1_2.getWorkflowInstanceId(),
				"toEnd", null);

		WorkflowInstance workflowInstance1_3 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				defaultUserId, _workflowDefinition1.getName(),
				_workflowDefinition1.getVersion(), null, null);

		WorkflowInstance workflowInstance2_1 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				defaultUserId, _workflowDefinition2.getName(),
				_workflowDefinition2.getVersion(), null, null);

		workflowInstance2_1 =
			WorkflowInstanceManagerUtil.signalWorkflowInstance(
				defaultUserId, workflowInstance2_1.getWorkflowInstanceId(),
				"toEnd", null);

		WorkflowInstance workflowInstance2_2 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				defaultUserId, _workflowDefinition2.getName(),
				_workflowDefinition2.getVersion(), null, null);

		// Workflow definition 1

		int count = WorkflowInstanceManagerUtil.getWorkflowInstanceCount(
			DEFINITION_NAME_1, _workflowDefinition1.getVersion(), Boolean.TRUE);

		assertEquals(2, count);

		count = WorkflowInstanceManagerUtil.getWorkflowInstanceCount(
			DEFINITION_NAME_1, _workflowDefinition1.getVersion(),
			Boolean.FALSE);

		assertEquals(1, count);

		// Workflow definition 2

		count = WorkflowInstanceManagerUtil.getWorkflowInstanceCount(
			DEFINITION_NAME_2, _workflowDefinition2.getVersion(), Boolean.TRUE);

		assertEquals(1, count);

		count = WorkflowInstanceManagerUtil.getWorkflowInstanceCount(
			DEFINITION_NAME_2, _workflowDefinition2.getVersion(),
			Boolean.FALSE);

		assertEquals(1, count);

		// Clean up

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance1_1.getWorkflowInstanceId());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance1_2.getWorkflowInstanceId());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance1_3.getWorkflowInstanceId());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance2_1.getWorkflowInstanceId());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance2_2.getWorkflowInstanceId());
	}

	public void testGetWorkflowInstances() throws Exception {

		// Prepare

		WorkflowInstance workflowInstance1_1 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				defaultUserId, _workflowDefinition1.getName(),
				_workflowDefinition1.getVersion(), null, null);

		workflowInstance1_1 =
			WorkflowInstanceManagerUtil.signalWorkflowInstance(
				defaultUserId, workflowInstance1_1.getWorkflowInstanceId(),
				"toTaskNode", null);

		workflowInstance1_1 =
			WorkflowInstanceManagerUtil.signalWorkflowInstance(
				defaultUserId, workflowInstance1_1.getWorkflowInstanceId(),
				"toEnd", null);

		WorkflowInstance workflowInstance1_2 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				defaultUserId, _workflowDefinition1.getName(),
				_workflowDefinition1.getVersion(), null, null);

		workflowInstance1_2 =
			WorkflowInstanceManagerUtil.signalWorkflowInstance(
				defaultUserId, workflowInstance1_2.getWorkflowInstanceId(),
				"toTaskNode", null);

		workflowInstance1_2 =
			WorkflowInstanceManagerUtil.signalWorkflowInstance(
				defaultUserId, workflowInstance1_2.getWorkflowInstanceId(),
				"toEnd", null);

		WorkflowInstance workflowInstance1_3 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				defaultUserId, _workflowDefinition1.getName(),
				_workflowDefinition1.getVersion(), null, null);

		WorkflowInstance workflowInstance2_1 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				defaultUserId, _workflowDefinition2.getName(),
				_workflowDefinition2.getVersion(), null, null);

		workflowInstance2_1 =
			WorkflowInstanceManagerUtil.signalWorkflowInstance(
				defaultUserId, workflowInstance2_1.getWorkflowInstanceId(),
				"toEnd", null);

		WorkflowInstance workflowInstance2_2 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				defaultUserId, _workflowDefinition2.getName(),
				_workflowDefinition2.getVersion(), null, null);

		// Workflow definition 1, completed, all

		List<WorkflowInstance> workflowInstances =
			WorkflowInstanceManagerUtil.getWorkflowInstances(
				DEFINITION_NAME_1, _workflowDefinition1.getVersion(),
				Boolean.TRUE, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new WorkflowInstanceStartDateComparator(true));

		assertEquals(2, workflowInstances.size());

		WorkflowInstance workflowInstance1 = workflowInstances.get(0);

		assertEquals(0, workflowInstance1.getChildrenWorkflowInstanceCount());

		Map<String, Object> context1 = workflowInstance1.getContext();

		assertEquals(1, context1.size());
		assertEquals("success", context1.get("javaNode11"));

		assertEquals("end", workflowInstance1.getCurrentNodeName());
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

		Map<String, Object> context2 = workflowInstance2.getContext();

		assertEquals(1, context2.size());
		assertEquals("success", context2.get("javaNode11"));

		assertEquals("end", workflowInstance2.getCurrentNodeName());
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
			DEFINITION_NAME_1, _workflowDefinition1.getVersion(), Boolean.TRUE,
			1, 2, new WorkflowInstanceStartDateComparator(true));

		assertEquals(1, workflowInstances.size());

		WorkflowInstance workflowInstance3 = workflowInstances.get(0);

		assertEquals(0, workflowInstance3.getChildrenWorkflowInstanceCount());

		Map<String, Object> context3 = workflowInstance3.getContext();

		assertEquals(1, context3.size());
		assertEquals("success", context3.get("javaNode11"));

		assertEquals("end", workflowInstance3.getCurrentNodeName());
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
			DEFINITION_NAME_1, _workflowDefinition1.getVersion(), Boolean.FALSE,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new WorkflowInstanceStartDateComparator(true));

		assertEquals(1, workflowInstances.size());

		WorkflowInstance workflowInstance4 = workflowInstances.get(0);

		assertEquals(0, workflowInstance4.getChildrenWorkflowInstanceCount());

		Map<String, Object> context4 = workflowInstance4.getContext();

		assertEquals(1, context4.size());
		assertEquals("success", context4.get("javaNode11"));

		assertEquals("State1-1", workflowInstance4.getCurrentNodeName());
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
			DEFINITION_NAME_2, _workflowDefinition2.getVersion(), Boolean.TRUE,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new WorkflowInstanceStartDateComparator(true));

		assertEquals(1, workflowInstances.size());

		WorkflowInstance workflowInstance5 = workflowInstances.get(0);

		assertEquals(0, workflowInstance5.getChildrenWorkflowInstanceCount());

		Map<String, Object> context5 = workflowInstance5.getContext();

		assertEquals(1, context5.size());
		assertEquals("success", context5.get("javaNode21"));

		assertEquals("end", workflowInstance5.getCurrentNodeName());
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
			DEFINITION_NAME_2, _workflowDefinition2.getVersion(), Boolean.FALSE,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new WorkflowInstanceStartDateComparator(true));

		assertEquals(1, workflowInstances.size());

		WorkflowInstance workflowInstance6 = workflowInstances.get(0);

		assertEquals(0, workflowInstance6.getChildrenWorkflowInstanceCount());

		Map<String, Object> context6 = workflowInstance6.getContext();

		assertEquals(1, context6.size());
		assertEquals("success", context6.get("javaNode21"));

		assertEquals("taskNode3", workflowInstance6.getCurrentNodeName());
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
			workflowInstance1_1.getWorkflowInstanceId());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance1_2.getWorkflowInstanceId());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance1_3.getWorkflowInstanceId());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance2_1.getWorkflowInstanceId());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance2_2.getWorkflowInstanceId());
	}

	public void testSignalWorkflowInstance() throws Exception {

		// Path 1

		WorkflowInstance workflowInstance =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				defaultUserId, _workflowDefinition4.getName(),
				_workflowDefinition4.getVersion(), null, null);

		assertEquals("Switch", workflowInstance.getCurrentNodeName());

		workflowInstance = WorkflowInstanceManagerUtil.signalWorkflowInstance(
			defaultUserId, workflowInstance.getWorkflowInstanceId(), "toState1",
			null);

		assertEquals("State1", workflowInstance.getCurrentNodeName());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance.getWorkflowInstanceId());

		// Path 2

		workflowInstance = WorkflowInstanceManagerUtil.startWorkflowInstance(
			defaultUserId, _workflowDefinition4.getName(),
			_workflowDefinition4.getVersion(), null, null);

		assertEquals("Switch", workflowInstance.getCurrentNodeName());

		workflowInstance = WorkflowInstanceManagerUtil.signalWorkflowInstance(
			defaultUserId, workflowInstance.getWorkflowInstanceId(), "toState2",
			null);

		assertEquals("State2", workflowInstance.getCurrentNodeName());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance.getWorkflowInstanceId());

		// Path 3

		workflowInstance = WorkflowInstanceManagerUtil.startWorkflowInstance(
			defaultUserId, _workflowDefinition4.getName(),
			_workflowDefinition4.getVersion(), null, null);

		assertEquals("Switch", workflowInstance.getCurrentNodeName());

		workflowInstance = WorkflowInstanceManagerUtil.signalWorkflowInstance(
			defaultUserId, workflowInstance.getWorkflowInstanceId(), "toState3",
			null);

		assertEquals("State3", workflowInstance.getCurrentNodeName());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance.getWorkflowInstanceId());
	}

	public void testStartWorkflowInstance() throws Exception {

		// Workflow instance 1

		WorkflowInstance workflowInstance1 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				defaultUserId, _workflowDefinition1.getName(),
				_workflowDefinition1.getVersion(), null, null);

		assertNotNull(workflowInstance1);
		assertEquals(0, workflowInstance1.getChildrenWorkflowInstanceCount());

		Map<String, Object> context1 = workflowInstance1.getContext();

		assertNotNull(context1);
		assertEquals(1, context1.size());
		assertEquals("success", context1.get("javaNode11"));

		assertEquals("State1-1", workflowInstance1.getCurrentNodeName());
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
				defaultUserId, _workflowDefinition2.getName(),
				_workflowDefinition2.getVersion(), null, null);

		assertNotNull(workflowInstance2);
		assertEquals(0, workflowInstance2.getChildrenWorkflowInstanceCount());

		Map<String, Object> context2 = workflowInstance2.getContext();

		assertNotNull(context2);
		assertEquals(1, context2.size());
		assertEquals("success", context2.get("javaNode21"));

		assertEquals("taskNode3", workflowInstance2.getCurrentNodeName());
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
				defaultUserId, _workflowDefinition3.getName(),
				_workflowDefinition3.getVersion(), null, null);

		assertNotNull(workflowInstance3);
		assertEquals(0, workflowInstance3.getChildrenWorkflowInstanceCount());

		Map<String, Object> context3 = workflowInstance3.getContext();

		assertNotNull(context3);
		assertEquals(1, context3.size());
		assertEquals("success", context3.get("javaNode31"));

		assertEquals("forkNode", workflowInstance3.getCurrentNodeName());
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
			workflowInstance1.getWorkflowInstanceId());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance2.getWorkflowInstanceId());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance3.getWorkflowInstanceId());
	}

	public void testUpdateContext() throws Exception {

		// Prepare

		WorkflowInstance workflowInstance =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				defaultUserId, _workflowDefinition1.getName(),
				_workflowDefinition1.getVersion(), null, null);

		Map<String, Object> context = workflowInstance.getContext();

		assertNotNull(context);
		assertEquals(1, context.size());
		assertEquals("success", context.get("javaNode11"));

		String testKey = "testKey";
		String testValue1 = "testValue1";
		String testValue2 = "testValue2";

		Map<String, Object> updateContext = new HashMap<String, Object>();

		updateContext.put(testKey, testValue1);

		// Add new variable

		workflowInstance = WorkflowInstanceManagerUtil.updateContext(
			workflowInstance.getWorkflowInstanceId(), updateContext);

		context = workflowInstance.getContext();

		assertNotNull(context);
		assertEquals(2, context.size());
		assertEquals("success", context.get("javaNode11"));
		assertEquals(testValue1, context.get(testKey));

		updateContext = new HashMap<String, Object>();

		updateContext.put(testKey, testValue2);

		// Update variable

		workflowInstance = WorkflowInstanceManagerUtil.updateContext(
			workflowInstance.getWorkflowInstanceId(), updateContext);

		context = workflowInstance.getContext();

		assertNotNull(context);
		assertEquals(2, context.size());
		assertEquals("success", context.get("javaNode11"));
		assertEquals(testValue2, context.get(testKey));

		// Clean up

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance.getWorkflowInstanceId());
	}

	private WorkflowDefinition _workflowDefinition1;
	private WorkflowDefinition _workflowDefinition2;
	private WorkflowDefinition _workflowDefinition3;
	private WorkflowDefinition _workflowDefinition4;

}