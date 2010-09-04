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

package com.liferay.vldap.server;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.vldap.server.handler.AbandonLdapHandler;
import com.liferay.vldap.server.handler.BindLdapHandler;
import com.liferay.vldap.server.handler.CompareLdapHandler;
import com.liferay.vldap.server.handler.ExtendedLdapHandler;
import com.liferay.vldap.server.handler.LdapHandler;
import com.liferay.vldap.server.handler.SearchLdapHandler;
import com.liferay.vldap.server.handler.UnbindLdapHandler;
import com.liferay.vldap.server.handler.util.LdapHandlerContext;
import com.liferay.vldap.util.VLDAPConstants;

import java.util.List;
import java.util.Map;

import org.apache.directory.shared.ldap.codec.MessageTypeEnum;
import org.apache.directory.shared.ldap.message.internal.InternalRequest;
import org.apache.directory.shared.ldap.message.internal.InternalResponse;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class DispatchIoHandler implements IoHandler {

	public void exceptionCaught(IoSession ioSession, Throwable cause) {
	}

	public void messageReceived(IoSession ioSession, Object message) {
		InternalRequest internalRequest = (InternalRequest)message;

		LdapHandler ldapHandler = getLdapHandler(internalRequest);

		if (ldapHandler != null) {
			try {
				LdapHandlerContext ldapHandlerContext = getLdapHandlerContext(
					ioSession);

				List<InternalResponse> internalResposes =
					ldapHandler.messageReceived(
						internalRequest, ioSession, ldapHandlerContext);

				writeResponses(internalResposes, ioSession);
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

	public void setVLDAPServer(VLDAPServer vldapServer) {
		_vldapServer = vldapServer;
	}

	protected LdapHandler getLdapHandler(InternalRequest internalRequest) {
		MessageTypeEnum messageTypeEnum = internalRequest.getType();

		if (messageTypeEnum == MessageTypeEnum.ABANDON_REQUEST) {
			return _abandonLdapHandler;
		}
		else if (messageTypeEnum == MessageTypeEnum.BIND_REQUEST) {
			return _bindLdapHandler;
		}
		else if (messageTypeEnum == MessageTypeEnum.COMPARE_REQUEST) {
			return _compareLdapHandler;
		}
		else if (messageTypeEnum == MessageTypeEnum.EXTENDED_REQUEST) {
			return _extendedLdapHandler;
		}
		else if (messageTypeEnum == MessageTypeEnum.SEARCH_REQUEST) {
			return _searchLdapHandler;
		}
		else if (messageTypeEnum == MessageTypeEnum.UNBIND_REQUEST) {
			return _unbindLdapHandler;
		}

		return null;
	}

	protected LdapHandlerContext getLdapHandlerContext(IoSession ioSession)
		throws Exception {

		LdapHandlerContext ldapHandlerContext =
			(LdapHandlerContext)ioSession.getAttribute(
				LdapHandlerContext.class.getName());

		if (ldapHandlerContext == null) {
			synchronized (ioSession) {
				if (ldapHandlerContext == null) {
					ldapHandlerContext = new LdapHandlerContext();

					ldapHandlerContext.setSchemaManager(
						_vldapServer.getSchemaManager());

					ioSession.setAttribute(
						LdapHandlerContext.class.getName(), ldapHandlerContext);
				}
			}
		}

		return ldapHandlerContext;
	}

	protected void setSessionAttributes(
		InternalResponse internalRespose, IoSession ioSession) {

		Map<Object, Object> sessionAttributes =
			(Map<Object, Object>)internalRespose.get(
				VLDAPConstants.SESSION_ATTRIBUTES);

		if (sessionAttributes == null) {
			return;
		}

		for (Map.Entry<Object, Object> entry : sessionAttributes.entrySet()) {
			Object key = entry.getKey();
			Object value = entry.getValue();

			ioSession.setAttribute(key, value);
		}
	}

	protected void writeResponses(
		List<InternalResponse> internalResposes, IoSession ioSession) {

		if (internalResposes == null) {
			return;
		}

		for (InternalResponse internalRespose : internalResposes) {
			setSessionAttributes(internalRespose, ioSession);

			ioSession.write(internalRespose);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(DispatchIoHandler.class);

	private LdapHandler _abandonLdapHandler = new AbandonLdapHandler();
	private LdapHandler _bindLdapHandler = new BindLdapHandler();
	private LdapHandler _compareLdapHandler = new CompareLdapHandler();
	private LdapHandler _extendedLdapHandler = new ExtendedLdapHandler();
	private LdapHandler _searchLdapHandler = new SearchLdapHandler();
	private LdapHandler _unbindLdapHandler = new UnbindLdapHandler();
	private VLDAPServer _vldapServer;

}