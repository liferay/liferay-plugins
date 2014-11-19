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

package com.liferay.pushnotifications.service.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.security.ac.AccessControlled;
import com.liferay.pushnotifications.model.PushNotificationsEntry;
import com.liferay.pushnotifications.service.base.PushNotificationsEntryServiceBaseImpl;
import com.liferay.pushnotifications.service.permission.PushNotificationsPermission;
import com.liferay.pushnotifications.util.ActionKeys;

import java.util.List;

/**
 * @author Bruno Farache
 */
@ProviderType
public class PushNotificationsEntryServiceImpl
	extends PushNotificationsEntryServiceBaseImpl {

	@AccessControlled(guestAccessEnabled = true)
	@Override
	public PushNotificationsEntry addPushNotificationsEntry(String payload)
		throws PortalException {

		PushNotificationsPermission.check(
			getPermissionChecker(), ActionKeys.ADD_ENTRY);

		JSONObject payloadJSONObject = JSONFactoryUtil.createJSONObject(
			payload);

		return pushNotificationsEntryLocalService.addPushNotificationsEntry(
			getUserId(), payloadJSONObject);
	}

	@Override
	public PushNotificationsEntry dislikePushNotificationsEntry(
			long pushNotificationsEntryId)
		throws PortalException {

		return pushNotificationsEntryLocalService.dislikePushNotificationsEntry(
			getUserId(), pushNotificationsEntryId);
	}

	@Override
	public List<PushNotificationsEntry> getPushNotificationsEntries(
		long parentPushNotificationsEntryId, long lastAccessTime, int start,
		int end) {

		return pushNotificationsEntryLocalService.getPushNotificationsEntries(
			parentPushNotificationsEntryId, lastAccessTime, start, end);
	}

	@Override
	public PushNotificationsEntry likePushNotificationsEntry(
			long pushNotificationsEntryId)
		throws PortalException {

		return pushNotificationsEntryLocalService.likePushNotificationsEntry(
			getUserId(), pushNotificationsEntryId);
	}

}