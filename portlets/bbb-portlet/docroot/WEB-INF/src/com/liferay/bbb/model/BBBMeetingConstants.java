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

package com.liferay.bbb.model;

import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Shinn Lok
 */
public class BBBMeetingConstants {

	public static final int BBB_SERVER_ID_DEFAULT = 0;

	public static final String LABEL_ANY = "any";

	public static final String LABEL_COMPLETED = "completed";

	public static final String LABEL_IN_PROGRESS = "in-progress";

	public static final String LABEL_SCHEDULED = "scheduled";

	public static final int STATUS_ANY = -1;

	public static final int STATUS_COMPLETED = 2;

	public static final int STATUS_IN_PROGRESS = 1;

	public static final int STATUS_SCHEDULED = 0;

	public static String getStatusLabel(int status) {
		if (status == STATUS_ANY) {
			return LABEL_ANY;
		}
		else if (status == STATUS_COMPLETED) {
			return LABEL_COMPLETED;
		}
		else if (status == STATUS_IN_PROGRESS) {
			return LABEL_IN_PROGRESS;
		}
		else if (status == STATUS_SCHEDULED) {
			return LABEL_SCHEDULED;
		}
		else {
			return StringPool.BLANK;
		}
	}

}