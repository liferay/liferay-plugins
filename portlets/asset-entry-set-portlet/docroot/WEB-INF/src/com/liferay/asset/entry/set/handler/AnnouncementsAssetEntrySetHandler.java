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
import com.liferay.portal.kernel.json.JSONFactoryUtil;
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
	public String interpret(JSONObject payload)
		throws PortalException, SystemException {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		long userId = payload.getLong("userId");
		long classNameId = payload.getLong("classNameId");
		long classPK = payload.getLong("classPK");
		String title = payload.getString("title");
		String content = payload.getString("content");
		String url = payload.getString("url");
		String type = payload.getString("type");

		String displayDateString = payload.getString("displayDate");

		Date displayDate = GetterUtil.getDate(
			displayDateString, DateUtil.getISOFormat(displayDateString));

		Calendar displayCalendar = getCalendar(displayDate);

		boolean displayImmediately = payload.getBoolean("displayImmediately");

		String expirationDateString = payload.getString("expirationDate");

		Date expirationDate = GetterUtil.getDate(
			expirationDateString, DateUtil.getISOFormat(expirationDateString));

		Calendar expirationCalendar = getCalendar(expirationDate);

		int priority = payload.getInt("priority");
		boolean alert = payload.getBoolean("alert");

		AnnouncementsEntry announcementEntry =
			AnnouncementsEntryLocalServiceUtil.addEntry(
				userId, classNameId, classPK, title, content, url, type,
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
				payload.getString(
					AssetEntrySetConstants.PAYLOAD_KEY_ASSET_TAG_NAMES)));

		jsonObject.put("assetEntryIds", assetEntry.getEntryId());

		jsonObject.put("message", payload.getString("message"));
		jsonObject.put("type", payload.getString("type"));
		jsonObject.put("url", payload.getString("url"));

		jsonObject.put(
			AssetEntrySetConstants.PAYLOAD_KEY_SHARED_TO_CLASS_PKS_MAP,
			payload.getJSONObject(
				AssetEntrySetConstants.PAYLOAD_KEY_SHARED_TO_CLASS_PKS_MAP));

		return JSONFactoryUtil.looseSerialize(jsonObject);
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