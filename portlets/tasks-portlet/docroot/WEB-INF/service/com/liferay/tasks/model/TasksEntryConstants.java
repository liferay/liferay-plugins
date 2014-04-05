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

package com.liferay.tasks.model;

/**
 * @author Ryan Park
 */
public class TasksEntryConstants {

	public static final int[] PRIORITIES = {
		TasksEntryConstants.PRIORITY_HIGH, TasksEntryConstants.PRIORITY_NORMAL,
		TasksEntryConstants.PRIORITY_LOW
	};

	public static final int PRIORITY_HIGH = 1;

	public static final String PRIORITY_HIGH_LABEL = "high";

	public static final int PRIORITY_LOW = 3;

	public static final String PRIORITY_LOW_LABEL = "low";

	public static final int PRIORITY_NORMAL = 2;

	public static final String PRIORITY_NORMAL_LABEL = "normal";

	public static final int STATUS_ALL = 0;

	public static final String STATUS_ALL_LABEL = "all";

	public static final int STATUS_OPEN = 1;

	public static final String STATUS_OPEN_LABEL = "open";

	public static final int STATUS_PERCENT_EIGHTY = 5;

	public static final String STATUS_PERCENT_EIGHTY_LABEL =
		"80-percent-complete";

	public static final int STATUS_PERCENT_FORTY = 3;

	public static final String STATUS_PERCENT_FORTY_LABEL =
		"40-percent-complete";

	public static final int STATUS_PERCENT_SIXTY = 4;

	public static final String STATUS_PERCENT_SIXTY_LABEL =
		"60-percent-complete";

	public static final int STATUS_PERCENT_TWENTY = 2;

	public static final String STATUS_PERCENT_TWENTY_LABEL =
		"20-percent-complete";

	public static final int STATUS_REOPENED = 7;

	public static final String STATUS_REOPENED_LABEL = "reopened";

	public static final int STATUS_RESOLVED = 6;

	public static final String STATUS_RESOLVED_LABEL = "resolved";

	public static final int[] STATUSES = {
		STATUS_OPEN, STATUS_PERCENT_TWENTY, STATUS_PERCENT_FORTY,
		STATUS_PERCENT_SIXTY, STATUS_PERCENT_EIGHTY, STATUS_RESOLVED,
		STATUS_REOPENED
	};

	public static String getPriorityLabel(int priority) {
		if (priority == PRIORITY_HIGH) {
			return PRIORITY_HIGH_LABEL;
		}
		else if (priority == PRIORITY_LOW) {
			return PRIORITY_LOW_LABEL;
		}
		else if (priority == PRIORITY_NORMAL) {
			return PRIORITY_NORMAL_LABEL;
		}
		else {
			return null;
		}
	}

	public static String getStatusLabel(int status) {
		if (status == STATUS_OPEN) {
			return STATUS_OPEN_LABEL;
		}
		else if (status == STATUS_PERCENT_TWENTY) {
			return STATUS_PERCENT_TWENTY_LABEL;
		}
		else if (status == STATUS_PERCENT_FORTY) {
			return STATUS_PERCENT_FORTY_LABEL;
		}
		else if (status == STATUS_PERCENT_SIXTY) {
			return STATUS_PERCENT_SIXTY_LABEL;
		}
		else if (status == STATUS_PERCENT_EIGHTY) {
			return STATUS_PERCENT_EIGHTY_LABEL;
		}
		else if (status == STATUS_RESOLVED) {
			return STATUS_RESOLVED_LABEL;
		}
		else if (status == STATUS_REOPENED) {
			return STATUS_REOPENED_LABEL;
		}
		else {
			return null;
		}
	}

}