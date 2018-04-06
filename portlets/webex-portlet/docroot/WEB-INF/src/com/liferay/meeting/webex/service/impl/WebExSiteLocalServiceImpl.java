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
import com.liferay.meeting.webex.service.base.WebExSiteLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;

/**
 * @author Anant Singh
 * @author Michael C. Han
 */
public class WebExSiteLocalServiceImpl extends WebExSiteLocalServiceBaseImpl {

	public void addWebExSite(
			long userId, long groupId, String name, String apiURL, String login,
			String password, String partnerKey, long siteKey,
			ServiceContext serviceContext)
		throws PortalException {

		// WebEx site

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		long webExSiteId = counterLocalService.increment();

		WebExSite webExSite = webExSitePersistence.create(webExSiteId);

		webExSite.setUuid(serviceContext.getUuid());
		webExSite.setGroupId(groupId);
		webExSite.setCompanyId(user.getCompanyId());
		webExSite.setUserId(user.getUserId());
		webExSite.setUserName(user.getFullName());
		webExSite.setCreateDate(serviceContext.getCreateDate(now));
		webExSite.setModifiedDate(serviceContext.getModifiedDate(now));
		webExSite.setName(name);
		webExSite.setApiURL(apiURL);
		webExSite.setLogin(login);
		webExSite.setPassword(password);

		if (Validator.isNotNull(partnerKey)) {
			webExSite.setPartnerKey(partnerKey);
		}

		webExSite.setSiteKey(siteKey);
		webExSite.setExpandoBridgeAttributes(serviceContext);

		webExSitePersistence.update(webExSite);

		// Resources

		resourceLocalService.addModelResources(webExSite, serviceContext);
	}

	@Override
	public WebExSite deleteWebExSite(long webExSiteId) throws PortalException {
		WebExSite webExSite = webExSitePersistence.findByPrimaryKey(
			webExSiteId);

		return deleteWebExSite(webExSite);
	}

	@Override
	public WebExSite deleteWebExSite(WebExSite webExSite)
		throws PortalException {

		// WebEx site

		webExSitePersistence.remove(webExSite);

		// Resources

		resourceLocalService.deleteResource(
			webExSite, ResourceConstants.SCOPE_INDIVIDUAL);

		// WebEx accounts

		webExAccountLocalService.deleteWebExSiteWebExAccounts(
			webExSite.getGroupId(), webExSite.getWebExSiteId());

		// Expando

		expandoValueLocalService.deleteValues(
			WebExSite.class.getName(), webExSite.getWebExSiteId());

		return webExSite;
	}

	public WebExSite fetchSiteKeyWebExSite(long siteKey) {
		return webExSitePersistence.fetchBySiteKey(siteKey);
	}

	public WebExSite getSiteKeyWebExSite(long siteKey) throws PortalException {
		return webExSitePersistence.findBySiteKey(siteKey);
	}

	public List<WebExSite> getWebExSites(long groupId, int start, int end) {
		return webExSitePersistence.findByGroupId(groupId, start, end);
	}

	public List<WebExSite> getWebExSites(
		long groupId, int start, int end, OrderByComparator obc) {

		return webExSitePersistence.findByGroupId(groupId, start, end, obc);
	}

	public void updateWebExSite(
			long webExSiteId, String apiURL, String login, String password,
			ServiceContext serviceContext)
		throws PortalException {

		// WebEx site

		WebExSite webExSite = webExSitePersistence.findByPrimaryKey(
			webExSiteId);

		webExSite.setModifiedDate(serviceContext.getModifiedDate(null));
		webExSite.setApiURL(apiURL);
		webExSite.setLogin(login);

		if (Validator.isNotNull(password)) {
			webExSite.setPassword(password);
		}

		webExSite.setExpandoBridgeAttributes(serviceContext);

		webExSitePersistence.update(webExSite);
	}

}