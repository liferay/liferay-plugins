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
import com.liferay.asset.entry.set.model.AssetEntrySetLike;
import com.liferay.asset.entry.set.model.AssetEntrySetReference;
import com.liferay.asset.entry.set.service.base.AssetEntrySetLocalServiceBaseImpl;
import com.liferay.asset.entry.set.service.persistence.AssetEntrySetFinderUtil;
import com.liferay.asset.entry.set.service.persistence.AssetEntrySetLikePK;
import com.liferay.asset.entry.set.util.AssetEntrySetConstants;
import com.liferay.asset.entry.set.util.AssetEntrySetImageUtil;
import com.liferay.asset.entry.set.util.AssetEntrySetManagerUtil;
import com.liferay.asset.entry.set.util.AssetEntrySetParticipantInfoUtil;
import com.liferay.asset.entry.set.util.PortletKeys;
import com.liferay.asset.entry.set.util.PortletPropsKeys;
import com.liferay.asset.entry.set.util.PortletPropsValues;
import com.liferay.asset.sharing.service.AssetSharingEntryLocalServiceUtil;
import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.util.portlet.PortletProps;

import java.io.File;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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
			boolean privateAssetEntrySet, long stickyTime, int type)
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
		assetEntrySet.setCreatorName(
			AssetEntrySetParticipantInfoUtil.getParticipantName(
				creatorClassNameId, creatorClassPK));

		filterAssetTagNames(payloadJSONObject);

		assetEntrySet.setPayload(
			JSONFactoryUtil.looseSerialize(
				AssetEntrySetManagerUtil.interpret(
					user.getUserId(), assetEntrySetId, payloadJSONObject)));

		assetEntrySet.setPrivateAssetEntrySet(privateAssetEntrySet);
		assetEntrySet.setStickyTime(stickyTime);
		assetEntrySet.setType(type);

		assetEntrySetPersistence.update(assetEntrySet);

		updateChildAssetEntrySetsCount(parentAssetEntrySetId);
		updateModifiedTime(parentAssetEntrySetId, now.getTime());

		updateAssetEntry(
			assetEntrySet,
			StringUtil.split(
				payloadJSONObject.getString(
					AssetEntrySetConstants.PAYLOAD_KEY_ASSET_TAG_NAMES)));

		updateAssetSharingEntries(assetEntrySet);

		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			AssetEntrySet.class);

		indexer.reindex(assetEntrySet);

		return assetEntrySet;
	}

	@Override
	public JSONObject addFileAttachment(long userId, File file)
		throws PortalException, SystemException {

		String extension =
			StringPool.PERIOD + FileUtil.getExtension(file.getName());

		if (ArrayUtil.contains(
				PortletPropsValues.ASSET_ENTRY_SET_IMAGE_EXTENSIONS,
				extension)) {

			return AssetEntrySetImageUtil.addImageFile(
				userId, AssetEntrySetConstants.ASSET_ENTRY_SET_CLASS_NAME_ID,
				0L, PortletKeys.ASSET_ENTRY_SET, file, _imageMaxSizes);
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
			AssetEntrySetConstants.ASSET_ENTRY_SET_CLASS_NAME_ID,
			assetEntrySet.getAssetEntrySetId());

		if (assetEntrySet.getParentAssetEntrySetId() == 0) {
			deleteChildAssetEntrySets(assetEntrySet.getAssetEntrySetId());
		}
		else {
			AssetEntrySet parentAssetEntrySet = fetchAssetEntrySet(
				assetEntrySet.getParentAssetEntrySetId());

			if (parentAssetEntrySet != null) {
				updateAssetSharingEntries(parentAssetEntrySet);

				updateChildAssetEntrySetsCount(
					parentAssetEntrySet.getAssetEntrySetId());
			}
		}

		List<Long> fileEntryIds = getFileEntryIds(
			JSONFactoryUtil.createJSONObject(assetEntrySet.getPayload()));

		for (long fileEntryId : fileEntryIds) {
			DLFileEntryLocalServiceUtil.deleteFileEntry(fileEntryId);
		}

		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			AssetEntrySet.class);

		indexer.delete(assetEntrySet);

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
	public List<AssetEntrySet> getChildAssetEntrySets(
			long parentAssetEntrySetId)
		throws SystemException {

		return assetEntrySetPersistence.findByParentAssetEntrySetId(
			parentAssetEntrySetId);
	}

	@Override
	public List<AssetEntrySet> getNewAssetEntrySets(
			long userId, long time, boolean modifiedTime,
			long parentAssetEntrySetId, long stickyTime,
			JSONArray creatorJSONArray, JSONArray sharedToJSONArray,
			long[] includeAssetEntrySetIds, long[] excludeAssetEntrySetIds,
			String[] assetTagNames, int start, int end)
		throws PortalException, SystemException {

		return getAssetEntrySets(
			userId, time, true, modifiedTime, parentAssetEntrySetId, stickyTime,
			creatorJSONArray, sharedToJSONArray, includeAssetEntrySetIds,
			excludeAssetEntrySetIds, assetTagNames, start, end);
	}

	@Override
	public List<AssetEntrySet> getNewAssetEntrySets(
			long userId, long createTime, long parentAssetEntrySetId,
			long stickyTime, int type, JSONArray sharedToJSONArray,
			String[] assetTagNames, int start, int end)
		throws PortalException, SystemException {

		return getAssetEntrySets(
			userId, createTime, true, parentAssetEntrySetId, stickyTime, type,
			sharedToJSONArray, assetTagNames, start, end);
	}

	@Override
	public List<AssetEntrySet> getNewChildAssetEntrySets(
			long userId, long createTime, long parentAssetEntrySetId, int start,
			int end, OrderByComparator orderByComparator)
		throws PortalException, SystemException {

		List<AssetEntrySet> assetEntrySets =
			assetEntrySetPersistence.findByGtCT_PAESI(
				createTime, parentAssetEntrySetId, start, end,
				orderByComparator);

		return assetEntrySets;
	}

	@Override
	public List<AssetEntrySet> getOldAssetEntrySets(
			long userId, long time, boolean modifiedTime,
			long parentAssetEntrySetId, long stickyTime,
			JSONArray creatorJSONArray, JSONArray sharedToJSONArray,
			long[] includeAssetEntrySetIds, long[] excludeAssetEntrySetIds,
			String[] assetTagNames, int start, int end)
		throws PortalException, SystemException {

		return getAssetEntrySets(
			userId, time, false, modifiedTime, parentAssetEntrySetId,
			stickyTime, creatorJSONArray, sharedToJSONArray,
			includeAssetEntrySetIds, excludeAssetEntrySetIds, assetTagNames,
			start, end);
	}

	@Override
	public List<AssetEntrySet> getOldAssetEntrySets(
			long userId, long createTime, long parentAssetEntrySetId,
			long stickyTime, int type, JSONArray sharedToJSONArray,
			String[] assetTagNames, int start, int end)
		throws PortalException, SystemException {

		return getAssetEntrySets(
			userId, createTime, false, parentAssetEntrySetId, stickyTime, type,
			sharedToJSONArray, assetTagNames, start, end);
	}

	@Override
	public List<AssetEntrySet> getOldChildAssetEntrySets(
			long userId, long createTime, long parentAssetEntrySetId, int start,
			int end, OrderByComparator orderByComparator)
		throws PortalException, SystemException {

		List<AssetEntrySet> assetEntrySets =
			assetEntrySetPersistence.findByLtCT_PAESI(
				createTime, parentAssetEntrySetId, start, end,
				orderByComparator);

		return assetEntrySets;
	}

	@Override
	public AssetEntrySet likeAssetEntrySet(long userId, long assetEntrySetId)
		throws PortalException, SystemException {

		return updateAssetEntrySetLike(userId, assetEntrySetId, true);
	}

	@Override
	public AssetEntrySet unlikeAssetEntrySet(long userId, long assetEntrySetId)
		throws PortalException, SystemException {

		return updateAssetEntrySetLike(userId, assetEntrySetId, false);
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
			boolean privateAssetEntrySet, long stickyTime, int type)
		throws PortalException, SystemException {

		AssetEntrySet assetEntrySet = assetEntrySetPersistence.findByPrimaryKey(
			assetEntrySetId);

		boolean updateAssetSharingEntries = true;

		JSONObject oldPayloadJSONObject = JSONFactoryUtil.createJSONObject(
			assetEntrySet.getPayload());

		List<Long> fileEntryIds = getFileEntryIds(oldPayloadJSONObject);

		for (long fileEntryId : fileEntryIds) {
			DLFileEntry dlFileEntry =
				DLFileEntryLocalServiceUtil.getDLFileEntry(fileEntryId);

			dlFileEntry.setClassPK(0);

			DLFileEntryLocalServiceUtil.updateDLFileEntry(dlFileEntry);
		}

		JSONArray oldSharedToJSONArray = JSONFactoryUtil.createJSONArray(
			oldPayloadJSONObject.getString(
				AssetEntrySetConstants.PAYLOAD_KEY_SHARED_TO));

		payloadJSONObject = AssetEntrySetManagerUtil.interpret(
			assetEntrySet.getUserId(), assetEntrySetId, payloadJSONObject);

		JSONArray sharedTOJSONArray = JSONFactoryUtil.createJSONArray(
			payloadJSONObject.getString(
				AssetEntrySetConstants.PAYLOAD_KEY_SHARED_TO));

		if (Validator.equals(
				oldSharedToJSONArray.toString(),
				sharedTOJSONArray.toString())) {

			updateAssetSharingEntries = false;
		}

		Date now = new Date();

		assetEntrySet.setModifiedTime(now.getTime());

		filterAssetTagNames(payloadJSONObject);

		assetEntrySet.setPayload(
			JSONFactoryUtil.looseSerialize(payloadJSONObject));

		assetEntrySet.setPrivateAssetEntrySet(privateAssetEntrySet);
		assetEntrySet.setStickyTime(stickyTime);
		assetEntrySet.setType(type);

		assetEntrySetPersistence.update(assetEntrySet);

		updateAssetEntry(
			assetEntrySet,
			StringUtil.split(
				payloadJSONObject.getString(
					AssetEntrySetConstants.PAYLOAD_KEY_ASSET_TAG_NAMES)));

		if (updateAssetSharingEntries) {
			updateAssetSharingEntries(assetEntrySet);
		}

		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			AssetEntrySet.class);

		indexer.reindex(assetEntrySet);

		return assetEntrySet;
	}

	protected void deleteChildAssetEntrySets(long parentAssetEntrySetId)
		throws PortalException, SystemException {

		List<AssetEntrySet> childAssetEntrySets = getChildAssetEntrySets(
			parentAssetEntrySetId);

		for (AssetEntrySet assetEntrySet : childAssetEntrySets) {
			deleteAssetEntrySet(assetEntrySet);
		}
	}

	protected void filterAssetTagNames(JSONObject payloadJSONObject) {
		List<String> newAssetTagNames = new ArrayList<String>();

		String[] curAssetTagNames = StringUtil.split(
			payloadJSONObject.getString(
				AssetEntrySetConstants.PAYLOAD_KEY_ASSET_TAG_NAMES));

		for (String assetTagName : curAssetTagNames) {
			if (isValidAssetTagName(assetTagName)) {
				newAssetTagNames.add(assetTagName);
			}
		}

		payloadJSONObject.put(
			AssetEntrySetConstants.PAYLOAD_KEY_ASSET_TAG_NAMES,
			StringUtil.merge(newAssetTagNames));
	}

	protected List<AssetEntrySet> getAssetEntrySets(
			long userId, long time, boolean gtTime, boolean modifiedTime,
			long parentAssetEntrySetId, long stickyTime,
			JSONArray creatorJSONArray, JSONArray sharedToJSONArray,
			long[] includeAssetEntrySetIds, long[] excludeAssetEntrySetIds,
			String[] assetTagNames, int start, int end)
		throws PortalException, SystemException {

		ObjectValuePair<Long, Long> classNameIdAndClassPKOVP =
			AssetEntrySetParticipantInfoUtil.getClassNameIdAndClassPKOVP(
				userId);

		if (!modifiedTime) {
			return assetEntrySetFinder.findByCT_PAESI_ST_CNI(
				classNameIdAndClassPKOVP.getKey(),
				classNameIdAndClassPKOVP.getValue(), time, gtTime,
				parentAssetEntrySetId, stickyTime, creatorJSONArray,
				sharedToJSONArray, includeAssetEntrySetIds,
				excludeAssetEntrySetIds, assetTagNames, start, end);
		}
		else {
			return assetEntrySetFinder.findByMT_PAESI_ST_CNI(
				classNameIdAndClassPKOVP.getKey(),
				classNameIdAndClassPKOVP.getValue(), time, gtTime,
				parentAssetEntrySetId, stickyTime, creatorJSONArray,
				sharedToJSONArray, includeAssetEntrySetIds,
				excludeAssetEntrySetIds, assetTagNames, start, end);
		}
	}

	protected List<AssetEntrySet> getAssetEntrySets(
			long userId, long createTime, boolean gtCreateTime,
			long parentAssetEntrySetId, long stickyTime, int type,
			JSONArray sharedToJSONArray, String[] assetTagNames, int start,
			int end)
		throws PortalException, SystemException {

		ObjectValuePair<Long, Long> classNameIdAndClassPKOVP =
			AssetEntrySetParticipantInfoUtil.getClassNameIdAndClassPKOVP(
				userId);

		return assetEntrySetFinder.findByCT_PAESI_ST_T_CNI(
			classNameIdAndClassPKOVP.getKey(),
			classNameIdAndClassPKOVP.getValue(), createTime, gtCreateTime,
			parentAssetEntrySetId, stickyTime, type, sharedToJSONArray,
			assetTagNames, start, end);
	}

	protected List<Long> getFileEntryIds(JSONObject payloadJSONObject)
		throws PortalException, SystemException {

		List<Long> fileEntryIds = new ArrayList<Long>();

		JSONArray imageDataJSONArray = payloadJSONObject.getJSONArray(
			"imageData");

		if (imageDataJSONArray != null) {
			for (int i = 0; i < imageDataJSONArray.length(); i++) {
				JSONObject imageDataJSONObject =
					imageDataJSONArray.getJSONObject(i);

				JSONObject fileEntryIdsJSONObject =
					imageDataJSONObject.getJSONObject("fileEntryIds");

				Iterator<String> iterator = fileEntryIdsJSONObject.keys();

				while (iterator.hasNext()) {
					String key = iterator.next();

					fileEntryIds.add(fileEntryIdsJSONObject.getLong(key));
				}
			}
		}

		return fileEntryIds;
	}

	protected Map<Long, Set<Long>> getSharedToClassPKsMap(
			AssetEntrySet assetEntrySet)
		throws PortalException, SystemException {

		Map<Long, Set<Long>> sharedToClassPKsMap =
			new LinkedHashMap<Long, Set<Long>>();

		JSONObject payloadJSONObject = JSONFactoryUtil.createJSONObject(
			assetEntrySet.getPayload());

		JSONArray sharedToJSONArray = payloadJSONObject.getJSONArray(
			AssetEntrySetConstants.PAYLOAD_KEY_SHARED_TO);

		if (sharedToJSONArray == null) {
			return sharedToClassPKsMap;
		}

		for (int i = 0; i < sharedToJSONArray.length(); i++) {
			JSONObject sharedToJSONObject = sharedToJSONArray.getJSONObject(i);

			long classNameId = sharedToJSONObject.getLong("classNameId");
			long classPK = sharedToJSONObject.getLong("classPK");

			setSharedToClassPKsMap(sharedToClassPKsMap, classNameId, classPK);
		}

		setSharedToClassPKsMap(
			sharedToClassPKsMap, assetEntrySet.getCreatorClassNameId(),
			assetEntrySet.getCreatorClassPK());

		return sharedToClassPKsMap;
	}

	protected boolean isValidAssetTagName(String assetTagName) {
		if (Validator.isDigit(assetTagName.charAt(0)) ||
			(assetTagName.length() > _ASSET_TAG_NAME_MAX_LENGTH)) {

			return false;
		}

		for (char c : assetTagName.toCharArray()) {
			if (!Validator.isChar(c) && !Validator.isDigit(c) &&
				(c != CharPool.UNDERLINE)) {

				return false;
			}
		}

		return true;
	}

	protected void setSharedToClassPKsMap(
			Map<Long, Set<Long>> sharedToClassPKsMap, long classNameId,
			long classPK)
		throws PortalException, SystemException {

		if (Validator.isNull(
				AssetEntrySetParticipantInfoUtil.getParticipantName(
					classNameId, classPK))) {

			return;
		}

		Set<Long> classNamePks = new HashSet<Long>();

		if (sharedToClassPKsMap.containsKey(classNameId)) {
			classNamePks = sharedToClassPKsMap.get(classNameId);
		}

		classNamePks.add(classPK);

		sharedToClassPKsMap.put(classNameId, classNamePks);
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

	protected AssetEntrySet updateAssetEntrySetLike(
			long userId, long assetEntrySetId, boolean like)
		throws PortalException, SystemException {

		ObjectValuePair<Long, Long> classNameIdAndClassPKOVP =
			AssetEntrySetParticipantInfoUtil.getClassNameIdAndClassPKOVP(
				userId);

		AssetEntrySetLikePK assetEntrySetLikePK = new AssetEntrySetLikePK(
			assetEntrySetId, classNameIdAndClassPKOVP.getKey(),
			classNameIdAndClassPKOVP.getValue());

		if (like) {
			AssetEntrySetLike assetEntrySetLike =
				assetEntrySetLikePersistence.fetchByPrimaryKey(
					assetEntrySetLikePK);

			if (assetEntrySetLike == null) {
				assetEntrySetLike = assetEntrySetLikePersistence.create(
					assetEntrySetLikePK);

				assetEntrySetLikePersistence.update(assetEntrySetLike);
			}
		}
		else {
			assetEntrySetLikePersistence.remove(assetEntrySetLikePK);
		}

		int assetEntrySetLikesCount =
			assetEntrySetLikePersistence.countByAssetEntrySetId(
				assetEntrySetId);

		AssetEntrySet assetEntrySet =
			assetEntrySetPersistence.fetchByPrimaryKey(assetEntrySetId);

		assetEntrySet.setAssetEntrySetLikesCount(assetEntrySetLikesCount);

		assetEntrySetPersistence.update(assetEntrySet);

		return assetEntrySet;
	}

	protected void updateAssetSharingEntries(AssetEntrySet assetEntrySet)
		throws PortalException, SystemException {

		AssetSharingEntryLocalServiceUtil.deleteAssetSharingEntries(
			AssetEntrySetConstants.ASSET_ENTRY_SET_CLASS_NAME_ID,
			assetEntrySet.getAssetEntrySetId());

		Map<Long, Set<Long>> sharedToClassPKsMap = getSharedToClassPKsMap(
			assetEntrySet);

		if (assetEntrySet.getParentAssetEntrySetId() == 0) {
			List<AssetEntrySetReference>
				assetEntrySetReferences =
					AssetEntrySetFinderUtil.
						findAssetEntrySetReferenceByPAESI_CNI(
							assetEntrySet.getAssetEntrySetId());

			for (AssetEntrySetReference assetEntrySetReference :
					assetEntrySetReferences) {

				setSharedToClassPKsMap(
					sharedToClassPKsMap,
					assetEntrySetReference.getSharedToClassNameId(),
					assetEntrySetReference.getSharedToClassPK());
			}
		}

		AssetSharingEntryLocalServiceUtil.addAssetSharingEntries(
			AssetEntrySetConstants.ASSET_ENTRY_SET_CLASS_NAME_ID,
			assetEntrySet.getAssetEntrySetId(), sharedToClassPKsMap);

		if (assetEntrySet.getParentAssetEntrySetId() > 0) {
			AssetEntrySet parentAssetEntrySet = getAssetEntrySet(
				assetEntrySet.getParentAssetEntrySetId());

			updateAssetSharingEntries(parentAssetEntrySet);
		}
	}

	protected void updateChildAssetEntrySetsCount(long assetEntrySetId)
		throws PortalException, SystemException {

		if (assetEntrySetId == 0) {
			return;
		}

		AssetEntrySet assetEntrySet = assetEntrySetPersistence.findByPrimaryKey(
			assetEntrySetId);

		int childAssetEntrySetsCount =
			assetEntrySetPersistence.countByParentAssetEntrySetId(
				assetEntrySetId);

		assetEntrySet.setChildAssetEntrySetsCount(childAssetEntrySetsCount);

		assetEntrySetPersistence.update(assetEntrySet);
	}

	protected void updateModifiedTime(long assetEntrySetId, long modifiedTime)
		throws PortalException, SystemException {

		if (assetEntrySetId == 0) {
			return;
		}

		AssetEntrySet assetEntrySet = assetEntrySetPersistence.findByPrimaryKey(
			assetEntrySetId);

		assetEntrySet.setModifiedTime(modifiedTime);

		assetEntrySetPersistence.update(assetEntrySet);
	}

	private static final int _ASSET_TAG_NAME_MAX_LENGTH = 75;

	private static Map<String, String> _imageMaxSizes =
		new HashMap<String, String>();

	static {
		for (String imageType :
				PortletPropsValues.ASSET_ENTRY_SET_IMAGE_TYPES) {

			String imageMaxSize = PortletProps.get(
				PortletPropsKeys.ASSET_ENTRY_SET_IMAGE_MAX_SIZE,
				new Filter(imageType));

			_imageMaxSizes.put(imageType, imageMaxSize);
		}
	}

}