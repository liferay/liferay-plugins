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

package com.liferay.mail.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.util.portlet.PortletProps;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletPropsValues {

	public static final String DEFAULT_ACCOUNTS = PortletProps.get(
		PortletPropsKeys.DEFAULT_ACCOUNTS);

	public static final int[] INCOMING_PORTS = StringUtil.split(
		PortletProps.get(PortletPropsKeys.INCOMING_PORTS), 0);

	public static final boolean JAVAMAIL_DEBUG = GetterUtil.getBoolean(
		PortletProps.get(PortletPropsKeys.JAVAMAIL_DEBUG));

	public static final int MESSAGES_SYNC_COUNT = GetterUtil.getInteger(
		PortletProps.get(PortletPropsKeys.MESSAGES_SYNC_COUNT));

	public static final int[] OUTGOING_PORTS = StringUtil.split(
		PortletProps.get(PortletPropsKeys.OUTGOING_PORTS), 0);

}