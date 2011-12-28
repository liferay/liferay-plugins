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

package com.liferay.samplejsfmyfaces.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.URLEncoder;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 */
public class LogURLEncoder implements URLEncoder {

	public String encodeURL(HttpServletResponse res, String path) {
		if (_log.isInfoEnabled()) {
			_log.info("Path " + path);
		}

		String encodedURL = res.encodeURL(path);

		if (_log.isInfoEnabled()) {
			_log.info("Encoded URL " + encodedURL);
		}

		return encodedURL;
	}

	private static Log _log = LogFactoryUtil.getLog(LogURLEncoder.class);

}