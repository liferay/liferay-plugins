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

package com.liferay.screens.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.ratings.model.RatingsEntry;
import com.liferay.screens.service.base.ScreensRatingsEntryServiceBaseImpl;

import java.util.List;

/**
 * @author José Manuel Navarro
 */
public class ScreensRatingsEntryServiceImpl
	extends ScreensRatingsEntryServiceBaseImpl {

	@Override
	public JSONObject deleteRatingsEntry(
			long classPK, String className, int ratingsLength)
		throws PortalException, SystemException {

		ratingsEntryService.deleteEntry(className, classPK);

		return getRatingsEntries(classPK, className, ratingsLength);
	}

	@Override
	public JSONObject getRatingsEntries(long assetEntryId, int ratingsLength)
		throws PortalException, SystemException {

		AssetEntry assetEntry = assetEntryService.getEntry(assetEntryId);

		return getRatingsEntries(
			assetEntry.getClassPK(), assetEntry.getClassName(), ratingsLength);
	}

	@Override
	public JSONObject getRatingsEntries(
			long classPK, String className, int ratingsLength)
		throws PortalException, SystemException {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		int[] ratings = new int[ratingsLength];
		int ratingsDefaultNumberOfStars = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.RATINGS_DEFAULT_NUMBER_OF_STARS));
		double totalScore = 0;
		double userScore = -1;

		List<RatingsEntry> ratingsEntries = ratingsEntryLocalService.getEntries(
			className, classPK);

		for (RatingsEntry ratingsEntry : ratingsEntries) {
			double score =
				ratingsEntry.getScore() / ratingsDefaultNumberOfStars;

			int index = (int)(score * ratingsLength);

			if (index == ratingsLength) {
				index--;
			}

			ratings[index]++;

			totalScore += score;

			if (ratingsEntry.getUserId() == getUserId()) {
				userScore = score;
			}
		}

		if (!ratingsEntries.isEmpty()) {
			jsonObject.put("average", totalScore / ratingsEntries.size());
		}
		else {
			jsonObject.put("average", 0);
		}

		jsonObject.put("className", className);
		jsonObject.put("classPK", classPK);

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (int rating : ratings) {
			jsonArray.put(rating);
		}

		jsonObject.put("ratings", jsonArray);

		jsonObject.put("totalCount", ratingsEntries.size());
		jsonObject.put("totalScore", totalScore);
		jsonObject.put("userScore", userScore);

		return jsonObject;
	}

	@Override
	public JSONObject updateRatingsEntry(
			long classPK, String className, double score, int ratingsLength)
		throws PortalException, SystemException {

		ratingsEntryService.updateEntry(className, classPK, score);

		return getRatingsEntries(classPK, className, ratingsLength);
	}

	private static Log _log = LogFactoryUtil.getLog(
		ScreensRatingsEntryServiceImpl.class);

}