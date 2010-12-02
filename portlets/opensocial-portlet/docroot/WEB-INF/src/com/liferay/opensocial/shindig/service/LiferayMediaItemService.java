/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.opensocial.shindig.service;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.portlet.social.model.SocialRelationConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.shindig.auth.SecurityToken;
import org.apache.shindig.common.util.ImmediateFuture;
import org.apache.shindig.protocol.ProtocolException;
import org.apache.shindig.protocol.RestfulCollection;
import org.apache.shindig.social.core.model.AddressImpl;
import org.apache.shindig.social.core.model.MediaItemImpl;
import org.apache.shindig.social.opensocial.model.Address;
import org.apache.shindig.social.opensocial.model.MediaItem.Type;
import org.apache.shindig.social.opensocial.model.MediaItem;
import org.apache.shindig.social.opensocial.spi.CollectionOptions;
import org.apache.shindig.social.opensocial.spi.GroupId;
import org.apache.shindig.social.opensocial.spi.MediaItemService;
import org.apache.shindig.social.opensocial.spi.UserId;

/**
 * @author Michael Young
 */
public class LiferayMediaItemService implements MediaItemService {

	public Future<Void> createMediaItem(
			UserId userId, String appId, String albumId, MediaItem mediaItem,
			SecurityToken securityToken)
		throws ProtocolException {

		try {
			doCreateMediaItem(
				userId, appId, albumId, mediaItem, securityToken);

			return ImmediateFuture.newInstance(null);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e,e);
			}

			throw new ProtocolException(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage(),
				e);
		}
	}

	public Future<Void> deleteMediaItem(
			UserId userId, String appId, String albumId, String mediaItemId,
			SecurityToken securityToken)
		throws ProtocolException {

		try {
			doDeleteMediaItem(
				userId, appId, albumId, mediaItemId, securityToken);

			return ImmediateFuture.newInstance(null);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}

			throw new ProtocolException(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage(),
				e);
		}
	}

	public Future<MediaItem> getMediaItem(
			UserId userId, String appId, String albumId, String mediaItemId,
			Set<String> fields, SecurityToken securityToken)
		throws ProtocolException {

		try {
			MediaItem mediaItem = doGetMediaItem(userId, appId, albumId,
				mediaItemId, fields, securityToken);

			return ImmediateFuture.newInstance(mediaItem);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}

			throw new ProtocolException(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage(),
				e);
		}
	}

	public Future<RestfulCollection<MediaItem>> getMediaItems(
			Set<UserId> userIds, GroupId groupId, String appId,
			Set<String> fields, CollectionOptions collectionOptions,
			SecurityToken securityToken)
		throws ProtocolException {

		try {
			RestfulCollection<MediaItem> mediaItems = doGetMediaItems(
				userIds, groupId, appId, fields, collectionOptions,
				securityToken);

			return ImmediateFuture.newInstance(mediaItems);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}

			throw new ProtocolException(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage(),
				e);
		}
	}

	public Future<RestfulCollection<MediaItem>> getMediaItems(
			UserId userId, String appId, String albumId, Set<String> fields,
			CollectionOptions collectionOptions, SecurityToken securityToken)
		throws ProtocolException {

		try {
			RestfulCollection<MediaItem> mediaItems = doGetMediaItems(userId,
				appId, albumId, fields, collectionOptions, securityToken);

			return ImmediateFuture.newInstance(mediaItems);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}

			throw new ProtocolException(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage(),
				e);
		}
	}

	public Future<RestfulCollection<MediaItem>> getMediaItems(
			UserId userId, String appId, String albumId,
			Set<String> mediaItemIds, Set<String> fields,
			CollectionOptions collectionOptions, SecurityToken securityToken)
		throws ProtocolException {

		try {
			RestfulCollection<MediaItem> mediaItems = doGetMediaItems(
				userId, appId, albumId, mediaItemIds, fields, collectionOptions,
				securityToken);

			return ImmediateFuture.newInstance(mediaItems);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}

			throw new ProtocolException(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage(),
				e);
		}
	}

	public Future<Void> updateMediaItem(
			UserId userId, String appId, String albumId, String mediaItemId,
			MediaItem mediaItem, SecurityToken securityToken)
		throws ProtocolException {

		try {
			doUpdateMediaItem(
				userId, appId, albumId, mediaItemId, mediaItem, securityToken);

			return ImmediateFuture.newInstance(null);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e,e);
			}

			throw new ProtocolException(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage(),
				e);
		}
	}

	protected void doCreateMediaItem(
			UserId userId, String appId, String albumId, MediaItem mediaItem,
			SecurityToken securityToken)
		throws Exception {

		doUpdateMediaItem(
			userId, appId, albumId, null, mediaItem, securityToken);
	}

	protected void doDeleteMediaItem(
			UserId userId, String appId, String albumId, String mediaItemId,
			SecurityToken securityToken)
		throws Exception {

		long mediaItemIdLong = GetterUtil.getLong(mediaItemId);

		DLAppLocalServiceUtil.deleteFileEntry(mediaItemIdLong);
	}

	protected MediaItem doGetMediaItem(
			UserId userId, String appId, String albumId, String mediaItemId,
			Set<String> fields, SecurityToken securityToken)
		throws Exception {

		long mediaItemIdLong = GetterUtil.getLong(mediaItemId);

		DLFileEntry dlFileEntry = DLAppLocalServiceUtil.getFileEntry(
			mediaItemIdLong);

		return getMediaItem(dlFileEntry, fields, securityToken);
	}

	protected RestfulCollection<MediaItem> doGetMediaItems(
			Set<UserId> userIds, GroupId groupId, String appId,
			Set<String> fields, CollectionOptions collectionOptions,
			SecurityToken securityToken)
		throws Exception {

		List<MediaItem> mediaItems = new ArrayList<MediaItem>();

		for (UserId userId : userIds) {
			long userIdLong = GetterUtil.getLong(
				userId.getUserId(securityToken));

			User owner = UserLocalServiceUtil.getUserById(userIdLong);

			List<DLFileEntry> dlFileEntries = new ArrayList<DLFileEntry>();

			GroupId.Type groupIdType = groupId.getType();

			if (groupIdType.equals(GroupId.Type.all) ||
				groupIdType.equals(GroupId.Type.friends) ||
				groupIdType.equals(GroupId.Type.groupId)) {

				List<User> friends = UserLocalServiceUtil.getSocialUsers(
					owner.getUserId(), SocialRelationConstants.TYPE_BI_FRIEND,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

				for (User friend : friends) {
					List<DLFileEntry> friendDLFileEntries =
						DLAppLocalServiceUtil.getGroupFileEntries(
							friend.getGroup().getGroupId(),
							collectionOptions.getFirst(),
							collectionOptions.getMax());

					dlFileEntries.addAll(friendDLFileEntries);
				}
			}
			else if (groupIdType.equals(GroupId.Type.self)) {
				dlFileEntries = DLAppLocalServiceUtil.getGroupFileEntries(
					owner.getGroup().getGroupId(), collectionOptions.getFirst(),
					collectionOptions.getMax());
			}

			for (DLFileEntry dlFileEntry : dlFileEntries) {
				MediaItem.Type mediaItemType = getMediaItemType(dlFileEntry);

				if (mediaItemType != null) {
					mediaItems.add(
						getMediaItem(dlFileEntry, fields, securityToken));
				}
			}
		}

		return new RestfulCollection<MediaItem>(
			mediaItems, collectionOptions.getFirst(), mediaItems.size(),
			collectionOptions.getMax());
	}

	protected RestfulCollection<MediaItem> doGetMediaItems(
			UserId userId, String appId, String albumId, Set<String> fields,
			CollectionOptions collectionOptions, SecurityToken securityToken)
		throws Exception {

		long albumIdLong = GetterUtil.getLong(albumId);

		long userIdLong = GetterUtil.getLong(userId.getUserId(securityToken));

		User owner = UserLocalServiceUtil.getUserById(userIdLong);

		long groupIdLong = owner.getGroup().getGroupId();

		List<DLFileEntry> dLFileEntries =
			DLAppLocalServiceUtil.getFileEntries(groupIdLong, albumIdLong);

		List<MediaItem> mediaItems = new ArrayList<MediaItem>();

		for (DLFileEntry dlFileEntry : dLFileEntries) {
			MediaItem.Type mediaItemType = getMediaItemType(dlFileEntry);

			if (mediaItemType != null) {
				mediaItems.add(
					getMediaItem(dlFileEntry, fields, securityToken));
			}
		}

		return new RestfulCollection<MediaItem>(
			mediaItems, collectionOptions.getFirst(), mediaItems.size(),
			collectionOptions.getMax());
	}

	protected RestfulCollection<MediaItem> doGetMediaItems(
			UserId userId, String appId, String albumId,
			Set<String> mediaItemIds, Set<String> fields,
			CollectionOptions collectionOptions, SecurityToken securityToken)
		throws Exception {

		long albumIdLong = GetterUtil.getLong(albumId);

		long userIdLong = GetterUtil.getLong(userId.getUserId(securityToken));

		User owner = UserLocalServiceUtil.getUserById(userIdLong);

		long groupIdLong = owner.getGroup().getGroupId();

		List<DLFileEntry> dLFileEntries =
			DLAppLocalServiceUtil.getFileEntries(groupIdLong, albumIdLong);

		List<MediaItem> mediaItems = new ArrayList<MediaItem>();

		for (DLFileEntry dlFileEntry : dLFileEntries) {
			MediaItem.Type mediaItemType = getMediaItemType(dlFileEntry);

			if (mediaItemType != null) {
				MediaItem mediaItem = new MediaItemImpl();

				mediaItem = getMediaItem(dlFileEntry, fields, securityToken);

				if (mediaItemIds.contains(mediaItem.getId())) {
					mediaItems.add(mediaItem);
				}
			}
		}

		return new RestfulCollection<MediaItem>(mediaItems,
			collectionOptions.getFirst(), mediaItems.size(),
			collectionOptions.getMax());
	}

	protected void doUpdateMediaItem(
			UserId userId, String appId, String albumId, String mediaItemId,
			MediaItem mediaItem, SecurityToken securityToken)
		throws Exception {

		long albumIdLong = GetterUtil.getLong(albumId);

		long userIdLong = GetterUtil.getLong(userId.getUserId(securityToken));

		User owner = UserLocalServiceUtil.getUserById(userIdLong);

		long groupIdLong = owner.getGroup().getGroupId();

		String fileName;

		Http.Options options = new Http.Options();

		options.setLocation(mediaItem.getUrl());

		byte[] byteArray = HttpUtil.URLtoByteArray(options);

		String contentDisposition = options.getResponse().getHeader(
			"Content-Disposition");

		if (contentDisposition != null) {
			Matcher filenameMatcher = _filenamePattern.matcher(
				contentDisposition);

			if (filenameMatcher.find()) {
				fileName = filenameMatcher.group(1);
			}
			else {
				fileName = mediaItem.getTitle();
			}
		}
		else {
			fileName = FileUtil.getShortFileName(mediaItem.getUrl());
		}

		JSONObject extraDataJSON = JSONFactoryUtil.createJSONObject();

		extraDataJSON.put("DURATION", mediaItem.getDuration());
		extraDataJSON.put("fileSize", mediaItem.getFileSize());
		extraDataJSON.put("language", mediaItem.getLanguage());
		extraDataJSON.put("location", getLocation(mediaItem.getLocation()));
		extraDataJSON.put("numCommments", mediaItem.getNumComments());
		extraDataJSON.put("numVotes", mediaItem.getNumVotes());
		extraDataJSON.put("rating", mediaItem.getRating());
		extraDataJSON.put("startTime", mediaItem.getStartTime());
		extraDataJSON.put("taggedPeople", mediaItem.getTaggedPeople());
		extraDataJSON.put("tags", mediaItem.getTags());
		extraDataJSON.put("thumbnailUrl", mediaItem.getThumbnailUrl());

		if (mediaItemId == null) {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setAddCommunityPermissions(true);
			serviceContext.setAddGuestPermissions(true);
			serviceContext.setScopeGroupId(groupIdLong);

			DLAppLocalServiceUtil.addFileEntry(
				userIdLong, groupIdLong, albumIdLong, fileName,
				mediaItem.getDescription(), StringPool.BLANK,
				extraDataJSON.toString(), byteArray, serviceContext);
		}
		else {
			long mediaItemIdLong = GetterUtil.getLong(mediaItemId);

			DLFileEntry dlFileEntry = DLAppLocalServiceUtil.getFileEntry(
				mediaItemIdLong);

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setAddCommunityPermissions(true);
			serviceContext.setAddGuestPermissions(true);
			serviceContext.setCreateDate(dlFileEntry.getCreateDate());
			serviceContext.setModifiedDate(dlFileEntry.getModifiedDate());
			serviceContext.setScopeGroupId(groupIdLong);

			DLAppLocalServiceUtil.updateFileEntry(
				userIdLong, dlFileEntry.getFileEntryId(), fileName,
				mediaItem.getTitle(), mediaItem.getDescription(), "", false,
				extraDataJSON.toString(), byteArray, serviceContext);
		}
	}

	protected Address getAddress (JSONObject addressJSON) {
		Address address = new AddressImpl();

		if (addressJSON.has(Address.Field.COUNTRY.toString())) {
			address.setCountry(addressJSON.getString("country"));
		}

		if (addressJSON.has(Address.Field.FORMATTED.toString())) {
			address.setFormatted(addressJSON.getString("formatted"));
		}

		if (addressJSON.has(Address.Field.LATITUDE.toString())) {
			address.setLatitude((float) addressJSON.getDouble("latitude"));
		}

		if (addressJSON.has(Address.Field.LOCALITY.toString())) {
			address.setLocality(addressJSON.getString("locality"));
		}

		if (addressJSON.has(Address.Field.LONGITUDE.toString())) {
			address.setLongitude((float) addressJSON.getDouble("longitude"));
		}

		if (addressJSON.has(Address.Field.POSTAL_CODE.toString())) {
			address.setPostalCode(addressJSON.getString("postalCode"));
		}

		if (addressJSON.has(Address.Field.PRIMARY.toString())) {
			address.setPrimary(addressJSON.getBoolean("primary"));
		}

		if (addressJSON.has(Address.Field.REGION.toString())) {
			address.setRegion(addressJSON.getString("region"));
		}

		if (addressJSON.has(Address.Field.STREET_ADDRESS.toString())) {
			address.setStreetAddress(addressJSON.getString("streetAddress"));
		}

		if (addressJSON.has(Address.Field.TYPE.toString())) {
			address.setType(addressJSON.getString("type"));
		}

		return address;
	}

	protected String getDLFileEntryURL(
			DLFileEntry dlFileEntry, SecurityToken securityToken) {

		StringBuilder sb = new StringBuilder();

		sb.append(securityToken.getDomain());
		sb.append(PortalUtil.getPathContext());
		sb.append("/documents/");
		sb.append(dlFileEntry.getGroupId());
		sb.append(StringPool.SLASH);
		sb.append(dlFileEntry.getUuid());

		return sb.toString();
	}

	protected JSONObject getLocation(Address address) {
		if (address == null) {
			return null;
		}

		JSONObject addressJSON = JSONFactoryUtil.createJSONObject();

		addressJSON.put("country", address.getCountry());
		addressJSON.put("formatted", address.getFormatted());
		addressJSON.put("latitude", address.getLatitude());
		addressJSON.put("locality", address.getLocality());
		addressJSON.put("longitude", address.getLongitude());
		addressJSON.put("postalCode", address.getPostalCode());
		addressJSON.put("primary", address.getPrimary());
		addressJSON.put("region", address.getRegion());
		addressJSON.put("streetAddress", address.getStreetAddress());
		addressJSON.put("type", address.getType());

		return addressJSON;
	}

	protected MediaItem getMediaItem(
			DLFileEntry dlFileEntry, Set<String> fields,
			SecurityToken securityToken)
		throws Exception {

		MediaItem mediaItem = new MediaItemImpl();

		mediaItem.setAlbumId(Long.toString(dlFileEntry.getFolderId()));
		mediaItem.setCreated(dlFileEntry.getCreateDate().toString());
		mediaItem.setDescription(dlFileEntry.getDescription());
		mediaItem.setId(Long.toString(dlFileEntry.getFileEntryId()));
		mediaItem.setLastUpdated(
			dlFileEntry.getModifiedDate().toString());
		mediaItem.setMimeType(MimeTypesUtil.getContentType(
			dlFileEntry.getExtension()));
		mediaItem.setNumViews(Long.toString(
			dlFileEntry.getReadCount()));
		mediaItem.setTitle(dlFileEntry.getTitle());
		mediaItem.setType(getMediaItemType(dlFileEntry));
		mediaItem.setUrl(getDLFileEntryURL(dlFileEntry, securityToken));

		JSONObject extraDataJSON = null;

		try {
			extraDataJSON = JSONFactoryUtil.createJSONObject(
				dlFileEntry.getExtraSettings());
		}
		catch (JSONException e) {}

		if (extraDataJSON != null) {
			if (extraDataJSON.has(MediaItem.Field.DURATION.toString())) {
				mediaItem.setDuration(extraDataJSON.getString("DURATION"));
			}

			if (extraDataJSON.has(MediaItem.Field.FILE_SIZE.toString())) {
				mediaItem.setFileSize(extraDataJSON.getString("fileSize"));
			}

			if (extraDataJSON.has(MediaItem.Field.LANGUAGE.toString())) {
				mediaItem.setLanguage(extraDataJSON.getString("language"));
			}

			if (extraDataJSON.has(MediaItem.Field.LOCATION.toString())) {
				mediaItem.setLocation(
					getAddress(extraDataJSON.getJSONObject("location")));
			}

			if (extraDataJSON.has(MediaItem.Field.NUM_COMMENTS.toString())) {
				mediaItem.setNumComments(
					extraDataJSON.getString("numComments"));
			}

			if (extraDataJSON.has(MediaItem.Field.NUM_VOTES.toString())) {
				mediaItem.setNumVotes(extraDataJSON.getString("numVotes"));
			}

			if (extraDataJSON.has(MediaItem.Field.RATING.toString())) {
				mediaItem.setRating(extraDataJSON.getString("rating"));
			}

			if (extraDataJSON.has(MediaItem.Field.START_TIME.toString())) {
				mediaItem.setStartTime(extraDataJSON.getString("startTime"));
			}

			if (extraDataJSON.has(MediaItem.Field.TAGGED_PEOPLE.toString())) {
				mediaItem.setTaggedPeople(
					extraDataJSON.getString("taggedPeople"));
			}

			if (extraDataJSON.has(MediaItem.Field.TAGS.toString())) {
				mediaItem.setTags(extraDataJSON.getString("tags"));
			}

			if (extraDataJSON.has(MediaItem.Field.THUMBNAIL_URL.toString())) {
				mediaItem.setThumbnailUrl(
					extraDataJSON.getString("thumbnailUrl"));
			}
		}

		return mediaItem;
	}

	protected MediaItem.Type getMediaItemType(DLFileEntry dlFileEntry) {
		String contentType = MimeTypesUtil.getContentType(
			dlFileEntry.getExtension());

		if (contentType.startsWith("audio")) {
			return Type.AUDIO;
		}
		else if (contentType.startsWith("image")) {
			return Type.IMAGE;
		}
		else if (contentType.startsWith("video")) {
			return Type.VIDEO;
		}
		else {
			return null;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		LiferayMediaItemService.class);

	private static Pattern _filenamePattern = Pattern.compile(
		".*?filename=\"?([^\";]+)");

}