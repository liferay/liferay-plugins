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

package com.liferay.socialcoding.svn.util;

import com.liferay.util.portlet.PortletProps;

/**
 * @author Brian Wing Shun Chan
 */
public interface SVNConstants {

	public static final String SVN_AUTH_PASSWORD = PortletProps.get(
		"svn.auth.password");

	public static final String SVN_AUTH_USERNAME = PortletProps.get(
		"svn.auth.username");

	public static final String SVN_PLUGINS_TRUNK_URL =
		"svn://svn.liferay.com/repos/public/plugins/trunk";

	public static final String SVN_PORTAL_TRUNK_URL =
		"svn://svn.liferay.com/repos/public/portal/trunk";

	public static final String WEB_REVISION_NUMBER_URL =
		"http://svn.liferay.com/changelog/{0}/?cs={1}";

	public static final String[] SVN_URLS = new String[] {
		SVN_PORTAL_TRUNK_URL, SVN_PLUGINS_TRUNK_URL
	};

}