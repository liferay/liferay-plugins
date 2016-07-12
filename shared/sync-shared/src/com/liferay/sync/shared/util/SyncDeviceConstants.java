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

package com.liferay.sync.shared.util;

/**
 * @author Shinn Lok
 */
public class SyncDeviceConstants {

	/**
	 * Feature set 1:
	 *
	 *  Sites removed from Sync context
	 */
	public static final int FEATURE_SET_1 = 1;

	public static final int STATUS_ACTIVE = 0;

	public static final int STATUS_INACTIVE = 1;

	public static final String STATUS_LABEL_ACTIVE = "active";

	public static final String STATUS_LABEL_INACTIVE = "inactive";

	public static final String STATUS_LABEL_PENDING_WIPE = "pending-wipe";

	public static final String STATUS_LABEL_WIPED = "wiped";

	public static final int STATUS_PENDING_WIPE = 2;

	public static final int STATUS_WIPED = 3;

	public static String getStatusLabel(int status) {
		if (status == STATUS_ACTIVE) {
			return STATUS_LABEL_ACTIVE;
		}
		else if (status == STATUS_INACTIVE) {
			return STATUS_LABEL_INACTIVE;
		}
		else if (status == STATUS_PENDING_WIPE) {
			return STATUS_LABEL_PENDING_WIPE;
		}
		else if (status == STATUS_WIPED) {
			return STATUS_LABEL_WIPED;
		}
		else {
			return null;
		}
	}

}