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

import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.net.URL;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.framework.Bundle;
import org.osgi.service.http.HttpContext;

/**
 * @author Raymond Augé
 * @author Miguel Pastor
 */
public class DefaultHttpContext implements HttpContext {

	public DefaultHttpContext(Bundle bundle) {
		_bundle = bundle;
	}

	@Override
	public String getMimeType(String name) {
		return MimeTypesUtil.getContentType(name);
	}

	@Override
	public URL getResource(String path) {
		if (!path.startsWith(StringPool.SLASH)) {
			path = StringPool.SLASH.concat(path);
		}

		URL url = null;

		if (!path.startsWith(_PATH_EXT)) {
			url = getResource(_PATH_EXT.concat(path));

			if (url != null) {
				return url;
			}
		}

		url = _bundle.getResource(path);

		if (url != null) {
			return url;
		}

		String filePattern = path;

		int index = path.lastIndexOf(StringPool.SLASH);

		if (index != -1) {
			filePattern = path.substring(index + 1);

			path = path.substring(0, index);
		}

		Enumeration<URL> enumeration = _bundle.findEntries(
			path, filePattern, false);

		if ((enumeration != null) && enumeration.hasMoreElements()) {
			return enumeration.nextElement();
		}

		return null;
	}

	@Override
	public boolean handleSecurity(
		HttpServletRequest request, HttpServletResponse response) {

		return true;
	}

	private static final String _PATH_EXT = "/ext";

	private Bundle _bundle;

}