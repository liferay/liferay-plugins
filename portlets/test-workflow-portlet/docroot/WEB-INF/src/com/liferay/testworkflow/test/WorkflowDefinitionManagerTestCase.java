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
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.kernel.workflow.WorkflowDefinitionManagerUtil;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.kernel.workflow.comparator.WorkflowComparatorFactoryUtil;

import java.io.ByteArrayInputStream;

import java.util.List;

/**
 * @author Shuyang Zhou
 */
public class WorkflowDefinitionManagerTestCase extends WorkflowTestCase {

	public void testDeployWorkflowDefinition() throws Exception {

		// Workflow definition 1

		_workflowDefinition1_1 =
			WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
				companyId, defaultUserId, DEFINITION_NAME_1,
				new ByteArrayInputStream(definitionBytes1));

		_workflowDefinition1_2 =
			WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
				companyId, defaultUserId, DEFINITION_NAME_1,
				new ByteArrayInputStream(definitionBytes1));

		_workflowDefinition1_3 =
			WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
				companyId, defaultUserId, DEFINITION_NAME_1,
				new ByteArrayInputStream(definitionBytes1));

		// Workflow definition 2

		_workflowDefinition2_1 =
			WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
				companyId, defaultUserId, DEFINITION_NAME_2,
				new ByteArrayInputStream(definitionBytes2));

		_workflowDefinition2_2 =
			WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
				companyId, defaultUserId, DEFINITION_NAME_2,
				new ByteArrayInputStream(definitionBytes2));

		// Workflow definition 3

		_workflowDefinition3_1 =
			WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
				companyId, defaultUserId, DEFINITION_NAME_3,
				new ByteArrayInputStream(definitionBytes3));
	}

	public void testGetWorkflowDefinition() throws Exception {

		// Workflow definition 1

		// Version 1

		WorkflowDefinition workflowDefinition1_1 =
			WorkflowDefinitionManagerUtil.getWorkflowDefinition(
				companyId, _workflowDefinition1_1.getName(),
				_workflowDefinition1_1.getVersion());

		String name1_1 = workflowDefinition1_1.getName();
		int version1_1 = workflowDefinition1_1.getVersion();

		assertEquals(DEFINITION_NAME_1, name1_1);
		assertEquals(_workflowDefinition1_1.getVersion(), version1_1);

		// Version 2

		WorkflowDefinition workflowDefinition1_2 =
			WorkflowDefinitionManagerUtil.getWorkflowDefinition(
				companyId, _workflowDefinition1_2.getName(),
				_workflowDefinition1_2.getVersion());

		String name1_2 = workflowDefinition1_2.getName();
		int version1_2 = workflowDefinition1_2.getVersion();

		assertEquals(DEFINITION_NAME_1, name1_2);
		assertEquals(_workflowDefinition1_2.getVersion(), version1_2);

		// Version 3

		WorkflowDefinition workflowDefinition1_3 =
			WorkflowDefinitionManagerUtil.getWorkflowDefinition(
				companyId, _workflowDefinition1_3.getName(),
				_workflowDefinition1_3.getVersion());

		String name1_3 = workflowDefinition1_3.getName();
		int version1_3 = workflowDefinition1_3.getVersion();

		assertEquals(DEFINITION_NAME_1, name1_3);
		assertEquals(_workflowDefinition1_3.getVersion(), version1_3);

		// Version 4

		try{
			WorkflowDefinitionManagerUtil.getWorkflowDefinition(
				companyId, _workflowDefinition1_3.getName(),
				_workflowDefinition1_3.getVersion() + 1);

			fail();
		}
		catch (WorkflowException we) {
		}

		// Workflow definition 2

		// Version 1

		WorkflowDefinition workflowDefinition2_1 =
			WorkflowDefinitionManagerUtil.getWorkflowDefinition(
				companyId, _workflowDefinition2_1.getName(),
				_workflowDefinition2_1.getVersion());

		String name2_1 = workflowDefinition2_1.getName();
		int version2_1 = workflowDefinition2_1.getVersion();

		assertEquals(DEFINITION_NAME_2, name2_1);
		assertEquals(_workflowDefinition2_1.getVersion(), version2_1);

		// Version 2

		WorkflowDefinition workflowDefinition2_2 =
			WorkflowDefinitionManagerUtil.getWorkflowDefinition(
				companyId, _workflowDefinition2_2.getName(),
				_workflowDefinition2_2.getVersion());

		String name2_2 = workflowDefinition2_2.getName();
		int version2_2 = workflowDefinition2_2.getVersion();

		assertEquals(DEFINITION_NAME_2, name2_2);
		assertEquals(_workflowDefinition2_2.getVersion(), version2_2);

		// Version 3

		try{
			WorkflowDefinitionManagerUtil.getWorkflowDefinition(
				companyId, _workflowDefinition2_2.getName(),
				_workflowDefinition2_2.getVersion() + 1);

			fail();
		}
		catch (WorkflowException we) {
		}

		// Workflow definition 3

		// Version 1

		WorkflowDefinition workflowDefinition3_1 =
			WorkflowDefinitionManagerUtil.getWorkflowDefinition(
				companyId, _workflowDefinition3_1.getName(),
				_workflowDefinition3_1.getVersion());

		String name3_1 = workflowDefinition3_1.getName();
		int version3_1 = workflowDefinition3_1.getVersion();

		assertEquals(DEFINITION_NAME_3, name3_1);
		assertEquals(_workflowDefinition3_1.getVersion(), version3_1);

		// Version 2

		try{
			WorkflowDefinitionManagerUtil.getWorkflowDefinition(
				companyId, _workflowDefinition3_1.getName(),
				_workflowDefinition3_1.getVersion() + 1);

			fail();
		}
		catch (WorkflowException we) {
		}
	}

	public void testGetWorkflowDefinitionCount() throws Exception {
		int count = WorkflowDefinitionManagerUtil.getWorkflowDefinitionCount(
			companyId);

		assertEquals(3, count);
	}

	public void testGetWorkflowDefinitionCountByName() throws Exception {
		int count = WorkflowDefinitionManagerUtil.getWorkflowDefinitionCount(
			companyId, DEFINITION_NAME_1);

		assertEquals(3, count);

		count = WorkflowDefinitionManagerUtil.getWorkflowDefinitionCount(
			companyId, DEFINITION_NAME_2);

		assertEquals(2, count);

		count = WorkflowDefinitionManagerUtil.getWorkflowDefinitionCount(
			companyId, DEFINITION_NAME_3);

		assertEquals(1, count);
	}

	public void testGetWorkflowDefinitions() throws Exception {

		// All workflow definitions

		OrderByComparator orderByComparator =
			WorkflowComparatorFactoryUtil.getDefinitionNameComparator();

		List<WorkflowDefinition> workflowDefinitions =
			WorkflowDefinitionManagerUtil.getWorkflowDefinitions(
				companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				orderByComparator);

		assertEquals(3, workflowDefinitions.size());

		// Workflow definition 3

		WorkflowDefinition workflowDefinition3 = workflowDefinitions.get(0);

		String name3 = workflowDefinition3.getName();
		int version3 = workflowDefinition3.getVersion();

		assertEquals(DEFINITION_NAME_3, name3);
		assertEquals(_workflowDefinition3_1.getVersion(), version3);

		// Workflow definition 2

		WorkflowDefinition workflowDefinition2 = workflowDefinitions.get(1);

		String name2 = workflowDefinition2.getName();
		int version2 = workflowDefinition2.getVersion();

		assertEquals(DEFINITION_NAME_2, name2);
		assertEquals(_workflowDefinition2_2.getVersion(), version2);

		// Workflow definition 1

		WorkflowDefinition workflowDefinition1 = workflowDefinitions.get(2);

		String name1 = workflowDefinition1.getName();
		int version1 = workflowDefinition1.getVersion();

		assertEquals(DEFINITION_NAME_1, name1);
		assertEquals(_workflowDefinition1_3.getVersion(), version1);

		// All workflow definitions by range

		workflowDefinitions =
			WorkflowDefinitionManagerUtil.getWorkflowDefinitions(
				companyId, 0, 2, orderByComparator);

		assertEquals(2, workflowDefinitions.size());

		// Workflow definition 3

		workflowDefinition3 = workflowDefinitions.get(0);

		name3 = workflowDefinition3.getName();
		version3 = workflowDefinition3.getVersion();

		assertEquals(DEFINITION_NAME_3, name3);
		assertEquals(_workflowDefinition3_1.getVersion(), version3);

		// Workflow definition 2

		workflowDefinition2 = workflowDefinitions.get(1);

		name2 = workflowDefinition2.getName();
		version2 = workflowDefinition2.getVersion();

		assertEquals(DEFINITION_NAME_2, name2);
		assertEquals(_workflowDefinition2_2.getVersion(), version2);
	}

	public void testGetWorkflowDefinitionsByName() throws Exception {

		// All versions of workflow definition 1

		OrderByComparator orderByComparator =
			WorkflowComparatorFactoryUtil.getDefinitionNameComparator();

		List<WorkflowDefinition> workflowDefinitions =
			WorkflowDefinitionManagerUtil.getWorkflowDefinitions(
				companyId, DEFINITION_NAME_1, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, orderByComparator);

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

		// All versions of workflow definition 1 by range

		workflowDefinitions =
			WorkflowDefinitionManagerUtil.getWorkflowDefinitions(
				companyId, DEFINITION_NAME_1, 0, 2, orderByComparator);

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

		// Workflow definition 1

		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			companyId, defaultUserId, _workflowDefinition1_1.getName(),
			_workflowDefinition1_1.getVersion());

		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			companyId, defaultUserId, _workflowDefinition1_2.getName(),
			_workflowDefinition1_2.getVersion());

		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			companyId, defaultUserId, _workflowDefinition1_3.getName(),
			_workflowDefinition1_3.getVersion());

		// Workflow definition 2

		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			companyId, defaultUserId, _workflowDefinition2_1.getName(),
			_workflowDefinition2_1.getVersion());

		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			companyId, defaultUserId, _workflowDefinition2_2.getName(),
			_workflowDefinition2_2.getVersion());

		// Workflow definition 3

		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			companyId, defaultUserId, _workflowDefinition3_1.getName(),
			_workflowDefinition3_1.getVersion());

		int count = WorkflowDefinitionManagerUtil.getWorkflowDefinitionCount(
			companyId);

		assertEquals(0, count);
	}

	private WorkflowDefinition _workflowDefinition1_1;
	private WorkflowDefinition _workflowDefinition1_2;
	private WorkflowDefinition _workflowDefinition1_3;
	private WorkflowDefinition _workflowDefinition2_1;
	private WorkflowDefinition _workflowDefinition2_2;
	private WorkflowDefinition _workflowDefinition3_1;

}