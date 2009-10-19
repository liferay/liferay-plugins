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
import com.liferay.portal.model.Company;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

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
		List<Company> companyList = CompanyLocalServiceUtil.getCompanies(0, 1);
		_testUser = UserLocalServiceUtil.getDefaultUser(
			companyList.get(0).getCompanyId());

		String workflowEngineKey =
			WorkflowEngineManagerUtil.getWorkflowEngineKey();
		String testFileName =
			"WEB-INF/" + workflowEngineKey + "-test-workflow.zip";
		ZipInputStream testFileInputStream = new ZipInputStream(
			servletContext.getResourceAsStream(testFileName));

		ZipEntry testFileEntry;
		while ((testFileEntry = testFileInputStream.getNextEntry()) != null) {
			String entryName = testFileEntry.getName();
			if (entryName.equals(TEST_DEFINITION_NAME_1 + ".jar")) {
				_testDefinitionContent1 = readContent(testFileInputStream);
			}
			else if (entryName.equals(TEST_DEFINITION_NAME_2 + ".jar")) {
				_testDefinitionContent2 = readContent(testFileInputStream);
			}
			else if (entryName.equals(TEST_DEFINITION_NAME_3 + ".jar")) {
				_testDefinitionContent3 = readContent(testFileInputStream);
			}
		}

		if (_testDefinitionContent1 == null) {
			throw new Exception("Failed to load "+TEST_DEFINITION_NAME_1);
		}
		if (_testDefinitionContent2 == null) {
			throw new Exception("Failed to load "+TEST_DEFINITION_NAME_2);
		}
		if (_testDefinitionContent3 == null) {
			throw new Exception("Failed to load "+TEST_DEFINITION_NAME_3);
		}
		testFileInputStream.close();
	}

	public void testDeployWorkflowDefinition() throws Exception {
		WorkflowDefinition testDefinition1_1 = buildDeployWorkflowDefinition(
			TEST_DEFINITION_NAME_1, 1, _testDefinitionContent1);
		WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
			testDefinition1_1, _testUser.getUserId(), false, null);
		WorkflowDefinition testDefinition1_2 = buildDeployWorkflowDefinition(
			TEST_DEFINITION_NAME_1, 2, _testDefinitionContent1);
		WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
			testDefinition1_2, _testUser.getUserId(), false, null);
		WorkflowDefinition testDefinition1_3 = buildDeployWorkflowDefinition(
			TEST_DEFINITION_NAME_1, 3, _testDefinitionContent1);
		WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
			testDefinition1_3, _testUser.getUserId(), false, null);

		WorkflowDefinition testDefinition2_1 = buildDeployWorkflowDefinition(
			TEST_DEFINITION_NAME_2, 1, _testDefinitionContent2);
		WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
			testDefinition2_1, _testUser.getUserId(), false, null);
		WorkflowDefinition testDefinition2_2 = buildDeployWorkflowDefinition(
			TEST_DEFINITION_NAME_2, 2, _testDefinitionContent2);
		WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
			testDefinition2_2, _testUser.getUserId(), false, null);

		WorkflowDefinition testDefinition3_1 = buildDeployWorkflowDefinition(
			TEST_DEFINITION_NAME_3, 1, _testDefinitionContent3);
		WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
			testDefinition3_1, _testUser.getUserId(), false, null);
	}

	public void testGetWorkflowDefinitionCount() throws Exception {
		int count = WorkflowDefinitionManagerUtil.getWorkflowDefinitionCount();
		if (count != 3) {
			throw new Exception(
				"There should be 3 latest definitions, but found " + count);
		}
	}

	public void testGetWorkflowDefinitionCountByName() throws Exception {
		int count = WorkflowDefinitionManagerUtil.getWorkflowDefinitionCount(
			TEST_DEFINITION_NAME_1);
		if (count != 3) {
			throw new Exception(
				"There should be 3 definitions, but found " + count);
		}

		count = WorkflowDefinitionManagerUtil.getWorkflowDefinitionCount(
			TEST_DEFINITION_NAME_2);
		if (count != 2) {
			throw new Exception(
				"There should be 2 definitions, but found " + count);
		}

		count = WorkflowDefinitionManagerUtil.getWorkflowDefinitionCount(
			TEST_DEFINITION_NAME_3);
		if (count != 1) {
			throw new Exception(
				"There should be 1 definition, but found " + count);
		}
	}

	public void testGetWorkflowDefinitions() throws Exception {
		OrderByComparator comparator = new WorkflowDefinitionNameComparator();
		List<WorkflowDefinition> definitions =
			WorkflowDefinitionManagerUtil.getWorkflowDefinitions(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, comparator);
		if (definitions.size() != 3) {
			throw new Exception(
				"There should be 3 definitions, but found " +
					definitions.size());
		}
		WorkflowDefinition definition3 = definitions.get(0);
		String definitionName3 = definition3.getWorkflowDefinitionName();
		int definitionVersion3 = definition3.getWorkflowDefinitionVersion();
		if (!definitionName3.equals(TEST_DEFINITION_NAME_3)) {
			throw new Exception(
				"The name of 1/3 workflow definition should be " +
					TEST_DEFINITION_NAME_3 + ", but found " + definitionName3);
		}
		if (definitionVersion3 != 1) {
			throw new Exception(
				"The version of 1/3 workflow definition should be " + 1 +
					", but found " + definitionVersion3);
		}

		WorkflowDefinition definition2 = definitions.get(1);
		String definitionName2 = definition2.getWorkflowDefinitionName();
		int definitionVersion2 = definition2.getWorkflowDefinitionVersion();
		if (!definitionName2.equals(TEST_DEFINITION_NAME_2)) {
			throw new Exception(
				"The name of 2/3 workflow definition should be " +
					TEST_DEFINITION_NAME_2 + ", but found " + definitionName2);
		}
		if (definitionVersion2 != 2) {
			throw new Exception(
				"The version of 2/3 workflow definition should be " + 2 +
					", but found " + definitionVersion2);
		}

		WorkflowDefinition definition1 = definitions.get(2);
		String definitionName1 = definition1.getWorkflowDefinitionName();
		int definitionVersion1 = definition1.getWorkflowDefinitionVersion();
		if (!definitionName1.equals(TEST_DEFINITION_NAME_1)) {
			throw new Exception(
				"The name of 3/3 workflow definition should be " +
					TEST_DEFINITION_NAME_1 + ", but found " + definitionName1);
		}
		if (definitionVersion1 != 3) {
			throw new Exception(
				"The version of 3/3 workflow definition should be " + 3 +
					", but found " + definitionVersion1);
		}

		definitions = WorkflowDefinitionManagerUtil.getWorkflowDefinitions(
			0, 2, comparator);
		if (definitions.size() != 2) {
			throw new Exception(
				"There should be 2 definitions, but found " +
					definitions.size());
		}
		definition3 = definitions.get(0);
		definitionName3 = definition3.getWorkflowDefinitionName();
		definitionVersion3 = definition3.getWorkflowDefinitionVersion();
		if (!definitionName3.equals(TEST_DEFINITION_NAME_3)) {
			throw new Exception(
				"The name of 1/2 workflow definition should be " +
					TEST_DEFINITION_NAME_3 + ", but found " + definitionName3);
		}
		if (definitionVersion3 != 1) {
			throw new Exception(
				"The version of 1/2 workflow definition should be " + 1 +
					", but found " + definitionVersion3);
		}

		definition2 = definitions.get(1);
		definitionName2 = definition2.getWorkflowDefinitionName();
		definitionVersion2 = definition2.getWorkflowDefinitionVersion();
		if (!definitionName2.equals(TEST_DEFINITION_NAME_2)) {
			throw new Exception(
				"The name of 2/2 workflow definition should be " +
					TEST_DEFINITION_NAME_2 + ", but found " + definitionName2);
		}
		if (definitionVersion2 != 2) {
			throw new Exception(
				"The version of 2/2 workflow definition should be " + 2 +
					", but found " + definitionVersion2);
		}

	}

	public void testGetWorkflowDefinitionsByName() throws Exception {

		OrderByComparator comparator = new WorkflowDefinitionNameComparator();
		List<WorkflowDefinition> definitions =
			WorkflowDefinitionManagerUtil.getWorkflowDefinitions(
				TEST_DEFINITION_NAME_1, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				comparator);
		if (definitions.size() != 3) {
			throw new Exception(
				"There should be 3 definitions, but found " +
					definitions.size());
		}
		WorkflowDefinition definition1_3 = definitions.get(0);
		String definitionName1_3 = definition1_3.getWorkflowDefinitionName();
		int definitionVersion1_3 = definition1_3.getWorkflowDefinitionVersion();
		if (!definitionName1_3.equals(TEST_DEFINITION_NAME_1)) {
			throw new Exception(
				"The name of 1/3 workflow definition should be " +
					TEST_DEFINITION_NAME_1 + ", but found " +
						definitionName1_3);
		}
		if (definitionVersion1_3 != 3) {
			throw new Exception(
				"The version of 1/3 workflow definition should be " + 3 +
					", but found " + definitionVersion1_3);
		}

		WorkflowDefinition definition1_2 = definitions.get(1);
		String definitionName1_2 = definition1_2.getWorkflowDefinitionName();
		int definitionVersion1_2 = definition1_2.getWorkflowDefinitionVersion();
		if (!definitionName1_2.equals(TEST_DEFINITION_NAME_1)) {
			throw new Exception(
				"The name of 2/3 workflow definition should be " +
					TEST_DEFINITION_NAME_1 + ", but found " +
						definitionName1_2);
		}
		if (definitionVersion1_2 != 2) {
			throw new Exception(
				"The version of 2/3 workflow definition should be " + 2 +
					", but found " + definitionVersion1_2);
		}

		WorkflowDefinition definition1_1 = definitions.get(2);
		String definitionName1_1 = definition1_1.getWorkflowDefinitionName();
		int definitionVersion1_1 = definition1_1.getWorkflowDefinitionVersion();
		if (!definitionName1_1.equals(TEST_DEFINITION_NAME_1)) {
			throw new Exception(
				"The name of 3/3 workflow definition should be " +
					TEST_DEFINITION_NAME_1 + ", but found " +
						definitionName1_1);
		}
		if (definitionVersion1_1 != 1) {
			throw new Exception(
				"The version of 3/3 workflow definition should be " + 1 +
					", but found " + definitionVersion1_1);
		}

		definitions = WorkflowDefinitionManagerUtil.getWorkflowDefinitions(
			TEST_DEFINITION_NAME_1, 0, 2, comparator);
		if (definitions.size() != 2) {
			throw new Exception(
				"There should be 2 definitions, but found " +
					definitions.size());
		}
		definition1_3 = definitions.get(0);
		definitionName1_3 = definition1_3.getWorkflowDefinitionName();
		definitionVersion1_3 = definition1_3.getWorkflowDefinitionVersion();
		if (!definitionName1_3.equals(TEST_DEFINITION_NAME_1)) {
			throw new Exception(
				"The name of 1/2 workflow definition should be " +
					TEST_DEFINITION_NAME_1 + ", but found " +
						definitionName1_3);
		}
		if (definitionVersion1_3 != 3) {
			throw new Exception(
				"The version of 1/2 workflow definition should be " + 3 +
					", but found " + definitionVersion1_3);
		}

		definition1_2 = definitions.get(1);
		definitionName1_2 = definition1_2.getWorkflowDefinitionName();
		definitionVersion1_2 = definition1_2.getWorkflowDefinitionVersion();
		if (!definitionName1_2.equals(TEST_DEFINITION_NAME_1)) {
			throw new Exception(
				"The name of 2/2 workflow definition should be " +
					TEST_DEFINITION_NAME_1 + ", but found " +
						definitionName1_2);
		}
		if (definitionVersion1_2 != 2) {
			throw new Exception(
				"The version of 2/2 workflow definition should be " + 2 +
					", but found " + definitionVersion1_2);
		}

	}

	public void testUndeployWorkflowDefinition() throws Exception {
		WorkflowDefinition testDefinition1_1 = buildDeployWorkflowDefinition(
			TEST_DEFINITION_NAME_1, 1, null);
		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			testDefinition1_1, _testUser.getUserId(), null);
		WorkflowDefinition testDefinition1_2 = buildDeployWorkflowDefinition(
			TEST_DEFINITION_NAME_1, 2, null);
		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			testDefinition1_2, _testUser.getUserId(), null);
		WorkflowDefinition testDefinition1_3 = buildDeployWorkflowDefinition(
			TEST_DEFINITION_NAME_1, 3, null);
		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			testDefinition1_3, _testUser.getUserId(), null);

		WorkflowDefinition testDefinition2_1 = buildDeployWorkflowDefinition(
			TEST_DEFINITION_NAME_2, 1, null);
		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			testDefinition2_1, _testUser.getUserId(), null);
		WorkflowDefinition testDefinition2_2 = buildDeployWorkflowDefinition(
			TEST_DEFINITION_NAME_2, 2, null);
		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			testDefinition2_2, _testUser.getUserId(), null);

		WorkflowDefinition testDefinition3_1 = buildDeployWorkflowDefinition(
			TEST_DEFINITION_NAME_3, 1, null);
		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			testDefinition3_1, _testUser.getUserId(), null);

		int count = WorkflowDefinitionManagerUtil.getWorkflowDefinitionCount();
		if (count != 0) {
			throw new Exception(
				"There should be 0 definitions, but found " + count);
		}
	}

	private WorkflowDefinition buildDeployWorkflowDefinition(
		String workflowDefinitionName, int workflowDefinitionVersion,
		byte[] workflowDefinitionContent) {
		ResourceRetriever contentRetriever = null;
		if (workflowDefinitionContent != null) {
			contentRetriever =
				new ByteArrayResourceRetriever(workflowDefinitionContent);
		}
		return new DefaultWorkflowDefinition(
			contentRetriever, workflowDefinitionName, workflowDefinitionVersion,
			null);

	}

	private byte[] readContent(InputStream inputStream) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int b = -1;
		while ((b = inputStream.read()) != -1) {
			baos.write(b);
		}
		baos.close();
		return baos.toByteArray();
	}

	private static String TEST_DEFINITION_NAME_1 = "TestWorkflowDefinition1";
	private static String TEST_DEFINITION_NAME_2 = "TestWorkflowDefinition2";
	private static String TEST_DEFINITION_NAME_3 = "TestWorkflowDefinition3";
	private byte[] _testDefinitionContent1;
	private byte[] _testDefinitionContent2;
	private byte[] _testDefinitionContent3;
	private User _testUser;

}