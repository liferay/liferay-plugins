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
import com.liferay.vldap.util.PortletPropsValues;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

import java.util.Map;

import org.apache.directory.shared.ldap.codec.protocol.mina.LdapProtocolCodecFactory;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class VLDAPServer {

	public void destroy() throws Exception {
		destroyIoAcceptor();
	}

	public void init() throws Exception {
		initIoAcceptor();
	}

	protected void destroyIoAcceptor() {
		if (_ioAcceptor != null) {
			Map<Long, IoSession> managedSessions =
				_ioAcceptor.getManagedSessions();

			for (IoSession ioSession : managedSessions.values()) {
				ioSession.close(true);
			}

			_ioAcceptor.unbind();
			_ioAcceptor.dispose();
		}
	}

	protected void initCodec() {
		DefaultIoFilterChainBuilder defaultIoFilterChainBuilder =
			_ioAcceptor.getFilterChain();

		ProtocolCodecFactory protocolCodecFactory =
			new LdapProtocolCodecFactory();

		IoFilterAdapter ioFilterAdapter = new ProtocolCodecFilter(
			protocolCodecFactory);

		defaultIoFilterChainBuilder.addLast("codec", ioFilterAdapter);
	}

	protected void initIoAcceptor() throws Exception {
		_ioAcceptor = new NioSocketAcceptor();

		initIoHandler();
		initCodec();
		initLogging();

		SocketAddress socketAddress = new InetSocketAddress(
			PortletPropsValues.BIND_PORT);

		_ioAcceptor.bind(socketAddress);
	}

	protected void initIoHandler() {
		DispatchIoHandler dispatchIoHandler = new DispatchIoHandler();

		_ioAcceptor.setHandler(dispatchIoHandler);
	}

	protected  void initLogging() {
		DefaultIoFilterChainBuilder defaultIoFilterChainBuilder =
			_ioAcceptor.getFilterChain();

		if (_log.isDebugEnabled()) {
			defaultIoFilterChainBuilder.addLast("logger", new LoggingFilter());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(VLDAPServer.class);

	private IoAcceptor _ioAcceptor;

}