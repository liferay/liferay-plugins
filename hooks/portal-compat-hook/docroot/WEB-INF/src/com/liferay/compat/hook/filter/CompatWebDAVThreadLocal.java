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

package com.liferay.compat.hook.filter;

import com.liferay.portal.kernel.util.InitialThreadLocal;

/**
 * @author Brian Wing Shun Chan
 */
public class CompatWebDAVThreadLocal {

	public static boolean isManualCheckInRequired() {
		return _manualCheckInRequired.get();
	}

	public static void setManualCheckInRequired(boolean manualCheckInRequired) {
		_manualCheckInRequired.set(manualCheckInRequired);
	}

	private static ThreadLocal<Boolean> _manualCheckInRequired =
		new InitialThreadLocal<Boolean>(
			CompatWebDAVThreadLocal.class + "._manualCheckInRequired", false);

}