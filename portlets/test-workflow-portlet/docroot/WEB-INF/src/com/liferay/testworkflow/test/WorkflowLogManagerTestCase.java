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
import com.liferay.portal.kernel.workflow.WorkflowLog;
import com.liferay.portal.kernel.workflow.WorkflowLogManagerUtil;
import com.liferay.portal.kernel.workflow.comparator.WorkflowLogCreateDateComparator;

import java.io.ByteArrayInputStream;

import java.util.List;

/**
 *
 * <a href="WorkflowLogManagerTestCase.java.html"><b><i>View Source</i></b></a>
 *
 * @author Shuyang Zhou
 */
public class WorkflowLogManagerTestCase extends WorkflowTestCase {

	public void setUp() throws Exception {
		super.setUp();
		_workflowDefinition =
			WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
				user.getUserId(), DEFINITION_NAME_3,
				new ByteArrayInputStream(definitionBytes3));
	}

	public void tearDown() throws Exception {

		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			user.getUserId(), DEFINITION_NAME_3,
			_workflowDefinition.getVersion());

	}

	public void testGetWorkflowLogCount() throws Exception {
		WorkflowInstance workflowInstance =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				user.getUserId(), _workflowDefinition.getName(),
				_workflowDefinition.getVersion(), null, null);

		int countForRoot =
			WorkflowLogManagerUtil.getWorkflowLogCount(
				workflowInstance.getWorkflowInstanceId(), false);
		assertTrue(countForRoot > 0);

		int countForAll =
			WorkflowLogManagerUtil.getWorkflowLogCount(
				workflowInstance.getWorkflowInstanceId(), true);
		assertTrue(countForAll > 0);

		assertTrue(countForAll > countForRoot);

		workflowInstance =
			WorkflowInstanceManagerUtil.getWorkflowInstance(
				workflowInstance.getWorkflowInstanceId());

		List<WorkflowInstance> childrenWorkflowInstances =
			workflowInstance.getChildren();
		assertEquals(3, childrenWorkflowInstances.size());

		int countForChild1 =
			WorkflowLogManagerUtil.getWorkflowLogCount(
				childrenWorkflowInstances.get(0).getWorkflowInstanceId(), true);
		assertTrue(countForChild1 > 0);

		int countForChild2 =
			WorkflowLogManagerUtil.getWorkflowLogCount(
				childrenWorkflowInstances.get(1).getWorkflowInstanceId(), true);
		assertTrue(countForChild1 > 0);

		int countForChild3 =
			WorkflowLogManagerUtil.getWorkflowLogCount(
				childrenWorkflowInstances.get(2).getWorkflowInstanceId(), true);
		assertTrue(countForChild1 > 0);

		assertEquals(
			countForAll,
			countForRoot + countForChild1 + countForChild2 + countForChild3);

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			workflowInstance.getWorkflowInstanceId());
	}

	public void testGetWorkflowLogs() throws Exception {
		WorkflowInstance workflowInstance =
			WorkflowInstanceManagerUtil.startWorkflowInstance(
				user.getUserId(), _workflowDefinition.getName(),
				_workflowDefinition.getVersion(), null, null);

		long rootWorkflowInstanceId = workflowInstance.getWorkflowInstanceId();

		List<WorkflowLog> logsForRoot =
			WorkflowLogManagerUtil.getWorkflowLogs(
				rootWorkflowInstanceId, false, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, new WorkflowLogCreateDateComparator(true));
		assertTrue(logsForRoot.size() > 0);

		for(WorkflowLog log : logsForRoot) {
			assertEquals(rootWorkflowInstanceId, log.getWorkflowInstanceId());
		}
		List<WorkflowLog> logsForAll =
			WorkflowLogManagerUtil.getWorkflowLogs(
				rootWorkflowInstanceId, true, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, new WorkflowLogCreateDateComparator(true));
		assertTrue(logsForAll.size() > 0);

		assertTrue(logsForAll.size() > logsForRoot.size());

		workflowInstance =
			WorkflowInstanceManagerUtil.getWorkflowInstance(
				workflowInstance.getWorkflowInstanceId());

		List<WorkflowInstance> childrenWorkflowInstances =
			workflowInstance.getChildren();
		assertEquals(3, childrenWorkflowInstances.size());

		long childWorkflowInstanceId1 =
			childrenWorkflowInstances.get(0).getWorkflowInstanceId();
		List<WorkflowLog> logsForChild1 =
			WorkflowLogManagerUtil.getWorkflowLogs(
				childWorkflowInstanceId1, true, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, new WorkflowLogCreateDateComparator(true));
		assertTrue(logsForChild1.size() > 0);
		for(WorkflowLog log : logsForChild1) {
			assertEquals(childWorkflowInstanceId1, log.getWorkflowInstanceId());
		}

		long childWorkflowInstanceId2 =
			childrenWorkflowInstances.get(1).getWorkflowInstanceId();
		List<WorkflowLog> logsForChild2 =
			WorkflowLogManagerUtil.getWorkflowLogs(
				childWorkflowInstanceId2, true, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, new WorkflowLogCreateDateComparator(true));
		assertTrue(logsForChild2.size() > 0);
		for(WorkflowLog log : logsForChild2) {
			assertEquals(childWorkflowInstanceId2, log.getWorkflowInstanceId());
		}

		long childWorkflowInstanceId3 =
			childrenWorkflowInstances.get(2).getWorkflowInstanceId();
		List<WorkflowLog> logsForChild3 =
			WorkflowLogManagerUtil.getWorkflowLogs(
				childWorkflowInstanceId3, true, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, new WorkflowLogCreateDateComparator(true));
		assertTrue(logsForChild3.size() > 0);
		for(WorkflowLog log : logsForChild3) {
			assertEquals(childWorkflowInstanceId3, log.getWorkflowInstanceId());
		}

		assertEquals(
			logsForAll.size(),
			logsForRoot.size() + logsForChild1.size() + logsForChild2.size() +
				logsForChild3.size());

		WorkflowInstanceManagerUtil.deleteWorkflowInstance(
			rootWorkflowInstanceId);
	}

	private WorkflowDefinition _workflowDefinition;

}