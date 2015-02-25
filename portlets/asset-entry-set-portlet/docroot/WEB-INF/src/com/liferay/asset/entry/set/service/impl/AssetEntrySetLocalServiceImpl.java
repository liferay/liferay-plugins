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

package com.liferay.asset.entry.set.service.impl;

import com.liferay.asset.entry.set.model.AssetEntrySet;
import com.liferay.asset.entry.set.participant.AssetEntrySetParticipantInfoUtil;
import com.liferay.asset.entry.set.service.base.AssetEntrySetLocalServiceBaseImpl;
import com.liferay.asset.entry.set.util.AssetEntrySetConstants;
import com.liferay.asset.entry.set.util.AssetEntrySetManagerUtil;
import com.liferay.asset.entry.set.util.PortletKeys;
import com.liferay.asset.entry.set.util.PortletPropsKeys;
import com.liferay.asset.entry.set.util.PortletPropsValues;
import com.liferay.asset.sharing.service.AssetSharingEntryLocalServiceUtil;
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
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.documentlibrary.util.DLUtil;
import com.liferay.portlet.ratings.model.RatingsStats;
import com.liferay.util.portlet.PortletProps;

import java.awt.image.RenderedImage;

import java.io.File;
import java.io.IOException;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Calvin Keum
 * @author Sherry Yang
 */
public class AssetEntrySetLocalServiceImpl
	extends AssetEntrySetLocalServiceBaseImpl {

	@Override
	public AssetEntrySet addAssetEntrySet(
			long userId, long parentAssetEntrySetId, long creatorClassNameId,
			long creatorClassPK, JSONObject payloadJSONObject,
			boolean privateAssetEntrySet)
		throws PortalException, SystemException {

		long assetEntrySetId = counterLocalService.increment();

		AssetEntrySet assetEntrySet = assetEntrySetPersistence.create(
			assetEntrySetId);

		User user = userPersistence.findByPrimaryKey(userId);

		assetEntrySet.setCompanyId(user.getCompanyId());
		assetEntrySet.setUserId(user.getUserId());

		Date now = new Date();

		assetEntrySet.setCreateTime(now.getTime());
		assetEntrySet.setModifiedTime(now.getTime());

		assetEntrySet.setParentAssetEntrySetId(parentAssetEntrySetId);
		assetEntrySet.setCreatorClassNameId(creatorClassNameId);
		assetEntrySet.setCreatorClassPK(creatorClassPK);
		assetEntrySet.setPayload(
			JSONFactoryUtil.looseSerialize(
				AssetEntrySetManagerUtil.interpret(
					payloadJSONObject, assetEntrySetId)));
		assetEntrySet.setPrivateAssetEntrySet(privateAssetEntrySet);

		assetEntrySetPersistence.update(assetEntrySet);

		updateChildAssetEntrySetsCount(parentAssetEntrySetId);

		updateAssetEntry(
			assetEntrySet,
			StringUtil.split(
				payloadJSONObject.getString(
					AssetEntrySetConstants.PAYLOAD_KEY_ASSET_TAG_NAMES)));

		Map<Long, long[]> sharedToClassPKsMap = getSharedToClassPKsMap(
			payloadJSONObject);

		addCreatorToSharedToClassPKsMap(
			sharedToClassPKsMap, creatorClassNameId, creatorClassPK);

		AssetSharingEntryLocalServiceUtil.addAssetSharingEntries(
			_ASSET_ENTRY_SET_CLASS_NAME_ID, assetEntrySetId,
			sharedToClassPKsMap);

		setParticipants(assetEntrySet);

		return assetEntrySet;
	}

	public JSONObject addFileAttachment(long userId, File file)
		throws PortalException, SystemException {

		String extension =
			StringPool.PERIOD + FileUtil.getExtension(file.getName());

		if (ArrayUtil.contains(
				PortletPropsValues.ASSET_ENTRY_SET_IMAGE_EXTENSIONS,
				extension)) {

			return addImageFile(userId, file);
		}

		return JSONFactoryUtil.createJSONObject();
	}

	@Override
	public AssetEntrySet deleteAssetEntrySet(AssetEntrySet assetEntrySet)
		throws PortalException, SystemException {

		assetEntrySetPersistence.remove(assetEntrySet);

		assetEntryLocalService.deleteEntry(
			AssetEntrySet.class.getName(), assetEntrySet.getAssetEntrySetId());

		AssetSharingEntryLocalServiceUtil.deleteAssetSharingEntries(
			_ASSET_ENTRY_SET_CLASS_NAME_ID, assetEntrySet.getAssetEntryId());

		return assetEntrySet;
	}

	@Override
	public AssetEntrySet deleteAssetEntrySet(long assetEntrySetId)
		throws PortalException, SystemException {

		AssetEntrySet assetEntrySet = assetEntrySetPersistence.findByPrimaryKey(
			assetEntrySetId);

		return deleteAssetEntrySet(assetEntrySet);
	}

	@Override
	public List<AssetEntrySet> getAssetEntrySets(
			JSONArray sharedToJSONArray, int childAssetEntrySetsLimit,
			int start, int end)
		throws PortalException, SystemException {

		List<AssetEntrySet> assetEntrySets = assetEntrySetFinder.findBySharedTo(
			sharedToJSONArray, start, end);

		setDisplayFields(assetEntrySets, childAssetEntrySetsLimit);

		return assetEntrySets;
	}

	@Override
	public List<AssetEntrySet> getAssetEntrySets(
			long creatorClassNameId, long creatorClassPK, String assetTagName,
			JSONArray sharedToJSONArray, boolean andOperator,
			int childAssetEntrySetsLimit, int start, int end)
		throws PortalException, SystemException {

		List<AssetEntrySet> assetEntrySets =
			assetEntrySetFinder.findByCCNI_CCPK_ATN(
				creatorClassNameId, creatorClassPK, assetTagName,
				sharedToJSONArray, andOperator, start, end);

		setDisplayFields(assetEntrySets, childAssetEntrySetsLimit);

		return assetEntrySets;
	}

	@Override
	public List<AssetEntrySet> getAssetEntrySets(
			long creatorClassNameId, String assetTagName,
			JSONArray sharedToJSONArray, int childAssetEntrySetsLimit,
			int start, int end)
		throws PortalException, SystemException {

		List<AssetEntrySet> assetEntrySets = assetEntrySetFinder.findByCCNI_ATN(
			creatorClassNameId, assetTagName, sharedToJSONArray, start, end);

		setDisplayFields(assetEntrySets, childAssetEntrySetsLimit);

		return assetEntrySets;
	}

	@Override
	public int getAssetEntrySetsCount(JSONArray sharedToJSONArray)
		throws PortalException, SystemException {

		return assetEntrySetFinder.countBySharedTo(sharedToJSONArray);
	}

	@Override
	public int getAssetEntrySetsCount(
			long creatorClassNameId, long creatorClassPK, String assetTagName,
			JSONArray sharedToJSONArray, boolean andOperator)
		throws PortalException, SystemException {

		return assetEntrySetFinder.countByCCNI_CCPK_ATN(
			creatorClassNameId, creatorClassPK, assetTagName, sharedToJSONArray,
			andOperator);
	}

	@Override
	public int getAssetEntrySetsCount(
			long creatorClassNameId, String assetTagName,
			JSONArray sharedToJSONArray)
		throws PortalException, SystemException {

		return assetEntrySetFinder.countByCCNI_ATN(
			creatorClassNameId, assetTagName, sharedToJSONArray);
	}

	@Override
	public List<AssetEntrySet> getChildAssetEntrySets(
			long parentAssetEntrySetId, int start, int end,
			OrderByComparator orderByComparator)
		throws PortalException, SystemException {

		List<AssetEntrySet> assetEntrySets =
			assetEntrySetPersistence.findByParentAssetEntrySetId(
				parentAssetEntrySetId, start, end, orderByComparator);

		setParticipants(assetEntrySets);

		return assetEntrySets;
	}

	@Override
	public List<AssetEntrySet> getNewAssetEntrySets(
			long createTime, long parentAssetEntrySetId,
			JSONArray sharedToJSONArray, int childAssetEntrySetsLimit,
			int start, int end)
		throws PortalException, SystemException {

		return getAssetEntrySets(
			createTime, true, parentAssetEntrySetId, sharedToJSONArray,
			childAssetEntrySetsLimit, start, end);
	}

	@Override
	public List<AssetEntrySet> getOldAssetEntrySets(
			long createTime, long parentAssetEntrySetId,
			JSONArray sharedToJSONArray, int childAssetEntrySetsLimit,
			int start, int end)
		throws PortalException, SystemException {

		return getAssetEntrySets(
			createTime, false, parentAssetEntrySetId, sharedToJSONArray,
			childAssetEntrySetsLimit, start, end);
	}

	@Override
	public AssetEntrySet likeAssetEntrySet(long userId, long assetEntrySetId)
		throws PortalException, SystemException {

		return updateRatingsStatsTotalScore(userId, assetEntrySetId, 1);
	}

	@Override
	public AssetEntrySet unlikeAssetEntrySet(long userId, long assetEntrySetId)
		throws PortalException, SystemException {

		return updateRatingsStatsTotalScore(userId, assetEntrySetId, 0);
	}

	@Override
	public void updateAssetEntry(long assetEntrySetId, String[] assetTagNames)
		throws PortalException, SystemException {

		AssetEntrySet assetEntrySet = assetEntrySetPersistence.findByPrimaryKey(
			assetEntrySetId);

		updateAssetEntry(assetEntrySet, assetTagNames);
	}

	@Override
	public AssetEntrySet updateAssetEntrySet(
			long assetEntrySetId, JSONObject payloadJSONObject,
			boolean privateAssetEntrySet)
		throws PortalException, SystemException {

		AssetEntrySet assetEntrySet = assetEntrySetPersistence.findByPrimaryKey(
			assetEntrySetId);

		Date now = new Date();

		assetEntrySet.setModifiedTime(now.getTime());

		assetEntrySet.setPayload(
			JSONFactoryUtil.looseSerialize(
				AssetEntrySetManagerUtil.interpret(
					payloadJSONObject, assetEntrySetId)));
		assetEntrySet.setPrivateAssetEntrySet(privateAssetEntrySet);

		assetEntrySetPersistence.update(assetEntrySet);

		updateAssetEntry(
			assetEntrySet,
			StringUtil.split(
				payloadJSONObject.getString(
					AssetEntrySetConstants.PAYLOAD_KEY_ASSET_TAG_NAMES)));

		AssetSharingEntryLocalServiceUtil.deleteAssetSharingEntries(
			_ASSET_ENTRY_SET_CLASS_NAME_ID, assetEntrySetId);

		Map<Long, long[]> sharedToClassPKsMap = getSharedToClassPKsMap(
			payloadJSONObject);

		addCreatorToSharedToClassPKsMap(
			sharedToClassPKsMap, assetEntrySet.getCreatorClassNameId(),
			assetEntrySet.getCreatorClassPK());

		AssetSharingEntryLocalServiceUtil.addAssetSharingEntries(
			_ASSET_ENTRY_SET_CLASS_NAME_ID, assetEntrySetId,
			sharedToClassPKsMap);

		setParticipants(assetEntrySet);

		return assetEntrySet;
	}

	protected void addCreatorToSharedToClassPKsMap(
		Map<Long, long[]> sharedToClassPKsMap, long creatorClassNameId,
		long creatorClassPK) {

		long[] sharedToParticipantIds = sharedToClassPKsMap.get(
			creatorClassNameId);

		if (sharedToParticipantIds == null) {
			sharedToClassPKsMap.put(
				creatorClassNameId, new long[] {creatorClassPK});
		}
		else if (!ArrayUtil.contains(sharedToParticipantIds, creatorClassPK)) {
			sharedToClassPKsMap.put(
				creatorClassNameId,
				ArrayUtil.append(sharedToParticipantIds, creatorClassPK));
		}
	}

	protected FileEntry addFileEntry(long userId, File file, String type)
		throws PortalException, SystemException {

		User user = userLocalService.getUser(userId);

		String fileName =
			Calendar.getInstance().getTimeInMillis() + type + file.getName();

		return PortletFileRepositoryUtil.addPortletFileEntry(
			user.getGroupId(), userId, AssetEntrySet.class.getName(), 0L,
			PortletKeys.ASSET_ENTRY_SET, 0L, file, fileName, null, false);
	}

	protected JSONObject addImageFile(long userId, File file)
		throws PortalException, SystemException {

		JSONObject imageJSONObject = JSONFactoryUtil.createJSONObject();

		Set<Long> fileEntryIds = new HashSet<Long>();

		FileEntry rawFileEntry = addFileEntry(userId, file, StringPool.BLANK);

		fileEntryIds.add(rawFileEntry.getFileEntryId());

		for (String imageType :
				PortletPropsValues.ASSET_ENTRY_SET_IMAGE_TYPES) {

			FileEntry fileEntry = addImageFileEntry(
				userId, file, rawFileEntry, imageType);

			fileEntryIds.add(fileEntry.getFileEntryId());

			imageJSONObject.put(
				"imageURL" + StringPool.UNDERLINE + imageType,
				DLUtil.getPreviewURL(
					fileEntry, fileEntry.getFileVersion(), null,
					StringPool.BLANK, false, true));
		}

		imageJSONObject.put("fileEntryIds", StringUtil.merge(fileEntryIds));
		imageJSONObject.put(
			"imageURL_raw",
			DLUtil.getPreviewURL(
				rawFileEntry, rawFileEntry.getFileVersion(), null,
				StringPool.BLANK, false, true));
		imageJSONObject.put("name", rawFileEntry.getTitle());

		return imageJSONObject;
	}

	protected FileEntry addImageFileEntry(
			long userId, File file, FileEntry rawFileEntry, String imageType)
		throws PortalException, SystemException {

		ImageBag imageBag = null;

		try {
			imageBag = ImageToolUtil.read(file);
		}
		catch (IOException ioe) {
			throw new SystemException(ioe);
		}

		RenderedImage rawRenderedImage = imageBag.getRenderedImage();

		String imageMaxSize = PortletProps.get(
			PortletPropsKeys.ASSET_ENTRY_SET_IMAGE_TYPE, new Filter(imageType));

		String[] maxDimensions = imageMaxSize.split("x");

		RenderedImage scaledRenderedImage = ImageToolUtil.scale(
			rawRenderedImage, GetterUtil.getInteger(maxDimensions[0]),
			GetterUtil.getInteger(maxDimensions[1]));

		if ((rawRenderedImage.getWidth() == scaledRenderedImage.getWidth()) &&
			(rawRenderedImage.getHeight() == scaledRenderedImage.getHeight())) {

			return rawFileEntry;
		}

		File scaledFile = null;

		try {
			scaledFile = FileUtil.createTempFile(
				ImageToolUtil.getBytes(
					scaledRenderedImage, imageBag.getType()));

			return addFileEntry(userId, scaledFile, imageType);
		}
		catch (IOException ioe) {
			throw new SystemException(ioe);
		}
		finally {
			FileUtil.delete(scaledFile);
		}
	}

	protected List<AssetEntrySet> getAssetEntrySets(
			long createTime, boolean gtCreateTime, long parentAssetEntrySetId,
			JSONArray sharedToJSONArray, int childAssetEntrySetsLimit,
			int start, int end)
		throws PortalException, SystemException {

		List<AssetEntrySet> assetEntrySets =
			assetEntrySetFinder.findByCT_PASEI(
				createTime, gtCreateTime, parentAssetEntrySetId,
				sharedToJSONArray, start, end);

		setDisplayFields(assetEntrySets, childAssetEntrySetsLimit);

		return assetEntrySets;
	}

	protected JSONObject getCreatorJSONObject(
			long creatorClassNameId, long creatorClassPK)
		throws PortalException, SystemException {

		return AssetEntrySetParticipantInfoUtil.getParticipantJSONObject(
			JSONFactoryUtil.createJSONObject(), creatorClassNameId,
			creatorClassPK, true);
	}

	protected Map<Long, long[]> getSharedToClassPKsMap(
		JSONObject payloadJSONObject) {

		Map<Long, long[]> sharedToClassPKsMap =
			new LinkedHashMap<Long, long[]>();

		JSONArray sharedToJSONArray = payloadJSONObject.getJSONArray(
			AssetEntrySetConstants.PAYLOAD_KEY_SHARED_TO);

		if (sharedToJSONArray == null) {
			return sharedToClassPKsMap;
		}

		for (int i = 0; i < sharedToJSONArray.length(); i++) {
			JSONObject sharedToJSONObject = sharedToJSONArray.getJSONObject(i);

			long classNameId = sharedToJSONObject.getLong("classNameId");
			long classPK = sharedToJSONObject.getLong("classPK");

			if (sharedToClassPKsMap.containsKey(classNameId)) {
				sharedToClassPKsMap.put(
					classNameId,
					ArrayUtil.append(
						sharedToClassPKsMap.get(classNameId), classPK));
			}
			else {
				sharedToClassPKsMap.put(classNameId, new long[] {classPK});
			}
		}

		return sharedToClassPKsMap;
	}

	protected JSONArray getSharedToJSONArray(JSONObject payloadJSONObject)
		throws PortalException, SystemException {

		JSONArray returnedSharedToJSONArray = JSONFactoryUtil.createJSONArray();

		JSONArray payloadSharedToJSONArray =
			payloadJSONObject.getJSONArray(
				AssetEntrySetConstants.PAYLOAD_KEY_SHARED_TO);

		if (payloadSharedToJSONArray == null) {
			return returnedSharedToJSONArray;
		}

		for (int i = 0; i < payloadSharedToJSONArray.length(); i++) {
			JSONObject participantJSONObject =
				payloadSharedToJSONArray.getJSONObject(i);

			long classNameId = participantJSONObject.getLong("classNameId");
			long classPK = participantJSONObject.getLong("classPK");

			returnedSharedToJSONArray.put(
				AssetEntrySetParticipantInfoUtil.getParticipantJSONObject(
					participantJSONObject, classNameId, classPK, false));
		}

		return returnedSharedToJSONArray;
	}

	protected void setChildAssetEntrySets(
			AssetEntrySet assetEntrySet, int childAssetEntrySetsLimit)
		throws PortalException, SystemException {

		if (childAssetEntrySetsLimit <= 0) {
			return;
		}

		List<AssetEntrySet> childAssetEntrySets = getChildAssetEntrySets(
			assetEntrySet.getAssetEntrySetId(), 0, childAssetEntrySetsLimit,
			null);

		JSONArray childAssetEntrySetsJSONArray =
			JSONFactoryUtil.createJSONArray();

		for (AssetEntrySet childAssetEntrySet : childAssetEntrySets) {
			JSONObject childAssetEntrySetPayload =
				JSONFactoryUtil.createJSONObject(
					childAssetEntrySet.getPayload());

			childAssetEntrySetPayload.put(
				"assetEntrySetId", childAssetEntrySet.getAssetEntrySetId());
			childAssetEntrySetPayload.put(
				"modifiedTime", childAssetEntrySet.getModifiedTime());

			childAssetEntrySetsJSONArray.put(childAssetEntrySetPayload);
		}

		JSONObject payloadJSONObject = JSONFactoryUtil.createJSONObject(
			assetEntrySet.getPayload());

		payloadJSONObject.put(
			AssetEntrySetConstants.PAYLOAD_KEY_CHILD_ASSET_ENTRY_SETS,
			childAssetEntrySetsJSONArray);

		assetEntrySet.setPayload(
			JSONFactoryUtil.looseSerialize(payloadJSONObject));
	}

	protected void setDisplayFields(
			List<AssetEntrySet> assetEntrySets, int childAssetEntrySetsLimit)
		throws PortalException, SystemException {

		for (AssetEntrySet assetEntrySet : assetEntrySets) {
			setChildAssetEntrySets(assetEntrySet, childAssetEntrySetsLimit);

			setParticipants(assetEntrySet);
		}
	}

	protected void setParticipants(AssetEntrySet assetEntrySet)
		throws PortalException, SystemException {

		JSONObject payloadJSONObject = JSONFactoryUtil.createJSONObject(
			assetEntrySet.getPayload());

		JSONObject creatorJSONObject = getCreatorJSONObject(
			assetEntrySet.getCreatorClassNameId(),
			assetEntrySet.getCreatorClassPK());

		payloadJSONObject.put(
			AssetEntrySetConstants.PAYLOAD_KEY_CREATOR, creatorJSONObject);

		JSONArray sharedToJSONArray = getSharedToJSONArray(payloadJSONObject);

		payloadJSONObject.put(
			AssetEntrySetConstants.PAYLOAD_KEY_SHARED_TO, sharedToJSONArray);

		assetEntrySet.setPayload(
			JSONFactoryUtil.looseSerialize(payloadJSONObject));
	}

	protected void setParticipants(List<AssetEntrySet> assetEntrySets)
		throws PortalException, SystemException {

		for (AssetEntrySet assetEntrySet : assetEntrySets) {
			setParticipants(assetEntrySet);
		}
	}

	protected void updateAssetEntry(
			AssetEntrySet assetEntrySet, String[] assetTagNames)
		throws PortalException, SystemException {

		Group group = groupLocalService.getCompanyGroup(
			assetEntrySet.getCompanyId());

		assetEntryLocalService.updateEntry(
			assetEntrySet.getUserId(), group.getGroupId(),
			AssetEntrySet.class.getName(), assetEntrySet.getAssetEntrySetId(),
			null, assetTagNames);
	}

	protected void updateChildAssetEntrySetsCount(long parentAssetEntrySetId)
		throws PortalException, SystemException {

		if (parentAssetEntrySetId == 0) {
			return;
		}

		AssetEntrySet assetEntrySet = assetEntrySetPersistence.findByPrimaryKey(
			parentAssetEntrySetId);

		int childAssetEntrySetsCount =
			assetEntrySetPersistence.countByParentAssetEntrySetId(
				parentAssetEntrySetId);

		assetEntrySet.setChildAssetEntrySetsCount(childAssetEntrySetsCount);

		assetEntrySetPersistence.update(assetEntrySet);
	}

	protected AssetEntrySet updateRatingsStatsTotalScore(
			long userId, long assetEntrySetId, long score)
		throws PortalException, SystemException {

		String className = AssetEntrySet.class.getName();

		ratingsEntryLocalService.updateEntry(
			userId, className, assetEntrySetId, score, new ServiceContext());

		AssetEntrySet assetEntrySet = assetEntrySetPersistence.findByPrimaryKey(
			assetEntrySetId);

		RatingsStats ratingsStats = ratingsStatsLocalService.getStats(
			className, assetEntrySetId);

		assetEntrySet.setRatingsStatsTotalScore(
			(int)ratingsStats.getTotalScore());

		assetEntrySetPersistence.update(assetEntrySet);

		return assetEntrySet;
	}

	private static final long _ASSET_ENTRY_SET_CLASS_NAME_ID =
		ClassNameLocalServiceUtil.getClassNameId(AssetEntrySet.class);

	private static final long _USER_CLASS_NAME_ID =
		ClassNameLocalServiceUtil.getClassNameId(User.class);

}