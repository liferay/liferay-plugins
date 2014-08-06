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

package com.liferay.mobilewidgets.service.impl;

import com.liferay.mobilewidgets.service.base.MobileWidgetsUserServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;

/**
 * @author José Manuel Navarro
 */
public class MobileWidgetsUserServiceImpl
	extends MobileWidgetsUserServiceBaseImpl {

	public boolean sendPasswordByEmailAddress(
			long companyId, String emailAddress, ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userLocalService.getUserByEmailAddress(
			companyId, emailAddress);

		return sendPasswordEmail(user, serviceContext);
	}

	public boolean sendPasswordByScreenName(
			long companyId, String screenName, ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userLocalService.getUserByScreenName(companyId, screenName);

		return sendPasswordEmail(user, serviceContext);
	}

	public boolean sendPasswordByUserId(
			long userId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userLocalService.getUserById(userId);

		return sendPasswordEmail(user, serviceContext);
	}

	protected void populateServiceContext(
			long companyId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		if (serviceContext.getPathMain() == null) {
			serviceContext.setPathMain(PortalUtil.getPathMain());
		}

		if (serviceContext.getScopeGroupId() == 0) {
			Group guestGroup = GroupLocalServiceUtil.getGroup(
				companyId, GroupConstants.GUEST);

			serviceContext.setScopeGroupId(guestGroup.getGroupId());
		}

		if (serviceContext.getPlid() == 0) {
			long plid = LayoutLocalServiceUtil.getDefaultPlid(
				serviceContext.getScopeGroupId(), false);

			serviceContext.setPlid(plid);
		}
	}

	protected boolean sendPasswordEmail(
			User user, ServiceContext serviceContext)
		throws PortalException, SystemException {

		populateServiceContext(user.getCompanyId(), serviceContext);

		userLocalService.sendPassword(
			user.getCompanyId(), user.getEmailAddress(), null, null, null, null,
			serviceContext);

		Company company = companyLocalService.getCompanyById(
			user.getCompanyId());

		return company.isSendPassword();
	}

}