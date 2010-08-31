/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.vldap.server.handler;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.apache.directory.shared.ldap.message.internal.InternalRequest;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class DispatchIoHandler implements IoHandler {

	public DispatchIoHandler() {
		_bindLdapHandler = new UnbindLdapHandler();
		_compareLdapHandler = new UnbindLdapHandler();
		_extendedLdapHandler = new UnbindLdapHandler();
		_searchLdapHandler = new UnbindLdapHandler();
		_unbindLdapHandler = new UnbindLdapHandler();
	}

	public void exceptionCaught(IoSession ioSession, Throwable cause) {
	}

	public void messageReceived(IoSession ioSession, Object message) {
		InternalRequest internalRequest = (InternalRequest)message;

		LdapHandler ldapHandler = getLdapHandler(internalRequest);

		if (ldapHandler != null) {
			LdapHandlerContext ldapHandlerContext = getLdapHandlerContext(
				ioSession);

			try {
				ldapHandler.messageReceived(
					internalRequest, ioSession, ldapHandlerContext);
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		else {
			if (_log.isWarnEnabled()) {
				_log.warn(internalRequest.getType() + " is not supported");
			}
		}
	}

	public void messageSent(IoSession ioSession, Object message) {
	}

	public void sessionClosed(IoSession ioSession) {
	}

	public void sessionCreated(IoSession ioSession) {
	}

	public void sessionIdle(IoSession ioSession, IdleStatus idleStatus) {
	}

	public void sessionOpened(IoSession ioSession) {
	}

	protected LdapHandler getLdapHandler(InternalRequest internalRequest) {
		return _bindLdapHandler;
	}

	protected LdapHandlerContext getLdapHandlerContext(IoSession ioSession) {
		LdapHandlerContext ldapHandlerContext =
			(LdapHandlerContext)ioSession.getAttribute(
				LdapHandlerContext.class.getName());

		if (ldapHandlerContext == null) {
			synchronized (ioSession) {
				if (ldapHandlerContext == null) {
					ldapHandlerContext = new LdapHandlerContext();

					ioSession.setAttribute(
						LdapHandlerContext.class.getName(), ldapHandlerContext);
				}
			}
		}

		return ldapHandlerContext;
	}

	private static Log _log = LogFactoryUtil.getLog(DispatchIoHandler.class);

	private LdapHandler _bindLdapHandler;
	private LdapHandler _compareLdapHandler;
	private LdapHandler _extendedLdapHandler;
	private LdapHandler _searchLdapHandler;
	private LdapHandler _unbindLdapHandler;

}