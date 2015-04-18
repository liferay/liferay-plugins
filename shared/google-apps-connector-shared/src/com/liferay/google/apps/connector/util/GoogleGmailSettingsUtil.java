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

package com.liferay.google.apps.connector.util;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.gdata.util.AuthenticationException;

import com.liferay.google.apps.connector.auth.GoogleCredentialUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Company;
import com.liferay.portal.service.CompanyLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

import sample.appsforyourdomain.gmailsettings.GmailSettingsService;

/**
 * @author Amos Fong
 */
public class GoogleGmailSettingsUtil {

	public static void addSendAs(
			long companyId, long userId, String fullName, String emailAddress)
		throws Exception {

		GmailSettingsService gmailSettingsService = _getGmailSettingsService(
			companyId);

		List<String> users = new ArrayList<>();

		users.add(String.valueOf(userId));

		gmailSettingsService.createSendAs(
			users, fullName, emailAddress, StringPool.BLANK, true);
	}

	private static GmailSettingsService _getGmailSettingsService(long companyId)
		throws Exception {

		if (_gmailSettingsService != null) {
			return _gmailSettingsService;
		}

		Company company = CompanyLocalServiceUtil.getCompany(companyId);

		_gmailSettingsService = new GmailSettingsService(
			StringPool.BLANK, company.getMx(), StringPool.BLANK,
			StringPool.BLANK) {

			@Override
			public void setUserCredentials(String username, String password)
				throws AuthenticationException {
			}

		};

		GoogleCredential googleCredential =
			GoogleCredentialUtil.getGoogleCredential();

		_gmailSettingsService.setOAuth2Credentials(googleCredential);

		return _gmailSettingsService;
	}

	private static GmailSettingsService _gmailSettingsService;

}