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

import java.util.Collections;
import java.util.Comparator;
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
				user.getUserId(), DEFINITION_NAME_1,
				new ByteArrayInputStream(definitionBytes1));

		_workflowDefinition2 =
			WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
				user.getUserId(), DEFINITION_NAME_2,
				new ByteArrayInputStream(definitionBytes2));

		_workflowDefinition3 =
			WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
				user.getUserId(), DEFINITION_NAME_3,
				new ByteArrayInputStream(definitionBytes3));

		_workflowDefinition4 =
			WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
				user.getUserId(), DEFINITION_NAME_4,
				new ByteArrayInputStream(definitionBytes4));

	}

	public void tearDown() throws Exception {
		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			user.getUserId(), DEFINITION_NAME_1,
			_workflowDefinition1.getVersion());
		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			user.getUserId(), DEFINITION_NAME_2,
			_workflowDefinition2.getVersion());
		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			user.getUserId(), DEFINITION_NAME_3,
			_workflowDefinition3.getVersion());
		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			user.getUserId(), DEFINITION_NAME_4,
			_workflowDefinition4.getVersion());
	}

	public void testDeleteWorkflowInstance() throws Exception {

		WorkflowInstance workflowInstance =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				user.getUserId(), _workflowDefinition1.getName(),
				_workflowDefinition1.getVersion(), null, null);

		workflowInstance =
			WorkflowInstanceManagerUtil.getWorkflowInstance(
				workflowInstance.getWorkflowInstanceId());
		assertNotNull(workflowInstance);

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance.getWorkflowInstanceId());

		try{
			WorkflowInstanceManagerUtil.getWorkflowInstance(
				workflowInstance.getWorkflowInstanceId());
			fail();
		}catch(WorkflowException we) {
		}

	}

	public void testGetNextTransitionNames() throws Exception {

		// Prepare

		WorkflowInstance workflowInstance1 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				user.getUserId(), _workflowDefinition1.getName(),
				_workflowDefinition1.getVersion(), null, null);
		WorkflowInstance workflowInstance2 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				user.getUserId(), _workflowDefinition2.getName(),
				_workflowDefinition2.getVersion(), null, null);
		WorkflowInstance workflowInstance3 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				user.getUserId(), _workflowDefinition3.getName(),
				_workflowDefinition3.getVersion(), null, null);

		// Workflow Instance 1

		List<String> nextTransitionNames1 =
			WorkflowInstanceManagerUtil.getNextTransitionNames(
				user.getUserId(), workflowInstance1.getWorkflowInstanceId());
		assertEquals(1, nextTransitionNames1.size());
		assertEquals("toTaskNode", nextTransitionNames1.get(0));

		// Workflow Instance 2

		List<String> nextTransitionNames2 =
			WorkflowInstanceManagerUtil.getNextTransitionNames(
				user.getUserId(), workflowInstance2.getWorkflowInstanceId());
		assertEquals(1, nextTransitionNames2.size());
		assertEquals("toEnd", nextTransitionNames2.get(0));

		// Workflow Instance 3

		List<String> nextTransitionNames3 =
			WorkflowInstanceManagerUtil.getNextTransitionNames(
				user.getUserId(), workflowInstance3.getWorkflowInstanceId());
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
				user.getUserId(), _workflowDefinition1.getName(),
				_workflowDefinition1.getVersion(), null, null);
		WorkflowInstance workflowInstance2 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				user.getUserId(), _workflowDefinition2.getName(),
				_workflowDefinition2.getVersion(), null, null);
		WorkflowInstance workflowInstance3 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				user.getUserId(), _workflowDefinition3.getName(),
				_workflowDefinition3.getVersion(), null, null);

		// Workflow Instance 1

		long rootWorkflowInstanceId1 =
			workflowInstance1.getWorkflowInstanceId();

		workflowInstance1 =
			WorkflowInstanceManagerUtil.getWorkflowInstance(
				workflowInstance1.getWorkflowInstanceId());

		assertEquals(0, workflowInstance1.getChildren().size());
		Map<String, Object> context1 = workflowInstance1.getContext();
		assertNotNull(context1);
		assertEquals(1, context1.size());
		assertEquals("success", context1.get("javaNode11"));
		assertEquals("State1-1", workflowInstance1.getCurrentNodeName());
		assertNull(workflowInstance1.getEndDate());
		assertNull(workflowInstance1.getParent());
		assertNotNull(workflowInstance1.getStartDate());
		assertEquals(
			DEFINITION_NAME_1, workflowInstance1.getWorkflowDefinitionName());
		assertEquals(
			_workflowDefinition1.getVersion(),
			workflowInstance1.getWorkflowDefinitionVersion());
		assertEquals(
			rootWorkflowInstanceId1, workflowInstance1.getWorkflowInstanceId());

		// Workflow Instance 2

		long rootWorkflowInstanceId2 =
			workflowInstance2.getWorkflowInstanceId();

		workflowInstance2 =
			WorkflowInstanceManagerUtil.getWorkflowInstance(
				workflowInstance2.getWorkflowInstanceId());

		assertEquals(0, workflowInstance2.getChildren().size());
		Map<String, Object> context2 = workflowInstance2.getContext();
		assertNotNull(context2);
		assertEquals(1, context2.size());
		assertEquals("success", context2.get("javaNode21"));
		assertEquals("taskNode3", workflowInstance2.getCurrentNodeName());
		assertNull(workflowInstance2.getEndDate());
		assertNull(workflowInstance2.getParent());
		assertNotNull(workflowInstance2.getStartDate());
		assertEquals(
			DEFINITION_NAME_2, workflowInstance2.getWorkflowDefinitionName());
		assertEquals(
			_workflowDefinition2.getVersion(),
			workflowInstance2.getWorkflowDefinitionVersion());
		assertEquals(
			rootWorkflowInstanceId2, workflowInstance2.getWorkflowInstanceId());

		// Workflow Instance 3

		long rootWorkflowInstanceId3 =
			workflowInstance3.getWorkflowInstanceId();

		workflowInstance3 =
			WorkflowInstanceManagerUtil.getWorkflowInstance(
			workflowInstance3.getWorkflowInstanceId());

		List<WorkflowInstance> childrenInstances =
			workflowInstance3.getChildren();
		Collections.sort(childrenInstances, new Comparator<WorkflowInstance>() {

			public int compare(
				WorkflowInstance child1, WorkflowInstance child2) {
				return child1.getCurrentNodeName().compareTo(
					child2.getCurrentNodeName());
			}
		});

		assertEquals(3, childrenInstances.size());

		// Child Instance 1

		WorkflowInstance childInstance1 = childrenInstances.get(0);
		assertEquals(0, childInstance1.getChildren().size());
		Map<String, Object> context3_1 = childInstance1.getContext();
		assertNotNull(context3_1);
		assertEquals(1, context3_1.size());
		assertEquals("success", context3_1.get("javaNode31"));
		assertEquals("taskNode1", childInstance1.getCurrentNodeName());
		assertNull(childInstance1.getEndDate());
		assertEquals(
			rootWorkflowInstanceId3,
			childInstance1.getParent().getWorkflowInstanceId());
		assertNotNull(childInstance1.getStartDate());
		assertEquals(
			DEFINITION_NAME_3, childInstance1.getWorkflowDefinitionName());
		assertEquals(
			_workflowDefinition3.getVersion(),
			childInstance1.getWorkflowDefinitionVersion());

		// Child Instance 2

		WorkflowInstance childInstance2 = childrenInstances.get(1);
		assertEquals(0, childInstance2.getChildren().size());
		Map<String, Object> context3_2 = childInstance2.getContext();
		assertNotNull(context3_2);
		assertEquals(1, context3_2.size());
		assertEquals("success", context3_2.get("javaNode31"));
		assertEquals("taskNode2", childInstance2.getCurrentNodeName());
		assertNull(childInstance2.getEndDate());
		assertEquals(
			rootWorkflowInstanceId3,
			childInstance2.getParent().getWorkflowInstanceId());
		assertNotNull(childInstance2.getStartDate());
		assertEquals(
			DEFINITION_NAME_3, childInstance2.getWorkflowDefinitionName());
		assertEquals(
			_workflowDefinition3.getVersion(),
			childInstance2.getWorkflowDefinitionVersion());

		// Child Instance 3

		WorkflowInstance childInstance3 = childrenInstances.get(2);
		assertEquals(0, childInstance3.getChildren().size());
		Map<String, Object> context3_3 = childInstance3.getContext();
		assertNotNull(context3_3);
		assertEquals(1, context3_3.size());
		assertEquals("success", context3_3.get("javaNode31"));
		assertEquals("taskNode3", childInstance3.getCurrentNodeName());
		assertNull(childInstance3.getEndDate());
		assertEquals(
			rootWorkflowInstanceId3,
			childInstance3.getParent().getWorkflowInstanceId());
		assertNotNull(childInstance3.getStartDate());
		assertEquals(
			DEFINITION_NAME_3, childInstance3.getWorkflowDefinitionName());
		assertEquals(
			_workflowDefinition3.getVersion(),
			childInstance3.getWorkflowDefinitionVersion());

		Map<String, Object> context3 = workflowInstance3.getContext();
		assertNotNull(context3);
		assertEquals(1, context3.size());
		assertEquals("success", context3.get("javaNode31"));
		assertEquals("forkNode", workflowInstance3.getCurrentNodeName());
		assertNull(workflowInstance3.getEndDate());
		assertNull(workflowInstance3.getParent());
		assertNotNull(workflowInstance3.getStartDate());
		assertEquals(
			DEFINITION_NAME_3, workflowInstance3.getWorkflowDefinitionName());
		assertEquals(
			_workflowDefinition3.getVersion(),
			workflowInstance3.getWorkflowDefinitionVersion());
		assertEquals(
			rootWorkflowInstanceId3, workflowInstance3.getWorkflowInstanceId());

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
				user.getUserId(), _workflowDefinition1.getName(),
				_workflowDefinition1.getVersion(), null, null);
		WorkflowInstance workflowInstance1_2 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				user.getUserId(), _workflowDefinition1.getName(),
				_workflowDefinition1.getVersion(), null, null);
		WorkflowInstance workflowInstance1_3 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				user.getUserId(), _workflowDefinition1.getName(),
				_workflowDefinition1.getVersion(), null, null);
		WorkflowInstance workflowInstance2_1 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				user.getUserId(), _workflowDefinition2.getName(),
				_workflowDefinition2.getVersion(), null, null);
		WorkflowInstance workflowInstance2_2 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				user.getUserId(), _workflowDefinition2.getName(),
				_workflowDefinition2.getVersion(), null, null);

		workflowInstance1_1 =
			WorkflowInstanceManagerUtil.signalWorkflowInstance(
				user.getUserId(), workflowInstance1_1.getWorkflowInstanceId(),
				"toTaskNode", null);
		workflowInstance1_1 =
			WorkflowInstanceManagerUtil.signalWorkflowInstance(
				user.getUserId(), workflowInstance1_1.getWorkflowInstanceId(),
				"toEnd", null);

		workflowInstance1_2 =
			WorkflowInstanceManagerUtil.signalWorkflowInstance(
				user.getUserId(), workflowInstance1_2.getWorkflowInstanceId(),
				"toTaskNode", null);
		workflowInstance1_2 =
			WorkflowInstanceManagerUtil.signalWorkflowInstance(
				user.getUserId(), workflowInstance1_2.getWorkflowInstanceId(),
				"toEnd", null);

		workflowInstance2_1 =
			WorkflowInstanceManagerUtil.signalWorkflowInstance(
				user.getUserId(), workflowInstance2_1.getWorkflowInstanceId(),
				"toEnd", null);

		// Workflow Definition 1

		int count1 = WorkflowInstanceManagerUtil.getWorkflowInstanceCount(
			DEFINITION_NAME_1, _workflowDefinition1.getVersion(), Boolean.TRUE);
		assertEquals(2, count1);
		int count2 = WorkflowInstanceManagerUtil.getWorkflowInstanceCount(
			DEFINITION_NAME_1, _workflowDefinition1.getVersion(),
			Boolean.FALSE);
		assertEquals(1, count2);

		// Workflow Definition 2

		int count3 = WorkflowInstanceManagerUtil.getWorkflowInstanceCount(
			DEFINITION_NAME_2, _workflowDefinition2.getVersion(), Boolean.TRUE);
		assertEquals(1, count3);
		int count4 = WorkflowInstanceManagerUtil.getWorkflowInstanceCount(
			DEFINITION_NAME_2, _workflowDefinition2.getVersion(),
			Boolean.FALSE);
		assertEquals(1, count4);

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
				user.getUserId(), _workflowDefinition1.getName(),
				_workflowDefinition1.getVersion(), null, null);
		WorkflowInstance workflowInstance1_2 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				user.getUserId(), _workflowDefinition1.getName(),
				_workflowDefinition1.getVersion(), null, null);
		WorkflowInstance workflowInstance1_3 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				user.getUserId(), _workflowDefinition1.getName(),
				_workflowDefinition1.getVersion(), null, null);
		WorkflowInstance workflowInstance2_1 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				user.getUserId(), _workflowDefinition2.getName(),
				_workflowDefinition2.getVersion(), null, null);
		WorkflowInstance workflowInstance2_2 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				user.getUserId(), _workflowDefinition2.getName(),
				_workflowDefinition2.getVersion(), null, null);

		workflowInstance1_1 =
			WorkflowInstanceManagerUtil.signalWorkflowInstance(
				user.getUserId(), workflowInstance1_1.getWorkflowInstanceId(),
				"toTaskNode", null);
		workflowInstance1_1 =
			WorkflowInstanceManagerUtil.signalWorkflowInstance(
				user.getUserId(), workflowInstance1_1.getWorkflowInstanceId(),
				"toEnd", null);

		workflowInstance1_2 =
			WorkflowInstanceManagerUtil.signalWorkflowInstance(
				user.getUserId(), workflowInstance1_2.getWorkflowInstanceId(),
				"toTaskNode", null);
		workflowInstance1_2 =
			WorkflowInstanceManagerUtil.signalWorkflowInstance(
				user.getUserId(), workflowInstance1_2.getWorkflowInstanceId(),
				"toEnd", null);

		workflowInstance2_1 =
			WorkflowInstanceManagerUtil.signalWorkflowInstance(
				user.getUserId(), workflowInstance2_1.getWorkflowInstanceId(),
				"toEnd", null);

		// Workflow Definition 1 completed, all

		List<WorkflowInstance> workflowInstances1 =
			WorkflowInstanceManagerUtil.getWorkflowInstances(
				DEFINITION_NAME_1, _workflowDefinition1.getVersion(),
				Boolean.TRUE, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new WorkflowInstanceStartDateComparator(true));
		assertEquals(2, workflowInstances1.size());

		WorkflowInstance instance1 = workflowInstances1.get(0);
		assertEquals(0, instance1.getChildren().size());
		Map<String, Object> context1 = instance1.getContext();
		assertEquals(1, context1.size());
		assertEquals("success", context1.get("javaNode11"));
		assertEquals("end", instance1.getCurrentNodeName());
		assertNotNull(instance1.getEndDate());
		assertNull(instance1.getParent());
		assertNotNull(instance1.getStartDate());
		assertEquals(DEFINITION_NAME_1, instance1.getWorkflowDefinitionName());
		assertEquals(
			_workflowDefinition1.getVersion(),
			instance1.getWorkflowDefinitionVersion());
		assertEquals(
			workflowInstance1_1.getWorkflowInstanceId(),
			instance1.getWorkflowInstanceId());

		WorkflowInstance instance2 = workflowInstances1.get(1);
		assertEquals(0, instance2.getChildren().size());
		Map<String, Object> context2 = instance2.getContext();
		assertEquals(1, context2.size());
		assertEquals("success", context2.get("javaNode11"));
		assertEquals("end", instance2.getCurrentNodeName());
		assertNotNull(instance2.getEndDate());
		assertNull(instance2.getParent());
		assertNotNull(instance2.getStartDate());
		assertEquals(DEFINITION_NAME_1, instance2.getWorkflowDefinitionName());
		assertEquals(
			_workflowDefinition1.getVersion(),
			instance2.getWorkflowDefinitionVersion());
		assertEquals(
			workflowInstance1_2.getWorkflowInstanceId(),
			instance2.getWorkflowInstanceId());

		// Workflow Definition 1 completed, by range

		List<WorkflowInstance> workflowInstances2 =
			WorkflowInstanceManagerUtil.getWorkflowInstances(
				DEFINITION_NAME_1, _workflowDefinition1.getVersion(),
				Boolean.TRUE, 1, 2,
				new WorkflowInstanceStartDateComparator(true));
		assertEquals(1, workflowInstances2.size());

		WorkflowInstance instance3 = workflowInstances2.get(0);
		assertEquals(0, instance3.getChildren().size());
		Map<String, Object> context3 = instance3.getContext();
		assertEquals(1, context3.size());
		assertEquals("success", context3.get("javaNode11"));
		assertEquals("end", instance3.getCurrentNodeName());
		assertNotNull(instance3.getEndDate());
		assertNull(instance3.getParent());
		assertNotNull(instance3.getStartDate());
		assertEquals(DEFINITION_NAME_1, instance3.getWorkflowDefinitionName());
		assertEquals(
			_workflowDefinition1.getVersion(),
			instance3.getWorkflowDefinitionVersion());
		assertEquals(
			workflowInstance1_2.getWorkflowInstanceId(),
			instance3.getWorkflowInstanceId());

		// Workflow Definition 1 uncompleted, all

		List<WorkflowInstance> workflowInstances3 =
			WorkflowInstanceManagerUtil.getWorkflowInstances(
				DEFINITION_NAME_1, _workflowDefinition1.getVersion(),
				Boolean.FALSE, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new WorkflowInstanceStartDateComparator(true));
		assertEquals(1, workflowInstances3.size());

		WorkflowInstance instance4 = workflowInstances3.get(0);
		assertEquals(0, instance4.getChildren().size());
		Map<String, Object> context4 = instance4.getContext();
		assertEquals(1, context4.size());
		assertEquals("success", context4.get("javaNode11"));
		assertEquals("State1-1", instance4.getCurrentNodeName());
		assertNull(instance4.getEndDate());
		assertNull(instance4.getParent());
		assertNotNull(instance4.getStartDate());
		assertEquals(DEFINITION_NAME_1, instance4.getWorkflowDefinitionName());
		assertEquals(
			_workflowDefinition1.getVersion(),
			instance4.getWorkflowDefinitionVersion());
		assertEquals(
			workflowInstance1_3.getWorkflowInstanceId(),
			instance4.getWorkflowInstanceId());

		// Workflow Definition 2 completed, all

		List<WorkflowInstance> workflowInstances4 =
			WorkflowInstanceManagerUtil.getWorkflowInstances(
				DEFINITION_NAME_2, _workflowDefinition2.getVersion(),
				Boolean.TRUE, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new WorkflowInstanceStartDateComparator(true));
		assertEquals(1, workflowInstances4.size());

		WorkflowInstance instance5 = workflowInstances4.get(0);
		assertEquals(0, instance5.getChildren().size());
		Map<String, Object> context5 = instance5.getContext();
		assertEquals(1, context5.size());
		assertEquals("success", context5.get("javaNode21"));
		assertEquals("end", instance5.getCurrentNodeName());
		assertNotNull(instance5.getEndDate());
		assertNull(instance5.getParent());
		assertNotNull(instance5.getStartDate());
		assertEquals(DEFINITION_NAME_2, instance5.getWorkflowDefinitionName());
		assertEquals(
			_workflowDefinition2.getVersion(),
			instance5.getWorkflowDefinitionVersion());
		assertEquals(
			workflowInstance2_1.getWorkflowInstanceId(),
			instance5.getWorkflowInstanceId());

		// Workflow Definition 2 uncompleted, all

		List<WorkflowInstance> workflowInstances5 =
			WorkflowInstanceManagerUtil.getWorkflowInstances(
				DEFINITION_NAME_2, _workflowDefinition2.getVersion(),
				Boolean.FALSE, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new WorkflowInstanceStartDateComparator(true));
		assertEquals(1, workflowInstances5.size());

		WorkflowInstance instance6 = workflowInstances5.get(0);
		assertEquals(0, instance6.getChildren().size());
		Map<String, Object> context6 = instance6.getContext();
		assertEquals(1, context6.size());
		assertEquals("success", context6.get("javaNode21"));
		assertEquals("taskNode3", instance6.getCurrentNodeName());
		assertNull(instance6.getEndDate());
		assertNull(instance6.getParent());
		assertNotNull(instance6.getStartDate());
		assertEquals(DEFINITION_NAME_2, instance6.getWorkflowDefinitionName());
		assertEquals(
			_workflowDefinition2.getVersion(),
			instance6.getWorkflowDefinitionVersion());
		assertEquals(
			workflowInstance2_2.getWorkflowInstanceId(),
			instance6.getWorkflowInstanceId());

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
				user.getUserId(), _workflowDefinition4.getName(),
				_workflowDefinition4.getVersion(), null, null);
		assertEquals("Switch", workflowInstance.getCurrentNodeName());

		workflowInstance =
			WorkflowInstanceManagerUtil.signalWorkflowInstance(
				user.getUserId(), workflowInstance.getWorkflowInstanceId(),
				"toState1", null);
		assertEquals("State1", workflowInstance.getCurrentNodeName());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance.getWorkflowInstanceId());

		// Path 2
		workflowInstance =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				user.getUserId(), _workflowDefinition4.getName(),
				_workflowDefinition4.getVersion(), null, null);
		assertEquals("Switch", workflowInstance.getCurrentNodeName());

		workflowInstance =
			WorkflowInstanceManagerUtil.signalWorkflowInstance(
				user.getUserId(), workflowInstance.getWorkflowInstanceId(),
				"toState2", null);
		assertEquals("State2", workflowInstance.getCurrentNodeName());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance.getWorkflowInstanceId());

		// Path 3
		workflowInstance =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				user.getUserId(), _workflowDefinition4.getName(),
				_workflowDefinition4.getVersion(), null, null);
		assertEquals("Switch", workflowInstance.getCurrentNodeName());

		workflowInstance =
			WorkflowInstanceManagerUtil.signalWorkflowInstance(
				user.getUserId(), workflowInstance.getWorkflowInstanceId(),
				"toState3", null);
		assertEquals("State3", workflowInstance.getCurrentNodeName());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance.getWorkflowInstanceId());
	}

	public void testStartWorkflowInstance() throws Exception {

		// Workflow Instance 1

		WorkflowInstance workflowInstance1 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				user.getUserId(), _workflowDefinition1.getName(),
				_workflowDefinition1.getVersion(), null, null);

		assertNotNull(workflowInstance1);
		assertEquals(0, workflowInstance1.getChildren().size());
		Map<String, Object> context1 = workflowInstance1.getContext();
		assertNotNull(context1);
		assertEquals(1, context1.size());
		assertEquals("success", context1.get("javaNode11"));
		assertEquals("State1-1", workflowInstance1.getCurrentNodeName());
		assertNull(workflowInstance1.getEndDate());
		assertNull(workflowInstance1.getParent());
		assertNotNull(workflowInstance1.getStartDate());
		assertEquals(
			DEFINITION_NAME_1, workflowInstance1.getWorkflowDefinitionName());
		assertEquals(
			_workflowDefinition1.getVersion(),
			workflowInstance1.getWorkflowDefinitionVersion());

		// Workflow Instance 2

		WorkflowInstance workflowInstance2 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				user.getUserId(), _workflowDefinition2.getName(),
				_workflowDefinition2.getVersion(), null, null);

		assertNotNull(workflowInstance2);
		assertEquals(0, workflowInstance2.getChildren().size());
		Map<String, Object> context2 = workflowInstance2.getContext();
		assertNotNull(context2);
		assertEquals(1, context2.size());
		assertEquals("success", context2.get("javaNode21"));
		assertEquals("taskNode3", workflowInstance2.getCurrentNodeName());
		assertNull(workflowInstance2.getEndDate());
		assertNull(workflowInstance2.getParent());
		assertNotNull(workflowInstance2.getStartDate());
		assertEquals(
			DEFINITION_NAME_2, workflowInstance2.getWorkflowDefinitionName());
		assertEquals(
			_workflowDefinition2.getVersion(),
			workflowInstance2.getWorkflowDefinitionVersion());

		// Workflow Instance 3

		WorkflowInstance workflowInstance3 =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				user.getUserId(), _workflowDefinition3.getName(),
				_workflowDefinition3.getVersion(), null, null);

		assertNotNull(workflowInstance3);
		List<WorkflowInstance> childrenInstances =
			workflowInstance3.getChildren();
		assertEquals(0, childrenInstances.size());
		Map<String, Object> context3 = workflowInstance3.getContext();
		assertNotNull(context3);
		assertEquals(1, context3.size());
		assertEquals("success", context3.get("javaNode31"));
		assertEquals("forkNode", workflowInstance3.getCurrentNodeName());
		assertNull(workflowInstance3.getEndDate());
		assertNull(workflowInstance3.getParent());
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
				user.getUserId(), _workflowDefinition1.getName(),
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

		workflowInstance =
			WorkflowInstanceManagerUtil.updateContext(
				workflowInstance.getWorkflowInstanceId(), updateContext);

		context = workflowInstance.getContext();
		assertNotNull(context);
		assertEquals(2, context.size());
		assertEquals("success", context.get("javaNode11"));
		assertEquals(testValue1, context.get(testKey));

		updateContext = new HashMap<String, Object>();
		updateContext.put(testKey, testValue2);

		// Change exist variable

		workflowInstance =
			WorkflowInstanceManagerUtil.updateContext(
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