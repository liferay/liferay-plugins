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

package com.liferay.meeting.webex.service.impl;

import com.liferay.meeting.webex.model.WebExAccount;
import com.liferay.meeting.webex.service.base.WebExAccountServiceBaseImpl;
import com.liferay.meeting.webex.service.permission.WebExAccountPermission;
import com.liferay.meeting.webex.service.permission.WebExSitePermission;
import com.liferay.meeting.webex.util.ActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * @author Anant Singh
 * @author Michael C. Han
 */
public class WebExAccountServiceImpl extends WebExAccountServiceBaseImpl {

	public void addWebExAccount(
			long groupId, long webExSiteId, String login, String password,
			ServiceContext serviceContext)
		throws PortalException {

		WebExSitePermission.check(
			getPermissionChecker(), webExSiteId, ActionKeys.ADD_ACCOUNT);

		webExAccountLocalService.addWebExAccount(
			getUserId(), groupId, webExSiteId, login, password, serviceContext);
	}

	public void deleteWebExAccount(long webExAccountId) throws PortalException {
		WebExAccountPermission.check(
			getPermissionChecker(), webExAccountId, ActionKeys.DELETE);

		webExAccountLocalService.deleteWebExAccount(webExAccountId);
	}

	public WebExAccount getWebExAccount(long webExAccountId)
		throws PortalException {

		WebExAccountPermission.check(
			getPermissionChecker(), webExAccountId, ActionKeys.VIEW);

		return webExAccountLocalService.getWebExAccount(webExAccountId);
	}

	public List<WebExAccount> getWebExSiteWebExAccounts(
		long groupId, long webExSiteId, int start, int end,
		OrderByComparator obc) {

		return webExAccountPersistence.filterFindByG_W(
			groupId, webExSiteId, start, end, obc);
	}

	public int getWebExSiteWebExAccountsCount(long groupId, long webExSiteId) {
		return webExAccountPersistence.filterCountByG_W(groupId, webExSiteId);
	}

	public void updateWebExAccount(
			long webExAccountId, String password, ServiceContext serviceContext)
		throws PortalException {

		WebExAccountPermission.check(
			getPermissionChecker(), webExAccountId, ActionKeys.UPDATE);

		webExAccountLocalService.updateWebExAccount(
			webExAccountId, password, serviceContext);
	}

}