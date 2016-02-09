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

package com.liferay.sync.messaging;

import com.liferay.document.library.kernel.exception.NoSuchFileEntryException;
import com.liferay.document.library.kernel.exception.NoSuchFolderException;
import com.liferay.document.library.kernel.model.DLSyncEvent;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLSyncEventLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.sync.model.SyncDLObject;
import com.liferay.sync.model.SyncDLObjectConstants;
import com.liferay.sync.service.SyncDLObjectLocalServiceUtil;
import com.liferay.sync.util.SyncUtil;

import java.util.List;

/**
 * @author Dennis Ju
 */
public class DLSyncEventMessageListener extends BaseMessageListener {

	protected void addSyncDLObject(SyncDLObject syncDLObject) throws Exception {
		SyncDLObjectLocalServiceUtil.addSyncDLObject(
			syncDLObject.getCompanyId(), syncDLObject.getUserId(),
			syncDLObject.getUserName(), syncDLObject.getModifiedTime(),
			syncDLObject.getRepositoryId(), syncDLObject.getParentFolderId(),
			syncDLObject.getTreePath(), syncDLObject.getName(),
			syncDLObject.getExtension(), syncDLObject.getMimeType(),
			syncDLObject.getDescription(), syncDLObject.getChangeLog(),
			syncDLObject.getExtraSettings(), syncDLObject.getVersion(),
			syncDLObject.getVersionId(), syncDLObject.getSize(),
			syncDLObject.getChecksum(), syncDLObject.getEvent(),
			syncDLObject.getLockExpirationDate(), syncDLObject.getLockUserId(),
			syncDLObject.getLockUserName(), syncDLObject.getType(),
			syncDLObject.getTypePK(), syncDLObject.getTypeUuid());
	}

	protected void deleteDLSyncEvent(
			long modifiedTime, long syncEventId, long typePK)
		throws Exception {

		if (syncEventId != 0) {
			DLSyncEventLocalServiceUtil.deleteDLSyncEvent(syncEventId);

			return;
		}

		DynamicQuery dynamicQuery = DLSyncEventLocalServiceUtil.dynamicQuery();

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("modifiedTime", modifiedTime));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("typePK", typePK));

		List<DLSyncEvent> dlSyncEvents =
			DLSyncEventLocalServiceUtil.dynamicQuery(dynamicQuery);

		if (dlSyncEvents.isEmpty()) {
			return;
		}

		DLSyncEvent dlSyncEvent = dlSyncEvents.get(0);

		DLSyncEventLocalServiceUtil.deleteDLSyncEvent(dlSyncEvent);
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		String event = message.getString("event");
		long modifiedTime = message.getLong("modifiedTime");
		long syncEventId = message.getLong("syncEventId");
		String type = message.getString("type");
		long typePK = message.getLong("typePK");

		processDLSyncEvent(modifiedTime, event, type, typePK);

		deleteDLSyncEvent(modifiedTime, syncEventId, typePK);
	}

	protected void processDLSyncEvent(
			long modifiedTime, String event, String type, long typePK)
		throws Exception {

		if (event.equals(SyncDLObjectConstants.EVENT_DELETE)) {
			long userId = 0;
			String userName = StringPool.BLANK;

			PermissionChecker permissionChecker =
				PermissionThreadLocal.getPermissionChecker();

			if (permissionChecker != null) {
				User user = permissionChecker.getUser();

				userId = user.getUserId();
				userName = user.getFullName();
			}

			SyncDLObjectLocalServiceUtil.addSyncDLObject(
				0, userId, userName, modifiedTime, 0, 0, StringPool.BLANK,
				StringPool.BLANK, StringPool.BLANK, StringPool.BLANK,
				StringPool.BLANK, StringPool.BLANK, StringPool.BLANK,
				StringPool.BLANK, 0, 0, StringPool.BLANK, event, null, 0,
				StringPool.BLANK, type, typePK, StringPool.BLANK);

			return;
		}

		SyncDLObject syncDLObject = null;

		if (type.equals(SyncDLObjectConstants.TYPE_FILE)) {
			FileEntry fileEntry = null;

			try {
				fileEntry = DLAppLocalServiceUtil.getFileEntry(typePK);
			}
			catch (NoSuchFileEntryException nsfee) {
				return;
			}

			syncDLObject = SyncUtil.toSyncDLObject(fileEntry, event, true);
		}
		else {
			Folder folder = null;

			try {
				folder = DLAppLocalServiceUtil.getFolder(typePK);
			}
			catch (NoSuchFolderException nsfe) {
				return;
			}

			if (!SyncUtil.isSupportedFolder(folder)) {
				return;
			}

			syncDLObject = SyncUtil.toSyncDLObject(folder, event);
		}

		syncDLObject.setModifiedTime(modifiedTime);

		addSyncDLObject(syncDLObject);
	}

}