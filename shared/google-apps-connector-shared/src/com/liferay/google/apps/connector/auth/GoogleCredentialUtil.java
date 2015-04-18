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

package com.liferay.google.apps.connector.auth;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import com.liferay.google.apps.connector.util.PortletPropsValues;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

import java.io.File;

import java.util.Arrays;

/**
 * @author Matthew Kong
 */
public class GoogleCredentialUtil {

	public static GoogleCredential getGoogleCredential() throws Exception {
		if (_googleCredential != null) {
			return _googleCredential;
		}

		GoogleCredential.Builder builder = new GoogleCredential.Builder();

		builder.setJsonFactory(new JacksonFactory());
		builder.setServiceAccountId(
			PortletPropsValues.GOOGLE_API_SERVICE_ACCOUNT_ID);

		File file = new File(
			PropsUtil.get(PropsKeys.LIFERAY_HOME) +
				PortletPropsValues.
					GOOGLE_API_SERVICE_ACCOUNT_PRIVATE_KEY_P12_FILE);

		builder.setServiceAccountPrivateKeyFromP12File(file);

		builder.setServiceAccountScopes(
			Arrays.asList(
				PortletPropsValues.GOOGLE_API_SERVICE_ACCOUNT_SCOPES));
		builder.setServiceAccountUser(
			PortletPropsValues.GOOGLE_API_SERVICE_ACCOUNT_USER);
		builder.setTransport(new NetHttpTransport());

		_googleCredential = builder.build();

		return _googleCredential;
	}

	private static GoogleCredential _googleCredential;

}