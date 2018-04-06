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

import com.liferay.meeting.webex.model.WebExSite;
import com.liferay.meeting.webex.service.base.WebExSiteServiceBaseImpl;
import com.liferay.meeting.webex.service.permission.WebExPermission;
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
public class WebExSiteServiceImpl extends WebExSiteServiceBaseImpl {

	public void addWebExSite(
			long groupId, String name, String apiURL, String login,
			String password, String partnerKey, long siteKey,
			ServiceContext serviceContext)
		throws PortalException {

		WebExPermission.check(
			getPermissionChecker(), groupId, ActionKeys.ADD_SITE);

		webExSiteLocalService.addWebExSite(
			getUserId(), groupId, name, apiURL, login, password, partnerKey,
			siteKey, serviceContext);
	}

	public WebExSite deleteWebExSite(long webExSiteId) throws PortalException {
		WebExSitePermission.check(
			getPermissionChecker(), webExSiteId, ActionKeys.DELETE);

		return webExSiteLocalService.deleteWebExSite(webExSiteId);
	}

	public WebExSite fetchSiteKeyWebExSite(long siteKey)
		throws PortalException {

		WebExSite webExSite = webExSiteLocalService.fetchSiteKeyWebExSite(
			siteKey);

		WebExSitePermission.check(
			getPermissionChecker(), webExSite.getWebExSiteId(),
			ActionKeys.VIEW);

		return webExSite;
	}

	public WebExSite getWebExSite(long webExSiteId) throws PortalException {
		WebExSitePermission.check(
			getPermissionChecker(), webExSiteId, ActionKeys.VIEW);

		return webExSiteLocalService.getWebExSite(webExSiteId);
	}

	public List<WebExSite> getWebExSites(
		long groupId, int start, int end, OrderByComparator obc) {

		return webExSitePersistence.filterFindByGroupId(
			groupId, start, end, obc);
	}

	public int getWebExSitesCount(long groupId) {
		return webExSitePersistence.filterCountByGroupId(groupId);
	}

	public void updateWebExSite(
			long webExSiteId, String apiURL, String login, String password,
			ServiceContext serviceContext)
		throws PortalException {

		WebExSitePermission.check(
			getPermissionChecker(), webExSiteId, ActionKeys.UPDATE);

		webExSiteLocalService.updateWebExSite(
			webExSiteId, apiURL, login, password, serviceContext);
	}

}