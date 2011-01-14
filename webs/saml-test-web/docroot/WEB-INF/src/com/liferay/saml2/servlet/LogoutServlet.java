/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.saml2.servlet;

import com.liferay.saml2.session.SPSession;
import com.liferay.saml2.session.SessionManager;
import com.liferay.saml2.session.SessionManagerFactory;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		HttpSession session = request.getSession();

		SPSession spSession = sessionManager.findBySessionId(session.getId());

		if (spSession != null) {
			sessionManager.invalidate(spSession);
		}

		session.invalidate();

		response.sendRedirect(request.getContextPath());
	}

	private static SessionManager sessionManager = SessionManagerFactory.getSessionManager();
}
