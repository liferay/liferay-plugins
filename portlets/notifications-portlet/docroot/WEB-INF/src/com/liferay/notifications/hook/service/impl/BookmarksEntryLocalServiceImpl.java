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
import com.liferay.portlet.bookmarks.model.BookmarksFolderConstants;
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

		BookmarksEntry bookmarksEntry = super.addEntry(
			userId, groupId, folderId, name, url, description, serviceContext);

		AssetRenderer assetRenderer = _assetRendererFactory.getAssetRenderer(
			bookmarksEntry.getEntryId());

		String entryURL = NotificationsUtil.getEntryURL(
			assetRenderer, PortletKeys.BOOKMARKS, serviceContext);

		if (Validator.isNotNull(entryURL)) {
			NotificationsUtil.sendNotificationEvent(
				bookmarksEntry.getCompanyId(), PortletKeys.BOOKMARKS,
				_BOOKMARKS_FOLDER_CLASS_NAME, bookmarksEntry.getFolderId(),
				assetRenderer.getTitle(serviceContext.getLocale()), entryURL,
				UserNotificationDefinition.NOTIFICATION_TYPE_ADD_ENTRY,
				getSubscribersOVPs(
					bookmarksEntry, _BOOKMARKS_FOLDER_CLASS_NAME,
					bookmarksEntry.getFolderId()),
				userId);
		}

		return bookmarksEntry;
	}

	@Override
	public BookmarksEntry updateEntry(
			long userId, long entryId, long groupId, long folderId, String name,
			String url, String description, ServiceContext serviceContext)
		throws PortalException, SystemException {

		BookmarksEntry bookmarksEntry = super.updateEntry(
			userId, entryId, groupId, folderId, name, url, description,
			serviceContext);

		AssetRenderer assetRenderer = _assetRendererFactory.getAssetRenderer(
			bookmarksEntry.getEntryId());

		String entryURL = NotificationsUtil.getEntryURL(
			assetRenderer, PortletKeys.BOOKMARKS, serviceContext);

		if (Validator.isNotNull(entryURL)) {
			NotificationsUtil.sendNotificationEvent(
				bookmarksEntry.getCompanyId(), PortletKeys.BOOKMARKS,
				_BOOKMARKS_ENTRY_CLASS_NAME, bookmarksEntry.getEntryId(),
				assetRenderer.getTitle(serviceContext.getLocale()), entryURL,
				UserNotificationDefinition.NOTIFICATION_TYPE_UPDATE_ENTRY,
				getSubscribersOVPs(
					bookmarksEntry, _BOOKMARKS_ENTRY_CLASS_NAME,
					bookmarksEntry.getEntryId()),
				userId);
		}

		return bookmarksEntry;
	}

	protected List<ObjectValuePair<String, Long>> getSubscribersOVPs(
			BookmarksEntry bookmarksEntry, String subscriptionClassName,
			long subscriptionClassPK)
		throws PortalException, SystemException {

		List<ObjectValuePair<String, Long>> subscribersOVPs =
			new ArrayList<ObjectValuePair<String, Long>>();

		if (subscriptionClassName.equals(_BOOKMARKS_ENTRY_CLASS_NAME)) {
			subscribersOVPs.add(
				new ObjectValuePair<String, Long>(
					subscriptionClassName, subscriptionClassPK));

			subscriptionClassName = _BOOKMARKS_FOLDER_CLASS_NAME;
			subscriptionClassPK = bookmarksEntry.getFolderId();
		}

		if (subscriptionClassPK <= 0) {
			subscriptionClassPK = bookmarksEntry.getGroupId();
		}

		List<Long> folderIds = new ArrayList<Long>();

		folderIds.add(subscriptionClassPK);

		BookmarksFolder folder = bookmarksEntry.getFolder();

		if (subscriptionClassPK !=
				BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

			folderIds.addAll(folder.getAncestorFolderIds());
		}

		for (Long folderId : folderIds) {
			subscribersOVPs.add(
				new ObjectValuePair<String, Long>(
					subscriptionClassName, folderId));
		}

		return subscribersOVPs;
	}

	protected AssetRendererFactory _assetRendererFactory =
		AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(
			_BOOKMARKS_ENTRY_CLASS_NAME);

	private static final String _BOOKMARKS_ENTRY_CLASS_NAME =
		BookmarksEntry.class.getName();

	private static final String _BOOKMARKS_FOLDER_CLASS_NAME =
		BookmarksFolder.class.getName();

}