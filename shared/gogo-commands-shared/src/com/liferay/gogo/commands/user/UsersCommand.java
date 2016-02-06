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

package com.liferay.gogo.commands.user;

import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;

import com.liferay.gogo.commands.user.internal.AbstractCommand;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;

import java.util.List;

/**
 * @author Miguel Pastor
 */
@Component(
	properties = {
		AbstractCommand.OSGI_COMMAND_FUNCTION + "=users",
		AbstractCommand.OSGI_COMMAND_SCOPE + "=liferay"
	},
	provide = Object.class
)
public class UsersCommand extends AbstractCommand {

	@Override
	@Reference
	public void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	public void users(long companyId) {
		List<User> users = userLocalService.getCompanyUsers(
			companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		System.out.println(
			"Company " + companyId + " has " + users.size() + " users.");

		for (User user : users) {
			System.out.println();
			System.out.println("Email address: " + user.getEmailAddress());
			System.out.println("User ID: " + user.getUserId());
		}
	}

}