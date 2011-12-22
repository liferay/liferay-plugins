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

package com.liferay.wsrp.servlet;

import oasis.names.tc.wsrp.v2.intf.WSRP_v2_Markup_PortType;
import oasis.names.tc.wsrp.v2.types.RegistrationContext;
import oasis.names.tc.wsrp.v2.types.SessionContext;

/**
 * @author Michael Young
 */
public class ServiceHolder {

	public WSRP_v2_Markup_PortType getMarkupService() {
		return _markupService;
	}

	public RegistrationContext getRegistrationContext() {
		return _registrationContext;
	}

	public SessionContext getSessionContext() {
		return _sessionContext;
	}

	public void setMarkupService(WSRP_v2_Markup_PortType markupService) {
		_markupService = markupService;
	}

	public void setRegistrationContext(
		RegistrationContext registrationContext) {

		_registrationContext = registrationContext;
	}

	public void setSessionContext(SessionContext sessionContext) {
		_sessionContext = sessionContext;
	}

	private WSRP_v2_Markup_PortType _markupService;
	private RegistrationContext _registrationContext;
	private SessionContext _sessionContext;

}