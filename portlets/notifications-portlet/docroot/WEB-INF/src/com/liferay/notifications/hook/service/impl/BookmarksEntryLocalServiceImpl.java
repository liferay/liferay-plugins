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

package com.liferay.notifications.hook.service.impl;

import com.liferay.compat.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.notifications.util.NotificationsUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.asset.AssetRendererFactoryRegistryUtil;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.asset.model.AssetRendererFactory;
import com.liferay.portlet.bookmarks.model.BookmarksEntry;
import com.liferay.portlet.bookmarks.model.BookmarksFolder;
import com.liferay.portlet.bookmarks.service.BookmarksEntryLocalService;
import com.liferay.portlet.bookmarks.service.BookmarksEntryLocalServiceWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lin Cui
 */
public class BookmarksEntryLocalServiceImpl
	extends BookmarksEntryLocalServiceWrapper {

	public BookmarksEntryLocalServiceImpl(
		BookmarksEntryLocalService bookmarksEntryLocalService) {

		super(bookmarksEntryLocalService);
	}

	@Override
	public BookmarksEntry addEntry(
			long userId, long groupId, long folderId, String name, String url,
			String description, ServiceContext serviceContext)
		throws PortalException, SystemException {

		BookmarksEntry entry = super.addEntry(
			userId, groupId, folderId, name, url, description, serviceContext);

		notifySubscribers(userId, entry, serviceContext);

		return entry;
	}

	@Override
	public BookmarksEntry updateEntry(
			long userId, long entryId, long groupId, long folderId, String name,
			String url, String description, ServiceContext serviceContext)
		throws PortalException, SystemException {

		BookmarksEntry entry = super.updateEntry(
			userId, entryId, groupId, folderId, name, url, description,
			serviceContext);

		notifySubscribers(userId, entry, serviceContext);

		return entry;
	}

	protected List<ObjectValuePair<String, Long>> getSubscribersOVPs(
			BookmarksEntry entry)
		throws PortalException, SystemException {

		List<ObjectValuePair<String, Long>> subscribersOVPs =
			new ArrayList<ObjectValuePair<String, Long>>();

		BookmarksFolder folder = entry.getFolder();

		List<Long> folderIds = new ArrayList<Long>();

		if (folder != null) {
			folderIds.add(folder.getFolderId());

			folderIds.addAll(folder.getAncestorFolderIds());
		}

		for (long folderId : folderIds) {
			subscribersOVPs.add(
				new ObjectValuePair<String, Long>(
					_BOOKMARKS_FOLDER_CLASS_NAME, folderId));
		}

		subscribersOVPs.add(
			new ObjectValuePair<String, Long>(
				_BOOKMARKS_FOLDER_CLASS_NAME, entry.getGroupId()));

		subscribersOVPs.add(
			new ObjectValuePair<String, Long>(
				_BOOKMARKS_ENTRY_CLASS_NAME, entry.getEntryId()));

		return subscribersOVPs;
	}

	protected void notifySubscribers(
			long userId, BookmarksEntry entry, ServiceContext serviceContext)
		throws PortalException, SystemException {

		AssetRenderer assetRenderer = _assetRendererFactory.getAssetRenderer(
			entry.getEntryId());

		String entryURL = NotificationsUtil.getEntryURL(
			assetRenderer, PortletKeys.BOOKMARKS, serviceContext);

		int notificationType =
			UserNotificationDefinition.NOTIFICATION_TYPE_ADD_ENTRY;

		if (serviceContext.isCommandUpdate()) {
			notificationType =
				UserNotificationDefinition.NOTIFICATION_TYPE_UPDATE_ENTRY;
		}

		if (Validator.isNotNull(entryURL)) {
			NotificationsUtil.sendNotificationEvent(
				entry.getCompanyId(), PortletKeys.BOOKMARKS,
				_BOOKMARKS_ENTRY_CLASS_NAME, entry.getEntryId(),
				assetRenderer.getTitle(serviceContext.getLocale()), entryURL,
				notificationType, getSubscribersOVPs(entry), userId);
		}
	}

	protected AssetRendererFactory _assetRendererFactory =
		AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(
			_BOOKMARKS_ENTRY_CLASS_NAME);

	private static final String _BOOKMARKS_ENTRY_CLASS_NAME =
		BookmarksEntry.class.getName();

	private static final String _BOOKMARKS_FOLDER_CLASS_NAME =
		BookmarksFolder.class.getName();

}