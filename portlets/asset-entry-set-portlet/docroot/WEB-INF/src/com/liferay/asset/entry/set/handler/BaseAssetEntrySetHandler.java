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
import com.liferay.asset.entry.set.service.AssetEntrySetLocalServiceUtil;
import com.liferay.asset.entry.set.util.AssetEntrySetConstants;
import com.liferay.asset.entry.set.util.AssetEntrySetParticipantInfoUtil;
import com.liferay.asset.entry.set.util.PortletPropsValues;
import com.liferay.compat.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Country;
import com.liferay.portal.service.CountryServiceUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
	public JSONObject interpret(
			long userId, long assetEntrySetId, JSONObject payloadJSONObject)
		throws PortalException, SystemException {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		JSONObject geolocationJSONObject = JSONFactoryUtil.createJSONObject();

		AssetEntrySet assetEntrySet =
			AssetEntrySetLocalServiceUtil.fetchAssetEntrySet(assetEntrySetId);

		if (assetEntrySet != null) {
			if (isContentModified(
					JSONFactoryUtil.createJSONObject(
						assetEntrySet.getPayload()),
					payloadJSONObject)) {

				jsonObject.put(
					"contentModifiedTime", System.currentTimeMillis());
			}

			JSONObject oldPayloadJSONObject = JSONFactoryUtil.createJSONObject(
				assetEntrySet.getPayload());

			geolocationJSONObject = oldPayloadJSONObject.getJSONObject(
				"geolocation");
		}
		else {
			geolocationJSONObject = getGeolocationJSONObject(payloadJSONObject);
		}

		jsonObject.put("geolocation", geolocationJSONObject);

		jsonObject.put("linkData", payloadJSONObject.getJSONObject("linkData"));
		jsonObject.put("message", payloadJSONObject.getString("message"));
		jsonObject.put("rawMessage", payloadJSONObject.getString("rawMessage"));
		jsonObject.put(
			"sendEmailNotifications",
			payloadJSONObject.getBoolean("sendEmailNotifications"));
		jsonObject.put("truncated", payloadJSONObject.getBoolean("truncated"));

		String truncatedMessage = payloadJSONObject.getString(
			"truncatedMessage");

		if (Validator.isNotNull(truncatedMessage)) {
			jsonObject.put("truncatedMessage", truncatedMessage);
		}

		jsonObject.put("type", payloadJSONObject.getString("type"));
		jsonObject.put("userAgent", payloadJSONObject.getString("userAgent"));

		JSONArray sharedToJSONArray = payloadJSONObject.getJSONArray(
			AssetEntrySetConstants.PAYLOAD_KEY_SHARED_TO);

		if (sharedToJSONArray != null) {
			String[] assetTagNames = StringUtil.split(
				payloadJSONObject.getString(
					AssetEntrySetConstants.PAYLOAD_KEY_ASSET_TAG_NAMES));

			JSONArray assetTagsJSONArray =
				AssetEntrySetParticipantInfoUtil.getAssetTagsJSONArray(
					userId, assetTagNames);

			for (int i = 0; i < assetTagsJSONArray.length(); i++) {
				sharedToJSONArray.put(assetTagsJSONArray.getJSONObject(i));
			}

			sharedToJSONArray = processSharedToJSONArray(sharedToJSONArray);

			jsonObject.put(
				AssetEntrySetConstants.PAYLOAD_KEY_SHARED_TO,
				sharedToJSONArray);
		}

		return jsonObject;
	}

	protected JSONArray dedupeSharedToJSONArray(JSONArray sharedToJSONArray) {
		Map<Long, List<Long>> entityClassNameIds =
			new HashMap<Long, List<Long>>();

		JSONArray newSharedToJSONArray = JSONFactoryUtil.createJSONArray();

		for (int i = 0; i < sharedToJSONArray.length(); i++) {
			JSONObject sharedToJSONObject = sharedToJSONArray.getJSONObject(i);

			long entityClassNameId = sharedToJSONObject.getLong(
				"entityClassNameId");

			List<Long> entityClassPKs = entityClassNameIds.get(
				entityClassNameId);

			if (entityClassPKs == null) {
				entityClassPKs = new ArrayList<Long>();
			}

			long entityClassPK = sharedToJSONObject.getLong("entityClassPK");

			if (entityClassPKs.contains(entityClassPK)) {
				continue;
			}

			entityClassPKs.add(entityClassPK);

			entityClassNameIds.put(entityClassNameId, entityClassPKs);

			newSharedToJSONArray.put(sharedToJSONObject);
		}

		return newSharedToJSONArray;
	}

	protected JSONArray filterSharedToJSONArray(JSONArray sharedToJSONArray)
		throws PortalException {

		JSONArray newSharedToJSONArray = JSONFactoryUtil.createJSONArray();

		List<String> keys = ListUtil.toList(
			PortletPropsValues.ASSET_ENTRY_SET_SHARED_TO_JSON_OBJECT_KEYS);

		for (int i = 0; i < sharedToJSONArray.length(); i++) {
			JSONObject sharedToJSONObject = sharedToJSONArray.getJSONObject(i);

			JSONObject newSharedToJSONObject = JSONFactoryUtil.createJSONObject(
				sharedToJSONObject.toString());

			Iterator<String> itr = sharedToJSONObject.keys();

			while (itr.hasNext()) {
				String key = itr.next();

				if (!keys.contains(key)) {
					newSharedToJSONObject.remove(key);
				}
			}

			newSharedToJSONArray.put(newSharedToJSONObject);
		}

		return newSharedToJSONArray;
	}

	protected JSONObject getGeolocationJSONObject(
		JSONObject payloadJSONObject) {

		if (Validator.isNull(PortletPropsValues.GEONAMES_URL)) {
			return JSONFactoryUtil.createJSONObject();
		}

		JSONObject geolocationJSONObject = payloadJSONObject.getJSONObject(
			"geolocation");

		if (geolocationJSONObject == null) {
			return JSONFactoryUtil.createJSONObject();
		}

		double latitude = geolocationJSONObject.getDouble("latitude");
		double longitude = geolocationJSONObject.getDouble("longitude");

		geolocationJSONObject.put(
			"locationName", getLocationName(latitude, longitude));

		geolocationJSONObject.remove("latitude");
		geolocationJSONObject.remove("longitude");

		return geolocationJSONObject;
	}

	protected String getLocationName(double latitude, double longitude) {
		try {
			String url = HttpUtil.addParameter(
				PortletPropsValues.GEONAMES_URL, "latitude", latitude);

			url = HttpUtil.addParameter(url, "longitude", longitude);

			JSONObject geoNamesJSONObject = JSONFactoryUtil.createJSONObject(
				HttpUtil.URLtoString(url));

			String name = geoNamesJSONObject.getString("name");

			String countryCode = geoNamesJSONObject.getString("countryCode");

			Country country = CountryServiceUtil.fetchCountryByA2(countryCode);

			if (country == null) {
				return name;
			}

			return name + StringPool.COMMA_AND_SPACE +
				country.getName(LocaleUtil.getDefault());
		}
		catch (Exception e) {
			return StringPool.BLANK;
		}
	}

	protected boolean isContentModified(
		JSONObject oldPayloadJSONObject, JSONObject newPayloadJSONObject) {

		Iterator<String> keys = oldPayloadJSONObject.keys();

		while (keys.hasNext()) {
			String key = keys.next();

			if (key.equals(
					AssetEntrySetConstants.PAYLOAD_KEY_ASSET_TAG_NAMES) ||
				key.equals(AssetEntrySetConstants.PAYLOAD_KEY_SHARED_TO)) {

				continue;
			}

			String oldValue = oldPayloadJSONObject.getString(key);
			String newValue = newPayloadJSONObject.getString(key);

			if (!Validator.equals(oldValue, newValue)) {
				return true;
			}
		}

		return false;
	}

	protected JSONArray processSharedToJSONArray(JSONArray sharedToJSONArray)
		throws PortalException {

		JSONArray newSharedToJSONArray = dedupeSharedToJSONArray(
			sharedToJSONArray);

		newSharedToJSONArray = filterSharedToJSONArray(newSharedToJSONArray);

		return newSharedToJSONArray;
	}

	protected void setPortletId(String portletId) {
		_portletId = portletId;
	}

	private String _portletId;

}