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

package com.liferay.asset.entry.set.service.participant;

import com.liferay.asset.entry.set.util.AssetEntrySetConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserConstants;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

/**
 * @author Matthew Kong
 */
public class AssetEntrySetParticipantInfoImpl
	implements AssetEntrySetParticipantInfo {

	public JSONObject getParticipantJSONObject(
			JSONObject participantJSONObject, long classNameId, long classPK,
			boolean includePortraitURL)
		throws PortalException, SystemException {

		if (classNameId != _USER_CLASS_NAME_ID) {
			return participantJSONObject;
		}

		User user = UserLocalServiceUtil.getUser(classPK);

		participantJSONObject.put(
			AssetEntrySetConstants.ASSET_ENTRY_KEY_PARTICIPANT_FULL_NAME,
			user.getFullName());

		participantJSONObject.put(
			AssetEntrySetConstants.ASSET_ENTRY_KEY_PARTICIPANT_PORTRAIT_URL,
			UserConstants.getPortraitURL(
				PortalUtil.getPathImage(), user.isMale(),
				user.getPortraitId()));

		Group group = user.getGroup();

		participantJSONObject.put(
			AssetEntrySetConstants.ASSET_ENTRY_KEY_PARTICIPANT_URL,
			_LAYOUT_FRIENDLY_URL_PUBLIC_SERVLET_MAPPING +
				group.getFriendlyURL());

		return participantJSONObject;
	}

	private static final String _LAYOUT_FRIENDLY_URL_PUBLIC_SERVLET_MAPPING =
		PropsUtil.get(PropsKeys.LAYOUT_FRIENDLY_URL_PUBLIC_SERVLET_MAPPING);

	private static final long _USER_CLASS_NAME_ID =
		ClassNameLocalServiceUtil.getClassNameId(User.class);

}