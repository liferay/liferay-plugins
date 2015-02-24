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

package com.liferay.sync.model;

import com.liferay.portlet.documentlibrary.model.DLSyncConstants;

/**
 * @author Dennis Ju
 */
public class SyncConstants extends DLSyncConstants {

	public static final int PERMISSIONS_DEFAULT = 0;

	public static final int PERMISSIONS_FULL_ACCESS = 4;

	public static final int PERMISSIONS_NONE = 1;

	public static final int PERMISSIONS_VIEW_AND_ADD_DISCUSSION = 3;

	public static final int PERMISSIONS_VIEW_ONLY = 2;

	public static final String TYPE_PRIVATE_WORKING_COPY = "privateWorkingCopy";

}