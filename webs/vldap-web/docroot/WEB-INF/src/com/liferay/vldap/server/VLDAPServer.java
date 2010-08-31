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

import com.liferay.vldap.server.codec.LdapCodecFactory;
import com.liferay.vldap.server.handler.DispatchIoHandler;

import java.net.InetSocketAddress;

import org.apache.directory.shared.ldap.schema.SchemaManager;
import org.apache.directory.shared.ldap.schema.loader.ldif.DynamicJarLdifSchemaLoader;
import org.apache.directory.shared.ldap.schema.manager.impl.DefaultSchemaManager;
import org.apache.directory.shared.ldap.schema.registries.SchemaLoader;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class VLDAPServer {

	public void destroy() throws Exception {
		destroyIoAcceptor();
	}

	public void init() throws Exception {
		initSchemaManager();
		initIoAcceptor();
	}

	protected void destroyIoAcceptor() {
		if (_ioAcceptor != null) {
			_ioAcceptor.unbind();
			_ioAcceptor.dispose();
		}
	}

	protected void initCodec() {
		DefaultIoFilterChainBuilder defaultIoFilterChainBuilder =
			_ioAcceptor.getFilterChain();

		ProtocolCodecFactory protocolCodecFactory = new LdapCodecFactory(
			_schemaManager);

		IoFilterAdapter ioFilterAdapter = new ProtocolCodecFilter(
			protocolCodecFactory);

		defaultIoFilterChainBuilder.addLast("codec", ioFilterAdapter);
	}

	protected void initIoAcceptor() throws Exception {
		_ioAcceptor = new NioSocketAcceptor();

		initIoHandler();
		initCodec();
		initLogging();

		_ioAcceptor.bind(new InetSocketAddress(_PORT));
	}

	protected void initIoHandler() {
		_ioAcceptor.setHandler(new DispatchIoHandler());
	}

	protected  void initLogging() {
		DefaultIoFilterChainBuilder defaultIoFilterChainBuilder =
			_ioAcceptor.getFilterChain();

		defaultIoFilterChainBuilder.addLast("logger", new LoggingFilter());
	}

	protected void initSchemaManager() throws Exception {
		SchemaLoader schemaLoader = new DynamicJarLdifSchemaLoader();

		_schemaManager = new DefaultSchemaManager(schemaLoader);

		_schemaManager.loadWithDeps("core");
	}

	private static final int _PORT = 11389;

	private IoAcceptor _ioAcceptor;
	private SchemaManager _schemaManager;

}