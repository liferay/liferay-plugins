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
import com.liferay.meeting.webex.service.base.WebExAccountLocalServiceBaseImpl;
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
public class WebExAccountLocalServiceImpl
	extends WebExAccountLocalServiceBaseImpl {

	public void addWebExAccount(
			long userId, long groupId, long webExSiteId, String login,
			String password, ServiceContext serviceContext)
		throws PortalException {

		// WebEx account

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		long webExAccountId = counterLocalService.increment();

		WebExAccount webExAccount = webExAccountPersistence.create(
			webExAccountId);

		webExAccount.setUuid(serviceContext.getUuid());
		webExAccount.setGroupId(groupId);
		webExAccount.setCompanyId(user.getCompanyId());
		webExAccount.setUserId(user.getUserId());
		webExAccount.setUserName(user.getFullName());
		webExAccount.setCreateDate(serviceContext.getCreateDate(now));
		webExAccount.setModifiedDate(serviceContext.getModifiedDate(now));
		webExAccount.setWebExSiteId(webExSiteId);
		webExAccount.setLogin(login);
		webExAccount.setPassword(password);
		webExAccount.setExpandoBridgeAttributes(serviceContext);

		webExAccountPersistence.update(webExAccount);

		// Resources

		resourceLocalService.addModelResources(webExAccount, serviceContext);
	}

	@Override
	public WebExAccount deleteWebExAccount(long webExAccountId)
		throws PortalException {

		WebExAccount webExAccount = webExAccountPersistence.findByPrimaryKey(
			webExAccountId);

		return deleteWebExAccount(webExAccount);
	}

	@Override
	public WebExAccount deleteWebExAccount(WebExAccount webExAccount)
		throws PortalException {

		// WebEx account

		webExAccountPersistence.remove(webExAccount);

		// Resources

		resourceLocalService.deleteResource(
			webExAccount, ResourceConstants.SCOPE_INDIVIDUAL);

		// Expando

		expandoValueLocalService.deleteValues(
			WebExAccount.class.getName(), webExAccount.getWebExAccountId());

		return webExAccount;
	}

	public void deleteWebExSiteWebExAccounts(long groupId, long webExSiteId)
		throws PortalException {

		List<WebExAccount> webExAccounts = webExAccountPersistence.findByG_W(
			groupId, webExSiteId);

		for (WebExAccount webExAccount : webExAccounts) {
			deleteWebExAccount(webExAccount);
		}
	}

	public List<WebExAccount> getWebExSiteWebExAccounts(
		long groupId, long webExSiteId) {

		return webExAccountPersistence.findByG_W(groupId, webExSiteId);
	}

	public List<WebExAccount> getWebExSiteWebExAccounts(
		long groupId, long webExSiteId, int start, int end) {

		return webExAccountPersistence.findByG_W(
			groupId, webExSiteId, start, end);
	}

	public List<WebExAccount> getWebExSiteWebExAccounts(
		long groupId, long webExSiteId, int start, int end,
		OrderByComparator obc) {

		return webExAccountPersistence.findByG_W(
			groupId, webExSiteId, start, end, obc);
	}

	public int getWebExSiteWebExAccountsCount(long groupId, long webExSiteId) {
		return webExAccountPersistence.countByG_W(groupId, webExSiteId);
	}

	public void updateWebExAccount(
			long webExAccountId, String password, ServiceContext serviceContext)
		throws PortalException {

		// WebEx account

		WebExAccount webExAccount = webExAccountPersistence.findByPrimaryKey(
			webExAccountId);

		webExAccount.setModifiedDate(serviceContext.getModifiedDate(null));

		if (Validator.isNotNull(password)) {
			webExAccount.setPassword(password);
		}

		webExAccount.setExpandoBridgeAttributes(serviceContext);

		webExAccountPersistence.update(webExAccount);
	}

}