/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Lock;
import com.liferay.portlet.documentlibrary.NoSuchFileEntryException;
import com.liferay.portlet.documentlibrary.NoSuchFolderException;
import com.liferay.portlet.documentlibrary.model.DLFileVersion;
import com.liferay.portlet.documentlibrary.model.DLSyncConstants;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileVersionLocalServiceUtil;
import com.liferay.sync.service.SyncDLObjectLocalServiceUtil;
import com.liferay.sync.util.SyncUtil;

import java.util.Date;

/**
 * @author Dennis Ju
 */
public class SyncDLObjectMessageListener extends BaseMessageListener {

	protected void addSyncDLObject(
			long modifiedTime, String event, String type, long typePK)
		throws Exception {

		if (event.equals(DLSyncConstants.EVENT_DELETE)) {
			SyncDLObjectLocalServiceUtil.addSyncDLObject(
				0, modifiedTime, 0, 0, StringPool.BLANK, StringPool.BLANK,
				StringPool.BLANK, StringPool.BLANK, StringPool.BLANK,
				StringPool.BLANK, StringPool.BLANK, 0, StringPool.BLANK, event,
				null, 0, StringPool.BLANK, type, typePK, StringPool.BLANK);
		}
		else if (type.equals(DLSyncConstants.TYPE_FILE)) {
			FileEntry fileEntry = null;

			try {
				fileEntry = DLAppLocalServiceUtil.getFileEntry(typePK);
			}
			catch (NoSuchFileEntryException nsfee) {
				return;
			}

			Date lockExpirationDate = null;
			long lockUserId = 0;
			String lockUserName = StringPool.BLANK;

			Lock lock = fileEntry.getLock();

			if (lock != null) {
				lockExpirationDate = lock.getExpirationDate();
				lockUserId = lock.getUserId();
				lockUserName = lock.getUserName();
			}

			DLFileVersion dlFileVersion =
				DLFileVersionLocalServiceUtil.getLatestFileVersion(
					fileEntry.getFileEntryId(), false);

			SyncDLObjectLocalServiceUtil.addSyncDLObject(
				dlFileVersion.getCompanyId(), modifiedTime,
				dlFileVersion.getRepositoryId(), fileEntry.getFolderId(),
				dlFileVersion.getTitle(), dlFileVersion.getExtension(),
				dlFileVersion.getMimeType(), dlFileVersion.getDescription(),
				dlFileVersion.getChangeLog(), dlFileVersion.getExtraSettings(),
				dlFileVersion.getVersion(), dlFileVersion.getSize(),
				SyncUtil.getChecksum(dlFileVersion), event, lockExpirationDate,
				lockUserId, lockUserName, type, fileEntry.getFileEntryId(),
				fileEntry.getUuid());
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

			SyncDLObjectLocalServiceUtil.addSyncDLObject(
				folder.getCompanyId(), modifiedTime, folder.getRepositoryId(),
				folder.getParentFolderId(), folder.getName(), StringPool.BLANK,
				StringPool.BLANK, folder.getDescription(), StringPool.BLANK,
				StringPool.BLANK, "-1", 0, StringPool.BLANK, event, null, 0,
				StringPool.BLANK, type, folder.getFolderId(), folder.getUuid());
		}
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		String event = message.getString("event");
		long modifiedTime = message.getLong("modifiedTime");
		String type = message.getString("type");
		long typePK = message.getLong("typePK");

		addSyncDLObject(modifiedTime, event, type, typePK);
	}

}