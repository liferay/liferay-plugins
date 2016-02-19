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

import com.liferay.asset.entry.set.model.AssetEntrySet;
import com.liferay.portal.util.PortalUtil;

/**
 * @author Matthew Kong
 */
public class AssetEntrySetConstants {

	public static final String ASSET_ENTRY_KEY_PARTICIPANT_FULL_NAME =
		"participantFullName";

	public static final String ASSET_ENTRY_KEY_PARTICIPANT_PROFILE_IMAGE_URL =
		"participantProfileImageURL";

	public static final String ASSET_ENTRY_KEY_PARTICIPANT_URL =
		"participantURL";

	public static final long ASSET_ENTRY_SET_CLASS_NAME_ID =
		PortalUtil.getClassNameId(AssetEntrySet.class);

	public static final String IMAGE_TYPE_FULL = "full";

	public static final String IMAGE_TYPE_RAW = "raw";

	public static final String PAYLOAD_KEY_ASSET_TAG_NAMES = "assetTagNames";

	public static final String PAYLOAD_KEY_CREATOR = "creator";

	public static final String PAYLOAD_KEY_LIKED_PARTICIPANTS =
		"likedParticipants";

	public static final String PAYLOAD_KEY_SHARED_TO = "sharedTo";

	public static final int TYPE_ANNOUNCEMENT = 1;

	public static final int TYPE_POST = 0;

}