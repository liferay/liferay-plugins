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

import org.apache.directory.shared.ldap.codec.api.LdapApiService;
import org.apache.directory.shared.ldap.codec.api.LdapMessageContainer;
import org.apache.directory.shared.ldap.codec.api.MessageDecorator;
import org.apache.directory.shared.ldap.codec.standalone.StandaloneLdapApiService;
import org.apache.directory.shared.ldap.model.message.Message;
import org.apache.directory.shared.ldap.model.message.MessageTypeEnum;
import org.apache.directory.shared.ldap.model.message.Request;
import org.apache.directory.shared.ldap.model.message.Response;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class DispatchIoHandler implements IoHandler {

	public void exceptionCaught(IoSession ioSession, Throwable cause) {
		if (_log.isDebugEnabled()) {
			_log.debug(cause, cause);
		}
	}

	public void messageReceived(IoSession ioSession, Object message) {
		Request request = (Request)message;

		LdapHandler ldapHandler = getLdapHandler(request);

		if (ldapHandler == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(request.getType() + " is not supported");
			}

			return;
		}

		try {
			LdapHandlerContext ldapHandlerContext = getLdapHandlerContext(
				ioSession);

			List<Response> resposes = ldapHandler.messageReceived(
				request, ioSession, ldapHandlerContext);

			writeResponses(resposes, ioSession);
		}
		catch (Exception e) {
			_log.error(e, e);
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
		try {
			LdapApiService ldapApiService = new StandaloneLdapApiService();

			LdapMessageContainer <MessageDecorator<? extends Message>>
				ldapMessageContainer =
					new LdapMessageContainer
						<MessageDecorator<? extends Message>>(ldapApiService);

			// Needed by org.apache.directory.shared.ldap.codec.protocol.mina.
			// LdapProtocolDecoder

			ioSession.setAttribute("messageContainer", ldapMessageContainer);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	protected LdapHandler getLdapHandler(Request request) {
		MessageTypeEnum messageTypeEnum = request.getType();

		switch (messageTypeEnum) {
			case ABANDON_REQUEST:
				return _abandonLdapHandler;

			case BIND_REQUEST:
				return _bindLdapHandler;

			case COMPARE_REQUEST:
				return _compareLdapHandler;

			case EXTENDED_REQUEST:
				return _extendedLdapHandler;

			case SEARCH_REQUEST:
				return _searchLdapHandler;

			case UNBIND_REQUEST:
				return _unbindLdapHandler;

			default:
				return null;
		}
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

					ioSession.setAttribute(
						LdapHandlerContext.class.getName(), ldapHandlerContext);
				}
			}
		}

		return ldapHandlerContext;
	}

	protected void setSessionAttributes(
		Response response, IoSession ioSession) {

		Map<Object, Object> sessionAttributes =
			(Map<Object, Object>)response.get(
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
		List<Response> responses, IoSession ioSession) {

		if (responses == null) {
			return;
		}

		for (Response response : responses) {
			setSessionAttributes(response, ioSession);

			ioSession.write(response);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(DispatchIoHandler.class);

	private LdapHandler _abandonLdapHandler = new AbandonLdapHandler();
	private LdapHandler _bindLdapHandler = new BindLdapHandler();
	private LdapHandler _compareLdapHandler = new CompareLdapHandler();
	private LdapHandler _extendedLdapHandler = new ExtendedLdapHandler();
	private LdapHandler _searchLdapHandler = new SearchLdapHandler();
	private LdapHandler _unbindLdapHandler = new UnbindLdapHandler();

}