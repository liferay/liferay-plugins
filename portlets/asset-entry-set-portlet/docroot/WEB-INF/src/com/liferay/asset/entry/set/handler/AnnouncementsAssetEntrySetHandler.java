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
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portlet.announcements.model.AnnouncementsEntry;
import com.liferay.portlet.announcements.service.AnnouncementsEntryLocalServiceUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Calvin Keum
 */
public class AnnouncementsAssetEntrySetHandler
	extends BaseAssetEntrySetHandler {

	public AnnouncementsAssetEntrySetHandler(String portletId) {
		super(portletId);
	}

	@Override
	public JSONObject interpret(
			JSONObject payloadJSONObject, long assetEntrySetId)
		throws PortalException, SystemException {

		JSONObject jsonObject = super.interpret(
			payloadJSONObject, assetEntrySetId);

		long userId = payloadJSONObject.getLong("userId");
		long classNameId = payloadJSONObject.getLong("classNameId");
		String title = payloadJSONObject.getString("title");
		String content = payloadJSONObject.getString("content");
		String url = payloadJSONObject.getString("url");
		String type = payloadJSONObject.getString("type");

		String displayDateString = payloadJSONObject.getString("displayDate");

		Date displayDate = GetterUtil.getDate(
			displayDateString, DateUtil.getISOFormat(displayDateString));

		Calendar displayCalendar = getCalendar(displayDate);

		boolean displayImmediately = payloadJSONObject.getBoolean(
			"displayImmediately");

		String expirationDateString = payloadJSONObject.getString(
			"expirationDate");

		Date expirationDate = GetterUtil.getDate(
			expirationDateString, DateUtil.getISOFormat(expirationDateString));

		Calendar expirationCalendar = getCalendar(expirationDate);

		int priority = payloadJSONObject.getInt("priority");
		boolean alert = payloadJSONObject.getBoolean("alert");

		AnnouncementsEntry announcementEntry =
			AnnouncementsEntryLocalServiceUtil.addEntry(
				userId, classNameId, assetEntrySetId, title, content, url, type,
				displayCalendar.get(Calendar.MONTH),
				displayCalendar.get(Calendar.DATE),
				displayCalendar.get(Calendar.YEAR),
				displayCalendar.get(Calendar.HOUR_OF_DAY),
				displayCalendar.get(Calendar.MINUTE), displayImmediately,
				expirationCalendar.get(Calendar.MONTH),
				expirationCalendar.get(Calendar.DATE),
				expirationCalendar.get(Calendar.YEAR),
				expirationCalendar.get(Calendar.HOUR_OF_DAY),
				expirationCalendar.get(Calendar.MINUTE), priority, alert);

		AssetEntry assetEntry = updateAssetEntry(
			announcementEntry,
			StringUtil.split(
				payloadJSONObject.getString(
					AssetEntrySetConstants.PAYLOAD_KEY_ASSET_TAG_NAMES)));

		jsonObject.put("assetEntryIds", assetEntry.getEntryId());

		return jsonObject;
	}

	protected Calendar getCalendar(Date date) {
		Calendar calendar = new GregorianCalendar();

		calendar.setTime(date);

		return calendar;
	}

	protected AssetEntry updateAssetEntry(
			AnnouncementsEntry announcementEntry, String[] assetTagNames)
		throws PortalException, SystemException {

		Group group = GroupLocalServiceUtil.getCompanyGroup(
			announcementEntry.getCompanyId());

		return AssetEntryLocalServiceUtil.updateEntry(
			announcementEntry.getUserId(), group.getGroupId(),
			AssetEntrySet.class.getName(), announcementEntry.getEntryId(), null,
			assetTagNames);
	}

}