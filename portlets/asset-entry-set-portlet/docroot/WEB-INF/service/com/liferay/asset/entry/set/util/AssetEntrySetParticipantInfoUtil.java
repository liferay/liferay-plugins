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

package com.liferay.asset.entry.set.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ObjectValuePair;

/**
 * @author Matthew Kong
 */
public class AssetEntrySetParticipantInfoUtil {

	public static AssetEntrySetParticipantInfo
		getAssetEntrySetParticipantInfo() {

		return _assetEntrySetParticipantInfo;
	}

	public static ObjectValuePair<Long, Long> getClassNameIdAndClassPKOVP(
			long userId)
		throws SystemException {

		return getAssetEntrySetParticipantInfo().getClassNameIdAndClassPKOVP(
			userId);
	}

	public static JSONObject getParticipantJSONObject(
			JSONObject participantJSONObject, long classNameId, long classPK,
			boolean includePortraitURL)
		throws PortalException, SystemException {

		return getAssetEntrySetParticipantInfo().getParticipantJSONObject(
			participantJSONObject, classNameId, classPK, includePortraitURL);
	}

	public static boolean isMember(
		long classNameId, long classPK, long sharedToClassNameId,
		long sharedToClassPK) {

		return getAssetEntrySetParticipantInfo().isMember(
			classNameId, classPK, sharedToClassNameId, sharedToClassPK);
	}

	public void setAssetEntrySetParticipantInfo(
		AssetEntrySetParticipantInfo assetEntrySetParticipantInfo) {

		_assetEntrySetParticipantInfo = assetEntrySetParticipantInfo;
	}

	private static AssetEntrySetParticipantInfo _assetEntrySetParticipantInfo;

}