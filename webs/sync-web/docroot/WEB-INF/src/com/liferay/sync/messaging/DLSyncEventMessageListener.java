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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFileVersion;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFolderLocalServiceUtil;
import com.liferay.sync.model.SyncDLObject;
import com.liferay.sync.model.SyncDLObjectConstants;
import com.liferay.sync.model.impl.SyncDLObjectImpl;
import com.liferay.sync.util.SyncUtil;

/**
 * @author Dennis Ju
 */
public class DLSyncEventMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		String event = message.getString("event");
		long modifiedTime = message.getLong("modifiedTime");
		String type = message.getString("type");
		long typePK = message.getLong("typePK");

		try {
			processDLSyncEvent(modifiedTime, event, type, typePK);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	protected void processDLSyncEvent(
			long modifiedTime, String event, String type, long typePK)
		throws Exception {

		SyncDLObject syncDLObject = null;

		if (event.equals(SyncDLObjectConstants.EVENT_DELETE)) {
			syncDLObject = new SyncDLObjectImpl();

			setUser(syncDLObject);

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

			DLFileVersion dlFileVersion = dlFileEntry.getFileVersion();

			if (dlFileVersion.isPending()) {
				return;
			}

			boolean calculateChecksum = false;

			String checksum = SyncUtil.getChecksum(modifiedTime, typePK);

			if ((checksum == null) && !dlFileEntry.isInTrash()) {
				calculateChecksum = true;
			}

			syncDLObject = SyncUtil.toSyncDLObject(
				dlFileEntry, event, calculateChecksum);

			if (checksum != null) {
				syncDLObject.setChecksum(checksum);
			}

			if (event.equals(SyncDLObjectConstants.EVENT_TRASH)) {
				setUser(syncDLObject);
			}

			syncDLObject.setLanTokenKey(
				SyncUtil.getLanTokenKey(modifiedTime, typePK, false));
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

	protected void setUser(SyncDLObject syncDLObject) {
		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if (permissionChecker != null) {
			User user = permissionChecker.getUser();

			syncDLObject.setUserId(user.getUserId());
			syncDLObject.setUserName(user.getFullName());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		DLSyncEventMessageListener.class);

}