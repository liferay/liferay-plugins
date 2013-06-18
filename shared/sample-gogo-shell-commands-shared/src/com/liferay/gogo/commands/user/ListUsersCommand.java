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
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalService;

import java.util.List;

/**
 * @author Miguel Pastor
 */
@Component(
	properties = {
		AbstractUserManagementCommand.OSGI_COMMAND_FUNCTION + "=listByCompany",
		AbstractUserManagementCommand.OSGI_COMMAND_SCOPE + "=usermanagement"
	}, provide=Object.class)
public class ListUsersCommand extends AbstractUserManagementCommand {

	public void listByCompany(long companyId) throws SystemException {
		List<User> users = userLocalService.getCompanyUsers(companyId, -1, -1);

		System.out.println("Users of the company " + companyId);

		for (User user : users) {
			System.out.println(
				"\tUser " + user.getEmailAddress() + " with id " +
					user.getUserId());
		}
	}

	@Reference
	public void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

}