/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.testpacl.servlet;

import java.io.File;

import javax.servlet.http.HttpServlet;

/**
 * @author Brian Wing Shun Chan
 */
public class TestPACLServlet extends HttpServlet {

	public TestPACLServlet() {
		testWriteFile();
	}

	@Override
	public void destroy() {
		testWriteFile();
	}

	@Override
	public void init() {
		testWriteFile();
	}

	protected void testWriteFile() {
		File file = new File("../webapps/chat-portlet/css/main.css");

		try {
			file.exists();

			throw new RuntimeException("File is not protected");
		}
		catch (SecurityException se) {
		}
	}

}