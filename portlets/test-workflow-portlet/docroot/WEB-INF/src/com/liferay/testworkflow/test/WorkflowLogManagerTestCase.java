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
import com.liferay.portal.kernel.workflow.comparator.WorkflowLogCreateDateComparator;

import java.io.ByteArrayInputStream;

import java.util.List;

/**
 * <a href="WorkflowLogManagerTestCase.java.html"><b><i>View Source</i></b></a>
 *
 * @author Shuyang Zhou
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

		int rootCount = WorkflowLogManagerUtil.getWorkflowLogCount(
			workflowInstance.getWorkflowInstanceId(), false);

		assertTrue(rootCount > 0);

		int allCount = WorkflowLogManagerUtil.getWorkflowLogCount(
			workflowInstance.getWorkflowInstanceId(), true);

		assertTrue(allCount > 0);

		assertTrue(allCount > rootCount);

		List<WorkflowInstance> childrenWorkflowInstances =
			workflowInstance.getChildrenWorkflowInstances();

		assertEquals(3, childrenWorkflowInstances.size());

		WorkflowInstance childWorkflowInstance1 = childrenWorkflowInstances.get(
			0);

		int childCount1 = WorkflowLogManagerUtil.getWorkflowLogCount(
			childWorkflowInstance1.getWorkflowInstanceId(), true);

		assertTrue(childCount1 > 0);

		WorkflowInstance childWorkflowInstance2 = childrenWorkflowInstances.get(
			1);

		int childCount2 = WorkflowLogManagerUtil.getWorkflowLogCount(
			childWorkflowInstance2.getWorkflowInstanceId(), true);

		assertTrue(childCount2 > 0);

		WorkflowInstance childWorkflowInstance3 = childrenWorkflowInstances.get(
			2);

		int childCount3 = WorkflowLogManagerUtil.getWorkflowLogCount(
			childWorkflowInstance3.getWorkflowInstanceId(), true);

		assertTrue(childCount3 > 0);

		assertEquals(
			allCount, rootCount + childCount1 + childCount2 + childCount3);

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance.getWorkflowInstanceId());
	}

	public void testGetWorkflowLogs() throws Exception {
		WorkflowInstance workflowInstance =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				defaultUserId, _workflowDefinition.getName(),
				_workflowDefinition.getVersion(), null, null);

		List<WorkflowLog> rootWorkflowLogs =
			WorkflowLogManagerUtil.getWorkflowLogs(
				workflowInstance.getWorkflowInstanceId(), false,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new WorkflowLogCreateDateComparator(true));

		assertTrue(!rootWorkflowLogs.isEmpty());

		for (WorkflowLog workflowLog : rootWorkflowLogs) {
			assertEquals(
				workflowInstance.getWorkflowInstanceId(),
				workflowLog.getWorkflowInstanceId());
		}

		List<WorkflowLog> allWorkflowLogs =
			WorkflowLogManagerUtil.getWorkflowLogs(
				workflowInstance.getWorkflowInstanceId(), true,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new WorkflowLogCreateDateComparator(true));

		assertTrue(!allWorkflowLogs.isEmpty());
		assertTrue(allWorkflowLogs.size() > rootWorkflowLogs.size());

		List<WorkflowInstance> childrenWorkflowInstances =
			workflowInstance.getChildrenWorkflowInstances();

		assertEquals(3, childrenWorkflowInstances.size());

		WorkflowInstance childWorkflowInstance1 = childrenWorkflowInstances.get(
			0);

		List<WorkflowLog> childWorkflowLogs1 =
			WorkflowLogManagerUtil.getWorkflowLogs(
				childWorkflowInstance1.getWorkflowInstanceId(), true,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new WorkflowLogCreateDateComparator(true));

		assertTrue(!childWorkflowLogs1.isEmpty());

		for (WorkflowLog workflowLog : childWorkflowLogs1) {
			assertEquals(
				childWorkflowInstance1.getWorkflowInstanceId(),
				workflowLog.getWorkflowInstanceId());
		}

		WorkflowInstance childWorkflowInstance2 = childrenWorkflowInstances.get(
			1);

		List<WorkflowLog> childWorkflowLogs2 =
			WorkflowLogManagerUtil.getWorkflowLogs(
				childWorkflowInstance2.getWorkflowInstanceId(), true,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new WorkflowLogCreateDateComparator(true));

		assertTrue(!childWorkflowLogs2.isEmpty());

		for (WorkflowLog workflowLog : childWorkflowLogs2) {
			assertEquals(
				childWorkflowInstance2.getWorkflowInstanceId(),
				workflowLog.getWorkflowInstanceId());
		}

		WorkflowInstance childWorkflowInstance3 = childrenWorkflowInstances.get(
			2);

		List<WorkflowLog> childWorkflowLogs3 =
			WorkflowLogManagerUtil.getWorkflowLogs(
				childWorkflowInstance3.getWorkflowInstanceId(), true,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new WorkflowLogCreateDateComparator(true));

		assertTrue(!childWorkflowLogs2.isEmpty());

		for (WorkflowLog workflowLog : childWorkflowLogs3) {
			assertEquals(
				childWorkflowInstance3.getWorkflowInstanceId(),
				workflowLog.getWorkflowInstanceId());
		}

		assertEquals(
			allWorkflowLogs.size(),
			rootWorkflowLogs.size() + childWorkflowLogs1.size() +
				childWorkflowLogs2.size() + childWorkflowLogs3.size());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance.getWorkflowInstanceId());
	}

	private WorkflowDefinition _workflowDefinition;

}