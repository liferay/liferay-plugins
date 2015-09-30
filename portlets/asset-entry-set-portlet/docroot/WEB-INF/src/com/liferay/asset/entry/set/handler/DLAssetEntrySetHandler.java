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
import com.liferay.asset.entry.set.util.AssetEntrySetImageUtil;
import com.liferay.asset.entry.set.util.PortletKeys;
import com.liferay.asset.entry.set.util.PortletPropsKeys;
import com.liferay.asset.entry.set.util.PortletPropsValues;
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.image.ImageBag;
import com.liferay.portal.kernel.image.ImageToolUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.model.Group;
import com.liferay.portal.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.util.portlet.PortletProps;

import java.io.IOException;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Matthew Kong
 */
public class DLAssetEntrySetHandler extends BaseAssetEntrySetHandler {

	public DLAssetEntrySetHandler(String portletId) {
		super(portletId);
	}

	@Override
	public JSONObject interpret(
			long userId, long assetEntrySetId, JSONObject payloadJSONObject)
		throws PortalException, SystemException {

		JSONObject jsonObject = super.interpret(
			userId, assetEntrySetId, payloadJSONObject);

		Set<Long> assetEntryIds = new HashSet<Long>();
		JSONArray processedImageDataJSONArray =
			JSONFactoryUtil.createJSONArray();

		String[] assetTagNames = StringUtil.split(
			payloadJSONObject.getString(
				AssetEntrySetConstants.PAYLOAD_KEY_ASSET_TAG_NAMES));

		JSONArray imageDataJSONArray = payloadJSONObject.getJSONArray(
			"imageData");

		for (int i = 0; i < imageDataJSONArray.length(); i++) {
			JSONObject processedImageJSONObject =
				JSONFactoryUtil.createJSONObject();

			JSONObject imageJSONObject = imageDataJSONArray.getJSONObject(i);

			long rawFileEntryId = imageJSONObject.getLong(
				AssetEntrySetConstants.IMAGE_TYPE_RAW);

			FileEntry rawFileEntry =
				PortletFileRepositoryUtil.getPortletFileEntry(rawFileEntryId);

			ImageBag imageBag = null;

			try {
				imageBag = ImageToolUtil.read(rawFileEntry.getContentStream());
			}
			catch (IOException ioe) {
				throw new SystemException(ioe);
			}

			for (String imageType :
					PortletPropsValues.ASSET_ENTRY_SET_IMAGE_TYPES) {

				FileEntry fileEntry = null;

				if (imageType.equals(AssetEntrySetConstants.IMAGE_TYPE_RAW)) {
					fileEntry = rawFileEntry;
				}
				else {
					String imageMaxSize = PortletProps.get(
						PortletPropsKeys.ASSET_ENTRY_SET_IMAGE_TYPE,
						new Filter(imageType));

					fileEntry = AssetEntrySetImageUtil.addScaledImageFileEntry(
						userId,
						AssetEntrySetConstants.ASSET_ENTRY_SET_CLASS_NAME_ID,
						0L, PortletKeys.ASSET_ENTRY_SET, imageBag, imageType,
						imageMaxSize);
				}

				DLFileEntry dlFileEntry =
					DLFileEntryLocalServiceUtil.getFileEntry(
						fileEntry.getFileEntryId());

				dlFileEntry.setClassPK(assetEntrySetId);

				dlFileEntry = DLFileEntryLocalServiceUtil.updateDLFileEntry(
					dlFileEntry);

				AssetEntry assetEntry = updateAssetEntry(
					dlFileEntry, assetTagNames);

				assetEntryIds.add(assetEntry.getEntryId());

				processedImageJSONObject =
					AssetEntrySetImageUtil.getImageJSONObject(
						processedImageJSONObject, fileEntry, imageType);
			}

			processedImageDataJSONArray.put(processedImageJSONObject);
		}

		jsonObject.put("assetEntryIds", StringUtil.merge(assetEntryIds));
		jsonObject.put("imageData", processedImageDataJSONArray);

		return jsonObject;
	}

	protected AssetEntry updateAssetEntry(
			DLFileEntry dlFileEntry, String[] assetTagNames)
		throws PortalException, SystemException {

		Group group = GroupLocalServiceUtil.getCompanyGroup(
			dlFileEntry.getCompanyId());

		return AssetEntryLocalServiceUtil.updateEntry(
			dlFileEntry.getUserId(), group.getGroupId(),
			AssetEntrySet.class.getName(), dlFileEntry.getFileEntryId(), null,
			assetTagNames);
	}

}