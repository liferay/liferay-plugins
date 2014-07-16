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
public interface PropsKeys {

	public static final String SYNC_CONFIGURATION_DIRECTORY =
		"sync.configuration.directory";

	public static final String SYNC_DATABASE_NAME = "sync.database.name";

	public static final String SYNC_FILE_BLACKLIST_CHARS =
		"sync.file.blacklist.chars";

	public static final String SYNC_FILE_BLACKLIST_CHARS_LAST =
		"sync.file.blacklist.chars.last";

	public static final String SYNC_FILE_BLACKLIST_NAMES =
		"sync.file.blacklist.names";

	public static final String SYNC_FILE_IGNORE_HIDDEN =
		"sync.file.ignore.hidden";

	public static final String SYNC_FILE_IGNORE_NAMES =
		"sync.file.ignore.names";

	public static final String SYNC_FILE_PATCHING_IGNORE_EXTENSIONS =
		"sync.file.patching.ignore.extensions";

	public static final String SYNC_FILE_PATCHING_SIZE_RATIO_THRESHOLD =
		"sync.file.patching.size.ratio.threshold";

	public static final String SYNC_LOGGER_CONFIGURATION_FILE =
		"sync.logger.configuration.file";

	public static final String SYNC_NOTIFICATION_FIELD_NAMES_PREFIX =
		"sync.notification.field.names";

	public static final String SYNC_PRODUCT_NAME = "sync.product.name";

	public static final String SYNC_UPDATE_CHECK_URL = "sync.update.check.url";

}