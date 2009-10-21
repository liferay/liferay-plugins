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

package com.liferay.portlet.workflow.test;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.resource.ByteArrayResourceRetriever;
import com.liferay.portal.kernel.resource.ResourceRetriever;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.DefaultWorkflowDefinition;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.kernel.workflow.WorkflowDefinitionManagerUtil;
import com.liferay.portal.kernel.workflow.WorkflowEngineManagerUtil;
import com.liferay.portal.kernel.workflow.comparator.WorkflowDefinitionNameComparator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

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

		String workflowEngineKey =
			WorkflowEngineManagerUtil.getWorkflowEngineKey();

		ZipInputStream zipInputStream = new ZipInputStream(
			servletContext.getResourceAsStream(
				"WEB-INF/tests/" + workflowEngineKey + "-web.zip"));

		ZipEntry zipEntry = null;

		while ((zipEntry = zipInputStream.getNextEntry()) != null) {
			String name = zipEntry.getName();

			if (name.equals(_WORKFLOW_DEFINITION_NAME_1 + ".jar")) {
				_workflowDefinitionContent1 = readContent(zipInputStream);
			}
			else if (name.equals(_WORKFLOW_DEFINITION_NAME_2 + ".jar")) {
				_workflowDefinitionContent2 = readContent(zipInputStream);
			}
			else if (name.equals(_WORKFLOW_DEFINITION_NAME_3 + ".jar")) {
				_workflowDefinitionContent3 = readContent(zipInputStream);
			}
		}

		zipInputStream.close();

		assertNotNull(_workflowDefinitionContent1);
		assertNotNull(_workflowDefinitionContent2);
		assertNotNull(_workflowDefinitionContent3);
	}

	public void testDeployWorkflowDefinition() throws Exception {

		// TestWorkflowDefinition1

		WorkflowDefinition workflowDefinition1_1 = _getWorkflowDefinition(
			_WORKFLOW_DEFINITION_NAME_1, 1, _workflowDefinitionContent1);

		WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
			workflowDefinition1_1, _user.getUserId(), false, null);

		WorkflowDefinition testDefinition1_2 = _getWorkflowDefinition(
			_WORKFLOW_DEFINITION_NAME_1, 2, _workflowDefinitionContent1);

		WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
			testDefinition1_2, _user.getUserId(), false, null);

		WorkflowDefinition testDefinition1_3 = _getWorkflowDefinition(
			_WORKFLOW_DEFINITION_NAME_1, 3, _workflowDefinitionContent1);

		WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
			testDefinition1_3, _user.getUserId(), false, null);

		// TestWorkflowDefinition2

		WorkflowDefinition testDefinition2_1 = _getWorkflowDefinition(
			_WORKFLOW_DEFINITION_NAME_2, 1, _workflowDefinitionContent2);

		WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
			testDefinition2_1, _user.getUserId(), false, null);

		WorkflowDefinition testDefinition2_2 = _getWorkflowDefinition(
			_WORKFLOW_DEFINITION_NAME_2, 2, _workflowDefinitionContent2);

		WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
			testDefinition2_2, _user.getUserId(), false, null);

		// TestWorkflowDefinition3

		WorkflowDefinition testDefinition3_1 = _getWorkflowDefinition(
			_WORKFLOW_DEFINITION_NAME_3, 1, _workflowDefinitionContent3);

		WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
			testDefinition3_1, _user.getUserId(), false, null);
	}

	public void testGetWorkflowDefinitionCount() throws Exception {
		int count = WorkflowDefinitionManagerUtil.getWorkflowDefinitionCount();

		assertEquals(3, count);
	}

	public void testGetWorkflowDefinitionCountByName() throws Exception {
		int count = WorkflowDefinitionManagerUtil.getWorkflowDefinitionCount(
			_WORKFLOW_DEFINITION_NAME_1);

		assertEquals(3, count);

		count = WorkflowDefinitionManagerUtil.getWorkflowDefinitionCount(
			_WORKFLOW_DEFINITION_NAME_2);

		assertEquals(2, count);

		count = WorkflowDefinitionManagerUtil.getWorkflowDefinitionCount(
			_WORKFLOW_DEFINITION_NAME_3);

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

		String workflowDefinitionName3 =
			workflowDefinition3.getWorkflowDefinitionName();
		int workflowDefinitionVersion3 =
			workflowDefinition3.getWorkflowDefinitionVersion();

		assertEquals(_WORKFLOW_DEFINITION_NAME_3, workflowDefinitionName3);
		assertEquals(1, workflowDefinitionVersion3);

		// TestWorkflowDefinition2

		WorkflowDefinition workflowDefinition2 = workflowDefinitions.get(1);

		String workflowDefinitionName2 =
			workflowDefinition2.getWorkflowDefinitionName();
		int workflowDefinitionVersion2 =
			workflowDefinition2.getWorkflowDefinitionVersion();

		assertEquals(_WORKFLOW_DEFINITION_NAME_2, workflowDefinitionName2);
		assertEquals(2, workflowDefinitionVersion2);

		// TestWorkflowDefinition1

		WorkflowDefinition workflowDefinition1 = workflowDefinitions.get(2);

		String workflowDefinitionName1 =
			workflowDefinition1.getWorkflowDefinitionName();
		int workflowDefinitionVersion1 =
			workflowDefinition1.getWorkflowDefinitionVersion();

		assertEquals(_WORKFLOW_DEFINITION_NAME_1, workflowDefinitionName1);
		assertEquals(3, workflowDefinitionVersion1);

		// Get all workflow definitions by range

		workflowDefinitions =
			WorkflowDefinitionManagerUtil.getWorkflowDefinitions(
				0, 2, orderByComparator);

		assertEquals(2, workflowDefinitions.size());

		// TestWorkflowDefinition3

		workflowDefinition3 = workflowDefinitions.get(0);

		workflowDefinitionName3 =
			workflowDefinition3.getWorkflowDefinitionName();
		workflowDefinitionVersion3 =
			workflowDefinition3.getWorkflowDefinitionVersion();

		assertEquals(_WORKFLOW_DEFINITION_NAME_3, workflowDefinitionName3);
		assertEquals(1, workflowDefinitionVersion3);

		// TestWorkflowDefinition2

		workflowDefinition2 = workflowDefinitions.get(1);

		workflowDefinitionName2 =
			workflowDefinition2.getWorkflowDefinitionName();
		workflowDefinitionVersion2 =
			workflowDefinition2.getWorkflowDefinitionVersion();

		assertEquals(_WORKFLOW_DEFINITION_NAME_2, workflowDefinitionName2);
		assertEquals(2, workflowDefinitionVersion2);
	}

	public void testGetWorkflowDefinitionsByName() throws Exception {

		// Get all workflow definitions with name TestWorkflowDefinition1

		OrderByComparator orderByComparator =
			new WorkflowDefinitionNameComparator();

		List<WorkflowDefinition> workflowDefinitions =
			WorkflowDefinitionManagerUtil.getWorkflowDefinitions(
				_WORKFLOW_DEFINITION_NAME_1, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, orderByComparator);

		assertEquals(3, workflowDefinitions.size());

		// Version 3

		WorkflowDefinition workflowDefinition1_3 =
			workflowDefinitions.get(0);

		String workflowDefinitionName1_3 =
			workflowDefinition1_3.getWorkflowDefinitionName();
		int workflowDefinitionVersion1_3 =
			workflowDefinition1_3.getWorkflowDefinitionVersion();

		assertEquals(_WORKFLOW_DEFINITION_NAME_1, workflowDefinitionName1_3);
		assertEquals(3, workflowDefinitionVersion1_3);

		// Version 2

		WorkflowDefinition workflowDefinition1_2 =
			workflowDefinitions.get(1);

		String workflowDefinitionName1_2 =
			workflowDefinition1_2.getWorkflowDefinitionName();
		int workflowDefinitionVersion1_2 =
			workflowDefinition1_2.getWorkflowDefinitionVersion();

		assertEquals(_WORKFLOW_DEFINITION_NAME_1, workflowDefinitionName1_2);
		assertEquals(2, workflowDefinitionVersion1_2);

		// Version 1

		WorkflowDefinition workflowDefinition1_1 =
			workflowDefinitions.get(2);

		String workflowDefinitionName1_1 =
			workflowDefinition1_1.getWorkflowDefinitionName();
		int workflowDefinitionVersion1_1 =
			workflowDefinition1_1.getWorkflowDefinitionVersion();

		assertEquals(_WORKFLOW_DEFINITION_NAME_1, workflowDefinitionName1_1);
		assertEquals(1, workflowDefinitionVersion1_1);

		// Get all workflow definitions with name TestWorkflowDefinition1 by
		// range

		workflowDefinitions =
			WorkflowDefinitionManagerUtil.getWorkflowDefinitions(
				_WORKFLOW_DEFINITION_NAME_1, 0, 2, orderByComparator);

		assertEquals(2, workflowDefinitions.size());

		// Version 3

		workflowDefinition1_3 = workflowDefinitions.get(0);
		workflowDefinitionName1_3 =
			workflowDefinition1_3.getWorkflowDefinitionName();
		workflowDefinitionVersion1_3 =
			workflowDefinition1_3.getWorkflowDefinitionVersion();

		assertEquals(_WORKFLOW_DEFINITION_NAME_1, workflowDefinitionName1_3);
		assertEquals(3, workflowDefinitionVersion1_3);

		// Version 2

		workflowDefinition1_2 = workflowDefinitions.get(1);

		workflowDefinitionName1_2 =
			workflowDefinition1_2.getWorkflowDefinitionName();
		workflowDefinitionVersion1_2 =
			workflowDefinition1_2.getWorkflowDefinitionVersion();

		assertEquals(_WORKFLOW_DEFINITION_NAME_1, workflowDefinitionName1_2);
		assertEquals(2, workflowDefinitionVersion1_2);
	}

	public void testUndeployWorkflowDefinition() throws Exception {

		// TestWorkflowDefinition1

		WorkflowDefinition testDefinition1_1 = _getWorkflowDefinition(
			_WORKFLOW_DEFINITION_NAME_1, 1, null);

		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			testDefinition1_1, _user.getUserId(), null);

		WorkflowDefinition testDefinition1_2 = _getWorkflowDefinition(
			_WORKFLOW_DEFINITION_NAME_1, 2, null);

		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			testDefinition1_2, _user.getUserId(), null);

		WorkflowDefinition testDefinition1_3 = _getWorkflowDefinition(
			_WORKFLOW_DEFINITION_NAME_1, 3, null);

		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			testDefinition1_3, _user.getUserId(), null);

		// TestWorkflowDefinition2

		WorkflowDefinition testDefinition2_1 = _getWorkflowDefinition(
			_WORKFLOW_DEFINITION_NAME_2, 1, null);

		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			testDefinition2_1, _user.getUserId(), null);

		WorkflowDefinition testDefinition2_2 = _getWorkflowDefinition(
			_WORKFLOW_DEFINITION_NAME_2, 2, null);

		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			testDefinition2_2, _user.getUserId(), null);

		// TestWorkflowDefinition3

		WorkflowDefinition testDefinition3_1 = _getWorkflowDefinition(
			_WORKFLOW_DEFINITION_NAME_3, 1, null);

		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			testDefinition3_1, _user.getUserId(), null);

		int count = WorkflowDefinitionManagerUtil.getWorkflowDefinitionCount();

		assertEquals(0, count);
	}

	private WorkflowDefinition _getWorkflowDefinition(
		String workflowDefinitionName, int workflowDefinitionVersion,
		byte[] workflowDefinitionContent) {

		ResourceRetriever resourceRetriever = null;

		if (workflowDefinitionContent != null) {
			resourceRetriever = new ByteArrayResourceRetriever(
				workflowDefinitionContent);
		}

		return new DefaultWorkflowDefinition(
			resourceRetriever, workflowDefinitionName,
			workflowDefinitionVersion, null);
	}

	private static String _WORKFLOW_DEFINITION_NAME_1 =
		"TestWorkflowDefinition1";

	private static String _WORKFLOW_DEFINITION_NAME_2 =
		"TestWorkflowDefinition2";

	private static String _WORKFLOW_DEFINITION_NAME_3 =
		"TestWorkflowDefinition3";

	private byte[] _workflowDefinitionContent1;
	private byte[] _workflowDefinitionContent2;
	private byte[] _workflowDefinitionContent3;
	private User _user;

}