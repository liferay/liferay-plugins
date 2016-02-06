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

package com.liferay.samplehibernate.servlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.samplehibernate.FoodItemComponentImpl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Charles May
 */
public class HibernateServlet extends HttpServlet {

	@Override
	public void service(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		String contentType = request.getHeader(HttpHeaders.CONTENT_TYPE);

		if (_log.isDebugEnabled()) {
			_log.debug("Content type " + contentType);
		}

		if ((contentType != null) &&
			contentType.startsWith(ContentTypes.MULTIPART_FORM_DATA)) {

			request = PortalUtil.getUploadServletRequest(request);
		}

		FoodItemComponentImpl foodItemComponentImpl =
			new FoodItemComponentImpl();

		String result = foodItemComponentImpl.process(request);

		response.setContentType("text/xml");

		try {
			ServletResponseUtil.write(response, result);
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(HibernateServlet.class);

}