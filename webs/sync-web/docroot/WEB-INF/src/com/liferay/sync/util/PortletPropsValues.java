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

package com.liferay.sync.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.util.portlet.PortletProps;

/**
 * @author Dennis Ju
 */
public class PortletPropsValues {

	public static final boolean SYNC_ALLOW_USER_PERSONAL_SITES =
		GetterUtil.getBoolean(
			PortletProps.get(PortletPropsKeys.SYNC_ALLOW_USER_PERSONAL_SITES));

	public static final String SYNC_AUTH_VERIFIER_PIPELINE = PortletProps.get(
		PortletPropsKeys.SYNC_AUTH_VERIFIER_PIPELINE);

	public static final int SYNC_CLIENT_MAX_CONNECTIONS = GetterUtil.getInteger(
		PortletProps.get(PortletPropsKeys.SYNC_CLIENT_MAX_CONNECTIONS));

	public static final int SYNC_CLIENT_POLL_INTERVAL = GetterUtil.getInteger(
		PortletProps.get(PortletPropsKeys.SYNC_CLIENT_POLL_INTERVAL));

	public static final int SYNC_FILE_CHECKSUM_THRESHOLD_SIZE =
		GetterUtil.getInteger(
			PortletProps.get(
				PortletPropsKeys.SYNC_FILE_CHECKSUM_THRESHOLD_SIZE));

	public static final int SYNC_FILE_DIFF_CACHE_DELETE_INTERVAL =
		GetterUtil.getInteger(
			PortletProps.get(
				PortletPropsKeys.SYNC_FILE_DIFF_CACHE_DELETE_INTERVAL));

	public static final boolean SYNC_FILE_DIFF_CACHE_ENABLED =
		GetterUtil.getBoolean(
			PortletProps.get(PortletPropsKeys.SYNC_FILE_DIFF_CACHE_ENABLED));

	public static final long SYNC_FILE_DIFF_CACHE_EXPIRATION_TIME =
		GetterUtil.getLong(
			PortletProps.get(
				PortletPropsKeys.SYNC_FILE_DIFF_CACHE_EXPIRATION_TIME));

	public static final String[] SYNC_MAC_PACKAGE_FOLDER_EXTENSIONS =
		GetterUtil.getStringValues(
			PortletProps.getArray(
				PortletPropsKeys.SYNC_MAC_PACKAGE_FOLDER_EXTENSIONS));

	public static final String[] SYNC_MAC_PACKAGE_METADATA_FILE_NAMES =
		GetterUtil.getStringValues(
			PortletProps.getArray(
				PortletPropsKeys.SYNC_MAC_PACKAGE_METADATA_FILE_NAMES));

	public static final boolean SYNC_SERVICES_ENABLED = GetterUtil.getBoolean(
		PortletProps.get(PortletPropsKeys.SYNC_SERVICES_ENABLED));

}