/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

	public static String SYNC_CONFIGURATION_DIRECTORY = PropsUtil.get(
		PropsKeys.SYNC_CONFIGURATION_DIRECTORY);

	public static String SYNC_DATABASE_NAME = PropsUtil.get(
		PropsKeys.SYNC_DATABASE_NAME);

	public static String SYNC_LOGGER_CONFIGURATION_FILE = PropsUtil.get(
		PropsKeys.SYNC_LOGGER_CONFIGURATION_FILE);

	public static String SYNC_PRODUCT_NAME = PropsUtil.get(
		PropsKeys.SYNC_PRODUCT_NAME);

}