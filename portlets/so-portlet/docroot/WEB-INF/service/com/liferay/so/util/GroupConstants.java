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
 * @author Jonathan Lee
 */
public class GroupConstants
	extends com.liferay.portal.kernel.model.GroupConstants {

	public static final int TYPE_SITE_PRIVATE_RESTRICTED = 102;

	public static final String TYPE_SITE_PRIVATE_RESTRICTED_LABEL =
		"private-restricted";

	public static final int TYPE_SITE_PUBLIC_RESTRICTED = 101;

	public static final String TYPE_SITE_PUBLIC_RESTRICTED_LABEL =
		"public-restricted";

	public static String getTypeLabel(int type) {
		if (type == TYPE_SITE_OPEN) {
			return TYPE_SITE_OPEN_LABEL;
		}
		else if (type == TYPE_SITE_PRIVATE) {
			return TYPE_SITE_PRIVATE_LABEL;
		}
		else if (type == TYPE_SITE_RESTRICTED) {
			return TYPE_SITE_RESTRICTED_LABEL;
		}
		else if (type == TYPE_SITE_PRIVATE_RESTRICTED) {
			return TYPE_SITE_PRIVATE_RESTRICTED_LABEL;
		}
		else if (type == TYPE_SITE_PUBLIC_RESTRICTED) {
			return TYPE_SITE_PUBLIC_RESTRICTED_LABEL;
		}
		else {
			return TYPE_SITE_SYSTEM_LABEL;
		}
	}

}