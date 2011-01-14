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
public interface SessionManager {

	public void destroySession(String sessionId)
		throws PortalException, SystemException;

	public List<String> getActiveSPs(String sessionId)
		throws PortalException, SystemException;

	public boolean isExpired(String sessionId)
		throws PortalException, SystemException;

	public void registerSession(
			String sessionId, String issuer, ServiceContext serviceContext)
		throws PortalException, SystemException;

}
