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

package com.liferay.asset.entry.set.handler;

import com.liferay.asset.entry.set.model.AssetEntrySet;
import com.liferay.asset.entry.set.util.AssetEntrySetConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserConstants;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;

import java.io.File;

/**
 * @author Calvin Keum
 */
public class BaseAssetEntrySetHandler implements AssetEntrySetHandler {

	public BaseAssetEntrySetHandler(String portletId) {
		_portletId = portletId;
	}

	@Override
	public String getPortletId() {
		return _portletId;
	}

	@Override
	public JSONObject interpret(JSONObject payloadJSONObject, File file)
		throws PortalException, SystemException {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("linkData", payloadJSONObject.getString("linkData"));
		jsonObject.put("message", payloadJSONObject.getString("message"));
		jsonObject.put("type", payloadJSONObject.getString("type"));

		jsonObject.put(
			AssetEntrySetConstants.PAYLOAD_KEY_ASSET_TAG_NAMES,
			payloadJSONObject.getString(
				AssetEntrySetConstants.PAYLOAD_KEY_ASSET_TAG_NAMES));
		jsonObject.put(
			AssetEntrySetConstants.PAYLOAD_KEY_SHARED_TO,
			payloadJSONObject.getJSONArray(
				AssetEntrySetConstants.PAYLOAD_KEY_SHARED_TO));

		return jsonObject;
	}

	@Override
	public void updateParticipants(AssetEntrySet assetEntrySet)
		throws PortalException, SystemException {

		JSONObject payloadJSONObject = JSONFactoryUtil.createJSONObject(
			assetEntrySet.getPayload());

		JSONObject creatorJSONObject = getCreator(
			assetEntrySet.getCreatorClassNameId(),
			assetEntrySet.getCreatorClassPK());

		payloadJSONObject.put(
			AssetEntrySetConstants.PAYLOAD_KEY_CREATOR, creatorJSONObject);

		JSONArray sharedToJSONArray = getSharedTo(payloadJSONObject);

		payloadJSONObject.put(
			AssetEntrySetConstants.PAYLOAD_KEY_SHARED_TO, sharedToJSONArray);

		assetEntrySet.setPayload(
			JSONFactoryUtil.looseSerialize(payloadJSONObject));
	}

	protected JSONObject getCreator(
			long creatorClassNameId, long creatorClassPK)
		throws PortalException, SystemException {

		return getParticipant(
			JSONFactoryUtil.createJSONObject(), creatorClassNameId,
			creatorClassPK, true);
	}

	protected JSONObject getParticipant(
			JSONObject participantJSONObject, long classNameId, long classPK,
			boolean includePortraitURL)
		throws PortalException, SystemException {

		String participantFullName = StringPool.BLANK;
		String participantPortraitURL = StringPool.BLANK;
		String participantURL = StringPool.BLANK;

		if (classNameId == _USER_CLASS_NAME_ID) {
			User user = UserLocalServiceUtil.getUser(classPK);

			participantFullName = user.getFullName();

			if (includePortraitURL) {
				participantPortraitURL = UserConstants.getPortraitURL(
					PortalUtil.getPathImage(), user.isMale(),
					user.getPortraitId());
			}

			Group group = user.getGroup();

			participantURL =
				_LAYOUT_FRIENDLY_URL_PUBLIC_SERVLET_MAPPING +
					group.getFriendlyURL();
		}
		else {
			AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(
				PortalUtil.getClassName(classNameId), classPK);

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
				assetEntry.getDescription());

			participantFullName = jsonObject.getString(
				AssetEntrySetConstants.ASSET_ENTRY_KEY_PARTICIPANT_FULL_NAME);

			if (includePortraitURL) {
				participantPortraitURL = jsonObject.getString(
					AssetEntrySetConstants.
						ASSET_ENTRY_KEY_PARTICIPANT_PORTRAIT_URL);
			}

			participantURL = jsonObject.getString(
				AssetEntrySetConstants.ASSET_ENTRY_KEY_PARTICIPANT_URL);
		}

		participantJSONObject.put(
			AssetEntrySetConstants.ASSET_ENTRY_KEY_PARTICIPANT_FULL_NAME,
			participantFullName);

		if (includePortraitURL) {
			participantJSONObject.put(
				AssetEntrySetConstants.ASSET_ENTRY_KEY_PARTICIPANT_PORTRAIT_URL,
				participantPortraitURL);
		}

		participantJSONObject.put(
			AssetEntrySetConstants.ASSET_ENTRY_KEY_PARTICIPANT_URL,
			participantURL);

		return participantJSONObject;
	}

	protected JSONArray getSharedTo(JSONObject payloadJSONObject)
		throws PortalException, SystemException {

		JSONArray sharedToJSONArray =
			payloadJSONObject.getJSONArray(
				AssetEntrySetConstants.PAYLOAD_KEY_SHARED_TO);

		JSONArray returnedSharedToJSONArray = JSONFactoryUtil.createJSONArray();

		for (int i = 0; i < sharedToJSONArray.length(); i++) {
			JSONObject participantJSONObject = sharedToJSONArray.getJSONObject(
				i);

			long classNameId = participantJSONObject.getLong("classNameId");
			long classPK = participantJSONObject.getLong("classPK");

			returnedSharedToJSONArray.put(
				getParticipant(
					participantJSONObject, classNameId, classPK, false));
		}

		return returnedSharedToJSONArray;
	}

	protected void setPortletId(String portletId) {
		_portletId = portletId;
	}

	private static final String _LAYOUT_FRIENDLY_URL_PUBLIC_SERVLET_MAPPING =
		PropsUtil.get(PropsKeys.LAYOUT_FRIENDLY_URL_PUBLIC_SERVLET_MAPPING);

	private static final long _USER_CLASS_NAME_ID =
		ClassNameLocalServiceUtil.getClassNameId(User.class);

	private String _portletId;

}