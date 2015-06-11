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

package com.liferay.pushnotifications.util.comparator;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.pushnotifications.model.PushNotificationsDevice;

/**
 * @author Javier Gamarra
 * @author Salva Tejero
 */
public class PushNotificationsDevicePlatformComparator
	extends OrderByComparator<PushNotificationsDevice> {

	public static String ORDER_BY_ASC = "platform ASC";

	public static String ORDER_BY_DESC = "platform DESC";

	public static final String[] ORDER_BY_FIELDS = {"platform"};

	public PushNotificationsDevicePlatformComparator() {
		this(true);
	}

	public PushNotificationsDevicePlatformComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(
		PushNotificationsDevice pushNotificationsDevice1,
		PushNotificationsDevice pushNotificationsDevice2) {

		String platform1 = pushNotificationsDevice1.getPlatform();
		String platform2 = pushNotificationsDevice2.getPlatform();

		int value = platform1.compareToIgnoreCase(platform2);

		if (_ascending) {
			return value;
		}
		else {
			return -value;
		}
	}

	@Override
	public String getOrderBy() {
		if (_ascending) {
			return ORDER_BY_ASC;
		}
		else {
			return ORDER_BY_DESC;
		}
	}

	@Override
	public String[] getOrderByFields() {
		return ORDER_BY_FIELDS;
	}

	@Override
	public boolean isAscending() {
		return _ascending;
	}

	private boolean _ascending;

}