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

import com.liferay.portal.kernel.workflow.WorkflowEngineManagerUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author Shuyang Zhou
 */
public class WorkflowTestCase extends TestCase {

	public void setUp() throws Exception {
		super.setUp();

		companyId = PortalUtil.getDefaultCompanyId();

		defaultUser = UserLocalServiceUtil.getDefaultUser(companyId);
		defaultUserId = defaultUser.getUserId();

		guestRoleId = defaultUser.getRoleIds()[0];

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

	protected static final String DEFINITION_NAME_1 = "Workflow Definition 1";

	protected static final String DEFINITION_NAME_2 = "Workflow Definition 2";

	protected static final String DEFINITION_NAME_3 = "Workflow Definition 3";

	protected static final String DEFINITION_NAME_4 = "Workflow Definition 4";

	protected long companyId;
	protected byte[] definitionBytes1;
	protected byte[] definitionBytes2;
	protected byte[] definitionBytes3;
	protected byte[] definitionBytes4;
	protected long guestRoleId;
	protected User defaultUser;
	protected long defaultUserId;

	private static final String _JAR_1 = "test1.jar";

	private static final String _JAR_2 = "test2.jar";

	private static final String _JAR_3 = "test3.jar";

	private static final String _JAR_4 = "test4.jar";

}