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

package com.liferay.pushnotifications.util;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.pushnotifications.model.PushNotificationsDevice;
import com.liferay.pushnotifications.util.comparator.PushNotificationsDevicePlatformComparator;

/**
 * @author Javier Gamarra
 * @author Salva Tejero
 */
public class PushNotificationsUtil {

	public static OrderByComparator<PushNotificationsDevice>
		getPushNotificationsDeviceOrderByComparator(
			String orderByCol, String orderByType) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		if (orderByCol.equals("platform")) {
			return new PushNotificationsDevicePlatformComparator(orderByAsc);
		}

		return null;
	}

}