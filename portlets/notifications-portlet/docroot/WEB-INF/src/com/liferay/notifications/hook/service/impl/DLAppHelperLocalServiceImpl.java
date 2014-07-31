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
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.asset.AssetRendererFactoryRegistryUtil;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.asset.model.AssetRendererFactory;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLAppHelperLocalService;
import com.liferay.portlet.documentlibrary.service.DLAppHelperLocalServiceWrapper;

import java.io.Serializable;

import java.util.Map;

/**
 * @author Lin Cui
 */
public class DLAppHelperLocalServiceImpl
	extends DLAppHelperLocalServiceWrapper {

	public DLAppHelperLocalServiceImpl(
		DLAppHelperLocalService dlAppHelperLocalService) {

		super(dlAppHelperLocalService);
	}

	@Override
	public void updateStatus(
			long userId, FileEntry fileEntry, FileVersion latestFileVersion,
			int oldStatus, int newStatus,
			Map<String, Serializable> workflowContext,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		super.updateStatus(
			userId, fileEntry, latestFileVersion, oldStatus, newStatus,
			workflowContext, serviceContext);

		int notificationType =
			UserNotificationDefinition.NOTIFICATION_TYPE_ADD_ENTRY;

		if (serviceContext.isCommandUpdate()) {
			notificationType =
				UserNotificationDefinition.NOTIFICATION_TYPE_UPDATE_ENTRY;
		}

		AssetRenderer assetRenderer = _assetRendererFactory.getAssetRenderer(
			latestFileVersion.getFileEntryId());

		String entryURL = NotificationsUtil.getEntryURL(
			assetRenderer, PortletKeys.DOCUMENT_LIBRARY, serviceContext);

		if (Validator.isNotNull(entryURL)) {
			NotificationsUtil.sendNotificationEvent(
				latestFileVersion.getCompanyId(), _FOLDER_CLASS_NAME,
				latestFileVersion.getGroupId(), PortletKeys.DOCUMENT_LIBRARY,
				_DL_FILE_ENTRY_CLASS_NAME, latestFileVersion.getFileEntryId(),
				assetRenderer.getTitle(serviceContext.getLocale()), entryURL,
				notificationType, userId);
		}
	}

	protected AssetRendererFactory _assetRendererFactory =
		AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(
			_DL_FILE_ENTRY_CLASS_NAME);

	private static final String _DL_FILE_ENTRY_CLASS_NAME =
		DLFileEntry.class.getName();

	private static final String _FOLDER_CLASS_NAME = Folder.class.getName();

}