/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.httpservice.internal.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;

import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.http.HttpContext;

/**
 * @author Raymond Augé
 * @author Miguel Pastor
 */
public class PortalHttpContext implements HttpContext {

	public PortalHttpContext(ServletContext servletContext) {
		_servletContext = servletContext;
	}

	@Override
	public String getMimeType(String name) {
		String mimeType = _servletContext.getMimeType(name);

		if (mimeType == null) {
			mimeType = MimeTypesUtil.getContentType(name);
		}

		return mimeType;
	}

	@Override
	public URL getResource(String path) {
		try {
			return _servletContext.getResource(path);
		}
		catch (MalformedURLException mue) {
			_log.error(mue, mue);
		}

		return null;
	}

	@Override
	public boolean handleSecurity(
		HttpServletRequest request, HttpServletResponse response) {

		return true;
	}

	private static Log _log = LogFactoryUtil.getLog(PortalHttpContext.class);

	private ServletContext _servletContext;

}