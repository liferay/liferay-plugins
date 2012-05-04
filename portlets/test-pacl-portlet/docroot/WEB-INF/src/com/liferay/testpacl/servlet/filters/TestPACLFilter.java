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

package com.liferay.testpacl.servlet.filters;

import java.io.File;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author Brian Wing Shun Chan
 */
public class TestPACLFilter implements Filter {

	public TestPACLFilter() {
		testWriteFile();
	}

	public void destroy() {
		testWriteFile();
	}

	public void doFilter(
			ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain)
		throws IOException, ServletException {

		testWriteFile();

		filterChain.doFilter(servletRequest, servletResponse);
	}

	public void init(FilterConfig filterConfig) {
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