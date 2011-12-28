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

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.util.portlet.PortletProps;

/**
 * @author Raymond Augé
 * @author Brian Wing Shun Chan
 */
public class PortletPropsValues {

	public static final String DRIVER_AUTOCONNECT_RETRY = PortletProps.get(
		PortletPropsKeys.DRIVER_AUTOCONNECT_RETRY);

	public static final String DRIVER_CONNECT_TIMEOUT = PortletProps.get(
		PortletPropsKeys.DRIVER_CONNECT_TIMEOUT);

	public static final String DRIVER_CONNECTIONS_PER_HOST = PortletProps.get(
		PortletPropsKeys.DRIVER_CONNECTIONS_PER_HOST);

	public static final String DRIVER_MAX_WAIT_TIME = PortletProps.get(
		PortletPropsKeys.DRIVER_MAX_WAIT_TIME);

	public static final String DRIVER_SOCKET_TIMEOUT = PortletProps.get(
		PortletPropsKeys.DRIVER_SOCKET_TIMEOUT);

	public static final String DRIVER_THREADS_ALLOWED_TO_BLOCK =
		PortletProps.get(PortletPropsKeys.DRIVER_THREADS_ALLOWED_TO_BLOCK);

	public static final String SERVER_DATABASE = PortletProps.get(
		PortletPropsKeys.SERVER_DATABASE);

	public static final String[] SERVER_HOSTNAMES = PortletProps.getArray(
		PortletPropsKeys.SERVER_HOSTNAMES);

	public static final String SERVER_PASSWORD = PortletProps.get(
		PortletPropsKeys.SERVER_PASSWORD);

	public static final int SERVER_PORT = GetterUtil.getInteger(
		PortletProps.get(PortletPropsKeys.SERVER_PORT));

	public static final String SERVER_USERNAME = PortletProps.get(
		PortletPropsKeys.SERVER_USERNAME);

}