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

package com.liferay.portal.saml.session;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * @author Mika Koivisto
 */
public class SessionManagerUtil {

	public SessionManager getSessionManager() {
		return _sessionManager;
	}

	public static void destroySession(String sessionId)
		throws PortalException, SystemException {

		_sessionManager.destroySession(sessionId);
	}

	public static List<String> getActiveSPs(String sessionId)
		throws PortalException, SystemException {

		return _sessionManager.getActiveSPs(sessionId);
	}

	public static boolean isExpired(String sessionId)
		throws PortalException, SystemException {

		return _sessionManager.isExpired(sessionId);
	}

	public static void registerSession(
			String sessionId, String issuer, ServiceContext serviceContext)
		throws PortalException, SystemException {

		_sessionManager.registerSession(sessionId, issuer, serviceContext);
	}

	public void setSessionManager(SessionManager sessionManager) {
		_sessionManager = sessionManager;
	}

	private static SessionManager _sessionManager;
}
