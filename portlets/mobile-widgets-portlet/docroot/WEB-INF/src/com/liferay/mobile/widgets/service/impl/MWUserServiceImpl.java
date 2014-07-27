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

package com.liferay.mobile.widgets.service.impl;

import com.liferay.mobile.widgets.service.base.MWUserServiceBaseImpl;
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
 * The implementation of the m w user remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.mobile.widgets.service.MWUserService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Jose M. Navarro
 * @see com.liferay.mobile.widgets.service.base.MWUserServiceBaseImpl
 * @see com.liferay.mobile.widgets.service.MWUserServiceUtil
 */
public class MWUserServiceImpl extends MWUserServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.liferay.mobile.widgets.service.MWUserServiceUtil} to access the m w user remote service.
	 */
	public boolean sendPasswordByEmailAddress(
		long companyId, String emailAddress, ServiceContext serviceContext)
	throws Exception {

		User user = userLocalService.getUserByEmailAddress(
			companyId, emailAddress);

		return sendPasswordEmail(user, serviceContext);
	}

	public boolean sendPasswordByScreenName(
		long companyId, String screenName, ServiceContext serviceContext)
	throws Exception {

		User user = userLocalService.getUserByScreenName(companyId, screenName);

		return sendPasswordEmail(user, serviceContext);
	}

	public boolean sendPasswordByUserId(
		long companyId, long userId, ServiceContext serviceContext)
	throws Exception {

		User user = userLocalService.getUserById(userId);

		return sendPasswordEmail(user, serviceContext);
	}
	
	protected void fillServiceContext(
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
	
		fillServiceContext(user.getCompanyId(), serviceContext);
	
		userLocalService.sendPassword(
			user.getCompanyId(), user.getEmailAddress(), null, null, null, null,
			serviceContext);
	
		Company company = companyLocalService.getCompanyById(
			user.getCompanyId());		
	
		return company.isSendPassword();
	}

}