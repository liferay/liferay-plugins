/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.so.util;

/**
 * @author Ryan Park
 */
public class PortletKeys extends com.liferay.portal.kernel.util.PortletKeys {

	public static final String MICROBLOGS_STATUS_UPDATE =
		com.liferay.microblogs.util.PortletKeys.MICROBLOGS_STATUS_UPDATE;

	public static final String SO_ACTIVITIES = "1_WAR_soportlet";

	public static final String SO_ANNOUNCEMENTS =
		com.liferay.so.announcements.util.PortletKeys.SO_ANNOUNCEMENTS;

	public static final String SO_CONFIGURATIONS =
		com.liferay.so.configurations.util.PortletKeys.SO_CONFIGURATIONS;

	public static final String SO_DOCKBAR_NOTIFICATION = "7_WAR_soportlet";

	public static final String SO_EXPERTISE = "4_WAR_soportlet";

	public static final String SO_INVITE_MEMBERS = "2_WAR_soportlet";

	public static final String SO_SITES = "5_WAR_soportlet";

}