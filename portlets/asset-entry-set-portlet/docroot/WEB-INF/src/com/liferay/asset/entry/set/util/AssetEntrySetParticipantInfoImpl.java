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
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.asset.model.AssetTag;
import com.liferay.portlet.asset.service.AssetTagLocalServiceUtil;

/**
 * @author Matthew Kong
 */
public class AssetEntrySetParticipantInfoImpl
	implements AssetEntrySetParticipantInfo {

	public JSONArray getAssetTagsJSONArray(long userId, String[] assetTagNames)
		throws PortalException, SystemException {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		User user = UserLocalServiceUtil.getUser(userId);

		Group group = GroupLocalServiceUtil.getCompanyGroup(
			user.getCompanyId());

		AssetTagLocalServiceUtil.checkTags(userId, group, assetTagNames);

		for (String assetTagName : assetTagNames) {
			AssetTag assetTag = AssetTagLocalServiceUtil.getTag(
				group.getGroupId(), assetTagName);

			if (assetTag == null) {
				throw new SystemException(
					"Asset tag does not exist for name " + assetTagName);
			}

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("classNameId", _ASSET_TAG_CLASS_NAME_ID);
			jsonObject.put("classPK", assetTag.getTagId());
			jsonObject.put("name", assetTagName);

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	public ObjectValuePair<Long, Long> getClassNameIdAndClassPKOVP(long userId)
		throws SystemException {

		return new ObjectValuePair<Long, Long>(_USER_CLASS_NAME_ID, userId);
	}

	public String getParticipantName(long classNameId, long classPK)
		throws PortalException, SystemException {

		if (classNameId != _USER_CLASS_NAME_ID) {
			return StringPool.BLANK;
		}

		User user = UserLocalServiceUtil.getUser(classPK);

		return user.getFullName();
	}

	public boolean isMember(
		long classNameId, long classPK, long sharedToClassNameId,
		long sharedToClassPK) {

		if (classNameId != _USER_CLASS_NAME_ID) {
			return false;
		}

		if (sharedToClassNameId == _USER_CLASS_NAME_ID) {
			if (classPK == sharedToClassPK) {
				return true;
			}

			return false;
		}

		try {
			if (classNameId == _GROUP_CLASS_NAME_ID) {
				return GroupLocalServiceUtil.hasUserGroup(
					classPK, sharedToClassPK);
			}
		}
		catch (Exception e) {
		}

		return false;
	}

	private static final long _ASSET_TAG_CLASS_NAME_ID =
		ClassNameLocalServiceUtil.getClassNameId(AssetTag.class);

	private static final long _GROUP_CLASS_NAME_ID =
		ClassNameLocalServiceUtil.getClassNameId(Group.class);

	private static final long _USER_CLASS_NAME_ID =
		ClassNameLocalServiceUtil.getClassNameId(User.class);

}