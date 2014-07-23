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

package com.liferay.sync.engine.util;

/**
 * @author Shinn Lok
 */
public class PropsValues {

	public static final String SYNC_CONFIGURATION_DIRECTORY = PropsUtil.get(
		PropsKeys.SYNC_CONFIGURATION_DIRECTORY);

	public static final String SYNC_DATABASE_NAME = PropsUtil.get(
		PropsKeys.SYNC_DATABASE_NAME);

	public static final String[] SYNC_FILE_BLACKLIST_CHARS = PropsUtil.getArray(
		PropsKeys.SYNC_FILE_BLACKLIST_CHARS);

	public static final String[] SYNC_FILE_BLACKLIST_CHARS_LAST =
		PropsUtil.getArray(PropsKeys.SYNC_FILE_BLACKLIST_CHARS_LAST);

	public static final String[] SYNC_FILE_BLACKLIST_NAMES = PropsUtil.getArray(
		PropsKeys.SYNC_FILE_BLACKLIST_NAMES);

	public static final int SYNC_FILE_CHECKSUM_THRESHOLD_SIZE =
		Integer.parseInt(
			PropsUtil.get(PropsKeys.SYNC_FILE_CHECKSUM_THRESHOLD_SIZE));

	public static final boolean SYNC_FILE_IGNORE_HIDDEN = Boolean.valueOf(
		PropsUtil.get(PropsKeys.SYNC_FILE_IGNORE_HIDDEN));

	public static final String[] SYNC_FILE_IGNORE_NAMES = PropsUtil.getArray(
		PropsKeys.SYNC_FILE_IGNORE_NAMES);

	public static final String[] SYNC_FILE_PATCHING_IGNORE_FILE_EXTENSIONS =
		PropsUtil.getArray(PropsKeys.SYNC_FILE_PATCHING_IGNORE_EXTENSIONS);

	public static final int SYNC_FILE_PATCHING_THRESHOLD_SIZE_RATIO =
		Integer.parseInt(
			PropsUtil.get(PropsKeys.SYNC_FILE_PATCHING_THRESHOLD_SIZE_RATIO));

	public static final String SYNC_LOGGER_CONFIGURATION_FILE = PropsUtil.get(
		PropsKeys.SYNC_LOGGER_CONFIGURATION_FILE);

	public static final String SYNC_PRODUCT_NAME = PropsUtil.get(
		PropsKeys.SYNC_PRODUCT_NAME);

	public static final String SYNC_UPDATE_CHECK_URL = PropsUtil.get(
		PropsKeys.SYNC_UPDATE_CHECK_URL);

}