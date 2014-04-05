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

package com.liferay.chat.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.util.portlet.PortletProps;

/**
 * @author Brian Wing Shun Chan
 * @author Tibor Lipusz
 */
public class PortletPropsValues {

	public static final int[] BUDDY_LIST_ALLOWED_SOCIAL_RELATION_TYPES =
		GetterUtil.getIntegerValues(
			PortletProps.getArray(
				PortletPropsKeys.BUDDY_LIST_ALLOWED_SOCIAL_RELATION_TYPES));

	public static final int BUDDY_LIST_MAX_BUDDIES = GetterUtil.getInteger(
		PortletProps.get(PortletPropsKeys.BUDDY_LIST_MAX_BUDDIES));

	public static final String[] BUDDY_LIST_SITE_EXCLUDES =
		PortletProps.getArray(PortletPropsKeys.BUDDY_LIST_SITE_EXCLUDES);

	public static final String BUDDY_LIST_STRATEGY = GetterUtil.getString(
		PortletProps.get(PortletPropsKeys.BUDDY_LIST_STRATEGY));

	public static final boolean JABBER_ENABLED = GetterUtil.getBoolean(
		PortletProps.get(PortletPropsKeys.JABBER_ENABLED));

	public static final String JABBER_HOST = GetterUtil.getString(
		PortletProps.get(PortletPropsKeys.JABBER_HOST));

	public static final boolean JABBER_IMPORT_USER_ENABLED =
		GetterUtil.getBoolean(
			PortletProps.get(PortletPropsKeys.JABBER_IMPORT_USER_ENABLED));

	public static final int JABBER_PORT = GetterUtil.getInteger(
		PortletProps.get(PortletPropsKeys.JABBER_PORT));

	public static final String JABBER_RESOURCE = GetterUtil.getString(
		PortletProps.get(PortletPropsKeys.JABBER_RESOURCE));

	public static final String JABBER_SERVICE_NAME = GetterUtil.getString(
		PortletProps.get(PortletPropsKeys.JABBER_SERVICE_NAME));

	public static final boolean JABBER_SOCK5_PROXY_ENABLED =
		GetterUtil.getBoolean(
			PortletProps.get(PortletPropsKeys.JABBER_SOCK5_PROXY_ENABLED));

	public static final int JABBER_SOCK5_PROXY_PORT = GetterUtil.getInteger(
		PortletProps.get(PortletPropsKeys.JABBER_SOCK5_PROXY_PORT));

}