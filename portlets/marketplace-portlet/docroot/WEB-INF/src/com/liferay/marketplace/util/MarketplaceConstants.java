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

package com.liferay.marketplace.util;

import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Ryan Park
 */
public class MarketplaceConstants {

	public static final String MARKETPLACE_URL_LOGOUT =
		PortletPropsValues.MARKETPLACE_URL + "/c/portal/logout";

	public static String getPathPurchased() {
		if (_pathPurchased == null) {
			_pathPurchased =
				_MARKETPLACE_PATH_PURCHASED + _MARKETPLACE_CLIENT_BUILD +
					StringPool.SLASH;
		}

		return _pathPurchased + ReleaseInfo.getBuildNumber();
	}

	public static String getPathStore() {
		if (_pathStore == null) {
			_pathStore =
				_MARKETPLACE_PATH_STORE + _MARKETPLACE_CLIENT_BUILD +
					StringPool.SLASH;
		}

		return _pathStore + ReleaseInfo.getBuildNumber();
	}

	private static final String _MARKETPLACE_CLIENT_BUILD = "3";

	private static final String _MARKETPLACE_PATH_PURCHASED =
		"/widget/web/guest/mpserver/-/mp_server/purchased/";

	private static final String _MARKETPLACE_PATH_STORE =
		"/widget/web/guest/mpserver/-/mp_server/store/";

	private static String _pathPurchased;
	private static String _pathStore;

}