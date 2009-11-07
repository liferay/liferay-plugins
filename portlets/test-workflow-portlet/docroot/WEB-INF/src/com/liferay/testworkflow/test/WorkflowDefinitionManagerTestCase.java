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
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.kernel.workflow.WorkflowDefinitionManagerUtil;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.kernel.workflow.comparator.WorkflowDefinitionNameComparator;

import java.io.ByteArrayInputStream;

import java.util.List;

/**
 * <a href="WorkflowDefinitionManagerTestCase.java.html"><b><i>View Source</i>
 * </b></a>
 *
 * @author Shuyang Zhou
 */
public class WorkflowDefinitionManagerTestCase extends WorkflowTestCase {

	public void testDeployWorkflowDefinition() throws Exception {

		// Test Workflow Definition 1

		_workflowDefinition1_1 =
			WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
				user.getUserId(), DEFINITION_NAME_1,
				new ByteArrayInputStream(definitionBytes1));

		_workflowDefinition1_2 =
			WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
				user.getUserId(), DEFINITION_NAME_1,
				new ByteArrayInputStream(definitionBytes1));

		_workflowDefinition1_3 =
			WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
				user.getUserId(), DEFINITION_NAME_1,
				new ByteArrayInputStream(definitionBytes1));

		// Test Workflow Definition 2

		_workflowDefinition2_1 =
			WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
				user.getUserId(), DEFINITION_NAME_2,
				new ByteArrayInputStream(definitionBytes2));

		_workflowDefinition2_2 =
			WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
				user.getUserId(), DEFINITION_NAME_2,
				new ByteArrayInputStream(definitionBytes2));

		// Test Workflow Definition 3

		_workflowDefinition3_1 =
			WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
				user.getUserId(), DEFINITION_NAME_3,
				new ByteArrayInputStream(definitionBytes3));
	}

	public void testGetWorkflowDefinition() throws Exception {

		// Test Workflow Definition 1

		// Version 1

		WorkflowDefinition workflowDefinition1_1 =
			WorkflowDefinitionManagerUtil.getWorkflowDefinition(
				_workflowDefinition1_1.getName(),
				_workflowDefinition1_1.getVersion());

		String name1_1 = workflowDefinition1_1.getName();
		int version1_1 = workflowDefinition1_1.getVersion();

		assertEquals(DEFINITION_NAME_1, name1_1);
		assertEquals(_workflowDefinition1_1.getVersion(), version1_1);

		// Version 2

		WorkflowDefinition workflowDefinition1_2 =
			WorkflowDefinitionManagerUtil.getWorkflowDefinition(
				_workflowDefinition1_2.getName(),
				_workflowDefinition1_2.getVersion());

		String name1_2 = workflowDefinition1_2.getName();
		int version1_2 = workflowDefinition1_2.getVersion();

		assertEquals(DEFINITION_NAME_1, name1_2);
		assertEquals(_workflowDefinition1_2.getVersion(), version1_2);

		// Version 3

		WorkflowDefinition workflowDefinition1_3 =
			WorkflowDefinitionManagerUtil.getWorkflowDefinition(
				_workflowDefinition1_3.getName(),
				_workflowDefinition1_3.getVersion());

		String name1_3 = workflowDefinition1_3.getName();
		int version1_3 = workflowDefinition1_3.getVersion();

		assertEquals(DEFINITION_NAME_1, name1_3);
		assertEquals(_workflowDefinition1_3.getVersion(), version1_3);

		// Version 4

		try{
			WorkflowDefinitionManagerUtil.getWorkflowDefinition(
				_workflowDefinition1_3.getName(),
				_workflowDefinition1_3.getVersion() + 1);

			fail();
		}
		catch (WorkflowException we) {
		}

		// Test Workflow Definition 2

		// Version 1

		WorkflowDefinition workflowDefinition2_1 =
			WorkflowDefinitionManagerUtil.getWorkflowDefinition(
				_workflowDefinition2_1.getName(),
				_workflowDefinition2_1.getVersion());

		String name2_1 = workflowDefinition2_1.getName();
		int version2_1 = workflowDefinition2_1.getVersion();

		assertEquals(DEFINITION_NAME_2, name2_1);
		assertEquals(_workflowDefinition2_1.getVersion(), version2_1);

		// Version 2

		WorkflowDefinition workflowDefinition2_2 =
			WorkflowDefinitionManagerUtil.getWorkflowDefinition(
				_workflowDefinition2_2.getName(),
				_workflowDefinition2_2.getVersion());

		String name2_2 = workflowDefinition2_2.getName();
		int version2_2 = workflowDefinition2_2.getVersion();

		assertEquals(DEFINITION_NAME_2, name2_2);
		assertEquals(_workflowDefinition2_2.getVersion(), version2_2);

		// Version 3

		try{
			WorkflowDefinitionManagerUtil.getWorkflowDefinition(
				_workflowDefinition2_2.getName(),
				_workflowDefinition2_2.getVersion() + 1);

			fail();
		}
		catch (WorkflowException we) {
		}

		// Test Workflow Definition 3

		// Version 1

		WorkflowDefinition workflowDefinition3_1 =
			WorkflowDefinitionManagerUtil.getWorkflowDefinition(
				_workflowDefinition3_1.getName(),
				_workflowDefinition3_1.getVersion());

		String name3_1 = workflowDefinition3_1.getName();
		int version3_1 = workflowDefinition3_1.getVersion();

		assertEquals(DEFINITION_NAME_3, name3_1);
		assertEquals(_workflowDefinition3_1.getVersion(), version3_1);

		// Version 2

		try{
			WorkflowDefinitionManagerUtil.getWorkflowDefinition(
				_workflowDefinition3_1.getName(),
				_workflowDefinition3_1.getVersion() + 1);

			fail();
		}
		catch (WorkflowException we) {
		}
	}

	public void testGetWorkflowDefinitionCount() throws Exception {
		int count = WorkflowDefinitionManagerUtil.getWorkflowDefinitionCount();

		assertEquals(3, count);
	}

	public void testGetWorkflowDefinitionCountByName() throws Exception {
		int count = WorkflowDefinitionManagerUtil.getWorkflowDefinitionCount(
			DEFINITION_NAME_1);

		assertEquals(3, count);

		count = WorkflowDefinitionManagerUtil.getWorkflowDefinitionCount(
			DEFINITION_NAME_2);

		assertEquals(2, count);

		count = WorkflowDefinitionManagerUtil.getWorkflowDefinitionCount(
			DEFINITION_NAME_3);

		assertEquals(1, count);
	}

	public void testGetWorkflowDefinitions() throws Exception {

		// Get all workflow definitions

		OrderByComparator orderByComparator =
			new WorkflowDefinitionNameComparator();

		List<WorkflowDefinition> workflowDefinitions =
			WorkflowDefinitionManagerUtil.getWorkflowDefinitions(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, orderByComparator);

		assertEquals(3, workflowDefinitions.size());

		// Test Workflow Definition 3

		WorkflowDefinition workflowDefinition3 = workflowDefinitions.get(0);

		String name3 = workflowDefinition3.getName();
		int version3 = workflowDefinition3.getVersion();

		assertEquals(DEFINITION_NAME_3, name3);
		assertEquals(_workflowDefinition3_1.getVersion(), version3);

		// Test Workflow Definition 2

		WorkflowDefinition workflowDefinition2 = workflowDefinitions.get(1);

		String name2 = workflowDefinition2.getName();
		int version2 = workflowDefinition2.getVersion();

		assertEquals(DEFINITION_NAME_2, name2);
		assertEquals(_workflowDefinition2_2.getVersion(), version2);

		// Test Workflow Definition 1

		WorkflowDefinition workflowDefinition1 = workflowDefinitions.get(2);

		String name1 = workflowDefinition1.getName();
		int version1 = workflowDefinition1.getVersion();

		assertEquals(DEFINITION_NAME_1, name1);
		assertEquals(_workflowDefinition1_3.getVersion(), version1);

		// Get all workflow definitions by range

		workflowDefinitions =
			WorkflowDefinitionManagerUtil.getWorkflowDefinitions(
				0, 2, orderByComparator);

		assertEquals(2, workflowDefinitions.size());

		// Test Workflow Definition 3

		workflowDefinition3 = workflowDefinitions.get(0);

		name3 = workflowDefinition3.getName();
		version3 = workflowDefinition3.getVersion();

		assertEquals(DEFINITION_NAME_3, name3);
		assertEquals(_workflowDefinition3_1.getVersion(), version3);

		// Test Workflow Definition 2

		workflowDefinition2 = workflowDefinitions.get(1);

		name2 = workflowDefinition2.getName();
		version2 = workflowDefinition2.getVersion();

		assertEquals(DEFINITION_NAME_2, name2);
		assertEquals(_workflowDefinition2_2.getVersion(), version2);
	}

	public void testGetWorkflowDefinitionsByName() throws Exception {

		// Get all workflow definitions with name Test Workflow Definition 1

		OrderByComparator orderByComparator =
			new WorkflowDefinitionNameComparator();

		List<WorkflowDefinition> workflowDefinitions =
			WorkflowDefinitionManagerUtil.getWorkflowDefinitions(
				DEFINITION_NAME_1, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				orderByComparator);

		assertEquals(3, workflowDefinitions.size());

		// Version 3

		WorkflowDefinition workflowDefinition1_3 =
			workflowDefinitions.get(0);

		String name1_3 = workflowDefinition1_3.getName();
		int version1_3 = workflowDefinition1_3.getVersion();

		assertEquals(DEFINITION_NAME_1, name1_3);
		assertEquals(_workflowDefinition1_3.getVersion(), version1_3);

		// Version 2

		WorkflowDefinition workflowDefinition1_2 =
			workflowDefinitions.get(1);

		String name1_2 = workflowDefinition1_2.getName();
		int version1_2 = workflowDefinition1_2.getVersion();

		assertEquals(DEFINITION_NAME_1, name1_2);
		assertEquals(_workflowDefinition1_2.getVersion(), version1_2);

		// Version 1

		WorkflowDefinition workflowDefinition1_1 =
			workflowDefinitions.get(2);

		String name1_1 = workflowDefinition1_1.getName();
		int version1_1 = workflowDefinition1_1.getVersion();

		assertEquals(DEFINITION_NAME_1, name1_1);
		assertEquals(_workflowDefinition1_1.getVersion(), version1_1);

		// Get all workflow definitions with name Test Workflow Definition 1 by
		// range

		workflowDefinitions =
			WorkflowDefinitionManagerUtil.getWorkflowDefinitions(
				DEFINITION_NAME_1, 0, 2, orderByComparator);

		assertEquals(2, workflowDefinitions.size());

		// Version 3

		workflowDefinition1_3 = workflowDefinitions.get(0);
		name1_3 = workflowDefinition1_3.getName();
		version1_3 = workflowDefinition1_3.getVersion();

		assertEquals(DEFINITION_NAME_1, name1_3);
		assertEquals(_workflowDefinition1_3.getVersion(), version1_3);

		// Version 2

		workflowDefinition1_2 = workflowDefinitions.get(1);

		name1_2 = workflowDefinition1_2.getName();
		version1_2 = workflowDefinition1_2.getVersion();

		assertEquals(DEFINITION_NAME_1, name1_2);
		assertEquals(_workflowDefinition1_2.getVersion(), version1_2);
	}

	public void testUndeployWorkflowDefinition() throws Exception {

		// Test Workflow Definition 1

		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			user.getUserId(), _workflowDefinition1_1.getName(),
			_workflowDefinition1_1.getVersion());

		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			user.getUserId(), _workflowDefinition1_2.getName(),
			_workflowDefinition1_2.getVersion());

		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			user.getUserId(), _workflowDefinition1_3.getName(),
			_workflowDefinition1_3.getVersion());

		// Test Workflow Definition 2

		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			user.getUserId(), _workflowDefinition2_1.getName(),
			_workflowDefinition2_1.getVersion());

		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			user.getUserId(), _workflowDefinition2_2.getName(),
			_workflowDefinition2_2.getVersion());

		// Test Workflow Definition 3

		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			user.getUserId(), _workflowDefinition3_1.getName(),
			_workflowDefinition3_1.getVersion());

		int count = WorkflowDefinitionManagerUtil.getWorkflowDefinitionCount();

		assertEquals(0, count);
	}

	private WorkflowDefinition _workflowDefinition1_1;
	private WorkflowDefinition _workflowDefinition1_2;
	private WorkflowDefinition _workflowDefinition1_3;
	private WorkflowDefinition _workflowDefinition2_1;
	private WorkflowDefinition _workflowDefinition2_2;
	private WorkflowDefinition _workflowDefinition3_1;

}