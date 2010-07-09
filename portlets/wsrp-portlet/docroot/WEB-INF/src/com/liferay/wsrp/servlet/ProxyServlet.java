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

package com.liferay.wsrp.servlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.util.servlet.ServletResponseUtil;
import com.liferay.wsrp.util.WebKeys;

import java.io.IOException;

import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Brian Wing Shun Chan
 *
 */
public class ProxyServlet extends HttpServlet {

	public void service(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		try {
			String url = ParamUtil.getString(request, "url");

			proxyURL(request, response, new URL(url));
		}
		catch (Exception e) {
			_log.error(e, e);

			response.sendError(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	protected void proxyURL(
			HttpServletRequest request, HttpServletResponse response, URL url)
		throws Exception {

		HttpSession session = request.getSession();

		URLConnection urlConnection = url.openConnection();

		String cookie = (String)session.getAttribute(WebKeys.COOKIE);

		if (cookie != null) {
			urlConnection.setRequestProperty(HttpHeaders.COOKIE, cookie);
		}

		urlConnection.connect();

		response.setContentLength(urlConnection.getContentLength());
		response.setContentType(urlConnection.getContentType());

		ServletResponseUtil.write(response, urlConnection.getInputStream());
	}

	private static Log _log = LogFactoryUtil.getLog(ProxyServlet.class);

}