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
 * <a href="BaseWorkflowTestCase.java.html"><b><i>View Source</i></b></a>
 *
 * @author Shuyang Zhou
 */
public class BaseWorkflowTestCase extends BaseTestCase {

	public static void setUpClass() throws Exception {

		if (SERVLET_CONTEXT == null) {
			throw new IllegalArgumentException("ServletContext is null");
		}

		long companyId = PortalUtil.getDefaultCompanyId();

		USER = UserLocalServiceUtil.getDefaultUser(companyId);

		ZipInputStream zipInputStream = new ZipInputStream(
			SERVLET_CONTEXT.getResourceAsStream(
				"WEB-INF/bundles/" + WorkflowEngineManagerUtil.getKey() +
					"-tests.zip"));

		ZipEntry zipEntry = null;

		while ((zipEntry = zipInputStream.getNextEntry()) != null) {
			String name = zipEntry.getName();

			if (name.equals(_JAR_1)) {
				BYTES_1 = readBytes(zipInputStream);
			}
			else if (name.equals(_JAR_2)) {
				BYTES_2 = readBytes(zipInputStream);
			}
			else if (name.equals(_JAR_3)) {
				BYTES_3 = readBytes(zipInputStream);
			}
			else if (name.equals(_JAR_4)) {
				BYTES_4 = readBytes(zipInputStream);
			}
		}

		zipInputStream.close();

		assertNotNull(BYTES_1);
		assertNotNull(BYTES_2);
		assertNotNull(BYTES_3);
		assertNotNull(BYTES_4);

	}

	public static final String NAME_1 = "Test Workflow Definition 1";
	public static final String NAME_2 = "Test Workflow Definition 2";
	public static final String NAME_3 = "Test Workflow Definition 3";
	public static final String NAME_4 = "Test Workflow Definition 4";

	protected static byte[] BYTES_1;
	protected static byte[] BYTES_2;
	protected static byte[] BYTES_3;
	protected static byte[] BYTES_4;
	protected static User USER;

	private static final String _JAR_1 = "test1.jar";
	private static final String _JAR_2 = "test2.jar";
	private static final String _JAR_3 = "test3.jar";
	private static final String _JAR_4 = "test4.jar";

}