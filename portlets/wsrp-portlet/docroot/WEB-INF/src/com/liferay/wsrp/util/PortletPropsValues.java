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

package com.liferay.wsrp.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.util.portlet.PortletProps;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletPropsValues {

	public static final String[] CONSUMER_REQUEST_EXTENSIONS =
		PortletProps.getArray(PortletPropsKeys.CONSUMER_REQUEST_EXTENSIONS);

	public static final String[] PROXY_URL_IPS_ALLOWED =
		PortletProps.getArray(PortletPropsKeys.PROXY_URL_IPS_ALLOWED);

	public static final boolean SECURE_RESOURCE_URLS_ENABLED =
		GetterUtil.getBoolean(
			PortletProps.get(PortletPropsKeys.SECURE_RESOURCE_URLS_ENABLED));

	public static final String[] SECURE_RESOURCE_URLS_SALT =
		PortletProps.getArray(PortletPropsKeys.SECURE_RESOURCE_URLS_SALT);

	public static final boolean SOAP_DEBUG = GetterUtil.getBoolean(
		PortletProps.get(PortletPropsKeys.SOAP_DEBUG));

}