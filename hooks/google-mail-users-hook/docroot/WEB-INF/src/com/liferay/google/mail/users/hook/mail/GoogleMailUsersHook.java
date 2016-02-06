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

package com.liferay.google.mail.users.hook.mail;

import com.liferay.google.apps.connector.util.GoogleDirectoryUtil;
import com.liferay.google.apps.connector.util.GoogleGmailSettingsUtil;
import com.liferay.mail.kernel.model.Filter;
import com.liferay.mail.kernel.util.Hook;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.FullNameGenerator;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.lang.reflect.Method;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
public class GoogleMailUsersHook implements Hook {

	@Override
	public void addForward(
		long companyId, long userId, List<Filter> filters,
		List<String> emailAddresses, boolean leaveCopy) {
	}

	@Override
	public void addUser(
		long companyId, long userId, String password, String firstName,
		String middleName, String lastName, String emailAddress) {

		try {
			String primaryEmailAddress = _getPrimaryEmailAddress(
				companyId, userId);

			GoogleDirectoryUtil.addUser(
				primaryEmailAddress, password, firstName, middleName, lastName);

			GoogleDirectoryUtil.addUserAlias(primaryEmailAddress, emailAddress);

			FullNameGenerator fullNameGenerator = _getFullNameGenerator();

			GoogleGmailSettingsUtil.addSendAs(
				companyId, userId,
				fullNameGenerator.getFullName(firstName, middleName, lastName),
				emailAddress);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	@Override
	public void addVacationMessage(
		long companyId, long userId, String emailAddress,
		String vacationMessage) {
	}

	@Override
	public void deleteEmailAddress(long companyId, long userId) {
		try {
			String primaryEmailAddress = _getPrimaryEmailAddress(
				companyId, userId);

			User user = UserLocalServiceUtil.getUserById(userId);

			GoogleDirectoryUtil.deleteUserAlias(
				primaryEmailAddress, user.getEmailAddress());
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	@Override
	public void deleteUser(long companyId, long userId) {
		try {
			String primaryEmailAddress = _getPrimaryEmailAddress(
				companyId, userId);

			GoogleDirectoryUtil.deleteUser(primaryEmailAddress);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	@Override
	public void updateBlocked(
		long companyId, long userId, List<String> blocked) {
	}

	@Override
	public void updateEmailAddress(
		long companyId, long userId, String emailAddress) {

		try {
			User user = UserLocalServiceUtil.getUserById(userId);

			deleteEmailAddress(companyId, userId);

			String primaryEmailAddress = _getPrimaryEmailAddress(
				companyId, userId);

			GoogleDirectoryUtil.addUserAlias(primaryEmailAddress, emailAddress);

			GoogleGmailSettingsUtil.addSendAs(
				companyId, userId, user.getFullName(), emailAddress);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	@Override
	public void updatePassword(long companyId, long userId, String password) {
		try {
			String primaryEmailAddress = _getPrimaryEmailAddress(
				companyId, userId);

			GoogleDirectoryUtil.updateUserPassword(
				primaryEmailAddress, password);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private FullNameGenerator _getFullNameGenerator() throws Exception {
		ClassLoader classLoader = PortalClassLoaderUtil.getClassLoader();

		Class<?> clazz = classLoader.loadClass(
			"com.liferay.portal.kernel.security.auth.FullNameGeneratorFactory");

		Method method = clazz.getMethod("getInstance");

		return (FullNameGenerator)method.invoke(null);
	}

	private String _getPrimaryEmailAddress(long companyId, long userId)
		throws PortalException {

		Company company = CompanyLocalServiceUtil.getCompany(companyId);

		return userId + StringPool.AT + company.getMx();
	}

	private static Log _log = LogFactoryUtil.getLog(GoogleMailUsersHook.class);

}