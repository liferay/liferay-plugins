/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.opensocial.shindig.servlet;

import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.IOException;

import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Dennis Ju
 */
public class JsServlet extends org.apache.shindig.gadgets.servlet.JsServlet {

	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		String requestURI = request.getRequestURI();

		if (!requestURI.equals("/combo")) {
			super.doGet(request, response);

			return;
		}

		StringBundler sb = new StringBundler(3);

		sb.append(
			request.getAttribute(
				JavaConstants.JAVAX_SERVLET_INCLUDE_REQUEST_URI));
		sb.append(CharPool.QUESTION);
		sb.append(
			request.getAttribute(
				JavaConstants.JAVAX_SERVLET_INCLUDE_QUERY_STRING));

		String urlString = PortalUtil.getAbsoluteURL(request, sb.toString());

		URL url = new URL(urlString);

		URLConnection urlConnection = url.openConnection();

		ServletResponseUtil.write(response, urlConnection.getInputStream());
	}

}