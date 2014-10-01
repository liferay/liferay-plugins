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

package com.liferay.google.mail.groups.util;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.groupssettings.Groupssettings;
import com.google.api.services.groupssettings.model.Groups;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Matthew Kong
 */
public class GoogleGroupssettingsUtil {

	public static Groups getGroups(String groupEmailAddress) {
		try {
			Groupssettings groupssettings = getGroupssettings();

			Groupssettings.Groups groups = groupssettings.groups();

			Groupssettings.Groups.Get get = groups.get(groupEmailAddress);

			return get.execute();
		}
		catch (Exception e) {
			return null;
		}
	}

	public static Groupssettings getGroupssettings() throws Exception {
		if (_groupssettings != null) {
			return _groupssettings;
		}

		GoogleCredential googleCredential =
			GoogleCredentialUtil.getGoogleCredential();

		Groupssettings.Builder builder = new Groupssettings.Builder(
			googleCredential.getTransport(), googleCredential.getJsonFactory(),
			googleCredential);

		_groupssettings = builder.build();

		return _groupssettings;
	}

	public static void updateGroups(String groupEmailAddress, Groups groups)
		throws PortalException {

		try {
			Groupssettings groupssettings = getGroupssettings();

			Groupssettings.Groups groupssettingsGroups =
				groupssettings.groups();

			Groupssettings.Groups.Update update = groupssettingsGroups.update(
				groupEmailAddress, groups);

			update.execute();
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	private static Groupssettings _groupssettings;

}