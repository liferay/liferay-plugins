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

package com.liferay.mail.util;

import com.liferay.mail.model.Filter;
import com.liferay.portal.kernel.googleapps.GEmailSettingsManager;
import com.liferay.portal.kernel.googleapps.GNicknameManager;
import com.liferay.portal.kernel.googleapps.GUserManager;
import com.liferay.portal.kernel.googleapps.GoogleAppsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.FullNameGenerator;
import com.liferay.portal.security.auth.FullNameGeneratorFactory;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class GoogleHook implements Hook {

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
			String nickname = _getNickname(emailAddress);

			GUserManager gUserManager = GoogleAppsFactoryUtil.getGUserManager(
				companyId);

			gUserManager.addGUser(userId, password, firstName, lastName);

			GNicknameManager gNicknameManager =
				GoogleAppsFactoryUtil.getGNicknameManager(companyId);

			gNicknameManager.addGNickname(userId, nickname);

			GEmailSettingsManager gEmailSettingsManager =
				GoogleAppsFactoryUtil.getGEmailSettingsManager(companyId);

			FullNameGenerator fullNameGenerator =
				FullNameGeneratorFactory.getInstance();

			gEmailSettingsManager.addSendAs(
				userId,
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
			User user = UserLocalServiceUtil.getUserById(userId);

			String nickname = _getNickname(user.getEmailAddress());

			GNicknameManager gNicknameManager =
				GoogleAppsFactoryUtil.getGNicknameManager(companyId);

			gNicknameManager.deleteGNickname(nickname);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	@Override
	public void deleteUser(long companyId, long userId) {
		try {
			GUserManager gUserManager = GoogleAppsFactoryUtil.getGUserManager(
				companyId);

			gUserManager.deleteGUser(userId);
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

			GNicknameManager gNicknameManager =
				GoogleAppsFactoryUtil.getGNicknameManager(companyId);

			String nickname = _getNickname(emailAddress);

			gNicknameManager.addGNickname(userId, nickname);

			GEmailSettingsManager gEmailSettingsManager =
				GoogleAppsFactoryUtil.getGEmailSettingsManager(companyId);

			gEmailSettingsManager.addSendAs(
				userId, user.getFullName(), emailAddress);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	@Override
	public void updatePassword(long companyId, long userId, String password) {
		try {
			GUserManager gUserManager = GoogleAppsFactoryUtil.getGUserManager(
				companyId);

			gUserManager.updatePassword(userId, password);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private String _getNickname(String emailAddress) {
		int pos = emailAddress.indexOf(CharPool.AT);

		return emailAddress.substring(0, pos);
	}

	private static Log _log = LogFactoryUtil.getLog(GoogleHook.class);

}