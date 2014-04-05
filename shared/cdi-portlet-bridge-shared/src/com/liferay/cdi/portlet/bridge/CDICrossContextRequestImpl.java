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

package com.liferay.cdi.portlet.bridge;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Neil Griffin
 */
public class CDICrossContextRequestImpl extends CDICrossContextRequest {

	public CDICrossContextRequestImpl(HttpServletRequest httpServletRequest) {
		super(httpServletRequest);
	}

	@Override
	public HttpSession getSession(boolean create) {
		if (_httpSession == null) {
			HttpServletRequest httpServletRequest = getRequest();

			HttpSession httpSession = new CDICrossContextSessionImpl(
				httpServletRequest.getSession(create));

			if (create) {
				_httpSession = httpSession;
			}

			return httpSession;
		}
		else {
			return _httpSession;
		}
	}

	private HttpSession _httpSession;

}