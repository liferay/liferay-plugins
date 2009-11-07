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

import com.liferay.portal.kernel.workflow.WorkflowEngineManagerUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * <a href="WorkflowTestCase.java.html"><b><i>View Source</i></b></a>
 *
 * @author Shuyang Zhou
 */
public class WorkflowTestCase extends TestCase {

	public void setUp() throws Exception {
		long companyId = PortalUtil.getDefaultCompanyId();

		user = UserLocalServiceUtil.getDefaultUser(companyId);

		ZipInputStream zipInputStream = new ZipInputStream(
			servletContext.getResourceAsStream(
				"WEB-INF/bundles/" + WorkflowEngineManagerUtil.getKey() +
					"-tests.zip"));

		ZipEntry zipEntry = null;

		while ((zipEntry = zipInputStream.getNextEntry()) != null) {
			String name = zipEntry.getName();

			if (name.equals(_JAR_1)) {
				definitionBytes1 = readBytes(zipInputStream);
			}
			else if (name.equals(_JAR_2)) {
				definitionBytes2 = readBytes(zipInputStream);
			}
			else if (name.equals(_JAR_3)) {
				definitionBytes3 = readBytes(zipInputStream);
			}
			else if (name.equals(_JAR_4)) {
				definitionBytes4 = readBytes(zipInputStream);
			}
		}

		zipInputStream.close();

		assertNotNull(definitionBytes1);
		assertNotNull(definitionBytes2);
		assertNotNull(definitionBytes3);
		assertNotNull(definitionBytes4);

	}

	protected static final String DEFINITION_NAME_1 =
		"Test Workflow Definition 1";

	protected static final String DEFINITION_NAME_2 =
		"Test Workflow Definition 2";

	protected static final String DEFINITION_NAME_3 =
		"Test Workflow Definition 3";

	protected static final String DEFINITION_NAME_4 =
		"Test Workflow Definition 4";

	protected byte[] definitionBytes1;
	protected byte[] definitionBytes2;
	protected byte[] definitionBytes3;
	protected byte[] definitionBytes4;
	protected User user;

	private static final String _JAR_1 = "test1.jar";

	private static final String _JAR_2 = "test2.jar";

	private static final String _JAR_3 = "test3.jar";

	private static final String _JAR_4 = "test4.jar";

}