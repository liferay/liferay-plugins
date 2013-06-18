/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.gogo.commands.user;

import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;

import com.liferay.gogo.commands.user.internal.AbstractUserManagementCommand;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalService;

/**
 * @author Miguel Pastor
 */
@Component(
	properties = {
		AbstractUserManagementCommand.OSGI_COMMAND_FUNCTION + "=getUserInfo",
		AbstractUserManagementCommand.OSGI_COMMAND_SCOPE + "=usermanagement"
	}, provide=Object.class)
public class GetUserInfoCommand extends AbstractUserManagementCommand {

	public void getUserInfo(long companyId, String emailAddress)
		throws PortalException, SystemException {

		User user = userLocalService.fetchUserByEmailAddress(
			companyId, emailAddress);

		if (user == null) {
			System.out.println(
				"The user " + emailAddress + " does not belong to the " +
					"company " + companyId);

			return;
		}

		System.out.println("Details of user " + emailAddress);
		System.out.println("\tUser id" + user.getUserId());
		System.out.println("\tLogin info: " + user.getLogin());
		System.out.println("\tEmail address" + user.getEmailAddress());
	}

	@Reference
	public void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

}