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

import com.liferay.gogo.commands.user.internal.AbstractCommand;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalService;

/**
 * @author Miguel Pastor
 */
@Component(
	properties = {
		AbstractCommand.OSGI_COMMAND_FUNCTION + "=user",
		AbstractCommand.OSGI_COMMAND_SCOPE + "=liferay"
	},
	provide = Object.class)
public class UserCommand extends AbstractCommand {

	@Override
	@Reference
	public void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	public void user(long companyId, String emailAddress)
		throws PortalException, SystemException {

		User user = userLocalService.fetchUserByEmailAddress(
			companyId, emailAddress);

		if (user == null) {
			System.out.println(
				"There is no user with the company ID " + companyId +
					" and the email address " + emailAddress + ".");

			return;
		}

		System.out.println("Email address: " + user.getEmailAddress());
		System.out.println("First name: " + user.getFirstName());
		System.out.println("Last name: " + user.getLastName());
		System.out.println("Login: " + user.getLogin());
		System.out.println("Middle name: " + user.getMiddleName());
		System.out.println("Screen name: " + user.getScreenName());
		System.out.println("User ID: " + user.getUserId());
	}

}