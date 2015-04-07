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

package com.liferay.wsrp.servlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.TransientValue;
import com.liferay.portal.kernel.util.Validator;

import java.util.Enumeration;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import oasis.names.tc.wsrp.v2.intf.WSRP_v2_Markup_PortType;
import oasis.names.tc.wsrp.v2.types.ReleaseSessions;
import oasis.names.tc.wsrp.v2.types.SessionContext;

/**
 * @author Michael Young
 */
public class WSRPSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		HttpSession session = httpSessionEvent.getSession();

		Enumeration<String> enu = session.getAttributeNames();

		while (enu.hasMoreElements()) {
			String name = enu.nextElement();

			Object value = session.getAttribute(name);

			if (value instanceof TransientValue) {
				TransientValue<?> transientValue = ((TransientValue<?>)value);

				releaseSessions(transientValue.getValue());
			}
		}
	}

	protected void releaseSessions(Object value) {
		if ((value == null) || !(value instanceof ServiceHolder)) {
			return;
		}

		ServiceHolder serviceHolder = (ServiceHolder)value;

		SessionContext sessionContext = serviceHolder.getSessionContext();

		if ((sessionContext == null) ||
			Validator.isNull(sessionContext.getSessionID())) {

			return;
		}

		WSRP_v2_Markup_PortType markupService =
			serviceHolder.getMarkupService();

		ReleaseSessions releaseSessions = new ReleaseSessions();

		releaseSessions.setRegistrationContext(
			serviceHolder.getRegistrationContext());

		String[] sessionIDs = new String[] {sessionContext.getSessionID()};

		releaseSessions.setSessionIDs(sessionIDs);

		try {
			markupService.releaseSessions(releaseSessions);
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e.getMessage());
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(WSRPSessionListener.class);

}