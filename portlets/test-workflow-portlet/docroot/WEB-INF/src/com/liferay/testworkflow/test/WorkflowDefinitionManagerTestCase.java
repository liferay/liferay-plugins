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
import com.liferay.portal.kernel.workflow.WorkflowEngineManagerUtil;
import com.liferay.portal.kernel.workflow.comparator.WorkflowDefinitionNameComparator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import java.io.ByteArrayInputStream;

import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.servlet.ServletContext;

/**
 * <a href="WorkflowDefinitionManagerTestCase.java.html"><b><i>View Source</i>
 * </b></a>
 *
 * @author Shuyang Zhou
 */
public class WorkflowDefinitionManagerTestCase extends BaseTestCase {

	public WorkflowDefinitionManagerTestCase(ServletContext servletContext) {
		super(servletContext);
	}

	public void setUp() throws Exception {
		long companyId = PortalUtil.getDefaultCompanyId();

		_user = UserLocalServiceUtil.getDefaultUser(companyId);

		ZipInputStream zipInputStream = new ZipInputStream(
			servletContext.getResourceAsStream(
				"WEB-INF/bundles/" + WorkflowEngineManagerUtil.getKey() +
					"-tests.zip"));

		ZipEntry zipEntry = null;

		while ((zipEntry = zipInputStream.getNextEntry()) != null) {
			String name = zipEntry.getName();

			if (name.equals(_JAR_1)) {
				_bytes1 = readBytes(zipInputStream);
			}
			else if (name.equals(_JAR_2)) {
				_bytes2 = readBytes(zipInputStream);
			}
			else if (name.equals(_JAR_3)) {
				_bytes3 = readBytes(zipInputStream);
			}
		}

		zipInputStream.close();

		assertNotNull(_bytes1);
		assertNotNull(_bytes2);
		assertNotNull(_bytes3);
	}

	public void testDeployWorkflowDefinition() throws Exception {

		// Test Workflow Definition 1

		WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
			_user.getUserId(), _NAME_1, new ByteArrayInputStream(_bytes1));

		WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
			_user.getUserId(), _NAME_1, new ByteArrayInputStream(_bytes1));

		WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
			_user.getUserId(), _NAME_1, new ByteArrayInputStream(_bytes1));

		// TestWorkflowDefinition2

		WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
			_user.getUserId(), _NAME_2, new ByteArrayInputStream(_bytes2));

		WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
			_user.getUserId(), _NAME_2, new ByteArrayInputStream(_bytes2));

		// TestWorkflowDefinition3

		WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
			_user.getUserId(), _NAME_3, new ByteArrayInputStream(_bytes3));
	}

	public void testGetWorkflowDefinition() throws Exception {

		// TestWorkflowDefinition1 version1

		WorkflowDefinition workflowDefinition1_1 =
			WorkflowDefinitionManagerUtil.getWorkflowDefinition(_NAME_1, 1);
		String name1_1 = workflowDefinition1_1.getName();
		Integer version1_1 = workflowDefinition1_1.getVersion();
		assertEquals(_NAME_1, name1_1);
		assertEquals(1, version1_1);

		// TestWorkflowDefinition1 version2

		WorkflowDefinition workflowDefinition1_2 =
			WorkflowDefinitionManagerUtil.getWorkflowDefinition(_NAME_1, 2);
		String name1_2 = workflowDefinition1_2.getName();
		Integer version1_2 = workflowDefinition1_2.getVersion();
		assertEquals(_NAME_1, name1_2);
		assertEquals(2, version1_2);

		// TestWorkflowDefinition1 version3

		WorkflowDefinition workflowDefinition1_3 =
			WorkflowDefinitionManagerUtil.getWorkflowDefinition(_NAME_1, 3);
		String name1_3 = workflowDefinition1_3.getName();
		Integer version1_3 = workflowDefinition1_3.getVersion();
		assertEquals(_NAME_1, name1_3);
		assertEquals(3, version1_3);

		// TestWorkflowDefinition1 version4

		boolean catchedException = false;
		try{
			WorkflowDefinitionManagerUtil.getWorkflowDefinition(_NAME_1, 4);
		}catch(Exception we) {
			catchedException = true;
		}
		assertTrue(catchedException);
		catchedException = false;

		// TestWorkflowDefinition2 version1

		WorkflowDefinition workflowDefinition2_1 =
			WorkflowDefinitionManagerUtil.getWorkflowDefinition(_NAME_2, 1);
		String name2_1 = workflowDefinition2_1.getName();
		Integer version2_1 = workflowDefinition2_1.getVersion();
		assertEquals(_NAME_2, name2_1);
		assertEquals(1, version2_1);

		// TestWorkflowDefinition2 version2

		WorkflowDefinition workflowDefinition2_2 =
			WorkflowDefinitionManagerUtil.getWorkflowDefinition(_NAME_2, 2);
		String name2_2 = workflowDefinition2_2.getName();
		Integer version2_2 = workflowDefinition2_2.getVersion();
		assertEquals(_NAME_2, name2_2);
		assertEquals(2, version2_2);

		// TestWorkflowDefinition2 version3

		try{
			WorkflowDefinitionManagerUtil.getWorkflowDefinition(_NAME_2, 3);
		}catch(Exception we) {
			catchedException = true;
		}
		assertTrue(catchedException);
		catchedException = false;

		// TestWorkflowDefinition3 version1

		WorkflowDefinition workflowDefinition3_1 =
			WorkflowDefinitionManagerUtil.getWorkflowDefinition(_NAME_3, 1);
		String name3_1 = workflowDefinition3_1.getName();
		Integer version3_1 = workflowDefinition3_1.getVersion();
		assertEquals(_NAME_3, name3_1);
		assertEquals(1, version3_1);

		// TestWorkflowDefinition3 version2

		try{
			WorkflowDefinitionManagerUtil.getWorkflowDefinition(_NAME_3, 2);
		}catch(Exception we) {
			catchedException = true;
		}
		assertTrue(catchedException);
		catchedException = false;

	}

	public void testGetWorkflowDefinitionCount() throws Exception {
		int count = WorkflowDefinitionManagerUtil.getWorkflowDefinitionCount();

		assertEquals(3, count);
	}

	public void testGetWorkflowDefinitionCountByName() throws Exception {
		int count = WorkflowDefinitionManagerUtil.getWorkflowDefinitionCount(
			_NAME_1);

		assertEquals(3, count);

		count = WorkflowDefinitionManagerUtil.getWorkflowDefinitionCount(
			_NAME_2);

		assertEquals(2, count);

		count = WorkflowDefinitionManagerUtil.getWorkflowDefinitionCount(
			_NAME_3);

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

		// TestWorkflowDefinition3

		WorkflowDefinition workflowDefinition3 = workflowDefinitions.get(0);

		String name3 = workflowDefinition3.getName();
		int version3 = workflowDefinition3.getVersion();

		assertEquals(_NAME_3, name3);
		assertEquals(1, version3);

		// TestWorkflowDefinition2

		WorkflowDefinition workflowDefinition2 = workflowDefinitions.get(1);

		String name2 = workflowDefinition2.getName();
		int version2 = workflowDefinition2.getVersion();

		assertEquals(_NAME_2, name2);
		assertEquals(2, version2);

		// Test Workflow Definition 1

		WorkflowDefinition workflowDefinition1 = workflowDefinitions.get(2);

		String name1 = workflowDefinition1.getName();
		int version1 = workflowDefinition1.getVersion();

		assertEquals(_NAME_1, name1);
		assertEquals(3, version1);

		// Get all workflow definitions by range

		workflowDefinitions =
			WorkflowDefinitionManagerUtil.getWorkflowDefinitions(
				0, 2, orderByComparator);

		assertEquals(2, workflowDefinitions.size());

		// TestWorkflowDefinition3

		workflowDefinition3 = workflowDefinitions.get(0);

		name3 = workflowDefinition3.getName();
		version3 = workflowDefinition3.getVersion();

		assertEquals(_NAME_3, name3);
		assertEquals(1, version3);

		// TestWorkflowDefinition2

		workflowDefinition2 = workflowDefinitions.get(1);

		name2 = workflowDefinition2.getName();
		version2 = workflowDefinition2.getVersion();

		assertEquals(_NAME_2, name2);
		assertEquals(2, version2);
	}

	public void testGetWorkflowDefinitionsByName() throws Exception {

		// Get all workflow definitions with name Test Workflow Definition 1

		OrderByComparator orderByComparator =
			new WorkflowDefinitionNameComparator();

		List<WorkflowDefinition> workflowDefinitions =
			WorkflowDefinitionManagerUtil.getWorkflowDefinitions(
				_NAME_1, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				orderByComparator);

		assertEquals(3, workflowDefinitions.size());

		// Version 3

		WorkflowDefinition workflowDefinition1_3 =
			workflowDefinitions.get(0);

		String name1_3 = workflowDefinition1_3.getName();
		int version1_3 = workflowDefinition1_3.getVersion();

		assertEquals(_NAME_1, name1_3);
		assertEquals(3, version1_3);

		// Version 2

		WorkflowDefinition workflowDefinition1_2 =
			workflowDefinitions.get(1);

		String name1_2 = workflowDefinition1_2.getName();
		int version1_2 = workflowDefinition1_2.getVersion();

		assertEquals(_NAME_1, name1_2);
		assertEquals(2, version1_2);

		// Version 1

		WorkflowDefinition workflowDefinition1_1 =
			workflowDefinitions.get(2);

		String name1_1 = workflowDefinition1_1.getName();
		int version1_1 = workflowDefinition1_1.getVersion();

		assertEquals(_NAME_1, name1_1);
		assertEquals(1, version1_1);

		// Get all workflow definitions with name Test Workflow Definition 1 by
		// range

		workflowDefinitions =
			WorkflowDefinitionManagerUtil.getWorkflowDefinitions(
				_NAME_1, 0, 2, orderByComparator);

		assertEquals(2, workflowDefinitions.size());

		// Version 3

		workflowDefinition1_3 = workflowDefinitions.get(0);
		name1_3 = workflowDefinition1_3.getName();
		version1_3 = workflowDefinition1_3.getVersion();

		assertEquals(_NAME_1, name1_3);
		assertEquals(3, version1_3);

		// Version 2

		workflowDefinition1_2 = workflowDefinitions.get(1);

		name1_2 = workflowDefinition1_2.getName();
		version1_2 = workflowDefinition1_2.getVersion();

		assertEquals(_NAME_1, name1_2);
		assertEquals(2, version1_2);
	}

	public void testUndeployWorkflowDefinition() throws Exception {

		// Test Workflow Definition 1

		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			_user.getUserId(), _NAME_1, 1);

		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			_user.getUserId(), _NAME_1, 2);

		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			_user.getUserId(), _NAME_1, 3);

		// TestWorkflowDefinition2

		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			_user.getUserId(), _NAME_2, 1);

		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			_user.getUserId(), _NAME_2, 2);

		// TestWorkflowDefinition3

		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			_user.getUserId(), _NAME_3, 1);

		int count = WorkflowDefinitionManagerUtil.getWorkflowDefinitionCount();

		assertEquals(0, count);
	}

	private static String _JAR_1 = "test1.jar";

	private static String _JAR_2 = "test2.jar";

	private static String _JAR_3 = "test3.jar";

	private static String _NAME_1 = "Test Workflow Definition 1";

	private static String _NAME_2 = "Test Workflow Definition 2";

	private static String _NAME_3 = "Test Workflow Definition 3";

	private byte[] _bytes1;
	private byte[] _bytes2;
	private byte[] _bytes3;
	private User _user;

}