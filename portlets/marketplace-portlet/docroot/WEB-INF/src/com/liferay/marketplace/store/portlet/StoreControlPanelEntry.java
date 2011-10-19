/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.marketplace.store.portlet;

import com.liferay.marketplace.util.MarketplaceUtil;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.BaseControlPanelEntry;

/**
 * @author Ryan Park
 */
public class StoreControlPanelEntry extends BaseControlPanelEntry {

	public boolean isVisible(
			Portlet portlet, String category, ThemeDisplay themeDisplay)
		throws Exception {

		return isVisible(themeDisplay.getPermissionChecker(), portlet);
	}

	public boolean isVisible(
			PermissionChecker permissionChecker, Portlet portlet)
		throws Exception {

		long companyId = permissionChecker.getCompanyId();

		if (!MarketplaceUtil.hasMarketplaceAdmin(companyId) &&
			permissionChecker.isOmniadmin()) {

			return true;
		}

		User user = UserLocalServiceUtil.getUserById(
			permissionChecker.getUserId());

		if (MarketplaceUtil.isMarketplaceAdmin(user)) {
			return true;
		}

		return false;
	}

}