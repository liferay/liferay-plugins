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

package com.liferay.opensocial.shindig.service;

import com.liferay.opensocial.shindig.util.SerializerUtil;
import com.liferay.opensocial.shindig.util.ShindigUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLAppServiceUtil;
import com.liferay.portlet.social.model.SocialRelationConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServletResponse;

import org.apache.shindig.auth.SecurityToken;
import org.apache.shindig.common.util.ImmediateFuture;
import org.apache.shindig.protocol.ProtocolException;
import org.apache.shindig.protocol.RestfulCollection;
import org.apache.shindig.social.core.model.AlbumImpl;
import org.apache.shindig.social.opensocial.model.Album;
import org.apache.shindig.social.opensocial.spi.AlbumService;
import org.apache.shindig.social.opensocial.spi.CollectionOptions;
import org.apache.shindig.social.opensocial.spi.GroupId;
import org.apache.shindig.social.opensocial.spi.UserId;

/**
 * @author Dennis Ju
 */
public class LiferayAlbumService implements AlbumService {

	public Future<Void> createAlbum(
			UserId userId, String appId, Album album,
			SecurityToken securityToken)
		throws ProtocolException {

		try {
			doCreateAlbum(userId, appId, album, securityToken);

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

	public Future<Void> deleteAlbum(
			UserId userId, String appId, String albumId,
			SecurityToken securityToken)
		throws ProtocolException {

		try {
			doDeleteAlbum(userId, appId, albumId, securityToken);

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

	public Future<Album> getAlbum(
			UserId userId, String appId, Set<String> fields, String albumId,
			SecurityToken securityToken)
		throws ProtocolException {

		try {
			Album album = doGetAlbum(
				userId, appId, fields, albumId, securityToken);

			return ImmediateFuture.newInstance(album);
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

	public Future<RestfulCollection<Album>> getAlbums(
			Set<UserId> userIds, GroupId groupId, String appId,
			Set<String> fields, CollectionOptions collectionOptions,
			SecurityToken securityToken)
		throws ProtocolException {

		try {
			RestfulCollection<Album> albums = doGetAlbums(
				userIds, groupId, appId, fields, collectionOptions,
				securityToken);

			return ImmediateFuture.newInstance(albums);
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

	public Future<RestfulCollection<Album>> getAlbums(
			UserId userId, String appId, Set<String> fields,
			CollectionOptions collectionOptions, Set<String> albumIds,
			SecurityToken securityToken)
		throws ProtocolException {

		try {
			RestfulCollection<Album> albums = doGetAlbums(
				userId, appId, fields, collectionOptions, albumIds,
				securityToken);

			return ImmediateFuture.newInstance(albums);
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

	public Future<Void> updateAlbum(
			UserId userId, String appId, Album album, String albumId,
			SecurityToken securityToken)
		throws ProtocolException {

		try {
			doUpdateAlbum(userId, appId, album, albumId, securityToken);

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

	protected void doCreateAlbum(
			UserId userId, String appId, Album album,
			SecurityToken securityToken)
		throws Exception {

		doUpdateAlbum(userId, appId, album, null, securityToken);
	}

	protected void doDeleteAlbum(
			UserId userId, String appId, String albumId,
			SecurityToken securityToken)
		throws Exception {

		long albumIdLong = GetterUtil.getLong(albumId);

		DLAppServiceUtil.deleteFolder(albumIdLong);
	}

	protected Album doGetAlbum(
			UserId userId, String appId, Set<String> fields, String albumId,
			SecurityToken securityToken)
		throws Exception {

		long albumIdLong = GetterUtil.getLong(albumId);

		Folder folder = DLAppServiceUtil.getFolder(albumIdLong);

		return toAlbum(folder, fields, securityToken);
	}

	protected RestfulCollection<Album> doGetAlbums(
			Set<UserId> userIds, GroupId groupId, String appId,
			Set<String> fields, CollectionOptions collectionOptions,
			SecurityToken securityToken)
		throws Exception {

		List<Album> albums = new ArrayList<Album>();

		for (UserId userId : userIds) {
			String userIdString = userId.getUserId(securityToken);

			long userIdLong = GetterUtil.getLong(userIdString);

			User user = UserLocalServiceUtil.getUserById(userIdLong);

			if (!ShindigUtil.isValidUser(user)) {
				continue;
			}

			List<Folder> folders = new ArrayList<Folder>();

			GroupId.Type groupIdType = groupId.getType();

			if (groupIdType.equals(GroupId.Type.all) ||
				groupIdType.equals(GroupId.Type.friends) ||
				groupIdType.equals(GroupId.Type.groupId)) {

				List<User> socialUsers = UserLocalServiceUtil.getSocialUsers(
					user.getUserId(), SocialRelationConstants.TYPE_BI_FRIEND,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

				for (User socialUser : socialUsers) {
					Group group = socialUser.getGroup();

					List<Folder> friendFolders = DLAppServiceUtil.getFolders(
						group.getGroupId(),
						DLFolderConstants.DEFAULT_PARENT_FOLDER_ID);

					folders.addAll(friendFolders);
				}
			}
			else if (groupIdType.equals(GroupId.Type.self)) {
				Group group = user.getGroup();

				folders = DLAppServiceUtil.getFolders(
					group.getGroupId(),
					DLFolderConstants.DEFAULT_PARENT_FOLDER_ID);
			}

			for (Folder folder : folders) {
				Album album = toAlbum(folder, fields, securityToken);

				albums.add(album);
			}
		}

		return new RestfulCollection<Album>(
			albums, collectionOptions.getFirst(), albums.size(),
			collectionOptions.getMax());
	}

	protected RestfulCollection<Album> doGetAlbums(
			UserId userId, String appId, Set<String> fields,
			CollectionOptions collectionOptions, Set<String> albumIds,
			SecurityToken securityToken)
		throws Exception {

		List<Album> albums = new ArrayList<Album>();

		for (String albumId : albumIds) {
			Folder folder = DLAppServiceUtil.getFolder(
				GetterUtil.getLong(albumId));

			Album album = toAlbum(folder, fields, securityToken);

			albums.add(album);
		}

		return new RestfulCollection<Album>(
			albums, collectionOptions.getFirst(), albums.size(),
			collectionOptions.getMax());
	}

	protected void doUpdateAlbum(
			UserId userId, String appId, Album album, String albumId,
			SecurityToken securityToken)
		throws Exception {

		long userIdLong = GetterUtil.getLong(userId.getUserId(securityToken));

		User user = UserLocalServiceUtil.getUserById(userIdLong);

		if (!ShindigUtil.isValidUser(user)) {
			return;
		}

		Group group = user.getGroup();

		long groupIdLong = group.getGroupId();

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);
		serviceContext.setExpandoBridgeAttributes(
			SerializerUtil.toExpandoAttributes(
				album, _ALBUM_FIELDS, user.getCompanyId(),
				DLFolder.class.getName()));
		serviceContext.setScopeGroupId(groupIdLong);

		if (albumId == null) {
			DLAppServiceUtil.addFolder(
				groupIdLong, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
				album.getTitle(), album.getDescription(), serviceContext);
		}
		else {
			Folder folder = DLAppLocalServiceUtil.getFolder(
				GetterUtil.getLong(albumId));

			DLAppServiceUtil.updateFolder(
				folder.getFolderId(), album.getTitle(), album.getDescription(),
				serviceContext);
		}
	}

	protected Album toAlbum(
			Folder folder, Set<String> fields, SecurityToken securityToken)
		throws JSONException {

		Album album = new AlbumImpl();

		album.setDescription(folder.getDescription());
		album.setId(String.valueOf(folder.getFolderId()));
		album.setOwnerId(String.valueOf(folder.getUserId()));
		album.setTitle(folder.getName());

		SerializerUtil.copyProperties(
			folder.getAttributes(), album, _ALBUM_FIELDS);

		return album;
	}

	private static final Album.Field[] _ALBUM_FIELDS = {
		Album.Field.LOCATION, Album.Field.THUMBNAIL_URL
	};

	private static Log _log = LogFactoryUtil.getLog(LiferayAlbumService.class);

}