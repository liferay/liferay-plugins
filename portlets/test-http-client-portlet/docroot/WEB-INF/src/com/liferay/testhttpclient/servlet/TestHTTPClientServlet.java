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

package com.liferay.testhttpclient.servlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 */
public class TestHTTPClientServlet extends HttpServlet {

	@Override
	public void service(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		boolean result = false;

		try {
			String pathInfo = request.getPathInfo();

			if (pathInfo.startsWith(StringPool.SLASH)) {
				pathInfo = pathInfo.substring(1);
			}

			if (pathInfo.equals("testPostFileAndStringParts")) {
				result = testPostFileAndStringParts(request);
			}
			else if (pathInfo.equals("testPostFilePart")) {
				result = testPostFilePart(request);
			}
			else if (pathInfo.equals("testPostFileParts")) {
				result = testPostFileParts(request);
			}
			else if (pathInfo.equals("testPostStringPart")) {
				result = testPostStringPart(request);
			}
			else if (pathInfo.equals("testPostStringParts")) {
				result = testPostStringParts(request);
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		response.setContentType(ContentTypes.TEXT_HTML);

		ServletOutputStream servletOutputStream = response.getOutputStream();

		if (result) {
			servletOutputStream.print("PASSED");
		}
		else {
			servletOutputStream.print("FAILED");
		}

		servletOutputStream.flush();
	}

	protected boolean testPostFileAndStringParts(HttpServletRequest request) {
		String string1 = ParamUtil.getString(request, "string1");

		if (!string1.equals("string1")) {
			return false;
		}

		/*String file1 = ParamUtil.getString(request, "file1");

		if (!file1.equals("file1")) {
			return false;
		}*/

		return true;
	}

	protected boolean testPostFilePart(HttpServletRequest request) {
		return true;
	}

	protected boolean testPostFileParts(HttpServletRequest request) {
		return true;
	}

	protected boolean testPostStringPart(HttpServletRequest request) {
		return true;
	}

	protected boolean testPostStringParts(HttpServletRequest request) {
		return true;
	}

	private static Log _log = LogFactoryUtil.getLog(
		TestHTTPClientServlet.class);

}