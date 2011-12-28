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

package com.liferay.mongodb.util;

/**
 * @author Raymond Augé
 * @author Brian Wing Shun Chan
 */
public interface PortletPropsKeys {

	public static final String DRIVER_AUTOCONNECT_RETRY =
		"driver.autoConnectRetry";

	public static final String DRIVER_CONNECT_TIMEOUT = "driver.connectTimeout";

	public static final String DRIVER_CONNECTIONS_PER_HOST =
		"driver.connectionsPerHost";

	public static final String DRIVER_MAX_WAIT_TIME = "driver.maxWaitTime";

	public static final String DRIVER_SOCKET_TIMEOUT = "driver.socketTimeout";

	public static final String DRIVER_THREADS_ALLOWED_TO_BLOCK =
		"driver.threadsAllowedToBlockForConnectionMultiplier";

	public static final String SERVER_DATABASE = "server.database";

	public static final String SERVER_HOSTNAMES = "server.hostnames";

	public static final String SERVER_PASSWORD = "server.password";

	public static final String SERVER_PORT = "server.port";

	public static final String SERVER_USERNAME = "server.username";

}