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

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLSyncEvent;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLSyncEventLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.sync.model.SyncDLObject;
import com.liferay.sync.model.SyncDLObjectConstants;
import com.liferay.sync.model.impl.SyncDLObjectImpl;
import com.liferay.sync.util.SyncUtil;

import java.util.List;

/**
 * @author Dennis Ju
 */
public class DLSyncEventMessageListener extends BaseMessageListener {

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

		SyncDLObject syncDLObject = null;

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

			syncDLObject = new SyncDLObjectImpl();

			syncDLObject.setUserId(userId);
			syncDLObject.setUserName(userName);
			syncDLObject.setEvent(event);
			syncDLObject.setType(type);
			syncDLObject.setTypePK(typePK);
		}
		else if (type.equals(SyncDLObjectConstants.TYPE_FILE)) {
			DLFileEntry dlFileEntry =
				DLFileEntryLocalServiceUtil.fetchDLFileEntry(typePK);

			if (dlFileEntry == null) {
				return;
			}

			syncDLObject = SyncUtil.toSyncDLObject(
				dlFileEntry, event, !dlFileEntry.isInTrash());
		}
		else {
			DLFolder dlFolder = DLFolderLocalServiceUtil.fetchDLFolder(typePK);

			if ((dlFolder == null) || !SyncUtil.isSupportedFolder(dlFolder)) {
				return;
			}

			syncDLObject = SyncUtil.toSyncDLObject(dlFolder, event);
		}

		syncDLObject.setModifiedTime(modifiedTime);

		SyncUtil.addSyncDLObject(syncDLObject);
	}

}