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
public class SyncPermissionsConstants {

	public static final int PERMISSIONS_DEFAULT = 0;

	public static final int PERMISSIONS_FULL_ACCESS = 4;

	public static final int PERMISSIONS_NONE = 1;

	public static final int PERMISSIONS_VIEW_AND_ADD_DISCUSSION = 3;

	public static final int PERMISSIONS_VIEW_ONLY = 2;

	public static final int PERMISSIONS_VIEW_UPDATE_AND_ADD_DISCUSSION = 5;

	public static String[] getFileResourceActions(int value) {
		if (value == PERMISSIONS_FULL_ACCESS) {
			return new String[] {"ADD_DISCUSSION", "DELETE", "UPDATE", "VIEW"};
		}
		else if (value == PERMISSIONS_NONE) {
			return new String[0];
		}
		else if (value == PERMISSIONS_VIEW_AND_ADD_DISCUSSION) {
			return new String[] {"ADD_DISCUSSION", "VIEW"};
		}
		else if (value == PERMISSIONS_VIEW_ONLY) {
			return new String[] {"VIEW"};
		}
		else if (value == PERMISSIONS_VIEW_UPDATE_AND_ADD_DISCUSSION) {
			return new String[] {"ADD_DISCUSSION", "UPDATE", "VIEW"};
		}

		return new String[0];
	}

	public static String[] getFolderResourceActions(int value) {
		if (value == PERMISSIONS_FULL_ACCESS) {
			return new String[] {
				"ADD_DOCUMENT", "ADD_SHORTCUT", "ADD_SUBFOLDER", "DELETE",
				"UPDATE", "VIEW"
			};
		}
		else if (value == PERMISSIONS_NONE) {
			return new String[0];
		}
		else if (value == PERMISSIONS_VIEW_AND_ADD_DISCUSSION) {
			return new String[] {"VIEW"};
		}
		else if (value == PERMISSIONS_VIEW_ONLY) {
			return new String[] {"VIEW"};
		}
		else if (value == PERMISSIONS_VIEW_UPDATE_AND_ADD_DISCUSSION) {
			return new String[] {
				"ADD_DOCUMENT", "ADD_SHORTCUT", "ADD_SUBFOLDER", "UPDATE",
				"VIEW"
			};
		}

		return new String[0];
	}

}