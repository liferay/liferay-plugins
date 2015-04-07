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

package com.liferay.repository.external.cache;

import com.liferay.portal.kernel.repository.RepositoryException;
import com.liferay.portal.kernel.servlet.PortalSessionThreadLocal;
import com.liferay.portal.kernel.util.AutoResetThreadLocal;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.TransientValue;

import javax.servlet.http.HttpSession;

/**
 * @author Iván Zaera
 */
public class ConnectionCache<T> {

	public ConnectionCache(
		Class<T> connectionClass, long repositoryId,
		ConnectionBuilder<T> connectionBuilder) {

		_connectionBuilder = connectionBuilder;

		_sessionKey =
			ConnectionCache.class.getName() + StringPool.POUND + repositoryId;

		_connectionThreadLocal = new AutoResetThreadLocal<>(
			connectionClass.getName());
	}

	public T getConnection() throws RepositoryException {
		T connection = null;

		HttpSession httpSession = PortalSessionThreadLocal.getHttpSession();

		if (httpSession != null) {
			TransientValue<T> transientValue =
				(TransientValue<T>)httpSession.getAttribute(_sessionKey);

			if (transientValue != null) {
				connection = transientValue.getValue();
			}
		}
		else {
			connection = _connectionThreadLocal.get();
		}

		if (connection != null) {
			return connection;
		}

		connection = _connectionBuilder.buildConnection();

		if (httpSession != null) {
			TransientValue<T> transientValue = new TransientValue<>(connection);

			httpSession.setAttribute(_sessionKey, transientValue);
		}

		_connectionThreadLocal.set(connection);

		return connection;
	}

	private ConnectionBuilder<T> _connectionBuilder;
	private ThreadLocal<T> _connectionThreadLocal;
	private String _sessionKey;

}